# Tabela-Verdade

Projeto I da cadeira de Lógica para Computação (IF673)

## Projeto I - Gerador de Tabela-Verdade

O Projeto I é um Gerador de Tabela-Verdade a partir de uma Expressão bem-formada. Para tal, haverá a
necessidade que seu programa gere automaticamente a tabela-verdade, com elementos atômicos e
subexpressões distintas entre si que existam na expressão bem-formada. É garantido que as expressões serão
bem-formadas.

O gerador poderá ser feito nas linguagens Python, C, C++ ou Java.

O gerador deve ser entregue compilando, portanto, antes de enviar teste-o nas máquinas do CIn.

O Gerador de Tabela-Verdade deve ser entregue em uma única classe com o nome "Gerador.xx" (onde xx
corresponde à linguagem que você usará) por e-mail para o seu monitor, com o assunto:
[Tabela] - login (onde login corresponde ao login do aluno no CIn).

Este exercício é individual. Qualquer tentativa de fraude ou cópia será punida com uma nota 0 (ZERO) para
ambos os infratores.



## Detalhes

■ Quanto aos operadores:


Operador Lógico | Caracter Utilizado
--- | ---
OU | +
E | .
IMPLICAÇÃO | >
NEGAÇÃO | -

Só serão utilizados os operadores acima.

■ Quanto às variáveis:

Só serão utilizadas 4 (quatro) variáveis e, na saída, elas deverão aparecer estritamente nesta ordem:

```
x y z t
```
■ Quanto aos parênteses:


Operador Unário | Operador Binário
--- | ---
(-x) | (x.y)
(-(x+y)) | (x>("subexpressão"))
((-x)+(-y)) | ("sub1"+"sub2")

■ Quanto à Entrada/Saída

Nome do arquivo: "Gerador.xx"

Entrada: Nome do arquivo "Expressoes.in"

Contem várias expressões que serão avaliadas.
A 1ª linha da entrada será um número n, indicando quantas expressões precisarão ter suas entradas
avaliadas. Logo a seguir teremos n linhas, onde cada uma desta possuirá uma expressão, sem espaçamento,
que será a expressão a ser avaliada, conforme as regras explicitadas anteriormente. É garantido que todas as
expressões são bem-formadas e, consequentemente, possuem resposta e que cada expressão possuirá menos
que 200 caracteres. O aluno que não seguir as especificações dadas perderá 0,5 durante a correção, portanto
prestem atenção aos nomes dos arquivos!!!

Saída: Nome do arquivo "Expressoes.out"

Deve possuir TODAS as saídas do gerador em um único arquivo, separadas por uma linha em branco.
para cada caso de teste imprima uma linha só com "Tabela #x", onde x indica o número de caso de teste,
iniciando de '1'. A partir das outras linhas, será a geração da tabela verdade.
Na primeira linha da tabela, serão impressas, primeiramente, as variáveis. Posteriormente, devverão ser impressas
todas as subexpressões com a seguinte ordenação: uma string A será menor que uma string B se o tamanho de
A for menor que o tamanho de B, ou, as duas havendo o mesmo tamanho, A deverá ser lexicograficamente
menor que B.
Só imprima sub-expressões distintas, por exemplo, caso apareçam duas sub-expressões (x.y), a saída válida só
deverá ter uma única aparição de (x.y).
Após o seu programa gerar uma tabela, ele deve imprimir três palavras: a primeira delas é "satisfativel" ou
"insatisfativel" a segunda é "e" e a terceira é "tautologia" ou "refutavel", dependendo da tabela que for gerada.
Depois disso, sempre deixar uma linha em branco.
É garantido que cada expressão da entrada possuirá menos de 50 subexpressões distintas não-atômicas.

Observação: Na saída, só deverão aparecer as variáveis que estão na expressão avaliada e devem seguir ordem
crescente. Ou seja, se a entrada só possuir x, y e t, a saída só poderá ter essas variáveis e deve ir de 000 até
111 em ordem crescente.

## Exemplo

Arquivo: "Expressoes.in"

```
2
(x+y)
((x+y).(z.t))
```
Arquivo: "Expressoes.out"

```
Tabela #1
-----------
|x|y|(x+y)|
-----------
|0|0|    0|
-----------
|0|1|    1|
-----------
|1|0|    1|
-----------
|1|1|    1|
-----------
satisfativel e refutavel
{linha em branco}
Tabela #2
-----------------------------------
|x|y|z|t|(x+y)|(z.t)|((x+y).(z.t))|
-----------------------------------
|0|0|0|0|    0|    0|            0|
-----------------------------------
|0|0|0|1|    0|    0|            0|
-----------------------------------
|0|0|1|0|    0|    0|            0|
-----------------------------------
|0|0|1|1|    0|    1|            0|
-----------------------------------
|0|1|0|0|    1|    0|            0|
-----------------------------------
|0|1|0|1|    1|    0|            0|
-----------------------------------
|0|1|1|0|    1|    0|            0|
-----------------------------------
|0|1|1|1|    1|    1|            1|
-----------------------------------
|1|0|0|0|    1|    0|            0|
-----------------------------------
|1|0|0|1|    1|    0|            0|
-----------------------------------
|1|0|1|0|    1|    0|            0|
-----------------------------------
|1|0|1|1|    1|    1|            1|
-----------------------------------
|1|1|0|0|    1|    0|            0|
-----------------------------------
|1|1|0|1|    1|    0|            0|
-----------------------------------
|1|1|1|0|    1|    0|            0|
-----------------------------------
|1|1|1|1|    1|    1|            1|
-----------------------------------
satisfativel e refutavel
{linha em branco}
{cursor aqui}
```
## Dúvidas Frequentes

■ Do pacote java.util somente poderão ser utilizadas as classes Vector, ArrayList e Scanner.


■ Do pacote java.io somente poderão ser utilizadas as classes BufferedWriter, FileWriter, BufferedReader e
FileReader.

■ De C/C++ somente poderão ser utilizadas as bibliotecas cstdio, cstdlib, iostream, string, cstring, vector e
cmath.

■ A saída deve ser exatamente como está demonstrada na especificação.

■ Não haverá nenhum acento nem ponto na saída.

■ Não haverá espaços no meio da expressão, logo não é necessário o uso do readLine(). Em vez disso, pode-se
utilizar o readString().

Obs: Todo o projeto deve ser entregue em uma única classe. É permitido o uso da classe Arquivo de Algoritmos,
contanto que o seu monitor seja avisado no e-mail de envio do projeto.
