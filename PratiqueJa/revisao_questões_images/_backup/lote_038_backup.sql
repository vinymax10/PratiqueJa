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
-- WHERE:  questao_id IN (51214,51266,51872,51881,51900)

LOCK TABLES `paragrafo` WRITE;
/*!40000 ALTER TABLE `paragrafo` DISABLE KEYS */;
INSERT INTO `paragrafo` VALUES (51215,0,'A tabela mostra a duração de cada uma das 5 faixas de um CD de música clássica.',NULL,51214);
INSERT INTO `paragrafo` VALUES (51217,1,NULL,51216,51214);
INSERT INTO `paragrafo` VALUES (51218,2,'Sabendo-se que o tempo total de duração dessas 5 faixas é 28 minutos e 27 segundos, então, o tempo de duração da 2a faixa é',NULL,51214);
INSERT INTO `paragrafo` VALUES (51267,0,'A tabela mostra a movimentação da conta corrente de uma pessoa no mês de abril.',NULL,51266);
INSERT INTO `paragrafo` VALUES (51269,1,NULL,51268,51266);
INSERT INTO `paragrafo` VALUES (51270,2,'O valor do depósito feito no dia 25 foi de',NULL,51266);
INSERT INTO `paragrafo` VALUES (51873,0,'Encontre o valor de θ , sabendo que',NULL,51872);
INSERT INTO `paragrafo` VALUES (51875,1,NULL,51874,51872);
INSERT INTO `paragrafo` VALUES (51882,0,'O valor da expressão é',NULL,51881);
INSERT INTO `paragrafo` VALUES (51884,1,NULL,51883,51881);
INSERT INTO `paragrafo` VALUES (51901,0,'A raiz',NULL,51900);
INSERT INTO `paragrafo` VALUES (51902,1,'vale:',NULL,51900);
INSERT INTO `paragrafo` VALUES (51904,2,NULL,51903,51900);
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

-- Dump completed on 2026-07-03 10:53:33
