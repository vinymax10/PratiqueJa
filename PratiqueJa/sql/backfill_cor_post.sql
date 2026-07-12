-- Backfill de ConfigPost.corDestaque após a migração que separa a cor de destaque
-- do EstiloPost (antes a cor vinha embutida em cada estilo: FotoFundo/MolduraAzul=iris/azul,
-- SombraCoral=coral, CantosVivos=roxo, FaixaSuperior=rosa). A cor agora é livre, escolhida
-- via color picker (hex), e não mais um enum fixo.
--
-- Contexto: a coluna corDestaque é nova (hbm2ddl=update só cria a coluna, não popula os
-- registros já existentes). Sem este backfill, ConfigPost de usuários já cadastrados ficam
-- com corDestaque NULL, o que quebra o LaTeX gerado (ex.: "\definecolor{destaque}{rgb}{NULL}").
--
-- Regra: reaproveita o hex da cor que cada estilo já usava antes da migração, preservando a
-- aparência atual dos posts de quem já configurou; #4059E3 (azul/iris) é o default para quem
-- ainda não passou pelo estilo (equivalente ao default de FotoFundo/ConfigPost.COR_TITULO).
--
-- Execução: mysql -u root -ptiquinho10 pratiqueja < sql/backfill_cor_post.sql

UPDATE configpost
SET corDestaque = CASE estilo
	WHEN 'SombraCoral'   THEN '#F06657'
	WHEN 'CantosVivos'   THEN '#784FC9'
	WHEN 'FaixaSuperior' THEN '#DB4A8C'
	ELSE '#4059E3'
END
WHERE corDestaque IS NULL;

-- Conferência
SELECT estilo, corDestaque, COUNT(*) FROM configpost GROUP BY estilo, corDestaque;
