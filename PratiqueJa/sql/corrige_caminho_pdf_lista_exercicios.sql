-- ============================================================
--  Insere "lista_exercicios" no caminho dos PDFs já existentes:
--  <enderecoPdf>\<assunto>\arquivo.pdf
--    -> <enderecoPdf>\lista_exercicios\<assunto>\arquivo.pdf
--
--  Robusto quanto a escape de barra: usa CHAR(92) para "\" e INSTR
--  (sem wildcards). Idempotente: só altera linhas sob o prefixo de
--  enderecoPdf que ainda não contêm "lista_exercicios".
--  Banco: MySQL (pratiqueja).
-- ============================================================

SET @pref = CONCAT((SELECT enderecoPdf FROM ConfigLatex LIMIT 1), CHAR(92));
SET @new  = CONCAT(@pref, 'lista_exercicios', CHAR(92));

START TRANSACTION;

UPDATE Pdf
SET caminho = REPLACE(caminho, @pref, @new)
WHERE INSTR(caminho, @pref) = 1
  AND INSTR(caminho, 'lista_exercicios') = 0;

COMMIT;
