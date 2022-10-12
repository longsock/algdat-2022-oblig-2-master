# Obligatorisk oppgave 2 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende studenter:

* Duc Tan Vo, S364733, s364733@oslomet.no

# Oppgavebeskrivelse

Oppgave 1:

Metodene int antall() returnerer variabelen, og boolean tom() har en hjelpevariabel og sjekker om antall() er tom eller ikke. Returnerer tom.
I konstruktøren setter den hodet først, også itererer den gjennom listen og lager resten av listen.

Oppgave 2:

a. I toString() metoden satte jeg delimiter, prefix, og suffix, også legger den til verdien i listen. Itererer gjennom med current.neste.
Planla å lage med StringBuilder først, men innså at StringJoiner i både toString og omvendtString bruker mindre kode.

b. Siden null-verdier ikke er tillatt brukte jeg requireNonNull-metoden fra klassen Objects. Sjekker om det er null eller ikke og kaster en feilmelding.
Jeg lager en ny node, og hvis listen er tom setter jeg noden som hode og hale. Oppdaterer antall og endringer.
Hvis listen ikke er tom legges noden bak.

Oppgave 3:

a. Lagd en hjelpemetode finnNode som tar inn int indeks som parameter. Sjekker om indeks <= antall/2 også teller den fra hodet til riktig indeks.
Hvis motsatt starter den fra hale og teller nedover og gjør det samme.
Metoden public T hent bruker metoden finnNode og returnerer verdien.
finnNode blir også brukt i T oppdater som lagrer verdi i en midlertidig node, også oppdateres verdien, så returneres den.

b. Hentet fratilKontroll fra kompendiet. Lager en liste og bruker metoden finnNode. For-loop som teller fra indeks fra til indeks til, og legger disse i listen og returnerer denne.

Oppgave 4:

Lagd metode indeksTil som starter i hodet og hopper gjennom listen. Hvis hodets verdi er samme som verdi returneres indeks. Hvis det ikke finnes returner det -1 istedenfor å kaste en unntak.
Metoden boolean inneholder(T verdi) sjekker om indeks ==-1 og returnerer false, hvis ikke returnerer den true.

Oppgave 5:

requireNonNull metode slik at null-verdier ikke tillates. Sjekker om indeks < 0 eller negativ og kaster unntak hvis det stemmer.
Hvis listen er tom setter jeg den nyee noden til hode og hale. Hvis verdien skal legges til venstre setter jeg ny node til hode, og setter riktig pekere.
Hvis verdi skal legges bakerst sjekker vi om indeks == antall, hvis det er det setter vi ny node til hale og setter riktig pekere.
Hvis verdi skal settes inn i midten bruker vi finnNode metoden og setter riktig pekere.
Antall og endringer endres.