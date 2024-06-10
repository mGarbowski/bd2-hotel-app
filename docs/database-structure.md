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




# The tables
