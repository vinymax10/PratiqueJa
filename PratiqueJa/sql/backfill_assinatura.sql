-- Backfill da entidade Assinatura a partir do estado atual de Usuario + histórico de Pagamento.
--
-- Contexto: antes da entidade Assinatura, o estado de cada assinatura Hotmart vivia só em
-- Usuario (perfil*/validadePlano*/subscriberCodeHotmart*), sem histórico. Este script roda
-- UMA VEZ, depois do deploy que cria a tabela `assinatura` e a coluna `assinatura_id` em
-- `pagamento` (hibernate.hbm2ddl.auto=update), para reconstruir uma Assinatura "Ativa" para
-- cada assinatura Hotmart em andamento hoje.
--
-- Estratégia por eixo: pega, para cada usuário com subscriberCodeHotmart* preenchido, o
-- Produto mais recentemente comprado (por pagamento.data) que concede o perfil daquele eixo,
-- e confere que o perfil desse produto bate com o perfil atual do usuário (senão pula, para
-- não inventar um vínculo errado — ver SELECTs de conferência ao final).
--
-- Execução: mysql -u root -ptiquinho10 pratiqueja < sql/backfill_assinatura.sql

-- ── Eixo conteúdo (perfil / perfilUsuario) ──────────────────────────────────
INSERT INTO assinatura (usuario_id, produto_id, subscriberCodeHotmart, dataInicio, dataValidade, status, dataCancelamento)
SELECT
	u.id,
	x.produto_id,
	u.subscriberCodeHotmart,
	COALESCE(x.primeira_data, u.validadePlano),
	u.validadePlano,
	'Ativa',
	NULL
FROM usuario u
JOIN (
	SELECT pg.usuario_id, pg.produto_id, MIN(pg.data) AS primeira_data,
		ROW_NUMBER() OVER (PARTITION BY pg.usuario_id ORDER BY MAX(pg.data) DESC) AS rn
	FROM pagamento pg
	JOIN produto pr ON pr.id = pg.produto_id
	WHERE pr.perfilUsuario IS NOT NULL
	GROUP BY pg.usuario_id, pg.produto_id
) x ON x.usuario_id = u.id AND x.rn = 1
JOIN produto p ON p.id = x.produto_id
WHERE u.subscriberCodeHotmart IS NOT NULL
AND p.perfilUsuario = u.perfil
AND NOT EXISTS (
	SELECT 1 FROM assinatura a
	WHERE a.usuario_id = u.id AND a.subscriberCodeHotmart = u.subscriberCodeHotmart
);

-- ── Eixo criador (perfilCriador) ────────────────────────────────────────────
INSERT INTO assinatura (usuario_id, produto_id, subscriberCodeHotmart, dataInicio, dataValidade, status, dataCancelamento)
SELECT
	u.id,
	x.produto_id,
	u.subscriberCodeHotmartCriador,
	COALESCE(x.primeira_data, u.validadePlanoCriador),
	u.validadePlanoCriador,
	'Ativa',
	NULL
FROM usuario u
JOIN (
	SELECT pg.usuario_id, pg.produto_id, MIN(pg.data) AS primeira_data,
		ROW_NUMBER() OVER (PARTITION BY pg.usuario_id ORDER BY MAX(pg.data) DESC) AS rn
	FROM pagamento pg
	JOIN produto pr ON pr.id = pg.produto_id
	WHERE pr.perfilCriador IS NOT NULL
	GROUP BY pg.usuario_id, pg.produto_id
) x ON x.usuario_id = u.id AND x.rn = 1
JOIN produto p ON p.id = x.produto_id
WHERE u.subscriberCodeHotmartCriador IS NOT NULL
AND p.perfilCriador = u.perfilCriador
AND NOT EXISTS (
	SELECT 1 FROM assinatura a
	WHERE a.usuario_id = u.id AND a.subscriberCodeHotmart = u.subscriberCodeHotmartCriador
);

-- ── Eixo avaliação (perfilAvaliacao) ────────────────────────────────────────
INSERT INTO assinatura (usuario_id, produto_id, subscriberCodeHotmart, dataInicio, dataValidade, status, dataCancelamento)
SELECT
	u.id,
	x.produto_id,
	u.subscriberCodeHotmartAvaliacao,
	COALESCE(x.primeira_data, u.validadePlanoAvaliacao),
	u.validadePlanoAvaliacao,
	'Ativa',
	NULL
FROM usuario u
JOIN (
	SELECT pg.usuario_id, pg.produto_id, MIN(pg.data) AS primeira_data,
		ROW_NUMBER() OVER (PARTITION BY pg.usuario_id ORDER BY MAX(pg.data) DESC) AS rn
	FROM pagamento pg
	JOIN produto pr ON pr.id = pg.produto_id
	WHERE pr.perfilAvaliacao IS NOT NULL
	GROUP BY pg.usuario_id, pg.produto_id
) x ON x.usuario_id = u.id AND x.rn = 1
JOIN produto p ON p.id = x.produto_id
WHERE u.subscriberCodeHotmartAvaliacao IS NOT NULL
AND p.perfilAvaliacao = u.perfilAvaliacao
AND NOT EXISTS (
	SELECT 1 FROM assinatura a
	WHERE a.usuario_id = u.id AND a.subscriberCodeHotmart = u.subscriberCodeHotmartAvaliacao
);

-- ── Vincula os Pagamentos existentes à Assinatura correspondente ───────────
UPDATE pagamento pg
JOIN assinatura a ON a.usuario_id = pg.usuario_id AND a.produto_id = pg.produto_id
SET pg.assinatura_id = a.id
WHERE pg.assinatura_id IS NULL;

-- ── Conferência: usuários com assinatura Hotmart ativa que NÃO geraram Assinatura ──
-- (produto comprado não bate mais com o perfil atual do usuário — resolver manualmente)
SELECT u.id, u.nome, u.email, 'conteudo' AS eixo, u.perfil AS perfil_atual, u.subscriberCodeHotmart
FROM usuario u
WHERE u.subscriberCodeHotmart IS NOT NULL
AND NOT EXISTS (SELECT 1 FROM assinatura a WHERE a.usuario_id = u.id AND a.subscriberCodeHotmart = u.subscriberCodeHotmart)
UNION ALL
SELECT u.id, u.nome, u.email, 'criador', u.perfilCriador, u.subscriberCodeHotmartCriador
FROM usuario u
WHERE u.subscriberCodeHotmartCriador IS NOT NULL
AND NOT EXISTS (SELECT 1 FROM assinatura a WHERE a.usuario_id = u.id AND a.subscriberCodeHotmart = u.subscriberCodeHotmartCriador)
UNION ALL
SELECT u.id, u.nome, u.email, 'avaliacao', u.perfilAvaliacao, u.subscriberCodeHotmartAvaliacao
FROM usuario u
WHERE u.subscriberCodeHotmartAvaliacao IS NOT NULL
AND NOT EXISTS (SELECT 1 FROM assinatura a WHERE a.usuario_id = u.id AND a.subscriberCodeHotmart = u.subscriberCodeHotmartAvaliacao);
