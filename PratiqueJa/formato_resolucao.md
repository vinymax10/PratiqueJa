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

**Why:** Fica mais legível, a quebra de linha acontece naturalmente no texto e só é forçada onde é necessário (dentro do matemático).

---

## Quebra de linha entre blocos: `\(\\\)`

Para separar visualmente parágrafos ou etapas da resolução, inserir o token `\\(\\\\\\)` no Java (renderiza como `\(\\\)`), que força uma quebra de linha.

### Quando usar `\(\\\)`

| Situação | Exemplo Java |
|---|---|
| Após frase que termina com `:` e é seguida de fórmula | `"Pelo Teorema de Pitágoras: \\(\\\\\\)"` |
| Após fórmula (`.`) antes da próxima etapa de texto | `"\\(d = \\sqrt{x^2+y^2}\\). \\(\\\\\\)"` |
| Após leitura de dados do gráfico antes de calcular | `"...lemos \\(x = 3\\) e \\(y = 4\\). \\(\\\\\\)"` |
| Após enunciar a regra geral antes de aplicar ao caso concreto | `"\\(P(a,b) \\to P'(a,-b)\\). \\(\\\\\\)"` |

### Quando NÃO usar `\(\\\)`

- Na última linha da resolução (não colocar quebra depois do resultado final).
- Entre frases de prosa contínua que não introduzem fórmula.

### Exemplo completo (distância à origem)

```java
String res = "A distância de um ponto \\(P(x,\\;y)\\) à origem é a hipotenusa ";
res += "do triângulo retângulo cujos catetos são \\(|x|\\) e \\(|y|\\). ";
res += "Pelo Teorema de Pitágoras: \\(\\\\\\) ";
res += "\\(d(P,\\;O) = \\sqrt{x^2 + y^2}\\). \\(\\\\\\)";
res += "Substituindo \\(x = " + px + "\\) e \\(y = " + py + "\\): \\(\\\\\\)";
res += "\\(d = \\sqrt{" + strPx + "^2 + " + strPy + "^2} =\\\\ "
    + "\\sqrt{" + px2 + " + " + py2 + "} = \\sqrt{" + soma + "} = " + dist + "\\).";
setResolucao(res);
```

### Exemplo completo (simetria)

```java
res = "A simetria em relação ao eixo \\(x\\) mantém a abscissa e inverte a ordenada: \\(\\\\\\)";
res += "\\(P(a,\\;b) \\to P'(a,\\;{-b})\\). \\(\\\\\\)";
res += "Do plano, identificamos \\(P = (" + a + ",\\;" + b + ")\\). \\(\\\\\\)";
res += "Aplicando a regra: \\(P' = (" + rx + ",\\;" + ry + ")\\).";
setResolucao(res);
```

---

## Quebra de linha dentro de `\(…\)`: `=\\`

Para cadeias de igualdades longas (4+ membros), quebrar na metade com `=\\\\ ` no Java (renderiza como `=\\` dentro do math).

Quebrar **após** a etapa de substituição de valores, antes de calcular:

```java
// d = √(px² + py²) =\\ √(px2 + py2) = √d2
res += "\\(d = \\sqrt{" + strPx + "^2 + " + strPy + "^2} =\\\\ "
    + "\\sqrt{" + px2 + " + " + py2 + "} = \\sqrt{" + soma + "}\\).";
```

**How to apply:** Sempre que uma expressão math ultrapasse ~60 caracteres renderizados, quebrar num `=` natural.

---

## Resumo dos tokens em Java

| Efeito desejado | Código Java |
|---|---|
| Quebra entre blocos de texto/fórmula | `\\(\\\\\\)` |
| Quebra dentro de `\(…\)` | `=\\\\ ` (espaço depois) |
| Inline math simples | `\\(expressão\\)` |
| `\;` (espaço fino em math) | `\\;` dentro de `\\(…\\)` |
