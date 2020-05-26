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

A ideia deste método é encontrar a resposta mais eficiente para cada troco possível $n^{\ast} \in \{0, 1, 2, \dots, n \}$, partindo de um caso base (todo o troco é dado com moedas de 1 centavo), e com isso encontrar recursivamente a sequência ótima que leva a um troco total de $n$.

Partindo de uma lista de k moedas válidas $M = \{m_1, m_2, \dots, m_k \}$, o algoritmo deve encontrar qual moeda deve ser escolhida para se encontrar o melhor caminho que leva a um troco $n^{\ast}$, e repetir para todos os $n^{\ast}$ (em ordem crescente) até chegar no caso $n^{\ast} = n$, que é o caso desejado.

Para cada $n^{\ast}$, como é conhecido que uma solução possível é "n∗ moedas de 1 centavo", atribui-se moedas factíveis (ou seja, moedas válidas em $M$ e que sejam menores que $n^{\ast}$, pois caso contrário "estouraria" a quantia imediatamente).
Após testar para cada moeda factível, armazenar a moeda que induz a sequência com menor quantidade de moedas necessárias para se completar $n^{\ast}$, e assim sucessivamente, até que se chegue em $n$.
