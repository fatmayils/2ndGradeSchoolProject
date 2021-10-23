
public class Puan extends Oyuncu {

    @Override
    public String puaniGoster(Oyuncu oyuncu) {
        if(oyuncu.getName().equals("Gözlüklü Şirin"))
        {
           return  "Gözlüklü Şirinimizin puani : "+oyuncu.getSkor();
        }
        return  "Tembel  Şirinimizin puani : "+oyuncu.getSkor();
    }
    
}
