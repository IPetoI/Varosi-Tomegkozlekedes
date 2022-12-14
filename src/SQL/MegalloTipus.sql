DROP TABLE IF EXISTS `megallo_tipus`;

CREATE TABLE IF NOT EXISTS `megallo_tipus` (
    `Megálló_Azonosító` int(3) NOT NULL,
    `Megálló_Típus` varchar(10) NOT NULL,
    PRIMARY KEY (`Megálló_Azonosító`,`Megálló_Típus`),
    KEY `Megálló_Azonosító` (`Megálló_Azonosító`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

ALTER TABLE `megallo_tipus`
    ADD CONSTRAINT `megallo_tipus_ibfk_1` FOREIGN KEY (`Megálló_Azonosító`) REFERENCES `megallo` (`Megálló_Azonosító`) ON DELETE CASCADE ON UPDATE CASCADE;
