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

ALTER TABLE `jegy`
    ADD CONSTRAINT `jegy_ibfk_1` FOREIGN KEY (`Irányítószám`) REFERENCES `varos` (`Irányítószám`) ON DELETE CASCADE ON UPDATE CASCADE;
