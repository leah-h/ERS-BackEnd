DROP TABLE user_roles;
DROP TABLE users;
DROP TABLE r_type;
DROP TABLE r_status;
DROP TABLE reimbursements;

CREATE TABLE user_roles(
    roleId INTEGER PRIMARY KEY,
    role VARCHAR(10) NOT NULL
);

INSERT INTO user_roles
VALUES (1, "manager");

INSERT INTO user_roles
VALUES (2, "employee");


-- mock reimbursements data
insert into reimbursements (reim_id, amount, submitted, resolved, author, resolver, status_id, type_id)
values (239, 301.41, '2021-08-27', '2020-11-05', 1002, 1001, 2, 1);

insert into reimbursements (reim_id, amount, submitted, resolved, author, resolver, status_id, type_id)
values (221, 190.01, '2020-11-12', '2021-07-09', 1002, 1001, 3, 4);

insert into reimbursements (reim_id, amount, submitted, resolved, author, resolver, status_id, type_id)
 values (136, 202.93, '2021-08-08', '2021-11-23', 1002, 1001, 3, 1);

insert into reimbursements (reim_id, amount, submitted, resolved, author, resolver, status_id, type_id)
values (274, 298.34, '2021-02-24', '2021-09-11', 1002, 1001, 2, 3);

insert into reimbursements (reim_id, amount, submitted, resolved, author, resolver, status_id, type_id)
values (394, 421.3, '2021-03-05', '2020-05-01', 1, 0, 1, 3);

insert into reimbursements (reim_id, amount, submitted, resolved, author, resolver, status_id, type_id)
values (339, 279.9, '2020-07-08', '2020-10-08', 363, 0, 2, 1);

insert into reimbursements (reim_id, amount, submitted, resolved, author, resolver, status_id, type_id)
values (203, 354.26, '2020-12-11', '2021-09-26', 363, 0, 3, 2);

insert into reimbursements (reim_id, amount, submitted, resolved, author, resolver, status_id, type_id)
values (316, 187.47, '2021-01-06', '2021-07-06', 363, 0, 2, 4);

insert into reimbursements (reim_id, amount, submitted, resolved, author, resolver, status_id, type_id)
values (335, 78.61, '2021-07-31', '2021-04-07', 1, 0, 2, 1);

insert into reimbursements (reim_id, amount, submitted, resolved, author, resolver, status_id, type_id)
 values (399, 250.87, '2020-11-21', '2021-08-30', 1, 0, 3, 1);

insert into reimbursements (reim_id, amount, submitted, resolved, author, resolver, status_id, type_id)
values (242, 338.44, '2020-10-13', '2020-11-19', 1, 0, 1, 2);

insert into reimbursements (reim_id, amount, submitted, resolved, author, resolver, status_id, type_id)
values (351, 211.69, '2020-05-17', '2021-10-17', 376, 0, 3, 3);

insert into reimbursements (reim_id, amount, submitted, resolved, author, resolver, status_id, type_id)
values (364, 94.64, '2020-11-12', '2021-11-29', 376, 0, 3, 3);

insert into reimbursements (reim_id, amount, submitted, resolved, author, resolver, status_id, type_id)
values (326, 302.69, '2020-10-25', '2021-01-09', 376, 0, 2, 3);

insert into reimbursements (reim_id, amount, submitted, resolved, author, resolver, status_id, type_id)
values (279, 244.45, '2021-09-29', '2021-08-20', 119, 0, 1, 1);

insert into reimbursements (reim_id, amount, submitted, resolved, author, resolver, status_id, type_id)
values (245, 321.76, '2021-10-25', '2021-06-28', 119, 0, 2, 1);

insert into reimbursements (reim_id, amount, submitted, resolved, author, resolver, status_id, type_id)
values (282, 439.3, '2021-01-23', '2021-06-18', 119, 0, 2, 1);

insert into reimbursements (reim_id, amount, submitted, resolved, author, resolver, status_id, type_id)
values (287, 130.5, '2020-09-12', '2021-08-20', 1, 0, 2, 1);

insert into reimbursements (reim_id, amount, submitted, resolved, author, resolver, status_id, type_id)
 values (215, 128.8, '2021-07-08', '2020-10-18', 1, 0, 2, 4);

insert into reimbursements (reim_id, amount, submitted, resolved, author, resolver, status_id, type_id)
values (441, 234.04, '2020-07-28', '2021-06-15', 2, 0, 2, 4);



-- mock users data
insert into users (user_id, username, password, first_name, last_name, email, role_id)
values (363, 'ileblanc0', 'BeczXv', 'Irvin', 'Leblanc',
        'ileblanc0@newyorker.com', 2);

insert into users (user_id, username, password, first_name, last_name, email, role_id)
 values (119, 'tlias1', 'lEHojpS2dW9C', 'Torrey',
         'Lias', 'tlias1@guardian.co.uk', 2);

insert into users (user_id, username, password, first_name, last_name, email, role_id)
values (376, 'jbaylay2', 'MKXpeq', 'Jerad', 'Baylay',
        'jbaylay2@elpais.com', 2);

insert into users (user_id, username, password, first_name, last_name, email, role_id)
values (263, 'tredier3', 'ArwTHMxFX2Ag', 'Talyah', 'Redier',
        'tredier3@comsenz.com', 1);

insert into users (user_id, username, password, first_name, last_name, email, role_id)
values (287, 'eyouings4', 'ZG3leuL7O', 'Ellynn', 'Youings',
        'eyouings4@sciencedirect.com', 1);

insert into users (user_id, username, password, first_name, last_name, email, role_id)
values (277, 'ypeagrim5', 'OV8B9NNibT', 'Yolande', 'Peagrim',
        'ypeagrim5@bing.com', 2);

insert into users (user_id, username, password, first_name, last_name, email, role_id)
values (387, 'dmedcalf6', 'hGo8Ha', 'Dionis', 'Medcalf',
        'dmedcalf6@angelfire.com', 1);

insert into users (user_id, username, password, first_name, last_name, email, role_id)
 values (228, 'beuplate7', 'QUvU4pFXE', 'Bengt', 'Euplate',
         'beuplate7@creativecommons.org', 2);

insert into users (user_id, username, password, first_name, last_name, email, role_id)
 values (461, 'bdampier8', 'SugeuCI0dR', 'Burt', 'Dampier',
         'bdampier8@google.cn', 1);

insert into users (user_id, username, password, first_name, last_name, email, role_id)
 values (233, 'spocknell9', 'liSutFyAzB', 'Simonette', 'Pocknell',
         'spocknell9@businesswire.com', 1);

INSERT INTO users
VALUES
(1002, "user", "pswd", "leah", "employee", "test@lhdev.io", 2);

INSERT INTO users
VALUES
(1001, "username", "password", "leah", "manager", "manager@lhdev.io", 1);



-- r_status

INSERT INTO r_status
VALUES(1, "pending");

INSERT INTO r_status
VALUES(2, "approved");

INSERT INTO r_status
VALUES(3, "denied");


-- r_type

INSERT INTO r_type
VALUES(1, "food");

INSERT INTO r_type
VALUES(2, "lodging");

INSERT INTO r_type
VALUES(3, "travel");

INSERT INTO r_type
VALUES(4, "other");

