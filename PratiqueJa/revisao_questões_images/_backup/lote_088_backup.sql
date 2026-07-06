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
-- WHERE:  questao_id IN (125633,125653,125718)

LOCK TABLES `paragrafo` WRITE;
/*!40000 ALTER TABLE `paragrafo` DISABLE KEYS */;
INSERT INTO `paragrafo` VALUES (125634,0,'A tabela indica nome, valor e numeração das 80 peças de um jogo:',NULL,125633);
INSERT INTO `paragrafo` VALUES (125636,1,NULL,125635,125633);
INSERT INTO `paragrafo` VALUES (125637,2,'Joana separou todas as peças do jogo cuja numeração é um múltiplo comum de 2 e 3. A soma dos valores das peças separadas por Joana é igual a',NULL,125633);
INSERT INTO `paragrafo` VALUES (125654,0,'Na segunda-feira, Tiago deixou seu carro em um estacionamento por 6 horas. Ao efetuar o pagamento, recebeu o seguinte recibo de cobrança:',NULL,125653);
INSERT INTO `paragrafo` VALUES (125655,1,'Na terça-feira, Tiago deixou novamente seu carro nesse estacionamento, só que agora por 5 horas. Seguindo o mesmo padrão de cobrança indicado no recibo da segunda-feira, Tiago teve que pagar ao Estacionamento do Parque, na terça-feira, a quantia de',NULL,125653);
INSERT INTO `paragrafo` VALUES (125657,2,NULL,125656,125653);
INSERT INTO `paragrafo` VALUES (125719,0,'Utilize as informações e as figuras seguintes, para responder à questão .',NULL,125718);
INSERT INTO `paragrafo` VALUES (125721,1,'A figura 1 indica um heptágono AEFGHCD construído a partir de: um retângulo ABCD, um quadrado BEFG e um triângulo equilátero GHC.',NULL,125718);
INSERT INTO `paragrafo` VALUES (125725,2,NULL,125724,125718);
INSERT INTO `paragrafo` VALUES (125727,3,'A figura 2 indica um prisma reto cuja base é o heptágono AEFGHCD da figura 1.',NULL,125718);
INSERT INTO `paragrafo` VALUES (125731,4,NULL,125730,125718);
INSERT INTO `paragrafo` VALUES (125732,5,'Na figura 1, se o perímetro do heptágono AEFGHCD é igual a 41,5 cm, então a medida de , em cm, é igual a',NULL,125718);
INSERT INTO `paragrafo` VALUES (125734,6,NULL,125733,125718);
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

-- Dump completed on 2026-07-04 18:52:08
