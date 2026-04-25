je veux créer un site site de gestion de cabinet medical avec springboot L'application permettra de
gérer les dossiers patients, les médecins, les rendez-vous et les ordonnances. L'accent est mis sur la cohérence des données, la navigation fluide entre entités liées, et la sécurité des accès par rôles.j'ai 3 roles va etre authentifié lors de navigation dans le site   l'admin qui a acces au 3 dashbord de de tous (dashbord secretaire ,dachbord medecin,dachbord patient)  .  la secretaire qui va créer fiche patient  qui contenir les infos (id,nom,prenom..) aussi ce fiche contient  l'ordonnance de ce patient depuis la base de donne ordonnance ou son nom (select *ordonnance where  patient=x) et historique des rdv de ce patient  ,elle est concu aussi de créer rdv( patient (ManyToOne), medecin (le medecin doit être disponible)  Implémenter un système de RDV avec contraintes de non-chevauchement. dateHeure (LocalDateTime), dureeMinutes (int), statut (Enum: PLANIFIE, CONFIRME, ANNULE, TERMINE) ,aussi elle peut  consulter rdv de semaine /jour ,consulter patient en attente, maintenant le medecin qui est déjà créer par l'admin ce medecin peut créer ordonnance (rendezvous ,dateemission,medicament ou il va créer (nom medicament,posologie,duree) et observations ,il peut aussi consulter ses rendezvous ceer par la secretaire ,patient qui peut s'inscrire s'il n'a pas de compte ou entrer a sa dashboard s'il avait un compte sa dashboard qui contient crreer un rdv ou entrer ses infos (, cin (Unique), nom, prenom, dateNaissance (LocalDate)
• telephone, email, antecedents (Text), dateCreation (LocalDateTime))
 Objectifs pédagogiques
• Concevoir un schéma relationnel complexe avec plusieurs entités interdépendantes.
• Implémenter un système de RDV avec contraintes de non-chevauchement.
• Utiliser les projections Spring Data pour optimiser les requêtes de liste.
• Maîtriser la navigation entre vues et les fragments Thymeleaf réutilisables.
• Appliquer des règles de validation métier au niveau de la couche service.
 Fonctionnalités requises
4.1 Fonctionnalités obligatoires
• CRUD Patients (CIN, nom, prénom, date de naissance, antécédents, téléphone, email).
• CRUD Médecins (nom, spécialité, numéro d'ordre, horaires de disponibilité).
• Gestion des Rendez-vous : création, modification, annulation, affichage par médecin/jour.
• Vérification automatique de la non-superposition des RDV d'un médecin.
• Création d'Ordonnances liées à une consultation (médicaments, posologie, durée).
• Fiche patient complète : infos, historique des RDV, ordonnances.
• Tableau de bord : RDV du jour, de la semaine, patients en attente.
• Authentification avec rôles (admin, secrétaire, médecin).
5. Modèle de données — Entités JPA principales
Entité : Patient
• id, cin (Unique), nom, prenom, dateNaissance (LocalDate)
• telephone, email, antecedents (Text), dateCreation (LocalDateTime)
Entité : Medecin
• id, nom, prenom, specialite, numeroOrdre (Unique)
• telephone, email, actif (boolean)
Entité : RendezVous
• id, patient (ManyToOne), medecin (ManyToOne)
• dateHeure (LocalDateTime), dureeMinutes (int)
• statut (Enum: PLANIFIE, CONFIRME, ANNULE, TERMINE), motif
Entité : Ordonnance
• id, rendezVous (OneToOne), dateEmission (LocalDate)
• medicaments (OneToMany → LigneMedicament), observations (Text)
Entité : LigneMedicament
• id, ordonnance (ManyToOne), nomMedicament, posologie, duree

6. Architecture des packages
L'application doit respecter l'architecture MVC avec la structure de packages suivante :
• com.fst.cabinet.entity — Entités JPA
• com.fst.cabinet.repository — Spring Data Repos + requêtes @Query JPQL
• com.fst.cabinet.service — Services métier (validation chevauchements)
• com.fst.cabinet.controller — Contrôleurs MVC par domaine métier

• com.fst.cabinet.config — SecurityConfig (rôles et permissions)
• src/main/resources/db/migration/ — Scripts Flyway (V1__init.sql, V2__data.sql
d'apres les exigence de la cahier de charge la premier etape pour le le premier semaine :Analyse, modélisation & setup: Diagramme ER, scripts Flyway V1/V2, entités JPA,
Spring Security
je veux un demarge bien conforme avec la cahier de charge et sans conflis et probleme 
 Contraintes techniques
• Les migrations BDD doivent utiliser Flyway (pas de ddl-auto=create).
• La validation de non-chevauchement des RDV doit être dans la couche service.
• Utiliser des fragments Thymeleaf pour les éléments répétés (navbar, footer, sidebar).
• Toutes les dates/heures gérées avec java.time (LocalDate, LocalDateTime).
Stack technologique
Couche
Backend
Frontend
Base de données
Sécurité
Build & outils
Technologie
Spring Boot 3.x, Spring MVC, Spring Data JPA
Thymeleaf 3, Bootstrap 5, Thymeleaf Layout Dialect
MySQL 8, Hibernate, Flyway (migrations)
Spring Security — rôles ADMIN, MEDECIN, SECRETAIRE
Maven, Lombok, Git (branches feature/
