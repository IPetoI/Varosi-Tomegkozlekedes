DROP TABLE IF EXISTS `villamos`;

CREATE TABLE IF NOT EXISTS `villamos` (
    `Jármű_Azonosító` int(3) NOT NULL,
    `Kocsi_szám` int(5) NOT NULL,
    PRIMARY KEY (`Jármű_Azonosító`) USING BTREE,
    KEY `Jármű Azonosító` (`Jármű_Azonosító`) USING BTREE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

ALTER TABLE `villamos`
    ADD CONSTRAINT `villamos_ibfk_1` FOREIGN KEY (`Jármű_Azonosító`) REFERENCES `jarmu` (`Jármű_Azonosító`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;