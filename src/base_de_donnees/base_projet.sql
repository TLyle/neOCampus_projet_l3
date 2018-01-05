-- log adminer
-- user : root
-- mdp : _

DROP TABLE IF EXISTS ticket;
CREATE TABLE ticket (
	IdTicket integer,
	Titre VARCHAR(100),
	Groupe VARCHAR(10),
    constraint pk_Ticket primary key(idTicket),
    constraint fk_Groupe foreign key(Groupe)
		references groupe(Nom)
        on delete cascade
);

DROP TABLE IF EXISTS groupe;
CREATE TABLE groupe (
	Nom VARCHAR(10),
    constraint pk_Groupe primary key(Nom)
);

DROP TABLE IF EXISTS utilisateur;
CREATE TABLE utilisateur (
	Username VARCHAR(8),
	Mdp VARCHAR(50),
	Type VARCHAR(15), 
	Groupe VARCHAR(10),
	Nom VARCHAR(50),
	Prenom VARCHAR(50),
	Mail VARCHAR(100),
	CONSTRAINT pk_Utilisateur PRIMARY KEY(Username),
	CONSTRAINT fk_Groupe FOREIGN KEY(Groupe)
		REFERENCES groupe(Nom)
		on delete cascade,
	CONSTRAINT ck_type CHECK (type IN ('Etudiant','Professeur','Administrateur'))
);

DROP TABLE IF EXISTS message;
CREATE TABLE message (
	IdMessage INTEGER,
	Ticket INTEGER,
	Etat VARCHAR(10),
	Texte VARCHAR(500),
    constraint pk_Message primary key(IdMessage),
    constraint fk_Ticket foreign key(Ticket)
		references ticket(idTicket)
        on delete cascade,
	constraint ck_etat check (Etat in ('Lu','Recu','Envoye','Pas envoye'))
);

insert into groupe values ('TDA1');
insert into groupe values ('TDA2');
insert into groupe values ('TDA3');
insert into groupe values ('TDA4');

insert into utilisateur values ('thomas', 'oui', 'Etudiant', 'TDA2', 'Lyle', 'Thomas', 'qqch@autrechose.fr');
insert into utilisateur values ('leo', 'yes', 'Etudiant', 'TDA2', 'Laugier', 'Leo', 'qqch@autrechose.fr');
insert into utilisateur values ('jules', 'si', 'Etudiant', 'TDA2', 'Lehodey', 'Jules', 'qqch@autrechose.fr');

insert into ticket values ('1', 'test', 'TDA2');

insert into message values ('1', '1', 'Recu', 'Ceci est un test');