
package Models;

public class SongModel {
    int songID,viewsCounts;
    String songName,songKind;

    public SongModel(int songID, int viewsCounts, String songName, String songKind, double time) {
        this.songID = songID;
        this.viewsCounts = viewsCounts;
        this.songName = songName;
        this.songKind = songKind;
        this.time = time;
    }

    public String getSongKind() {
        return songKind;
    }

    public void setSongKind(String songKind) {
        this.songKind = songKind;
    }
    double time;

    public SongModel(int songID, int viewsCounts, String songName, double time) {
        this.songID = songID;
        this.viewsCounts = viewsCounts;
        this.songName = songName;
        this.time = time;
    }

    public int getSongID() {
        return songID;
    }

    public void setSongID(int songID) {
        this.songID = songID;
    }
    public int getViewsCounts() {
        return viewsCounts;
    }

    public void setViewsCounts(int viewsCounts) {
        this.viewsCounts = viewsCounts;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }
}
