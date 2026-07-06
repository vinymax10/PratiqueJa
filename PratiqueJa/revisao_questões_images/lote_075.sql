-- questao_id=107019 paragrafo_imagem_id=107022 ordem_original=1 (1 paragrafo(s) novo(s))
UPDATE paragrafo SET ordem = ordem + 1 WHERE questao_id = 107019 AND ordem >= 1;
INSERT INTO paragrafo (id, ordem, texto, questao_id, imagemFile_id) VALUES (900000621, 1, UNHEX('5c285c626567696e7b61727261797d7b7c637c637c637c637c637c7d5c686c696e65205c7465787462667b4a414e4549524f7d2026205c7465787462667b46455645524549524f7d2026205c7465787462667b4d4152c3874f7d2026205c7465787462667b414252494c7d2026205c7465787462667b4d41494f7d5c5c5c686c696e65205c746578747b525c24207d382e3030307b2c7d30302026202620262026205c5c5c686c696e655c656e647b61727261797d5c29'), 107019, NULL);

-- questao_id=107761 paragrafo_imagem_id=107764 ordem_original=1 (1 paragrafo(s) novo(s))
UPDATE paragrafo SET ordem = ordem + 1 WHERE questao_id = 107761 AND ordem >= 1;
INSERT INTO paragrafo (id, ordem, texto, questao_id, imagemFile_id) VALUES (900000622, 1, UNHEX('5c285c6c616d6264615c29'), 107761, NULL);

-- questao_id=108333 paragrafo_imagem_id=108336 ordem_original=1 (1 paragrafo(s) novo(s))
UPDATE paragrafo SET ordem = ordem + 1 WHERE questao_id = 108333 AND ordem >= 1;
INSERT INTO paragrafo (id, ordem, texto, questao_id, imagemFile_id) VALUES (900000623, 1, UNHEX('5c285c6f7665726c696e657b41427d5c746578747b2065207d5c6f7665726c696e657b43447d5c29'), 108333, NULL);

