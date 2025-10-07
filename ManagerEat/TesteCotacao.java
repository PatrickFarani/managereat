import Application.CotacaoAPI;

public class TesteCotacao {
    public static void main(String[] args) {
        System.out.println("🧪 TESTE DA API DE COTAÇÃO USD/BRL");
        System.out.println("═══════════════════════════════════");
        
        double cotacao = CotacaoAPI.obterCotacaoDolar();
        System.out.println("💱 Cotação atual: " + CotacaoAPI.formatarCotacao(cotacao));
        
        double valorReal = 50.00;
        System.out.printf("💰 R$ %.2f = %s\n", valorReal, 
            CotacaoAPI.converterParaDolar(valorReal, cotacao));
            
        System.out.println("✅ Teste concluído!");
    }
}