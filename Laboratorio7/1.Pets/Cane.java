public class Cane extends Pet{
    public Cane(String nome){
        this.nomeAnimale=nome;
    }
    
    @Override
    public void verso(){
        System.out.println(this.nomeAnimale + " dice bau");
    }
}
