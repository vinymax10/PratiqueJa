---
name: plano-qualidade-questao
description: Plano para pontuar (0-100) a qualidade da formulação e da resolução de cada questão
metadata:
  type: project
---

# Plano — Notas de qualidade das questões

## Objetivo
Atribuir a cada questão (`tabela questao`, banco `pratiqueja`) duas notas inteiras
**0–100** e gravá-las nos campos:
- `qualidadeFormulacao` — qualidade do enunciado + alternativas.
- `qualidadeResolucao` — qualidade da resolução, tendo como referência
  [`formato_resolucao_questao.md`](../formato_resolucao_questao.md).

As notas servem para tratamento posterior (ex.: filtrar/priorizar questões fracas
para revisão — a tela `/administracao/conteudo/questao/gerenciar` já filtra por
faixa de qualidade).

## Estado (2026-06-16)
- 14.584 questões; 14.577 com resolução.
- Ledger `_qualidade_fix` (questao_id PK, status, qf, qr, obs, ts): `feito` cresce
  por lote; restante `pendente`.

## Rubrica — qualidadeFormulacao (0–100)
Avalia o **enunciado + alternativas** (não a resolução):
- **90–100:** enunciado claro, completo e sem ambiguidade; todos os dados
  necessários presentes; alternativas bem construídas (exatamente uma correta,
  distratores plausíveis e distintos); LaTeX e português impecáveis.
- **70–89:** boa, com pequenos deslizes (um distrator fraco, leve imprecisão de
  redação, dado importante apenas na imagem, formatação menor).
- **50–69:** aceitável com problemas notáveis (redação truncada, dado implícito,
  distrator repetido, LaTeX inconsistente).
- **30–49:** deficiente (ambiguidade real, faltam dados, alternativas problemáticas).
- **1–29:** muito ruim (enunciado incompreensível, sem alternativa correta clara).
- **0:** sem enunciado utilizável.
- Penalidades fortes: marcada `malFormulada`, sem alternativa correta, enunciado vazio.

## Rubrica — qualidadeResolucao (0–100)
Avalia a **resolução** quanto a correção e aderência ao `formato_resolucao_questao.md`:
- **90–100:** resolve corretamente e chega à alternativa marcada; segue o formato —
  prosa fora de `\(…\)`, passos sem saltos, variáveis definidas, `\dfrac`, decimais
  `{,}`, `\operatorname{}`, tabela `\begin{array}` para MMC/MDC/regra de três,
  termina em `Resposta: \( \mathbf{...} \).`; quebras de linha corretas.
- **70–89:** resolve certo e é clara, mas desvia do formato (sem linha `Resposta:`,
  sem `\mathbf`, algum salto de passo, formatação menor).
- **50–69:** resolve, porém pobre (muito curta, poucos passos, prosa dentro de
  `\text`, LaTeX inconsistente) ou praticamente só o resultado.
- **30–49:** incompleta ou parcialmente errada.
- **1–29:** presente mas errada/inutilizável.
- **0:** sem resolução.

## Conexão MySQL local
- Banco: `pratiqueja` · Usuário: `root` · Binários:
  `C:\Program Files\MySQL\MySQL Server 8.0\bin\` (credenciais fora do versionado).

## Pipeline (arquivos nesta pasta `avaliacao_questoes/`)
- `_lote.txt` — saída legível do lote atual (enunciado, alternativas, resolução).
- `_apply.sql` — UPDATEs de `Questao` + `_qualidade_fix` gerados para o lote.

## Fluxo por lote (~25 questões)
1. Buscar lote pendente (com enunciado + alternativas + resolução):
   ```sql
   SET SESSION group_concat_max_len = 1000000;
   SELECT q.id,
     (SELECT GROUP_CONCAT(CASE WHEN p.imagemFile_id IS NOT NULL THEN '[IMG]' ELSE p.texto END
        ORDER BY p.ordem SEPARATOR '  //  ') FROM Paragrafo p WHERE p.questao_id=q.id) AS enunciado,
     (SELECT GROUP_CONCAT(CONCAT(a.ordem,') ',COALESCE(a.texto,''),IF(a.correta,' <<OK',''))
        ORDER BY a.ordem SEPARATOR '  ') FROM Alternativa a WHERE a.questao_id=q.id) AS alternativas,
     COALESCE(q.resolucao,'(SEM RESOLUCAO)') AS resolucao
   FROM Questao q JOIN _qualidade_fix f ON f.questao_id=q.id
   WHERE f.status='pendente' ORDER BY q.id LIMIT 25;
   ```
2. Ler cada questão e atribuir `qf` e `qr` pela rubrica.
3. Gerar `_apply.sql` (UPDATE Questao + UPDATE _qualidade_fix status='feito').
4. Aplicar o SQL e registrar o andamento.

> Observações de leitura: `[IMG]` indica parágrafo-imagem (conteúdo visual não
> textual) — se um dado essencial estiver só na imagem, isso reduz `qf`.
> A alternativa correta vem marcada com `<<OK`.

## Andamento
- Lote 1 (2026-06-16, ids 893–1155): 25 questões. qf 80–90 (méd. 86,6),
  qr 90–93 (méd. 91,5) — questões reais com resoluções já no padrão.
- Lote 2 (2026-06-16, ids 1163–5594): 40 questões (MMC/MDC, regra de três).
- Lote 3 (2026-06-16, ids 5601–6420): 100 questões (porcentagem, frações,
  juros, sequências, racionalização, complexos, lógica).
- Lote 4 (2026-06-16, ids 6427–7262): 100 questões. Inclui as 7 questões SEM
  resolução do banco (qr=0) e itens "julgue Certo/Errado" (CESPE) + pedagogia/história.
- Lote 5 (2026-06-16, ids 7267–8017): 100 questões (julgue avançados: matrizes,
  funções, trigonometria, geometria analítica; + aplicadas).
- Lotes 6–? (2026-06-16, ids a partir de 8024): blocos de 100 via loop autônomo.
- **Total: 465 feitas, 14.119 pendentes. Médias acumuladas: qf ~85 · qr ~90.**
- Observado: nesta faixa as resoluções já estavam no padrão (notas altas);
  qf cai (~80–82) quando dados essenciais ficam só na imagem (listas de preço,
  gráficos). Lotes processados em blocos de 100 via `_lote.txt`/`_apply.sql`.
