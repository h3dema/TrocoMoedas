# Troco em Moedas (coin change problem)

O problema do troco consiste em encontrar a combinação de moedas cuja soma seja igual a uma quantia determinada, a partir de uma lista de moedas válidas que possuem disponibilidade infinita.
Por exemplo, suponhamos que existem moedas para 1, 2, e 3 "peças".
O objetivo é, dada uma quantia (número inteiro, em "peças"), encontrar as combinações de moedas que fornece essa quantia com a menor quantidade de "peças" válidas.
Assim se temos um total de 4 "peças", as combinações de moedas são:
* 4 moedas de 1
* 2 moedas de 1 e uma de 2
* uma moeda de 1 e uma de 3
* 2 moedas de 2


## Código

Este repositório contem o codigo em Java para resolução do problema do troco em moedas.

```
javac CoinChange.java
java CoinChange
```

Você pode ver um [video com a explicação no Youtube](https://youtu.be/EGIMSyqftHc) e o texto com a explicação no [blog](http://h3dema.blogspot.com/2015/10/dp-problema-do-troco-em-moedas.html).
No site da [USP](https://panda.ime.usp.br/pythonds/static/pythonds_pt/04-Recursao/11-programacaoDinamica.html), você pode ainda ver uma versão em python com uma explicação elaborada do método de resolução.

## Variação

Existe uma variação deste problema que consiste em obter o menor número de moedas correspondente ao total. Pode parecer que uma abordagem gulosa é a solução ótima, mas não é verdade para todos os casos.
Se consideramos que temos moedas de 1, 7 e 10 e precisamos achar a quantia de 14.
Uma abordagem gulosa que seleciona da maior moeda para a menor, fornece uma solução igual a 1 x 10 + 4 x 1, ou seja, 4 moedas. Contudo podemos ver claramente que uma solução 2 x 7 tem somente 2 moedas para a mesma quantidade.
Assim a solução ótima pode ser obtida por programação dinâmica também.
Uma solução é utilizar o código acima e ver qual a solução com menor quantidade.
Existe um jeito mais direto?
Sim!