-- V1__init.sql : création des tables
CREATE TABLE patient (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         cin VARCHAR(20) NOT NULL UNIQUE,
                         nom VARCHAR(100) NOT NULL,
                         prenom VARCHAR(100) NOT NULL,
                         date_naissance DATE NOT NULL,
                         telephone VARCHAR(20),
                         email VARCHAR(100),
                         antecedents TEXT,
                         date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE medecin (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         nom VARCHAR(100) NOT NULL,
                         prenom VARCHAR(100) NOT NULL,
                         specialite VARCHAR(100),
                         numero_ordre VARCHAR(50) NOT NULL UNIQUE,
                         telephone VARCHAR(20),
                         email VARCHAR(100),
                         actif BOOLEAN DEFAULT TRUE
);

CREATE TABLE rendez_vous (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             patient_id BIGINT NOT NULL,
                             medecin_id BIGINT NOT NULL,
                             date_heure DATETIME NOT NULL,
                             duree_minutes INT NOT NULL DEFAULT 30,
                             statut ENUM('PLANIFIE', 'CONFIRME', 'ANNULE', 'TERMINE') NOT NULL,
                             motif VARCHAR(255),
                             CONSTRAINT fk_rdv_patient FOREIGN KEY (patient_id) REFERENCES patient(id) ON DELETE CASCADE,
                             CONSTRAINT fk_rdv_medecin FOREIGN KEY (medecin_id) REFERENCES medecin(id) ON DELETE CASCADE,
                             UNIQUE KEY uk_rdv_medecin_dateheure (medecin_id, date_heure)
);

CREATE TABLE ordonnance (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            rendez_vous_id BIGINT NOT NULL UNIQUE,
                            date_emission DATE NOT NULL,
                            observations TEXT,
                            CONSTRAINT fk_ordo_rdv FOREIGN KEY (rendez_vous_id) REFERENCES rendez_vous(id) ON DELETE CASCADE
);

CREATE TABLE ligne_medicament (
                                  id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                  ordonnance_id BIGINT NOT NULL,
                                  nom_medicament VARCHAR(255) NOT NULL,
                                  posologie VARCHAR(255),
                                  duree VARCHAR(100),
                                  CONSTRAINT fk_ligne_ordo FOREIGN KEY (ordonnance_id) REFERENCES ordonnance(id) ON DELETE CASCADE
);

CREATE TABLE app_role ( id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(50) NOT NULL
);

CREATE TABLE app_user (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          username VARCHAR(50) UNIQUE NOT NULL,
                          password VARCHAR(255) NOT NULL,
                          nom VARCHAR(100),
                          prenom VARCHAR(100),
                          email VARCHAR(100),
                          actif BOOLEAN DEFAULT TRUE
);

CREATE TABLE app_user_roles (
                                app_user_id BIGINT,
                                app_role_id BIGINT,
                                PRIMARY KEY (app_user_id, app_role_id),
                                CONSTRAINT fk_user FOREIGN KEY (app_user_id) REFERENCES app_user(id) ON DELETE CASCADE,
                                CONSTRAINT fk_role FOREIGN KEY (app_role_id) REFERENCES app_role(id) ON DELETE CASCADE
);