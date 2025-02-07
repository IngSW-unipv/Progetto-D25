-- CREATE DATABASE testdb
use testdb;
drop table if exists AMMINISTRATORE;
drop table if exists PROFILO; 
drop table if exists PROGRAMMA; 
drop table if exists MENUST;
drop table if exists ALLOGGIOST;
drop table if exists TRASPORTIST;
drop table if exists STAFF;

CREATE TABLE AMMINISTRATORE
        (passwordAD VARCHAR(15),
          username VARCHAR(20),
          location VARCHAR(15),
          PRIMARY KEY (passwordAD, location));

CREATE TABLE STAFF
        ( usernameST VARCHAR(20),
          passwordST VARCHAR(15),
          nameST VARCHAR(15),
		  surnameST VARCHAR(20),
          ageST int,
          CFST CHAR(16),
          accomodationST VARCHAR(20),
          residenceST VARCHAR(20),
          transporationST VARCHAR(20),
          allergiesST TEXT(100),
          medicalIssuesST TEXT(100),
          phonenumberST INT,
          PRIMARY KEY (usernameST, CFST));          

drop table if exists ISCRIZIONE;
drop table if exists ALLOGGIOPA;
drop table if exists MENUPA;
drop table if exists TRASPORTIPA; 
drop table if exists UTENTE;

CREATE TABLE UTENTE
        ( usernameUT VARCHAR(20),
          passwordUT VARCHAR(15),
          nameGE VARCHAR(15),
          surnameGE VARCHAR(20),
          namePA VARCHAR(15),
          surnamePA VARCHAR(20),
          agePA INTEGER,
          CFPA CHAR(16),
          accomodationPA VARCHAR(20),
          residencePA VARCHAR(20),
          transporationPA VARCHAR(20),
          allergiesPA TEXT(100),
          medicalIssuesPA TEXT(100),
          sensitiveInfoPA TEXT(100),
          parentsInfoPA INT,
          PRIMARY KEY (usernameUT, passwordUT, CFPA));
 

 CREATE TABLE ISCRIZIONE
        ( usernameIS VARCHAR(20),
          passwordIS VARCHAR(15),
          nameGE VARCHAR(15),
		  surnameGE VARCHAR(20),
          email VARCHAR(30),
          parentsInfoPA INT,
		  namePA VARCHAR(15),
          surnamePA VARCHAR(20),
          agePA INTEGER,
		  CFPA CHAR(16) PRIMARY KEY,
          genderPA VARCHAR(8),
          accomodationPA VARCHAR(20),
          paese VARCHAR(20),
          indirizzo VARCHAR(50),
          civico VARCHAR(5),
          CAP INT,
          provincia VARCHAR(20),
          località VARCHAR(20),
          dateOfBirthPA date,
          placeOfBirthPA VARCHAR(20),
          particiaptionWeek date, 
          transporationPA VARCHAR(20),
          allergiesPA TEXT(100),
          medicalIssuesPA TEXT(100),
          sensitiveInfoPA TEXT(100),
          camp VARCHAR(20),
          notes TEXT(100),
          FOREIGN KEY (usernameIS, passwordIS, CFPA)
			REFERENCES UTENTE(usernameUT, passwordUT, CFPA) );  
            
drop table if exists PROFILO;         
CREATE TABLE PROFILO
        ( usernameST VARCHAR(20),
          nameST VARCHAR(15),
		  surnameST VARCHAR(20),
          CFST CHAR(16),
		  phonenumberST INT,
          ageST INT,
          camp VARCHAR(20),
          accomodationST VARCHAR(20),
          residenceST VARCHAR(20),
          transporationST VARCHAR(20),
          allergiesST TEXT(100),
          medicalIssuesST TEXT(100),
          campExp TEXT(100),
          FOREIGN KEY (usernameST, CFST)
			REFERENCES STAFF(usernameST, CFST) ); 
 
CREATE TABLE PROGRAMMA
         (datePR DATE,
          timePR TIME,
          location VARCHAR(15),
          groupPR VARCHAR(5),
          userINST VARCHAR(20),
          CFINST CHAR (16),
          FOREIGN KEY (userINST, CFINST) REFERENCES STAFF(usernameST, CFST));

 drop table if exists ALLOGGIOPA;
CREATE TABLE ALLOGGIOPA
         (CFPA CHAR(16),
          usernameAL VARCHAR(20),
          passwordAL VARCHAR(15),
          nameAL VARCHAR(15),
          surnameAL VARCHAR(20),
          location VARCHAR(15),
          camp VARCHAR(15),
          hotel VARCHAR(16),
          PRIMARY KEY (CFPA),
          FOREIGN KEY (usernameAL, passwordAL, CFPA)
			REFERENCES UTENTE(usernameUT, passwordUT,CFPA));

CREATE TABLE ALLOGGIOST
         (CFST CHAR(16) PRIMARY KEY,
          usernameST VARCHAR(20),
          nameAL VARCHAR(15),
          surnameAL VARCHAR(20),
          location VARCHAR(15),
          camp VARCHAR(15),
          hotel VARCHAR(16),
          room INT,
          FOREIGN KEY (usernameST, CFST)
			REFERENCES STAFF(usernameST, CFST));
  
CREATE TABLE TRASPORTIPA
         (CFPA CHAR(16),
          usernamePA VARCHAR(20),
          passwordPA VARCHAR(15),
          dateTR DATE,
          timeTR TIME,
          namePA VARCHAR(15),
          surnamePA VARCHAR(20),
          placeOfDeparture VARCHAR(15),
          destination VARCHAR(16),
          ETA TIME,
          FOREIGN KEY (usernamePA, passwordPA, CFPA)
			REFERENCES UTENTE(usernameUT, passwordUT, CFPA));
            
CREATE TABLE TRASPORTIST
         (CFST CHAR(16) PRIMARY KEY,
          usernameST VARCHAR(20),
          dateTR DATE,
          timeTR TIME,
          nameST VARCHAR(15),
          surnameST VARCHAR(20),
          placeOfDeparture VARCHAR(15),
          destination VARCHAR(16),
          ETA TIME,
          FOREIGN KEY (usernameST, CFST)
			REFERENCES STAFF(usernameST, CFST));

drop table if exists LISTAAUTISTI; 
CREATE TABLE LISTAAUTISTI
        ( nameAU VARCHAR(15),
          surnameAU VARCHAR(20),
          phoneNumberAU INT PRIMARY KEY,
          location VARCHAR(13));

CREATE TABLE MENUPA
         (CFPA CHAR(16) PRIMARY KEY,
          usernamePA VARCHAR(20),
          passwordPA VARCHAR(15),
          datamenu date,
          primo VARCHAR(25),
          secondo VARCHAR(25),
          FOREIGN KEY (usernamePA, passwordPA, CFPA)
			REFERENCES UTENTE(usernameUT, passwordUT, CFPA));      

CREATE TABLE MENUST
         (CFST CHAR(16) PRIMARY KEY,
          usernameST VARCHAR(20),
          datamenu date,
          primo VARCHAR(25),
          secondo VARCHAR(25),
          FOREIGN KEY (usernameST, CFST)
			REFERENCES STAFF(usernameST, CFST));

drop table if exists BACHECA;
CREATE TABLE BACHECA
         (datepost date,
          id int auto_increment PRIMARY KEY,
          image longblob not null);     
 
drop table if exists FEEDBACK;
CREATE TABLE FEEDBACK
        ( id int auto_increment PRIMARY KEY,
          experience INT,
          mostlikedact VARCHAR(50),
          leastlikedact VARCHAR(50),
          menu INT,
          accomodation INT,
          transportation INT,
          comments TEXT(100));