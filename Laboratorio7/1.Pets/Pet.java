import java.util.Scanner;
abstract class Pet {
    protected String nomeAnimale;
    abstract void verso();
    public String getNome(){
        return nomeAnimale;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Inserisci righe nel formato `nomePersona nomeAnimale tipoAnimale` (CTRL+D per terminare)");
        while (sc.hasNext()){
            String riga = sc.nextLine();
            String[] parole = riga.split(" ");
            Persona p = new Persona(parole[0]);
            if (parole[2].equals("Cane")){
                Cane c = new Cane(parole[1]);
                p.add(c);
            }
            
            p.coro();
        }
        sc.close();
    }
}
