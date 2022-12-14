
DROP TABLE IF EXISTS `iranyitoszam`;

CREATE TABLE IF NOT EXISTS `iranyitoszam` (
    `Irányítószám` int(4) NOT NULL,
    `Város_Név` varchar(40) NOT NULL,
    PRIMARY KEY (`Irányítószám`) USING BTREE,
    UNIQUE KEY `Város_Név` (`Város_Név`),
    KEY `Irányítószám` (`Irányítószám`) USING BTREE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

ALTER TABLE `iranyitoszam`
    ADD CONSTRAINT `iranyitoszam_ibfk_1` FOREIGN KEY (`Irányítószám`) REFERENCES `varos` (`Irányítószám`) ON DELETE CASCADE ON UPDATE CASCADE;
