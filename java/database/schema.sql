BEGIN TRANSACTION;

DROP TABLE IF EXISTS tournament_user;
DROP TABLE IF EXISTS tournament;
DROP TABLE IF EXISTS organizer;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS seq_user_id;



CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;


CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	user_first_name,
	user_last_name,
	user_email,
	user_phone,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');





CREATE TABLE organizer (
        organizer_id serial primary key NOT NULL,
        user_id int NOT NULL UNIQUE,
        organizer_first_name varchar(50) NOT NULL,
        organizer_last_name varchar(50) NOT NULL,
        organizer_phone varchar(150),
        organizer_email varchar(100) NOT NULL,
        
        constraint fk_user_id_organizer foreign key (user_id) references users (user_id)
);

CREATE TABLE tournament (
        id serial primary key NOT NULL,
        tournament_name varchar(300) NOT NULL,
        organizer_id int NOT NULL, 
        max_participants int,
        is_team boolean,
        is_double boolean,
        start_date date,
        end_date date,  
        constraint fk_organizer_id_tournament foreign key (organizer_id) references organizer (organizer_id)

);


CREATE TABLE tournament_user (
        tournament_id int, 
        user_id int,
        user_seeding int,
        user_nickname varchar(150),
        
        constraint fk_tournament_id_tournament_user foreign key (tournament_id) references tournament (id),
        constraint fk_user_id_tournament_user foreign key (user_id) references users (user_id)
        );


COMMIT TRANSACTION;
SELECT organizer_id, user_id, organizer_first_name, organizer_last_name, organizer_phone, organizer_email FROM organizer;
