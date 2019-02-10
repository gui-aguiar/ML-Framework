# ML-Framework
The source code and documentations of the framework created during my master degree in its latest version


Framework Hot Spots

*Pacote FormsManagement

MainForm - O construtor da classe MainForm é um método template. Ele realiza a chamada para vários métodos hook que podem ter seus parâmetros modificados de acordo com cada aplicação. São eles:

  configureCloseAction – Método que define ações a serem realizadas quando o formulário principal for fechado. Pode ser modificado para adicionar ações que vão além do simples encerramento da aplicação, como por exemplo a escrita de um arquivo de log.

  defineDataInfo – Esse método por sua vez faz a chamada de outros dois métodos que devem ser parametrizados. Os métodos setNumberOfQuestionForms e setNumberOfQuestions. O primeiro deve ser invocado com o número de formulários em que as features do problema foram divididas. O formulário inicial com as instruções de uso não deve ser contabilizado para a parametrização desse método. Já o segundo, refere-se ao número total de features do problema. Portanto se um determinado problema possui 60 features, e os formulários foram desenhados com 06 questões cada, os métodos deverão ser invocados com os parâmetros 10 e 60 respectivamente.

  setInstrucionsPanelComponents – Adiciona e configura componentes visuais do painel que irpa mostrar as intruções para o usuário da aplicação.

  setQuestionPanelsComponents – Cria o painel que conterá todos os formulários com as questões da aplicação. O método tambem faz invoca addQuestionPanels, que tem a implementação vazia por padrão. addQuestionPanels deve ser preenchido com as criações de todos os formulários de questões da aplicação. Cada um desses formularios deve ser adicionado ao componente questionPanelsPane.

  checkNumberOfPages – não deve ser modificado. Faz a averiguação se o método addQuestionPanels foi configurado com o mesmo número de formulários que foram preenchidos no método setNumberOfQuestionForms.

  setFormInfo – faz a chamada para outros dois métodos. setTitle e setInstructions. O primeiro recebe como parâmetro o título dado a aplicação, que será exibido no formulário de instruções. O segundo,  a string com todas as instruções fornecidas ao usuário da aplicação.

  runApplication – não deve ser modificado.

QuestionPanel - Classe abstrata que representa um formulário com questões a serem preenchidas pelo usuário. Já contém um mecanismo de navegação entre as instâncias de seu tipo, através de botões, e que também invoca a finalização do questionário quando se encontra na última página. Existem duas variáveis globais que devem ser preenchidas após a criação das instâncias das subclasses de QuestionPanel para auxiliar no controle dos botões. IsFirstPanel e IsLastPanel devem ter o valor verdadeiro atribuído a elas caso a instância esteja representando, respectivamente, a primeira ou a última páginas de quetões.
	Deve então ser estendida para ser livremente desenhada com componentes adequados a aplicação. Os métodos que devem ser sobrescritos são dois: checkAnswerRules e collectData. “checkAnswerRules” deve implementar a verificação das regras de preenchimento do formulário, como por exemplo, se todos os campos estão preenchidos. “collectData” deve levar os dados dos campos preenchidos pelo usuário até a variável global “data”. Para auxiliar nessa tarefa, a variável global “index” deve ser utilizada. Tal variável dese ver preenchida com o valor do índice do formulário com relação a todos os formulários de questões criados. 

*Pacote MachineLearning

Application – Classe que serve como fachada entre os dois pacotes e propaga as configurações dos formulários para as classes de machine learning. O método “createOperationMode” deve ser implementado com a criação de uma instância de alguma subclasse de OperationMode.

Classifier – Representa o objeto responsável pela comunicação e utilização do algoritmo. Deve ter seu método createDataDAO preenchido com o código da criação de uma instância da subclasse de DataDAO e também implementar um método de normalização dos dados da aplicação, caso necessário, no método normalizeData;


DataDAO – Classe abstrata responsável por acessar os dados de treinamento da aplicação. Possui dois métodos que podem ser implementados pelos usuários opcionalmente. São os métodos addLog e delLog. Esses métodos podem ser utilizados para adicionar ou remover entradas nos dados originais da aplicação. 
	Para estender a classe, deve-se sobrescrever o método abstrato load. Esse método deve fazer o carregamento dos dados para os campos features e labels. Por exemplo, o método load pode implementar um algoritmo que carregada dados de um arquivo csv para tais estruturas

Learn – Classe abstrata que deve ser especializada para sobrescrever o método addAlgorithms. Tal método é responsável por criar as instâncias dos algoritmos utilizados durante a aplicação, e adicioná-los na lista de algoritmos da classe Classifier.

PerformaceData  - Classe abstrata, que deve ser especializada para conter os dados de performance do algoritmo. Por não estar relacionada diretamente com nenhuma plataforma ou algoritmo a classe é totalmente vazia, permitindo ao usuário adaptá-la as medidas de desempenho adequadas a cada caso.

