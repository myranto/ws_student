-- create database hiu;
-- \c hiu
CREATE TABLE Etudiant (
  idEtudiant      serial NOT NULL,
  Nom             varchar(30) NOT NULL, 
  Prenom          varchar(30) NOT NULL, 
  Email           varchar(30) NOT NULL unique ,
  MotDePasse      varchar(10) NOT NULL, 
  DateNaissance   date NOT NULL check ( DateNaissance<current_date ),
  DateInscription date NOT NULL, 
  CarteEtudiant   varchar(255) NOT NULL, 
  PRIMARY KEY (idEtudiant));
CREATE TABLE BibliothequeEtudiant (
  idBibliothequeEtudiant serial NOT NULL,
  EtudiantidEtudiant     int NOT NULL,
  NomDocument            varchar(30) NOT NULL, 
  TypeDocument           varchar(30) NOT NULL, 
  CheminDocument         text NOT NULL,
  PRIMARY KEY (idBibliothequeEtudiant));
CREATE TABLE PriseDeNotes (
  idPriseDeNotes     serial NOT NULL,
  EtudiantidEtudiant int NOT NULL,
  TitreNotes         varchar(40) NOT NULL,
  ContenuNotes       text NOT NULL,
  image              text NOT NULL,
  mots_cles          varchar(100) NOT NULL,
  PRIMARY KEY (idPriseDeNotes));
CREATE TABLE Tache (
  idTache          SERIAL NOT NULL, 
  idEtudiant       int NOT NULL,
  DatePlanning     timestamp DEFAULT current_timestamp NOT NULL, 
  Durree           int NOT NULL,
  TitreTache       varchar(30) NOT NULL, 
  DescriptionTache varchar(255) NOT NULL, 
  priorite         int DEFAULT 0 NOT NULL,
  rappel           timestamp, 
  etat             int DEFAULT 0 NOT NULL,
  ProjetidProjet   int NOT NULL,
  PRIMARY KEY (idTache));
CREATE TABLE Projet (
  idProjet           SERIAL NOT NULL, 
  EtudiantidEtudiant int NOT NULL,
  NomProjet          varchar(30) NOT NULL, 
  DateDebut          timestamp NOT NULL, 
  DescriptionProjet  varchar(255) NOT NULL, 
  DateFin            timestamp NOT NULL, 
  PRIMARY KEY (idProjet));
CREATE TABLE SousTache (
  idSousTache     serial NOT NULL,
  TitreSousTache  varchar(255) NOT NULL,
  Description     varchar(255) NOT NULL,
  Date_sous_tache          Timestamp NOT NULL,
  estimation      int NOT NULL,
  TempsPasse      int NOT NULL,
  priorite        int DEFAULT 0 NOT NULL,
  etat            int DEFAULT 0 NOT NULL,
  PlanningidTache int NOT NULL,
  PRIMARY KEY (idSousTache));
CREATE TABLE Pomodoro (
  idPomodoro serial NOT NULL,
  DateDebut  timestamp NOT NULL,
  Durree     int NOT NULL,
  Pause      int NOT NULL,
  PRIMARY KEY (idPomodoro));
-- CREATE TABLE Etudiant_Pomodoro (
--     EtudiantidEtudiant   int NOT NULL  references Etudiant(idEtudiant),
--     PomodoroidPomodoro   int NOT NULL  references Pomodoro(idPomodoro),
--     SousTacheidSousTache int NOT NULL ,
--     DateFin timestamp DEFAULT current_timestamp NOT NULL
-- );
CREATE TABLE Etudiant_Pomodoro (
  EtudiantidEtudiant   int NOT NULL,
  PomodoroidPomodoro   int NOT NULL,
  SousTacheidSousTache int NOT NULL,
  DateFin              timestamp DEFAULT current_timestamp NOT NULL,
  PRIMARY KEY (EtudiantidEtudiant,
  PomodoroidPomodoro));

CREATE TABLE Publication (
  idPublication      SERIAL NOT NULL, 
  texte              varchar(255) NOT NULL, 
  DatePublication    timestamp NOT NULL, 
  EtudiantidEtudiant int NOT NULL,
  PRIMARY KEY (idPublication));

CREATE TABLE Commentaire (
  idCommentaire            SERIAL NOT NULL, 
  PublicationidPublication int NOT NULL,
  texte                    varchar(255) NOT NULL, 
  DateCommentaire          timestamp NOT NULL, 
  EtudiantidEtudiant       int NOT NULL,
--   Notes                    int NOT NULL,
  PRIMARY KEY (idCommentaire));
create table note_coms(
    idCommentaire int not null references Commentaire(idCommentaire),
    notes int not null check ( notes >=0 ),
    idEtudiant int not null references Etudiant(idEtudiant),
    primary key (idEtudiant,idCommentaire)
);
CREATE TABLE FichierPartager (
  CommentaireidCommentaire int NOT NULL,
  FichierPartager          text NOT NULL);
ALTER TABLE BibliothequeEtudiant ADD CONSTRAINT FKBibliotheq508354 FOREIGN KEY (EtudiantidEtudiant) REFERENCES Etudiant (idEtudiant);
ALTER TABLE PriseDeNotes ADD CONSTRAINT FKPriseDeNot507607 FOREIGN KEY (EtudiantidEtudiant) REFERENCES Etudiant (idEtudiant);
ALTER TABLE Projet ADD CONSTRAINT FKProjet106811 FOREIGN KEY (EtudiantidEtudiant) REFERENCES Etudiant (idEtudiant);
ALTER TABLE Tache ADD CONSTRAINT FKTache775918 FOREIGN KEY (ProjetidProjet) REFERENCES Projet (idProjet);
ALTER TABLE Tache ADD CONSTRAINT FKTache457809 FOREIGN KEY (idEtudiant) REFERENCES Etudiant (idEtudiant);
ALTER TABLE SousTache ADD CONSTRAINT FKSousTache696225 FOREIGN KEY (PlanningidTache) REFERENCES Tache (idTache);
ALTER TABLE Etudiant_Pomodoro ADD CONSTRAINT FKEtudiant_P894367 FOREIGN KEY (EtudiantidEtudiant) REFERENCES Etudiant (idEtudiant);
ALTER TABLE Etudiant_Pomodoro ADD CONSTRAINT FKEtudiant_P76745 FOREIGN KEY (PomodoroidPomodoro) REFERENCES Pomodoro (idPomodoro);
ALTER TABLE Etudiant_Pomodoro ADD CONSTRAINT FKEtudiant_P973613 FOREIGN KEY (SousTacheidSousTache) REFERENCES SousTache (idSousTache);
ALTER TABLE Publication ADD CONSTRAINT FKPublicatio483930 FOREIGN KEY (EtudiantidEtudiant) REFERENCES Etudiant (idEtudiant);
ALTER TABLE Commentaire ADD CONSTRAINT FKCommentair265886 FOREIGN KEY (PublicationidPublication) REFERENCES Publication (idPublication);
ALTER TABLE Commentaire ADD CONSTRAINT FKCommentair337648 FOREIGN KEY (EtudiantidEtudiant) REFERENCES Etudiant (idEtudiant);
ALTER TABLE FichierPartager ADD CONSTRAINT FKFichierPar118554 FOREIGN KEY (CommentaireidCommentaire) REFERENCES Commentaire (idCommentaire);
-- // list dataheh
insert into Etudiant (Nom, Prenom, Email, MotDePasse, DateNaissance, DateInscription, CarteEtudiant) VALUES
('randria','myranto','my.@gmail.com','mmm','2003-10-04',current_date,'scsc'),
('tita','steven','tita.@gmail.com','mm','2003-10-04',current_date,'scsc');
insert into Publication(texte, DatePublication, EtudiantidEtudiant) VALUES ('za zany misy probleme eo am resaka deploiement react',current_timestamp,1);
insert into Commentaire(PublicationidPublication, texte, DateCommentaire, EtudiantidEtudiant) values
(1,'zao lesy ah,mandehana am netlify fa any mora deploiena',current_timestamp,2);

INSERT INTO Etudiant (Nom, Prenom, Email, MotDePasse, DateNaissance, DateInscription, CarteEtudiant)
VALUES ('Dupont', 'Jean', 'jean.dupont@mail.com', '123456', '2000-01-01', '2022-01-01', '12345');

INSERT INTO BibliothequeEtudiant (EtudiantidEtudiant, NomDocument, TypeDocument, CheminDocument)
VALUES (1, 'Document1', 'pdf', '/documents/document1.pdf');

INSERT INTO PriseDeNotes (EtudiantidEtudiant, TitreNotes, ContenuNotes, image, mots_cles)
VALUES (1, 'Notes de physique', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed accumsan tellus quis semper bibendum. Integer nec nibh feugiat, volutpat libero vitae, tempor ante. Nunc congue orci at metus tincidunt, nec tincidunt mi malesuada. ', 'image1.jpg', 'physique, formules');
INSERT INTO Projet (EtudiantidEtudiant, NomProjet, DateDebut, DescriptionProjet, DateFin)
VALUES (1, 'Projet de physique', '2022-02-01 00:00:00', 'Projet de recherche sur la physique quantique', '2022-06-30 00:00:00');

INSERT INTO Tache (idEtudiant, DatePlanning, Durree, TitreTache, DescriptionTache, priorite, rappel, etat, ProjetidProjet)
VALUES (1, '2022-03-10 14:30:00', 2, 'Réunion projet', 'Réunion pour discuter des avancées sur le projet', 1, NULL, 0, 1);


INSERT INTO SousTache (TitreSousTache, Description, Date_sous_tache, estimation, TempsPasse, priorite, etat, PlanningidTache)
VALUES ('Recherches bibliographiques', 'Recherche de publications sur le sujet du projet', '2022-02-05 00:00:00', 3, 2, 2, 1, 1);

INSERT INTO Pomodoro (DateDebut, Durree, Pause)
VALUES ('2022-03-07 09:00:00', 25, 5);

INSERT INTO Etudiant_Pomodoro (EtudiantidEtudiant, PomodoroidPomodoro, SousTacheidSousTache, DateFin)
VALUES (1, 1, 1, '2022-03-07 09:30:00');

INSERT INTO Publication (texte, DatePublication, EtudiantidEtudiant)
VALUES ('Bonjour tout le monde', '2022-03-07 10:00:00', 1);

INSERT INTO Commentaire (PublicationidPublication, texte, DateCommentaire, EtudiantidEtudiant)
VALUES (2, 'Salut !', '2022-03-07 10:01:00', 2);

INSERT INTO note_coms (idCommentaire, notes, idEtudiant)
VALUES (1, 4, 1);

INSERT INTO FichierPartager (CommentaireidCommentaire, FichierPartager)
VALUES (1, '/fichiers/fichier1.pdf');