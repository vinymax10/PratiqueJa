# Plano de Revisão e Padronização das Questões — PratiqueJá

> Objetivo: revisar as questões de concurso para qualidade visual **profissional** —
> matemática em **LaTeX**, escrita corrigida e padronizada, **resolução** objetiva, e
> imagens que são apenas fórmulas convertidas em LaTeX. Ao final de cada revisão, marcar
> `revisada = 1`.

## Capacidades técnicas confirmadas

- **Renderização:** o app usa **MathJax v2** (`js/mathJaxConfig.js`), com extensões
  `color` e `cancel`. A `resolucao` é exibida via `outputLabel` + `reloadMathJax()` →
  **renderiza LaTeX**. Os parágrafos e alternativas também passam pelo MathJax.
- **Delimitador inline padrão: `\( ... \)`** (97% dos parágrafos com matemática usam `\(`;
  só ~92 usam `$`). Display/quebras com `\\` dentro do bloco.
- **Imagens:** ficam como blob em `imagemfile.file` (1:1 com `paragrafo`). Consigo
  **exportar e visualizar** cada imagem para decidir se é fórmula (→ LaTeX) ou figura real
  (→ manter).
- **Base atual:** 15.241 questões, **603 revisadas**, ~14.6 mil a revisar.

## Padrão de escrita (LaTeX)

- Inline math sempre em `\( ... \)`.
- **Unidades** dentro do math com `\text{}` e espaço fino `\,`: `\(48\,\text{m}^2\)`,
  `\(16\,\text{cm}\)`.
- Frações: `\dfrac{a}{b}` · Potências: `x^2`, `x^{10}` · Raízes: `\sqrt{2}`, `\sqrt[3]{27}`
  · Multiplicação: `\cdot` · Aproximação: `\approx`.
- Converter matemática "crua" em texto: `m2`→`\(\text{m}^2\)`, `x2`→`\(x^2\)`,
  `raiz de 2`→`\(\sqrt{2}\)`, `1/2`→`\(\dfrac{1}{2}\)`, `0,8`→`\(0{,}8\)`.
- **Correção de escrita:** acentuação/encoding, ortografia, pontuação; remover `?` inicial
  espúrio; enunciado termina com `:` quando seguido de alternativas. **Nunca alterar os
  dados/o sentido nem qual é a alternativa correta.**

## Padrão da resolução (simples e objetivo)

- **Definir as variáveis no início** quando a questão introduz uma incógnita (ex.:
  `n = nº de revistas`, `x = idade`, `C = capital`). Deixa a resolução autoexplicativa.
  (Pedido do usuário em 2026-05-30, a partir da Q25125.)
- **Fórmula primeiro, depois a aplicação:** quando usar uma fórmula, escrever a **fórmula
  geral** em uma linha e só então substituir os valores, para o aluno reconhecer de onde
  vem a conta. Ex.: `S_n=\dfrac{n(a_1+a_n)}{2}` → depois `=\dfrac{10(2+20)}{2}=110`;
  `A=\dfrac{(B+b)\,h}{2}` → depois com números; `J=C\cdot i\cdot t` → depois.
  (Pedido do usuário em 2026-05-30.)
- **Frações: usar `\dfrac{}{}` (não `\frac{}{}`)** — `\dfrac` mantém a fração em tamanho
  cheio (display); `\frac` fica achatado/pequeno inline. Vale também em `\tfrac` → trocar
  por `\dfrac`. (Pedido do usuário em 2026-05-30.)
- Um bloco `\( ... \)` com a solução em **passos curtos**, `\\` entre linhas.
- Fluxo: dado → cálculo-chave → resultado. **2 a 5 linhas**, sem prosa longa.
- Destacar o resultado final (ex.: `= \mathbf{65\,\text{m}^2}` ou `\color{darkblue}{...}`).
- Se precisar de 1 frase de contexto, vai curta e fora do `\(\)`.

### ⚠️ Limite de largura — apostila PDF em coluna dupla
As resoluções entram numa **apostila PDF em coluna dupla**, então a coluna é estreita:

- **Expressão matemática: máximo ~40 caracteres por linha.** Matemática **não quebra
  sozinha** → quando a linha passar de ~40 chars, **quebrar manualmente com `\\`**.
- **Texto corrido (prosa)** quebra/justifica sozinho na coluna → não precisa de `\\`.
  Mas atenção: dentro de `\( ... \)` *tudo* é modo matemático (até `\text{}` não quebra
  sozinho) → se houver texto longo, tirá-lo do `\(\)` ou quebrar com `\\`.
- Preferir **um passo por linha** (cada `\\` = um passo): isola o cálculo, encurta a linha
  e fica mais legível na coluna.
- Exemplo (Q 11254, 5 linhas, todas < 40 chars):
  ```latex
  \( \text{Lados: } c \text{ e } \dfrac{c}{3}. \\
  c\cdot\dfrac{c}{3}=48 \Rightarrow c^2=144 \\
  c=12,\ \text{largura}=4 \\
  \text{Reformado: }(12{+}1)(4{+}1) \\
  =13\cdot5=\mathbf{65\,\text{m}^2} \)
  ```

## Tratamento de imagens

- **Imagem = fórmula/expressão** (equação, expressão numérica como figura) → transcrever
  para LaTeX no parágrafo e **remover** a imagem (`paragrafo.imagemFile_id = NULL` +
  apagar a `imagemfile`).
- **Imagem = figura real** (triângulo, gráfico, desenho geométrico, tabela) → **manter**.
  Nesses casos a figura carrega dados → só revisar o texto ao redor.
- Eu visualizo cada imagem antes de decidir.

## Fluxo de trabalho

### Fase 1 — Interativa (agora, para calibrar o padrão)
Para **cada questão**:
1. Mostro **ANTES → DEPOIS**: parágrafos, alternativas, resolução (e a imagem, quando há).
2. Você ajusta / aprova.
3. Aplico no banco (backup + transação): `UPDATE paragrafo`, `UPDATE alternativa`,
   `UPDATE questao SET resolucao=..., revisada=1`.

### Fase 2 — Automática via /loop (RUNBOOK — cada ciclo)
Disparada pelo usuário com `/loop` (autônomo). **A cada ciclo do loop:**
1. Buscar próximo lote (**~40** — ajustado de 20 em 2026-05-30 a pedido do usuário) de não-revisadas de Matemática, **priorizando sem imagem e
   sem LaTeX ainda** (`d.nome LIKE '%atem%' AND revisada=0`), excluindo as `#@SKIP`.
2. Para cada questão: resolver o problema, reescrever enunciado/alternativas em LaTeX
   (regras do padrão), escrever resolução (math em linhas ≤40 chars com `\\`; texto-corrido
   com math inline). Unidades por extenso nas alternativas quando o original é por extenso.
3. **Pular casos sem confiança** (cálculo muito complexo, dados ambíguos, imagem duvidosa):
   NÃO marcar revisada; registrar o id em `_revisao_skip.txt` (id | motivo). Além disso,
   gravar o flag na própria questão (colunas novas em `questao`):
   - **Gabarito furado / estrutura quebrada** → `UPDATE questao SET malFormulada=1,
     obsRevisao='<motivo>' WHERE id=...` (deixar `revisada=0`).
   - **Aborda mais de um assuntoCurso** (ex.: "Leia as afirmativas: I… II… III…" com tópicos
     distintos) → `multiAssunto=1` (+ `obsRevisao`).
   - Apenas "complexa demais p/ o automático" (questão válida) → só skip list, **não** marcar
     malFormulada.
4. Aplicar via `_rev_apply.py` (arquivo de lote `#Q/#P/#A/#R` → UNHEX → transação). Backup
   do lote em `_backup_revisao/batch_*.sql` antes de aplicar.
5. Reportar progrésso (revisadas / faltam) e seguir para o próximo ciclo.

**Imagens:** questões com imagem → exportar blob (`imagemfile.file`) p/ PNG, abrir com Read,
decidir: fórmula → transcrever LaTeX e remover imagem; figura real → manter. (Lote separado,
mais lento.)

**Skip list:** `_revisao_skip.txt` — ids para revisão manual posterior.

## Priorização sugerida
1. Por **assunto** (ex.: começar por "Área e Perímetro", depois trigonometria, etc.) — dá
   consistência e permite reaproveitar padrões de resolução.
2. Dentro do assunto, primeiro as **sem imagem** (mais simples), depois as com imagem.

## Nota técnica — gravação do LaTeX (importante)

O cliente `mysql` colapsa `\\`→`\` (mesmo com `NO_BACKSLASH_ESCAPES`), o que quebra a
quebra de linha LaTeX `\\`. **Método à prova de falhas adotado:** escrever o conteúdo
exato num arquivo (bytes literais) → `hex=$(... | xxd -p)` → `UPDATE ... SET col=UNHEX('$hex')`.
Backslashes, acentos e aspas entram exatamente. Validado na questão 11254.

## Progresso
- [x] Padrão definido: unidades em `\(\,\text{}\)`, resolução 2–3 passos, fluxo 1-a-1.
- [x] Pipeline de gravação (UNHEX) validado.
- [x] **Q 11254** revisada (calibração).
- [ ] Fase 1 calibrada (padrão aprovado em ~5–10 questões)
- [ ] Início da Fase 2 (lote)

*(Marcação `revisada=1` por questão; backup por lote em `_backup_revisao_*`.)*

---
*Gerado em 2026-05-29.*
