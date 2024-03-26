BEGIN TRANSACTION;

-- CREATE TABLE statements
CREATE TABLE address
(
    id       SERIAL PRIMARY KEY,
    street   VARCHAR(64),
    zip_code VARCHAR(64),
    city_id  INTEGER NOT NULL
);

CREATE TABLE apartment
(
    id                SERIAL PRIMARY KEY,
    no_rooms          INTEGER,
    no_bathrooms      INTEGER,
    no_beds           INTEGER,
    max_no_people     INTEGER,
    area              INTEGER,
    price_per_day     NUMERIC(10, 2),
    hotel_id          INTEGER     NOT NULL,
    currency_iso_code VARCHAR(32) NOT NULL
);

CREATE UNIQUE INDEX apartment__idx ON
    apartment (
               currency_iso_code
               ASC);

CREATE TABLE apartment_feature
(
    feature_name VARCHAR NOT NULL,
    apartment_id INTEGER NOT NULL,
    PRIMARY KEY (apartment_id, feature_name)
);

CREATE UNIQUE INDEX apartment_feature__idx ON
    apartment_feature (
                       feature_name
                       ASC);

CREATE TABLE booking
(
    id           SERIAL PRIMARY KEY,
    start_date   DATE,
    end_date     DATE,
    no_people    INTEGER,
    customer_id  INTEGER NOT NULL,
    apartment_id INTEGER NOT NULL
);

CREATE UNIQUE INDEX booking__idx ON
    booking (
             apartment_id
             ASC);

CREATE TABLE city
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(64),
    country_id INTEGER NOT NULL
);

CREATE TABLE complaint
(
    id         SERIAL PRIMARY KEY,
    "date"     DATE,
    text       TEXT,
    booking_id INTEGER NOT NULL
);

CREATE UNIQUE INDEX complaint__idx ON
    complaint (
               booking_id
               ASC);

CREATE TABLE country
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR,
    code VARCHAR
);

CREATE TABLE currency
(
    iso_code VARCHAR(32) PRIMARY KEY,
    name     VARCHAR(32) NOT NULL
);

CREATE TABLE customer
(
    id           SERIAL PRIMARY KEY,
    name         VARCHAR(64),
    surname      VARCHAR(64),
    age          INTEGER,
    gender       VARCHAR(1),
    email        VARCHAR(64),
    phone_number VARCHAR(64),
    address_id   INTEGER NOT NULL
);

CREATE UNIQUE INDEX client__idx ON
    customer (
              address_id
              ASC);

CREATE TABLE feature
(
    name VARCHAR NOT NULL PRIMARY KEY
);

CREATE TABLE hotel
(
    id           SERIAL PRIMARY KEY,
    phone_number VARCHAR(64),
    email        VARCHAR(64),
    stars        INTEGER,
    address_id   INTEGER NOT NULL
);

CREATE UNIQUE INDEX hotel__idx ON
    hotel (
           address_id
           ASC);

CREATE TABLE payment
(
    id         SERIAL PRIMARY KEY,
    timestamp  DATE,
    amount     NUMERIC(10, 2),
    booking_id INTEGER NOT NULL
);

CREATE TABLE rating
(
    id          SERIAL PRIMARY KEY,
    "date"      DATE,
    star_rating INTEGER,
    text        TEXT,
    booking_id  INTEGER NOT NULL
);

CREATE UNIQUE INDEX rating__idx ON
    rating (
            booking_id
            ASC);

CREATE TABLE service_order
(
    service_order_id SERIAL PRIMARY KEY,
    timestamp        TIMESTAMP,
    booking_id       INTEGER NOT NULL,
    services_id      INTEGER NOT NULL
);

CREATE UNIQUE INDEX service_order__idx ON
    service_order (
                   services_id
                   ASC);

CREATE TABLE services
(
    id                SERIAL PRIMARY KEY,
    name              VARCHAR(64),
    price             NUMERIC(10, 2),
    currency_iso_code VARCHAR(32) NOT NULL
);

CREATE UNIQUE INDEX services__idx ON
    services (
              currency_iso_code
              ASC);

-- FOREIGN KEY constraints
ALTER TABLE address
    ADD CONSTRAINT address_city_fk FOREIGN KEY (city_id)
        REFERENCES city (id);

ALTER TABLE apartment
    ADD CONSTRAINT apartment_currency_fk FOREIGN KEY (currency_iso_code)
        REFERENCES currency (iso_code);

ALTER TABLE apartment_feature
    ADD CONSTRAINT apartment_feature_apartment_fk FOREIGN KEY (apartment_id)
        REFERENCES apartment (id);

ALTER TABLE apartment_feature
    ADD CONSTRAINT apartment_feature_feature_fk FOREIGN KEY (feature_name)
        REFERENCES feature (name);

ALTER TABLE apartment
    ADD CONSTRAINT apartment_hotel_fk FOREIGN KEY (hotel_id)
        REFERENCES hotel (id);

ALTER TABLE booking
    ADD CONSTRAINT booking_apartment_fk FOREIGN KEY (apartment_id)
        REFERENCES apartment (id);

ALTER TABLE booking
    ADD CONSTRAINT booking_customer_fk FOREIGN KEY (customer_id)
        REFERENCES customer (id);

ALTER TABLE city
    ADD CONSTRAINT city_country_fk FOREIGN KEY (country_id)
        REFERENCES country (id);

ALTER TABLE complaint
    ADD CONSTRAINT complaint_booking_fk FOREIGN KEY (booking_id)
        REFERENCES booking (id);

ALTER TABLE customer
    ADD CONSTRAINT customer_address_fk FOREIGN KEY (address_id)
        REFERENCES address (id);

ALTER TABLE hotel
    ADD CONSTRAINT hotel_address_fk FOREIGN KEY (address_id)
        REFERENCES address (id);

ALTER TABLE payment
    ADD CONSTRAINT payment_booking_fk FOREIGN KEY (booking_id)
        REFERENCES booking (id);

ALTER TABLE rating
    ADD CONSTRAINT rating_booking_fk FOREIGN KEY (booking_id)
        REFERENCES booking (id);

ALTER TABLE service_order
    ADD CONSTRAINT service_order_booking_fk FOREIGN KEY (booking_id)
        REFERENCES booking (id);

ALTER TABLE service_order
    ADD CONSTRAINT service_order_services_fk FOREIGN KEY (services_id)
        REFERENCES services (id);

ALTER TABLE services
    ADD CONSTRAINT services_currency_fk FOREIGN KEY (currency_iso_code)
        REFERENCES currency (iso_code);

CREATE SEQUENCE service_order_service_order_id START WITH 1;

COMMIT;