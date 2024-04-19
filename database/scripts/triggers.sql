-- CREATE OR REPLACE function update_aparment_avg_rating()
CREATE OR REPLACE FUNCTION update_avg_ratings() RETURNS TRIGGER AS $$
    DECLARE
        rating_sum NUMERIC;
        rating_count NUMERIC;
        avg_apt_rating NUMERIC;
        apt_row RECORD;
        hotel_row RECORD;
BEGIN
    FOR apt_row in SELECT * FROM apartment LOOP
        SELECT count(rating.id), sum(rating.star_rating)
        INTO rating_count, rating_sum
        FROM rating join public.booking b on rating.booking_id = b.id
        where b.apartment_id = apt_row.id
        GROUP BY b.apartment_id;
        RAISE NOTICE 'rating_count of apt %: %, sum=%', apt_row.id, rating_count, rating_sum;

        IF rating_count=0 then
            avg_apt_rating = null;
        ELSE avg_apt_rating = rating_sum/rating_count;
        end if;

        UPDATE apartment
        SET avg_rating = avg_apt_rating
        WHERE apartment.id = apt_row.id;
        end loop;

    FOR hotel_row in SELECT * FROM hotel LOOP
            SELECT count(rating.id), sum(rating.star_rating)
            INTO rating_count, rating_sum
            FROM rating join public.booking b on rating.booking_id = b.id
                join public.apartment a on a.id = b.apartment_id
            where a.hotel_id = hotel_row.id
            GROUP BY a.hotel_id;
            RAISE NOTICE 'rating_count of hotel %: %, sum=%', hotel_row.id, rating_count, rating_sum;

            IF rating_count=0 then
                avg_apt_rating = null;
            ELSE avg_apt_rating = rating_sum/rating_count;
            end if;

            UPDATE hotel
            SET avg_rating = avg_apt_rating
            WHERE hotel.id = hotel_row.id;
        end loop;
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

