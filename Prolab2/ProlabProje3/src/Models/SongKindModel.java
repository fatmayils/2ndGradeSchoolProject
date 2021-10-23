
package Models;


public class SongKindModel {
    int songKindID,songID,kindID;

    public SongKindModel(int songKindID, int songID, int kindID) {
        this.songKindID = songKindID;
        this.songID = songID;
        this.kindID = kindID;
    }

    public int getSongKindID() {
        return songKindID;
    }

    public void setSongKindID(int songKindID) {
        this.songKindID = songKindID;
    }

    public int getSongID() {
        return songID;
    }

    public void setSongID(int songID) {
        this.songID = songID;
    }

    public int getKindID() {
        return kindID;
    }

    public void setKindID(int kindID) {
        this.kindID = kindID;
    }
}
