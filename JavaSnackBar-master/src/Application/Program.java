package Application;

import java.util.*;

public class Program {
    
    // Códigos de cor ANSI
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String YELLOW = "\u001B[33m";
    public static final String GREEN = "\u001B[32m";
    public static final String CYAN = "\u001B[36m";
    public static final String BOLD = "\u001B[1m";
    public static final String BLUE = "\u001B[34m";

    private static Scanner sc = new Scanner(System.in);
    private static Pedido pedidoAtual = new Pedido();
    
    // Ingredientes disponíveis para personalização
    private static ArrayList<Ingrediente> ingredientesDisponiveis = new ArrayList<>();

    public static void main(String[] args) {
        inicializarIngredientes();
        mostrarLogo();
        
        int opcao;
        do {
            mostrarMenuPrincipal();
            opcao = sc.nextInt();
            
            switch (opcao) {
                case 1:
                    fazerPedido();
                    break;
                case 2:
                    mostrarRelatórios();
                    break;
                case 3:
                    verPedidoAtual();
                    break;
                case 4:
                    finalizarPedido();
                    break;
                case 0:
                    System.out.println(YELLOW + BOLD + "\n🎉 Obrigado por usar o ManagerEAT! Volte sempre! 🎉" + RESET);
                    break;
                default:
                    System.out.println(RED + "❌ Opção inválida! Tente novamente." + RESET);
            }
        } while (opcao != 0);
        
        sc.close();
    }

    private static void mostrarLogo() {
        System.out.println(RED + BOLD + "╔══════════════════════════════════════╗" + RESET);
        System.out.println(RED + BOLD + "║" + YELLOW + "            🍔 ManagerEAT 🍔           " + RED + "║" + RESET);
        System.out.println(RED + BOLD + "╠══════════════════════════════════════╣" + RESET);
        System.out.println(RED + BOLD + "║" + YELLOW + "     ████████   X-SALADA   ████████    " + RED + "║" + RESET);
        System.out.println(RED + BOLD + "║" + YELLOW + "    ██      ██ 🥬🍅🧀🥓 ██      ██   " + RED + "║" + RESET);
        System.out.println(RED + BOLD + "║" + YELLOW + "     ████████  DELICIOSO   ████████    " + RED + "║" + RESET);
        System.out.println(RED + BOLD + "╚══════════════════════════════════════╝" + RESET);
        System.out.println(YELLOW + BOLD + "\n🎉 BEM-VINDO AO ManagerEAT PREMIUM! 🎉" + RESET);
        System.out.println(CYAN + "Sistema completo com personalização e relatórios!\n" + RESET);
    }
    
    private static void inicializarIngredientes() {
        ingredientesDisponiveis.add(new Ingrediente("Bacon Extra", 3.00, "proteína"));
        ingredientesDisponiveis.add(new Ingrediente("Queijo Extra", 2.50, "queijo"));
        ingredientesDisponiveis.add(new Ingrediente("Cebola Roxa", 1.50, "vegetal"));
        ingredientesDisponiveis.add(new Ingrediente("Tomate Extra", 1.00, "vegetal"));
        ingredientesDisponiveis.add(new Ingrediente("Alface Extra", 1.00, "vegetal"));
        ingredientesDisponiveis.add(new Ingrediente("Picles", 1.50, "vegetal"));
        ingredientesDisponiveis.add(new Ingrediente("Molho Barbecue", 2.00, "molho"));
        ingredientesDisponiveis.add(new Ingrediente("Molho Ranch", 2.00, "molho"));
        ingredientesDisponiveis.add(new Ingrediente("Pimenta Jalapeño", 2.50, "tempero"));
        ingredientesDisponiveis.add(new Ingrediente("Cheddar Cremoso", 3.50, "queijo"));
    }

    private static void mostrarMenuPrincipal() {
        System.out.println(CYAN + BOLD + "\n═══════════ MENU PRINCIPAL ManagerEAT ═══════════" + RESET);
        System.out.println(YELLOW + "1  🛒 Fazer Pedido" + RESET);
        System.out.println(YELLOW + "2  📊 Ver Relatórios e Estatísticas" + RESET);
        System.out.println(YELLOW + "3  👀 Ver Pedido Atual" + RESET);
        System.out.println(YELLOW + "4  💳 Finalizar Pedido (com cotação USD)" + RESET);
        System.out.println(RED + "0  🚪 Sair" + RESET);
        System.out.println(CYAN + "═══════════════════════════════════════════════" + RESET);
        System.out.print(BLUE + "Escolha uma opção: " + RESET);
    }


    private static void fazerPedido() {
        int codigo;
        do {
            mostrarCardapio();
            System.out.print(BLUE + "Escolha um item (0 para voltar): " + RESET);
            codigo = sc.nextInt();

            if (codigo == 0) {
                break;
            }

            Lanche lanche = criarLanche(codigo);
            if (lanche != null) {
                System.out.print("Quantas unidades deseja? ");
                int quantidade = sc.nextInt();
                lanche.setQuantidade(quantidade);
                
                // Opção de personalização
                System.out.print(YELLOW + "Deseja personalizar este lanche? (1=Sim, 0=Não): " + RESET);
                int personalizar = sc.nextInt();
                
                if (personalizar.equalsIgnoreCase("s")) {
                    personalizarLanche(lanche);
                }
                
                pedidoAtual.adicionarLanche(lanche);
                System.out.println(GREEN + "✅ Item adicionado ao pedido!" + RESET);
            }
        } while (codigo != 0);
    }

    private static void mostrarCardapio() {
        System.out.println(CYAN + BOLD + "\n═══════════ CARDÁPIO ManagerEAT ═══════════" + RESET);
        System.out.println(YELLOW + "1  🍔 Big Mac ManagerEAT      " + GREEN + "R$ 18,50" + RESET);
        System.out.println(YELLOW + "2  🥗 X-Salada ManagerEAT     " + GREEN + "R$ 15,00" + RESET);
        System.out.println(YELLOW + "3  🥓 Bacon Burger Premium    " + GREEN + "R$ 22,00" + RESET);
        System.out.println(YELLOW + "4  🍟 Batata Frita Grande     " + GREEN + "R$ 12,00" + RESET);
        System.out.println(YELLOW + "5  🥤 Combo Refrigerante      " + GREEN + "R$ 10,50" + RESET);
        System.out.println(CYAN + "═══════════════════════════════════════════" + RESET);
    }

    private static Lanche criarLanche(int codigo) {
        switch (codigo) {
            case 1:
                System.out.println(YELLOW + "🍔 Big Mac ManagerEAT selecionado!" + RESET);
                return new Lanche("Big Mac ManagerEAT", 18.50);
            case 2:
                System.out.println(YELLOW + "🥗 X-Salada ManagerEAT selecionado!" + RESET);
                return new Lanche("X-Salada ManagerEAT", 15.00);
            case 3:
                System.out.println(YELLOW + "🥓 Bacon Burger Premium selecionado!" + RESET);
                return new Lanche("Bacon Burger Premium", 22.00);
            case 4:
                System.out.println(YELLOW + "🍟 Batata Frita Grande selecionada!" + RESET);
                return new Lanche("Batata Frita Grande", 12.00);
            case 5:
                System.out.println(YELLOW + "🥤 Combo Refrigerante selecionado!" + RESET);
                return new Lanche("Combo Refrigerante", 10.50);
            default:
                System.out.println(RED + "❌ Código inválido!" + RESET);
                return null;
        }
    }

    private static void personalizarLanche(Lanche lanche) {
        System.out.println(CYAN + BOLD + "\n🔧 PERSONALIZAÇÃO DO LANCHE" + RESET);
        System.out.println(YELLOW + "Ingredientes atuais do " + lanche.getNome() + ":" + RESET);
        
        for (Ingrediente ing : lanche.getIngredientesBase()) {
            System.out.println(CYAN + "• " + ing.getNome() + RESET);
        }
        
        int opcao;
        do {
            System.out.println(YELLOW + BOLD + "\nOpções de personalização:" + RESET);
            System.out.println("1 - Adicionar ingrediente");
            System.out.println("2 - Remover ingrediente");
            System.out.println("0 - Finalizar personalização");
            System.out.print(BLUE + "Escolha: " + RESET);
            opcao = sc.nextInt();
            
            switch (opcao) {
                case 1:
                    adicionarIngrediente(lanche);
                    break;
                case 2:
                    removerIngrediente(lanche);
                    break;
                case 0:
                    System.out.println(GREEN + "✅ Personalização concluída!" + RESET);
                    break;
                default:
                    System.out.println(RED + "❌ Opção inválida!" + RESET);
            }
        } while (opcao != 0);
    }

    private static void adicionarIngrediente(Lanche lanche) {
        System.out.println(YELLOW + BOLD + "\n🍯 INGREDIENTES EXTRAS DISPONÍVEIS:" + RESET);
        for (int i = 0; i < ingredientesDisponiveis.size(); i++) {
            System.out.printf("%d - %s\n", (i + 1), ingredientesDisponiveis.get(i));
        }
        
        System.out.print(BLUE + "Escolha o ingrediente (0 para cancelar): " + RESET);
        int escolha = sc.nextInt();
        
        if (escolha > 0 && escolha <= ingredientesDisponiveis.size()) {
            Ingrediente ingrediente = ingredientesDisponiveis.get(escolha - 1);
            lanche.adicionarIngrediente(ingrediente);
            System.out.println(GREEN + "✅ " + ingrediente.getNome() + " adicionado!" + RESET);
        }
    }

    private static void removerIngrediente(Lanche lanche) {
        ArrayList<Ingrediente> ingredientesBase = lanche.getIngredientesBase();
        if (ingredientesBase.isEmpty()) {
            System.out.println(RED + "❌ Não há ingredientes para remover!" + RESET);
            return;
        }
        
        System.out.println(YELLOW + BOLD + "\n❌ REMOVER INGREDIENTES:" + RESET);
        for (int i = 0; i < ingredientesBase.size(); i++) {
            System.out.printf("%d - %s\n", (i + 1), ingredientesBase.get(i).getNome());
        }
        
        System.out.print(BLUE + "Escolha o ingrediente para remover (0 para cancelar): " + RESET);
        int escolha = sc.nextInt();
        
        if (escolha > 0 && escolha <= ingredientesBase.size()) {
            Ingrediente ingrediente = ingredientesBase.get(escolha - 1);
            lanche.removerIngrediente(ingrediente);
            System.out.println(GREEN + "✅ " + ingrediente.getNome() + " removido!" + RESET);
        }
    }

    private static void mostrarRelatórios() {
        RelatorioVendas.gerarRelatorioCompleto();
    }

    private static void verPedidoAtual() {
        if (pedidoAtual.getLanches().isEmpty()) {
            System.out.println(YELLOW + "🛒 Seu pedido está vazio!" + RESET);
            return;
        }
        
        System.out.println(CYAN + BOLD + "\n🛒 SEU PEDIDO ATUAL:" + RESET);
        System.out.println(YELLOW + "Pedido #" + pedidoAtual.getNumeroPedido() + RESET);
        
        for (Lanche lanche : pedidoAtual.getLanches()) {
            System.out.printf(CYAN + "• %s - R$ %.2f\n" + RESET, 
                lanche.toString(), lanche.calcularPrecoFinal());
        }
        
        System.out.println(YELLOW + "─────────────────────" + RESET);
        System.out.printf(GREEN + BOLD + "💰 TOTAL: R$ %.2f\n" + RESET, pedidoAtual.getValorTotal());
    }

    private static void finalizarPedido() {
        if (pedidoAtual.getLanches().isEmpty()) {
            System.out.println(RED + "❌ Não há itens no pedido!" + RESET);
            return;
        }
        
        System.out.println(YELLOW + "💱 Buscando cotação do dólar..." + RESET);
        double cotacao = CotacaoAPI.obterCotacaoDolar();
        pedidoAtual.setCotacaoDolar(cotacao);
        
        // Gerar nota fiscal
        gerarNotaFiscal();
        
        // Salvar pedido para relatórios
        RelatorioVendas.salvarPedido(pedidoAtual);
        
        System.out.println(GREEN + BOLD + "\n✅ Pedido finalizado com sucesso!" + RESET);
        
        // Reiniciar pedido
        pedidoAtual = new Pedido();
    }

    private static void gerarNotaFiscal() {
        System.out.println(RED + BOLD + "\n╔════════════════════════════════════════╗" + RESET);
        System.out.println(RED + BOLD + "║" + YELLOW + "          🧾 NOTA FISCAL ManagerEAT          " + RED + "║" + RESET);
        System.out.println(RED + BOLD + "╠════════════════════════════════════════╣" + RESET);
        System.out.println(YELLOW + BOLD + "📦 Pedido #" + pedidoAtual.getNumeroPedido() + RESET);
        System.out.println(CYAN + "Data: " + pedidoAtual.getDataHora().format(
            java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + RESET);
        System.out.println(RED + BOLD + "╠════════════════════════════════════════╣" + RESET);
        
        for (Lanche lanche : pedidoAtual.getLanches()) {
            System.out.printf(YELLOW + "• %s\n" + RESET, lanche.toString());
            System.out.printf(GREEN + "  R$ %.2f\n" + RESET, lanche.calcularPrecoFinal());
        }
        
        System.out.println(RED + BOLD + "╠════════════════════════════════════════╣" + RESET);
        System.out.println(CYAN + "📊 Total de itens: " + pedidoAtual.getTotalItens() + RESET);
        System.out.println(RED + BOLD + "╠════════════════════════════════════════╣" + RESET);
        System.out.printf(GREEN + BOLD + "💰 TOTAL (BRL): R$ %.2f\n" + RESET, pedidoAtual.getValorTotal());
        
        if (pedidoAtual.getCotacaoDolar() > 0) {
            System.out.printf(YELLOW + "💵 Cotação USD: %s\n" + RESET, 
                CotacaoAPI.formatarCotacao(pedidoAtual.getCotacaoDolar()));
            System.out.printf(BLUE + BOLD + "💵 TOTAL (USD): %s\n" + RESET, 
                CotacaoAPI.converterParaDolar(pedidoAtual.getValorTotal(), pedidoAtual.getCotacaoDolar()));
        }
        
        System.out.println(RED + BOLD + "╚════════════════════════════════════════╝" + RESET);
        System.out.println(YELLOW + BOLD + "\n🎉 Obrigado por escolher o ManagerEAT! 🎉" + RESET);
        System.out.println(CYAN + "Volte sempre! 😊\n" + RESET);
    }
}
