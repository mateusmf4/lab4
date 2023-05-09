package mrbet;

import java.util.Scanner;

public class Main {
    private Scanner scanner;
    private MrBet sistema;

    public static void main(String[] args) {
        Main inst = new Main();
        while (true) {
            inst.imprimirMenu();
            inst.pegarOpcao();
        }
    }

    private Main() {
        scanner = new Scanner(System.in);
        sistema = new MrBet();

        sistema.incluirTime("250_PB", "Nacional de Patos", "Canário");
        sistema.incluirTime("210_RN", "Natalense sla", "Lobo");

        sistema.adicionarCampeonato("Brasileirão Série A 2023", 20);
        sistema.adicionarCampeonato("Josefino", 4);

        sistema.adicionarTimeCampeonato("250_PB", "brasileirão série a 2023");
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
            "\n" +
            "Opção> "
        );
    }

    private void pegarOpcao() {
        String opcao = scanner.nextLine();

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
        return Integer.parseInt(scanner.nextLine());
    }

    private String lerLinha(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private double lerDouble(String prompt) {
        System.out.print(prompt);
        return Double.parseDouble(scanner.nextLine());
    }

    private void incluirTime() {
        String codigo = lerLinha("Código: ");
        String nome = lerLinha("Nome: ");
        String mascote = lerLinha("Mascote: ");
        try {
            sistema.incluirTime(codigo, nome, mascote);
        } catch (IllegalArgumentException err) {
            System.out.println(err.getMessage());
            return;
        }
        System.out.println("INCLUSÃO REALIZADA!");
    }

    private void recuperarTime() {
        String codigo = lerLinha("Código: ");
        try {
            String time = sistema.mostrarTime(codigo);
            System.out.println(time);
        } catch (IllegalArgumentException err) {
            System.out.println(err.getMessage());
        }
    }

    private void adicionarCampeonato() {
        String nome = lerLinha("Campeonato: ");
        int participantes = lerInt("Participantes: ");
        try {
            sistema.adicionarCampeonato(nome, participantes);
        } catch (IllegalArgumentException err) {
            System.out.println(err.getMessage());
            return;
        }
        System.out.println("CAMPEONATO ADICIONADO!");
    }

    private void boraIncluirTimeEmCampeonato() {
        String opcao = lerLinha("(I) Incluir time em campeonato ou (V) Verificar se time está em campeonato? ");

        String codigo = lerLinha("Código: ");
        String campeonato = lerLinha("Campeonato: ");

        try {
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
        } catch (IllegalArgumentException err) {
            System.out.println(err.getMessage());
        }
    }

    private void exibirCampeonatosDeTime() {
        String codigo = lerLinha("Time: ");
        try {
            System.out.println("\n" + sistema.mostrarTimeCampeonatos(codigo));
        } catch (IllegalArgumentException err) {
            System.out.println(err.getMessage());
        }
    }

    private void tentarSorteStatus() {
        String opcao = lerLinha("(A)Apostar ou (S)Status das Apostas? ");
        
        if (opcao.equals("A")) {
            adicionarAposta();
        } else if (opcao.equals("S")) {
            mostrarApostas();
        }
    }
    
    private void adicionarAposta() {
        String codigo = lerLinha("Código: ");
        String campeonato = lerLinha("Campeonato: ");
        int colocacao = lerInt("Colocação: ");
        double valor = lerDouble("Valor: R$ ");
        try {
            sistema.adicionarAposta(codigo, campeonato, colocacao, valor);
            System.out.println("APOSTA REGISTRADA!");  
        } catch (IllegalArgumentException err) {
            System.out.println(err.getMessage());
        }
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
