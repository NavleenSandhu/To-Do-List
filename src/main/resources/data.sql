INSERT INTO Users (USER_NAME, FIRST_NAME, LAST_NAME, PASSWORD) VALUES ('navleen', 'Navleen Singh', 'Sandhu', '$2a$10$zgw6OSMHVN6J660o/wPAUOrkw7o.j9aNcUHFfjDjKlLknliDHxNI.');
INSERT INTO Users (USER_NAME, FIRST_NAME, LAST_NAME, PASSWORD) VALUES ('arsh', 'Arshdeep', 'Singh', '$2a$10$zgw6OSMHVN6J660o/wPAUOrkw7o.j9aNcUHFfjDjKlLknliDHxNI.');
INSERT INTO Users (USER_NAME, FIRST_NAME, LAST_NAME, PASSWORD) VALUES ('john', 'John', 'Cena', '$2a$10$zgw6OSMHVN6J660o/wPAUOrkw7o.j9aNcUHFfjDjKlLknliDHxNI.');

insert into sec_role (role_Name)
values ('ROLE_USER');
insert into user_role (user_Id, role_Id)
values (1, 1);
insert into user_role (user_Id, role_Id)
values (2, 1);
insert into user_role (user_Id, role_Id)
values (3, 1);

INSERT INTO Tasks (TASK_DESCRIPTION, DUE_DATE, USER_ID) VALUES
('Complete project proposal', '2023-12-01', 1);

INSERT INTO Tasks (TASK_DESCRIPTION, DUE_DATE, USER_ID) VALUES
('Prepare for upcoming presentation', '2023-11-14', 1);

INSERT INTO Tasks (TASK_DESCRIPTION, DUE_DATE, USER_ID) VALUES
('Submit monthly report', '2023-11-20', 1);

INSERT INTO Tasks (TASK_DESCRIPTION, DUE_DATE, USER_ID) VALUES
('Review and respond to emails', '2023-11-25', 1);

INSERT INTO Tasks (TASK_DESCRIPTION, DUE_DATE, USER_ID) VALUES
('Complete coding assignment', '2023-12-05', 1);


-- Tasks for User 1
INSERT INTO Tasks (TASK_DESCRIPTION, DUE_DATE, USER_ID) VALUES
('Complete project proposal', '2023-12-01', 1),
('Prepare for client meeting', '2023-12-02', 1),
('Submit weekly report', '2023-12-05', 1),
('Review code changes', '2023-12-10', 1),
('Attend team brainstorming session', '2023-12-15', 1);

-- Tasks for User 2
INSERT INTO Tasks (TASK_DESCRIPTION, DUE_DATE, USER_ID) VALUES
('Create marketing presentation', '2023-12-03', 2),
('Interview potential candidates', '2023-12-06', 2),
('Update financial reports', '2023-12-08', 2),
('Collaborate with design team', '2023-12-12', 2),
('Attend project status meeting', '2023-12-18', 2);

-- Tasks for User 3
INSERT INTO Tasks (TASK_DESCRIPTION, DUE_DATE, USER_ID) VALUES
('Research industry trends', '2023-12-07', 3),
('Complete training modules', '2023-12-11', 3),
('Prepare for client presentation', '2023-12-14', 3),
('Submit expense reports', '2023-12-16', 3),
('Participate in team-building activity', '2023-12-20', 3);
