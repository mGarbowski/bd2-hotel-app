# Hotel app
* Mikołaj Garbowski
* Michał Łuszczek
* Maksym Bieńkowski
* Jędrzej Grabski
* Aleksander Drwal

Projekt semestralny na przedmiot Bazy Danych 2 (2024L)

## Opis projektu
Projekt składa się z bazy danych i aplikacji obsługujących system rezerwacji w hotelach

* [Model danych](./docs/data-model.md)
* [Instrukcja uruchomienia](./docs/build-and-run.md)
* [Dokumentacja bazy](./docs/database-structure.md)

## Funkcjonalności
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


## Wykorzystane technologie
* Baza danych PostgreSQL
* Aplikacja wykorzystująca Spring Framework
  * ORM Spring Data JPA
  * Moduł Spring Shell to tworzenia aplikacji CLI
* Model ER i logiczny - Oracle Data Modeler
