
DROP TABLE IF EXISTS `jarmu`;

CREATE TABLE IF NOT EXISTS `jarmu` (
    `Jármű_Azonosító` int(3) NOT NULL,
    `Irányítószám` int(4) NOT NULL,
    PRIMARY KEY (`Jármű_Azonosító`),
    UNIQUE KEY `Jármű_Azonosító` (`Jármű_Azonosító`),
    KEY `Irányítószám` (`Irányítószám`) USING BTREE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

ALTER TABLE `jarmu`
    ADD CONSTRAINT `jarmu_ibfk_1` FOREIGN KEY (`Irányítószám`) REFERENCES `varos` (`Irányítószám`) ON DELETE CASCADE ON UPDATE CASCADE;
