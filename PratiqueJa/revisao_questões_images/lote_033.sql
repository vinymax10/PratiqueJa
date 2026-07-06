-- questao_id=42040 paragrafo_imagem_id=42043 ordem_original=1 (1 paragrafo(s) novo(s))
UPDATE paragrafo SET ordem = ordem + 1 WHERE questao_id = 42040 AND ordem >= 1;
INSERT INTO paragrafo (id, ordem, texto, questao_id, imagemFile_id) VALUES (900000200, 1, UNHEX('5c28523d5c64667261637b35377d7b33377d2b5c64667261637b353735377d7b333733377d2b5c64667261637b3537353735377d7b3337333733377d2b5c63646f74732b5c64667261637b3537353735375c63646f747335375c20283134385c746578747b20616c67617269736d6f737d297d7b3337333733375c63646f747333375c20283134385c746578747b20616c67617269736d6f737d297d5c29'), 42040, NULL);

-- questao_id=42070 paragrafo_imagem_id=42075 ordem_original=2 (1 paragrafo(s) novo(s))
UPDATE paragrafo SET ordem = ordem + 1 WHERE questao_id = 42070 AND ordem >= 2;
INSERT INTO paragrafo (id, ordem, texto, questao_id, imagemFile_id) VALUES (900000201, 2, UNHEX('5c285c6f7665727365747b5c66726f776e7d7b4f4e7d5c292c205c285c6f7665727365747b5c66726f776e7d7b4f4d7d5c292065205c285c6f7665726c696e657b4d4e7d5c29'), 42070, NULL);

-- questao_id=42070 paragrafo_imagem_id=42073 ordem_original=1 (1 paragrafo(s) novo(s))
UPDATE paragrafo SET ordem = ordem + 1 WHERE questao_id = 42070 AND ordem >= 1;
INSERT INTO paragrafo (id, ordem, texto, questao_id, imagemFile_id) VALUES (900000202, 1, UNHEX('5c285c6f7665727365747b5c66726f776e7d7b4f4d7d5c292065205c285c6f7665727365747b5c66726f776e7d7b4f4e7d5c292c206461646f20717565205c285c6f7665726c696e657b4f417d5c29'), 42070, NULL);

