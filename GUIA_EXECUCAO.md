# 📖 Guia Completo de Execução

## 🎯 Pré-requisitos

Antes de começar, certifique-se de ter instalado:

### 1. Java Development Kit (JDK) 17 ou superior

**Windows:**
- Baixe o JDK em: https://www.oracle.com/java/technologies/downloads/
- Instale e configure a variável de ambiente `JAVA_HOME`
- Verifique a instalação: `java -version`

**Linux/Mac:**
```bash
# Usando SDKMAN (recomendado)
curl -s "https://get.sdkman.io" | bash
sdk install java 17.0.9-oracle

# Ou usando apt (Ubuntu/Debian)
sudo apt update
sudo apt install openjdk-17-jdk

# Verificar instalação
java -version
```

### 2. Apache Maven 3.6 ou superior

**Windows:**
- Baixe em: https://maven.apache.org/download.cgi
- Extraia e adicione `bin` ao PATH

**Linux/Mac:**
```bash
# Ubuntu/Debian
sudo apt install maven

# Mac (usando Homebrew)
brew install maven

# Verificar instalação
mvn -version
```

### 3. IDE (Opcional, mas recomendado)

- **IntelliJ IDEA** (recomendado): https://www.jetbrains.com/idea/
- **Eclipse**: https://www.eclipse.org/downloads/
- **VS Code** com extensão Java: https://code.visualstudio.com/

## 📥 Baixando o Projeto

### Opção 1: Clone do GitHub

```bash
git clone [URL_DO_REPOSITORIO]
cd gerenciamento-filmes
```

### Opção 2: Download ZIP

1. Baixe o arquivo ZIP do repositório
2. Extraia em uma pasta de sua preferência
3. Abra o terminal/prompt na pasta extraída

## 🏗️ Estrutura de Pastas

Certifique-se de que a estrutura está assim:

```
gerenciamento-filmes/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/projeto/filmes/
│   │   │       ├── config/
│   │   │       ├── controller/
│   │   │       ├── dto/
│   │   │       ├── exception/
│   │   │       ├── model/
│   │   │       ├── repository/
│   │   │       ├── service/
│   │   │       └── FilmesApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── data.sql (opcional)
│   └── test/
│       └── java/
│           └── com/projeto/filmes/
├── pom.xml
└── README.md
```

## 🚀 Executando o Projeto

### Método 1: Usando Maven (Linha de Comando)

1. **Limpe e compile o projeto:**
```bash
mvn clean install
```

2. **Execute a aplicação:**
```bash
mvn spring-boot:run
```

3. **Aguarde a mensagem:**
```
🎬 API de Gerenciamento de Filmes iniciada!
```

### Método 2: Usando IntelliJ IDEA

1. Abra o IntelliJ IDEA
2. File → Open → Selecione a pasta do projeto
3. Aguarde o Maven baixar as dependências
4. Localize `FilmesApplication.java`
5. Clique com botão direito → Run 'FilmesApplication'

### Método 3: Usando Eclipse

1. Abra o Eclipse
2. File → Import → Maven → Existing Maven Projects
3. Selecione a pasta do projeto
4. Botão direito no projeto → Run As → Spring Boot App

### Método 4: Usando JAR executável

```bash
# Gerar o JAR
mvn clean package

# Executar o JAR
java -jar target/gerenciamento-filmes-1.0.0.jar
```

## 🔍 Verificando se está funcionando

Após a execução, você deve ver no console:

```
==============================================
🎬 API de Gerenciamento de Filmes iniciada!
==============================================
📚 Swagger UI: http://localhost:8080/swagger-ui.html
📖 API Docs: http://localhost:8080/api-docs
🗄️ H2 Console: http://localhost:8080/h2-console
==============================================
```

## 🧪 Testando a API

### 1. Acessar o Swagger UI

Abra seu navegador e acesse: http://localhost:8080/swagger-ui.html

Você verá a interface do Swagger com todos os endpoints disponíveis.

### 2. Testar um endpoint simples

**Usando o navegador:**
- Acesse: http://localhost:8080/filmes
- Você deve ver uma lista JSON de filmes (vazia inicialmente)

**Usando cURL:**
```bash
curl http://localhost:8080/filmes
```

### 3. Criar seu primeiro filme

**Usando cURL:**
```bash
curl -X POST http://localhost:8080/filmes \
  -H "Content-Type: application/json" \
  -d '{
    "titulo": "Meu Primeiro Filme",
    "diretor": "Diretor Teste",
    "anoLancamento": 2024,
    "genero": "Drama",
    "sinopse": "Filme de teste",
    "duracaoMinutos": 120,
    "avaliacao": 8.0
  }'
```

**Usando Swagger:**
1. Clique em POST `/filmes`
2. Clique em "Try it out"
3. Edite o JSON de exemplo
4. Clique em "Execute"

## 🗄️ Acessando o Banco de Dados H2

1. Acesse: http://localhost:8080/h2-console
2. Configure a conexão:
   - **JDBC URL**: `jdbc:h2:mem:filmesdb`
   - **User Name**: `sa`
   - **Password**: (deixe em branco)
3. Clique em "Connect"
4. Você verá a tabela `FILMES` e poderá executar queries SQL

**Exemplo de Query:**
```sql
SELECT * FROM FILMES;
```

## 🔧 Populando o Banco com Dados Iniciais

Se você quiser começar com alguns filmes já cadastrados:

1. Crie o arquivo `src/main/resources/data.sql` (já fornecido)
2. Reinicie a aplicação
3. Os filmes serão inseridos automaticamente

## 🌐 Integração com OMDB (Opcional)

Para usar a funcionalidade de importação de filmes do OMDB:

1. **Obtenha uma chave gratuita:**
   - Acesse: http://www.omdbapi.com/apikey.aspx
   - Selecione "FREE! (1,000 daily limit)"
   - Preencha o formulário
   - Ative a chave no email recebido

2. **Configure a chave:**
   - Edite `src/main/resources/application.properties`
   - Substitua `sua-chave-aqui` pela sua chave real:
   ```properties
   omdb.api.key=SUA_CHAVE_AQUI
   ```

3. **Teste a integração:**
   ```bash
   curl -X POST "http://localhost:8080/filmes/omdb/importar-por-titulo?titulo=Inception"
   ```

## ❌ Solucionando Problemas Comuns

### Erro: "Port 8080 is already in use"

**Solução 1:** Pare o processo que está usando a porta
```bash
# Windows
netstat -ano | findstr :8080
taskkill /PID [PID_NUMBER] /F

# Linux/Mac
lsof -ti:8080 | xargs kill -9
```

**Solução 2:** Mude a porta no `application.properties`
```properties
server.port=8081
```

### Erro: "mvn: command not found"

Maven não está instalado ou não está no PATH.
- Reinstale o Maven seguindo os pré-requisitos
- Verifique a variável de ambiente PATH

### Erro: "Cannot resolve dependency"

Limpe o cache do Maven e tente novamente:
```bash
mvn clean install -U
```

### Erro na compilação: "invalid source release: 17"

Sua versão do Java é inferior a 17.
- Instale o JDK 17 ou superior
- Configure a IDE para usar o JDK correto

### Banco de dados não conecta

Verifique se:
- A URL JDBC está correta: `jdbc:h2:mem:filmesdb`
- O console H2 está habilitado no `application.properties`
- Você está usando o usuário `sa` sem senha

## 📊 Comandos Úteis

```bash
# Limpar e compilar
mvn clean install

# Executar sem testes
mvn spring-boot:run -DskipTests

# Executar apenas os testes
mvn test

# Gerar JAR
mvn clean package

# Ver dependências do projeto
mvn dependency:tree

# Atualizar dependências
mvn versions:display-dependency-updates
```

## 🎓 Próximos Passos

Agora que sua API está funcionando:

1. ✅ Teste todos os endpoints no Swagger
2. ✅ Crie alguns filmes usando POST
3. ✅ Experimente os filtros (por título, gênero, etc.)
4. ✅ Teste a atualização parcial (PATCH)
5. ✅ Explore o banco H2
6. ✅ Se configurou, teste a integração OMDB

## 📞 Suporte

Se encontrar problemas:

1. Verifique os logs no console
2. Consulte a documentação oficial do Spring Boot
3. Entre em contato com os membros do grupo

---

**Bom desenvolvimento! 🚀**