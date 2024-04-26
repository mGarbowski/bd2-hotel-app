-- CREATE OR REPLACE function update_aparment_avg_rating()
CREATE OR REPLACE FUNCTION update_avg_ratings() RETURNS TRIGGER AS $$
DECLARE
    apt_id_v INTEGER;
    hotel_id_v INTEGER;
BEGIN
    -- check which apartment's rating is changing
    IF NEW.id is null then
        SELECT apartment_id
        INTO apt_id_v
        from booking
        where booking.id = OLD.booking_id;
    ELSE
        SELECT apartment_id
        INTO apt_id_v
        from booking
        where booking.id = NEW.booking_id;
    end if;

        UPDATE apartment
        SET avg_rating = (SELECT avg(rating.star_rating)
                          FROM rating join booking b on rating.booking_id = b.id
                          where b.apartment_id = apt_id_v
                          GROUP BY b.apartment_id)
        WHERE apartment.id = apt_id_v;

        SELECT apartment.hotel_id
        INTO hotel_id_v
        from apartment
        where apartment.id = apt_id_v;

        UPDATE hotel
        SET avg_rating = (SELECT avg(rating.star_rating)
                          FROM rating join booking b on rating.booking_id = b.id
                                      join apartment a on a.id = b.apartment_id
                          Where a.hotel_id = hotel_id_v
                          group by a.hotel_id)
        where hotel.id = hotel_id_v;
    RETURN new;
END;
$$ LANGUAGE plpgsql;

-- SELECT update_avg_ratings();
-- DROP FUNCTION update_avg_ratings();

create or replace trigger update_avg_rating_trigger
    AFTER INSERT OR UPDATE OR DELETE
    ON rating
    for EACH STATEMENT
execute FUNCTION update_avg_ratings();


-- drop trigger update_avg_rating_trigger on rating;

