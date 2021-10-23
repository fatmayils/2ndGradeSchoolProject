package Models;

public class UserListeningSongModel {

 

    int songID, listenCount, userListenCount, time;
String songName, kindName, albumName,albumDate;

    public String getAlbumDate() {
        return albumDate;
    }

    public void setAlbumDate(String albumDate) {
        this.albumDate = albumDate;
    }
   public UserListeningSongModel(int songID, String songName, String kindName, String albumName, int listenCount, int userListenCount, int time) {
        this.songID = songID;
        this.listenCount = listenCount;
        this.userListenCount = userListenCount;
        this.time = time;
        this.songName = songName;
        this.kindName = kindName;
        this.albumName = albumName;
    }
    public UserListeningSongModel(int songID, String songName, String kindName, String albumName, int listenCount, int userListenCount, int time,String albumDate) {
        this.songID = songID;
        this.listenCount = listenCount;
        this.userListenCount = userListenCount;
        this.time = time;
        this.songName = songName;
        this.kindName = kindName;
        this.albumName = albumName;
        this.albumDate=albumDate;
    }
    public int getSongID() {
        return songID;
    }

    public void setSongID(int songID) {
        this.songID = songID;
    }

    public int getListenCount() {
        return listenCount;
    }

    public void setListenCount(int listenCount) {
        this.listenCount = listenCount;
    }

    public int getUserListenCount() {
        return userListenCount;
    }

    public void setUserListenCount(int userListenCount) {
        this.userListenCount = userListenCount;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getKindName() {
        return kindName;
    }

    public void setKindName(String kindName) {
        this.kindName = kindName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }
    
}
