# 🍔 ManagerEAT - Sistema Avançado de Gestão de Lanchonete

<div align="center">

```
╔══════════════════════════════════════╗
║            🍔 ManagerEAT 🍔           ║
╠══════════════════════════════════════╣
║     ████████   X-SALADA   ████████    ║
║    ██      ██ 🥬🍅🧀🥓 ██      ██   ║
║     ████████  DELICIOSO   ████████    ║
╚══════════════════════════════════════╝
```

**Sistema completo de pedidos para lanchonete com funcionalidades avançadas**

![Java](https://img.shields.io/badge/Java-11%2B-orange)
![Status](https://img.shields.io/badge/Status-Completo-green)
![License](https://img.shields.io/badge/License-MIT-blue)

</div>

## 📋 Sobre o Projeto

O **ManagerEAT** é um sistema robusto de gestão de pedidos para lanchonetes, desenvolvido em Java com interface de console colorida. O sistema oferece funcionalidades completas para gerenciamento de vendas, personalização de produtos, relatórios estatísticos e integração com API de cotação de moedas.

### 🎯 Características Principais

- **Interface Intuitiva** com cores temáticas (vermelho e amarelo)
- **Sistema de Pedidos** com carrinho de compras
- **Personalização Total** de lanches com ingredientes extras
- **Relatórios Avançados** de vendas e estatísticas
- **Cotação em Tempo Real** USD/BRL via API externa
- **Persistência de Dados** para histórico completo

## 🚀 Funcionalidades

### 🛒 **Sistema de Pedidos**
- Menu interativo com 5 opções premium
- Controle de quantidades por item
- Carrinho de compras persistente
- Visualização do pedido em tempo real

### 🎨 **Personalização de Lanches**
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

- Sistema de **remoção de ingredientes** padrão
- **Recálculo automático** do preço final

### 📊 **Relatórios e Estatísticas**
- Total de pedidos realizados
- Faturamento completo (BRL + USD)
- Ranking de lanches mais vendidos
- Ingredientes mais utilizados  
- Ticket médio por cliente
- Histórico persistente em arquivo

### 💵 **Cotação de Moedas**
- **Integração com AwesomeAPI** do Banco Central
- Conversão automática BRL → USD
- Exibição dual na nota fiscal
- Fallback automático para offline

## 🍟 Cardápio ManagerEAT

| Item | Preço Base | Descrição |
|------|------------|-----------|
| 🍔 Big Mac ManagerEAT | R$ 18,50 | Hambúrguer duplo com molho especial |
| 🥗 X-Salada ManagerEAT | R$ 15,00 | Clássico com alface, tomate e queijo |
| 🥓 Bacon Burger Premium | R$ 22,00 | Premium com bacon crocante |
| 🍟 Batata Frita Grande | R$ 12,00 | Batata premium com sal especial |
| 🥤 Combo Refrigerante | R$ 10,50 | Bebida 500ml gelada |

## 🛠️ Tecnologias Utilizadas

- **Java 11+** - Linguagem principal
- **Scanner** - Interface de entrada
- **ArrayList/HashMap** - Estruturas de dados
- **LocalDateTime** - Controle de tempo
- **HttpURLConnection** - Cliente HTTP para API
- **FileWriter/BufferedReader** - Persistência de dados
- **Cores ANSI** - Interface colorida no terminal

## 📁 Estrutura do Projeto

```
ManagerEat/
├── src/Application/
│   ├── Program.java           # Classe principal com interface
│   ├── Lanche.java           # Gestão de produtos e personalização
│   ├── Ingrediente.java      # Estrutura de ingredientes
│   ├── Pedido.java           # Controle de pedidos e carrinho
│   ├── CotacaoAPI.java       # Integração com API de cotação
│   └── RelatorioVendas.java  # Sistema de relatórios
├── vendas_managereat.txt     # Arquivo de histórico (gerado automaticamente)
├── README.md                 # Documentação do projeto
├── COMO_EXECUTAR.md         # Guia de execução
└── demonstracao.md          # Demonstração das funcionalidades
```

## 🚀 Como Executar

### Pré-requisitos
- Java 11 ou superior instalado
- Terminal com suporte a cores ANSI (recomendado)

### Passos para Executar

1. **Clone ou baixe o projeto**
   ```bash
   git clone <repository-url>
   cd ManagerEat
   ```

2. **Compile o projeto**
   ```bash
   javac src/Application/*.java
   ```

3. **Execute o sistema**
   ```bash
   java -cp src Application.Program
   ```

### Testes Específicos

**Teste da API de Cotação:**
```bash
javac -cp src TesteCotacao.java
java -cp .:src TesteCotacao
```

**Teste dos Relatórios:**
```bash
javac -cp src TesteRelatorio.java  
java -cp .:src TesteRelatorio
```

## 📱 Interface do Sistema

### Menu Principal
```
═══════════ MENU PRINCIPAL ManagerEAT ═══════════
1  🛒 Fazer Pedido
2  📊 Ver Relatórios e Estatísticas
3  👀 Ver Pedido Atual
4  💳 Finalizar Pedido (com cotação USD)
0  🚪 Sair
═══════════════════════════════════════════════
```

### Exemplo de Nota Fiscal
```
╔════════════════════════════════════════╗
║          🧾 NOTA FISCAL ManagerEAT          ║
╠════════════════════════════════════════╣
📦 Pedido #1 - 07/10/2024 15:30
• Big Mac ManagerEAT + Bacon Extra (2x) - R$ 43,00
• X-Salada ManagerEAT (1x) - R$ 15,00
╠════════════════════════════════════════╣
📊 Total de itens: 3
╠════════════════════════════════════════╣
💰 TOTAL (BRL): R$ 58,00
💵 Cotação USD: R$ 5,5000
💵 TOTAL (USD): US$ 10,55
╚════════════════════════════════════════╝
```

### Exemplo de Relatório
```
╔════════════════════════════════════════╗
║       📊 RELATÓRIO DE VENDAS ManagerEAT       ║
╠════════════════════════════════════════╣
📋 ESTATÍSTICAS GERAIS:
• Total de Pedidos: 5
• Total de Itens Vendidos: 14
• Faturamento Total: R$ 224,00
• Valor em Dólar: US$ 40,72
• Ticket Médio: R$ 44,80
╠════════════════════════════════════════╣
🏆 LANCHES MAIS VENDIDOS:
• Big Mac ManagerEAT: 3 unidades
• Bacon Burger Premium: 3 unidades
• X-Salada ManagerEAT: 2 unidades
╚════════════════════════════════════════╝
```

## 🔧 API Externa

O sistema utiliza a **AwesomeAPI** do Banco Central do Brasil para cotações:

- **Endpoint:** `https://api.awesomeapi.com.br/json/last/USD-BRL`
- **Fallback:** R$ 5,50 (caso a API esteja indisponível)
- **Timeout:** 5 segundos para conexão
- **Parsing:** Manual do JSON (sem bibliotecas externas)

## 📊 Funcionalidades Avançadas

### Sistema de Personalização
1. Selecione um lanche do cardápio
2. Escolha a quantidade desejada
3. Opte por personalizar (1=Sim, 0=Não)
4. **Adicione ingredientes extras** com preços específicos
5. **Remova ingredientes padrão** conforme preferência
6. Visualize o **recálculo automático** do preço

### Sistema de Relatórios
- **Persistência automática** de todos os pedidos
- **Análise estatística** completa das vendas
- **Ranking de produtos** mais populares
- **Controle de ingredientes** mais utilizados
- **Métricas financeiras** em múltiplas moedas

## 🤝 Contribuição

Contribuições são bem-vindas! Para contribuir:

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📝 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## 👨‍💻 Autor

**ManagerEAT Team**
- Sistema desenvolvido como evolução do ManagerEAT
- Focado em funcionalidades avançadas para gestão de lanchonetes
- Interface otimizada para experiência do usuário

---

<div align="center">

**🎉 Obrigado por usar o ManagerEAT! 🎉**

*Sistema completo de gestão de lanchonete com tecnologia avançada*

</div>
