-- questao_id=111902 paragrafo_imagem_id=111908 ordem_original=3 (1 paragrafo(s) novo(s))
UPDATE paragrafo SET ordem = ordem + 1 WHERE questao_id = 111902 AND ordem >= 3;
INSERT INTO paragrafo (id, ordem, texto, questao_id, imagemFile_id) VALUES (900000627, 3, UNHEX('5c28435c6861747b587d595c29'), 111902, NULL);

-- questao_id=111914 paragrafo_imagem_id=111919 ordem_original=2 (1 paragrafo(s) novo(s))
UPDATE paragrafo SET ordem = ordem + 1 WHERE questao_id = 111914 AND ordem >= 2;
INSERT INTO paragrafo (id, ordem, texto, questao_id, imagemFile_id) VALUES (900000628, 2, UNHEX('5c28425c6861747b487d435c29'), 111914, NULL);

-- questao_id=113218 paragrafo_imagem_id=113221 ordem_original=1 (1 paragrafo(s) novo(s))
UPDATE paragrafo SET ordem = ordem + 1 WHERE questao_id = 113218 AND ordem >= 1;
INSERT INTO paragrafo (id, ordem, texto, questao_id, imagemFile_id) VALUES (900000629, 1, UNHEX('5c285c6d61746862627b527d5c746578747b20656d207d5c6d61746862627b527d5c29'), 113218, NULL);

-- questao_id=113324 paragrafo_imagem_id=113327 ordem_original=1 (1 paragrafo(s) novo(s))
UPDATE paragrafo SET ordem = ordem + 1 WHERE questao_id = 113324 AND ordem >= 1;
INSERT INTO paragrafo (id, ordem, texto, questao_id, imagemFile_id) VALUES (900000630, 1, UNHEX('5c285c626567696e7b61727261797d7b7c6c7c637c637c7d5c686c696e652026205c7465787462667b4dc3a96469636f20646f207365786f206d617363756c696e6f7d2026205c7465787462667b4dc3a96469636f20646f207365786f2066656d696e696e6f7d5c5c5c686c696e65205c7465787462667b4f6674616c6d6f6c6f67697374617d202620353020262033305c5c5c686c696e65205c7465787462667b43617264696f6c6f67697374617d202620343020262035305c5c5c686c696e65205c7465787462667b50656469617472617d202620373020262039305c5c5c686c696e655c656e647b61727261797d5c29'), 113324, NULL);

