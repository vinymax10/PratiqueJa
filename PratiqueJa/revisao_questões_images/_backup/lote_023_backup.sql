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
-- WHERE:  questao_id IN (30854,30878,30894,30904)

LOCK TABLES `paragrafo` WRITE;
/*!40000 ALTER TABLE `paragrafo` DISABLE KEYS */;
INSERT INTO `paragrafo` VALUES (30855,0,'Após examinar uma caixa contendo 80 peças, um engenheiro montou a tabela a seguir:',NULL,30854);
INSERT INTO `paragrafo` VALUES (30856,1,'Escolhendo-se ao acaso uma dessas 80 peças, a probabilidade de que a peça escolhida seja do tipo A ou que não tenha defeito é de:',NULL,30854);
INSERT INTO `paragrafo` VALUES (30858,2,NULL,30857,30854);
INSERT INTO `paragrafo` VALUES (30879,0,'A tabela a seguir mostra o comprimento, em metros, de seis cordas quando completamente esticadas:',NULL,30878);
INSERT INTO `paragrafo` VALUES (30880,1,'Comparando a mediana com a média desses seis comprimentos, é possível concluir que a mediana supera a média em x centímetros. O valor de x é:',NULL,30878);
INSERT INTO `paragrafo` VALUES (30882,2,NULL,30881,30878);
INSERT INTO `paragrafo` VALUES (30895,0,'A tabela apresenta a distribuição do número total de aprovados em concursos, realizados de 2015 a 2019, em certo município, nas categorias idade de 18 a 30 anos e idade maior que 30 anos.',NULL,30894);
INSERT INTO `paragrafo` VALUES (30896,1,'Com base apenas nas informações apresentadas, assinale a alternativa que contém uma afirmação necessariamente verdadeira.',NULL,30894);
INSERT INTO `paragrafo` VALUES (30898,2,NULL,30897,30894);
INSERT INTO `paragrafo` VALUES (30905,0,'A tabela apresenta o número de pessoas que visitaram  certa biblioteca, em alguns dias da semana anterior:',NULL,30904);
INSERT INTO `paragrafo` VALUES (30906,1,'Sabendo-se que o número de visitantes na sexta-feira, comparado ao número de visitantes na segunda-feira, foi maior em 10%, e que, no período em questão, o número médio de visitantes foi de 470 pessoas, é correto afirmar que, na sexta-feira, o número de visitantes superou o número de visitantes da segunda-feira em',NULL,30904);
INSERT INTO `paragrafo` VALUES (30908,2,NULL,30907,30904);
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

-- Dump completed on 2026-07-02 19:07:53
