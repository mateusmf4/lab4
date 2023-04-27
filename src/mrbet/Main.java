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

    private void incluirTime() {
        String codigo = lerLinha("Código: ");
        String nome = lerLinha("Nome: ");
        String mascote = lerLinha("Mascote: ");
        try {
            sistema.incluirTime(codigo, nome, mascote);
        } catch (IllegalArgumentException err) {
            System.out.println("TIME JÁ EXISTE!");
            return;
        }
        System.out.println("INCLUSÃO REALIZADA!");
    }

    private void boraIncluirTimeEmCampeonato() {
        
    }

    private void recuperarTime() {

    }

    private void adicionarCampeonato() {
        
    }
}
