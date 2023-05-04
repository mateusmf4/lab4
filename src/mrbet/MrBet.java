package mrbet;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Representa um sistema de apostas de time em campeonatos,
 * sendo esse sistema chamdo de Mr.Bet
 * 
 * @author Mateus
 */
public class MrBet {
    private HashMap<String, Time> times;
    private HashMap<String, Campeonato> campeonatos;
    private ArrayList<Aposta> apostas;

    public MrBet() {
        times = new HashMap<>();
        campeonatos = new HashMap<>();
        apostas = new ArrayList<>();
    }

    private Time pegarTime(String codigo) throws IllegalArgumentException {
        if (!times.containsKey(codigo)) {
            throw new IllegalArgumentException("TIME NÃO EXISTE!");
        }
        return times.get(codigo);
    }

    private Campeonato pegarCampeonato(String codigo) throws IllegalArgumentException {
        codigo = codigo.toLowerCase();
        if (!campeonatos.containsKey(codigo)) {
            throw new IllegalArgumentException("CAMPEONATO NÃO EXISTE!");
        }
        return campeonatos.get(codigo);
    }

    public void incluirTime(String codigo, String nome, String mascote) throws IllegalArgumentException {
        if (times.containsKey(codigo)) {
            throw new IllegalArgumentException("TIME JÁ EXISTE!");
        }
        times.put(codigo, new Time(codigo, nome, mascote));
    }

    public String mostrarTime(String codigo) {
        return pegarTime(codigo).toString();
    }

    public void adicionarCampeonato(String nome, int participantes) throws IllegalArgumentException {
        if (campeonatos.containsKey(nome.toLowerCase())) {
            throw new IllegalArgumentException("CAMPEONATO JÁ EXISTE!");
        }
        campeonatos.put(nome.toLowerCase(), new Campeonato(nome, participantes));
    }

    public void adicionarTimeCampeonato(String codigoTime, String codigoCamp) throws IllegalArgumentException {
        Time time = pegarTime(codigoTime);
        Campeonato campeonato = pegarCampeonato(codigoCamp);

        campeonato.adicionarTime(time);
    }

    public boolean isTimeEmCampeonato(String codigoTime, String codigoCamp) throws IllegalArgumentException {
        Time time = pegarTime(codigoTime);
        Campeonato campeonato = pegarCampeonato(codigoCamp);

        return campeonato.contemTime(time);
    }

    public String mostrarTimeCampeonatos(String codigo) throws IllegalArgumentException {
        Time time = pegarTime(codigo);
        
        String resultado = time.getNome() + ":";
        for (Campeonato camp : campeonatos.values()) {
            if (camp.contemTime(time)) {
                resultado += "\n* " + camp.toString();
            }
        }
        return resultado;
    }

    public void adicionarAposta(String codigoTime, String codigoCamp, int colocacao, double valor) {
        Time time = pegarTime(codigoTime);
        Campeonato camp = pegarCampeonato(codigoCamp);
        if (colocacao > camp.getParticipantes()) {
            throw new IllegalArgumentException("COLOCAÇÃO INVÁLIDA!");
        }
        apostas.add(new Aposta(time, camp, colocacao, valor));
    }

    public String mostrarApostas() {
        String resultado = "Apostas:";
        for (int i = 0; i < apostas.size(); ++i) {
            resultado += String.format("\n\n%d. %s", i + 1, apostas.get(i).toString());
        }
        return resultado;
    }
}
