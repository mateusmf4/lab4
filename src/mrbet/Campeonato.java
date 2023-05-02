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

    public void adicionarTime(Time time) throws IllegalArgumentException {
        if (times.contains(time)) {
            throw new IllegalArgumentException("TIME JÁ ESTÁ NO CAMPEONATO!");
        }
        if (times.size() >= participantes) {
            throw new IllegalArgumentException("TODOS OS TIMES DESSE CAMPEONATO JÁ FORAM INCLUÍDOS!");
        }
        times.add(time);
    }

    public boolean contemTime(Time time) {
        return times.contains(time);
    }

    @Override
    public String toString() {
        return String.format("%s - %d/%d", nome, times.size(), participantes);
    }
}
