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

INSERT INTO users (user_first_name, user_last_name, user_nickname, user_email, user_phone, username, password_hash, role, user_image_url) VALUES ('Matt', 'Egads', 'DadJokes4Days', 'brcktproject@gmail.com', 5555555555, 'user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER',
'https://firebasestorage.googleapis.com/v0/b/brckt-125af.appspot.com/o/matt.jpeg?alt=media&token=fa3d9818-056d-491b-b959-2f39d74984b9');
INSERT INTO users (user_first_name, user_last_name, user_nickname, user_email, user_phone, username, password_hash, role, user_image_url) VALUES ('Brian', 'Slayer', 'Java Slaya', 'brcktproject@gmail.com', 5555555555, 'admin', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER',
'https://firebasestorage.googleapis.com/v0/b/brckt-125af.appspot.com/o/brian.jpeg?alt=media&token=ad2dfeaf-effa-4284-b9a1-c42744cdae9f');
INSERT INTO users (user_first_name, user_last_name, user_nickname, user_email, user_phone, username, password_hash, role) VALUES ('Nick', 'Stewart', 'nickstewart', 'brcktproject@gmail.com', 5555555555, 'nickstewart', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER');
INSERT INTO users (user_first_name, user_last_name, user_nickname, user_email, user_phone, username, password_hash, role, user_image_url) VALUES ('Ben', 'Baker', 'benbaker', 'brcktproject@gmail.com', 4444444444, 'benbaker', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_ADMIN',
'https://firebasestorage.googleapis.com/v0/b/brckt-125af.appspot.com/o/ben.jpeg?alt=media&token=aa989f6e-88a8-426e-917e-ba84d7afa676');
INSERT INTO users (user_first_name, user_last_name, user_nickname, user_email, user_phone, username, password_hash, role) VALUES ('JW', 'Kriewall', 'jwkriewall', 'brcktproject@gmail.com', 3333333333, 'jwkriewall', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER');
INSERT INTO users (user_first_name, user_last_name, user_nickname, user_email, user_phone, username, password_hash, role, user_image_url) VALUES ('Alex', 'Danger', 'Blue Eyes', 'brcktproject@gmail.com', 4444444444, 'alexdanger', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_ADMIN', 
'https://firebasestorage.googleapis.com/v0/b/brckt-125af.appspot.com/o/alexandradidarrio.jpg?alt=media&token=49b5138c-36d7-4d0c-b338-604857b9e86c');
INSERT INTO users (user_first_name, user_last_name, user_nickname, user_email, user_phone, username, password_hash, role, user_image_url) VALUES ('Brad', 'Peaks', 'PitStop McGraw', 'brcktproject@gmail.com', 4444444444, 'bradpeaks', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_ADMIN', 
'https://firebasestorage.googleapis.com/v0/b/brckt-125af.appspot.com/o/bradPitt.jpeg?alt=media&token=d2898e96-4ae7-4d31-a147-e09278fb0ffc');
INSERT INTO users (user_first_name, user_last_name, user_nickname, user_email, user_phone, username, password_hash, role, user_image_url) VALUES ('Some', 'Girl', 'Salsa Spice', 'brcktproject@gmail.com', 4444444444, 'somegirl', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_ADMIN', 
'https://firebasestorage.googleapis.com/v0/b/brckt-125af.appspot.com/o/dunno.jpeg?alt=media&token=a6710f95-ac90-497d-8c08-790f83fb5d8e');
INSERT INTO users (user_first_name, user_last_name, user_nickname, user_email, user_phone, username, password_hash, role, user_image_url) VALUES ('Harry', 'Otter', 'Voldemort', 'brcktproject@gmail.com', 4444444444, 'harryotter', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_ADMIN', 
'https://firebasestorage.googleapis.com/v0/b/brckt-125af.appspot.com/o/harrypotter.jpeg?alt=media&token=4d28dab7-644b-4493-bc5b-435ab49b75f1');
INSERT INTO users (user_first_name, user_last_name, user_nickname, user_email, user_phone, username, password_hash, role, user_image_url) VALUES ('Jenny', 'Stantion', 'Your Best Friend', 'brcktproject@gmail.com', 4444444444, 'jennystantion', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_ADMIN', 
'https://firebasestorage.googleapis.com/v0/b/brckt-125af.appspot.com/o/jenniferaniston.jpeg?alt=media&token=ebbe5693-62ff-48c9-aa78-462a9218d596');
INSERT INTO users (user_first_name, user_last_name, user_nickname, user_email, user_phone, username, password_hash, role, user_image_url) VALUES ('Justin', 'Time', 'TimbaWolf', 'brcktproject@gmail.com', 4444444444, 'justintime', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_ADMIN', 
'https://firebasestorage.googleapis.com/v0/b/brckt-125af.appspot.com/o/justinTimberlake.jpeg?alt=media&token=a5c83e72-345a-43dc-945f-e9e08140af9a');
INSERT INTO users (user_first_name, user_last_name, user_nickname, user_email, user_phone, username, password_hash, role, user_image_url) VALUES ('Mila', 'Racoon', 'That 70''s Girl', 'brcktproject@gmail.com', 4444444444, 'milaracoon', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_ADMIN', 
'https://firebasestorage.googleapis.com/v0/b/brckt-125af.appspot.com/o/mila.jpeg?alt=media&token=80cc2edd-7464-4e72-a0f7-044cc29d7cc2');
INSERT INTO users (user_first_name, user_last_name, user_nickname, user_email, user_phone, username, password_hash, role, user_image_url) VALUES ('William', 'Prince', 'Columbus Prince', 'brcktproject@gmail.com', 4444444444, 'williamprince', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_ADMIN', 
'https://firebasestorage.googleapis.com/v0/b/brckt-125af.appspot.com/o/willSmith.jpeg?alt=media&token=c1e5d39d-d0ca-46b8-b176-68a89fba2eb3');
INSERT INTO users (user_first_name, user_last_name, user_nickname, user_email, user_phone, username, password_hash, role) VALUES ('Kaushal', 'Patel', '', 'brcktproject@gmail.com', 4444444444, 'kaushalpatel', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_ADMIN');


CREATE TABLE organizer (
        organizer_id serial primary key NOT NULL,
        user_id int NOT NULL UNIQUE,
        organizer_first_name varchar(50) NOT NULL,
        organizer_last_name varchar(50) NOT NULL,
        organizer_phone varchar(150),
        organizer_email varchar(100) NOT NULL,
        
        constraint fk_user_id_organizer foreign key (user_id) references users (user_id)
);

INSERT INTO organizer (user_id, organizer_first_name, organizer_last_name, organizer_phone, organizer_email) VALUES (2, 'Tech', 'Elevator', 5555555555, 'brcktproject@gmail.com');
INSERT INTO organizer (user_id, organizer_first_name, organizer_last_name, organizer_phone, organizer_email) VALUES (3, 'Nick', 'Stewart', 5555555555, 'nick@gmail.com');
INSERT INTO organizer (user_id, organizer_first_name, organizer_last_name, organizer_phone, organizer_email) VALUES (4, 'Ben', 'Baker', 4444444444, 'ben@gmail.com');
INSERT INTO organizer (user_id, organizer_first_name, organizer_last_name, organizer_phone, organizer_email) VALUES (5, 'John', 'Kriewell', 3333333333, 'jw@gmail.com');
INSERT INTO organizer (user_id, organizer_first_name, organizer_last_name, organizer_phone, organizer_email) VALUES (14, 'Kaushal', 'Patel', 3333333333, 'kaushal@gmail.com');

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
INSERT INTO tournament (tournament_name, organizer_id, max_participants, is_team, is_double, start_date, end_date, ended) VALUES ('Tournament of DOOM', 1, 16, false, false, '2022-04-25', '2022-04-30', false);
INSERT INTO tournament (tournament_name, organizer_id, max_participants, is_team, is_double, start_date, end_date, ended) VALUES ('meh', 2, 10, false, false, '2014-04-25', '2015-04-30', true);
INSERT INTO tournament (tournament_name, organizer_id, max_participants, is_team, is_double, start_date, end_date, ended) VALUES ('Java Blue? Java Best!', 1, 14, false, true, '2021-04-22', '2021-04-28', false);
INSERT INTO tournament (tournament_name, organizer_id, max_participants, is_team, is_double, start_date, end_date, ended) VALUES ('Space Adventure', 3, 6, true, false, '2021-04-25', '2021-04-30', false);
INSERT INTO tournament (tournament_name, organizer_id, max_participants, is_team, is_double, start_date, end_date, ended) VALUES ('Matt''s Tourney', 2, 4, true, false, '2019-04-25', '2019-04-30', false);
INSERT INTO tournament (tournament_name, organizer_id, max_participants, is_team, is_double, start_date, end_date, ended) VALUES ('Losers Only', 2, 12, false, true, '2021-06-25', '2021-06-30', false);
INSERT INTO tournament (tournament_name, organizer_id, max_participants, is_team, is_double, start_date, end_date, ended) VALUES ('Dad Jokes Required', 2, 16, false, false, '2021-05-01', '2021-05-16', false);

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

--tournament 1--

INSERT INTO tournament_user (tournament_id, user_id, user_seeding, user_nickname, user_email, notify) VALUES (1, 1, 2, 'DadJokes4Days', 'brcktproject@gmail.com', false);
INSERT INTO tournament_user (tournament_id, user_id, user_seeding, user_nickname, user_email, notify) VALUES (1, 2, 3, 'Java Slaya', 'brcktproject@gmail.com', false);
INSERT INTO tournament_user (tournament_id, user_id, user_seeding, user_nickname, user_email, notify) VALUES (1, 3, 1, 'Big Test', 'brcktproject@gmail.com', false);
INSERT INTO tournament_user (tournament_id, user_id, user_seeding, user_nickname, user_email, notify) VALUES (1, 4, 7, 'N1ghtBreak3r', 'brcktproject@gmail.com', false);
INSERT INTO tournament_user (tournament_id, user_id, user_seeding, user_nickname, user_email, notify) VALUES (1, 5, 5, 'VUE.JS ROCKS', 'brcktproject@gmail.com', false);
INSERT INTO tournament_user (tournament_id, user_id, user_seeding, user_nickname, user_email, notify) VALUES (1, 6, 9, 'Solid 10', 'brcktproject@gmail.com', false);
INSERT INTO tournament_user (tournament_id, user_id, user_seeding, user_nickname, user_email, notify) VALUES (1, 7, 4, 'Pitstop McGraw', 'brcktproject@gmail.com', false);
INSERT INTO tournament_user (tournament_id, user_id, user_seeding, user_nickname, user_email, notify) VALUES (1, 8, 6, 'Salsa Spice', 'brcktproject@gmail.com', false);
INSERT INTO tournament_user (tournament_id, user_id, user_seeding, user_nickname, user_email, notify) VALUES (1, 9, 10, 'Voldemort', 'brcktproject@gmail.com', true);
INSERT INTO tournament_user (tournament_id, user_id, user_seeding, user_nickname, user_email, notify) VALUES (1, 10, 0, 'Best Friend', 'brcktproject@gmail.com', true);
INSERT INTO tournament_user (tournament_id, user_id, user_seeding, user_nickname, user_email, notify) VALUES (2, 11, 0, 'TimbaWolf', 'brcktproject@gmail.com', true);
INSERT INTO tournament_user (tournament_id, user_id, user_seeding, user_nickname, user_email, notify) VALUES (2, 12, 0, '70''s Girl', 'brcktproject@gmail.com', false);
--other tournaments--
INSERT INTO tournament_user (tournament_id, user_id, user_seeding, user_nickname, user_email, notify) VALUES (2, 2, 0, '', '', false);
INSERT INTO tournament_user (tournament_id, user_id, user_seeding, user_nickname, user_email, notify) VALUES (2, 3, 0, '', '', false);
INSERT INTO tournament_user (tournament_id, user_id, user_seeding, user_nickname, user_email, notify) VALUES (8, 2, 0, 'You cant handle the truthy', '', false);
INSERT INTO tournament_user (tournament_id, user_id, user_seeding, user_nickname, user_email, notify) VALUES (8, 5, 0, 'Bad Chad', '', false);
INSERT INTO tournament_user (tournament_id, user_id, user_seeding, user_nickname, user_email, notify) VALUES (8, 4, 0, 'Capn Jack', '', false);
INSERT INTO tournament_user (tournament_id, user_id, user_seeding, user_nickname, user_email, notify) VALUES (8, 3, 0, 'Trix Rabbit', '', false);
INSERT INTO tournament_user (tournament_id, user_id, user_seeding, user_nickname, user_email, notify) VALUES (3, 3, 0, '', '', false);




COMMIT TRANSACTION;