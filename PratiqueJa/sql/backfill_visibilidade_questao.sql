-- Backfill de Questao.visibilidade a partir da mesma regra do "PDF grátis"
-- (ListaQuestoesPdfService.gerarTodos(): só o 1º lote — 20 questões, por ConfigPdfQuestao —
-- do assunto na dificuldade Fácil, em ordem ordemInsercao, fica Básico; o resto vira Premium).
--
-- Contexto: até aqui, Questao.visibilidade nunca era escrita em lugar nenhum do código (todas
-- as 14.412 linhas estavam NULL). Este script roda UMA VEZ, manualmente, depois do deploy que
-- passa a ler esse campo em QuestaoBean.toogleResolucaoComentada()/listaQuestoes.xhtml.
--
-- Uma questão pode pertencer a mais de um assunto (questao_assunto é N:N); ela vira Básico se
-- estiver entre as 20 primeiras em QUALQUER um dos assuntos aos quais pertence.
--
-- Execução: mysql -u root -ptiquinho10 pratiqueja < sql/backfill_visibilidade_questao.sql

UPDATE questao q
LEFT JOIN (
	SELECT DISTINCT x.questao_id
	FROM (
		SELECT qa.questao_id,
			ROW_NUMBER() OVER (PARTITION BY qa.assunto_id ORDER BY q2.ordemInsercao ASC) AS rn
		FROM questao_assunto qa
		JOIN questao q2 ON q2.id = qa.questao_id
		WHERE q2.dificuldade = 'Facil'
		AND q2.revisada = 1
		AND EXISTS (SELECT 1 FROM paragraforesolucaoquestao p WHERE p.questao_id = q2.id)
	) x
	WHERE x.rn <= 20
) livre ON livre.questao_id = q.id
-- Hibernate grava Questao.visibilidade por ORDINAL (tinyint), não por nome como em Pdf/Exercicio:
-- Visibilidade.Basico = 0, Visibilidade.Premium = 1.
SET q.visibilidade = IF(livre.questao_id IS NOT NULL, 0, 1);

-- Conferência
SELECT CASE visibilidade WHEN 0 THEN 'Basico' WHEN 1 THEN 'Premium' ELSE 'NULL' END AS visibilidade, COUNT(*)
FROM questao GROUP BY visibilidade;
