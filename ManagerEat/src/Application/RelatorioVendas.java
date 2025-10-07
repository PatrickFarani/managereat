package Application;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class RelatorioVendas {
    private static final String ARQUIVO_VENDAS = "vendas_managereat.txt";
    
    public static void salvarPedido(Pedido pedido) {
        try (FileWriter writer = new FileWriter(ARQUIVO_VENDAS, true)) {
            writer.write(pedido.toFileString() + "\n");
        } catch (IOException e) {
            System.out.println("⚠️  Erro ao salvar pedido: " + e.getMessage());
        }
    }
    
    public static void gerarRelatorioCompleto() {
        ArrayList<String> linhas = lerArquivoVendas();
        if (linhas.isEmpty()) {
            System.out.println("📊 Nenhuma venda registrada ainda.");
            return;
        }
        
        System.out.println("\n" + Program.RED + Program.BOLD + "╔════════════════════════════════════════╗" + Program.RESET);
        System.out.println(Program.RED + Program.BOLD + "║" + Program.YELLOW + "       📊 RELATÓRIO DE VENDAS ManagerEAT       " + Program.RED + "║" + Program.RESET);
        System.out.println(Program.RED + Program.BOLD + "╠════════════════════════════════════════╣" + Program.RESET);
        
        // Estatísticas gerais
        gerarEstatisticasGerais(linhas);
        
        System.out.println(Program.RED + Program.BOLD + "╠════════════════════════════════════════╣" + Program.RESET);
        
        // Lanches mais vendidos
        gerarLanchesMaisVendidos(linhas);
        
        System.out.println(Program.RED + Program.BOLD + "╠════════════════════════════════════════╣" + Program.RESET);
        
        // Ingredientes mais utilizados
        gerarIngredientesMaisUtilizados(linhas);
        
        System.out.println(Program.RED + Program.BOLD + "╚════════════════════════════════════════╝" + Program.RESET);
    }
    
    private static void gerarEstatisticasGerais(ArrayList<String> linhas) {
        int totalPedidos = linhas.size();
        double totalVendas = 0.0;
        double totalDolar = 0.0;
        int totalItens = 0;
        
        for (String linha : linhas) {
            String[] partes = linha.split("\\|");
            if (partes.length >= 4) {
                try {
                    totalVendas += Double.parseDouble(partes[2].split(":")[1]);
                    totalDolar += Double.parseDouble(partes[3].split(":")[1]);
                    
                    // Contar itens nos lanches
                    if (partes.length > 5) {
                        String lanchesPart = partes[5].split(":", 2)[1];
                        String[] lanches = lanchesPart.split(";");
                        for (String lanche : lanches) {
                            String[] lancheInfo = lanche.split(":");
                            if (lancheInfo.length >= 2) {
                                totalItens += Integer.parseInt(lancheInfo[1]);
                            }
                        }
                    }
                } catch (Exception e) {
                    // Ignora linhas com erro
                }
            }
        }
        
        System.out.println(Program.YELLOW + Program.BOLD + "📋 ESTATÍSTICAS GERAIS:" + Program.RESET);
        System.out.println(Program.CYAN + "• Total de Pedidos: " + totalPedidos + Program.RESET);
        System.out.println(Program.CYAN + "• Total de Itens Vendidos: " + totalItens + Program.RESET);
        System.out.printf(Program.GREEN + "• Faturamento Total: R$ %.2f\n" + Program.RESET, totalVendas);
        System.out.printf(Program.GREEN + "• Valor em Dólar: US$ %.2f\n" + Program.RESET, totalDolar);
        if (totalPedidos > 0) {
            System.out.printf(Program.YELLOW + "• Ticket Médio: R$ %.2f\n" + Program.RESET, totalVendas / totalPedidos);
        }
    }
    
    private static void gerarLanchesMaisVendidos(ArrayList<String> linhas) {
        HashMap<String, Integer> contadorLanches = new HashMap<>();
        
        for (String linha : linhas) {
            String[] partes = linha.split("\\|");
            if (partes.length > 5) {
                try {
                    String lanchesPart = partes[5].split(":", 2)[1];
                    String[] lanches = lanchesPart.split(";");
                    
                    for (String lanche : lanches) {
                        String[] lancheInfo = lanche.split(":");
                        if (lancheInfo.length >= 2) {
                            String nome = lancheInfo[0];
                            int quantidade = Integer.parseInt(lancheInfo[1]);
                            contadorLanches.put(nome, contadorLanches.getOrDefault(nome, 0) + quantidade);
                        }
                    }
                } catch (Exception e) {
                    // Ignora erros
                }
            }
        }
        
        System.out.println(Program.YELLOW + Program.BOLD + "🏆 LANCHES MAIS VENDIDOS:" + Program.RESET);
        
        // Ordenar por quantidade
        contadorLanches.entrySet().stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
            .limit(5)
            .forEach(entry -> {
                System.out.printf(Program.CYAN + "• %s: %d unidades\n" + Program.RESET, 
                    entry.getKey(), entry.getValue());
            });
    }
    
    private static void gerarIngredientesMaisUtilizados(ArrayList<String> linhas) {
        HashMap<String, Integer> contadorIngredientes = new HashMap<>();
        
        for (String linha : linhas) {
            String[] partes = linha.split("\\|");
            if (partes.length > 5) {
                try {
                    String lanchesPart = partes[5].split(":", 2)[1];
                    String[] lanches = lanchesPart.split(";");
                    
                    for (String lanche : lanches) {
                        String[] lancheInfo = lanche.split(":");
                        if (lancheInfo.length >= 2) {
                            String nome = lancheInfo[0];
                            int quantidade = Integer.parseInt(lancheInfo[1]);
                            
                            // Simular ingredientes baseados no nome do lanche
                            ArrayList<String> ingredientes = getIngredientesPorLanche(nome);
                            for (String ingrediente : ingredientes) {
                                contadorIngredientes.put(ingrediente, 
                                    contadorIngredientes.getOrDefault(ingrediente, 0) + quantidade);
                            }
                            
                            // Adicionar ingredientes extras se houver
                            if (lancheInfo.length > 3 && lancheInfo[2].equals("ADD")) {
                                String[] extras = lancheInfo[3].split(",");
                                for (String extra : extras) {
                                    contadorIngredientes.put(extra, 
                                        contadorIngredientes.getOrDefault(extra, 0) + quantidade);
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    // Ignora erros
                }
            }
        }
        
        System.out.println(Program.YELLOW + Program.BOLD + "🥬 INGREDIENTES MAIS UTILIZADOS:" + Program.RESET);
        
        // Ordenar por quantidade
        contadorIngredientes.entrySet().stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
            .limit(8)
            .forEach(entry -> {
                System.out.printf(Program.CYAN + "• %s: %d vezes\n" + Program.RESET, 
                    entry.getKey(), entry.getValue());
            });
    }
    
    private static ArrayList<String> getIngredientesPorLanche(String nomeLanche) {
        ArrayList<String> ingredientes = new ArrayList<>();
        
        switch (nomeLanche) {
            case "Big Mac ManagerEAT":
                ingredientes.add("Pão Especial");
                ingredientes.add("Hambúrguer Duplo");
                ingredientes.add("Queijo Cheddar");
                ingredientes.add("Alface");
                ingredientes.add("Molho Especial");
                break;
            case "X-Salada ManagerEAT":
                ingredientes.add("Pão");
                ingredientes.add("Hambúrguer");
                ingredientes.add("Queijo");
                ingredientes.add("Alface");
                ingredientes.add("Tomate");
                break;
            case "Bacon Burger Premium":
                ingredientes.add("Pão Artesanal");
                ingredientes.add("Hambúrguer Premium");
                ingredientes.add("Bacon Crocante");
                ingredientes.add("Queijo Suíço");
                break;
            case "Batata Frita Grande":
                ingredientes.add("Batata Premium");
                ingredientes.add("Sal Especial");
                break;
            case "Combo Refrigerante":
                ingredientes.add("Refrigerante 500ml");
                ingredientes.add("Gelo");
                break;
        }
        
        return ingredientes;
    }
    
    private static ArrayList<String> lerArquivoVendas() {
        ArrayList<String> linhas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_VENDAS))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                linhas.add(linha);
            }
        } catch (FileNotFoundException e) {
            // Arquivo não existe ainda
        } catch (IOException e) {
            System.out.println("⚠️  Erro ao ler arquivo de vendas: " + e.getMessage());
        }
        return linhas;
    }
}