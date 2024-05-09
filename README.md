# Hotel app
* Mikołaj Garbowski
* Michał Łuszczek
* Maksym Bieńkowski
* Jędrzej Grabski
* Aleksander Drwal

Projekt semestralny na przedmiot Bazy Danych 2 (2024L)

## Opis projektu
Projekt składa się z bazy danych i aplikacji obsługujących system rezerwacji w hotelach

### Planowane funkcjonalności
* Operacyjne
    * Wyszukiwanie dostępnych pokoi w wybranym obszarze przez klienta
    * Złożenie rezerwacji przez klienta
    * Wyświetlenie aktywnych rezerwacji klienta
    * Zamówienie dodatkowej usługi przez klienta
    * Wyświetlenie kwoty do zapłacenia przez klienta
    * Symulowana zapłata należności przez klienta
    * Wystawienie opinii przez klienta
    * Zgłoszenie zażalenia przez klienta
* Analityczno-raportowe
    * Wyświetlenie podsumowania liczby rezerwacji z podziałem na hotele
    * Wyświetlenie podsumowania przychodów z podziałem na hotele
    * Wyświetlenie rankingu hoteli względem zagregowanych ocen
    * Wyświetlenie podsumowania liczby zażaleń z podziałem na hotele

TODO tabele analityczne uzupełniane wyzwalaczami albo widoki
Zaplanować użycie triggerów i procedur składowanych

## Model danych

### Model pojęciowy
![Diagram związków encji](./docs/er-diagram-2.png)

## Wykorzystane technologie
* Baza danych PostgreSQL
* Aplikacja wykorzystująca Spring Framework
  * ORM Spring Data JPA
  * Moduł Spring Shell to tworzenia aplikacji CLI
* Model ER i logiczny - Oracle Data Modeler

## Instalacja i uruchomienie

### Baza danych
Do uruchomienia lokalnie używamy Docker Compose ([instalacja dla Ubuntu](https://docs.docker.com/engine/install/ubuntu/))

Uruchomienie
```shell
docker compose -f database/docker-compose.yml up -d
```

Plik `docker-compose.yml` zawiera konfigurację uruchomienia. Dane z bazy będą nieulotne, przechowywane w katalogu 
`./database/data` (ignorowanym przez git)

Katalog [scripts](./database/scripts) zawiera skrypty do bazy danych
* [schema.sql](./database/scripts/schema.sql) - definiuje schemat bazy danych (tworzy tabele)
* [sample-data.sql](./database/scripts/sample-data.sql) - dodaje przykładowe dane
* [clean-all.sql](./database/scripts/clean-all.sql) - czyści zawartość bazy
* [query.sql](./database/scripts/query.sql) - przykładowe zapytania

Uruchomić skrypt można np. przez wtyczkę do IntelliJ. Trzeba dodać źródło danych w zakładce Database,
otworzyć wybrany skrypt i wybrać sesję bazy danych. Skrypt można uruchomić zielonym przyciskiem play lub skrótem Ctrl+Enter.

Skrypt można uruchomić wywołując w kontenerze polecenie psql i przekazując skrypt potokiem na stdin.
Zakładając, że kontener ma nazwę `database-db-1` wykonujemy:

```shell
cat ./database/scripts/query.sql | docker exec -i database-db-1 psql -U admin -d postgres
```


### Aplikacja
Aplikacja wymaga Javy w wersji 17, wygodną instalację umożliwia [SDKMan](https://sdkman.io/).

Wszystkie komendy wykonujemy przez wrapper do Gradle `gradlew`

#### Budowanie
Do zbudowania aplikacji do pliku "fat jar" - plik wykonywalny dla JVM, aplikacja spakowana ze wszystkimi zależnościami

```shell
./gradlew bootJar
```

#### Uruchomienie jar
```shell
java -jar ./hotelapp/build/libs/hotelapp-0.0.1-SNAPSHOT.jar
```

#### Uruchomienie w IntelliJ
Przy poprawnym załadowaniu projektu Gradle w IntelliJ, można wygodnie uruchamiać aplikację przez run configuration

### Data modeler
Program do pobrania na stronie [Oracle](https://www.oracle.com/database/sqldeveloper/technologies/sql-data-modeler/download/)

File > Open > data-modeler-design.dmd
