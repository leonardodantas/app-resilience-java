# APP-RESILIENCE-JAVA

<p>
Projeto desenvolvido com o objetivo de explorar e estudar as funcionalidades da biblioteca Resilience4j, com foco em implementar padrões de resiliência para aplicações distribuídas, como por exemplo, Circuit Breaker, Rate Limiter, Bulkhead, e Retry.
</p>


### :hammer: Pré-requisitos

- IDE.
- JDK 21.
- Docker e Docker Compose

### 🛠 Detalhes Tecnicos

- Java 21
- Arquitetura baseada em Clean Arch
- Swagger
- MongoDB
- Docker e Docker Compose
- Inserção de dados de forma automatica
- Resilience4j
- Spring Cloud


O Circuit Breaker (Disjuntor) é um padrão de design utilizado em sistemas distribuídos para aumentar a resiliência e a tolerância a falhas. Ele funciona como um mecanismo de proteção que monitora as chamadas entre serviços ou operações, interrompendo temporariamente as requisições a um serviço que está falhando ou apresentando problemas de desempenho.

Esse conceito é inspirado nos disjuntores elétricos, que cortam o fornecimento de energia para evitar sobrecarga e danos. Em sistemas de software, o objetivo é proteger os recursos e evitar que falhas em um componente afetem todo o sistema.


O Circuit Breaker pode estar em um dos seguintes três estados:

Fechado (Closed)

Todas as chamadas são permitidas.
O sistema está funcionando normalmente.
Se ocorrerem erros ou chamadas lentas, essas são monitoradas.
Aberto (Open)

Todas as chamadas são bloqueadas automaticamente.
O sistema detectou um número elevado de falhas ou chamadas lentas e "abriu o circuito" para evitar novas chamadas por um período configurado (tempo de espera).
Durante este estado, as chamadas são imediatamente respondidas com um erro ou redirecionadas para um método de fallback.
Meio Aberto (Half-Open)

Após o período de espera no estado "Aberto", o circuito entra em "Meio Aberto".
Permite um número limitado de chamadas para verificar se o sistema se recuperou.
Se as chamadas forem bem-sucedidas, o circuito volta ao estado "Fechado". Caso contrário, retorna ao estado "Aberto".

O Resilience4j é uma biblioteca leve, baseada em padrões de resiliência, projetada para ajudar a tornar aplicações mais robustas contra falhas transitórias, problemas de desempenho e outros comportamentos inesperados em sistemas distribuídos. Ele foi inspirado no projeto Netflix Hystrix e é frequentemente usado em aplicações Java modernas, especialmente com o Spring Boot.


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

    
