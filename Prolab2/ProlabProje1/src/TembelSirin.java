
public class TembelSirin extends Oyuncu {

    public TembelSirin() {
        super(20,2,"Tembel Şirin","iyi",6,5,1);//özelliklerini direkt verdim
    }

    @Override
    public String puaniGoster(Oyuncu oyuncu) {
return  "Tembel Şirinimizin puani : "+super.getSkor();
    }
    
}
