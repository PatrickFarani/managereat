package Application;

import java.util.ArrayList;
import java.util.Scanner;

public class Program {
    
    // Códigos de cor ANSI
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String YELLOW = "\u001B[33m";
    public static final String GREEN = "\u001B[32m";
    public static final String CYAN = "\u001B[36m";
    public static final String BOLD = "\u001B[1m";

    public static void main(String[] args) {

        ArrayList<String> produtos = new ArrayList<>();
        ArrayList<Integer> quantidades_cada = new ArrayList<>();

        Scanner sc = new Scanner(System.in);

        // Logo ManagerEAT com arte ASCII do X-Salada
        System.out.println(RED + BOLD + "╔══════════════════════════════════════╗" + RESET);
        System.out.println(RED + BOLD + "║" + YELLOW + "            🍔 ManagerEAT 🍔           " + RED + "║" + RESET);
        System.out.println(RED + BOLD + "╠══════════════════════════════════════╣" + RESET);
        System.out.println(RED + BOLD + "║" + YELLOW + "     ████████   X-SALADA   ████████    " + RED + "║" + RESET);
        System.out.println(RED + BOLD + "║" + YELLOW + "    ██      ██ 🥬🍅🧀🥓 ██      ██   " + RED + "║" + RESET);
        System.out.println(RED + BOLD + "║" + YELLOW + "     ████████  DELICIOSO   ████████    " + RED + "║" + RESET);
        System.out.println(RED + BOLD + "╚══════════════════════════════════════╝" + RESET);
        System.out.println(YELLOW + BOLD + "\n🎉 BEM-VINDO AO ManagerEAT! 🎉\n" + RESET);


        int codigo;
        int quantidade_total = 0;
        double valor_total = 0;

        do {
            System.out.println(CYAN + BOLD + "═══════════ CARDÁPIO ManagerEAT ═══════════" + RESET);
            System.out.println(YELLOW + "1  🍔 Big Mac ManagerEAT      " + GREEN + "R$ 18,50" + RESET);
            System.out.println(YELLOW + "2  🥗 X-Salada ManagerEAT     " + GREEN + "R$ 15,00" + RESET);
            System.out.println(YELLOW + "3  🥓 Bacon Burger Premium    " + GREEN + "R$ 22,00" + RESET);
            System.out.println(YELLOW + "4  🍟 Batata Frita Grande     " + GREEN + "R$ 12,00" + RESET);
            System.out.println(YELLOW + "5  🥤 Combo Refrigerante      " + GREEN + "R$ 10,50" + RESET);
            System.out.println(CYAN + "═══════════════════════════════════════════" + RESET);
            System.out.println(RED + "\n✋ Digite -1 para encerrar a compra." + RESET);

            codigo = sc.nextInt();

            if (codigo == -1) {
                break;
            }

            switch (codigo) {
                case 1:
                    System.out.println("Quantas unidades deseja?");
                    int quantidade1 = sc.nextInt();
                    valor_total += quantidade1 * 4.00;
                    quantidade_total += quantidade1;
                    produtos.add("Cachorro Quente");
                    quantidades_cada.add(quantidade1);
                    break;

                case 2:
                    System.out.println("Quantas unidades deseja?");
                    int quantidade2 = sc.nextInt();
                    valor_total += quantidade2 * 4.50;
                    quantidade_total += quantidade2;
                    produtos.add("X-Salada");
                    quantidades_cada.add(quantidade2);
                    break;

                case 3:
                    System.out.println("Quantas unidades deseja?");
                    int quantidade3 = sc.nextInt();
                    valor_total += quantidade3 * 5.00;
                    quantidade_total += quantidade3;
                    produtos.add("X-Bacon");
                    quantidades_cada.add(quantidade3);
                    break;

                case 4:
                    System.out.println("Quantas unidades deseja?");
                    int quantidade4 = sc.nextInt();
                    valor_total += quantidade4 * 2.00;
                    quantidade_total += quantidade4;
                    produtos.add("Torrada Simples");
                    quantidades_cada.add(quantidade4);
                    break;

                case 5:
                    System.out.println("Quantas unidades deseja?");
                    int quantidade5 = sc.nextInt();
                    valor_total += quantidade5 * 1.50;
                    quantidade_total += quantidade5;
                    produtos.add("Refrigerante");
                    quantidades_cada.add(quantidade5);
                    break;

                default:
                    System.out.println("Código informado é inválido!\n");
                    break;
            }
        } while (codigo != -1);

        System.out.println("\nNOTA DA COMPRA:");
        System.out.println("-------------------");
        System.out.println("Produtos comprados:");
        for (int i = 0; i < produtos.size(); i++) {
            System.out.printf("- %s: %d\n", produtos.get(i), quantidades_cada.get(i));
        }
        System.out.println("Total de produtos comprados: " + quantidade_total);
        System.out.println("---------------------------");
        System.out.printf("VALOR TOTAL A PAGAR: R$ %.2f\n", valor_total);

        sc.close();
    }
}
