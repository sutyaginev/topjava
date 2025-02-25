DELETE FROM user_role;
DELETE FROM meals;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin'),
       ('Guest', 'guest@gmail.com', 'guest');

INSERT INTO user_role (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);


INSERT INTO meals (user_id, date_time, calories, description)
VALUES (100000, to_timestamp('30.01.2020 10:00', 'DD.MM.YYYY HH24:MI'), 500, 'Завтрак'),
       (100000, to_timestamp('30.01.2020 13:00', 'DD.MM.YYYY HH24:MI'), 1000, 'Обед'),
       (100000, to_timestamp('30.01.2020 20:00', 'DD.MM.YYYY HH24:MI'), 500, 'Ужин'),
       (100000, to_timestamp('31.01.2020 00:00', 'DD.MM.YYYY HH24:MI'), 100, 'Еда на граничное значение'),
       (100000, to_timestamp('31.01.2020 10:00', 'DD.MM.YYYY HH24:MI'), 1000, 'Завтрак'),
       (100000, to_timestamp('31.01.2020 13:00', 'DD.MM.YYYY HH24:MI'), 500, 'Обед'),
       (100000, to_timestamp('31.01.2020 20:00', 'DD.MM.YYYY HH24:MI'), 410, 'Ужин'),
       (100001, to_timestamp('30.01.2020 09:00', 'DD.MM.YYYY HH24:MI'), 600, 'Завтрак'),
       (100001, to_timestamp('30.01.2020 12:00', 'DD.MM.YYYY HH24:MI'), 1100, 'Обед'),
       (100001, to_timestamp('30.01.2020 19:00', 'DD.MM.YYYY HH24:MI'), 510, 'Ужин');
