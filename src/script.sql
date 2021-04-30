DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS r_type;

CREATE TABLE user_roles(
    roleId INTEGER PRIMARY KEY,
    role VARCHAR(10) NOT NULL
);

INSERT INTO user_roles
VALUES (1, "manager");

INSERT INTO user_roles
VALUES (2, "employee");

SELECT * FROM user_roles;

CREATE TABLE users(
    userId INTEGER PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL,
    roleId INTEGER NOT NULL,
    FOREIGN KEY (roleId) REFERENCES user_roles(roleId)
);

INSERT INTO users
VALUES
(1002, "user", "pswd", "leah", "employee", "test@lhdev.io", 2);

INSERT INTO users
VALUES
(1001, "username", "password", "leah", "testLastName", "manager@lhdev.io", 1);

SELECT * FROM users;

DELETE FROM users
WHERE id=0;

CREATE TABLE r_type(
    typeId INTEGER PRIMARY KEY,
    type VARCHAR(10)
);

INSERT INTO r_type
VALUES(1, "food");

INSERT INTO r_type
VALUES(2, "lodging");

INSERT INTO r_type
VALUES(3, "transport");

INSERT INTO r_type
VALUES(4, "education");

INSERT INTO r_type
VALUES(5, "wellness");


SELECT * FROM r_type;

CREATE TABLE r_status(
    statusId INTEGER PRIMARY KEY,
    status VARCHAR(10)
);

INSERT INTO r_status
VALUES(1, "pending");

INSERT INTO r_status
VALUES(2, "approved");

INSERT INTO r_status
VALUES(3, "denied");

SELECT * FROM r_status;



SELECT * FROM r_status;


CREATE TABLE reimbursements(
                               reimId INTEGER PRIMARY KEY AUTO_INCREMENT,
                               amount INTEGER,
                               submitted DATETIME,
                               resolved DATETIME,
                               description VARCHAR(250),
                               author INTEGER,
                               resolver INTEGER,
                               statusId INTEGER,
                               typeId INTEGER

);

SELECT * from reimbursements;

-- getUserBYUsernameAndPassword
SELECT * FROM users
WHERE username = "username" & password = "password";

