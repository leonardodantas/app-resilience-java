# APP-RESILIENCE-JAVA

<p>
Projeto desenvolvido com o objetivo de explorar e estudar as funcionalidades da biblioteca Resilience4j, com foco em implementar padrões de resiliência para aplicações distribuídas, como por exemplo, Circuit Breaker, Rate Limiter, Bulkhead, e Retry.
</p>


### Pré-requisitos

- IDE.
- JDK 21.
- Docker e Docker Compose

### Detalhes Tecnicos

- Java 21
- Arquitetura baseada em Clean Arch
- Swagger
- MongoDB
- Docker e Docker Compose
- Inserção de dados de forma automatica
- Resilience4j
- Spring Cloud

###  Iniciando projeto pela primeira vez

```bash
# Clone este repositório
git clone https://github.com/leonardodantas/app-resilience-java.git

# Tenha o docker compose instalando, acesse a pasta raiz do projeto e execute o seguinte comando
docker-compose up --build

# O comando acima ira criar instâncias das seguintes aplicações
- Mongo
- Mongo Express
 
# Inicie a aplicação com uma IDE

#Acesse o seguinte endereço no navegador
http://localhost:8089/swagger-ui/index.html
```
### Resilience4J

O Resilience4j é uma biblioteca baseada em padrões de resiliência, projetada para ajudar a tornar aplicações mais robustas contra falhas transitórias, problemas de desempenho e outros comportamentos inesperados em sistemas distribuídos.

### Circuit Breaker

O Circuit Breaker (Disjuntor) é um padrão de design utilizado em sistemas distribuídos para aumentar a resiliência e a tolerância a falhas. Ele funciona como um mecanismo de proteção que monitora as chamadas entre serviços ou operações, interrompendo temporariamente as requisições a um serviço que está falhando ou apresentando problemas de desempenho.

Esse conceito é inspirado nos disjuntores elétricos, que cortam o fornecimento de energia para evitar sobrecarga e danos. Em sistemas de software, o objetivo é proteger os recursos e evitar que falhas em um componente afetem todo o sistema.


O Circuit Breaker pode estar em um dos seguintes três estados:

#### Fechado (Closed)

Todas as chamadas são permitidas.
O sistema está funcionando normalmente.
Se ocorrerem erros ou chamadas lentas, essas são monitoradas.

#### Aberto (Open)

Todas as chamadas são bloqueadas automaticamente.
O sistema detectou um número elevado de falhas ou chamadas lentas e "abriu o circuito" para evitar novas chamadas por um período configurado (tempo de espera).
Durante este estado, as chamadas são imediatamente respondidas com um erro ou redirecionadas para um método de fallback.

#### Meio Aberto (Half-Open)

Após o período de espera no estado "Aberto", o circuito entra em "Meio Aberto".
Permite um número limitado de chamadas para verificar se o sistema se recuperou.
Se as chamadas forem bem-sucedidas, o circuito volta ao estado "Fechado". Caso contrário, retorna ao estado "Aberto".


Nesta aplicação, demonstramos o funcionamento de um Circuit Breaker. Quando a aplicação não consegue estabelecer uma conexão com o MongoDB, ela utiliza uma API externa como alternativa, garantindo que o cliente continue usufruindo de um funcionamento correto e estável. Além disso, foi configurado um limite de tempo para considerar uma resposta da base de dados como lenta, o que também aciona o uso da API externa como solução de contingência.

Utilizamos as seguintes configurações para o circuit breaker:


**slidingWindowSize: 10**
- Define o tamanho da janela deslizante usada para calcular métricas (sucessos e falhas). Aqui, a janela considera as últimas 10 chamadas.

**failureRateThreshold: 50**
- Percentual de falhas permitido antes de abrir o circuito. Se 50% ou mais das chamadas falharem, o circuito será aberto.

**waitDurationInOpenState: 30s**
- Tempo que o circuito permanece aberto antes de passar para o estado "Half-Open" para testar a recuperação.

**permittedNumberOfCallsInHalfOpenState: 3**
- Número de chamadas permitidas no estado "Half-Open" para verificar se o sistema se recuperou.

**minimumNumberOfCalls: 5**
- Número mínimo de chamadas necessárias antes de calcular o percentual de falhas ou lentidão.

**slowCallDurationThreshold: 2s**
- Tempo máximo para considerar uma chamada lenta. Qualquer chamada que exceda 2 segundos será tratada como lenta.

**slowCallRateThreshold: 10**
- Percentual de chamadas lentas permitido. Se mais de 10% das chamadas forem lentas, o circuito será ativado.


## Funcionamento do Circuit Breaker

- Para ver o funcionamento do circuit breaker, primeiro é necessario subir a aplicação e o docker compose com os comandos descritos acima.

- Logo depois acessar o endpoint que lista todos os filmes que estão na base de dados:

```
  GET /v1/movies?size=20&page=1
```

CURL de exemplo:

```
curl -X 'GET' \
  'http://localhost:8090/v1/movies?size={tamanho}&page={pagina}' \
  -H 'accept: */*'
```

- Copiar o id de algum filme e buscar o detalhe do filme utilizado o id no seguinte endpoint 


```
  GET /v1/movies/{id do filme}/details
```

CURL de exemplo:

```
curl -X 'GET' \
  'http://localhost:8090/v1/movies/1214667/details' \
  -H 'accept: */*'
```

- A primeira fase do circuit breaker é ele fechado, todas as chamadas sao permitidas e podemos visualizar esse comportamento via console da propria ide

- Para visualizarmos o circuit breaker aberto, basta apenas para o container do docker, pois nesse caso teremos o cenario onde a nossa fonte primaria de dados ficará indisponivel, para isso basta apenas listarmos os containers com o comando ***docker container ps*** e logo em seguinte para o container com o comando ***docker container stop <id container>***

- Até que os requisitos de configurações sejam atigindos, a aplicação tentara acessar o mongo db, porem após atingimento das configurações, o circuito sera aberto e apos isso todas as requisições serão redirecionadas para o fallback automaticamente

- Durante o periodo que o circuito estiver aberto, apos um numero configuravel de requisições, o circuito mudara para o estado meio aberto, onde a api tentara acessar novamente o mongo db de acordo com uma quantidade de requisições configuraveis

- Caso não tenha sucesso o circuito continuara fechado.

- Podemos alterar e testar o comportamento do circuito inicializando e parando o container durante toda a execução.

## Tecnologias

<div style="display: inline_block">
  <img align="center" alt="java" src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white" />
  <img align="center" alt="spring" src="https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white" />
  <img align="center" alt="swagger" src="https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white" />
  <img align="center" alt="docker" src="https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white" />
  <img align="center" alt="mongodb" src="https://img.shields.io/badge/MongoDB-%234ea94b.svg?style=for-the-badge&logo=mongodb&logoColor=white" />
</div>

### :sunglasses: Autor

Criado por Leonardo Rodrigues Dantas.

[![Linkedin Badge](https://img.shields.io/badge/-Leonardo-blue?style=flat-square&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/leonardo-rodrigues-dantas/)](https://www.linkedin.com/in/leonardo-rodrigues-dantas/)
[![Gmail Badge](https://img.shields.io/badge/-leonardordnt1317@gmail.com-c14438?style=flat-square&logo=Gmail&logoColor=white&link=mailto:leonardordnt1317@gmail.com)](mailto:leonardordnt1317@gmail.com)

## Licença

Este projeto esta sobe a licença MIT.


    
