import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Bag<E> implements Iterable<E> {
    // sto usando generico
    // OVERVIEW: modella un Bag che memorizza elementi generici e il numero di volte
    // che sono stati inseriti

    HashMap<E, Integer> map;

    // costruttori
    // MODiFIES: this
    // EFFECTS: inizializzazione di un Bag
    public Bag() {
        this.map = new HashMap<>();

    }

    // REQUIRES: nessuno, controllo io con eccezione se elem è nullo
    // MODIFIES: this
    // EFFECTS: se elem non nullo lo inserisco in this, altrimenti lancio
    // IllegalArgumentException
    public void insert(E elem) throws IllegalArgumentException {
        if (elem == null)
            throw new IllegalArgumentException("Elemento nullo");
        Integer count = this.map.putIfAbsent(elem, 1);
        if (count != null) {
            this.map.replace(elem, ++count);
        }
    }

    // MODIFIES: this
    // EFFECTS: se elem non nullo lo elimino da this, altrimenti lancio
    // IllegalArgumentException
    public void remove(E elem) throws IllegalArgumentException {
        if (elem == null)
            throw new IllegalArgumentException("Elemento nullo");
        Integer count = this.map.get(elem);
        if (count != null) {
            if (count == 1){
                ;
            this.map.remove(elem);
            } else {
            this.map.replace(elem, --count);
            }
        }
    }

    // EFFECTS: restituisce un oggetto iteratore su E

    @Override
    public String toString() {
        String rappr = "Bag - elements: [ ";
        
        for (E i : this) {
            rappr += i + " ";
          }
        //oppure:
        /* Iterator <E> iter = this.iterator();
        while (iter.hasNext()){
            rapp+= iter.next() + " ";
        }
         */
        return rappr + "]";
    }

    //EFFECTS: restituisce un iteratore su E
    //sfrutto iteratore che già esiste per la hashmap (iteratore interno già esiste, il nostro è un iteratore esterno)
    @Override
    public Iterator<E> iterator(){
        return new Iterator<E>(){
                E curr = null;
                int counter = 1;
                Iterator<E> keys = map.keySet().iterator();
                //iteratore che punterà a tutte chiavi della mappa

            
            //EFFECTS: se ci sono ancora elementi nel bag e se il loro contatore è inferiore al numero di elementi da restituire allora rispondi true
            @Override
            public boolean hasNext(){
                if (curr!=null && map.get(curr)>counter){
                    return true;
                }else if ( keys.hasNext()){
                    return true;
                }
            return false;
            }

            //MODIFIES: contatore degli elementi e l'elemento corrente
            //EFFECTS: restituisce il prossimo elemento e aggiorno il contatore
            //se il contatore dell'elemento non supera la disponibilità passo al prox elemento
            //se non ci sono più elementi lancio una NoSuchElementException
            @Override
            public E next() throws NoSuchElementException{
                if (!(this.hasNext())) throw new NoSuchElementException("Non ci sono più elementi");
                if (curr!=null && map.get(curr)>counter){
                    counter++;
                }else{
                    curr=keys.next(); //quando ho finito di leggere una chiave passo alla prossima
                    counter=1;
                }
                return curr;
            }
  
        };
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String type = args[0];
        if (type.equals("String")){
            Bag<String> a = new Bag<>();
            System.out.println("Inserisci stringhe (CTRL-D per terminare)");
            while (sc.hasNext()){
                a.insert(sc.next());
            }
            System.out.println(a);
        }else if (type.equals("Integer")){
            Bag<Integer> b = new Bag<>();
            System.out.println("Inserisci interi (CTRL-D per terminare)");
            while (sc.hasNextInt()){
                b.insert(sc.nextInt());
            }
            System.out.println(b);
        }else if (type.equals("Double")){
            Bag<Double> c = new Bag<>();
            System.out.println("Inserisci double (CTRL-D per terminare)");
            while (sc.hasNextDouble()){
                c.insert(sc.nextDouble());
            }
            System.out.println(c);
        }
        sc.close();
        
    }

}
