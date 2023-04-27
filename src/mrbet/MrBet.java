package mrbet;

import java.util.HashMap;

public class MrBet {
    private HashMap<String, Time> times;

    public MrBet() {
        times = new HashMap<String, Time>();
    }

    public void incluirTime(String codigo, String nome, String mascote) throws IllegalArgumentException {
        if (times.containsKey(codigo)) {
            throw new IllegalArgumentException("TIME JÁ EXISTE!");
        }
        times.put(codigo, new Time(codigo, nome, mascote));
    }
}
