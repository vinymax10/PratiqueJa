# Retomar — Revisão de imagens de enunciado → LaTeX

**STATUS (2026-07-05): CONCLUÍDA.** Todos os 2.323 parágrafos foram classificados e os 686 `latex_candidato`
foram inseridos no banco (lotes 001-103). Falta apenas a revisão manual do usuário no app, ajustando/removendo
as imagens originais conforme necessário — isso está fora do escopo desta automação e não precisa de retomada.

Tarefa: revisar os 2.323 parágrafos de enunciado de `Questao` que têm imagem
(`paragrafo.imagemFile_id IS NOT NULL`). Para os que são fórmula/matriz/expressão (texto que poderia ser
LaTeX), inserir um novo parágrafo de texto/LaTeX imediatamente antes do parágrafo de imagem (a imagem
permanece; o usuário revisa manualmente depois e ajusta/remove).

Plano completo: `C:\Users\maximovrm\.claude\plans\resilient-prancing-robin.md`.
Convenção LaTeX: `formato_resolucao_questao.md` (raiz do projeto).

## Estado atual

- [x] Fase 0 — pasta criada, `.gitignore` atualizado.
- [x] Fase 1 — `manifest.tsv` com as 2.323 linhas + imagens exportadas em `imagens/` (2.313 exportadas,
  10 com `status=erro_sem_blob` — `imagemfile.file` NULL no banco, dado orfao pre-existente).
- [x] Fase 2 — piloto (Racionais + Sistema de Equações, 93 itens; 91 processados, 2 excluidos por
  `erro_sem_blob`) classificado: 52 `latex_candidato`, 39 `manter_imagem`.
- [x] Fase 3 — `lote_001.sql` aplicado no banco (52 itens, 56 parágrafos novos, ids 900000001..900000056).
  Backup em `_backup/lote_001_backup.sql`. Verificado: sem gaps/duplicatas de `ordem`, conteúdo íntegro
  via `HEX(texto)` (confirma que o `\\` duplicado que aparece no `SELECT` do cliente `mysql` é só
  escaping de exibição do proprio cliente CLI, os bytes armazenados estão corretos).
- [x] Fase 4 — checkpoint com o usuário. Reportado: 52/91 viraram LaTeX, 39 mantidos como imagem,
  6 exemplos indicados p/ conferência no app (ver tabela na resposta do checkpoint). Usuário pausou sem
  aprovar/reprovar o formato ainda.
- [x] **Fase 5 — ESCALADA (retomada 2026-07-02)**: usuário confirmou "pode retomar" e pediu para
  **continuar salvando os ids das questões em lote** (para revisão manual posterior, sem esperar
  aprovação a cada lote — mesmo padrão de autorização contínua usado em `resolucao_questao/`). Processando
  os 2.222 parágrafos pendentes fora do piloto, em rodadas de ~20 imagens (custo de visão), 1 lote SQL por
  rodada (não acumula como no piloto). **Lote 002 aplicado** (927,1077,5818,5879,6083 — 5 questões, todas
  latex_candidato eram tabelas puras; os outros 15 do lote ficaram manter_imagem — maioria eram fotos/
  diagramas/gráficos, confirma a expectativa do plano de que os assuntos grandes restantes —
  Estatística/Área e Perímetro/Geometria Espacial/Pitágoras/Círculo — têm proporção alta de imagem
  genuína). Ledger de ids por lote: `_questao_ids_loteNNN.txt` (só o lote) +
  `_questao_ids_todos_lotes.txt` (acumulado, um lote por linha).
- **Lotes 002-004 aplicados na mesma sessão (2026-07-02)**: 80 parágrafos pendentes processados (lote_002:
  20, lote_003: 20, lote_004: 20 — nota: rodada de 20 imagens nem sempre gera exatamente 1 lote SQL 1:1,
  mas nessa sessão coincidiu), 15 latex_candidato / 65 manter_imagem. Padrões observados: fórmulas puras
  (`\operatorname{senh}`/`\cosh`), tabelas de dados (preço/distância/avaliação), sequências numéricas em
  caixa, e texto de banner promocional (extrai só o texto, ilustração de fundo fica imagem) → sempre
  `latex_candidato`. Diagramas geométricos com medidas (a NORMA nos assuntos grandes restantes), gráficos
  (barra/linha/histograma), fotos/fotoilustrações (mesmo com medidas anotadas, ex. tijolo/jogadores),
  quadrinhos/charges, ícones/pictogramas, cupom fiscal (layout tabular realista) → `manter_imagem`.
- **Lote 005 aplicado (2026-07-02)**: mais 20 pendentes, 6 latex_candidato (questão 8271 teve 2 parágrafos
  de imagem minúsculos só com texto "HD," — provavelmente recorte truncado de uma frase do enunciado;
  transcritos literalmente mas **marcados em `obs` para conferência de contexto na revisão manual**, caso
  raro assim recorrer, sinalizar do mesmo jeito). Estado em 2026-07-02 (fim da sessão): `pendente`=2142,
  `classificado`=98, `inserido`=73, `erro_sem_blob`=10. **5 lotes aplicados nesta sessão (002-005): 100
  parágrafos processados, 21 latex_candidato.**
- **Lote 006 aplicado (2026-07-02, via /loop dinâmico)**: mais 20 pendentes, só 2 latex_candidato (tabelas
  de dados puras; o resto do lote foi 100% diagramas/gráficos — taxa de `manter_imagem` continua alta nos
  assuntos grandes). Estado: `pendente`=2122, `classificado`=116, `inserido`=75, `erro_sem_blob`=10.
  **Tarefa rodando em `/loop` autônomo (auto-pace, ScheduleWakeup) a partir daqui — continua lote a lote
  sem intervenção; usuário não respondeu à pergunta de confirmação em 2026-07-02 então segui o padrão já
  estabelecido em [[project_resolucoes]] (autorização contínua, sem checkpoint por lote).**
- **Lote 007 aplicado (2026-07-02, via /loop)**: mais 20 pendentes, 4 latex_candidato (fórmula de matriz
  por partes, fração textual "área de circulação/7", tabela de dias da semana, tabela trigonométrica
  clássica sen/cos/tg 30-45-60°). Estado: `pendente`=2102, `classificado`=132, `inserido`=79,
  `erro_sem_blob`=10.

- **Lote 008 aplicado (2026-07-02, via /loop)**: mais 20 pendentes, 5 latex_candidato (2 tabelas de dados,
  1 fórmula recursiva de Pn, 1 texto de placa "Estacionamento da Di"). **Gotcha usado: tabela com célula
  mesclada no original (ex. título "Candidato Robert" cobrindo as 4 colunas) — em vez de `\multicolumn`
  (proibido), virou linha própria com só a 1ª célula preenchida e as outras vazias** (`& & &`), mesma
  técnica já registrada acima p/ cabeçalhos mesclados. **Item 10652 flagado em obs**: imagem original
  parece cortada (tabela "Dia/Valor" só mostra até dia 8, pode ter mais linhas fora do crop) — conferir na
  revisão manual. Estado: `pendente`=2082, `classificado`=147, `inserido`=84, `erro_sem_blob`=10.
- **Lote 009 aplicado (2026-07-02, via /loop)**: mais 20 pendentes, 6 latex_candidato. Novo padrão visto:
  imagem com **fórmula/texto + gráfico(s) juntos** (parágrafo 2/questão 12644: texto "Leis de formação" +
  4 mini-gráficos A-D) — mesma solução do banner promocional: extrai só o texto como novo parágrafo,
  imagem inteira (com os gráficos) permanece. Também confirmado: **screenshot de planilha eletrônica
  (Excel-like, com referências de célula/fórmula) fica `manter_imagem`** (mesmo raciocínio do cupom
  fiscal — layout de software é parte do enunciado). Estado: `pendente`=2062, `classificado`=161,
  `inserido`=90, `erro_sem_blob`=10.

## REGRA DO USUÁRIO (2026-07-02) — LER ANTES DE CLASSIFICAR, VALE A PARTIR DE AGORA

Usuário pediu explicitamente para SER MAIS CONSERVADOR na classificação `latex_candidato`, em dois pontos:

1. **Evitar tratar questões cujas imagens têm figura/desenho que o LaTeX não consegue substituir.** Isso
   já era a regra (diagrama geométrico, gráfico, foto, ilustração, ícone, logo, mapa, quadrinho → sempre
   `manter_imagem`) — o usuário está reforçando, então redobrar o cuidado: na dúvida entre "é só texto/
   tabela" ou "tem algum elemento visual relevante", **preferir `manter_imagem`**.
2. **NOVO — evitar tabelas muito largas (muitas colunas, formato horizontal).** Ficam difíceis de
   renderizar bem em LaTeX (não tem `\multicolumn`, quebra em telas estreitas, tabela literal `\begin{array}`
   com 8+ colunas fica ilegível). **A partir de agora: tabela com muitas colunas (a partir de ~6-7
   colunas) → `manter_imagem`, mesmo sendo dado puro.** Tabelas com muitas LINHAS mas poucas colunas
   continuam ok como `latex_candidato` (o problema é largura horizontal, não altura). Não precisa reverter
   os casos já aplicados (ex.: lote_018 parágrafo 24212 tabela 6 colunas, lote_019 parágrafos 26582/26584
   tabelas de 15 colunas cada, lote_027 parágrafo 34981 tabela com 3 colunas mas 11 linhas — essa é ok,
   poucas colunas) — só não fazer mais tabelas largas dora em diante.

## Bug crítico encontrado e corrigido (2026-07-02, durante o lote_010) — LER ANTES DE MEXER NO SCRIPT

**`_gerar_lote.py` tinha um bug de ordenação quando a MESMA questão tem 2+ parágrafos-imagem convertidos
no mesmo lote.** Processando em ordem crescente de `ordem`, o `UPDATE ordem=ordem+n WHERE ordem>=X` do
1º item desloca a posição real dos itens seguintes, mas o script usava o `ordem` ORIGINAL (capturado antes
de qualquer UPDATE) pro 2º item — resultado: o novo parágrafo do 2º item era inserido no lugar errado
(ex.: antes de um diagrama no meio, em vez de imediatamente antes da imagem correta). **Corrigido**:
agora agrupa por `questao_id` e processa cada grupo em ordem DECRESCENTE de `ordem` (a partir do
lote_010 em diante já sai correto). Testado e confirmado com a questão 15569 (4 parágrafos AB/AC/CD/AD)
saindo perfeitamente intercalados.

**Retroativo**: questão **8271** (lote_005) tinha esse bug — corrigido na mão via
`UPDATE paragrafo SET ordem=... WHERE id IN (8277,900000076)` (swap de ordem 4↔5, backup em
`_backup/fix_8271_backup.sql`). **Lote_004 (questões 7523/7530/7537) tinha o mesmo bug em tese, mas
não precisa de fix** — ver achado abaixo.

### Bug irmão (2026-07-02, lote_031): `ordem` do manifest.tsv fica DESATUALIZADO entre lotes diferentes

Quando uma questão já recebeu uma inserção em um lote ANTERIOR, e depois aparece de novo com outro
parágrafo-imagem pendente num lote POSTERIOR, o valor de `ordem` no `manifest.tsv` (gravado na Fase 1,
congelado) **não reflete mais a posição real no banco** (que já foi deslocada pela inserção anterior).
Achado real: questão 38345 recebeu 1 parágrafo no lote_030 (antes do parágrafo 38348, `ordem=1`→2); no
lote_031 o parágrafo 38350 (mesma questão) tinha `ordem=2` no manifest, mas a posição REAL dele no banco
já era `ordem=3` (deslocada pelo insert do lote_030). Se aplicado sem corrigir, o novo texto seria inserido
no lugar errado (antes de 38348 em vez de antes de 38350).

**Regra a partir de agora**: antes de aplicar qualquer `lote_NNN.sql`, para cada `questao_id` do lote,
rodar `SELECT id, ordem, imagemFile_id FROM paragrafo WHERE questao_id=? ORDER BY ordem` e comparar com o
que o `_gerar_lote.py` assumiu (visível no comentário `-- questao_id=... ordem_original=N` de cada bloco).
Se a questão já tiver algum id da faixa alta (900000xxx) ou se o `ordem` real do `paragrafo_imagem_id` no
banco não bater com o `ordem_original` do comentário, **editar `lote_NNN.sql` na mão** trocando os dois
números (o `WHERE ordem >= X` do UPDATE e o `ordem` do INSERT) pelo valor real visto no `SELECT`, antes de
aplicar. Só então rodar o `mysql ... < lote_NNN.sql` e verificar de novo com o mesmo `SELECT`.

## Achado importante: lotes antigos JÁ estão sendo revisados manualmente no app (não é corrupção)

Ao investigar o bug acima, percebi que os parágrafos inseridos no **lote_001 (piloto)** e em parte do
**lote_004** (questões 7523, 7530, 7537, 17169, 22565) **não batem mais com o que o SQL originalmente
inseriu** — imagens e parágrafos novos meus sumiram, e em alguns casos (17169) o texto do LaTeX foi
**incorporado diretamente no parágrafo do enunciado**. Isso NÃO é bug/corrupção: é o **usuário (ou
processo) já revisando manualmente no app** exatamente como o plano previa ("usuário revisa manualmente
depois e ajusta/remove a imagem") — deu tempo real entre o lote_001 (sessão anterior) e agora. **Não
tentar "restaurar" ou re-verificar lotes antigos contra o SQL original** — o estado atual do banco
reflete edição humana legítima, que tem prioridade sobre o que eu apliquei.

**Lição para classificação (aplicar daqui pra frente)**: em pelo menos 3 casos (7523/7526, 7530/7533,
7537/7540) eu classifiquei como `latex_candidato` uma imagem cujo conteúdo (fórmula senh/cosh) **já
estava presente como texto simples em outro parágrafo da mesma questão** (o enunciado, ordem0) — criando
um parágrafo novo redundante. O usuário corrigiu isso deletando a imagem E meu parágrafo duplicado. **A
partir de agora, antes de classificar como `latex_candidato`, conferir se o conteúdo da imagem já não
está escrito em outro parágrafo de texto da mesma questão** (via `SELECT texto FROM paragrafo WHERE
questao_id=? AND texto IS NOT NULL` antes de decidir) — se já estiver, marcar `manter_imagem` com obs
"conteúdo já presente em outro parágrafo como texto — imagem provavelmente redundante, considerar remover
direto na revisão manual" em vez de duplicar.

- **Lote 010 aplicado (2026-07-02, via /loop)**: mais 20 pendentes, 7 latex_candidato — incluindo a
  questão 15569 com 4 parágrafos-imagem de notação de segmento (AB, AC, CD, AD) intercalados com 1
  diagrama, primeiro caso real do bug de ordenação acima (corrigido antes de aplicar, verificado no banco
  como perfeitamente intercalado). Estado: `pendente`=2042, `classificado`=174, `inserido`=97,
  `erro_sem_blob`=10.
- **Lote 011 aplicado (2026-07-02, via /loop)**: mais 20 pendentes, só 2 latex_candidato. Um deles
  (questão 16082, parágrafo 16087) é uma **tabela grande (12 linhas × 9 colunas, dados de área queimada
  por bioma)** — transcrita com cuidado mas marcada com obs pedindo conferência dígito a dígito na revisão
  manual (risco de erro maior em tabelas grandes). Estado: `pendente`=2022, `classificado`=192,
  `inserido`=99, `erro_sem_blob`=10.
- **Lote 012 aplicado (2026-07-02, via /loop)**: mais 20 pendentes, 7 latex_candidato (tabelas de dados,
  2 fórmulas de números complexos, e 2 casos de **foto + dado numérico juntos** — capas de quadrinhos com
  "Total de Páginas" ao lado; capas ficam na imagem, só o número vira parágrafo, mesma solução do banner).
  Estado: `pendente`=2002, `classificado`=205, `inserido`=106, `erro_sem_blob`=10.
- **Lote 013 aplicado (2026-07-02, via /loop)**: mais 20 pendentes, 4 latex_candidato — incluindo a
  questão 18935 com 2 parágrafos-imagem adjacentes (ordem1, ordem2), segundo caso real testado do fix de
  ordenação, confirmado corretamente intercalado no banco (`texto→img→texto→img`). Estado: `pendente`=1982,
  `classificado`=221, `inserido`=110, `erro_sem_blob`=10.
- **Lote 014 aplicado (2026-07-02, via /loop)**: mais 20 pendentes, só 2 latex_candidato. Confirmado que o
  banco tem **questões duplicadas/reaproveitadas** com a mesma tabela de dados (Equipe/Tarefa) repetida em
  ao menos 3 questões diferentes (19754, 20184, 20249) — não é erro meu, é conteúdo do banco reaproveitado
  entre questões. Estado: `pendente`=1962, `classificado`=239, `inserido`=112, `erro_sem_blob`=10.
- **Lote 015 aplicado (2026-07-02, via /loop)**: mais 20 pendentes, 3 latex_candidato — 1 com 2 tabelas
  lado a lado na mesma imagem (Quadro I e Quadro II), viraram 2 parágrafos separados via `<<P>>` (mesma
  técnica de múltiplos parágrafos já usada p/ listas/passos). Mais uma expressão numérica repetida entre
  questões (confirma o padrão de conteúdo reaproveitado). Estado: `pendente`=1942, `classificado`=256,
  `inserido`=115, `erro_sem_blob`=10.
- **Lote 016 aplicado (2026-07-02, via /loop)**: mais 20 pendentes, 9 latex_candidato — incluindo a
  questão 22546 com **5 fragmentos de segmento** (RA/AB/RA/RB/AB, alguns duplicados) + 1 diagrama no final,
  teste de escala do fix de ordenação (5 itens na mesma questão) — **verificado no banco, todos
  intercalados corretamente**. Também mais 1 expressão numérica repetida (padrão já visto) e outra imagem
  com 2 tabelas lado a lado (`<<P>>`). Estado: `pendente`=1922, `classificado`=267, `inserido`=124,
  `erro_sem_blob`=10.
- **Lote 017 aplicado (2026-07-02, via /loop)**: mais 20 pendentes, 5 latex_candidato (2 fragmentos de
  segmento, 2 tabelas de dados, mais 1 repetição da expressão numérica que já apareceu em 4 questões
  diferentes agora). Estado: `pendente`=1902, `classificado`=282, `inserido`=129, `erro_sem_blob`=10.
- **Lote 018 aplicado (2026-07-02, via /loop)**: mais 20 pendentes, 4 latex_candidato (tabela nutricional
  6x6, 2 fragmentos de segmento, 1 texto repetitivo "ARAGUAIA... ARAGUAIA"). Estado: `pendente`=1882,
  `classificado`=298, `inserido`=133, `erro_sem_blob`=10.
- **Lote 019 aplicado (2026-07-02, via /loop)**: mais 20 pendentes, 7 latex_candidato — 1 imagem com 2
  tabelas empilhadas (`<<P>>`), 1 questão (26575) com 2 tabelas largas de 15 dias cada (30 dias de valores
  de ativo, adjacentes na mesma questão — testado o fix de ordenação de novo, ok), fórmulas algébricas
  diversas. Estado: `pendente`=1862, `classificado`=311, `inserido`=140, `erro_sem_blob`=10.
- **Lote 020 aplicado (2026-07-02, via /loop)**: mais 20 pendentes, 8 latex_candidato — 1 tabela com
  células vazias/pontilhadas no original (dado a completar pelo aluno, preservado como `\ldots\ldots`),
  1 texto de citação de fonte junto com a tabela de dados da mesma questão, questão 27933 com 3
  fragmentos de ângulo/segmento (`C\hat{B}G`, `BG`, `CF`). Estado: `pendente`=1842, `classificado`=323,
  `inserido`=148, `erro_sem_blob`=10.
- **Lote 021 aplicado (2026-07-02, via /loop)**: mais 20 pendentes, 5 latex_candidato (fórmulas de números
  complexos, fragmentos de segmento repetidos de padrões já vistos). Estado: `pendente`=1822,
  `classificado`=338, `inserido`=153, `erro_sem_blob`=10.
- **Lote 022 aplicado (2026-07-02, via /loop)**: mais 20 pendentes, 4 latex_candidato (tabela de tipos
  sanguíneos com possível inconsistência no material original — transcrita fielmente e sinalizada, não
  corrigida; tabela de porcentagens; fórmula fracionária; quebra-cabeça de adição criptoaritmética).
  Estado: `pendente`=1802, `classificado`=354, `inserido`=157, `erro_sem_blob`=10.
- **Lote 023 aplicado (2026-07-02, via /loop)**: mais 20 pendentes, 4 latex_candidato (todas tabelas de
  dados puras, incluindo uma com células "-" indicando ausência de dado no dia, preservado literalmente).
  Estado: `pendente`=1782, `classificado`=370, `inserido`=161, `erro_sem_blob`=10.
- **Lote 024 aplicado (2026-07-02, via /loop)**: mais 20 pendentes, só 2 latex_candidato (extrato bancário
  em texto, equação com radicais aninhados). Muitas figuras repetidas (mesmo conjunto de 3 formas
  geométricas aparece em várias questões consecutivas). Estado: `pendente`=1762, `classificado`=388,
  `inserido`=163, `erro_sem_blob`=10.
- **Lote 025 aplicado (2026-07-02, via /loop)**: mais 20 pendentes, 6 latex_candidato (notações de
  conjuntos com subscritos/sobrescritos — sinalizados p/ conferência por serem fragmentos pequenos e
  difíceis de ter 100% de certeza visual; tabela de dados; fórmula aritmética). Estado: `pendente`=1742,
  `classificado`=402, `inserido`=169, `erro_sem_blob`=10.
- **Lote 026 aplicado (2026-07-02, via /loop)**: mais 20 pendentes, 4 latex_candidato (2 fragmentos de
  segmento, mais 2 repetições de expressões já vistas em lotes anteriores). Estado: `pendente`=1722,
  `classificado`=418, `inserido`=173, `erro_sem_blob`=10.
- **Lote 027 aplicado (2026-07-02, via /loop)**: mais 20 pendentes, 4 latex_candidato (banner promocional
  com texto extraído, tabela grande de mortalidade por faixa etária, 2 tabelas de dados simples). Muitos
  gráficos de pizza duplicados nesse trecho (mesmo par de gráficos "Peças vendidas/Calças por tamanho"
  reaparece em 5+ questões seguidas). Estado: `pendente`=1702, `classificado`=434, `inserido`=177,
  `erro_sem_blob`=10.
- **Lote 028 aplicado (2026-07-02, via /loop)**: mais 20 pendentes, só 1 latex_candidato. Confirmado o
  padrão: um par de gráficos de pizza idêntico ("Peças vendidas"/"Calças por tamanho") se repete em
  dezenas de questões seguidas de Estatística — todas `manter_imagem` sem exceção nesse trecho. Estado:
  `pendente`=1682, `classificado`=453, `inserido`=178, `erro_sem_blob`=10.
- **Lote 029 aplicado (2026-07-02, via /loop)**: mais 20 pendentes, 3 latex_candidato (tabela de classes
  de altura usando notação `⊢` de intervalo semiaberto — `\vdash` no LaTeX; matriz 3x3 de leitura difícil,
  sinalizada; fração com radicais). Estado: `pendente`=1662, `classificado`=470, `inserido`=181,
  `erro_sem_blob`=10.
- **Lote 030 aplicado (2026-07-02, via /loop)**: mais 20 pendentes, 5 latex_candidato — incluindo caso
  novo: imagem com diagrama de rotas (cidades + distâncias, gráfico) E uma matriz de adjacência 5x5 em
  texto na mesma imagem; diagrama fica na imagem, só a matriz virou parágrafo (mesma solução do
  banner/foto+dado). Marco: **30 lotes aplicados, 186 questões processadas** desde o início da escalada.
  Estado: `pendente`=1642, `classificado`=485, `inserido`=186, `erro_sem_blob`=10.
- **Usuário pediu (2026-07-02) para ser mais conservador**: evitar `latex_candidato` quando a imagem tem
  elemento visual real, e evitar tabelas muito LARGAS (muitas colunas) — ver regra completa no topo deste
  arquivo. Aplicando a partir do lote_031.
- **Lote 031 aplicado (2026-07-02, via /loop)**: mais 20 pendentes, 3 latex_candidato. **Bug novo
  encontrado e corrigido**: questão 38345 (que já tinha recebido 1 parágrafo no lote_030) tinha `ordem`
  desatualizado no manifest.tsv para seu 2º parágrafo-imagem pendente — corrigido na mão antes de aplicar
  (ver seção "Bug irmão" acima). Verificado no banco: intercalação correta. Estado: `pendente`=1622,
  `classificado`=502, `inserido`=189, `erro_sem_blob`=10.
- **Lote 032 aplicado (2026-07-03)**: mais 20 pendentes, só 3 latex_candidato (2 fórmulas com limites/
  radicais, 1 tabela de notas 5 linhas × 5 colunas incluindo uma célula "n" preservada literalmente — dado
  ausente/ilegível no material original). Resto do lote foi 100% diagramas geométricos, gráficos de barra
  e fotos/ilustrações — confirma o padrão de que os assuntos grandes restantes (Estatística, Geometria)
  seguem majoritariamente `manter_imagem`. Sem casos do bug de `ordem`/questões repetidas neste lote.
  Estado: `pendente`=1602, `classificado`=519, `inserido`=192, `erro_sem_blob`=10.
- **Lote 033 aplicado (2026-07-03)**: mais 20 pendentes, 3 latex_candidato — 1 fórmula de soma de frações
  padronizadas, e questão 42070 com **2 fragmentos de notação de arco/segmento** (`ȮM`/`ȮN`/`OA` e
  `ȮN`/`ȮM`/`MN`, continuação de um enunciado sobre terreno em quadrante circular) intercalados com 1
  diagrama geométrico — testado de novo o fix de ordenação (2 itens na mesma questão em ordens não
  adjacentes: 1 e 2 de um total de 4 paragrafos-imagem, o 3º ficou manter_imagem), verificado no banco como
  perfeitamente intercalado. **Novo símbolo usado**: `\overset{\frown}{XY}` para notação de arco (distinto
  de `\overline{XY}` para segmento reto) — MathJax v2 suporta `\frown` nativamente (símbolo base, não
  precisa de extensão). Resto do lote 100% diagramas/gráficos/screenshots de app. Estado: `pendente`=1582,
  `classificado`=536, `inserido`=195, `erro_sem_blob`=10.
- **Lote 034 aplicado (2026-07-03)**: mais 20 pendentes, 3 latex_candidato — matriz de determinantes com
  5 blocos (enunciado + I/II/III/IV) via `\begin{vmatrix}...\end{vmatrix}` (AMSmath, `<<P>>` para os 5
  parágrafos), e questão 42947 com 2 fragmentos de segmento (`\overline{HI}`, `\overline{FG}`) intercalados
  com 2 diagramas — verificado ordem correta no banco. **Nova decisão conservadora**: parágrafo 42874
  (ilustração de produtos de drogaria com preço ao lado de cada item) marcado `manter_imagem` mesmo
  contendo só texto/números, porque a associação item↔preço depende do desenho (não há rótulo textual
  dizendo o nome de cada produto) — segue a diretriz de conservadorismo do usuário. Também confirmada
  reutilização da mesma figura pitagórica (quadrados 9/16 com lados a/b/c) em 3 questões consecutivas
  (42734, 42745, 42756). Estado: `pendente`=1562, `classificado`=553, `inserido`=198, `erro_sem_blob`=10.
- **Lote 035 aplicado (2026-07-03)**: mais 20 pendentes, 3 latex_candidato (lista I/II/III de radicais via
  `<<P>>`, fragmento de fórmula `f(x)=b+\log_a{x}` continuando enunciado truncado, soma de frações com
  radicais aninhados). **2 casos novos de tabela grande mantidos `manter_imagem` por conservadorismo**:
  balanço financeiro de prefeitura (documento oficial escaneado, 2 sub-tabelas de 2 colunas cada mas com
  20+ linhas de valores monetários precisos — risco de erro de transcrição) e tabela climática de 13
  colunas (indicador + 12 meses, bem acima do limite de ~6-7 colunas da regra do usuário). Estado:
  `pendente`=1542, `classificado`=570, `inserido`=201, `erro_sem_blob`=10.
- **Lote 036 aplicado (2026-07-03, via /loop autônomo)**: mais 20 pendentes, 8 latex_candidato (maior taxa
  em várias rodadas) — 4 tabelas de dados puras (alunos/notas lado a lado com incógnita "?", tamanho/
  quantidade, receita com incógnitas X/Y/Z/W, esportes praticados, dia da semana/horas), 1 fragmento de
  ângulo (`A\hat{D}C`), 1 fórmula de raiz aninhada, e 1 **manchete de notícia sobre desvalorização do Real**
  com fotos de notas de 1994/2019 (padrão banner: só o texto/título virou parágrafo, fotos permanecem
  imagem). Tabela "alunos/notas" marcada com obs pedindo conferência dígito a dígito (2 valores repetidos
  9,1 podem ser confusão de leitura, não necessariamente erro). Estado: `pendente`=1522, `classificado`=582,
  `inserido`=209, `erro_sem_blob`=10.
- **Lote 037 aplicado (2026-07-03, via /loop autônomo)**: mais 20 pendentes, 7 latex_candidato — questão
  50397 (estrela em pentágono) com 2 fragmentos (`\overline{EF}` e soma
  `\overline{AD}+\overline{DB}+\overline{BE}+\overline{EC}+\overline{CA}`) intercalados com o diagrama;
  tabela de tarifa de consumo de água (4 colunas + linha de total sem `\multicolumn`, célula mesclada virou
  1ª coluna preenchida + resto em branco); lista de 3 sequências numéricas via `<<P>>`; tabela de frequência
  com coluna vazia a preencher; fragmento de notação `\mathbb{R}^3`; tabela aprovados/reprovados com
  incógnitas. **Novo caso de tabela larga mantida por conservadorismo**: 8 colunas (Lista1..Lista8) →
  `manter_imagem`. Estado: `pendente`=1502, `classificado`=595, `inserido`=216, `erro_sem_blob`=10.
- **Lote 038 aplicado (2026-07-03, via /loop autônomo)**: mais 20 pendentes, 5 latex_candidato (2 tabelas
  de dados — faixas de duração de CD, movimentação bancária com "Salto total" transcrito literalmente
  como aparece no original mesmo parecendo typo de "Saldo total" —, fragmento de fórmula trigonométrica,
  soma de frações conjugadas com radicais, radical aninhado infinito). Resto do lote foi diagramas/
  gráficos/fotos (embalagem de chocolate, placa de carro, tabuleiro de xadrez, submarino/iceberg). Estado:
  `pendente`=1482, `classificado`=610, `inserido`=221, `erro_sem_blob`=10.
- **Lote 039 aplicado (2026-07-03, via /loop autônomo)**: mais 20 pendentes, 5 latex_candidato (tabela de
  medalhas olímpicas, manchete de notícia sobre temperatura, tabela de despesas por percentual, fragmento
  de fórmula `g(x)`, e **foto de cartaz físico de lanchonete** com erro proposital "300 m²" em vez de mL —
  o próprio erro é o objeto da questão, preservado literalmente). **Novo caso de redundância detectado**:
  parágrafo 54927 (fragmento `TR²/(t+5)³`) já estava presente por completo no parágrafo de texto 54923
  da mesma questão (`i=2000·TR²/(t+5)³`) — marcado `manter_imagem` com obs em vez de duplicar, seguindo a
  regra de checar redundância antes de classificar. Estado: `pendente`=1462, `classificado`=625,
  `inserido`=226, `erro_sem_blob`=10.
- **Lote 040 aplicado (2026-07-03, via /loop autônomo)**: mais 20 pendentes, só 2 latex_candidato (2
  tabelas de dados puras — decaimento de antibiótico no sangue, salários por faixa de funcionários). Resto
  do lote 100% diagramas geométricos/gráficos/mapas. **Gotcha evitado**: ao transcrever a tabela de
  decaimento (216/108/54/13,5 para t=0,1,2,4), quase adicionei uma linha t=3 inferida pelo padrão
  geométrico que não existe no material original (a tabela pula de t=2 para t=4 de propósito) — conferido
  a imagem de novo antes de gerar o lote e corrigido para transcrever fielmente sem inventar dado. Estado:
  `pendente`=1442, `classificado`=643, `inserido`=228, `erro_sem_blob`=10.
- **Lote 041 aplicado (2026-07-03, via /loop autônomo)**: mais 20 pendentes, 7 latex_candidato (5 tabelas
  de dados puras — alíquotas IRPF, escolaridade por secretaria, acertos com incógnita x, regra Ana/Bruno,
  covid por município — e questão 58880 com 2 itens: fragmento de fórmula `\sum_{i=n-6}^{n}C_i/7`
  continuando a definição de MVn, e tabela de 11 dias × 2 bairros; ambos intercalados corretamente com o
  restante da questão, testado mais uma vez o fix de ordenação). Estado: `pendente`=1422, `classificado`=656,
  `inserido`=235, `erro_sem_blob`=10.
- **Lote 042 aplicado (2026-07-03, via /loop autônomo)**: mais 20 pendentes, 8 latex_candidato (tabela de
  ações 10 linhas, fórmula LP=(VF-VA)/VA, tabela de escolaridade reutilizada de outra questão, raiz cúbica
  de porcentagem+fração, sistema de 2 retas, tabela de imóveis 5 colunas, tabela de meses 6 colunas, limite
  trigonométrico). **2 novos casos de redundância detectados** (mesmo padrão do lote_039): parágrafo 59304
  (fórmula `x²/4-32x/5-17=0`) e parágrafo 59793 (fórmula `\sqrt[5]{(2^31+2^34)/18}`) já idênticos aos
  parágrafos de texto das respectivas questões — marcados `manter_imagem` com obs em vez de duplicar.
  Estado: `pendente`=1402, `classificado`=668, `inserido`=243, `erro_sem_blob`=10.
- **Lote 043 aplicado (2026-07-03, via /loop autônomo)**: mais 20 pendentes, 10 latex_candidato (maior taxa
  até agora) — tabela log(x), tabela de correspondência letras↔números A-Z (reutilizada em 2 questões
  irmãs de criptografia com matrizes), matrizes A e B, tabela salário/colaboradores, sistema de equações
  com `\mathbb{N}`, adição criptoaritmética FG+23=99, ficha técnica de coquetel, tabela do Tangram (imagem
  combinava tabela+ilustração colorida — extraída só a tabela, ilustração permanece na imagem), tabela de
  empréstimos de biblioteca 4 colunas. **2 novas redundâncias detectadas** (matriz de rotação 3x3 e fórmula
  de radical, ambas já completas no parágrafo de texto da questão) — marcadas `manter_imagem` com obs.
  Estado: `pendente`=1382, `classificado`=678, `inserido`=253, `erro_sem_blob`=10.
- **Lote 044 aplicado (2026-07-03, via /loop autônomo)**: mais 20 pendentes, só 2 latex_candidato (fórmula
  de notação científica; lista de 15 idades — **novo padrão**: transcrita como sequência de números em
  texto corrido `\(85, 94, ...\)`, sem `\begin{array}`, para evitar o problema de "tabela larga" já que
  não é uma grade real, é só uma lista horizontal — assim quebra linha naturalmente como texto). Resto do
  lote 100% diagramas/gráficos, com 3 pares de figuras reutilizadas entre questões (triângulo 50°/70°,
  circunferência C=(4,3)/P=(6,5), trapézio 4/8/3cm). Estado: `pendente`=1362, `classificado`=696,
  `inserido`=255, `erro_sem_blob`=10.
- **Lote 045 aplicado (2026-07-03, via /loop autônomo)**: mais 20 pendentes, 5 latex_candidato (fórmula de
  somatório PG, tabela de pontos de 2 retas, tabela de população por ano, tabela de embalagens — reutilizada
  em 2 questões). **Novo caso de tabela larga**: 7 colunas (atrasos × empregados) mantida `manter_imagem`
  por atingir o limite. Confirmado padrão CESPE/CEBRASPE nesse trecho: várias questões com `ordem=0` para a
  imagem (imagem vem ANTES do texto "com base na figura acima, julgue o item") e conjuntos de 3 questões
  irmãs reaproveitando a mesma figura (trapézio hachurado, paralelepípedos sobrepostos). Estado:
  `pendente`=1342, `classificado`=711, `inserido`=260, `erro_sem_blob`=10.
- **Lote 046 aplicado (2026-07-03, via /loop autônomo)**: mais 20 pendentes, 6 latex_candidato (tabela de
  pesos de espécies de coruja, tabela com célula de cabeçalho diagonal simplificada como "Estudantes /
  Alturas (cm)", fórmula de série infinita, determinantes, e 2 fórmulas fracionárias que completam
  perguntas de um bloco de 4 questões-irmãs CESPE reaproveitando a mesma figura de trapézio ABDC). Também
  achado: questão sobre poema "A Corujinha" com 29 parágrafos de texto (letra de música linha a linha) +
  1 imagem de coruja feita de polígonos regulares — confirma que textos longos (poemas/letras) são
  quebrados em muitos parágrafos curtos no banco. Estado: `pendente`=1322, `classificado`=725,
  `inserido`=266, `erro_sem_blob`=10.
- **Lote 047 aplicado (2026-07-03, via /loop autônomo)**: mais 20 pendentes, 11 latex_candidato (fórmula
  algébrica, tabela risco/retorno de ativos, tabela de notas por unidade, os 3 fragmentos de fórmula da
  questão 71997 (itens I/II/III intercalados corretamente), tabela de população mundial, 2 tabelas de
  intervalos de classe com notação `⊢` (uma reutilizada em 2 questões), tabela de vendas de carros por
  intervalo, e uma tabela densa de 6 colunas (peso/fi/Xi/fi.Xi/Fac/fi.Xi²) com texto+título+fonte via
  `<<P>>`, marcada com obs pedindo conferência dígito a dígito). **Achado**: item III da questão 71997
  (fórmula `(9CD-5C+98)/(15CD+20D-5)`) não bate com o valor 15 afirmado no enunciado para C=-1,D=2 (dá 17)
  — sinalizado em obs para o usuário verificar se é erro de leitura da imagem ou do material original,
  sem alterar a transcrição. **Novo caso de conservadorismo**: diagrama de ramos e folhas (91 candidatos)
  mantido `manter_imagem` por ser denso e de alto risco de erro dígito a dígito, mais um caso de gráfico de
  pizza reutilizado (5ª vez consecutiva da mesma figura feminino 80%/masculino 20% em questões CESPE
  diferentes). Estado: `pendente`=1302, `classificado`=734, `inserido`=277, `erro_sem_blob`=10.
- **Lote 048 aplicado (2026-07-03, via /loop autônomo)**: mais 20 pendentes, 4 latex_candidato — **novo
  padrão**: foto de rótulo de informação nutricional (tabela padrão em embalagem real, extraída por
  completo com título/subtítulo/tabela/notas via `<<P>>`, mesmo sendo uma fotografia, porque o conteúdo é
  puramente tabular sem elemento gráfico relevante); tabela de movimentação bancária; tabela verduras
  favoritas (conjuntos combinados); tabela número de crianças por residência. **Novo caso de tabela larga**:
  tabela de operação (Cayley/multiplication table) 7×7 para conjunto G mantida `manter_imagem` por atingir
  o limite de colunas, apesar de ser conteúdo matemático relevante — mesma regra aplicada a tabelas de
  dados comuns. Estado: `pendente`=1282, `classificado`=750, `inserido`=281, `erro_sem_blob`=10.
- **Lote 049 aplicado (2026-07-03, via /loop autônomo)**: mais 20 pendentes, 4 latex_candidato (tabela de
  idades com incógnita "?", tabela de frequência de partidas, tabela de horários com lacunas I/II/III,
  tabela moças/rapazes por idade — 2 delas tinham título mesclado sobre as 2 colunas do original;
  **quase usei `\multicolumn` para isso e corrigi antes de aplicar** — título mesclado vira 1ª célula
  preenchida + resto vazio, igual à técnica já usada para cabeçalhos mesclados). Também 2 ícones pequenos
  de quadrado vazio (possível artefato de digitalização, sem conteúdo matemático) mantidos `manter_imagem`,
  e mais uma tabela larga (8 colunas, com ícone decorativo ocultando o último valor) mantida por
  conservadorismo. Estado: `pendente`=1262, `classificado`=766, `inserido`=285, `erro_sem_blob`=10.
- **Lote 050 aplicado (2026-07-03, via /loop autônomo)**: marco de 50 lotes desde o início da escalada.
  Mais 20 pendentes, só 3 latex_candidato (tabela de rentabilidade da poupança com fonte/link via `<<P>>`,
  2 fragmentos de notação isolados — `\in\mathbb{R}` e `\alpha` — completando enunciados truncados). Resto
  do lote 100% diagramas/gráficos, incluindo um caso de tabela+gráfico combinados onde a própria comparação
  entre eles é o objeto da questão (mantido como imagem única). Estado: `pendente`=1242, `classificado`=783,
  `inserido`=288, `erro_sem_blob`=10.
- **Lote 051 aplicado (2026-07-03, via /loop autônomo)**: mais 20 pendentes, 10 latex_candidato — fórmula
  de função quadrática, 3 fragmentos de notação da mesma questão (`\overline{AB}`, `\overline{BC}`,
  `A\hat{B}C`), tabela de salários, e uma tabela "valores em R\$ por m²" (5 colunas, título mesclado sem
  `\multicolumn`) **reutilizada em 5 questões CESPE diferentes** (82528/82535/82542/82549/82556, cada uma
  julgando uma afirmação estatística distinta sobre a mesma amostra de 10 imóveis). Também confirmado bloco
  de 3 questões-irmãs reaproveitando mapa do Acre em plano cartesiano, e outro par reaproveitando planta
  baixa com regiões A-F. Estado: `pendente`=1222, `classificado`=793, `inserido`=298, `erro_sem_blob`=10.
- **Lote 052 aplicado (2026-07-03, via /loop autônomo)**: mais 20 pendentes, 8 latex_candidato — 2 pares
  de questões-irmãs reaproveitando texto+diagrama de área de preservação ambiental e texto+tabela de
  leptospirose (cabeçalho "casos" mesclado sobre notificados/confirmados, tratado sem `\multicolumn`);
  cartaz de cotação de câmbio (padrão banner); sequência de frações (10/5, 279/93, ...); tabela de IMC com
  título mesclado; tabela de vacinação infantil. Estado: `pendente`=1202, `classificado`=805, `inserido`=306,
  `erro_sem_blob`=10.
- **Lote 053 aplicado (2026-07-03, via /loop autônomo)**: mais 20 pendentes, 10 latex_candidato — lista de
  4 proposições sobre radiciação (I-IV, via `<<P>>`), tabela de população residente com título mesclado,
  fragmento de soma de segmentos, 2 tabelas de dados (veículos/consumo, utensílios/água), e **questão 85292
  com 5 fragmentos de notação de segmento em sequência** (MN, AB, AB, OP, MN) — maior cadeia de fragmentos
  processada até agora, testado o fix de ordenação em escala maior (5 itens intercalados corretamente).
  Estado: `pendente`=1182, `classificado`=815, `inserido`=316, `erro_sem_blob`=10.
- **Lote 054 aplicado (2026-07-03, via /loop autônomo)**: mais 20 pendentes, 9 latex_candidato — 4
  fragmentos de segmento (AB, AB, AC, BD), tabela do quadrado mágico, tabela com incógnita, **foto de
  etiqueta de preço de supermercado** (código de barras permanece na imagem, dados textuais extraídos em
  6 parágrafos via `<<P>>` — data/hora/validade/lote/tara/preço + peso + peso drenado "???" + total), lista
  de 4 proposições de radiciação (algumas falsas, transcritas literalmente), tabela de latas/preços. Estado:
  `pendente`=1162, `classificado`=826, `inserido`=325, `erro_sem_blob`=10.
- **Lote 055 aplicado (2026-07-03, via /loop autônomo)**: mais 20 pendentes, 6 latex_candidato — destaque
  para uma **derivação matemática completa de 2 alunos (Ana e Danilo) comparando simplificação de
  expressão algébrica**, transcrita em 13 novos parágrafos via `<<P>>` (passo a passo de cada aluno até
  chegar em x+2); mais 4 tabelas de dados (acertos/alunos, mapeamento numérico, faixa etária/atividades,
  hotéis/preços, tipos de laranja). Estado: `pendente`=1142, `classificado`=840, `inserido`=331,
  `erro_sem_blob`=10.
- **Lote 056 aplicado (2026-07-03, via /loop autônomo)**: mais 20 pendentes, 12 latex_candidato — **3
  questões com cadeias de 3 fragmentos de segmento cada** (XY/YZ/XZ, PR/AD/RS, AB/BC/AC), todas
  intercaladas corretamente junto com o diagrama de cada uma (fix de ordenação testado em 3 casos
  simultâneos no mesmo lote); mais 3 tabelas de dados (consumo de água por dia com frações do restante,
  preço por mês, litros por marca). Estado: `pendente`=1122, `classificado`=848, `inserido`=343,
  `erro_sem_blob`=10.
- **Lote 057 aplicado (2026-07-03, via /loop autônomo)**: mais 20 pendentes, 7 latex_candidato — tabela de
  anagramas ABA, questão 88984 com 3 fragmentos de segmento (AB, A'B', AB) sobre redução de figura,
  derivação completa de resolução de aluno (equação `(x+3)²=(2x-1)²`, 5 passos via `<<P>>` até S={4}), e
  questão 89048 com 2 fragmentos de segmento (CD, AD). Estado: `pendente`=1102, `classificado`=861,
  `inserido`=350, `erro_sem_blob`=10.
- **Lote 058 aplicado (2026-07-03, via /loop autônomo)**: mais 20 pendentes, 4 latex_candidato (tabela
  suco/preço com incógnita, e a **tabela de definições nutricionais reutilizada em 3 questões CESPE**
  89830/89841/89852 — gordura/energia/açúcar com sub-linhas, célula vertical mesclada representada com
  1ª coluna vazia na 2ª sub-linha, sem `\multicolumn`/`\multirow`). Resto do lote 100% diagramas/gráficos/
  fotos. Estado: `pendente`=1082, `classificado`=877, `inserido`=354, `erro_sem_blob`=10.
- **Lote 059 aplicado (2026-07-03, via /loop autônomo)**: mais 20 pendentes, 3 latex_candidato (texto+tabela
  de atendimentos odontológicos com cabeçalho mesclado, e um **anúncio ilustrado reutilizado em 2 questões**
  com preços de calculadora/caneta/borracha/lapiseira + oferta de pacote — padrão banner, ilustrações dos
  produtos permanecem na imagem). Confirmado bloco de 4 questões-irmãs CESPE reaproveitando o mesmo gráfico
  EMBRAER, e 2 pares reaproveitando mapa de Roraima e planta de casa. **Caso de conservadorismo**: tabela de
  instrumentos odontológicos com ilustrações de cada peça mantida `manter_imagem` (elemento visual
  genuíno por item, mesmo com preços já no texto). Estado: `pendente`=1062, `classificado`=894,
  `inserido`=357, `erro_sem_blob`=10.
- **Lote 060 aplicado (2026-07-03, via /loop autônomo)**: 60 lotes desde o início da escalada. Mais 20
  pendentes, 8 latex_candidato — tabela de salários reutilizada em 2 questões, **receita culinária
  "Canja de Galinha" reutilizada em 3 questões** (texto puro com frações mistas via `\tfrac`, 11 parágrafos
  cada via `<<P>>`, sem diagrama), questão 91087 com 2 fragmentos de segmento (DE, DE) intercalados com o
  diagrama, e tabela de população indígena por estado/ano. Estado: `pendente`=1042, `classificado`=906,
  `inserido`=365, `erro_sem_blob`=10.
- **Lote 061 aplicado (2026-07-04)**: mais 20 pendentes, 6 latex_candidato — tabela gols/seleções, banner
  de promoção de supermercado (texto puro em caixa, sem ilustração, 6 parágrafos via `<<P>>`), tabela
  profundidade/temperatura do oceano, **matriz 3x4 (nutrientes×porções A-D) com vetor coluna de
  percentuais 4x1** (matriz densa, marcada com obs pedindo conferência dígito a dígito), tabela cargo/
  salário/funcionários com incógnita X, e fragmento `\overline{AB}` completando "sendo AB = 6 cm"
  truncado no enunciado. Resto do lote foi diagramas/gráficos genuínos (grafo, pentágonos+triângulo,
  gráfico de pizza, pista com semicírculos, gráfico de 5 retas, círculo com 4 círculos inscritos, cubos
  3D, gráfico de barras, círculos tangentes, região sombreada de inequação, cruz em quadrado). Nenhum caso
  de redundância ou drift de `ordem` neste lote (nenhuma das 6 questões tinha inserção anterior). Estado:
  `pendente`=1022, `classificado`=920, `inserido`=371, `erro_sem_blob`=10.
- **Lote 062 aplicado (2026-07-04)**: mais 20 pendentes, 7 latex_candidato — tabela palestra/participantes,
  **tabela de prova "2=1" (falácia algébrica clássica, 7 passos I-VII com descrição+fórmula)**, lista de
  4 proposições com checkbox `( )` estilo CESPE, **4 itens de determinantes/matrizes (I-IV) — imagem
  ampliada 4x com PIL antes de transcrever por ser densa (290x291px original), confirmado `2^{-1}` como
  pivô da diagonal (produto dos pivôs bate com o resultado dado, -3)**, **legenda de símbolos lógicos
  (⇒/⇔/∧/∨/XOR sublinhado/¬) reutilizada em 2 questões-irmãs** (93406/93417 — este último tinha 2ª imagem
  de paralelepípedo com unidades mistas mm/cm/dm mantida `manter_imagem`, sem problema de ordenação pois só
  1 das 2 imagens da questão virou latex_candidato), tabela número de dias/temperatura. **Caso de
  conservadorismo**: gráfico de temperatura por dia com valores anotados no próprio gráfico mantido
  `manter_imagem` (evitar tabela de 11 colunas). Estado: `pendente`=1002, `classificado`=933,
  `inserido`=378, `erro_sem_blob`=10.
- **Lote 063 aplicado (2026-07-04, via /loop 3min)**: mais 20 pendentes, 9 latex_candidato — 3 limites
  (I-III), tabela órgão/projetos, 2 mini-tabelas de setores (4 colunas cada, via `<<P>>`), tabela caixas/
  arestas, tabela janeiro/fevereiro/março, banner de boleto (texto puro), tabela de classificação de
  IMC por sexo, e **tabela "Eleitores segundo sexo e faixa etária" com título+citação de fonte
  (TSE/ipardes.gov.br) reutilizada em 2 questões** (94365/94376) — imagem ampliada 3x com PIL para
  confirmar a URL da citação com precisão. Confirmado (94073) que o rodapé "Fonte: Ministério da Saúde"
  já existia como parágrafo de texto separado, então não foi duplicado na tabela extraída. Estado:
  `pendente`=982, `classificado`=944, `inserido`=387, `erro_sem_blob`=10.
- **Lote 064 aplicado (2026-07-04, via /loop 3min)**: mais 20 pendentes, 7 latex_candidato — tabela
  população residente (título+tabela+fonte IBGE via `<<P>>`), tabela tipo de produto/preço, tabela de
  frequências (linha 1 duplicada `0,3,3` no material original, confirmada com zoom 3x e transcrita
  literalmente sem corrigir), **questão 95415 com 2 fragmentos `\overline{AB}` intercalados com o
  diagrama** (mesmo segmento citado 2x no enunciado — "o segmento ___ é tangente..." e "determine a
  medida de ___" — não é redundância, testado mais uma vez o fix de ordenação com sucesso), questão 95428
  com fragmento `\overline{AB}\equiv\overline{AC}`, e questão 95471 com fragmento `\overline{MN}\parallel
  \overline{BC}` (**esta questão tem mais 3 imagens pendentes em ordens posteriores, não incluídas neste
  lote — corrigido proativamente o `ordem` no manifest.tsv dessas 3 linhas, de 2/4/5 para 3/5/6, para
  evitar o "bug irmão" de drift quando forem processadas em lote futuro**). **2 casos de tabela larga
  mantidos `manter_imagem`** (13 colunas: número final do NIS × 12 meses, reutilizada em 2 questões).
  Estado: `pendente`=962, `classificado`=957, `inserido`=394, `erro_sem_blob`=10.
- **Lote 065 aplicado (2026-07-04, via /loop 3min)**: mais 20 pendentes, 9 latex_candidato — **questão
  95471 finalizada** (últimos 2 fragmentos `\overline{AM},\overline{MB},\overline{AN}\text{ e }\overline{NC}`
  e `\overline{BC}\text{ e }\overline{MN},`, completando as 6 posições de imagem dessa questão iniciada no
  lote_064), fragmento `\overline{AB}` (questão 95487), fragmento `\overline{AB}` (questão 95812),
  fragmento `D\hat{A}E` (questão 95831), e **4 dos 5 fragmentos de segmento da questão 95852**
  (`\overline{AB}`, `\overline{AE}`, `\overline{BC}`, `\overline{AD}` — o 5º, paragrafo 95863 em ordem
  original 5, não estava neste lote de 20; **corrigido proativamente no manifest.tsv de ordem=5 para
  ordem=9** para refletir o deslocamento real no banco, evitando o "bug irmão" quando for processado).
  Resto do lote foi diagramas/gráficos genuínos (triângulos, infográfico de pirâmide de renda, gráficos
  de barras, quadrilátero, octógono inscrito, diagrama 3D com ângulos). Estado: `pendente`=942,
  `classificado`=968, `inserido`=403, `erro_sem_blob`=10.
- **Lote 066 aplicado (2026-07-04, via /loop 3min)**: mais 20 pendentes, 11 latex_candidato — **achado
  novo: caixa "FÓRMULAS:" de referência (fórmula de Bhaskara, Pitágoras, volume do paralelepípedo)
  reutilizada como ordem0 em 8 questões diferentes deste lote** (96144,96159,96169,96179,96192,96207,
  96217,96227 — assuntos variados: Regra de Três, Pitágoras, Equações 2º Grau, Potenciação,
  geometriaEspacial, Divisibilidade — parece ser um rodapé/cheat-sheet anexado a várias questões do
  mesmo import), fragmentos `\overline{AP}`/`\overline{RQ}` (questão 95869) e `\overline{OA}` (questão
  96144, imagem ampliada 5x). **Achado de posição**: questão 96179 tem a caixa de fórmulas em ordem0 mas
  a "figura abaixo" citada no texto (rota de avião, pontos E/A/B/P/C) só aparece em ordem7 (bem depois do
  texto que a menciona) — mesmo padrão de dessincronia texto/figura já visto antes, não é erro nosso.
  Resto do lote foi diagramas/gráficos genuínos. Estado: `pendente`=922, `classificado`=977,
  `inserido`=414, `erro_sem_blob`=10.
- **Lote 067 aplicado (2026-07-04, via /loop 3min)**: mais 20 pendentes, 7 latex_candidato — **tabela
  "Ofertas da semana" (produto/preço por kg) reutilizada em 2 questões** (96601/97069), tabela nota/
  quantidade de alunos (com "?" a completar), tabela atividade/dias da semana/horas por dia (turno
  infantil), tabela cargo/homens/mulheres/total, e **tabela "Classificação/IMC" reutilizada em 2
  questões** (97178/97204). Resto do lote foi diagramas/gráficos genuínos, incluindo 3 imagens da mesma
  quadra de basquete reutilizada (questão 97239) e um ícone/pictograma abstrato (barco+sol). Estado:
  `pendente`=902, `classificado`=990, `inserido`=421, `erro_sem_blob`=10.
- **Lote 068 aplicado (2026-07-04, via /loop 3min)**: mais 20 pendentes, 6 latex_candidato — **lista de
  coordenadas `O=(0,0), A=(1/4,30), B=(1,30), C=(5/4,25) e D=(3/2,40)` reutilizada em 3 questões-irmãs**
  (97281/97295/97309, mesmo texto-base sobre prova de ciclismo Ana e Bernardo, cada uma com pergunta
  final diferente), tabela dia/distância percorrida, fragmentos `\overline{DF}` (questão 97834) e
  `\overline{DE}` (questão 97845 — **esta tem mais 3 fragmentos pendentes em ordens posteriores,
  corrigido proativamente o `ordem` no manifest.tsv de 2/3/4 para 3/4/5**). Resto do lote foi diagramas/
  gráficos genuínos, incluindo reaproveitamento de figuras já vistas (quadra de basquete, trajetória
  parabólica, gráfico velocidade×tempo) e um gráfico institucional com logos (Subprocuradoria-Geral).
  Estado: `pendente`=882, `classificado`=1004, `inserido`=427, `erro_sem_blob`=10.
- **Lote 069 aplicado (2026-07-04, via /loop 3min)**: mais 20 pendentes, 12 latex_candidato — **questão
  97845 finalizada** (últimos 2 fragmentos `\overline{AB}`/`\overline{DE}`, completando os 5 itens dessa
  questão iniciada no lote_068), **questão 97876 com 7 fragmentos de ângulo/segmento intercalados com o
  diagrama** (`A\hat{B}C`, `A\hat{C}B`, `\overline{AH}`, `B\hat{A}C`, `\overline{CS}`, `A\hat{C}H`,
  `A\hat{S}C` — maior cadeia de fragmentos testada até agora no fix de ordenação, verificado no banco
  como perfeitamente intercalado; **a 1ª leitura do fragmento 97879 achou que era só "ABC" mas o zoom 5x
  revelou um circunflexo sobre o B — é o ângulo `A\hat{B}C`, não o nome do triângulo**), tabela número de
  peças/custo, tabela de função X/Y com incógnitas A/B/C, e **lista de 15 números em fundo de quadro-negro
  decorativo** (fonte cursiva estilizada, ampliada 4x para confirmar dígitos, transcrita como lista
  inline em vez de tabela). Estado: `pendente`=862, `classificado`=1012, `inserido`=439,
  `erro_sem_blob`=10.
- **Lote 070 aplicado (2026-07-04, via /loop 3min)**: mais 20 pendentes, só 4 latex_candidato (menor taxa
  em várias rodadas — lote dominado por Área e Perímetro/Pitágoras/Semelhança, todos diagramas
  geométricos genuínos) — tabela mês/dias trabalhados, tabela disciplinas×bimestres (cabeçalho
  "Bimestres" mesclado sobre 4 colunas no original, resolvido com título separado + colunas 1º-4º diretas,
  sem `\multicolumn`), tabela faixa de consumo de água/tarifa (linha de total mesclada virou parágrafo
  separado), tabela número de alunos/notas com incógnita X. **Caso de conservadorismo**: 3 quadrados de
  tamanhos visivelmente diferentes rotulados só com a área (576/324/144 cm²) mantidos `manter_imagem`
  pois o tamanho relativo dos quadrados é informação visual, não só texto. Estado: `pendente`=842,
  `classificado`=1028, `inserido`=443, `erro_sem_blob`=10.
- **Lote 071 aplicado (2026-07-04, via /loop 3min)**: mais 20 pendentes, só 3 latex_candidato (tabela
  item/quantidade/valor unitário, tabela marcas/unidades produzidas/percentual vendido com incógnitas X/Y,
  tabela avaliação/peso). Resto do lote 100% diagramas/gráficos genuínos — inclui um screenshot de
  software GeoGebra (janela completa com barra de tarefas, mantido como imagem por ser literalmente uma
  captura de tela) e mais um caso de comparação visual de tamanho (2 quadrados A/B "fora de escala")
  mantido `manter_imagem` pelo mesmo critério do lote_070. Estado: `pendente`=822, `classificado`=1045,
  `inserido`=446, `erro_sem_blob`=10.
- **Lote 072 aplicado (2026-07-04, via /loop 3min)**: mais 20 pendentes, só 1 latex_candidato (tabela
  experimento/x/y). Menor taxa de conversão registrada até agora — os outros 19 foram 100% diagramas/
  gráficos genuínos (dispersão, parábolas, cone, dodecaedro planificado, prédio+trajetória, retas com
  ângulos, histogramas, pizza). **1 caso de tabela larga com células mescladas mantido `manter_imagem`**
  (nota/conceito, 8 colunas: 0-7, com "Péssimo/Ruim/Regular/Bom/Ótimo" mesclados sobre faixas de colunas —
  excede o limite e teria problema de `\multicolumn`). Estado: `pendente`=802, `classificado`=1064,
  `inserido`=447, `erro_sem_blob`=10.
- **Lote 073 aplicado (2026-07-04, via /loop 3min)**: mais 20 pendentes, só 2 latex_candidato — **ambas
  reutilizações de tabelas já vistas** (número de alunos/notas, igual à questão 99663/99667; marca/
  quantidade de lenços/tamanho/valor). Resto do lote 100% diagramas/gráficos/fotos genuínos, incluindo
  reaproveitamento de figuras já vistas (3 quadrados de área, gráfico de percentual por região) e um
  infográfico complexo de times de futebol com logos e mini-gráficos de pizza. Estado: `pendente`=782,
  `classificado`=1082, `inserido`=449, `erro_sem_blob`=10.
- **Lote 074 aplicado (2026-07-04, via /loop 3min)**: mais 20 pendentes, só 2 latex_candidato — tabela
  funcionários/salário, e **infográfico complexo "Entenda a mudança" (Sanepar, tarifa de água)** com
  título+texto+2 tabelas lado a lado (ANTES/HOJE) + fonte, 9 parágrafos via `<<P>>` — a coluna MÍNIMO/
  EXCEDENTE tinha células mescladas verticalmente (rowspan) no original, resolvida repetindo o texto em
  cada linha em vez de `\multirow` (que também não existe no MathJax v2, mesma lógica já aplicada a
  `\multicolumn`). Resto do lote 100% diagramas/gráficos/fotos genuínos, incluindo screenshot de planilha
  eletrônica e reaproveitamento de figura já vista (triângulo com região sombreada, igual a 104268).
  Estado: `pendente`=762, `classificado`=1100, `inserido`=451, `erro_sem_blob`=10.
- **Lote 075 aplicado (2026-07-04, via /loop 3min)**: mais 20 pendentes, só 3 latex_candidato — tabela
  de meses com a maioria das células vazias (a preencher pelo aluno, só Janeiro preenchido), fragmento
  `\lambda` (nome de circunferência continuando o enunciado), fragmento `\overline{AB}\text{ e
  }\overline{CD}` (vetores). Resto do lote 100% diagramas/gráficos genuínos. **1 caso de tabela larga**
  (resultado de enquete com colunas A/B/C/AeB/AeC/BeC/AeBeC = 8 colunas) mantido `manter_imagem` por
  exceder o limite. Estado: `pendente`=742, `classificado`=1117, `inserido`=454, `erro_sem_blob`=10.
- **Lote 076 aplicado (2026-07-04, via /loop 3min)**: mais 20 pendentes, só 3 latex_candidato — fórmula
  de proporção `24,5/32 = a/b'`, equação de número complexo `5z+\bar{z}=12+16i`, e **grade 3×3 tipo
  quebra-cabeça (quadradinhos a preencher com números)**, mesmo padrão já usado para "quadrado mágico" em
  lote anterior. Resto do lote 100% diagramas/gráficos/fotos genuínos, incluindo reaproveitamento de
  gráfico já visto (destino das exportações) e uma foto de placa de carro com fonte citada. Estado:
  `pendente`=722, `classificado`=1134, `inserido`=457, `erro_sem_blob`=10.
- **Lote 077 aplicado (2026-07-04, via /loop 3min)**: mais 20 pendentes, 4 latex_candidato — fragmentos
  de ângulo `C\hat{X}Y` (questão 111902, hexágono+quadrado) e `B\hat{H}C` (questão 111914, ambos com o
  padrão de arco largo sobre as 3 letras, confirmados com zoom 5x), fragmento `\mathbb{R}\text{ em
  }\mathbb{R}` (letra R em blackboard bold, "função f de ℝ em ℝ", confirmado com zoom 6x), e tabela
  médico por especialidade/sexo. Resto do lote 100% diagramas/gráficos genuínos. Estado: `pendente`=702,
  `classificado`=1150, `inserido`=461, `erro_sem_blob`=10.
- **Lote 078 aplicado (2026-07-04, via /loop 3min)**: mais 20 pendentes, 7 latex_candidato — tabela
  semana1/semana2 (máquinas/peças/carga horária), fragmento `\overline{BD}`, tabela idade/entrevistados,
  **tabela de 2 portfólios (Ativo/Proporção/Beta) com cabeçalhos mesclados** (ampliada 3x para corrigir
  um dígito mal lido na 1ª leitura — Beta do ativo b no Portfólio 2 é -0,25, não -1,25), tabela nº de
  pedaços/comprimento, e **2 dos 6 símbolos de uma "lista de símbolos" nova variante** (questão 115956:
  aqui cada símbolo já vem como imagem separada intercalada com o rótulo em texto, diferente do padrão
  anterior de imagem única — `\Rightarrow`/Condicional e `\Leftrightarrow`/Bicondicional convertidos;
  **os outros 4 símbolos (∧/∨/XOR/¬) ficaram pendentes em ordens posteriores, corrigido proativamente o
  `ordem` no manifest.tsv de 6/8/10/12 para 8/10/12/14**). Estado: `pendente`=682, `classificado`=1163,
  `inserido`=468, `erro_sem_blob`=10.
- **Lote 079 aplicado (2026-07-04, via /loop 3min)**: mais 20 pendentes, 5 latex_candidato — **questão
  115956 finalizada** (últimos 4 símbolos: `\wedge`/"e", `\vee`/"ou", `\underline{\vee}`/"ou exclusivo",
  `\neg`/negação — completando os 6 símbolos da lista iniciada no lote_078), e tabela período manhã/
  tarde/porcentagem (várias células vazias a preencher). Resto do lote 100% diagramas/gráficos genuínos,
  incluindo o mesmo gráfico de linha (taxa de participação PNADC/T) reutilizado 3 vezes em questões
  diferentes. **1 caso de conservadorismo**: notação de "chave de divisão" (algoritmo de divisão ABC÷13)
  mantida `manter_imagem` por ser um layout específico de difícil reprodução fiel em `\begin{array}`.
  Estado: `pendente`=662, `classificado`=1178, `inserido`=473, `erro_sem_blob`=10.
- **Lote 080 aplicado (2026-07-04, via /loop 3min)**: mais 20 pendentes, só 3 latex_candidato — tabela
  descrição/nota/peso, tabela peças/quantidade/preço, e uma imagem com 2 tabelas (Fabricação X/Y e
  Versão Branca/Colorida/Aço-Inox) via `<<P>>`. **Achado importante**: a mesma imagem "Lista de
  símbolos:" (legenda lógica completa ⇒/⇔/∧/∨/XOR/¬, formato de imagem única igual ao já visto em
  lote_062/066) aparece em **6 questões diferentes deste lote** (117926/117936/117946/117956/118103/
  118113) cujos enunciados **não têm nenhuma relação com lógica proposicional** (são sobre média
  aritmética, área de triângulo, equações do 2º grau, proporção, área de quadrado) — provável anexo
  incorreto/dado órfão da importação. Por conservadorismo, mantidas `manter_imagem` (não faria sentido
  inserir a legenda como texto no meio de um enunciado de outro assunto) e sinalizadas em `obs` para o
  usuário decidir se remove essas imagens na revisão manual. Estado: `pendente`=642, `classificado`=1195,
  `inserido`=476, `erro_sem_blob`=10.
- **Lote 081 aplicado (2026-07-04, via /loop 3min)**: mais 20 pendentes, só 1 latex_candidato (tabela
  candidato/idade/cursos/anos vivendo/anos experiência, 5 colunas). **Mais uma ocorrência (7ª) da
  anomalia "Lista de símbolos" anexada a questão sem relação com lógica** (desta vez sobre média
  ponderada de notas escolares) — mantida `manter_imagem` com obs, mesmo critério dos lotes anteriores.
  Resto do lote 100% diagramas/gráficos genuínos, incluindo **1 tabela larga** (quadro de sinais de
  função, 11 colunas) mantida `manter_imagem` por exceder o limite. Estado: `pendente`=622,
  `classificado`=1214, `inserido`=477, `erro_sem_blob`=10.
- **Lote 082 aplicado (2026-07-04, via /loop 3min)**: mais 20 pendentes, 10 latex_candidato — tabela
  salário/frequência, e **2 questões-irmãs sobre números complexos (Bombelli/Cardano/Tartaglia, mesmo
  texto-base CESPE) reutilizando o mesmo conjunto de 5 fórmulas cada** (`x=\sqrt[3]{2+\sqrt{-121}}+
  \sqrt[3]{2-\sqrt{-121}}`, `\sqrt{-1}` (2x), `(2+\sqrt{-1})^3=2+\sqrt{-121}`, `(2-\sqrt{-1})^3=
  2-\sqrt{-121}` — questão 120912 com as 5 posições completas nesta lote, questão 120928 com 4 de 5,
  **corrigido proativamente o `ordem` do 5º fragmento restante (120941) de 7 para 11**). Resto do lote
  100% diagramas/gráficos genuínos. Estado: `pendente`=602, `classificado`=1224, `inserido`=487,
  `erro_sem_blob`=10.
- **Lote 083 aplicado (2026-07-04, via /loop 3min)**: mais 20 pendentes, 14 latex_candidato (maior taxa
  em várias rodadas) — **questões 120928 e 120944 finalizadas** (3ª e 4ª questão-irmã do mesmo conjunto
  de fórmulas de números complexos iniciado no lote_082), e vários fragmentos isolados de questões CESPE
  "julgue o item" (geometria analítica/combinatória/funções): `P=(\sqrt{3}/2,1/2)`, `L/\sqrt{2}`,
  `4\sqrt{2}\text{ cm}`, `(x-1/\sqrt{x})^6`, `\binom{16}{5}+\binom{11}{5}+\binom{6}{6}`,
  `1\leq x<\sqrt{3}, f(x)/g(x)\leq 0`, `g(x)=\sqrt{x+4}`, `3/4-5/6+7/9=(5/6)^2` — todos conferidos com
  zoom 5x por serem fórmulas densas. **1 tabela reutilizada 4 vezes** (idade/quantidade de estudantes, 7
  colunas) mantida `manter_imagem` por estar no limite do critério de largura. Estado: `pendente`=582,
  `classificado`=1230, `inserido`=501, `erro_sem_blob`=10.
- **Lote 084 aplicado (2026-07-04, via /loop 3min)**: mais 20 pendentes, 5 latex_candidato — fração
  algébrica `(4ab-2b²+6bc)/(2a²-ab+3ac)=2b/a`, **tabela de festival de talentos (música/dança/imitação
  com combinações) ampliada 3x — a 1ª leitura tinha 2 valores errados (49/58 lidos como 68/62) e faltava
  a linha "Dança e Imitação", corrigidos após o zoom**, tabela número de aulas/preço, tabela formas de
  pagamento/valores, fração `y/0,8x`. **1 caso de conservadorismo**: grade irregular em formato L (não
  retangular) mantida `manter_imagem` por ser difícil reproduzir fielmente em `\begin{array}`. Resto do
  lote foi diagramas/gráficos/infográficos genuínos. Estado: `pendente`=562, `classificado`=1245,
  `inserido`=506, `erro_sem_blob`=10.
- **Lote 085 aplicado (2026-07-04, via /loop 3min)**: mais 20 pendentes, 10 latex_candidato — tabela
  municípios/precipitação, sequência numérica em reta transcrita como lista inline, tabela dias da
  semana/alunos (com "?"), tabela tipo de muda/preço, **tabela número de funcionários/salários
  reutilizada 3 vezes** (questões-irmãs perguntando média/mediana/moda), **derivação de 5 passos (I-V)
  de uma inequação do 2º grau** (passo I é idêntico a um parágrafo de texto já existente na mesma
  questão — a inequação original —, mas os passos II-V são conteúdo novo, transcritos por completo com
  a redundância sinalizada em obs), tabela nota/nº consumidores, tabela sanduíche/vendas/preço. **1 caso
  de tabela larga** (vinho com combinações A/B/C, 8 colunas) mantido `manter_imagem`. Estado:
  `pendente`=542, `classificado`=1255, `inserido`=516, `erro_sem_blob`=10.
- **Lote 086 aplicado (2026-07-04, via /loop 3min)**: mais 20 pendentes, só 2 latex_candidato (fragmento
  `\Delta x` continuando enunciado de histograma, e tabela "Valores de Bolsas no Brasil" com
  título+tabela+público-alvo via `<<P>>`). **Achado de reclassificação**: o parágrafo 123604 (círculo com
  equação "2x-4=6" escrita dentro) parecia à primeira vista um caso tipo "quadro-negro decorativo", mas o
  texto do enunciado ("círculo inscrito dentro de um quadrado") revelou que a figura tem um quadrado de
  fundo genuíno — reclassificado para `manter_imagem` antes de aplicar. Resto do lote 100% diagramas/
  gráficos genuínos, incluindo **mais 5 ocorrências do gráfico de linha "taxa por trimestre" já visto**
  (agora 8 vezes reutilizado no total desde o lote_079) e 1 tabela larga (13 colunas: mês + jan-dez).
  Estado: `pendente`=522, `classificado`=1273, `inserido`=518, `erro_sem_blob`=10.
- **Lote 087 aplicado (2026-07-04, via /loop 3min)**: mais 20 pendentes, só 2 latex_candidato (tabela
  produto/estoque/vendidas/restantes com várias células vazias, fragmento `g:\mathbb{R}\rightarrow
  ]1,+\infty[` continuando enunciado de gráfico de função). Resto do lote 100% diagramas/gráficos/fotos
  genuínos, incluindo 2 pares de figuras reutilizadas (figura irregular 50/20/15cm, retângulos A/B) e uma
  foto de copo real junto com o tronco de cone. Estado: `pendente`=502, `classificado`=1291,
  `inserido`=520, `erro_sem_blob`=10.
- **Lote 088 aplicado (2026-07-04, via /loop 3min)**: mais 20 pendentes, 3 latex_candidato — tabela
  nome/valor/numeração de peças de jogo, um recibo de estacionamento (título+4 linhas com reticências
  para as linhas pontilhadas do original), e fragmento `\overline{EF},` continuando enunciado sobre
  heptágono. Resto do lote 100% diagramas/gráficos/fotos genuínos, incluindo **2 figuras (Figura 1 casa
  e Figura 2 caixa com telhado) reutilizadas 3 vezes cada** entre 3 questões-irmãs sobre o mesmo
  heptágono/prisma, uma foto de família antiga (1948) com nomes/idades sobrepostos, e um problema de
  subtração com dígitos borrados/manchados mantido `manter_imagem` (mancha é elemento gráfico do
  quebra-cabeça). Estado: `pendente`=482, `classificado`=1308, `inserido`=523, `erro_sem_blob`=10.
- **Lote 089 aplicado (2026-07-05)**: mais 20 pendentes, 4 latex_candidato — **banner "Viaje Bem"** de agência de
  turismo (texto extraído em 7 parágrafos via `<<P>>`, a foto da cachoeira com legendas internas permanece na
  imagem, mesmo padrão banner/foto+dado de lotes anteriores), **caixa de remédio "GRIPAMEX"** (3 parágrafos,
  caixa 3D decorativa permanece na imagem), tabela período/homens/mulheres (3 colunas, com células vazias a
  completar), e fragmento `\dfrac{3}{8}` completando o enunciado truncado "menor lado igual a ___ do maior lado"
  da questão 126701 (que tem uma 2ª imagem, um diagrama Q1/Q2, mantida `manter_imagem` — sem problema de
  ordenação pois só 1 das 2 imagens virou latex_candidato). Resto do lote foi 100% diagramas/gráficos genuínos
  (gráficos de linha/barra de Estatística, diagramas de Área e Perímetro reutilizando a mesma figura retangular
  ABCD/Q1/Q2 em 4 questões consecutivas). Estado: `pendente`=462, `classificado`=1324, `inserido`=527,
  `erro_sem_blob`=10.
- **Lote 090 aplicado (2026-07-05)**: mais 20 pendentes, só 2 latex_candidato (tabela dia da semana/número de
  unidades vendidas com incógnita X; tabela de pares de números sem cabeçalho, questão de Números Primos e
  Fatoração). Resto do lote 100% diagramas/gráficos genuínos, com bastante reaproveitamento de figura (triângulo
  40m/120m/130m e triângulo √65/√52 cm, ambos reutilizados 2-3 vezes cada neste lote). Estado: `pendente`=442,
  `classificado`=1342, `inserido`=529, `erro_sem_blob`=10.
- **Lote 091 aplicado (2026-07-05)**: mais 20 pendentes, 7 latex_candidato — tabela ano/receita com incógnita x,
  **tabela de altitudes de morros do Rio de Janeiro (4 colunas) + foto da Praia de Ipanema com legenda/crédito**
  (mesmo padrão tabela+foto do lote_089, foto permanece na imagem), banner "Oferta" de queijo (ícones ilustrativos
  repetidos do produto permanecem na imagem), tabela cidade/público, **ilustração de liquidificador com balão de
  preço "R\$ 78,88 pagos em 4 prestações iguais"** (só o texto do balão virou parágrafo, ilustração do
  liquidificador permanece), e 2 tabelas com incógnitas x/2x (corrida/km percorridos, meses/horas extras).
  **1 novo caso classificado `manter_imagem` por analogia ao cupom fiscal**: bilhete de passagem rodoviária
  (layout tabular realista de documento, mesmo critério já usado para cupom fiscal/extrato bancário). Resto do
  lote foi diagramas/ilustrações genuínos (balança, esfera em cilindro, retângulos algébricos, figuras
  irregulares). Estado: `pendente`=422, `classificado`=1355, `inserido`=536, `erro_sem_blob`=10.
- **Lote 092 aplicado (2026-07-05, via /loop autônomo)**: mais 20 pendentes, 9 latex_candidato — tabela de
  números reais (1 coluna, π^0/mistos/potências), fragmento `\overline{ED}` completando "a medida, em cm, do
  segmento ___ é", tabela taxa de ingresso no Ensino Superior (título+tabela via `<<P>>`), tabela número de
  filhos/pacientes, **tabela "Sarampo no Brasil" (título+tabela 11 linhas+2 linhas de fonte/data via `<<P>>`)
  reutilizada em 2 questões-irmãs** (129750/129763, mesmo padrão tabela+mapa do lote_089/091 — o mapa do Brasil
  permanece na imagem), tabela dia da semana/atividade, e **lista de 11 números (inteiros/decimais/frações)
  escritos em quadro decorativo, reutilizada em 2 questões CESPE** (130096/130104 — confirmado pelo texto
  "a partir dos números escritos no quadro acima" nas duas questões; posição não é relevante pois as perguntas
  comparam números individualmente, não em conjunto/sequência). **1 caso de tabela larga mantido
  `manter_imagem`**: tabela dia da semana × 7 dias + participantes (8 colunas, excede o limite). Resto do lote
  100% diagramas/gráficos genuínos. Estado: `pendente`=402, `classificado`=1366, `inserido`=545,
  `erro_sem_blob`=10.
- **Lote 093 aplicado (2026-07-05, via /loop autônomo)**: mais 20 pendentes, 10 latex_candidato — **o mesmo
  "quadro" de 11 números reutilizado mais 2 vezes** (130112/130128, agora 4 questões CESPE no total desde o
  lote_092), tabela opinião contra/a favor/não opinaram com incógnitas x/y/z, **grade 5×4 de números sem
  cabeçalho** (confirmada célula por célula contra o texto — "elemento da 4ª linha e 3ª coluna, 25" bateu com a
  transcrição), tabela vendedores×meses (5 colunas), tabela mês/dias trabalhados, **etiqueta rasgada de produto**
  (peso/preço/preço-por-kg com reticências, o valor final ausente é literalmente o que a questão pede para
  descobrir — transcrito sem inventar o número faltante), tabela salário/funcionários, tabela nomes/alturas
  (cabeçalho do original diz "Idade" mas os valores são alturas em cm — inconsistência do material original,
  sinalizada em obs, transcrita literalmente), e fragmento `\sqrt{\pi\sqrt{3}}` completando "triângulo
  equilátero de lado ___". Resto do lote 100% diagramas/gráficos genuínos. Estado: `pendente`=382,
  `classificado`=1376, `inserido`=555, `erro_sem_blob`=10.
- **Lote 094 aplicado (2026-07-05, via /loop autônomo)**: mais 20 pendentes, 6 latex_candidato — **tabela
  transferências escola A/B por ano reutilizada em 2 questões-irmãs** (131437/131449), tabela produto/preço
  (artigos de higiene pessoal), título+tabela "Produção mensal" (tipo de sapato/pares), **tabela de clubes
  brasileiros de futebol (participações/títulos, 4 colunas, decimais com ponto conforme o original — fonte
  Wikipedia) + fonte via `<<P>>`** (questão tem uma 2ª imagem, um histograma derivado dessa mesma tabela,
  mantida `manter_imagem` — sem problema de ordenação pois só 1 das 2 virou latex_candidato), e tabela
  trabalhos/tempo de impressão com incógnita "?" no valor de C. **1 novo caso de tabela larga**: par de tabelas
  financeiras de juros compostos (fator `(1+i)^n` e `1/(1+i)^n`, 10 colunas de taxas × 15 linhas cada,
  reutilizado em 2 questões) mantido `manter_imagem` por exceder muito o limite de colunas. Resto do lote 100%
  diagramas/gráficos genuínos. Estado: `pendente`=362, `classificado`=1390, `inserido`=561, `erro_sem_blob`=10.
- **Usuário pediu (2026-07-05) para aumentar o tamanho dos lotes e reduzir a pausa entre iterações do /loop para
  3 min.** A partir do lote_095, rodadas passam a processar ~40 pendentes por vez (em vez de 20).
- **Lote 095 aplicado (2026-07-05, via /loop 3min, primeiro lote em tamanho dobrado)**: 40 pendentes processados
  de uma vez, 21 latex_candidato — tabela amigas/canais, fórmula de soma de série `\sum 1/(n(n+1))`, **tabela de
  dimensões da laje reutilizada em 2 questões-irmãs** (132966/134061, mesmo enunciado "Arthur construiu uma
  casa..."), **tabela de despesas de cinema com incógnita X reutilizada em 2 questões-irmãs** (132983/134078,
  "Renata foi ao cinema..."), **tabela produtos/preço reutilizada em 2 questões-irmãs** (133240/133303, "André é
  dono de um quiosque..."), tabela de valores de 1,12^t (6 colunas), título+tabela+fonte "Reajustes individuais"
  (ANS), 2 banners de promoção (celular R$1850 em 5 prestações; kit de pneus R$269 — ilustrações permanecem na
  imagem), **tabela de viagens ida-e-volta reconstruída como 5 colunas** (cidade-origem/km/cidade-destino/km/
  cidade-retorno, em vez de simplificar para 2 colunas — a repetição observada no original tinha significado
  genuíno), tabela faixa salarial/funcionários, **2 tabelas ANOVA distintas** (uma numérica com células vazias a
  completar, outra simbólica com SQReg/SqRes/SqTot — cabeçalho da 1ª coluna desta última diz "Soma dos
  Quadrados" no original mas os valores da coluna são Regressão/Resíduo/Total, transcrito literalmente sem
  corrigir), **2 fragmentos de fórmula sequenciais na mesma questão** (134444: `\hat{\beta}\text{ e }\hat{\alpha},`
  em ordem5 seguido por `y_i=\hat{\alpha}-\beta x_i` em ordem7, com o parágrafo original de ordem6 entre eles —
  verificado no banco como perfeitamente intercalado, mais um teste do fix de ordenação), tabela de compras de
  churrasco, texto "Tabela de preços" (sem grade, só texto formatado), e tabela horários/ônibus (10 linhas).
  **3 casos de tabela larga mantidos `manter_imagem`**: a mesma tabela financeira de juros compostos (10
  colunas) reaparecendo 2x mais, e uma tabela IPTU com cabeçalhos mesclados por faixa (7 colunas no total,
  no limite) — ambas no mesmo par de imagens de uma única questão (132559), e uma tabela idade/jogadores (7
  colunas). Resto do lote 100% diagramas/gráficos/ilustrações genuínos. Estado: `pendente`=322,
  `classificado`=1409, `inserido`=582, `erro_sem_blob`=10.
- **Lote 096 aplicado (2026-07-05, via /loop 3min)**: 40 pendentes, 11 latex_candidato — **tabela horários/ônibus
  reutilizada da questão 134631** (lote_095), **derivação passo a passo de 2 alunos (Ana/Beatriz) resolvendo
  equação e inequação do 2º grau** (16 parágrafos via `<<P>>`, formato sequencial por aluno igual ao já usado
  para Ana/Danilo no lote_055), **4 falas de alunos (Júlia/Caio/André/Luana) debatendo grandezas inversamente
  proporcionais** (texto puro, via `<<P>>`), 5 tabelas de dados simples (horário/atendidos, escolas/ensino
  fundamental I-II, estoque de 3 lojas — colunas sem categoria fixa por linha, transcritas na ordem visual do
  original —, produto/preço/lucro, gênero de livro/páginas, nome/horário/tempo gasto), **tabela setor/ângulo com
  fórmulas algébricas recursivas** (o gráfico de pizza/roleta na mesma imagem permanece como imagem), e tabela de
  sequência numérica A-G (7 colunas, dentro do limite). **1 novo caso de conservadorismo**: diagrama de varal
  (representação vista de cima) com uma caixinha de referência do Teorema de Pitágoras embutida — mantido
  `manter_imagem` porque é fundamentalinho um diagrama geométrico com medidas, não um bloco de fórmula isolado
  como o caso do lote_066. Resto do lote 100% diagramas/gráficos/ilustrações genuínos (incluindo 1 quadrinho/
  charge e figuras Q1/Q2/Q3 reutilizadas). Estado: `pendente`=282, `classificado`=1438, `inserido`=593,
  `erro_sem_blob`=10.
- **Lote 097 aplicado (2026-07-05, via /loop 3min)**: 40 pendentes, 8 latex_candidato — **tabela Loja A/B/C
  reutilizada mais uma vez** (3ª aparição, questão 136656), **2 tabelas horas/dia×funcionários da mesma questão
  (136874, tabela antiga de 30 dias e tabela nova de 20 dias, incógnita X)** intercaladas em ordens adjacentes
  3/5, verificado no banco como correto, mais um teste do fix de ordenação, 2 somas criptoaritméticas (cada
  letra representa um algarismo, formato `\begin{array}{r}` com linha de soma), fragmento `\overline{AC}`
  (diagonal de retângulo em plano cartesiano), e 2 fragmentos da mesma questão (137932: `AB=30\sqrt{3}` m em
  ordem3 e `\sqrt{3}=1,7` em ordem5, intercalados corretamente com o diagrama em ordem2 e o próprio fragmento
  original em ordem4). **Achado importante de redundância real**: o parágrafo 137105 (matriz vitaminas/sucos)
  tinha o MESMO conteúdo já presente como texto no parágrafo **164976** da mesma questão (id fora da faixa alta
  900000xxx — não fui eu quem inseriu, já existia no banco antes desta sessão) — marcado `manter_imagem` com
  obs em vez de duplicar, confirma que a checagem de redundância antes de classificar continua pegando casos
  reais mesmo com ids legados. Resto do lote 100% diagramas/gráficos/ilustrações genuínos (padrão de círculos
  em arranjo triangular reutilizado, fluxograma de formas geométricas, gráficos diversos). Estado: `pendente`=242,
  `classificado`=1470, `inserido`=601, `erro_sem_blob`=10.
- **Lote 098 aplicado (2026-07-05, via /loop 3min)**: 40 pendentes, 20 latex_candidato — maior variedade de
  formatos vista até agora: rótulos de texto puro (VOLUME SÓLIDO I/II), 3 igualdades de radicais/potências
  (imagem ampliada 4x com PIL para conferência, item II com expoente negativo), tabela de viagens (6 colunas),
  banner de pneu, tabela infração/pontos/multa, texto de tarifas de táxi com reticências, **tabela de 3 pedágios
  simplificada** (valores idênticos nos 3 trechos, título+subtítulo+tabela+fonte via `<<P>>`), tabela de
  especificações de piscina olímpica, **tabela ANOVA com incógnitas A-E/B + fragmento de coeficientes
  β̂₀/β̂₁ da mesma questão**, tabela de regressão linear (coeficientes/erro padrão/teste t/p-valor + nota),
  tabela de vendas com incógnitas algébricas, agenda de eventos 2018 (11 parágrafos via `<<P>>`), tabela de
  feiras com célula mesclada verticalmente, banner "LANCHES", tabela de população de municípios (título+tabela+
  fonte), tabela de plano de saúde (enfermaria/apartamento, 4 colunas) reutilizada em outra questão, e grade
  3×4 de números (massa de pacientes) reutilizada em outra questão. **2 casos de "chave de divisão" (algoritmo
  de divisão com letras) mantidos `manter_imagem`** pelo mesmo critério do lote_079 (layout específico difícil
  de reproduzir). **Achado novo de redundância**: questão 138720 tinha **2 parágrafos-imagem consecutivos
  (ordem1/ordem2) apontando para o MESMO arquivo de imagem** (confirmado por md5sum idêntico) — transcrito uma
  única vez no primeiro parágrafo, o segundo marcado `manter_imagem` com obs explicando a duplicata (evita
  repetir a tabela inteira duas vezes). Resto do lote 100% diagramas/gráficos/infográficos genuínos (2 gráficos
  de barra com ícones decorativos mantidos `manter_imagem` por serem infográficos, não tabelas simples). Estado:
  `pendente`=202, `classificado`=1490, `inserido`=621, `erro_sem_blob`=10.
- **Lote 099 aplicado (2026-07-05, via /loop 3min)**: 40 pendentes, 15 latex_candidato — **grande cluster de 10
  ocorrências da anomalia "Lista de símbolos"** (lógica proposicional anexada a questões sem nenhuma relação:
  Estatística, Equações 2º Grau, Equações, Sistema Métrico, Números Decimais ×3, Divisibilidade, Adição com
  Naturais, Área e Perímetro — mantidas `manter_imagem` com obs, mesmo critério já usado desde o lote_080),
  **5 fragmentos de fórmula de regressão linear da mesma questão** (modelo `Y=β₀+β₁x+ε`, e 4 itens de julgamento
  V/F sobre Var[β₀]/β̂₁/β̂₀/Var[β̂₁] — 2 deles com os índices 0/1 aparentemente trocados em relação à fórmula
  padrão, o que é provavelmente proposital dado o formato "julgue o item"; todos conferidos com zoom 4x e
  transcritos literalmente sem corrigir), fragmento de equação ajustada `Ŷ=300+0,65X`, várias tabelas de dados
  simples (faixa salarial reutilizada, cardápio de lanchonete, valores por pessoa, gênero/eleitores com
  incógnita, categoria/diária, nome com colunas M/P/D/NF), 2 banners (Smart TV, kit de pneus reutilizado),
  e **um quadrinho (Calvin) cujo diálogo contém o próprio enunciado do problema matemático** (distâncias entre
  pontos A/B/C) — caso novo: mesmo sendo quadrinho/charge (categoria historicamente sempre `manter_imagem`),
  o texto das falas foi extraído porque carrega dado matemático essencial não presente em nenhum outro
  parágrafo, seguindo a mesma lógica do padrão banner/foto+dado; a ilustração do quadrinho permanece na imagem.
  Resto do lote 100% diagramas/gráficos genuínos, incluindo várias figuras reutilizadas de lotes anteriores.
  Estado: `pendente`=162, `classificado`=1515, `inserido`=636, `erro_sem_blob`=10.
- **Lote 100 aplicado (2026-07-05, via /loop 3min)**: marco de 100 lotes desde o início do processo. 40
  pendentes, 16 latex_candidato — mais 2 ocorrências da anomalia "Lista de símbolos" (agora 14 no total),
  quebra-cabeça numérico de 4 triângulos (sequência 10,17,26,? transcrita como tabela, forma triangular era só
  decorativa), **tabela Atleta/Equipe reutilizada em 5 questões-irmãs CESPE** sobre revezamento de natação
  (mesmo texto-base longo, cada uma com um item de julgamento V/F diferente; uma delas tem também um fragmento
  de fórmula `12!/(4!)³`), tabela de região/percentual de analfabetismo + fonte, tabela produto/preço, tabela
  vigilante/copinhos de café, e **3 questões-irmãs sobre um debate político (sorteio pergunta/responde/tema)**,
  cada uma com o MESMO padrão de 3 imagens: tabela-modelo em branco + tabela-exemplo preenchida + **a mesma
  tabela-exemplo preenchida repetida uma 2ª vez, byte-idêntica (confirmado por md5sum) — inclusive o texto
  entre as duas imagens também está duplicado no material original**. Tratado como o caso do lote_098: a
  tabela preenchida foi transcrita uma única vez (1ª ocorrência), a 2ª imagem idêntica marcada `manter_imagem`
  com obs explicando a duplicata, em cada uma das 3 questões. Resto do lote 100% diagramas/gráficos genuínos.
  Estado: `pendente`=122, `classificado`=1539, `inserido`=652, `erro_sem_blob`=10.
- **Lote 101 aplicado (2026-07-05, via /loop 3min)**: 40 pendentes, 10 latex_candidato — **11 ocorrências da
  anomalia "Lista de símbolos" só neste lote** (agora 25 no total desde o lote_099, confirma que esse trecho do
  banco está densamente povoado por essa anomalia), **tabela de estatísticas por região (média/mediana/desvio/
  mín/máx/quartis) + box plot** (mesmo padrão tabela+gráfico, o box plot permanece na imagem), fragmento
  `6\sqrt{3}` completando "triângulo equilátero de lado ___ cm", notação de matriz de mudança de base, tabela
  ano/passageiros, tabela locais/profundidade (10 linhas), fragmento trigonométrico `sen 2β/sen 2θ`, tabela
  dia da semana/distância/tempo, tabela mês/celulares vendidos, tabela questão/tempo (linha 5 com "---",
  exatamente o dado que a questão pede para descobrir), e nota de orçamento em fonte cursiva estilizada (texto
  puro via `<<P>>`). Resto do lote 100% diagramas/gráficos genuínos, incluindo 2 questões com 2 imagens cada
  (uma anomalia de Lista de símbolos + um diagrama genuíno de verdade na mesma questão, sem conflito de
  ordenação pois nenhuma das duas virou latex_candidato). Estado: `pendente`=82, `classificado`=1569,
  `inserido`=662, `erro_sem_blob`=10.
- **Lote 102 aplicado (2026-07-05, via /loop 3min)**: 39 pendentes, 10 latex_candidato — 2 fragmentos de
  aproximação de π (`\pi=3` e `\pi=3,14`), tabela ano/mês/usuários com variação, tabela de consumo de 3
  modelos de carro (5 colunas) + legenda via `<<P>>`, tabela cargo/salário/funcionários, tabela classificação/
  número de alunos, tabela comparativa de produção com incógnita H, e **a tabela "Cor/Ponta para
  cima/Total" do experimento das tachinhas reutilizada em 3 questões-irmãs** — em 2 delas a foto das
  tachinhas e a tabela são parágrafos-imagem separados (foto mantida `manter_imagem`, só a tabela virou
  latex_candidato), na 3ª questão a foto e a tabela vêm **combinadas em uma única imagem** (mesmo assim só a
  tabela foi extraída como texto, a foto permanece na imagem). Resto do lote 100% diagramas/gráficos genuínos,
  com bastante reaproveitamento de figura (gráfico de dengue reutilizado 4x, trapézio "platô" e 3 caixas A/B/C
  reutilizados 4x cada). **1 caso sem nada a transcrever**: grade de 6 quadrados vazios, sem texto/número
  algum, mantida `manter_imagem` por não ter conteúdo para extrair. Estado: `pendente`=42, `classificado`=1599,
  `inserido`=672, `erro_sem_blob`=10.
- **Lote 103 aplicado (2026-07-05, via /loop 3min) — ÚLTIMO LOTE, FILA ZERADA**: os 42 pendentes restantes
  processados de uma vez, 14 latex_candidato — fórmula do Fator de Empacotamento Atômico completando o
  enunciado (a ilustração de esferas empacotadas na sequência é diagrama genuíno, mantida `manter_imagem`),
  **2 fragmentos `\bar{X}` na mesma questão** (147021 — confirmado via texto que são 2 lacunas *distintas*
  no enunciado pedindo o mesmo símbolo, não uma imagem duplicada por engano, diferente dos casos de
  duplicata real dos lotes 098/100), 5 tabelas de dados simples (posição/gols, veículo/distância/consumo,
  tipo de peça, funcionários/salário, produção semana1/2 com incógnitas), **derivação de 3 inequações
  resolvidas por André (19 parágrafos via `<<P>>`, mesmo padrão Ana/Beatriz do lote_096)**, tabela x/y=f(x),
  **quadrado mágico 3×3 com incógnitas X/Y/Z**, e 2 casos de tabela pequena embutida junto com diagrama de
  triângulo na mesma imagem (tabela de senos aproximados; tabela de aproximações de raízes) — só a tabela
  extraída, o triângulo permanece na imagem em ambos. Resto do lote 100% diagramas/gráficos genuínos
  (triângulos de Lei dos Senos/Cossenos, retas paralelas com ângulos, ilustração de campo de futebol, círculo
  com ângulo inscrito). **Estado final: `pendente`=0, `classificado`=1627, `inserido`=686, `erro_sem_blob`=10**
  (total 2.323, bate com o total original de parágrafos com imagem). **A revisão de todos os 2.323 parágrafos
  está CONCLUÍDA.** Próximo passo (fora do escopo desta tarefa): revisão manual pelo usuário no app dos
  ~686 parágrafos LaTeX inseridos, ajustando/removendo as imagens originais conforme necessário.

## Decisão de implementação (Fase 2, não estava no plano original)

Quando a imagem tinha **múltiplos itens/passos** (lista I/II/III, ou múltiplas colunas de solução lado a
lado), foi inserido **mais de um novo parágrafo** em vez de um só — porque `h:outputText` (que renderiza
`paragrafo.texto`) não tem `escape="false"`/`white-space:pre`, então quebras de linha dentro de UM
parágrafo colapsam (viram espaço). Múltiplos parágrafos preservam a quebra visual real. Marcado no
manifesto via separador `<<P>>` dentro da célula `latex_proposto` (`_gerar_lote.py` faz o split e cria um
`INSERT` por parágrafo, todos nas ordens consecutivas logo antes da imagem).

Delimitador de math usado: sempre `\(...\)` inline (nunca `\[...\]` — o `mathJaxConfig.js` do projeto usa
o `tex2jax` padrão, que só processa `\(...\)`/`\[...\]`/`$$...$$`; texto fora desses delimitadores não é
processado). Texto que precisa de negrito/itálico fora de uma tabela (ex.: manchete de notícia) foi
envolvido em `\(\textbf{...}\)`/`\(\textit{...}\)` pelo mesmo motivo.

**Critério aplicado para `manter_imagem`**: gráficos (pizza, barra, linha), diagramas geométricos, fotos,
retas numéricas, diagramas de balança/grafo/trajeto, e — caso novo — **texto longo com numeração de linha
relevante para a questão** (ex.: parágrafo 66512, reportagem com linhas 1-24 referenciadas no enunciado;
não dá pra preservar a numeração de linha em 1 parágrafo só, pelo mesmo motivo do `white-space` acima).

**Critério aplicado para `latex_candidato`** (além de fórmula/matriz/sistema puro): tabelas de dados
(sem elemento gráfico), listas de números dispersos, texto em caixas/banners promocionais (a ilustração
decorativa permanece como imagem, só o dado numérico vira parágrafo novo), e uma manchete de notícia
(caso único, fora do escopo estrito pedido — marcado em `obs` para o usuário revisar).

## Correção pós-piloto: `\multicolumn` NÃO é suportado pelo MathJax v2

O app usa **MathJax v2.7.7** via CDN (`template.xhtml:27`, `config=TeX-MML-AM_CHTML`) +
`js/mathJaxConfig.js` (extensions: `color.js`, `cancel.js`). `\multicolumn` (colspan de tabela) **não
existe** em nenhuma versão do MathJax v2 — é um feature request em aberto desde 2015
(`github.com/mathjax/MathJax#1102`, ainda "Backlog"). Não é resolvido carregando `AMSmath.js` nem
nenhuma outra extensão (cheguei a tentar isso e reverti — `AMSmath.js` não define `\multicolumn`,
confirmado direto no source da CDN).

**Regra para todos os lotes futuros: nunca usar `\multicolumn` em `\begin{array}`.** Se a imagem original
tem cabeçalho mesclado (célula única cobrindo 2+ colunas), usar cabeçalhos diretos por coluna (ex.: em vez
de mesclar "Modelo adulto" sobre "V"/"Q", usar "V (adulto)"/"Q (adulto)") — mesma informação, sem depender
de um recurso que não renderiza.

**Correção aplicada**: parágrafo novo `id=900000017` (tabela "Fantasias" da questão 89669, imagem original
89673) tinha `\multicolumn` — corrigido direto no banco (`UPDATE ... UNHEX(...) WHERE id=900000017`,
sem precisar de backup extra por ser 1 linha reversível a partir do texto original já documentado aqui).
`piloto.tsv`/`manifest.tsv` atualizados com o LaTeX corrigido.

## Como retomar (fase de escala, pós-piloto)

1. Ler este arquivo. Estado atual do `manifest.tsv`: `pendente`=2202, `classificado`=54 (39 piloto
   manter_imagem + 15 lote_002 manter_imagem — nunca viram `inserido`, é estado terminal p/ manter_imagem),
   `inserido`=57 (52 piloto + 5 lote_002), `erro_sem_blob`=10.
2. Pegar as próximas ~20 linhas `status=pendente` (em ordem de arquivo — não é por assunto):
   `awk -F'\t' 'NR>1 && $7=="pendente"{print}' manifest.tsv | head -20`.
3. Achar os arquivos de imagem por prefixo `<paragrafo_id>_*` em `imagens/` (a coluna `arquivo_local` do
   manifest tem `/` cru do nome original; no disco os `/` viram `__`).
4. Ler cada imagem (tool Read) e classificar `latex_candidato` (fórmula/matriz/tabela pura de dados, sem
   elemento gráfico) vs `manter_imagem` (foto, diagrama, gráfico de barra/pizza/linha, reta numérica,
   ilustração, logo, diagrama geométrico/mecânico/trajeto/balança/grafo, Venn). **Assuntos grandes
   restantes são majoritariamente `manter_imagem`** (confirmado no lote_002): Estatística (gráficos), Área
   e Perímetro / Geometria Espacial / Pitágoras / Círculo (diagramas geométricos) — não forçar LaTeX nessas.
   **Antes de marcar `latex_candidato`, conferir se o conteúdo da imagem já não está escrito em outro
   parágrafo de texto da mesma questão** (`SELECT texto FROM paragrafo WHERE questao_id=? AND texto IS
   NOT NULL`) — se já estiver, marcar `manter_imagem` com obs sinalizando a redundância em vez de duplicar
   (achado do lote_004: 3 casos de fórmula repetida do enunciado, usuário teve que limpar na mão).
5. Escrever `_full_batch_NNN_updates.tsv` (colunas `paragrafo_id, tipo_conteudo, latex_proposto, obs`,
   mesmo formato do piloto: `\(\begin{array}{|...|}\hline ... \\\hline ... \end{array}\)`, `\textbf{}`
   headers, `\text{}` texto, `R\$` escapado, decimal `{,}`, nunca `\multicolumn`).
6. `python _apply_batch_full.py _full_batch_NNN_updates.tsv` (só toca `manifest.tsv` — `piloto.tsv` é
   exclusivo do piloto, não usar mais).
7. `python _gerar_lote.py manifest.tsv --start-id <próximo id livre> --out lote_NNN.sql --ids-out
   lote_NNN_ids.tsv` (funciona direto em cima do `manifest.tsv`, filtra só `status=classificado` +
   `tipo_conteudo=latex_candidato` — não recaptura linhas já `inserido`).
8. Backup: `mysqldump -uroot --no-create-info --skip-extended-insert --where="questao_id IN (...)"
   pratiqueja paragrafo > _backup/lote_NNN_backup.sql` (senha via `MYSQL_PWD=tiquinho10`; binário em
   `/c/Program Files/MySQL/MySQL Server 8.0/bin/mysqldump.exe` — não está no PATH do bash).
9. Aplicar: `mysql -uroot pratiqueja < lote_NNN.sql`.
10. Verificar: `ordem` sequencial sem gap/duplicata por `questao_id`, `HEX(texto)` dos novos ids confere
    com o esperado (checar `\\\hline` = 3 bytes `5C` seguidos de `hline`, não escaping duplicado).
11. Marcar as linhas aplicadas como `status=inserido` no `manifest.tsv` (script inline, ver histórico) —
    as `manter_imagem` do mesmo lote ficam em `classificado` (terminal, nada a aplicar).
12. Salvar ids: `_questao_ids_loteNNN.txt` (lista simples, só esse lote) e acrescentar uma linha em
    `_questao_ids_todos_lotes.txt` (`lote_NNN (N questoes): id,id,...`).
13. Atualizar este arquivo: contadores do passo 1 e "Próximo ID livre" abaixo.
14. Repetir a partir do passo 2 continuamente (autorização do usuário 2026-07-02: não pedir aprovação a
    cada lote).

## Mecânica de inserção (Fase 3)

- Novo `Paragrafo.id` usa faixa alta isolada: começar em `900000001`, incrementar por linha inserida
  (nunca reaproveitar — manter um contador único entre lotes, registrado abaixo).
- `UPDATE paragrafo SET ordem = ordem + 1 WHERE questao_id=? AND ordem >= ?` (desloca a imagem e tudo
  depois dela) seguido de `INSERT ... ordem = <ordem original da imagem>`.
- Backup antes de cada lote: `mysqldump --no-create-info --skip-extended-insert --where="questao_id IN
  (...)" pratiqueja paragrafo > _backup/lote_NNN_backup.sql`.

**Próximo ID livre da faixa alta: 900000992** (lote_001: 900000001-56; lote_002: 900000057-61; lote_003:
900000062-64; lote_004: 900000065-71; lote_005: 900000072-77; lote_006: 900000078-79; lote_007:
900000080-83; lote_008: 900000084-88; lote_009: 900000089-94; lote_010: 900000095-101; lote_011:
900000102-103; lote_012: 900000104-110; lote_013: 900000111-114; lote_014: 900000115-116; lote_015:
900000117-120; lote_016: 900000121-130; lote_017: 900000131-135; lote_018: 900000136-139; lote_019:
900000140-147; lote_020: 900000148-155; lote_021: 900000156-160; lote_022: 900000161-164; lote_023:
900000165-168; lote_024: 900000169-170; lote_025: 900000171-176; lote_026: 900000177-180; lote_027:
900000181-184; lote_028: 900000185; lote_029: 900000186-188; lote_030: 900000189-193; lote_031:
900000194-196; lote_032: 900000197-199; lote_033: 900000200-202; lote_034: 900000203-209; lote_035:
900000210-214; lote_036: 900000215-222; lote_037: 900000223-231; lote_038: 900000232-236; lote_039:
900000237-244; lote_040: 900000245-246; lote_041: 900000247-253; lote_042: 900000254-261; lote_043:
900000262-271; lote_044: 900000272-273; lote_045: 900000274-278; lote_046: 900000279-284; lote_047:
900000285-298; lote_048: 900000299-306; lote_049: 900000307-310; lote_050: 900000311-315; lote_051:
900000316-325; lote_052: 900000326-336; lote_053: 900000337-349; lote_054: 900000350-366; lote_055:
900000367-384; lote_056: 900000385-396; lote_057: 900000397-407; lote_058: 900000408-411; lote_059:
900000412-424; lote_060: 900000425-462; lote_061: 900000463-473; lote_062: 900000474-496; lote_063:
900000497-518; lote_064: 900000519-527; lote_065: 900000528-536; lote_066: 900000537-571; lote_067: 900000572-582; lote_068: 900000583-588; lote_069: 900000589-600; lote_070: 900000601-606; lote_071: 900000607-609; lote_072: 900000610; lote_073: 900000611-612; lote_074: 900000613-620; lote_075: 900000621-623; lote_076: 900000624-626; lote_077: 900000627-630; lote_078: 900000631-637; lote_079: 900000638-642; lote_080: 900000643-646; lote_081: 900000647; lote_082: 900000648-657; lote_083: 900000658-671; lote_084: 900000672-676; lote_085: 900000677-690; lote_086: 900000691-694; lote_087: 900000695-696; lote_088: 900000697-703; lote_089: 900000704-715; lote_090: 900000716-717; lote_091: 900000718-726; lote_092: 900000727-742; lote_093: 900000743-754; lote_094: 900000755-762; lote_095: 900000763-792 (lote dobrado p/ 40 pendentes/rodada); lote_096: 900000793-821; lote_097: 900000822-829; lote_098: 900000830-883; lote_099: 900000884-915; lote_100: 900000916-932; lote_101: 900000933-947; lote_102: 900000948-958; lote_103: 900000959-991 (ULTIMO LOTE); atualizar aqui após cada lote aplicado —
CONFERIR que o número em negrito no início desta linha bate com o final da lista antes de prosseguir, já
houve um caso em que ficaram dessincronizados).

**Nota sobre `paragrafo_id` fora do padrão**: a maioria usa ids na casa de milhares/dezenas de milhares,
mas alguns são bem baixos (ex. `paragrafo_id=2`, questão 12644) — são válidos, só ids antigos/legado.
Buscar arquivo de imagem sempre pelo prefixo exato `<paragrafo_id>_` (ex. `2_f58b68257bd3f86195b1.png`),
nunca assumir que o id tem uma faixa mínima.
