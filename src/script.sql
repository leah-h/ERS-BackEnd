DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;

CREATE TABLE user_roles(
    role_id INTEGER PRIMARY KEY,
    user_role VARCHAR(10) NOT NULL
);

INSERT INTO user_roles
VALUES (1, "manager");

INSERT INTO user_roles
VALUES (2, "employee");

SELECT * FROM user_roles;

CREATE TABLE users(
    user_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL,
    role_id INTEGER NOT NULL,
    FOREIGN KEY (role_id) REFERENCES user_roles(role_id)
);

ALTER TABLE users
ADD UNIQUE (username);


INSERT INTO users
VALUES
(1001, "user", "pswd", "leah", "employee", "test@lhdev.io", 2);

INSERT INTO users
VALUES
(1002, "username", "password", "leah", "manager", "manager@lhdev.io", 1);

SELECT * FROM users;

-- getUserBYUsernameAndPassword
SELECT * FROM users
WHERE username = "username" & password = "password";