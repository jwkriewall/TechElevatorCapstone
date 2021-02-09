-- ORDERING RESULTS

-- Populations of all countries in descending order
SELECT name, population
FROM country
ORDER BY population desc;
--Names of countries and continents in ascending order
SELECT name, population
FROM country
ORDER BY surfacearea;

SELECT name, (population / surfacearea) AS density FROM country ORDER BY density DESC;
-- can order by aliases!
-- LIMITING RESULTS
-- The name and average life expectancy of the countries with the 10 highest life expectancies.
SELECT name, lifeexpectancy FROM country WHERE lifeexpectancy IS NOT NULL ORDER BY lifeexpectancy DESC LIMIT 10;


SELECT round(gnp / population, 4) FROM country WHERE gnp != 0;
-- CONCATENATING OUTPUTS

-- The name & state of all cities in California, Oregon, or Washington.
-- "city, state", sorted by state then city
SELECT name || ', ' || district || ': ' || population FROM city WHERE district IN ( 'California', 'Oregon' ,'Washington') 
ORDER BY district, name;

-- AGGREGATE FUNCTIONS
-- Average Life Expectancy in the World
SELECT AVG(lifeexpectancy) FROM country;

-- Total population in Ohio
SELECT SUM(population) FROM city WHERE district = 'Ohio';

-- The surface area of the smallest country in the world
SELECT MIN(surfacearea) FROM country;

-- The 10 largest countries in the world
SELECT name FROM country ORDER BY population DESC LIMIT 10;

-- The number of countries who declared independence in 1991
SELECT COUNT( * ) FROM country WHERE indepyear = 1991;

-- GROUP BY
-- Count the number of countries where each language is spoken, ordered from most countries to least
SELECT language, COUNT(*) as number_of_countries
FROM countrylanguage
GROUP BY language
ORDER BY number_of_countries DESC;
-- Average life expectancy of each continent ordered from highest to lowest
SELECT continent, AVG(lifeexpectancy) AS avg_life_expectancy
FROM country
WHERE lifeexpectancy IS NOT NULL
GROUP BY continent
ORDER BY avg_life_expectancy DESC;

-- Exclude Antarctica from consideration for average life expectancy

-- Sum of the population of cities in each state in the USA ordered by state name
SELECT district, SUM(population) FROM city WHERE countrycode = 'USA' GROUP BY district ORDER BY district;

-- The average population of cities in each state in the USA ordered by state name
SELECT district, SUM(population), round(AVG(population), 4), MIN(population), MAX(population), COUNT(*) 
FROM city WHERE countrycode = 'USA' GROUP BY district ORDER BY district;


--
SELECT continent, region, SUM(surfacearea) FROM country
GROUP BY continent, region
ORDER BY continent, region;

-- SUBQUERIES
-- Find the names of cities in Europe with a gnp > 1000000
SELECT code FROM country WHERE continent = 'Europe' AND gnp > 1000000;

SELECT *
FROM city
WHERE countrycode IN (SELECT code FROM country WHERE continent = 'Europe' AND gnp > 1000000);
-- Find the names of cities under a given government leader
SELECT code FROM country WHERE headofstate = 'Elisabeth II';

SELECT * FROM city
WHERE countrycode IN(SELECT code FROM country WHERE headofstate = 'Elisabeth II') ORDER BY population DESC, countrycode;

-- Find the names of cities whose country they belong to has not declared independence yet
SELECT * FROM city WHERE countrycode IN(SELECT code FROM country WHERE indepyear IS NULL);

-- Find the sum of the population of each district in the country with the largest surface area
SELECT district, SUM(population) FROM city
WHERE countrycode =(SELECT code FROM country ORDER BY surfacearea DESC LIMIT 1)
GROUP BY district
ORDER BY district;


-- Additional samples
-- You may alias column and table names to be more descriptive
SELECT name  AS city_name
FROM city AS cities;

-- Alias can also be used to create shorthand references, such as "c" for city and "co" for country.
SELECT city.name, country.name
FROM city, country;

SELECT c.name, co.name
FROM city AS c, country AS co;

SELECT c.name, co.name
FROM city c, country co;

-- Ordering allows columns to be displayed in ascending order, or descending order (Look at Arlington)
SELECT name, population
FROM city
WHERE countrycode = 'USA'
ORDER BY name ASC, population DESC;

-- Limiting results allows rows to be returned in 'limited' clusters,where LIMIT says how many, and OFFSET (optional) specifies the number of rows to skip

SELECT name, population
FROM city
LIMIT 10 OFFSET 20;


-- Most database platforms provide string functions that can be useful for working with string data. In addition to string functions, string concatenation is also usually supported, which allows us to build complete sentences if necessary.
SELECT (name || ' is in the state of ' || district) AS city_and_state
FROM city
WHERE countrycode = 'USA';


-- Aggregate functions provide the ability to COUNT, SUM, and AVG, as well as determine MIN and MAX. Only returns a single row of value(s) unless used with GROUP BY.
SELECT COUNT(name) AS city_count
FROM city;

SELECT COUNT(population) FROM city;
SELECT COUNT(countrycode)FROM city;


-- Counts the number of rows in the city table

-- Also counts the number of rows in the city table

-- Gets the SUM of the population field in the city table, as well as
-- the AVG population, and a COUNT of the total number of rows.
SELECT COUNT(*), SUM(population), AVG(population)
FROM city;

-- Gets the MIN population and the MAX population from the city table.
SELECT MIN(population), MAX(population) FROM city;

-- Using a GROUP BY with aggregate functions allows us to summarize information for a specific column. 
-- For instance, we are able to determine the MIN and MAX population for each countrycode in the city table.
SELECT countrycode, MIN(population), MAX(population) FROM city GROUP BY countrycode;

