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
-- WHERE:  questao_id IN (10413,10638,10648,10925,11721)

LOCK TABLES `paragrafo` WRITE;
/*!40000 ALTER TABLE `paragrafo` DISABLE KEYS */;
INSERT INTO `paragrafo` VALUES (10414,0,'Os números de gols marcados pelos jogadores de certo time em um campeonato de futsal estão relacionados na tabela abaixo.',NULL,10413);
INSERT INTO `paragrafo` VALUES (10416,1,NULL,10415,10413);
INSERT INTO `paragrafo` VALUES (10417,2,'Em virtude de violação das regras vigentes, Jonas foi desclassificado, de modo que foi calculada a média de gols por jogador levando em conta apenas os outros 4 jogadores. Se isso não tivesse ocorrido, e a média fosse calculada levando em conta os 5 jogadores, seria obtido um valor inferior ao valor da média anterior em',NULL,10413);
INSERT INTO `paragrafo` VALUES (10639,0,'Robert realizou um concurso no qual cada matéria tem um peso de importância. A executora divulgou suas notas conforme quadro abaixo:',NULL,10638);
INSERT INTO `paragrafo` VALUES (10640,1,'Considerando que, para esse concurso, a nota final do candidato será a média ponderada, qual foi a nota obtida por Robert?',NULL,10638);
INSERT INTO `paragrafo` VALUES (10642,2,NULL,10641,10638);
INSERT INTO `paragrafo` VALUES (10649,0,'Nina trabalha como Fiscal de Estacionamento Rotativo e estava controlando os valores recebidos para depois passar para o responsável. Para facilitar, ela montou a seguinte planilha:',NULL,10648);
INSERT INTO `paragrafo` VALUES (10650,1,'Analisando os valores informados por Nina, podemos dizer que a média de valores recebidos por ela é de:',NULL,10648);
INSERT INTO `paragrafo` VALUES (10652,2,NULL,10651,10648);
INSERT INTO `paragrafo` VALUES (10926,0,'Os números de Pell são definidos pela relação de recorrência seguinte.',NULL,10925);
INSERT INTO `paragrafo` VALUES (10928,1,NULL,10927,10925);
INSERT INTO `paragrafo` VALUES (10929,2,'Assinale a alternativa que apresenta o P8.',NULL,10925);
INSERT INTO `paragrafo` VALUES (11722,0,'Diana é dona de um estacionamento e definiu os preços conforme mostrado na tabela abaixo. Considerando que, em um determinado dia, estavam estacionados 13 carros e 7 motos e que as motos permaneceram somente 1 hora e os carros 2 horas, qual o valor recebido no final do dia por Diana?',NULL,11721);
INSERT INTO `paragrafo` VALUES (11724,1,NULL,11723,11721);
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

-- Dump completed on 2026-07-02 12:40:24
