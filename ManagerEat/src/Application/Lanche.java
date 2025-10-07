package Application;

import java.util.*;

public class Lanche {
    private String nome;
    private double precoBase;
    private ArrayList<Ingrediente> ingredientesBase;
    private ArrayList<Ingrediente> ingredientesAdicionais;
    private ArrayList<Ingrediente> ingredientesRemovidos;
    private int quantidade;

    public Lanche(String nome, double precoBase) {
        this.nome = nome;
        this.precoBase = precoBase;
        this.ingredientesBase = new ArrayList<>();
        this.ingredientesAdicionais = new ArrayList<>();
        this.ingredientesRemovidos = new ArrayList<>();
        this.quantidade = 1;
        initIngredientesBase();
    }

    private void initIngredientesBase() {
        // Ingredientes padrão por lanche
        switch (nome) {
            case "Big Mac ManagerEAT":
                ingredientesBase.add(new Ingrediente("Pão Especial", 0, "base"));
                ingredientesBase.add(new Ingrediente("Hambúrguer Duplo", 0, "proteína"));
                ingredientesBase.add(new Ingrediente("Queijo Cheddar", 0, "queijo"));
                ingredientesBase.add(new Ingrediente("Alface", 0, "vegetal"));
                ingredientesBase.add(new Ingrediente("Molho Especial", 0, "molho"));
                break;
            case "X-Salada ManagerEAT":
                ingredientesBase.add(new Ingrediente("Pão", 0, "base"));
                ingredientesBase.add(new Ingrediente("Hambúrguer", 0, "proteína"));
                ingredientesBase.add(new Ingrediente("Queijo", 0, "queijo"));
                ingredientesBase.add(new Ingrediente("Alface", 0, "vegetal"));
                ingredientesBase.add(new Ingrediente("Tomate", 0, "vegetal"));
                break;
            case "Bacon Burger Premium":
                ingredientesBase.add(new Ingrediente("Pão Artesanal", 0, "base"));
                ingredientesBase.add(new Ingrediente("Hambúrguer Premium", 0, "proteína"));
                ingredientesBase.add(new Ingrediente("Bacon Crocante", 0, "proteína"));
                ingredientesBase.add(new Ingrediente("Queijo Suíço", 0, "queijo"));
                break;
            case "Batata Frita Grande":
                ingredientesBase.add(new Ingrediente("Batata Premium", 0, "base"));
                ingredientesBase.add(new Ingrediente("Sal Especial", 0, "tempero"));
                break;
            case "Combo Refrigerante":
                ingredientesBase.add(new Ingrediente("Refrigerante 500ml", 0, "bebida"));
                ingredientesBase.add(new Ingrediente("Gelo", 0, "adicional"));
                break;
        }
    }

    public void adicionarIngrediente(Ingrediente ingrediente) {
        if (!ingredientesAdicionais.contains(ingrediente)) {
            ingredientesAdicionais.add(ingrediente);
        }
    }

    public void removerIngrediente(Ingrediente ingrediente) {
        if (!ingredientesRemovidos.contains(ingrediente)) {
            ingredientesRemovidos.add(ingrediente);
        }
    }

    public double calcularPrecoFinal() {
        double preco = precoBase;
        for (Ingrediente ing : ingredientesAdicionais) {
            preco += ing.getPreco();
        }
        return preco * quantidade;
    }

    public ArrayList<Ingrediente> getIngredientesFinais() {
        ArrayList<Ingrediente> ingredientes = new ArrayList<>(ingredientesBase);
        ingredientes.addAll(ingredientesAdicionais);
        ingredientes.removeAll(ingredientesRemovidos);
        return ingredientes;
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public double getPrecoBase() { return precoBase; }
    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
    public ArrayList<Ingrediente> getIngredientesBase() { return ingredientesBase; }
    public ArrayList<Ingrediente> getIngredientesAdicionais() { return ingredientesAdicionais; }
    public ArrayList<Ingrediente> getIngredientesRemovidos() { return ingredientesRemovidos; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(nome).append(" (").append(quantidade).append("x)");
        if (!ingredientesAdicionais.isEmpty()) {
            sb.append(" + ");
            for (int i = 0; i < ingredientesAdicionais.size(); i++) {
                if (i > 0) sb.append(", ");
                sb.append(ingredientesAdicionais.get(i).getNome());
            }
        }
        if (!ingredientesRemovidos.isEmpty()) {
            sb.append(" - ");
            for (int i = 0; i < ingredientesRemovidos.size(); i++) {
                if (i > 0) sb.append(", ");
                sb.append(ingredientesRemovidos.get(i).getNome());
            }
        }
        return sb.toString();
    }
}