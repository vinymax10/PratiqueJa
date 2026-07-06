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
-- WHERE:  questao_id IN (73594,73633,74384,75091)

LOCK TABLES `paragrafo` WRITE;
/*!40000 ALTER TABLE `paragrafo` DISABLE KEYS */;
INSERT INTO `paragrafo` VALUES (73595,0,'Segue a tabela nutricional de um suco de laranja integral sem adição de açúcares.',NULL,73594);
INSERT INTO `paragrafo` VALUES (73597,1,NULL,73596,73594);
INSERT INTO `paragrafo` VALUES (73598,2,'Uma pessoa, ao ingerir 1,5 litros desse suco, haverá ingerido',NULL,73594);
INSERT INTO `paragrafo` VALUES (73634,0,'Ontem, o saldo da conta bancária de Jorge era R$ 3.525,00. Veja no quadro abaixo a movimentação de hoje na conta dele:',NULL,73633);
INSERT INTO `paragrafo` VALUES (73635,1,'Após todas essas movimentações, o saldo bancário dele hoje é igual a',NULL,73633);
INSERT INTO `paragrafo` VALUES (73637,2,NULL,73636,73633);
INSERT INTO `paragrafo` VALUES (74385,0,'As pessoas que frequentam o restaurante de Danilo responderam a uma pesquisa referente ao consumo de suas verduras favoritas, conforme apresentado na tabela a seguir.',NULL,74384);
INSERT INTO `paragrafo` VALUES (74386,1,'Com base nesta pesquisa, a quantidade total de pessoas participantes foi:',NULL,74384);
INSERT INTO `paragrafo` VALUES (74388,2,NULL,74387,74384);
INSERT INTO `paragrafo` VALUES (75092,0,'Em certo município foi feita uma pesquisa para determinar, em cada residência, quantas crianças havia até 10 anos de idade.',NULL,75091);
INSERT INTO `paragrafo` VALUES (75093,1,'O resultado está na tabela a seguir:',NULL,75091);
INSERT INTO `paragrafo` VALUES (75094,2,'Em relação ao total de residências pesquisadas, as que possuem somente uma ou duas crianças representam:',NULL,75091);
INSERT INTO `paragrafo` VALUES (75096,3,NULL,75095,75091);
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

-- Dump completed on 2026-07-03 16:34:32
