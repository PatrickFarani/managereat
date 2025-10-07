package Application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Pedido {
    private static int contadorPedidos = 1;
    private int numeroPedido;
    private LocalDateTime dataHora;
    private ArrayList<Lanche> lanches;
    private double valorTotal;
    private double valorDolar;
    private double cotacaoDolar;

    public Pedido() {
        this.numeroPedido = contadorPedidos++;
        this.dataHora = LocalDateTime.now();
        this.lanches = new ArrayList<>();
        this.valorTotal = 0.0;
        this.valorDolar = 0.0;
        this.cotacaoDolar = 0.0;
    }

    public void adicionarLanche(Lanche lanche) {
        lanches.add(lanche);
        calcularValorTotal();
    }

    public void calcularValorTotal() {
        valorTotal = 0.0;
        for (Lanche lanche : lanches) {
            valorTotal += lanche.calcularPrecoFinal();
        }
    }

    public void setCotacaoDolar(double cotacao) {
        this.cotacaoDolar = cotacao;
        if (cotacao > 0) {
            this.valorDolar = valorTotal / cotacao;
        }
    }

    public int getTotalItens() {
        int total = 0;
        for (Lanche lanche : lanches) {
            total += lanche.getQuantidade();
        }
        return total;
    }

    public HashMap<String, Integer> getIngredientesUtilizados() {
        HashMap<String, Integer> ingredientes = new HashMap<>();
        
        for (Lanche lanche : lanches) {
            // Ingredientes base
            for (Ingrediente ing : lanche.getIngredientesBase()) {
                ingredientes.put(ing.getNome(), 
                    ingredientes.getOrDefault(ing.getNome(), 0) + lanche.getQuantidade());
            }
            // Ingredientes adicionais
            for (Ingrediente ing : lanche.getIngredientesAdicionais()) {
                ingredientes.put(ing.getNome(), 
                    ingredientes.getOrDefault(ing.getNome(), 0) + lanche.getQuantidade());
            }
        }
        
        return ingredientes;
    }

    public String toFileString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        StringBuilder sb = new StringBuilder();
        
        sb.append("PEDIDO:").append(numeroPedido).append("|");
        sb.append("DATA:").append(dataHora.format(formatter)).append("|");
        sb.append("VALOR_TOTAL:").append(String.format("%.2f", valorTotal)).append("|");
        sb.append("VALOR_DOLAR:").append(String.format("%.2f", valorDolar)).append("|");
        sb.append("COTACAO:").append(String.format("%.4f", cotacaoDolar)).append("|");
        sb.append("LANCHES:");
        
        for (int i = 0; i < lanches.size(); i++) {
            if (i > 0) sb.append(";");
            Lanche lanche = lanches.get(i);
            sb.append(lanche.getNome()).append(":").append(lanche.getQuantidade());
            
            if (!lanche.getIngredientesAdicionais().isEmpty()) {
                sb.append(":ADD:");
                for (int j = 0; j < lanche.getIngredientesAdicionais().size(); j++) {
                    if (j > 0) sb.append(",");
                    sb.append(lanche.getIngredientesAdicionais().get(j).getNome());
                }
            }
            
            if (!lanche.getIngredientesRemovidos().isEmpty()) {
                sb.append(":REM:");
                for (int j = 0; j < lanche.getIngredientesRemovidos().size(); j++) {
                    if (j > 0) sb.append(",");
                    sb.append(lanche.getIngredientesRemovidos().get(j).getNome());
                }
            }
        }
        
        return sb.toString();
    }

    // Getters
    public int getNumeroPedido() { return numeroPedido; }
    public LocalDateTime getDataHora() { return dataHora; }
    public ArrayList<Lanche> getLanches() { return lanches; }
    public double getValorTotal() { return valorTotal; }
    public double getValorDolar() { return valorDolar; }
    public double getCotacaoDolar() { return cotacaoDolar; }
}