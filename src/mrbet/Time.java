package mrbet;

/**
 * Representa um time com um codigo, nome e mascote.
 * 
 * @author Mateus
 */
public class Time {
    private String codigo;
    private String nome;
    private String mascote;

    /**
     * Cria um time com um código identificador, nome e mascote.
     */
    public Time(String identificador, String nome, String mascote) {
        this.codigo = identificador;
        this.nome = nome;
        this.mascote = mascote;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Time other = (Time) obj;
        if (codigo == null) {
            if (other.codigo != null)
                return false;
        } else if (!codigo.equals(other.codigo))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s / %s", codigo, nome, mascote);
    }

    public String getNome() {
        return nome;
    }

    public String getCodigo() {
        return codigo;
    }
}
