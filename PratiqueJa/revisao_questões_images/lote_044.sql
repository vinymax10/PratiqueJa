-- questao_id=62798 paragrafo_imagem_id=62802 ordem_original=2 (1 paragrafo(s) novo(s))
UPDATE paragrafo SET ordem = ordem + 1 WHERE questao_id = 62798 AND ordem >= 2;
INSERT INTO paragrafo (id, ordem, texto, questao_id, imagemFile_id) VALUES (900000272, 2, UNHEX('5c285c64667261637b307b2c7d303532355c63646f7431305e387d7b31305e337d5c29'), 62798, NULL);

-- questao_id=62904 paragrafo_imagem_id=62908 ordem_original=2 (1 paragrafo(s) novo(s))
UPDATE paragrafo SET ordem = ordem + 1 WHERE questao_id = 62904 AND ordem >= 2;
INSERT INTO paragrafo (id, ordem, texto, questao_id, imagemFile_id) VALUES (900000273, 2, UNHEX('5c2838352c2039342c203130312c2037392c2038332c2038302c2039392c2039332c2039312c2037362c2038342c2038352c203130302c2038322c2039355c29'), 62904, NULL);

