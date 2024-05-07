CREATE OR REPLACE TRIGGER increment_total_bookings_trigger
    AFTER INSERT
    ON booking
    FOR EACH ROW
EXECUTE PROCEDURE
    increment_total_bookings();


CREATE OR REPLACE FUNCTION increment_total_bookings()
    RETURNS TRIGGER
    LANGUAGE plpgsql
AS
$$
BEGIN
    UPDATE apartment
    SET total_bookings = total_bookings + 1
    WHERE id = new.apartment_id;

    UPDATE hotel
    SET total_bookings = total_bookings + 1
    WHERE id = hotel_id_from_apt_id(new.apartment_id);
    RETURN NEW;
END;
$$;


CREATE OR REPLACE FUNCTION hotel_id_from_apt_id(apt_id INTEGER) returns integer
    LANGUAGE SQL AS
$$
SELECT hotel_id
from apartment
where id = apt_id;
$$

