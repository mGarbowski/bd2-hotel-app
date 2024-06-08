# Struktura Aplikacji

* [Baza danych](#baza-danych)
  * [Uruchomienie](#uruchomienie)
  * [Skrypty](#skrypty)
    * [Schemat](#schemat)
    * [Dane](#dane)
    * [Czyszczenie](#czyszczenie)
    * [Zapytania](#zapytania)
* [Aplikacja](#aplikacja)
  * [Komendy](#komendy)
  * [Domain](#domain)
    * [Service](#service)
    * [Repository](#repository)


## Baza danych

### Uruchomienie

Do uruchomienia bazy danych, najporstszym rozwiązaniem jest użycie Docker Compose
z plikiem konfiguracyjnym `database/docker-compose.yml`.

```shell
docker compose -f ../database/docker-compose.yml up -d
```

### Skrypty

Schemat, przykładowe dane, oraz pomocnicze skrypty,
są zdefiniowane w plikach SQL w katalogu `database/scripts`.

Skrypty najporściej uruchamiać w uruchomionym kontenerze bazy danych,
poprzez `docker exec`:

```shell
cat ../database/scripts/<skrypt>.sql | docker exec -i database-db-1 psql -U admin -d postgres
```

#### Schemat

W pliku `schema.sql` zdefiniowany jest schemat bazy danych
wraz z widokami, triggerami, oraz procedurami.
Uruchomienie tego skryptu utworzy strukturę bazy danych.

#### Dane

W pliku `sample-data.sql` zdefiniowane są przykładowe dane.
Uruchomienie tego skryptu wypełnienie bazę danymi.

#### Czyszczenie
W pliku `clean-all.sql`
zdefiniowane są skrypty do czyszczące zawartość bazy danych.
Uruchomienie tego skryptu przywróci bazę do stanu początkowego.

#### Zapytania
W pliku `query.sql`
zdefiniowane są przykładowe zapytania do bazy danych.
Uruchomienie tego skryptu wykona zapytania na bazie danych.

## Aplikacja

### Komendy

Wszystkie komendy zdefiniowane są w podkatalogu `commands`.

Komendy opdowiadają za opdowiednią walidację argumentów i opcji,
przekazanie ich do logiki aplikacji oraz wyświetlenie wyników.

Każda komenda jest osobną klasą, której metody odpowiadają
za wykonanie kodu dla poszczególnych argumentów i opcji.
Np. `booking makeComplaint --bookingId 1 --complaintText "Zbyt głośno"`
Wywoła metodę `makeComplaint` z klasy `BookingCommand` z argumentami `1` oraz `Zbyt głośno`. 

Dodatkowo opis komend oraz przyjmowane argumenty i opcje można wyświetlić poprzez `--help`.
