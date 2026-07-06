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
-- WHERE:  questao_id IN (6923,6932,7221)

LOCK TABLES `paragrafo` WRITE;
/*!40000 ALTER TABLE `paragrafo` DISABLE KEYS */;
INSERT INTO `paragrafo` VALUES (6924,0,'Sobre a Progressão Aritmética (PA) a seguir, assinale a alternativa incorreta:',NULL,6923);
INSERT INTO `paragrafo` VALUES (6926,1,NULL,6925,6923);
INSERT INTO `paragrafo` VALUES (6933,0,'Analise a seguir o anúncio de uma loja de eletrodomésticos:',NULL,6932);
INSERT INTO `paragrafo` VALUES (6935,1,NULL,6934,6932);
INSERT INTO `paragrafo` VALUES (6936,2,'É correto afirmar que se o cliente optar pela compra a prazo, ele pagará um acréscimo sobre o valor à vista, em porcentagem, de:',NULL,6932);
INSERT INTO `paragrafo` VALUES (7222,0,'Com base em estatística, julgue o item a seguir.',NULL,7221);
INSERT INTO `paragrafo` VALUES (7223,1,'Considere que, em uma turma de matemática, o professor tenha distribuído as notas da primeira avaliação dos alunos conforme a tabela apresentada adiante. Com base nos dados dessa tabela, é correto afirmar que a média das notas dessa turma na primeira avaliação foi superior a 5.',NULL,7221);
INSERT INTO `paragrafo` VALUES (7225,2,NULL,7224,7221);
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

-- Dump completed on 2026-07-02 10:24:27
