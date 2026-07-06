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
-- WHERE:  questao_id IN (16082,16603)

LOCK TABLES `paragrafo` WRITE;
/*!40000 ALTER TABLE `paragrafo` DISABLE KEYS */;
INSERT INTO `paragrafo` VALUES (16083,0,'A tabela abaixo, fornecida pelo Instituto Nacional de Pesquisas Espaciais, apresenta área queimada de cada um dos principais biomas brasileiros, durante os meses de 2019.',NULL,16082);
INSERT INTO `paragrafo` VALUES (16084,1,'Fonte: http://queimadas.dgi.inpe.br/queimadas/aq1km/',NULL,16082);
INSERT INTO `paragrafo` VALUES (16085,2,'Com base nas informações apresentadas na tabela, conclui-se, corretamente, o seguinte:',NULL,16082);
INSERT INTO `paragrafo` VALUES (16087,3,NULL,16086,16082);
INSERT INTO `paragrafo` VALUES (16604,0,'Uma das despesas de uma casa que atualmente deve ser controlada por todos os integrantes da família é o uso da água. O uso consciente desse precioso bem, reduz os valores pagos mensalmente e ainda conserva água do Planeta. A seguir está sendo demostrado um quadro da conta de água de uma residência. Nela está identificada faixas mínimas e diferentes de tarifação de consumo e o valor total pago num determinado mês.',NULL,16603);
INSERT INTO `paragrafo` VALUES (16605,1,'Devido a um vazamento, no mês seguinte dobrou o consumo de água dessa residência. O novo valor da conta será:',NULL,16603);
INSERT INTO `paragrafo` VALUES (16607,2,NULL,16606,16603);
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

-- Dump completed on 2026-07-02 14:05:44
