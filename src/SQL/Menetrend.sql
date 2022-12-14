DROP TABLE IF EXISTS `menetrend`;

CREATE TABLE IF NOT EXISTS `menetrend` (
    `Megálló_Azonosító` int(3) NOT NULL,
    `Jármű_Azonosító` int(3) NOT NULL,
    `Beér` time NOT NULL,
    PRIMARY KEY (`Megálló_Azonosító`,`Jármű_Azonosító`),
    KEY `Jármű_Azonosító_kkulcs` (`Jármű_Azonosító`) USING BTREE,
    KEY `Megálló_Azonosító_kkulcs` (`Megálló_Azonosító`) USING BTREE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

ALTER TABLE `menetrend`
    ADD CONSTRAINT `menetrend_ibfk_1` FOREIGN KEY (`Jármű_Azonosító`) REFERENCES `jarmu` (`Jármű_Azonosító`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `menetrend_ibfk_2` FOREIGN KEY (`Megálló_Azonosító`) REFERENCES `megallo` (`Megálló_Azonosító`) ON DELETE CASCADE ON UPDATE CASCADE;
