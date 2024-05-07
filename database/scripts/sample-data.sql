-- Populate database with sample data

BEGIN TRANSACTION;

INSERT INTO country (name, code)
VALUES ('United States', 'US'),
       ('United Kingdom', 'UK'),
       ('France', 'FR'),
       ('Germany', 'DE'),
       ('Canada', 'CA');

INSERT INTO currency (iso_code, name)
VALUES ('USD', 'US Dollar'),
       ('GBP', 'British Pound'),
       ('EUR', 'Euro'),
       ('CAD', 'Canadian Dollar'),
       ('AUD', 'Australian Dollar');

INSERT INTO feature (name)
VALUES ('Wi-Fi'),
       ('Swimming Pool'),
       ('Gym'),
       ('Parking'),
       ('Breakfast');

INSERT INTO city (name, country_id)
VALUES ('New York', 1),
       ('London', 2),
       ('Paris', 3),
       ('Berlin', 4),
       ('Toronto', 5);

INSERT INTO address (street, zip_code, city_id)
VALUES ('123 Main St', '10001', 1),
       ('456 Park Ave', 'SW1A 1AA', 2),
       ('789 Rue de la Paix', '75001', 3),
       ('101 Friedrichstraße', '10117', 4),
       ('456 Bay St', 'M5J 2T3', 5);

INSERT INTO customer (name, surname, age, gender, email, phone_number, address_id)
VALUES ('John', 'Doe', 30, 'M', 'john.doe@example.com', '123-456-7890', 1),
       ('Jane', 'Smith', 25, 'F', 'jane.smith@example.com', '987-654-3210', 2),
       ('Michael', 'Johnson', 40, 'M', 'michael.johnson@example.com', '555-123-4567', 3),
       ('Emily', 'Brown', 35, 'F', 'emily.brown@example.com', '555-987-6543', 4),
       ('William', 'Davis', 28, 'M', 'william.davis@example.com', '444-567-8901', 5);

INSERT INTO hotel (phone_number, email, stars, address_id)
VALUES ('555-111-2222', 'hotel1@example.com', 4, 1),
       ('555-333-4444', 'hotel2@example.com', 5, 2),
       ('555-555-6666', 'hotel3@example.com', 3, 3),
       ('555-777-8888', 'hotel4@example.com', 4, 4),
       ('555-999-0000', 'hotel5@example.com', 5, 5);

INSERT INTO apartment (n_rooms, n_bathrooms, n_beds, max_n_people, area, price_per_day, hotel_id, currency_iso_code)
VALUES (2, 1, 1, 2, 80, 150.00, 1, 'USD'),
       (3, 2, 2, 4, 120, 250.00, 2, 'GBP'),
       (1, 1, 1, 1, 50, 100.00, 3, 'EUR'),
       (2, 1, 2, 3, 90, 200.00, 4, 'CAD'),
       (3, 2, 3, 6, 150, 300.00, 5, 'AUD');

INSERT INTO services (name, price, currency_iso_code)
VALUES ('Room Cleaning', 20.00, 'USD'),
       ('Breakfast', 15.00, 'USD'),
       ('Spa', 50.00, 'USD'),
       ('Parking', 10.00, 'USD'),
       ('Laundry', 25.00, 'USD');

INSERT INTO available_service (services_id, hotel_id)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 4),
       (5, 5);

INSERT INTO available_feature (feature_name, apartment_id)
VALUES ('Wi-Fi', 1),
       ('Parking', 2),
       ('Swimming Pool', 3),
       ('Gym', 4),
       ('Breakfast', 5);

INSERT INTO booking (start_date, end_date, n_people, customer_id, apartment_id)
VALUES ('2024-04-01', '2024-04-07', 2, 1, 1),
       ('2024-05-15', '2024-05-20', 3, 2, 2),
       ('2024-06-10', '2024-06-17', 1, 3, 3),
       ('2024-07-20', '2024-07-25', 2, 4, 4),
       ('2024-08-05', '2024-08-12', 4, 5, 5),
       ('2024-09-07', '2024-09-14', 3, 4, 5);

INSERT INTO payment (timestamp, amount, booking_id, currency_iso_code)
VALUES ('2024-03-29', 300.00, 1, 'USD'),
       ('2024-04-20', 500.00, 2, 'GBP'),
       ('2024-06-15', 200.00, 3, 'EUR'),
       ('2024-07-30', 400.00, 4, 'CAD'),
       ('2024-08-10', 600.00, 5, 'AUD');

INSERT INTO rating (timestamp, star_rating, text, booking_id)
VALUES ('2024-04-10', 4, 'Great experience!', 1),
       ('2024-05-25', 5, 'Excellent service!', 2),
       ('2024-06-15', 3, 'Average stay.', 3),
       ('2024-07-25', 4, 'Enjoyed the amenities.', 4),
       ('2024-08-20', 5, 'Highly recommended!', 5);

INSERT INTO complaint (timestamp, text, booking_id)
VALUES ('2024-04-05', 'No hot water in the shower.', 1),
       ('2024-05-18', 'Room was not cleaned properly.', 2),
       ('2024-06-25', 'AC was not working.', 3),
       ('2024-07-22', 'Noise from the street.', 4),
       ('2024-08-15', 'Uncomfortable mattress.', 5);

INSERT INTO service_order (timestamp, booking_id, available_service_services_id, available_service_hotel_id)
VALUES ('2024-03-01 08:00:00', 1, 1, 1),
       ('2024-03-02 09:00:00', 2, 2, 2),
       ('2024-03-03 10:00:00', 3, 3, 3),
       ('2024-03-04 11:00:00', 4, 4, 4),
       ('2024-03-05 12:00:00', 5, 5, 5);

COMMIT;