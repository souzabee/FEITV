# Relatório do Projeto

## 1. Identificação do projeto

**Nome do projeto:** Sistema de gerenciamento de filmes e playlists  
**Linguagem utilizada:** Java  
**Interface gráfica:** Java Swing  
**Banco de dados:** PostgreSQL  
**Padrão arquitetural:** MVC (Model - View - Controller)  

---

## 2. Objetivo do projeto

O objetivo deste projeto foi desenvolver uma aplicação desktop em Java para gerenciamento de filmes, permitindo que usuários pesquisem filmes, visualizem detalhes, criem playlists personalizadas, adicionem e removam filmes dessas playlists e realizem interações como curtir e descurtir conteúdos.

A aplicação foi desenvolvida com foco em organização por usuário, persistência em banco de dados e separação de responsabilidades entre interface, controle e acesso aos dados.

---

## 3. Arquitetura do projeto

O sistema foi estruturado com base no padrão **MVC (Model - View - Controller)**, de forma a organizar melhor o código e facilitar manutenção, entendimento e evolução do projeto.

### 3.1 Model
A camada **Model** representa os dados e entidades do sistema.

Exemplos:
- `Usuario`
- `Video`
- `Playlist`

Essas classes armazenam atributos e comportamentos básicos relacionados às informações manipuladas pela aplicação.

### 3.2 View
A camada **View** corresponde às telas da aplicação, desenvolvidas com Java Swing.

Exemplos:
- `TelaPrincipal`
- `Card`
- `Criarplaylist`
- `SuaPlaylist`
- `Playlist`
- `Addplaylist`

Essas classes são responsáveis pela interação com o usuário, exibição das informações e captura de eventos da interface.

### 3.3 Controller
A camada **Controller** é responsável por receber as ações da interface, aplicar regras de negócio e coordenar a comunicação entre a `view` e a persistência de dados.

Exemplos:
- `PlaylistController`
- `CurtidaController`
- `VideoController`

Os controllers centralizam a lógica do sistema, como:
- criar playlists;
- adicionar filmes em playlists;
- remover filmes de playlists;
- validar curtidas e descurtidas;
- buscar filmes no banco.

### 3.4 DAO
A camada **DAO (Data Access Object)** é responsável pela comunicação direta com o banco de dados.

Exemplos:
- `Conexao`
- `PlaylistDAO`
- `VideoDAO`
- `CurtidaDAO`

Essa camada executa consultas SQL, inserções, exclusões e buscas no banco.

---

## 4. Funcionalidades implementadas

### 4.1 Login e controle por usuário
O sistema opera com usuário autenticado/logado, permitindo que playlists e curtidas sejam armazenadas individualmente para cada usuário.

### 4.2 Tela principal
A tela principal apresenta:
- saudação personalizada;
- campo de pesquisa de filmes;
- botão para criar playlist;
- botão para visualizar playlists.

### 4.3 Busca de filmes
O usuário pode pesquisar filmes pelo nome.  
O controller recebe a solicitação da tela, consulta o DAO correspondente e retorna o resultado para exibição.

### 4.4 Exibição de detalhes do filme
Ao selecionar um filme, a aplicação abre uma tela com informações detalhadas, como:
- título;
- gênero;
- descrição;
- ano.

### 4.5 Curtir e descurtir filmes
Foi implementado um mecanismo de curtidas por usuário, com as seguintes regras:
- se o usuário tentar curtir um filme já curtido, o sistema informa que ele já curtiu;
- se tentar descurtir um filme que ainda não foi curtido, o sistema informa que a curtida não existe;
- cada curtida fica vinculada ao usuário e ao vídeo no banco de dados.

Essa lógica é tratada pelo controller de curtidas, que consulta o DAO antes de executar a ação.

### 4.6 Criação de playlists
O usuário pode criar playlists personalizadas informando um nome.  
O controller recebe a solicitação da tela e utiliza o DAO para persistir os dados no banco, associando a playlist ao usuário logado.

### 4.7 Listagem de playlists
As playlists criadas por cada usuário são recuperadas do banco e exibidas na interface.  
Essa funcionalidade permite ao usuário visualizar suas listas personalizadas.

### 4.8 Adição de filmes à playlist
Ao visualizar um filme, o usuário pode adicioná-lo a uma playlist existente.  
O controller coordena a seleção da playlist, valida o vínculo e utiliza o DAO para armazenar a relação entre playlist e vídeo.

### 4.9 Visualização dos filmes de uma playlist
Ao abrir uma playlist, os filmes associados a ela são carregados do banco e apresentados na interface.

### 4.10 Remoção de filmes da playlist
Foi implementada também a funcionalidade de remover filmes de uma playlist, permitindo ao usuário reorganizar seu conteúdo.

---

## 5. Estrutura de pacotes

O projeto foi organizado da seguinte forma:

### `model`
Contém as entidades do sistema:
- `Usuario`
- `Video`
- `Playlist`

### `view`
Contém as telas da aplicação:
- `TelaPrincipal`
- `Card`
- `Criarplaylist`
- `SuaPlaylist`
- `Playlist`
- `Addplaylist`

### `controller`
Contém a lógica intermediária da aplicação:
- controllers de playlist;
- controllers de curtida;
- controllers de vídeo.

### `dao`
Contém as classes de acesso ao banco:
- `Conexao`
- `PlaylistDAO`
- `VideoDAO`
- `CurtidaDAO`

---

## 6. Banco de dados

O sistema utiliza banco de dados relacional para armazenar os dados da aplicação.

### 6.1 Principais tabelas

#### `tbusuarios`
Armazena os dados dos usuários.

#### `tbvideos`
Armazena os filmes cadastrados.

#### `tbplaylists`
Armazena as playlists criadas pelos usuários.

#### `tbplaylist_videos`
Tabela de relacionamento entre playlists e vídeos.

#### `tbcurtidas`
Tabela que registra os vídeos curtidos por cada usuário.

---

## 7. Fluxo de funcionamento

### 7.1 Busca de filme
1. o usuário informa o nome do filme na interface;
2. a `view` envia a ação ao controller;
3. o controller consulta o DAO;
4. o resultado é retornado para a tela.

### 7.2 Criação de playlist
1. o usuário informa o nome da playlist;
2. a `view` envia os dados ao controller;
3. o controller valida e chama o DAO;
4. o banco persiste a nova playlist.

### 7.3 Adição de filme à playlist
1. o usuário escolhe um filme;
2. abre a tela de adição em playlist;
3. seleciona uma playlist existente;
4. o controller processa a ação;
5. o DAO grava a relação no banco.

### 7.4 Curtir e descurtir
1. o usuário clica em curtir ou descurtir;
2. o controller verifica no banco se a curtida já existe;
3. dependendo do caso, a ação é realizada ou uma mensagem de aviso é exibida.

### 7.5 Remoção de filme da playlist
1. o usuário acessa a playlist;
2. escolhe o filme a ser removido;
3. a ação é enviada ao controller;
4. o DAO remove o vínculo no banco;
5. a tela é atualizada.

---

## 8. Principais dificuldades encontradas


- passagem do usuário logado entre as telas;
- atualização dinâmica da interface Swing;
- carregamento de dados do banco em componentes visuais;
- manipulação de eventos de botões;
- manutenção da consistência entre dados persistidos e interface.


---

## 9. Resultados

Após finalizar o projeto, o sistema pode:

- pesquisar filmes;
- exibir informações detalhadas;
- criar playlists por usuário;
- listar playlists;
- adicionar filmes em playlists;
- visualizar os filmes de cada playlist;
- remover filmes da playlist;
- curtir e descurtir filmes com validações;
- armazenar dados no banco de forma persistente.

---

## 10. Aprendizados obtidos

O projeto permitiu consolidar conhecimentos em:

- programação orientada a objetos em Java;
- desenvolvimento de interfaces com Swing;
- arquitetura MVC;
- separação de responsabilidades em camadas;
- acesso a banco de dados com JDBC;
- modelagem relacional;
- tratamento de eventos;
- validação de regras de negócio.

---

## 11. Conclusão

O projeto foi importante para aplicar, na prática, conceitos de desenvolvimento de software com Java, banco de dados e arquitetura em camadas.

A utilização do padrão MVC contribuiu para a melhor organização do sistema, tipo:
- a interface com o usuário;
- a lógica de controle;
- o acesso aos dados.

---

## 12. Considerações finais

Acho que o projeto agregou muito aos meus conhecimentos em java apesar das dificuldades
