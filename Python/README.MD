# Exemplo

Neste folder considero uma variação do problema que consiste em obter a menor número de moedas necessário para compor o troco total.

## Abordagem gulosa

Um forma de resolver este problema é via uma abordagem gulosa, porém não é garantido que ela irá fornecer uma solução ótima.

Por exemplo, se consideramos que temos somente moedas de $1, $7 e $10.
Precisamos achar um troco de $14.

Consideremos uma abordagem que seleciona o troco usando primeiro a model de maior valor. Com isto temos o um valor restante do troco que não pode ser coberto por esta moeda. Aí selecionamos a segunda maior moeda, achamos o que resta e vamos reduzindo o valor da moeda até que todo o troco seja formado.
Note que este método pressupõe que sempre haverá uma combinação que permita forma todo o troco. Em nosso exemplo, a moeda de $1 garante que sempre conseguimos fazer o troco, isto é, sempre é possível fazer n x $1 para obter n.

Seguindo o algoritmo explicado acima, chegamos a solução igual a:

> 1 x 10 + 4 x 1

ou seja, 4 moedas.

Contudo podemos ver claramente que uma solução 2 x 7 tem somente 2 moedas para a mesma quantidade.

## Programação dinâmica

A solução ótima pode ser obtida por programação dinâmica.

A ideia deste método é encontrar a resposta mais eficiente para cada troco possível __n*__ para a sequencia partindo de 1 até __n__ (o valor do troco que queremos).
O caso base é aquele onde todo o troco é dado com moedas de $1 (menor moeda possível) para cada nível.
A partir desta condição, buscamos encontrar recursivamente a sequência ótima que leva a um troco total de __n__.

A ideia portanto é:

* Criamos um array auxiliar que contem duas informações para cada montante de troco de 0 a n: a quantidade de moedas necessárias Q[i] e a maior moeda M[i], para cada troco i (de 1 até n). Note que para dar um troco de zero, precisamos de zero moedas (este é um dos casos base), isto é, Q[0] = 0 e M[0] = 1. O outro é para um troco de $1 precisamos de 1 moeda de 1.
* Selecionamos o troco que deverá ser formado __t__, começando de 1 (podemos começar de 2, se o caso base de $1 for acrescentado no primeiro passo).
    * Consideramos que começando a moeda de menor valor (no nosso exemplo $1), identificamos que precisamos de __min__ = __t__ moedas da moeda selecionada __M__ = $1 para dar o troco
    * Pegamos a primeira moeda de sequencia __m__
        - Se __m__ for menor ou igual a __t__ e Q[__t__ - __m__] + 1 for menor que __min__, atualizamos o valor de __min__ para Q[__t__ - __m__] + 1 e registramos que a moeda selecionada __M__ é __m__.
        - Testamos agora a próxima moeda da sequencia
* Assim ao termino do loop, para sabermos a quantidade de moedas necessarias, basta ler o valor de Q[i], para i == n.
* Para saber quais moedas, começamos com M[i] para i == n, e caminhamos para baixo, fazendo i = i - M[i].


Esta sequencia irá permitir obter a melhor combinação de moedas.
Vamos fazer o passo a passo considerando o exemplo acima:

> Troco = $14
> Moedas disponíveis = $1, $7 e $10

Armazenadores auxiliares com os dois casos bases:

> Q = [0, 1]
> M = [1, 1]

* Loop 2: Troco $2
    + m = 1 --> min = 2
    + m = 7 --> pular pois m > $2
    + m = 10 --> pular pois m > $2

> Q = [0, 1, 2]
> M = [1, 1, 1]

* Loop 3: Troco $3
    + m = 1 --> min = 3
    + m = 7 --> pular pois m > $3
    + m = 10 --> pular pois m > $3

> Q = [0, 1, 2, 3]
> M = [1, 1, 1, 1]

* Loop 4: Troco $4
    + m = 1 --> min = 4
    + m = 7 --> pular pois m > $4
    + m = 10 --> pular pois m > $4

> Q = [0, 1, 2, 3, 4]
> M = [1, 1, 1, 1, 1]

* Loop 5: Troco $5
    + m = 1 --> min = 5
    + m = 7 --> pular pois m > $5
    + m = 10 --> pular pois m > $5

> Q = [0, 1, 2, 3, 4, 5]
> M = [1, 1, 1, 1, 1, 1]

* Loop 6: Troco $6
    + m = 1 --> min = 6
    + m = 7 --> pular pois m > $6
    + m = 10 --> pular pois m > $6

> Q = [0, 1, 2, 3, 4, 5, 6]
> M = [1, 1, 1, 1, 1, 1, 1]

* Loop 7: Troco $7
    + m = 1 --> min = 7
    + m = 7 --> min = Q[0] + 1 = 1
    + m = 10 --> pular pois m > $7

> Q = [0, 1, 2, 3, 4, 5, 6, 1]
> M = [1, 1, 1, 1, 1, 1, 1, 7]

* Loop 8: Troco $8
    + m = 1 --> min = 8
    + m = 7 --> min = Q[1] + 1 = 2 (2 moedas: $1 + $7)
    + m = 10 --> pular pois m > $7

> Q = [0, 1, 2, 3, 4, 5, 6, 1, 2]
> M = [1, 1, 1, 1, 1, 1, 1, 7, 7]

* Loop 9: Troco $9
    + m = 1 --> min = 9
    + m = 7 --> min = Q[2] + 1 = 3 (3 moedas: 2 x $1 + $7)
    + m = 10 --> pular pois m > $7

> Q = [0, 1, 2, 3, 4, 5, 6, 1, 2, 3]
> M = [1, 1, 1, 1, 1, 1, 1, 7, 7, 7]

* Loop 10: Troco $10
    + m = 1 --> min = 10
    + m = 7 --> min = Q[3] + 1 = 4 (4 moedas: 3 x $1 + $7)
    + m = 10 --> min = Q[0] + 1 = 1 (1 moeda: $10)

> Q = [0, 1, 2, 3, 4, 5, 6, 1, 2, 3, 1]
> M = [1, 1, 1, 1, 1, 1, 1, 7, 7, 7, 10]

* Loop 11: Troco $11
    + m = 1 --> min = 11
    + m = 7 --> min = Q[4] + 1 = 5 (4 moedas: 4 x $1 + $7)
    + m = 10 --> min = Q[1] + 1 = 2 (2 moedas: $1 + $10)

> Q = [0, 1, 2, 3, 4, 5, 6, 1, 2, 3,  1,  2]
> M = [1, 1, 1, 1, 1, 1, 1, 7, 7, 7, 10, 10]

* Loop 12: Troco $12
    + m = 1 --> min = 12
    + m = 7 --> min = Q[5] + 1 = 6 (6 moedas: 5 x $1 + $7)
    + m = 10 --> min = Q[2] + 1 = 3 (3 moedas: 2 x $1 + $10)

> Q = [0, 1, 2, 3, 4, 5, 6, 1, 2, 3,  1,  2,  3]
> M = [1, 1, 1, 1, 1, 1, 1, 7, 7, 7, 10, 10, 10]

* Loop 13: Troco $13
    + m = 1 --> min = 13
    + m = 7 --> min = Q[6] + 1 = 7 (7 moedas: 6 x $1 + $7)
    + m = 10 --> min = Q[3] + 1 = 4 (4 moedas: 3 x $1 + $10)

> Q = [0, 1, 2, 3, 4, 5, 6, 1, 2, 3,  1,  2,  3,  4]
> M = [1, 1, 1, 1, 1, 1, 1, 7, 7, 7, 10, 10, 10, 10]

* Loop 14: Troco $14
    + m = 1 --> min = 14
    + m = 7 --> min = Q[7] + 1 = 2 (2 moedas: 2 x $7)
    + m = 10 --> min = Q[4] + 1 = 5 (5 moedas: 4 x $1 + $10)

> Q = [0, 1, 2, 3, 4, 5, 6, 1, 2, 3,  1,  2,  3,  4, 2]
> M = [1, 1, 1, 1, 1, 1, 1, 7, 7, 7, 10, 10, 10, 10, 7]

