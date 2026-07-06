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
-- WHERE:  questao_id IN (23096,23364,23427,24066)

LOCK TABLES `paragrafo` WRITE;
/*!40000 ALTER TABLE `paragrafo` DISABLE KEYS */;
INSERT INTO `paragrafo` VALUES (23097,0,'Um arquiteto desenhou um retângulo ABCD no centro de um jardim. Ele colocou uma árvore frutífera em cada vértice desse retângulo e a cada 1 m, nos lados do retângulo, ele colocou uma planta ornamental, conforme figura a seguir.',NULL,23096);
INSERT INTO `paragrafo` VALUES (23098,1,'No ponto E, cruzamento das diagonais e , ele colocou uma fonte de água. A distância, em metros, entre a fonte e qualquer uma das árvores frutíferas é de',NULL,23096);
INSERT INTO `paragrafo` VALUES (23100,2,NULL,23099,23096);
INSERT INTO `paragrafo` VALUES (23102,3,NULL,23101,23096);
INSERT INTO `paragrafo` VALUES (23104,4,NULL,23103,23096);
INSERT INTO `paragrafo` VALUES (23365,0,'Em uma clínica veterinária foi atendido, no decorrer de uma semana, o total de 150 animais, entre cães, gatos e aves. A tabela mostra algumas informações sobre o número de atendimentos, de internações e a porcentagem dessas internações em relação ao seu respectivo grupo.',NULL,23364);
INSERT INTO `paragrafo` VALUES (23366,1,'Em relação ao número total de animais atendidos nessa semana, o número total de internações corresponde a',NULL,23364);
INSERT INTO `paragrafo` VALUES (23368,2,NULL,23367,23364);
INSERT INTO `paragrafo` VALUES (23428,0,'Qual é o valor da solução (S) da expressão abaixo?',NULL,23427);
INSERT INTO `paragrafo` VALUES (23430,1,NULL,23429,23427);
INSERT INTO `paragrafo` VALUES (24067,0,'Uma criança pegou um cofrinho, inicialmente vazio, e começou a colocar moedas dentro dele, mas também retirou diariamente algumas moedas para comprar chicletes. A tabela mostra os valores depositados e retirados diariamente por essa criança.',NULL,24066);
INSERT INTO `paragrafo` VALUES (24068,1,'Com o valor total restante no cofrinho, no final do 5.º dia essa criança poderá comprar 13 balas iguais. O valor de uma bala é:',NULL,24066);
INSERT INTO `paragrafo` VALUES (24070,2,NULL,24069,24066);
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

-- Dump completed on 2026-07-02 16:35:44
