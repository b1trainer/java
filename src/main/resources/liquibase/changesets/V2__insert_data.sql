insert into users (name, username, password)
values ('John Doe', 'johnDoe@gmail.com', 'пароль1'),
       ('Mike Smith', 'mikeSmith@google.us', 'пароль2');

insert into tasks (title, description, status, expiration_date)
values ('Buy cheese', null, 'TODO', '2022-02-01 11:00:00'),
       ('Do homework', 'Math, Physics, Chemistry', 'TODO', '2024-02-05 10:00:00'),
       ('Clean room', null, 'DONE', '2023-02-01 16:00:00'),
       ('Call Mike', 'Ask something', 'TODO', '2023-01-01 12:00:00');

insert into users_tasks (task_id, user_id)
values (1, 2),
       (2, 2),
       (3, 2),
       (4, 1);

insert into users_roles (user_id, role)
values (1, 'ROLE_ADMIN'),
       (2, 'ROLE_USER');