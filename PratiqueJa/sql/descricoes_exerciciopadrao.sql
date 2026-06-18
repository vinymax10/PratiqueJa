-- ============================================================
--  Preenche o campo `descricao` de cada ExercicioPadrao com um
--  texto (150-300 caracteres) descrevendo os conteúdos abordados
--  pelos geradores daquele assunto e nível.
--
--  Conteúdos extraídos da análise dos geradores em
--  src/main/java/matematica/<modulo>/<assunto>/nivelXpackage.
--  Chaveado por id. Banco: MySQL (pratiqueja), utf8mb4.
-- ============================================================

START TRANSACTION;

-- ===================== BÁSICO =====================

-- Adição (naturais)
UPDATE ExercicioPadrao SET descricao = 'Adição de números naturais explorando suas propriedades: comutativa, associativa e elemento neutro. Inclui soma de três parcelas, parcela desconhecida, múltiplos de 10, verificação de resultados e problemas contextualizados do dia a dia.' WHERE id = 2151;
UPDATE ExercicioPadrao SET descricao = 'Adição de naturais com números maiores: algoritmo da soma armada em coluna com reagrupamento, três parcelas, parcela faltante, verificação do resultado e problemas contextualizados de dois passos em situações reais.' WHERE id = 2152;
UPDATE ExercicioPadrao SET descricao = 'Adição de naturais em nível avançado: soma armada em coluna com vários reagrupamentos, três parcelas, descoberta de parcela desconhecida, verificação e problemas contextualizados de dois ou mais passos.' WHERE id = 2153;

-- Subtração (naturais)
UPDATE ExercicioPadrao SET descricao = 'Subtração de números naturais: conceito de diferença, termos (minuendo e subtraendo), elemento neutro, minuendo e subtraendo desconhecidos, prova real e o fato de não ser comutativa nem associativa, com problemas contextualizados.' WHERE id = 2154;
UPDATE ExercicioPadrao SET descricao = 'Subtração de naturais com números maiores: cálculo armado em coluna com recurso (empréstimo), diferença, prova real, cálculos de três etapas e problemas contextualizados do cotidiano.' WHERE id = 2155;
UPDATE ExercicioPadrao SET descricao = 'Subtração de naturais em nível avançado: subtração armada em coluna com vários reagrupamentos, prova real, cálculos de três etapas e problemas contextualizados que combinam várias operações.' WHERE id = 2156;

-- Multiplicação (naturais)
UPDATE ExercicioPadrao SET descricao = 'Multiplicação de naturais e suas propriedades: comutativa, associativa, distributiva, elemento neutro e absorvente, fator faltante, multiplicação por 10, sequências, truques de cálculo mental e problemas contextualizados.' WHERE id = 2157;
UPDATE ExercicioPadrao SET descricao = 'Multiplicação de naturais: cálculo com números maiores, propriedade distributiva, comparação sem calcular, estimativas, dobro e metade, fator faltante, verificação e problemas contextualizados.' WHERE id = 2158;
UPDATE ExercicioPadrao SET descricao = 'Multiplicação de naturais em nível avançado: cálculo com fatores grandes, três fatores, expressões, fator desconhecido, estimativas e problemas contextualizados de múltiplos passos.' WHERE id = 2159;

-- Divisão (naturais)
UPDATE ExercicioPadrao SET descricao = 'Divisão de números naturais: termos da divisão, tabuada, quantas vezes cabe, divisão exata e inexata, divisão por potências de 10, fator faltante, prova real e o fato de não ser comutativa, com problemas contextualizados.' WHERE id = 2160;
UPDATE ExercicioPadrao SET descricao = 'Divisão de naturais: algoritmo da divisão armada em coluna, divisão com resto, divisão por 10, dividendo desconhecido, prova real e problemas contextualizados do cotidiano.' WHERE id = 2161;
UPDATE ExercicioPadrao SET descricao = 'Divisão de naturais em nível avançado: divisão armada com resto, dividendo desconhecido, prova real, cálculos de três etapas e problemas contextualizados que envolvem várias operações.' WHERE id = 2162;

-- Adição e subtração de inteiros
UPDATE ExercicioPadrao SET descricao = 'Adição e subtração de números inteiros: regra de sinais (parcelas de sinais iguais e diferentes), oposto e valor absoluto, elemento neutro, subtração como soma do oposto e problemas contextualizados de ganhos e perdas.' WHERE id = 2163;
UPDATE ExercicioPadrao SET descricao = 'Adição e subtração de inteiros: soma de dois termos, subtração pelo oposto, expressões com três termos, termo desconhecido e problemas contextualizados envolvendo números positivos e negativos.' WHERE id = 2164;
UPDATE ExercicioPadrao SET descricao = 'Adição e subtração de inteiros em nível avançado: expressões com três e quatro termos, subtração pelo oposto e problemas contextualizados que combinam várias operações com sinais.' WHERE id = 2165;

-- Multiplicação e divisão de inteiros
UPDATE ExercicioPadrao SET descricao = 'Multiplicação e divisão de números inteiros: regra de sinais, identificação do sinal do resultado, elemento neutro, fator faltante, potências de base negativa, prova real e problemas contextualizados.' WHERE id = 2166;
UPDATE ExercicioPadrao SET descricao = 'Multiplicação e divisão de inteiros: cálculo com sinais, propriedade distributiva, três fatores, divisão e problemas contextualizados envolvendo números positivos e negativos.' WHERE id = 2167;
UPDATE ExercicioPadrao SET descricao = 'Multiplicação e divisão de inteiros em nível avançado: produtos e quocientes com sinais, expressões com três fatores e problemas contextualizados que combinam as duas operações.' WHERE id = 2168;

-- Números decimais
UPDATE ExercicioPadrao SET descricao = 'Números decimais: adição e subtração com uma e duas casas decimais, multiplicação por 10 e 100 com deslocamento da vírgula e conversão de frações de denominador 10 ou 100 em números decimais.' WHERE id = 1;
UPDATE ExercicioPadrao SET descricao = 'Números decimais: adição de vários decimais, subtração, multiplicação de decimais, divisão de decimal por inteiro e conversão de frações usuais (1/2, 1/4, 1/5) em números decimais.' WHERE id = 2;
UPDATE ExercicioPadrao SET descricao = 'Números decimais em nível avançado: divisão de decimais, produtos com mais casas decimais, subtração com precisões diferentes, expressões com parênteses e comparação entre decimais.' WHERE id = 3;

-- Números primos
UPDATE ExercicioPadrao SET descricao = 'Números primos e compostos: identificar se um número é primo ou composto, contar os divisores de um número e reconhecer o maior fator primo, introduzindo a ideia de fatoração.' WHERE id = 52;
UPDATE ExercicioPadrao SET descricao = 'Números primos: teste de primalidade pelos primos até a raiz quadrada, decomposição em fatores primos, contagem de divisores e identificação do maior fator primo de um número.' WHERE id = 53;
UPDATE ExercicioPadrao SET descricao = 'Números primos em nível avançado: número de divisores via fatoração (inclusive de N²), números coprimos, fatoração com vários primos distintos e contagem de primos em um intervalo (crivo de Eratóstenes).' WHERE id = 54;

-- Sistema métrico
UPDATE ExercicioPadrao SET descricao = 'Sistema métrico decimal: conversão de unidades de comprimento e de massa entre múltiplos e submúltiplos (de maior para menor e vice-versa), incluindo resultados com números decimais.' WHERE id = 152;
UPDATE ExercicioPadrao SET descricao = 'Sistema métrico: conversões de capacidade (L, mL, kL), de área (m², cm², km²) e de comprimento em dois passos, além de problemas de massa contextualizados.' WHERE id = 153;
UPDATE ExercicioPadrao SET descricao = 'Sistema métrico em nível avançado: conversão de volume (m³, cm³ e equivalência com litros), unidades de tempo e problemas mistos que combinam comprimento, massa, capacidade e área em contextos reais.' WHERE id = 154;

-- Área e perímetro
UPDATE ExercicioPadrao SET descricao = 'Área e perímetro de figuras planas: quadrado, retângulo, triângulo, paralelogramo, trapézio, losango e círculo (e circunferência), com cálculo direto a partir das medidas indicadas na figura.' WHERE id = 2196;
UPDATE ExercicioPadrao SET descricao = 'Área e perímetro com figuras compostas: quadrados e retângulos com círculos ou quadrados inscritos, triângulos isósceles e equiláteros, e o losango usando o teorema de Pitágoras.' WHERE id = 2197;
UPDATE ExercicioPadrao SET descricao = 'Área e perímetro em nível avançado: figuras com medidas envolvendo radicais (triângulo equilátero), losango a partir das diagonais e problemas que combinam várias figuras planas.' WHERE id = 2198;

-- Soma dos ângulos do triângulo
UPDATE ExercicioPadrao SET descricao = 'Soma dos ângulos internos do triângulo: cálculo do ângulo desconhecido x usando que a soma dos internos vale 180°, incluindo o caso do triângulo isósceles, a partir de figuras.' WHERE id = 2205;
UPDATE ExercicioPadrao SET descricao = 'Soma dos ângulos internos do triângulo: determinação do valor de x em triângulos com expressões algébricas nos ângulos, aplicando a propriedade da soma igual a 180°.' WHERE id = 2206;
UPDATE ExercicioPadrao SET descricao = 'Soma dos ângulos do triângulo em nível avançado: ângulos internos e externos, teorema do ângulo externo e situações em que se conhecem dois ângulos para determinar o terceiro.' WHERE id = 2207;

-- Divisibilidade
UPDATE ExercicioPadrao SET descricao = 'Critérios de divisibilidade por 2, 3, 5 e 10: reconhecer quando um número é divisível por esses valores observando suas características, servindo de base para o estudo de múltiplos e divisores.' WHERE id = 2190;
UPDATE ExercicioPadrao SET descricao = 'Critérios de divisibilidade por 4, 6, 7, 8 e 9: aplicação das regras práticas para verificar quando um número é divisível por esses valores, ampliando os critérios estudados no nível anterior.' WHERE id = 2191;
UPDATE ExercicioPadrao SET descricao = 'Critérios de divisibilidade em nível avançado: combinação de vários critérios em um mesmo número e problemas que exigem identificar divisores e analisar a divisibilidade de números maiores.' WHERE id = 2192;

-- Expressões numéricas
UPDATE ExercicioPadrao SET descricao = 'Expressões numéricas com as quatro operações: expressões planas respeitando a hierarquia das operações e expressões com um par de parênteses (à esquerda e à direita).' WHERE id = 2214;
UPDATE ExercicioPadrao SET descricao = 'Expressões numéricas com parênteses e colchetes: vários níveis de agrupamento, parênteses internos a colchetes e combinação das quatro operações respeitando a precedência.' WHERE id = 2215;
UPDATE ExercicioPadrao SET descricao = 'Expressões numéricas avançadas: três níveis de agrupamento (parênteses, colchetes e chaves) e expressões na forma de fração com operações no numerador e no denominador.' WHERE id = 2216;

-- MMC e MDC
UPDATE ExercicioPadrao SET descricao = 'Mínimo múltiplo comum (MMC): cálculo do MMC de dois ou mais números pela fatoração em fatores primos, incluindo problemas contextualizados que envolvem coincidências e repetições.' WHERE id = 2217;
UPDATE ExercicioPadrao SET descricao = 'Máximo divisor comum (MDC): cálculo do MDC de dois ou mais números pela fatoração em fatores primos, com problemas contextualizados de divisão em partes iguais.' WHERE id = 2218;
UPDATE ExercicioPadrao SET descricao = 'MMC e MDC em nível avançado: problemas que combinam os dois conceitos, uso da relação MDC × MMC = produto dos números e situações contextualizadas mais elaboradas.' WHERE id = 2219;

-- Regra de três
UPDATE ExercicioPadrao SET descricao = 'Regra de três simples direta: resolução de problemas com grandezas diretamente proporcionais, montando e resolvendo a proporção em situações do cotidiano.' WHERE id = 2227;
UPDATE ExercicioPadrao SET descricao = 'Regra de três simples inversa: problemas com grandezas inversamente proporcionais, identificando o tipo de proporcionalidade e resolvendo a proporção correspondente.' WHERE id = 2228;
UPDATE ExercicioPadrao SET descricao = 'Regra de três composta: problemas que envolvem três ou mais grandezas, analisando a proporcionalidade direta ou inversa de cada uma para chegar à solução.' WHERE id = 2229;

-- Números racionais (frações)
UPDATE ExercicioPadrao SET descricao = 'Números racionais (frações): simplificação de frações, comparação de frações, operações básicas e o conceito de fração equivalente, trabalhando a leitura e o significado das frações.' WHERE id = 2172;
UPDATE ExercicioPadrao SET descricao = 'Números racionais: operações com frações (adição, subtração, multiplicação e divisão), frações equivalentes com numerador desconhecido e comparação entre frações.' WHERE id = 2173;
UPDATE ExercicioPadrao SET descricao = 'Números racionais em nível avançado: operações combinadas com frações, potência de fração, fração de uma quantidade e conversão entre fração mista e imprópria.' WHERE id = 2174;

-- Plano cartesiano
UPDATE ExercicioPadrao SET descricao = 'Plano cartesiano: identificação de quadrantes, distância de um ponto à origem e entre dois pontos, ponto simétrico em relação aos eixos e distância de um ponto aos eixos coordenados.' WHERE id = 163124;
UPDATE ExercicioPadrao SET descricao = 'Plano cartesiano com leitura de gráfico: identificar as coordenadas de um ponto marcado, calcular a distância à origem e determinar o ponto médio de um segmento.' WHERE id = 163125;
UPDATE ExercicioPadrao SET descricao = 'Plano cartesiano em nível avançado: a partir de pontos no gráfico, calcular distâncias (com simplificação de radicais), ponto médio de segmentos e ponto simétrico em relação aos eixos.' WHERE id = 163126;

-- Conjuntos
UPDATE ExercicioPadrao SET descricao = 'Conjuntos: operações de união, intersecção e diferença, complementar, cardinalidade, relação de pertinência e subconjuntos, a partir de conjuntos dados por seus elementos.' WHERE id = 162097;
UPDATE ExercicioPadrao SET descricao = 'Conjuntos com diagramas de Venn: cálculo das cardinalidades |A|, |B|, |A∩B|, |A∪B| e |A−B| usando a relação de inclusão-exclusão a partir do diagrama.' WHERE id = 162098;
UPDATE ExercicioPadrao SET descricao = 'Conjuntos em nível avançado: problemas contextualizados de união e diferença e aplicação do princípio da inclusão-exclusão |A∪B| = |A| + |B| − |A∩B| para determinar quantidades em situações reais.' WHERE id = 162099;

-- ===================== INTERMEDIÁRIO =====================

-- Ângulo inscrito na circunferência
UPDATE ExercicioPadrao SET descricao = 'Ângulos na circunferência: relação entre ângulo central e ângulo inscrito que enxergam o mesmo arco, ângulo inscrito em semicircunferência (reto) e cálculo de x a partir de figuras.' WHERE id = 2208;
UPDATE ExercicioPadrao SET descricao = 'Ângulos na circunferência: aplicação das propriedades do ângulo inscrito e do ângulo central em configurações variadas, determinando o valor de x em figuras com arcos e cordas.' WHERE id = 2209;
UPDATE ExercicioPadrao SET descricao = 'Ângulos na circunferência em nível avançado: combinação de ângulo inscrito, ângulo central e arcos em figuras mais complexas para determinar o valor de x.' WHERE id = 2210;

-- Círculo
UPDATE ExercicioPadrao SET descricao = 'Círculo e circunferência: cálculo do comprimento (C = 2πr ou πd) e da área (A = πr²), comprimento de arco e área do setor circular, além da área da coroa circular.' WHERE id = 352;
UPDATE ExercicioPadrao SET descricao = 'Círculo: problemas inversos a partir do comprimento ou da área (encontrar o raio), relação entre comprimento de arco, ângulo central e raio, e área do setor circular.' WHERE id = 353;
UPDATE ExercicioPadrao SET descricao = 'Círculo em nível avançado: área da coroa circular para achar o raio interno ou externo, e relações entre arco, setor, ângulo central e raio em problemas de dois passos.' WHERE id = 354;

-- Dízima periódica
UPDATE ExercicioPadrao SET descricao = 'Dízimas periódicas: conversão de dízima periódica em fração geratriz, conversão de fração em dízima e classificação da dízima em simples ou composta.' WHERE id = 2187;
UPDATE ExercicioPadrao SET descricao = 'Dízimas periódicas: determinação da fração geratriz de dízimas simples e compostas, identificação do período e conversão de fração em dízima periódica.' WHERE id = 2188;
UPDATE ExercicioPadrao SET descricao = 'Dízimas periódicas em nível avançado: cálculo da fração geratriz de dízimas compostas com parte não periódica, consolidando a conversão entre dízima e fração.' WHERE id = 2189;

-- Equações do 1º grau
UPDATE ExercicioPadrao SET descricao = 'Equações do primeiro grau: resolução de equações simples do tipo ax + b = c, isolando a incógnita por meio de operações inversas e verificando a solução encontrada.' WHERE id = 2181;
UPDATE ExercicioPadrao SET descricao = 'Equações do primeiro grau com parênteses: resolução de equações do tipo a(bx + c) = d, aplicando a propriedade distributiva antes de isolar a incógnita.' WHERE id = 2182;
UPDATE ExercicioPadrao SET descricao = 'Equações do primeiro grau com incógnita nos dois membros: resolução de equações do tipo ax + b = cx + d, agrupando os termos com a incógnita de um mesmo lado.' WHERE id = 2183;

-- Expressões algébricas
UPDATE ExercicioPadrao SET descricao = 'Expressões algébricas: cálculo do valor numérico de uma expressão substituindo a variável por um valor dado, introduzindo a linguagem algébrica e o uso de letras.' WHERE id = 2178;
UPDATE ExercicioPadrao SET descricao = 'Expressões algébricas: cálculo do valor numérico de expressões e operações básicas com termos algébricos, manipulando variáveis e coeficientes em situações variadas.' WHERE id = 2179;
UPDATE ExercicioPadrao SET descricao = 'Expressões algébricas em nível avançado: simplificação de expressões, multiplicação de monômios (coeficiente e expoente do resultado), redução de termos semelhantes e valor numérico com duas variáveis.' WHERE id = 2180;

-- Função afim
UPDATE ExercicioPadrao SET descricao = 'Função afim (do 1º grau): cálculo de f(x), identificação dos coeficientes angular e linear, cálculo do zero da função e determinação de x conhecendo o valor de f(x).' WHERE id = 148537;
UPDATE ExercicioPadrao SET descricao = 'Função afim: coeficiente angular a partir do gráfico, determinação dos coeficientes conhecendo um ponto, e problemas contextualizados (taxa fixa mais variável, temperatura, poupança).' WHERE id = 148552;
UPDATE ExercicioPadrao SET descricao = 'Função afim em nível avançado: determinação da lei da função a partir de dois pontos ou do gráfico e estudo do sinal (valores de x para os quais f(x) é positiva ou negativa).' WHERE id = 148545;

-- Funções (conceito)
UPDATE ExercicioPadrao SET descricao = 'Conceito de função: identificar se uma relação no diagrama de setas é função, ler imagens de elementos, reconhecer função injetora e determinar o conjunto imagem.' WHERE id = 163127;
UPDATE ExercicioPadrao SET descricao = 'Funções: cálculo de valores f(x), verificação se um ponto pertence ao gráfico da função e determinação de x tal que f(x) assuma um valor dado.' WHERE id = 163128;
UPDATE ExercicioPadrao SET descricao = 'Funções em nível avançado: composição de funções (f∘g e f∘f) e cálculo de valores de funções compostas a partir de suas leis, consolidando a ideia de função como transformação.' WHERE id = 163129;

-- Juros e desconto
UPDATE ExercicioPadrao SET descricao = 'Matemática financeira: juros simples — cálculo de juros, montante, capital, taxa e tempo, com problemas contextualizados de aplicações e empréstimos.' WHERE id = 157420;
UPDATE ExercicioPadrao SET descricao = 'Matemática financeira: desconto simples, além da revisão de juros simples, resolvendo problemas de valor nominal, valor atual e taxa de desconto.' WHERE id = 157453;
UPDATE ExercicioPadrao SET descricao = 'Matemática financeira em nível avançado: juros compostos e desconto composto, comparando com o regime simples em problemas contextualizados de aplicações financeiras.' WHERE id = 157454;

-- Notação científica
UPDATE ExercicioPadrao SET descricao = 'Notação científica: conversão de números grandes e pequenos para a notação científica (e vice-versa), identificação da forma correta e comparação de grandezas.' WHERE id = 102;
UPDATE ExercicioPadrao SET descricao = 'Notação científica: multiplicação e divisão (com e sem ajuste da mantissa), adição e subtração igualando expoentes, potência e conversão de unidades em notação científica.' WHERE id = 103;
UPDATE ExercicioPadrao SET descricao = 'Notação científica em nível avançado: operações encadeadas e aplicações em contextos de Física e Ciências (distância, velocidade, tempo, crescimento exponencial) com ajuste de mantissa.' WHERE id = 104;

-- Pitágoras
UPDATE ExercicioPadrao SET descricao = 'Teorema de Pitágoras: cálculo de um lado do triângulo retângulo (cateto ou hipotenusa) a partir da figura, incluindo o triângulo especial 30°-60°-90°.' WHERE id = 2199;
UPDATE ExercicioPadrao SET descricao = 'Teorema de Pitágoras: cálculo de lados em diversas configurações, relações métricas no triângulo retângulo (altura e projeções sobre a hipotenusa) e verificação de triângulo retângulo.' WHERE id = 2200;
UPDATE ExercicioPadrao SET descricao = 'Teorema de Pitágoras em nível avançado: problemas contextualizados (escadas, rampas, cabos) e cálculo da distância entre dois pontos no plano cartesiano.' WHERE id = 2201;

-- Polígonos regulares
UPDATE ExercicioPadrao SET descricao = 'Polígonos regulares: soma dos ângulos internos S = (n−2)·180°, ângulo interno, ângulo externo (360°/n), perímetro e relações de apótema e área no hexágono regular.' WHERE id = 173103;
UPDATE ExercicioPadrao SET descricao = 'Polígonos regulares: problemas inversos para descobrir o número de lados n a partir de ângulos, o lado a partir do perímetro e o perímetro a partir da área e do apótema.' WHERE id = 173104;
UPDATE ExercicioPadrao SET descricao = 'Polígonos regulares em nível avançado: problemas de dois passos relacionando ângulos e número de lados, e cálculo de área e perímetro do hexágono e do quadrado via apótema.' WHERE id = 173105;

-- Potenciação
UPDATE ExercicioPadrao SET descricao = 'Potenciação: cálculo de potências de base inteira, potências de base negativa, expoente zero e propriedades básicas, com foco no significado da potência como multiplicação de fatores iguais.' WHERE id = 2169;
UPDATE ExercicioPadrao SET descricao = 'Potenciação: aplicação das propriedades (produto e quociente de potências de mesma base, potência de potência), potências de expoente negativo e conversão de números para notação científica.' WHERE id = 2170;
UPDATE ExercicioPadrao SET descricao = 'Potenciação em nível avançado: equações exponenciais simples (descobrir o expoente x), aplicação combinada das propriedades de potências e expressões com potências de mesma base.' WHERE id = 2171;

-- Radiciação
UPDATE ExercicioPadrao SET descricao = 'Radiciação: raiz quadrada e raízes de diversos índices de quadrados e potências perfeitas, incluindo raiz de frações com quadrados perfeitos, reforçando a relação entre radiciação e potenciação.' WHERE id = 2175;
UPDATE ExercicioPadrao SET descricao = 'Radiciação: simplificação de radicais extraindo fatores quadrados, adição e subtração de radicais semelhantes, racionalização de denominadores, expoentes fracionários e raízes cúbicas.' WHERE id = 2176;
UPDATE ExercicioPadrao SET descricao = 'Radiciação em nível avançado: operações com radicais, produto de conjugados, simplificação de índices, expoentes fracionários e equações irracionais simples.' WHERE id = 2177;

-- Razões trigonométricas
UPDATE ExercicioPadrao SET descricao = 'Razões trigonométricas no triângulo retângulo: seno, cosseno e tangente de ângulos a partir da figura, relacionando catetos e hipotenusa e introduzindo as razões trigonométricas.' WHERE id = 157970;
UPDATE ExercicioPadrao SET descricao = 'Razões trigonométricas: cálculo de lados desconhecidos de triângulos retângulos usando seno, cosseno e tangente, a partir de figuras com ângulos dados.' WHERE id = 157978;
UPDATE ExercicioPadrao SET descricao = 'Razões trigonométricas em nível avançado: determinação do valor de x em triângulos retângulos por meio das razões trigonométricas em figuras mais complexas.' WHERE id = 157979;

-- Semelhança (ângulos)
UPDATE ExercicioPadrao SET descricao = 'Ângulos: ângulos complementares e suplementares, ângulos opostos pelo vértice e formados por retas, determinando o valor de x a partir das relações angulares.' WHERE id = 2202;
UPDATE ExercicioPadrao SET descricao = 'Ângulos: relações angulares em retas paralelas cortadas por uma transversal (ângulos correspondentes, alternos e colaterais) para determinar o valor de x.' WHERE id = 2203;
UPDATE ExercicioPadrao SET descricao = 'Relações angulares em nível avançado: combinação de ângulos formados por retas paralelas e transversais em figuras mais elaboradas para determinar o valor de x.' WHERE id = 2204;

-- Semelhança de triângulos
UPDATE ExercicioPadrao SET descricao = 'Semelhança de triângulos: cálculo de lados desconhecidos usando a proporcionalidade entre triângulos semelhantes e a razão de semelhança, a partir de figuras.' WHERE id = 2211;
UPDATE ExercicioPadrao SET descricao = 'Semelhança de triângulos: determinação de medidas em triângulos semelhantes, razão de semelhança e relações métricas no triângulo retângulo (altura e projeções sobre a hipotenusa).' WHERE id = 2212;
UPDATE ExercicioPadrao SET descricao = 'Semelhança de triângulos em nível avançado: cálculo de lados e razões em configurações variadas, aplicando a proporcionalidade e a razão de semelhança em problemas elaborados.' WHERE id = 2213;

-- Sistemas de equações
UPDATE ExercicioPadrao SET descricao = 'Sistemas de duas equações do primeiro grau com duas incógnitas: resolução pelos métodos da adição e da substituição, determinando o valor de x e de y.' WHERE id = 162868;
UPDATE ExercicioPadrao SET descricao = 'Sistemas de equações do primeiro grau: resolução pelos métodos da substituição, adição e comparação, determinando o par solução (x, y) de cada sistema.' WHERE id = 162869;
UPDATE ExercicioPadrao SET descricao = 'Sistemas de equações em nível avançado: sistemas com três incógnitas (x, y, z), resolvidos pelos métodos da substituição, adição e comparação.' WHERE id = 162870;

-- Equação do 2º grau
UPDATE ExercicioPadrao SET descricao = 'Equação do segundo grau: resolução de equações completas e incompletas, encontrando a raiz positiva ou a raiz não nula, com aplicação da fórmula de Bhaskara.' WHERE id = 148597;
UPDATE ExercicioPadrao SET descricao = 'Equação do segundo grau: cálculo do discriminante e do número de raízes reais, relações de Girard (soma e produto das raízes) e problemas como o produto de inteiros consecutivos.' WHERE id = 148603;
UPDATE ExercicioPadrao SET descricao = 'Equação do segundo grau em nível avançado: interpretação do gráfico da parábola para determinar raízes, vértice e coeficientes a partir da representação visual.' WHERE id = 148645;

-- ===================== AVANÇADO =====================

-- Combinatória
UPDATE ExercicioPadrao SET descricao = 'Análise combinatória: princípio fundamental da contagem (princípio multiplicativo), permutações simples e permutações circulares, com problemas de contagem contextualizados.' WHERE id = 159841;
UPDATE ExercicioPadrao SET descricao = 'Análise combinatória: arranjos simples, combinações simples e triângulo de Pascal, diferenciando situações em que a ordem dos elementos importa ou não.' WHERE id = 159842;
UPDATE ExercicioPadrao SET descricao = 'Análise combinatória em nível avançado: permutações com repetição, anagramas, combinações com repetição e com restrição, e o binômio de Newton.' WHERE id = 159843;

-- Estatística
UPDATE ExercicioPadrao SET descricao = 'Estatística: medidas básicas de tendência central e dispersão — média aritmética, moda e amplitude — e classificação de variáveis estatísticas (discreta, contínua, nominal e ordinal).' WHERE id = 173100;
UPDATE ExercicioPadrao SET descricao = 'Estatística: média ponderada, mediana, distribuição de frequências e frequência relativa (em porcentagem), interpretando conjuntos de dados.' WHERE id = 173101;
UPDATE ExercicioPadrao SET descricao = 'Estatística em nível avançado: quartis, medidas de dispersão (variância e desvio) e detecção de valores atípicos (outliers) pelo critério do boxplot (Q3 + 1,5 × AIQ).' WHERE id = 173102;

-- Função exponencial
UPDATE ExercicioPadrao SET descricao = 'Função exponencial: cálculo de f(x) (inclusive em x negativo e x = 0), classificação em crescente ou decrescente e resolução de equações exponenciais simples por igualdade de bases.' WHERE id = 202;
UPDATE ExercicioPadrao SET descricao = 'Função exponencial: equações exponenciais por igualdade de bases, leitura de gráficos (determinar a base) e problemas de crescimento e decaimento (bactérias, meia-vida).' WHERE id = 203;
UPDATE ExercicioPadrao SET descricao = 'Função exponencial em nível avançado: equações com fração e do tipo quadrático em a^x, leitura de gráficos e aplicações de crescimento populacional e juros compostos.' WHERE id = 204;

-- Função logarítmica
UPDATE ExercicioPadrao SET descricao = 'Função logarítmica: cálculo de f(x) = log_a(x), zero da função, relação com a função exponencial como sua inversa e classificação em crescente ou decrescente.' WHERE id = 252;
UPDATE ExercicioPadrao SET descricao = 'Função logarítmica: domínio da função, resolução de equações e inequações logarítmicas simples e leitura de gráficos para determinar a base.' WHERE id = 253;
UPDATE ExercicioPadrao SET descricao = 'Função logarítmica em nível avançado: inequações logarítmicas (inclusive de base fracionária), estudo do sinal, relação com a inversa exponencial e leitura de gráficos.' WHERE id = 254;

-- Função modular
UPDATE ExercicioPadrao SET descricao = 'Função modular e módulo: cálculo de |a − b|, valor de f(x) de uma função modular, identificação do vértice e do conjunto imagem da função.' WHERE id = 302;
UPDATE ExercicioPadrao SET descricao = 'Função modular: equações modulares |bx + c| = k (soma e maior das soluções, número de soluções), vértice e leitura de gráficos da função modular.' WHERE id = 303;
UPDATE ExercicioPadrao SET descricao = 'Função modular em nível avançado: inequações modulares (com <, ≤, > e ≥) e interpretação gráfica para determinar os valores de x em que f(x) fica acima ou abaixo de uma reta.' WHERE id = 304;

-- Função quadrática
UPDATE ExercicioPadrao SET descricao = 'Função quadrática (do 2º grau): cálculo de f(x), cálculo do discriminante Δ, identificação da concavidade da parábola e da coordenada x do vértice.' WHERE id = 163130;
UPDATE ExercicioPadrao SET descricao = 'Função quadrática: coordenadas do vértice (Xv e Yv), cálculo de raízes, determinação dos coeficientes a, b e c a partir de pontos e problemas de máximo e mínimo (projétil, lucro, área).' WHERE id = 163131;
UPDATE ExercicioPadrao SET descricao = 'Função quadrática em nível avançado: relações de Girard, forma fatorada, conjunto imagem, vértice e determinação dos coeficientes a partir do gráfico da parábola.' WHERE id = 163132;

-- Funções trigonométricas
UPDATE ExercicioPadrao SET descricao = 'Funções trigonométricas: conversão entre graus e radianos, seno, cosseno e tangente de ângulos, quadrantes do ciclo trigonométrico, imagem das funções e comprimento de arco.' WHERE id = 452;
UPDATE ExercicioPadrao SET descricao = 'Funções trigonométricas: relações trigonométricas (dado o seno ou o cosseno, achar os demais), período e zeros das funções e identificação de expressões equivalentes.' WHERE id = 453;
UPDATE ExercicioPadrao SET descricao = 'Funções trigonométricas em nível avançado: relações entre seno, cosseno e tangente em diferentes quadrantes (com atenção aos sinais) e cálculo de expressões trigonométricas.' WHERE id = 454;

-- Geometria analítica
UPDATE ExercicioPadrao SET descricao = 'Geometria analítica: distância entre dois pontos e à origem, ponto médio, coeficiente angular de uma reta, coeficientes angular e linear e pontos pertencentes à reta.' WHERE id = 402;
UPDATE ExercicioPadrao SET descricao = 'Geometria analítica: equação da reta (coeficientes), distância de ponto a reta, reta por um ponto com inclinação dada e equação da circunferência (centro e raio).' WHERE id = 403;
UPDATE ExercicioPadrao SET descricao = 'Geometria analítica em nível avançado: interseção de retas, reta a partir do ponto médio, equação geral da circunferência (raio) e posição de um ponto em relação à circunferência.' WHERE id = 404;

-- Geometria espacial
UPDATE ExercicioPadrao SET descricao = 'Geometria espacial: cálculo de volume e de área de sólidos básicos — paralelepípedo (caixa retangular), cilindro, esfera e prisma — a partir de suas dimensões.' WHERE id = 405;
UPDATE ExercicioPadrao SET descricao = 'Geometria espacial: cone, pirâmide de base quadrada, cilindro e esfera — áreas (lateral e total) e volumes, além de problemas inversos para encontrar o raio.' WHERE id = 406;
UPDATE ExercicioPadrao SET descricao = 'Geometria espacial em nível avançado: área total e volume de cilindro, cone (com geratriz) e pirâmide, sólidos de revolução e prismas com relações entre as dimensões.' WHERE id = 407;

-- Inequação do 2º grau
UPDATE ExercicioPadrao SET descricao = 'Inequações do segundo grau: resolução de inequações estritas (> ou <) e não estritas (≥ ou ≤) com raízes inteiras, incluindo casos incompletos e com raiz negativa, via estudo do sinal.' WHERE id = 163133;
UPDATE ExercicioPadrao SET descricao = 'Inequações do segundo grau: inequações com coeficiente a diferente de 1, concavidade para baixo, forma fatorada com quadro de sinais, casos Δ = 0 e Δ < 0, e problemas com rearranjo.' WHERE id = 163134;
UPDATE ExercicioPadrao SET descricao = 'Inequações do segundo grau em nível avançado: interpretação gráfica da parábola (concavidade e raízes) para determinar os intervalos em que f(x) é positiva ou negativa.' WHERE id = 163135;

-- Lei dos senos e cossenos
UPDATE ExercicioPadrao SET descricao = 'Lei dos senos e lei dos cossenos: resolução de triângulos quaisquer (não retângulos), calculando lados desconhecidos a partir de lados e ângulos dados.' WHERE id = 159087;
UPDATE ExercicioPadrao SET descricao = 'Lei dos senos e lei dos cossenos: cálculo de lados e ângulos em triângulos quaisquer, escolhendo a lei adequada conforme os dados do problema.' WHERE id = 159088;
UPDATE ExercicioPadrao SET descricao = 'Lei dos senos e dos cossenos em nível avançado: resolução completa de triângulos quaisquer e aplicação em problemas contextualizados que envolvem lados e ângulos.' WHERE id = 159089;

-- Logaritmo
UPDATE ExercicioPadrao SET descricao = 'Logaritmos: cálculo do valor de um logaritmo pela definição, logaritmos especiais e aplicação das propriedades log_a(a^n) = n e a^(log_a b) = b.' WHERE id = 2193;
UPDATE ExercicioPadrao SET descricao = 'Logaritmos: propriedades operatórias (logaritmo do produto, do quociente e da potência) no cálculo de expressões e resolução de equações logarítmicas simples.' WHERE id = 2194;
UPDATE ExercicioPadrao SET descricao = 'Logaritmos em nível avançado: mudança de base, equações logarítmicas e aplicações em contextos reais (escala Richter de terremotos, pH e intensidade sonora).' WHERE id = 2195;

-- Matrizes
UPDATE ExercicioPadrao SET descricao = 'Matrizes: identificação de elementos (inclusive da matriz transposta), adição, subtração e multiplicação por escalar, com a soma dos elementos da matriz resultante.' WHERE id = 160836;
UPDATE ExercicioPadrao SET descricao = 'Matrizes: multiplicação de matrizes, multiplicação pela transposta, combinações lineares com escalares e condição de matriz simétrica, trabalhando elementos do resultado.' WHERE id = 160837;
UPDATE ExercicioPadrao SET descricao = 'Matrizes em nível avançado: multiplicação e potência de matrizes, cálculo do determinante, matriz inversa e equações com determinante igual a zero.' WHERE id = 160838;

-- Números complexos
UPDATE ExercicioPadrao SET descricao = 'Números complexos: forma algébrica a + bi, identificação da parte real e da imaginária, conjugado e potências da unidade imaginária i, introduzindo o conjunto dos complexos.' WHERE id = 155815;
UPDATE ExercicioPadrao SET descricao = 'Números complexos: operações (adição, subtração e multiplicação), produto pelo conjugado, módulo e igualdade de complexos para determinar incógnitas.' WHERE id = 155816;
UPDATE ExercicioPadrao SET descricao = 'Números complexos em nível avançado: módulo, forma trigonométrica r(cosθ + i senθ) e cálculo de potências por meio da fórmula de De Moivre.' WHERE id = 155817;

-- Progressão aritmética
UPDATE ExercicioPadrao SET descricao = 'Progressão aritmética (PA): termo geral (n-ésimo termo), cálculo da razão, número de termos e determinação de termos desconhecidos, em PA crescente e decrescente.' WHERE id = 154932;
UPDATE ExercicioPadrao SET descricao = 'Progressão aritmética: soma dos n primeiros termos, número de termos, termos desconhecidos e interpolação de meios aritméticos entre dois valores dados.' WHERE id = 154933;
UPDATE ExercicioPadrao SET descricao = 'Progressão aritmética em nível avançado: termo geral, soma dos termos, interpolação de meios e problemas envolvendo propriedades da PA com vários termos.' WHERE id = 154934;

-- Progressão geométrica
UPDATE ExercicioPadrao SET descricao = 'Progressão geométrica (PG): termo geral (n-ésimo termo), número de termos e determinação de termos desconhecidos da sequência, identificando a razão.' WHERE id = 155472;
UPDATE ExercicioPadrao SET descricao = 'Progressão geométrica: soma dos n termos, soma da PG infinita, número de termos, termo desconhecido e interpolação de meios geométricos.' WHERE id = 155473;
UPDATE ExercicioPadrao SET descricao = 'Progressão geométrica em nível avançado: termo geral, soma dos termos finita e infinita, número de termos e problemas com propriedades da PG.' WHERE id = 155474;

-- Polinômios
UPDATE ExercicioPadrao SET descricao = 'Polinômios: identificação do grau, redução de termos semelhantes e produtos notáveis — quadrado da soma (ax+b)² e produto da soma pela diferença (ax+b)(ax−b).' WHERE id = 163136;
UPDATE ExercicioPadrao SET descricao = 'Polinômios: produtos notáveis e fatoração — fator comum, trinômio quadrado perfeito e diferença de quadrados — e teorema do resto (divisão por x − r).' WHERE id = 163137;
UPDATE ExercicioPadrao SET descricao = 'Polinômios em nível avançado: adição e subtração, multiplicação de binômio por trinômio, divisão por Briot-Ruffini e fatoração completa de polinômios do 3º grau.' WHERE id = 163138;

-- Probabilidade
UPDATE ExercicioPadrao SET descricao = 'Probabilidade: cálculo de probabilidades simples, evento complementar, eventos mutuamente exclusivos (regra da soma) e eventos independentes (regra do produto).' WHERE id = 163121;
UPDATE ExercicioPadrao SET descricao = 'Probabilidade: regra da soma para eventos não exclusivos P(A∪B), probabilidade de ocorrer ao menos um sucesso (evento complementar) e probabilidade condicional.' WHERE id = 163122;
UPDATE ExercicioPadrao SET descricao = 'Probabilidade em nível avançado: probabilidade condicional, teorema de Bayes, distribuição binomial e problemas de retirada de bolas em urnas usando combinatória.' WHERE id = 163123;

-- Trigonometria: fórmulas de adição
UPDATE ExercicioPadrao SET descricao = 'Trigonometria, fórmulas de adição: seno, cosseno e tangente da soma e da diferença de ângulos notáveis, arco duplo e arco metade (bissecção), com resultados envolvendo radicais.' WHERE id = 502;
UPDATE ExercicioPadrao SET descricao = 'Trigonometria, fórmulas de adição e arco duplo aplicadas a triplas pitagóricas: seno, cosseno e tangente da soma e diferença, sen(2α), cos(2α), tg(2α) e bissecção.' WHERE id = 503;
UPDATE ExercicioPadrao SET descricao = 'Trigonometria em nível avançado: fórmulas de adição, arco duplo e bissecção com ângulos em diferentes quadrantes (atenção aos sinais), com resultados positivos e negativos.' WHERE id = 504;

COMMIT;
