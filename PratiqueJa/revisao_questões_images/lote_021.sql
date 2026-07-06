-- questao_id=28395 paragrafo_imagem_id=28400 ordem_original=2 (1 paragrafo(s) novo(s))
UPDATE paragrafo SET ordem = ordem + 1 WHERE questao_id = 28395 AND ordem >= 2;
INSERT INTO paragrafo (id, ordem, texto, questao_id, imagemFile_id) VALUES (900000156, 2, UNHEX('5c28435f32203d205c6c6566745c7b7a205c696e205c6d61746862627b437d3a207c7a7c205c6c6571205c64667261637b5c737172747b327d7d7b327d5c72696768745c7d5c29'), 28395, NULL);

-- questao_id=28395 paragrafo_imagem_id=28398 ordem_original=1 (1 paragrafo(s) novo(s))
UPDATE paragrafo SET ordem = ordem + 1 WHERE questao_id = 28395 AND ordem >= 1;
INSERT INTO paragrafo (id, ordem, texto, questao_id, imagemFile_id) VALUES (900000157, 1, UNHEX('5c28435f31203d205c7b7a205c696e205c6d61746862627b437d3a207c7a7c205c6c657120315c7d5c29'), 28395, NULL);

-- questao_id=28599 paragrafo_imagem_id=28606 ordem_original=4 (1 paragrafo(s) novo(s))
UPDATE paragrafo SET ordem = ordem + 1 WHERE questao_id = 28599 AND ordem >= 4;
INSERT INTO paragrafo (id, ordem, texto, questao_id, imagemFile_id) VALUES (900000158, 4, UNHEX('5c285c6f7665726c696e657b42437d5c29'), 28599, NULL);

-- questao_id=29284 paragrafo_imagem_id=29289 ordem_original=2 (1 paragrafo(s) novo(s))
UPDATE paragrafo SET ordem = ordem + 1 WHERE questao_id = 29284 AND ordem >= 2;
INSERT INTO paragrafo (id, ordem, texto, questao_id, imagemFile_id) VALUES (900000159, 2, UNHEX('5c285c6f7665726c696e657b42437d5c29'), 29284, NULL);

-- questao_id=29284 paragrafo_imagem_id=29287 ordem_original=1 (1 paragrafo(s) novo(s))
UPDATE paragrafo SET ordem = ordem + 1 WHERE questao_id = 29284 AND ordem >= 1;
INSERT INTO paragrafo (id, ordem, texto, questao_id, imagemFile_id) VALUES (900000160, 1, UNHEX('5c285c6f7665726c696e657b44457d5c29'), 29284, NULL);

