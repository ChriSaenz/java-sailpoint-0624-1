SHOW DATABASES;

#	Switch to new database
CREATE DATABASE personnelmgmt;
USE personnelmgmt;
SHOW TABLES;

#	Initialize the department table
CREATE TABLE department (
	dept_id INT NOT NULL AUTO_INCREMENT,
    dept_name VARCHAR(30) NOT NULL,
    PRIMARY KEY (dept_id),
    UNIQUE INDEX dept_name_UNIQUE (dept_name ASC) VISIBLE
);
INSERT INTO department (dept_name)
	VALUES ("Management"), ("Training"), ("Sales"), ("Accounting"), ("Information Technology");
SELECT * FROM department;

#	Initialize the employee table which references the department table
CREATE TABLE employee (
	empl_id INT NOT NULL AUTO_INCREMENT,
    empl_firstname VARCHAR(30) NOT NULL,
    empl_lastname VARCHAR(30) NOT NULL,
    empl_title VARCHAR(30),
    dept_id INT,
    PRIMARY KEY (empl_id),
    INDEX empl_lastname1 (empl_lastname ASC) VISIBLE,
    CONSTRAINT dept_id1
		FOREIGN KEY (dept_id) REFERENCES department(dept_id)
		ON DELETE SET NULL ON UPDATE CASCADE
);
INSERT INTO employee(empl_firstname, empl_lastname, empl_title, dept_id)
	VALUES ("Joe", "Joebeth", "Accountant", 4),
			("Bill", "Lumbergh", "Manager", 1),
			("Jon", "Provan", "Lord of the Trainers", 2),
            ("Jim", "Halpert", "Sales Representative", 3),
            ("Gomer", "Pyle", "Accountant", 4),
            ("Harry", "Potter", "Senior IT Specialist", 5),
            ("Bill", "Mays", "Sales Representative", 3),
            ("Ash", "Ketchum", "Trainer", 2),
            ("Ivo", "Robotnik", "Network Administrator", 5);
SELECT * FROM employee;