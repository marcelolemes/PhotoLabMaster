-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           5.6.16 - MySQL Community Server (GPL)
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              8.3.0.4694
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Copiando estrutura do banco de dados para login
CREATE DATABASE IF NOT EXISTS `login` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `login`;


-- Copiando estrutura para tabela login.album
CREATE TABLE IF NOT EXISTS `album` (
  `cod` int(11) NOT NULL AUTO_INCREMENT,
  `numero` varchar(255) DEFAULT NULL,
  `obs` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `cont_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`cod`),
  KEY `FK_niyo2a01isyabu5myc6pqv6dx` (`cont_id`),
  CONSTRAINT `FK_niyo2a01isyabu5myc6pqv6dx` FOREIGN KEY (`cont_id`) REFERENCES `contrato` (`cod`)
) ENGINE=InnoDB AUTO_INCREMENT=271 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela login.album: ~67 rows (aproximadamente)
/*!40000 ALTER TABLE `album` DISABLE KEYS */;
/*!40000 ALTER TABLE `album` ENABLE KEYS */;


-- Copiando estrutura para tabela login.cliente
CREATE TABLE IF NOT EXISTS `cliente` (
  `cod` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `obs` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cod`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela login.cliente: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;


-- Copiando estrutura para tabela login.contrato
CREATE TABLE IF NOT EXISTS `contrato` (
  `cod` int(11) NOT NULL AUTO_INCREMENT,
  `caminho` varchar(255) DEFAULT NULL,
  `cidade` varchar(255) DEFAULT NULL,
  `curso` varchar(255) DEFAULT NULL,
  `dataBackup` datetime DEFAULT NULL,
  `dataEntrada` datetime DEFAULT NULL,
  `dataEntrega` datetime DEFAULT NULL,
  `dataPrazo` datetime DEFAULT NULL,
  `entidade` varchar(255) DEFAULT NULL,
  `ficha` varchar(255) DEFAULT NULL,
  `media` int(11) DEFAULT NULL,
  `numeroContrato` varchar(255) DEFAULT NULL,
  `obs` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `urgencia` int(11) DEFAULT NULL,
  `ficha_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`cod`),
  KEY `FK_84lgjerx13wfj15j1yvvib9oh` (`ficha_id`),
  CONSTRAINT `FK_84lgjerx13wfj15j1yvvib9oh` FOREIGN KEY (`ficha_id`) REFERENCES `ficha` (`cod`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela login.contrato: ~13 rows (aproximadamente)
/*!40000 ALTER TABLE `contrato` DISABLE KEYS */;
INSERT INTO `contrato` (`cod`, `caminho`, `cidade`, `curso`, `dataBackup`, `dataEntrada`, `dataEntrega`, `dataPrazo`, `entidade`, `ficha`, `media`, `numeroContrato`, `obs`, `status`, `urgencia`, `ficha_id`) VALUES
	(3, '', 'bauru', 'direito', NULL, '2014-09-10 00:00:00', NULL, '2014-12-10 00:00:00', 'fib', '27/2014', 90, '7790', '', 5, 3, NULL),
	(4, '', 'jundiaí', 'psicologia/letras', NULL, '2014-09-10 00:00:00', NULL, '2014-12-10 00:00:00', 'unianchieta', '25/2014', 100, '7668 - 7962', '', 5, 3, NULL),
	(5, '\\\\192.168.1.50\\Armazenamento\\FICHAS SAGAE\\Trabalhado\\FICHA 021-2014\\01 - 7679 - ODONTOLOGIA - UCB - BRASILIA', 'brasília', 'odontologia', NULL, '2014-09-15 00:00:00', NULL, '2014-10-01 00:00:00', 'ucb', '21-2014', 100, '7679', 'Caminho Correto', 16, 5, NULL),
	(7, '', 'sta rita de sapucaí', 'eng telecom/ eng elétrica/ eng comp.', NULL, '2014-09-15 00:00:00', NULL, '2014-09-28 00:00:00', 'inatel', '21-2014', 100, '7164', '', 13, 3, NULL),
	(8, '\\\\192.168.1.50\\Armazenamento\\FICHAS SAGAE\\Trabalhado\\FICHA 023-2014\\02 - 7048 - ENFERNAGEM - FACIPLAC - BRASILIA', 'brasília', 'enfermagem', NULL, '2014-09-16 00:00:00', NULL, '2014-10-08 00:00:00', 'faciplac', '23-2014', 100, '7048', 'Caminho Correto', 16, 6, NULL),
	(9, '', 'uberaba-mg', 'serviço social/história', NULL, '2014-09-16 00:00:00', NULL, '2014-10-08 00:00:00', 'uftm', '23-2014', 110, '7243', '', 11, 3, NULL),
	(10, '', 'cornélio procópio', 'economia/pedagogia', NULL, '2014-09-16 00:00:00', NULL, '2014-10-22 00:00:00', 'uenp-faficop', '23-2014', 80, '7224 - 6957', '', 8, 4, NULL),
	(11, '', 'leme/araras', 'vários cursos', NULL, '2014-09-16 00:00:00', NULL, '2014-11-10 00:00:00', 'anhanguera/uniararas', '25-2014', 120, '8015', '', 5, 3, NULL),
	(12, '\\\\192.168.1.50\\Armazenamento\\FICHAS SAGAE\\Trabalhado\\FICHA 17\\05 - 8687 - ADM - C CONT - BRASILIA', 'sorocaba', 'direito', NULL, '2014-09-16 00:00:00', NULL, '2014-11-10 00:00:00', 'uniso', '25-2014', 100, '8911', '', 5, 3, NULL),
	(13, '', 'goiania', 'vários cursos', NULL, '2014-09-16 00:00:00', NULL, '2014-11-20 00:00:00', 'estácio de sá/universo', '25-2014', 120, '9167 - 8523 - 8358', '', 5, 3, NULL),
	(14, '\\\\192.168.1.50\\Armazenamento\\FICHAS SAGAE\\Trabalhado\\FICHA 17\\05 - 8687 - ADM - C CONT - BRASILIA', 'valparaíso de goías', 'vários cursos', NULL, '2014-09-16 00:00:00', NULL, '2014-11-20 00:00:00', 'unidesc', '25-2014', 120, '7686', '', 8, 3, NULL),
	(15, '', 'bairu', 'med vet-zootecnia', NULL, '2014-09-16 00:00:00', NULL, '2014-12-10 00:00:00', 'fib', '27-2014', 90, '7247 - 7277', '', 4, 3, NULL),
	(16, '', 'goiânia', 'ciências contábeis / administração', NULL, '2014-09-17 00:00:00', NULL, '2014-11-20 00:00:00', 'delta', '25-2014', 120, '8161 - 8160', '', 3, 3, NULL);
/*!40000 ALTER TABLE `contrato` ENABLE KEYS */;


-- Copiando estrutura para tabela login.ficha
CREATE TABLE IF NOT EXISTS `ficha` (
  `cod` int(11) NOT NULL AUTO_INCREMENT,
  `ano` varchar(255) DEFAULT NULL,
  `numero` varchar(255) DEFAULT NULL,
  `cli_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`cod`),
  KEY `FK_7h6h0y86ykyu1eouucjrh7ekk` (`cli_id`),
  CONSTRAINT `FK_7h6h0y86ykyu1eouucjrh7ekk` FOREIGN KEY (`cli_id`) REFERENCES `cliente` (`cod`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela login.ficha: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `ficha` DISABLE KEYS */;
/*!40000 ALTER TABLE `ficha` ENABLE KEYS */;


-- Copiando estrutura para tabela login.usuario
CREATE TABLE IF NOT EXISTS `usuario` (
  `cod` int(11) NOT NULL AUTO_INCREMENT,
  `apelido` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  `servicoanterior` varchar(255) DEFAULT NULL,
  `servicoatual` varchar(255) DEFAULT NULL,
  `ultimoacesso` varchar(255) DEFAULT NULL,
  `nivelacesso` int(11) DEFAULT NULL,
  `logado` bit(1) DEFAULT NULL,
  `setor` int(11) DEFAULT NULL,
  PRIMARY KEY (`cod`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela login.usuario: ~7 rows (aproximadamente)
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`cod`, `apelido`, `senha`, `servicoanterior`, `servicoatual`, `ultimoacesso`, `nivelacesso`, `logado`, `setor`) VALUES
	(2, 'admin', 'admin', NULL, NULL, '2014-09-19 15:17:53.68', 5, b'1', 2),
	(3, 'sergio', 'sanches', NULL, NULL, '2014-09-12 14:25:21', 4, b'1', 2),
	(4, 'sysadmin', 'sysadmin', NULL, NULL, '2014-09-09 15:22:57', 5, b'1', 2),
	(14, 'norberto', 'norberto', NULL, NULL, '2014-09-19 14:36:21.285', 2, b'0', 5),
	(15, 'montagem', '', NULL, NULL, '2014-09-19 15:02:56.9', 3, b'0', 3),
	(17, 'cadastro', 'cadastro', NULL, NULL, '2014-09-19 15:17:17.977', 2, b'0', 0),
	(18, 'marcelo', '', NULL, NULL, '2014-09-19 15:18:02.745', 3, b'1', 3);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
