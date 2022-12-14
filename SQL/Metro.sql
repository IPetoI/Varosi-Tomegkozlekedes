DROP TABLE IF EXISTS `metro`;

CREATE TABLE IF NOT EXISTS `metro` (
    `Jármű_Azonosító` int(3) NOT NULL,
    `Szín` varchar(30) NOT NULL,
    PRIMARY KEY (`Jármű_Azonosító`) USING BTREE,
    KEY `Jármű Azonosító` (`Jármű_Azonosító`) USING BTREE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

ALTER TABLE `metro`
    ADD CONSTRAINT `metro_ibfk_1` FOREIGN KEY (`Jármű_Azonosító`) REFERENCES `jarmu` (`Jármű_Azonosító`) ON DELETE CASCADE ON UPDATE CASCADE;
