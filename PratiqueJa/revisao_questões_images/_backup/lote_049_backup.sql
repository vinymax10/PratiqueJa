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
-- WHERE:  questao_id IN (75854,75896,76099,76216)

LOCK TABLES `paragrafo` WRITE;
/*!40000 ALTER TABLE `paragrafo` DISABLE KEYS */;
INSERT INTO `paragrafo` VALUES (75855,0,'A tabela mostra a distribuição das idades, em anos completos, dos atletas de um grupo de treinamento.',NULL,75854);
INSERT INTO `paragrafo` VALUES (75856,1,'Para que a média aritmética simples das idades desses 12 atletas seja 19 anos completos, os atletas mais novos deverão ter',NULL,75854);
INSERT INTO `paragrafo` VALUES (75858,2,NULL,75857,75854);
INSERT INTO `paragrafo` VALUES (75897,0,'Na rodoviária de uma cidade partem 3 linhas de ônibus. A tabela mostra a frequência de partida de cada linha.',NULL,75896);
INSERT INTO `paragrafo` VALUES (75898,1,'As linhas A, B e C iniciam suas atividades todos os dias às 5 horas. Das 5 horas até as 11 horas, inclusive, o número de vezes em que, pelo menos, duas dessas linhas partiram ao mesmo tempo é',NULL,75896);
INSERT INTO `paragrafo` VALUES (75900,2,NULL,75899,75896);
INSERT INTO `paragrafo` VALUES (76100,0,'Considere a tabela a seguir:',NULL,76099);
INSERT INTO `paragrafo` VALUES (76102,1,NULL,76101,76099);
INSERT INTO `paragrafo` VALUES (76103,2,'Assinale a alternativa abaixo que corresponde às lacunas I, II e III respectivamente.',NULL,76099);
INSERT INTO `paragrafo` VALUES (76217,0,'A tabela a seguir apresenta os 40 alunos de uma turma, subdivididos por sexo e idade:',NULL,76216);
INSERT INTO `paragrafo` VALUES (76219,1,NULL,76218,76216);
INSERT INTO `paragrafo` VALUES (76220,2,'Um professor realizou um sorteio entre os alunos dessa turma para representá-la num evento e, antes de anunciar o resultado, informou que o sorteado havia sido um dos rapazes da turma. A probabilidade de que esse rapaz tenha menos de 18 anos é de:',NULL,76216);
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

-- Dump completed on 2026-07-03 17:08:09
