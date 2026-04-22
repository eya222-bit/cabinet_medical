-- Insertion des rôles disponibles
INSERT INTO app_role (role_name) VALUES
                                     ('ADMIN'),
                                     ('MEDECIN'),
                                     ('SECRETAIRE');

-- Insertion de l'utilisateur administrateur
-- Password : 'password123' (haché avec BCrypt)
INSERT INTO app_user (username, password) VALUES
    ('admin', '$2a$10$8.I6Yt05/P.E8.Y9V.6Oquh2xYmJ7t8T/NqHlVzV9Z9.6z7z8.z6.');

-- Liaison entre l'utilisateur 1 (admin) et le rôle 'ADMIN'
INSERT INTO app_user_roles (app_user_id, roles_role_name) VALUES
    (1, 'ADMIN');