DROP TABLE IF EXISTS `megallo_azonosito`;

CREATE TABLE IF NOT EXISTS `megallo_azonosito` (
    `Megálló_Azonosító` int(3) NOT NULL,
    `Megálló_Név` varchar(40) NOT NULL,
    PRIMARY KEY (`Megálló_Azonosító`) USING BTREE,
    UNIQUE KEY `Megálló_Név` (`Megálló_Név`),
    KEY `Megálló_Azonosító` (`Megálló_Azonosító`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

ALTER TABLE `megallo_azonosito`
    ADD CONSTRAINT `megallo_azonosito_ibfk_1` FOREIGN KEY (`Megálló_Azonosító`) REFERENCES `megallo` (`Megálló_Azonosító`) ON DELETE CASCADE ON UPDATE CASCADE;
