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
-- WHERE:  questao_id IN (21819,21965,22254)

LOCK TABLES `paragrafo` WRITE;
/*!40000 ALTER TABLE `paragrafo` DISABLE KEYS */;
INSERT INTO `paragrafo` VALUES (21820,0,'Qual é o valor da solução (S) da expressão abaixo?',NULL,21819);
INSERT INTO `paragrafo` VALUES (21822,1,NULL,21821,21819);
INSERT INTO `paragrafo` VALUES (21966,0,'O Coeficiente de Rendimento Acadêmico (CRA) é uma medida de desempenho atribuída ao aluno no final de cada período letivo em uma universidade. O CRA é calculado a partir de uma média ponderada, em que a carga horária da disciplina cursada é equivalente ao peso desta média ponderada. O quadro I apresenta as disciplinas cursadas por um aluno e suas respectivas carga horária e conceito obtido no final do período letivo, enquanto que o quadro II apresenta a equivalência em notas de cada um dos conceitos, para efeito de cálculo do CRA.',NULL,21965);
INSERT INTO `paragrafo` VALUES (21967,1,'Desta forma, ao final do período letivo o aluno terá seu CRA igual a',NULL,21965);
INSERT INTO `paragrafo` VALUES (21969,2,NULL,21968,21965);
INSERT INTO `paragrafo` VALUES (22255,0,'Qual é o valor da solução (S) da expressão abaixo?',NULL,22254);
INSERT INTO `paragrafo` VALUES (22257,1,NULL,22256,22254);
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

-- Dump completed on 2026-07-02 15:43:05
