
public abstract class Oyuncu extends Karakter{
 
    //private String oyuncuAdi,oyuncuTur;
    //private int oyuncuID,Skor;
    private int skor;
    public abstract String puaniGoster(Oyuncu oyuncu);

    public int getSkor() {
        return skor;
    }

    public void setSkor(int skor) {
        this.skor = skor;
    }

    public Oyuncu(int skor) {
        this.skor = skor;
    }

    public Oyuncu(int id, String ad, String tur, int xLokasyonu, int yLokasyonu, int adim) {
        super(id, ad, tur, xLokasyonu, yLokasyonu, adim);
    }

    public Oyuncu(int skor, int id, String ad, String tur, int xLokasyonu, int yLokasyonu, int adim) {
        super(id, ad, tur, xLokasyonu, yLokasyonu, adim);
        this.skor = skor;
    }

    public Oyuncu() {
    }

   
}
