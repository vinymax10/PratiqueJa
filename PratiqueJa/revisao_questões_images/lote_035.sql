-- questao_id=43544 paragrafo_imagem_id=43547 ordem_original=1 (3 paragrafo(s) novo(s))
UPDATE paragrafo SET ordem = ordem + 3 WHERE questao_id = 43544 AND ordem >= 1;
INSERT INTO paragrafo (id, ordem, texto, questao_id, imagemFile_id) VALUES (900000210, 1, UNHEX('492e205c285c737172747b5c64667261637b397d7b367d7d3d5c737172747b337d5c29'), 43544, NULL);
INSERT INTO paragrafo (id, ordem, texto, questao_id, imagemFile_id) VALUES (900000211, 2, UNHEX('49492e205c285c737172745b335d7b5c737172747b337d7d3d5c737172745b355d7b337d5c29'), 43544, NULL);
INSERT INTO paragrafo (id, ordem, texto, questao_id, imagemFile_id) VALUES (900000212, 3, UNHEX('4949492e205c285c64667261637b327d7b5c737172747b357d2b357d3d5c64667261637b352d5c737172747b357d7d7b31307d5c29'), 43544, NULL);

-- questao_id=43816 paragrafo_imagem_id=43819 ordem_original=1 (1 paragrafo(s) novo(s))
UPDATE paragrafo SET ordem = ordem + 1 WHERE questao_id = 43816 AND ordem >= 1;
INSERT INTO paragrafo (id, ordem, texto, questao_id, imagemFile_id) VALUES (900000213, 1, UNHEX('5c28662878293d622b5c6c6f675f617b787d5c29'), 43816, NULL);

-- questao_id=43989 paragrafo_imagem_id=43992 ordem_original=1 (1 paragrafo(s) novo(s))
UPDATE paragrafo SET ordem = ordem + 1 WHERE questao_id = 43989 AND ordem >= 1;
INSERT INTO paragrafo (id, ordem, texto, questao_id, imagemFile_id) VALUES (900000214, 1, UNHEX('5c285c64667261637b377d7b5c737172747b31312d365c737172747b327d7d7d2b5c64667261637b357d7b5c737172747b372b5c737172747b327d7d7d5c29'), 43989, NULL);

