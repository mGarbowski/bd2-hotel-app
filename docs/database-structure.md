# The design and purpose of the database

The database created by the database/scripts/schema.sql script is an operational database designed to manage
the operations of a hotel network. The design of the database is focused on booking a hotel room by a client,
storing all the relevant information about a booking, and keeping a record of transactions regarding that booking.
It also allows for storing the reviews clients might write on a site such as booking.com.

Additionally, the database has several analytic features that make overseeing the business more efficient and allow
the owner to identify best and worst performing hotels, locations, apartments, etc. And make managing finances easier.

These analytic features include:
The apartment_statistics, hotel_statistics and payments_summary views,
avg_rating and total_bookings columns in Apartment and Hotel tables.

The database scripts are written in the PostreSQL dialect, as that is the database system the team decided on and
recommend using. (setting up PostreSQL using Docker in [./build-and-run.md](./build-and-run.md))

# The tables

1. **country**: Stores information about countries, including their names and codes. Used for associating locations with
   countries.

2. **currency**: Contains information about different currencies, including their ISO codes and names. Used for handling
   various currencies in transactions and pricing.

3. **feature**: Lists distinct features that apartments can have (e.g., Wi-Fi, air conditioning). Acts as a catalog of
   possible features.

4. **city**: Holds information about cities, including their names and associated country. Helps in organizing addresses
   and locations geographically.

5. **address**: Stores detailed address information, including street names, zip codes, and associated cities. Used to
   locate hotels and customers.

6. **customer**: Keeps personal information about customers, such as name, age, gender, contact information, and
   address. Used for managing customer details.

7. **hotel**: Contains information about hotels, including their contact details, star rating, address, total bookings,
   and average rating. Central to the hotel's details.

8. **apartment**: Holds information about individual apartments within hotels, such as number of rooms, bathrooms, beds,
   maximum capacity, area, price per day, hotel association, and currency for pricing. Used for managing the different
   rental units available.

9. **extra_service**: Lists extra services offered by hotels (e.g., spa, gym access), including their names and prices
   in specific currencies. Used for managing optional services that guests can purchase.

10. **available_service**: Represents a many-to-many relationship between extra services and hotels, indicating which
    extra services are available at which hotels.

11. **available_feature**: Represents a many-to-many relationship between features and apartments, indicating which
    features are available in which apartments.

12. **booking**: Manages reservations made by customers, including start and end dates, number of people, customer
    details, and the apartment booked. Central to tracking reservations.

13. **payment**: Stores payment transactions for bookings, including the amount, timestamp, booking reference, and
    currency used. Essential for financial tracking.

14. **rating**: Captures customer ratings for their bookings, including the rating, review text, and booking reference.
    Used for feedback and quality assessment.

15. **complaint**: Records customer complaints associated with bookings, including the complaint text and timestamp.
    Used for handling customer issues and feedback.

16. **extra_service_order**: Tracks orders for extra services made during bookings, including the service ordered,
    booking reference, and timestamp. Manages additional service usage by guests.

Each table serves a specific role in the overall structure, ensuring comprehensive management of the hotel network's
data, from geographical and personal details to bookings, payments, and customer feedback.

# Summary of triggers and functions:

## Triggers

### increment_total_bookings_trigger:

Triggered after a new booking is inserted into the booking table.
Calls increment_total_bookings() to increment the total_bookings counters in the appropriate records of the apartment
and hotel tables.

### update_avg_rating_trigger:

Triggered after any insert, update, or delete on the rating table.
Calls update_avg_ratings() to recalculate and update the average_rating in the respectable apartment and hotel based on
the latest ratings data.

## Functions

### get_conflicting_bookings(apartment_id_param, start_date_param, end_date_param):

A function designed to be called when checking for booking conflicts.
Returns a set of bookings that overlap with the specified date range for the given apartment.

1. **View: `payments_summary`**:
    - Combines information from bookings, apartments, extra services, and payments.
    - Summarizes all financial transactions related to a booking, including reservation fees, extra service charges, and
      payments.
    - Uses a generated UUID to ensure a unique identifier for each entry, required by JPA.

2. **View: `apartment_statistics`**:
    - Provides statistical information about each apartment. This view includes data on the number of
      customers, bookings, complaints, total earnings, and average earnings for each apartment. It aggregates data from
      bookings, customers, complaints, and the `payments_summary` view to offer a comprehensive overview of each
      apartment's performance and customer interaction.

3. **View: `hotel_statistics`**:
    - Aggregates data on the hotel's name, contact details, average rating, address, number of customers, total
      bookings, and complaints.
    - Calculates total earnings from the `payments_summary` view.
    - Provides a comprehensive statistical overview of each hotel's performance, financial results, and customer
      interaction.

These views are designed to facilitate easy access to aggregated and detailed statistical data for apartments and
hotels, assisting in performance monitoring and financial analysis.