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
-- WHERE:  questao_id IN (58428,58508,58542,58628,58851,58880)

LOCK TABLES `paragrafo` WRITE;
/*!40000 ALTER TABLE `paragrafo` DISABLE KEYS */;
INSERT INTO `paragrafo` VALUES (58429,0,'A tabela apresenta as alíquotas para cálculo do Imposto sobre as Rendas das Pessoas Físicas (IRPF), em vigor desde abril de 2015.',NULL,58428);
INSERT INTO `paragrafo` VALUES (58431,1,NULL,58430,58428);
INSERT INTO `paragrafo` VALUES (58432,2,'Observe que, de acordo com a tabela, o cálculo do IRPF é escalonado, no sentido de que sobre a parcela da base de cálculo até R$ 1.903,98 não incide imposto, sobre a parcela situada entre R$ 1.903,99 e R$ 2.826,65 incide uma alíquota de 7,5%, e assim sucessivamente. Que valor de imposto de renda incide sobre uma base de cálculo de R$ 3.000,00?',NULL,58428);
INSERT INTO `paragrafo` VALUES (58509,0,'Na tabela a seguir, são apresentadas informações sobre a distribuição do número de servidores que atuam nas secretarias A e B de um município, com base nos seus graus de instrução.',NULL,58508);
INSERT INTO `paragrafo` VALUES (58510,1,'Com base nas informações apresentadas, assinale a alternativa que contém uma afirmação necessariamente verdadeira.',NULL,58508);
INSERT INTO `paragrafo` VALUES (58512,2,NULL,58511,58508);
INSERT INTO `paragrafo` VALUES (58543,0,'A tabela apresenta informações sobre o número de acertos em uma avaliação com 5 questões de múltipla escolha.',NULL,58542);
INSERT INTO `paragrafo` VALUES (58545,1,NULL,58544,58542);
INSERT INTO `paragrafo` VALUES (58546,2,'Sabendo-se que média aritmética de acertos nessa avaliação foi de 2,5 questões, o número total de pessoas que fizeram essa avaliação foi',NULL,58542);
INSERT INTO `paragrafo` VALUES (58629,0,'Ana e Bruno fizeram uma atividade de adivinhação. Ana dizia um número, Bruno analisava o número dito por Ana e respondia com outro número.',NULL,58628);
INSERT INTO `paragrafo` VALUES (58630,1,'Bruno disse que utiliza uma regra para, a partir do número de Ana, apresentar outro número como resposta. Analisando os números da tabela, pode-se afirmar que, usando a mesma regra, se Ana disser 10, o número em resposta de Bruno será:',NULL,58628);
INSERT INTO `paragrafo` VALUES (58632,2,NULL,58631,58628);
INSERT INTO `paragrafo` VALUES (58852,0,'Um grupo de pesquisadores deseja comparar o panorama de infecções por covid-19 entre três municípios (A, B e C) por meio de um ranking a ser elaborado com dados fornecidos pelas respectivas prefeituras. Na ausência de alguns dados, decidiu-se que a maneira mais adequada será a comparação pelo quociente Q = C/P ,em que C corresponde ao número total de casos confirmados no município e P, à sua população. Assim, segundo o critério definido pelos pesquisadores, o município com maior quociente Q seria escolhido como aquele com a pior situação da covid-19.',NULL,58851);
INSERT INTO `paragrafo` VALUES (58853,1,'Os dados divulgados pelas prefeituras dos três municípios estão apresentados na tabela adiante.',NULL,58851);
INSERT INTO `paragrafo` VALUES (58854,2,'Com base nas informações precedentes, e admitindo que o índice de testagem nos três municípios tenha sido estatisticamente o mesmo, é correto afirmar que',NULL,58851);
INSERT INTO `paragrafo` VALUES (58856,3,NULL,58855,58851);
INSERT INTO `paragrafo` VALUES (58881,0,'Uma maneira bastante difundida de acompanhar a evolução da covid-19 em uma localidade é a média móvel de casos dos últimos 7 dias no n-ésimo dia (MVn). Por definição, no n-ésimo dia, para n = 7, 8, 9, 10 e 11, tem-se MVn = em que Ci é o número de novos casos de determinada região no n-ésimo dia.',NULL,58880);
INSERT INTO `paragrafo` VALUES (58882,1,'A tabela seguinte apresenta dados sobre o número de novos casos de covid-19 em dois bairros (A e B) de uma mesma cidade, no transcorrer de 11 dias.',NULL,58880);
INSERT INTO `paragrafo` VALUES (58883,2,'A média móvel de casos dos últimos 7 dias no dia 8 do bairro A é a média aritmética entre os casos ocorridos nesse bairro nos dias 2, 3, 4, 5, 6, 7 e 8, e assim por diante.',NULL,58880);
INSERT INTO `paragrafo` VALUES (58884,3,'Com base nessas informações, e considerando-se n = 7, 8, 9, 10 e 11, é correto afirmar que a menor diferença entre as médias móveis dos bairros A e B ocorreu no dia',NULL,58880);
INSERT INTO `paragrafo` VALUES (58886,4,NULL,58885,58880);
INSERT INTO `paragrafo` VALUES (58888,5,NULL,58887,58880);
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

-- Dump completed on 2026-07-03 12:41:17
