package mrbet;

import java.util.HashSet;

/**
 * Representa um campeonato de times, com um número máximo de participantes.
 * 
 * @author Mateus
 */
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

    public String getNome() {
        return nome;
    }

    public int getParticipantes() {
        return participantes;
    }
}
