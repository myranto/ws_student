\c postgres
drop database gestion;
create database gestion;
\c gestion

create table etudiant(
    id_etudiant serial primary key ,
    nom varchar(50),
    prenom varchar(50),
    email varchar(50),
    motdepasse varchar(50),
    date_naissance date,
    date_inscription date,
    carte_etudiant varchar(100)

);

create table repertoire(
    id_repertoire serial primary key,
    id_etudiant int references etudiant(id_etudiant),
    nom varchar(50),
    date_creation timestamp default now()
);

create table sous_repertoire(
    id_sous_repertoire serial primary key,
    id_repertoire_mere int references repertoire(id_repertoire),
    id_repertoire_fille int references repertoire(id_repertoire)
);

create table fichier(
    id_fichier serial primary key,
    id_etudiant int references etudiant(id_etudiant),
    id_repertoire int references repertoire(id_repertoire),
    nom varchar(100),
    value text,
    date_creation timestamp default now()
);

create or replace view sous_repertoire_view as
select s.id_repertoire_mere, r.*
from sous_repertoire s
join repertoire r on s.id_repertoire_fille=r.id_repertoire;

