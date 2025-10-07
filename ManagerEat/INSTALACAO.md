# 🚀 Guia de Instalação - ManagerEAT

## ⚡ Instalação Rápida

### Windows
```cmd
# 1. Verificar Java
java -version

# 2. Compilar projeto
javac src/Application/*.java

# 3. Executar sistema
java -cp src Application.Program
```

### Linux/MacOS
```bash
# 1. Verificar Java
java -version

# 2. Compilar projeto  
javac src/Application/*.java

# 3. Executar sistema
java -cp src Application.Program
```

## 📋 Pré-requisitos

### Java Development Kit (JDK)
- **Versão mínima:** Java 11
- **Recomendada:** Java 17 ou superior

#### Instalar Java no Windows:
1. Baixe o JDK em [Oracle](https://www.oracle.com/java/technologies/downloads/)
2. Execute o instalador
3. Configure a variável `JAVA_HOME`
4. Adicione `%JAVA_HOME%\bin` ao PATH

#### Instalar Java no Linux (Ubuntu/Debian):
```bash
sudo apt update
sudo apt install default-jdk
```

#### Instalar Java no MacOS:
```bash
# Com Homebrew
brew install openjdk@17
```

## 🔧 Compilação e Execução

### Método 1: Terminal/Prompt
```bash
# Navegue até o diretório do projeto
cd ManagerEat

# Compile todas as classes
javac src/Application/*.java

# Execute o programa principal
java -cp src Application.Program
```

### Método 2: Script de Compilação (Windows)
Crie um arquivo `executar.bat`:
```batch
@echo off
echo Compilando ManagerEAT...
javac src/Application/*.java
if %ERRORLEVEL% == 0 (
    echo Compilacao concluida! Iniciando sistema...
    java -cp src Application.Program
) else (
    echo Erro na compilacao!
    pause
)
```

### Método 3: Script de Compilação (Linux/Mac)
Crie um arquivo `executar.sh`:
```bash
#!/bin/bash
echo "Compilando ManagerEAT..."
javac src/Application/*.java
if [ $? -eq 0 ]; then
    echo "Compilação concluída! Iniciando sistema..."
    java -cp src Application.Program
else
    echo "Erro na compilação!"
fi
```

## 🧪 Testes

### Teste Básico do Sistema
```bash
java -cp src Application.Program
```

### Teste da API de Cotação
```bash
javac -cp src TesteCotacao.java
java -cp .:src TesteCotacao
```

### Teste dos Relatórios
```bash
javac -cp src TesteRelatorio.java
java -cp .:src TesteRelatorio
```

## 🐛 Solução de Problemas

### Erro: "java: command not found"
**Solução:** Java não está instalado ou não está no PATH
```bash
# Verificar se Java está instalado
which java
java -version
```

### Erro: "Could not find or load main class"
**Solução:** Problema no classpath
```bash
# Use o comando correto:
java -cp src Application.Program
# NÃO use: java src.Application.Program
```

### Erro de compilação: "package does not exist"
**Solução:** Compile a partir do diretório correto
```bash
# Certifique-se de estar na pasta JavaSnackBar-master
javac src/Application/*.java
```

### Cores não aparecem no terminal
**Solução:** Use um terminal com suporte ANSI
- **Windows:** Use Windows Terminal ou PowerShell moderno
- **Linux/Mac:** A maioria dos terminais já suporta

## 📁 Estrutura de Arquivos Esperada

```
JavaSnackBar-master/
├── src/
│   └── Application/
│       ├── Program.java
│       ├── Lanche.java
│       ├── Ingrediente.java
│       ├── Pedido.java
│       ├── CotacaoAPI.java
│       └── RelatorioVendas.java
├── README.md
├── INSTALACAO.md
└── LICENSE
```

## 🌐 Requisitos de Rede

### API de Cotação (Opcional)
- **URL:** https://api.awesomeapi.com.br/json/last/USD-BRL
- **Porta:** 443 (HTTPS)
- **Fallback:** Funciona offline com cotação padrão

## ✅ Verificação da Instalação

Execute esta sequência para verificar se tudo está funcionando:

1. **Compilação bem-sucedida:**
   ```bash
   javac src/Application/*.java
   # Não deve mostrar erros
   ```

2. **Execução do menu:**
   ```bash
   java -cp src Application.Program
   # Deve mostrar o logo do ManagerEAT
   ```

3. **Teste de funcionalidades:**
   - Escolha opção 1 (Fazer Pedido)
   - Selecione um item do cardápio
   - Finalize um pedido
   - Verifique os relatórios (opção 2)

## 🎯 Primeiros Passos

Após a instalação:

1. **Faça um pedido teste** para entender o fluxo
2. **Experimente a personalização** de lanches
3. **Visualize os relatórios** gerados
4. **Teste a cotação USD** na finalização

---

**🎉 Instalação concluída! Bem-vindo ao ManagerEAT! 🎉**