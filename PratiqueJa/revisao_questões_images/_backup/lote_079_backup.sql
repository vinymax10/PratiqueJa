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
-- WHERE:  questao_id IN (115956,116822)

LOCK TABLES `paragrafo` WRITE;
/*!40000 ALTER TABLE `paragrafo` DISABLE KEYS */;
INSERT INTO `paragrafo` VALUES (115957,0,'Lista de símbolos:',NULL,115956);
INSERT INTO `paragrafo` VALUES (115958,1,'Condicional',NULL,115956);
INSERT INTO `paragrafo` VALUES (115960,3,NULL,115959,115956);
INSERT INTO `paragrafo` VALUES (115961,4,'Bicondicional',NULL,115956);
INSERT INTO `paragrafo` VALUES (115963,6,NULL,115962,115956);
INSERT INTO `paragrafo` VALUES (115964,7,'Conector “e”',NULL,115956);
INSERT INTO `paragrafo` VALUES (115966,8,NULL,115965,115956);
INSERT INTO `paragrafo` VALUES (115967,9,'Conector “ou”',NULL,115956);
INSERT INTO `paragrafo` VALUES (115969,10,NULL,115968,115956);
INSERT INTO `paragrafo` VALUES (115970,11,'Conector “ou” exclusivo',NULL,115956);
INSERT INTO `paragrafo` VALUES (115972,12,NULL,115971,115956);
INSERT INTO `paragrafo` VALUES (115973,13,'Negação da proposição',NULL,115956);
INSERT INTO `paragrafo` VALUES (115975,14,NULL,115974,115956);
INSERT INTO `paragrafo` VALUES (115976,15,'Acerca das raízes da seguinte equação de 2º grau: \\(x^2 - 3x - 10\\), é correto afirmar que:',NULL,115956);
INSERT INTO `paragrafo` VALUES (900000636,5,'\\(\\Leftrightarrow\\)',NULL,115956);
INSERT INTO `paragrafo` VALUES (900000637,2,'\\(\\Rightarrow\\)',NULL,115956);
INSERT INTO `paragrafo` VALUES (116823,0,'Uma assistente social atendeu, em determinado mês, o total de 120 pessoas. A tabela mostra algumas informações sobre esses atendimentos, por período, e a porcentagem de pessoas atendidas por período, em relação ao número total de pessoas atendidas no mês.',NULL,116822);
INSERT INTO `paragrafo` VALUES (116824,1,'No período da tarde, o número de mulheres atendidas superou o número de homens atendidos em',NULL,116822);
INSERT INTO `paragrafo` VALUES (116826,2,NULL,116825,116822);
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

-- Dump completed on 2026-07-04 18:04:35
