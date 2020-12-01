The Defender
-

O planeta terra está prestes a ser invadido por extraterrestres,  esses invasores estão determinados a conquistar o nosso planeta. Depois de ter o esquadrão derrotado, cabe a uma única nave completar a honrada missão de defender o nosso lar.

Desenvolvido por:

**Amanda Silva (up201800698@fe.up.pt)**

**Carolina Guilhermino (up201800171@fe.up.pt)**
 
Projeto proposto na UC LPOO(Laboratório de programação orientado  a objetos), FEUP 2019/2020. 


## **Recursos já implementados**

  - Mover-se - A nave move-se para cima e para baixo (Eixo y).
  - Inimigos - Os inimigos já aparecem de forma apropriada (Eixo x), criando a ilusão de movimento da nave e cenário.
  - Colisão - Quando a nave se choca com algum inimigo, o jogo termina.
  - Tela Inicial - Ao rodar o programa, esiste uma tela inicial do jogo na qual deve-se pressionar ENTER para começar o jogo.
  - Tela Final - Ao colidir com um inimigo, o jogo termina mostrando uma tela com as melhores pontuações.
  - Contagem de pontos - São adicionados 5 pontos à pontuação final.


## **Recursos a implementar**

  - Misseis - A nave deveria disparar misseis para matar os inimigos



## **Design**

### MVC
####     Contexto do problema:
O jogo demanda interações  com o usuário, que interferem o funcionamento do jogo. Ao criar a primeira versão do jogo percebemos que as classes tinham diversas funções, interferindo no Single Responsibility Principle.  Além de violar o SOLID, para facilitar modificações futuras e adição de novos elementos optamos pela pattern MVC.

#### Pattern:
   - Uma vez que o jogo possui diversos elementos que interagem com usuário, o pattern MVC foi escolhido. Ao separar tarefas  entre models, views e controllers faz com que sua aplicação não fique sobrecarregada e dependente, facilitando modificações futuras.

    
#### Consequências:
Há uma maior facilidade controlar o comportamento individual de cada elemento (AbtracModel). Permitindo adicionar, e alterar de forma individual.
Apesar de haver mais ficheiros, cada classe tem funções bem definidas, colaborando com a detecção de problemas.

##### UML do MVC pattern:
<img src="/Docs/MVC.jpg" alt="MVC UML" height="400" width="800"/>
 

### State
#### Contexto do problema:
Ao longo do jogo a tela difere de forma grotesca, afim de eviar alteracoes nas classe, implementamos o pattern State.Graças ao pattern State, podemos encapsular a lógica em classes dedicadas para mudar a tela, aplicar o Princípio de Responsabilidade Única (SOLID) e o  Princípio  Open/Closed, ter um código mais limpo e mais maleavel.


#### Pattern:
   No padrão State, criamos objetos que representam vários estados e um objeto de contexto cujo comportamento varia conforme o objeto de estado muda.
  
    
#### Consequências:
A idéia principal do padrão de Estado é  permitir que o objeto altere seu comportamento sem alterar sua classe. Além disso, ao implementá-lo, o código ficou mais limpo sem muitas instruções if / else.

 ##### UML do State pattern:
  <img src="/Docs/StatePatternUML.jpg" alt="State Pattern UML" height="400" width="800"/>



## **UML**:

##### UML das classes indivualmente

 - Abstract Model: 
 <img src="/Docs/AbstractModelUML.jpg" alt=" Abstract Model UML" height="200" width="300"/>
 - Begin: <img src="/Docs/BeginUML.jpg" alt="Begin UML" height="200" width="400"/>
 - Defender Context: <img src="/Docs/DefenderContextUML.jpg" alt="Defender Context UML" height="200" width="300"/>
 - Game Controller: <img src="/Docs/GameControllerUML.jpg" alt="Game Controller UML" height="200" width="300"/>
 - Game Model: <img src="/Docs/GameModelUML.jpg" alt="Game Model UML" height="200" width="300"/>
 - Game View: <img src="/Docs/GameViewUML.jpg" alt="Game View UML" height="200" width="400"/>
 - Invaders Controller: <img src="/Docs/InvadersControllerUML.jpg" alt="Invaders Controller UML" height="200" width="300"/>
 - Invaders Model: <img src="/Docs/InvadersModelUML.jpg" alt="Invaders Model UML" height="200" width="300"/>
 - Invaders View: <img src="/Docs/InvadersViewUML.jpg" alt="Invaders View UML" height="200" width="400"/>
 - IView:<img src="/Docs/IViewUML.jpg" alt="IView UML" height="200" width="300"/>
 - Over:<img src="/Docs/OverUML.jpg" alt="Over UML" height="300" width="400"/>
 - Position: <img src="/Docs/PositionUML.jpg" alt="Position UML" height="250" width="250"/>
 - Running: <img src="/Docs/Implements.jpg" alt="Running UML " height="200" width="400"/>
 - Spaceship Controller: <img src="/Docs/SpaceshipControllerUML.jpg" alt="Spaceship Controller UML" height="200" width="300"/>
 - Spaceship Model: <img src="/Docs/SpaceshipModelUML.jpg" alt="Spaceship Model UML" height="200" width="300"/>
 - Spaceship View:<img src="/Docs/SpaceshipViewUML.jpg" alt="Spaceship View UML" height="200" width="400"/>
 - State: <img src="/Docs/StateUML.jpg" alt="State UML" height="200" width="300"/>
 - Utils: <img src="/Docs/UtilsUML.jpg" alt="Utils UML" height="200" width="300"/>
 

## **Code Smells e Refactoring Suggestions** 
#### Nao foram "Refatorados":

- #### Classe Position:
    - A cada vez que uma ação é feita (up,down..) uma nova Position é criada, isso não é eficiente. A melhor maneira é somente modificar membro dado que vai sofrer a alteração pela ação.


- #### IOException 
   - As execoes nao foram tratadas.De fato, as silenciamos usando  try/catch "vazios" criados pela IntelliJ.

#### Modificados depois da primeira entrega:

- #### Classes SpaceshipController e InvadersTroopController 
    - Ambas as classes têm como membro dado GameModel. O que é desnecessário, podendo receber somente SpaceshipModel e InvadersTroopModel, respectivamente.
    
    - Solocao. SpaceshipController e InvadersTroopController, as classes agora  possuem SpaceshipModel e InvaderModel  como membros dados, respectivamente. 
- #### Código repetido: 
    - Há código comum em Spaceship e Invader, com a adição de eventuais itens, é passível de criar uma classe mãe, element. Assim não haveria o repetição de código.
    
    - Solucao. De acabar com a repeticao de código foi escrita a classe AbstractModel, onde SpaceshipModel e InvaderModel a extendem. Os metodos aplicados foram, "getPosition", "setPosition" e o membro dado "Position position".


## **Teste:** 
<img src="/Docs/TestCoverage.png" alt="Tests Coverage" height="300" width="350"/>


## **Imagens do Jogo**:
  <img src="/Docs/INITIAL.png" alt="Initial Image" height="200" width="300"/>
  <img src="/Docs/game.jpg" alt="Game Image"  height="200" width="300">
  <img src="/Docs/game_final.jpeg" alt="Game Final Image"  height="200" width="300">

 
## **Avaliação**:

 - Amanda Silva - 50%
 - Carolina Guilhermino - 50%
