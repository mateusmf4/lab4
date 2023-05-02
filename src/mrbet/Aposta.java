package mrbet;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Aposta {
    private Time time;
    private Campeonato campeonato;
    private int colocacao;
    private double valor;

    public Aposta(Time time, Campeonato campeonato, int colocacao, double valor) {
        this.time = time;
        this.campeonato = campeonato;
        this.colocacao = colocacao;
        this.valor = valor;
    }

    @Override
    public String toString() {
        // muita tristeza esse java
        final DecimalFormat nf = new DecimalFormat("#0.00", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        final String num = nf.format(valor);
        
        return String.format(
            "%s\n" +
            "%s\n" +
            "%d/%d\n" +
            "R$ %s",
            time.toString(),
            campeonato.getNome(),
            colocacao, campeonato.getParticipantes(),
            num
        );
    }
}