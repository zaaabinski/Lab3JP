## Lab3JP
Zaprojektowanie i implementacja aplikacji z wykorzystaniem kontenerów danych (tablice, kolekcje) oraz szablonów (generics).

## Cel zajęć

Celem zajęć jest zbudowanie aplikacji pozwalającą na interakcję z użytkownikiem z poziomu konsoli. Aplikacja będzie realizować uproszczona wizję przepływu feedbacku w firmie 
zainspirowaną koncepcjami Raya Dalio przedstawionymi w książce "[*Zasady*](https://lubimyczytac.pl/ksiazka/4899240/zasady)", gdzie głównym użytkownikiem będzie manager zarządzający firmą.

Dostępne będą dwie główne funkcjonalności:

- log decyzyjny
- notowanie informacji zwrotnej (feedback) o pracowniku

## Grupa B

**Feedback pracowników**

Jest to komponent pozwalający na zbieranie subiektywnych informacji nt. działań pracowników.

Funkcjonalności:

- dodanie opinii o pracowniku (data, id pracownika, rodzaj [pozytywna/negatywna], waga, komentarz),
- anulowanie opinii o pracowniku (na podstawie numeru id),
- analiza trendu  otrzymanych opinii dla danego pracownika (np trend tygodniowy, miesięczny, kwartalny) uwzględniający rodzaj opinii i wagę.
- **Przykład:**
    
    Pracownik X przez kilka dni z rzędu ma lekceważące nastawienie do swoich współpracowników. Manager dodaje tą informację do systemu, pomoże mu zachować obiektywne spojrzenie podczas późniejszej ewaluacji.
