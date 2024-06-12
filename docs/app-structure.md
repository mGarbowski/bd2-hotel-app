# Application Structure

* [Database](#database)
  * [Startup](#startup)
  * [Scripts](#scripts)
    * [Schema](#schema)
    * [Data](#data)
    * [Cleanup](#cleanup)
    * [Queries](#queries)
* [Application](#application)
  * [Commands](#commands)
  * [Domain](#domain)
    * [Service](#service)
    * [Repository](#repository)

## Database

### Startup

To start the database, the simplest solution is to use Docker Compose
with the configuration file `database/docker-compose.yml`.

```shell
docker compose -f ../database/docker-compose.yml up -d
```

### Scripts

The schema, sample data, and helper scripts
are defined in SQL files in the `database/scripts` directory.

The easiest way to run the scripts is in the running database container
using `docker exec`:

```shell
cat ../database/scripts/<script>.sql | docker exec -i database-db-1 psql -U admin -d postgres
```

#### Schema

The `schema.sql` file defines the database schema
along with views, triggers, and procedures.
Running this script will create the database structure.

#### Data

The `sample-data.sql` file defines sample data.
Running this script will populate the database with data.

#### Cleanup

The `clean-all.sql` file
defines scripts for cleaning the database content.
Running this script will restore the database to its initial state.

#### Queries

The `query.sql` file
defines sample database queries.
Running this script will execute queries on the database.

## Application

### Commands

All commands are defined in the `commands` subdirectory.

Commands are responsible for appropriate argument and option validation,
passing them to the application logic, and displaying results.

Each command is a separate class, with methods responsible
for executing code for specific arguments and options.
For example, `booking makeComplaint --bookingId 1 --complaintText "Too noisy"`
will call the `makeComplaint` method of the `BookingCommand` class with arguments `1` and `Too noisy`.

Additionally, command descriptions and accepted arguments and options can be displayed using `--help`.

### Domain

The `domain` subdirectory contains folders
grouping individual database tables.
Each of these folders contains `Service` and `Repository` classes.

#### Service

`Service` classes implement the logic for modifying data
in the database and are used by commands.
They validate data at the application level before saving to the database.

#### Repository

`Repository` classes are responsible for directly executing
SQL queries on the database.