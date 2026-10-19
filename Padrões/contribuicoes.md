# Padrões de Projeto — Minhas Contribuições

**Aluna:** Ana França (`ana-franca-01`)
**Projeto:** JurisAI, um assistente jurídico feito em equipe
**Repositório do projeto:** https://github.com/joaosantos13/Padroes-De-Projetos

---

## 1. Sobre o projeto

O **JurisAI** é um assistente de conhecimento jurídico feito em **Java 17**, **Spring Boot 3.5** e **Spring AI 1.0.1**, com interface em **JavaFX**. Ele responde dúvidas jurídicas usando o **Llama 3** rodando local, via **Ollama**.

A gente dividiu os padrões de projeto entre os integrantes da equipe:

| Padrão | Papel no projeto |
|---|---|
| **State** | Controla o ciclo de vida do agente (**minha parte**) |
| **Strategy** | Escolhe a estratégia de planejamento (`ReactPlanner`) |
| **Command** | Ações da interface (buscar lei, gerar resposta, salvar histórico) |
| **Observer** | Avisa GUI, logs e histórico sobre eventos |
| **Composite** | Organiza os componentes visuais em hierarquia |

---

## 2. O que eu fiz

1. **Criei os estados do agente (padrão State):** as seis classes que controlam o fluxo, que os outros padrões (principalmente o Strategy) usam para fazer o agente andar.
2. **Criei o `LegalAgent`**, que é o *Context* do State.
3. **Fiz a integração com Spring AI + Ollama:** o `LegalAiService`, a configuração do modelo e o `SpringContext`.
4. **Escrevi os testes** do fluxo de estados e da integração com o Llama 3.

### Arquivos que eu criei

| Arquivo | O que faz | Padrão |
|---|---|---|
| `pattern/state/AgentState.java` | Interface do estado | State |
| `pattern/state/ReceivingQuestionState.java` | Valida a pergunta recebida | State |
| `pattern/state/AnalyzingState.java` | Analisa a pergunta | State |
| `pattern/state/SearchingKnowledgeState.java` | Busca na base de conhecimento | State |
| `pattern/state/GeneratingAnswerState.java` | Gera a resposta com a IA | State |
| `pattern/state/CompletedState.java` | Estado final de sucesso | State |
| `pattern/state/ErrorState.java` | Estado final de erro | State |
| `agent/LegalAgent.java` | Guarda o estado atual do agente | State (Context) |
| `ai/LegalAiService.java` | Conversa com o Llama 3 via Spring AI | Facade / Service |
| `ai/SpringContext.java` | Pega beans do Spring fora do contêiner | Service Locator |
| `src/test/java/LegalAgentStateTest.java` | Testes do fluxo de estados | — |
| `src/test/java/LegalAiServiceTest.java` | Teste de integração com o Llama 3 | — |
| `application.yml` (parte `spring.ai`) | Configuração do Ollama/Llama 3 | — |
| `pom.xml` (dependências Spring AI) | Dependências do Spring AI | — |

---

## 3. Padrão State: os estados do agente

### 3.1 Por que State?

Quando o agente recebe uma pergunta, ele passa por etapas bem definidas, e o que ele faz depende da etapa em que está. Sem o State, o `LegalAgent` ia precisar de um `switch` enorme em cima de um enum de status, e cada etapa nova significaria mexer nessa classe.

Com o State, cada etapa vira uma classe, e o agente só repassa o trabalho pro estado atual. Assim dá pra adicionar um estado novo sem mexer nos outros (princípio Aberto/Fechado), e cada responsabilidade fica separada.

### 3.2 Estrutura

```
            ┌──────────────────────┐
            │      LegalAgent      │  ← Context
            │  - state: AgentState │
            │  + process()         │──── state.handle(this)
            │  + setState(...)     │
            └──────────┬───────────┘
                       │ usa
                       ▼
            ┌──────────────────────┐
            │  «interface»         │  ← State
            │  AgentState          │
            │  + handle(agent)     │
            └──────────┬───────────┘
        ┌──────────┬───┴──────┬───────────┬───────────┬──────────┐
        ▼          ▼          ▼           ▼           ▼          ▼
  Receiving   Analyzing   Searching   Generating  Completed   Error
  Question    State       Knowledge   Answer      State       State
  State                   State       State          ← ConcreteStates
```

### 3.3 Transições entre os estados

```
 ┌───────────────────┐  pergunta vazia/nula   ┌────────────┐
 │ ReceivingQuestion │ ─────────────────────▶ │ ErrorState │
 └─────────┬─────────┘                        └────────────┘
           │ pergunta válida
           ▼
 ┌───────────────────┐   decide "BUSCAR"    ┌──────────────────────┐
 │  AnalyzingState   │ ───────────────────▶ │ SearchingKnowledge   │
 │ (usa ReactPlanner)│                      └──────────┬───────────┘
 └─────────┬─────────┘                                 │
           │ decide "DIRETO" (ou falha)                │
           ▼                                           ▼
 ┌───────────────────┐ ◀─────────────────────────────────
 │ GeneratingAnswer  │
 └─────────┬─────────┘
           │ resposta gerada
           ▼
 ┌───────────────────┐
 │  CompletedState   │
 └───────────────────┘
```

### 3.4 A interface `AgentState`

É o contrato que todos os estados seguem. Cada estado recebe o agente pra poder ler os dados dele e decidir qual é o próximo estado.

```java
public interface AgentState {
    void handle(LegalAgent agent);
}
```

### 3.5 O contexto `LegalAgent`

É a classe que **guarda o estado atual** e **repassa** o trabalho pra ele. O `process()` não tem regra de negócio nenhuma, só chama `state.handle(this)`. Quem decide a transição é o próprio estado, usando `setState(...)`.

```java
public class LegalAgent {

    private AgentState state;
    private String question;
    private String context;
    private String answer;
    private LegalAiService legalAiService;

    public LegalAgent(AgentState state) {
        this.state = state;
        try {
            this.legalAiService = SpringContext.getBean(LegalAiService.class);
        } catch (Exception e) {
            System.out.println("Aviso: Contexto do Spring ainda não inicializado.");
        }
    }

    public void process() {
        state.handle(this);   // repassa pro estado atual
    }

    public void setState(AgentState state) {
        this.state = state;   // os estados usam isso pra fazer a transição
    }
    // getters e setters de question, context, answer...
}
```

> No construtor, o agente tenta pegar o `LegalAiService` do Spring. Se o contexto ainda não estiver de pé (como nos testes unitários do fluxo de estados), ele é criado mesmo assim, só que sem a IA. Isso deixa a gente testar o State isolado.

### 3.6 Os estados concretos

#### `ReceivingQuestionState`: valida a entrada
É o estado inicial. Se a pergunta for nula ou vazia, o agente vai pro `ErrorState`. Se estiver ok, segue pro `AnalyzingState`.

```java
public class ReceivingQuestionState implements AgentState {
    @Override
    public void handle(LegalAgent agent) {
        if (agent.getQuestion() == null || agent.getQuestion().isBlank()) {
            agent.setState(new ErrorState());
        } else {
            agent.setState(new AnalyzingState());
        }
    }
}
```

#### `AnalyzingState`: analisa a pergunta
Na minha versão original, esse estado só mandava o agente pra busca:

```java
// Versão original (commit 6f25488)
public class AnalyzingState implements AgentState {
    @Override
    public void handle(LegalAgent agent) {
        agent.setState(new SearchingKnowledgeState());
    }
}
```

Depois, quando o pessoal integrou o **Strategy**, o estado passou a **delegar a decisão** pra um `PlannerStrategy` (o `ReactPlanner`). É aqui que State e Strategy trabalham juntos: **o Strategy decide e o State faz a transição**.

```java
// Versão atual (depois da integração do Strategy por outro colega)
public class AnalyzingState implements AgentState {
    @Override
    public void handle(LegalAgent agent) {
        PlannerStrategy strategy = new ReactPlanner();
        strategy.executePlan(agent);   // o planejador define o próximo estado
    }
}
```

#### `SearchingKnowledgeState`: busca de conhecimento
Representa a etapa de consultar a base de conhecimento (Constituição, CDC, LGPD). Por enquanto só passa pra geração da resposta, mas já deixa o ponto de extensão pronto pra busca de verdade.

```java
public class SearchingKnowledgeState implements AgentState {
    @Override
    public void handle(LegalAgent agent) {
        agent.setState(new GeneratingAnswerState());
    }
}
```

#### `GeneratingAnswerState`: gera a resposta
Chama o `LegalAiService` (Spring AI) com a pergunta, guarda a resposta no agente e segue pro `CompletedState`. É aqui que o State **se conecta com o Spring AI**.

```java
public class GeneratingAnswerState implements AgentState {
    @Override
    public void handle(LegalAgent agent) {
        if (agent.getLegalAiService() != null) {
            String resposta = agent.getLegalAiService()
                    .generateAnswer(agent.getQuestion());
            agent.setAnswer(resposta);
        }
        agent.setState(new CompletedState());
    }
}
```

#### `CompletedState` e `ErrorState`: estados finais
Marcam o fim do fluxo, com sucesso ou com erro.

```java
public class CompletedState implements AgentState {
    @Override
    public void handle(LegalAgent agent) {
        System.out.println("Processamento concluído.");
    }
}

public class ErrorState implements AgentState {
    @Override
    public void handle(LegalAgent agent) {
        System.out.println("Erro no processamento da pergunta.");
    }
}
```

### 3.7 Como os outros padrões usam os estados

Os estados são o centro do controle do agente, e os outros padrões se apoiam neles:

- **Strategy → State:** o `ReactPlanner` recebe o `LegalAgent` e escolhe o próximo estado com `agent.setState(...)`. A estratégia decide o caminho, e a máquina de estados executa.

  ```java
  // ReactPlanner (de um colega) usando os meus estados
  if (decisaoIa.contains("buscar")) {
      agent.setState(new SearchingKnowledgeState());
  } else {
      agent.setState(new GeneratingAnswerState());
  }
  ```

- **Command → State:** os comandos da interface (como o `GenerateAnswerCommand`) disparam o processamento do agente, que aí percorre os estados.
- **Observer ← State:** o `AgentObserver` tem o método `onStateChanged(String stateName)`, pensado pra avisar GUI, logs e histórico a cada mudança de estado.

---

## 4. Integração com Spring AI

### 4.1 Dependências (`pom.xml`)

Usei o **BOM do Spring AI** pra gerenciar as versões e o starter do **Ollama**:

```xml
<properties>
    <java.version>17</java.version>
    <spring-ai.version>1.0.1</spring-ai.version>
</properties>

<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework.ai</groupId>
            <artifactId>spring-ai-bom</artifactId>
            <version>${spring-ai.version}</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>

<dependency>
    <groupId>org.springframework.ai</groupId>
    <artifactId>spring-ai-starter-model-ollama</artifactId>
</dependency>
```

### 4.2 Configuração (`application.yml`)

Aponta o Spring AI pro Ollama local e define o **Llama 3** como modelo. O `web-application-type: none` evita subir servidor web, já que a aplicação é desktop (JavaFX).

```yaml
spring:
  application:
    name: juris-ai

  main:
    web-application-type: none

  ai:
    ollama:
      base-url: http://localhost:11434
      chat:
        options:
          model: llama3:latest
```

### 4.3 O serviço `LegalAiService`

Concentra toda a conversa com o LLM. O resto do sistema (estados, estratégias) não precisa saber nada do Spring AI, só chama `generateAnswer(...)`. O `ChatClient.Builder` é injetado pelo Spring (auto-configuração do starter do Ollama).

O **prompt de sistema** define como o assistente se comporta: respostas claras, uso só do contexto fornecido, **sem inventar leis, artigos ou decisões** (pra reduzir alucinação) e aviso de que a resposta é só informativa.

```java
@Service
public class LegalAiService {

    private final ChatClient chatClient;

    public LegalAiService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public String generateAnswer(String question) {
        return chatClient
                .prompt()
                .system("""
                        Você é o JurisAI, um assistente de conhecimento jurídico.

                        Sua função é fornecer respostas claras e objetivas
                        sobre questões jurídicas.

                        Utilize apenas as informações fornecidas no contexto
                        quando um contexto estiver disponível.

                        Não invente leis, artigos, decisões judiciais ou fontes.

                        Suas respostas possuem finalidade exclusivamente
                        informativa e não substituem orientação jurídica
                        profissional.
                        """)
                .user(question)
                .call()
                .content();
    }
}
```

### 4.4 A ponte `SpringContext`

O `LegalAgent` é criado com `new` (não é bean do Spring), então não dá pra injetar o `LegalAiService` nele. Pra resolver isso, criei o `SpringContext`, que guarda o `ApplicationContext` de forma estática e deixa buscar beans de qualquer lugar.

```java
@Component
public class SpringContext implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static <T> T getBean(Class<T> beanClass) {
        return context.getBean(beanClass);
    }
}
```

Como o agente usa:

```java
this.legalAiService = SpringContext.getBean(LegalAiService.class);
```

---

## 5. Padrões de projeto usados nas minhas contribuições

| Padrão | Onde aparece | Como foi aplicado |
|---|---|---|
| **State** | `AgentState` + 6 estados + `LegalAgent` | Cada etapa do agente é uma classe. O agente repassa o `process()` pro estado atual, e cada estado decide a próxima transição. |
| **Strategy** *(colaboração)* | `AnalyzingState` → `PlannerStrategy` | O estado de análise deixa a escolha do próximo passo com a estratégia, e a máquina de estados fica independente da lógica de planejamento. |
| **Facade / Service** | `LegalAiService` | Esconde o `ChatClient` do Spring AI atrás de um único método, o `generateAnswer`. |
| **Service Locator** | `SpringContext` | Deixa objetos criados fora do Spring (como o `LegalAgent`) pegarem beans do contêiner. |
| **Dependency Injection** | `LegalAiService(ChatClient.Builder)` | O Spring injeta o builder do cliente de chat pelo construtor. |

> **Sobre o `SpringContext`:** o Service Locator é um padrão meio polêmico, porque esconde as dependências da classe. Usei porque era o jeito mais direto de ligar o agente (criado na mão) ao Spring. Uma alternativa mais limpa seria criar o `LegalAgent` por uma *factory* gerenciada pelo Spring e injetar o serviço direto.

---

## 6. Testes

### 6.1 Testes do fluxo de estados (`LegalAgentStateTest`)

São testes unitários que checam as transições da máquina de estados, **sem precisar de Spring nem de Ollama**.

```java
@Test
void deveIrParaAnalyzingQuandoPerguntaForValida() {
    LegalAgent agent = new LegalAgent(new ReceivingQuestionState());
    agent.setQuestion("Quais são meus direitos como consumidor?");

    agent.process();

    assertInstanceOf(AnalyzingState.class, agent.getState());
}

@Test
void deveIrParaErrorQuandoPerguntaForVazia() {
    LegalAgent agent = new LegalAgent(new ReceivingQuestionState());
    agent.setQuestion("");

    agent.process();

    assertInstanceOf(ErrorState.class, agent.getState());
}
```

O teste de fluxo completo percorre a sequência
`Receiving → Analyzing → SearchingKnowledge → GeneratingAnswer → Completed`:

```java
@Test
void devePercorrerFluxoCompletoDosEstados() {
    LegalAgent agent = new LegalAgent(new ReceivingQuestionState());
    agent.setQuestion("Quais são meus direitos como consumidor?");

    agent.process();
    assertInstanceOf(AnalyzingState.class, agent.getState());

    agent.process();
    assertInstanceOf(SearchingKnowledgeState.class, agent.getState());

    agent.process();
    assertInstanceOf(GeneratingAnswerState.class, agent.getState());

    agent.process();
    assertInstanceOf(CompletedState.class, agent.getState());
}
```

### 6.2 Teste de integração com o Llama 3 (`LegalAiServiceTest`)

Sobe o contexto completo do Spring e faz uma pergunta de verdade pro modelo. **Precisa do Ollama rodando local.**

```java
@SpringBootTest(classes = JurisAiApplication.class)
public class LegalAiServiceTest {

    @Autowired
    private LegalAiService legalAiService;

    @Test
    void deveGerarRespostaComLlama() {
        String pergunta = "Quais são meus direitos básicos como consumidor?";
        String resposta = legalAiService.generateAnswer(pergunta);

        System.out.println("PERGUNTA: " + pergunta);
        System.out.println("RESPOSTA DO LLAMA 3: " + resposta);
    }
}
```

---

## 7. Como rodar

1. Instale o [Ollama](https://ollama.com) e baixe o modelo:
   ```bash
   ollama run llama3
   ```
2. Clone o repositório e rode a aplicação:
   ```bash
   mvn spring-boot:run
   ```
3. Pra rodar só os testes do fluxo de estados (não precisam do Ollama):
   ```bash
   mvn test -Dtest=LegalAgentStateTest
   ```

---

## 8. O que eu aprendi

- Como o **State** acaba com condicionais gigantes e deixa o fluxo do agente fácil de evoluir: o `AnalyzingState` mudou de comportamento e nenhum outro estado precisou ser alterado.
- Como **combinar padrões**: o Strategy decide *o que fazer* e o State controla *em que etapa a gente está*.
- Como integrar uma aplicação com um **LLM local** usando **Spring AI** e o `ChatClient`, deixando a comunicação isolada em um único serviço.
- Como é importante **isolar o que dá pra testar**: o fluxo de estados foi testado sem precisar do modelo de IA.
