-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Gép: 127.0.0.1:3306
-- Létrehozás ideje: 2021. Nov 21. 23:31
-- Kiszolgáló verziója: 10.4.18-MariaDB
-- PHP verzió: 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Adatbázis: `varosi_tomegkozlekedes`
--

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `busz`
--

DROP TABLE IF EXISTS `busz`;
CREATE TABLE IF NOT EXISTS `busz` (
  `Jármű_Azonosító` int(3) NOT NULL,
  `Férőhely_szám` int(140) NOT NULL,
  PRIMARY KEY (`Jármű_Azonosító`) USING BTREE,
  KEY `Jármű_Azonosító` (`Jármű_Azonosító`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- A tábla adatainak kiíratása `busz`
--

INSERT INTO `busz` (`Jármű_Azonosító`, `Férőhely_szám`) VALUES
(1, 64),
(2, 60),
(7, 55),
(8, 72),
(9, 68),
(10, 55),
(11, 50),
(17, 62);

-- --------------------------------------------------------

--
-- A nézet helyettes szerkezete `esti_jaratok`
-- (Lásd alább az aktuális nézetet)
--
DROP VIEW IF EXISTS `esti_jaratok`;
CREATE TABLE IF NOT EXISTS `esti_jaratok` (
`Megálló_Azonosító` int(3)
,`Jármű_Azonosító` int(3)
,`Beér` time
);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `iranyitoszam`
--

DROP TABLE IF EXISTS `iranyitoszam`;
CREATE TABLE IF NOT EXISTS `iranyitoszam` (
  `Irányítószám` int(4) NOT NULL,
  `Város_Név` varchar(40) NOT NULL,
  PRIMARY KEY (`Irányítószám`) USING BTREE,
  UNIQUE KEY `Város_Név` (`Város_Név`),
  KEY `Irányítószám` (`Irányítószám`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- A tábla adatainak kiíratása `iranyitoszam`
--

INSERT INTO `iranyitoszam` (`Irányítószám`, `Város_Név`) VALUES
(1529, 'Budapest'),
(6000, 'Kecskemét'),
(6050, 'Lajosmizse'),
(6700, 'Szeged');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `jarmu`
--

DROP TABLE IF EXISTS `jarmu`;
CREATE TABLE IF NOT EXISTS `jarmu` (
  `Jármű_Azonosító` int(3) NOT NULL,
  `Irányítószám` int(4) NOT NULL,
  PRIMARY KEY (`Jármű_Azonosító`),
  UNIQUE KEY `Jármű_Azonosító` (`Jármű_Azonosító`),
  KEY `Irányítószám` (`Irányítószám`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- A tábla adatainak kiíratása `jarmu`
--

INSERT INTO `jarmu` (`Jármű_Azonosító`, `Irányítószám`) VALUES
(12, 1529),
(13, 1529),
(14, 1529),
(15, 1529),
(16, 1529),
(17, 1529),
(7, 6000),
(8, 6000),
(9, 6000),
(10, 6000),
(11, 6000),
(1, 6700),
(2, 6700),
(3, 6700),
(4, 6700),
(5, 6700),
(6, 6700);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `jarmu_azonosito`
--

DROP TABLE IF EXISTS `jarmu_azonosito`;
CREATE TABLE IF NOT EXISTS `jarmu_azonosito` (
  `Jármű_Azonosító` int(3) NOT NULL,
  `Járatszám` varchar(4) NOT NULL,
  PRIMARY KEY (`Jármű_Azonosító`) USING BTREE,
  UNIQUE KEY `Járatszám` (`Járatszám`) USING BTREE,
  KEY `Jármű_Azonosító` (`Jármű_Azonosító`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- A tábla adatainak kiíratása `jarmu_azonosito`
--

INSERT INTO `jarmu_azonosito` (`Jármű_Azonosító`, `Járatszám`) VALUES
(5, '1'),
(3, '10'),
(8, '11'),
(10, '16'),
(17, '16A'),
(6, '2'),
(9, '21'),
(11, '29'),
(16, '3'),
(14, '4'),
(7, '5'),
(15, '50'),
(12, '74'),
(4, '8'),
(13, '80'),
(1, '90'),
(2, '90F');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `jegy`
--

DROP TABLE IF EXISTS `jegy`;
CREATE TABLE IF NOT EXISTS `jegy` (
  `Irányítószám` int(4) NOT NULL,
  `Normál_ár` int(4) NOT NULL,
  `Diák_ár` int(4) NOT NULL,
  `Nyugdíjas_ár` int(4) NOT NULL,
  `90%_ár` int(4) NOT NULL,
  PRIMARY KEY (`Irányítószám`) USING BTREE,
  KEY `Irányítószám` (`Irányítószám`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- A tábla adatainak kiíratása `jegy`
--

INSERT INTO `jegy` (`Irányítószám`, `Normál_ár`, `Diák_ár`, `Nyugdíjas_ár`, `90%_ár`) VALUES
(1529, 1000, 600, 350, 100),
(6000, 600, 300, 300, 60),
(6050, 700, 350, 350, 70),
(6700, 740, 400, 400, 75);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `megallo`
--

DROP TABLE IF EXISTS `megallo`;
CREATE TABLE IF NOT EXISTS `megallo` (
  `Megálló_Azonosító` int(3) NOT NULL AUTO_INCREMENT,
  `Irányítószám` int(4) NOT NULL,
  PRIMARY KEY (`Megálló_Azonosító`) USING BTREE,
  KEY `Irányítószám` (`Irányítószám`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4;

--
-- A tábla adatainak kiíratása `megallo`
--

INSERT INTO `megallo` (`Megálló_Azonosító`, `Irányítószám`) VALUES
(36, 1529),
(37, 1529),
(38, 1529),
(39, 1529),
(40, 1529),
(41, 1529),
(42, 1529),
(43, 1529),
(44, 1529),
(45, 1529),
(46, 1529),
(47, 1529),
(48, 1529),
(49, 1529),
(50, 1529),
(51, 1529),
(52, 1529),
(53, 1529),
(27, 6000),
(28, 6000),
(29, 6000),
(30, 6000),
(31, 6000),
(32, 6000),
(33, 6000),
(34, 6000),
(35, 6000),
(1, 6700),
(2, 6700),
(3, 6700),
(4, 6700),
(5, 6700),
(6, 6700),
(7, 6700),
(8, 6700),
(9, 6700),
(10, 6700),
(11, 6700),
(12, 6700),
(13, 6700),
(14, 6700),
(15, 6700),
(16, 6700),
(17, 6700),
(18, 6700),
(19, 6700),
(20, 6700),
(21, 6700),
(22, 6700),
(23, 6700),
(24, 6700),
(25, 6700),
(26, 6700);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `megallo_azonosito`
--

DROP TABLE IF EXISTS `megallo_azonosito`;
CREATE TABLE IF NOT EXISTS `megallo_azonosito` (
  `Megálló_Azonosító` int(3) NOT NULL,
  `Megálló_Név` varchar(40) NOT NULL,
  PRIMARY KEY (`Megálló_Azonosító`) USING BTREE,
  UNIQUE KEY `Megálló_Név` (`Megálló_Név`),
  KEY `Megálló_Azonosító` (`Megálló_Azonosító`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- A tábla adatainak kiíratása `megallo_azonosito`
--

INSERT INTO `megallo_azonosito` (`Megálló_Azonosító`, `Megálló_Név`) VALUES
(5, 'Agyagos utca'),
(9, 'Alkony utca'),
(39, 'Amerikai út'),
(16, 'Anna-Kút'),
(14, 'Aradi Vértanuk tere'),
(33, 'Árpádváros'),
(23, 'Bem utca'),
(1, 'Csanádi utca'),
(8, 'Csemegi-tó'),
(10, 'Cserepes sor'),
(4, 'Csillag tér'),
(18, 'Diófa Vendéglő'),
(53, 'Dísz tér'),
(13, 'Dugonics tér'),
(26, 'Európa Liget'),
(3, 'Fecske utca'),
(52, 'Fény utca '),
(2, 'Fő Fasor'),
(41, 'Gumigyár'),
(50, 'Határ út'),
(35, 'Hetényegyháza'),
(42, 'Honvéd utca'),
(30, 'Kadafalvi út'),
(44, 'Király utca'),
(7, 'Kisteleki utca'),
(15, 'Klinika'),
(51, 'Kőbánya alsó'),
(38, 'Közlekedési Múzeum'),
(47, 'Lajosmizsei sorompó'),
(45, 'Mester utca'),
(49, 'Mexikói út'),
(34, 'Miklovicsfalu'),
(31, 'Noszlopy Gáspár park'),
(46, 'Petőfi híd'),
(29, 'Petőfiváros'),
(40, 'Pillangó utca'),
(37, 'Rózsák tere'),
(12, 'Sándor Utca'),
(22, 'Somogyi utca'),
(25, 'Szatymazi utca'),
(27, 'Szécheny tér'),
(21, 'Széchenyi tér'),
(32, 'Széchenyiváros'),
(19, 'Szeged Pláza'),
(24, 'Szeged Vasútállomás'),
(28, 'Széktó park'),
(36, 'Szövetség utca'),
(20, 'Tavasz utca'),
(17, 'Tündér utca'),
(43, 'Újváros park'),
(6, 'Vértó'),
(48, 'Villanytelep'),
(11, 'Víztorony tér');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `megallo_tipus`
--

DROP TABLE IF EXISTS `megallo_tipus`;
CREATE TABLE IF NOT EXISTS `megallo_tipus` (
  `Megálló_Azonosító` int(3) NOT NULL,
  `Megálló_Típus` varchar(10) NOT NULL,
  PRIMARY KEY (`Megálló_Azonosító`,`Megálló_Típus`),
  KEY `Megálló_Azonosító` (`Megálló_Azonosító`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- A tábla adatainak kiíratása `megallo_tipus`
--

INSERT INTO `megallo_tipus` (`Megálló_Azonosító`, `Megálló_Típus`) VALUES
(1, 'Busz'),
(2, 'Busz'),
(3, 'Busz'),
(3, 'Troli'),
(4, 'Busz'),
(5, 'Busz'),
(6, 'Busz'),
(6, 'Villamos'),
(7, 'Busz'),
(8, 'Busz'),
(9, 'Busz'),
(10, 'Busz'),
(11, 'Troli'),
(12, 'Troli'),
(13, 'Troli'),
(14, 'Troli'),
(15, 'Troli'),
(16, 'Troli'),
(17, 'Troli'),
(18, 'Troli'),
(19, 'Villamos'),
(20, 'Villamos'),
(21, 'Villamos'),
(22, 'Villamos'),
(23, 'Villamos'),
(24, 'Villamos'),
(25, 'Villamos'),
(26, 'Villamos'),
(27, 'Busz'),
(28, 'Busz'),
(29, 'Busz'),
(30, 'Busz'),
(31, 'Busz'),
(32, 'Busz'),
(33, 'Busz'),
(34, 'Busz'),
(35, 'Busz'),
(36, 'Troli'),
(37, 'Troli'),
(37, 'Villamos'),
(38, 'Troli'),
(39, 'Busz'),
(39, 'Troli'),
(40, 'Troli'),
(41, 'Busz'),
(41, 'Troli'),
(42, 'Troli'),
(42, 'Villamos'),
(43, 'Troli'),
(44, 'Villamos'),
(45, 'Villamos'),
(46, 'Villamos'),
(47, 'Villamos'),
(48, 'Villamos'),
(49, 'Metro'),
(50, 'Metro'),
(51, 'Metro'),
(52, 'Busz'),
(53, 'Busz');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `menetrend`
--

DROP TABLE IF EXISTS `menetrend`;
CREATE TABLE IF NOT EXISTS `menetrend` (
  `Megálló_Azonosító` int(3) NOT NULL,
  `Jármű_Azonosító` int(3) NOT NULL,
  `Beér` time NOT NULL,
  PRIMARY KEY (`Megálló_Azonosító`,`Jármű_Azonosító`),
  KEY `Jármű_Azonosító_kkulcs` (`Jármű_Azonosító`) USING BTREE,
  KEY `Megálló_Azonosító_kkulcs` (`Megálló_Azonosító`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- A tábla adatainak kiíratása `menetrend`
--

INSERT INTO `menetrend` (`Megálló_Azonosító`, `Jármű_Azonosító`, `Beér`) VALUES
(1, 1, '17:10:00'),
(1, 2, '13:30:00'),
(2, 1, '17:14:00'),
(2, 2, '13:33:00'),
(3, 1, '17:17:00'),
(3, 2, '13:35:00'),
(3, 3, '10:05:00'),
(4, 1, '17:20:00'),
(4, 2, '13:38:00'),
(5, 1, '17:24:00'),
(5, 2, '13:40:00'),
(6, 1, '17:26:00'),
(6, 2, '13:43:00'),
(6, 6, '11:20:00'),
(7, 1, '17:29:00'),
(7, 2, '13:46:00'),
(8, 1, '17:33:00'),
(8, 2, '13:47:00'),
(9, 1, '17:35:00'),
(9, 2, '13:50:00'),
(10, 2, '13:52:00'),
(11, 3, '10:00:00'),
(12, 3, '10:08:00'),
(13, 3, '10:10:00'),
(13, 4, '13:48:00'),
(14, 3, '10:13:00'),
(14, 4, '13:50:00'),
(15, 3, '10:15:00'),
(15, 4, '13:52:00'),
(16, 4, '13:45:00'),
(17, 4, '13:43:00'),
(18, 4, '13:40:00'),
(19, 5, '17:40:00'),
(20, 5, '17:42:00'),
(20, 6, '11:15:00'),
(21, 5, '17:44:00'),
(22, 5, '17:46:00'),
(22, 6, '11:12:00'),
(23, 5, '17:47:00'),
(24, 5, '17:49:00'),
(24, 6, '11:10:00'),
(25, 6, '11:18:00'),
(26, 6, '11:22:00'),
(27, 7, '12:00:00'),
(27, 8, '15:30:00'),
(27, 10, '11:20:00'),
(27, 11, '18:30:00'),
(28, 7, '12:08:00'),
(29, 8, '15:37:00'),
(30, 8, '15:43:00'),
(31, 9, '07:00:00'),
(32, 9, '07:08:00'),
(32, 11, '18:37:00'),
(33, 9, '07:14:00'),
(34, 10, '11:25:00'),
(35, 11, '18:43:00'),
(36, 12, '09:40:00'),
(37, 12, '09:45:00'),
(37, 14, '19:13:00'),
(38, 12, '09:47:00'),
(39, 12, '09:51:00'),
(39, 17, '21:17:00'),
(40, 13, '14:20:00'),
(41, 13, '14:25:00'),
(41, 17, '21:12:00'),
(42, 13, '14:30:00'),
(42, 15, '16:40:00'),
(43, 13, '14:32:00'),
(44, 14, '19:00:00'),
(45, 14, '19:10:00'),
(46, 14, '19:18:00'),
(47, 15, '16:44:00'),
(48, 15, '16:49:00'),
(49, 16, '15:50:00'),
(50, 16, '15:57:00'),
(51, 16, '16:02:00'),
(52, 17, '21:00:00'),
(53, 17, '21:08:00');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `metro`
--

DROP TABLE IF EXISTS `metro`;
CREATE TABLE IF NOT EXISTS `metro` (
  `Jármű_Azonosító` int(3) NOT NULL,
  `Szín` varchar(30) NOT NULL,
  PRIMARY KEY (`Jármű_Azonosító`) USING BTREE,
  KEY `Jármű Azonosító` (`Jármű_Azonosító`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- A tábla adatainak kiíratása `metro`
--

INSERT INTO `metro` (`Jármű_Azonosító`, `Szín`) VALUES
(16, 'Kék');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `troli`
--

DROP TABLE IF EXISTS `troli`;
CREATE TABLE IF NOT EXISTS `troli` (
  `Jármű_Azonosító` int(3) NOT NULL,
  `Mozgáskorlátozott_e` int(1) NOT NULL,
  PRIMARY KEY (`Jármű_Azonosító`) USING BTREE,
  KEY `Jármű Azonosító` (`Jármű_Azonosító`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- A tábla adatainak kiíratása `troli`
--

INSERT INTO `troli` (`Jármű_Azonosító`, `Mozgáskorlátozott_e`) VALUES
(3, 1),
(4, 1),
(12, 1),
(13, 0);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `varos`
--

DROP TABLE IF EXISTS `varos`;
CREATE TABLE IF NOT EXISTS `varos` (
  `Irányítószám` int(4) NOT NULL,
  PRIMARY KEY (`Irányítószám`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- A tábla adatainak kiíratása `varos`
--

INSERT INTO `varos` (`Irányítószám`) VALUES
(1529),
(6000),
(6050),
(6700);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `villamos`
--

DROP TABLE IF EXISTS `villamos`;
CREATE TABLE IF NOT EXISTS `villamos` (
  `Jármű_Azonosító` int(3) NOT NULL,
  `Kocsi_szám` int(5) NOT NULL,
  PRIMARY KEY (`Jármű_Azonosító`) USING BTREE,
  KEY `Jármű Azonosító` (`Jármű_Azonosító`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- A tábla adatainak kiíratása `villamos`
--

INSERT INTO `villamos` (`Jármű_Azonosító`, `Kocsi_szám`) VALUES
(5, 2),
(6, 1),
(14, 1),
(15, 2);

-- --------------------------------------------------------

--
-- Nézet szerkezete `esti_jaratok`
--
DROP TABLE IF EXISTS `esti_jaratok`;

DROP VIEW IF EXISTS `esti_jaratok`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `esti_jaratok`  AS  (select `menetrend`.`Megálló_Azonosító` AS `Megálló_Azonosító`,`menetrend`.`Jármű_Azonosító` AS `Jármű_Azonosító`,`menetrend`.`Beér` AS `Beér` from `menetrend` where `menetrend`.`Beér` > '18:00:00') ;

--
-- Megkötések a kiírt táblákhoz
--

--
-- Megkötések a táblához `busz`
--
ALTER TABLE `busz`
  ADD CONSTRAINT `busz_ibfk_1` FOREIGN KEY (`Jármű_Azonosító`) REFERENCES `jarmu` (`Jármű_Azonosító`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Megkötések a táblához `iranyitoszam`
--
ALTER TABLE `iranyitoszam`
  ADD CONSTRAINT `iranyitoszam_ibfk_1` FOREIGN KEY (`Irányítószám`) REFERENCES `varos` (`Irányítószám`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Megkötések a táblához `jarmu`
--
ALTER TABLE `jarmu`
  ADD CONSTRAINT `jarmu_ibfk_1` FOREIGN KEY (`Irányítószám`) REFERENCES `varos` (`Irányítószám`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Megkötések a táblához `jarmu_azonosito`
--
ALTER TABLE `jarmu_azonosito`
  ADD CONSTRAINT `jarmu_azonosito_ibfk_1` FOREIGN KEY (`Jármű_Azonosító`) REFERENCES `jarmu` (`Jármű_Azonosító`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Megkötések a táblához `jegy`
--
ALTER TABLE `jegy`
  ADD CONSTRAINT `jegy_ibfk_1` FOREIGN KEY (`Irányítószám`) REFERENCES `varos` (`Irányítószám`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Megkötések a táblához `megallo`
--
ALTER TABLE `megallo`
  ADD CONSTRAINT `megallo_ibfk_1` FOREIGN KEY (`Irányítószám`) REFERENCES `varos` (`Irányítószám`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Megkötések a táblához `megallo_azonosito`
--
ALTER TABLE `megallo_azonosito`
  ADD CONSTRAINT `megallo_azonosito_ibfk_1` FOREIGN KEY (`Megálló_Azonosító`) REFERENCES `megallo` (`Megálló_Azonosító`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Megkötések a táblához `megallo_tipus`
--
ALTER TABLE `megallo_tipus`
  ADD CONSTRAINT `megallo_tipus_ibfk_1` FOREIGN KEY (`Megálló_Azonosító`) REFERENCES `megallo` (`Megálló_Azonosító`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Megkötések a táblához `menetrend`
--
ALTER TABLE `menetrend`
  ADD CONSTRAINT `menetrend_ibfk_1` FOREIGN KEY (`Jármű_Azonosító`) REFERENCES `jarmu` (`Jármű_Azonosító`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `menetrend_ibfk_2` FOREIGN KEY (`Megálló_Azonosító`) REFERENCES `megallo` (`Megálló_Azonosító`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Megkötések a táblához `metro`
--
ALTER TABLE `metro`
  ADD CONSTRAINT `metro_ibfk_1` FOREIGN KEY (`Jármű_Azonosító`) REFERENCES `jarmu` (`Jármű_Azonosító`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Megkötések a táblához `troli`
--
ALTER TABLE `troli`
  ADD CONSTRAINT `troli_ibfk_1` FOREIGN KEY (`Jármű_Azonosító`) REFERENCES `jarmu` (`Jármű_Azonosító`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Megkötések a táblához `villamos`
--
ALTER TABLE `villamos`
  ADD CONSTRAINT `villamos_ibfk_1` FOREIGN KEY (`Jármű_Azonosító`) REFERENCES `jarmu` (`Jármű_Azonosító`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
