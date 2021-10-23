package Models;

public class AlbumModel {

    private int albumID;
    private String albumName;
    private String albumDate;
    private String albumKind;

    public AlbumModel(int albumID, String albumName, String albumDate, String albumKind) {
        this.albumID = albumID;
        this.albumName = albumName;
        this.albumDate = albumDate;
        this.albumKind = albumKind;
    }

    public String getAlbumKind() {
        return albumKind;
    }

    public void setAlbumKind(String albumKind) {
        this.albumKind = albumKind;
    }

    public int getAlbumID() {
        return albumID;
    }

    public void setAlbumID(int AlbumID) {
        this.albumID = AlbumID;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String AlbumName) {
        this.albumName = AlbumName;
    }

    public String getAlbumDate() {
        return albumDate;
    }

    public void setAlbumDate(String AlbumDate) {
        this.albumDate = AlbumDate;
    }
}
