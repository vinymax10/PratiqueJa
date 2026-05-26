# Plano de Reestruturação — Matemática

Baseado na auditoria curricular comparada com a matriz do ENEM/BNCC.
Gerado em 2026-05-25.

---

## Visão Geral

| Tipo | Qtd | Descrição |
|---|---|---|
| Novos assuntos | 13 | Pastas novas com `teoria.xhtml` + entrada no menu |
| Divisão de assunto existente | 1 | `equacaoSegundoGrau` → extrair `funcaoQuadratica` |
| Adição em assunto existente | 1 | Seção em `areaPerimetro` |
| Já coberto (sem ação) | — | `geometriaAnalitica`, `areaPerimetro` base, `funcoesTrigonometricas` |

---

## FASE 1 — Fundamentos Ausentes do Básico (alta prioridade)

Estes assuntos são pré-requisito para tudo e estão completamente ausentes.

---

### 1.1 Expressões Numéricas — novo assunto

**Pasta:** `matematica/basico/expressaoNumerica/`

**Seções:**
1. **Hierarquia das Operações** — potenciação antes de ×÷ antes de +−; regra do parêntese interno para externo
2. **Parênteses, Colchetes e Chaves** — ordem de resolução, exemplos aninhados
3. **Expressões com Frações e Decimais** — combinando todas as operações
4. **Exemplos Resolvidos Passo a Passo** — 4–5 expressões do mais simples ao mais complexo

**Observação:** não há nenhum assunto cobrindo isso hoje. É pré-requisito para equações, logaritmos e praticamente tudo.

---

### 1.2 Números Decimais — novo assunto

**Pasta:** `matematica/basico/numerosDecimais/`

**Seções:**
1. **Leitura e Escrita** — casa decimal, valor posicional (décimos, centésimos, milésimos)
2. **Comparação e Ordenação** — alinhamento de vírgula, exemplos
3. **Adição e Subtração** — alinhamento de vírgula
4. **Multiplicação** — contagem das casas decimais no produto
5. **Divisão** — divisor inteiro e divisor decimal (ajuste)
6. **Conversão com Frações** — 0,25 = 1/4; 1,5 = 3/2 etc.

**Observação:** hoje só existe `dizima` (conversão dízima → fração). Operações com decimais não são cobertas em nenhum assunto.

---

### 1.3 Números Primos e Fatoração — novo assunto

**Pasta:** `matematica/basico/numerosPrimos/`

**Seções:**
1. **Números Primos e Compostos** — definição, crivo de Eratóstenes até 100
2. **Decomposição em Fatores Primos** — algoritmo da "chave", exemplos
3. **Aplicação no MMC e MDC** — relação com o assunto `mmcMdc` já existente
4. **Problemas** — situações que exigem fatoração (divisão de grupos, distribuição)

**Observação:** `divisibilidade` cita primos mas não os ensina formalmente. `mmcMdc` pressupõe que o aluno já sabe fatorar.

---

### 1.4 Sistema Métrico e Unidades — novo assunto

**Pasta:** `matematica/basico/sistemaMetrico/`

**Seções:**
1. **Comprimento** — km, hm, dam, m, dm, cm, mm; tabela de conversão
2. **Massa** — kg, g, mg; tonelada
3. **Capacidade** — litro, mililitro, kL
4. **Área e Volume** — m², cm², m³, cm³; conversões
5. **Tempo** — horas, minutos, segundos; conversões

---

## FASE 2 — Fundamentos Ausentes do Intermediário (alta prioridade)

---

### 2.1 Expressões Algébricas — novo assunto

**Pasta:** `matematica/intermediario/expressaoAlgebrica/`

**Seções:**
1. **Definição** — variável, constante, termo, coeficiente, parte literal
2. **Valor Numérico** — substituição da variável, avaliação da expressão
3. **Termos Semelhantes** — identificação, adição e subtração
4. **Multiplicação de Monômios** — produto de coeficientes × produto das partes literais
5. **Distributiva** — a(b + c), expansão e simplificação
6. **Exemplos Contextualizados** — área, perímetro, velocidade expressos algebricamente

**Observação:** pré-requisito direto para `equacoes`, `polinomios` e `equacaoSegundoGrau`. Atualmente o aluno chega em equações sem saber operar expressões algébricas.

---

### 2.2 Circunferência e Círculo — novo assunto

**Pasta:** `matematica/intermediario/circulo/`

**Seções:**
1. **Definição** — circunferência vs. círculo, raio, diâmetro, corda, arco
2. **Comprimento da Circunferência** — C = 2πr; aproximação π ≈ 3,14
3. **Área do Círculo** — A = πr²
4. **Arco e Setor Circular** — proporção com o ângulo central; comprimento do arco
5. **Coroa Circular** — área entre dois círculos concêntricos
6. **Problemas Contextualizados** — pista de corrida, pizza, relógio

**Assets SVG necessários:**
- `fig-circulo-elementos.svg` — círculo com raio, diâmetro, corda e arco nomeados
- `fig-setor-circular.svg` — setor com ângulo central e arco destacados

**Observação:** `anguloInscritoCircunferencia` existe mas aborda apenas ângulos. Comprimento e área são completamente ausentes — e são dos temas mais frequentes no ENEM.

---

### 2.3 Notação Científica — novo assunto

**Pasta:** `matematica/intermediario/notacaoCientifica/`

**Seções:**
1. **Definição** — a × 10ⁿ com 1 ≤ a < 10; leitura de números muito grandes/pequenos
2. **Conversão** — número → notação científica e vice-versa
3. **Operações** — multiplicação e divisão (some/subtraia os expoentes)
4. **Adição e Subtração** — igualação dos expoentes antes de operar
5. **Aplicações** — distâncias astronômicas, tamanho de células, dados do ENEM

---

### 2.4 Polígonos Regulares — novo assunto

**Pasta:** `matematica/intermediario/poligonosRegulares/`

**Seções:**
1. **Definição** — polígono regular, ângulo interno, ângulo externo, apótema
2. **Soma dos Ângulos Internos** — fórmula (n−2)×180°; casos: triângulo, quad., pentágono, hexágono
3. **Ângulo Externo** — 360°/n; soma sempre 360°
4. **Apótema** — cálculo pelo triângulo retângulo interno
5. **Área do Polígono Regular** — A = (P × a) / 2 onde P = perímetro e a = apótema
6. **Hexágono Regular** — caso especial: lado = raio; área = 3√3/2 × l²

**Assets SVG necessários:**
- `fig-poligono-angulos.svg` — polígono com ângulos interno/externo destacados
- `fig-hexagono-apotema.svg` — hexágono com apótema e triângulo interno

---

## FASE 3 — Conceito Geral de Funções (alta prioridade)

Esta fase precisa ser feita **antes** de `funcaoAfim`, `funcaoExponencial` etc. no percurso do aluno — mas os assuntos específicos já existem e não precisam ser refeitos.

---

### 3.1 Funções — Conceito Geral — novo assunto

**Pasta:** `matematica/avancado/funcoes/`

**Seções:**
1. **Definição** — relação, função, domínio (D), contradomínio (CD), imagem (Im)
2. **Representações** — diagrama de setas, tabela, fórmula, gráfico
3. **Identificar se é Função** — teste da reta vertical no gráfico; cada x tem um único y
4. **Tipos de Função** — injetora (um-a-um), sobrejetora (Im = CD), bijetora
5. **Função Composta** — (g∘f)(x) = g(f(x)); exemplos práticos
6. **Função Inversa** — definição, condição (bijetora), como calcular f⁻¹(x)

---

### 3.2 Função Quadrática — DIVISÃO de `equacaoSegundoGrau`

**Ação:** criar `matematica/avancado/funcaoQuadratica/` extraindo e expandindo as seções de `equacaoSegundoGrau` que tratam da função:

**Mover / reescrever:**
- Gráfico (parábola) com TikZ detalhado
- Vértice da parábola — coordenadas e interpretação
- Estudo do sinal — relação com as raízes e concavidade

**Seções novas a adicionar:**
- **Domínio e Imagem** — Im = [yv, +∞) se a > 0; (−∞, yv] se a < 0 *(já existe em `equacaoSegundoGrau` como seção extra — mover para cá)*
- **Intervalos de Crescimento/Decrescimento**
- **Zero da Função** — relação com raízes da equação
- **Aplicações de Otimização** — maximizar lucro, minimizar custo, trajetória de projétil

**Manter em `equacaoSegundoGrau`:**
- Definição e forma geral
- Fórmula de Bhaskara e discriminante Δ
- Natureza das raízes (Δ > 0, = 0, < 0)
- Equações incompletas
- Relações de Vieta
- Problemas contextualizados de equação

---

### 3.3 Inequações do 2º Grau — novo assunto

**Pasta:** `matematica/avancado/inequacoes2grau/`

**Seções:**
1. **Revisão** — inequações do 1º grau, notação de intervalo
2. **Método Gráfico** — usar o gráfico da parábola para determinar sinal
3. **Passo a Passo** — encontrar raízes, analisar concavidade, montar a solução
4. **Casos Especiais** — Δ < 0 (sem raiz real): solução é ∅ ou ℝ conforme concavidade
5. **Inequação Fatorada** — produto de binômios, estudo de sinal por tabela
6. **Exemplos** — resolução de x² − 5x + 6 ≤ 0; x² + 1 > 0; −x² + 4 ≥ 0

---

## FASE 4 — Avançado Complementar (média prioridade)

---

### 4.1 Função Modular — novo assunto

**Pasta:** `matematica/avancado/funcaoModular/`

**Seções:**
1. **Definição** — |x| como distância à origem; definição por casos
2. **Gráfico** — forma em V; domínio ℝ; imagem [0, +∞)
3. **Equações Modulares** — |f(x)| = k → casos positivo e negativo
4. **Inequações Modulares** — |f(x)| < k ↔ −k < f(x) < k; |f(x)| > k ↔ f(x) < −k ou f(x) > k
5. **Exemplos** — |2x − 3| = 5; |x + 1| ≤ 4; |3x − 1| > 2

**Assets SVG necessários:**
- `fig-grafico-modular.svg` — gráfico em V de f(x) = |x| com deslocamentos

---

### 4.2 Fórmulas de Adição e Duplicação — novo assunto

**Pasta:** `matematica/avancado/trigoAdicao/`

**Seções:**
1. **Fórmulas de Adição** — sen(a±b), cos(a±b), tg(a±b)
2. **Fórmulas de Duplicação** — sen(2a), cos(2a), tg(2a) derivadas das de adição
3. **Fórmulas de Bissecção (metade)** — sen²(a/2) e cos²(a/2)
4. **Produto em Soma** — transformações para facilitar cálculos
5. **Exemplos** — calcular sen 75°, cos 15°; provar identidades

---

## FASE 5 — Plano Cartesiano Básico (baixa prioridade)

---

### 5.1 Plano Cartesiano — novo assunto

**Pasta:** `matematica/basico/planoCoordenado/`

**Seções:**
1. **Eixos e Origem** — eixo x, eixo y, origem (0,0), quadrantes
2. **Localização de Pontos** — par ordenado (x, y), abscissa e ordenada
3. **Distância à Origem** — sem Pitágoras ainda, apenas conceito visual
4. **Pares Simétricos** — reflexão em relação aos eixos e à origem

**Observação:** pré-requisito visual para `funcaoAfim` e `geometriaAnalitica`. Hoje o aluno é lançado direto no gráfico sem nunca ter visto um plano cartesiano formalmente.

---

## Resumo de Execução

```
FASE 1 — Fundamentos Básicos  ✅ CONCLUÍDA (2026-05-25)
  [x] 1.1  basico/expressaoNumerica/teoria.xhtml
  [x] 1.2  basico/numerosDecimais/teoria.xhtml
  [x] 1.3  basico/numerosPrimos/teoria.xhtml
  [x] 1.4  basico/sistemaMetrico/teoria.xhtml

FASE 2 — Fundamentos Intermediários  ✅ CONCLUÍDA (2026-05-25)
  [x] 2.1  intermediario/expressaoAlgebrica/teoria.xhtml
  [x] 2.2  intermediario/circulo/teoria.xhtml              + 2 SVGs
  [x] 2.3  intermediario/notacaoCientifica/teoria.xhtml
  [x] 2.4  intermediario/poligonosRegulares/teoria.xhtml   + 2 SVGs

FASE 3 — Funções  ✅ CONCLUÍDA (2026-05-25)
  [x] 3.1  avancado/funcoes/teoria.xhtml
  [x] 3.2  avancado/funcaoQuadratica/teoria.xhtml          (divisão de equacaoSegundoGrau)
  [x] 3.3  avancado/inequacoes2grau/teoria.xhtml

FASE 4 — Avançado Complementar  ✅ CONCLUÍDA (2026-05-25)
  [x] 4.1  avancado/funcaoModular/teoria.xhtml             + 1 SVG
  [x] 4.2  avancado/trigoAdicao/teoria.xhtml

FASE 5 — Complemento Básico  ✅ CONCLUÍDA (2026-05-25)
  [x] 5.1  basico/planoCoordenado/teoria.xhtml
```

**Total: 14 novos assuntos · 5 SVGs · 1 divisão de assunto existente**

---

## Reestruturação de Existentes (quando realizar a Fase 3)

### `equacaoSegundoGrau` — o que mover para `funcaoQuadratica`

Seções a **mover** (remover de `equacaoSegundoGrau/teoria.xhtml` e inserir em `funcaoQuadratica/teoria.xhtml`):
- `sec-grafico` — Gráfico (A Parábola)
- `sec-vertice` — Vértice da Parábola
- `sec-sinal` — Estudo do Sinal
- `sec-dominio-imagem` — Domínio e Imagem (adicionada no plano anterior)

Seções a **manter** em `equacaoSegundoGrau`:
- `sec-def` — Definição
- `sec-bhaskara` — Fórmula de Bhaskara
- `sec-incompletas` — Equações Incompletas
- `sec-vieta` — Relações de Vieta
- `sec-problemas` — Problemas Contextualizados

---

## Priorização ENEM

| Fase | Assunto | Freq. ENEM | Esforço |
|---|---|---|---|
| 2.2 | Circunferência e Círculo | ★★★★★ | médio |
| 3.3 | Inequações do 2º Grau | ★★★★★ | baixo |
| 1.1 | Expressões Numéricas | ★★★★☆ | baixo |
| 2.1 | Expressões Algébricas | ★★★★☆ | baixo |
| 3.1 | Funções — Conceito Geral | ★★★★☆ | médio |
| 3.2 | Função Quadrática | ★★★★☆ | alto (divisão) |
| 2.4 | Polígonos Regulares | ★★★☆☆ | médio |
| 1.2 | Números Decimais | ★★★☆☆ | baixo |
| 4.1 | Função Modular | ★★★☆☆ | médio |
| 2.3 | Notação Científica | ★★☆☆☆ | baixo |
| 1.3 | Números Primos | ★★☆☆☆ | baixo |
| 4.2 | Fórmulas de Adição | ★★☆☆☆ | médio |
| 5.1 | Plano Cartesiano | ★★☆☆☆ | baixo |
| 1.4 | Sistema Métrico | ★★☆☆☆ | baixo |
