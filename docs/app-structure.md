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

