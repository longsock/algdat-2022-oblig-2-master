package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;
import java.util.StringJoiner;


public class DobbeltLenketListe<T> implements Liste<T> {

    /**
     * Node class
     *
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;                   // nodens verdi
        private Node<T> forrige, neste;    // pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }
    }

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;         // antall endringer i listen

    // Hjelpemetoder
    private void fratilKontroll(int antall, int fra, int til) {
        if (fra < 0)                                  // fra er negativ
            throw new IndexOutOfBoundsException
                    ("fra(" + fra + ") er negativ!");

        if (til > antall)                          // til er utenfor tabellen
            throw new IndexOutOfBoundsException
                    ("til(" + til + ") > antall(" + antall + ")");

        if (fra > til)                                // fra er større enn til
            throw new IllegalArgumentException
                    ("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
    }

    private Node<T> finnNode (int indeks){
        Node<T> current;

        if(indeks < antall / 2){
            current = hode;
            for (int i = 0; i < indeks; i++){
                current = current.neste;
            }
        } else {
            current = hale;
            for (int i = antall-1; i > indeks; i--){
                current = current.forrige;
            }
        }
        return current;
    }

    public DobbeltLenketListe(){    // Tom liste konstruktør
        hode = hale = null;
        antall = 0;
        endringer = 0;
    }

    public DobbeltLenketListe(T[] a) {
        Objects.requireNonNull(a, "Tabellen a er null!");

        for(T i : a){
            if (i != null){
                if (antall == 0){
                    hode = new Node<>(i, null, null);
                    hale = hode;
                }
                else {
                    hale.neste = new Node<>(i, hale, null);
                    hale = hale.neste;
                }
                antall++;
            }
        }

    }

    public Liste<T> subliste(int fra, int til) {
        Liste<T> liste = new DobbeltLenketListe<>();
        fratilKontroll(antall, fra, til);
        Node<T> denne = finnNode(fra);
        for (int i = fra; i < til; i++){
            liste.leggInn(denne.verdi);
            denne = denne.neste;
        }
        return liste;
    }

    @Override
    public int antall() {
        return antall;
    }

    @Override
    public boolean tom() {
        boolean tom = false;
            if(antall() == 0) {
            tom = true;
        }
            return tom;
    }

    @Override
    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi, "Null-verdi er ikke tillatt!");

        Node p = new Node<>(verdi, null, null);

        if(hale == null & hode == null){
            p.forrige = null;
            hode = p;
            hale = p;
            antall++;
            endringer++;
        } else {
            hale.neste = p;
            p.forrige = hale;
            hale = p;
            antall++;
            endringer++;
        }
        return true;
    }

    @Override
    public void leggInn(int indeks, T verdi) {
        // requireNonNull for sjekk av null-verdier
        Objects.requireNonNull(verdi, "Null-verdier er ikke tillatt!");
        Node<T> nyNode = new Node<>(verdi, null, null);

        // Sjekk om indeks er negativ, eller større enn antall og kaster unntak
        if(indeks > antall || indeks < 0){
            throw new NullPointerException("Indeks er større enn antall noder eller negativ!");
        } else if (hode == null && hale == null){   // 1) Hvis listen er tom, er node hodet og hale
            hode = nyNode;
            hale = nyNode;
        } else if (indeks == 0) {// 2) Hvis verdien skal legges først, bruk node, ny node peker til hode, gjør ny node til hode.
            nyNode.neste = hode;
            hode.forrige = nyNode;
            hode = nyNode;
        } else if (indeks == antall){   // 3) Hvis verdien skal legges bakerst, node blir ny hale, forrige hale peker på ny node, gjør ny node til hale.
            nyNode.forrige = hale;
            hale.neste = nyNode;
            hale = nyNode;
        } else {    // 4) Hvis verdien skal legges mellom to verdier, bruk metode finnNode, sett riktig pekere
            Node<T> p = finnNode(indeks-1);
            nyNode.neste = p.neste;
            nyNode.forrige = p;
            p.neste = nyNode;
            nyNode.neste.forrige = nyNode;
            }
        antall++;
        endringer++;
        }

    @Override
    public boolean inneholder(T verdi) {
        int indeks = indeksTil(verdi);
        if(indeks == -1){
            return false;
        } else {
            return true;
        }
    }

    @Override
    public T hent(int indeks) {
        indeksKontroll(indeks, false);
        return finnNode(indeks).verdi;
    }

    @Override
    public int indeksTil(T verdi) {
        Node<T> p = hode;

        for (int i = 0; i < antall; i++, p = p.neste) {
            if (p.verdi.equals(verdi)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        indeksKontroll(indeks, false);
        Objects.requireNonNull(nyverdi, "Null-verdi er ikke tillatt!");
        Node<T> current = finnNode(indeks);
        T gammelVerdi = current.verdi;
        current.verdi = nyverdi;
        endringer++;

        return gammelVerdi;
    }

    @Override
    public boolean fjern(T verdi) {
        if(verdi == null) {
            return false;
        }
        Node<T> current = hode;

        int i = 1;
        while(!current.verdi.equals(verdi) && i < antall) {
            current = current.neste;
            i++;
        }
        if(i == antall && !current.verdi.equals(verdi)) {
            return false;
        }
        if(antall == 1){
            hode = null;
            hale = null;
        }else if(current.forrige == null) {
            hode.neste.forrige = null;
            hode = hode.neste;
        }else if(current.neste == null) {
            hale.forrige.neste = null;
            hale = hale.forrige;
        }else {
            current.forrige.neste = current.neste;
            current.neste.forrige = current.forrige;
        }
        antall--;
        endringer++;
        return true;
    }

    @Override
    public T fjern(int indeks) {
        Node<T> current = hode;

        if(indeks < 0) {
            throw new IndexOutOfBoundsException("Indeksen kan ikke være negativ!");
        }
        if(indeks >= antall) {
            throw new IndexOutOfBoundsException("Indeksen kan ikke være større enn antall noder!");
        }

        T retur;

        if(antall == 1) {
            retur = hode.verdi;
            hode = hale = null;
        } else if (indeks == 0){
            retur = hode.verdi;
            hode = hode.neste;
            hode.forrige = null;
        } else if (indeks == antall-1){
            retur = hale.verdi;
            hale.forrige.neste = null;
            hale = hale.forrige;
        } else {
            current = finnNode(indeks);
            retur = current.verdi;
            current.forrige.neste = current.neste;
            current.neste.forrige = current.forrige;
        }
        antall--;
        endringer++;
        return retur;
    }

    @Override
    public void nullstill() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(", ", "[", "]");
        Node<T> current = hode;

        while(current != null){
            sj.add(current.verdi.toString());
            current = current.neste;
        }
        return sj.toString();
    }

    public String omvendtString() {
        StringJoiner sj = new StringJoiner(", ", "[", "]");
        Node<T> current = hale;

        while(current != null){
            sj.add(current.verdi.toString());
            current = current.forrige;
        }
        return sj.toString();
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    public Iterator<T> iterator(int indeks) {
        throw new UnsupportedOperationException();
    }

    private class DobbeltLenketListeIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator() {
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        private DobbeltLenketListeIterator(int indeks) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        Character[] c = {'A','B','C','D','E','F','G','H','I','J',};
        DobbeltLenketListe<Character> liste = new DobbeltLenketListe<>(c);
        System.out.println(liste.subliste(3,8)); // [D, E, F, G, H]
        System.out.println(liste.subliste(5,5)); // []
        System.out.println(liste.subliste(8,liste.antall())); // [I, J]
        // System.out.println(liste.subliste(0,11)); // skal kaste unntak
    }

} // class DobbeltLenketListe


