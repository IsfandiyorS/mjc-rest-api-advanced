INSERT INTO tag (name)
VALUES ('tag1'),
       ('tag2'),
       ('tag3'),
       ('tag4'),
       ('tag5');

INSERT INTO gift_certificate (name, description, price, duration)
VALUES ('giftCertificate1', 'description1', 10.1, 1),
       ('giftCertificate2', 'description2', 20.2, 2),
       ('giftCertificate3', 'description3', 30.3, 3);

INSERT INTO gift_certificate_tag (gift_certificate_id, tag_id)
VALUES (1, 1),
       (2, 3),
       (2, 2);

INSERT INTO users(username, email, first_name, last_name, password, phone_number, user_type)
VALUES ('user1', 'gali@mail.ru','ali','ali','ali1ali1','998903353875', 0),
       ('user2', 'vali@mail.ru','vali','vali','vali1vali1','998903353876', 0);


INSERT INTO orders(price, order_quantity, user_id, gift_certificate_id)
VALUES (10.1, 1, 1, 1),
       (30.3, 2, 1, 2),
       (20.2, 2, 2, 3);