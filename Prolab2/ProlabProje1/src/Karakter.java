
import java.util.ArrayList;
public class Karakter {

    private int M = 11;
    private int N = 13;
    static int[][] map=new int[11][13];

    public static int[][] getMap() {
        return map;
    }

    public static void setMap(int[][] map) {
        Karakter.map = map;
    }
    private int id;
    private String name;
    private String kind;
    private int xLokasyonu;//başta oluşturmuştum karakterlere ilk başlangıcı vermek için
    private int yLokasyonu;//ama kullanmadım.
    private final ArrayList<Locasyon> arr = new ArrayList<>();
    int adim;
    int min;

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }
    private Locasyon lokasyon = new Locasyon();//locasyon sınıfından bir değişken yarattım.Kolaylıkla karakterin yerini tutabiliyorum.

    public Karakter(int id, String name, String kind, int xLokasyonu, int yLokasyonu, int adim) {
        this.id = id;
        this.name = name;
        this.kind = kind;
        this.xLokasyonu = xLokasyonu;
        this.yLokasyonu = yLokasyonu;
        this.adim = adim;

    }

    public Karakter() {

    }

    public void shortestPath(int goodX, int goodY) {
        GraphShow graf = new GraphShow(goodX, goodY, getLokasyon().getY(), getLokasyon().getX());
    }

    public Karakter(int id, String name, String kind, int adim) {
        this.id = id;
        this.name = name;
        this.kind = kind;
        this.adim = adim;
    }

    public Karakter(int id, String name, String kind) {
        this.id = id;
        this.name = name;
        this.kind = kind;

    }

    public Locasyon getLokasyon() {
        return lokasyon;
    }

    public void setLokasyon(Locasyon lokasyon) {
        this.lokasyon = lokasyon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public int getxLokasyonu() {
        return xLokasyonu;
    }

    public void setxLokasyonu(int xLokasyonu) {
        this.xLokasyonu = xLokasyonu;
    }

    public int getyLokasyonu() {
        return yLokasyonu;
    }

    public void setyLokasyonu(int yLokasyonu) {
        this.yLokasyonu = yLokasyonu;
    }

}
