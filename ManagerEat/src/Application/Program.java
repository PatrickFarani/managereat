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
        
        // Sistema de autenticação obrigatório
        if (!realizarLogin()) {
            System.out.println(RED + "❌ Login necessário para usar o sistema!" + RESET);
            return;
        }
        
        int opcao;
        do {
            mostrarMenuPrincipal();
            opcao = sc.nextInt();
            
            switch (opcao) {
                case 1:
                    mostrarCardapioCompleto();
                    break;
                case 2:
                    fazerPedido();
                    break;
                case 3:
                    gerenciarPedidos();
                    break;
                case 4:
                    gerenciarLanches();
                    break;
                case 5:
                    gerenciarIngredientes();
                    break;
                case 6:
                    mostrarRelatórios();
                    break;
                case 7:
                    SistemaAutenticacao.logout();
                    System.out.println(YELLOW + "👋 Logout realizado com sucesso!" + RESET);
                    if (!realizarLogin()) {
                        opcao = 0;
                    }
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

    private static boolean realizarLogin() {
        System.out.println(CYAN + BOLD + "\n🔐 SISTEMA DE AUTENTICAÇÃO ManagerEAT" + RESET);
        
        // Mostrar credenciais de demonstração
        for (String credencial : SistemaAutenticacao.getCredenciaisDemo()) {
            System.out.println(YELLOW + credencial + RESET);
        }
        
        System.out.print(BLUE + "\n👤 Login: " + RESET);
        String login = sc.next();
        System.out.print(BLUE + "🔑 Senha: " + RESET);
        String senha = sc.next();
        
        if (SistemaAutenticacao.login(login, senha)) {
            Usuario usuario = SistemaAutenticacao.getUsuarioLogado();
            System.out.println(GREEN + "✅ Login realizado com sucesso!" + RESET);
            System.out.printf(CYAN + "Bem-vindo, %s! Tipo: %s\n" + RESET, 
                usuario.getNome(), usuario.getTipo());
            return true;
        } else {
            System.out.println(RED + "❌ Login ou senha inválidos!" + RESET);
            return false;
        }
    }

    private static void mostrarMenuPrincipal() {
        Usuario usuario = SistemaAutenticacao.getUsuarioLogado();
        System.out.println(CYAN + BOLD + "\n═══════════ MENU PRINCIPAL ManagerEAT ═══════════" + RESET);
        System.out.printf(YELLOW + "👤 Logado como: %s (%s)\n" + RESET, usuario.getNome(), usuario.getTipo());
        System.out.println(CYAN + "═══════════════════════════════════════════════" + RESET);
        
        System.out.println(YELLOW + "1  📋 Ver Cardápio Completo" + RESET);
        System.out.println(YELLOW + "2  🛒 Fazer Pedido" + RESET);
        System.out.println(YELLOW + "3  📦 Gerenciar Pedidos" + RESET);
        
        if (SistemaAutenticacao.podeGerenciarLanches()) {
            System.out.println(YELLOW + "4  🍔 Gerenciar Lanches (Admin/Funcionário)" + RESET);
        }
        
        if (SistemaAutenticacao.podeGerenciarIngredientes()) {
            System.out.println(YELLOW + "5  🥬 Gerenciar Ingredientes (Admin)" + RESET);
        }
        
        System.out.println(YELLOW + "6  📊 Ver Relatórios e Estatísticas" + RESET);
        System.out.println(YELLOW + "7  🔄 Trocar Usuário" + RESET);
        System.out.println(RED + "0  🚪 Sair" + RESET);
        System.out.println(CYAN + "═══════════════════════════════════════════════" + RESET);
        System.out.print(BLUE + "Escolha uma opção: " + RESET);
    }
    
    private static void mostrarCardapioCompleto() {
        GerenciadorLanches.exibirCardapioCompleto();
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
                
                if (personalizar == 1) {
                    personalizarLanche(lanche);
                }
                
                pedidoAtual.adicionarLanche(lanche);
                System.out.println(GREEN + "✅ Item adicionado ao pedido!" + RESET);
            }
        } while (codigo != 0);
    }

    private static void mostrarCardapio() {
        System.out.println(CYAN + BOLD + "\n═══════════ CARDÁPIO PARA PEDIDOS ═══════════" + RESET);
        
        List<String> nomesLanches = GerenciadorLanches.getNomesLanches();
        int contador = 1;
        for (String nome : nomesLanches) {
            Lanche lanche = GerenciadorLanches.buscarLanche(nome);
            System.out.printf(YELLOW + "%d  %s      " + GREEN + "R$ %.2f\n" + RESET, 
                contador++, nome, lanche.getPrecoBase());
        }
        System.out.println(CYAN + "═══════════════════════════════════════════" + RESET);
    }

    private static Lanche criarLanche(int codigo) {
        List<String> nomesLanches = GerenciadorLanches.getNomesLanches();
        
        if (codigo > 0 && codigo <= nomesLanches.size()) {
            String nomeLanche = nomesLanches.get(codigo - 1);
            Lanche lanche = GerenciadorLanches.buscarLanche(nomeLanche);
            if (lanche != null) {
                System.out.printf(YELLOW + "✅ %s selecionado!" + RESET + "\n", nomeLanche);
                return new Lanche(lanche.getNome(), lanche.getPrecoBase());
            }
        }
        
        System.out.println(RED + "❌ Código inválido!" + RESET);
        return null;
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
        List<Ingrediente> ingredientesDisp = GerenciadorIngredientes.getIngredientesDisponiveis();
        
        for (int i = 0; i < ingredientesDisp.size(); i++) {
            System.out.printf("%d - %s\n", (i + 1), ingredientesDisp.get(i));
        }
        
        System.out.print(BLUE + "Escolha o ingrediente (0 para cancelar): " + RESET);
        int escolha = sc.nextInt();
        
        if (escolha > 0 && escolha <= ingredientesDisp.size()) {
            Ingrediente ingrediente = ingredientesDisp.get(escolha - 1);
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

    private static void gerenciarPedidos() {
        int opcao;
        do {
            System.out.println(CYAN + BOLD + "\n═══════════ GERENCIAR PEDIDOS ═══════════" + RESET);
            System.out.println(YELLOW + "1  👀 Ver Pedido Atual" + RESET);
            System.out.println(YELLOW + "2  ✏️  Alterar Pedido Atual" + RESET);
            System.out.println(YELLOW + "3  🗑️  Limpar Pedido Atual" + RESET);
            System.out.println(YELLOW + "4  💳 Finalizar Pedido (com descontos e cotação USD)" + RESET);
            System.out.println(RED + "0  ⬅️  Voltar" + RESET);
            System.out.print(BLUE + "Escolha uma opção: " + RESET);
            opcao = sc.nextInt();
            
            switch (opcao) {
                case 1:
                    verPedidoAtual();
                    break;
                case 2:
                    alterarPedido();
                    break;
                case 3:
                    limparPedido();
                    break;
                case 4:
                    finalizarPedido();
                    break;
            }
        } while (opcao != 0);
    }
    
    private static void alterarPedido() {
        if (pedidoAtual.getLanches().isEmpty()) {
            System.out.println(YELLOW + "🛒 Pedido está vazio! Use 'Fazer Pedido' para adicionar itens." + RESET);
            return;
        }
        
        System.out.println(CYAN + BOLD + "\n📝 ITENS NO PEDIDO ATUAL:" + RESET);
        for (int i = 0; i < pedidoAtual.getLanches().size(); i++) {
            Lanche lanche = pedidoAtual.getLanches().get(i);
            System.out.printf(YELLOW + "%d. %s - R$ %.2f\n" + RESET, 
                (i + 1), lanche.toString(), lanche.calcularPrecoFinal());
        }
        
        System.out.print(BLUE + "Digite o número do item para remover (0 para cancelar): " + RESET);
        int escolha = sc.nextInt();
        
        if (escolha > 0 && escolha <= pedidoAtual.getLanches().size()) {
            Lanche removido = pedidoAtual.getLanches().remove(escolha - 1);
            pedidoAtual.calcularValorTotal();
            System.out.println(GREEN + "✅ " + removido.getNome() + " removido do pedido!" + RESET);
        }
    }
    
    private static void limparPedido() {
        if (!pedidoAtual.getLanches().isEmpty()) {
            System.out.print(YELLOW + "⚠️  Tem certeza que deseja limpar todo o pedido? (s/n): " + RESET);
            String confirmacao = sc.next();
            if (confirmacao.equalsIgnoreCase("s")) {
                pedidoAtual = new Pedido();
                System.out.println(GREEN + "✅ Pedido limpo com sucesso!" + RESET);
            }
        } else {
            System.out.println(YELLOW + "🛒 O pedido já está vazio!" + RESET);
        }
    }

    private static void finalizarPedido() {
        if (pedidoAtual.getLanches().isEmpty()) {
            System.out.println(RED + "❌ Não há itens no pedido!" + RESET);
            return;
        }
        
        // Aplicar descontos e promoções
        pedidoAtual.aplicarDescontos();
        
        System.out.println(YELLOW + "💱 Buscando cotação do dólar..." + RESET);
        double cotacao = CotacaoAPI.obterCotacaoDolar();
        pedidoAtual.setCotacaoDolar(cotacao);
        
        // Gerar nota fiscal com descontos
        gerarNotaFiscalCompleta();
        
        // Salvar pedido para relatórios
        RelatorioVendas.salvarPedido(pedidoAtual);
        
        System.out.println(GREEN + BOLD + "\n✅ Pedido finalizado com sucesso!" + RESET);
        
        // Reiniciar pedido
        pedidoAtual = new Pedido();
    }
    
    private static void gerenciarLanches() {
        if (!SistemaAutenticacao.podeGerenciarLanches()) {
            System.out.println(RED + "❌ Acesso negado! Apenas Admin/Funcionário podem gerenciar lanches." + RESET);
            return;
        }
        
        int opcao;
        do {
            System.out.println(CYAN + BOLD + "\n═══════════ GERENCIAR LANCHES ═══════════" + RESET);
            System.out.println(YELLOW + "1  ➕ Incluir Lanche" + RESET);
            System.out.println(YELLOW + "2  ✏️  Alterar Lanche" + RESET);
            System.out.println(YELLOW + "3  🗑️  Excluir Lanche" + RESET);
            System.out.println(YELLOW + "4  📋 Visualizar Lanches" + RESET);
            System.out.println(RED + "0  ⬅️  Voltar" + RESET);
            System.out.print(BLUE + "Escolha uma opção: " + RESET);
            opcao = sc.nextInt();
            
            switch (opcao) {
                case 1:
                    incluirLanche();
                    break;
                case 2:
                    alterarLanche();
                    break;
                case 3:
                    excluirLanche();
                    break;
                case 4:
                    GerenciadorLanches.exibirCardapioCompleto();
                    break;
            }
        } while (opcao != 0);
    }
    
    private static void gerenciarIngredientes() {
        if (!SistemaAutenticacao.podeGerenciarIngredientes()) {
            System.out.println(RED + "❌ Acesso negado! Apenas Admin pode gerenciar ingredientes." + RESET);
            return;
        }
        
        int opcao;
        do {
            System.out.println(CYAN + BOLD + "\n═══════════ GERENCIAR INGREDIENTES ═══════════" + RESET);
            System.out.println(YELLOW + "1  ➕ Incluir Ingrediente" + RESET);
            System.out.println(YELLOW + "2  ✏️  Alterar Ingrediente" + RESET);
            System.out.println(YELLOW + "3  🗑️  Excluir Ingrediente" + RESET);
            System.out.println(YELLOW + "4  📋 Visualizar Ingredientes" + RESET);
            System.out.println(RED + "0  ⬅️  Voltar" + RESET);
            System.out.print(BLUE + "Escolha uma opção: " + RESET);
            opcao = sc.nextInt();
            
            switch (opcao) {
                case 1:
                    incluirIngrediente();
                    break;
                case 2:
                    alterarIngrediente();
                    break;
                case 3:
                    excluirIngrediente();
                    break;
                case 4:
                    GerenciadorIngredientes.exibirListaIngredientes();
                    break;
            }
        } while (opcao != 0);
    }

    private static void incluirLanche() {
        sc.nextLine(); // Limpar buffer
        System.out.print(BLUE + "Nome do lanche: " + RESET);
        String nome = sc.nextLine();
        System.out.print(BLUE + "Preço base (R$): " + RESET);
        double preco = sc.nextDouble();
        
        try {
            GerenciadorLanches.incluirLanche(nome, preco);
            System.out.println(GREEN + "✅ Lanche incluído com sucesso!" + RESET);
        } catch (IllegalArgumentException e) {
            System.out.println(RED + "❌ " + e.getMessage() + RESET);
        }
    }
    
    private static void alterarLanche() {
        GerenciadorLanches.exibirCardapioCompleto();
        sc.nextLine(); // Limpar buffer
        System.out.print(BLUE + "Nome do lanche para alterar: " + RESET);
        String nomeAntigo = sc.nextLine();
        System.out.print(BLUE + "Novo nome: " + RESET);
        String novoNome = sc.nextLine();
        System.out.print(BLUE + "Novo preço (R$): " + RESET);
        double novoPreco = sc.nextDouble();
        
        try {
            GerenciadorLanches.alterarLanche(nomeAntigo, novoNome, novoPreco);
            System.out.println(GREEN + "✅ Lanche alterado com sucesso!" + RESET);
        } catch (IllegalArgumentException e) {
            System.out.println(RED + "❌ " + e.getMessage() + RESET);
        }
    }
    
    private static void excluirLanche() {
        GerenciadorLanches.exibirCardapioCompleto();
        sc.nextLine(); // Limpar buffer
        System.out.print(BLUE + "Nome do lanche para excluir: " + RESET);
        String nome = sc.nextLine();
        
        try {
            GerenciadorLanches.excluirLanche(nome);
            System.out.println(GREEN + "✅ Lanche excluído com sucesso!" + RESET);
        } catch (IllegalArgumentException e) {
            System.out.println(RED + "❌ " + e.getMessage() + RESET);
        }
    }
    
    private static void incluirIngrediente() {
        sc.nextLine(); // Limpar buffer
        System.out.print(BLUE + "Nome do ingrediente: " + RESET);
        String nome = sc.nextLine();
        System.out.print(BLUE + "Preço (R$): " + RESET);
        double preco = sc.nextDouble();
        sc.nextLine(); // Limpar buffer
        System.out.print(BLUE + "Categoria: " + RESET);
        String categoria = sc.nextLine();
        
        try {
            GerenciadorIngredientes.incluirIngrediente(nome, preco, categoria);
            System.out.println(GREEN + "✅ Ingrediente incluído com sucesso!" + RESET);
        } catch (IllegalArgumentException e) {
            System.out.println(RED + "❌ " + e.getMessage() + RESET);
        }
    }
    
    private static void alterarIngrediente() {
        GerenciadorIngredientes.exibirListaIngredientes();
        sc.nextLine(); // Limpar buffer
        System.out.print(BLUE + "Nome do ingrediente para alterar: " + RESET);
        String nome = sc.nextLine();
        System.out.print(BLUE + "Novo preço (R$): " + RESET);
        double novoPreco = sc.nextDouble();
        sc.nextLine(); // Limpar buffer
        System.out.print(BLUE + "Nova categoria: " + RESET);
        String novaCategoria = sc.nextLine();
        
        try {
            GerenciadorIngredientes.alterarIngrediente(nome, novoPreco, novaCategoria);
            System.out.println(GREEN + "✅ Ingrediente alterado com sucesso!" + RESET);
        } catch (IllegalArgumentException e) {
            System.out.println(RED + "❌ " + e.getMessage() + RESET);
        }
    }
    
    private static void excluirIngrediente() {
        GerenciadorIngredientes.exibirListaIngredientes();
        sc.nextLine(); // Limpar buffer
        System.out.print(BLUE + "Nome do ingrediente para excluir: " + RESET);
        String nome = sc.nextLine();
        
        try {
            GerenciadorIngredientes.excluirIngrediente(nome);
            System.out.println(GREEN + "✅ Ingrediente excluído com sucesso!" + RESET);
        } catch (IllegalArgumentException e) {
            System.out.println(RED + "❌ " + e.getMessage() + RESET);
        }
    }

    private static void gerarNotaFiscalCompleta() {
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
        
        // Exibir descontos se existirem
        if (pedidoAtual.getDescontos() != null) {
            SistemaDescontos.exibirResumoDescontos(pedidoAtual.getDescontos());
        }
        
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
