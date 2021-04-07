START TRANSACTION;
INSERT INTO organizer (organizer_first_name, organizer_last_name, organizer_phone, organizer_email)
        VALUES ('Nick', 'Stewart', 7777777777, 'nickstewart@gmail.com');


INSERT INTO tournament (tournament_name, organizer_id, max_participants, is_team, is_double)
        VALUES ('Nick Stewart Open', 1, 100, false, false);
        
SELECT tournament_name, max_participants, is_team, is_double, organizer_first_name, organizer_last_name, organizer_phone, organizer_email FROM tournament
JOIN organizer ON organizer.organizer_id = tournament.organizer_id;

SELECT * from tournament;
SELECT * from organizer;

UPDATE organizer SET organizer_first_name = ?, organizer_last_name = ?, organizer_phone = ?, organizer_email = ?
WHERE organizer_id = ?;

DELETE FROM organizer WHERE organizer_id = ?;


ROLLBACK TRANSACTION;