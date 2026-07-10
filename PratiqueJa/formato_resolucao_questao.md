---
name: feedback-formato-resolucao-questao
description: Como elaborar e formatar as resoluções das questões (lista de ParagrafoResolucaoQuestao; autoria em bloco + converter _lote_apply.py)
metadata:
  type: feedback
---

# Formato de resolução das questões

> **Modelo de dados (pós-migração).** A resolução da `Questao` deixou de ser uma String única
> (coluna `questao.resolucao`, **removida**) e passou a ser uma **lista de parágrafos**
> (`modelo.questao.ParagrafoResolucaoQuestao`: `texto`, `ordem`, `questao`). Cada parágrafo = uma
> linha renderizada; a quebra entre parágrafos é automática (web `<br/>`, PDF `\\` em modo texto).

> **Como você escreve (NÃO mudou).** Você continua autorando a resolução no **mesmo formato em bloco**
> de sempre — com `\(\\\)` separando linhas, `\\` dentro do math e arrays multilinha. O converter
> **`resolucao_questao/_lote_apply.py`** fatia esse bloco em parágrafos (lógica IDÊNTICA à da migração,
> `ResolucaoSplitter`): **cada linha separada por `\(\\\)` vira um parágrafo armazenado**. Os tokens
> são a **notação de fronteira** que o converter consome — não ficam no banco.
>
> No **editor admin** (tela da questão → "Resolução (parágrafos)") é o contrário: cada caixa é UM
> parágrafo já pronto (o conteúdo de uma "linha", sem o `\(\\\)`).

> **Notação crua (uma barra).** O conteúdo vai direto p/ o banco e é renderizado como está:
> `\dfrac{}{}`, `\(...\)`, `\\` (só em matriz), etc. — sem escape de String. A tabela de tokens está crua.

---

## 1. Princípios didáticos (questões são escritas à mão)

A resolução é **autoral** e precisa ser confiável:

1. **Definir cada variável/grandeza antes de usar.**
2. **Explicar cada passo, sem saltos.** Enuncie a fórmula/teorema/propriedade antes de substituir.
3. **Corrigir erros do original.** Se a resolução/gabarito textual tiver erro factual, corrija.
4. **Conferir a alternativa marcada como correta** e garantir que a resolução chega exatamente nela.
5. **Profissional e didática:** prosa clara, transições explicadas, nada de "pulo do gato".
6. **Nunca salvar raciocínio de rascunho.** A resolução é o texto final para o aluno — nada de
   "espera...", "na verdade...", autocorreções ou hesitação visíveis (ver §13.4).

---

## 2. Regra principal

Texto explicativo fica **fora** de `\(…\)`. Expressões matemáticas ficam em blocos `\(…\)`. Nunca
embrulhar a resolução inteira num único `\(…\)` com `\text{}`.

**Why:** legibilidade e quebra natural — a coluna dupla quebra a prosa sozinha; só o trecho matemático
precisa ser quebrado à mão (em **linhas**, que o converter vira parágrafos).

---

## 3. Largura e o porquê das quebras

Renderizada em **coluna estreita (dupla)** — mire **~40 caracteres por linha/parágrafo**. Texto corrido
**não** leva quebra manual (a apostila quebra). A quebra manual serve **só para o trecho matemático**.

**Medida de largura (proxy):** dígito/símbolo/operador ≈ 1; `\dfrac{a}{b}` conta como o **maior** entre
numerador e denominador (empilha, não soma); `\sqrt{x}` ≈ x+1; função (`\cos`, `\operatorname{sen}`) ≈ 3.
Mire **~20–30 unidades** por linha matemática.

---

## 4. Tokens de fronteira (forma crua) — entrada do converter

### `\(\\\)` — quebra entre um bloco de texto e um bloco matemático (→ novo parágrafo)

Um span de math contendo só `\\`. Usado **entre** segmentos texto↔fórmula. Cada `\(\\\)` marca o **fim
de um parágrafo**.

```text
Substituindo na equação: \(\\\)
\(3x + 5 = 20\)
```

### `\\` — quebra entre passos matemáticos no mesmo bloco

No fim da linha, **sem fechar** `\)`. O converter (via `ResolucaoSplitter`) move esse `\\` para o modo
texto e **separa em parágrafos** — então o efeito final também é um parágrafo por passo.

### `= \\` — quebra dentro de `\(…\)` numa cadeia de igualdades

O `=` fica no **fim** da linha. Nunca comece a linha seguinte com `=`.

```text
CERTO:
\(2x = 20 - 6 = \\
14\)            ← mas não deixe número/fração órfão (ver §6)
```

> **Exceção — matriz/array:** o `\\` **interno** de `\begin{array}…\\…\end{array}` (ou `bmatrix`,
> `cases`, `aligned`) é preservado e a tabela inteira vira **um único parágrafo**. Não confundir com o
> `\\` separador de passos.

---

## 5. Sequência de passos matemáticos — bloco único

Quando vários passos consecutivos são **todos matemáticos** (sem texto entre eles), **não** feche/reabra
`\(…\)` a cada linha. Use **um único bloco** com `\\` separando os passos:

```text
ERRADO — abre/fecha entre cada passo:
\(3x + 5 = 20\) \(\\\)
\(3x = 15\) \(\\\)
\(x = 5\)

CERTO — um bloco, \\ entre passos, \) só no final:
\(3x + 5 = 20 \\
3x = 15 \\
x = 5\)
```

| Próximo segmento | Ação |
|---|---|
| Outro passo matemático | Não fecha — `\\` e continua no mesmo bloco |
| Texto / nova frase | Fecha `\)`, depois `\(\\\)` como separador |

> **Atenção:** "mesmo bloco" é só na **fonte**. Na gravação, o converter fatia TODO `\\` em
> parágrafos separados (um passo por parágrafo) — exceto o `\\` interno de `\begin{array}` (§8),
> que é preservado. Ou seja: escrever um bloco com `\\` ou vários `\(\\\)` dá no mesmo resultado.

**Exceção — texto no meio:** os blocos se separam naturalmente.

---

## 6. Regra de quebra por "passos" (cadeias de igualdade)

Numa cadeia `A = B = C = D`, cada pedaço separado por `=` é um **passo**. Ordem de prioridade:

1. **~2 passos por linha.**
2. **Passo "pesado" sozinho** (fração aninhada, raiz dentro de fração, vários termos com potências) —
   com o `=` que o introduz.
3. **Nunca uma fração/número sozinho numa linha.** Se a quebra deixaria a fração isolada, **não quebre
   ali** — junte ao passo vizinho.
4. **`=` no fim da linha**; o resultado final nunca vai sozinho.

**Conflito (1)×(3): vence (3).** Expressões curtas ficam numa só linha — não quebre `12 + 5 = 17`.

---

## 7. Convenções de notação

| Item | Convenção |
|---|---|
| Fração | `\dfrac{n}{d}` (nunca `n/d`) — ver regra de `\frac` vs `\dfrac` abaixo |
| Decimais | vírgula com `{,}` — ex.: `13{,}5` |
| Operadores nomeados | `\operatorname{}` — `\operatorname{MMC}`, `\operatorname{MDC}`, `\operatorname{sen}`, `\operatorname{tg}` |
| Resposta em destaque | `\mathbf{...}` — **só** no parágrafo Resposta (ver §9) |
| Grupo negativo | `\left( ... \right)` quando o valor é negativo |
| Unidade física | `\,\text{}` — ex.: `5\,\text{cm}`, `9{,}8\,\text{m/s}^2` |
| Unidade ao quadrado/cúbica | `\,\text{m}^2`, `\,\text{cm}^3` — **nunca** texto puro (`m2`, `cm2`, `km2`, `dm2`, `mm2`) |
| Letra grega | comando LaTeX dentro de `\(...\)` — `\pi`, `\alpha`, `\beta`, `\gamma`, `\delta`, `\varepsilon`, `\theta`, `\sigma`, `\mu`, `\phi`, `\omega`, `\Delta`, `\Lambda`... — **nunca** o caractere unicode solto (`π`, `σ`...) |
| Raiz | sempre `\sqrt{}` — **nunca** o caractere `√` |
| Dinheiro | "R$" **fora** do math — ex.: `R$ \(150{,}00\)` |
| Vários valores na mesma linha | `\quad` entre eles dentro do `\(…\)` |
| Espaço fino em math | `\;` |

### `\frac` vs `\dfrac`

`\dfrac{n}{d}` é o padrão sempre que a fração aparece **no nível da linha** (um passo do cálculo, um
valor isolado). `\frac{n}{d}` só é aceitável quando a fração precisa ficar **pequena** por estar:

- dentro de um **expoente** — `x^{\frac{a}{b}}`;
- dentro de um **subscrito**;
- **aninhada** dentro do numerador/denominador de outra fração;
- dentro de uma **raiz** — `\sqrt{\frac{a}{b}}`.

Fração **nunca** em texto puro (`3/4`) — sempre `\dfrac` ou `\frac`, conforme o contexto acima.

---

## 8. Tabelas (`\begin{array}`) — uma tabela = um parágrafo

A tabela inteira (com seus `\\` internos) é **um único parágrafo**; texto introdutório e resposta são
parágrafos à parte (separados por `\(\\\)`).

### MMC / MDC — decomposição em fatores primos

`{r r|l}` (2 números) / `{r r r|l}` (3). Cada linha `a, & b & divisor\\`; `\hline` ao final; última
linha com a fatoração. No **MDC**, linhas cujo divisor divide **todos** levam `\textcolor{iris}{...}`.

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
\(\\\)
Resposta: \( \mathbf{36} \).
```

### Regra de três — tabela de comparação

`{cc}` (simples) ou `{ccc}` (composta). Cabeçalho com setas `(\downarrow)`/`(\uparrow)`; `\hline` após
o cabeçalho; `x` na célula desconhecida.

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
\(\\\)
Resposta: \( \mathbf{13{,}5} \).
```

---

## 9. Parágrafo de resposta final

A resolução **termina** com uma linha de resposta dedicada, valor em negrito. **SEMPRE precedida de um
`\(\\\)` em linha própria** — é o que faz o converter isolar a resposta no **último parágrafo** (sem
isso ela gruda no último passo de cálculo):

```text
\(... = valor\)
\(\\\)
Resposta: \( \mathbf{...} \).
```

- O valor deve **coincidir com a alternativa marcada** como correta.
- `\mathbf{}` fica **só** na linha Resposta — a cadeia de cálculo termina no valor **sem** negrito.
- **Dinheiro:** destaque o valor inteiro, com `R$`, dentro do negrito — `Resposta: \( \mathbf{R\$\ 365{,}50} \).`
- **Itens Certo/Errado:** `Resposta: Certo.` / `Resposta: Errado.`, após explicar.

---

## 10. Resumo dos tokens (forma crua)

| Efeito | Código |
|---|---|
| Quebra texto↔math (→ novo parágrafo) | `\(\\\)` |
| Quebra entre passos math (mesmo bloco) | `\\` no fim da linha (sem fechar `\)`) |
| Quebra em cadeia, `=` no fim | `= \\` |
| `\\` de matriz/ambiente (fica no mesmo parágrafo) | interno a `\(\begin{…}…\\…\end{…}\)` |
| Math inline | `\(expressão\)` |
| Fração (nível da linha) | `\dfrac{n}{d}` |
| Fração pequena (expoente/subscrito/raiz/aninhada) | `\frac{n}{d}` |
| Decimal | `{,}` |
| Operador nomeado | `\operatorname{MMC}` |
| Grupo negativo | `\left( ... \right)` |
| Unidade | `\,\text{km}` |
| Unidade ao quadrado/cúbica | `\,\text{m}^2` |
| Letra grega | `\pi`, `\alpha`, `\theta`... (nunca unicode) |
| Raiz | `\sqrt{}` (nunca `√`) |
| Vários valores na linha | `\quad` |
| Resposta final | `\(\\\)` + `Resposta: \( \mathbf{...} \).` |

---

## 11. Exemplos completos (formato de autoria — o converter fatia em parágrafos)

### Equação do 1º grau

```text
Isolamos a incógnita \(x\). Passamos o \(5\) para o outro lado e dividimos por \(3\): \(\\\)
\(3x + 5 = 20 \\
3x = 20 - 5 = 15 \\
x = \dfrac{15}{3} = 5\)
\(\\\)
Resposta: \( \mathbf{5} \).
```

### Teorema de Pitágoras

```text
Num triângulo retângulo, \(a\) e \(b\) são os catetos e \(c\) a hipotenusa. Pelo teorema de Pitágoras: \(\\\)
\(c^2 = a^2 + b^2\) \(\\\)
Substituindo \(a = 3\) e \(b = 4\): \(\\\)
\(c^2 = 3^2 + 4^2 \\
c^2 = 9 + 16 = 25 \\
c = \sqrt{25} = 5\)
\(\\\)
Resposta: \( \mathbf{5} \).
```

---

## 12. Pipeline de autoria (gravação em parágrafos via converter)

A coluna `questao.resolucao` foi **removida**; a resolução vive em `ParagrafoResolucaoQuestao`. O
pipeline antigo (`UPDATE ... resolucao = UNHEX(...)`) está **aposentado**. Agora:

1. **Buscar** os pendentes e ler enunciado/alternativa/resolução ATUAL (a resolução atual vem dos
   **parágrafos**, não mais da coluna — ver `resolucao_questao/_RETOMAR.md`).
2. **Autorar** `resolucao_questao/_lote_batch.txt` **sempre via ferramenta Write** (heredoc colapsa `\\`),
   no formato em bloco de sempre, uma entrada por questão:
   ```text
   #Q <id> qr=<int> qf=<int> [obs=<texto>]
   <linhas da resolução, com \(\\\)/\\/arrays — formato de sempre>
   #Q <id> qr=<int> qf=<int>
   <...>
   ```
3. **Converter** para SQL (fatia em parágrafos, idêntico à migração; gera ids via
   `paragraforesolucaoquestao_seq` com margem; emite `UPDATE questao` (metadados) + `DELETE`+`INSERT`
   parágrafos + ledger):
   ```bash
   py resolucao_questao/_lote_apply.py resolucao_questao/_lote_batch.txt resolucao_questao/_lote_0NN.sql NN
   ```
4. **Backup** dos 30 ids ANTES de aplicar (agora a tabela de parágrafos):
   ```bash
   mysqldump.exe --no-create-info --skip-extended-insert --where="questao_id IN (...)" \
     pratiqueja ParagrafoResolucaoQuestao > resolucao_questao/_backup/lote_0NN_backup.sql
   ```
5. **Aplicar:** `mysql.exe --default-character-set=utf8mb4 -uroot pratiqueja < resolucao_questao/_lote_0NN.sql`
6. **Verificar:** cada questão com ≥1 parágrafo, `ordem` 0..n-1, nenhum `texto` > 4095, `revisada=1`.
   - Validado: o converter reproduz EXATAMENTE o fatiamento da migração (testado na Q110796: 13 parágrafos).

> O converter só **fatia + grava** (mecânico); o conteúdo continua autorado à mão (regra #1 do projeto).
> Conexão local: banco `pratiqueja`, binários em `C:\Program Files\MySQL\MySQL Server 8.0\bin\`.
> Credenciais fora de arquivos versionados.

---

## 13. Lições da revisão pós-Fase 7 (2026-07)

Depois da migração para parágrafos (Fase 7), uma revisão dedicada de 516 questões encontrou padrões
que **passavam despercebidos na autoria original** por não violarem nenhuma regra explícita até então.
As quatro lições abaixo complementam os princípios do §1 e a regra de quebra do §6.

### 13.1 "logo" não substitui a quebra de parágrafo

Encadear **duas equações/estados diferentes** no mesmo parágrafo usando "logo" é o mesmo problema que a
seta (`\Rightarrow`) resolvia antes: o aluno vê o resultado sem ver a substituição acontecer.

```text
ERRADO — duas equações coladas por "logo":
\(3(2a+17)=6a+51\), logo \(a+6a+51=9\).

CERTO — fato (avaliação) e nova equação em parágrafos separados:
\(3(2a+17)=6a+51\). \(\\\)
\(a+6a+51=9\).
```

**Exceções — não separar** (o "logo" aqui não esconde uma substituição, então fica num parágrafo só):

| Caso | Exemplo |
|---|---|
| Mesma operação, explícita | "Multiplicando por \(4\): \(8n=3n+30\)." |
| Mesma expressão avaliada em etapas (sem equação nova alheia) | "\(3\cdot4=12\), \(3\cdot8=24\), \(4\cdot8=32\), logo \(2(12+24+32)=2\cdot68\)." |
| Conclusão final simples | "logo \(x=5\)." |
| Raciocínio narrativo/lógico trivial | "Eva voltou com \(Q/2\), logo gastou \(Q/2\)." |
| Leitura direta de dígito/coeficiente já dado | "\(\overline{A8}=98\), logo \(A=9\)." / "\(f(x)=3x-5\), logo \(a=3\)." |
| Definição/identidade aplicada diretamente | "\(\log_4 b=y\), logo \(b=4^y\)." (definição de logaritmo) |

Quando o "logo" é só a **substituição de um valor já derivado** numa relação já estabelecida, sem
frase explicativa, prefira **reescrever com framing** em vez de separar:

```text
ERRADO:
\(2a+8{,}5=98{,}1/9\), logo \(2a+8{,}5=10{,}9\).

CERTO — operação nomeada, sem "logo":
Dividindo por \(9\): \(2a+8{,}5=\dfrac{98{,}1}{9}=10{,}9\).
```

### 13.2 Desenvolvimento de termo/lado isolado precisa de narração antes

Quando um parágrafo vai **sair do fluxo principal** para desenvolver um termo, lado da equação ou
sub-expressão sozinho, o parágrafo **anterior** precisa dizer o quê e por quê — senão o próximo
parágrafo "aparece do nada" para quem está lendo.

```text
ERRADO — o termo (n+2)^2 aparece sem contexto:
\((n+4)^2=n^2+8n+16\). \(\\\)
\((n+2)^2=n^2+4n+4\).

CERTO — frase de contexto antes de cada termo desenvolvido isoladamente:
Vamos desenvolver cada termo desta equação separadamente. Primeiro, o lado esquerdo \((n+4)^2\): \(\\\)
\((n+4)^2=n^2+2\cdot n\cdot4+4^2=n^2+8n+16\). \(\\\)
Agora o termo \((n+2)^2\), do lado direito: \(\\\)
\((n+2)^2=n^2+2\cdot n\cdot2+2^2=n^2+4n+4\).
```

A evolução mecânica em si **pode ficar direta** (fórmula + resultado no mesmo parágrafo) — o que não
pode faltar é a frase anunciando a mudança de foco.

### 13.3 Múltiplas incógnitas/entradas resolvidas em sequência: cada uma (a partir da 2ª) precisa de transição

O mesmo problema do §13.2 aparece quando a resolução resolve **mais de uma incógnita, entrada de
matriz, raiz ou item** em sequência: a primeira costuma fluir naturalmente da equação inicial, mas as
seguintes não podem simplesmente aparecer.

```text
ERRADO — a equação de "y" aparece sem aviso:
Para \(A+B=I\): \(x+2=1\Rightarrow x=-1\). \(\\\)
\(y+4=0\Rightarrow y=-4\).

CERTO — cada entrada seguinte tem sua transição:
Para \(A+B=I\): \(x+2=1\Rightarrow x=-1\). \(\\\)
Para \(y\): \(\\\)
\(y+4=0\Rightarrow y=-4\).
```

Frases curtas resolvem: "Para \(y\):", "Da segunda operação:", "Para a segunda raiz:", "Também da
soma:". Quando **todas** as raízes/soluções já foram anunciadas juntas antes de ramificar (ex.: "as
raízes são \(u=5\) ou \(u=25\)"), a ramificação seguinte não precisa de transição própria — o aviso
já cobre as duas.

### 13.4 Nunca deixar raciocínio de rascunho na resolução

Texto como "— espera, ...", "na verdade, ..." é sinal de autocorreção de quem (ou do processo que)
gerou o conteúdo e **não deveria chegar ao aluno**. Revise o resultado final e reescreva limpo, sem
vestígio de hesitação:

```text
ERRADO:
\(8\times2=16\) — espera: os 10 vendidos são a parte de 5, logo o total é 8+5=13 partes? Na verdade,
a razão vendidos/total = 8:5, e 1 parte = 2; total = 8 partes = 16.

CERTO:
A razão entre o total de veículos e os vendidos é \(8:5\). \(\\\)
Os vendidos correspondem à parte "5": \(5\) partes \(=10\) veículos, ou seja \(1\) parte \(=2\). \(\\\)
O total corresponde à parte "8": \(8\times2=\mathbf{16}\).
```
