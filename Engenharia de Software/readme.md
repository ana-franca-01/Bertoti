**texto 1**

What precisely do we mean by software engineering? What distinguishes “software engineering” from “programming” or “computer science”? And why would Google have
a unique perspective to add to the corpus of previous software engineering literature written over the past 50 years? The terms “programming” and “software 
engineering” have been used interchangeably for quite some time in our industry, although each term has a different emphasis and different implications. University 
students tend to study computer science and get jobs writing code as “programmers.” “Software engineering,” however, sounds more serious, as if it implies the 
application of some theoretical knowledge to build something real and precise. Mechanical engineers, civil engineers, aeronautical engineers, and those in other 
engineering disciplines all practice engineering. They all work in the real world and use the application of their theoretical knowledge to create something real.
Software engineers also create “something real,” though it is less tangible than the things other engineers create. Unlike those more established engineering 
professions, current software engineering theory or practice is not nearly as rigorous. Aeronautical engineers must follow rigid guidelines and practices, because 
errors in their calculations can cause real damage; programming, on the whole, has traditionally not followed such rigorous practices. But, as software becomes 
more integrated into our lives, we must adopt and rely on more rigorous engineering methods. We hope this book helps others see a path toward more reliable 
software practices.

**comentário**
1- Software engineering vai além de apenas programar. Enquanto muitos estudantes de Ciência da Computação acabam atuando como programadores, o termo “engenharia 
de software” transmite a ideia de aplicar conhecimentos teóricos para criar algo concreto, mesmo que menos tangível que obras da engenharia civil ou mecânica.
Diferente dessas áreas, que seguem normas rigorosas para evitar erros críticos, o desenvolvimento de software historicamente não adotou práticas tão estruturadas.
No entanto, com a crescente presença da tecnologia no cotidiano, torna-se essencial aplicar métodos mais rigorosos para garantir confiabilidade. É nesse contexto
que o Google traz uma visão própria, baseada em sua experiência, para contribuir com práticas mais sólidas na área.

**texto 2**
 
We propose that “software engineering” encompasses not just the act of writing code, but all of the tools and processes an organization uses to build and maintain
that code over time. What practices can a software organization introduce that will best keep its code valuable over the long term? How can engineers make a 
codebase more sustainable and the software engineering discipline itself more rigorous? We don’t have fundamental answers to these questions, but we hope that 
Google’s collective experience over the past two decades illuminates possible paths toward finding those answers. One key insight we share in this book is that 
software engineering can be thought of as “programming integrated over time.” What practices can we introduce to our code to make it sustainable—able to react to 
necessary change—over its life cycle, from conception to introduction to maintenance to deprecation?
 
The book emphasizes three fundamental principles that we feel software organizations should keep in mind when designing, architecting, and writing their code:
 
Time and Change
How code will need to adapt over the length of its life
Scale and Growth
 
How an organization will need to adapt as it evolves
Trade-offs and Costs 
How an organization makes decisions, based on the lessons of Time and Change and Scale and Growth

**comentário**
2- Engenharia de software envolve mais do que escrever código — inclui também as ferramentas e processos usados para criar e manter esse código ao longo do tempo.
O desafio é definir práticas que mantenham o código útil e sustentável durante todo o seu ciclo de vida, desde a concepção até a descontinuação. O Google, com sua 
experiência de duas décadas, propõe que engenharia de software seja vista como “programação integrada ao longo do tempo”, destacando a importância de preparar o 
código para mudanças inevitáveis.

O livro apresenta três princípios essenciais para guiar organizações de software:

* **Tempo e Mudança** – como o código deve se adaptar ao longo de sua vida útil.
* **Escala e Crescimento** – como a organização precisa evoluir à medida que se expande.
* **Compensações e Custos** – como tomar decisões equilibradas considerando os dois princípios anteriores.

**questão 3**

tres exemplos de tradeoffs são:
* **Escabilidade X simplicidade** :
  Um código simples é mais rápido e mais fácil de ser codificado, porém ele muito provavelmente não vai funcionar em larga escala. Todavia, um código feiro próprio para uma larga escala e grande número de usuário, muito provavelmnete será mais complexo e vai demorar mais de ser produzido.
* **SQL X NoSQL** :
  O SQL é ótimo para dados com muitas relações ele é consistente e muito bem organizado, porém não se torna tão funcional quando aplicado em larga escala, em contra partida o NoSQL se faz muito melhor adaptado em operações em larga escala, mas perde na cosistência e organização de dados.
* **Otimização de desempenho X Clareza e manutenibilidade** :
  Um código otimizado pode ser mais rápido, pórem mais cpmplicado de se editar  e se torna um código complexo. Por outro lado um código claro e mais facil de entender e editar na maioria das vezes nao tem o melhor desempeenho que poderia atingir se tornando um código mmais lento

  **questão 4**
  

  
