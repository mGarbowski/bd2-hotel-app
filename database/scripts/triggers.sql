-- CREATE OR REPLACE function update_aparment_avg_rating()
CREATE OR REPLACE FUNCTION update_apartment_avg_rating() RETURNS integer AS $$
    DECLARE
        rating_sum NUMERIC;
        rating_count NUMERIC;
        avg_rating NUMERIC;
        apt_row RECORD;
BEGIN
    FOR apt_row in SELECT * FROM apartment LOOP
        SELECT count(rating.id), sum(rating.star_rating)
        INTO rating_count, rating_sum
        FROM rating join public.booking b on rating.booking_id = b.id
        where b.apartment_id = apt_row.id
        GROUP BY b.apartment_id;
        RAISE NOTICE 'rating_count of apt %: %, sum=%', apt_row.id, rating_count, rating_sum;

        end loop;
    RETURN 1;
END;
$$ LANGUAGE plpgsql;

SELECT update_apartment_avg_rating();

-- create or replace trigger update_apartment_avg_rating
--     AFTER INSERT OR UPDATE OR DELETE
--     ON apartment
--     for STATEMENT
--     execute

