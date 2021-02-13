-- CREATE DATABASE organizer;
DROP TABLE IF EXISTS department_job;
DROP TABLE IF EXISTS employee_department;
DROP TABLE IF EXISTS project_employee;
DROP TABLE IF EXISTS project;
DROP TABLE IF EXISTS job;
DROP TABLE IF EXISTS department;
DROP TABLE IF EXISTS employee;

CREATE TABLE employee (
        employee_id serial primary key,
        first_name varchar(32),
        last_name varchar(32) NOT NULL,
        gender char(10),
        date_of_birth varchar(10),
        date_of_hire DATE NOT NULL
);

CREATE TABLE department (
        department_id serial primary key,
        name varchar(32) NOT NULL
);

CREATE TABLE employee_department (
        employee_id int NOT NULL,
        department_id int,  
        
        constraint fk_employee_id_employee_department foreign key (employee_id) REFERENCES employee(employee_id),
        constraint fk_department_id_employee_department foreign key (department_id) REFERENCES department(department_id)
);

CREATE TABLE job (
        job_id serial primary key,
        description varchar(255) NOT NULL
);

CREATE TABLE department_job (
        job_id int NOT NULL,
        department_key int NOT NULL,
        
        constraint fk_department_job_job_id foreign key (job_id) REFERENCES job(job_id),
        constraint fk_department_job_department_key foreign key (department_key) REFERENCES department(department_id)
);


CREATE TABLE project (
       project_id serial primary key,
       name varchar(48) NOT NULL,
       start_date DATE
);

CREATE TABLE project_employee (
        employee_id int NOT NULL,
        project_id int NOT NULL,
        
        constraint fk_employee_id_project_employee foreign key (employee_id) REFERENCES employee(employee_id),
        constraint fk_project_id_project_employee foreign key (project_id) REFERENCES project(project_id)
);
