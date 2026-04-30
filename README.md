# 70Graus API - Diamante 02

API REST desenvolvida em **Java + Spring Boot** para gerenciamento de produtos, estoque, movimentações de estoque e funcionários da operação **70Graus**.

O projeto foi desenvolvido para o **Projeto Diamante 02** da disciplina **Java Advanced**, aplicando os principais conceitos trabalhados no semestre: API REST, CRUD, relacionamentos entre entidades, persistência com JPA, validações, filtros, paginação, ordenação e projections.

---

## Objetivo do projeto

O objetivo da API é permitir o controle básico de uma loja de roupas, possibilitando:

- cadastrar, listar, atualizar e remover produtos;
- controlar estoque por produto;
- registrar movimentações de entrada e saída;
- cadastrar e gerenciar funcionários;
- buscar produtos por filtros específicos;
- consultar dados com paginação e ordenação;
- retornar dados resumidos com projection;
- validar dados enviados para a API.

---

## Stack principal

- **Java 17**
- **Spring Boot**
- **Spring Web MVC**
- **Spring Data JPA**
- **H2 Database**
- **Jakarta Validation**
- **Lombok**
- **Maven**

---

## Arquitetura do projeto

A aplicação foi organizada de forma simples e objetiva:

- **controllers**: expõem os endpoints REST;
- **services**: concentram as regras de negócio;
- **repositories**: fazem o acesso aos dados com Spring Data JPA;
- **model**: contém as entidades do sistema;
- **projection**: contém a projection de produto;
- **validation**: contém validações personalizadas e tratamento de erros;
- **data**: contém o `DataLoader`, responsável por popular dados iniciais.

Estrutura resumida:

```text
70Graus/
├── endpoints-70graus.json
├── mvnw
├── mvnw.cmd
├── pom.xml
├── README.md
└── src/
    └── main/
        ├── java/fiap/com/br/graus/
        │   ├── config/
        │   ├── controllers/
        │   ├── data/
        │   ├── model/
        │   ├── projection/
        │   ├── repositories/
        │   ├── services/
        │   ├── validation/
        │   └── GrausApplication.java
        └── resources/
            └── application.properties
```

---

## Entidades do sistema

### Produto

Representa os produtos vendidos pela loja.

Principais campos:

- `id`
- `nome`
- `descricao`
- `preco`
- `sku`
- `tamanho`
- `cor`
- `marca`
- `ativo`
- `categoria`

### Estoque

Representa o estoque de um produto.

Principais campos:

- `id`
- `produto`
- `quantidadeDisponivel`
- `quantidadeMinima`

### Funcionário

Representa o funcionário responsável por operações no sistema.

Principais campos:

- `id`
- `nome`
- `email`
- `senha`

### Movimentação de Estoque

Representa uma entrada ou saída de produtos no estoque.

Principais campos:

- `id`
- `estoque`
- `funcionario`
- `tipoMovimentacao`
- `quantidade`
- `dataMovimentacao`

---

## Relacionamentos

O projeto possui relacionamentos entre entidades usando JPA:

- Um `Produto` pode estar relacionado a registros de `Estoque`;
- Um `Estoque` pertence a um `Produto`;
- Um `Estoque` pode possuir várias `Movimentações de Estoque`;
- Uma `Movimentação de Estoque` pertence a um `Estoque`;
- Um `Funcionário` pode registrar várias `Movimentações de Estoque`;
- Uma `Movimentação de Estoque` pertence a um `Funcionário`.

---

## Dados iniciais

O projeto possui uma classe `DataLoader`, responsável por popular o banco H2 com dados iniciais.

Ela cria:

- produtos;
- registros de estoque;
- funcionário;
- movimentações de entrada e saída.

Esses dados facilitam os testes dos endpoints de listagem, filtros, paginação, ordenação e movimentações de estoque.

---

## Endpoints principais

### Produtos

| Método | Rota | Descrição |
|---|---|---|
| GET | `/produtos` | Lista todos os produtos |
| GET | `/produtos/{id}` | Busca produto por ID |
| POST | `/produtos` | Cadastra um novo produto |
| PUT | `/produtos/{id}` | Atualiza um produto |
| DELETE | `/produtos/{id}` | Remove um produto |
| GET | `/produtos/paginado?page=0&size=5&sort=nome,asc` | Lista produtos com paginação e ordenação |

### Estoque

| Método | Rota | Descrição |
|---|---|---|
| GET | `/estoque` | Lista os registros de estoque |
| GET | `/estoque/{id}` | Busca estoque por ID |
| POST | `/estoque` | Cadastra um novo estoque |
| PUT | `/estoque/{id}` | Atualiza um estoque |
| DELETE | `/estoque/{id}` | Remove um estoque |

### Funcionários

| Método | Rota | Descrição |
|---|---|---|
| GET | `/funcionario` | Lista todos os funcionários |
| GET | `/funcionario/{id}` | Busca funcionário por ID |
| POST | `/funcionario` | Cadastra um funcionário |
| PUT | `/funcionario/{id}` | Atualiza um funcionário |
| DELETE | `/funcionario/{id}` | Remove um funcionário |

### Movimentações de estoque

| Método | Rota | Descrição |
|---|---|---|
| GET | `/movi-estoque` | Lista movimentações |
| GET | `/movi-estoque/{id}` | Busca movimentação por ID |
| POST | `/movi-estoque` | Registra uma movimentação |
| PUT | `/movi-estoque/{id}` | Atualiza uma movimentação |
| DELETE | `/movi-estoque/{id}` | Remove uma movimentação |

---

## Endpoints de busca e filtros

A API possui 5 endpoints de busca e filtro para produtos:

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/produtos/nome?nome=camiseta&page=0&size=5&sort=nome,asc` | Filtra produtos por nome |
| GET | `/produtos/preco?min=50&max=200&page=0&size=5&sort=preco,asc` | Filtra produtos por faixa de preço |
| GET | `/produtos/tamanho?tamanho=M&page=0&size=5&sort=nome,asc` | Filtra produtos por tamanho |
| GET | `/produtos/categoria?categoria=Camisetas&page=0&size=5&sort=preco,desc` | Filtra produtos por categoria |
| GET | `/produtos/cor?cor=Preto&page=0&size=5&sort=nome,asc` | Filtra produtos por cor |

Exemplo:

```http
GET http://localhost:8080/produtos/nome?nome=camiseta&page=0&size=5&sort=nome,asc
```

---

## Paginação e ordenação

A API possui endpoint específico para listar produtos com paginação e ordenação:

```http
GET http://localhost:8080/produtos/paginado?page=0&size=5&sort=nome,asc
```

Também é possível usar paginação e ordenação nos endpoints de filtro:

```http
GET http://localhost:8080/produtos/categoria?categoria=Camisetas&page=0&size=5&sort=preco,desc
```

Parâmetros:

- `page`: número da página, começando em 0;
- `size`: quantidade de registros por página;
- `sort`: campo usado para ordenação e direção, como `nome,asc` ou `preco,desc`.

Exemplo de resposta paginada:

```json
{
  "content": [
    {
      "id": 1,
      "nome": "Camiseta Básica Branca",
      "preco": 49.90
    }
  ],
  "number": 0,
  "size": 5,
  "totalElements": 10,
  "totalPages": 2
}
```

---

## Projection

O projeto utiliza uma projection chamada `ProdutoSummary`.

Ela é usada nos endpoints de filtro para retornar uma versão resumida dos produtos, evitando expor todos os campos da entidade completa.

Campos retornados pela projection:

- `id`
- `nome`
- `preco`
- `tamanho`
- `ativo`

Exemplo de endpoint que utiliza projection:

```http
GET http://localhost:8080/produtos/nome?nome=camiseta&page=0&size=5&sort=nome,asc
```

---

## Validações

O projeto utiliza validações padrão com Jakarta Validation.

Exemplos de annotations utilizadas:

- `@NotBlank`
- `@NotNull`
- `@Email`
- `@Size`
- `@Pattern`
- `@Positive`
- `@PositiveOrZero`

Essas validações impedem o cadastro ou atualização de dados inválidos, como campos vazios, preço negativo, email inválido e senha fora do padrão esperado.

---

## Validação personalizada

Além das validações padrão, o projeto possui uma validação personalizada para o campo `tipoMovimentacao`.

A annotation personalizada `@TipoMovimentacaoValida` permite apenas os seguintes valores:

- `ENTRADA`
- `SAIDA`

Exemplo inválido:

```json
{
  "estoque": {
    "id": 1
  },
  "funcionario": {
    "id": 1
  },
  "tipoMovimentacao": "TRANSFERENCIA",
  "quantidade": 10,
  "dataMovimentacao": "2026-04-30"
}
```

Nesse caso, a API retorna erro de validação, pois `TRANSFERENCIA` não é um tipo permitido.

---

## Tratamento de erros

O projeto possui um `ValidationHandler` para retornar erros de validação de forma organizada.

Exemplo de resposta:

```json
[
  {
    "field": "nome",
    "message": "não deve estar em branco"
  }
]
```

Também há tratamento para preservar erros de status, como `404 NOT_FOUND`, quando um recurso não é encontrado.

---

## Exemplos de payload

### Cadastro de produto

```json
{
  "nome": "Camiseta Oversized",
  "descricao": "Modelo casual em algodão",
  "preco": 89.90,
  "sku": "CAM-001",
  "tamanho": "M",
  "cor": "Preto",
  "marca": "70Graus",
  "ativo": true,
  "categoria": "Camisetas"
}
```

### Cadastro de funcionário

```json
{
  "nome": "Kevin Josh",
  "email": "kevin@email.com",
  "senha": "Senha123"
}
```

A senha precisa conter pelo menos:

- uma letra maiúscula;
- uma letra minúscula;
- um número.

### Cadastro de estoque

```json
{
  "produto": {
    "id": 1
  },
  "quantidadeDisponivel": 25,
  "quantidadeMinima": 5
}
```

### Cadastro de movimentação de entrada

```json
{
  "estoque": {
    "id": 1
  },
  "funcionario": {
    "id": 1
  },
  "tipoMovimentacao": "ENTRADA",
  "quantidade": 10,
  "dataMovimentacao": "2026-04-30"
}
```

### Cadastro de movimentação de saída

```json
{
  "estoque": {
    "id": 1
  },
  "funcionario": {
    "id": 1
  },
  "tipoMovimentacao": "SAIDA",
  "quantidade": 3,
  "dataMovimentacao": "2026-04-30"
}
```

---

## Coleção de endpoints

Na raiz do projeto existe o arquivo:

```text
endpoints-70graus.json
```

Esse arquivo contém os endpoints utilizados para testar a API em um API Client, como Insomnia ou Postman.

A coleção inclui testes para:

- CRUD de produtos;
- CRUD de estoque;
- CRUD de funcionários;
- CRUD de movimentações de estoque;
- filtros de produtos;
- paginação;
- ordenação;
- validações padrão;
- validação personalizada.

---

## Como executar o backend

### 1. Clonar o projeto

```bash
git clone <URL_DO_REPOSITORIO_BACKEND>
cd 70Graus
```

### 2. Executar a aplicação

No Linux ou macOS:

```bash
./mvnw spring-boot:run
```

No Windows:

```bash
mvnw.cmd spring-boot:run
```

### 3. Acessar a API

Por padrão, a aplicação sobe em:

```text
http://localhost:8080
```

---

## Banco de dados H2

O projeto utiliza **H2 em memória**, configurado em `application.properties`.

Exemplo de configuração:

```properties
spring.datasource.url=jdbc:h2:mem:graus
```

Isso significa que os dados são reiniciados sempre que a aplicação é encerrada.

Caso o console do H2 esteja habilitado, acesse:

```text
http://localhost:8080/h2-console
```

Configuração esperada:

```text
JDBC URL: jdbc:h2:mem:graus
User: sa
Password:
```

---

## Integração com o mobile

O frontend mobile foi desenvolvido em **React Native + Expo** e funciona como cliente da API.

Repositório do app mobile:

```bash
git clone https://github.com/KelsonZh0/70Graus.git
```

A integração no emulador Android pode apontar para:

```text
http://10.0.2.2:8080
```

Esse endereço funciona no **emulador Android**. Para testar em **dispositivo físico**, é necessário trocar `10.0.2.2` pelo IP local da máquina onde o backend estiver rodando.

---

## Observações técnicas

- O projeto possui configuração de **CORS liberada** para facilitar integração com o app mobile durante o desenvolvimento.
- O backend está configurado com **H2 em memória**, então os dados não permanecem após reiniciar a aplicação.
- O `DataLoader` popula dados iniciais para facilitar testes.
- Para testar exclusões, é recomendado criar um novo registro via `POST` e deletar esse novo registro, pois os dados iniciais possuem relacionamentos entre produtos, estoque e movimentações.


---

## Integrantes da equipe

| Nome | RM | Turma | GitHub | LinkedIn |
|---|---|---|---|---|
| Alexander Dennis Isidro Mamani | 565554 | 2TDSPG | [alex-isidro](https://github.com/alex-isidro) | [LinkedIn](https://www.linkedin.com/in/alexander-dennis-a3b48824b/) |
| Kelson Zhang | 563748 | 2TDSPG | [KelsonZh0](https://github.com/KelsonZh0) | [LinkedIn](https://www.linkedin.com/in/kelson-zhang-211456323/) |

---

Projeto acadêmico com foco em **desenvolvimento backend**, **APIs REST**, **persistência com JPA** e **integração com aplicação mobile**.
