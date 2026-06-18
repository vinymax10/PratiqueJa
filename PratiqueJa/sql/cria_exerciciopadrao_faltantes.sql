-- ============================================================
--  Cria os ExercicioPadrao faltantes (1 por Nivel) para os
--  assuntos que estavam sem nenhum exercício padrão:
--    - Estatística         (assunto_id = 54,     modulo 2)
--    - Polígonos Regulares (assunto_id = 173009, modulo 1)
--
--  IDs obtidos do hibernate_sequence (gerador ativo, next_val=173100,
--  incremento 1). A sequência é avançada em 6 ao final.
--
--  Valores seguem o default das demais linhas:
--    quantidade=10, enunciado='Resolva os seguintes exercícios.',
--    enunciadoSingular='', descricao='', mostrarResolucao=0,
--    imagemQuadrada=0, tipoExercicio=NULL.
--  layoutLista: PoligonosRegulares -> ESPACOSO (tem imagens, vide
--  atualiza_layout_lista.sql); Estatistica -> NULL (PADRAO).
--  nivel: 0=Nivel1, 1=Nivel2, 2=Nivel3 (ordinal do enum Nivel).
--  Banco: MySQL (pratiqueja).
-- ============================================================

START TRANSACTION;

INSERT INTO `ExercicioPadrao`
    (`id`, `assunto_id`, `nivel`, `nome`, `quantidade`, `enunciado`,
     `enunciadoSingular`, `descricao`, `tipoExercicio`, `mostrarResolucao`,
     `imagemQuadrada`, `layoutLista`)
VALUES
    (173100, 54, 0, 'EstatisticaNivel1', 10, 'Resolva os seguintes exercícios.', '', '', NULL, 0, 0, NULL),
    (173101, 54, 1, 'EstatisticaNivel2', 10, 'Resolva os seguintes exercícios.', '', '', NULL, 0, 0, NULL),
    (173102, 54, 2, 'EstatisticaNivel3', 10, 'Resolva os seguintes exercícios.', '', '', NULL, 0, 0, NULL),
    (173103, 173009, 0, 'PoligonosRegularesNivel1', 10, 'Resolva os seguintes exercícios.', '', '', NULL, 0, 0, 'ESPACOSO'),
    (173104, 173009, 1, 'PoligonosRegularesNivel2', 10, 'Resolva os seguintes exercícios.', '', '', NULL, 0, 0, 'ESPACOSO'),
    (173105, 173009, 2, 'PoligonosRegularesNivel3', 10, 'Resolva os seguintes exercícios.', '', '', NULL, 0, 0, 'ESPACOSO');

UPDATE `hibernate_sequence` SET `next_val` = 173106 WHERE `next_val` = 173100;

COMMIT;
