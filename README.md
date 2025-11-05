# 🎬 Sistema de Gerenciamento de Filmes

API REST completa para gerenciamento de filmes desenvolvida com Spring Boot, incluindo integração com API externa OMDB.

## 📋 Sobre o Projeto

Este projeto implementa uma API REST completa para gerenciamento de filmes, atendendo todos os requisitos do projeto final:

### ✅ Requisitos Implementados

**Obrigatórios:**
- ✅ CRUD completo (GET, POST, PUT, PATCH, DELETE)
- ✅ Persistência em banco de dados (H2)
- ✅ Framework Spring Boot

**Opcionais:**
- ✅ Integração com API externa (OMDB)
- ✅ Funcionalidades extras (filtros, top rated, recentes)
- ✅ Documentação Swagger/OpenAPI
- ✅ Tratamento de exceções

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Data JPA**
- **H2 Database** (banco em memória)
- **Lombok**
- **SpringDoc OpenAPI** (Swagger)
- **Maven**

## 📦 Estrutura do Projeto

```
src/main/java/com/projeto/filmes/
├── config/
│   └── OpenApiConfig.java          # Configuração do Swagger
├── controller/
│   ├── FilmeController.java        # Endpoints REST principais
│   └── OmdbController.java         # Endpoints de integração OMDB
├── dto/
│   └── FilmeDTO.java               # Objetos de transferência de dados
├── exception/
│   └── GlobalExceptionHandler.java # Tratamento global de erros
├── model/
│   └── Filme.java                  # Entidade JPA
├── repository/
│   └── FilmeRepository.java        # Acesso aos dados
├── service/
│   ├── FilmeService.java           # Lógica de negócio
│   └── OmdbService.java            # Serviço de integração externa
└── FilmesApplication.java          # Classe principal
```

## 🔧 Como Executar

### Pré-requisitos

- Java 17 ou superior
- Maven 3.6 ou superior

### Passos para execução

1. **Clone o repositório**
```bash
git clone [URL_DO_REPOSITORIO]
cd gerenciamento-filmes
```

2. **Compile o projeto**
```bash
mvn clean install
```

3. **Execute a aplicação**
```bash
mvn spring-boot:run
```

4. **Acesse os recursos**

A aplicação estará disponível em `http://localhost:8080`

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **API Docs**: http://localhost:8080/api-docs
- **H2 Console**: http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:mem:filmesdb`
  - Username: `sa`
  - Password: (deixe em branco)

## 📚 Endpoints da API

### Operações CRUD Básicas

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/filmes` | Lista todos os filmes |
| GET | `/filmes/{id}` | Busca um filme por ID |
| POST | `/filmes` | Cria um novo filme |
| PUT | `/filmes/{id}` | Atualiza completamente um filme |
| PATCH | `/filmes/{id}` | Atualiza parcialmente um filme |
| DELETE | `/filmes/{id}` | Deleta um filme |

### Endpoints Adicionais

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/filmes?titulo={titulo}` | Filtra filmes por título |
| GET | `/filmes?diretor={diretor}` | Filtra filmes por diretor |
| GET | `/filmes?genero={genero}` | Filtra filmes por gênero |
| GET | `/filmes?ano={ano}` | Filtra filmes por ano |
| GET | `/filmes/top-rated` | Lista filmes mais bem avaliados |
| GET | `/filmes/recentes` | Lista filmes recentes |

### Integração OMDB (Opcional)

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/filmes/omdb/buscar-por-titulo?titulo={titulo}` | Busca filme na API OMDB |
| GET | `/filmes/omdb/buscar-por-imdb-id?imdbId={id}` | Busca filme por IMDB ID |
| POST | `/filmes/omdb/importar-por-titulo?titulo={titulo}` | Importa e salva filme |
| POST | `/filmes/omdb/importar-por-imdb-id?imdbId={id}` | Importa por IMDB ID |

## 📝 Exemplos de Requisições

### Criar um novo filme (POST)

```bash
curl -X POST http://localhost:8080/filmes \
  -H "Content-Type: application/json" \
  -d '{
    "titulo": "A Origem",
    "diretor": "Christopher Nolan",
    "anoLancamento": 2010,
    "genero": "Ficção Científica",
    "sinopse": "Um ladrão que rouba segredos corporativos através do uso da tecnologia de compartilhamento de sonhos.",
    "duracaoMinutos": 148,
    "avaliacao": 8.8
  }'
```

### Atualizar parcialmente (PATCH)

```bash
curl -X PATCH http://localhost:8080/filmes/1 \
  -H "Content-Type: application/json" \
  -d '{
    "avaliacao": 9.0,
    "sinopse": "Nova sinopse atualizada"
  }'
```

### Buscar filmes por gênero

```bash
curl http://localhost:8080/filmes?genero=Acao
```

### Importar filme do OMDB

```bash
curl -X POST "http://localhost:8080/filmes/omdb/importar-por-titulo?titulo=Inception"
```

## 🗄️ Modelo de Dados

```java
{
  "id": 1,
  "titulo": "A Origem",
  "diretor": "Christopher Nolan",
  "anoLancamento": 2010,
  "genero": "Ficção Científica",
  "sinopse": "Descrição do filme...",
  "duracaoMinutos": 148,
  "avaliacao": 8.8,
  "urlPoster": "http://...",
  "imdbId": "tt1375666"
}
```

## 🔑 Configuração da API OMDB (Opcional)

Para usar a integração com OMDB:

1. Obtenha uma chave gratuita em: http://www.omdbapi.com/apikey.aspx
2. Adicione a chave no arquivo `application.properties`:

```properties
omdb.api.key=SUA_CHAVE_AQUI
```

## 🧪 Testando a Aplicação

### Usando Swagger UI

1. Acesse http://localhost:8080/swagger-ui.html
2. Explore e teste todos os endpoints diretamente pela interface

### Usando cURL ou Postman

Importe a coleção do Swagger ou use os exemplos de cURL acima.

## 📊 Funcionalidades Extras

1. **Filtros avançados**: Busca por título, diretor, gênero e ano
2. **Top Rated**: Lista filmes ordenados por avaliação
3. **Recentes**: Lista filmes mais recentes
4. **Integração OMDB**: Importação automática de dados de filmes
5. **Validações**: Validação automática de dados de entrada
6. **Tratamento de erros**: Mensagens de erro padronizadas e informativas

## 🐛 Troubleshooting

### Erro ao conectar ao H2

- Verifique se a URL JDBC está correta: `jdbc:h2:mem:filmesdb`
- Certifique-se de que o console H2 está habilitado nas configurações

### Erro na API OMDB

- Verifique se você configurou a chave da API
- A chave gratuita tem limite de 1000 requisições por dia

## 📄 Licença

Este projeto foi desenvolvido para fins educacionais como parte do projeto final capítulo do curso da Ada Tech.


---

**Desenvolvido com Java usando Spring Boot**