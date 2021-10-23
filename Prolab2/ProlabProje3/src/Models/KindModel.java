
package Models;


public class KindModel {
    private int kindID;
    private String kindName;

    public KindModel(int kindID, String kindName) {
        this.kindID = kindID;
        this.kindName = kindName;
    }

    public int getKindID() {
        return kindID;
    }

    public void setKindID(int kindID) {
        this.kindID = kindID;
    }

    public String getKindName() {
        return kindName;
    }

    public void setKindName(String kindName) {
        this.kindName = kindName;
    }

   
}
