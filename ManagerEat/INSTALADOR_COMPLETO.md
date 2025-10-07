# 🚀 INSTALADOR COMPLETO - ManagerEat

## 📦 **OPÇÃO 1: Instalação Automática (Recomendada)**

### **Para Windows (PowerShell):**

```powershell
# Criar estrutura de pastas
New-Item -ItemType Directory -Force -Path "C:\ManagerEat"
New-Item -ItemType Directory -Force -Path "C:\ManagerEat\src\Application"
cd C:\ManagerEat

# Baixar arquivos (você precisará criar cada arquivo manualmente)
# Veja a seção "ARQUIVOS PARA CRIAR" abaixo
```

### **Para Linux/Mac (Terminal):**

```bash
# Criar estrutura de pastas
mkdir -p ~/ManagerEat/src/Application
cd ~/ManagerEat

# Os arquivos serão criados individualmente abaixo
```

---

## 📄 **ARQUIVOS PARA CRIAR MANUALMENTE:**

### **Passo 1: Criar README.md**
```bash
# No diretório ManagerEat/, criar arquivo README.md
# Copie o conteúdo do README.md disponível na documentação
```

### **Passo 2: Criar src/Application/Program.java**
```java
// Copie TODO o código Java do arquivo Program.java
// (Arquivo principal - cerca de 300 linhas)
```

### **Passo 3: Criar demais arquivos Java**
- src/Application/Lanche.java
- src/Application/Ingrediente.java  
- src/Application/Pedido.java
- src/Application/CotacaoAPI.java
- src/Application/RelatorioVendas.java

---

## 🎯 **OPÇÃO 2: Download Completo via ZIP**

### **Estrutura Completa para Criar:**

```
ManagerEat/
├── src/Application/
│   ├── Program.java           (300+ linhas)
│   ├── Lanche.java           (150+ linhas)
│   ├── Ingrediente.java      (50+ linhas)
│   ├── Pedido.java           (120+ linhas)
│   ├── CotacaoAPI.java       (80+ linhas)
│   └── RelatorioVendas.java  (200+ linhas)
├── README.md                 (Documentação completa)
├── INSTALACAO.md            (Guia de instalação)
├── COMO_EXECUTAR.md         (Guia de uso)
├── LICENSE                  (Licença MIT)
└── vendas_managereat.txt    (Arquivo de dados - será criado automaticamente)
```

---

## 📋 **PASSO A PASSO DETALHADO:**

### **1. Criar Pasta Principal**
```bash
# Windows
mkdir C:\ManagerEat
cd C:\ManagerEat

# Linux/Mac  
mkdir ~/ManagerEat
cd ~/ManagerEat
```

### **2. Criar Estrutura de Subpastas**
```bash
mkdir src
mkdir src/Application
```

### **3. Baixar Java (se não tiver)**
- **Windows:** https://www.oracle.com/java/technologies/downloads/
- **Linux:** `sudo apt install default-jdk`
- **Mac:** `brew install openjdk@17`

### **4. Criar Cada Arquivo**

**Vou disponibilizar cada arquivo completo abaixo:**

---

## 💾 **TODOS OS ARQUIVOS COMPLETOS:**

Como não posso fazer upload direto, você pode:

**OPÇÃO A:** Copiar cada arquivo individualmente
**OPÇÃO B:** Pedir para eu criar um arquivo ZIP
**OPÇÃO C:** Usar Git (se disponível):

```bash
# Se você tiver acesso ao repositório
git clone [URL-DO-REPOSITORIO]
```

---

## 🔧 **APÓS CRIAR TODOS OS ARQUIVOS:**

```bash
# 1. Navegar até a pasta
cd ManagerEat

# 2. Compilar  
javac src/Application/*.java

# 3. Executar
java -cp src Application.Program

# 4. Testar funcionamento
# Deve aparecer o menu do ManagerEAT
```

---

## 📞 **PRECISA DE AJUDA?**

Se preferir, posso:

1. **📤 Criar arquivo ZIP** com todos os arquivos
2. **📋 Listar conteúdo** de cada arquivo para você copiar
3. **🔧 Dar suporte** na instalação passo a passo

**Qual opção você prefere?**

- Copiar arquivo por arquivo?  
- Receber um ZIP completo?
- Suporte detalhado na instalação?

**Me fale qual método prefere e eu te ajudo! 🚀**