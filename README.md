# Obligatorisk oppgave 3 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende student:
* Gaute Zhang Kjelstadli, S362066, s362066@oslomet.no


# Oppgavebeskrivelse

I oppgave 1 så gikk jeg frem ved å lime inn programkode 5.2.3 a) og endret på "p = new Node<>(verdi);" til å bli
"p = new Node<>(verdi, q);". Altså jeg endret på parameterne slik at forelder noden pekte på noden q.

I oppgave 2 begynte jeg med å opprette rotnoden p og en hjelpevariabel "antall". Deretter brukte jeg en while-løkke som
kjører så lenge treet ikke er "tomt" og øker "antall" med 1 for hver gang vi finner samsvarende verdi. Hvis cmp er mindre
enn 0 går vi til venstre, hvis cmp er større enn null går vi til høyre.

I oppgave 3 tok jeg utgangspunkt i programkode 5.1.7 - h), men uten å opprette en node i metoden. Nemlig fordi metoden
tar inn noden p som parameter.

I nestePostorden tok jeg utgangspunkt i følgende punkt fra 5.1.7 i kompendie:

* Hvis p ikke har en forelder (p er rotnoden), så er p den siste i postorden.
* Hvis p er høyre barn til sin forelder f, er forelderen f den neste.
* Hvis p er venstre barn til sin forelder f, gjelder:
* Hvis p er enebarn (f.høyre er null), er forelderen f den neste.
* Hvis p ikke er enebarn (dvs. f.høyre er ikke null), så er den neste den noden som kommer først i postorden i subtreet med f.høyre som rot.

I oppgave 4 gikk jeg frem ved å sette noden p lik første noden i postorden ved å bruke metoden førstePostorden() fra oppgave 3.
Deretter opprettet jeg en while-løkke som kjører så lenge p ikke er null. I løkken bruker jeg metoden oppgave.utførOppgave 
som en metode for å printe (som sout).

Videre i postordenRecursive gikk jeg frem ved å lage et basistilfelle : if (p == null) return;
Deretter kalles metoden selv