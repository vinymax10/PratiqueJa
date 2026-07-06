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
-- WHERE:  questao_id IN (42040,42070)

LOCK TABLES `paragrafo` WRITE;
/*!40000 ALTER TABLE `paragrafo` DISABLE KEYS */;
INSERT INTO `paragrafo` VALUES (42041,0,'Rédson é aluno dedicado e talentoso, sobretudo em operações numéricas. Ele, então, elaborou a expressão numérica indicada abaixo.',NULL,42040);
INSERT INTO `paragrafo` VALUES (42043,1,NULL,42042,42040);
INSERT INTO `paragrafo` VALUES (42044,2,'Rédson lançou o desafio a seus colegas de sala para que determinassem o valor da soma dos algarismos presentes no resultado da expressão R ao quadrado. Qual o valor encontrado?',NULL,42040);
INSERT INTO `paragrafo` VALUES (42071,0,'Lucas possui um terreno no formato de um quadrante circular. Ele o dividiu em três partes, do seguinte modo: A e B são, respectivamente, centros dos arcos  = 12m. Lucas vai delimitar nesse terreno uma parte cercada pelos arcos com três fileiras de arame. Quantos metros de arame ele usará para fazer esse serviço?',NULL,42070);
INSERT INTO `paragrafo` VALUES (42073,1,NULL,42072,42070);
INSERT INTO `paragrafo` VALUES (42075,2,NULL,42074,42070);
INSERT INTO `paragrafo` VALUES (42077,3,NULL,42076,42070);
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

-- Dump completed on 2026-07-03  9:08:09
