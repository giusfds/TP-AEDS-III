# Trabalho Pratico AEDs 3

## Descrição
Este projeto tem como objetivo a implementação de um CRUD (Create, Read, Update, Delete) genérico em Java, capaz de manipular entidades armazenadas em arquivos. O foco inicial do projeto é gerenciar tarefas, incluindo operações como inclusão, leitura, atualização e exclusão de registros.

Cada tarefa possui os seguintes atributos:

- **Nome**: Descrição da tarefa.
- **Data de Criação**: Data em que a tarefa foi criada.
- **Data de Conclusão**: Data em que a tarefa foi concluída.
- **Status**: Estado atual da tarefa (Pendente, Em Progresso, Concluída, etc.).
- **Prioridade**: Nível de prioridade da tarefa.

## Como Usar

### Pré-requisitos

- Java Development Kit (JDK) 8 ou superior.
- Um ambiente de desenvolvimento Java (Eclipse, IntelliJ IDEA, etc.).

### Passos para Executar

1. Faça um fork deste repositório.
2. Clone o seu fork: `git clone`.
3. Crie uma branch: `git checkout -b minha-branch`.
   - Por favor, não use a branch `main`.
   - Cada aluno deve criar uma branch com seu nome; eu decidirei se o pull request será aceito ou não, apenas para garantir que seja alguém do grupo.
4. Faça suas alterações.
5. Faça o commit das suas alterações: `git commit -m 'minhas alterações'`.
6. Envie sua branch: `git push origin minha-branch`.
7. Crie um pull request.

**Atenção:** Certifique-se de que todas as alterações sejam enviadas para a branch `dev` e não para a branch `main`. A branch `main` é reservada para a versão estável e final do projeto.

## Estrutura do Registro
Os registros no arquivo são compostos por três partes:

1. **Lápide**: Byte que indica se o registro é válido ou foi excluído.
2. **Indicador de Tamanho**: Número inteiro que indica o tamanho do registro em bytes.
3. **Vetor de Bytes**: Dados da entidade convertidos em bytes.

### Operações Implementadas
As operações de CRUD são realizadas por uma classe genérica `Arquivo<T>` que pode manipular qualquer entidade que implemente a interface `Registro`. A interface `Registro` define os métodos necessários para que uma entidade possa ser armazenada e manipulada no arquivo.

### Índice Direto
O projeto utiliza um índice direto baseado em tabela hash extensível para gerenciar os registros. Este índice armazena a chave primária (ID) e o endereço do registro, facilitando as operações de busca, inserção, atualização e exclusão.

## Estrutura do Projeto
### Classes Principais
- Arquivo<T extends Registro>: Classe genérica que gerencia as operações de CRUD no arquivo de dados.
- Tarefa: Classe que representa a entidade Tarefa, implementando a interface Registro.
- Teste: Classe principal que realiza testes das operações CRUD utilizando a entidade Tarefa.
### Métodos Principais
- create(T objeto): Insere um novo registro no arquivo e retorna o ID gerado.
- read(int id): Lê um registro do arquivo com base no ID e retorna o objeto correspondente.
- update(T objeto): Atualiza um registro existente no arquivo com base no ID do objeto.
- delete(int id): Marca um registro como excluído no arquivo.

## Checklist

- O trabalho possui um índice direto implementado com a tabela hash extensível?
- A operação de inclusão insere um novo registro no fim do arquivo e no índice e retorna o ID desse registro?
- A operação de busca retorna os dados do registro, após localizá-lo por meio do índice direto?
- A operação de alteração altera os dados do registro e trata corretamente as reduções e aumentos no espaço do registro?
- A operação de exclusão marca o registro como excluído e o remove do índice direto?
- O trabalho está funcionando corretamente?
- O trabalho está completo?
- O trabalho é original e não a cópia de um trabalho de outro grupo?

## Integrantes
- [Breno Pires](https://www.linkedin.com/in/brenopiressantos/)
- [Caio Faria](https://www.linkedin.com/in/caiofdiniz)
- [Giuseppe Cordeiro](https://www.linkedin.com/in/giuseppecordeiro/)
- [Vinicius Miranda](https://www.linkedin.com/in/vinimiraa/)

**Regras** 
Certifique-se de comentar seu commit seguindo o padrão:

**criação** => create: criar um novo arquivo
**nova funcionalidade** => feat: nova funcionalidade
**correção de bug** => fix: correção de bug
**documentação** => docs: documentação

## Experiência de Desenvolvimento
Durante o desenvolvimento do projeto, implementamos todas as funcionalidades básicas exigidas para o CRUD de tarefas. O maior desafio foi a implementação da tabela hash extensível para o índice direto, mas conseguimos alcançar os resultados esperados. A experiência foi enriquecedora, pois nos permitiu aplicar conceitos teóricos em um cenário prático.

