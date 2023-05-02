package mrbet;

import java.util.HashSet;

public class Campeonato {
    private String nome;
    private int participantes;
    private HashSet<Time> times;


    public Campeonato(String nome, int participantes) {
        this.nome = nome;
        this.participantes = participantes;
        times = new HashSet<>();
    }

    void adicionarTime(Time time) throws IllegalArgumentException {
        if (times.contains(time)) {
            throw new IllegalArgumentException("TIME JÁ ESTÁ NO CAMPEONATO!");
        }
        if (times.size() >= participantes) {
            throw new IllegalArgumentException("TODOS OS TIMES DESSE CAMPEONATO JÁ FORAM INCLUÍDOS!");
        }
        times.add(time);
    }
}
