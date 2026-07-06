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
-- WHERE:  questao_id IN (88693,88984,89014,89048)

LOCK TABLES `paragrafo` WRITE;
/*!40000 ALTER TABLE `paragrafo` DISABLE KEYS */;
INSERT INTO `paragrafo` VALUES (88694,0,'Um anagrama é uma recombinação das letras de uma palavra. Considere a palavra ABA, ela pode ser recombinada de 3 formas possíveis, conforme tabela abaixo.',NULL,88693);
INSERT INTO `paragrafo` VALUES (88695,1,'Assinale a alternativa que indica corretamente o número de vezes mais que a palavra ABBA tem de recombinações (anagramas) possíveis em relação à palavra ABA.',NULL,88693);
INSERT INTO `paragrafo` VALUES (88697,2,NULL,88696,88693);
INSERT INTO `paragrafo` VALUES (88985,0,'O desenho de uma cadeira será reduzido à terça parte dos seus comprimentos. A figura indica o início do processo de redução, com sendo transformado em , que é um segmento com a terça parte do comprimento do segmento de .',NULL,88984);
INSERT INTO `paragrafo` VALUES (88987,1,NULL,88986,88984);
INSERT INTO `paragrafo` VALUES (88989,2,NULL,88988,88984);
INSERT INTO `paragrafo` VALUES (88991,3,NULL,88990,88984);
INSERT INTO `paragrafo` VALUES (88993,4,NULL,88992,88984);
INSERT INTO `paragrafo` VALUES (88994,5,'Depois de feita a redução, a área do assento da cadeira inicialmente desenhada será igual a área do assento da nova cadeira desenhada multiplicada por',NULL,88984);
INSERT INTO `paragrafo` VALUES (89015,0,'Um professor do 9º ano pediu que seus alunos resolvessem a equação \\((x + 3)^2 = (2x - 1)^2\\). Um dos alunos resolveu o problema da seguinte forma:',NULL,89014);
INSERT INTO `paragrafo` VALUES (89017,1,NULL,89016,89014);
INSERT INTO `paragrafo` VALUES (89018,2,'Com relação à resolução feita pelo aluno, é correto afirmar que ele encontrou',NULL,89014);
INSERT INTO `paragrafo` VALUES (89049,0,'Na figura, ABCD é um quadrado que delimita uma região plana de área 16 cm2 , M e N são pontos médios de e , e o triângulo MNP é equilátero.',NULL,89048);
INSERT INTO `paragrafo` VALUES (89050,1,'O perímetro do polígono ABCMPN (indicado em linha mais grossa na figura), em centímetros, é igual a',NULL,89048);
INSERT INTO `paragrafo` VALUES (89052,2,NULL,89051,89048);
INSERT INTO `paragrafo` VALUES (89054,3,NULL,89053,89048);
INSERT INTO `paragrafo` VALUES (89056,4,NULL,89055,89048);
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

-- Dump completed on 2026-07-03 21:48:25
