package Application;

import java.util.*;

public class GerenciadorIngredientes {
    private static Map<String, Ingrediente> ingredientes = new HashMap<>();
    
    static {
        inicializarIngredientesDefault();
    }
    
    private static void inicializarIngredientesDefault() {
        // Ingredientes extras disponíveis
        ingredientes.put("Bacon Extra", new Ingrediente("Bacon Extra", 3.00, "proteína"));
        ingredientes.put("Queijo Extra", new Ingrediente("Queijo Extra", 2.50, "queijo"));
        ingredientes.put("Cebola Roxa", new Ingrediente("Cebola Roxa", 1.50, "vegetal"));
        ingredientes.put("Tomate Extra", new Ingrediente("Tomate Extra", 1.00, "vegetal"));
        ingredientes.put("Alface Extra", new Ingrediente("Alface Extra", 1.00, "vegetal"));
        ingredientes.put("Picles", new Ingrediente("Picles", 1.50, "vegetal"));
        ingredientes.put("Molho Barbecue", new Ingrediente("Molho Barbecue", 2.00, "molho"));
        ingredientes.put("Molho Ranch", new Ingrediente("Molho Ranch", 2.00, "molho"));
        ingredientes.put("Pimenta Jalapeño", new Ingrediente("Pimenta Jalapeño", 2.50, "tempero"));
        ingredientes.put("Cheddar Cremoso", new Ingrediente("Cheddar Cremoso", 3.50, "queijo"));
    }
    
    public static void incluirIngrediente(String nome, double preco, String categoria) {
        if (!ingredientes.containsKey(nome)) {
            ingredientes.put(nome, new Ingrediente(nome, preco, categoria));
        } else {
            throw new IllegalArgumentException("Ingrediente já existe!");
        }
    }
    
    public static void alterarIngrediente(String nome, double novoPreco, String novaCategoria) {
        if (ingredientes.containsKey(nome)) {
            Ingrediente ing = ingredientes.get(nome);
            ing.setPreco(novoPreco);
            ing.setCategoria(novaCategoria);
        } else {
            throw new IllegalArgumentException("Ingrediente não encontrado!");
        }
    }
    
    public static void excluirIngrediente(String nome) {
        if (ingredientes.containsKey(nome)) {
            ingredientes.remove(nome);
        } else {
            throw new IllegalArgumentException("Ingrediente não encontrado!");
        }
    }
    
    public static Ingrediente buscarIngrediente(String nome) {
        return ingredientes.get(nome);
    }
    
    public static Map<String, Ingrediente> listarIngredientes() {
        return new HashMap<>(ingredientes);
    }
    
    public static List<Ingrediente> getIngredientesDisponiveis() {
        return new ArrayList<>(ingredientes.values());
    }
    
    public static void exibirListaIngredientes() {
        System.out.println(Program.YELLOW + Program.BOLD + "\n📋 LISTA DE INGREDIENTES DISPONÍVEIS:" + Program.RESET);
        
        Map<String, List<Ingrediente>> porCategoria = new HashMap<>();
        for (Ingrediente ing : ingredientes.values()) {
            porCategoria.computeIfAbsent(ing.getCategoria(), k -> new ArrayList<>()).add(ing);
        }
        
        for (Map.Entry<String, List<Ingrediente>> entry : porCategoria.entrySet()) {
            System.out.println(Program.CYAN + Program.BOLD + "\n🔸 " + entry.getKey().toUpperCase() + ":" + Program.RESET);
            for (Ingrediente ing : entry.getValue()) {
                System.out.printf(Program.YELLOW + "  • %s - R$ %.2f\n" + Program.RESET, 
                    ing.getNome(), ing.getPreco());
            }
        }
    }
    
    public static boolean ingredienteExiste(String nome) {
        return ingredientes.containsKey(nome);
    }
}