package Application;

import java.util.*;

public class SistemaDescontos {
    
    public static class ResultadoDesconto {
        private double valorOriginal;
        private double valorFinal;
        private double descontoTotal;
        private List<String> descontosAplicados;
        private List<String> promocoesAplicadas;
        
        public ResultadoDesconto(double valorOriginal) {
            this.valorOriginal = valorOriginal;
            this.valorFinal = valorOriginal;
            this.descontoTotal = 0.0;
            this.descontosAplicados = new ArrayList<>();
            this.promocoesAplicadas = new ArrayList<>();
        }
        
        public void aplicarDesconto(double percentual, String descricao) {
            double desconto = valorOriginal * (percentual / 100);
            valorFinal -= desconto;
            descontoTotal += desconto;
            descontosAplicados.add(String.format("%s: -%.1f%% (R$ %.2f)", descricao, percentual, desconto));
        }
        
        public void adicionarPromocao(String descricao) {
            promocoesAplicadas.add(descricao);
        }
        
        // Getters
        public double getValorOriginal() { return valorOriginal; }
        public double getValorFinal() { return valorFinal; }
        public double getDescontoTotal() { return descontoTotal; }
        public List<String> getDescontosAplicados() { return descontosAplicados; }
        public List<String> getPromocoesAplicadas() { return promocoesAplicadas; }
    }
    
    public static ResultadoDesconto calcularDescontos(Pedido pedido) {
        ResultadoDesconto resultado = new ResultadoDesconto(pedido.getValorTotal());
        
        // 1. Verificar promoção X-Salada + Batata = Refrigerante grátis
        verificarPromocaoRefrigerante(pedido, resultado);
        
        // 2. Desconto de 3% para pedidos acima de R$ 50,00
        if (pedido.getValorTotal() > 50.00) {
            resultado.aplicarDesconto(3.0, "Desconto pedido acima de R$ 50");
        }
        
        // 3. Desconto adicional de 5% para clientes antigos
        Usuario usuario = SistemaAutenticacao.getUsuarioLogado();
        if (usuario != null && usuario.getTipo() == Usuario.TipoUsuario.CLIENTE && usuario.isClienteAntigo()) {
            resultado.aplicarDesconto(5.0, "Desconto cliente há mais de 6 meses");
        }
        
        return resultado;
    }
    
    private static void verificarPromocaoRefrigerante(Pedido pedido, ResultadoDesconto resultado) {
        boolean temXSalada = false;
        boolean temBatata = false;
        
        for (Lanche lanche : pedido.getLanches()) {
            if (lanche.getNome().contains("X-Salada")) {
                temXSalada = true;
            }
            if (lanche.getNome().contains("Batata Frita")) {
                temBatata = true;
            }
        }
        
        if (temXSalada && temBatata) {
            resultado.adicionarPromocao("🎁 Refrigerante GRÁTIS (X-Salada + Batata)");
            // Adicionar refrigerante grátis ao pedido se não existir
            adicionarRefrigeranteGratis(pedido);
        }
    }
    
    private static void adicionarRefrigeranteGratis(Pedido pedido) {
        // Verificar se já tem refrigerante no pedido
        boolean jaTemRefrigerante = false;
        for (Lanche lanche : pedido.getLanches()) {
            if (lanche.getNome().contains("Refrigerante")) {
                jaTemRefrigerante = true;
                break;
            }
        }
        
        if (!jaTemRefrigerante) {
            Lanche refrigeranteGratis = new Lanche("Combo Refrigerante", 0.00); // Preço zerado
            refrigeranteGratis.setQuantidade(1);
            pedido.adicionarLanche(refrigeranteGratis);
        }
    }
    
    public static void exibirResumoDescontos(ResultadoDesconto resultado) {
        System.out.println(Program.YELLOW + Program.BOLD + "\n💰 RESUMO DE DESCONTOS E PROMOÇÕES:" + Program.RESET);
        System.out.println(Program.CYAN + "╔════════════════════════════════════════╗" + Program.RESET);
        
        System.out.printf(Program.CYAN + "║ Valor Original: " + Program.YELLOW + "R$ %.2f" + Program.CYAN + "                ║\n" + Program.RESET, 
            resultado.getValorOriginal());
        
        if (!resultado.getDescontosAplicados().isEmpty()) {
            System.out.println(Program.CYAN + "║ Descontos Aplicados:                   ║" + Program.RESET);
            for (String desconto : resultado.getDescontosAplicados()) {
                System.out.printf(Program.CYAN + "║ • " + Program.GREEN + "%-34s" + Program.CYAN + " ║\n" + Program.RESET, desconto);
            }
        }
        
        if (!resultado.getPromocoesAplicadas().isEmpty()) {
            System.out.println(Program.CYAN + "║ Promoções:                             ║" + Program.RESET);
            for (String promocao : resultado.getPromocoesAplicadas()) {
                System.out.printf(Program.CYAN + "║ " + Program.YELLOW + "%-38s" + Program.CYAN + " ║\n" + Program.RESET, promocao);
            }
        }
        
        System.out.println(Program.CYAN + "╠════════════════════════════════════════╣" + Program.RESET);
        System.out.printf(Program.CYAN + "║ " + Program.GREEN + Program.BOLD + "VALOR FINAL: R$ %.2f" + Program.CYAN + "                ║\n" + Program.RESET, 
            resultado.getValorFinal());
        System.out.println(Program.CYAN + "╚════════════════════════════════════════╝" + Program.RESET);
        
        if (resultado.getDescontoTotal() > 0) {
            System.out.printf(Program.GREEN + "🎉 Você economizou R$ %.2f!\n" + Program.RESET, resultado.getDescontoTotal());
        }
    }
}