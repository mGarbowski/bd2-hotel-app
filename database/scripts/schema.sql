-- Create database schema
-- All tables, constraints, views, indexes

BEGIN TRANSACTION;

CREATE TABLE country
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL,
    code VARCHAR NOT NULL
);

CREATE TABLE currency
(
    iso_code VARCHAR(32) PRIMARY KEY,
    name     VARCHAR(32) NOT NULL
);

CREATE TABLE feature
(
    name VARCHAR NOT NULL PRIMARY KEY
);

CREATE TABLE city
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(64) NOT NULL,
    country_id INTEGER     NOT NULL,
    CONSTRAINT city_country_fk FOREIGN KEY (country_id) REFERENCES country (id)
);

CREATE TABLE address
(
    id       SERIAL PRIMARY KEY,
    street   VARCHAR(64) NOT NULL,
    zip_code VARCHAR(64) NOT NULL,
    city_id  INTEGER     NOT NULL,
    CONSTRAINT address_city_fk FOREIGN KEY (city_id) REFERENCES city (id)
);

CREATE TABLE customer
(
    id           SERIAL PRIMARY KEY,
    name         VARCHAR(64) NOT NULL,
    surname      VARCHAR(64) NOT NULL,
    age          INTEGER     NOT NULL,
    gender       CHAR(1)     NOT NULL,
    email        VARCHAR(64) NOT NULL,
    phone_number VARCHAR(64) NOT NULL,
    address_id   INTEGER     NOT NULL,
    CONSTRAINT customer_address_fk FOREIGN KEY (address_id) REFERENCES address (id)
);

CREATE TABLE hotel
(
    id           SERIAL PRIMARY KEY,
    phone_number VARCHAR(64) NOT NULL,
    email        VARCHAR(64) NOT NULL,
    stars        INTEGER     NOT NULL,
    address_id   INTEGER     NOT NULL,
    avg_rating   NUMERIC(5, 2),
    CONSTRAINT hotel_address_fk FOREIGN KEY (address_id) REFERENCES address (id)
);

CREATE TABLE apartment
(
    id                SERIAL PRIMARY KEY,
    n_rooms           INTEGER        NOT NULL,
    n_bathrooms       INTEGER        NOT NULL,
    n_beds            INTEGER        NOT NULL,
    max_n_people      INTEGER        NOT NULL,
    area              INTEGER        NOT NULL,
    price_per_day     NUMERIC(10, 2) NOT NULL,
    hotel_id          INTEGER        NOT NULL,
    currency_iso_code VARCHAR(32)    NOT NULL,
    avg_rating        NUMERIC(5, 2),
    CONSTRAINT apartment_hotel_fk FOREIGN KEY (hotel_id) REFERENCES hotel (id),
    CONSTRAINT apartment_currency_fk FOREIGN KEY (currency_iso_code) REFERENCES currency (iso_code)
);

CREATE TABLE services
(
    id                SERIAL PRIMARY KEY,
    name              VARCHAR(64)    NOT NULL,
    price             NUMERIC(10, 2) NOT NULL,
    currency_iso_code VARCHAR(32)    NOT NULL,
    CONSTRAINT services_currency_fk FOREIGN KEY (currency_iso_code) REFERENCES currency (iso_code)
);

CREATE TABLE available_service
(
    services_id INTEGER NOT NULL,
    hotel_id    INTEGER NOT NULL,
    CONSTRAINT available_service_pk PRIMARY KEY (services_id, hotel_id),
    CONSTRAINT available_service_hotel_fk FOREIGN KEY (hotel_id) REFERENCES hotel (id),
    CONSTRAINT available_service_services_fk FOREIGN KEY (services_id) REFERENCES services (id)
);

CREATE TABLE available_feature
(
    feature_name VARCHAR NOT NULL,
    apartment_id INTEGER NOT NULL,
    CONSTRAINT apartment_feature_pk PRIMARY KEY (apartment_id, feature_name),
    CONSTRAINT apartment_feature_apartment_fk FOREIGN KEY (apartment_id) REFERENCES apartment (id),
    CONSTRAINT apartment_feature_feature_fk FOREIGN KEY (feature_name) REFERENCES feature (name)
);

CREATE TABLE booking
(
    id           SERIAL PRIMARY KEY,
    start_date   DATE    NOT NULL,
    end_date     DATE    NOT NULL,
    n_people     INTEGER NOT NULL,
    customer_id  INTEGER NOT NULL,
    apartment_id INTEGER NOT NULL,
    CONSTRAINT booking_customer_fk FOREIGN KEY (customer_id) REFERENCES customer (id),
    CONSTRAINT booking_apartment_fk FOREIGN KEY (apartment_id) REFERENCES apartment (id)
);

CREATE TABLE payment
(
    id                SERIAL PRIMARY KEY,
    timestamp         DATE           NOT NULL,
    amount            NUMERIC(10, 2) NOT NULL,
    booking_id        INTEGER        NOT NULL,
    currency_iso_code VARCHAR(32)    NOT NULL,
    CONSTRAINT payment_booking_fk FOREIGN KEY (booking_id) REFERENCES booking (id),
    CONSTRAINT payment_currency_fk FOREIGN KEY (currency_iso_code) REFERENCES currency (iso_code)
);

CREATE TABLE rating
(
    id          SERIAL PRIMARY KEY,
    timestamp   DATE    NOT NULL,
    star_rating INTEGER NOT NULL,
    text        TEXT    NOT NULL,
    booking_id  INTEGER NOT NULL,
    CONSTRAINT rating_booking_fk FOREIGN KEY (booking_id) REFERENCES booking (id)
);

CREATE TABLE complaint
(
    id         SERIAL PRIMARY KEY,
    timestamp  DATE    NOT NULL,
    text       TEXT    NOT NULL,
    booking_id INTEGER NOT NULL,
    CONSTRAINT complaint_booking_fk FOREIGN KEY (booking_id) REFERENCES booking (id)
);

CREATE TABLE service_order
(
    id                            SERIAL PRIMARY KEY,
    timestamp                     TIMESTAMP,
    booking_id                    INTEGER NOT NULL,
    available_service_services_id INTEGER NOT NULL,
    available_service_hotel_id    INTEGER NOT NULL,
    CONSTRAINT service_order_booking_fk FOREIGN KEY (booking_id) REFERENCES booking (id),
    CONSTRAINT service_order_available_service_fk FOREIGN KEY (available_service_services_id, available_service_hotel_id) REFERENCES available_service (services_id, hotel_id)
);

COMMIT;

-- CREATE OR REPLACE function update_aparment_avg_rating()
CREATE OR REPLACE FUNCTION update_avg_ratings() RETURNS TRIGGER AS $$
DECLARE
    apt_id_v INTEGER;
    hotel_id_v INTEGER;
BEGIN
    RAISE NOTICE 'NEW RECORD %', new;
    RAISE NOTICE 'OLD RECORD %', old;

    -- check which apartment's rating is changing
    IF NEW is null then
        SELECT apartment_id
        INTO apt_id_v
        from booking
        where booking.id = OLD.booking_id;
        RAISE NOTICE 'OLD apartment id is %', apt_id_v;
    ELSE
        SELECT apartment_id
        INTO apt_id_v
        from booking
        where booking.id = NEW.booking_id;
        RAISE NOTICE 'NEW apartment id is %', apt_id_v;
    end if;

    UPDATE apartment
    SET avg_rating = (SELECT avg(rating.star_rating)
                      FROM rating join booking b on rating.booking_id = b.id
                      where b.apartment_id = apt_id_v
                      GROUP BY b.apartment_id)
    WHERE apartment.id = apt_id_v;

    SELECT apartment.hotel_id
    INTO hotel_id_v
    from apartment
    where apartment.id = apt_id_v;

    UPDATE hotel
    SET avg_rating = (SELECT avg(rating.star_rating)
                      FROM rating join booking b on rating.booking_id = b.id
                                  join apartment a on a.id = b.apartment_id
                      Where a.hotel_id = hotel_id_v
                      group by a.hotel_id)
    where hotel.id = hotel_id_v;
    RETURN new;
END;
$$ LANGUAGE plpgsql;

-- SELECT update_avg_ratings();
-- DROP FUNCTION update_avg_ratings();

create or replace trigger update_avg_rating_trigger
    AFTER INSERT OR UPDATE OR DELETE
    ON rating
    for EACH ROW
execute FUNCTION update_avg_ratings();


