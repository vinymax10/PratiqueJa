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
-- WHERE:  questao_id IN (29368,29493,29788,29875)

LOCK TABLES `paragrafo` WRITE;
/*!40000 ALTER TABLE `paragrafo` DISABLE KEYS */;
INSERT INTO `paragrafo` VALUES (29369,0,'O tipo sanguíneo de uma pessoa é determinado conforme a presença ou ausência de três antígenos, denominados de A, B e Rh. A tabela a seguir mostra as possibilidades dos tipos sanguíneos.',NULL,29368);
INSERT INTO `paragrafo` VALUES (29370,1,'Fonte: Elaborado pela banca, 2021.',NULL,29368);
INSERT INTO `paragrafo` VALUES (29371,2,'Suponha que em um grupo de pessoas, 19 tenham pelo menos dois desses antígenos, sendo que destas, 7 tenham os antígenos A e B, 8 pessoas tenham os antígenos A e Rh e 3 pessoas sejam do tipo sanguíneo (AB+).',NULL,29368);
INSERT INTO `paragrafo` VALUES (29372,3,'Portanto, é correto afirmar que a quantidade de pessoas nessa família com o tipo sanguíneo (B+) é igual a',NULL,29368);
INSERT INTO `paragrafo` VALUES (29374,4,NULL,29373,29368);
INSERT INTO `paragrafo` VALUES (29494,0,'Em uma empresa, os funcionários foram entrevistados para saber quantas horas, em média, passam no computador fora do horário de trabalho. A tabela a seguir representa as respostas dadas pelos funcionários.',NULL,29493);
INSERT INTO `paragrafo` VALUES (29495,1,'Durante o preenchimento da tabela, duas informações não foram acrescentadas: a quantidade de funcionários que ficam entre 2 e 3 horas no computador fora do horário de trabalho e a porcentagem de funcionários que ficam menos de 1 hora no computador para o horário de trabalho. Quando uma alternativa que apresenta essas duas informações.',NULL,29493);
INSERT INTO `paragrafo` VALUES (29497,2,NULL,29496,29493);
INSERT INTO `paragrafo` VALUES (29789,0,'Considere a expressão abaixo:',NULL,29788);
INSERT INTO `paragrafo` VALUES (29791,1,NULL,29790,29788);
INSERT INTO `paragrafo` VALUES (29792,2,'O valor de A é:',NULL,29788);
INSERT INTO `paragrafo` VALUES (29876,0,'Na operação abaixo, três dígitos foram substituídos pelas letras X, Y e Z. Sendo assim, assinalar a alternativa que apresenta os valores de X, Y e Z que satisfazem a operação abaixo:',NULL,29875);
INSERT INTO `paragrafo` VALUES (29878,1,NULL,29877,29875);
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

-- Dump completed on 2026-07-02 18:41:35
