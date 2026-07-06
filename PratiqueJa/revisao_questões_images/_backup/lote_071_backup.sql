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
-- WHERE:  questao_id IN (99756,100252,101707)

LOCK TABLES `paragrafo` WRITE;
/*!40000 ALTER TABLE `paragrafo` DISABLE KEYS */;
INSERT INTO `paragrafo` VALUES (99757,0,'A tabela mostra os itens comprados por uma pessoa, a quantidade e o respectivo valor unitário.',NULL,99756);
INSERT INTO `paragrafo` VALUES (99758,1,'Essa compra foi paga com uma nota de R$ 50,00. Com o troco recebido, o número máximo de canetinhas que ainda poderiam ser compradas era',NULL,99756);
INSERT INTO `paragrafo` VALUES (99760,2,NULL,99759,99756);
INSERT INTO `paragrafo` VALUES (100253,0,'A tabela abaixo apresenta dados sobre a produção e a venda de celulares de três marcas, em um determinado mês. Sabendo-se que nesse mesmo mês as três marcas venderam 84% dos 50.000 celulares produzidos, então, o valor de Y na tabela é aproximadamente igual a:',NULL,100252);
INSERT INTO `paragrafo` VALUES (100255,1,NULL,100254,100252);
INSERT INTO `paragrafo` VALUES (101708,0,'Para calcular a média, o professor sugeriu aos alunos que cada atividade teria um valor diferenciado, de acordo com a tabela:',NULL,101707);
INSERT INTO `paragrafo` VALUES (101710,1,NULL,101709,101707);
INSERT INTO `paragrafo` VALUES (101711,2,'Helena conseguiu 8,5 na avaliação mensal, 7,0 na avaliação bimestral e 10,0 no trabalho. Qual será sua média?',NULL,101707);
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

-- Dump completed on 2026-07-04 17:31:28
