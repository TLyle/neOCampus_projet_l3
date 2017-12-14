CREATE TABLE ticket (
	IdTicket integer,
	Titre VARCHAR(100),
	Requete VARCHAR(500),
	Groupe VARCHAR(10),
    constraint pk_Ticket primary key(idTicket),
    constraint fk_Groupe foreign key(Groupe)
		references groupe(Nom)
        on delete cascade
);


CREATE TABLE groupe (
	Nom VARCHAR(10),
	Users SET('100'),
	Tickets integer,
    constraint pk_Groupe primary key(Nom),
    constraint fk_ticket foreign key(Tickets)
		references ticket(idTicket)
        on delete cascade
);

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

CREATE TABLE message (
	IdMessage integer,
	Ticket integer,
	Etat VARCHAR(10),
	Texte VARCHAR(500),
    constraint pk_Message primary key(IdMessage),
    constraint fk_Ticket foreign key(Ticket)
		references ticket(idTicket)
        on delete cascade,
	constraint ck_etat check (Etat in ('Lu','Recu','Envoye','Pas envoye'))
);
