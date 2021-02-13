-- Write queries to return the following:
-- The following changes are applied to the "dvdstore" database.**

-- 1. Add actors, Hampton Avenue, and Lisa Byway to the actor table.
SELECT * FROM actor
ORDER BY last_name;
INSERT INTO actor (first_name, last_name) VALUES ('HAMPTON', 'AVENUE');
INSERT INTO actor (first_name, last_name) VALUES ('LISA', 'BYWAY');

-- 2. Add "Euclidean PI", "The epic story of Euclid as a pizza delivery boy in
-- ancient Greece", to the film table. The movie was released in 2008 in English.
-- Since its an epic, the run length is 3hrs and 18mins (198). There are no special
-- features, the film speaks for itself, and doesn't need any gimmicks.

SELECT * FROM film;
INSERT INTO film (title, description, release_year, language_id, length) 
VALUES ('EUCLIDEAN PI', 'The epic story of Euclid as a pizza delivery boy in ancient Greece', 2008, 1, 198);

-- 3. Hampton Avenue plays Euclid, while Lisa Byway plays his slightly
-- overprotective mother, in the film, "Euclidean PI". Add them to the film.

INSERT INTO film_actor(film_id, actor_id)
VALUES (1001, 202);

INSERT INTO film_actor(film_id, actor_id)
VALUES (1001, 201);

SELECT * FROM actor
WHERE actor.first_name = 'HAMPTON';

-- 4. Add Mathmagical to the category table.
SELECT * FROM category;
INSERT INTO category (name) VALUES ('Mathmagical');

-- 5. Assign the Mathmagical category to the following films, "Euclidean PI",
-- "EGG IGBY", "KARATE MOON", "RANDOM GO", and "YOUNG LANGUAGE"
SELECT * FROM film_category;
WHERE category.name = 'Mathmagical';

SELECT * FROM film
JOIN film_category ON film_category.film_id = film.film_id
JOIN category ON category.category_id = film_category.category_id
WHERE film.title = 'EGG IGBY';
INSERT INTO category (name) VALUES ('Mathmagical');

SELECT * FROM film
JOIN film_category ON film_category.film_id = film.film_id
JOIN category ON category.category_id = film_category.category_id
WHERE film.title = 'EUCLIDEAN PI';

INSERT INTO film_category(film_id, category_id) VALUES (1001, 17);

SELECT * FROM film
JOIN film_category ON film_category.film_id = film.film_id
JOIN category ON category.category_id = film_category.category_id
WHERE film.title = 'EUCLIDEAN PI';


INSERT INTO film_category(film_id, category_id) VALUES(274, 17);

UPDATE film_category SET category_id = 17
WHERE film_category.film_id = 494;

UPDATE film_category SET category_id = 17
WHERE film_id = 714;

UPDATE film_category SET category_id = 17
WHERE film_id = 996;

-- 6. Mathmagical films always have a "G" rating, adjust all Mathmagical films
-- accordingly.
-- (5 rows affected)

UPDATE film SET rating = 'G'
WHERE film.film_id IN (SELECT film_id FROM film_category WHERE category_id = 17);

-- 7. Add a copy of "Euclidean PI" to all the stores.
SELECT * FROM store;

INSERT INTO inventory (film_id, store_id)
VALUES (1001, 1);

INSERT INTO inventory (film_id, store_id)
VALUES (1001, 2);;

-- 8. The Feds have stepped in and have impounded all copies of the pirated film,
-- "Euclidean PI". The film has been seized from all stores, and needs to be
-- deleted from the film table. Delete "Euclidean PI" from the film table.
-- (Did it succeed? Why?)
-- <YOUR ANSWER HERE>

-- the delete command did not work because the film ID is referenced on the film_actor table.
DELETE FROM film
WHERE film.title = 'EUCLIDEAN PI';



-- 9. Delete Mathmagical from the category table.
-- (Did it succeed? Why?)
-- <YOUR ANSWER HERE>

-- the delete command did not work because the category name is used as a foreign key in the film_category table.

DELETE FROM category
WHERE category.name = 'Mathmagical';



-- 10. Delete all links to Mathmagical in the film_category tale.
-- (Did it succeed? Why?)
-- <YOUR ANSWER HERE>

-- The delete command worked! I was able to delete the titles links to Mathmagical because I started at the relational midpoint.
-- The film names still exist and the category still exists, but they are no longer linked to each other.
DELETE FROM film_category
WHERE category_id = 17;

-- 11. Retry deleting Mathmagical from the category table, followed by retrying
-- to delete "Euclidean PI".
-- (Did either deletes succeed? Why?)
-- <YOUR ANSWER HERE>

-- The mathmagical category delete worked because there was now no longer a link between it and another table.
-- However, the Euclidean Pi delete didn't work because it is still referenced in the film_actor table.

DELETE FROM category 
WHERE category.name = 'Mathmagical';

DELETE FROM film
WHERE film.title = 'EUCLIDEAN PI';