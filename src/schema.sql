DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS r_types;
DROP TABLE IF EXISTS r_status;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS reimbursements;

CREATE TABLE user_roles (
    role_id INTEGER PRIMARY KEY,
    u_role VARCHAR(20) NOT NULL
);

CREATE TABLE r_types (
    type_id INTEGER PRIMARY KEY,
    r_type VARCHAR(20) NOT NULL
);

CREATE TABLE r_status (
    status_id INTEGER PRIMARY KEY,
    r_status VARCHAR(20) NOT NULL
);

CREATE TABLE users (
    user_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(50),
    role_id INTEGER NOT NULL DEFAULT 2,
    CONSTRAINT `fk_users_role` FOREIGN KEY (role_id) REFERENCES user_roles(role_id) ON DELETE CASCADE
);

CREATE TABLE reimbursements (
    reim_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    amount DOUBLE NOT NULL,
    submitted DATE DEFAULT NULL,
    resolved DATE,
    description VARCHAR(250),
    author INTEGER,
    resolver INTEGER,
    status_id INTEGER DEFAULT 1,
    type_id INTEGER NOT NULL,
    receipt LONGBLOB,
    INDEX (author), FOREIGN KEY (author )
        REFERENCES users(user_id) ON DELETE CASCADE,
    INDEX (resolver), FOREIGN KEY (resolver)
        REFERENCES users(user_id) ON DELETE CASCADE,
    INDEX (status_id), FOREIGN KEY (status_id)
        REFERENCES r_status(status_id) ON DELETE CASCADE,
    INDEX (type_id), FOREIGN KEY (type_id)
        REFERENCES r_types(type_id) ON DELETE CASCADE
);

# CONSTRAINT `fk_reim_author` FOREIGN KEY (author) REFERENCES users(user_id) ON DELETE CASCADE,
# CONSTRAINT `fk_reim_resolver` FOREIGN KEY (resolver) REFERENCES users(user_id) ON DELETE CASCADE
# CONSTRAINT `fk_reim_status_id` FOREIGN KEY (status_id) REFERENCES r_status(status_id) ON DELETE CASCADE,
# CONSTRAINT `fk_reim_type_id` FOREIGN KEY (type_id) REFERENCES r_types(type_id) ON DELETE CASCADE



