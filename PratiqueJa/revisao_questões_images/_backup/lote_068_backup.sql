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
-- WHERE:  questao_id IN (97281,97295,97309,97794,97834,97845)

LOCK TABLES `paragrafo` WRITE;
/*!40000 ALTER TABLE `paragrafo` DISABLE KEYS */;
INSERT INTO `paragrafo` VALUES (97282,0,'Leia o texto a seguir para responder à questão.',NULL,97281);
INSERT INTO `paragrafo` VALUES (97283,1,'Ana e Bernardo são irmãos que adoram participar de provas de ciclismo juntos. Em uma dessas provas, Bernardo acabou vencendo com um tempo de 1 hora e 30 minutos, cuja velocidade ao longo do trajeto está representada no gráfico abaixo, no qual os pontos  estão ligados segmentos de reta.',NULL,97281);
INSERT INTO `paragrafo` VALUES (97285,2,NULL,97284,97281);
INSERT INTO `paragrafo` VALUES (97288,3,NULL,97287,97281);
INSERT INTO `paragrafo` VALUES (97289,4,'Ana, por sua vez, percorreu o mesmo trajeto feito por ele, mas acabou completando a prova com um tempo 30% maior.',NULL,97281);
INSERT INTO `paragrafo` VALUES (97290,5,'Ana terminou a prova fazendo um tempo de x horas e y minutos em que \\(x\\) e \\(y\\) são números inteiros não negativos, com \\(0 \\leq y < 60\\). Pode-se dizer que o valor de \\(y\\) é um número compreendido entre:',NULL,97281);
INSERT INTO `paragrafo` VALUES (97296,0,'Leia o texto a seguir para responder à questão.',NULL,97295);
INSERT INTO `paragrafo` VALUES (97297,1,'Ana e Bernardo são irmãos que adoram participar de provas de ciclismo juntos. Em uma dessas provas, Bernardo acabou vencendo com um tempo de 1 hora e 30 minutos, cuja velocidade ao longo do trajeto está representada no gráfico abaixo, no qual os pontos  estão ligados segmentos de reta.',NULL,97295);
INSERT INTO `paragrafo` VALUES (97299,2,NULL,97298,97295);
INSERT INTO `paragrafo` VALUES (97302,3,NULL,97301,97295);
INSERT INTO `paragrafo` VALUES (97303,4,'Ana, por sua vez, percorreu o mesmo trajeto feito por ele, mas acabou completando a prova com um tempo 30% maior.',NULL,97295);
INSERT INTO `paragrafo` VALUES (97304,5,'Pode-se dizer que 80 minutos após iniciar a prova, a velocidade em que Bernardo se encontrava na prova, em km/h, é igual a:',NULL,97295);
INSERT INTO `paragrafo` VALUES (97310,0,'Leia o texto a seguir para responder à questão.',NULL,97309);
INSERT INTO `paragrafo` VALUES (97311,1,'Ana e Bernardo são irmãos que adoram participar de provas de ciclismo juntos. Em uma dessas provas, Bernardo acabou vencendo com um tempo de 1 hora e 30 minutos, cuja velocidade ao longo do trajeto está representada no gráfico abaixo, no qual os pontos  estão ligados segmentos de reta.',NULL,97309);
INSERT INTO `paragrafo` VALUES (97313,2,NULL,97312,97309);
INSERT INTO `paragrafo` VALUES (97316,3,NULL,97315,97309);
INSERT INTO `paragrafo` VALUES (97317,4,'Ana, por sua vez, percorreu o mesmo trajeto feito por ele, mas acabou completando a prova com um tempo 30% maior.',NULL,97309);
INSERT INTO `paragrafo` VALUES (97318,5,'Durante 90 minutos de prova, pode-se dizer que a quantidade de vezes que Bernardo atingiu a velocidade de 27 km/h é igual a:',NULL,97309);
INSERT INTO `paragrafo` VALUES (97795,0,'André registrou a distância percorrida por ele nos treinos de certa semana. Considerando-se que o quadro abaixo apresenta as distâncias percorridas por ele nesses dias, pode-se afirmar que a média das distâncias percorridas por ele é igual a:',NULL,97794);
INSERT INTO `paragrafo` VALUES (97797,1,NULL,97796,97794);
INSERT INTO `paragrafo` VALUES (97835,0,'Na figura seguinte, considerando-se que o polígono ABCD é um quadrado cujo lado mede 6 cm e que AE = CG = 1,5 cm , a medida de , em cm , é',NULL,97834);
INSERT INTO `paragrafo` VALUES (97837,1,NULL,97836,97834);
INSERT INTO `paragrafo` VALUES (97839,2,NULL,97838,97834);
INSERT INTO `paragrafo` VALUES (97846,0,'Tales de Mileto foi um matemático grego que viveu por volta de 546 a.C e é considerado um dos “sete sábios” da antiguidade. De acordo com o seu teorema, qualquer reta paralela a um dos lados de um triângulo que intercepta os outros dois lados, divide esses lados em segmentos proporcionais. A partir dessa definição, qual é a medida do segmento representado na figura seguinte, sabendo-se que //, AB=EC=6 cm, BE=2 cm e DC=7,5 cm ?',NULL,97845);
INSERT INTO `paragrafo` VALUES (97848,1,NULL,97847,97845);
INSERT INTO `paragrafo` VALUES (97850,2,NULL,97849,97845);
INSERT INTO `paragrafo` VALUES (97852,3,NULL,97851,97845);
INSERT INTO `paragrafo` VALUES (97854,4,NULL,97853,97845);
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

-- Dump completed on 2026-07-04 17:17:32
