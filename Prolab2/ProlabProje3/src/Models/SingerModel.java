package Models;


public class SingerModel {
 private int singerID;
 private String singerName;
 private String singerCountry;
 public SingerModel(int singerID,String singerName,String singerCountry)
 {
     this.singerID=singerID;
     this.singerName=singerName;
     this.singerCountry=singerCountry;
 }

    public int getSingerID() {
        return singerID;
    }

    public void setSingerID(int singerID) {
        this.singerID = singerID;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getSingerCountry() {
        return singerCountry;
    }

    public void setSingerCountry(String singerCountry) {
        this.singerCountry = singerCountry;
    }
}
