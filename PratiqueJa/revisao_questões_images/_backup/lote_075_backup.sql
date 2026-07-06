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
-- WHERE:  questao_id IN (107019,107761,108333)

LOCK TABLES `paragrafo` WRITE;
/*!40000 ALTER TABLE `paragrafo` DISABLE KEYS */;
INSERT INTO `paragrafo` VALUES (107020,0,'Uma empresa foi inaugurada em janeiro de 2019 conseguindo R$ 8 mil de vendas no mês. Um dos sócios definiu como meta que as vendas crescessem 1% em relação ao mês anterior. Baseada nesta meta e caso a mesma se concretize, qual será o total de vendas do mês de abril de 2019?',NULL,107019);
INSERT INTO `paragrafo` VALUES (107022,1,NULL,107021,107019);
INSERT INTO `paragrafo` VALUES (107762,0,'Um possível valor de \\(k\\) para que a reta \\(t: x - y + k = 0\\) seja tangente à circunferência: \\(x^2 + y^2 - 4x + 2 = 0\\) é',NULL,107761);
INSERT INTO `paragrafo` VALUES (107764,1,NULL,107763,107761);
INSERT INTO `paragrafo` VALUES (108334,0,'Dados os pontos A(-2, 1), B(0 , 2), C(3, -1) e D(2, 1), o ângulo θ formado pelos vetores é:',NULL,108333);
INSERT INTO `paragrafo` VALUES (108336,1,NULL,108335,108333);
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

-- Dump completed on 2026-07-04 17:45:06
