START TRANSACTION;
INSERT INTO organizer (user_id, organizer_first_name, organizer_last_name, organizer_phone, organizer_email)
        VALUES (1, 'Nick', 'Stewart', 7777777777, 'nickstewart@gmail.com');


INSERT INTO tournament (tournament_name, organizer_id, max_participants, is_team, is_double, start_date, end_date)
        VALUES (?, ?, ?, ?, ?, ?, ?);
        
        
INSERT INTO tournament_user (tournament_id, user_id, user_seeding, user_nickname)
        VALUES (?, ?, ?, ?);        
     
INSERT INTO users (user_first_name, user_last_name, user_nickname, user_email, user_phone, username, password_hash, role)
       VALUES(?,?,?,?,?,?,?,'ROLE_USER');
        
UPDATE users 
SET  user_first_name = ?, user_last_name = ?, user_nickname = ?, user_email = ?, user_phone = ?, username = ?, password_hash = ?
WHERE user_id = ?;      
        
SELECT tournament_name, max_participants, is_team, is_double, organizer_first_name, organizer_last_name, organizer_phone, organizer_email FROM tournament
JOIN organizer ON organizer.organizer_id = tournament.organizer_id;

SELECT * from tournament;
SELECT * from organizer;

UPDATE organizer SET organizer_first_name = ?, organizer_last_name = ?, organizer_phone = ?, organizer_email = ?
WHERE organizer_id = ?;

DELETE FROM organizer WHERE organizer_id = ?;

SELECT tournament_name, max_participants, is_team, is_double, organizer_first_name, organizer_last_name, organizer_phone, organizer_email FROM tournament
JOIN tournament_user ON tournament.id = tournament_user.tournament_id
JOIN users ON tournament_user.user_id = users.user_id
JOIN organizer ON organizer.organizer_id = tournament.organizer_id
WHERE users.user_id = ?;


SELECT user_id FROM tournament_user WHERE tournament_id = ? AND user_id = ?;

INSERT INTO tournament_user VALUES (?, ?, 1, ?)
JOIN;

SELECT user_id FROM tournament_user WHERE tournament_id = ? AND user_id = ?;

WHERE tournament_id = ? AND user_id = ?;



SELECT * from USERS;

ROLLBACK TRANSACTION;