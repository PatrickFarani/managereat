# 🍔 ManagerEAT - Demonstração das Novas Funcionalidades

## 🚀 Funcionalidades Implementadas

### 1. 📊 Sistema de Relatórios e Estatísticas
- **Histórico de vendas** salvo em arquivo `vendas_managereat.txt`
- **Relatórios automáticos** com:
  - Total de pedidos realizados
  - Faturamento total (BRL + USD)
  - Lanches mais vendidos (ranking)
  - Ingredientes mais utilizados
  - Ticket médio por pedido

### 2. 🍔 Personalização Completa de Lanches
- **10 ingredientes extras** disponíveis:
  - Bacon Extra (+R$ 3,00)
  - Queijo Extra (+R$ 2,50)
  - Cebola Roxa (+R$ 1,50)
  - Tomate Extra (+R$ 1,00)
  - Alface Extra (+R$ 1,00)
  - Picles (+R$ 1,50)
  - Molho Barbecue (+R$ 2,00)
  - Molho Ranch (+R$ 2,00)
  - Pimenta Jalapeño (+R$ 2,50)
  - Cheddar Cremoso (+R$ 3,50)

- **Sistema de remoção** de ingredientes padrão
- **Cálculo automático** do preço com personalizações

### 3. 💵 Cotação em Dólar (API Real)
- **Integração com AwesomeAPI** do Banco Central
- **Conversão automática** BRL → USD
- **Exibição dupla** na nota fiscal (Real + Dólar)
- **Fallback automático** se API indisponível

## 🎮 Como Usar o Sistema

### Menu Principal:
```
1 - 🛒 Fazer Pedido
2 - 📊 Ver Relatórios e Estatísticas  
3 - 👀 Ver Pedido Atual
4 - 💳 Finalizar Pedido (com cotação USD)
0 - 🚪 Sair
```

### Fluxo de Personalização:
```
1. Selecione um lanche
2. Escolha a quantidade
3. Opte por personalizar (1=Sim, 0=Não)
4. Adicione/Remova ingredientes
5. Finalize a personalização
```

### Nota Fiscal Completa:
```
🧾 NOTA FISCAL ManagerEAT
Pedido #1 - 07/10/2024 15:30
• Big Mac ManagerEAT + Bacon Extra (2x) - R$ 43,00
• X-Salada ManagerEAT - Alface (1x) - R$ 15,00
Total de itens: 3
💰 TOTAL (BRL): R$ 58,00
💵 Cotação USD: R$ 5,5000  
💵 TOTAL (USD): US$ 10,55
```

## 🔧 Arquitetura Técnica

### Classes Implementadas:
- **`Ingrediente.java`** - Gestão de ingredientes e preços
- **`Lanche.java`** - Lógica de personalização e cálculo
- **`Pedido.java`** - Controle de pedidos e relatórios
- **`CotacaoAPI.java`** - Integração com API de cotação
- **`RelatorioVendas.java`** - Persistência e estatísticas
- **`Program.java`** - Interface principal expandida

### Persistência:
- **Arquivo de vendas**: `vendas_managereat.txt` 
- **Formato estruturado** para análise de dados
- **Recuperação automática** do histórico

## ✅ Teste das Funcionalidades

### Para compilar:
```bash
javac src/Application/*.java
```

### Para executar:
```bash
java -cp src Application.Program
```

### Exemplos de teste:
1. **Fazer pedido personalizado**
2. **Ver relatórios** (após alguns pedidos)
3. **Finalizar com cotação USD**
4. **Verificar persistência** dos dados

## 🎯 Melhorias Implementadas

✅ **Interface mais intuitiva** com menu estruturado  
✅ **Sistema de cores** vermelho/amarelo mantido  
✅ **Persistência de dados** para relatórios  
✅ **API externa real** para cotação  
✅ **Personalização completa** de produtos  
✅ **Estatísticas avançadas** de vendas  
✅ **Cálculo automático** de preços personalizados  
✅ **Tratamento de erros** robusto  

---

**🎉 O ManagerEAT agora é um sistema completo de gestão de lanchonete com todas as funcionalidades avançadas solicitadas!**