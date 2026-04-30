INSERT INTO app_role (name) VALUES ('ADMIN'), ('MEDECIN'), ('SECRETAIRE'),('PATIENT');

-- password = "password123" encodé en BCrypt
INSERT INTO app_user (username, password, nom, prenom, email, actif) VALUES
                                                                         ('admin', '$2a$12$WaHHbWo9/HkdY188gXgo2eRmGhSOqZLx3yLS.dDa3QW3.1hCtOYAq', 'Admin', 'Système', 'admin@cabinet.com', true),
                                                                         ('medecin.dupont', '$2a$12$hwQPBvrU0je6WJRCaU46Fu9WTPpoowzcanuROjmV6RBKpiYwHPwSq', 'Dupont', 'Jean', 'jean.dupont@cabinet.com', true),
                                                                         ('secretaire.martin', '$2a$12$xfwo/nDxdcZOEYaCJAmxNe.OJYH.ZMdPrSkBMWwli7upBPJQjTmJ.', 'Martin', 'Sophie', 'sophie.martin@cabinet.com', true);

INSERT INTO app_user_roles (app_user_id, app_role_id) VALUES
                                                              (1, 1),
                                                              (2, 2),
                                                              (3, 3);

INSERT INTO medecin (nom, prenom, specialite, numero_ordre, telephone, email, actif) VALUES
    ('Dupont', 'Jean', 'Généraliste', 'ORD12345', '0612345678', 'jean.dupont@cabinet.com', true);

INSERT INTO patient (cin, nom, prenom, date_naissance, telephone, email, antecedents, date_creation) VALUES
    ('BE123456', 'Bernard', 'Alain', '1980-05-15', '0623456789', 'alain.bernard@example.com', 'Allergie pénicilline', NOW());