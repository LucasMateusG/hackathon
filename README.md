# Tema do HackAthon:

Criar um Portal de estágios, conectando o aluno da Faculdade UniALFA as empresas.

# Estrutura:

### Web:
    - Uma pagina dedicada aos alunos, que podem pesquisar por vagas abertas e se candidatar.
    - Uma pagina decicada as empresas para cadastrar e gerenciar vagas de estagio com facilidade.

### Aplicação em Java:
    - Deve ser um painel para supervisão e gerenciamento da instituição de ensino das vagas abertas e preenchidas.

# Descrição do ambiente em Docker:

Docker, através do compose.yml criando as variaveis de ambiente, gerando um container do apache 8.2, um container de node 24-alpine e mais dois utilizando imagens do dockerhub, um para o banco em mysql e outro para o acesso via phpmyadmin.

## Configuração do Apache

- Imagem configurada dentro do Dockerfile na pasta php-web.
- Versão 8.2
- Porta: 80
- Depende da aplicação em Node para se comunicar com o banco

# Configuração do Node

- Imagem configurada dentro do Dockerfile na pasta node-api
- Versão 24-alpine
- Porta 5000
- Se comunica diretamente como o banco e aceita receber requisições HTTP do localhost:80 -> configuração do cors
- Para ver todas as configurações e libs utilizadas no container veja o arquivo .md dentro da pasta node-api

# Configuração do DataBase

- Imagem mysql: 8.0
- Porta 3306

# Configuração do PHPmyAdmin

- Imagem phpmyadmin/phpmyadmin
- Container exclusivo para visualização e operação dentro do banco mysql

# Como utilizar
1. Clone o repositório usando:
 git clone https://github.com/MatrCastelini23/hackathon.git

2. Crie um arquivo .env, e cole as variaveis de banco de dados dentro. Siga o .env.exemple

3. Após o clone rode o comando docker compose up --build para construir os containers.
    - Veja se dentro dos containers estão operando
4. Acesse a pagina web no enderço localhost:80, o phpmyadmin para visualizar o banco no endereço localhost:8080. 

5. Rode o npm run seed:run para povoar o banco dentro do container de node.js