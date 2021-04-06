START TRANSACTION;
INSERT INTO organizer (user_id, organizer_first_name, organizer_last_name, organizer_phone, organizer_email)
        VALUES (2, 'Nick', 'Stewart', 7777777777, 'nickstewart@gmail.com');
        
INSERT INTO users (user_id, username, password_hash, role) VALUES (?, ?, ?, ?);


INSERT INTO tournament (tournament_name, organizer_id, max_participants, is_team, is_double) VALUES (?, ?, ?, ?, ?);
        
SELECT tournament_name, max_participants, is_team, is_double, organizer_first_name, organizer_last_name, organizer_phone, organizer_email FROM tournament
JOIN organizer ON organizer.organizer_id = tournament.organizer_id;

SELECT organizer_id, users.user_id, organizer_first_name, organizer_last_name, organizer_phone, organizer_email
FROM organizer
JOIN users ON organizer.user_id = users.user_id
WHERE (users.user_id = ? AND organizer_id IS NOT NULL);

SELECT organizer_id, user_id, organizer_first_name, organizer_last_name, organizer_phone, organizer_email
FROM organizer
WHERE organizer_id = ?;



SELECT organizer_id, users.user_id, organizer_first_name, organizer_last_name, organizer_phone, organizer_email
FROM organizer
JOIN users ON organizer.user_id = users.user_id
WHERE users.user_id = ?;


SELECT CASE users.user_id WHEN (users.user_id != null AND organizer_id != null)  THEN true
                                                                ELSE false
                                                                END AS is_organizer
FROM users
JOIN organizer ON organizer.user_id = users.user_id;


IF (users.user_id != null AND organizer_id != null)
TRUE
ELSE FALSE;

SELECT 
FROM users 
JOIN organizer ON organizer.user_id = users.user_id
IF((users.user_id != null AND organizer_id != null), true, false);


SELECT * from tournament;
SELECT * from organizer;


ROLLBACK TRANSACTION;

INSERT INTO organizer (organizer_id, user_id, organizer_first_name, organizer_last_name, organizer_phone, organizer_email)
VALUES (DEFAULT, ?, ?, ?, ?, ?);
RETURNING organizer_id, user_id, organizer_first_name, organizer_last_name, organizer_phone, organizer_email;

SELECT organizer_id, user_id, organizer_first_name, organizer_last_name, organizer_phone, organizer_email
FROM organizer WHERE (users.user_id = ? AND organizer_id IS NOT NULL);
