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

-- ALTER TABLE patient DROP COLUMN id;

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

INSERT INTO employee VALUES ('HLE#0001', 'Hemal', 'Male', 'Udawalawa', '0785411122','Physician','MBBS',100000);
INSERT INTO employee VALUES ('HLE#0002', 'Gayani', 'Female', 'Ranna', '0709211122','OfficeAid','Grade 8',25000);

DESC employee;

SELECT * FROM employee;

SELECT * FROM employee WHERE `name`='SAMAN';


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

-- Creating doctor_session table 

CREATE TABLE doctor_session (
	session_id VARCHAR(10),
    `name` VARCHAR(30) NOT NULL,
    `date` DATE NOT NULL,
    `time` TIME NOT NULL,
    `number_limit` VARCHAR(15),
    `status` VARCHAR(20),
    doctor_id VARCHAR(10),
    CONSTRAINT PRIMARY KEY (session_id),
    CONSTRAINT FOREIGN KEY (doctor_id) REFERENCES doctor(doctor_id)
);

DESC doctor_session;

SELECT * FROM doctor_session;

-- Creating appointment table 

CREATE TABLE appointment (
    appointment_id VARCHAR(10),
    `date` DATE,
    `time` TIME,
    `number` INT(3),
    `status` VARCHAR(20),
    doctor_id VARCHAR(10),
    patient_id VARCHAR(10),
    session_id VARCHAR(10),
    CONSTRAINT PRIMARY KEY (appointment_id),
    CONSTRAINT FOREIGN KEY (doctor_id) REFERENCES doctor(doctor_id),
    CONSTRAINT FOREIGN KEY (patient_id) REFERENCES patient(patient_id),
    CONSTRAINT FOREIGN KEY (session_id) REFERENCES doctor_session(session_id)
);

DESC appointment;

SELECT * FROM appointment;

-- Creating prescription table 

CREATE TABLE prescription (
    prescription_id VARCHAR(15),
    diagnosis VARCHAR(50) NOT NULL,
    medicine VARCHAR(30),
    dosage VARCHAR(20),
    duration VARCHAR(20),
    doctor_id VARCHAR(10),
    patient_id VARCHAR(10),
    CONSTRAINT PRIMARY KEY (prescription_id),
    CONSTRAINT FOREIGN KEY (doctor_id) REFERENCES doctor(doctor_id),
    CONSTRAINT FOREIGN KEY (patient_id) REFERENCES patient(patient_id)
);

DESC prescription;

SELECT * FROM prescription;

-- Creating prescription table 

CREATE TABLE billing (
    billing_id VARCHAR(10),
    gen_date DATE,
    gen_time TIME,
    `description` VARCHAR(30),
    total DOUBLE,
    `status` VARCHAR(20),
    patient_id VARCHAR(10),
    CONSTRAINT PRIMARY KEY (billing_id),
    CONSTRAINT FOREIGN KEY (patient_id) REFERENCES patient(patient_id)
);

DESC billing;

SELECT * FROM billing;

CREATE TABLE room (
    room_id VARCHAR(10),
    `type` VARCHAR(20),
    `capacity` VARCHAR(20),
    availability VARCHAR(20),
    patient_id VARCHAR(10),
    CONSTRAINT PRIMARY KEY (room_id),
    CONSTRAINT FOREIGN KEY (patient_id) REFERENCES patient(patient_id)
);

DESC room;

INSERT INTO room VALUES ('HLRM001', 'Luxury', '2 Person', 'Yes', null);
INSERT INTO room VALUES ('HLRM002', 'Semi-Luxury', '2 Person', 'Yes', null);
INSERT INTO room VALUES ('HLRM003', 'Semi-Luxury', '4 Person', 'No', 'HLP#0004');
INSERT INTO room VALUES ('HLRM004', 'Semi-Luxury', '2 Person', 'No', 'HLP#0007');
INSERT INTO room VALUES ('HLRM005', 'Normal', '4 Person', 'Yes', null);
INSERT INTO room VALUES ('HLRM006', 'Normal', '3 Person', 'Yes', null);
INSERT INTO room VALUES ('HLRM007', 'Luxury', '2 Person', 'Yes', null);
INSERT INTO room VALUES ('HLRM008', 'Semi-Luxury', '3 Person', 'No', 'HLP#0005');
INSERT INTO room VALUES ('HLRM009', 'Semi-Luxury', '2 Person', 'Yes', null);
INSERT INTO room VALUES ('HLRM010', 'Normal', '2 Person', 'Yes', null);

SELECT * FROM room;


CREATE TABLE medical_record (
    record_id VARCHAR(15),
    date DATE,
    diagnosis VARCHAR(30),
    treatment VARCHAR(50),
    patient_id VARCHAR(10),
    CONSTRAINT PRIMARY KEY (record_id),
    CONSTRAINT FOREIGN KEY (patient_id) REFERENCES patient(patient_id)
);

DESC medical_record;

SELECT * FROM medical_record;

SHOW TABLES;

-- DROP TABLE medical_record;
