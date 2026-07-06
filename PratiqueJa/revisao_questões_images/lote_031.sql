-- questao_id=38345 paragrafo_imagem_id=38350 ordem_original=2 (STALE — questao ja recebeu insercao no
-- lote_030; ordem real atual de 38350 no banco e 3, corrigido manualmente antes de aplicar)
UPDATE paragrafo SET ordem = ordem + 1 WHERE questao_id = 38345 AND ordem >= 3;
INSERT INTO paragrafo (id, ordem, texto, questao_id, imagemFile_id) VALUES (900000194, 3, UNHEX('5c285c64667261637b795f312b795f322b795f332b5c6c646f74732b795f6e7d7b6e7d5c29'), 38345, NULL);

-- questao_id=38615 paragrafo_imagem_id=38619 ordem_original=2 (1 paragrafo(s) novo(s))
UPDATE paragrafo SET ordem = ordem + 1 WHERE questao_id = 38615 AND ordem >= 2;
INSERT INTO paragrafo (id, ordem, texto, questao_id, imagemFile_id) VALUES (900000195, 2, UNHEX('5c285c626567696e7b61727261797d7b7c637c637c7d5c686c696e65205c7465787462667b4ec3ba6d65726f20646520626f6ec3a9737d2026205c7465787462667b437573746f20746f74616c2028525c24297d205c5c5c686c696e65203439202620343431205c5c5c686c696e65203532202620343638205c5c5c686c696e655c656e647b61727261797d5c29'), 38615, NULL);

-- questao_id=38767 paragrafo_imagem_id=38770 ordem_original=1 (1 paragrafo(s) novo(s))
UPDATE paragrafo SET ordem = ordem + 1 WHERE questao_id = 38767 AND ordem >= 1;
INSERT INTO paragrafo (id, ordem, texto, questao_id, imagemFile_id) VALUES (900000196, 1, UNHEX('5c285c64667261637b5c636f732878292d5c6f70657261746f726e616d657b74677d2878297d7b5c7365632878292d317d5c29'), 38767, NULL);

