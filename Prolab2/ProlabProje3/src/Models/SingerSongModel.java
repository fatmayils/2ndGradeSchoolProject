package Models;


public class SingerSongModel {
    int singerSongID,singerID,songID;

    public SingerSongModel(int singerSongID, int singerID, int songID) {
        this.singerSongID = singerSongID;
        this.singerID = singerID;
        this.songID = songID;
    }

    public int getSingerSongID() {
        return singerSongID;
    }

    public void setSingerSongID(int singerSongID) {
        this.singerSongID = singerSongID;
    }

    public int getSingerID() {
        return singerID;
    }

    public void setSingerID(int singerID) {
        this.singerID = singerID;
    }

    public int getSongID() {
        return songID;
    }

    public void setSongID(int songID) {
        this.songID = songID;
    }
}
