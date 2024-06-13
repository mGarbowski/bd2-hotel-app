### Scenario 1: User Logs In, Lists Bookings, Searches for Apartments, Books an Apartment, Orders an Extra Service, and Logs Out

**Test Case 1: Complete Booking Process**

1. **Step 1**: Log in as a customer with ID 2.
    - **Command**: `user login 2`
    - **Expected Result**: `Welcome Jane Smith!`

2. **Step 2**: List active bookings.
    - **Command**: `booking list`
    - **Expected Result**:
      ```
      You have no active bookings
      ```

3. **Step 3**: Search for free apartments in London from 2024-05-28 to 2024-05-31.
    - **Command**: `apartment searchCity London 2024-07-28 2024-07-31`
    - **Expected Result**:
      ```
      Hotel: Chimponello Estate – Apartment number [6] (0.00 stars) – Capacity: 7 person – Price per day: 170.00
          - Rooms: 5
          - Bathrooms: 1
          - Beds: 1
          - Address: 456 Park Ave, SW1A 1AA, London, United Kingdom
      
      and a few more apartments...
      ```

4. **Step 4**: Book the apartment with ID 6 from 2024-07-28 to 2024-07-31 for 2 people.
    - **Command**: `booking make 6 2024-07-28 2024-07-31 2`
    - **Expected Result**: `Booking complete`

5. **Step 5**: Order the Breakfast service for the newly created booking.
    - **Command**: `Extra order Breakfast 9`
    - **Expected Result**: `Breakfast ordered successfully`

6. **Step 6**: Log out.
    - **Command**: `user logout`
    - **Expected Result**: `Logged out`

### Scenario 2: User Logs In, Leaves a Complaint and Rating, and Logs Out

**Test Case 2: Complaint and Rating Process**

1. **Step 1**: Log in as a customer with ID 1.
    - **Command**: `user login 2`
    - **Expected Result**: `Welcome Jane Doe!`

2. **Step 2**: Make a complaint for booking 9
    - **Command**: `booking makeComplaint 9 There was no hot water`
    - **Expected Result**: `Complaint created`

3. **Step 3**: Add a 3-star rating and opinion for booking 9.
    - **Command**: `booking addOpinion 9 3 Room was okay but service was poor`
    - **Expected Result**: `Opinion added`

4. **Step 4**: Log out.
    - **Command**: `user logout`
    - **Expected Result**: `Logged out`

### Scenario 3: User Logs In, Lists All Countries, Gets Country Name by Code, and Logs Out

**Test Case 3: Country Information Process**

1. **Step 1**: Log in as a customer with ID 1.
    - **Command**: `user login 1`
    - **Expected Result**: `Welcome John Doe!`

2. **Step 2**: List all countries.
    - **Command**: `country list`
    - **Expected Result**: `[United States, United Kingdom, France, Germany, Canada]`

3. **Step 3**: Get the country name for code 'US'.
    - **Command**: `country byCode US`
    - **Expected Result**: `United States`

4. **Step 4**: Log out.
    - **Command**: `user logout`
    - **Expected Result**: `Logged out`

### Scenario 4: User Logs In, Lists Customers, Searches for Free Apartments, and Logs Out

**Test Case 4: Customer and Apartment Search Process**

1. **Step 1**: Log in as a customer with ID 2.
    - **Command**: `user login 2`
    - **Expected Result**: `Welcome Jane Smith!`

2. **Step 2**: List all customers.
    - **Command**: `user list`
    - **Expected Result**:
      ```
      Customer(id=1, name=John, surname=Doe, age=30, gender=M, email=john.doe@example.com, phoneNumber=123-456-7890, address=[1] 123 Main St, 10001, New York, United States)
      Customer(id=2, name=Jane, surname=Smith, age=25, gender=F, email=jane.smith@example.com, phoneNumber=987-654-3210, address=[2] 456 Park Ave, SW1A 1AA, London, United Kingdom)
      Customer(id=3, name=Michael, surname=Johnson, age=40, gender=M, email=michael.johnson@example.com, phoneNumber=555-123-4567, address=[3] 789 Rue de la Paix, 75001, Paris, France)
      ```

3. **Step 3**: Search for free apartments in Berlin from 2024-08-01 to 2024-08-05.
    - **Command**: `apartment searchCity Berlin 2024-08-01 2024-08-05`
    - **Expected Result**: Displays available apartments or a message indicating no availability.
      ```
      Hotel: Three Trees Tavern – Apartment number [4] (0.00 stars) – Capacity: 5 person – Price per day: 150.00
          - Rooms: 3
          - Bathrooms: 1
          - Beds: 3
          - Address: 101 Friedrichstraße, 10117, Berlin, Germany 
      ```

4. **Step 4**: Log out.
    - **Command**: `user logout`
    - **Expected Result**: `Logged out`

### Scenario 5: User Logs In, Lists Extra Services, Orders an Extra Service, Checks Payment Summary, Makes Payment, and Logs Out

**Test Case 5: Extra Service and Payment Process**

1. **Step 1**: Log in as a customer with ID 3.
    - **Command**: `user login 3`
    - **Expected Result**: `Welcome Michael Johnson!`

2. **Step 2**: List extra services available for booking 5.
    - **Command**: `extra list 5`
    - **Expected Result**: `[3] Spa, 50.00USD`

3. **Step 3**: Order the 'Spa' service for booking 5.
    - **Command**: `extra order Spa 9`
    - **Expected Result**: `Spa ordered successfully`

4. **Step 4**: Display the payment summary for booking 5.
    - **Command**: `payment summary 5`
    - **Expected Result**:
      ```
      Reservation fee                        -800.00
      Spa                                     -50.00
      Laundry                                 -25.00
      Payment                                 600.00
      ----------------------------------------------
      TOTAL:                                 -275.00
      ```

5. **Step 5**: Make a payment of 275 for booking 5.
    - **Command**: `payment make 5 275`
    - **Expected Result**: `Payment made successfully. Balance for the given booking after this payment is 0.00`

6. **Step 6**: Log out.
    - **Command**: `user logout`
    - **Expected Result**: `Logged out`