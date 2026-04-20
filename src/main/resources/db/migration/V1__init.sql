-- =====================
-- TABLE PATIENT
-- =====================
CREATE TABLE patient (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cin VARCHAR(20) UNIQUE,
    nom VARCHAR(50),
    prenom VARCHAR(50),
    date_naissance DATE,
    telephone VARCHAR(20),
    email VARCHAR(100),
    antecedents TEXT,
    date_creation DATETIME
);

-- =====================
-- TABLE MEDECIN
-- =====================
CREATE TABLE medecin (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50),
    prenom VARCHAR(50),
    specialite VARCHAR(100),
    numero_ordre VARCHAR(50) UNIQUE,
    telephone VARCHAR(20),
    email VARCHAR(100),
    actif BOOLEAN
);

-- =====================
-- TABLE RENDEZ_VOUS
-- =====================
CREATE TABLE rendez_vous (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    patient_id BIGINT,
    medecin_id BIGINT,
    date_heure DATETIME,
    duree_minutes INT,
    statut VARCHAR(20),
    motif VARCHAR(255),

    CONSTRAINT fk_patient
    FOREIGN KEY (patient_id) REFERENCES patient(id),

    CONSTRAINT fk_medecin
    FOREIGN KEY (medecin_id) REFERENCES medecin(id)
);

-- =====================
-- TABLE ORDONNANCE
-- =====================
CREATE TABLE ordonnance (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    rendez_vous_id BIGINT UNIQUE,
    date_emission DATE,
    observations TEXT,

    CONSTRAINT fk_rdv
    FOREIGN KEY (rendez_vous_id) REFERENCES rendez_vous(id)
);

-- =====================
-- TABLE LIGNE MEDICAMENT
-- =====================
CREATE TABLE ligne_medicament (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    ordonnance_id BIGINT,
    nom_medicament VARCHAR(100),
    posologie VARCHAR(100),
    duree DATETIME,

    CONSTRAINT fk_ordonnance
    FOREIGN KEY (ordonnance_id) REFERENCES ordonnance(id)
);