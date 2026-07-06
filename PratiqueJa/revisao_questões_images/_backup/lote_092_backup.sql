-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: pratiqueja
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `paragrafo`
--
-- WHERE:  questao_id IN (129262,129286,129546,129558,129750,129763,130018,130096,130104)

LOCK TABLES `paragrafo` WRITE;
/*!40000 ALTER TABLE `paragrafo` DISABLE KEYS */;
INSERT INTO `paragrafo` VALUES (129263,0,'Considere a tabela de números reais ao lado:',NULL,129262);
INSERT INTO `paragrafo` VALUES (129264,1,'A soma desses valores é igual a:',NULL,129262);
INSERT INTO `paragrafo` VALUES (129266,2,NULL,129265,129262);
INSERT INTO `paragrafo` VALUES (129287,0,'Na figura, sem escala, o ponto E pertence a um lado do retângulo ABCD. O triângulo ABE tem área 18 cm2.',NULL,129286);
INSERT INTO `paragrafo` VALUES (129288,1,'Sabendo que a área do triângulo AED excede a área do triângulo BCE em 14 cm2, a medida, em cm, do segmento é',NULL,129286);
INSERT INTO `paragrafo` VALUES (129290,2,NULL,129289,129286);
INSERT INTO `paragrafo` VALUES (129292,3,NULL,129291,129286);
INSERT INTO `paragrafo` VALUES (129547,0,'No ano de 2018, o Instituto Brasileiro de Geografia e Estatística (IBGE) publicou um estudo contendo uma síntese dos indicadores sociais brasileiros.',NULL,129546);
INSERT INTO `paragrafo` VALUES (129548,1,'Na tabela a seguir, constam informações de 2017, sobre a taxa de ingresso no Ensino Superior, tendo como base o referido estudo.',NULL,129546);
INSERT INTO `paragrafo` VALUES (129549,2,'(Fonte: Síntese de Indicadores Sociais: uma análise das condições de vida da população brasileira. Instituto Brasileiro de Geografia e Estatística, 2018, p. 102, modificado. Disponível em: https://biblioteca.ibge.gov.br/visualizacao/livros/liv101629.pdf)',NULL,129546);
INSERT INTO `paragrafo` VALUES (129550,3,'Com base nas informações apresentadas, é correto afirmar que, em 2017,',NULL,129546);
INSERT INTO `paragrafo` VALUES (129552,4,NULL,129551,129546);
INSERT INTO `paragrafo` VALUES (129559,0,'A tabela indica o número de filhos de dez pacientes de um profissional:',NULL,129558);
INSERT INTO `paragrafo` VALUES (129560,1,'Quanto à média aritmética do número de filhos desses pacientes, é correto afirmar que ela é de',NULL,129558);
INSERT INTO `paragrafo` VALUES (129562,2,NULL,129561,129558);
INSERT INTO `paragrafo` VALUES (129751,0,'INSTRUÇÃO: Analise a tabela a seguir para responder à questão.',NULL,129750);
INSERT INTO `paragrafo` VALUES (129752,1,'Os dados apresentados a seguir se referem à atualização dos casos confirmados de sarampo no Brasil, em janeiro de 2019.',NULL,129750);
INSERT INTO `paragrafo` VALUES (129756,2,NULL,129755,129750);
INSERT INTO `paragrafo` VALUES (129757,3,'O número de casos confirmados de sarampo no estado do Pará é P% maior do que o número de casos confirmados no estado do Rio de Janeiro.',NULL,129750);
INSERT INTO `paragrafo` VALUES (129758,4,'Determine o valor que mais se aproxima de P.',NULL,129750);
INSERT INTO `paragrafo` VALUES (129764,0,'INSTRUÇÃO: Analise a tabela a seguir para responder à questão.',NULL,129763);
INSERT INTO `paragrafo` VALUES (129765,1,'Os dados apresentados a seguir se referem à atualização dos casos confirmados de sarampo no Brasil, em janeiro de 2019.',NULL,129763);
INSERT INTO `paragrafo` VALUES (129769,2,NULL,129768,129763);
INSERT INTO `paragrafo` VALUES (129770,3,'Em quantas Unidades Federadas o número de casos confirmados era múltiplo de 3 ou múltiplo de 5, em janeiro de 2019?',NULL,129763);
INSERT INTO `paragrafo` VALUES (130019,0,'Frederico, muito eclético, pratica várias atividades durante a semana. A tabela abaixo ilustra as atividades realizadas por Frederico a cada dia da semana.',NULL,130018);
INSERT INTO `paragrafo` VALUES (130021,1,NULL,130020,130018);
INSERT INTO `paragrafo` VALUES (130022,2,'Considere que Frederico nunca deixa de realizar as suas atividades diárias de acordo com a tabela, ainda que alguns minutos atrasado no dia da inauguração da loja de bombons de sua mãe, pois estava na aula de música. Cento e oitenta e cinco dias depois da inauguração da loja, Frederico começou a namorar. É possível afirmar que nessa data ele realizou que tipo de atividade?',NULL,130018);
INSERT INTO `paragrafo` VALUES (130099,0,NULL,130098,130096);
INSERT INTO `paragrafo` VALUES (130100,1,'A partir dos números escritos no quadro acima, julgue o item.',NULL,130096);
INSERT INTO `paragrafo` VALUES (130101,2,'O número –4 é maior que o número –7.',NULL,130096);
INSERT INTO `paragrafo` VALUES (130107,0,NULL,130106,130104);
INSERT INTO `paragrafo` VALUES (130108,1,'A partir dos números escritos no quadro acima, julgue o item.',NULL,130104);
INSERT INTO `paragrafo` VALUES (130109,2,'No quadro, o número 57 é o único divisível por 3.',NULL,130104);
/*!40000 ALTER TABLE `paragrafo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-07-05 11:39:32
