/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

public class ListeningSongModel {

    int songID, listenCount, time;
    String songName, kindName, albumName, albumDate;

    public String getAlbumDate() {
        return albumDate;
    }

    public void setAlbumDate(String albumDate) {
        this.albumDate = albumDate;
    }

    public ListeningSongModel(int songID, String songName, String kindName, String albumName, int listenCount, int time) {
        this.songID = songID;
        this.listenCount = listenCount;

        this.time = time;
        this.songName = songName;
        this.kindName = kindName;
        this.albumName = albumName;
    }

    public ListeningSongModel(int songID, String songName, String kindName, String albumName, int listenCount, int time, String albumDate) {
        this.songID = songID;
        this.listenCount = listenCount;
        this.albumDate = albumDate;
        this.time = time;
        this.songName = songName;
        this.kindName = kindName;
        this.albumName = albumName;
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
