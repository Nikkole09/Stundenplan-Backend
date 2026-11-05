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
