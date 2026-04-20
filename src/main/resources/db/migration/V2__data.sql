
-- PATIENT TEST
-- =====================
INSERT INTO patient 
(cin, nom, prenom, telephone, date_creation)
VALUES 
('12345578', 'Ali', 'Ahmed', '20000000', NOW()),
('87654321', 'Ben', 'Sara', '50000000', NOW());


-- =====================
-- MEDECIN TEST
-- =====================
INSERT INTO medecin
(nom, prenom, specialite, numero_ordre, actif)
VALUES
('Trabelsi', 'Mohamed', 'Cardiologue', 'MED001', true),
('Gharbi', 'Amal', 'Generaliste', 'MED002', true);