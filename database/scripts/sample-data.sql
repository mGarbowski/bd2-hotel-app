BEGIN TRANSACTION;

-- Populate country table
INSERT INTO country (id, name, code)
VALUES (1, 'United States', 'US'),
       (2, 'France', 'FR'),
       (3, 'United Kingdom', 'UK');

-- Populate currency table
INSERT INTO currency (iso_code, name)
VALUES ('USD', 'US Dollar'),
       ('EUR', 'Euro'),
       ('GBP', 'British Pound');

-- Populate city table
INSERT INTO city (id, name, country_id)
VALUES (1, 'New York', 1),
       (2, 'Paris', 2),
       (3, 'London', 3);

-- Populate address table
INSERT INTO address (id, street, zip_code, city_id)
VALUES (1, '123 Main St', '12345', 1),
       (2, '456 Elm St', '23456', 2),
       (3, '789 Oak St', '34567', 3);

-- Populate hotel table
INSERT INTO hotel (id, phone_number, email, stars, address_id)
VALUES (1, '555-123-4567', 'hotel1@example.com', 4, 1),
       (2, '333-789-0123', 'hotel2@example.com', 5, 2),
       (3, '777-456-7890', 'hotel3@example.com', 3, 3);

-- Populate customer table
INSERT INTO customer (id, name, surname, age, gender, email, phone_number, address_id)
VALUES (1, 'John', 'Doe', 30, 'M', 'john.doe@example.com', '123-456-7890', 1),
       (2, 'Alice', 'Smith', 25, 'F', 'alice.smith@example.com', '987-654-3210', 2),
       (3, 'Bob', 'Johnson', 35, 'M', 'bob.johnson@example.com', '456-789-0123', 3);

-- Populate apartment table
INSERT INTO apartment (id, no_rooms, no_bathrooms, no_beds, max_no_people, area, price_per_day, hotel_id,
                       currency_iso_code)
VALUES (1, 2, 1, 2, 4, 80, 120.00, 1, 'USD'),
       (2, 3, 2, 3, 6, 120, 200.00, 2, 'EUR'),
       (3, 1, 1, 1, 2, 50, 80.00, 3, 'GBP');

-- Populate feature table
INSERT INTO feature (name)
VALUES ('Balcony'),
       ('Swimming Pool'),
       ('Garden View');

-- Populate apartment_feature table
INSERT INTO apartment_feature (feature_name, apartment_id)
VALUES ('Balcony', 1),
       ('Swimming Pool', 2),
       ('Garden View', 3);

-- Populate booking table
INSERT INTO booking (id, start_date, end_date, no_people, customer_id, apartment_id)
VALUES (1, '2024-04-01', '2024-04-05', 2, 1, 1),
       (2, '2024-04-15', '2024-04-20', 4, 2, 2),
       (3, '2024-05-01', '2024-05-05', 1, 3, 3);

-- Populate complaint table
INSERT INTO complaint (id, "date", text, booking_id)
VALUES (1, '2024-04-03', 'No hot water in the bathroom.', 1),
       (2, '2024-04-18', 'Room was not cleaned properly.', 2),
       (3, '2024-05-03', 'Uncomfortable bed.', 3);

-- Populate feature table
INSERT INTO feature (name)
VALUES ('Wi-Fi'),
       ('Breakfast Included'),
       ('Parking Available');

-- Populate payment table
INSERT INTO payment (id, timestamp, amount, booking_id)
VALUES (1, '2024-04-05', 480.00, 1),
       (2, '2024-04-20', 1000.00, 2),
       (3, '2024-05-05', 160.00, 3);

-- Populate rating table
INSERT INTO rating (id, "date", star_rating, text, booking_id)
VALUES (1, '2024-04-05', 4, 'Overall good experience.', 1),
       (2, '2024-04-20', 5, 'Excellent service and amenities.', 2),
       (3, '2024-05-05', 3, 'Average stay.', 3);

-- Populate services table
INSERT INTO services (id, name, price, currency_iso_code)
VALUES (1, 'Wi-Fi', 10.00, 'USD'),
       (2, 'Breakfast', 20.00, 'EUR'),
       (3, 'Parking', 15.00, 'GBP');

-- Populate service_order table
INSERT INTO service_order (timestamp, booking_id, service_order_id, services_id)
VALUES ('2024-04-03', 1, 1, 1),
       ('2024-04-18', 2, 2, 2),
       ('2024-05-03', 3, 3, 3);

COMMIT;