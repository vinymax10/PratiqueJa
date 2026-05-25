# Plano de Implementação — Matemática Ensino Médio

Baseado na análise dos assuntos existentes em `src/main/webapp/matematica/`, este plano descreve
todas as adições necessárias para cobrir o currículo completo do Ensino Médio (BNCC).

---

## Visão Geral

| Tipo | Qtd | Descrição |
|---|---|---|
| Novos assuntos | 7–8 | Arquivos `teoria.xhtml` em nova pasta |
| Adições em assuntos existentes | 5 | Nova seção `<div class="tr-section">` dentro do arquivo |
| Já coberto (nenhuma ação) | 3 | Determinantes, equações logarítmicas, inequações 2º grau |

---

## FASE 1 — Funções (alta prioridade)

Categoria: **avancado** · Estas são as lacunas mais críticas do currículo atual.

---

### 1.1 Função Exponencial — novo assunto

**Pasta:** `matematica/avancado/funcaoExponencial/`

**Seções:**
1. **Definição** — f(x) = aˣ, restrições (a > 0, a ≠ 1), exemplos com base 2 e 1/2
2. **Gráfico** — curvas crescente (a > 1) e decrescente (0 < a < 1), intercepto em (0,1)
3. **Número de Euler (e)** — definição, aproximação, função f(x) = eˣ
4. **Equações Exponenciais** — igualação de bases, casos com logaritmo
5. **Aplicações** — crescimento populacional, decaimento radioativo, juros compostos revisitado

**Assets SVG necessários:**
- `fig-grafico-exponencial.svg` — duas curvas (base > 1 e 0 < base < 1)

---

### 1.2 Função Logarítmica — novo assunto

**Pasta:** `matematica/avancado/funcaoLogaritmica/`

**Seções:**
1. **Definição** — f(x) = logₐx como função, domínio (x > 0), imagem ℝ
2. **Gráfico** — curvas crescente e decrescente, assíntota vertical x = 0
3. **Inversa da Exponencial** — relação f⁻¹, simetria em relação à reta y = x
4. **Análise de Crescimento** — domínio, imagem, zeros da função, sinal

> Obs.: operações e propriedades já estão em `intermediario/logaritmo/teoria.xhtml`.
> Este assunto foca exclusivamente na perspectiva de função.

**Assets SVG necessários:**
- `fig-grafico-logaritmo.svg` — curva e assíntota vertical

---

### 1.3 Funções Trigonométricas — novo assunto

**Pasta:** `matematica/avancado/funcoesTrigonometricas/`

**Seções:**
1. **Radiano** — conversão grau ↔ radiano, arcos notáveis (π/6, π/4, π/3, π/2, π)
2. **Ciclo Trigonométrico** — definição de sen(x) e cos(x) no círculo unitário
3. **Tabela de Valores Notáveis** — sen e cos para 0°, 30°, 45°, 60°, 90°, 180°, 270°, 360°
4. **Gráficos de Seno e Cosseno** — período 2π, amplitude 1, deslocamentos
5. **Tangente** — definição, período π, assíntotas
6. **Identidades Fundamentais** — sen²x + cos²x = 1, relações derivadas

> Obs.: razões trigonométricas no triângulo retângulo já estão em
> `intermediario/razoesTrigonometricas/teoria.xhtml`.

**Assets SVG necessários:**
- `fig-ciclo-trigonometrico.svg`
- `fig-grafico-seno-cosseno.svg`

---

## FASE 2 — Geometria (alta prioridade)

Categoria: **avancado** · Completamente ausente no conteúdo atual.

---

### 2.1 Geometria Analítica — novo assunto

**Pasta:** `matematica/avancado/geometriaAnalitica/`

**Seções:**
1. **Ponto no Plano Cartesiano** — coordenadas, distância entre pontos, ponto médio
2. **Equação da Reta** — formas: slope-intercept, ponto-inclinação, geral (ax + by + c = 0)
3. **Coeficiente Angular** — inclinação, retas paralelas (m₁ = m₂), perpendiculares (m₁·m₂ = −1)
4. **Posições Relativas** — paralelas, concorrentes, coincidentes
5. **Distância Ponto–Reta** — fórmula e aplicação
6. **Equação da Circunferência** — forma canônica (x−a)² + (y−b)² = r², forma geral
7. **Posição Relativa Ponto–Circunferência** — interior, exterior, na curva

**Assets SVG necessários:**
- `fig-plano-cartesiano-reta.svg`
- `fig-circunferencia-analitica.svg`

---

### 2.2 Geometria Espacial — novo assunto

**Pasta:** `matematica/avancado/geometriaEspacial/`

**Seções:**
1. **Prismas** — definição, planificação, área total, volume (V = A_base × h)
2. **Pirâmides** — definição, área lateral, área total, volume (V = A_base × h / 3)
3. **Cilindro** — área lateral (2πrh), área total, volume (πr²h)
4. **Cone** — geratriz, área lateral (πrl), área total, volume (πr²h / 3)
5. **Esfera** — área (4πr²), volume (4πr³/3)
6. **Sólidos de Revolução** — cilindro e cone como sólidos gerados por rotação

**Assets SVG necessários:**
- `fig-prisma.svg`
- `fig-piramide.svg`
- `fig-cilindro-cone.svg`
- `fig-esfera.svg`

---

## FASE 3 — Estatística (alta prioridade)

Categoria: **avancado** · Muito cobrado no ENEM, completamente ausente.

---

### 3.1 Estatística — novo assunto

**Pasta:** `matematica/avancado/estatistica/`

**Seções:**
1. **Conceitos Iniciais** — população, amostra, variável quantitativa e qualitativa
2. **Medidas de Tendência Central** — média aritmética (simples e ponderada), mediana, moda
3. **Medidas de Dispersão** — amplitude, desvio médio, variância, desvio padrão
4. **Distribuição de Frequências** — tabela de frequência, frequência relativa e acumulada
5. **Gráficos Estatísticos** — histograma, polígono de frequências, boxplot básico

**Assets SVG necessários:**
- `fig-histograma.svg`

---

## FASE 4 — Álgebra complementar (média prioridade)

---

### 4.1 Polinômios — novo assunto

**Pasta:** `matematica/avancado/polinomios/`

**Seções:**
1. **Definição e Nomenclatura** — grau, coeficientes, termos semelhantes
2. **Operações** — adição, subtração, multiplicação
3. **Casos Notáveis** — quadrado da soma/diferença, produto da soma pela diferença
4. **Fatoração** — fator comum, agrupamento, trinômio perfeito, diferença de quadrados
5. **Divisão de Polinômios** — algoritmo de Briot-Ruffini (divisão por (x−a))
6. **Teorema do Resto e das Raízes** — p(a) = 0 ↔ (x−a) é fator

---

## FASE 5 — Adições em assuntos existentes (baixa esforço)

Estas mudanças adicionam **uma seção** ao `teoria.xhtml` existente.

---

### 5.1 Inequações do 1º Grau → adicionar em `intermediario/equacoes/`

**Nova seção:** "Inequações do 1º Grau"
- Símbolos de desigualdade, regra de inversão ao multiplicar/dividir por negativo
- Representação na reta numérica e notação de intervalo
- Exemplos: `3x − 2 > 7`, `−2x + 1 ≤ 5`

---

### 5.2 Porcentagem → adicionar em `basico/regraTres/`

**Nova seção:** "Porcentagem"
- Porcentagem como proporção de 100, conversão fração ↔ decimal ↔ %
- Cálculo de % de um valor, aumento e desconto percentual
- Exemplos contextualizados (desconto, lucro, taxa)

---

### 5.3 Binômio de Newton → adicionar em `avancado/combinatoria/`

**Nova seção:** "Binômio de Newton"
- Triângulo de Pascal e relação com combinações
- Fórmula geral: (a+b)ⁿ = Σ C(n,k) · aⁿ⁻ᵏ · bᵏ
- Expansão de casos simples

---

### 5.4 Números Irracionais → adicionar em `basico/racionais/`

**Nova seção:** "Números Irracionais e Reais"
- Definição de irracional (dízima não periódica), exemplos: √2, π, e
- Conjunto dos reais ℝ como união de Q e I
- Diagrama de inclusão dos conjuntos numéricos (ℕ ⊂ ℤ ⊂ ℚ ⊂ ℝ)

---

### 5.5 Domínio e Imagem da Função Quadrática → adicionar em `avancado/equacaoSegundoGrau/`

**Nova seção:** "Função Quadrática — Domínio e Imagem"
- Domínio: ℝ
- Imagem: [yᵥ, +∞) se a > 0 · (−∞, yᵥ] se a < 0
- Intervalos de crescimento e decrescimento em relação ao vértice

---

## Resumo de Execução

```
FASE 1 — Funções
  [x] 1.1  avancado/funcaoExponencial/teoria.xhtml        + 1 SVG
  [x] 1.2  avancado/funcaoLogaritmica/teoria.xhtml        + 1 SVG
  [x] 1.3  avancado/funcoesTrigonometricas/teoria.xhtml   + 2 SVGs

FASE 2 — Geometria
  [x] 2.1  avancado/geometriaAnalitica/teoria.xhtml       + 2 SVGs
  [x] 2.2  avancado/geometriaEspacial/teoria.xhtml        + 4 SVGs

FASE 3 — Estatística
  [ ] 3.1  avancado/estatistica/teoria.xhtml              + 1 SVG

FASE 4 — Álgebra
  [ ] 4.1  avancado/polinomios/teoria.xhtml

FASE 5 — Adições
  [ ] 5.1  + seção em intermediario/equacoes/teoria.xhtml
  [ ] 5.2  + seção em basico/regraTres/teoria.xhtml
  [ ] 5.3  + seção em avancado/combinatoria/teoria.xhtml
  [ ] 5.4  + seção em basico/racionais/teoria.xhtml
  [ ] 5.5  + seção em avancado/equacaoSegundoGrau/teoria.xhtml
```

**Total: 7 novos assuntos · 10 SVGs · 5 adições em arquivos existentes**
