-- Delete all objects from the database

BEGIN TRANSACTION;

-- Drop all stored procedures etc TODO: replace with loops deleting all
-- DROP TRIGGER update_avg_rating_trigger on rating;

-- DROP FUNCTION update_avg_ratings();

-- Drop all tables
DO
$$
    DECLARE
        tabname RECORD;
    BEGIN
        FOR tabname IN (SELECT tablename FROM pg_tables WHERE schemaname = current_schema())
            LOOP
                EXECUTE 'DROP TABLE IF EXISTS ' || tabname.tablename || ' CASCADE';
            END LOOP;
    END
$$;

-- Drop all sequences
DO
$$
    DECLARE
        seqname RECORD;
    BEGIN
        FOR seqname IN (SELECT relname
                        FROM pg_class
                        WHERE relkind = 'S'
                          AND relnamespace = (SELECT oid FROM pg_namespace WHERE nspname = current_schema()))
            LOOP
                EXECUTE 'DROP SEQUENCE IF EXISTS ' || seqname.relname || ' CASCADE';
            END LOOP;
    END
$$;

COMMIT;