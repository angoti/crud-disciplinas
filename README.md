# CRUD Disciplinas

Sistema web para gerenciamento de disciplinas acadêmicas e professores utilizando Spring Boot, Thymeleaf e JDBC.

## 📋 Descrição do Projeto

Este é um sistema CRUD (Create, Read, Update, Delete) completo desenvolvido em Java com Spring Boot. O sistema permite gerenciar disciplinas acadêmicas e seus respectivos professores, incluindo informações como período letivo e código de sala do Google Classroom.

## 🚀 Tecnologias Utilizadas

- **Java 8** - Linguagem de programação
- **Spring Boot 2.4.0** - Framework principal
  - Spring Web - Para desenvolvimento web MVC
  - Spring Boot DevTools - Ferramentas de desenvolvimento
  - Spring Data JDBC - Acesso a dados via JDBC
- **Thymeleaf** - Engine de templates para renderização de páginas HTML
- **H2 Database** - Banco de dados em memória (desenvolvimento)
- **MySQL** - Banco de dados (produção)
- **Maven** - Gerenciador de dependências e build
- **Bootstrap & Material Icons** - Framework CSS e ícones para interface

## 📁 Estrutura do Projeto

```
crud-disciplinas/
├── src/
│   ├── main/
│   │   ├── java/com/angoti/crud/
│   │   │   ├── DisciplinaApplication.java          # Classe principal do Spring Boot
│   │   │   ├── controle/                           # Camada de Controllers
│   │   │   │   ├── HomeControle.java               # Controller da página inicial
│   │   │   │   ├── DisciplinaControle.java         # Controller de Disciplinas
│   │   │   │   └── ProfessorControle.java          # Controller de Professores
│   │   │   ├── dao/                                # Camada de acesso a dados
│   │   │   │   ├── DisciplinaDAO.java              # DAO de Disciplinas
│   │   │   │   ├── ProfessorDAO.java               # DAO de Professores
│   │   │   │   └── FabricaDeConexao.java           # Factory de conexões
│   │   │   └── dominio/                            # Camada de modelo/entidades
│   │   │       ├── Disciplina.java                 # Entidade Disciplina
│   │   │       └── Professor.java                  # Entidade Professor
│   │   └── resources/
│   │       ├── application.properties               # Configurações da aplicação
│   │       ├── application-prod.properties          # Configurações de produção
│   │       └── templates/                           # Templates Thymeleaf
│   │           ├── home.html                        # Página inicial
│   │           ├── disciplinas.html                 # Listagem de disciplinas
│   │           ├── disciplina-form.html             # Formulário de disciplina
│   │           ├── professores.html                 # Listagem de professores
│   │           ├── professor-form.html              # Formulário de professor
│   │           ├── error.html                       # Página de erro
│   │           └── fragments/                       # Fragmentos reutilizáveis
│   └── test/
├── pom.xml                                          # Configuração Maven
├── Procfile                                         # Configuração para deploy (Heroku)
└── schema.sql                                       # Script de criação do banco
```

## 🎯 Modelo de Domínio

### Classe Professor

Representa um professor no sistema.

**Atributos:**
- `id` (Integer): Identificador único do professor
- `nome` (String): Nome completo do professor

**Construtores:**
```java
Professor()                           // Construtor vazio
Professor(String nome)                // Construtor com nome
Professor(String nome, Integer id)   // Construtor completo
```

**Métodos principais:**
- `getId()` / `setId(Integer id)`: Getter e setter para o ID
- `getNome()` / `setNome(String nome)`: Getter e setter para o nome

### Classe Disciplina

Representa uma disciplina acadêmica no sistema.

**Atributos:**
- `id` (Integer): Identificador único da disciplina
- `nome` (String): Nome da disciplina
- `periodo` (Integer): Período letivo em que a disciplina é oferecida
- `codigo` (String): Código da sala no Google Classroom
- `professor` (Professor): Objeto Professor responsável pela disciplina

**Construtores:**
```java
Disciplina()                                                           // Construtor vazio
Disciplina(Integer periodo, String nome, Professor professor, String codigo)
Disciplina(Integer id, Integer periodo, String nome, Professor professor, String codigo)
```

**Métodos principais:**
- Getters e setters para todos os atributos

## 🗄️ Camada DAO (Data Access Object)

### ProfessorDAO

Classe responsável pelo acesso aos dados de professores no banco de dados.

**Principais operações:**

1. **`todos()`**: Retorna lista com todos os professores
   ```java
   List<Professor> todos()
   ```

2. **`inserir(Professor professor)`**: Insere um novo professor
   ```java
   void inserir(Professor professor)
   ```
   - SQL: `INSERT INTO professor(nome) VALUES (?)`

3. **`buscaPorId(Integer cod)`**: Busca professor por ID
   ```java
   Professor buscaPorId(Integer cod)
   ```

4. **`atualizar(Professor professor)`**: Atualiza dados do professor
   ```java
   void atualizar(Professor professor)
   ```
   - SQL: `UPDATE professor SET nome=? WHERE id=?`

5. **`excluir(Integer cod)`**: Exclui professor por ID
   ```java
   void excluir(Integer cod)
   ```
   - SQL: `DELETE FROM professor WHERE id=?`

**Mapeamento de dados:**
- Utiliza `ProfessorRowMapper` para converter ResultSet em objeto Professor
- Usa `JdbcTemplate` do Spring para executar queries

### DisciplinaDAO

Classe responsável pelo acesso aos dados de disciplinas no banco de dados.

**Principais operações:**

1. **`todos()`**: Retorna lista com todas as disciplinas e seus professores
   ```java
   List<Disciplina> todos()
   ```
   - Realiza JOIN entre tabelas disciplina e professor

2. **`inserir(Disciplina disciplina)`**: Insere uma nova disciplina
   ```java
   void inserir(Disciplina disciplina)
   ```
   - SQL: `INSERT INTO disciplina(nome,prof,periodo,codigo_sala_classroom) VALUES (?,?,?,?)`

3. **`buscaPorId(int id)`**: Busca disciplina por ID com dados do professor
   ```java
   Disciplina buscaPorId(int id)
   ```

4. **`atualizar(Disciplina disciplina)`**: Atualiza dados da disciplina
   ```java
   void atualizar(Disciplina disciplina)
   ```
   - SQL: `UPDATE disciplina SET nome=?,prof=?,periodo=?,codigo_sala_classroom=? WHERE id=?`

5. **`excluir(int id)`**: Exclui disciplina por ID
   ```java
   void excluir(int id)
   ```
   - SQL: `DELETE FROM disciplina WHERE id=?`

**Mapeamento de dados:**
- Utiliza `DisciplinaRowMapper` para converter ResultSet em objeto Disciplina
- Mapeia também o objeto Professor associado à disciplina

### FabricaDeConexao

Implementa o padrão Singleton para gerenciar conexões com o banco de dados H2.

**Características:**
- Thread-safe utilizando sincronização
- Cria conexão única com banco H2 em memória
- URL: `jdbc:h2:mem:testdb`
- Usuário: `sa`
- Senha: vazia

## 🎮 Camada de Controllers

### HomeControle

Controller responsável pelas páginas iniciais do sistema.

**Endpoints:**

1. **`GET /`**: Página inicial principal
   - Retorna: `home.html`
   - Exibe diagrama ER e descrição do sistema

2. **`GET /home2`**: Página alternativa
   - Retorna: `home2.html`

### ProfessorControle

Controller responsável pelo gerenciamento de professores.

**Endpoints:**

1. **`GET /professores`**: Lista todos os professores
   - Retorna: `professores.html`
   - Model: `lista` contendo todos os professores

2. **`GET /professor-form`**: Exibe formulário para novo professor
   - Retorna: `professor-form.html`
   - Model: objeto `professor` vazio

3. **`POST /professor-form`**: Processa criação de professor
   - Recebe: objeto Professor do formulário
   - Redireciona para: `/professores`

4. **`GET /editar-professor?id={id}`**: Exibe formulário de edição
   - Parâmetro: `id` do professor
   - Retorna: `professor-form.html`
   - Model: objeto `professor` preenchido

5. **`POST /editar-professor`**: Processa edição de professor
   - Recebe: objeto Professor atualizado
   - Redireciona para: `/professores`

6. **`GET /excluir-professor?id={id}`**: Exclui professor
   - Parâmetro: `id` do professor
   - Redireciona para: `/professores`

### DisciplinaControle

Controller responsável pelo gerenciamento de disciplinas.

**Endpoints:**

1. **`GET /disciplinas`**: Lista todas as disciplinas
   - Retorna: `disciplinas.html`
   - Model: `lista` contendo todas as disciplinas

2. **`GET /disciplina-form`**: Exibe formulário para nova disciplina
   - Retorna: `disciplina-form.html`
   - Model: 
     - `disciplina`: objeto vazio
     - `lista`: todos os professores (para seleção)

3. **`POST /disciplina-form`**: Processa criação de disciplina
   - Recebe: objeto Disciplina do formulário
   - Redireciona para: `/disciplinas`

4. **`GET /editar-disciplina?id={id}`**: Exibe formulário de edição
   - Parâmetro: `id` da disciplina
   - Retorna: `disciplina-form.html`
   - Model:
     - `disciplina`: objeto preenchido
     - `lista`: todos os professores

5. **`POST /editar-disciplina`**: Processa edição de disciplina
   - Recebe: objeto Disciplina atualizado
   - Redireciona para: `/disciplinas`

6. **`GET /excluir-disciplina?id={id}`**: Exclui disciplina
   - Parâmetro: `id` da disciplina
   - Redireciona para: `/disciplinas`

## 🖥️ Camada de Visualização (Templates Thymeleaf)

### home.html

Página inicial do sistema que exibe:
- Título: "Sistema Web MVC e SQL CRUD"
- Descrição do projeto
- Diagrama de Entidades e Relacionamentos (DER)
- Link para página de vídeos

### disciplinas.html

Exibe tabela com todas as disciplinas cadastradas.

**Colunas da tabela:**
- ID
- Nome da disciplina
- Professor responsável
- Período
- Código do Classroom
- Ações (editar e excluir)

**Funcionalidades:**
- Ícone de edição (material-icons: create)
- Ícone de exclusão (material-icons: delete)
- Links dinâmicos usando Thymeleaf

### disciplina-form.html

Formulário para criar/editar disciplinas.

**Campos:**
- Nome da disciplina (texto)
- Professor (seleção dropdown)
- Período (número)
- Código da sala Classroom (texto)

### professores.html

Exibe tabela com todos os professores cadastrados.

**Colunas da tabela:**
- ID
- Nome do professor
- Ações (editar e excluir)

### professor-form.html

Formulário para criar/editar professores.

**Campos:**
- Nome do professor (texto)

### fragments/

Contém fragmentos reutilizáveis:
- **layout.html**: Layout base com Bootstrap e Material Icons
- **fragmentos.html**: Componentes reutilizáveis

## 🗃️ Banco de Dados

### Estrutura das Tabelas

**Tabela: professor**
```sql
CREATE TABLE professor (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

**Tabela: disciplina**
```sql
CREATE TABLE disciplina (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(45) DEFAULT NULL,
  periodo INT DEFAULT NULL,
  codigo_sala_classroom VARCHAR(45) DEFAULT NULL,
  prof INT NOT NULL,
  KEY disciplina_ibfk_1 (prof),
  CONSTRAINT disciplina_ibfk_1 FOREIGN KEY (prof) REFERENCES professor (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

**Relacionamento:**
- Uma disciplina pertence a um professor (relação N:1)
- Chave estrangeira: `disciplina.prof` → `professor.id`

### Configuração do Banco

**Desenvolvimento (application.properties):**
```properties
spring.datasource.url=jdbc:mysql://localhost/crud0?serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=
```

**Produção (application-prod.properties):**
- Configurações específicas para ambiente de produção
- Utilizado no deploy via Procfile

## 📦 Instalação e Configuração

### Pré-requisitos

- Java JDK 8 ou superior
- Maven 3.x
- MySQL Server (para produção)
- IDE (Eclipse, IntelliJ IDEA, VS Code, etc.)

### Passos de Instalação

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/angoti/crud-disciplinas.git
   cd crud-disciplinas
   ```

2. **Configure o banco de dados:**
   
   Execute o script SQL para criar o banco:
   ```bash
   mysql -u root -p < schema.sql
   ```

3. **Configure as credenciais do banco:**
   
   Edite o arquivo `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost/crud0?serverTimezone=UTC
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   ```

4. **Compile o projeto:**
   ```bash
   ./mvnw clean install
   ```
   
   Ou no Windows:
   ```bash
   mvnw.cmd clean install
   ```

5. **Execute a aplicação:**
   ```bash
   ./mvnw spring-boot:run
   ```
   
   Ou no Windows:
   ```bash
   mvnw.cmd spring-boot:run
   ```

6. **Acesse a aplicação:**
   
   Abra o navegador e acesse:
   ```
   http://localhost:8080
   ```

## 🎯 Como Usar

### Gerenciar Professores

1. Acesse `/professores` para ver a lista de professores
2. Clique em "Novo Professor" para adicionar
3. Preencha o formulário e salve
4. Use os ícones de editar (✏️) ou excluir (🗑️) para modificar registros

### Gerenciar Disciplinas

1. Acesse `/disciplinas` para ver a lista de disciplinas
2. Clique em "Nova Disciplina" para adicionar
3. Preencha o formulário:
   - Nome da disciplina
   - Selecione o professor responsável
   - Informe o período
   - Informe o código do Classroom (opcional)
4. Use os ícones de editar (✏️) ou excluir (🗑️) para modificar registros

## 🚀 Deploy

### Deploy no Heroku

O projeto inclui um `Procfile` configurado para deploy no Heroku:

```
web: java -Dserver.port=$PORT -Dspring.profiles.active=prod -jar target/crud-0.0.1-SNAPSHOT.jar
```

**Passos para deploy:**

1. Crie uma aplicação no Heroku
2. Configure o banco de dados (ClearDB MySQL ou JawsDB)
3. Configure as variáveis de ambiente para conexão com o banco
4. Faça o push do código:
   ```bash
   git push heroku main
   ```

## 🏗️ Arquitetura do Sistema

O sistema segue o padrão **MVC (Model-View-Controller)**:

- **Model (Modelo)**: Classes de domínio (`Professor`, `Disciplina`)
- **View (Visualização)**: Templates Thymeleaf (arquivos HTML)
- **Controller (Controlador)**: Classes de controle (`HomeControle`, `ProfessorControle`, `DisciplinaControle`)

**Camadas adicionais:**
- **DAO (Data Access Object)**: Acesso e manipulação de dados no banco
- **Domain (Domínio)**: Entidades do negócio

**Fluxo de dados:**
```
Cliente (Browser) 
    ↓
Controller (recebe requisição HTTP)
    ↓
DAO (acessa banco de dados)
    ↓
Model (objetos de domínio)
    ↓
View (renderiza HTML com Thymeleaf)
    ↓
Cliente (recebe resposta HTML)
```

## 🛠️ Tecnologias e Padrões

### Padrões de Projeto Utilizados

1. **MVC (Model-View-Controller)**: Separação de responsabilidades
2. **DAO (Data Access Object)**: Abstração do acesso a dados
3. **Singleton**: Gerenciamento de conexão única (FabricaDeConexao)
4. **Dependency Injection**: Uso de @Autowired do Spring
5. **Template Method**: RowMapper para mapeamento de dados

### Anotações Spring Utilizadas

- `@SpringBootApplication`: Classe principal do Spring Boot
- `@Controller`: Marca classe como controller MVC
- `@Repository`: Marca classe como componente DAO
- `@Autowired`: Injeção de dependências automática
- `@GetMapping`: Mapeia requisições HTTP GET
- `@PostMapping`: Mapeia requisições HTTP POST
- `@RequestParam`: Captura parâmetros da URL

## 📝 Notas de Desenvolvimento

### H2 Database (Desenvolvimento)

O projeto está configurado para usar H2 Database em memória durante o desenvolvimento. O banco é criado automaticamente ao iniciar a aplicação.

### DevTools

O Spring Boot DevTools está habilitado, permitindo:
- Reinicialização automática da aplicação ao modificar código
- LiveReload para atualização automática do browser

### JdbcTemplate

O projeto utiliza `JdbcTemplate` do Spring para:
- Executar queries SQL de forma simplificada
- Gerenciamento automático de conexões
- Tratamento de exceções
- Mapeamento de resultados

## 🤝 Contribuindo

Para contribuir com o projeto:

1. Faça um fork do repositório
2. Crie uma branch para sua feature (`git checkout -b feature/MinhaFeature`)
3. Commit suas mudanças (`git commit -m 'Adiciona MinhaFeature'`)
4. Push para a branch (`git push origin feature/MinhaFeature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto é de código aberto e está disponível para fins educacionais.

## 👨‍💻 Autor

**Angoti**

- GitHub: [@angoti](https://github.com/angoti)

## 📧 Contato

Para dúvidas ou sugestões sobre o projeto, abra uma issue no GitHub.

---

**Desenvolvido com ☕ e Spring Boot**
