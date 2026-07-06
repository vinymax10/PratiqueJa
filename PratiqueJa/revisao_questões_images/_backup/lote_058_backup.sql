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
-- WHERE:  questao_id IN (89174,89830,89841,89852)

LOCK TABLES `paragrafo` WRITE;
/*!40000 ALTER TABLE `paragrafo` DISABLE KEYS */;
INSERT INTO `paragrafo` VALUES (89175,0,'Uma pessoa comprou várias garrafas de diferentes sabores de suco de frutas. A tabela mostra o sabor do suco, a respectiva quantidade de garrafas e o preço unitário da garrafa.',NULL,89174);
INSERT INTO `paragrafo` VALUES (89177,1,NULL,89176,89174);
INSERT INTO `paragrafo` VALUES (89178,2,'Considerando-se o número total de garrafas compradas, o preço de uma garrafa de suco foi, em média, R$ 5,00. O preço de uma garrafa de suco de caju é',NULL,89174);
INSERT INTO `paragrafo` VALUES (89831,0,'Alguns termos nutricionais utilizados pelos fabricantes de produtos alimentícios são definidos por lei de acordo com a tabela abaixo.',NULL,89830);
INSERT INTO `paragrafo` VALUES (89833,1,'Guia de saúde familiar. In: Alimentos e nutrição. Istoé, v. 16, p. 76 (com adaptações).',NULL,89830);
INSERT INTO `paragrafo` VALUES (89835,2,NULL,89834,89830);
INSERT INTO `paragrafo` VALUES (89836,3,'Guia de saúde familiar. In: Alimentos e nutrição. Istoé, v. 16, p. 76 (com adaptações).',NULL,89830);
INSERT INTO `paragrafo` VALUES (89837,4,'A partir dessas informações, julgue o item a seguir.',NULL,89830);
INSERT INTO `paragrafo` VALUES (89838,5,'Considere que 1,5 kg de um alimento convencional tenha 10 g de açúcar e que a quantidade de açúcar desse alimento foi reduzida, o que permite rotulá-lo como “com açúcar reduzido”. Nessa situação, o alimento não-convencional também pode receber o rótulo de “sem açúcar”.',NULL,89830);
INSERT INTO `paragrafo` VALUES (89842,0,'Alguns termos nutricionais utilizados pelos fabricantes de produtos alimentícios são definidos por lei de acordo com a tabela abaixo.',NULL,89841);
INSERT INTO `paragrafo` VALUES (89844,1,'Guia de saúde familiar. In: Alimentos e nutrição. Istoé, v. 16, p. 76 (com adaptações).',NULL,89841);
INSERT INTO `paragrafo` VALUES (89846,2,NULL,89845,89841);
INSERT INTO `paragrafo` VALUES (89847,3,'Guia de saúde familiar. In: Alimentos e nutrição. Istoé, v. 16, p. 76 (com adaptações).',NULL,89841);
INSERT INTO `paragrafo` VALUES (89848,4,'A partir dessas informações, julgue o item a seguir.',NULL,89841);
INSERT INTO `paragrafo` VALUES (89849,5,'Supondo que uma porção de 250 g de determinado alimento tenha 0,45 g de gordura, então esse alimento pode receber o rótulo de “sem gordura”.',NULL,89841);
INSERT INTO `paragrafo` VALUES (89853,0,'Alguns termos nutricionais utilizados pelos fabricantes de produtos alimentícios são definidos por lei de acordo com a tabela abaixo.',NULL,89852);
INSERT INTO `paragrafo` VALUES (89855,1,'Guia de saúde familiar. In: Alimentos e nutrição. Istoé, v. 16, p. 76 (com adaptações).',NULL,89852);
INSERT INTO `paragrafo` VALUES (89857,2,NULL,89856,89852);
INSERT INTO `paragrafo` VALUES (89858,3,'Guia de saúde familiar. In: Alimentos e nutrição. Istoé, v. 16, p. 76 (com adaptações).',NULL,89852);
INSERT INTO `paragrafo` VALUES (89859,4,'A partir dessas informações, julgue o item a seguir.',NULL,89852);
INSERT INTO `paragrafo` VALUES (89860,5,'Considerando que 335 mL de um suco convencional tem 185 kcal de energia, então 200 mL do mesmo suco light podem ter até 60 kcal.',NULL,89852);
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

-- Dump completed on 2026-07-03 22:23:11
