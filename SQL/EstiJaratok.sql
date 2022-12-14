
DROP VIEW IF EXISTS `esti_jaratok`;

CREATE TABLE IF NOT EXISTS `esti_jaratok` (
    `Megálló_Azonosító` int(3)
    ,`Jármű_Azonosító` int(3)
    ,`Beér` time
    );


DROP TABLE IF EXISTS `esti_jaratok`;

DROP VIEW IF EXISTS `esti_jaratok`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `esti_jaratok`  AS  (select `menetrend`.`Megálló_Azonosító` AS `Megálló_Azonosító`,`menetrend`.`Jármű_Azonosító` AS `Jármű_Azonosító`,`menetrend`.`Beér` AS `Beér` from `menetrend` where `menetrend`.`Beér` > '18:00:00') ;
