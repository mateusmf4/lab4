package mrbet;

import java.util.HashMap;

public class MrBet {
    private HashMap<String, Time> times;
    private HashMap<String, Campeonato> campeonatos;

    public MrBet() {
        times = new HashMap<>();
        campeonatos = new HashMap<>();
    }

    public void incluirTime(String codigo, String nome, String mascote) throws IllegalArgumentException {
        if (times.containsKey(codigo)) {
            throw new IllegalArgumentException("TIME JÁ EXISTE!");
        }
        times.put(codigo, new Time(codigo, nome, mascote));
    }

    public String mostrarTime(String codigo) throws IllegalArgumentException {
        if (!times.containsKey(codigo)) {
            throw new IllegalArgumentException("TIME NÃO EXISTE!");
        }
        return times.get(codigo).toString();
    }

    public void adicionarCampeonato(String nome, int participantes) throws IllegalArgumentException {
        if (campeonatos.containsKey(nome.toLowerCase())) {
            throw new IllegalArgumentException("CAMPEONATO JÁ EXISTE!");
        }
        campeonatos.put(nome.toLowerCase(), new Campeonato(nome, participantes));
    }

    public void adicionarTimeCampeonato(String codigoTime, String codigoCamp) throws IllegalArgumentException {
        if (!times.containsKey(codigoTime)) {
            throw new IllegalArgumentException("TIME NÃO EXISTE!");
        }
        Time time = times.get(codigoTime);

        if (!campeonatos.containsKey(codigoCamp.toLowerCase())) {
            throw new IllegalArgumentException("CAMPEONATO NÃO EXISTE!");
        }
        Campeonato campeonato = campeonatos.get(codigoCamp.toLowerCase());

        campeonato.adicionarTime(time);
    }

    public boolean isTimeEmCampeonato(String codigoTime, String codigoCamp) throws IllegalArgumentException {
        if (!times.containsKey(codigoTime)) {
            throw new IllegalArgumentException("O TIME NÃO EXISTE!");
        }
        Time time = times.get(codigoTime);

        if (!campeonatos.containsKey(codigoCamp.toLowerCase())) {
            throw new IllegalArgumentException("O CAMPEONATO NÃO EXISTE!");
        }
        Campeonato campeonato = campeonatos.get(codigoCamp.toLowerCase());

        return campeonato.contemTime(time);
    }

    public String mostrarTimeCampeonatos(String codigo) throws IllegalArgumentException {
        if (!times.containsKey(codigo)) {
            throw new IllegalArgumentException("TIME NÃO EXISTE!");
        }

        Time time = times.get(codigo);
        
        String resultado = time.getNome() + ":";
        for (Campeonato camp : campeonatos.values()) {
            if (camp.contemTime(time)) {
                resultado += "\n* " + camp.toString();
            }
        }
        return resultado;
    }
}
