-- CREATE OR REPLACE function update_aparment_avg_rating()
CREATE OR REPLACE FUNCTION update_avg_ratings() RETURNS TRIGGER AS $$
BEGIN
        UPDATE apartment
        SET avg_rating = (SELECT avg(rating.star_rating)
                          FROM rating join public.booking b on rating.booking_id = b.id
                          where b.apartment_id = apartment.id
                          GROUP BY b.apartment_id);

        UPDATE hotel
        SET avg_rating = (SELECT avg(rating.star_rating)
                          FROM rating join public.booking b on rating.booking_id = b.id
                                      join public.apartment a on a.id = b.apartment_id
                          Where hotel.id = a.hotel_id
                          group by a.hotel_id);
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

