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
-- WHERE:  questao_id IN (90372,90444,90453)

LOCK TABLES `paragrafo` WRITE;
/*!40000 ALTER TABLE `paragrafo` DISABLE KEYS */;
INSERT INTO `paragrafo` VALUES (90373,0,'A tabela a seguir mostra o número de atendimentos ao público realizados por dentistas em um posto de saúde, durante uma semana de funcionamento.',NULL,90372);
INSERT INTO `paragrafo` VALUES (90375,1,NULL,90374,90372);
INSERT INTO `paragrafo` VALUES (90376,2,'Qual foi a porcentagem de crianças atendidas durante essa semana?',NULL,90372);
INSERT INTO `paragrafo` VALUES (90447,0,NULL,90446,90444);
INSERT INTO `paragrafo` VALUES (90449,1,'Com base nas informações do anúncio acima, julgue o item subseqüente.',NULL,90444);
INSERT INTO `paragrafo` VALUES (90450,2,'Uma escola que precisa de 20 calculadoras, 18 canetas, 10 lapiseiras e 16 borrachas, gasta menos comprando 20 pacotes do que adquirindo os produtos individualmente.',NULL,90444);
INSERT INTO `paragrafo` VALUES (90456,0,NULL,90455,90453);
INSERT INTO `paragrafo` VALUES (90458,1,'Com base nas informações do anúncio acima, julgue o item subseqüente.',NULL,90453);
INSERT INTO `paragrafo` VALUES (90459,2,'É mais vantajoso comprar todo o pacote do que comprar apenas a caneta, a lapiseira e a borracha.',NULL,90453);
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

-- Dump completed on 2026-07-03 22:56:22
