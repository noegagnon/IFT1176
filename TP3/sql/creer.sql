CREATE TABLE Jeu (
	fabricant VARCHAR (20) NOT NULL, 
        nom VARCHAR(50) NOT NULL, 
        cote VARCHAR(5) DEFAULT 'E', 
        consoles VARCHAR(50) NOT NULL,
        CONSTRAINT fabricantNom_pk PRIMARY KEY(fabricant, nom)
        );
