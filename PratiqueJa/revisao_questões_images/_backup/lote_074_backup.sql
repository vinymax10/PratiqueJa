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
-- WHERE:  questao_id IN (105450,105775)

LOCK TABLES `paragrafo` WRITE;
/*!40000 ALTER TABLE `paragrafo` DISABLE KEYS */;
INSERT INTO `paragrafo` VALUES (105451,0,'A tabela a seguir apresenta os salários mensais de todos os 16 funcionários de um departamento.',NULL,105450);
INSERT INTO `paragrafo` VALUES (105452,1,'Serão contratados para esse departamento quatro novos funcionários com salários iguais, pois irão executar a mesma função. Para que a média salarial dos 20 funcionários não ultrapasse o valor de R$ 4.500,00, o maior salário possível para cada um desses quatro novos contratados não poderá ultrapassar o valor de',NULL,105450);
INSERT INTO `paragrafo` VALUES (105454,2,NULL,105453,105450);
INSERT INTO `paragrafo` VALUES (105776,0,'Em maio de 2017, a Sanepar alterou a forma de cálculo da conta da água e de seu consumo mínimo. Antes da mudança, o valor mínimo era de R$ 33,74 e permitia a utilização de até 10 metros cúbicos de água. Depois da mudança, o valor mínimo passou a ser de R$ 32,90 e permite a utilização de até 5 metros cúbicos de água. Além disso, foram criadas mais faixas de consumo, conforme o quadro ao lado:',NULL,105775);
INSERT INTO `paragrafo` VALUES (105777,1,'Com base nessas informações, é correto afirmar:',NULL,105775);
INSERT INTO `paragrafo` VALUES (105779,2,NULL,105778,105775);
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

-- Dump completed on 2026-07-04 17:41:57
