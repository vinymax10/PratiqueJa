-- questao_id=108787 paragrafo_imagem_id=108790 ordem_original=1 (1 paragrafo(s) novo(s))
UPDATE paragrafo SET ordem = ordem + 1 WHERE questao_id = 108787 AND ordem >= 1;
INSERT INTO paragrafo (id, ordem, texto, questao_id, imagemFile_id) VALUES (900000624, 1, UNHEX('5c285c64667261637b32347b2c7d357d7b33327d3d5c64667261637b617d7b62277d5c29'), 108787, NULL);

-- questao_id=108874 paragrafo_imagem_id=108877 ordem_original=1 (1 paragrafo(s) novo(s))
UPDATE paragrafo SET ordem = ordem + 1 WHERE questao_id = 108874 AND ordem >= 1;
INSERT INTO paragrafo (id, ordem, texto, questao_id, imagemFile_id) VALUES (900000625, 1, UNHEX('5c28357a2b5c6261727b7a7d3d31322b3136695c29'), 108874, NULL);

-- questao_id=109153 paragrafo_imagem_id=109157 ordem_original=2 (1 paragrafo(s) novo(s))
UPDATE paragrafo SET ordem = ordem + 1 WHERE questao_id = 109153 AND ordem >= 2;
INSERT INTO paragrafo (id, ordem, texto, questao_id, imagemFile_id) VALUES (900000626, 2, UNHEX('5c285c626567696e7b61727261797d7b7c637c637c637c7d5c686c696e65202620322026205c5c5c686c696e6520352026202620335c5c5c686c696e65202620342026205c5c5c686c696e655c656e647b61727261797d5c29'), 109153, NULL);

