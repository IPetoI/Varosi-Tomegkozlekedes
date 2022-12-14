
DROP TABLE IF EXISTS `jarmu_azonosito`;

CREATE TABLE IF NOT EXISTS `jarmu_azonosito` (
    `Jármű_Azonosító` int(3) NOT NULL,
    `Járatszám` varchar(4) NOT NULL,
    PRIMARY KEY (`Jármű_Azonosító`) USING BTREE,
    UNIQUE KEY `Járatszám` (`Járatszám`) USING BTREE,
    KEY `Jármű_Azonosító` (`Jármű_Azonosító`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

ALTER TABLE `jarmu_azonosito`
    ADD CONSTRAINT `jarmu_azonosito_ibfk_1` FOREIGN KEY (`Jármű_Azonosító`) REFERENCES `jarmu` (`Jármű_Azonosító`) ON DELETE CASCADE ON UPDATE CASCADE;
