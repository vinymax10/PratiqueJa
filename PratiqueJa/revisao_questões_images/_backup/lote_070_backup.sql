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
-- WHERE:  questao_id IN (99464,99556,99608,99663)

LOCK TABLES `paragrafo` WRITE;
/*!40000 ALTER TABLE `paragrafo` DISABLE KEYS */;
INSERT INTO `paragrafo` VALUES (99465,0,'A tabela abaixo indica o total de dias que um servente trabalhou em cada um dos três últimos meses de certo ano.',NULL,99464);
INSERT INTO `paragrafo` VALUES (99467,1,NULL,99466,99464);
INSERT INTO `paragrafo` VALUES (99468,2,'Quanto ao número de dias trabalhados pelo servente nos três últimos meses, assinale a alternativa correta.',NULL,99464);
INSERT INTO `paragrafo` VALUES (99557,0,'As notas no boletim de um aluno durante o ano foram:',NULL,99556);
INSERT INTO `paragrafo` VALUES (99559,1,NULL,99558,99556);
INSERT INTO `paragrafo` VALUES (99560,2,'Ao somarem as notas de cada disciplina nos respectivos bimestres, o maior valor encontrado foi',NULL,99556);
INSERT INTO `paragrafo` VALUES (99609,0,'O quadro a seguir indica o valor de R$ 136,00 a ser pago  pelo consumo de 28 m³ de água de uma residência, segundo os preços praticados por certa companhia distribuidora. O instrumento que mede o consumo indica apenas números inteiros. Nesse quadro constam, além do valor total a pagar, diferentes faixas de tarifação, incluindo a tarifa mínima de R$ 30,00.',NULL,99608);
INSERT INTO `paragrafo` VALUES (99610,1,'Uma outra residência cujo consumo de água é o dobro do indicado no quadro deverá pagar o valor de',NULL,99608);
INSERT INTO `paragrafo` VALUES (99612,2,NULL,99611,99608);
INSERT INTO `paragrafo` VALUES (99664,0,'A tabela a seguir apresenta as notas de 20 alunos em uma prova.',NULL,99663);
INSERT INTO `paragrafo` VALUES (99665,1,'Sabe-se que a média das notas de todos esses alunos é igual a 7,5. A nota X é igual a',NULL,99663);
INSERT INTO `paragrafo` VALUES (99667,2,NULL,99666,99663);
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

-- Dump completed on 2026-07-04 17:27:42
