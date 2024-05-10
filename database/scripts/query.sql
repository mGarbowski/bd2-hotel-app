SELECT *
FROM country;


SELECT *
FROM hotel;

SELECT b.apartment_id, count(rating.id), sum(rating.star_rating)
FROM rating
         JOIN public.booking b ON rating.booking_id = b.id
GROUP BY b.apartment_id;


INSERT INTO apartment (n_rooms, n_bathrooms, n_beds, max_n_people, area, price_per_day, hotel_id, currency_iso_code)
VALUES (3, 2, 3, 6, 150, 300.00, 5, 'USD');

INSERT INTO hotel (phone_number, email, stars, address_id)
VALUES ('111-222-3333', 'hotel6@example.com', 3, 2);


INSERT INTO rating (timestamp, star_rating, text, booking_id)
VALUES ('2024-06-22', 1, 'Awful', 3);


INSERT INTO rating (timestamp, star_rating, text, booking_id)
VALUES ('2024-06-22', 2, 'bad', 2); -- updates the average properly

INSERT INTO rating (timestamp, star_rating, text, booking_id)
VALUES ('2024-06-22', 2, 'bad', 15); -- not possible thanks to forign key constraint


SELECT *
FROM hotel;


DELETE
FROM rating
WHERE star_rating = 3; -- also updates the averages

UPDATE rating
SET star_rating = 5
WHERE star_rating = 1; -- update works properly


SELECT *
FROM hotel;

SELECT *
FROM apartment;

