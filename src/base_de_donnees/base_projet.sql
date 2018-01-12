-- log adminer
-- user : root
-- mdp : _

DROP TABLE IF EXISTS ticket;
CREATE TABLE ticket (
	IdTicket integer,
	Titre VARCHAR(100),
	GroupeSortant VARCHAR(25),
        GroupeDestinataire VARCHAR(25),
    constraint pk_Ticket primary key(idTicket),
    constraint fk_GroupeSortant foreign key(GroupeSortant)
	references groupe(Nom)
        on delete cascade,
    constraint fk_GroupeDestinataire foreign key(GroupeDestinataire)
        references groupe(Nom)
        on delete cascade
);

DROP TABLE IF EXISTS groupe;
CREATE TABLE groupe (
	Nom VARCHAR(25),
        Categorie VARCHAR(9), 
    constraint pk_Groupe primary key(Nom),
    CONSTRAINT ck_categorie CHECK (Categorie IN ('Technique', 'Etude'))
);

DROP TABLE IF EXISTS utilisateur;
CREATE TABLE utilisateur (
    Username VARCHAR(8),
    Mdp VARCHAR(50),
    Categorie VARCHAR(20), 
    Groupe VARCHAR(25),
    Nom VARCHAR(50),
    Prenom VARCHAR(50),
    Mail VARCHAR(100),
    CONSTRAINT pk_Utilisateur PRIMARY KEY(Username),
    CONSTRAINT fk_Groupe FOREIGN KEY(Groupe)
        REFERENCES groupe(Nom)
        on delete cascade,
    CONSTRAINT ck_categorie CHECK (Categorie IN ('Etudiant','Professeur','Service technique','Administrateur'))
);

DROP TABLE IF EXISTS message;
CREATE TABLE message (
    Ticket INTEGER,
    IdExpediteur VARCHAR(50),
    Etat VARCHAR(10),
    Texte VARCHAR(500),
    constraint fk_Ticket foreign key(Ticket)
        references ticket(idTicket)
        on delete cascade,
    constraint fk_idExpeditauer foreign key(IdExpediteur)
        references utilisateur(Username)
        on delete cascade,
    constraint ck_etat check (Etat in ('Lu','Recu','Envoye','Pas envoye'))
);

insert into groupe values ('TDA2', 'Etude');
insert into groupe values ('TDA3', 'Etude');
insert into groupe values ('Electricien', 'Technique');
insert into groupe values ('Plombier', 'Technique');

insert into utilisateur values ('thomas', 'oui', 'Etudiant', 'TDA2', 'Lyle', 'Thomas', 'qqch@autrechose.fr');
insert into utilisateur values ('leo', 'yes', 'Etudiant', 'TDA2', 'Laugier', 'Leo', 'qqch@autrechose.fr');
insert into utilisateur values ('jules', 'si', 'Etudiant', 'TDA3', 'Lehodey', 'Jules', 'qqch@autrechose.fr');
insert into utilisateur values ('elec', 'elec', 'Service technique', 'Electricien', 'Electricien', 'Un', 'elec@serviceTech.fr');
insert into utilisateur values ('plomb', 'plomb', 'Service technique', 'Plombier', 'Plombier', 'Un', 'plomb@serviceTech.fr');

insert into ticket values ('1', 'test plombier', 'TDA2', 'Plombier');
insert into ticket values ('2', 'test electricien', 'TDA3', 'Electricien');
insert into ticket values ('3', 'test tda3', 'Electricien', 'TDA3'); 
insert into ticket values ('4', 'test tda2', 'Plombier', 'TDA2');

insert into message values ('1', 'leo','Recu', 'Ceci est un test pour les plombier');
insert into message values ('2', 'jules', 'Recu', 'Ceci est un test pour les electriciens');
insert into message values ('1', 'plomb', 'Recu', 'Ceci est une reponse des plombiers');
insert into message values ('1', 'plomb', 'Recu', 'Ceci est une reponse des plombiers'); 
insert into message values ('3', 'elec', 'Recu', 'Ceci est un test pour tda3'); 
insert into message values ('4', 'plomb', 'Recu', 'Ceci est un test pour tda2');