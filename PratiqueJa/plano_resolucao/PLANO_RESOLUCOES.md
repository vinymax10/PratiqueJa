# Plano — Revisão didática das RESOLUÇÕES das questões

## Objetivo
Tornar as resoluções da tabela `questao.resolucao` **profissionais e didáticas**: explicar cada passo, definir/enunciar as variáveis, inserir fórmulas (LaTeX) quando aplicável, evitar transições rápidas. Corrigir erros factuais encontrados. **Largura ≤ 40 caracteres por linha** (layout de coluna dupla); quantas linhas forem necessárias.

## Estado (2026-06-05)
- 14.584 questões; **14.577 com resolução**.
- Ledger `_resol_fix` (questao_id PK, status, obs, ts): `ja_bom`=237 (já desenvolvidas, contêm "Resposta:"), `pendente`=**14.340**.
- Maioria das pendentes é curta/terça (<200 chars), algumas semi-prontas (tabela de MMC sem explicação) e às vezes com erro.

## Formato-alvo  ⚠️ QUEBRA DE LINHA (regra definitiva do usuário)
- **Prosa em texto normal, FORA do math** (não usar `\text{}`). Fórmulas inline em `\( ... \)`.
- **Texto corrido NÃO leva quebra** — a apostila quebra sozinha. O `\(\\\)` (span math só com `\\`) serve **SÓ para isolar/quebrar o TRECHO MATEMÁTICO** a cada ~40 chars, p/ caber na coluna. (`\\` solto na prosa não quebra; precisa estar dentro de `\(...\)`.)
- Padrão: bloco de prosa introdutória flui livre e termina com `\(\\\)`; depois **cada passo de cálculo (fórmula) numa linha própria** terminada por `\(\\\)`; fórmula longa (>~40 chars) quebra em mais linhas.
- Última linha: `Resposta: \( \mathbf{...} \).`
- Definir o que cada variável significa antes de usar.
- Operadores por extenso em `\operatorname{}` (ex.: `\operatorname{MMC}`, `\operatorname{sen}`).
- Decimais com `{,}` (ex.: `16{,}5`); `\dfrac`; resultado final em `\mathbf{}`.
- Terminar com linha **`Resposta: \( \mathbf{...} \).`**
- Conferir a alternativa correta (marcada) e garantir que a resolução chega nela; se o original tiver erro, corrigir.

## Conexão MySQL local
- Banco: `pratiqueja` · Usuário: `root` · Binários: `C:\Program Files\MySQL\MySQL Server 8.0\bin\`

## Pipeline (arquivos em `plano_resolucao/`)
- Autoria no `_resol_batch.txt` (formato `#Q <id>` seguido das linhas da resolução, com `\\`). **Sempre via ferramenta Write** (heredoc colapsa `\\`).
- `py plano_resolucao\_resol_apply.py` lê o batch e gera `_resol_apply.sql` (UPDATE ... UNHEX preserva `\\`).
- Backup por lote: `mysqldump ... --where="id IN (...)"` de `questao` em `_backup_resol/`.
- Aplicar SQL; atualizar `_resol_fix.status='feito'`; verificar renderização/coerência.

## Fluxo por lote (~40 questões)
1. `SELECT questao_id FROM _resol_fix WHERE status='pendente' ORDER BY questao_id LIMIT 40;`
2. Buscar enunciado + alternativa correta + resolução atual de cada id.
3. Redigir resolução didática (corrigindo erros); escrever `_resol_batch.txt` via Write.
4. `py plano_resolucao\_resol_apply.py` → backup → aplicar SQL.
5. `UPDATE _resol_fix SET status='feito', ts=NOW() WHERE questao_id IN (...);`
6. Registrar andamento neste arquivo.

## Andamento
- Estilo VALIDADO pelo usuário (formato: prosa corrida + `\(\\\)` só no math).
- Lotes 1–4 APLICADOS (30 resoluções): 1035,1052,1060,1069,1099,1155 · 1202,1214,1220,1228,1236,1267,2055,2144 · 5587,5594,5601,5622,5672,5681,5703,5723 · 5745,5828,6005,6011,6018,6024,6093,6127.
- Lotes de 40/vez (Q5828–Q8361): +136 resoluções aplicadas.
- Lote 2026-06-07a (Q8368–Q8704): +40 resoluções aplicadas.
- Lote 2026-06-07b (Q8710–Q8963): +40 resoluções aplicadas.
- Lote 2026-06-08a (Q8973–Q9254): +40 resoluções aplicadas.
- Lote 2026-06-08b (Q9259–Q9546): +40 resoluções aplicadas.
- Lote 2026-06-08c (Q9555–Q9874): +40 resoluções aplicadas.
- Lote 2026-06-08d (Q9880–Q10191): +40 resoluções aplicadas.
- Lote 2026-06-08e (Q10198–Q10510): +40 resoluções aplicadas.
- Lote 2026-06-08f (Q10517–Q10831): +40 resoluções aplicadas.
- Lote 2026-06-08g (Q10838–Q11171): +40 resoluções aplicadas.
- Lote 2026-06-08h (Q11179–Q11472): +40 resoluções aplicadas.
- Lote 2026-06-08i (Q11481–Q11827): +40 resoluções aplicadas.
- Lote 2026-06-08j (Q11834–Q12226): +40 resoluções aplicadas.
- Lote 2026-06-08k (Q12233–Q12600): +40 resoluções aplicadas.
- Lote 2026-06-08l (Q12606–Q12902): +40 resoluções aplicadas.
- Lote 2026-06-08m (Q12910–Q13822): +40 resoluções aplicadas.
- Lote 2026-06-08n (Q13829–Q14360): +40 resoluções aplicadas.
- Lote 2026-06-08o (Q14370–Q14644): +40 resoluções aplicadas.
- Lote 2026-06-08p (Q14650–Q15115): +40 resoluções aplicadas.
- Lote 2026-06-08q (Q15121–Q15586): +40 resoluções aplicadas.
- Lote 2026-06-08r (Q15593–Q15879): +40 resoluções aplicadas.
- Lote 2026-06-08s (Q15885–Q16187): +40 resoluções aplicadas.
- Lote 2026-06-08t (Q16194–Q16501): +40 resoluções aplicadas.
- Lote 2026-06-08u (Q16508–Q16820): +40 resoluções aplicadas.
- Lote 2026-06-08v (Q16826–Q17292): +40 resoluções aplicadas.
- Lote 2026-06-08w (Q17298–Q17804): +40 resoluções aplicadas.
- Lote 2026-06-08x (Q17813–Q18186): +40 resoluções aplicadas.
- Lote 2026-06-08y (Q18192–Q18613): +40 resoluções aplicadas.
- Lote 2026-06-08z (Q18622–Q19076): +40 resoluções aplicadas.
- Lote 2026-06-08aa (Q19083–Q19591): +40 resoluções aplicadas.
- Lote 2026-06-08ab (Q19597–Q20099): +40 resoluções aplicadas.
- Lote 2026-06-08ac (Q20105–Q20458): +40 resoluções aplicadas.
- Lote 2026-06-08ad (Q20468–Q20861): +40 resoluções aplicadas.
- Lote 2026-06-08ae (Q20889–Q21538): +40 resoluções aplicadas.
- Lote 2026-06-08af (Q21547–Q21879): +40 resoluções aplicadas.
- Lote 2026-06-08ag (Q21886–Q22240): +40 resoluções aplicadas.
- Lote 2026-06-08ah (Q22247–Q22565): +40 resoluções aplicadas.
- Lote 2026-06-08ai (Q22584–Q23002): +40 resoluções aplicadas.
- Lote 2026-06-08aj (Q23008–Q23316): +40 resoluções aplicadas.
- Lote 2026-06-08ak (Q23323–Q23649): +40 resoluções aplicadas.
- Lote 2026-06-08al (Q23667–Q24007): +40 resoluções aplicadas.
- Lote 2026-06-08am (Q24017–Q24400): +40 resoluções aplicadas.
- Lote 2026-06-08an (Q24407–Q25042): +40 resoluções aplicadas.
- Lote 2026-06-08ao (Q25053–Q25455): +40 resoluções aplicadas.
- Lote 2026-06-08ap (Q25461–Q25733): +40 resoluções aplicadas.
- Lote 2026-06-08aq (Q25741–Q26122): +40 resoluções aplicadas.
- Lote 2026-06-08ar (Q26129–Q26388): +40 resoluções aplicadas.
- Lote 2026-06-08as (Q26395–Q26687): +40 resoluções aplicadas.
- Lote 2026-06-08at (Q26695–Q27037): +40 resoluções aplicadas.
- Lote 2026-06-08au (Q27044–Q27383): +40 resoluções aplicadas.
- Lote 2026-06-08av (Q27775–Q28123): +40 resoluções aplicadas.
- Lote 2026-06-08aw (Q28131–Q28434): +40 resoluções aplicadas.
- Lote 2026-06-08ax (Q28441–Q28966): +40 resoluções aplicadas.
- Lote 2026-06-08ay (Q29040–Q29457): +40 resoluções aplicadas.
- Lote 2026-06-08az (Q29463–Q29777): +40 resoluções aplicadas.
- Lote 2026-06-08aaa (Q29788–Q30162): +40 resoluções aplicadas.
- Lote 2026-06-08aab (Q30168–Q30553): +40 resoluções aplicadas.
- Lote 2026-06-08aac (Q30560–Q30975): +40 resoluções aplicadas.
- Lote 2026-06-08aad (Q30989–Q31322): +40 resoluções aplicadas.
- Lote 2026-06-08aae (Q31330–Q31609): +40 resoluções aplicadas.
- Lote 2026-06-08aaf (Q31617–Q31963): +40 resoluções aplicadas.
- Lote 2026-06-08aag (Q31970–Q32280): +40 resoluções aplicadas.
- Lote 2026-06-08aah (Q32287–Q32586): +40 resoluções aplicadas.
- **`_resol_fix` (2026-06-08): ja_bom=237, feito=2.646, pendente=11.694. Último id feito: 32586.**
- **Próximo lote (aai) — IDs já buscados, dados já consultados, ainda não aplicado:**
  32601,32608,32618,32625,32635,32645,32655,32662,32669,32690,
  32697,32704,32711,32718,32725,32732,32739,32746,32758,32765,
  32772,32781,32788,32795,32802,32809,32816,32823,32830,32837,
  32844,32854,32861,32868,32875,32885,32899,32915,32934,32940
- Tipos cobertos: MMC/MDC (tabela `\begin{array}`), regra de três simples/composta (tabela `\begin{array}`), racionalização, equações do 1º e 2º grau, Bhaskara, probabilidade/união, combinação/arranjo/PM, dízima geratriz, Tales, Pitágoras, juros simples, aumentos sucessivos, trig. retângulo, sólidos, anagramas, itens "julgue Certo/Errado", média ponderada, percentagem, proporção, gráfico de setores, Venn.
- Convenções fixadas: corrigir erros do original; regra de três com variável definida; `\operatorname{MMC/MDC}`; decimais `{,}`; dinheiro "R$" fora do math; `\dfrac`; `\mathbf{}` no resultado.
- **MMC/MDC — tabela de decomposição em fatores primos:** usar `\begin{array}[t]{r r|l}` (2 números) ou `{r r r|l}` (3 números). Cada passo: `a, & b & divisor\\`; `\hline` ao final; última linha com fatoração `p^e \cdot ... = resultado`. Para MDC: linhas onde o divisor divide TODOS os números levam `\textcolor{iris}{...}`.
- **Regra de três — tabela de comparação dos valores:** usar `\begin{array}[t]{cc}` (simples, 2 colunas) ou `{ccc}` (composta, 3 colunas). Cabeçalho com setas `(\downarrow)` (direta) ou `(\uparrow)` (inversa); `\hline` entre linhas; `x` na célula desconhecida. Após a tabela: equação de proporção com `\dfrac` e resolução passo a passo.
