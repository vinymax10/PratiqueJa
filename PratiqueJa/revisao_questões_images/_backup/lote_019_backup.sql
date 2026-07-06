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
-- WHERE:  questao_id IN (25193,25904,26371,26423,26575,26625)

LOCK TABLES `paragrafo` WRITE;
/*!40000 ALTER TABLE `paragrafo` DISABLE KEYS */;
INSERT INTO `paragrafo` VALUES (25194,0,'Considere as tabelas a seguir:',NULL,25193);
INSERT INTO `paragrafo` VALUES (25196,1,NULL,25195,25193);
INSERT INTO `paragrafo` VALUES (25197,2,'Um morador de Ilhabela da categoria residencial/comum que consuma 60 m3 pagará, referente unicamente às tarifas de água e de esgoto, um total de',NULL,25193);
INSERT INTO `paragrafo` VALUES (25905,0,'Uma empresa de tecnologia decidiu ampliar seus produtos e, para tal, precisou conhecer a proficiência (certificação) de seus programadores em relação as linguagens de programação utilizadas na empresa. A pesquisa demonstrou o seguinte resultado, conforme o quadro abaixo:',NULL,25904);
INSERT INTO `paragrafo` VALUES (25907,1,NULL,25906,25904);
INSERT INTO `paragrafo` VALUES (25908,2,'À diretoria decide que os programadores que possuem certificações exclusivamente em Python e Java ficarão responsáveis pela implementação desses novos produtos. Assim, os programadores que farão parte desse novo produto são exatamente um total de',NULL,25904);
INSERT INTO `paragrafo` VALUES (26372,0,'A expressão tem resultado',NULL,26371);
INSERT INTO `paragrafo` VALUES (26374,1,NULL,26373,26371);
INSERT INTO `paragrafo` VALUES (26424,0,'Na prova de Estatística, a probabilidade de a questão A ser resolvida corretamente é . Se quatro alunos fizerem essa prova, a probabilidade de a questão ser resolvida corretamente por pelo menos um aluno é aproximadamente',NULL,26423);
INSERT INTO `paragrafo` VALUES (26426,1,NULL,26425,26423);
INSERT INTO `paragrafo` VALUES (26576,0,'Durante a pandemia da COVID-19, muito se tem falado sobre o conceito de médias móveis. Esse conceito também é amplamente utilizado no estudo de gráficos de preços de ativos no mercado financeiro. O propósito da média móvel é suavizar o gráfico a fim de melhor visualizar tendências de longo prazo, ignorando oscilações de curto prazo, já que estas são, muitas vezes, causadas por instabilidades momentâneas ou pela irregularidade na coleta dos dados. Há vários tipos de médias móveis, entre elas a simples, a cumulativa e a exponencial. Para exemplificar, veja o gráfico com valores fictícios de um ativo financeiro e, em seguida, o gráfico das médias móveis desse ativo com período de 04 dias. Veja que a média móvel simples, de um ativo financeiro, com período 04 (quatro), é calculada em cada data, tomando-se a média aritmética dos valores do ativo em quatro dias consecutivos, sendo o último dia do período igual à data desejada. Com base nos dados das tabelas abaixo referentes ao mesmo ativo fictício, calcule o valor da média móvel com período 04 referente ao dia 24 (vinte e quatro).',NULL,26575);
INSERT INTO `paragrafo` VALUES (26578,1,NULL,26577,26575);
INSERT INTO `paragrafo` VALUES (26580,2,NULL,26579,26575);
INSERT INTO `paragrafo` VALUES (26582,3,NULL,26581,26575);
INSERT INTO `paragrafo` VALUES (26584,4,NULL,26583,26575);
INSERT INTO `paragrafo` VALUES (26626,0,'Sabendo que a ≠ b, uma expressão que simplifica é:',NULL,26625);
INSERT INTO `paragrafo` VALUES (26628,1,NULL,26627,26625);
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

-- Dump completed on 2026-07-02 17:26:01
