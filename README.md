# APP-RESILIENCE-JAVA

Este projeto foi desenvolvido com o objetivo de explorar e estudar as funcionalidades da biblioteca Resilience4j, com foco na implementação de padrões de resiliência para aplicações distribuídas. Entre os padrões estão **Circuit Breaker**, **Rate Limiter**, **Bulkhead** e **Retry**, que ajudam a garantir a robustez e a estabilidade de sistemas em cenários de falhas ou instabilidades.

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
http://localhost:8090/swagger-ui/index.html
```
### Resilience4J

O Resilience4j é uma biblioteca baseada em padrões de resiliência, projetada para ajudar a tornar aplicações mais robustas contra falhas transitórias, problemas de desempenho e outros comportamentos inesperados em sistemas distribuídos.

## Circuit Breaker

O Circuit Breaker é um padrão de design utilizado em sistemas distribuídos para aumentar a resiliência e a tolerância a falhas. Ele funciona como um mecanismo de proteção que monitora as chamadas entre serviços ou operações, interrompendo temporariamente as requisições a um serviço que está falhando ou apresentando problemas de desempenho.

Esse conceito é inspirado nos disjuntores elétricos, que cortam o fornecimento de energia para evitar sobrecarga e danos

O Circuit Breaker pode estar em um dos seguintes três estados:

#### Fechado

Todas as chamadas são permitidas.
O sistema está funcionando normalmente.
Se ocorrerem erros ou chamadas lentas, essas são monitoradas.

#### Aberto

Todas as chamadas são bloqueadas automaticamente.
O sistema detectou um número elevado de falhas ou chamadas lentas e "abriu o circuito" para evitar novas chamadas por um período configurado.
Durante este estado, as chamadas são imediatamente respondidas com um erro ou redirecionadas para um método de fallback.

#### Meio Aberto 

Após o período de espera no estado "Aberto", o circuito entra em "Meio Aberto".
Permite um número limitado de chamadas para verificar se o sistema se recuperou.
Se as chamadas forem bem-sucedidas, o circuito volta ao estado "Fechado". Caso contrário, retorna ao estado "Aberto".

### Exemplo no projeto

Nesta aplicação, demonstramos o funcionamento de um Circuit Breaker. Quando a aplicação não consegue estabelecer uma conexão com o MongoDB, ela utiliza uma API externa como alternativa, garantindo que o cliente continue tendo um funcionamento correto e estável. Além disso, foi configurado um limite de tempo para considerar uma resposta da base de dados como lenta, o que também aciona o uso da API externa como solução de contingência.

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


### Funcionamento do Circuit Breaker

Para visualizar o funcionamento do Circuit Breaker, é necessário iniciar a aplicação e os contêineres do Docker Compose utilizando os comandos descritos anteriormente.

Em seguida, basta acessar o endpoint que lista todos os filmes presentes na base de dados.

```
  GET /v1/movies?size=20&page=1
```

CURL de exemplo:

```
curl -X 'GET' \
  'http://localhost:8090/v1/movies?size={tamanho}&page={pagina}' \
  -H 'accept: */*'
```

Copie o ID de um dos filmes listados e utilize-o para buscar os detalhes do filme no endpoint correspondente.

```
  GET /v1/movies/{id do filme}/details
```

CURL de exemplo:

```
curl -X 'GET' \
  'http://localhost:8090/v1/movies/1214667/details' \
  -H 'accept: */*'
```

### Funcionamento do Circuit Breaker no projeto

#### Estado Fechado

Na primeira fase, o Circuit Breaker está fechado, permitindo todas as chamadas ao serviço. Esse comportamento pode ser visualizado diretamente no console da IDE.

#### Estado Aberto
Para simular o estado aberto do Circuit Breaker, basta parar o contêiner do Docker. Isso criará um cenário em que a fonte primária de dados (MongoDB) ficará indisponível.

Para parar o contêiner, liste os contêineres em execução com o comando:

```
docker container ps
```

Em seguida, pare o contêiner desejado com o comando:

```
docker container stop <id_do_container>
```
Enquanto o Circuit Breaker estiver aberto, a aplicação tentará acessar o MongoDB até que as configurações definidas sejam atingidas seja com limite de falhas ou lentidão.

Após atingir esses limites, o circuito será aberto, e todas as requisições seguintes serão redirecionadas automaticamente para o fallback.

#### Estado Meio Aberto

Durante o período em que o circuito está aberto, após um número configurável de requisições, ele mudará para o estado meio aberto.

Nesse estado, a aplicação tentará acessar o MongoDB novamente, mas apenas para uma quantidade limitada de requisições configuradas.

Se as requisições forem bem-sucedidas, o circuito voltará ao estado fechado. Caso contrário, ele permanecerá aberto.

## Time Limiter

O Time Limiter é um padrão utilizado em sistemas distribuídos que tem como objetivo garantir que as requisições realizadas não ultrapassem um tempo máximo de execução pré-definido em uma configuração. Com ele, conseguimos interromper uma operação automaticamente e estabelecer uma função de fallback para garantir que o usuário não será afetado e o sistema continuará funcionando dentro do esperado.

### Funcionamento Time Limiter no projeto

Nesse projeto, foi configurado um tempo máximo de resposta esperado em 1s. A configuração está disponível no arquivo **properties.yaml** do projeto, com o nome **timeoutDuration**. Ao acessar a base de dados, caso o tempo máximo configurado seja ultrapassado, a função de fallback é acionada e, com isso, os dados esperados são buscados de uma API externa.

#### Testado o comportamento
É possível alterar e testar o comportamento do Circuit Breaker iniciando e parando o contêiner do MongoDB durante a execução da aplicação. Isso permite simular diferentes cenários de falha e recuperação.

## Rate Limiter

O Rate Limiter é um mecanismo de defesa utilizado em sistemas distribuídos que tem como objetivo limitar a quantidade de requisições que um endpoint pode receber durante um determinado período de tempo. Com ele, conseguimos evitar que o sistema seja sobrecarregado ou utilizado de forma inadequada, garantindo estabilidade e segurança ao sistema.

### Funcionamento Rate Limiter no projeto

Nesse projeto, foi configurado um tempo máximo de resposta esperado em 1s. A configuração está disponível no arquivo **properties.yaml** do projeto, com o nome **timeoutDuration**. Ao acessar a base de dados, caso o tempo máximo configurado seja ultrapassado, a função de fallback é acionada e, com isso, os dados esperados são buscados de uma API externa.

#### Testado o comportamento

Nesse projeto foi utilizado as seguintes configurações de Rate Limiter:

**limitForPeriod: 10**
- Limite de requisições permitidas.

**limitRefreshPeriod: 20s**
- Após 20 segundos o limite do Rate Limiter será zerado e o contador voltará ao estado inicial.

**timeoutDuration: 2s**
- Limite máximo que o sistema esperará para que a execução seja finalizada ou um erro seja retornado.

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


    
