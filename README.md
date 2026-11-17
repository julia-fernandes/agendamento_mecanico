# DESAFIO PRÁTICO

**API de Agendamento Mecânico \| Backend com Java e Spring Boot**

## 1. Contexto

Uma oficina mecânica deseja informatizar o controle dos agendamentos de
veículos que chegam para manutenção. Atualmente, as informações são
registradas manualmente, dificultando a consulta dos serviços
programados, o acompanhamento dos atendimentos e o controle dos
registros ativos e inativos.

Para solucionar esse problema, a oficina solicitou o desenvolvimento de
uma API REST utilizando Java e Spring Boot, responsável por gerenciar os
agendamentos mecânicos.

## 2. Desafio

Desenvolver uma API REST para gerenciamento de agendamentos mecânicos,
implementando as operações de cadastrar, consultar, alterar e excluir
registros. A API deverá persistir os dados em PostgreSQL, validar as
informações recebidas e disponibilizar endpoints que possam ser testados
com Postman ou Insomnia.

**Escopo:** exclusivamente backend. Não é necessário desenvolver páginas
HTML, CSS, JavaScript ou qualquer interface gráfica.

## 3. Dados do agendamento

| Campo | Tipo sugerido | Descrição |
|---|---|---|
| id | Long | Identificador único gerado automaticamente. |
| nomePessoa | String | Nome do cliente responsável pelo veículo. |
| placaVeiculo | String | Placa do veículo. |
| veiculo | String | Marca, modelo ou identificação do veículo. |
| dataEntrada | LocalDate | Data prevista para entrada na oficina. |
| horaEntrada | LocalTime | Horário previsto para entrada. |
| andamento | Definido pelo aluno | Situação do atendimento, por exemplo: a fazer, em andamento ou finalizado. |
| statusRegistro | Definido pelo aluno | Situação do registro, por exemplo: ativo ou inativo. |

## 4. Requisitos funcionais

### RF01 ── Cadastrar agendamento

Permitir o cadastro de um agendamento com os dados obrigatórios. O
identificador deverá ser gerado automaticamente.

### RF02 ── Consultar agendamentos

Permitir listar os agendamentos e consultar um registro específico pelo
identificador.

### RF03 ── Alterar agendamento

Permitir modificar os dados de um agendamento existente, incluindo data,
horário, andamento e status do registro.

### RF04 ── Excluir agendamento

Permitir excluir ou inativar um agendamento por seu identificador,
retornando uma resposta adequada à operação.

### RF05 ── Consultar por filtros (opcional)

Como extensão, permitir filtrar por placa, nome da pessoa, data de
entrada, andamento ou status do registro.

## 5. Regras de negócio

-   **RN01:** Todo agendamento deve possuir um identificador único.
-   **RN02:** Nome da pessoa, placa do veículo, veículo, data de
    entrada, hora de entrada, andamento e status do registro são
    obrigatórios.
-   **RN03:** O aluno deverá definir como representar e validar os
    valores possíveis para andamento e status do registro.
-   **RN04:** Não deve ser possível alterar ou excluir um agendamento
    inexistente.
-   **RN05:** Consultas por identificador devem retornar uma resposta de
    não encontrado quando o agendamento não existir.
-   **RN06:** A API deverá validar os dados recebidos e retornar
    mensagens adequadas quando houver informações inválidas.
-   **RN07:** O andamento do atendimento e o status do registro
    representam conceitos diferentes e não devem ser confundidos.
-   **RN08:** A estratégia de exclusão deverá ser definida e
    implementada pelo aluno: exclusão física ou exclusão lógica por meio
    do status do registro.

## 6. Endpoints esperados

| Método | Endpoint | Finalidade | Resposta esperada |
|---|---|---|---|
| POST | /api/agendamentos | Cadastrar | 201 Created |
| GET | /api/agendamentos | Listar todos | 200 OK |
| GET | /api/agendamentos/{id} | Consultar por ID | 200 OK ou 404 Not Found |
| PUT | /api/agendamentos/{id} | Alterar | 200 OK ou 404 Not Found |
| DELETE | /api/agendamentos/{id} | Excluir/inativar | 204 No Content ou resposta equivalente documentada |

Os códigos HTTP são referências recomendadas. O aluno deverá manter
respostas coerentes com o resultado de cada operação e tratar
adequadamente erros de validação e registros inexistentes.

## 7. Exemplo de requisição para cadastro

**Método:** POST

**Endpoint:** /api/agendamentos

``` json
{
  "nomePessoa": "Carlos Mendes",
  "placaVeiculo": "ABC1D23",
  "veiculo": "Honda Civic",
  "dataEntrada": "2026-11-10",
  "horaEntrada": "08:30:00",
  "andamento": "A_FAZER",
  "statusRegistro": "ATIVO"
}
```

Os valores de andamento e status apresentados são apenas exemplos. O
aluno deverá definir a representação dos valores adotados e utilizá-la
de forma consistente na API.

## 8. Requisitos técnicos

-   Java e Spring Boot.
-   Spring Web para criação dos endpoints REST.
-   Spring Data JPA para persistência.
-   PostgreSQL como banco de dados obrigatório.
-   Validação dos dados recebidos.
-   Tratamento adequado de exceções e respostas HTTP.
-   [Testes dos endpoints utilizando Postman ou Insomnia.](docs/imagens/)
-   Organização do código em camadas, com Controller, Repository e
    Model/Entity. A camada Service é opcional.

## 9. Resultado esperado

Ao finalizar o desafio, o estudante deverá entregar uma API funcional,
capaz de gerenciar agendamentos mecânicos por meio de requisições HTTP,
com persistência no PostgreSQL, validações e respostas adequadas. A
solução deverá demonstrar a aplicação de conceitos de programação
orientada a objetos, desenvolvimento de APIs REST, persistência de
dados, separação de responsabilidades e tratamento de erros.

## 10. Checklist para entrega

- [x] Projeto Spring Boot executando corretamente.
- [x] Entidade de agendamento com todos os campos definidos.
- [x] Conexão com PostgreSQL funcionando.
- [x] Cadastro e consulta de agendamentos testados.
- [x] Alteração e exclusão testadas.
- [x] Validações e regras de negócio implementadas.
- [x] Tratamento de erros e códigos HTTP verificados.
- [x] Evidências dos testes no Postman ou Insomnia.
