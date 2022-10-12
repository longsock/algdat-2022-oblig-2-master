# Obligatorisk oppgave 2 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende studenter:

* Duc Tan Vo, S364733, s364733@oslomet.no

# Oppgavebeskrivelse

Oppgave 1: Metodene int antall() returnerer variabelen, 
og boolean tom() har en hjelpevariabel og sjekker om antall() er tom eller ikke. Returnerer tom.
I konstruktøren setter den hodet først, også itererer den gjennom listen og lager resten av listen. 

Oppgave 2a: I toString() metoden satte jeg delimiter, prefix, og suffix, også legger den til verdien i listen. Itererer gjennom med current.neste.
Planla å lage med StringBuilder først, men innså at StringJoiner i både toString og omvendtString bruker mindre kode.

Oppgave 2b: Siden null-verdier ikke er tillatt brukte jeg requireNonNull-metoden fra klassen Objects. Sjekker om det er null eller ikke og kaster en feilmelding.
Jeg lager en ny node, og hvis listen er tom setter jeg noden som hode og hale. Oppdaterer antall og endringer.
Hvis listen ikke er tom legges noden bak.



