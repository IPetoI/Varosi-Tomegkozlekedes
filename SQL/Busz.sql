
DROP TABLE IF EXISTS `busz`;

CREATE TABLE IF NOT EXISTS `busz` (
    `Jármű_Azonosító` int(3) NOT NULL,
    `Férőhely_szám` int(140) NOT NULL,
    PRIMARY KEY (`Jármű_Azonosító`) USING BTREE,
    KEY `Jármű_Azonosító` (`Jármű_Azonosító`) USING BTREE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

ALTER TABLE `busz`
    ADD CONSTRAINT `busz_ibfk_1` FOREIGN KEY (`Jármű_Azonosító`) REFERENCES `jarmu` (`Jármű_Azonosító`) ON DELETE CASCADE ON UPDATE CASCADE;

