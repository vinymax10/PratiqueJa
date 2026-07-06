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
-- WHERE:  questao_id IN (111902,111914,113218,113324)

LOCK TABLES `paragrafo` WRITE;
/*!40000 ALTER TABLE `paragrafo` DISABLE KEYS */;
INSERT INTO `paragrafo` VALUES (111903,0,'A figura mostra um hexágono regular ABCDEF e um quadrado ABXY.',NULL,111902);
INSERT INTO `paragrafo` VALUES (111905,1,NULL,111904,111902);
INSERT INTO `paragrafo` VALUES (111906,2,'O ângulo mede:',NULL,111902);
INSERT INTO `paragrafo` VALUES (111908,3,NULL,111907,111902);
INSERT INTO `paragrafo` VALUES (111916,0,NULL,111915,111914);
INSERT INTO `paragrafo` VALUES (111917,1,'Na figura, o ângulo  é igual a:',NULL,111914);
INSERT INTO `paragrafo` VALUES (111919,2,NULL,111918,111914);
INSERT INTO `paragrafo` VALUES (113219,0,'Qual dos pares ordenados a seguir pertence ao gráfico da função f de tal que \\(f(x) = 3x - 8\\)?',NULL,113218);
INSERT INTO `paragrafo` VALUES (113221,1,NULL,113220,113218);
INSERT INTO `paragrafo` VALUES (113325,0,'De acordo com a tabela a seguir, sorteando um médico ao acaso e tendo a informação de que este NÃO é do sexo feminino, qual a probabilidade de ter sido sorteado um médico cuja especialidade NÃO seja oftalmologia ou pediatria?',NULL,113324);
INSERT INTO `paragrafo` VALUES (113327,1,NULL,113326,113324);
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

-- Dump completed on 2026-07-04 17:54:11
