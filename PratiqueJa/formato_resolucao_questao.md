---
name: feedback-formato-resolucao-questao
description: Como elaborar e formatar as resoluções das questões (campo questao.resolucao no banco)
metadata:
  type: feedback
---

# Formato de resolução das questões

Padrão para escrever/reescrever o campo `questao.resolucao` (tabela `questao`,
banco `pratiqueja`). Reaproveita as convenções maduras de
[`formato_resolucao.md`](formato_resolucao.md) (geradores Java) e incorpora as
regras didáticas e de tabela definidas no plano de revisão das resoluções.

> **Diferença essencial vs. geradores Java.** Nos geradores você monta uma
> *string Java* e escapa as barras (`\\(\\\\\\)`). Aqui a resolução vai **direto
> para o banco**, então a notação é **crua** (uma barra): escreve-se `\(\\\)`,
> `\dfrac{}{}`, `\\`, etc. — exatamente o que será renderizado. Toda a tabela de
> tokens abaixo está na forma **crua**.

---

## 1. Princípios didáticos (questões são escritas à mão)

Diferente dos geradores (que produzem o resultado correto por construção), a
resolução de uma questão é **autoral** e precisa ser confiável:

1. **Definir cada variável/grandeza antes de usar.** Diga o que cada símbolo
   representa.
2. **Explicar cada passo, sem saltos.** Enuncie a fórmula/teorema/propriedade
   aplicada antes de substituir os valores.
3. **Corrigir erros do original.** Se a resolução existente (ou o gabarito
   textual) tiver erro factual, corrija.
4. **Conferir a alternativa marcada como correta** e garantir que a resolução
   chega exatamente nela. Se não chegar, há um erro a investigar (na resolução
   ou no gabarito).
5. **Profissional e didática:** prosa clara, transições explicadas, nada de
   "pulo do gato".

---

## 2. Regra principal

Texto explicativo fica **fora** de `\(…\)`. Expressões matemáticas ficam em
blocos `\(…\)`. Nunca embrulhar a resolução inteira num único `\(…\)` com
`\text{}`.

**Why:** legibilidade e quebra de linha natural — a coluna (layout de coluna
dupla) quebra a prosa sozinha; só o trecho matemático precisa ser quebrado à mão.

---

## 3. Largura e o porquê das quebras

A resolução é renderizada em **coluna estreita (dupla)** — mire **~40 caracteres
por linha**. O texto corrido **não** precisa de quebra manual (a apostila
quebra). A quebra manual serve **só para o trecho matemático**, que não quebra
sozinho.

**Medida de largura (proxy):** dígito/símbolo/operador ≈ 1; `\dfrac{a}{b}` conta
como o **maior** entre numerador e denominador (empilha, não soma); `\sqrt{x}` ≈
x+1; nome de função (`\cos`, `\operatorname{sen}`) ≈ 3. Mire **~20–30 unidades**
por linha matemática.

---

## 4. Tokens de quebra (forma crua)

### `\(\\\)` — quebra entre um bloco de texto e um bloco matemático

É um span de math contendo só `\\`. Usado **entre** segmentos texto↔fórmula.

```text
Substituindo na equação: \(\\\)
\(3x + 5 = 20\)
```

### `\\` — quebra entre passos matemáticos no mesmo bloco

No final da linha, **sem fechar** `\)`. Para encadear passos.

### `= \\` — quebra dentro de `\(…\)` numa cadeia de igualdades

O `=` fica no **fim** da linha (espaço depois do `\\`). Nunca comece a linha
seguinte com `=`.

```text
CORRETO:
\(2x = 20 - 6 = \\
14\)            ← mas: não deixe número/fração órfão (ver §6)

ERRADO (= no início da linha):
\(2x\) \(\\\)
\(= 14\)
```

---

## 5. Sequência de passos matemáticos — bloco único

Quando vários passos consecutivos são **todos matemáticos** (sem texto entre
eles), **não** feche e reabra `\(…\)` a cada linha. Use **um único bloco** com
`\\` separando os passos:

```text
ERRADO — abre/fecha entre cada passo:
\(3x + 5 = 20\) \(\\\)
\(3x = 15\) \(\\\)
\(x = 5\)

CORRETO — um bloco, \\ entre passos, \) só no final:
\(3x + 5 = 20 \\
3x = 15 \\
x = 5\)
```

| Próximo segmento | Ação |
|---|---|
| Outro passo matemático | Não fecha — `\\` e continua no mesmo bloco |
| Texto / nova frase | Fecha `\)`, depois `\(\\\)` como separador |

**Why:** `\(\\\)` é separador **texto↔math**; usá-lo entre dois blocos math cria
um "bloco vazio" desnecessário.

**Exceção — texto no meio:** os blocos se separam naturalmente.

```text
\(a^2 + b^2 = c^2\) \(\\\)
Substituindo \(a=3\) e \(b=4\): \(\\\)
\(9 + 16 = c^2 \\
c = \sqrt{25} = 5\)
```

---

## 6. Regra de quebra por "passos" (cadeias de igualdade)

Numa cadeia `A = B = C = D`, cada pedaço separado por `=` é um **passo**. Evite
os dois extremos: linha larga demais e fração/número órfão. Ordem de prioridade:

1. **~2 passos por linha.**
2. **Passo "pesado" sozinho na linha** (fração aninhada, raiz dentro de fração,
   vários termos com potências) — com o `=` que o introduz.
3. **Nunca uma fração ou número sozinho numa linha.** Se a quebra deixaria a
   fração isolada, **não quebre ali** — junte ao passo vizinho.
4. **`=` no fim da linha**; o resultado final nunca vai sozinho.

**Conflito entre (1) e (3): vence (3)** — prefira linha mais larga a fração órfã.

```text
ERRADO — fração órfã:
\(\operatorname{tg}(2\alpha) = \dfrac{2 \cdot \dfrac{3}{4}}{1 - \dfrac{9}{16}} = \\
\dfrac{\dfrac{6}{4}}{\dfrac{7}{16}} = \\          ← fração sozinha, proibido
\dfrac{6}{4} \cdot \dfrac{16}{7} = \dfrac{24}{7}\)

CORRETO — passo pesado sozinho; os demais juntos:
\(\operatorname{tg}(2\alpha) = \dfrac{2 \cdot \dfrac{3}{4}}{1 - \dfrac{9}{16}} = \\
\dfrac{\dfrac{6}{4}}{\dfrac{7}{16}} = \dfrac{6}{4} \cdot \dfrac{16}{7} = \dfrac{24}{7}\)
```

Expressões curtas ficam numa só linha — não quebre `12 + 5 = 17`.

---

## 7. Convenções de notação

| Item | Convenção |
|---|---|
| Fração | `\dfrac{n}{d}` (nunca `n/d`) |
| Decimais | vírgula com `{,}` — ex.: `13{,}5` |
| Operadores nomeados | `\operatorname{}` — `\operatorname{MMC}`, `\operatorname{MDC}`, `\operatorname{sen}`, `\operatorname{tg}` |
| Resposta em destaque | `\mathbf{...}` — **só** na linha Resposta (ver §9) |
| Grupo negativo | `\left( ... \right)` quando o valor é negativo |
| Unidade física | `\,\text{}` — ex.: `5\,\text{cm}`, `9{,}8\,\text{m/s}^2` |
| Dinheiro | "R$" **fora** do math — ex.: `R$ \(150{,}00\)` |
| Vários valores na mesma linha | `\quad` entre eles dentro do `\(…\)` |
| Espaço fino em math | `\;` |

```text
Unidade:        \(v = 20\,\text{m/s}\)
Dados na linha: \(|B| = 7, \quad |A \cup B| = 12, \quad |A \cap B| = 3\)
```

---

## 8. Tabelas (`\begin{array}`)

### MMC / MDC — decomposição em fatores primos

`{r r|l}` para 2 números, `{r r r|l}` para 3. Cada passo: `a, & b & divisor\\`;
`\hline` ao final; última linha com a fatoração. No **MDC**, as linhas cujo
divisor divide **todos** os números levam `\textcolor{iris}{...}`.

```text
Decompondo simultaneamente: \(\\\)
\(\begin{array}[t]{r r|l}
12, & 18 & 2 \\
6,  & 9  & 2 \\
3,  & 9  & 3 \\
1,  & 3  & 3 \\
1,  & 1  & \\
\hline
\end{array}\)
\(\\\)
\(\operatorname{MMC}(12,18) = 2^2 \cdot 3^2 = 36\)
Resposta: \( \mathbf{36} \).
```

### Regra de três — tabela de comparação

`{cc}` (simples) ou `{ccc}` (composta). Cabeçalho com setas `(\downarrow)`
(grandeza direta) ou `(\uparrow)` (inversa); `\hline` após o cabeçalho; `x` na
célula desconhecida. Depois da tabela: a proporção com `\dfrac` e a resolução.

```text
Grandezas diretamente proporcionais: \(\\\)
\(\begin{array}[t]{cc}
\text{kg} (\downarrow) & \text{preço} (\downarrow) \\
\hline
4 & 9 \\
6 & x \\
\end{array}\)
\(\\\)
Montando a proporção: \(\\\)
\(\dfrac{4}{6} = \dfrac{9}{x} \\
4x = 54 \\
x = \dfrac{54}{4} = 13{,}5\)
Resposta: \( \mathbf{13{,}5} \).
```

---

## 9. Linha de resposta final

A resolução **termina** com uma linha de resposta dedicada, com o valor em
negrito. **Sempre precedida do separador `\(\\\)`** para que a resposta fique
isolada na **última linha** (sem isso ela "gruda" no último passo de cálculo):

```text
\(... = valor\)
\(\\\)
Resposta: \( \mathbf{...} \).
```

- **Obrigatório:** uma linha `\(\\\)` imediatamente antes de `Resposta:`. Mesmo
  quando o passo anterior é prosa, mantenha o `\(\\\)` em linha própria.
- O valor deve **coincidir com a alternativa marcada** como correta.
- O negrito (`\mathbf{}`) fica **só nesta linha** — a cadeia de cálculo termina
  no valor **sem** negrito (evita destaque duplicado).
- **Dinheiro:** na linha Resposta, destaque o valor **inteiro, com o `R$`,
  dentro do negrito** — ex.: `Resposta: \( \mathbf{R\$\ 365{,}50} \).`
- **Itens Certo/Errado** ("julgue"): conclua com `Resposta: Certo.` ou
  `Resposta: Errado.`, após explicar o porquê.

---

## 10. Resumo dos tokens (forma crua)

| Efeito | Código |
|---|---|
| Quebra texto↔math | `\(\\\)` |
| Quebra entre passos math (mesmo bloco) | `\\` no fim da linha (sem fechar `\)`) |
| Quebra em cadeia, `=` no fim | `= \\` |
| Math inline | `\(expressão\)` |
| Fração | `\dfrac{n}{d}` |
| Decimal | `{,}` |
| Operador nomeado | `\operatorname{MMC}` |
| Grupo negativo | `\left( ... \right)` |
| Unidade | `\,\text{km}` |
| Vários valores na linha | `\quad` |
| Resposta final | `Resposta: \( \mathbf{...} \).` |

---

## 11. Exemplos completos

### Equação do 1º grau

```text
Isolamos a incógnita \(x\). Passamos o \(5\) para o outro lado e dividimos por \(3\): \(\\\)
\(3x + 5 = 20 \\
3x = 20 - 5 = 15 \\
x = \dfrac{15}{3} = 5\)
Resposta: \( \mathbf{5} \).
```

### Soma de frações (igualar denominadores)

```text
Igualamos os denominadores pelo \(\operatorname{MMC}(2,3) = 6\): \(\\\)
\(\dfrac{1}{2} + \dfrac{1}{3} = \\
\dfrac{3}{6} + \dfrac{2}{6} = \dfrac{5}{6}\)
Resposta: \( \mathbf{\dfrac{5}{6}} \).
```

### Teorema de Pitágoras (com definição das variáveis)

```text
Num triângulo retângulo, \(a\) e \(b\) são os catetos e \(c\) a hipotenusa. Pelo teorema de Pitágoras: \(\\\)
\(c^2 = a^2 + b^2\) \(\\\)
Substituindo \(a = 3\) e \(b = 4\): \(\\\)
\(c^2 = 3^2 + 4^2 \\
c^2 = 9 + 16 = 25 \\
c = \sqrt{25} = 5\)
Resposta: \( \mathbf{5} \).
```

### Equação do 2º grau (Bhaskara)

```text
Identificamos \(a = 1\), \(b = -5\) e \(c = 6\). Calculamos o discriminante: \(\\\)
\(\Delta = b^2 - 4ac = (-5)^2 - 4 \cdot 1 \cdot 6 = \\
25 - 24 = 1\) \(\\\)
Aplicamos a fórmula de Bhaskara: \(\\\)
\(x = \dfrac{-b \pm \sqrt{\Delta}}{2a} = \dfrac{5 \pm 1}{2}\) \(\\\)
\(x_1 = 3 \quad x_2 = 2\)
Resposta: \( \mathbf{3} \) e \( \mathbf{2} \).
```

---

## 12. Pipeline de autoria (gravação no banco)

Como o conteúdo tem `\\` e barras, a gravação preserva os backslashes:

1. Autoria no `_resol_batch.txt` — formato `#Q <id>` seguido das linhas da
   resolução (com `\\` cru). **Sempre via ferramenta Write** — heredoc colapsa
   `\\`.
2. `py plano_resolucao\_resol_apply.py` lê o batch e gera `_resol_apply.sql`
   (`UPDATE ... UNHEX(...)` — o UNHEX preserva os `\\`).
3. Backup do lote (`mysqldump --where="id IN (...)"`) antes de aplicar.
4. Aplicar o SQL e conferir a renderização e a coerência com a alternativa
   correta.

> Conexão local: banco `pratiqueja`, binários em
> `C:\Program Files\MySQL\MySQL Server 8.0\bin\`. Mantenha credenciais fora de
> arquivos versionados.
