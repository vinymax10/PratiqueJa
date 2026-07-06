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
-- WHERE:  questao_id IN (18935,19012,19750)

LOCK TABLES `paragrafo` WRITE;
/*!40000 ALTER TABLE `paragrafo` DISABLE KEYS */;
INSERT INTO `paragrafo` VALUES (18936,0,'Considere a progressão aritmética de razão  -1/2 destacada a seguir:',NULL,18935);
INSERT INTO `paragrafo` VALUES (18938,1,NULL,18937,18935);
INSERT INTO `paragrafo` VALUES (18940,2,NULL,18939,18935);
INSERT INTO `paragrafo` VALUES (19013,0,'Dona Maria viu o seguinte anúncio no supermercado:',NULL,19012);
INSERT INTO `paragrafo` VALUES (19014,1,'PROMOÇÃO!',NULL,19012);
INSERT INTO `paragrafo` VALUES (19015,2,'Dona Maria aproveitou o preço e comprou três quilos de maçã e dois quilos de limão. Assinale a alternativa que apresenta CORRETAMENTE quanto será o total da conta de Dona Maria no supermercado.',NULL,19012);
INSERT INTO `paragrafo` VALUES (19017,3,NULL,19016,19012);
INSERT INTO `paragrafo` VALUES (19751,0,'A tabela abaixo informa a pontuação que as três equipes que participaram de uma gincana obtiveram em cada tarefa.',NULL,19750);
INSERT INTO `paragrafo` VALUES (19752,1,'Calcule a média de cada equipe e indique a alternativa que traz a informação CORRETA.',NULL,19750);
INSERT INTO `paragrafo` VALUES (19754,2,NULL,19753,19750);
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

-- Dump completed on 2026-07-02 14:54:57
