# Hotel app documentation

## Overview
This document provides details on the available commands, their arguments, usage and expected output.

## Address commands

### List All Addresses

* **Command**: `address list`
* **Description**: This command retrieves and lists all the addresses stored in the system.
* **Example usage**: 
```
shell:>address list
[1] 123 Main St, 10001, New York, United States
[2] 456 Park Ave, SW1A 1AA, London, United Kingdom
[3] 789 Rue de la Paix, 75001, Paris, France
[4] 101 Friedrichstraße, 10117, Berlin, Germany
```

## Apartment commands

### Search Free Apartments in City

* **Command**: `apartment searchCity`
* **Description**: Searches for free apartments in a given city for a specified period. If apartments are found, it returns information about available apartments. If no apartments are available for the specified period, it returns a message indicating their unavailability.
* **Arguments**:
    - `city` (String): The city where apartments are to be searched.
    - `start` (Date in the format `YYYY-MM-DD`): The start date for the search period.
    - `end` (Date in the format `YYYY-MM-DD`): The end date for the search period.
* **Example Usage**: 
```
shell:>apartment searchCity London 2024-05-28 2024-05-31
    Hotel: Chimponello Estate – Apartment number [6] (0.00 stars) – Capacity: 7 person – Price per day: 170.00
        - Rooms: 5
        - Bathrooms: 1
        - Beds: 1
        - Address: 456 Park Ave, SW1A 1AA, London, United Kingdom 
```

## Booking commands

### Make Booking

* **Command**: `booking make`
* **Description**: Books an apartment for a specified period.
* **Arguments**:
  - `apartmentId` (Integer): The ID of the apartment to book.
  - `startDate` (Date in the format `YYYY-MM-DD`): The start date of the booking.
  - `endDate` (Date in the format `YYYY-MM-DD`): The end date of the booking.
  - `nPeople` (Integer): The number of people for the booking.
* **Note**: A user must be logged in to make a booking.
* **Example Usage**:
```
shell:>booking make 3 2024-07-19 2024-07-24 4
Booking complete
```

### Make Complaint

* **Command**: `booking makeComplaint`
* **Description**: Makes a complaint about a booking.
* **Arguments**:
  - `bookingId` (Integer): The ID of the booking to complain about.
  - `complaintText` (String): The text of the complaint.
* **Note**: A user must be logged in to make a complaint.
* **Example Usage**:
```
shell:>booking makeComplaint 9 There was no hot water
Complaint created
```

### Add Opinion

* **Command**: `booking addOpinion`
* **Description**: Adds an opinion to a booking.
* **Arguments**:
  - `bookingId` (Integer): The ID of the booking to add an opinion to.
  - `starRating` (Integer): The star rating for the opinion.
  - `opinionText` (String): The text of the opinion.
* **Note**: A user must be logged in to add an opinion.
* **Example Usage**:
```
shell:>booking addOpinion 9 5 Very nice room
Opinion added
```

### List Active Bookings

* **Command**: `booking list`
* **Description**: Lists the current user's active bookings.
* **Note**: A user must be logged in to view their active bookings.
* **Example Usage**: 
```
shell:>booking list
Upcoming:
[3] Chimponello Estate, London, 456 Park Ave for 2 people, from 2025-04-01 to 2025-04-07
[9] DeLaChumpee Manor, Paris, 789 Rue de la Paix for 4 people, from 2024-07-19 to 2024-07-24
```

## Country commands

### List All Countries

* **Command**: `country list`
* **Description**: Lists all countries.
* **Example Usage**:
```
shell:> country list
[United States, United Kingdom, France, Germany, Canada]
```

### Get Country Name by Code

* **Command**: `country byCode`
* **Description**: Retrieves the name of a country by its code.
* **Arguments**:
  - `code` (String): The code of the country.
* **Example Usage**: 
```
shell:>country byCode US
United States
```

## User commands

### Display Currently Logged-in User

* **Command**: `user who`
* **Description**: Displays information about the currently logged-in user.
* **Example Usage**:
```
shell:> user who
[1] John Doe
```

### Log in as an Existing Customer

* **Command**: `user login`
* **Description**: Logs in as an existing customer.
* **Arguments**:
  - `customerId` (Integer): The ID of the customer to log in as.
* **Example Usage**:
```
shell:>user login 2
Welcome Jane Smith!
```

### List All Customers

* **Command**: `user list`
* **Description**: Lists all customers.
* **Example Usage**: 
```
shell:>user list
[1] John Doe
[2] Jane Smith
[3] Michael Johnson
[4] Emily Brown
[5] William Davis
```

## Extra commands

### List Extra Services Available for Booking

* **Command**: `extra list`
* **Description**: Lists extra services available to order for a given booking.
* **Arguments**:
  - `bookingId` (Integer): The ID of the booking to list extra services for.
* **Note**: A user must be logged in to access bookings.
* **Example Usage**:
```
shell:>extra list 9
[3] Spa, 50.00USD
```

### Order Extra Service

* **Command**: `extra order`
* **Description**: Orders an extra service.
* **Arguments**:
  - `name` (String): The name of the extra service to order.
  - `bookingId` (Integer): The ID of the booking to order the extra service for.
* **Note**: A user must be logged in to order.
* **Example Usage**: 
```
shell:>extra order Spa 9
Spa ordered successfully
```

## Payment commands

### Display Summary for Booking

* **Command**: `payment summary`
* **Description**: Displays a summary for a given booking, including payment details.
* **Arguments**:
  - `bookingId` (Integer): The ID of the booking to display the summary for.
* **Example Usage**:
```
shell:>payment summary 9
Reservation fee                        -600.00
Spa                                     -50.00
----------------------------------------------
TOTAL:                                 -650.00
```

### Make Payment for Booking

* **Command**: `payment make`
* **Description**: Makes a payment for a given booking.
* **Arguments**:
  - `bookingId` (Integer): The ID of the booking to make the payment for.
  - `amount` (BigDecimal): The amount to pay.
* **Note**: A user must be logged in to pay for a booking.
* **Example Usage**: 
```
shell:>payment make 9 650
Payment made successfully. Balance for the given booking after this payment is 0.00
```

## Statistics commands

### Show Statistics for All Apartments

* **Command**: `stats apartments`
* **Description**: Shows statistics for all apartments, including bookings, customers, complaints, total earnings, and average earnings.
* **Example Usage**:
```
shell:>stats apartments
ID	Bookings  	Customers 	Complaints	Total earnings 	Avg earnings
1 	1         	1         	1         	4520.00        	2260.00     
2 	3         	3         	3         	5075.00        	845.83      
3 	1         	1         	1         	875.00         	291.67      
4 	1         	1         	0         	1200.00        	1200.00     
5 	2         	2         	0         	4800.00        	2400.00     
6 	1         	1         	1         	695.00         	347.50      
7 	0         	0         	0         	0.00           	0.00        
14	0         	0         	0         	0.00           	0.00        
```

### Show Statistics for Single Hotel

* **Command**: `stats hotel`
* **Description**: Shows statistics for a single hotel.
* **Arguments**:
  - `hotelId` (Integer): The ID of the hotel to show statistics for.
* **Example Usage**:
```
shell:>stats hotel 1

Escuza Hotel
123 Main St, 10001, New York, United States
hotel1@example.com

Number of customers:    1
Number of bookings:     1
Number of complaints:   1
Total earning:          4520.00
Average rating:         4.00
```

### Show Statistics for All Hotels

* **Command**: `stats hotels`
* **Description**: Shows statistics for all hotels, including average rating, name, address, email, number of customers, number of bookings, number of complaints, and total earning.
* **Example Usage**: 
```
ID	Avg Rating	Name                     	Address                                      	Email               	Customers 	Bookings  	Complaints	Total Earning
3 	5.00     	DeLaChumpee Manor        	789 Rue de la Paix, 75001, Paris, France     	hotel3@example.com  	1         	1         	1         	875.00
2 	4.20     	Chimponello Estate       	456 Park Ave, SW1A 1AA, London, United Kingdom	hotel2@example.com  	4         	4         	4         	5770.00
1 	4.00     	Escuza Hotel             	123 Main St, 10001, New York, United States  	hotel1@example.com  	1         	1         	1         	4520.00
4 	0.00     	Three Trees Tavern       	101 Friedrichstraße, 10117, Berlin, Germany  	hotel4@example.com  	1         	1         	0         	1200.00
5 	0.00     	Rabin Rooms              	456 Bay St, M5J 2T3, Toronto, Canada         	hotel5@example.com  	2         	2         	0         	4800.00
```