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

