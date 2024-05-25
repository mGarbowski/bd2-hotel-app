-- Delete all objects from the database

BEGIN TRANSACTION;

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

-- Drop all triggers
DO
$$
    DECLARE
        trgname RECORD;
    BEGIN
        FOR trgname IN (SELECT tgname, tgrelid::regclass FROM pg_trigger WHERE NOT tgisinternal)
            LOOP
                EXECUTE 'DROP TRIGGER IF EXISTS ' || trgname.tgname || ' ON ' || trgname.tgrelid || ' CASCADE';
            END LOOP;
    END
$$;

-- Drop all functions
DO
$$
    DECLARE
        funcname RECORD;
    BEGIN
        FOR funcname IN (SELECT proname
                         FROM pg_proc
                         WHERE pronamespace = (SELECT oid FROM pg_namespace WHERE nspname = current_schema()))
            LOOP
                EXECUTE 'DROP FUNCTION IF EXISTS ' || funcname.proname || ' CASCADE';
            END LOOP;
    END
$$;

-- Drop all procedures
DO
$$
    DECLARE
        procname RECORD;
    BEGIN
        FOR procname IN (SELECT proname
                         FROM pg_proc
                         WHERE pronamespace = (SELECT oid FROM pg_namespace WHERE nspname = current_schema()))
            LOOP
                EXECUTE 'DROP PROCEDURE IF EXISTS ' || procname.proname || ' CASCADE';
            END LOOP;
    END
$$;

COMMIT;
