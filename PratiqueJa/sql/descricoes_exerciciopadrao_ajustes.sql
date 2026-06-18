-- Ajuste: amplia para >= 150 caracteres as descrições que ficaram curtas.
START TRANSACTION;

UPDATE ExercicioPadrao SET descricao = 'Progressão geométrica: cálculo da soma dos n primeiros termos, soma da PG infinita, número de termos, determinação de termos desconhecidos e interpolação de meios geométricos entre dois valores.' WHERE id = 155473;
UPDATE ExercicioPadrao SET descricao = 'Função modular e módulo: cálculo de |a − b|, valor de f(x) de uma função modular, identificação do vértice e do conjunto imagem da função, introduzindo o conceito de módulo de um número.' WHERE id = 302;
UPDATE ExercicioPadrao SET descricao = 'Números complexos em nível avançado: cálculo do módulo, representação na forma trigonométrica r(cosθ + i senθ) e cálculo de potências de complexos por meio da fórmula de De Moivre.' WHERE id = 155817;
UPDATE ExercicioPadrao SET descricao = 'Estatística: cálculo de média ponderada e mediana, construção e leitura de distribuição de frequências e cálculo da frequência relativa (em porcentagem), interpretando conjuntos de dados.' WHERE id = 173101;
UPDATE ExercicioPadrao SET descricao = 'Função logarítmica: determinação do domínio da função, resolução de equações e inequações logarítmicas simples e leitura de gráficos para determinar a base da função.' WHERE id = 253;
UPDATE ExercicioPadrao SET descricao = 'Progressão geométrica em nível avançado: termo geral, soma dos termos finita e infinita, número de termos e problemas que exploram as propriedades da PG com vários termos.' WHERE id = 155474;
UPDATE ExercicioPadrao SET descricao = 'Sistemas de equações em nível avançado: sistemas com três incógnitas (x, y e z), resolvidos pelos métodos da substituição, da adição e da comparação entre as equações.' WHERE id = 162870;
UPDATE ExercicioPadrao SET descricao = 'Funções: cálculo de valores f(x) a partir da lei da função, verificação se um ponto pertence ao gráfico e determinação de x tal que f(x) assuma um valor dado.' WHERE id = 163128;
UPDATE ExercicioPadrao SET descricao = 'Lei dos senos e lei dos cossenos: cálculo de lados e de ângulos em triângulos quaisquer (não retângulos), escolhendo a lei adequada conforme os dados fornecidos no problema.' WHERE id = 159088;
UPDATE ExercicioPadrao SET descricao = 'Análise combinatória em nível avançado: permutações com repetição, anagramas de palavras, combinações com repetição e com restrição, e o binômio de Newton.' WHERE id = 159843;
UPDATE ExercicioPadrao SET descricao = 'Logaritmos: cálculo do valor de um logaritmo pela definição, logaritmos especiais e aplicação das propriedades log_a(a^n) = n e a^(log_a b) = b no cálculo de expressões.' WHERE id = 2193;
UPDATE ExercicioPadrao SET descricao = 'Matemática financeira: desconto simples, além da revisão de juros simples, resolvendo problemas que envolvem valor nominal, valor atual, taxa e tempo de desconto.' WHERE id = 157453;
UPDATE ExercicioPadrao SET descricao = 'Função modular: resolução de equações modulares |bx + c| = k (soma e maior das soluções, número de soluções), identificação do vértice e leitura de gráficos da função modular.' WHERE id = 303;
UPDATE ExercicioPadrao SET descricao = 'Função quadrática (do 2º grau): cálculo de f(x), cálculo do discriminante Δ, identificação da concavidade da parábola e da coordenada x do vértice da função.' WHERE id = 163130;
UPDATE ExercicioPadrao SET descricao = 'Matrizes em nível avançado: multiplicação e potência de matrizes, cálculo do determinante, obtenção da matriz inversa e equações que envolvem o determinante igual a zero.' WHERE id = 160838;
UPDATE ExercicioPadrao SET descricao = 'Progressão geométrica (PG): cálculo do termo geral (n-ésimo termo), número de termos e determinação de termos desconhecidos da sequência, identificando a razão da PG.' WHERE id = 155472;
UPDATE ExercicioPadrao SET descricao = 'Dízimas periódicas: conversão de dízima periódica em fração geratriz, conversão de fração em dízima periódica e classificação da dízima em simples ou composta.' WHERE id = 2187;
UPDATE ExercicioPadrao SET descricao = 'Números complexos: operações de adição, subtração e multiplicação, produto pelo conjugado, cálculo do módulo e igualdade de complexos para determinar incógnitas.' WHERE id = 155816;
UPDATE ExercicioPadrao SET descricao = 'Matemática financeira: juros simples — cálculo de juros, montante, capital, taxa e tempo, com problemas contextualizados de aplicações financeiras e empréstimos.' WHERE id = 157420;

COMMIT;
