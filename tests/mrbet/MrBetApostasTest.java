package mrbet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MrBetApostasTest {
    private MrBet sistema;

    @BeforeEach
    public void setup() {
        sistema = new MrBet();

        sistema.incluirTime("250_PB", "Nacional de Patos", "Canário");
        sistema.incluirTime("252_PB", "Sport Lagoa Seca", "Carneiro");
        sistema.incluirTime("002_RJ", "Clube de Regatas do Flamengo", "Urubu");
        sistema.incluirTime("105_PB", "Sociedade Recreativa de Monteiro (SOCREMO)", "Gavião");

        sistema.adicionarCampeonato("Nordestão", 10);
        sistema.adicionarCampeonato("Brasileirão", 20);

        sistema.adicionarTimeCampeonato("250_PB", "Nordestão");
        sistema.adicionarTimeCampeonato("250_PB", "Brasileirão");

        sistema.adicionarTimeCampeonato("252_PB", "Nordestão");
        
        sistema.adicionarTimeCampeonato("002_RJ", "Brasileirão");
    }

    @Test
    public void testeAdicionarAposta() {
        sistema.adicionarAposta("250_PB", "Nordestão", 2, 500);

        sistema.adicionarAposta("250_PB", "Nordestão", 10, 500);
    }

    @Test
    public void testeAdicionarApostaColocacaoInvalida() {
        Exception err = assertThrows(IllegalArgumentException.class, () -> sistema.adicionarAposta("250_PB", "Nordestão", 11, 500));
        assertEquals("COLOCAÇÃO INVÁLIDA!", err.getMessage());

        err = assertThrows(IllegalArgumentException.class, () -> sistema.adicionarAposta("250_PB", "Nordestão", 0, 500));
        assertEquals("COLOCAÇÃO INVÁLIDA!", err.getMessage());
    }

    @Test
    public void testeAdicionarApostaTimeInvalido() {
        Exception err = assertThrows(IllegalArgumentException.class, () -> sistema.adicionarAposta("250_RN", "Nordestão", 5, 500));
        assertEquals("TIME NÃO EXISTE!", err.getMessage());
    }

    @Test
    public void testeAdicionarApostaCampeonatoInvalido() {
        Exception err = assertThrows(IllegalArgumentException.class, () -> sistema.adicionarAposta("250_PB", "Festinha da júlia", 1, 500));
        assertEquals("CAMPEONATO NÃO EXISTE!", err.getMessage());
    }

    @Test
    public void testeMostrarApostas() {
        sistema.adicionarAposta("250_PB", "Nordestão", 2, 500);

        sistema.adicionarAposta("250_PB", "Nordestão", 10, 500);

        sistema.adicionarAposta("002_RJ", "Brasileirão", 1, 1000);

        assertEquals(
            "Apostas:\n" +
            "\n" +
            "1. [250_PB] Nacional de Patos / Canário\n" +
            "Nordestão\n" +
            "2/10\n" +
            "R$ 500.00\n" +
            "\n" +
            "2. [250_PB] Nacional de Patos / Canário\n" +
            "Nordestão\n" +
            "10/10\n" +
            "R$ 500.00\n" +
            "\n" +
            "3. [002_RJ] Clube de Regatas do Flamengo / Urubu\n" +
            "Brasileirão\n" +
            "1/20\n" +
            "R$ 1000.00",
            sistema.mostrarApostas()
        );
    }
}
