Escalonador de Processos

Visão Geral

Este projeto implementa um Escalonador de Processos em Java, projetado para gerenciar processos com diferentes prioridades (Alta, Média, Baixa) e lidar com a execução, bloqueio e gerenciamento de recursos dos processos. O escalonador utiliza uma estrutura de dados de fila circular (FilaCircularDeProcessos) para gerenciar os processos e suporta operações como adicionar, remover, atualizar e executar processos em ciclos. O sistema também inclui persistência baseada em arquivo para salvar e carregar dados de processos.

Funcionalidades

Escalonamento por Prioridade: Os processos são organizados em três filas de prioridade (Alta, Média, Baixa) e uma fila de processos bloqueados.

Gerenciamento de Processos: Adicionar, remover, atualizar e visualizar processos com atributos como ID, nome, prioridade, ciclos necessários e recursos.

Execução de Ciclos: Executar um ciclo único, múltiplos ciclos ou todos os ciclos até a conclusão.

Persistência: Salvar e carregar dados de processos em um arquivo (Processo.txt).


Interface CLI Interativa: Uma interface de linha de comando para gerenciar processos e executar operações.


Estrutura do Projeto


O projeto está organizado nos seguintes arquivos principais nos pacotes Dominio e Teste:

Dominio/Node.java: Define um nó para a fila circular, contendo um objeto Processo e uma referência ao próximo nó.

Dominio/Processo.java: Representa um processo com atributos como ID, nome, prioridade, ciclos necessários e recurso necessário.

Dominio/FilaCircularDeProcessos.java: Implementa uma fila circular para gerenciar processos, com métodos para adicionar, remover e buscar processos.

Dominio/Scheduler.java: A classe principal do escalonador que gerencia as filas de prioridade, executa ciclos e lida com o bloqueio/desbloqueio de processos.

Dominio/RepositorioProcessos.java: Gerencia a entrada/saída de arquivos para salvar e carregar dados de processos em Processo.txt.

Teste/Main.java: Fornece uma interface de linha de comando para interagir com o escalonador.



Pré-requisitos

Java: JDK 8 ou superior.
Um IDE Java (por exemplo, IntelliJ IDEA, Eclipse) ou um ambiente de linha de comando com javac e java instalados.

Instalação

Clone o repositório:git clone https://github.com/seuusuario/escalonador-de-processos.git


Navegue até o diretório do projeto:cd escalonador-de-processos


Compile os arquivos Java:javac Dominio/*.java Teste/*.java


Execute o programa:java Teste.Main



Uso
O programa oferece uma interface de linha de comando com as seguintes opções:

Adicionar um novo processo: Insira ID, nome, prioridade (1=Alta, 2=Média, 3=Baixa), ciclos e recurso (ou "null").

Remover um processo: Remova um processo pelo seu ID.

Atualizar um processo: Atualize atributos (nome, prioridade, ciclos, recurso) de um processo existente pelo ID.

Ver um processo: Exiba detalhes de um processo pelo seu ID.

Ver listas: Visualize processos nas filas de Alta, Média, Baixa, Bloqueados ou todas as filas.

Ver próximo processo: Mostre o próximo processo a ser executado.

Executar um ciclo: Execute um único ciclo de escalonamento.

Executar N ciclos: Execute um número especificado de ciclos.

Executar todos os ciclos: Execute ciclos até que todos os processos sejam concluídos.

Resetar escalonador: Limpe todas as filas e reinicie o escalonador.

Sair: Salve o estado atual e saia.

Exemplo

===|=========================================|===

===|         ESCALONADOR DE PROCESSOS        |===

===| Digite a opção abaixo que você deseja:  |===

===|      Adicionar um novo processo: 1      |===

===|         Remover um processo: 2          |===

...

Para adicionar um processo:

Selecione a opção 1.

Insira os detalhes (por exemplo, ID: 1, Nome: Processo1, Prioridade: 1, Ciclos: 5, Recurso: null).

O processo é adicionado à fila apropriada e salvo em Processo.txt.

Persistência de Arquivo

Processo.txt: Armazena dados dos processos no formato id;nome;prioridade;ciclos_necessarios;recurso_necessario.

A classe RepositorioProcessos gerencia a leitura e escrita de processos neste arquivo.

Como Funciona

Filas de Processos: Os processos são armazenados em filas circulares com base na prioridade (Alta, Média, Baixa) ou status de bloqueio.

Lógica de Escalonamento: O escalonador prioriza processos de alta prioridade, mas alterna para processos de média ou baixa prioridade após 5 ciclos de execução de alta prioridade para garantir justiça.

Bloqueio: Processos que requerem o recurso "DISCO" são movidos para a fila de bloqueados e desbloqueados em ciclos subsequentes.

Execução de Ciclos: Cada ciclo seleciona um processo, executa-o (decrementando seus ciclos necessários) e o conclui ou o recoloca na fila.

Contribuição

Faça um fork do repositório.

Crie uma nova branch (git checkout -b feature/sua-funcionalidade).

Realize suas alterações e faça o commit (git commit -m "Adiciona sua funcionalidade").

Envie para a branch (git push origin feature/sua-funcionalidade).

Abra um Pull Request.

Licença

Este projeto está licenciado sob a Licença MIT. Veja o arquivo LICENSE para mais detalhes.