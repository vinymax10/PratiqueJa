---
name: feedback-formato-resolucao
description: Como formatar a resolução nos geradores de exercício Java (GeradorExercicio.addResolucao por passo)
metadata:
  type: feedback
---

# Formato de resolução nos geradores de exercício

> **Atualizado para a resolução em parágrafos.** A resolução do `Exercicio` deixou de ser uma
> String única com `\\` embutido e passou a ser uma **lista de parágrafos** (`ParagrafoResolucao`).
> Os geradores **não montam mais `String res` nem chamam `setResolucao(...)`** (removido). Cada passo
> da resolução é uma chamada **`addResolucao(...)`** — um parágrafo, uma linha renderizada.

## Regra principal

- **Um `addResolucao(...)` por passo.** Cada chamada vira um parágrafo independente; a quebra de
  linha entre passos é automática (o modelo junta com `\\` no PDF e `<br/>` no HTML). **Não** existe
  mais token de quebra no fonte.
- Dentro de um passo: texto explicativo fica **fora** de `\(…\)`; expressões matemáticas ficam em
  blocos `\(…\)` individuais inline. Nunca embrulhar um passo inteiro num único `\(…\)` com `\text{}`.
- Math puro → `addResolucao("\\(" + expr + "\\)")`. Prosa (com inline math opcional) →
  `addResolucao("texto … \\(x = 3\\) …")`.
- Nunca passe a expressão já embrulhada redundante: `addResolucao(expr)` quando `expr` já é o passo,
  não `addResolucao("\\(" + jaTemDelimitador + "\\)")`.

**Why:** texto fora do math fica legível e a quebra acontece por parágrafo, sem `\\` frágil dentro de
`\(…\)` (era o bug que quebrava `A^{-1}`, subíndices etc.).

> **Rede de segurança:** `addResolucao(texto)` ainda passa o trecho pelo `ResolucaoSplitter`, que move
> qualquer `\\` solto de dentro de `\(…\)` para o modo texto e fatia em parágrafos. Ou seja, um `\\`
> acidental não quebra o math — mas o **padrão correto é uma chamada por passo, sem `\\` no fonte**.

---

## Helper que devolve os passos: `String[]`

Quando a resolução é montada por um helper (`Problema*`, `Resolucao*`), o helper devolve **`String[]`**
(um passo por elemento) e o gerador itera com `addResolucao`:

```java
// helper
public String[] resolucao()
{
    return new String[] {
        "O ponto ótimo é a abscissa do vértice \\(x_v = \\dfrac{-b}{2a}\\).",
        "Com \\(a = -" + a + "\\) e \\(b = " + b + "\\):",
        "\\(x_v = \\dfrac{-" + b + "}{2 \\cdot (-" + a + ")} = \\dfrac{-" + b + "}{-" + (2*a) + "} = \\mathbf{" + xv + "}\\) " + unidade
    };
}

// gerador
for(String passo : problema.resolucao())
    addResolucao(passo);
```

Cada elemento do array = um `addResolucao` = um parágrafo. Helpers **externos compartilhados**
(`ResolucaoNatural`, `MyExpression`, `ExpressionNC`) podem devolver String com `\\` interno — nesse
caso entregue a um único `addResolucao(...)` e deixe o splitter fatiar.

---

## Quebra de uma cadeia de igualdades longa

Numa cadeia `A = B = C = D`, se cabe confortavelmente (≤ ~50 chars renderizados), mantenha **tudo numa
chamada**:

```java
// curto — uma chamada só
addResolucao("\\(" + s2 + " = " + resultado + "\\)");   // ex.: "12 + 5 = 17"
```

Quando for longa demais, **quebre em chamadas separadas** — cada uma um `\(…\)` completo. **Repita o
lado esquerdo (ou um rótulo)** no início da continuação; **nunca comece um parágrafo com `=`**:

```java
// CORRETO — quebra por chamadas, repetindo o LHS (nada de "=" danglando)
addResolucao("\\(f(" + xv + ") = -" + aFat + xv + "^2 + " + b + " \\cdot " + xv + cNum + "\\)");
addResolucao("\\(f(" + xv + ") = " + t1 + " + " + t2 + cNum + " = \\mathbf{" + yv + "}\\)");
```

```java
// ERRADO — paradigma antigo: um bloco com "= \\" interno (proibido)
res += "\\(" + expSubs + " = \\\\ \\)";
res += "\\(" + resultado + "\\)";
setResolucao(res);   // setResolucao não existe mais
```

### Regra de "passos" para decidir onde quebrar

A *decisão* de onde quebrar continua valendo (muda só o mecanismo: cada linha vira um `addResolucao`).
Pense em **"passos"** = cada pedaço separado por `=`. Em ordem de prioridade:

1. **~2 passos por chamada.** Agrupe cerca de dois passos por linha renderizada.
2. **Passo "pesado" sozinho.** Fração composta/aninhada (`\dfrac` dentro de `\dfrac`), raiz dentro de
   fração, ou vários termos com potências → fica numa chamada própria (com o LHS que o introduz).
3. **Nunca uma fração ou número sozinho numa linha** — se uma quebra deixaria uma fração isolada,
   **não quebre ali**: junte-a ao passo vizinho na mesma chamada.
4. O resultado `\mathbf{…}` nunca vai sozinho — fica junto do passo que o gera.

**Conflito entre (1) e (3): a regra (3) vence** — prefira uma linha mais larga a deixar fração órfã.

**Medida de largura (proxy):** dígito/símbolo/operador ≈ 1; `\dfrac{a}{b}` conta como o **maior** entre
numerador e denominador (empilha, não soma); `\sqrt{x}` ≈ x+1; nome de função (`\cos`,
`\operatorname{sen}`) ≈ 3. Mira ~20–30 unidades por chamada.

```java
// ERRADO — cadeia inteira numa chamada só (larga demais)
addResolucao("\\(\\cos(2 \\cdot 60°) = \\left(\\dfrac{1}{2}\\right)^2 - \\left(\\dfrac{\\sqrt{3}}{2}\\right)^2 = \\dfrac{1}{4} - \\dfrac{3}{4} = \\mathbf{-\\dfrac{1}{2}}\\)");

// CORRETO — ~2 passos por chamada, repetindo o LHS na continuação
addResolucao("\\(\\cos(2 \\cdot 60°) = \\left(\\dfrac{1}{2}\\right)^2 - \\left(\\dfrac{\\sqrt{3}}{2}\\right)^2\\)");
addResolucao("\\(\\cos(2 \\cdot 60°) = \\dfrac{1}{4} - \\dfrac{3}{4} = \\mathbf{-\\dfrac{1}{2}}\\)");
```

---

## Matriz / array: `\\` interno fica onde está

O único `\\` legítimo no fonte é o **interno de um ambiente** (`\begin{bmatrix}…\\…\end{bmatrix}`,
`array`, `cases`, `aligned`) — ele separa linhas do ambiente, **não** é separador de passo. Esse passo
inteiro (com o ambiente) vai numa **única** chamada `addResolucao("\\(\\begin{bmatrix}…\\end{bmatrix}\\)")`.
O splitter preserva esses `\\`.

---

## Passos intermediários em expressões complexas

Não saltar etapas de cálculo. Mostrar cada grupo avaliado antes de combinar — **um `addResolucao` por etapa**.

### Padrão para expressão com parênteses: `(A op1 B) op2 (C op3 D)`

```java
String op1 = Algebra.sinalPlusMinus();
String op2 = Algebra.sinal();

int g1 = op1.equals("+") ? A + B : A - B;
int g2 = op3.equals("+") ? C + D : C - D;
String g1Str = g1 < 0 ? "\\left(" + g1 + "\\right)" : "" + g1;
String g2Str = g2 < 0 ? "\\left(" + g2 + "\\right)" : "" + g2;
String step2 = g1Str + " " + Algebra.converter(op2) + " " + g2Str;

addResolucao("Substituindo na expressão:");
addResolucao("\\(" + expSubs + "\\)");                                    // com grupos expandidos
addResolucao("\\(" + step2 + " = " + resultado.toStringLatex() + "\\)");  // grupos avaliados
```

### Padrão para `*/÷` antes de `+/-` (nível 1)

```java
boolean hasMulDiv = false;
for(String op : operadores)
    if(op.equals("*") || op.equals("/")) { hasMulDiv = true; break; }

if(hasMulDiv)
{
    addResolucao("\\(" + textoValores + "\\)");
    addResolucao("\\(" + inter + " = " + resultado.toStringLatex() + "\\)");  // produtos/divisões colapsados
}
else
    addResolucao("\\(" + textoValores + " = " + resultado.toStringLatex() + "\\)");
```

### Padrão para potência antes de multiplicar (nível 1, Expressao2)

```java
// s1 : expressão com potência   (ex.: "3·2² + 4·2 - 1")
// s1b: potência computada        (ex.: "3·4 + 4·2 - 1")
// s2 : todos os produtos feitos  (ex.: "12 + 8 - 1 = 19")
addResolucao("\\(" + s1 + "\\)");
addResolucao("\\(" + s1b + "\\)");
addResolucao("\\(" + s2 + " = " + resultado + "\\)");
```

### Grupos negativos entre parênteses

```java
String gStr = g < 0 ? "\\left(" + g + "\\right)" : "" + g;
```

---

## Frações: `\dfrac{}{}`

### Na resolução

Usar `resultado.toStringLatex()` em vez de `"" + resultado` quando o resultado for um `Racional`:

```java
// ERRADO
addResolucao("\\(" + textoValores + " = " + resultado + "\\)");            // imprime "18/7"

// CORRETO
addResolucao("\\(" + textoValores + " = " + resultado.toStringLatex() + "\\)");  // imprime "\dfrac{18}{7}"
```

`toStringLatex()` retorna `\dfrac{n}{d}` para frações e `"n"` para inteiros (denominador 1).

### Nas alternativas

Usar o overload `gerarAlternativas(Racional)` em vez de `gerarAlternativas("" + resultado)`:

```java
gerarAlternativas(resultado);   // produz alternativas com \dfrac{18}{7}
```

O overload em `GeradorExercicio`:
```java
protected void gerarAlternativas(Racional resultado)
{
    if(resultado == null) return;
    resultado.fatoracao(2);   // simplifica in-place antes de gerar distratores
    gerarAlternativas(resultado.toString());
}
```

`fatoracao(2)` simplifica a fração antes de gerar os distratores e também beneficia chamadas a
`toStringLatex()` posteriores na mesma execução de `construir()`.

---

## Alternativas para resultados algébricos (strings com variável)

Quando o resultado não é numérico (ex.: `"3a - 2"`), `gerarAlternativas(String)` falha silenciosamente.
Nesse caso, gerar distratores manualmente variando os coeficientes:

```java
Set<String> usados = new HashSet<>();
usados.add(resultadoCorreto);
List<String> distratores = new ArrayList<>();
int[] deltas = {1, -1, 2, -2, 3, -3, 4, -4};

for(int dx : deltas)   // varia coeficiente da variável
{
    if(distratores.size() >= 3) break;
    long cx = x.numerador + dx;
    if(cx == 0) continue;
    String alt = buildResultado(cx, naoX.numerador, variavel);
    if(usados.add(alt)) distratores.add(alt);
}
for(int dc : deltas)   // varia a constante, se faltar
{
    if(distratores.size() >= 3) break;
    String alt = buildResultado(x.numerador, naoX.numerador + dc, variavel);
    if(usados.add(alt)) distratores.add(alt);
}

List<String> distrLatex = new ArrayList<>();
for(String d : distratores) distrLatex.add("\\(" + d + "\\)");
embaralharEAdicionarAlternativas("\\(" + resultadoCorreto + "\\)", distrLatex);
```

---

## Resposta final em `\mathbf{}`

O último bloco math da resolução deve sempre envolver o resultado em `\mathbf{}`:

```java
// Correto
addResolucao("\\(|A| = " + x + " + " + y + " = \\mathbf{" + resultado + "}\\)");
addResolucao("\\(" + val + " \\times 1000 = \\mathbf{" + resultado + "}\\,\\text{m}\\)");

// Errado — resultado sem destaque
addResolucao("\\(|A| = " + x + " + " + y + " = " + resultado + "\\)");
```

---

## Unidades físicas dentro de math: `\,\text{}`

Usar `\,` (espaço fino) antes da unidade e `\text{}` para o nome da unidade. Nunca escrever a unidade
em texto simples colada a um número dentro de uma fórmula:

```java
// Correto
addResolucao("\\(" + val + "\\,\\text{km}\\)");
addResolucao("\\(" + val + "\\,\\text{m}^2\\)");
addResolucao("\\(" + val + "\\,\\text{kg/m}^3\\)");
```

Operações descritas em texto introdutório usam `\(…\)` inline para os fatores — e a quebra vira uma
nova chamada:

```java
// frase de texto seguida de fórmula (duas chamadas, sem token de quebra)
addResolucao("\\(1\\,\\text{km} = 1000\\,\\text{m}\\), logo multiplicar por 1000:");
addResolucao("\\(" + val + " \\times 1000 = \\mathbf{" + resultado + "}\\,\\text{m}\\)");

// operador descrito em texto com inline math
addResolucao("Converter km para m (\\(\\times 1000\\)) e h para s (\\(\\times 3600\\)):");
addResolucao("\\(\\dfrac{" + kmh + "\\,\\text{km}}{\\text{h}} = \\dfrac{" + ms + "\\,\\text{m}}{3600\\,\\text{s}} = \\mathbf{" + ms + "}\\,\\text{m/s}\\)");
```

---

## Vários valores conhecidos numa linha: `\quad`

Quando uma chamada lista múltiplos valores conhecidos, usar `\quad` para separá-los dentro do mesmo
bloco math:

```java
addResolucao("\\(|B| = " + b + ", \\quad |A \\cup B| = " + aUb + ", \\quad |A \\cap B| = " + aIb + "\\)");
```

---

## Caixa de fórmula de referência (ParCor)

Quando a resolução começa com uma fórmula de referência colorida (ex.: `ParCor.formula(…)`), ela é o
**primeiro passo** — uma chamada própria — e as etapas seguintes são chamadas subsequentes:

```java
// formulaMenos() retorna: \definecolor{…}\textcolor{azulEscuro}{|A-B|=|A|-|A∩B|}
addResolucao("\\(" + formulaMenos() + "\\)");
addResolucao("\\(|A-B| = " + aMb + ", \\quad |A \\cap B| = " + aIb + "\\)");
addResolucao("\\(|A| - " + aIb + " = " + aMb + "\\)");
addResolucao("\\(|A| = " + aMb + " + " + aIb + " = \\mathbf{" + a + "}\\)");
```

---

## Rótulo em math, descrição em texto corrido

Quando um rótulo curto é matemático mas o valor associado é texto descritivo (ex.: nome de conjunto),
colocar o rótulo em `\(…\)` e a descrição em texto normal — cada rótulo+descrição é uma chamada:

```java
addResolucao("\\(A =\\) " + descricaoA);
addResolucao("\\(B =\\) " + descricaoB);
// ... etapas seguintes
```

---

## Resumo dos tokens em Java

| Efeito desejado | Como fazer |
|---|---|
| Quebra entre passos (texto↔math ou math↔math) | **chamadas `addResolucao` separadas** (não há token) |
| Continuar uma cadeia de igualdades quebrada | nova chamada **repetindo o LHS/rótulo** (nunca iniciar com `=`) |
| Passo de math inline simples | `addResolucao("\\(expressão\\)")` |
| Passo de prosa (com inline math opcional) | `addResolucao("texto … \\(x\\) …")` |
| `\\` de matriz/ambiente | interno a `\\(\\begin{…}…\\\\…\\end{…}\\)`, numa única chamada |
| `\dfrac{n}{d}` (fração) | `resultado.toStringLatex()` |
| Grupo negativo com parênteses | `"\\left(" + g + "\\right)"` (quando `g < 0`) |
| `\;` (espaço fino em math) | `\\;` dentro de `\\(…\\)` |
| Espaço fino antes de unidade | `\\,` antes de `\\text{unidade}` |
| Unidade física em math | `\\,\\text{km}`, `\\,\\text{m}^2`, `\\,\\text{kg/m}^3` |
| Resultado final em negrito | `\\mathbf{" + resultado + "}` no último bloco |
| Vários valores conhecidos na mesma linha | `\\quad` entre eles dentro de `\\(…\\)` |
| Rótulo math + descrição em texto | `addResolucao("\\(A =\\) " + descricaoTexto)` |

---

## Exemplo completo: expressão com dois grupos

```java
String op1 = Algebra.sinalPlusMinus();   // entre A e B
String op2 = Algebra.sinal();            // entre grupos
String op3 = Algebra.sinalPlusMinus();   // entre C e D
String exp = "( A " + op1 + " B ) " + op2 + " ( C " + op3 + " D )";

// [... gerar coeficientes, calcular resultado ...]

int g1 = op1.equals("+") ? A + B : A - B;
int g2 = op3.equals("+") ? C + D : C - D;
String g1Str = g1 < 0 ? "\\left(" + g1 + "\\right)" : "" + g1;
String g2Str = g2 < 0 ? "\\left(" + g2 + "\\right)" : "" + g2;
String step2 = g1Str + " " + Algebra.converter(op2) + " " + g2Str;

addResolucao("Substituindo na expressão:");
addResolucao("\\(" + expSubs + "\\)");
addResolucao("\\(" + step2 + " = " + resultado.toStringLatex() + "\\)");
gerarAlternativas(resultado);   // Racional overload — usa \dfrac nas alternativas
```
