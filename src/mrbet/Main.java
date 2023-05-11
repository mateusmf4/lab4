package mrbet;

import java.util.Scanner;

public class Main {
    private Scanner scanner;
    private MrBet sistema;

    public static void main(String[] args) {
        Main inst = new Main();
        while (true) {
            inst.imprimirMenu();
            try {
                inst.pegarOpcao();
            } catch (IllegalArgumentException err) {
                System.out.println(err.getMessage() + "\n");
            }
        }
    }

    private Main() {
        scanner = new Scanner(System.in);
        sistema = new MrBet();
    }

    private void imprimirMenu() {
        System.out.print(
            "(M)Minha inclusão de times\n" +
            "(R)Recuperar time\n" +
            "(.)Adicionar campeonato\n" +
            "(B)Bora incluir time em campeonato e Verificar se time está em campeonato\n" +
            "(E)Exibir campeonatos que o time participa\n" +
            "(T)Tentar a sorte e status\n" +
            "(!)Já pode fechar o programa!\n" +
            "(H)Histórico\n" +
            "\n"
        );
    }

    private void pegarOpcao() {
        String opcao = lerLinha("Opção> ").toUpperCase();

        switch (opcao) {
            case "M":
                incluirTime();
                break;
            case "R":
                recuperarTime();
                break;
            case ".":
                adicionarCampeonato();
                break;
            case "B":
                boraIncluirTimeEmCampeonato();
                break;
            case "E":
                exibirCampeonatosDeTime();
                break;
            case "T":
                tentarSorteStatus();
                break;
            case "!":
                sair();
                break;
            case "H":
                mostrarHistorico();
                break;
            default:
                System.out.println("ENTRADA INVALIDA!");
                break;
        }
        System.out.println();
    }

    private int lerInt(String prompt) {
        System.out.print(prompt);
        String line = scanner.nextLine();
        if (line.isBlank())
            throw new IllegalArgumentException("ENTRADA VAZIA!");
        return Integer.parseInt(line);
    }

    private String lerLinha(String prompt) {
        System.out.print(prompt);
        String line = scanner.nextLine();
        if (line.isBlank())
            throw new IllegalArgumentException("ENTRADA VAZIA!");
        return line;
    }

    private double lerDouble(String prompt) {
        System.out.print(prompt);
        String line = scanner.nextLine();
        if (line.isBlank())
            throw new IllegalArgumentException("ENTRADA VAZIA!");
        return Double.parseDouble(line);
    }

    private void incluirTime() {
        String codigo = lerLinha("Código: ");
        String nome = lerLinha("Nome: ");
        String mascote = lerLinha("Mascote: ");

        sistema.incluirTime(codigo, nome, mascote);
        System.out.println("INCLUSÃO REALIZADA!");
    }

    private void recuperarTime() {
        String codigo = lerLinha("Código: ");

        String time = sistema.mostrarTime(codigo);
        System.out.println(time);
    }

    private void adicionarCampeonato() {
        String nome = lerLinha("Campeonato: ");
        int participantes = lerInt("Participantes: ");

        sistema.adicionarCampeonato(nome, participantes);
        System.out.println("CAMPEONATO ADICIONADO!");
    }

    private void boraIncluirTimeEmCampeonato() {
        String opcao = lerLinha("(I) Incluir time em campeonato ou (V) Verificar se time está em campeonato? ");

        if (!opcao.equals("I") && !opcao.equals("V")) {
            System.out.println("OPÇÃO INVALIDA!");
            return;
        }

        String codigo = lerLinha("Código: ");
        String campeonato = lerLinha("Campeonato: ");

        if (opcao.equals("I")) {
            sistema.adicionarTimeCampeonato(codigo, campeonato);
            System.out.println("TIME INCLUÍDO NO CAMPEONATO!");
        } else if (opcao.equals("V")) {
            if (sistema.isTimeEmCampeonato(codigo, campeonato)) {
                System.out.println("O TIME ESTÁ NO CAMPEONATO!");
            } else {
                System.out.println("O TIME NÃO ESTÁ NO CAMPEONATO!");
            }
        }
    }

    private void exibirCampeonatosDeTime() {
        String codigo = lerLinha("Time: ");

        System.out.println("\n" + sistema.mostrarTimeCampeonatos(codigo));
    }

    private void tentarSorteStatus() {
        String opcao = lerLinha("(A)Apostar ou (S)Status das Apostas? ");
        
        if (opcao.equals("A")) {
            adicionarAposta();
        } else if (opcao.equals("S")) {
            mostrarApostas();
        } else {
            System.out.println("OPÇÃO INVALIDA!");
        }
    }
    
    private void adicionarAposta() {
        String codigo = lerLinha("Código: ");
        String campeonato = lerLinha("Campeonato: ");
        int colocacao = lerInt("Colocação: ");
        double valor = lerDouble("Valor: R$ ");

        sistema.adicionarAposta(codigo, campeonato, colocacao, valor);
        System.out.println("APOSTA REGISTRADA!");  
    }
    
    private void mostrarApostas() {
        System.out.println(sistema.mostrarApostas());
    }

    private void mostrarHistorico() {
        System.out.println(sistema.mostrarHistorico());
    }

    private void sair() {
        System.out.println("Até mais!");
        System.exit(0);
    }
}
