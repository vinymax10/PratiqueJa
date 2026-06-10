---
name: feedback-formato-resolucao
description: Como formatar strings de resolução nos geradores de exercício Java (GeradorExercicio)
metadata:
  type: feedback
---

# Formato de resolução nos geradores de exercício

## Regra principal

Texto explicativo fica **fora** de `\(…\)`. Expressões matemáticas ficam em blocos `\(…\)` individuais inline. Nunca embrulhar toda a resolução num único `\(…\)` com `\text{}`.

Chamar `setResolucao(res)` diretamente — nunca `setResolucao("\\(" + res + "\\)")`.

**Why:** Fica mais legível, a quebra de linha acontece naturalmente no texto e só é forçada onde é necessário.

---

## Dois tokens de quebra de linha

### 1. `\(\\\)` — quebra entre bloco de texto e bloco math

Código Java: `\\(\\\\\\)`. Usado **entre** segmentos de texto/fórmula distintos.

| Situação | Exemplo Java |
|---|---|
| Após frase terminada em `:` seguida de fórmula | `"Substituindo \\(x = 3\\) na expressão: \\(\\\\\\)"` |
| Após fórmula antes de próxima etapa de texto | `"\\(d = \\sqrt{25}\\). \\(\\\\\\)"` |
| Após enunciar regra antes de aplicar ao caso | `"\\(P(a,b) \\to P'(a,-b)\\). \\(\\\\\\)"` |

### 2. `= \\ ` — quebra dentro de `\(…\)`, cadeia de igualdades

Código Java: `= \\\\ ` (espaço após). Usado **dentro** de um bloco math para quebrar uma cadeia longa de igualdades.

```java
// Correto: = ao final da linha, quebra dentro do bloco
res += "\\(" + expSubs + " = \\\\ \\)";
res += "\\(" + step2 + " = " + resultado + "\\)";
```

**Nunca** abrir um novo bloco math começando com `=`:
```java
// ERRADO — = no início da linha seguinte
res += "\\(" + expSubs + "\\) \\(\\\\\\)";
res += "\\(= " + resultado + "\\)";   // ← proibido

// CORRETO — = ao final da linha anterior
res += "\\(" + expSubs + " = \\\\ \\)";
res += "\\(" + resultado + "\\)";
```

---

## Quando quebrar vs. quando manter na mesma linha

Só quebrar se a expressão for longa. Expressões curtas ficam numa única linha:

```java
// Desnecessário — expressão curta
res += "\\(" + s2 + " = \\\\ \\)";
res += "\\(" + resultado + "\\)";   // ← evitar para "12 + 5 = 17"

// Correto — tudo na mesma linha
res += "\\(" + s2 + " = " + resultado + "\\)";
```

**Critério:** se a linha com `= resultado` cabe confortavelmente (≤ ~50 chars renderizados), não quebrar.

---

## Passos intermediários em expressões complexas

Não saltar etapas de cálculo. Mostrar cada grupo avaliado antes de combinar.

### Padrão para expressão com parênteses: `(A op1 B) op2 (C op3 D)`

```java
// Capturar operadores ANTES de montar a string exp
String op1 = Algebra.sinalPlusMinus();
String op2 = Algebra.sinal();

// Calcular valor de cada grupo
int g1 = op1.equals("+") ? A + B : A - B;
int g2 = op3.equals("+") ? C + D : C - D;
String g1Str = g1 < 0 ? "\\left(" + g1 + "\\right)" : "" + g1;
String g2Str = g2 < 0 ? "\\left(" + g2 + "\\right)" : "" + g2;
String step2 = g1Str + " " + Algebra.converter(op2) + " " + g2Str;

String res = "Substituindo na expressão: \\(\\\\\\)";
res += "\\(" + expSubs + " = \\\\ \\)";      // com grupos expandidos
res += "\\(" + step2 + " = " + resultado.toStringLatex() + "\\)";  // grupos avaliados
setResolucao(res);
```

### Padrão para `*/÷` antes de `+/-` (nível 1)

Verificar se há multiplicação/divisão e mostrar o resultado parcial antes de somar:

```java
boolean hasMulDiv = false;
for(String op : operadores)
    if(op.equals("*") || op.equals("/")) { hasMulDiv = true; break; }

if(hasMulDiv)
{
    // construir inter: colapsa os produtos/divisões, mantém somas/subtrações
    res += "\\(" + textoValores + " = \\\\ \\)";
    res += "\\(" + inter + " = " + resultado.toStringLatex() + "\\)";
}
else
    res += "\\(" + textoValores + " = " + resultado.toStringLatex() + "\\)";
```

### Padrão para potência antes de multiplicar (nível 1, Expressao2)

Mostrar o passo `x² → valor` antes de multiplicar:

```java
// s1: expressão com potência  (ex.: "3·2² + 4·2 - 1")
// s1b: potência computada     (ex.: "3·4 + 4·2 - 1")
// s2: todos os produtos feitos (ex.: "12 + 8 - 1 = 19")
res += "\\(" + s1 + " = \\\\ \\)";
res += "\\(" + s1b + " = \\\\ \\)";
res += "\\(" + s2 + " = " + resultado + "\\)";
```

### Grupos negativos entre parênteses

Quando um grupo avaliado é negativo, envolver em `\left(\right)` para não confundir com o operador:

```java
String gStr = g < 0 ? "\\left(" + g + "\\right)" : "" + g;
```

---

## Frações: `\dfrac{}{}`

### Na resolução

Usar `resultado.toStringLatex()` em vez de `"" + resultado` quando o resultado for um `Racional`:

```java
// ERRADO
res += "\\(" + textoValores + " = " + resultado + "\\)";    // imprime "18/7"

// CORRETO
res += "\\(" + textoValores + " = " + resultado.toStringLatex() + "\\)";  // imprime "\dfrac{18}{7}"
```

`toStringLatex()` retorna `\dfrac{n}{d}` para frações e `"n"` para inteiros (denominador 1).

### Nas alternativas

Usar o overload `gerarAlternativas(Racional)` em vez de `gerarAlternativas("" + resultado)`:

```java
// ERRADO — produz alternativas com "18/7" em texto
gerarAlternativas("" + resultado);

// CORRETO — produz alternativas com \dfrac{18}{7}
gerarAlternativas(resultado);
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

A chamada a `fatoracao(2)` simplifica a fração antes de gerar os distratores e também beneficia eventuais chamadas a `toStringLatex()` posteriores na mesma execução de `construir()`.

---

## Alternativas para resultados algébricos (strings com variável)

Quando o resultado não é numérico (ex.: `"3a - 2"`), `gerarAlternativas(String)` falha silenciosamente e exibe apenas uma alternativa. Nesse caso, gerar distratores manualmente variando os coeficientes:

```java
Set<String> usados = new HashSet<>();
usados.add(resultadoCorreto);
List<String> distratores = new ArrayList<>();
int[] deltas = {1, -1, 2, -2, 3, -3, 4, -4};

// variar coeficiente da variável
for(int dx : deltas)
{
    if(distratores.size() >= 3) break;
    long cx = x.numerador + dx;
    if(cx == 0) continue;
    String alt = buildResultado(cx, naoX.numerador, variavel);
    if(usados.add(alt)) distratores.add(alt);
}
// variar constante, se necessário completar
for(int dc : deltas)
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

## Resumo dos tokens em Java

| Efeito desejado | Código Java |
|---|---|
| Quebra entre texto e fórmula (blocos separados) | `\\(\\\\\\)` |
| Quebra dentro de `\(…\)` com `=` ao final | `= \\\\ ` (espaço depois) |
| Inline math simples | `\\(expressão\\)` |
| `\dfrac{n}{d}` (fração) | `resultado.toStringLatex()` |
| Grupo negativo com parênteses | `"\\left(" + g + "\\right)"` (quando `g < 0`) |
| `\;` (espaço fino em math) | `\\;` dentro de `\\(…\\)` |

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

String res = "Substituindo na expressão: \\(\\\\\\)";
res += "\\(" + expSubs + " = \\\\ \\)";
res += "\\(" + step2 + " = " + resultado.toStringLatex() + "\\)";
setResolucao(res);
gerarAlternativas(resultado);   // Racional overload — usa \dfrac nas alternativas
```
