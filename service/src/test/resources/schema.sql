CREATE TABLE IF NOT EXISTS tag
(
    id               bigserial primary key,
    create_date      timestamp not null,
    last_update_date timestamp,
    state            numeric default 0,
    name             varchar(255)
);

CREATE TABLE IF NOT EXISTS gift_certificate
(
    id               bigserial primary key,
    create_date      timestamp not null,
    last_update_date timestamp,
    state            numeric default 0,
    description      varchar(255),
    duration         integer,
    name             varchar(255),
    price            numeric(19, 2)
);

CREATE TABLE IF NOT EXISTS gift_certificate_tag
(
    gift_certificate_id BIGINT,
    tag_id              BIGINT,
    FOREIGN KEY (gift_certificate_id) REFERENCES gift_certificate (id),
    FOREIGN KEY (tag_id) REFERENCES tag (id)
);

CREATE TABLE IF NOT EXISTS users
(
    id               bigserial  primary key,
    create_date      timestamp  not null,
    last_update_date timestamp,
    state            numeric default 0,
    email            varchar(255) not null unique,
    first_name       varchar(255) not null,
    last_name        varchar(255) not null,
    password         varchar(255) not null,
    phone_number     varchar(255) not null unique,
    user_type        integer,
    username         varchar(255) not null unique
);

CREATE TABLE IF NOT EXISTS orders
(
    id                  bigserial primary key,
    create_date         timestamp  not null,
    last_update_date    timestamp,
    state               numeric default 0,
    order_quantity      bigint,
    price               numeric(19, 2),
    user_id             bigint,
    gift_certificate_id bigint,
    FOREIGN KEY (gift_certificate_id) REFERENCES gift_certificate (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);