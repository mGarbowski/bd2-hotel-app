CREATE TABLE hotels
(
    id   SERIAL PRIMARY KEY,
    name TEXT NOT NULL
);


INSERT INTO hotels (name)
VALUES ('Hotel A'),
       ('Hotel B'),
       ('Hotel C');


SELECT * FROM hotels;