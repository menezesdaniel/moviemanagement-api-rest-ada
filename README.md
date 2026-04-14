# 🎬 Sistema de Gerenciamento de Filmes

![Java](https://img.shields.io/badge/Java-17-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen?logo=springboot)
![Maven](https://img.shields.io/badge/Maven-3.6+-blue?logo=apachemaven)
![H2 Database](https://img.shields.io/badge/H2-Database-purple)
![Swagger](https://img.shields.io/badge/Swagger-OpenAPI%203-green?logo=swagger)

API REST completa para gerenciamento de filmes desenvolvida com **Spring Boot**, incluindo integração com a API externa [OMDB](http://www.omdbapi.com/).

## 📋 Sobre o Projeto

Este projeto implementa uma API REST completa para gerenciamento de filmes, atendendo todos os requisitos do projeto final do curso da **Ada Tech**.

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
- ✅ Validação de dados de entrada

## 🚀 Tecnologias Utilizadas

| Tecnologia | Descrição |
|---|---|
| **Java 17** | Linguagem de programação |
| **Spring Boot 3.2.0** | Framework principal |
| **Spring Data JPA** | Persistência e acesso a dados |
| **Spring Boot Starter Validation** | Validação de dados de entrada |
| **H2 Database** | Banco de dados relacional em memória |
| **Lombok** | Redução de código boilerplate |
| **SpringDoc OpenAPI 2.3.0** | Documentação interativa (Swagger UI) |
| **Maven** | Gerenciamento de dependências e build |

## 📦 Estrutura do Projeto

```
src/
├── main/java/com/projeto/filmes/
│   ├── config/
│   │   └── OpenApiConfig.java              # Configuração do Swagger/OpenAPI
│   ├── controller/
│   │   ├── FilmeController.java            # Endpoints REST principais (CRUD + filtros)
│   │   └── OmdbController.java             # Endpoints de integração com OMDB
│   ├── dto/
│   │   └── FilmeDTO.java                   # Objetos de transferência de dados (DTO e PatchDTO)
│   ├── exception/
│   │   ├── GlobalExceptionHandler.java     # Tratamento global de erros
│   │   └── ResourceNotFoundException.java  # Exceção para recurso não encontrado
│   ├── model/
│   │   └── Filme.java                      # Entidade JPA
│   ├── repository/
│   │   └── FilmeRepository.java            # Acesso aos dados (Spring Data JPA)
│   ├── service/
│   │   ├── FilmeService.java               # Lógica de negócio
│   │   └── OmdbService.java                # Serviço de integração com API externa
│   └── FilmesApplication.java              # Classe principal da aplicação
├── main/resources/
│   └── application.properties              # Configurações da aplicação
└── test/java/com/projeto/filmes/
    └── controller/
        └── FilmeControllerTest.java        # Testes do controller principal
```

## 🔧 Como Executar

### Pré-requisitos

- **Java 17** ou superior — [Download](https://www.oracle.com/java/technologies/downloads/)
- **Maven 3.6** ou superior — [Download](https://maven.apache.org/download.cgi)

> 📖 Para um guia de execução mais detalhado (incluindo instalação no Windows, Linux e Mac, IDEs, e solução de problemas), consulte o arquivo [GUIA_EXECUCAO.md](GUIA_EXECUCAO.md).

### Passos para execução

1. **Clone o repositório**

```bash
git clone https://github.com/menezesdaniel/moviemanagement-api-rest-ada.git
cd moviemanagement-api-rest-ada
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

| Recurso | URL |
|---|---|
| **Swagger UI** | http://localhost:8080/swagger-ui.html |
| **API Docs (JSON)** | http://localhost:8080/api-docs |
| **H2 Console** | http://localhost:8080/h2-console |

> **Configuração do H2 Console:**
> - JDBC URL: `jdbc:h2:mem:filmesdb`
> - Username: `sa`
> - Password: *(deixe em branco)*

## 📚 Endpoints da API

### Operações CRUD

| Método | Endpoint | Descrição |
|---|---|---|
| `GET` | `/filmes` | Lista todos os filmes |
| `GET` | `/filmes/{id}` | Busca um filme por ID |
| `POST` | `/filmes` | Cria um novo filme |
| `PUT` | `/filmes/{id}` | Atualiza completamente um filme |
| `PATCH` | `/filmes/{id}` | Atualiza parcialmente um filme |
| `DELETE` | `/filmes/{id}` | Remove um filme |

### Filtros e Consultas Especiais

| Método | Endpoint | Descrição |
|---|---|---|
| `GET` | `/filmes?titulo={titulo}` | Filtra filmes por título (busca parcial, case-insensitive) |
| `GET` | `/filmes?diretor={diretor}` | Filtra filmes por diretor (busca parcial, case-insensitive) |
| `GET` | `/filmes?genero={genero}` | Filtra filmes por gênero (busca parcial, case-insensitive) |
| `GET` | `/filmes?ano={ano}` | Filtra filmes por ano de lançamento |
| `GET` | `/filmes/top-rated` | Lista filmes ordenados por avaliação (maior → menor) |
| `GET` | `/filmes/recentes` | Lista filmes ordenados por ano de lançamento (mais recentes primeiro) |

### Integração OMDB

| Método | Endpoint | Descrição |
|---|---|---|
| `GET` | `/filmes/omdb/buscar-por-titulo?titulo={titulo}` | Busca filme na API OMDB por título |
| `GET` | `/filmes/omdb/buscar-por-imdb-id?imdbId={id}` | Busca filme na API OMDB por IMDB ID |
| `POST` | `/filmes/omdb/importar-por-titulo?titulo={titulo}` | Importa filme do OMDB e salva no banco |
| `POST` | `/filmes/omdb/importar-por-imdb-id?imdbId={id}` | Importa filme por IMDB ID e salva no banco |

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
curl "http://localhost:8080/filmes?genero=Acao"
```

### Importar filme do OMDB

```bash
curl -X POST "http://localhost:8080/filmes/omdb/importar-por-titulo?titulo=Inception"
```

## 🗄️ Modelo de Dados

A entidade `Filme` possui os seguintes campos:

```json
{
  "id": 1,
  "titulo": "A Origem",
  "diretor": "Christopher Nolan",
  "anoLancamento": 2010,
  "genero": "Ficção Científica",
  "sinopse": "Descrição do filme...",
  "duracaoMinutos": 148,
  "avaliacao": 8.8,
  "urlPoster": "https://exemplo.com/poster.jpg",
  "imdbId": "tt1375666"
}
```

### Validações

| Campo | Regras |
|---|---|
| `titulo` | Obrigatório, 1–200 caracteres |
| `diretor` | Obrigatório, 1–150 caracteres |
| `anoLancamento` | Obrigatório, entre 1888 e 2100 |
| `genero` | Obrigatório, 1–100 caracteres |
| `sinopse` | Opcional, até 1000 caracteres |
| `duracaoMinutos` | Opcional, mínimo 1 |
| `avaliacao` | Opcional, entre 0.0 e 10.0 |
| `urlPoster` | Opcional |
| `imdbId` | Opcional |

> **Nota:** A entidade também possui os campos `dataCadastro` e `dataAtualizacao`, que são gerenciados automaticamente pelo sistema.

## 🔑 Configuração da API OMDB (Opcional)

Para usar a integração com OMDB:

1. Obtenha uma chave gratuita em: http://www.omdbapi.com/apikey.aspx
2. Edite o arquivo `src/main/resources/application.properties` e substitua o valor da chave:

```properties
omdb.api.key=SUA_CHAVE_AQUI
```

> **Nota:** A chave gratuita permite até 1.000 requisições por dia.

## 🧪 Testando a Aplicação

### Testes automatizados

O projeto inclui testes automatizados para o controller principal:

```bash
mvn test
```

### Usando Swagger UI

1. Acesse http://localhost:8080/swagger-ui.html
2. Explore e teste todos os endpoints diretamente pela interface interativa

### Usando cURL ou Postman

Utilize os exemplos de cURL acima ou importe a especificação OpenAPI a partir de http://localhost:8080/api-docs.

## 📊 Funcionalidades Extras

| Funcionalidade | Descrição |
|---|---|
| **Filtros avançados** | Busca por título, diretor, gênero e ano (case-insensitive) |
| **Top Rated** | Lista filmes ordenados por avaliação |
| **Recentes** | Lista filmes mais recentes por ano de lançamento |
| **Integração OMDB** | Importação automática de dados de filmes da API OMDB |
| **Validações** | Validação automática de dados de entrada com mensagens descritivas |
| **Tratamento de erros** | Mensagens de erro padronizadas e informativas via `GlobalExceptionHandler` |
| **Documentação interativa** | Swagger UI com descrição de todos os endpoints e modelos |
| **CORS habilitado** | Aceita requisições de qualquer origem |

## 🐛 Troubleshooting

### Erro ao conectar ao H2

- Verifique se a URL JDBC está correta: `jdbc:h2:mem:filmesdb`
- Certifique-se de que o console H2 está habilitado nas configurações

### Erro na API OMDB

- Verifique se você configurou a chave da API corretamente em `application.properties`
- A chave gratuita tem limite de 1.000 requisições por dia

### Porta 8080 já em uso

Altere a porta no arquivo `application.properties`:

```properties
server.port=8081
```

> Para mais detalhes sobre resolução de problemas, consulte o [GUIA_EXECUCAO.md](GUIA_EXECUCAO.md).

## 📄 Licença

Este projeto foi desenvolvido para fins educacionais como parte do projeto final do curso da [Ada Tech](https://ada.tech/).

---

**Desenvolvido com ☕ Java e Spring Boot**