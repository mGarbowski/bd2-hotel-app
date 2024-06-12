INSERT INTO hotel (phone_number, email, stars, address_id) -- violates not null constraint, will not succeed
VALUES ('111-222-3333', 'hotel6@example.com', 3, 2);

INSERT INTO rating (timestamp, star_rating, text, booking_id)
VALUES ('2024-06-22', 2, 'bad', 15);
-- not possible thanks to foreign key constraint
-- Attempt to insert an apartment with a non-existent hotel_id
INSERT INTO apartment (n_rooms, n_bathrooms, n_beds, max_n_people, area, price_per_day, hotel_id, currency_iso_code)
VALUES (3, 2, 3, 6, 75, 150.00, 999, 'USD');

-- Attempt to insert an extra_service_order with a non-existent booking_id and available_service combination
INSERT INTO extra_service_order (timestamp, booking_id, available_service_extra_service_id, available_service_hotel_id)
VALUES ('2024-07-01 12:00:00', 1, 999, 1);

-- Attempt to insert an available_feature with a non-existent apartment_id and feature_name combination
INSERT INTO available_feature (apartment_id, feature_name)
VALUES (999, 'NonExistentFeature');

-- Attempt to insert a rating with a non-existent booking_id
INSERT INTO rating (timestamp, star_rating, text, booking_id)
VALUES ('2024-07-01', 5, 'Excellent stay!', 999);

-- Attempt to insert a complaint with a non-existent booking_id
INSERT INTO complaint (timestamp, text, booking_id)
VALUES ('2024-07-01', 'Room was too noisy.', 999);

-- Attempt to insert an available_service with a non-existent hotel_id
INSERT INTO available_service (extra_service_id, hotel_id)
VALUES (1, 999);

-- Attempt to insert a booking with overlapping dates for the same apartment
INSERT INTO booking (start_date, end_date, n_people, customer_id, apartment_id)
VALUES ('2024-07-01', '2024-07-10', 2, 1, 1);
-- Assuming apartment_id 1 already has a booking from '2024-07-05' to '2024-07-15'

-- Attempt to delete a country that is referenced by a city
DELETE
FROM country
WHERE id = 1;
-- Assuming country_id 1 is referenced by a city

-- Attempt to delete a city that is referenced by an address
DELETE
FROM city
WHERE id = 1;
-- Assuming city_id 1 is referenced by an address

-- Attempt to delete a hotel that is referenced by an apartment
DELETE
FROM hotel
WHERE id = 1;
-- Assuming hotel_id 1 is referenced by an apartment

