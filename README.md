<h1 align="center">🔗 Projeto MongoDB com Spring Boot e Spring Data (MongoRepository)</h1>

<h2 align="center">Curso Udemy: Programação Orientada a Objetos com Java</h2>

Este projeto faz parte do curso de Programação Orientada a Objetos com Java, ministrado na Udemy. O objetivo principal foi criar uma aplicação web utilizando Spring Boot e Spring Data para MongoDB, estruturando o sistema em camadas lógicas e implementando operações básicas de CRUD (Create, Retrieve, Update, Delete). Durante o desenvolvimento, foram exploradas as principais diferenças entre o paradigma orientado a documentos e o relacional, além da implementação de associações entre objetos, utilizando objetos aninhados e referências. Também foram realizadas consultas eficientes com Spring Data e MongoRepository, refletindo sobre as melhores decisões de design para um banco de dados orientado a documentos.

## 📌 Objetivo geral:
- Compreender as principais diferenças entre paradigma orientado a documentos e relacional 
- Implementar operações de CRUD 
- Refletir sobre decisões de design para um banco de dados orientado a documentos 
- Implementar associações entre objetos 
  - Objetos aninhados 
  - Referências
- Realizar consultas com Spring Data e MongoRepository

## Arquitetura de Conexão ao MongoDB com Padrões Repository e Service

![Arquitetura MongoDB](conectando_mongoDB_com_repository_e_service.png)

Este diagrama ilustra a arquitetura de conexão de uma aplicação ao MongoDB, utilizando os padrões de Repository e Service. A arquitetura está organizada em várias camadas que representam diferentes componentes do sistema:

1. **Aplicação Cliente**: Interface de usuário que interage com a aplicação.
2. **Controladores REST**: Pontos de entrada da aplicação que tratam as requisições HTTP e delegam para a camada de serviço.
3. **Camada de Serviço**: Contém a lógica de negócios e interage com a camada de acesso a dados.
4. **Camada de Acesso a Dados (Repository)**: Responsável por acessar e manipular os dados no MongoDB.
5. **Camada de Domínio**: Representa os modelos de dados e entidades do sistema.

O diagrama destaca a comunicação entre a aplicação cliente e os controladores REST, bem como a interação entre as diferentes camadas para gerenciar os dados de forma eficiente e organizada.

**Referências:** 

[Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html) 

[Spring Boot NoSQL Features](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-nosql.html)  

[MongoDB Default Username and Password](https://stackoverflow.com/questions/38921414/mongodb-what-are-the-default-user-and-password) 

## Modelo de Dados de Rede Social

![Modelo de Domínio de Rede Social](modelo_de_dominio.png)

A imagem mostra um diagrama de modelo de dados para uma rede social, representando as entidades `User` (Usuário), `Post` (Postagem) e `Comment` (Comentário) e suas relações. Este diagrama é útil para entender como os dados são estruturados e interconectados em um sistema de rede social.

### Entidades e Relacionamentos

1. **User (Usuário)**
   - Atributos:
     - `id`: Identificação do usuário
     - `name`: Nome do usuário
     - `email`: E-mail do usuário
   - Relacionamentos:
     - Um usuário pode ter múltiplas postagens (posts).
     - Um usuário pode ser autor de múltiplos comentários.

2. **Post (Postagem)**
   - Atributos:
     - `id`: Identificação do post
     - `date`: Data do post
     - `title`: Título do post
     - `body`: Conteúdo do post
     - `author_id`: Identificação do autor do post (relacionada à tabela **User**)
   - Relacionamentos:
     - Uma postagem pertence a um único usuário.
     - Uma postagem pode ter múltiplos comentários.

3. **Comment (Comentário)**
   - Atributos:
     - `id`: Identificação do comentário
     - `text`: Texto do comentário
     - `date`: Data do comentário
     - `post_id`: Identificação do post ao qual o comentário pertence (relacionada à tabela **Post**)
     - `author_id`: Identificação do autor do comentário (relacionada à tabela **User**)

### Exemplo de Instâncias em `Java`
```java
@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepositoty userRepositoty;

	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		userRepositoty.deleteAll();
		postRepository.deleteAll();

		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");

		userRepositoty.saveAll(Arrays.asList(maria, alex, bob));

		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Patiu viagem", "Vou viajar para ão Paulo. Abraços!",
				new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(maria));

		CommentDTO c1 = new CommentDTO("Boa viagem mano!", sdf.parse("21/03/2018"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Aproveite", sdf.parse("22/03/2018"), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", sdf.parse("23/03/2018"), new AuthorDTO(alex));

		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().addAll(Arrays.asList(c3));

		postRepository.saveAll(Arrays.asList(post1, post2));

		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepositoty.save(maria);
	}
}
```
**Resumo**

A classe `Instantiation` é responsável por inicializar o banco de dados com dados de exemplo ao iniciar a aplicação. Esta classe implementa a interface CommandLineRunner, que contém o método `run`, executado na inicialização da aplicação.

**Configuração**

`@Configuration:` Indica que a classe declara um ou mais métodos @Bean e pode ser processada pelo contêiner Spring para gerar definições de bean e solicitações de serviço.

`@Autowired:` Injeta automaticamente as dependências dos repositórios `UserRepository` e `PostRepository`.

**Método** `run`

- O método `run` apaga todos os dados existentes nos repositórios `UserRepository` e `PostRepository` usando os métodos `deleteAll`.

- Cria instâncias de `User` para Maria, Alex e Bob, e salva-as no repositório de usuários.

- Cria instâncias de `Post` para Maria, incluindo títulos e conteúdos, e atribui-as a ela como autora.

- Cria instâncias de `CommentDTO` e associa os comentários aos posts correspondentes.

- Salva os posts no repositório de posts.

- Adiciona os posts de Maria à sua lista de postagens e atualiza o repositório de usuários.

Este exemplo demonstra como inicializar e configurar dados básicos para usuários, posts e comentários, simulando um ambiente de rede social.


















