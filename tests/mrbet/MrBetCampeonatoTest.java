package mrbet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MrBetCampeonatoTest {
    private MrBet sistema;

    @BeforeEach
    public void setup() {
        sistema = new MrBet();

        sistema.incluirTime("250_PB", "Nacional de Patos", "Canário");
        sistema.incluirTime("252_PB", "Sport Lagoa Seca", "Carneiro");
        sistema.incluirTime("002_RJ", "Clube de Regatas do Flamengo", "Urubu");
        sistema.incluirTime("105_PB", "Sociedade Recreativa de Monteiro (SOCREMO)", "Gavião");
    }

    @Test
    public void testeAdicionarCampeonato() {
        sistema.adicionarCampeonato("Brasileirão série A 2023", 10);

        Exception err = assertThrows(IllegalArgumentException.class,
                () -> sistema.adicionarCampeonato("Brasileirão série A 2023", 10));
        assertEquals(err.getMessage(), "CAMPEONATO JÁ EXISTE!");

        err = assertThrows(IllegalArgumentException.class,
                () -> sistema.adicionarCampeonato("braSILeirão SÉRIE a 2023", 10));
        assertEquals(err.getMessage(), "CAMPEONATO JÁ EXISTE!");
    }

    @Test
    public void testeIncluirTimeEmCampeonato() {
        sistema.adicionarCampeonato("Brasileirão série A 2023", 10);

        sistema.adicionarTimeCampeonato("250_PB", "Brasileirão série A 2023");
        sistema.adicionarTimeCampeonato("252_PB", "Brasileirão série A 2023");
        // não deve dar erro
        sistema.adicionarTimeCampeonato("252_PB", "Brasileirão série A 2023");
    }

    @Test
    public void testeIncluirTimeInvalidoEmCampeonato() {
        sistema.adicionarCampeonato("Brasileirão série A 2023", 10);

        sistema.adicionarTimeCampeonato("250_PB", "Brasileirão série A 2023");

        Exception err = assertThrows(IllegalArgumentException.class,
                () -> sistema.adicionarTimeCampeonato("005_PB", "Brasileirão série A 2023"));
        assertEquals(err.getMessage(), "TIME NÃO EXISTE!");
    }

    @Test
    public void testeIncluirTimeEmCampeonatoInvalido() {
        sistema.adicionarCampeonato("Brasileirão série A 2023", 10);

        Exception err = assertThrows(IllegalArgumentException.class,
                () -> sistema.adicionarTimeCampeonato("252_PB", "Brasileirão série D 2023"));
        assertEquals(err.getMessage(), "CAMPEONATO NÃO EXISTE!");
    }

    @Test
    public void testeIncluirTimeEmCampeonatoCheio() {
        sistema.adicionarCampeonato("Brasileirão série A 2023", 1);

        sistema.adicionarTimeCampeonato("252_PB", "Brasileirão série A 2023");

        Exception err = assertThrows(IllegalArgumentException.class,
                () -> sistema.adicionarTimeCampeonato("250_PB", "Brasileirão série A 2023"));
        assertEquals(err.getMessage(), "TODOS OS TIMES DESSE CAMPEONATO JÁ FORAM INCLUÍDOS!");
    }

    @Test
    public void testeTimeEstaEmCampeonato() {
        sistema.adicionarCampeonato("Copa do Nordeste 2023", 10);

        sistema.adicionarTimeCampeonato("250_PB", "Copa do Nordeste 2023");

        assertTrue(sistema.isTimeEmCampeonato("250_PB", "Copa do Nordeste 2023"));
        assertTrue(sistema.isTimeEmCampeonato("250_PB", "copa do NORDESTE 2023"));
        assertFalse(sistema.isTimeEmCampeonato("252_PB", "Copa do Nordeste 2023"));
    }

    @Test
    public void testeTimeEstaEmCampeonatoInvalido() {
        sistema.adicionarCampeonato("Brasileirão série A 2023", 10);

        sistema.adicionarTimeCampeonato("252_PB", "Brasileirão série A 2023");

        Exception err = assertThrows(IllegalArgumentException.class,
                () -> sistema.isTimeEmCampeonato("252_PB", "Brasileirão série D 2023"));
        assertEquals(err.getMessage(), "CAMPEONATO NÃO EXISTE!");
    }

    @Test
    public void testeTimeInvalidoEstaEmCampeonato() {
        sistema.adicionarCampeonato("Copa do Nordeste 2023", 10);

        sistema.adicionarTimeCampeonato("250_PB", "Copa do Nordeste 2023");

        Exception err = assertThrows(IllegalArgumentException.class,
                () -> sistema.isTimeEmCampeonato("005_PB", "Copa do Nordeste 2023"));
        assertEquals(err.getMessage(), "TIME NÃO EXISTE!");
    }

}
