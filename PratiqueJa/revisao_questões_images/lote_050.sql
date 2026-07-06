-- questao_id=78242 paragrafo_imagem_id=78245 ordem_original=1 (3 paragrafo(s) novo(s))
UPDATE paragrafo SET ordem = ordem + 3 WHERE questao_id = 78242 AND ordem >= 1;
INSERT INTO paragrafo (id, ordem, texto, questao_id, imagemFile_id) VALUES (900000311, 1, UNHEX('5c285c626567696e7b61727261797d7b7c637c637c637c7d5c686c696e65205c7465787462667b446174617d2026205c7465787462667b6174c3a92030332e30352e323031327d2026205c7465787462667b61207061727469722064652030342e30352e323031327d5c5c5c686c696e652032352f31322f32303139202620307b2c7d35303030202620307b2c7d323837315c5c5c686c696e65205c656e647b61727261797d5c29'), 78242, NULL);
INSERT INTO paragrafo (id, ordem, texto, questao_id, imagemFile_id) VALUES (900000312, 2, UNHEX('5c285c746578747b466f6e74653a2042616e636f2043656e7472616c20646f2042726173696c2e7d5c29'), 78242, NULL);
INSERT INTO paragrafo (id, ordem, texto, questao_id, imagemFile_id) VALUES (900000313, 3, UNHEX('5c285c746578747b4c696e6b3a2068747470733a2f2f7777772e6263622e676f762e62722f6573746174697374696361732f72656d756e6572616465706f7369746f73706f7570616e63617d5c29'), 78242, NULL);

-- questao_id=78309 paragrafo_imagem_id=78313 ordem_original=2 (1 paragrafo(s) novo(s))
UPDATE paragrafo SET ordem = ordem + 1 WHERE questao_id = 78309 AND ordem >= 2;
INSERT INTO paragrafo (id, ordem, texto, questao_id, imagemFile_id) VALUES (900000314, 2, UNHEX('5c285c696e5c6d61746862627b527d5c29'), 78309, NULL);

-- questao_id=78711 paragrafo_imagem_id=78714 ordem_original=1 (1 paragrafo(s) novo(s))
UPDATE paragrafo SET ordem = ordem + 1 WHERE questao_id = 78711 AND ordem >= 1;
INSERT INTO paragrafo (id, ordem, texto, questao_id, imagemFile_id) VALUES (900000315, 1, UNHEX('5c285c616c7068615c29'), 78711, NULL);

