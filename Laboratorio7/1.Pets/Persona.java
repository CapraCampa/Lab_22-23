import java.util.ArrayList;
import java.util.Iterator;
public class Persona implements Iterable<Pet>{
    //OVERVIEW: modella una persona identificata da un nome che avrà una lista di Pets generici
    String nomePersona;
    ArrayList<Pet> pets;

        //MODIFIES: this
        //EFFECTS: crea persona con nome dato come parametro con lista animali vuota
    public Persona(String nome){
        this.nomePersona=nome;
        pets = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: se pet nullo sollevo eccezione, altrimenti lo aggiungo
    public void add(Pet pet){
        pets.add(pet);
    }

    //EFFECTS: crea lista di versi
    public void coro(){
        System.out.println("Il coro degli animali di "+ this.nomePersona + ": ");
        for (Pet p : pets) {
            p.verso();
        }
    }

    @Override
    public boolean equals(Object obj){
        if (obj instanceof Persona){
            return this.nomePersona.equals(((Persona)obj).nomePersona);
        }
        return false;
    }

    public Iterator<Pet> iterator(){
        return new Iterator<Pet>(){
            Iterator<Pet> ip= pets.iterator();

            @Override
            public boolean hasNext() {
                return ip.hasNext();
            }

            @Override
            public Pet next() {
                return ip.next();
            }
            
        };
    } 
}
