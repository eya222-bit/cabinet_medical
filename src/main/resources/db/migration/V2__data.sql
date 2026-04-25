INSERT INTO app_role (role_name) VALUES ('ADMIN'), ('MEDECIN'), ('SECRETAIRE');

-- password = "password123" encodé en BCrypt
INSERT INTO app_user (username, password, nom, prenom, email, actif) VALUES
                                                                         ('admin', '$2a$10$N9qo8uLOickgx2ZMRZoMy.Mr/.cYw5Kk7xqHhq4wR5JX1QxYxLxL.', 'Admin', 'Système', 'admin@cabinet.com', true),
                                                                         ('medecin.dupont', '$2a$10$N9qo8uLOickgx2ZMRZoMy.Mr/.cYw5Kk7xqHhq4wR5JX1QxYxLxL.', 'Dupont', 'Jean', 'jean.dupont@cabinet.com', true),
                                                                         ('secretaire.martin', '$2a$10$N9qo8uLOickgx2ZMRZoMy.Mr/.cYw5Kk7xqHhq4wR5JX1QxYxLxL.', 'Martin', 'Sophie', 'sophie.martin@cabinet.com', true);

INSERT INTO app_user_roles (app_user_id, roles_role_name) VALUES
                                                              (1, 'ADMIN'),
                                                              (2, 'MEDECIN'),
                                                              (3, 'SECRETAIRE');

INSERT INTO medecin (nom, prenom, specialite, numero_ordre, telephone, email, actif) VALUES
    ('Dupont', 'Jean', 'Généraliste', 'ORD12345', '0612345678', 'jean.dupont@cabinet.com', true);

INSERT INTO patient (cin, nom, prenom, date_naissance, telephone, email, antecedents, date_creation) VALUES
    ('BE123456', 'Bernard', 'Alain', '1980-05-15', '0623456789', 'alain.bernard@example.com', 'Allergie pénicilline', NOW());