DROP TABLE Piece_Employees CASCADE CONSTRAINTS;
DROP TABLE Salaried_Employees CASCADE CONSTRAINTS;
DROP TABLE Commission_Employees CASCADE CONSTRAINTS;
DROP TABLE Plus_Commission_Employees CASCADE CONSTRAINTS;
DROP TABLE Hourly_Employees CASCADE CONSTRAINTS;
DROP TABLE Employees CASCADE CONSTRAINTS;

CREATE TABLE Employees (
	SSN CHAR(11) NOT NULL,
	First_Name VARCHAR2 (30) NOT NULL,
	Last_Name VARCHAR2 (30) NOT NULL,
    Birthday DATE NOT NULL,
	Employee_Type VARCHAR2 (30) NOT NULL,
	Department_Name VARCHAR2 (30) NOT NULL,
	CONSTRAINT employees_pk PRIMARY KEY (SSN)
);

CREATE TABLE Salaried_Employees (
	SSN CHAR(11) NOT NULL,
	Week_Number NUMBER(2) NOT NULL,
	Weekly_Salary NUMBER(8,2) NOT NULL,
	Bonus NUMBER(8,2),
	CONSTRAINT salaried_employees_pk PRIMARY KEY (SSN, Week_Number),
	CONSTRAINT salaried_employees_fk FOREIGN KEY (SSN)
             REFERENCES Employees (SSN) 
);

CREATE TABLE Commission_Employees (
	SSN CHAR(11) NOT NULL,
	Week_Number NUMBER(2) NOT NULL,
	Gross_Sales NUMBER(8) NOT NULL,
	Commission_Rate NUMBER(5,2) NOT NULL,
	Bonus NUMBER(8,2),
	CONSTRAINT commission_employees_pk PRIMARY KEY (SSN, Week_Number),
	CONSTRAINT commission_employees_fk FOREIGN KEY (SSN)
             REFERENCES Employees (SSN) 
);

CREATE TABLE Plus_Commission_Employees (
	SSN CHAR(11) NOT NULL,
	Week_Number NUMBER(2) NOT NULL,
	Gross_Sales NUMBER(8) NOT NULL,
	Commission_Rate NUMBER(5,2) NOT NULL,
	Base_Salary NUMBER(8,2) NOT NULL,
	Bonus NUMBER(8,2),
	CONSTRAINT bp_commission_employees_pk PRIMARY KEY (SSN, Week_Number),
	CONSTRAINT bp_commission_employees_fk FOREIGN KEY (SSN)
             REFERENCES Employees (SSN) 
);

CREATE TABLE Hourly_Employees (
	SSN CHAR(11) NOT NULL,
	Week_Number NUMBER(2) NOT NULL,
	Hours_Worked NUMBER(8) NOT NULL,
	Pay_Rate NUMBER(8,2) NOT NULL,
	Bonus NUMBER(8,2),
	CONSTRAINT hourly_employees_pk PRIMARY KEY (SSN, Week_Number),
	CONSTRAINT hourly_employees_fk FOREIGN KEY (SSN)
             REFERENCES Employees (SSN) 
);

CREATE TABLE Piece_Employees (
	SSN CHAR(11) NOT NULL,
	Week_Number NUMBER(2) NOT NULL,
	Piece_Rate NUMBER(8,2) NOT NULL,
	Number_Pieces NUMBER(8) NOT NULL,
	Bonus NUMBER(8,2),
	CONSTRAINT piece_employees_pk PRIMARY KEY (SSN, Week_Number),
	CONSTRAINT piece_employees_fk FOREIGN KEY (SSN)
             REFERENCES Employees (SSN) 
);

INSERT INTO Employees VALUES ('111-11-1111', 'John', 'Smith', '02-JAN-1945', 'salariedEmployee', 'RD');
INSERT INTO Employees VALUES ('222-22-2222', 'Sue', 'Jones', '02-MAR-1961', 'commissionEmployee', 'SALES');
INSERT INTO Employees VALUES ('333-33-3333', 'Bob', 'Lowis', '10-MAY-1958', 'basePlusCommissionEmployee', 'SALES');
INSERT INTO Employees VALUES ('444-44-4444', 'Karen', 'Price', '25-MAY-1972', 'hourlyEmployee', 'HR');
INSERT INTO Salaried_Employees VALUES ('111-11-1111', 1, 2013.67, 0);
INSERT INTO Commission_Employees VALUES ('222-22-2222', 1, 10100, 0.05, 0);
INSERT INTO Plus_Commission_Employees VALUES ('333-33-3333', 1, 5000, 0.04, 300, 0);
INSERT INTO Hourly_Employees VALUES ('444-44-4444', 1, 30, 35.5, 0);
INSERT INTO Salaried_Employees VALUES ('111-11-1111', 2, 2013.67, 0);
INSERT INTO Commission_Employees VALUES ('222-22-2222', 2, 10100, 0.05, 0);
INSERT INTO Plus_Commission_Employees VALUES ('333-33-3333', 2, 5000, 0.04, 300, 0);
INSERT INTO Hourly_Employees VALUES ('444-44-4444', 2, 30, 35.5, 0);
INSERT INTO Piece_Employees VALUES ('444-44-4444', 1, 35.5, 25, 0);
INSERT INTO Piece_Employees VALUES ('444-44-4444', 2, 20.5, 35, 0);

COMMIT;
