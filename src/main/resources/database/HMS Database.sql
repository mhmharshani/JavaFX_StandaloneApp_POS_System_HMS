DROP DATABASE IF EXISTS `Hospital_Management_System_db`;

CREATE DATABASE `Hospital_Management_System_db`;

USE `Hospital_Management_System_db`;

-- Creating users table 

CREATE TABLE users (
	employee_id VARCHAR(10) UNIQUE,
    username VARCHAR(20),
    email VARCHAR(30),
    `password` VARCHAR(50) NOT NULL,
    CONSTRAINT PRIMARY KEY (email)
    -- CONSTRAINT FOREIGN KEY (employee_id) REFERENCES employee(employee_id)
);
DESC users;

SELECT * FROM users;

-- Creating patient table 

CREATE TABLE patient (
    patient_id VARCHAR(10),
    `name` VARCHAR(30),
    nic VARCHAR(15) UNIQUE,
    address VARCHAR(15),
    gender VARCHAR(10),
    phoneNo VARCHAR(15) NOT NULL,
    age INT(3),
    CONSTRAINT PRIMARY KEY (patient_id)
);

DESC patient;

SELECT * FROM patient;

-- Creating employee table 

CREATE TABLE employee (
    employee_id VARCHAR(10),
    `name` VARCHAR(30),
    gender VARCHAR(10),
    address VARCHAR(15),
    phoneNo VARCHAR(15) NOT NULL,
    designation VARCHAR(20),
    qualification VARCHAR(30),
    salary DOUBLE(10,2),
    CONSTRAINT PRIMARY KEY (employee_id)
);

DESC employee;

SELECT * FROM employee;

-- Creating doctor table 

CREATE TABLE doctor (
    doctor_id VARCHAR(10),
    speciality VARCHAR(30),
    availability VARCHAR(15),
    employee_id VARCHAR(10) NOT NULL,
    CONSTRAINT PRIMARY KEY (doctor_id),
    CONSTRAINT FOREIGN KEY (employee_id) REFERENCES employee(employee_id)
);

DESC doctor;

SELECT * FROM doctor;

-- Creating appointment table 

CREATE TABLE appointment (
    appointment_id VARCHAR(10),
    `date` DATE,
    `time` TIME,
    `number` INT(3),
    `status` VARCHAR(20),
    doctor_id VARCHAR(10),
    patient_id VARCHAR(10),
    CONSTRAINT PRIMARY KEY (appointment_id),
    CONSTRAINT FOREIGN KEY (doctor_id) REFERENCES doctor(doctor_id),
    CONSTRAINT FOREIGN KEY (patient_id) REFERENCES patient(patient_id)
);

DESC appointment;

SELECT * FROM appointment;





