CREATE DATABASE testdb;
use testdb;

drop table if exists AMMINISTRATORE;
drop table if exists STAFF;

drop table if exists UTENTE;
drop table if exists ALLOGGIOST;
drop table if exists ALLOGGIOPA;
drop table if exists ISCRIZIONE;

drop table if exists TRASPORTI;
drop table if exists programma_giornaliero;
drop table if exists programma_alternativo;
drop table if exists PROFILO;  
drop table if exists PERSONA;

CREATE TABLE if not exists PERSONA (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    pwd VARCHAR(255) NOT NULL
);

CREATE TABLE if not exists AMMINISTRATORE (
    persona_id INT PRIMARY KEY,
    location VARCHAR(255) NOT NULL,
    FOREIGN KEY (persona_id) REFERENCES PERSONA(id)
);

CREATE TABLE if not exists STAFF (
    persona_id INT,
	CFST VARCHAR(16),
    nameST VARCHAR(255) NOT NULL,
    surnameST VARCHAR(255) NOT NULL,
    ageST INT NOT NULL, CHECK (AgeST>=18),
    accommodationST VARCHAR(255),
    residenceST VARCHAR(255),
    placeOfDeparture VARCHAR(255),
	destination VARCHAR(255),
	idTR VARCHAR(255),
    transportationST VARCHAR(255),
    allergiesST TEXT ,
    medicalIssuesST TEXT,
    phonenumberST VARCHAR(255) NOT NULL,
    camp VARCHAR(255),
    PRIMARY KEY (persona_id, CFST),
    FOREIGN KEY (persona_id) REFERENCES PERSONA(id)
);         

CREATE TABLE if not exists ISCRIZIONE (
    usernameIS VARCHAR(255) UNIQUE NOT NULL,
    passwordIS VARCHAR(255) NOT NULL,
    nameGE VARCHAR(255) NOT NULL,
    surnameGE VARCHAR(255) NOT NULL,
    parentsInfoPA VARCHAR(255) NOT NULL,
    namePA VARCHAR(255) NOT NULL,
    surnamePA VARCHAR(255) NOT NULL,
    agePA INT NOT NULL, CHECK (AgePA<18),
    CFPA CHAR(16) PRIMARY KEY,
    genderPA VARCHAR(255),
    paese VARCHAR(255),
    indirizzo VARCHAR(255),
    civico VARCHAR(255),
    CAP INT,
    provincia VARCHAR(255),
    località VARCHAR(255),
    dateOfBirthPA DATE,
    placeOfBirthPA VARCHAR(255),
    participationWeek DATE NOT NULL, 
    transportationPA VARCHAR(255) NOT NULL,
    pointOfDeparture VARCHAR(255) NOT NULL,
    allergiesPA TEXT,
    medicalIssuesPA TEXT,
    sensitiveInfoPA TEXT,
    camp VARCHAR(255),
    notes TEXT,
    accettatoRegolamento boolean,
    accettatoConditions boolean,
    accettatoPrivacy boolean
);


CREATE TABLE if not exists UTENTE (
    persona_id INT,
    namePA VARCHAR(255) NOT NULL,
    surnamePA VARCHAR(255) NOT NULL,
    agePA INT NOT NULL, CHECK (AgePA<18),
    CFPA CHAR(16),
    accommodationPA VARCHAR(255),
	placeOfDeparture VARCHAR(255),
	destination VARCHAR(255),
	idTR VARCHAR(255),
    transportationPA VARCHAR(255),
    allergiesPA TEXT,
    medicalIssuesPA TEXT,
    sensitiveInfoPA TEXT,
    parentsInfoPA VARCHAR(255) NOT NULL,
	camp VARCHAR(255),
    PRIMARY KEY (persona_id, CFPA),
    FOREIGN KEY (persona_id) REFERENCES PERSONA(id),  -- Relazione con PERSONA per username e password
    FOREIGN KEY (CFPA) REFERENCES ISCRIZIONE(CFPA)  -- Relazione con ISCRIZIONE per ottenere i dati
);


-- drop table if exists TRASPORTI; 
CREATE TABLE if not exists TRASPORTI
	 (idTR  VARCHAR(255) PRIMARY KEY,  
	  dateTR DATE,
	  timeTR TIME,
	  placeOfDeparture VARCHAR(255),
	  modalitaTR VARCHAR(255),
	  ETA TIME,
	  persona_id INT,
      destination VARCHAR(255), 
	  FOREIGN KEY (persona_id) REFERENCES PERSONA(id)
);

-- drop table if exists ALLOGGIOPA;
CREATE TABLE if not exists ALLOGGIOPA (
	id_alloggio INT AUTO_INCREMENT PRIMARY KEY,
	location VARCHAR(255),
	camp VARCHAR(255),
	hotel VARCHAR(255),
	room INT,
	CFPA CHAR(16),
 	persona_id INT,
 	FOREIGN KEY (persona_id) REFERENCES PERSONA(id),
    FOREIGN KEY (CFPA) REFERENCES ISCRIZIONE(CFPA)
);

-- drop table if exists ALLOGGIOST;
CREATE TABLE if not exists ALLOGGIOST (
	id_alloggio INT AUTO_INCREMENT PRIMARY KEY,
	location VARCHAR(255),
	camp VARCHAR(255),
	hotel VARCHAR(255),
	room INT,
    CFST CHAR(16),
 	persona_id INT,
 	FOREIGN KEY (persona_id, CFST) REFERENCES STAFF(persona_id, CFST)
);

drop table if exists AUTISTI;
CREATE TABLE if not exists AUTISTI ( 
	nameAU VARCHAR(255),
	surnameAU VARCHAR(255),
	phoneNumberAU VARCHAR(255) PRIMARY KEY,
	location VARCHAR(255)
);

-- drop table if exists programma_giornaliero;
CREATE TABLE if not exists programma_giornaliero (
    id INT AUTO_INCREMENT PRIMARY KEY,
    datap DATE NOT NULL,
    nome VARCHAR(255),
    descrizione TEXT,
    orario VARCHAR(255),
    luogo VARCHAR(255),
 	persona_id INT,
 	FOREIGN KEY (persona_id) REFERENCES PERSONA(id)	
);

-- drop table if exists programma_alternativo;
CREATE TABLE if not exists programma_alternativo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    datap DATE NOT NULL,
    nome VARCHAR(255),
    descrizione TEXT,
    orario VARCHAR(255),
    luogo VARCHAR(255),
 	persona_id INT,
 	FOREIGN KEY (persona_id) REFERENCES PERSONA(id)	
);

-- drop table if exists PROFILO;         
CREATE TABLE if not exists PROFILO (
    persona_id INT,
    CFST CHAR(16),
	nameST VARCHAR(255) NOT NULL,
    surnameST VARCHAR(255) NOT NULL,
    ageST INT NOT NULL, 
    accomodationST VARCHAR(255),
    residenceST VARCHAR(255),
    placeOfDeparture VARCHAR(255),
	destination VARCHAR(255),
	idTR VARCHAR(255),
    transportationST VARCHAR(255),
    allergiesST TEXT,
    medicalIssuesST TEXT,
    phonenumberST VARCHAR(255) NOT NULL,
	camp VARCHAR(255),
    profilePicturePath VARCHAR(255),
	campExp TEXT,
    FOREIGN KEY (persona_id) REFERENCES PERSONA(id)
-- 	FOREIGN KEY (CFST) REFERENCES STAFF(CFST)
); 

-- MENU
ALTER TABLE STAFF ADD COLUMN pastoPranzo VARCHAR(255); 
ALTER TABLE STAFF ADD COLUMN pastoCena VARCHAR(255);
ALTER TABLE UTENTE ADD COLUMN pastoPranzo VARCHAR(255); 
ALTER TABLE UTENTE ADD COLUMN pastoCena VARCHAR(255);

drop table if exists MENU;
drop table if exists PASTI;
CREATE TABLE if not exists PASTI (
	pasti VARCHAR(255) PRIMARY KEY
);

CREATE TABLE if not exists MENU (
	pasti VARCHAR(255) PRIMARY KEY,
	datamenu date,
	primo VARCHAR(255),
	secondo VARCHAR(255),
	colazione VARCHAR(255),
    FOREIGN KEY (pasti) REFERENCES PASTI(pasti)
);   

drop table if exists BACHECA;
CREATE TABLE if not exists BACHECA (
	image longblob not null,
    file_path VARCHAR(255) PRIMARY KEY,
    dateTaken date,
    album VARCHAR(255)
);     
 
drop table if exists FEEDBACK;
CREATE TABLE  if not exists FEEDBACK ( 
	id int auto_increment PRIMARY KEY,
	experience INT,
	mostlikedact VARCHAR(255),
	leastlikedact VARCHAR(255),
	menu INT,
	accomodation INT,
	transportation INT,
	comments TEXT
);
