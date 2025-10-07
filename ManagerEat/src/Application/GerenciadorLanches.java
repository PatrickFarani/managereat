package Application;

import java.util.*;

public class GerenciadorLanches {
    private static Map<String, Lanche> lanches = new HashMap<>();
    
    static {
        // Lanches padrão do sistema
        inicializarLanchesDefault();
    }
    
    private static void inicializarLanchesDefault() {
        lanches.put("Big Mac ManagerEAT", new Lanche("Big Mac ManagerEAT", 18.50));
        lanches.put("X-Salada ManagerEAT", new Lanche("X-Salada ManagerEAT", 15.00));
        lanches.put("Bacon Burger Premium", new Lanche("Bacon Burger Premium", 22.00));
        lanches.put("Batata Frita Grande", new Lanche("Batata Frita Grande", 12.00));
        lanches.put("Combo Refrigerante", new Lanche("Combo Refrigerante", 10.50));
    }
    
    public static void incluirLanche(String nome, double preco) {
        if (!lanches.containsKey(nome)) {
            lanches.put(nome, new Lanche(nome, preco));
        } else {
            throw new IllegalArgumentException("Lanche já existe!");
        }
    }
    
    public static void alterarLanche(String nomeAntigo, String novoNome, double novoPreco) {
        if (lanches.containsKey(nomeAntigo)) {
            lanches.remove(nomeAntigo);
            lanches.put(novoNome, new Lanche(novoNome, novoPreco));
        } else {
            throw new IllegalArgumentException("Lanche não encontrado!");
        }
    }
    
    public static void excluirLanche(String nome) {
        if (lanches.containsKey(nome)) {
            lanches.remove(nome);
        } else {
            throw new IllegalArgumentException("Lanche não encontrado!");
        }
    }
    
    public static Lanche buscarLanche(String nome) {
        return lanches.get(nome);
    }
    
    public static Map<String, Lanche> listarLanches() {
        return new HashMap<>(lanches);
    }
    
    public static List<String> getNomesLanches() {
        return new ArrayList<>(lanches.keySet());
    }
    
    public static void exibirCardapioCompleto() {
        System.out.println(Program.CYAN + Program.BOLD + "\n═══════════ CARDÁPIO COMPLETO ManagerEAT ═══════════" + Program.RESET);
        
        int contador = 1;
        for (Map.Entry<String, Lanche> entry : lanches.entrySet()) {
            Lanche lanche = entry.getValue();
            System.out.printf(Program.YELLOW + "%d. %s" + Program.GREEN + " - R$ %.2f\n" + Program.RESET, 
                contador++, lanche.getNome(), lanche.getPrecoBase());
            
            // Exibir ingredientes
            System.out.println(Program.CYAN + "   Ingredientes: " + Program.RESET);
            for (Ingrediente ing : lanche.getIngredientesBase()) {
                System.out.println(Program.CYAN + "   • " + ing.getNome() + Program.RESET);
            }
            System.out.println();
        }
        System.out.println(Program.CYAN + "════════════════════════════════════════════════════" + Program.RESET);
    }
    
    public static boolean lancheExiste(String nome) {
        return lanches.containsKey(nome);
    }
}