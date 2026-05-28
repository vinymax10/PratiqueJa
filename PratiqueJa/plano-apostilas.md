# Plano de Implementação — Apostilas PratiqueJá

## Status Geral

- [x] **racionais** (Básico)
- [x] **divisibilidade** (Intermediário)
- [ ] ... demais assuntos listados abaixo

---

## Estrutura de Arquivos

```
PratiqueJa/
├── tex-new/
│   ├── pratiqueja.sty                      ← estilo único compartilhado (NÃO duplicar)
│   ├── basico/
│   │   ├── racionais/
│   │   │   └── racionais.tex               ✅
│   │   └── <assunto>/
│   │       └── <assunto>.tex
│   ├── intermediario/
│   │   ├── divisibilidade/
│   │   │   └── divisibilidade.tex          ✅
│   │   └── <assunto>/
│   │       └── <assunto>.tex
│   └── avancado/
│       └── <assunto>/
│           └── <assunto>.tex
└── src/main/webapp/matematica/
    ├── basico/<assunto>/teoria.xhtml        ← fonte de conteúdo
    ├── intermediario/<assunto>/teoria.xhtml
    └── avancado/<assunto>/teoria.xhtml
```

---

## Como Criar um Novo Arquivo `.tex`

### 1. Criar a pasta e o arquivo

```
tex-new/<modulo>/<assunto>/<assunto>.tex
```

Exemplos:
- `tex-new/basico/adicaoNatural/adicaoNatural.tex`
- `tex-new/intermediario/equacoes/equacoes.tex`
- `tex-new/avancado/matrizes/matrizes.tex`

### 2. Estrutura mínima do arquivo

```latex
% ============================================================
%  <Título do Assunto> — Apostila Premium | PratiqueJá
%  Engine: XeLaTeX
% ============================================================
\documentclass[12pt]{article}
\usepackage{../../pratiqueja}

\setsubject{<Título do Assunto>}

\begin{document}
\pagenumbering{arabic}
\setstretch{1.2}
\color{bodytext}

\heroheader{<Título>}%
           {<Subtítulo descritivo>}%
           {Matemática · <Básico|Intermediário|Avançado>}

\setlength{\columnsep}{18pt}
\setlength{\columnseprule}{0.5pt}
\def\columnseprulecolor{\color{exborder}}

\begin{multicols}{2}

% ... conteúdo aqui ...

\end{multicols}

\end{document}
```

**Atenção ao caminho do estilo:** como o `.tex` fica dois níveis abaixo de `tex-new/`, o caminho é sempre `../../pratiqueja` (dois `../`).

### 3. Se o assunto usa fórmulas com destaque em modo matemático

Adicionar logo após `\usepackage{../../pratiqueja}`:

```latex
\newcommand{\hlm}[1]{{\color{darkblue}#1}}
```

Usar `\hlm{}` dentro de `$...$` ou `\[...\]`, e `\hl{}` apenas em texto normal.

### 4. Se precisar de cancelamento de fração (ex: simplificação)

```latex
\usepackage{cancel}
```

---

## Como Compilar

### Comando completo (PowerShell ou terminal)

```powershell
C:/Users/maximovrm/AppData/Local/Programs/MiKTeX/miktex/bin/x64/xelatex.exe `
  -output-directory "tex-new/<modulo>/<assunto>" `
  "tex-new/<modulo>/<assunto>/<assunto>.tex"
```

Exemplos reais:
```powershell
# Básico — racionais
C:/Users/maximovrm/AppData/Local/Programs/MiKTeX/miktex/bin/x64/xelatex.exe `
  -output-directory "tex-new/basico/racionais" `
  "tex-new/basico/racionais/racionais.tex"

# Intermediário — divisibilidade
C:/Users/maximovrm/AppData/Local/Programs/MiKTeX/miktex/bin/x64/xelatex.exe `
  -output-directory "tex-new/intermediario/divisibilidade" `
  "tex-new/intermediario/divisibilidade/divisibilidade.tex"
```

**Atenção:** usar `xelatex.exe` — nunca `pdflatex` (fontspec exige XeLaTeX).

### Antes de recompilar

Fechar o PDF no visualizador, caso esteja aberto. O MiKTeX não consegue sobrescrever o arquivo enquanto ele está aberto.

---

## Componentes Visuais Disponíveis

| Comando | Uso |
|---|---|
| `\heroheader{título}{subtítulo}{categoria}` | Cabeçalho da apostila |
| `\TW{badge}{cor}{título}{subtítulo}` | Cabeçalho de seção |
| `\regra{conteúdo}` | Caixa azul de regra (breakable) |
| `\exemp{conteúdo}` | Caixa de exemplos (breakable) |
| `\formula{conteúdo}` | Caixa de fórmula centrada |
| `\obs{conteúdo}` | Observação cinza (NÃO breakable) |
| `\nota{conteúdo}` | Nota/dica fundo amarelo (NÃO breakable) |
| `\SH{rótulo}` | Sub-seção discreta em maiúsculas |
| `\badge{texto}{cor}` | Badge colorido inline |
| `\chip{texto}` | Chip para listas de itens (ex: divisores) |
| `\hl{texto}` | Destaque azul em texto |
| `\hlm{expr}` | Destaque azul em modo matemático (definir localmente) |
| `\ok` | ✓ verde |
| `\nok` | ✗ vermelho |

### Cores disponíveis para `\TW` e `\badge`

| Nome | Uso sugerido |
|---|---|
| `darkblue` | Regras, definições matemáticas |
| `badgepurple` | Definição conceitual |
| `badgegreen` | Tópicos de fechamento, resumo |

---

## Regras Importantes (não esquecer)

### breakable: somente em caixas grandes
- `\regra` e `\exemp` usam `breakable, enhanced jigsaw` — podem quebrar coluna/página.
- `\obs` e `\nota` são **não-breakable** — caixas pequenas com breakable saltam para a coluna seguinte deixando espaço vazio.

### Frações com espaçamento automático
Dentro de `\exemp`, `\obs` e `\nota` já há `\lineskiplimit` e `\lineskip` configurados — o espaço entre linhas com `\dfrac` é automático. Não usar `\\[Xpt]` manual desnecessariamente.

### `\hl` não funciona dentro de `$...$`
Usar `\hlm` (definido localmente no `.tex`) para destacar resultados dentro de expressões matemáticas.

### Não copiar o arquivo de estilo
O arquivo `pratiqueja.sty` fica **somente** em `tex-new/`. Cada `.tex` aponta com `\usepackage{../../pratiqueja}`. Nunca criar cópias por pasta.

### Tabuadas e grids multi-coluna: usar `minipage` com `\hfill`, nunca `multicols` + `\columnbreak`
`\columnbreak` usa `\vadjust` internamente, que só funciona em modo horizontal (dentro de parágrafo). Em grupos `{\footnotesize ... }` sem `\\` no último item, o `\columnbreak` não dispara no lugar certo e o cabeçalho da coluna seguinte acaba na coluna errada.

**Solução correta:** usar `\begin{minipage}[t]{3.3cm}...\end{minipage}\hfill` para cada coluna, agrupando as fileiras manualmente:
```latex
% Fileira 1
\noindent
\begin{minipage}[t]{3.3cm}
  \textcolor{darkblue}{\bfseries\small Título}\par\vspace{2pt}
  {\footnotesize $1{+}1=\mathbf{2}$\\ ... $1{+}10=\mathbf{11}$}
\end{minipage}\hfill
\begin{minipage}[t]{3.3cm}...\end{minipage}\hfill
... (5 por fileira)

% Fileira 2
\vspace{10pt}\noindent
\begin{minipage}[t]{3.3cm}...\end{minipage}\hfill
...
```
O `\hfill` distribui o espaço restante igualmente. Largura de 3.3cm por minipage funciona bem para 5 colunas em página A4 com margens de 1.5cm.

### Fonte Inter 18pt
Instalada pelo usuário nas fontes de usuário (não sistema). Se o PDF sair com fontes erradas (nullfont), verificar se a fonte está instalada e usar o nome exato `Inter 18pt` no `\setmainfont`.

---

## Lista de Assuntos

### Básico

- [x] racionais
- [x] adicaoNatural
- [x] adicaoSubtracaoInteiro
- [ ] areaPerimetro
- [ ] conjuntos
- [ ] divisaoNatural
- [ ] expressaoNumerica
- [ ] expressoesAlgebricas ⚠️ *(verificar se teoria.xhtml existe)*
- [ ] expressoesNumericas ⚠️ *(verificar se teoria.xhtml existe)*
- [ ] mmcMdc
- [ ] multiplicacaoDivisaoInteiro
- [ ] multiplicacaoNatural
- [ ] numerosDecimais
- [ ] numerosPrimos
- [ ] planoCoordenado
- [ ] regraTres
- [ ] sistemaMetrico
- [ ] subtracaoNatural

### Intermediário

- [x] divisibilidade
- [ ] anguloInscritoCircunferencia
- [ ] circulo
- [ ] decimal ⚠️ *(verificar se teoria.xhtml existe)*
- [ ] dizima
- [ ] equacoes
- [ ] expressaoAlgebrica
- [ ] logaritmo
- [ ] notacaoCientifica
- [ ] pitagoras
- [ ] poligonosRegulares
- [ ] potenciacao
- [ ] radiciacao
- [ ] razoesTrigonometricas
- [ ] semelhancaAngulos
- [ ] semelhancaTriangulos
- [ ] somaAngulosTriangulo

### Avançado

- [ ] combinatoria
- [ ] equacaoSegundoGrau
- [ ] estatistica
- [ ] funcaoAfim
- [ ] funcaoExponencial
- [ ] funcaoLogaritmica
- [ ] funcaoModular
- [ ] funcaoQuadratica
- [ ] funcoes
- [ ] funcoesTrigonometricas
- [ ] geometriaAnalitica
- [ ] geometriaEspacial
- [ ] inequacoes2grau
- [ ] jurosDesconto
- [ ] leiSenoCosseno
- [ ] matrizes
- [ ] numerosComplexos
- [ ] pA
- [ ] pG
- [ ] polinomios
- [ ] probabilidade
- [ ] sistemaEquacoes
- [ ] trigoAdicao

---

## Fluxo de Trabalho por Assunto

1. Abrir o `teoria.xhtml` correspondente em `src/main/webapp/matematica/<modulo>/<assunto>/teoria.xhtml`
2. Criar pasta `tex-new/<modulo>/<assunto>/` e arquivo `<assunto>.tex`
3. Mapear o conteúdo do XHTML para os componentes LaTeX:
   - `<div class="tr-rule">` → `\regra{}`
   - `<div class="tr-example">` → `\exemp{}`
   - `<div class="tr-note">` → `\nota{}`
   - `<div class="tr-obs">` ou observações → `\obs{}`
   - Fórmula centralizada → `\formula{}`
   - Subtítulos de seção → `\SH{}`
   - Cabeçalhos de seção → `\TW{badge}{cor}{título}{subtítulo}`
4. Compilar com XeLaTeX usando o comando da seção acima
5. Revisar PDF — verificar espaçamento, quebras de coluna, legibilidade
6. Fazer ajustes e recompilar
7. Marcar o checkbox neste plano

---

*Gerado em 2026-05-28*
