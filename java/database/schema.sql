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
	user_first_name varchar(50),
	user_last_name varchar(50),
	user_nickname varchar(50),
	user_email varchar(150),
	user_phone varchar(60),
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	user_image_url varchar(2000),
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (user_first_name, user_last_name, user_nickname, user_email, user_phone, username, password_hash, role) VALUES ('Nick', 'Stewart', 'nickstewart', 'brcktproject@gmail.com', 5555555555, 'nickstewart', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER');
INSERT INTO users (user_first_name, user_last_name, user_nickname, user_email, user_phone, username, password_hash, role) VALUES ('Ben', 'Baker', 'benbaker', 'brcktproject@gmail.com', 4444444444, 'benbaker', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_ADMIN');
INSERT INTO users (user_first_name, user_last_name, user_nickname, user_email, user_phone, username, password_hash, role) VALUES ('JW', 'Kriewall', 'jwkriewall', 'brcktproject@gmail.com', 3333333333, 'jwkriewall', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER');
INSERT INTO users (user_first_name, user_last_name, user_nickname, user_email, user_phone, username, password_hash, role) VALUES ('TestUser6', 'TU6', 'tester', 'tester@gmail.com', 6666666666, 'testuser', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER');


CREATE TABLE organizer (
        organizer_id serial primary key NOT NULL,
        user_id int NOT NULL UNIQUE,
        organizer_first_name varchar(50) NOT NULL,
        organizer_last_name varchar(50) NOT NULL,
        organizer_phone varchar(150),
        organizer_email varchar(100) NOT NULL,
        
        constraint fk_user_id_organizer foreign key (user_id) references users (user_id)
);

INSERT INTO organizer (user_id, organizer_first_name, organizer_last_name, organizer_phone, organizer_email) VALUES (2, 'Admin2', 'Admin2', 5555555555, 'admin@gmail.com');
INSERT INTO organizer (user_id, organizer_first_name, organizer_last_name, organizer_phone, organizer_email) VALUES (3, 'Admin3', 'Admin3', 5555555555, 'nick@gmail.com');
INSERT INTO organizer (user_id, organizer_first_name, organizer_last_name, organizer_phone, organizer_email) VALUES (4, 'Admin4', 'Admin4', 4444444444, 'ben@gmail.com');
INSERT INTO organizer (user_id, organizer_first_name, organizer_last_name, organizer_phone, organizer_email) VALUES (5, 'Admin5', 'Admin5', 3333333333, 'jw@gmail.com');

CREATE TABLE tournament (
        id serial primary key NOT NULL,
        tournament_name varchar(300) NOT NULL,
        organizer_id int NOT NULL, 
        max_participants int,
        is_team boolean,
        is_double boolean,
        start_date date,
        end_date date,
        ended boolean,  
        constraint fk_organizer_id_tournament foreign key (organizer_id) references organizer (organizer_id)

);

INSERT INTO tournament (tournament_name, organizer_id, max_participants, is_team, is_double, start_date, end_date, ended) VALUES ('Soccer Tournament', 1, 10, true, false, '2021-04-27', '2021-04-30', false);
INSERT INTO tournament (tournament_name, organizer_id, max_participants, is_team, is_double, start_date, end_date, ended) VALUES ('Super Smash', 3, 8, false, false, '2021-04-25', '2021-05-30', false);
INSERT INTO tournament (tournament_name, organizer_id, max_participants, is_team, is_double, start_date, end_date, ended) VALUES ('Boring Tourney', 3, 16, true, true, '2021-03-25', '2021-03-30', true);
INSERT INTO tournament (tournament_name, organizer_id, max_participants, is_team, is_double, start_date, end_date, ended) VALUES ('Tournament of DOOM', 1, 20, false, false, '2022-04-25', '2022-04-30', false);
INSERT INTO tournament (tournament_name, organizer_id, max_participants, is_team, is_double, start_date, end_date, ended) VALUES ('meh', 2, 10, false, false, '2014-04-25', '2015-04-30', true);
INSERT INTO tournament (tournament_name, organizer_id, max_participants, is_team, is_double, start_date, end_date, ended) VALUES ('Java Blue? Java Best!', 1, 10, false, true, '2021-04-22', '2021-04-28', false);
INSERT INTO tournament (tournament_name, organizer_id, max_participants, is_team, is_double, start_date, end_date, ended) VALUES ('Space Adventure', 3, 100, true, false, '2021-04-25', '2021-04-30', false);
INSERT INTO tournament (tournament_name, organizer_id, max_participants, is_team, is_double, start_date, end_date, ended) VALUES ('Matt''s Tourney', 2, 4, true, false, '2019-04-25', '2019-04-30', false);
INSERT INTO tournament (tournament_name, organizer_id, max_participants, is_team, is_double, start_date, end_date, ended) VALUES ('Losers Only', 2, 13, false, true, '2021-06-25', '2021-06-30', false);
INSERT INTO tournament (tournament_name, organizer_id, max_participants, is_team, is_double, start_date, end_date, ended) VALUES ('Dad Jokes Required', 2, 42, false, false, '2021-05-01', '2021-05-16', false);

CREATE TABLE tournament_user (
        tournament_id int, 
        user_id int,
        user_seeding int,
        user_nickname varchar(150),
        user_email varchar(150),
        notify boolean,
        
        constraint fk_tournament_id_tournament_user foreign key (tournament_id) references tournament (id),
        constraint fk_user_id_tournament_user foreign key (user_id) references users (user_id)
        );


INSERT INTO tournament_user (tournament_id, user_id, user_seeding, user_nickname, user_email, notify) VALUES (1, 2, 0, 'Nickname', '', false);
INSERT INTO tournament_user (tournament_id, user_id, user_seeding, user_nickname, user_email, notify) VALUES (2, 2, 0, '', '', false);
INSERT INTO tournament_user (tournament_id, user_id, user_seeding, user_nickname, user_email, notify) VALUES (3, 2, 0, '', '', false);
INSERT INTO tournament_user (tournament_id, user_id, user_seeding, user_nickname, user_email, notify) VALUES (1, 2, 0, 'THE BEAST', '', false);
INSERT INTO tournament_user (tournament_id, user_id, user_seeding, user_nickname, user_email, notify) VALUES (1, 1, 0, 'BIG TESTER', '', false);
INSERT INTO tournament_user (tournament_id, user_id, user_seeding, user_nickname, user_email, notify) VALUES (1, 5, 0, 'VUE.JS ROCKS', '', false);
INSERT INTO tournament_user (tournament_id, user_id, user_seeding, user_nickname, user_email, notify) VALUES (1, 6, 0, 'TE Grad', '', false);
INSERT INTO tournament_user (tournament_id, user_id, user_seeding, user_nickname, user_email, notify) VALUES (1, 4, 0, '8', '', false);
INSERT INTO tournament_user (tournament_id, user_id, user_seeding, user_nickname, user_email, notify) VALUES (2, 3, 0, '', '', false);
INSERT INTO tournament_user (tournament_id, user_id, user_seeding, user_nickname, user_email, notify) VALUES (8, 2, 0, 'You cant handle the truthy', '', false);
INSERT INTO tournament_user (tournament_id, user_id, user_seeding, user_nickname, user_email, notify) VALUES (8, 5, 0, 'Bad Chad', '', false);
INSERT INTO tournament_user (tournament_id, user_id, user_seeding, user_nickname, user_email, notify) VALUES (8, 4, 0, 'Capn Jack', '', false);
INSERT INTO tournament_user (tournament_id, user_id, user_seeding, user_nickname, user_email, notify) VALUES (8, 3, 0, 'Trix Rabbit', '', false);
INSERT INTO tournament_user (tournament_id, user_id, user_seeding, user_nickname, user_email, notify) VALUES (3, 3, 0, '', '', false);
INSERT INTO tournament_user (tournament_id, user_id, user_seeding, user_nickname, user_email, notify) VALUES (1, 4, 0, '', 'brcktproject@gmail.com', false);
INSERT INTO tournament_user (tournament_id, user_id, user_seeding, user_nickname, user_email, notify) VALUES (1, 3, 0, 'Fat Momma', 'brcktproject@gmail.com', false);
INSERT INTO tournament_user (tournament_id, user_id, user_seeding, user_nickname, user_email, notify) VALUES (1, 5, 0, '', 'brcktproject@gmail.com', false);



COMMIT TRANSACTION;