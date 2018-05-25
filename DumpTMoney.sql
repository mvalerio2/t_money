-- MySQL dump 10.13  Distrib 5.7.22, for Linux (x86_64)
--
-- Host: localhost    Database: t_money
-- ------------------------------------------------------
-- Server version	5.7.22-0ubuntu0.16.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `carteira`
--

DROP TABLE IF EXISTS `carteira`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `carteira` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `saldo` decimal(10,0) NOT NULL,
  `usuario_id` int(11) NOT NULL,
  `ativo` tinyint(1) NOT NULL,
  `criptomoeda_id` int(11) NOT NULL,
  `quantidade_criptomoeda` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_carteira_1_idx` (`usuario_id`),
  KEY `fk_carteira_2_idx` (`criptomoeda_id`),
  CONSTRAINT `FK9e7pmhyhhq6gccic4d9j5tdqd` FOREIGN KEY (`criptomoeda_id`) REFERENCES `criptomoeda` (`id`),
  CONSTRAINT `FKeh7bvct3tsjwmrp0qf0h2vsla` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  CONSTRAINT `fk_carteira_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_carteira_2` FOREIGN KEY (`criptomoeda_id`) REFERENCES `criptomoeda` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `configuracao`
--

DROP TABLE IF EXISTS `configuracao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `configuracao` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `valor_inicial` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `criptomoeda`
--

DROP TABLE IF EXISTS `criptomoeda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `criptomoeda` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `sigla` varchar(3) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `historico_cotacao`
--

DROP TABLE IF EXISTS `historico_cotacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `historico_cotacao` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `valor` decimal(10,0) NOT NULL,
  `data_hora` datetime NOT NULL,
  `criptomoeda_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_historico_cotacao_1_idx` (`criptomoeda_id`),
  CONSTRAINT `FKmd60q9oacaawco5vjjea81uul` FOREIGN KEY (`criptomoeda_id`) REFERENCES `criptomoeda` (`id`),
  CONSTRAINT `fk_historico_cotacao_1` FOREIGN KEY (`criptomoeda_id`) REFERENCES `criptomoeda` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `historico_transacao`
--

DROP TABLE IF EXISTS `historico_transacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `historico_transacao` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `valor_anterior_carteira` decimal(10,0) NOT NULL,
  `valor_atual_carteira` decimal(10,0) NOT NULL,
  `data_hora` datetime NOT NULL,
  `tipo_transacao_id` int(11) NOT NULL,
  `quantidade_anterior_criptomoeda` decimal(10,0) NOT NULL,
  `quantidade_atual_criptomoeda` decimal(10,0) NOT NULL,
  `carteira_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK868m17bll5l9rhrvnahq9huy9` (`tipo_transacao_id`),
  KEY `FK78f4yypaabcswef1al0gq1p1t` (`carteira_id`),
  CONSTRAINT `FK78f4yypaabcswef1al0gq1p1t` FOREIGN KEY (`carteira_id`) REFERENCES `carteira` (`id`),
  CONSTRAINT `FK868m17bll5l9rhrvnahq9huy9` FOREIGN KEY (`tipo_transacao_id`) REFERENCES `tipo_transacao` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome_role` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `solicitacao_saldo`
--

DROP TABLE IF EXISTS `solicitacao_saldo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `solicitacao_saldo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `carteira_id` int(11) NOT NULL,
  `valor_atual_carteira` decimal(10,0) NOT NULL,
  `valor_solicitado_carteira` decimal(10,0) NOT NULL,
  `ativo` tinyint(1) NOT NULL,
  `data_hora` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_solicitacao_saldo_1_idx` (`carteira_id`),
  CONSTRAINT `FKm7pe3syhuuurfws6e0o61pq87` FOREIGN KEY (`carteira_id`) REFERENCES `carteira` (`id`),
  CONSTRAINT `fk_solicitacao_saldo_1` FOREIGN KEY (`carteira_id`) REFERENCES `carteira` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tipo_transacao`
--

DROP TABLE IF EXISTS `tipo_transacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_transacao` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tipo` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `email` varchar(100) NOT NULL,
  `ativo` tinyint(1) NOT NULL,
  `senha` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `usuario_role`
--

DROP TABLE IF EXISTS `usuario_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario_role` (
  `usuario_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`usuario_id`,`role_id`),
  KEY `fk_usuario_role_1_idx` (`role_id`),
  CONSTRAINT `FKe7gfguqsiox6p89xggm8g2twf` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FKpc2qjts6sqq4hja9f6i3hf0ep` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  CONSTRAINT `fk_usuario_role_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuario_role_2` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-25 10:49:21
