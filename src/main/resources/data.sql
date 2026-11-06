-- US 4.2 (Issue-190) : Seed-Daten mit direkten INSERT...VALUES ...Statements
INSERT INTO studienordnung (id , kennung, name, standardsemester, gesamt_ects)
VALUES (1, '08/24', 'Studienordnung FIW 2024', 6, 180);
INSERT INTO studienordnung (id , kennung, name, standardsemester, gesamt_ects)
VALUES (2, '17/14', 'Studienordnung FIW 2023', 6, 180);

-- T1 (Issue-202) : StuPO 08/24 @author: Israa Alnaddaf

-- 1.Semester
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects)
VALUES (1, 1, 'B11', 'Programmieren 1', 1, 'P', 6);
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects)
VALUES (2, 1, 'B12', 'Rechnerarchitektur/Betriebssysteme', 1, 'P', 5);
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects)
VALUES (3, 1, 'B13', 'Grundlegende Konzepte der Informatik', 1, 'P', 5);
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects)
VALUES (4, 1, 'B14', 'BWL 1: Grundlagen der BWL', 1, 'P', 5);
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects)
VALUES (5, 1, 'B15', 'Mathematik 1', 1, 'P', 5);
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects)
VALUES (6, 1, 'B16', '1. Fremdsprache 1', 1, 'WP', 4);
--2.Semester
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects)
VALUES (7, 1, 'B21', 'Programmierung 2', 2, 'P', 5);
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects)
VALUES (8, 1, 'B22', 'Mathematik 2', 2, 'P', 6);
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects)
VALUES (9, 1, 'B23', 'Software-Engineering 1', 2, 'P', 5);
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects)
VALUES (10, 1, 'B24', 'Webtechnologien', 2, 'P', 5);
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects)
VALUES (11, 1, 'B25', 'BWL 2: Rechnungswesen', 2, 'P', 5);
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects)
VALUES (12, 1, 'B26', '1. Fremdsprache 2', 2, 'P', 4);
--3.Semester
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects)
VALUES (13, 1, 'B31', 'Data Science', 3, 'P', 5);
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects)
VALUES (14, 1, 'B32', 'Software-Engineering 2', 3, 'P', 5);
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects)
VALUES (15, 1, 'B33', 'Datenbanksysteme', 3, 'P', 5);
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects)
VALUES (16, 1, 'B34', 'Softwareentwicklungsprojekt', 3, 'WP', 10);
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects)
VALUES (17, 1, 'B35', 'Projektmanagement', 3, 'P', 5);
--4.Semester
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects)
VALUES (18, 1, 'B41', 'Wahlpflichtmodul (Wirtschafts-)Informatik 1', 4, 'WP', 5);
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects)
VALUES (19, 1, 'B42', 'Wissenschaftliches Schreiben', 4, 'P', 5);
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects)
VALUES (20, 1, 'B43', 'Fachpraktikum', 4, 'WP', 20);
--5.Semester
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects)
VALUES (21, 1, 'B51', 'Human Computer Interaction', 5, 'P', 5);
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects)
VALUES (22, 1, 'B52', 'Verteilte Systeme', 5, 'P', 5);
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects)
VALUES (23, 1, 'B53', 'Datenschutz und Datensicherheit', 5, 'P', 5);
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects)
VALUES (24, 1, 'B54', 'Wahlpflichtmodul (Wirtschafts-)Informatik 2', 5, 'WP', 5);
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects)
VALUES (25, 1, 'B55', 'Informatik und Gesellschaft', 5, 'P', 5);
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects)
VALUES (26, 1, 'B56', 'Projekte in der Wirtschaft', 5, 'WP', 6);
--6.Semester
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects)
VALUES (27, 1, 'B61', 'Modellierung von Informationssystemen', 6, 'P', 5);
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects)
VALUES (28, 1, 'B62', 'BWL 3: Berufspraxis IT-Wirtschaft', 6, 'P', 5);
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects)
VALUES (29, 1, 'B63', 'AWE-Modul 1', 6, 'WP', 2);
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects)
VALUES (30, 1, 'B64', 'AWE-Modul 2', 6, 'WP', 2);
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects)
VALUES (31, 1, 'B65', 'Bachelorseminar und Abschlusskolloquium', 6, 'P', 3);
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects)
VALUES (32, 1, 'B66', 'Bachelorarbeit', 6, 'P', 12);

--Modulteile der StuPO 2024
INSERT INTO modul_teil (id, modul_id, nummer, name, form, sws)
VALUES (1, 16, 'B34.1', 'Softwareentwicklungsprojekt', 'PS', 4);
INSERT INTO modul_teil (id, modul_id, nummer, name, form, sws)
VALUES (2, 16, 'B34.2', 'Arbeitswelt IT', 'PS', 2);
INSERT INTO modul_teil (id, modul_id, nummer, name, form, sws)
VALUES (3, 17, 'B35.1', 'Agiles Projektmanagement', 'PÜ', 2);
INSERT INTO modul_teil (id, modul_id, nummer, name, form, sws)
VALUES (4, 17, 'B35.2', 'Konfliktmanagement und Mediation', 'PÜ', 2);
INSERT INTO modul_teil (id, modul_id, nummer, name, form, sws)
VALUES (5, 31, 'B65.1', 'Bachelorseminar', 'PS', 1);

--Wahlpflichtmodule WP1 bis WP15
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects)
VALUES (33, 1, 'WP1', 'Machine Learning', 4, 'WP', 5);
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects)
VALUES (34, 1, 'WP2', 'Deep Learning', 4, 'WP', 5);
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects)
VALUES (35, 1, 'WP3', 'Vertiefung in Datenbanken und Datenstrukturen', 4, 'WP', 5);
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects)
VALUES (36, 1, 'WP4', 'Vertiefung in Datenschutz und Datensicherheit', 4, 'WP', 5);
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects)
VALUES (37, 1, 'WP5', 'IT-Security', 4, 'WP', 5);
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects)
VALUES (38, 1, 'WP6', 'Programmierung mit Python', 4, 'WP', 5);
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects)
VALUES (39, 1, 'WP7', 'DevOps', 4, 'WP', 5);
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects)
VALUES (40, 1, 'WP8', 'Cloud Computing', 4, 'WP', 5);
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects)
VALUES (41, 1, 'WP9', 'Micro-Service-Architekturen', 4, 'WP', 5);
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects)
VALUES (42, 1, 'WP10', 'Ethik in Informatik', 4, 'WP', 5);
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects)
VALUES (43, 1, 'WP11', 'Business Intelligence', 4, 'WP', 5);
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects)
VALUES (44, 1, 'WP12', 'Design Patterns', 4, 'WP', 5);
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects)
VALUES (45, 1, 'WP13', 'Progressive Web Apps', 4, 'WP', 5);
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects)
VALUES (46, 1, 'WP14', 'Besondere Kapitel der Informatik', 4, 'WP', 5);
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects)
VALUES (47, 1, 'WP15', 'Besondere Kapitel der Wirtschaftsinformatik', 4, 'WP', 5);

--Voraussetzungen der StuPO 2024 (NV und EV)
INSERT INTO modul_voraussetzung (modul_id, vorausgesetztes_modul_id, voraussetzung_typ)
VALUES (7, 1, 'EV');
INSERT INTO modul_voraussetzung (modul_id, vorausgesetztes_modul_id, voraussetzung_typ)
VALUES (7, 3, 'EV');
INSERT INTO modul_voraussetzung (modul_id, vorausgesetztes_modul_id, voraussetzung_typ)
VALUES (8, 5, 'EV');
INSERT INTO modul_voraussetzung (modul_id, vorausgesetztes_modul_id, voraussetzung_typ)
VALUES (9, 1, 'EV');
INSERT INTO modul_voraussetzung (modul_id, vorausgesetztes_modul_id, voraussetzung_typ)
VALUES (9, 3, 'EV');
INSERT INTO modul_voraussetzung (modul_id, vorausgesetztes_modul_id, voraussetzung_typ)
VALUES (10, 1, 'EV');
INSERT INTO modul_voraussetzung (modul_id, vorausgesetztes_modul_id, voraussetzung_typ)
VALUES (11, 4, 'EV');
INSERT INTO modul_voraussetzung (modul_id, vorausgesetztes_modul_id, voraussetzung_typ)
VALUES (12, 6, 'EV');
INSERT INTO modul_voraussetzung (modul_id, vorausgesetztes_modul_id, voraussetzung_typ)
VALUES (13, 3, 'EV');
INSERT INTO modul_voraussetzung (modul_id, vorausgesetztes_modul_id, voraussetzung_typ)
VALUES (14, 9, 'EV');
INSERT INTO modul_voraussetzung (modul_id, vorausgesetztes_modul_id, voraussetzung_typ)
VALUES (15, 10, 'EV');
INSERT INTO modul_voraussetzung (modul_id, vorausgesetztes_modul_id, voraussetzung_typ)
VALUES (16, 1, 'NV');
INSERT INTO modul_voraussetzung (modul_id, vorausgesetztes_modul_id, voraussetzung_typ)
VALUES (16, 7, 'EV');
INSERT INTO modul_voraussetzung (modul_id, vorausgesetztes_modul_id, voraussetzung_typ)
VALUES (16, 10, 'EV');
INSERT INTO modul_voraussetzung (modul_id, vorausgesetztes_modul_id, voraussetzung_typ)
VALUES (17, 9, 'EV');
INSERT INTO modul_voraussetzung (modul_id, vorausgesetztes_modul_id, voraussetzung_typ)
VALUES (21, 14, 'EV');
INSERT INTO modul_voraussetzung (modul_id, vorausgesetztes_modul_id, voraussetzung_typ)
VALUES (22, 10, 'EV');
INSERT INTO modul_voraussetzung (modul_id, vorausgesetztes_modul_id, voraussetzung_typ)
VALUES (22, 13, 'EV');
INSERT INTO modul_voraussetzung (modul_id, vorausgesetztes_modul_id, voraussetzung_typ)
VALUES (23, 2, 'EV');
INSERT INTO modul_voraussetzung (modul_id, vorausgesetztes_modul_id, voraussetzung_typ)
VALUES (25, 19, 'EV');
INSERT INTO modul_voraussetzung (modul_id, vorausgesetztes_modul_id, voraussetzung_typ)
VALUES (26, 16, 'NV');
INSERT INTO modul_voraussetzung (modul_id, vorausgesetztes_modul_id, voraussetzung_typ)
VALUES (26, 7, 'EV');
INSERT INTO modul_voraussetzung (modul_id, vorausgesetztes_modul_id, voraussetzung_typ)
VALUES (27, 15, 'EV');
INSERT INTO modul_voraussetzung (modul_id, vorausgesetztes_modul_id, voraussetzung_typ)
VALUES (27, 21, 'EV');
INSERT INTO modul_voraussetzung (modul_id, vorausgesetztes_modul_id, voraussetzung_typ)
VALUES (28, 4, 'EV');
INSERT INTO modul_voraussetzung (modul_id, vorausgesetztes_modul_id, voraussetzung_typ)
VALUES (28, 11, 'EV');
INSERT INTO modul_voraussetzung (modul_id, vorausgesetztes_modul_id, voraussetzung_typ)
VALUES (33, 13, 'EV');
INSERT INTO modul_voraussetzung (modul_id, vorausgesetztes_modul_id, voraussetzung_typ)
VALUES (34, 13, 'EV');
INSERT INTO modul_voraussetzung (modul_id, vorausgesetztes_modul_id, voraussetzung_typ)
VALUES (35, 15, 'EV');
INSERT INTO modul_voraussetzung (modul_id, vorausgesetztes_modul_id, voraussetzung_typ)
VALUES (38, 10, 'EV');
INSERT INTO modul_voraussetzung (modul_id, vorausgesetztes_modul_id, voraussetzung_typ)
VALUES (39, 14, 'EV');
INSERT INTO modul_voraussetzung (modul_id, vorausgesetztes_modul_id, voraussetzung_typ)
VALUES (40, 10, 'EV');
INSERT INTO modul_voraussetzung (modul_id, vorausgesetztes_modul_id, voraussetzung_typ)
VALUES (41, 10, 'EV');
INSERT INTO modul_voraussetzung (modul_id, vorausgesetztes_modul_id, voraussetzung_typ)
VALUES (43, 15, 'EV');
INSERT INTO modul_voraussetzung (modul_id, vorausgesetztes_modul_id, voraussetzung_typ)
VALUES (44, 8, 'EV');
INSERT INTO modul_voraussetzung (modul_id, vorausgesetztes_modul_id, voraussetzung_typ)
VALUES (45, 10, 'EV');



-- T2 (Issue-204) : StuPO 17/14 @author: Khawla Yousef

-- Wahlpflichtoptionen für B41 und B54
INSERT INTO wahlpflicht_option (slot_modul_id, wahl_modul_id) VALUES (18, 33);
INSERT INTO wahlpflicht_option (slot_modul_id, wahl_modul_id) VALUES (18, 34);
INSERT INTO wahlpflicht_option (slot_modul_id, wahl_modul_id) VALUES (18, 35);
INSERT INTO wahlpflicht_option (slot_modul_id, wahl_modul_id) VALUES (18, 36);
INSERT INTO wahlpflicht_option (slot_modul_id, wahl_modul_id) VALUES (18, 37);
INSERT INTO wahlpflicht_option (slot_modul_id, wahl_modul_id) VALUES (18, 38);
INSERT INTO wahlpflicht_option (slot_modul_id, wahl_modul_id) VALUES (18, 39);
INSERT INTO wahlpflicht_option (slot_modul_id, wahl_modul_id) VALUES (18, 40);
INSERT INTO wahlpflicht_option (slot_modul_id, wahl_modul_id) VALUES (18, 41);
INSERT INTO wahlpflicht_option (slot_modul_id, wahl_modul_id) VALUES (18, 42);
INSERT INTO wahlpflicht_option (slot_modul_id, wahl_modul_id) VALUES (18, 43);
INSERT INTO wahlpflicht_option (slot_modul_id, wahl_modul_id) VALUES (18, 44);
INSERT INTO wahlpflicht_option (slot_modul_id, wahl_modul_id) VALUES (18, 45);
INSERT INTO wahlpflicht_option (slot_modul_id, wahl_modul_id) VALUES (18, 46);
INSERT INTO wahlpflicht_option (slot_modul_id, wahl_modul_id) VALUES (18, 47);
INSERT INTO wahlpflicht_option (slot_modul_id, wahl_modul_id) VALUES (24, 33);
INSERT INTO wahlpflicht_option (slot_modul_id, wahl_modul_id) VALUES (24, 34);
INSERT INTO wahlpflicht_option (slot_modul_id, wahl_modul_id) VALUES (24, 35);
INSERT INTO wahlpflicht_option (slot_modul_id, wahl_modul_id) VALUES (24, 36);
INSERT INTO wahlpflicht_option (slot_modul_id, wahl_modul_id) VALUES (24, 37);
INSERT INTO wahlpflicht_option (slot_modul_id, wahl_modul_id) VALUES (24, 38);
INSERT INTO wahlpflicht_option (slot_modul_id, wahl_modul_id) VALUES (24, 39);
INSERT INTO wahlpflicht_option (slot_modul_id, wahl_modul_id) VALUES (24, 40);
INSERT INTO wahlpflicht_option (slot_modul_id, wahl_modul_id) VALUES (24, 41);
INSERT INTO wahlpflicht_option (slot_modul_id, wahl_modul_id) VALUES (24, 42);
INSERT INTO wahlpflicht_option (slot_modul_id, wahl_modul_id) VALUES (24, 43);
INSERT INTO wahlpflicht_option (slot_modul_id, wahl_modul_id) VALUES (24, 44);
INSERT INTO wahlpflicht_option (slot_modul_id, wahl_modul_id) VALUES (24, 45);
INSERT INTO wahlpflicht_option (slot_modul_id, wahl_modul_id) VALUES (24, 46);
INSERT INTO wahlpflicht_option (slot_modul_id, wahl_modul_id) VALUES (24, 47);


-- Module der StuPO 2014

-- 1.Semester
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects) VALUES (48, 2, 'B11', 'Programmierung 1', 1, 'P', 6);
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects) VALUES (49, 2, 'B12', 'Rechnerarchitektur/Betriebssysteme', 1, 'P', 5);
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects) VALUES (50, 2, 'B13', 'Grundlegende Konzepte der Informatik', 1, 'P', 5);
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects) VALUES (51, 2, 'B14', 'BWL 1: Grundlagen der BWL', 1, 'P', 5);
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects) VALUES (52, 2, 'B15', 'Mathematik', 1, 'P', 5);
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects) VALUES (53, 2, 'B16', 'Fremdsprache 1', 1, 'WP', 4);
--2.Semester
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects) VALUES (54, 2, 'B21', 'Programmierung 2', 2, 'P', 5);
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects) VALUES (55, 2, 'B22', 'Rechnernetze', 2, 'P', 6);
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects) VALUES (56, 2, 'B23', 'Softwaretechnik', 2, 'P', 5);
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects) VALUES (57, 2, 'B24', 'BWL 2: Rechnungswesen', 2, 'P', 5);
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects) VALUES (58, 2, 'B25', 'Betriebliche Anwendungen', 2, 'P', 5);
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects) VALUES (59, 2, 'B26', 'Fremdsprache 2', 2, 'WP', 4);
--3.Semester
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects) VALUES (60, 2, 'B31', 'Webtechnologien', 3, 'P', 5);
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects) VALUES (61, 2, 'B32', 'Datenschutz und Datensicherheit', 3, 'P', 5);
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects) VALUES (62, 2, 'B33', 'Datenbanksysteme', 3, 'P', 5);
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects) VALUES (63, 2, 'B34', 'Projekte in der Wirtschaft', 3, 'WP', 10);
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects) VALUES (64, 2, 'B35', 'Teamarbeit', 3, 'P', 5);
--4.Semester
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects) VALUES (65, 2, 'B41', 'Besondere Kapitel der Informatik', 4, 'WP', 5);
INSERT INTO modul (id, studienordnung_id, nummer, name, semester, modul_typ, ects) VALUES (66, 2, 'B42', 'Praxisphase', 4, 'P', 25);