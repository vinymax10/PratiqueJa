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
-- WHERE:  questao_id IN (8033,8128,8147,8271,8305)

LOCK TABLES `paragrafo` WRITE;
/*!40000 ALTER TABLE `paragrafo` DISABLE KEYS */;
INSERT INTO `paragrafo` VALUES (8034,0,'Ramanujan, um grande matemático indiano, apresentou uma fórmula para se calcular, aproximadamente, o perímetro \\(p\\) de uma elipse de semieixo maior \\(a\\) e semieixo menor \\(b\\): \\(p\\cong\\pi\\left[3(a+b)-\\sqrt{(3a+b)(a+3b)}\\right]\\). Se os comprimentos dos semieixos de uma elipse são as raízes da equação \\(x^{2}-17x+60=0\\), então, de acordo com a fórmula de Ramanujan, seu perímetro é de, aproximadamente,',NULL,8033);
INSERT INTO `paragrafo` VALUES (8036,1,NULL,8035,8033);
INSERT INTO `paragrafo` VALUES (8129,0,'Paulo quer comprar um determinado livro pela internet e encontrou as seguintes ofertas:',NULL,8128);
INSERT INTO `paragrafo` VALUES (8131,1,NULL,8130,8128);
INSERT INTO `paragrafo` VALUES (8132,2,'Sabe-se que o peso do livro é 280 g. Se Paulo optar pela compra do livro na loja I, ele pagará, em relação à loja II,',NULL,8128);
INSERT INTO `paragrafo` VALUES (8148,0,'Em uma aula de revisão sobre unidades de medida das grandezas tempo, capacidade e área, a professora Cláudia apresentou à classe as três afirmações do quadro a seguir para que os alunos respondessem se era verdadeira ou falsa cada uma delas.',NULL,8147);
INSERT INTO `paragrafo` VALUES (8149,1,'Os alunos que responderam corretamente escreveram que as afirmações I, II e III são, respectivamente,',NULL,8147);
INSERT INTO `paragrafo` VALUES (8151,2,NULL,8150,8147);
INSERT INTO `paragrafo` VALUES (8272,0,'Dois lotes, I e II, ambos quadrados e de mesmas medidas, encontram-se um ao lado do outro e foram conectados por um portão  formando assim um novo lote ABCDEFGH conforme mostra a figura.',NULL,8271);
INSERT INTO `paragrafo` VALUES (8273,1,'Sabendo-se que CD = GH = 6 m e que o perímetro do novo lote é 60 m, então a largura do portão  é igual a',NULL,8271);
INSERT INTO `paragrafo` VALUES (8275,2,NULL,8274,8271);
INSERT INTO `paragrafo` VALUES (8277,3,NULL,8276,8271);
INSERT INTO `paragrafo` VALUES (8279,4,NULL,8278,8271);
INSERT INTO `paragrafo` VALUES (8306,0,'A tabela apresenta algumas informações sobre a massa de cinco caixas de mercadorias:',NULL,8305);
INSERT INTO `paragrafo` VALUES (8308,1,NULL,8307,8305);
INSERT INTO `paragrafo` VALUES (8309,2,'Se a soma das massas dessas cinco caixas é 14,5 kg, então a massa da caixa 4 é igual a',NULL,8305);
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

-- Dump completed on 2026-07-02 10:59:02
