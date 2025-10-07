package Application;

import java.io.*;
import java.net.*;

public class CotacaoAPI {
    private static final String API_URL = "https://api.awesomeapi.com.br/json/last/USD-BRL";
    
    public static double obterCotacaoDolar() {
        try {
            URL url = new URL(API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                
                // Parse manual do JSON (sem biblioteca externa)
                String jsonResponse = response.toString();
                return parseJSON(jsonResponse);
            }
        } catch (Exception e) {
            System.out.println("⚠️  Erro ao buscar cotação: " + e.getMessage());
        }
        
        // Valor padrão caso a API falhe
        return 5.50; // Cotação aproximada padrão
    }
    
    private static double parseJSON(String json) {
        try {
            // Busca pelo valor "bid" no JSON
            String searchPattern = "\"bid\":\"";
            int startIndex = json.indexOf(searchPattern);
            if (startIndex != -1) {
                startIndex += searchPattern.length();
                int endIndex = json.indexOf("\"", startIndex);
                if (endIndex != -1) {
                    String bidValue = json.substring(startIndex, endIndex);
                    return Double.parseDouble(bidValue);
                }
            }
        } catch (Exception e) {
            System.out.println("⚠️  Erro ao processar cotação: " + e.getMessage());
        }
        
        return 5.50; // Valor padrão
    }
    
    public static String formatarCotacao(double cotacao) {
        return String.format("R$ %.4f", cotacao);
    }
    
    public static String converterParaDolar(double valorReal, double cotacao) {
        if (cotacao > 0) {
            double valorDolar = valorReal / cotacao;
            return String.format("US$ %.2f", valorDolar);
        }
        return "US$ 0.00";
    }
}