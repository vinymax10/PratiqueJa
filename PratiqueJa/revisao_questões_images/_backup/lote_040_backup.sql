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
-- WHERE:  questao_id IN (58090,58111)

LOCK TABLES `paragrafo` WRITE;
/*!40000 ALTER TABLE `paragrafo` DISABLE KEYS */;
INSERT INTO `paragrafo` VALUES (58091,0,'Algum tempo após a ingestão de uma medicação, nosso organismo a metaboliza e começa a eliminá-la, por meio da ação do fígado e dos rins. A tabela abaixo mostra o decrescimento da quantidade de um antibiótico na corrente sanguínea de um paciente:',NULL,58090);
INSERT INTO `paragrafo` VALUES (58092,1,'Se Q continuar decrescendo sempre com a regularidade observada na tabela, após 8 horas da ingestão do antibiótico, a quantidade Q, em mg, será, aproximadamente,',NULL,58090);
INSERT INTO `paragrafo` VALUES (58094,2,NULL,58093,58090);
INSERT INTO `paragrafo` VALUES (58112,0,'O quadro a seguir apresenta os salários de 16 funcionários de um dos departamentos de uma empresa.',NULL,58111);
INSERT INTO `paragrafo` VALUES (58113,1,'Serão contratados mais 4 funcionários, todos com o mes- mo salário, para esse departamento de modo que a média salaraial dos 20 funcionários seja igual a R$3.500,00. Assim, o salário de cada um desses novos funcionários será de',NULL,58111);
INSERT INTO `paragrafo` VALUES (58115,2,NULL,58114,58111);
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

-- Dump completed on 2026-07-03 12:09:45
