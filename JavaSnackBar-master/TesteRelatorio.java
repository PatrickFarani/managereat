import Application.RelatorioVendas;

public class TesteRelatorio {
    public static void main(String[] args) {
        System.out.println("🧪 TESTE DO SISTEMA DE RELATÓRIOS");
        System.out.println("══════════════════════════════════");
        
        RelatorioVendas.gerarRelatorioCompleto();
        
        System.out.println("\n✅ Teste de relatório concluído!");
    }
}