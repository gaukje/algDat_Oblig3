package no.oslomet.cs.algdat.Oblig3;


import java.util.*;

public class SBinTre<T> {
    private static final class Node<T>   // en indre nodeklasse
    {
        private T verdi;                   // nodens verdi
        private Node<T> venstre, høyre;    // venstre og høyre barn
        private Node<T> forelder;          // forelder

        // konstruktør
        private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder) {
            this.verdi = verdi;
            venstre = v;
            høyre = h;
            this.forelder = forelder;
        }

        private Node(T verdi, Node<T> forelder)  // konstruktør
        {
            this(verdi, null, null, forelder);
        }

        @Override
        public String toString() {
            return "" + verdi;
        }

    } // class Node

    private Node<T> rot;                            // peker til rotnoden
    private int antall;                             // antall noder
    private int endringer;                          // antall endringer

    private final Comparator<? super T> comp;       // komparator

    public SBinTre(Comparator<? super T> c)    // konstruktør
    {
        rot = null;
        antall = 0;
        comp = c;
    }

    public boolean inneholder(T verdi) {
        if (verdi == null) return false;

        Node<T> p = rot;

        while (p != null) {
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) p = p.venstre;
            else if (cmp > 0) p = p.høyre;
            else return true;
        }

        return false;
    }

    public int antall() {
        return antall;
    }

    public String toStringPostOrder() {
        if (tom()) return "[]";

        StringJoiner s = new StringJoiner(", ", "[", "]");

        Node<T> p = førstePostorden(rot); // går til den første i postorden
        while (p != null) {
            s.add(p.verdi.toString());
            p = nestePostorden(p);
        }

        return s.toString();
    }

    public boolean tom() {
        return antall == 0;
    }

    //Bruker programkode 5.2.3 a)
    public final boolean leggInn(T verdi)    // skal ligge i class SBinTre
    {

        Objects.requireNonNull(verdi, "Ulovlig med nullverdier!");
        Node<T> p = rot, q = null;               // p starter i roten
        int cmp = 0;                             // hjelpevariabel

        while (p != null)       // fortsetter til p er ute av treet
        {
            q = p;                                 // q er forelder til p
            cmp = comp.compare(verdi,p.verdi);     // bruker komparatoren
            p = cmp < 0 ? p.venstre : p.høyre;     // flytter p
        }

        // p er nå null, dvs. ute av treet, q er den siste vi passerte

        p = new Node<>(verdi, q);                   // oppretter en ny node

        if (q == null) rot = p;                  // p blir rotnode
        else if (cmp < 0) q.venstre = p;         // venstre barn til q
        else q.høyre = p;                        // høyre barn til q

        antall++; endringer++;                                // én verdi mer i treet
        return true;                             // vellykket innlegging
    }
    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public int fjernAlle(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public int antall(T verdi) {
        if (tom()|| verdi == null) return 0;

        int antall = 0;
        Node<T> p = rot;

        while (p != null) {

            if (p.verdi.equals(verdi)) {
                antall++;
                p = p.høyre;
            }
            else {
                p = comp.compare(verdi, p.verdi) < 0 ? p.venstre : p.høyre;
            }
        }
        return antall;

        //throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public void nullstill() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    private static <T> Node<T> førstePostorden(Node<T> p) {

        //Programkode 5.1.7 h)
        while (true) {
            if (p.venstre != null) p = p.venstre;
            else if (p.høyre != null) p = p.høyre;
            else return p;
        }
        //throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    private static <T> Node<T> nestePostorden(Node<T> p) {
        /*

    Hvis p ikke har en forelder (p er rotnoden), så er p den siste i postorden.
    Hvis p er høyre barn til sin forelder f, er forelderen f den neste.
    Hvis p er venstre barn til sin forelder f, gjelder:
        Hvis p er enebarn (f.høyre er null), er forelderen f den neste.
        Hvis p ikke er enebarn (dvs. f.høyre er ikke null), så er den neste den noden som kommer først i postorden i subtreet med f.høyre som rot.

         */
        Node<T> forelder = p.forelder;

        //if (forelder == null) return null;                  //Hvis p ikke har en forelder (p er rotnoden), så er p den siste i postorden.

        if (p == forelder.høyre) return forelder;             //Hvis p er høyre barn til sin forelder f, er forelderen f den neste.
                                                                //Hvis p er venstre barn til sin forelder f, gjelder:
        if (p == forelder.venstre) {                            //Hvis p er enebarn (f.høyre er null), er forelderen f den neste.
            if (p.forelder.høyre == null) return p.forelder;
        }
        return førstePostorden(forelder.høyre);
            //Hvis p ikke er enebarn (dvs. f.høyre er ikke null),
                                            // så er den neste den noden som kommer først i postorden i subtreet med f.høyre som rot.

        //throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public void postorden(Oppgave<? super T> oppgave) {
        if (tom()) throw new NoSuchElementException("Treet er tomt!");

        Node<T> p = førstePostorden(rot);
        while (p != null) {
            oppgave.utførOppgave(p.verdi);
            p = nestePostorden(p);
        }
        //throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public void postordenRecursive(Oppgave<? super T> oppgave) {
        postordenRecursive(rot, oppgave);
    }

    private void postordenRecursive(Node<T> p, Oppgave<? super T> oppgave) {

        //throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public ArrayList<T> serialize() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    static <K> SBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public static void main(String[] args) {
        Integer[] a = {4,7,2,9,4,10,8,7,4,6};
        SBinTre<Integer> tre = new SBinTre<>(Comparator.naturalOrder());
        for (int verdi : a) { tre.leggInn(verdi); }

        System.out.println(tre.antall());      // Utskrift: 10
        System.out.println(tre.antall(5));     // Utskrift: 0
        System.out.println(tre.antall(4));     // Utskrift: 3
        System.out.println(tre.antall(7));     // Utskrift: 2
        System.out.println(tre.antall(10));    // Utskrift: 1
    }

} // ObligSBinTre
