-- questao_id=37454 paragrafo_imagem_id=37462 ordem_original=5 (1 paragrafo(s) novo(s))
UPDATE paragrafo SET ordem = ordem + 1 WHERE questao_id = 37454 AND ordem >= 5;
INSERT INTO paragrafo (id, ordem, texto, questao_id, imagemFile_id) VALUES (900000189, 5, UNHEX('5c285c6261727b787d203d205c64667261637b317d7b6e7d5c73756d5f7b693d317d5e7b6e7d20785f695c29'), 37454, NULL);

-- questao_id=37468 paragrafo_imagem_id=37473 ordem_original=2 (1 paragrafo(s) novo(s))
UPDATE paragrafo SET ordem = ordem + 1 WHERE questao_id = 37468 AND ordem >= 2;
INSERT INTO paragrafo (id, ordem, texto, questao_id, imagemFile_id) VALUES (900000190, 2, UNHEX('5c285c64667261637b612d317d7b612b317d5c29'), 37468, NULL);

-- questao_id=37468 paragrafo_imagem_id=37471 ordem_original=1 (1 paragrafo(s) novo(s))
UPDATE paragrafo SET ordem = ordem + 1 WHERE questao_id = 37468 AND ordem >= 1;
INSERT INTO paragrafo (id, ordem, texto, questao_id, imagemFile_id) VALUES (900000191, 1, UNHEX('5c285c64667261637b5c232b317d7b5c232d317d5c29'), 37468, NULL);

-- questao_id=38231 paragrafo_imagem_id=164896 ordem_original=1 (1 paragrafo(s) novo(s))
UPDATE paragrafo SET ordem = ordem + 1 WHERE questao_id = 38231 AND ordem >= 1;
INSERT INTO paragrafo (id, ordem, texto, questao_id, imagemFile_id) VALUES (900000192, 1, UNHEX('5c284d203d205c626567696e7b706d61747269787d302026203234302026203020262030202620333935205c5c32353520262030202620302026203020262030205c5c30202620302026203020262033383020262030205c5c302026203020262034303020262030202620343830205c5c3338302026203020262034353020262030202620305c656e647b706d61747269787d5c29'), 38231, NULL);

-- questao_id=38345 paragrafo_imagem_id=38348 ordem_original=1 (1 paragrafo(s) novo(s))
UPDATE paragrafo SET ordem = ordem + 1 WHERE questao_id = 38345 AND ordem >= 1;
INSERT INTO paragrafo (id, ordem, texto, questao_id, imagemFile_id) VALUES (900000193, 1, UNHEX('5c284d203d205c64667261637b785f312b785f322b785f332b5c6c646f74732b785f6e7d7b6e7d5c29'), 38345, NULL);

