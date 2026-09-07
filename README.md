# Sistema Bancário API

API REST de um sistema bancário desenvolvida em Java com Spring Boot, utilizando MySQL para persistência dos dados.

O projeto foi desenvolvido com o objetivo de praticar e aplicar conceitos de desenvolvimento backend, como APIs REST, Spring Boot, JPA, Hibernate, banco de dados relacional, validações, tratamento de exceções e documentação de APIs.

## Tecnologias utilizadas

- Java 21
- Spring Boot 4.1.0
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Swagger / OpenAPI
- Jakarta Validation
- IntelliJ IDEA
- Git / GitHub

## Funcionalidades

### Clientes

- Cadastro de clientes
- Listagem de clientes
- Busca de cliente por CPF
- Atualização de dados
- Exclusão de clientes
- Validação dos dados
- CPF único
- Impedimento da exclusão de clientes que possuem contas vinculadas

### Contas

- Criação de contas vinculadas a clientes
- Geração automática do número da conta
- Saldo inicial igual a zero
- Listagem de contas
- Busca de conta por número
- Exclusão de contas
- Ativação e desativação de contas

### Operações bancárias

- Depósito
- Saque
- Transferência entre contas
- Validação de valores positivos
- Verificação de saldo insuficiente
- Bloqueio de operações em contas inativas
- Impedimento de transferência para a própria conta
- Controle transacional das transferências

## Regras de negócio

- Cada cliente deve possuir um CPF único.
- Uma conta deve estar vinculada a um cliente existente.
- O número da conta é gerado automaticamente.
- O saldo inicial de uma conta é zero.
- Depósitos devem possuir valores maiores que zero.
- Saques devem possuir valores maiores que zero.
- Não é possível realizar um saque com saldo insuficiente.
- Contas inativas não podem realizar operações bancárias.
- Não é possível transferir dinheiro para a própria conta.
- A transferência verifica as contas de origem e destino.
- A transferência é executada dentro de uma transação.
- Clientes que possuem contas vinculadas não podem ser excluídos.

## Tratamento de erros

A aplicação possui exceções personalizadas e um `GlobalExceptionHandler` responsável por transformar essas exceções em respostas HTTP adequadas.

Principais códigos utilizados:

- `400 Bad Request` — operação inválida ou dados de entrada inválidos.
- `404 Not Found` — cliente ou conta não encontrada.
- `409 Conflict` — tentativa de cadastrar um CPF já existente.

As respostas de erro são retornadas em formato JSON.

Exemplo:

```json
{
  "erro": "Conta nao encontrada!"
}
```

## Documentação da API

A API possui documentação utilizando OpenAPI e Swagger UI.

Com a aplicação em execução, a documentação pode ser acessada em:

```text
http://localhost:8080/swagger-ui/index.html
```

A especificação OpenAPI pode ser acessada em:

```text
http://localhost:8080/v3/api-docs
```

A documentação apresenta os endpoints da API, parâmetros, requisições, respostas e possíveis códigos HTTP.

## Estrutura do projeto

```text
src
└── main
    └── java
        └── com.vittor.sistema_bancario_api
            ├── config
            ├── controller
            ├── dto
            ├── entity
            ├── exception
            ├── repository
            └── service
```

### Organização das camadas

**Controller**

Responsável por receber as requisições HTTP e encaminhá-las para a camada de serviço.

**Service**

Contém as regras de negócio da aplicação.

**Repository**

Responsável pela comunicação com o banco de dados utilizando Spring Data JPA.

**Entity**

Representa as entidades persistidas no banco de dados.

**DTO**

Responsável por transportar dados entre a API e o cliente, além de permitir validações específicas para as requisições.

**Exception**

Contém as exceções personalizadas utilizadas pela aplicação.

**Config**

Contém configurações adicionais da aplicação.

## Relacionamento entre as entidades

O sistema possui um relacionamento entre `Cliente` e `Conta`.

Um cliente pode possuir várias contas, enquanto cada conta pertence a um único cliente.

```text
Cliente
   │
   └── 1:N ──> Conta
```

O relacionamento é implementado utilizando JPA/Hibernate.

## Validação dos dados

A API utiliza Jakarta Validation para validar os dados recebidos nas requisições.

Por exemplo, valores utilizados em operações bancárias devem ser maiores que zero.

Quando uma validação falha, a API retorna `400 Bad Request` contendo as mensagens correspondentes aos campos inválidos.

## Problemas encontrados e soluções

### Loop na serialização JSON

Foi identificado um problema na conversão das entidades para JSON causado pelo relacionamento bidirecional entre `Cliente` e `Conta`.

A estrutura permitia que a serialização percorresse repetidamente:

```text
Conta → Cliente → Contas → Cliente → ...
```

Isso causava problemas na resposta JSON da API.

O problema foi corrigido utilizando `@JsonIgnore` no relacionamento apropriado, impedindo a serialização recursiva.
