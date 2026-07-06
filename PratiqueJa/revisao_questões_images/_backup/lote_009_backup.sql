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
-- WHERE:  questao_id IN (12021,12093,12110,12644,12828,14377)

LOCK TABLES `paragrafo` WRITE;
/*!40000 ALTER TABLE `paragrafo` DISABLE KEYS */;
INSERT INTO `paragrafo` VALUES (12022,0,'Com um compasso de hastes = 10 cm, um estudante traça uma circunferência de centro C, concorrente com a reta r nos pontos P e Q, conforme o esquema abaixo. Observe que a ponta seca do compasso está indicada pelo ponto C, e a da grafite, pelo ponto B. As hastes formam o ângulo BÂC = 60º.',NULL,12021);
INSERT INTO `paragrafo` VALUES (12024,1,NULL,12023,12021);
INSERT INTO `paragrafo` VALUES (12026,2,NULL,12025,12021);
INSERT INTO `paragrafo` VALUES (12027,3,'Se a distância do ponto C à reta r mede 5 cm, o comprimento do segmento PQ mede x cm.',NULL,12021);
INSERT INTO `paragrafo` VALUES (12028,4,'O valor de x está no seguinte intervalo:',NULL,12021);
INSERT INTO `paragrafo` VALUES (12094,0,'Ao longo de um turno de trabalho de n horas, 105 requerimentos de matrícula de estudantes foram encaminhados para dois técnicos-administrativos. Observe na tabela de que modo parte dos requerimentos foi distribuída, entre os dois, nas três primeiras horas desse turno.',NULL,12093);
INSERT INTO `paragrafo` VALUES (12096,1,NULL,12095,12093);
INSERT INTO `paragrafo` VALUES (12097,2,'Mantendo o padrão da tabela, a quantidade de requerimentos recebidos por técnico aumenta sempre duas unidades a cada hora consecutiva.',NULL,12093);
INSERT INTO `paragrafo` VALUES (12098,3,'Assim, o valor de n é igual a:',NULL,12093);
INSERT INTO `paragrafo` VALUES (12111,0,'As quantidades de processos armazenados em oito arquivos foram escritas em ordem crescente, sendo que duas delas, representadas por x e y, se apagaram. Observe:',NULL,12110);
INSERT INTO `paragrafo` VALUES (12113,1,NULL,12112,12110);
INSERT INTO `paragrafo` VALUES (12114,2,'Se a mediana e a média aritmética desses oito números valem, respectivamente, 22 e 28, o valor de y - x é igual a:',NULL,12110);
INSERT INTO `paragrafo` VALUES (1,3,' I) \\(y = a^x\\) com \\(0 < a < 1\\); II) \\(y = a^x\\) com \\(a > 1\\); III) \\(y = \\log_a x\\) com \\(0 < a < 1\\); IV) \\(y = \\log_a x\\) com \\(a > 1\\).',NULL,12644);
INSERT INTO `paragrafo` VALUES (2,4,NULL,1,12644);
INSERT INTO `paragrafo` VALUES (12645,0,'Compare as leis de formação do 1º quadro com os gráficos das funções y = f(x) esboçadas no segundo quadro.',NULL,12644);
INSERT INTO `paragrafo` VALUES (12646,1,'A associação correta é',NULL,12644);
INSERT INTO `paragrafo` VALUES (164848,2,'Leis de formação:',NULL,12644);
INSERT INTO `paragrafo` VALUES (12829,0,'A distribuição por sexo e altura de um grupo de jogadores de basquete é dada pela tabela a seguir.',NULL,12828);
INSERT INTO `paragrafo` VALUES (12831,1,NULL,12830,12828);
INSERT INTO `paragrafo` VALUES (12832,2,'Sorteado um jogador e sabendo-se que ele mede menos que 1,80 m, a probabilidade de que ele seja homem é de:',NULL,12828);
INSERT INTO `paragrafo` VALUES (14378,0,'Analise a progressão aritmética (PA) a seguir.',NULL,14377);
INSERT INTO `paragrafo` VALUES (14380,1,NULL,14379,14377);
INSERT INTO `paragrafo` VALUES (14381,2,'É correto afirmar que o trigésimo termo da PA acima é igual a:',NULL,14377);
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

-- Dump completed on 2026-07-02 13:05:36
