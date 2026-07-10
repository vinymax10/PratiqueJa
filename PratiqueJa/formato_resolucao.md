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

> **Nunca deixe raciocínio de rascunho num `addResolucao`.** Nada de "espera...", "na verdade..." ou
> qualquer autocorreção visível — o texto gerado é o que o aluno lê (ver seção "Lições da revisão
> pós-Fase 7" no fim deste documento).

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

### `\frac` para frações pequenas

`toStringLatex()` sempre produz `\dfrac{n}{d}` — esse é o padrão quando a fração aparece **no nível da
linha**. Se a fração precisa ficar **pequena** por estar dentro de um expoente, subscrito, aninhada no
numerador/denominador de outra fração, ou dentro de uma raiz, monte a string manualmente com
`\frac{n}{d}` em vez de `toStringLatex()`. Nunca gerar uma fração como texto puro (`"n/d"`).

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

Para unidade ao quadrado/cúbica, o expoente vai **fora** de `\text{}` (`\,\text{m}^2`, nunca `"m2"` ou
`"m²"` colado no texto):

```java
// ERRADO — unidade ao quadrado como texto puro
addResolucao("A área é \\(" + area + "\\) m2.");

// CORRETO
addResolucao("A área é \\(" + area + "\\,\\text{m}^2\\).");
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

## Letras gregas e raiz

Letra grega sempre via comando LaTeX dentro de `\(…\)` — `\pi`, `\alpha`, `\beta`, `\gamma`, `\delta`,
`\varepsilon`, `\theta`, `\sigma`, `\mu`, `\phi`, `\omega`, `\Delta`, `\Lambda`... — **nunca** concatenar
o caractere unicode (`π`, `σ`...) numa `String` Java. Raiz sempre `\sqrt{}` — **nunca** o caractere `√`.

```java
// ERRADO
addResolucao("\\(A = " + raio + "² × π\\)");

// CORRETO
addResolucao("\\(A = " + raio + "^2 \\times \\pi\\)");
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
| `\dfrac{n}{d}` (fração, nível da linha) | `resultado.toStringLatex()` |
| `\frac{n}{d}` (fração pequena — expoente/subscrito/raiz/aninhada) | montar string manualmente, não usar `toStringLatex()` |
| Grupo negativo com parênteses | `"\\left(" + g + "\\right)"` (quando `g < 0`) |
| `\;` (espaço fino em math) | `\\;` dentro de `\\(…\\)` |
| Espaço fino antes de unidade | `\\,` antes de `\\text{unidade}` |
| Unidade física em math | `\\,\\text{km}`, `\\,\\text{m}^2`, `\\,\\text{kg/m}^3` (nunca `"m2"` em texto puro) |
| Letra grega | `\\pi`, `\\alpha`, `\\theta`... (nunca unicode `π`, `α`...) |
| Raiz | `\\sqrt{}` (nunca `√`) |
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

---

## Lições da revisão pós-Fase 7 (2026-07)

Uma revisão dedicada de 516 questões geradas (feita depois da migração para `addResolucao` por passo)
encontrou padrões que passavam despercebidos por não violarem nenhuma regra explícita até então. As
quatro lições abaixo valem tanto para geradores Java quanto para resoluções autoradas à mão
(`formato_resolucao_questao.md` §13 — mesmo texto, adaptado para `addResolucao`).

### "logo" não substitui a quebra de passo

Encadear **dois estados de equação diferentes** no mesmo `addResolucao` usando "logo" esconde a
substituição do aluno — o mesmo problema que motivou abandonar `res += "\\(...\\\\)"` no paradigma antigo.

```java
// ERRADO — duas equações coladas por "logo" na mesma chamada
addResolucao("\\(3(2a+17)=6a+51\\), logo \\(a+6a+51=9\\).");

// CORRETO — fato (avaliação) e nova equação em chamadas separadas
addResolucao("\\(3(2a+17)=6a+51\\).");
addResolucao("\\(a+6a+51=9\\).");
```

**Exceções — não separar** (não há substituição escondida, cabe numa chamada só): mesma operação
explícita ("Multiplicando por \(4\): \(8n=3n+30\)."), mesma expressão avaliada em etapas sem introduzir
equação alheia, conclusão final simples ("logo \(x=5\)."), raciocínio narrativo trivial, leitura direta
de um dígito/coeficiente já dado, ou definição/identidade aplicada diretamente (ex.: "\(\log_4 b=y\),
logo \(b=4^y\)." — definição de logaritmo).

Quando o "logo" é só a **substituição de um valor já derivado** sem frase explicativa, prefira
**reescrever nomeando a operação** em vez de separar:

```java
// ERRADO
addResolucao("\\(2a+8{,}5=98{,}1/9\\), logo \\(2a+8{,}5=10{,}9\\).");

// CORRETO — operação nomeada, sem "logo"
addResolucao("Dividindo por \\(9\\): \\(2a+8{,}5=\\dfrac{98{,}1}{9}=10{,}9\\).");
```

### Desenvolvimento de termo/lado isolado precisa de narração antes

Quando um passo sai do fluxo principal para desenvolver um termo ou lado da equação sozinho, o passo
**anterior** precisa dizer o quê e por quê — senão o próximo passo "aparece do nada":

```java
// ERRADO — o termo (n+2)^2 aparece sem contexto
addResolucao("\\((n+4)^2=n^2+8n+16\\).");
addResolucao("\\((n+2)^2=n^2+4n+4\\).");

// CORRETO — narração antes de cada termo desenvolvido isoladamente
addResolucao("Vamos desenvolver cada termo desta equação separadamente. Primeiro, o lado esquerdo \\((n+4)^2\\):");
addResolucao("\\((n+4)^2=n^2+2\\cdot n\\cdot4+4^2=n^2+8n+16\\).");
addResolucao("Agora o termo \\((n+2)^2\\), do lado direito:");
addResolucao("\\((n+2)^2=n^2+2\\cdot n\\cdot2+2^2=n^2+4n+4\\).");
```

A evolução mecânica em si **pode ficar direta** (fórmula + resultado na mesma chamada) — o que não pode
faltar é a chamada anterior anunciando a mudança de foco.

### Múltiplas incógnitas/entradas resolvidas em sequência: cada uma (a partir da 2ª) precisa de transição

O mesmo problema aparece quando o gerador resolve **mais de uma incógnita ou entrada** em sequência: a
primeira costuma fluir naturalmente da equação inicial, mas as seguintes não podem só aparecer:

```java
// ERRADO — a equação de "y" aparece sem aviso
addResolucao("Para \\(A+B=I\\): \\(x+2=1\\Rightarrow x=-1\\).");
addResolucao("\\(y+4=0\\Rightarrow y=-4\\).");

// CORRETO — cada entrada seguinte tem sua transição
addResolucao("Para \\(A+B=I\\): \\(x+2=1\\Rightarrow x=-1\\).");
addResolucao("Para \\(y\\):");
addResolucao("\\(y+4=0\\Rightarrow y=-4\\).");
```

Frases curtas resolvem: `"Para \\(y\\):"`, `"Da segunda operação:"`, `"Para a segunda raiz:"`. Quando
**todas** as raízes/soluções já foram anunciadas juntas antes de ramificar (ex.: "as raízes são
\(u=5\) ou \(u=25\)"), a ramificação seguinte não precisa de transição própria.

### Nunca deixar raciocínio de rascunho na resolução

Texto como `"— espera, ..."`, `"na verdade, ..."` é sinal de autocorreção que **não deveria ir para a
resolução salva**. Revise o `String[]`/`addResolucao` finais e garanta que só o raciocínio limpo entra:

```java
// ERRADO
addResolucao("\\(8\\times2=16\\) — espera: os 10 vendidos são a parte de 5, logo 8+5=13 partes? Na verdade, a razão vendidos/total = 8:5, e 1 parte = 2; total = 8 partes = 16.");

// CORRETO
addResolucao("A razão entre o total de veículos e os vendidos é \\(8:5\\).");
addResolucao("Os vendidos correspondem à parte \"5\": \\(5\\) partes \\(=10\\) veículos, ou seja \\(1\\) parte \\(=2\\).");
addResolucao("O total corresponde à parte \"8\": \\(8\\times2=\\mathbf{16}\\).");
```
