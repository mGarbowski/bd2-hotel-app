# Installation and Launch

## Database
To run locally, we use Docker Compose ([installation for Ubuntu](https://docs.docker.com/engine/install/ubuntu/))

### Launch
```shell
docker compose -f ../database/docker-compose.yml up -d
```

The `docker-compose.yml` file contains the launch configuration. The database data will be persistent, stored in the `./database/data` directory (ignored by git).

### Scripts

The [scripts](../database/scripts) directory contains database scripts:
* [schema.sql](../database/scripts/schema.sql) - defines the database schema (creates tables)
* [sample-data.sql](../database/scripts/sample-data.sql) - adds sample data
* [clean-all.sql](../database/scripts/clean-all.sql) - cleans the database content
* [query.sql](../database/scripts/query.sql) - sample queries

Scripts can be run, for example, through an IntelliJ plugin. You need to add a data source in the Database tab, open the selected script, and choose the database session. The script can be run with the green play button or the Ctrl+Enter shortcut.

A script can also be run by invoking the psql command in the container and passing the script through stdin. Assuming the container is named `database-db-1`, execute:

```shell
cat ../database/scripts/query.sql | docker exec -i database-db-1 psql -U admin -d postgres
```

## Application
The application requires Java version 17. Convenient installation can be done via [SDKMan](https://sdkman.io/).

All commands are executed through the Gradle wrapper `gradlew`.

### Building
To build the application into a "fat jar" - an executable file for the JVM, with the application packaged with all dependencies:

```shell
./gradlew bootJar
```

### Running the jar
```shell
java -jar ./hotelapp/build/libs/hotelapp-0.0.1-SNAPSHOT.jar
```

### Running in IntelliJ
With the project correctly loaded in IntelliJ Gradle, the application can be conveniently run through the run configuration.

## Data Modeler
The program can be downloaded from the [Oracle](https://www.oracle.com/database/sqldeveloper/technologies/sql-data-modeler/download/) website.

File > Open > data-modeler-design.dmd

### Testing
To run tests, you can run the `./gradlew check` binary in the apps main directory.