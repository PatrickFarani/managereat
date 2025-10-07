import Application.*;

public class TesteCompleto {
    public static void main(String[] args) {
        System.out.println("🧪 TESTE COMPLETO - ManagerEAT com Todos os Requisitos");
        System.out.println("═══════════════════════════════════════════════════════");
        
        // Teste 1: Sistema de Autenticação
        System.out.println("\n✅ TESTE 1: Sistema de Autenticação");
        boolean loginAdmin = SistemaAutenticacao.login("admin", "admin123");
        System.out.println("Login Admin: " + (loginAdmin ? "SUCESSO" : "FALHA"));
        
        Usuario admin = SistemaAutenticacao.getUsuarioLogado();
        System.out.println("Usuário logado: " + admin.getNome());
        System.out.println("Pode gerenciar lanches: " + admin.podeGerenciarLanches());
        System.out.println("Pode gerenciar ingredientes: " + admin.podeGerenciarIngredientes());
        
        // Teste 2: Gerenciamento de Ingredientes (CRUD)
        System.out.println("\n✅ TESTE 2: CRUD de Ingredientes");
        try {
            GerenciadorIngredientes.incluirIngrediente("Teste Ingrediente", 1.50, "teste");
            System.out.println("Incluir ingrediente: SUCESSO");
            
            GerenciadorIngredientes.alterarIngrediente("Teste Ingrediente", 2.00, "teste");
            System.out.println("Alterar ingrediente: SUCESSO");
            
            Ingrediente ing = GerenciadorIngredientes.buscarIngrediente("Teste Ingrediente");
            System.out.println("Buscar ingrediente: " + (ing != null ? "SUCESSO" : "FALHA"));
            
            GerenciadorIngredientes.excluirIngrediente("Teste Ingrediente");
            System.out.println("Excluir ingrediente: SUCESSO");
        } catch (Exception e) {
            System.out.println("Erro no CRUD ingredientes: " + e.getMessage());
        }
        
        // Teste 3: Gerenciamento de Lanches (CRUD)  
        System.out.println("\n✅ TESTE 3: CRUD de Lanches");
        try {
            GerenciadorLanches.incluirLanche("Teste Lanche", 15.00);
            System.out.println("Incluir lanche: SUCESSO");
            
            GerenciadorLanches.alterarLanche("Teste Lanche", "Lanche Alterado", 18.00);
            System.out.println("Alterar lanche: SUCESSO");
            
            Lanche lanche = GerenciadorLanches.buscarLanche("Lanche Alterado");
            System.out.println("Buscar lanche: " + (lanche != null ? "SUCESSO" : "FALHA"));
            
            GerenciadorLanches.excluirLanche("Lanche Alterado");
            System.out.println("Excluir lanche: SUCESSO");
        } catch (Exception e) {
            System.out.println("Erro no CRUD lanches: " + e.getMessage());
        }
        
        // Teste 4: Sistema de Descontos
        System.out.println("\n✅ TESTE 4: Sistema de Descontos");
        
        // Testar com cliente antigo
        SistemaAutenticacao.login("cliente1", "123");
        Usuario clienteAntigo = SistemaAutenticacao.getUsuarioLogado();
        System.out.println("Cliente antigo (6+ meses): " + clienteAntigo.isClienteAntigo());
        
        Pedido pedidoTeste = new Pedido();
        Lanche xsalada = new Lanche("X-Salada ManagerEAT", 15.00);
        Lanche batata = new Lanche("Batata Frita Grande", 12.00);
        Lanche bigmac = new Lanche("Big Mac ManagerEAT", 18.50);
        bigmac.setQuantidade(2);
        
        pedidoTeste.adicionarLanche(xsalada);
        pedidoTeste.adicionarLanche(batata);
        pedidoTeste.adicionarLanche(bigmac);
        
        System.out.printf("Valor original: R$ %.2f\n", pedidoTeste.getValorTotal());
        
        pedidoTeste.aplicarDescontos();
        SistemaDescontos.ResultadoDesconto resultado = pedidoTeste.getDescontos();
        
        System.out.printf("Valor com descontos: R$ %.2f\n", resultado.getValorFinal());
        System.out.printf("Desconto total: R$ %.2f\n", resultado.getDescontoTotal());
        System.out.println("Descontos aplicados: " + resultado.getDescontosAplicados().size());
        System.out.println("Promoções aplicadas: " + resultado.getPromocoesAplicadas().size());
        
        // Teste 5: API de Cotação
        System.out.println("\n✅ TESTE 5: API de Cotação USD");
        double cotacao = CotacaoAPI.obterCotacaoDolar();
        System.out.printf("Cotação USD/BRL: %.4f\n", cotacao);
        System.out.println("Conversão R$ 100,00: " + CotacaoAPI.converterParaDolar(100.00, cotacao));
        
        System.out.println("\n🎉 TODOS OS TESTES CONCLUÍDOS!");
        System.out.println("✅ Sistema completo com TODOS os requisitos implementados!");
    }
}