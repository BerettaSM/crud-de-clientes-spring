# CRUD de Clientes - API REST
[![SPRING FRAMEWORK](https://img.shields.io/badge/Spring%20framework-6DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://github.com/BerettaSM/exemplo-readme/blob/main/LICENSE) [![JAVA](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://github.com/BerettaSM/exemplo-readme/blob/main/LICENSE) 

## Sobre o projeto

Este projeto é uma API REST desenvolvida com Spring Boot para gerenciar um CRUD (Create, Read, Update, Delete) de clientes. A aplicação fornece endpoints para gerenciar clientes e realiza validações e tratamento de exceções conforme o desafio proposto pela [DevSuperior](https://devsuperior.com "Site da DevSuperior").

# Descrição

A API permite a execução das cinco operações básicas em um recurso de cliente:

- Busca paginada de recursos

- Busca de recurso por ID

- Inserir novo recurso

- Atualizar recurso

- Deletar recurso

## Entidade *Client*

A entidade Client é composta pelos seguintes campos:

- **id (Long)** - Identificador único do cliente.

- **name (String)** - Nome do cliente.

- **cpf (String)** - CPF do cliente.

- **income (Double)** - Renda do cliente.

- **birthDate (LocalDate)** - Data de nascimento do cliente.

- **children (Integer)** - Quantidade de filhos do cliente.

## Endpoints

### 1. Busca por ID de Cliente

    Endpoint: GET /clients/{id}

    Descrição: Busca um cliente pelo seu ID.

    Resposta 200: Cliente encontrado.

    Resposta 404: Cliente não encontrado.

    Exemplo de Requisição:

        GET /clients/1

### 2. Busca Paginada de Clientes

    Endpoint: GET /clients

    Descrição: Busca uma lista paginada de clientes.

    Parâmetros (opcionais):

        page (int): Número da página (padrão: 0).

        size (int): Tamanho da página (padrão: 20).

        sort (String): Ordenação dos resultados por um dado campo.

    Exemplo de Requisição:

        GET /clients?page=0&size=6&sort=name

### 3. Inserir Novo Cliente

    Endpoint: POST /clients

    Descrição: Cria um novo cliente.

    Corpo da Requisição:

        {
            "name": "Maria Silva",
            "cpf": "64336160031",
            "income": 6500.0,
            "birthDate": "1994-07-20",
            "children": 2
        }

    Resposta 201: Cliente inserido com sucesso.

    Response 409: CPF já registrado.

    Resposta 422: Validação falhou (mensagens customizadas).

Exemplo de cURL para inserção de cliente:

```bash
curl -X POST http://localhost:8080/clients \
    -H "Content-Type: application/json" \
    -d '{ "name": "Maria Silva", "cpf": "64336160031", "income": 6500.0, "birthDate": "1994-07-20", "children": 2 }'
```

### 4. Atualizar Cliente

    Endpoint: PUT /clients/{id}

    Descrição: Atualiza os dados de um cliente existente.

    Corpo da Requisição:

        {
            "name": "Maria Silva",
            "cpf": "64336160031",
            "income": 6500.0,
            "birthDate": "1994-07-20",
            "children": 2
        }

    Resposta 200: Cliente atualizado com sucesso.

    Resposta 404: Cliente não encontrado.

    Response 409: CPF já registrado.

    Resposta 422: Validação falhou (mensagens customizadas).

### 5. Deletar Cliente

    Endpoint: DELETE /clients/{id}

    Descrição: Deleta um cliente pelo ID.

    Resposta 204: Cliente deletado com sucesso.

    Resposta 404: Cliente não encontrado.

# Tecnologias utilizadas
## Back end
- Java
- Spring Boot
- JPA / Hibernate
- H2 Database
- Maven

# Como executar o projeto


## Back end
Pré-requisitos: Java 21

```bash
# clonar repositório
git clone https://github.com/BerettaSM/crud-de-clientes-spring.git

# entrar na pasta do projeto crud-de-clientes-spring
cd crud-de-clientes-spring

# (Linux/Mac) Atribuir permissões de execução
chmod +x mvnw

# executar o projeto
./mvnw spring-boot:run
```

A aplicação estará disponível em http://localhost:8080.

Utilize os endpoints para interagir com a API. Você pode usar ferramentas como o Postman ou cURL para testar os endpoints.

## Database
O projeto utiliza o banco de dados H2 em memória para desenvolvimento e testes. Não é necessário configurar nenhum banco de dados externo.

# Autor

Ramon Saviato de Matos


