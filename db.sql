-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Mer 01 Avril 2015 à 09:03
-- Version du serveur: 5.5.24-log
-- Version de PHP: 5.4.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `courrier`
--
CREATE DATABASE `courrier` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `courrier`;

-- --------------------------------------------------------

--
-- Structure de la table `courrier`
--

CREATE TABLE IF NOT EXISTS `courrier` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NOT NULL,
  `chuid` int(11) NOT NULL,
  `dfuid` int(11) NOT NULL,
  `chfuid` int(11) NOT NULL,
  `chiuid` int(11) NOT NULL,
  `type` varchar(250) NOT NULL,
  `nature` varchar(250) NOT NULL,
  `montant` mediumtext NOT NULL,
  `initdate` date NOT NULL,
  `finaldate` date DEFAULT NULL,
  `etat` int(1) DEFAULT NULL,
  `motif` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_charge_du_courier` (`chuid`),
  KEY `fk_destinataire_final` (`dfuid`),
  KEY `fk_charge_du_courier_final` (`chfuid`),
  KEY `fk_charge_du_courier_intermediaire` (`chiuid`),
  KEY `fk_pochette` (`pid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 ;



-- --------------------------------------------------------

--
-- Structure de la table `loc`
--

CREATE TABLE IF NOT EXISTS `loc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `lbl` varchar(250) NOT NULL,
  `type` varchar(60) NOT NULL,
  `lblzone` varchar(250) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_loc` (`uid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 ;


-- --------------------------------------------------------

--
-- Structure de la table `mode`
--

CREATE TABLE IF NOT EXISTS `mode` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cid` int(11) NOT NULL,
  `type` varchar(250) NOT NULL,
  `nbrrecom` int(11) NOT NULL,
  `transporteur` varchar(250) NOT NULL,
  `chauffeur` varchar(250) NOT NULL,
  `daterec` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_mode_envoi_courrier` (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

-- --------------------------------------------------------

--
-- Structure de la table `pochette`
--

CREATE TABLE IF NOT EXISTS `pochette` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nbr` int(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 ;



-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(1) NOT NULL,
  `login` varchar(60) NOT NULL,
  `pw` varchar(60) NOT NULL,
  `prenom` varchar(120) NOT NULL,
  `nom` varchar(120) NOT NULL,
  `cin` varchar(10) NOT NULL,
  `adress` varchar(250) NOT NULL,
  `tel` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login` (`login`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 ;



--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `courrier`
--
ALTER TABLE `courrier`
  ADD CONSTRAINT `fk_charge_du_courier` FOREIGN KEY (`chuid`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `fk_charge_du_courier_final` FOREIGN KEY (`chfuid`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `fk_charge_du_courier_intermediaire` FOREIGN KEY (`chiuid`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `fk_destinataire_final` FOREIGN KEY (`dfuid`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `fk_pochette` FOREIGN KEY (`pid`) REFERENCES `pochette` (`id`);

--
-- Contraintes pour la table `loc`
--
ALTER TABLE `loc`
  ADD CONSTRAINT `fk_user_loc` FOREIGN KEY (`uid`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `mode`
--
ALTER TABLE `mode`
  ADD CONSTRAINT `fk_mode_envoi_courrier` FOREIGN KEY (`cid`) REFERENCES `courrier` (`id`);


--
-- Contenu de la table `user`
--

INSERT INTO `user` (`id`, `role`, `login`, `pw`, `prenom`, `nom`, `cin`, `adress`, `tel`) VALUES
(1, '1', 'admin', 'admin', 'admin', 'administrateur', '01234567', '1, rue 1222 ', '6687687');

--
-- Contenu de la table `loc`
--

INSERT INTO `loc` (`id`, `uid`, `lbl`, `type`, `lblzone`) VALUES
(1, 1, 'lblb', 'type', 'zonelbl'),