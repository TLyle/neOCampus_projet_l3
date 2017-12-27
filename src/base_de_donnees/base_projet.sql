-- Adminer 4.3.1 MySQL dump

SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

DROP TABLE IF EXISTS `groupe`;
CREATE TABLE `groupe` (
  `Nom` varchar(10) NOT NULL,
  `Users` set('100') DEFAULT NULL,
  `Tickets` int(11) DEFAULT NULL,
  PRIMARY KEY (`Nom`),
  KEY `fk_ticket` (`Tickets`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `IdMessage` int(11) NOT NULL,
  `Ticket` int(11) NOT NULL,
  `Etat` varchar(10) NOT NULL,
  `Texte` varchar(500) NOT NULL,
  PRIMARY KEY (`IdMessage`),
  KEY `fk_Ticket` (`Ticket`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `ticket`;
CREATE TABLE `ticket` (
  `IdTicket` int(11) NOT NULL,
  `Titre` varchar(100) NOT NULL,
  `Requete` set('100') NOT NULL,
  `Groupe` varchar(10) NOT NULL,
  PRIMARY KEY (`IdTicket`),
  KEY `fk_Groupe` (`Groupe`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE `utilisateur` (
  `Username` varchar(8) NOT NULL,
  `Mdp` varchar(50) NOT NULL,
  `Type` varchar(15) NOT NULL,
  `Groupe` set('10') DEFAULT NULL,
  `Nom` varchar(50) NOT NULL,
  `Prenom` varchar(50) NOT NULL,
  `Mail` varchar(100) NOT NULL,
  PRIMARY KEY (`Username`),
  KEY `fk_Groupe` (`Groupe`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


-- 2017-12-13 13:24:59
