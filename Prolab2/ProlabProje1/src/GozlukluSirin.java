
public class GozlukluSirin extends Oyuncu {
    
    public GozlukluSirin() {
        super(20,1,"Gözlüklü Şirin","Dost",6,5,2);//özelliklerini direkt verdim
    }
    @Override
    public String puaniGoster(Oyuncu oyuncu) {
return  "Gözlüklü Şirinimizin puani : "+super.getSkor();    }
}
