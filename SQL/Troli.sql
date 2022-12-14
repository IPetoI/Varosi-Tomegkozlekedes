DROP TABLE IF EXISTS `troli`;

CREATE TABLE IF NOT EXISTS `troli` (
    `Jármű_Azonosító` int(3) NOT NULL,
    `Mozgáskorlátozott_e` int(1) NOT NULL,
    PRIMARY KEY (`Jármű_Azonosító`) USING BTREE,
    KEY `Jármű Azonosító` (`Jármű_Azonosító`) USING BTREE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

ALTER TABLE `troli`
    ADD CONSTRAINT `troli_ibfk_1` FOREIGN KEY (`Jármű_Azonosító`) REFERENCES `jarmu` (`Jármű_Azonosító`) ON DELETE CASCADE ON UPDATE CASCADE;
