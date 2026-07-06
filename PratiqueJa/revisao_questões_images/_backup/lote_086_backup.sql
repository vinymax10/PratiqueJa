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
-- WHERE:  questao_id IN (123260,123315)

LOCK TABLES `paragrafo` WRITE;
/*!40000 ALTER TABLE `paragrafo` DISABLE KEYS */;
INSERT INTO `paragrafo` VALUES (123261,0,'O histograma a seguir indica o Índice de Massa Corporal de um grupo de pessoas de um bairro M de uma cidade Z, sendo que F é número de pessoas e é o comprimento de cada intervalo de classe.',NULL,123260);
INSERT INTO `paragrafo` VALUES (123263,1,NULL,123262,123260);
INSERT INTO `paragrafo` VALUES (123264,2,'Índice de Massa Corporal (I.M.C.) das pessoas do bairro M da cidade Z',NULL,123260);
INSERT INTO `paragrafo` VALUES (123266,3,NULL,123265,123260);
INSERT INTO `paragrafo` VALUES (123267,4,'Com base nesse gráfico, é correto afirmar que',NULL,123260);
INSERT INTO `paragrafo` VALUES (123316,0,'O CNPq concede bolsas para a formação de recursos humanos no campo da pesquisa científica e tecnológica, em universidades, institutos de pesquisa, centros tecnológicos e de formação profissional, tanto no Brasil como no exterior. Confira alguns valores de bolsas no Brasil.',NULL,123315);
INSERT INTO `paragrafo` VALUES (123318,1,NULL,123317,123315);
INSERT INTO `paragrafo` VALUES (123319,2,'Considerando os dados acima e sabendo que dois jovens foram contemplados, cada um com uma bolsa, tal que a soma dos valores das duas bolsas está entre R$ 3.000,00 e R$ 4.000,00, então as duas bolsas concedidas foram de',NULL,123315);
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

-- Dump completed on 2026-07-04 18:42:29
