package mrbet;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Representa um sistema de apostas de time em campeonatos,
 * sendo esse sistema chamdo de MrBet
 * 
 * @author Mateus
 */
public class MrBet {
    private HashMap<String, Time> times = new HashMap<>();
    private HashMap<String, Campeonato> campeonatos = new HashMap<>();
    private ArrayList<Aposta> apostas = new ArrayList<>();

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

    /**
     * Adiciona um time no sistema do MrBet.
     * 
     * @param codigo Código único do time
     * @param nome Nome do time
     * @param mascote Mascote do time
     * @throws IllegalArgumentException Se o time já existe no sistema.
     */
    public void incluirTime(String codigo, String nome, String mascote) throws IllegalArgumentException {
        if (times.containsKey(codigo)) {
            throw new IllegalArgumentException("TIME JÁ EXISTE!");
        }
        times.put(codigo, new Time(codigo, nome, mascote));
    }

    /**
     * Mostra a representação de um time como string.
     * 
     * @param codigo Código do time
     * @return A representação. 
     */
    public String mostrarTime(String codigo) {
        return pegarTime(codigo).toString();
    }

    /**
     * Adiciona um campeonato no sistema do MrBet.
     * 
     * @param nome Nome do campeonato
     * @param participantes Número de participantes do campeonato
     * @throws IllegalArgumentException Se um campeonato com o mesmo nome já existe no sistema.
     */
    public void adicionarCampeonato(String nome, int participantes) throws IllegalArgumentException {
        if (campeonatos.containsKey(nome.toLowerCase())) {
            throw new IllegalArgumentException("CAMPEONATO JÁ EXISTE!");
        }
        campeonatos.put(nome.toLowerCase(), new Campeonato(nome, participantes));
    }

    /**
     * Adiciona um time em um campeonato do sistema.
     * 
     * @param codigoTime Código do time
     * @param nomeCamp Nome do campeonato
     * @throws IllegalArgumentException Se o time ou o campeonato não existem no sistema.
     */
    public void adicionarTimeCampeonato(String codigoTime, String nomeCamp) throws IllegalArgumentException {
        Time time = pegarTime(codigoTime);
        Campeonato campeonato = pegarCampeonato(nomeCamp);

        campeonato.adicionarTime(time);
    }

    /**
     * Verifica se um time está em um campeonato.
     * 
     * @param codigoTime Código do time
     * @param nomeCamp Nome do campeonato
     * @return True se tiver, false se não
     * @throws IllegalArgumentException Se o time ou o campeonato não existem no sistema.
     */
    public boolean isTimeEmCampeonato(String codigoTime, String nomeCamp) throws IllegalArgumentException {
        Time time = pegarTime(codigoTime);
        Campeonato campeonato = pegarCampeonato(nomeCamp);

        return campeonato.contemTime(time);
    }

    /**
     * Retorna uma string com todos os times em um campeonato.
     * 
     * @param nomeCamp Nome do campeonato
     * @return A string com todos os times.
     * @throws IllegalArgumentException Se o campeonato não existe no sistema.
     */
    public String mostrarTimeCampeonatos(String nomeCamp) throws IllegalArgumentException {
        Time time = pegarTime(nomeCamp);
        
        String resultado = time.getNome() + ":";
        for (Campeonato camp : campeonatos.values()) {
            if (camp.contemTime(time)) {
                resultado += "\n* " + camp.toString();
            }
        }
        return resultado;
    }

    /**
     * Adiciona uma aposta no sistema.
     * 
     * @param codigoTime Código do time
     * @param nomeCamp Nome do campeonato
     * @param colocacao Colocação do time
     * @param valor Valor em reais da aposta
     */
    public void adicionarAposta(String codigoTime, String nomeCamp, int colocacao, double valor) {
        Time time = pegarTime(codigoTime);
        Campeonato camp = pegarCampeonato(nomeCamp);
        if (colocacao > camp.getParticipantes()) {
            throw new IllegalArgumentException("COLOCAÇÃO INVÁLIDA!");
        }
        apostas.add(new Aposta(time, camp, colocacao, valor));
    }

    /**
     * Retorna todas as apostas do sistema.
     * 
     * @return A string.
     */
    public String mostrarApostas() {
        String resultado = "Apostas:";
        for (int i = 0; i < apostas.size(); ++i) {
            resultado += String.format("\n\n%d. %s", i + 1, apostas.get(i).toString());
        }
        return resultado;
    }
}
