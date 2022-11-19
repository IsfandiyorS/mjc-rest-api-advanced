INSERT INTO tag (name, create_date)
VALUES ('tag1', '2022-11-19T06:12:15.156'),
       ('tag2', '2022-11-19T06:12:15.156'),
       ('tag3', '2022-11-19T06:12:15.156'),
       ('tag4', '2022-11-19T06:12:15.156'),
       ('tag5', '2022-11-19T06:12:15.156');

INSERT INTO gift_certificate (name, description, price, duration, create_date)
VALUES ('giftCertificate1', 'description1', 10.1, 1, '2022-11-19T06:12:15.156'),
       ('giftCertificate2', 'description2', 20.2, 2, '2022-11-19T06:12:15.156'),
       ('giftCertificate3', 'description3', 30.3, 3, '2022-11-19T06:12:15.156');

INSERT INTO gift_certificate_tag (gift_certificate_id, tag_id)
VALUES (1, 1),
       (2, 3),
       (2, 2);

INSERT INTO users(username, email, first_name, last_name, password, phone_number, user_type, create_date)
VALUES ('user1', 'gali@mail.ru','ali','ali','ali1ali1','998903353875', 0, '2022-11-19T06:12:15.156'),
       ('user2', 'vali@mail.ru','vali','vali','vali1vali1','998903353876', 0, '2022-11-19T06:12:15.156');


INSERT INTO orders(price, order_quantity, user_id, gift_certificate_id, create_date)
VALUES (10.1, 1, 1, 1, '2022-11-19T06:12:15.156'),
       (30.3, 2, 1, 2, '2022-11-19T06:12:15.156'),
       (20.2, 2, 2, 3, '2022-11-19T06:12:15.156');