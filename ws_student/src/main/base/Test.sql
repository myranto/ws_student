INSERT INTO Projet (EtudiantidEtudiant, NomProjet, DateDebut, DescriptionProjet, DateFin)
VALUES (1, 'Projet de gestion de tâches', '2022-02-01 00:00:00', 'Un projet pour gérer les tâches à accomplir', '2022-02-28 23:59:59');

INSERT INTO Tache (idEtudiant, DatePlanning, Durree, TitreTache, DescriptionTache, priorite, rappel, etat, ProjetidProjet)
VALUES (1, '2022-02-01 08:00:00', 60, 'Tâche 1', 'Faire la liste des tâches', 1, NULL, 0, 1),
       (1, '2022-02-01 10:00:00', 120, 'Tâche 2', 'Implémenter la fonctionnalité de création de tâches', 2, NULL, 0, 1),
       (1, '2022-02-02 09:00:00', 90, 'Tâche 3', 'Implémenter la fonctionnalité de suppression de tâches', 3, NULL, 0, 1);

INSERT INTO SousTache (TitreSousTache, Description, Date_sous_tache, estimation, TempsPasse, priorite, etat, PlanningidTache)
VALUES ('Sous-tâche 1', 'Créer la vue pour la liste des tâches', '2022-02-01 08:00:00', 60, 60, 1, 0, 1),
       ('Sous-tâche 2', 'Créer la fonction de création de tâches', '2022-02-01 10:00:00', 120, 120, 2, 0, 2),
       ('Sous-tâche 3', 'Créer la fonction de suppression de tâches', '2022-02-02 09:00:00', 90, 90, 3, 0, 3);

INSERT INTO SousTache (TitreSousTache, Description, Date_sous_tache, estimation, TempsPasse, priorite, etat, PlanningidTache)
VALUES ('Sous-tâche 4', 'Créer la vue pour la liste des tâches', '2022-02-01 08:00:00', 60, 60, 1, 0, 1) , ('Sous-tâche 5', 'Créer la vue pour la liste des tâches', '2022-02-01 08:00:00', 60, 60, 1, 0, 1) ;


-- Exemples de données de test pour la table ProfilEtudiant
INSERT INTO ProfilEtudiant(idEtudiant, domaine_etude, profil, competences, experience)
VALUES (1, 'Informatique', 'Stage Développeur Web', 'HTML, CSS, JavaScript, PHP', 2);

-- Exemples de données de test pour la table OffreStage

INSERT INTO OffreStage (nom_entreprise, poste, description_stage, exigences, localisation, date_debut, date_fin, info_contact, lien)
VALUES
    ('Entreprise A', 'Stage Développeur Web', 'Stage pour le développement de sites web', 'Connaissances en HTML, CSS, JavaScript, PHP', 'Paris', '2023-05-01', '2023-08-30', 'contact@entrepriseA.com', 'https://www.entrepriseA.com/stage-dev-web'),
    ('Entreprise B', 'Stage Marketing Digital', 'Stage pour la promotion de produits en ligne', 'Connaissances en marketing digital', 'Lyon', '2023-06-01', '2023-09-30', 'contact@entrepriseB.com', 'https://www.entrepriseB.com/stage-marketing-digital'),
    ('Entreprise C', 'Stage Design Graphique', 'Stage pour la création de supports de communication visuelle', 'Connaissances en design graphique et outils de création', 'Marseille', '2023-07-01', '2023-10-30', 'contact@entrepriseC.com', 'https://www.entrepriseC.com/stage-design-graphique');