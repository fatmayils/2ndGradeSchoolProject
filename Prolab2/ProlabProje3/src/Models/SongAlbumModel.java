
package Models;

public class SongAlbumModel {
   int songAlbumID,songID,albumID; 

    public SongAlbumModel(int songAlbumID, int songID, int albumID) {
        this.songAlbumID = songAlbumID;
        this.songID = songID;
        this.albumID = albumID;
    }

    public int getSongAlbumID() {
        return songAlbumID;
    }

    public void setSongAlbumID(int songAlbumID) {
        this.songAlbumID = songAlbumID;
    }

    public int getSongID() {
        return songID;
    }

    public void setSongID(int songID) {
        this.songID = songID;
    }

    public int getAlbumID() {
        return albumID;
    }

    public void setAlbumID(int albumID) {
        this.albumID = albumID;
    }
}
