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
-- WHERE:  questao_id IN (37454,37468,38231,38345)

LOCK TABLES `paragrafo` WRITE;
/*!40000 ALTER TABLE `paragrafo` DISABLE KEYS */;
INSERT INTO `paragrafo` VALUES (37455,0,'No Pisa, avaliação internacional da OCDE, o cenário é difícil para jovens de 15 a 16 anos. Desde 2009, o Brasil não apresenta avanços nesta avaliação. Com o gráfico abaixo vemos a evolução da média do Brasil em Matemática no Pisa. Temos um grande desafio - 46,5% de nossos alunos na rede pública estão abaixo do nível 1 em Matemática.',NULL,37454);
INSERT INTO `paragrafo` VALUES (37456,1,'(Adaptado de https://fundacaolemann.org.br/noticias/como-esta-nossaeducacao-basica? - acessado em 06/03/2019 às 19:31h).',NULL,37454);
INSERT INTO `paragrafo` VALUES (37457,2,'Evolução da média do Brasil no Pisa em Matemática',NULL,37454);
INSERT INTO `paragrafo` VALUES (37458,3,'Seja xi a média em Matemática obtida pelo Brasil no Pisa e n a quantidade dessas médias nos anos 2003, 2006, 2009, 2012 e 2015. Determine',NULL,37454);
INSERT INTO `paragrafo` VALUES (37460,4,NULL,37459,37454);
INSERT INTO `paragrafo` VALUES (37462,5,NULL,37461,37454);
INSERT INTO `paragrafo` VALUES (37469,0,'Assinale o valor correto de “a” na expressão: = .',NULL,37468);
INSERT INTO `paragrafo` VALUES (37471,1,NULL,37470,37468);
INSERT INTO `paragrafo` VALUES (37473,2,NULL,37472,37468);
INSERT INTO `paragrafo` VALUES (38232,0,'Um sistema de roteirização entre alguns aeroportos do país, representado no gráfico abaixo, apresenta o número de vôos em certo período associado a uma matriz M.',NULL,38231);
INSERT INTO `paragrafo` VALUES (38235,2,'Marque a alternativa que associa corretamente a linha/coluna da matriz M com o aeroporto.',NULL,38231);
INSERT INTO `paragrafo` VALUES (164896,1,NULL,2,38231);
INSERT INTO `paragrafo` VALUES (38346,0,'Dado que então determine P tal que P = onde yi = 6xi − 2.',NULL,38345);
INSERT INTO `paragrafo` VALUES (38348,1,NULL,38347,38345);
INSERT INTO `paragrafo` VALUES (38350,2,NULL,38349,38345);
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

-- Dump completed on 2026-07-02 22:17:39
