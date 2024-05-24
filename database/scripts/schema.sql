-- Create database schema
-- All tables, constraints, views, indexes, functions, triggers

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
    id             SERIAL PRIMARY KEY,
    name           VARCHAR(64) NOT NULL,
    phone_number   VARCHAR(64) NOT NULL,
    email          VARCHAR(64) NOT NULL,
    stars          INTEGER     NOT NULL,
    address_id     INTEGER     NOT NULL,
    total_bookings INTEGER     NOT NULL DEFAULT 0,
    avg_rating     NUMERIC(5, 2)        DEFAULT 0,

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
    total_bookings    INTEGER        NOT NULL DEFAULT 0,
    avg_rating        NUMERIC(5, 2)  NOT NULL DEFAULT 0,
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

-- unique id required by JPA
CREATE VIEW payments_summary AS
SELECT gen_random_uuid()                                      AS id,
       b.id                                                   AS booking_id,
       'Reservation fee'                                      AS name,
       -1 * (b.end_date - b.start_date + 1) * a.price_per_day AS amount
FROM booking b
         JOIN apartment a on b.apartment_id = a.id
UNION ALL
SELECT gen_random_uuid() AS id, b.id AS booking_id, s.name, -1 * s.price
FROM services s
         JOIN available_service avs ON s.id = avs.services_id
         JOIN service_order so
              ON so.available_service_hotel_id = avs.hotel_id AND so.available_service_services_id = avs.services_id
         JOIN booking b ON so.booking_id = b.id
UNION ALL
SELECT gen_random_uuid() AS id, b.id AS booking_id, 'Payment' AS name, p.amount
FROM payment p
         JOIN booking b ON p.booking_id = b.id;



CREATE VIEW apartment_statistics AS
SELECT a.id                              AS apartment_id,
       COUNT(c.id)                       AS n_customers,
       COUNT(DISTINCT b.id)              AS n_bookings,
       COUNT(DISTINCT co.id)             AS n_complaints,
       coalesce(ps_sum.summed_amount, 0) AS total_earning,
       coalesce(ps_sum.avg_amount, 0)    AS avg_earning
FROM apartment a
         LEFT JOIN booking b on a.id = b.apartment_id
         LEFT JOIN customer c on b.customer_id = c.id
         LEFT JOIN complaint co on b.id = co.booking_id
         JOIN (SELECT a.id                as apartment_id,
                      SUM(-1 * ps.amount) AS summed_amount,
                      AVG(-1 * ps.amount) AS avg_amount
               FROM apartment a
                        LEFT JOIN booking b on a.id = b.apartment_id
                        LEFT JOIN payments_summary ps on b.id = ps.booking_id
               WHERE ps.amount < 0
                  OR ps.amount IS NULL
               GROUP BY a.id) ps_sum on a.id = ps_sum.apartment_id
GROUP BY a.id, ps_sum.summed_amount, ps_sum.avg_amount;


CREATE VIEW hotel_statistics AS
SELECT h.id                 AS hotel_id,
       COUNT(c2.id)         AS n_customers,
       h.total_bookings     AS n_bookings,
       COUNT(DISTINCT c.id) AS n_complaints,
        ps_summed.summed_amount AS total_earning
FROM hotel h
         JOIN apartment a on a.hotel_id = h.id
         LEFT JOIN booking b on a.id = b.apartment_id
         LEFT JOIN complaint c on b.id = c.booking_id
         LEFT JOIN customer c2 on b.customer_id = c2.id
         JOIN (SELECT h2.id                            as hotel_id,
                      COALESCE(SUM(-1 * ps.amount), 0) AS summed_amount
               FROM hotel h2
                        JOIN apartment a2 on h2.id = a2.hotel_id
                        JOIN booking b2 on a2.id = b2.apartment_id
                        LEFT JOIN payments_summary ps on b2.id = ps.booking_id
               WHERE ps.amount < 0
                  OR ps.amount IS NULL
               GROUP BY h2.id) ps_summed on h.id = ps_summed.hotel_id
GROUP BY h.id,ps_summed.summed_amount;


CREATE FUNCTION increment_total_bookings()
    RETURNS TRIGGER
    LANGUAGE plpgsql
AS
$$
BEGIN
    UPDATE apartment
    SET total_bookings = total_bookings + 1
    WHERE id = new.apartment_id;

    UPDATE hotel
    SET total_bookings = total_bookings + 1
    WHERE id = hotel_id_from_apt_id(new.apartment_id);
    RETURN NEW;
END;
$$;


CREATE FUNCTION hotel_id_from_apt_id(apt_id INTEGER) returns integer
    LANGUAGE SQL AS
$$
SELECT hotel_id
from apartment
where id = apt_id;
$$;

CREATE TRIGGER increment_total_bookings_trigger
    AFTER INSERT
    ON booking
    FOR EACH ROW
EXECUTE PROCEDURE
    increment_total_bookings();


CREATE OR REPLACE FUNCTION get_conflicting_bookings(apartment_id_param INT, start_date_param DATE, end_date_param DATE)
    RETURNS SETOF booking
AS
$$
BEGIN
    RETURN QUERY
        SELECT *
        FROM booking b
        WHERE b.apartment_id = apartment_id_param
          AND (
            (start_date_param <= b.start_date AND b.start_date <= end_date_param)
                OR (b.start_date <= start_date_param AND end_date_param <= b.end_date)
                OR (b.start_date <= end_date_param AND end_date_param <= b.end_date)
                OR (start_date_param <= b.start_date AND b.end_date <= end_date_param)
            );
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION update_avg_ratings() RETURNS TRIGGER AS
$$
DECLARE
    apt_id_v   INTEGER;
    hotel_id_v INTEGER;
BEGIN

    -- check which apartment's rating is changing
    IF NEW is null then
        SELECT apartment_id
        INTO apt_id_v
        from booking
        where booking.id = OLD.booking_id;
    ELSE
        SELECT apartment_id
        INTO apt_id_v
        from booking
        where booking.id = NEW.booking_id;
    end if;

    UPDATE apartment
    SET avg_rating = (SELECT avg(rating.star_rating)
                      FROM rating
                               join booking b on rating.booking_id = b.id
                      where b.apartment_id = apt_id_v
                      GROUP BY b.apartment_id)
    WHERE apartment.id = apt_id_v;

    SELECT apartment.hotel_id
    INTO hotel_id_v
    from apartment
    where apartment.id = apt_id_v;

    UPDATE hotel
    SET avg_rating = (SELECT avg(rating.star_rating)
                      FROM rating
                               join booking b on rating.booking_id = b.id
                               join apartment a on a.id = b.apartment_id
                      Where a.hotel_id = hotel_id_v
                      group by a.hotel_id)
    where hotel.id = hotel_id_v;
    RETURN new;
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE TRIGGER update_avg_rating_trigger
    AFTER INSERT OR UPDATE OR DELETE
    ON rating
    for EACH ROW
execute FUNCTION update_avg_ratings();

COMMIT;
