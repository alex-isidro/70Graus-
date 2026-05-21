# 70Graus API - Projeto Diamante 03

API REST desenvolvida em Java com Spring para o Projeto Diamante 03 da disciplina Java Advanced.

O sistema representa o controle de uma loja de roupas, com cadastro de produtos, controle de estoque, funcionários e movimentações de entrada/saída.

## Objetivo

Criar uma API REST real e contextualizada usando os principais recursos estudados no semestre:

- Spring Web MVC
- Spring Data JPA
- Banco relacional H2
- Bean Validation
- Paginação e ordenação
- Projections
- Swagger / OpenAPI
- Spring Cache
- Spring Actuator
- HATEOAS

## Entidades principais

### Produto
Representa uma peça da loja.

Campos principais:

- id
- nome
- descrição
- preço
- SKU
- tamanho
- cor
- marca
- categoria
- ativo

Relacionamento:

- Um produto pode possuir vários registros de estoque.

### Estoque
Representa a quantidade disponível de um produto.

Campos principais:

- id
- produto
- quantidade disponível
- quantidade mínima

Relacionamento:

- Muitos registros de estoque pertencem a um produto.
- Um estoque pode possuir várias movimentações.

### Funcionário
Representa o funcionário responsável por movimentações de estoque.

Campos principais:

- id
- nome
- email
- senha

Relacionamento:

- Um funcionário pode realizar várias movimentações de estoque.

### Movimentação de Estoque
Representa uma entrada ou saída de produtos do estoque.

Campos principais:

- id
- tipo de movimentação: `ENTRADA` ou `SAIDA`
- quantidade
- data da movimentação
- estoque
- funcionário

Relacionamento:

- Muitas movimentações pertencem a um estoque.
- Muitas movimentações são realizadas por um funcionário.

## Como executar

### Pré-requisitos

- Java 17+
- Maven ou Maven Wrapper

### Rodar o projeto

No terminal, dentro da pasta do projeto:

```bash
./mvnw spring-boot:run
```

No Windows PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

A API será iniciada em:

```text
http://localhost:8080
```

## Banco de dados H2

Console do H2:

```text
http://localhost:8080/h2-console
```

Configuração:

```text
JDBC URL: jdbc:h2:mem:graus
User: sa
Password: deixar vazio
```

## Swagger / OpenAPI

Documentação automática da API:

```text
http://localhost:8080/swagger-ui.html
```

JSON OpenAPI:

```text
http://localhost:8080/v3/api-docs
```

## Spring Actuator

Endpoints expostos:

```text
http://localhost:8080/actuator/health
http://localhost:8080/actuator/info
http://localhost:8080/actuator/metrics
http://localhost:8080/actuator/caches
```

## Endpoints principais

### Produtos

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/produtos` | Lista todos os produtos com links HATEOAS |
| GET | `/produtos/paginado?page=0&size=5&sort=nome,asc` | Lista produtos com paginação e ordenação |
| GET | `/produtos/{id}` | Busca produto por id |
| POST | `/produtos` | Cadastra produto |
| PUT | `/produtos/{id}` | Atualiza produto |
| DELETE | `/produtos/{id}` | Remove produto |
| GET | `/produtos/nome?nome=camiseta&page=0&size=5` | Busca por nome usando projection |
| GET | `/produtos/preco?min=50&max=200&page=0&size=5` | Busca por faixa de preço usando projection |
| GET | `/produtos/tamanho?tamanho=M&page=0&size=5` | Busca por tamanho usando projection |
| GET | `/produtos/categoria?categoria=Camisetas&page=0&size=5` | Busca por categoria usando projection |
| GET | `/produtos/cor?cor=Preto&page=0&size=5` | Busca por cor usando projection |

### Estoque

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/estoque` | Lista todos os estoques com links HATEOAS |
| GET | `/estoque/paginado?page=0&size=5&sort=id,asc` | Lista estoques com paginação e ordenação |
| GET | `/estoque/{id}` | Busca estoque por id |
| POST | `/estoque` | Cadastra estoque |
| PUT | `/estoque/{id}` | Atualiza estoque |
| DELETE | `/estoque/{id}` | Remove estoque |

### Funcionários

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/funcionario` | Lista funcionários com links HATEOAS |
| GET | `/funcionario/paginado?page=0&size=5&sort=nome,asc` | Lista funcionários com paginação e ordenação |
| GET | `/funcionario/{id}` | Busca funcionário por id |
| POST | `/funcionario` | Cadastra funcionário |
| PUT | `/funcionario/{id}` | Atualiza funcionário |
| DELETE | `/funcionario/{id}` | Remove funcionário |

### Movimentações de estoque

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/movi-estoque` | Lista movimentações com links HATEOAS |
| GET | `/movi-estoque/paginado?page=0&size=5&sort=dataMovimentacao,desc` | Lista movimentações com paginação e ordenação |
| GET | `/movi-estoque/{id}` | Busca movimentação por id |
| POST | `/movi-estoque` | Cadastra movimentação |
| PUT | `/movi-estoque/{id}` | Atualiza movimentação |
| DELETE | `/movi-estoque/{id}` | Remove movimentação |

## Exemplos de JSON

### Criar produto

```json
{
  "nome": "Camiseta Oversized Preta",
  "descricao": "Camiseta oversized em algodão premium",
  "preco": 89.90,
  "sku": "SKUOV011",
  "tamanho": "G",
  "cor": "Preto",
  "marca": "70 Graus",
  "ativo": true,
  "categoria": "Camisetas"
}
```

### Criar estoque

```json
{
  "produto": {
    "id": 1
  },
  "quantidadeDisponivel": 20,
  "quantidadeMinima": 5
}
```

### Criar funcionário

```json
{
  "nome": "Maria Silva",
  "email": "maria@email.com",
  "senha": "Senha123"
}
```

### Criar movimentação de estoque

```json
{
  "tipoMovimentacao": "ENTRADA",
  "quantidade": 5,
  "dataMovimentacao": "2026-05-21",
  "estoque": {
    "id": 1
  },
  "funcionario": {
    "id": 1
  }
}
```

