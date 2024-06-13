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


INSERT INTO rating (timestamp, star_rating, text, booking_id)
VALUES ('2024-06-22', 1, 'Awful', 3);


INSERT INTO rating (timestamp, star_rating, text, booking_id)
VALUES ('2024-06-22', 2, 'bad', 2); -- updates the average properly


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

-- Checking for conflicting bookings with ('2024-04-01', '2024-04-07', 2, 1, 1)

-- should be nonempty
SELECT *
FROM get_conflicting_bookings(1, '2024-03-01', '2024-05-01');
SELECT *
FROM get_conflicting_bookings(1, '2024-03-01', '2024-04-03');
SELECT *
FROM get_conflicting_bookings(1, '2024-04-01', '2024-04-07');
SELECT *
FROM get_conflicting_bookings(1, '2024-04-02', '2024-04-07');
SELECT *
FROM get_conflicting_bookings(1, '2024-04-01', '2024-04-06');
SELECT *
FROM get_conflicting_bookings(1, '2024-04-02', '2024-04-06');
--should be empty
SELECT *
FROM get_conflicting_bookings(1, '2024-03-01', '2024-03-03');
SELECT *
FROM get_conflicting_bookings(1, '2024-05-01', '2024-05-03');

-- List all cities with their corresponding countries
SELECT city.id, city.name AS city_name, country.name AS country_name
FROM city
         JOIN country ON city.country_id = country.id
ORDER BY city.name;

-- List all customers with their addresses, cities, and countries
SELECT customer.id,
       customer.name,
       customer.surname,
       address.street,
       address.zip_code,
       city.name    AS city_name,
       country.name AS country_name
FROM customer
         JOIN address ON customer.address_id = address.id
         JOIN city ON address.city_id = city.id
         JOIN country ON city.country_id = country.id
ORDER BY customer.surname, customer.name;

-- List all hotels with their addresses and cities
SELECT hotel.id, hotel.name, hotel.stars, address.street, address.zip_code, city.name AS city_name
FROM hotel
         JOIN address ON hotel.address_id = address.id
         JOIN city ON address.city_id = city.id
ORDER BY hotel.stars DESC, hotel.name;

-- List all apartments with their hotels and prices per day, ordered by price descending
SELECT apartment.id, apartment.n_rooms, apartment.price_per_day, hotel.name AS hotel_name
FROM apartment
         JOIN hotel ON apartment.hotel_id = hotel.id
ORDER BY apartment.price_per_day DESC;

-- List all extra services available at each hotel
SELECT hotel.name         AS hotel_name,
       extra_service.name AS service_name,
       extra_service.price,
       currency.name      AS currency_name
FROM available_service
         JOIN extra_service ON available_service.extra_service_id = extra_service.id
         JOIN hotel ON available_service.hotel_id = hotel.id
         JOIN currency ON extra_service.currency_iso_code = currency.iso_code
ORDER BY hotel.name, extra_service.name;

-- List the total number of bookings for each apartment
SELECT apartment.id, apartment.n_rooms, hotel.name AS hotel_name, COUNT(booking.id) AS total_bookings
FROM apartment
         LEFT JOIN booking ON apartment.id = booking.apartment_id
         JOIN hotel ON apartment.hotel_id = hotel.id
GROUP BY apartment.id, apartment.n_rooms, hotel.name
ORDER BY total_bookings DESC;

-- Find the average rating for each hotel
SELECT hotel.name, AVG(rating.star_rating) AS avg_rating
FROM hotel
         JOIN apartment ON hotel.id = apartment.hotel_id
         JOIN booking ON apartment.id = booking.apartment_id
         JOIN rating ON booking.id = rating.booking_id
GROUP BY hotel.name
ORDER BY avg_rating DESC;

-- List all customers with their total amount spent on bookings
SELECT customer.id, customer.name, customer.surname, SUM(payment.amount) AS total_spent
FROM customer
         JOIN booking ON customer.id = booking.customer_id
         JOIN payment ON booking.id = payment.booking_id
GROUP BY customer.id, customer.name, customer.surname
ORDER BY total_spent DESC;

-- List all hotels with the total earnings from bookings
SELECT hotel.name, SUM(payment.amount) AS total_earnings
FROM hotel
         JOIN apartment ON hotel.id = apartment.hotel_id
         JOIN booking ON apartment.id = booking.apartment_id
         JOIN payment ON booking.id = payment.booking_id
GROUP BY hotel.name
ORDER BY total_earnings DESC;

-- List all apartments with their total and average earnings
SELECT apartment.id,
       apartment.n_rooms,
       hotel.name          AS hotel_name,
       SUM(payment.amount) AS total_earnings,
       AVG(payment.amount) AS avg_earnings
FROM apartment
         JOIN hotel ON apartment.hotel_id = hotel.id
         JOIN booking ON apartment.id = booking.apartment_id
         JOIN payment ON booking.id = payment.booking_id
GROUP BY apartment.id, apartment.n_rooms, hotel.name
ORDER BY total_earnings DESC;

-- Find all bookings that conflict with a given date range for a specific apartment
SELECT *
FROM get_conflicting_bookings(1, '2023-06-01', '2023-06-10');

-- List all apartments with their features
SELECT apartment.id, apartment.n_rooms, feature.name AS feature_name
FROM apartment
         JOIN available_feature ON apartment.id = available_feature.apartment_id
         JOIN feature ON available_feature.feature_name = feature.name
ORDER BY apartment.id, feature.name;

-- List all customers with bookings that have complaints
SELECT customer.id, customer.name, customer.surname, complaint.text
FROM customer
         JOIN booking ON customer.id = booking.customer_id
         JOIN complaint ON booking.id = complaint.booking_id
ORDER BY customer.surname, customer.name;

-- Find the top 5 highest rated apartments
SELECT apartment.id, apartment.n_rooms, hotel.name AS hotel_name, apartment.avg_rating
FROM apartment
         JOIN hotel ON apartment.hotel_id = hotel.id
ORDER BY apartment.avg_rating DESC
LIMIT 5;

-- List all payments with the booking details and customer names
SELECT payment.id,
       payment.timestamp,
       payment.amount,
       booking.start_date,
       booking.end_date,
       customer.name,
       customer.surname
FROM payment
         JOIN booking ON payment.booking_id = booking.id
         JOIN customer ON booking.customer_id = customer.id
ORDER BY payment.timestamp DESC;

-- List all hotels with the number of complaints they received
SELECT hotel.name, COUNT(complaint.id) AS total_complaints
FROM hotel
         JOIN apartment ON hotel.id = apartment.hotel_id
         JOIN booking ON apartment.id = booking.apartment_id
         JOIN complaint ON booking.id = complaint.booking_id
GROUP BY hotel.name
ORDER BY total_complaints DESC;

-- List all customers who have booked more than once
SELECT customer.id, customer.name, customer.surname, COUNT(booking.id) AS total_bookings
FROM customer
         JOIN booking ON customer.id = booking.customer_id
GROUP BY customer.id, customer.name, customer.surname
HAVING COUNT(booking.id) > 1
ORDER BY total_bookings DESC;

-- Find all hotels that have no available extra services
SELECT hotel.id, hotel.name
FROM hotel
         LEFT JOIN available_service ON hotel.id = available_service.hotel_id
WHERE available_service.extra_service_id IS NULL;

-- List all bookings along with the total price paid including extra services
SELECT booking.id,
       booking.start_date,
       booking.end_date,
       customer.name,
       customer.surname,
       SUM(payment.amount) AS total_paid
FROM booking
         JOIN customer ON booking.customer_id = customer.id
         JOIN payment ON booking.id = payment.booking_id
GROUP BY booking.id, booking.start_date, booking.end_date, customer.name, customer.surname
ORDER BY total_paid DESC;

-- List the most popular extra services
SELECT extra_service.name, COUNT(extra_service_order.id) AS total_orders
FROM extra_service
         JOIN available_service ON extra_service.id = available_service.extra_service_id
         JOIN extra_service_order
              ON available_service.extra_service_id = extra_service_order.available_service_extra_service_id
GROUP BY extra_service.name
ORDER BY total_orders DESC;


-- Find customers who have booked all apartments in a specific hotel
SELECT customer.id, customer.name, customer.surname
FROM customer
         JOIN booking ON customer.id = booking.customer_id
         JOIN apartment ON booking.apartment_id = apartment.id
         JOIN hotel ON apartment.hotel_id = hotel.id
WHERE hotel.id = 1
GROUP BY customer.id, customer.name, customer.surname
HAVING COUNT(DISTINCT apartment.id) = (SELECT COUNT(*) FROM apartment WHERE hotel_id = 1);

-- List all hotels with their total bookings and total complaints, ordered by the total bookings descending
SELECT hotel.id,
       hotel.name,
       COUNT(DISTINCT booking.id)   AS total_bookings,
       COUNT(DISTINCT complaint.id) AS total_complaints
FROM hotel
         LEFT JOIN apartment ON hotel.id = apartment.hotel_id
         LEFT JOIN booking ON apartment.id = booking.apartment_id
         LEFT JOIN complaint ON booking.id = complaint.booking_id
GROUP BY hotel.id, hotel.name
ORDER BY total_bookings DESC, total_complaints DESC;

-- Find the top 3 hotels with the highest average rating and the lowest number of complaints
SELECT hotel.id, hotel.name, hotel.avg_rating, COUNT(complaint.id) AS total_complaints
FROM hotel
         LEFT JOIN apartment ON hotel.id = apartment.hotel_id
         LEFT JOIN booking ON apartment.id = booking.apartment_id
         LEFT JOIN complaint ON booking.id = complaint.booking_id
GROUP BY hotel.id, hotel.name, hotel.avg_rating
ORDER BY hotel.avg_rating DESC, total_complaints ASC
LIMIT 3;

-- List all customers who have booked apartments in different hotels and the number of different hotels they have booked
SELECT customer.id, customer.name, customer.surname, COUNT(DISTINCT hotel.id) AS different_hotels
FROM customer
         JOIN booking ON customer.id = booking.customer_id
         JOIN apartment ON booking.apartment_id = apartment.id
         JOIN hotel ON apartment.hotel_id = hotel.id
GROUP BY customer.id, customer.name, customer.surname
HAVING COUNT(DISTINCT hotel.id) > 1
ORDER BY different_hotels DESC;

-- List all hotels with their total earnings from bookings and extra services, ordered by total earnings descending
SELECT hotel.id,
       hotel.name,
       SUM(payment.amount) + COALESCE(SUM(extra_service.price), 0) AS total_earnings
FROM hotel
         LEFT JOIN apartment ON hotel.id = apartment.hotel_id
         LEFT JOIN booking ON apartment.id = booking.apartment_id
         LEFT JOIN payment ON booking.id = payment.booking_id
         LEFT JOIN extra_service_order ON booking.id = extra_service_order.booking_id
         LEFT JOIN extra_service ON extra_service_order.available_service_extra_service_id = extra_service.id
GROUP BY hotel.id, hotel.name
ORDER BY total_earnings DESC;

-- Find all apartments that have been booked for the entire year of 2023
SELECT apartment.id, apartment.n_rooms, apartment.price_per_day, hotel.name AS hotel_name
FROM apartment
         JOIN booking ON apartment.id = booking.apartment_id
         JOIN hotel ON apartment.hotel_id = hotel.id
WHERE booking.start_date <= '2023-01-01'
  AND booking.end_date >= '2023-12-31'
GROUP BY apartment.id, apartment.n_rooms, apartment.price_per_day, hotel.name;

-- List all customers with their average rating from all bookings, ordered by the highest average rating
SELECT customer.id, customer.name, customer.surname, AVG(rating.star_rating) AS avg_rating
FROM customer
         JOIN booking ON customer.id = booking.customer_id
         JOIN rating ON booking.id = rating.booking_id
GROUP BY customer.id, customer.name, customer.surname
ORDER BY avg_rating DESC;

-- List all hotels with their average rating and the average price per day of their apartments
SELECT hotel.id,
       hotel.name,
       AVG(apartment.avg_rating)    AS avg_rating,
       AVG(apartment.price_per_day) AS avg_price_per_day
FROM hotel
         JOIN apartment ON hotel.id = apartment.hotel_id
GROUP BY hotel.id, hotel.name
ORDER BY avg_rating DESC, avg_price_per_day ASC;

-- Find customers who have spent more than the average amount spent by all customers
WITH avg_spent AS (SELECT AVG(total_spent) AS avg_total_spent
                   FROM (SELECT customer.id, SUM(payment.amount) AS total_spent
                         FROM customer
                                  JOIN booking ON customer.id = booking.customer_id
                                  JOIN payment ON booking.id = payment.booking_id
                         GROUP BY customer.id) AS customer_total_spent)
SELECT customer.id, customer.name, customer.surname, SUM(payment.amount) AS total_spent
FROM customer
         JOIN booking ON customer.id = booking.customer_id
         JOIN payment ON booking.id = payment.booking_id
GROUP BY customer.id, customer.name, customer.surname
HAVING SUM(payment.amount) > (SELECT avg_total_spent FROM avg_spent)
ORDER BY total_spent DESC;

-- Find hotels with at least one booking and no complaints, ordered by the number of bookings descending
SELECT hotel.id, hotel.name, COUNT(booking.id) AS total_bookings
FROM hotel
         JOIN apartment ON hotel.id = apartment.hotel_id
         JOIN booking ON apartment.id = booking.apartment_id
         LEFT JOIN complaint ON booking.id = complaint.booking_id
GROUP BY hotel.id, hotel.name
HAVING COUNT(complaint.id) = 0
ORDER BY total_bookings DESC;
