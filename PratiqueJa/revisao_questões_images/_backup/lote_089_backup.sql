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
-- WHERE:  questao_id IN (126449,126466,126605,126701)

LOCK TABLES `paragrafo` WRITE;
/*!40000 ALTER TABLE `paragrafo` DISABLE KEYS */;
INSERT INTO `paragrafo` VALUES (126450,0,'A agência de turismo “Viaje Bem” está promovendo excursões para alguns destinos turísticos de Minas Gerais. A figura a seguir mostra uma dessas promoções com destino à Serra do Cipó, Capitólio e Cachoeira do Tabuleiro.',NULL,126449);
INSERT INTO `paragrafo` VALUES (126451,1,'Sabendo que esse roteiro tem duração de sete dias, qual será o valor gasto por uma pessoa que deseja participar dessa excursão?',NULL,126449);
INSERT INTO `paragrafo` VALUES (126453,2,NULL,126452,126449);
INSERT INTO `paragrafo` VALUES (126467,0,'Após uma consulta, o médico de Cristina lhe receitou um remédio. A figura a seguir mostra a embalagem desse medicamento.',NULL,126466);
INSERT INTO `paragrafo` VALUES (126469,1,NULL,126468,126466);
INSERT INTO `paragrafo` VALUES (126470,2,'O médico pediu para Cristina tomar três comprimidos por dia, durante sete dias.',NULL,126466);
INSERT INTO `paragrafo` VALUES (126471,3,'Sabendo-se que ela tomou o remédio seguindo corretamente a prescrição médica, quantas comprimidos sobraram ao final do tratamento?',NULL,126466);
INSERT INTO `paragrafo` VALUES (126606,0,'Em certo dia foram atendidos em um ambulatório 80 pessoas. A tabela mostra algumas informações sobre o número de pessoas atendidas nos períodos da manhã e da tarde.',NULL,126605);
INSERT INTO `paragrafo` VALUES (126608,1,NULL,126607,126605);
INSERT INTO `paragrafo` VALUES (126609,2,'O número total de pessoas atendidas no período da manhã superou o número total de pessoas atendidas no período da tarde em',NULL,126605);
INSERT INTO `paragrafo` VALUES (126702,0,'Uma região retangular ABCD tem o menor lado igual a do maior lado. Essa região foi dividida em duas regiões retangulares, Q1 e Q2 , de maneira que a área da região Q1 é o triplo da área da região Q2 .',NULL,126701);
INSERT INTO `paragrafo` VALUES (126704,1,NULL,126703,126701);
INSERT INTO `paragrafo` VALUES (126706,2,NULL,126705,126701);
INSERT INTO `paragrafo` VALUES (126707,3,'Se a área da região ABCD é de 54 m2 , o perímetro da região Q2 , em m, é de',NULL,126701);
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

-- Dump completed on 2026-07-05  9:46:50
