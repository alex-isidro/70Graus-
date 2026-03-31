# 70Graus API

API REST desenvolvida em **Java + Spring Boot** para gerenciamento de produtos, estoque, movimentações e funcionários da operação **70Graus**.

O foco principal deste repositório é o **backend**, concentrando as regras de cadastro, consulta, atualização e remoção dos dados da aplicação. O projeto mobile foi utilizado como **cliente da API** e entra como complemento para testes e demonstração da integração.

---

## Visão geral

A aplicação foi construída com arquitetura simples e objetiva, separando:

- **controllers** para exposição dos endpoints REST
- **services** para regras de negócio
- **repositories** para acesso aos dados com Spring Data JPA
- **models** para representação das entidades do sistema

A persistência é feita com **H2 em memória**, o que facilita testes locais e demonstrações rápidas sem depender de um banco externo.

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

## Funcionalidades da API

### Produtos
- cadastro de produtos
- listagem de produtos
- busca por ID
- atualização de dados
- remoção de registros

### Estoque
- cadastro de estoque por produto
- consulta geral
- busca por ID
- atualização de quantidade e quantidade mínima
- remoção de registros

### Movimentação de estoque
- registro de movimentações
- listagem geral
- busca por ID
- atualização de movimentações
- remoção de registros

### Funcionários
- cadastro de funcionários
- listagem geral
- busca por ID
- atualização de dados
- remoção de registros

---

## Estrutura do projeto

```text
70Graus/
├── mvnw
├── mvnw.cmd
├── pom.xml
└── src/
    └── main/
        ├── java/fiap/com/br/graus/
        │   ├── config/
        │   ├── controllers/
        │   ├── model/
        │   ├── repositories/
        │   ├── services/
        │   └── GrausApplication.java
        └── resources/
            └── application.properties
```

---

## Endpoints principais

### Produto
| Método | Rota | Descrição |
|---|---|---|
| GET | `/produtos` | Lista todos os produtos |
| GET | `/produtos/{id}` | Busca produto por ID |
| POST | `/produtos` | Cadastra um novo produto |
| PUT | `/produtos/{id}` | Atualiza um produto |
| DELETE | `/produtos/{id}` | Remove um produto |

### Estoque
| Método | Rota | Descrição |
|---|---|---|
| GET | `/estoque` | Lista os registros de estoque |
| GET | `/estoque/{id}` | Busca estoque por ID |
| POST | `/estoque` | Cadastra um novo estoque |
| PUT | `/estoque/{id}` | Atualiza um estoque |
| DELETE | `/estoque/{id}` | Remove um estoque |

### Movimentação de estoque
| Método | Rota | Descrição |
|---|---|---|
| GET | `/movi-estoque` | Lista movimentações |
| GET | `/movi-estoque/{id}` | Busca movimentação por ID |
| POST | `/movi-estoque` | Registra uma movimentação |
| PUT | `/movi-estoque/{id}` | Atualiza uma movimentação |
| DELETE | `/movi-estoque/{id}` | Remove uma movimentação |

### Funcionário
| Método | Rota | Descrição |
|---|---|---|
| GET | `/funcionario` | Lista todos os funcionários |
| GET | `/funcionario/{id}` | Busca funcionário por ID |
| POST | `/funcionario` | Cadastra um funcionário |
| PUT | `/funcionario/{id}` | Atualiza um funcionário |
| DELETE | `/funcionario/{id}` | Remove um funcionário |

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
  "ativo": true
}
```

### Cadastro de funcionário
```json
{
  "nome": "Kevin Josh",
  "email": "kevin@email.com",
  "senha": "123456"
}
```

### Cadastro de estoque
```json
{
  "produtoId": 1,
  "quantidadeDisponivel": 25,
  "quantidadeMinima": 5
}
```

### Cadastro de movimentação
```json
{
  "estoqueId": 1,
  "funcionarioId": 1,
  "tipoMovimentacao": "ENTRADA",
  "quantidade": 10,
  "dataMovimentacao": "2026-03-31"
}
```

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

## Banco de dados

O projeto utiliza **H2 em memória**, configurado em `application.properties`:

```properties
spring.datasource.url=jdbc:h2:mem:graus
```

Isso significa que os dados são reiniciados sempre que a aplicação é encerrada.

---

## Integração com o mobile

O frontend mobile foi desenvolvido em **React Native + Expo** e funciona como **cliente da API**.

Repositório do app mobile:

```bash
git clone https://github.com/KelsonZh0/70Graus.git
```

A integração atualmente aponta para:

```text
http://10.0.2.2:8080
```

Esse endereço funciona no **emulador Android**. Para testar em **dispositivo físico**, é necessário trocar `10.0.2.2` pelo IP local da máquina onde o backend estiver rodando.

---

## Observações técnicas

- O projeto possui configuração de **CORS liberada** para facilitar integração com o app mobile durante o desenvolvimento.
- O backend está configurado com **H2 em memória**, então os dados não permanecem após reiniciar a aplicação.
- O `pom.xml` declara **Java 17** e dependências do **Spring Boot 4.0.4**. Antes de publicar ou apresentar, vale validar a compatibilidade do ambiente local para evitar problemas de execução.

---

## 👥 Integrantes da Equipe

| Nome                           | RM      | Turma   | GitHub                                        | LinkedIn                                                                |
| ------------------------------ | ------- | ------- | --------------------------------------------- | ----------------------------------------------------------------------- |
| Alexander Dennis Isidro Mamani | 565554  | 2TDSPG  | [alex-isidro](https://github.com/alex-isidro) | [LinkedIn](https://www.linkedin.com/in/alexander-dennis-a3b48824b/)     |
| Kelson Zhang                   | 563748  | 2TDSPG  | [KelsonZh0](https://github.com/KelsonZh0)     | [LinkedIn](https://www.linkedin.com/in/kelson-zhang-211456323/)         |

---

Projeto acadêmico com foco em **desenvolvimento backend**, **APIs REST** e **integração com aplicação mobile**.
