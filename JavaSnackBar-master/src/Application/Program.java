package Application;

import java.util.ArrayList;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {

        ArrayList<String> produtos = new ArrayList<>();
        ArrayList<Integer> quantidades_cada = new ArrayList<>();

        Scanner sc = new Scanner(System.in);

        System.out.println("BEM VINDO À LANCHONETE!\n");


        int codigo;
        int quantidade_total = 0;
        double valor_total = 0;

        do {
            System.out.println("ESCOLHA SEUS PRODUTOS:");
            System.out.println("1   Cachorro Quente   R$ 4.00");
            System.out.println("2   X-Salada           R$ 4.50");
            System.out.println("3   X-Bacon            R$ 5.00");
            System.out.println("4   Torrada Simples    R$ 2.00");
            System.out.println("5   Refrigerante       R$ 1.50");
            System.out.println("\nDigite -1 para encerrar a compra.");

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
