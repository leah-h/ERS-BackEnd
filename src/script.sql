DROP TABLE IF EXISTS users;

CREATE TABLE users(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

INSERT INTO users
VALUES
("username", "password");

SELECT * FROM users;

-- getUserBYUsernameAndPassword
SELECT * FROM users
WHERE username = "user" & password = "password";