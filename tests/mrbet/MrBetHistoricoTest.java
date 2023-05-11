package mrbet;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MrBetHistoricoTest {
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
    public void testeHistoricoSimples() {
        assertEquals(
            "Participação mais frequente em campeonatos\n" +
            "[250_PB] Nacional de Patos / Canário\n" +
            "\n" +
            "Ainda não participou de campeonato\n" +
            "[105_PB] Sociedade Recreativa de Monteiro (SOCREMO) / Gavião\n" +
            "\n" +
            "Popularidade em apostas\n",
            sistema.mostrarHistorico()
        );

        sistema.adicionarTimeCampeonato("105_PB", "Nordestão");

        assertEquals(
            "Participação mais frequente em campeonatos\n" +
            "[250_PB] Nacional de Patos / Canário\n" +
            "\n" +
            "Ainda não participou de campeonato\n" +
            "\n" +
            "Popularidade em apostas\n",
            sistema.mostrarHistorico()
        );
    }

    @Test
    public void testeHistoricoComAposta() {
        sistema.adicionarAposta("250_PB", "Nordestão", 1, 2000);
        sistema.adicionarAposta("250_PB", "Nordestão", 1, 2300);
        sistema.adicionarAposta("250_PB", "Nordestão", 5, 300);
        sistema.adicionarAposta("250_PB", "Brasileirão", 1, 10);
        
        sistema.adicionarAposta("252_PB", "Nordestão", 1, 1000);
        
        sistema.adicionarAposta("002_RJ", "Brasileirão", 2, 1000);
        
        assertEquals(
            "Participação mais frequente em campeonatos\n" +
            "[250_PB] Nacional de Patos / Canário\n" +
            "\n" +
            "Ainda não participou de campeonato\n" +
            "[105_PB] Sociedade Recreativa de Monteiro (SOCREMO) / Gavião\n" +
            "\n" +
            "Popularidade em apostas\n" +
            "Sport Lagoa Seca / 1\n" +
            "Nacional de Patos / 3\n",
            sistema.mostrarHistorico()
        );
    }
}
