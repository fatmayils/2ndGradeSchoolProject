
public class Dusman extends Karakter {
//private int dusmanID;
//private String dusmanAdi,dusmanTür;
    private int reduceOfPoint;
    private int reduceOfPointG;
    private int reduceOfPointT;
    private static int[][] matris=new int[11][13];

    public static int[][] getMatris() {
        return matris;
    }

    public static void setMatris(int[][] matris) {
        Dusman.matris = matris;
    }

    

    public Dusman(int reduceOfPoint, int reduceOfPointG, int reduceOfPointT, int id, String name, String kind, int adim) {
        super(id, name, kind, adim);
        this.reduceOfPoint = reduceOfPoint;
        this.reduceOfPointG = reduceOfPointG;
        this.reduceOfPointT = reduceOfPointT;
    }

    public int getReduceOfPointG() {
        return reduceOfPointG;
    }

    public void setReduceOfPointG(int reduceOfPointG) {
        this.reduceOfPointG = reduceOfPointG;
    }

    public int getReduceOfPointT() {
        return reduceOfPointT;
    }

    public void setReduceOfPointT(int reduceOfPointT) {
        this.reduceOfPointT = reduceOfPointT;
    }

    public Dusman(int reduceOfPoint, int id, String ad, String tur, int adim) {
        super(id, ad, tur, adim);
        this.reduceOfPoint = reduceOfPoint;
    }

    public int getReduceOfPoint() {
        return reduceOfPoint;
    }

    public void setReduceOfPoint(int reduceOfPoint) {
        this.reduceOfPoint = reduceOfPoint;
    }

    public Dusman() {
    }

    public Dusman(int id, String name, String kind, int adim) {
        super(id, name, kind, adim);
    }

    public Dusman(int id, String name, String kind) {
        super(id, name, kind);
    }
}
