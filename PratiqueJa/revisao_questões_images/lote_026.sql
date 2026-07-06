-- questao_id=33743 paragrafo_imagem_id=33748 ordem_original=2 (1 paragrafo(s) novo(s))
UPDATE paragrafo SET ordem = ordem + 1 WHERE questao_id = 33743 AND ordem >= 2;
INSERT INTO paragrafo (id, ordem, texto, questao_id, imagemFile_id) VALUES (900000177, 2, UNHEX('5c285c6f7665726c696e657b41427d5c29'), 33743, NULL);

-- questao_id=33743 paragrafo_imagem_id=33746 ordem_original=1 (1 paragrafo(s) novo(s))
UPDATE paragrafo SET ordem = ordem + 1 WHERE questao_id = 33743 AND ordem >= 1;
INSERT INTO paragrafo (id, ordem, texto, questao_id, imagemFile_id) VALUES (900000178, 1, UNHEX('5c285c6f7665726c696e657b41437d5c29'), 33743, NULL);

-- questao_id=34033 paragrafo_imagem_id=34036 ordem_original=1 (1 paragrafo(s) novo(s))
UPDATE paragrafo SET ordem = ordem + 1 WHERE questao_id = 34033 AND ordem >= 1;
INSERT INTO paragrafo (id, ordem, texto, questao_id, imagemFile_id) VALUES (900000179, 1, UNHEX('5c285c64667261637b28342d31295c63646f7420352b332b377d7b355c63646f7420322d28372d32297d5c29'), 34033, NULL);

-- questao_id=34547 paragrafo_imagem_id=34551 ordem_original=2 (1 paragrafo(s) novo(s))
UPDATE paragrafo SET ordem = ordem + 1 WHERE questao_id = 34547 AND ordem >= 2;
INSERT INTO paragrafo (id, ordem, texto, questao_id, imagemFile_id) VALUES (900000180, 2, UNHEX('5c285c626567696e7b61727261797d7b7c637c637c637c637c7d5c686c696e65205c7465787462667b4ec3ba6d65726f206465206dc3a17175696e617320656d206f70657261c3a7c3a36f7d2026205c7465787462667b4ec3ba6d65726f20646520686f7261732074726162616c686164617320706f72206469617d2026205c7465787462667b5175616e746964616465206465207065c3a761732070726f64757a696461737d2026205c7465787462667b4ec3ba6d65726f20646520646961732074726162616c6861646f737d205c5c5c686c696e6520352026203820262039305c2c30303020262036205c5c5c686c696e652038202620362026203136325c2c30303020262078205c5c5c686c696e655c656e647b61727261797d5c29'), 34547, NULL);

