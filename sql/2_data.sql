-- -----------------------------------------------------
-- Data for table kind
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO kind (idkind, name) VALUES (1, 'AAC File');
INSERT INTO kind (idkind, name) VALUES (2, 'MPEG File');

COMMIT;


-- -----------------------------------------------------
-- Data for table key
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO key (idkey, openkey, musical) VALUES (1, '1m', 'Am');
INSERT INTO key (idkey, openkey, musical) VALUES (2, '1d', 'C');
INSERT INTO key (idkey, openkey, musical) VALUES (3, '2m', 'Em');
INSERT INTO key (idkey, openkey, musical) VALUES (4, '2d', 'G');
INSERT INTO key (idkey, openkey, musical) VALUES (5, '3m', 'Bm');
INSERT INTO key (idkey, openkey, musical) VALUES (6, '3d', 'D');
INSERT INTO key (idkey, openkey, musical) VALUES (7, '4m', 'F#m');
INSERT INTO key (idkey, openkey, musical) VALUES (8, '4d', 'A');
INSERT INTO key (idkey, openkey, musical) VALUES (9, '5m', 'Dbm');
INSERT INTO key (idkey, openkey, musical) VALUES (10, '5d', 'E');
INSERT INTO key (idkey, openkey, musical) VALUES (11, '6m', 'Abm');
INSERT INTO key (idkey, openkey, musical) VALUES (12, '6d', 'B');
INSERT INTO key (idkey, openkey, musical) VALUES (13, '7m', 'Ebm');
INSERT INTO key (idkey, openkey, musical) VALUES (14, '7d', 'F#');
INSERT INTO key (idkey, openkey, musical) VALUES (15, '8m', 'Bbm');
INSERT INTO key (idkey, openkey, musical) VALUES (16, '8d', 'Db');
INSERT INTO key (idkey, openkey, musical) VALUES (17, '9m', 'Fm');
INSERT INTO key (idkey, openkey, musical) VALUES (18, '9d', 'Ab');
INSERT INTO key (idkey, openkey, musical) VALUES (19, '10m', 'Cm');
INSERT INTO key (idkey, openkey, musical) VALUES (20, '10d', 'Ebm');
INSERT INTO key (idkey, openkey, musical) VALUES (21, '11m', 'Gm');
INSERT INTO key (idkey, openkey, musical) VALUES (22, '11d', 'Bb');
INSERT INTO key (idkey, openkey, musical) VALUES (23, '12m', 'Dm');
INSERT INTO key (idkey, openkey, musical) VALUES (24, '12d', 'F');

COMMIT;

