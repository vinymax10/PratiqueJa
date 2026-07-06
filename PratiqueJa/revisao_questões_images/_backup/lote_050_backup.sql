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
-- WHERE:  questao_id IN (78242,78309,78711)

LOCK TABLES `paragrafo` WRITE;
/*!40000 ALTER TABLE `paragrafo` DISABLE KEYS */;
INSERT INTO `paragrafo` VALUES (78243,0,'O quadro a seguir representa a rentabilidade mensal da caderneta de poupança em uma respectiva data. As alíquotas informadas são para poupança antiga (para valores depositados na poupança até 03.05.2012) e para poupança nova (para valores depositados na poupança a partir de 04.05.2012). Supondo que um cidadão tenha no dia 25/12/19 R$ 25.000,00 que serão corrigidos pela poupança antiga e outros R$ 25.000,00 que serão corrigidos pela poupança nova, marque a alternativa CORRETA:',NULL,78242);
INSERT INTO `paragrafo` VALUES (78245,1,NULL,78244,78242);
INSERT INTO `paragrafo` VALUES (78310,0,'O gráfico abaixo representa uma função do primeiro grau na forma \\(f(x) = ax + b\\), onde \\(a\\) e \\(b\\).',NULL,78309);
INSERT INTO `paragrafo` VALUES (78311,1,'Analisando esse gráfico, os coeficientes a e b são, respectivamente:',NULL,78309);
INSERT INTO `paragrafo` VALUES (78313,2,NULL,78312,78309);
INSERT INTO `paragrafo` VALUES (78315,3,NULL,78314,78309);
INSERT INTO `paragrafo` VALUES (78712,0,'Encontre o valor aproximado do seno do ângulo do triângulo abaixo.',NULL,78711);
INSERT INTO `paragrafo` VALUES (78714,1,NULL,78713,78711);
INSERT INTO `paragrafo` VALUES (78716,2,NULL,78715,78711);
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

-- Dump completed on 2026-07-03 17:41:38
