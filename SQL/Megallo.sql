DROP TABLE IF EXISTS `megallo`;

CREATE TABLE IF NOT EXISTS `megallo` (
    `Megálló_Azonosító` int(3) NOT NULL AUTO_INCREMENT,
    `Irányítószám` int(4) NOT NULL,
    PRIMARY KEY (`Megálló_Azonosító`) USING BTREE,
    KEY `Irányítószám` (`Irányítószám`)
    ) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4;

ALTER TABLE `megallo`
    ADD CONSTRAINT `megallo_ibfk_1` FOREIGN KEY (`Irányítószám`) REFERENCES `varos` (`Irányítószám`) ON DELETE CASCADE ON UPDATE CASCADE;
