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
-- WHERE:  questao_id IN (117401,118284,118319)

LOCK TABLES `paragrafo` WRITE;
/*!40000 ALTER TABLE `paragrafo` DISABLE KEYS */;
INSERT INTO `paragrafo` VALUES (117402,0,'O boletim escolar de Otavio foi molhado no percurso para casa e sua nota na disciplina de História ficou manchada. Como ele havia anotado suas notas durante o bimestre, ele decidiu calcular sua média aritmética ponderada baseado em suas notas. Ao finalizar as contas, ele verificou que sua nota bimestral foi:',NULL,117401);
INSERT INTO `paragrafo` VALUES (117404,1,NULL,117403,117401);
INSERT INTO `paragrafo` VALUES (118285,0,'O dono de uma loja de bijuterias comprou várias peças e os preços e quantidades estão indicados na tabela a seguir:',NULL,118284);
INSERT INTO `paragrafo` VALUES (118287,1,NULL,118286,118284);
INSERT INTO `paragrafo` VALUES (118288,2,'A média dos preços pagos pelas peças foi de:',NULL,118284);
INSERT INTO `paragrafo` VALUES (118320,0,'Uma indústria de refrigeração fabrica geladeiras Tipo X e Tipo Y nas versões: Branca, Colorida e Aço-Inox. Peças A, B e C são utilizadas na montagem dessas geladeiras. Na seção de montagem, são utilizadas as tabelas seguintes:',NULL,118319);
INSERT INTO `paragrafo` VALUES (118321,1,'Calcule a matriz Peça-Versão e assinale a alternativa que apresenta quantas peças do Tipo B são utilizadas na montagem da geladeira Aço-Inox.',NULL,118319);
INSERT INTO `paragrafo` VALUES (118323,2,NULL,118322,118319);
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

-- Dump completed on 2026-07-04 18:08:56
