INSERT INTO employee (first_name, last_name, gender, date_of_birth, date_of_hire) 
VALUES ('Beth', 'Winters', 'Female', '01/01/1980', '01/01/2020');

INSERT INTO employee (first_name, last_name, gender, date_of_birth, date_of_hire) 
VALUES ('Kurt', 'Winters', 'Male', '01/02/1981', '01/01/2010');

INSERT INTO employee (first_name, last_name, gender, date_of_birth, date_of_hire) 
VALUES ('Jeff', 'Smith', null, '01/03/1982', '01/04/2002');

INSERT INTO employee (first_name, last_name, gender, date_of_birth, date_of_hire) 
VALUES ('Lisa', 'Baker', 'Female', '09/01/1990', '01/01/2015');

INSERT INTO employee (first_name, last_name, gender, date_of_birth, date_of_hire) 
VALUES ('Leonard', 'Baker', 'Male', '01/21/1984', '01/01/1999');

INSERT INTO employee (first_name, last_name, gender, date_of_birth, date_of_hire) 
VALUES ('Elgin', 'Smith', 'Male', null, '01/21/1964');

INSERT INTO employee (first_name, last_name, gender, date_of_birth, date_of_hire) 
VALUES (null, 'Smith', null, null, '02/02/1902');

INSERT INTO employee (first_name, last_name, gender, date_of_birth, date_of_hire) 
VALUES ('Felicia', 'Piedmont', 'Female', '01/01/1980', '12/12/2012');



INSERT INTO department (name)
VALUES ('Sales');

INSERT INTO department (name)
VALUES ('Invoices');

INSERT INTO department (name)
VALUES ('Quality Assurance');



INSERT INTO project(name, start_date)
VALUES ('Database creation', null);

INSERT INTO project(name, start_date)
VALUES ('Customer suggestions', '01/01/2022');

INSERT INTO project(name, start_date)
VALUES ('New Interface', '01/01/2023');


INSERT INTO project_employee(employee_id, project_id)
VALUES (10, 1);

INSERT INTO project_employee(employee_id, project_id)
VALUES (11, 1);

INSERT INTO project_employee(employee_id, project_id)
VALUES (12, 2);

INSERT INTO project_employee(employee_id, project_id)
VALUES (13, 2);

INSERT INTO project_employee(employee_id, project_id)
VALUES (14, 3);

INSERT INTO project_employee(employee_id, project_id)
VALUES (15, 3);

INSERT INTO employee_department(employee_id, department_id)
VALUES (10, 1);

INSERT INTO employee_department(employee_id, department_id)
VALUES (11, 1);

INSERT INTO employee_department(employee_id, department_id)
VALUES (17, 2);

INSERT INTO employee_department(employee_id, department_id)
VALUES (16, 2);

INSERT INTO employee_department(employee_id, department_id)
VALUES (14, 3);

INSERT INTO employee_department(employee_id, department_id)
VALUES (13, 3);