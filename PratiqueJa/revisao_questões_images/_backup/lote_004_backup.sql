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
-- WHERE:  questao_id IN (7523,7530,7537,7581,7617,7747)

LOCK TABLES `paragrafo` WRITE;
/*!40000 ALTER TABLE `paragrafo` DISABLE KEYS */;
INSERT INTO `paragrafo` VALUES (7524,0,'O seno e o cosseno hiperbólicos são definidos, respectivamente, por \\(\\operatorname{senh} x=\\dfrac{e^{x}-e^{-x}}{2}\\) e \\(\\cosh x=\\dfrac{e^{x}+e^{-x}}{2}\\), onde \\(e\\) é o número de Euler. Com base nas definições apresentadas, julgue o item.',NULL,7523);
INSERT INTO `paragrafo` VALUES (7526,1,NULL,7525,7523);
INSERT INTO `paragrafo` VALUES (7527,2,'\\(\\operatorname{senh} 2x = 2\\,\\operatorname{senh} x\\,\\cosh x\\)',NULL,7523);
INSERT INTO `paragrafo` VALUES (7531,0,'O seno e o cosseno hiperbólicos são definidos, respectivamente, por \\(\\operatorname{senh} x=\\dfrac{e^{x}-e^{-x}}{2}\\) e \\(\\cosh x=\\dfrac{e^{x}+e^{-x}}{2}\\), onde \\(e\\) é o número de Euler. Com base nas definições apresentadas, julgue o item.',NULL,7530);
INSERT INTO `paragrafo` VALUES (7533,1,NULL,7532,7530);
INSERT INTO `paragrafo` VALUES (7534,2,'\\(\\operatorname{senh}^{2} x + \\cosh^{2} x = 1\\)',NULL,7530);
INSERT INTO `paragrafo` VALUES (7538,0,'O seno e o cosseno hiperbólicos são definidos, respectivamente, por \\(\\operatorname{senh} x=\\dfrac{e^{x}-e^{-x}}{2}\\) e \\(\\cosh x=\\dfrac{e^{x}+e^{-x}}{2}\\), onde \\(e\\) é o número de Euler. Com base nas definições apresentadas, julgue o item.',NULL,7537);
INSERT INTO `paragrafo` VALUES (7540,1,NULL,7539,7537);
INSERT INTO `paragrafo` VALUES (7542,2,'\\(\\cosh x=\\dfrac{1+e^{-2x}}{2e^{-x}}\\)',7541,7537);
INSERT INTO `paragrafo` VALUES (7582,0,'A tabela a seguir mostra a distância percorrida e o combustível consumido por um automóvel em quatro trajetos diferentes. Calcule qual foi o rendimento médio total do automóvel.',NULL,7581);
INSERT INTO `paragrafo` VALUES (7584,1,NULL,7583,7581);
INSERT INTO `paragrafo` VALUES (7618,0,'Uma escola técnica resolveu adotar o sistema de cálculo de média ponderada para seus alunos. A tabela a seguir apresenta as notas obtidas por um aluno em um bimestre. Com base nas informações calcule qual será a média do aluno:',NULL,7617);
INSERT INTO `paragrafo` VALUES (7620,1,NULL,7619,7617);
INSERT INTO `paragrafo` VALUES (7748,0,'João passou em frente a uma agência de viagens e viu o anúncio apresentado a seguir. Ele recebe um salário mínimo mensal de R$ 1.100,00 e só pode dispor de 5% desse valor mensalmente para pagar a viagem, o que não é suficiente para pagar as parcelas do anúncio. Sabendo disso, quantos meses João vai ter que esperar para conseguir juntar o dinheiro e pagar a viagem à vista?',NULL,7747);
INSERT INTO `paragrafo` VALUES (7750,1,NULL,7749,7747);
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

-- Dump completed on 2026-07-02 10:37:00
