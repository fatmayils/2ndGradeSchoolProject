package Models;

public class PlayListModel {

    int playListID, songID, time, viewCount;
    String userName, songName, albumName, kindName,albumDate;

    public PlayListModel( int playListID, int songID, int time, int viewCount, String userName, String songName, String albumName, String kindName) {
        
        this.playListID = playListID;
        this.songID = songID;
        this.time = time;
        this.viewCount = viewCount;
      
        this.userName = userName;
        this.songName = songName;
        this.albumName = albumName;
        this.kindName = kindName;
    }

    public int getPlayListID() {
        return playListID;
    }

    public void setPlayListID(int playListID) {
        this.playListID = playListID;
    }

    public int getSongID() {
        return songID;
    }

    public void setSongID(int songID) {
        this.songID = songID;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getKindName() {
        return kindName;
    }

    public void setKindName(String kindName) {
        this.kindName = kindName;
    }
}
