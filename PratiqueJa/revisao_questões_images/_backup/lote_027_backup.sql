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
-- WHERE:  questao_id IN (34683,34978,35186,35196)

LOCK TABLES `paragrafo` WRITE;
/*!40000 ALTER TABLE `paragrafo` DISABLE KEYS */;
INSERT INTO `paragrafo` VALUES (34684,0,'Antônio pegou um taxi de uma empresa que oferecia a promoção divulgada no cartaz a seguir.',NULL,34683);
INSERT INTO `paragrafo` VALUES (34686,1,NULL,34685,34683);
INSERT INTO `paragrafo` VALUES (34687,2,'Ao chegar ao seu destino, Antônio viu que o taxímetro marcava R$ 19,00. Ele então pediu ao motorista que desse uma volta no quarteirão e parasse no mesmo lugar. Depois disso, o taxímetro passou a marcar R$ 21,00.',NULL,34683);
INSERT INTO `paragrafo` VALUES (34688,3,'Assim, Antônio economizou',NULL,34683);
INSERT INTO `paragrafo` VALUES (34979,0,'A pandemia do vírus SARS-Cov-19 atingiu vários países ao redor do mundo, e em particular tem causado muitos mortos no Brasil. A tabela a seguir descreve a quantidade de mortos no Brasil por faixa etária até 29 de agosto de 2020.',NULL,34978);
INSERT INTO `paragrafo` VALUES (34981,1,NULL,34980,34978);
INSERT INTO `paragrafo` VALUES (34982,2,'Fonte: https://www.poder360.com.br/coronavirus/1-a-cada-4-',NULL,34978);
INSERT INTO `paragrafo` VALUES (34983,3,'mortos-por-covid-19-no-brasil-estava-fora-da-faixa-etaria-de-risco/',NULL,34978);
INSERT INTO `paragrafo` VALUES (34984,4,'O percentual do número de mortos na faixa etária superior a 60 anos em relação ao número de mortos na faixa etária inferior a 60 anos é aproximadamente:',NULL,34978);
INSERT INTO `paragrafo` VALUES (35187,0,'A tabela apresenta a distribuição dos salários fixos de 15 funcionários de uma Corretora de Imóveis, segundo a função que exercem.',NULL,35186);
INSERT INTO `paragrafo` VALUES (35188,1,'Essa Corretora irá contratar 5 assistentes com o mesmo salário. Para que o salário médio dos 20 funcionários diminua em 5% à média atual dos 15 funcionários da Corretora, o salário de cada assistente deverá ser igual a',NULL,35186);
INSERT INTO `paragrafo` VALUES (35190,2,NULL,35189,35186);
INSERT INTO `paragrafo` VALUES (35197,0,'As informações sobre a produção de um tipo de objeto de uma fábrica constam no quadro a seguir.',NULL,35196);
INSERT INTO `paragrafo` VALUES (35198,1,'Sabe-se que o número de máquinas em funcionamento no mês 2 foi apenas 90% do número utilizado no mês 1. No mês 2, produziram-se 3/4 do número de objetos produzidos no mês 1. Considerando a proporcionalidade das informações apresentadas, o número t de horas diárias de funcionamento de cada máquina no mês 1 foi igual a',NULL,35196);
INSERT INTO `paragrafo` VALUES (35200,2,NULL,35199,35196);
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

-- Dump completed on 2026-07-02 20:49:42
