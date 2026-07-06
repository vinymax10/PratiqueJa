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
-- WHERE:  questao_id IN (39450,39494,41522)

LOCK TABLES `paragrafo` WRITE;
/*!40000 ALTER TABLE `paragrafo` DISABLE KEYS */;
INSERT INTO `paragrafo` VALUES (39451,0,'Determine N tal que',NULL,39450);
INSERT INTO `paragrafo` VALUES (39453,1,NULL,39452,39450);
INSERT INTO `paragrafo` VALUES (39495,0,'Dada a igualdade , podemos afirmar que:',NULL,39494);
INSERT INTO `paragrafo` VALUES (39497,1,NULL,39496,39494);
INSERT INTO `paragrafo` VALUES (41523,0,'Em uma escola, o ano letivo é dividido em 4 bimestres. Ao final de cada um, os alunos realizam uma avaliação no valor de 10 pontos e, para serem aprovados, é necessário que a média anual das notas dos 4 bimestres supere ou tenha valor igual a 7. A média das notas é calculada de tal maneira que a prova do primeiro bimestre tem peso 1, a do segundo, peso 2, a do terceiro, peso 3 e a do quarto bimestre, peso 4.',NULL,41522);
INSERT INTO `paragrafo` VALUES (41524,1,'A seguir, as notas de 5 alunos dessa escola em cada um dos bimestres.',NULL,41522);
INSERT INTO `paragrafo` VALUES (41525,2,'Diante das condições apresentadas, Cláudio fez as contas necessárias para ver se conseguiria ficar com média anual das suas notas superior a 9 pontos.',NULL,41522);
INSERT INTO `paragrafo` VALUES (41526,3,'Após realizar todos os cálculos necessários de forma correta, Cláudio chegou à conclusão de que',NULL,41522);
INSERT INTO `paragrafo` VALUES (41528,4,NULL,41527,41522);
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

-- Dump completed on 2026-07-03  9:03:34
