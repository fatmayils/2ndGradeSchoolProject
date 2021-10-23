
import Models.ListeningSongModel;
import Models.PlayListModel;
import Models.SongModel;
import Models.UserListeningSongModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class HomePage extends javax.swing.JFrame {
    
    DefaultTableModel model;
    Connection connection = null;
    PreparedStatement preparedStatement;
    Statement statement = null;
    ResultSet resultSet = null;
    String query;
    int number;
    String kind;
    String myListName;
    String listUserNamee, listNamee;
    String listUserNameee, listNameee;
    String listUserNameeee, listNameeee;
    
    public HomePage() throws SQLException, Exception {
        initComponents();
        int x = 0;
        myListPanel.setVisible(false);
        this.getContentPane().setBackground(new Color(51, 51, 51));
        System.out.println(LoginScreen.name);
        System.out.println(LoginScreen.password);
        nameLabel.setText(LoginScreen.name);
        countryLabel.setText(LoginScreen.country);
        connection = DatabaseConnection.connect();
        statement = connection.createStatement();
        query = "select * from premiumaccount where UserID='" + LoginScreen.id + "'";
        resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            x++;
        }
        if (x == 0) {
            premornorm.setText("Free ");
        } else {
            premornorm.setText("Premium ");
        }
        connection.close();
        gozatPanel.setBackground(Color.BLACK);
        albumlerPanel.setVisible(false);
        girisPanel.setVisible(false);
        sanatcilerPanel.setVisible(false);
        gozatPanel.setVisible(false);
        jPanel5.setVisible(false);
        muzikTurleriPanel5.setVisible(false);
        albumlerPanel5.setVisible(false);
        sanatcilerPanel5.setVisible(false);
        listelerPanel5.setVisible(false);
        muzikTurleriAltPanel.setVisible(false);
        albumlerAltPanel.setVisible(false);
        sanatcilarAltPanel.setVisible(false);
        listelerAltPanel.setVisible(false);
        girisAltPanel.setVisible(false);
        muziklerAltPanel.setVisible(false);
        listelerAltAltPanel.setVisible(false);
        tursuzPanel.setVisible(false);
        singlePanel.setVisible(false);
        followedList_UserPanel.setVisible(false);
        newListPanel.setVisible(false);
        myListAltPanel.setVisible(false);
        myListPanel.setVisible(false);
        this.setLocationRelativeTo(null);
        setLocationRelativeTo(null);
    }
    //kapalı sayfalar
    public void close() {
        albumlerPanel.setVisible(false);
        girisPanel.setVisible(false);
        sanatcilerPanel.setVisible(false);
        gozatPanel.setVisible(false);
        jPanel5.setVisible(false);
        muzikTurleriPanel5.setVisible(false);
        albumlerPanel5.setVisible(false);
        sanatcilerPanel5.setVisible(false);
        listelerPanel5.setVisible(false);
        muzikTurleriAltPanel.setVisible(false);
        albumlerAltPanel.setVisible(false);
        sanatcilarAltPanel.setVisible(false);
        listelerAltPanel.setVisible(false);
        girisAltPanel.setVisible(false);
        muziklerAltPanel.setVisible(false);
        listelerAltAltPanel.setVisible(false);
        tursuzPanel.setVisible(false);
        singlePanel.setVisible(false);
        followedList_UserPanel.setVisible(false);
        newListPanel.setVisible(false);
    }
    //kullanıcının dinlediği müzikler
    public void showInTable(String kind) throws Exception {
        ArrayList<UserListeningSongModel> list = getList(kind);
        model = (DefaultTableModel) table.getModel();
        Object[] row = new Object[8];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getSongID();
            row[1] = list.get(i).getSongName();
            row[2] = list.get(i).getKindName();
            row[3] = list.get(i).getAlbumName();
            row[4] = list.get(i).getListenCount();
            row[5] = list.get(i).getUserListenCount();
            row[6] = list.get(i).getTime();
            row[7] = list.get(i).getAlbumDate();
            
            model.addRow(row);
        }
        
    }
    
    public ArrayList<UserListeningSongModel> getList(String kind) throws Exception {
        ArrayList<UserListeningSongModel> songsList = new ArrayList<UserListeningSongModel>();
        connection = DatabaseConnection.connect();
        query = "select so.songID,so.songName,ki.KindName,a.AlbumName,a.Date,so.ViewCount,uls.ListeningCount,so.Time,a.Date\n"
                + "from  songs so left outer join song_kinds sk \n"
                + "ON so.SongID=sk.SongID\n"
                + " left outer join kinds ki\n"
                + " ON sk.KindID=ki.KindID\n"
                + "  left outer join song_albums sa\n"
                + "  on so.SongID=sa.SongID\n"
                + "   left outer join albums a\n"
                + " on sa.AlbumID=a.AlbumID\n"
                + " left outer join user_listening_song uls\n"
                + " ON so.SongID=uls.SongID\n"
                + "  left outer join users u\n"
                + "  on u.UserID=uls.UserID "
                + "where u.UserName='" + LoginScreen.name + "'"
                + "and ki.KindName='" + kind + "'";
        Statement statement;
        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);
        UserListeningSongModel song;
        while (resultSet.next()) {
            song = new UserListeningSongModel(resultSet.getInt("SongID"), resultSet.getString("SongName"), resultSet.getString("KindName"),
                    resultSet.getString("AlbumName"), resultSet.getInt("ViewCount"), resultSet.getInt("ListeningCount"), resultSet.getInt("Time"), resultSet.getString("Date"));
            songsList.add(song);
        }
        connection.close();
        return songsList;
        
    }
    //kullanıcının playlistindeki şarkılar
    public void showInTableListeler(String listUserName, String listName) throws Exception {
        ArrayList<PlayListModel> list = getListeler(listUserName, listName);
        model = (DefaultTableModel) tableList.getModel();
        Object[] row = new Object[8];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getUserName();
            row[1] = list.get(i).getPlayListID();
            row[2] = list.get(i).getKindName();
            row[3] = list.get(i).getAlbumName();
            row[4] = list.get(i).getSongID();
            row[5] = list.get(i).getSongName();
            row[6] = list.get(i).getTime();
            row[7] = list.get(i).getViewCount();
            model.addRow(row);
        }
        
    }
    
    public ArrayList<PlayListModel> getListeler(String listUserName, String listName) throws Exception {
        ArrayList<PlayListModel> songsList = new ArrayList<PlayListModel>();
        connection = DatabaseConnection.connect();
        query = "select so.SongName,pl.PlayListID,a.AlbumName,ki.KindName,so.SongID,so.Time,so.ViewCount,u.UserName"
                + " from songs so,kinds ki,albums a,song_albums sa,song_kinds sk,"
                + "play_lists pl,play_list_songs pls,users u"
                + " where u.UserID=pl.UserID"
                + "  and u.UserName='" + listUserName + "'"
                + "  and pl.PlayListName='" + listName + "'"
                + "  and pl.PlayListID=pls.PlayListID"
                + "  and pls.SongID=so.SongID  "
                + "and so.SongID=sk.SongID "
                + " and so.SongID=sa.SongID "
                + "  and sa.AlbumID=a.AlbumID "
                + " and sk.KindID=ki.KindID";
        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);
        PlayListModel song;
        while (resultSet.next()) {
            song = new PlayListModel(resultSet.getInt("PlayListID"), resultSet.getInt("SongID"), resultSet.getInt("Time"),
                    resultSet.getInt("ViewCount"), resultSet.getString("UserName"),
                    resultSet.getString("SongName"), resultSet.getString("AlbumName"), resultSet.getString("KindName")
            );
            songsList.add(song);
        }
        connection.close();
        return songsList;
        
    }
    //takip edilen playlistlerin listesindeki şarkılar
    public void showInTableListelerFoll(String listUserName, String listName) throws Exception {
        ArrayList<PlayListModel> list = getListelerFoll(listUserName, listName);
        model = (DefaultTableModel) followedListTable.getModel();
        Object[] row = new Object[8];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getUserName();
            row[1] = list.get(i).getPlayListID();
            row[2] = list.get(i).getKindName();
            row[3] = list.get(i).getAlbumName();
            row[4] = list.get(i).getSongID();
            row[5] = list.get(i).getSongName();
            row[6] = list.get(i).getTime();
            row[7] = list.get(i).getViewCount();
            model.addRow(row);
        }
        
    }
    
    public ArrayList<PlayListModel> getListelerFoll(String listUserName, String listName) throws Exception {
        
        ArrayList<PlayListModel> songsList = new ArrayList<PlayListModel>();
        connection = DatabaseConnection.connect();
        query = "select so.SongName,pl.PlayListID,a.AlbumName,ki.KindName,so.SongID,so.Time,so.ViewCount,u.UserName"
                + " from songs so,kinds ki,albums a,song_albums sa,song_kinds sk,"
                + "play_lists pl,play_list_songs pls,users u"
                + " where u.UserID=pl.UserID"
                + "  and u.UserName='" + listUserName + "'"
                + "  and pl.PlayListName='" + listName + "'"
                + "  and pl.PlayListID=pls.PlayListID"
                + "  and pls.SongID=so.SongID  "
                + "and so.SongID=sk.SongID "
                + " and so.SongID=sa.SongID "
                + "  and sa.AlbumID=a.AlbumID "
                + " and sk.KindID=ki.KindID";
        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);
        PlayListModel song;
        while (resultSet.next()) {
            song = new PlayListModel(resultSet.getInt("PlayListID"), resultSet.getInt("SongID"), resultSet.getInt("Time"),
                    resultSet.getInt("ViewCount"), resultSet.getString("UserName"),
                    resultSet.getString("SongName"), resultSet.getString("AlbumName"), resultSet.getString("KindName")
            );
            songsList.add(song);
        }
        connection.close();
        return songsList;
        
    }
    //takip eden kullanıcıların listesindeki şarkılar
    public void showInTableListelerUserFoll(String listUserName, String listName) throws Exception {
        ArrayList<PlayListModel> list = getListelerUserFoll(listUserName, listName);
        model = (DefaultTableModel) followedUserListTable.getModel();
        Object[] row = new Object[8];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getUserName();
            row[1] = list.get(i).getPlayListID();
            row[2] = list.get(i).getKindName();
            row[3] = list.get(i).getAlbumName();
            row[4] = list.get(i).getSongID();
            row[5] = list.get(i).getSongName();
            row[6] = list.get(i).getTime();
            row[7] = list.get(i).getViewCount();
            model.addRow(row);
        }
        
    }
    
    public ArrayList<PlayListModel> getListelerUserFoll(String listUserName, String listName) throws Exception {
        
        ArrayList<PlayListModel> songsList = new ArrayList<PlayListModel>();
        connection = DatabaseConnection.connect();
        query = "select so.SongName,pl.PlayListID,a.AlbumName,ki.KindName,so.SongID,so.Time,so.ViewCount,u.UserName"
                + " from songs so,kinds ki,albums a,song_albums sa,song_kinds sk,"
                + "play_lists pl,play_list_songs pls,users u"
                + " where u.UserID=pl.UserID"
                + "  and u.UserName='" + listUserName + "'"
                + "  and pl.PlayListName='" + listName + "'"
                + "  and pl.PlayListID=pls.PlayListID"
                + "  and pls.SongID=so.SongID  "
                + "and so.SongID=sk.SongID "
                + " and so.SongID=sa.SongID "
                + "  and sa.AlbumID=a.AlbumID "
                + " and sk.KindID=ki.KindID";
        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);
        PlayListModel song;
        while (resultSet.next()) {
            song = new PlayListModel(resultSet.getInt("PlayListID"), resultSet.getInt("SongID"), resultSet.getInt("Time"),
                    resultSet.getInt("ViewCount"), resultSet.getString("UserName"),
                    resultSet.getString("SongName"), resultSet.getString("AlbumName"), resultSet.getString("KindName")
            );
            songsList.add(song);
        }
        connection.close();
        return songsList;
        
    }
    //tüm müzikleri listeliyor
    public void showInTableGenel() throws Exception {
        ArrayList<ListeningSongModel> list = getListGenel();
        model = (DefaultTableModel) tableGenel.getModel();
        Object[] row = new Object[7];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getSongID();
            row[1] = list.get(i).getSongName();
            row[2] = list.get(i).getKindName();
            row[3] = list.get(i).getAlbumName();
            row[4] = list.get(i).getListenCount();
            row[5] = list.get(i).getTime();
            row[6] = list.get(i).getAlbumDate();
            
            model.addRow(row);
        }
        
    }
    
    public ArrayList<ListeningSongModel> getListGenel() throws Exception {
        ArrayList<ListeningSongModel> songsList = new ArrayList<ListeningSongModel>();
        connection = DatabaseConnection.connect();
        query = "select so.songID,so.songName,ki.KindName,a.AlbumName,a.Date,so.ViewCount,so.Time,a.Date\n"
                + " from  songs so left outer join song_kinds sk \n"
                + " ON so.SongID=sk.SongID\n"
                + " left outer join kinds ki\n"
                + " ON sk.KindID=ki.KindID\n"
                + "  left outer join song_albums sa\n"
                + "  on so.SongID=sa.SongID\n"
                + "   left outer join albums a\n"
                + " on sa.AlbumID=a.AlbumID\n";
        
        Statement statement;
        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);
        ListeningSongModel song;
        while (resultSet.next()) {
            song = new ListeningSongModel(resultSet.getInt("SongID"), resultSet.getString("SongName"), resultSet.getString("KindName"),
                    resultSet.getString("AlbumName"), resultSet.getInt("ViewCount"), resultSet.getInt("Time"), resultSet.getString("Date"));
            songsList.add(song);
        }
        connection.close();
        return songsList;
        
    }
    //kendi playlistimdeki şarkıları listeliyor
    public void showInTableMyListeler(String listName) throws Exception {
        ArrayList<PlayListModel> list = getMyListeler(listName);
        model = (DefaultTableModel) myTable.getModel();
        Object[] row = new Object[8];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getUserName();
            row[1] = list.get(i).getPlayListID();
            row[2] = list.get(i).getKindName();
            row[3] = list.get(i).getAlbumName();
            row[4] = list.get(i).getSongID();
            row[5] = list.get(i).getSongName();
            row[6] = list.get(i).getTime();
            row[7] = list.get(i).getViewCount();
            model.addRow(row);
        }
        
    }
    
    public ArrayList<PlayListModel> getMyListeler(String listName) throws Exception {
        
        ArrayList<PlayListModel> songsList = new ArrayList<PlayListModel>();
        connection = DatabaseConnection.connect();
        query = "select so.SongName,pl.PlayListID,a.AlbumName,ki.KindName,so.SongID,so.Time,so.ViewCount,u.UserName"
                + " from songs so,kinds ki,albums a,song_albums sa,song_kinds sk,"
                + "play_lists pl,play_list_songs pls,users u"
                + " where u.UserID=pl.UserID"
                + "  and u.UserName='" + LoginScreen.name + "'"
                + "  and pl.PlayListName='" + listName + "'"
                + "  and pl.PlayListID=pls.PlayListID"
                + "  and pls.SongID=so.SongID  "
                + "and so.SongID=sk.SongID "
                + " and so.SongID=sa.SongID "
                + "  and sa.AlbumID=a.AlbumID "
                + " and sk.KindID=ki.KindID";
        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);
        PlayListModel song;
        while (resultSet.next()) {
            song = new PlayListModel(resultSet.getInt("PlayListID"), resultSet.getInt("SongID"), resultSet.getInt("Time"),
                    resultSet.getInt("ViewCount"), resultSet.getString("UserName"),
                    resultSet.getString("SongName"), resultSet.getString("AlbumName"), resultSet.getString("KindName")
            );
            songsList.add(song);
        }
        connection.close();
        return songsList;
        
    }
    //turu belirsiz olan şarkıları listeliyor
    public void showInTableTursuzSong() throws Exception {
        ArrayList<SongModel> list = getTursuzSongList();
        model = (DefaultTableModel) tursuzTable.getModel();
        Object[] row = new Object[4];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getSongID();
            row[1] = list.get(i).getSongName();
            row[2] = list.get(i).getViewsCounts();
            row[3] = list.get(i).getTime();
            model.addRow(row);
        }
        
    }
    
    public ArrayList<SongModel> getTursuzSongList() throws Exception {
        ArrayList<SongModel> songsList = new ArrayList<SongModel>();
        connection = DatabaseConnection.connect();
        query = "SELECT * from songs s where s.SongID not in(select sk.SongID from song_kinds sk)";
        Statement statement;
        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);
        SongModel song;
        while (resultSet.next()) {
            song = new SongModel(resultSet.getInt("SongID"), resultSet.getInt("ViewCount"), resultSet.getString("SongName"), resultSet.getDouble("Time"));
            songsList.add(song);
        }
        connection.close();
        return songsList;
        
    }
    //türü belli ama albümü olmayan şarkıları listeliyor
    public void showInTableSingleSong() throws Exception {
        ArrayList<SongModel> list = getSingleSongList();
        model = (DefaultTableModel) singleTable.getModel();
        Object[] row = new Object[5];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getSongID();
            row[1] = list.get(i).getSongName();
            row[2] = list.get(i).getViewsCounts();
            row[3] = list.get(i).getSongKind();
            row[4] = list.get(i).getTime();
            model.addRow(row);
        }
        
    }
    
    public ArrayList<SongModel> getSingleSongList() throws Exception {
        ArrayList<SongModel> songsList = new ArrayList<SongModel>();
        connection = DatabaseConnection.connect();
        query = "SELECT s.*,k.KindName from songs s,song_kinds sk,kinds k where s.SongID not in(SELECT sa.SongID from song_albums sa) "
                + "               and s.SongID=sk.SongID and sk.KindID=k.KindID";
        Statement statement;
        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);
        SongModel song;
        while (resultSet.next()) {
            song = new SongModel(resultSet.getInt("SongID"), resultSet.getInt("ViewCount"), resultSet.getString("SongName"), resultSet.getString("KindName"), resultSet.getDouble("Time"));
            songsList.add(song);
        }
        connection.close();
        return songsList;
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        girisLabel = new javax.swing.JLabel();
        gozatLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        albumlerLabel = new javax.swing.JLabel();
        sanatcilarLabel = new javax.swing.JLabel();
        listeler = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        followedListLabel = new javax.swing.JLabel();
        followedUserLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        nameLabel = new javax.swing.JLabel();
        countryLabel = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        premornorm = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        girisPanel = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        popLabel = new javax.swing.JLabel();
        jazzLabel = new javax.swing.JLabel();
        klasikLabel = new javax.swing.JLabel();
        genelLabel = new javax.swing.JLabel();
        ulkeLabel = new javax.swing.JLabel();
        popLabelUsers = new javax.swing.JLabel();
        jazzLabelUsers = new javax.swing.JLabel();
        klasikLabelUsers = new javax.swing.JLabel();
        genelLabelUsers = new javax.swing.JLabel();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        girisAltPanel = new javax.swing.JPanel();
        girisAltAltPanel = new javax.swing.JPanel();
        gozatPanel = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        muzikTurleriLabel5 = new javax.swing.JLabel();
        albumlerLabel5 = new javax.swing.JLabel();
        sanatcilerLabel5 = new javax.swing.JLabel();
        listelerLabel5 = new javax.swing.JLabel();
        muzikler = new javax.swing.JLabel();
        single = new javax.swing.JLabel();
        tursuz = new javax.swing.JLabel();
        jLayeredPane3 = new javax.swing.JLayeredPane();
        muzikTurleriPanel5 = new javax.swing.JPanel();
        albumlerPanel5 = new javax.swing.JPanel();
        sanatcilerPanel5 = new javax.swing.JPanel();
        listelerPanel5 = new javax.swing.JPanel();
        muzikTurleriAltPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        albumlerAltPanel = new javax.swing.JPanel();
        sanatcilarAltPanel = new javax.swing.JPanel();
        listelerAltPanel = new javax.swing.JPanel();
        muziklerAltPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableGenel = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        muziklerAltSongAdd = new javax.swing.JTextField();
        muziklerAltadd = new javax.swing.JLabel();
        muzikleraltaltOptionalList = new javax.swing.JTextField();
        muziklerAltaddOptional = new javax.swing.JLabel();
        listelerAltAltPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableList = new javax.swing.JTable();
        listAltAltAdd = new javax.swing.JLabel();
        addedSongName = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        addedListNameOPtional = new javax.swing.JTextField();
        listAltAltAddOptional = new javax.swing.JLabel();
        singlePanel = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        singleTable = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        addedSongNameSingle = new javax.swing.JTextField();
        addedListNameOPtionalSingle = new javax.swing.JTextField();
        listAltAltAdd1 = new javax.swing.JLabel();
        addOptionalSingle = new javax.swing.JLabel();
        tursuzPanel = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tursuzTable = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        addedSongNameTursuz = new javax.swing.JTextField();
        listAltAltAdd2 = new javax.swing.JLabel();
        addedListNameOPtionalTursuz = new javax.swing.JTextField();
        addOptionalTursuz = new javax.swing.JLabel();
        albumlerPanel = new javax.swing.JPanel();
        jLayeredPane4 = new javax.swing.JLayeredPane();
        followedAlbumPanel = new javax.swing.JPanel();
        followedAlbumAltPanel = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        sanatcilerPanel = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLayeredPane5 = new javax.swing.JLayeredPane();
        followedSingerPanel = new javax.swing.JPanel();
        followedSingerAltPanel = new javax.swing.JPanel();
        followedList_UserPanel = new javax.swing.JPanel();
        followedListPanel1 = new javax.swing.JLayeredPane();
        followedListPanel = new javax.swing.JPanel();
        followedUserPanel = new javax.swing.JPanel();
        followedUserAltPanel = new javax.swing.JPanel();
        followedListAltPanel = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        followedListTable = new javax.swing.JTable();
        followedUserALtAltPanel = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        followedUserListTable = new javax.swing.JTable();
        myListPanel = new javax.swing.JPanel();
        newListPanel = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        newListAddLabel = new javax.swing.JLabel();
        newListLabel = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        myListAltPanel = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        myTable = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        silName = new javax.swing.JTextField();
        sil = new javax.swing.JLabel();
        silNameLiist = new javax.swing.JTextField();
        silOther = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(24, 24, 24));

        girisLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        girisLabel.setForeground(new java.awt.Color(255, 255, 255));
        girisLabel.setText("Giriş");
        girisLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                girisLabelMouseClicked(evt);
            }
        });

        gozatLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        gozatLabel.setForeground(new java.awt.Color(255, 255, 255));
        gozatLabel.setText("Gözat");
        gozatLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                gozatLabelMouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("~Kitaplığın~");

        albumlerLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        albumlerLabel.setForeground(new java.awt.Color(255, 255, 255));
        albumlerLabel.setText("Albümler");
        albumlerLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                albumlerLabelMouseClicked(evt);
            }
        });

        sanatcilarLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sanatcilarLabel.setForeground(new java.awt.Color(255, 255, 255));
        sanatcilarLabel.setText("Sanatçılar");
        sanatcilarLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sanatcilarLabelMouseClicked(evt);
            }
        });

        listeler.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        listeler.setForeground(new java.awt.Color(255, 255, 255));
        listeler.setText("~Çalma Listeleri~");
        listeler.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listelerMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("+Yeni Çalma Listesi");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("~Takip Ettiğin");

        followedListLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        followedListLabel.setForeground(new java.awt.Color(255, 255, 255));
        followedListLabel.setText("PlayListler");
        followedListLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                followedListLabelMouseClicked(evt);
            }
        });

        followedUserLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        followedUserLabel.setForeground(new java.awt.Color(255, 255, 255));
        followedUserLabel.setText("Kişiler");
        followedUserLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                followedUserLabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sanatcilarLabel)
                            .addComponent(albumlerLabel)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(girisLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(gozatLabel))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(listeler)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(followedUserLabel)
                            .addComponent(followedListLabel))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(girisLabel)
                .addGap(26, 26, 26)
                .addComponent(gozatLabel)
                .addGap(28, 28, 28)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(albumlerLabel)
                .addGap(18, 18, 18)
                .addComponent(sanatcilarLabel)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(followedListLabel)
                .addGap(18, 18, 18)
                .addComponent(followedUserLabel)
                .addGap(18, 18, 18)
                .addComponent(listeler)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(29, 29, 29))
        );

        jPanel2.setBackground(new java.awt.Color(24, 24, 24));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 168, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 802, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(34, 34, 34));

        nameLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        nameLabel.setForeground(new java.awt.Color(255, 255, 255));
        nameLabel.setText("jLabel2");

        countryLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        countryLabel.setForeground(new java.awt.Color(255, 255, 255));
        countryLabel.setText("jLabel11");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Log Out");
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });

        premornorm.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        premornorm.setForeground(new java.awt.Color(255, 255, 255));
        premornorm.setText("jLabel13");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Delete This Account");
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(414, Short.MAX_VALUE)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(premornorm, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(countryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel)
                    .addComponent(countryLabel)
                    .addComponent(jLabel11)
                    .addComponent(premornorm)
                    .addComponent(jLabel15))
                .addGap(21, 21, 21))
        );

        girisPanel.setBackground(new java.awt.Color(51, 51, 51));
        girisPanel.setPreferredSize(new java.awt.Dimension(1129, 0));

        jPanel4.setBackground(new java.awt.Color(28, 28, 28));

        popLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        popLabel.setForeground(new java.awt.Color(255, 255, 255));
        popLabel.setText("En çok dinlenen Pop top10");
        popLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                popLabelMouseClicked(evt);
            }
        });

        jazzLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jazzLabel.setForeground(new java.awt.Color(255, 255, 255));
        jazzLabel.setText("En çok dinlenen Jazz top10");
        jazzLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jazzLabelMouseClicked(evt);
            }
        });

        klasikLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        klasikLabel.setForeground(new java.awt.Color(255, 255, 255));
        klasikLabel.setText("En çok dinlenen Klasik top10");
        klasikLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                klasikLabelMouseClicked(evt);
            }
        });

        genelLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        genelLabel.setForeground(new java.awt.Color(255, 255, 255));
        genelLabel.setText("Top10");
        genelLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                genelLabelMouseClicked(evt);
            }
        });

        ulkeLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ulkeLabel.setForeground(new java.awt.Color(255, 255, 255));
        ulkeLabel.setText("Ülke bazında en çok dinlenen top10");
        ulkeLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ulkeLabelMouseClicked(evt);
            }
        });

        popLabelUsers.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        popLabelUsers.setForeground(new java.awt.Color(255, 255, 255));
        popLabelUsers.setText("for users  Pop top10");
        popLabelUsers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                popLabelUsersMouseClicked(evt);
            }
        });

        jazzLabelUsers.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jazzLabelUsers.setForeground(new java.awt.Color(255, 255, 255));
        jazzLabelUsers.setText("for users Jazz top10");
        jazzLabelUsers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jazzLabelUsersMouseClicked(evt);
            }
        });

        klasikLabelUsers.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        klasikLabelUsers.setForeground(new java.awt.Color(255, 255, 255));
        klasikLabelUsers.setText("for users Klasik top10");
        klasikLabelUsers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                klasikLabelUsersMouseClicked(evt);
            }
        });

        genelLabelUsers.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        genelLabelUsers.setForeground(new java.awt.Color(255, 255, 255));
        genelLabelUsers.setText("for users Top10");
        genelLabelUsers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                genelLabelUsersMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(popLabel)
                        .addGap(27, 27, 27)
                        .addComponent(jazzLabel))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(popLabelUsers)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jazzLabelUsers)
                        .addGap(26, 26, 26)))
                .addGap(27, 27, 27)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(klasikLabel)
                        .addGap(30, 30, 30)
                        .addComponent(genelLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                        .addComponent(ulkeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(klasikLabelUsers)
                        .addGap(33, 33, 33)
                        .addComponent(genelLabelUsers)
                        .addGap(288, 288, 288))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(popLabelUsers)
                    .addComponent(jazzLabelUsers)
                    .addComponent(klasikLabelUsers)
                    .addComponent(genelLabelUsers))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(popLabel)
                    .addComponent(jazzLabel)
                    .addComponent(klasikLabel)
                    .addComponent(genelLabel)
                    .addComponent(ulkeLabel))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        girisAltPanel.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout girisAltPanelLayout = new javax.swing.GroupLayout(girisAltPanel);
        girisAltPanel.setLayout(girisAltPanelLayout);
        girisAltPanelLayout.setHorizontalGroup(
            girisAltPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1129, Short.MAX_VALUE)
        );
        girisAltPanelLayout.setVerticalGroup(
            girisAltPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 652, Short.MAX_VALUE)
        );

        girisAltAltPanel.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout girisAltAltPanelLayout = new javax.swing.GroupLayout(girisAltAltPanel);
        girisAltAltPanel.setLayout(girisAltAltPanelLayout);
        girisAltAltPanelLayout.setHorizontalGroup(
            girisAltAltPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1146, Short.MAX_VALUE)
        );
        girisAltAltPanelLayout.setVerticalGroup(
            girisAltAltPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 663, Short.MAX_VALUE)
        );

        jLayeredPane2.setLayer(girisAltPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(girisAltAltPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane2Layout = new javax.swing.GroupLayout(jLayeredPane2);
        jLayeredPane2.setLayout(jLayeredPane2Layout);
        jLayeredPane2Layout.setHorizontalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(girisAltPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(girisAltAltPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane2Layout.setVerticalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addComponent(girisAltPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 22, Short.MAX_VALUE))
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane2Layout.createSequentialGroup()
                    .addComponent(girisAltAltPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout girisPanelLayout = new javax.swing.GroupLayout(girisPanel);
        girisPanel.setLayout(girisPanelLayout);
        girisPanelLayout.setHorizontalGroup(
            girisPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLayeredPane2)
        );
        girisPanelLayout.setVerticalGroup(
            girisPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(girisPanelLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        gozatPanel.setPreferredSize(new java.awt.Dimension(1129, 699));

        jPanel5.setBackground(new java.awt.Color(28, 28, 28));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("GÖZAT");

        muzikTurleriLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        muzikTurleriLabel5.setForeground(new java.awt.Color(255, 255, 255));
        muzikTurleriLabel5.setText("Dinlediğiniz Müzik Türleri");
        muzikTurleriLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                muzikTurleriLabel5MouseClicked(evt);
            }
        });

        albumlerLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        albumlerLabel5.setForeground(new java.awt.Color(255, 255, 255));
        albumlerLabel5.setText("Albümler");
        albumlerLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                albumlerLabel5MouseClicked(evt);
            }
        });

        sanatcilerLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sanatcilerLabel5.setForeground(new java.awt.Color(255, 255, 255));
        sanatcilerLabel5.setText("Sanatçılar");
        sanatcilerLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sanatcilerLabel5MouseClicked(evt);
            }
        });

        listelerLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        listelerLabel5.setForeground(new java.awt.Color(255, 255, 255));
        listelerLabel5.setText("Listeler");
        listelerLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listelerLabel5MouseClicked(evt);
            }
        });

        muzikler.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        muzikler.setForeground(new java.awt.Color(255, 255, 255));
        muzikler.setText("Müzikler");
        muzikler.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                muziklerMouseClicked(evt);
            }
        });

        single.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        single.setForeground(new java.awt.Color(255, 255, 255));
        single.setText("Single ler");
        single.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                singleMouseClicked(evt);
            }
        });

        tursuz.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tursuz.setForeground(new java.awt.Color(255, 255, 255));
        tursuz.setText("Türsüzler");
        tursuz.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tursuzMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addContainerGap(1061, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(muzikTurleriLabel5)
                        .addGap(99, 99, 99)
                        .addComponent(albumlerLabel5)
                        .addGap(96, 96, 96)
                        .addComponent(sanatcilerLabel5)
                        .addGap(109, 109, 109)
                        .addComponent(listelerLabel5)
                        .addGap(96, 96, 96)
                        .addComponent(muzikler)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(single)
                        .addGap(104, 104, 104)
                        .addComponent(tursuz)
                        .addGap(99, 99, 99))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(muzikTurleriLabel5)
                    .addComponent(albumlerLabel5)
                    .addComponent(sanatcilerLabel5)
                    .addComponent(listelerLabel5)
                    .addComponent(muzikler)
                    .addComponent(single)
                    .addComponent(tursuz))
                .addContainerGap())
        );

        muzikTurleriPanel5.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout muzikTurleriPanel5Layout = new javax.swing.GroupLayout(muzikTurleriPanel5);
        muzikTurleriPanel5.setLayout(muzikTurleriPanel5Layout);
        muzikTurleriPanel5Layout.setHorizontalGroup(
            muzikTurleriPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1146, Short.MAX_VALUE)
        );
        muzikTurleriPanel5Layout.setVerticalGroup(
            muzikTurleriPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 653, Short.MAX_VALUE)
        );

        albumlerPanel5.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout albumlerPanel5Layout = new javax.swing.GroupLayout(albumlerPanel5);
        albumlerPanel5.setLayout(albumlerPanel5Layout);
        albumlerPanel5Layout.setHorizontalGroup(
            albumlerPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1149, Short.MAX_VALUE)
        );
        albumlerPanel5Layout.setVerticalGroup(
            albumlerPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 653, Short.MAX_VALUE)
        );

        sanatcilerPanel5.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout sanatcilerPanel5Layout = new javax.swing.GroupLayout(sanatcilerPanel5);
        sanatcilerPanel5.setLayout(sanatcilerPanel5Layout);
        sanatcilerPanel5Layout.setHorizontalGroup(
            sanatcilerPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1149, Short.MAX_VALUE)
        );
        sanatcilerPanel5Layout.setVerticalGroup(
            sanatcilerPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 653, Short.MAX_VALUE)
        );

        listelerPanel5.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout listelerPanel5Layout = new javax.swing.GroupLayout(listelerPanel5);
        listelerPanel5.setLayout(listelerPanel5Layout);
        listelerPanel5Layout.setHorizontalGroup(
            listelerPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1149, Short.MAX_VALUE)
        );
        listelerPanel5Layout.setVerticalGroup(
            listelerPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 653, Short.MAX_VALUE)
        );

        muzikTurleriAltPanel.setBackground(new java.awt.Color(51, 51, 51));
        muzikTurleriAltPanel.setPreferredSize(new java.awt.Dimension(1129, 622));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SongID", "SongName", "KindName", "AlbumName", "ListenCount", "UserListenCount", "Time", "AlbumDate"
            }
        ));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(5).setHeaderValue("UserListenCount");
        }

        javax.swing.GroupLayout muzikTurleriAltPanelLayout = new javax.swing.GroupLayout(muzikTurleriAltPanel);
        muzikTurleriAltPanel.setLayout(muzikTurleriAltPanelLayout);
        muzikTurleriAltPanelLayout.setHorizontalGroup(
            muzikTurleriAltPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, muzikTurleriAltPanelLayout.createSequentialGroup()
                .addContainerGap(389, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        muzikTurleriAltPanelLayout.setVerticalGroup(
            muzikTurleriAltPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(muzikTurleriAltPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 631, Short.MAX_VALUE)
                .addContainerGap())
        );

        albumlerAltPanel.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout albumlerAltPanelLayout = new javax.swing.GroupLayout(albumlerAltPanel);
        albumlerAltPanel.setLayout(albumlerAltPanelLayout);
        albumlerAltPanelLayout.setHorizontalGroup(
            albumlerAltPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1149, Short.MAX_VALUE)
        );
        albumlerAltPanelLayout.setVerticalGroup(
            albumlerAltPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 653, Short.MAX_VALUE)
        );

        sanatcilarAltPanel.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout sanatcilarAltPanelLayout = new javax.swing.GroupLayout(sanatcilarAltPanel);
        sanatcilarAltPanel.setLayout(sanatcilarAltPanelLayout);
        sanatcilarAltPanelLayout.setHorizontalGroup(
            sanatcilarAltPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1149, Short.MAX_VALUE)
        );
        sanatcilarAltPanelLayout.setVerticalGroup(
            sanatcilarAltPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 653, Short.MAX_VALUE)
        );

        listelerAltPanel.setBackground(new java.awt.Color(51, 51, 51));
        listelerAltPanel.setPreferredSize(new java.awt.Dimension(1129, 590));

        javax.swing.GroupLayout listelerAltPanelLayout = new javax.swing.GroupLayout(listelerAltPanel);
        listelerAltPanel.setLayout(listelerAltPanelLayout);
        listelerAltPanelLayout.setHorizontalGroup(
            listelerAltPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1129, Short.MAX_VALUE)
        );
        listelerAltPanelLayout.setVerticalGroup(
            listelerAltPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 653, Short.MAX_VALUE)
        );

        muziklerAltPanel.setBackground(new java.awt.Color(51, 51, 51));
        muziklerAltPanel.setPreferredSize(new java.awt.Dimension(1129, 642));

        tableGenel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SongID", "SongName", "KindName", "AlbumName", "ListenCount", "Time", "AlbumDate"
            }
        ));
        tableGenel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableGenelMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tableGenel);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Eklemek  İstediğiniz Şarkının Adını Giriniz");

        muziklerAltadd.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        muziklerAltadd.setForeground(new java.awt.Color(255, 255, 255));
        muziklerAltadd.setText("Ekle(POP-JAZZ-KLASIK)");
        muziklerAltadd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                muziklerAltaddMouseClicked(evt);
            }
        });

        muziklerAltaddOptional.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        muziklerAltaddOptional.setForeground(new java.awt.Color(255, 255, 255));
        muziklerAltaddOptional.setText("OTHER LİSTS");
        muziklerAltaddOptional.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                muziklerAltaddOptionalMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout muziklerAltPanelLayout = new javax.swing.GroupLayout(muziklerAltPanel);
        muziklerAltPanel.setLayout(muziklerAltPanelLayout);
        muziklerAltPanelLayout.setHorizontalGroup(
            muziklerAltPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, muziklerAltPanelLayout.createSequentialGroup()
                .addGroup(muziklerAltPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(muziklerAltPanelLayout.createSequentialGroup()
                        .addGroup(muziklerAltPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(muziklerAltPanelLayout.createSequentialGroup()
                                .addContainerGap(106, Short.MAX_VALUE)
                                .addComponent(jLabel8))
                            .addGroup(muziklerAltPanelLayout.createSequentialGroup()
                                .addGroup(muziklerAltPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(muziklerAltPanelLayout.createSequentialGroup()
                                        .addGap(82, 82, 82)
                                        .addComponent(muziklerAltSongAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(muziklerAltPanelLayout.createSequentialGroup()
                                        .addGap(69, 69, 69)
                                        .addComponent(muziklerAltadd, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(muziklerAltPanelLayout.createSequentialGroup()
                                        .addGap(82, 82, 82)
                                        .addComponent(muzikleraltaltOptionalList, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18))
                    .addGroup(muziklerAltPanelLayout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(muziklerAltaddOptional, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        muziklerAltPanelLayout.setVerticalGroup(
            muziklerAltPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(muziklerAltPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 631, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(muziklerAltPanelLayout.createSequentialGroup()
                .addGap(197, 197, 197)
                .addComponent(jLabel8)
                .addGap(28, 28, 28)
                .addComponent(muziklerAltSongAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(muziklerAltadd)
                .addGap(47, 47, 47)
                .addComponent(muzikleraltaltOptionalList, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(muziklerAltaddOptional)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        listelerAltAltPanel.setBackground(new java.awt.Color(51, 51, 51));
        listelerAltAltPanel.setPreferredSize(new java.awt.Dimension(1129, 590));

        tableList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "UserName", "PlayListID", "KindName", "AlbumName", "SongID", "SongName", "Time(sn)", "ListeningCount"
            }
        ));
        tableList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableList);

        listAltAltAdd.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        listAltAltAdd.setForeground(new java.awt.Color(255, 255, 255));
        listAltAltAdd.setText("Ekle(POP-JAZZ-KLASIK)");
        listAltAltAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listAltAltAddMouseClicked(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Eklemek istediğiniz şarkının adını giriniz:");

        listAltAltAddOptional.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        listAltAltAddOptional.setForeground(new java.awt.Color(255, 255, 255));
        listAltAltAddOptional.setText("Other Lists");
        listAltAltAddOptional.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listAltAltAddOptionalMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout listelerAltAltPanelLayout = new javax.swing.GroupLayout(listelerAltAltPanel);
        listelerAltAltPanel.setLayout(listelerAltAltPanelLayout);
        listelerAltAltPanelLayout.setHorizontalGroup(
            listelerAltAltPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, listelerAltAltPanelLayout.createSequentialGroup()
                .addGroup(listelerAltAltPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(listelerAltAltPanelLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(listelerAltAltPanelLayout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addGroup(listelerAltAltPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(listAltAltAdd)
                            .addComponent(addedSongName, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addedListNameOPtional, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(listelerAltAltPanelLayout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(listAltAltAddOptional)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 671, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        listelerAltAltPanelLayout.setVerticalGroup(
            listelerAltAltPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(listelerAltAltPanelLayout.createSequentialGroup()
                .addGroup(listelerAltAltPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(listelerAltAltPanelLayout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(listelerAltAltPanelLayout.createSequentialGroup()
                        .addGap(151, 151, 151)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(addedSongName, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(listAltAltAdd)
                        .addGap(29, 29, 29)
                        .addComponent(addedListNameOPtional, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(listAltAltAddOptional)))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        singlePanel.setBackground(new java.awt.Color(51, 51, 51));

        singleTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SongID", "SongName", "Count", "Kind", "Time"
            }
        ));
        jScrollPane7.setViewportView(singleTable);

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Eklemek istediğiniz şarkının adını giriniz:");

        addedSongNameSingle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addedSongNameSingleActionPerformed(evt);
            }
        });

        listAltAltAdd1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        listAltAltAdd1.setForeground(new java.awt.Color(255, 255, 255));
        listAltAltAdd1.setText("PlayListName");

        addOptionalSingle.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        addOptionalSingle.setForeground(new java.awt.Color(255, 255, 255));
        addOptionalSingle.setText("ADD");
        addOptionalSingle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addOptionalSingleMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout singlePanelLayout = new javax.swing.GroupLayout(singlePanel);
        singlePanel.setLayout(singlePanelLayout);
        singlePanelLayout.setHorizontalGroup(
            singlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, singlePanelLayout.createSequentialGroup()
                .addGroup(singlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(singlePanelLayout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(singlePanelLayout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addGroup(singlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(listAltAltAdd1)
                            .addComponent(addedSongNameSingle, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addedListNameOPtionalSingle, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(singlePanelLayout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addComponent(addOptionalSingle)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 163, Short.MAX_VALUE)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        singlePanelLayout.setVerticalGroup(
            singlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(singlePanelLayout.createSequentialGroup()
                .addGroup(singlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(singlePanelLayout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(singlePanelLayout.createSequentialGroup()
                        .addGap(177, 177, 177)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(addedSongNameSingle, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(listAltAltAdd1)
                        .addGap(29, 29, 29)
                        .addComponent(addedListNameOPtionalSingle, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(addOptionalSingle)))
                .addContainerGap(101, Short.MAX_VALUE))
        );

        tursuzPanel.setBackground(new java.awt.Color(51, 51, 51));

        tursuzTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SongID", "SongName", "Count", "Time"
            }
        ));
        jScrollPane8.setViewportView(tursuzTable);

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Eklemek istediğiniz şarkının adını giriniz:");

        listAltAltAdd2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        listAltAltAdd2.setForeground(new java.awt.Color(255, 255, 255));
        listAltAltAdd2.setText("PlayListName");

        addOptionalTursuz.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        addOptionalTursuz.setForeground(new java.awt.Color(255, 255, 255));
        addOptionalTursuz.setText("ADD");
        addOptionalTursuz.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addOptionalTursuzMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout tursuzPanelLayout = new javax.swing.GroupLayout(tursuzPanel);
        tursuzPanel.setLayout(tursuzPanelLayout);
        tursuzPanelLayout.setHorizontalGroup(
            tursuzPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tursuzPanelLayout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(tursuzPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(tursuzPanelLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(tursuzPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(listAltAltAdd2)
                            .addComponent(addedSongNameTursuz, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addedListNameOPtionalTursuz, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(tursuzPanelLayout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(addOptionalTursuz)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 150, Short.MAX_VALUE)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        tursuzPanelLayout.setVerticalGroup(
            tursuzPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tursuzPanelLayout.createSequentialGroup()
                .addContainerGap(130, Short.MAX_VALUE)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(96, 96, 96))
            .addGroup(tursuzPanelLayout.createSequentialGroup()
                .addGap(167, 167, 167)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(addedSongNameTursuz, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(listAltAltAdd2)
                .addGap(29, 29, 29)
                .addComponent(addedListNameOPtionalTursuz, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(addOptionalTursuz)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLayeredPane3.setLayer(muzikTurleriPanel5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(albumlerPanel5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(sanatcilerPanel5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(listelerPanel5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(muzikTurleriAltPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(albumlerAltPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(sanatcilarAltPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(listelerAltPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(muziklerAltPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(listelerAltAltPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(singlePanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(tursuzPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane3Layout = new javax.swing.GroupLayout(jLayeredPane3);
        jLayeredPane3.setLayout(jLayeredPane3Layout);
        jLayeredPane3Layout.setHorizontalGroup(
            jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(muzikTurleriPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(albumlerPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(sanatcilerPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(listelerPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(muzikTurleriAltPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1146, Short.MAX_VALUE))
            .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(albumlerAltPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(sanatcilarAltPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(listelerAltPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1146, Short.MAX_VALUE))
            .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(muziklerAltPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1146, Short.MAX_VALUE))
            .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(listelerAltAltPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1146, Short.MAX_VALUE))
            .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(singlePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(tursuzPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane3Layout.setVerticalGroup(
            jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(muzikTurleriPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(albumlerPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(sanatcilerPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(listelerPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(muzikTurleriAltPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE))
            .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(albumlerAltPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(sanatcilarAltPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(listelerAltPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE))
            .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(muziklerAltPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE))
            .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(listelerAltAltPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE))
            .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(singlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(tursuzPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout gozatPanelLayout = new javax.swing.GroupLayout(gozatPanel);
        gozatPanel.setLayout(gozatPanelLayout);
        gozatPanelLayout.setHorizontalGroup(
            gozatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLayeredPane3)
        );
        gozatPanelLayout.setVerticalGroup(
            gozatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gozatPanelLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLayeredPane3))
        );

        albumlerPanel.setPreferredSize(new java.awt.Dimension(1129, 703));

        followedAlbumPanel.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout followedAlbumPanelLayout = new javax.swing.GroupLayout(followedAlbumPanel);
        followedAlbumPanel.setLayout(followedAlbumPanelLayout);
        followedAlbumPanelLayout.setHorizontalGroup(
            followedAlbumPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        followedAlbumPanelLayout.setVerticalGroup(
            followedAlbumPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 658, Short.MAX_VALUE)
        );

        followedAlbumAltPanel.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout followedAlbumAltPanelLayout = new javax.swing.GroupLayout(followedAlbumAltPanel);
        followedAlbumAltPanel.setLayout(followedAlbumAltPanelLayout);
        followedAlbumAltPanelLayout.setHorizontalGroup(
            followedAlbumAltPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1144, Short.MAX_VALUE)
        );
        followedAlbumAltPanelLayout.setVerticalGroup(
            followedAlbumAltPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 647, Short.MAX_VALUE)
        );

        jLayeredPane4.setLayer(followedAlbumPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(followedAlbumAltPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane4Layout = new javax.swing.GroupLayout(jLayeredPane4);
        jLayeredPane4.setLayout(jLayeredPane4Layout);
        jLayeredPane4Layout.setHorizontalGroup(
            jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(followedAlbumPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(followedAlbumAltPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane4Layout.setVerticalGroup(
            jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(followedAlbumPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(followedAlbumAltPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(28, 28, 28));
        jPanel6.setAutoscrolls(true);

        jLabel2.setText("TAKİP EDİLEN ALBÜMLER");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(392, 392, 392)
                .addComponent(jLabel2)
                .addContainerGap(629, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel2)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout albumlerPanelLayout = new javax.swing.GroupLayout(albumlerPanel);
        albumlerPanel.setLayout(albumlerPanelLayout);
        albumlerPanelLayout.setHorizontalGroup(
            albumlerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane4)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        albumlerPanelLayout.setVerticalGroup(
            albumlerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, albumlerPanelLayout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLayeredPane4))
        );

        sanatcilerPanel.setPreferredSize(new java.awt.Dimension(1129, 703));

        jPanel7.setBackground(new java.awt.Color(28, 28, 28));

        jLabel3.setText("TAKİP EDİLEN ŞARKICILAR");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(415, 415, 415)
                .addComponent(jLabel3)
                .addContainerGap(600, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        followedSingerPanel.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout followedSingerPanelLayout = new javax.swing.GroupLayout(followedSingerPanel);
        followedSingerPanel.setLayout(followedSingerPanelLayout);
        followedSingerPanelLayout.setHorizontalGroup(
            followedSingerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1146, Short.MAX_VALUE)
        );
        followedSingerPanelLayout.setVerticalGroup(
            followedSingerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 667, Short.MAX_VALUE)
        );

        followedSingerAltPanel.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout followedSingerAltPanelLayout = new javax.swing.GroupLayout(followedSingerAltPanel);
        followedSingerAltPanel.setLayout(followedSingerAltPanelLayout);
        followedSingerAltPanelLayout.setHorizontalGroup(
            followedSingerAltPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1129, Short.MAX_VALUE)
        );
        followedSingerAltPanelLayout.setVerticalGroup(
            followedSingerAltPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 634, Short.MAX_VALUE)
        );

        jLayeredPane5.setLayer(followedSingerPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(followedSingerAltPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane5Layout = new javax.swing.GroupLayout(jLayeredPane5);
        jLayeredPane5.setLayout(jLayeredPane5Layout);
        jLayeredPane5Layout.setHorizontalGroup(
            jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(followedSingerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(followedSingerAltPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane5Layout.setVerticalGroup(
            jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(followedSingerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(followedSingerAltPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout sanatcilerPanelLayout = new javax.swing.GroupLayout(sanatcilerPanel);
        sanatcilerPanel.setLayout(sanatcilerPanelLayout);
        sanatcilerPanelLayout.setHorizontalGroup(
            sanatcilerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLayeredPane5)
        );
        sanatcilerPanelLayout.setVerticalGroup(
            sanatcilerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sanatcilerPanelLayout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLayeredPane5))
        );

        followedList_UserPanel.setBackground(new java.awt.Color(51, 51, 51));
        followedList_UserPanel.setPreferredSize(new java.awt.Dimension(1129, 703));

        followedListPanel1.setBackground(new java.awt.Color(255, 255, 255));

        followedListPanel.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout followedListPanelLayout = new javax.swing.GroupLayout(followedListPanel);
        followedListPanel.setLayout(followedListPanelLayout);
        followedListPanelLayout.setHorizontalGroup(
            followedListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1194, Short.MAX_VALUE)
        );
        followedListPanelLayout.setVerticalGroup(
            followedListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 736, Short.MAX_VALUE)
        );

        followedUserPanel.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout followedUserPanelLayout = new javax.swing.GroupLayout(followedUserPanel);
        followedUserPanel.setLayout(followedUserPanelLayout);
        followedUserPanelLayout.setHorizontalGroup(
            followedUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1194, Short.MAX_VALUE)
        );
        followedUserPanelLayout.setVerticalGroup(
            followedUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 736, Short.MAX_VALUE)
        );

        followedUserAltPanel.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout followedUserAltPanelLayout = new javax.swing.GroupLayout(followedUserAltPanel);
        followedUserAltPanel.setLayout(followedUserAltPanelLayout);
        followedUserAltPanelLayout.setHorizontalGroup(
            followedUserAltPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1194, Short.MAX_VALUE)
        );
        followedUserAltPanelLayout.setVerticalGroup(
            followedUserAltPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 736, Short.MAX_VALUE)
        );

        followedListAltPanel.setBackground(new java.awt.Color(51, 51, 51));

        followedListTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8"
            }
        ));
        followedListTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                followedListTableMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(followedListTable);

        javax.swing.GroupLayout followedListAltPanelLayout = new javax.swing.GroupLayout(followedListAltPanel);
        followedListAltPanel.setLayout(followedListAltPanelLayout);
        followedListAltPanelLayout.setHorizontalGroup(
            followedListAltPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, followedListAltPanelLayout.createSequentialGroup()
                .addContainerGap(571, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 599, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        followedListAltPanelLayout.setVerticalGroup(
            followedListAltPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(followedListAltPanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(172, Short.MAX_VALUE))
        );

        followedUserALtAltPanel.setBackground(new java.awt.Color(51, 51, 51));

        followedUserListTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8"
            }
        ));
        followedUserListTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                followedUserListTableMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(followedUserListTable);

        javax.swing.GroupLayout followedUserALtAltPanelLayout = new javax.swing.GroupLayout(followedUserALtAltPanel);
        followedUserALtAltPanel.setLayout(followedUserALtAltPanelLayout);
        followedUserALtAltPanelLayout.setHorizontalGroup(
            followedUserALtAltPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, followedUserALtAltPanelLayout.createSequentialGroup()
                .addContainerGap(571, Short.MAX_VALUE)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 599, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        followedUserALtAltPanelLayout.setVerticalGroup(
            followedUserALtAltPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(followedUserALtAltPanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(172, Short.MAX_VALUE))
        );

        followedListPanel1.setLayer(followedListPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        followedListPanel1.setLayer(followedUserPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        followedListPanel1.setLayer(followedUserAltPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        followedListPanel1.setLayer(followedListAltPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        followedListPanel1.setLayer(followedUserALtAltPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout followedListPanel1Layout = new javax.swing.GroupLayout(followedListPanel1);
        followedListPanel1.setLayout(followedListPanel1Layout);
        followedListPanel1Layout.setHorizontalGroup(
            followedListPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(followedListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(followedListPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(followedUserPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(followedListPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(followedUserAltPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(followedListPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(followedListAltPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(followedListPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(followedUserALtAltPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        followedListPanel1Layout.setVerticalGroup(
            followedListPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(followedListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(followedListPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(followedUserPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(followedListPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(followedUserAltPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(followedListPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(followedListAltPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(followedListPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(followedUserALtAltPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout followedList_UserPanelLayout = new javax.swing.GroupLayout(followedList_UserPanel);
        followedList_UserPanel.setLayout(followedList_UserPanelLayout);
        followedList_UserPanelLayout.setHorizontalGroup(
            followedList_UserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1146, Short.MAX_VALUE)
            .addGroup(followedList_UserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(followedListPanel1))
        );
        followedList_UserPanelLayout.setVerticalGroup(
            followedList_UserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 736, Short.MAX_VALUE)
            .addGroup(followedList_UserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(followedListPanel1))
        );

        myListPanel.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout myListPanelLayout = new javax.swing.GroupLayout(myListPanel);
        myListPanel.setLayout(myListPanelLayout);
        myListPanelLayout.setHorizontalGroup(
            myListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1146, Short.MAX_VALUE)
        );
        myListPanelLayout.setVerticalGroup(
            myListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 736, Short.MAX_VALUE)
        );

        newListPanel.setBackground(new java.awt.Color(51, 51, 51));
        newListPanel.setPreferredSize(new java.awt.Dimension(1129, 703));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Yeni Play List Oluşturma Sayfasına Hoşgeldiniz.......");

        newListAddLabel.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        newListAddLabel.setForeground(new java.awt.Color(255, 255, 255));
        newListAddLabel.setText("Oluşturmak İçin Tıklayınız");
        newListAddLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                newListAddLabelMouseClicked(evt);
            }
        });

        newListLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newListLabelActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("PlayList Adını Giriniz::::");

        javax.swing.GroupLayout newListPanelLayout = new javax.swing.GroupLayout(newListPanel);
        newListPanel.setLayout(newListPanelLayout);
        newListPanelLayout.setHorizontalGroup(
            newListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, newListPanelLayout.createSequentialGroup()
                .addContainerGap(107, Short.MAX_VALUE)
                .addGroup(newListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 834, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(newListPanelLayout.createSequentialGroup()
                        .addGap(179, 179, 179)
                        .addComponent(newListAddLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(188, 188, 188))
            .addGroup(newListPanelLayout.createSequentialGroup()
                .addGap(260, 260, 260)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(newListLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        newListPanelLayout.setVerticalGroup(
            newListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newListPanelLayout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(jLabel10)
                .addGap(120, 120, 120)
                .addGroup(newListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newListLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(91, 91, 91)
                .addComponent(newListAddLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(266, Short.MAX_VALUE))
        );

        myListAltPanel.setBackground(new java.awt.Color(51, 51, 51));

        myTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "UserName", "PlayListID", "KindName", "AlbumName", "SongID", "SongName", "Time(sn)", "ListeningCount"
            }
        ));
        myTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                myTableMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(myTable);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Listenizden Silmek İstediğiniz Şarkının Adını Yazınız....");

        sil.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        sil.setForeground(new java.awt.Color(255, 255, 255));
        sil.setText("SIL(POP-KLASIK-JAZZ)");
        sil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                silMouseClicked(evt);
            }
        });

        silOther.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        silOther.setForeground(new java.awt.Color(255, 255, 255));
        silOther.setText("SIL(POP-KLASIK-JAZZ)");
        silOther.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                silOtherMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout myListAltPanelLayout = new javax.swing.GroupLayout(myListAltPanel);
        myListAltPanel.setLayout(myListAltPanelLayout);
        myListAltPanelLayout.setHorizontalGroup(
            myListAltPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, myListAltPanelLayout.createSequentialGroup()
                .addGroup(myListAltPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, myListAltPanelLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel6))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, myListAltPanelLayout.createSequentialGroup()
                        .addGap(157, 157, 157)
                        .addComponent(silName, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, myListAltPanelLayout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addComponent(silNameLiist, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, myListAltPanelLayout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addGroup(myListAltPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(silOther, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sil, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 667, Short.MAX_VALUE)
                .addContainerGap())
        );
        myListAltPanelLayout.setVerticalGroup(
            myListAltPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(myListAltPanelLayout.createSequentialGroup()
                .addGap(280, 280, 280)
                .addComponent(jLabel6)
                .addGap(27, 27, 27)
                .addComponent(silName, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(sil)
                .addGap(18, 18, 18)
                .addComponent(silNameLiist, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(silOther)
                .addContainerGap(182, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, myListAltPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(112, 112, 112))
        );

        jLayeredPane1.setLayer(girisPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(gozatPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(albumlerPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(sanatcilerPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(followedList_UserPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(myListPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(newListPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(myListAltPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(girisPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1146, Short.MAX_VALUE)
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(gozatPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1146, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(albumlerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1146, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(sanatcilerPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1146, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(followedList_UserPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1146, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(myListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(newListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1146, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(myListAltPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(girisPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 736, Short.MAX_VALUE)
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(gozatPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 736, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(albumlerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 736, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(sanatcilerPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 736, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(followedList_UserPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 736, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(myListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(newListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 736, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(myListAltPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLayeredPane1)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLayeredPane1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
//tıklayınca top10 ların olduğu slider ı açıyor
    private void girisLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_girisLabelMouseClicked
        girisAltPanel.removeAll();
        girisPanel.setVisible(true);
        albumlerPanel.setVisible(false);
        sanatcilerPanel.setVisible(false);
        gozatPanel.setVisible(false);
        jPanel4.setVisible(true);
        muzikTurleriPanel5.setVisible(false);
        muzikTurleriAltPanel.setVisible(false);
        muziklerAltPanel.setVisible(false);
        newListPanel.setVisible(false);
        myListAltPanel.setVisible(false);

    }//GEN-LAST:event_girisLabelMouseClicked
//tıklayınca gözat slider ını açıyor
    private void gozatLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gozatLabelMouseClicked
        gozatPanel.setVisible(true);
        albumlerPanel.setVisible(false);
        girisPanel.setVisible(false);
        sanatcilerPanel.setVisible(false);
        jPanel5.setVisible(true);
        muzikTurleriPanel5.setVisible(false);
        muzikTurleriAltPanel.setVisible(false);
        muziklerAltPanel.setVisible(false);
        newListPanel.setVisible(false);
        myListAltPanel.setVisible(false);
    }//GEN-LAST:event_gozatLabelMouseClicked
//tıklayınca takip ettiğin albümleri getiriyor,gelen resmin üzerine tıklayınca şarkılarını gösteriyor
    private void albumlerLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_albumlerLabelMouseClicked
        followedAlbumAltPanel.removeAll();
        followedAlbumPanel.removeAll();
        close();
        newListPanel.setVisible(false);
        myListAltPanel.setVisible(false);
        albumlerPanel.setVisible(true);
        followedAlbumPanel.setVisible(true);
        followedAlbumAltPanel.setVisible(false);
        girisPanel.setVisible(false);
        sanatcilerPanel.setVisible(false);
        gozatPanel.setVisible(false);
        muzikTurleriPanel5.setVisible(false);
        muzikTurleriAltPanel.setVisible(false);
        muziklerAltPanel.setVisible(false);
        try {
            connection = DatabaseConnection.connect();
            query = "select a.AlbumName,fa.AlbumID from followed_albums fa,albums a where "
                    + " fa.AlbumId=a.AlbumID and UserID='" + LoginScreen.id + "'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            JLabel[] label = new JLabel[50];
            JLabel[] labell = new JLabel[50];
            JLabel[] labelll = new JLabel[50];
            int i = 0;
            while (resultSet.next()) {
                int x = (i % 6) * 170;
                double y = Math.floor(i / 6) * 170;
                System.out.println(i);
                String imagePath = "Images/image" + ((i + 1) % 19) + ".jpg";
                Image img = new ImageIcon(this.getClass().getResource(imagePath)).getImage();
                label[i] = new JLabel();
                label[i].setIcon(new ImageIcon(img));
                label[i].setBounds(x + 65, (int) y, 150, 150);
                labell[i] = new JLabel();
                labell[i].setBounds(x + 65, (int) y + 135, 80, 50);
                labell[i].setText(resultSet.getString("AlbumName"));
                labell[i].setForeground(Color.pink);
                labell[i].setFont(new Font(Font.SERIF, Font.PLAIN, 14));
                labelll[i] = new JLabel();
                labelll[i].setBounds(x + 65 + 80, (int) y + 135, 30, 50);
                labelll[i].setText("Sil");
                labelll[i].setForeground(Color.pink);
                labelll[i].setFont(new Font(Font.SERIF, Font.PLAIN, 14));
                followedAlbumPanel.add(label[i]);
                followedAlbumPanel.add(labell[i]);
                followedAlbumPanel.add(labelll[i]);
                followedAlbumPanel.repaint();
                
                labell[i].addMouseListener(new MouseAdapter() {
                    int index = i;
                    
                    public void mouseClicked(MouseEvent e) {
                        String names = "";
                        try {
                            connection = DatabaseConnection.connect();
                            query = "select DISTINCT si.SingerName,a.AlbumKind "
                                    + " from albums a,song_albums sa,songs s,singer_songs ss,singers si"
                                    + " where a.AlbumID=sa.AlbumID and sa.SongID=s.SongID and s.SongID=ss.SongID"
                                    + "  and ss.SingerID=si.SingerID "
                                    + " and a.AlbumName='" + labell[index].getText() + "'";
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            int one = 0;
                            while (resultSet.next()) {
                                if (one == 0) {
                                    names = names.concat(resultSet.getString("AlbumKind") + "\n");
                                    one++;
                                }
                                names = names.concat(resultSet.getString("SingerName") + "\n");
                                
                            }
                            JOptionPane.showMessageDialog(albumlerPanel5, names);
                        } catch (Exception ex) {
                            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                
                labelll[i].addMouseListener(new MouseAdapter() {
                    int index = i;
                    
                    public void mouseClicked(MouseEvent e) {
                        try {
                            int idSil = 0;
                            connection = DatabaseConnection.connect();
                            query = "Select AlbumID from albums where AlbumName='" + labell[index].getText() + "'";
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            while (resultSet.next()) {
                                idSil = resultSet.getInt("AlbumID");
                            }
                            connection = DatabaseConnection.connect();
                            query = "DELETE FROM followed_albums WHERE AlbumID='" + idSil + "'"
                                    + " and UserID='" + LoginScreen.id + "'";
                            statement = connection.createStatement();
                            statement.executeUpdate(query);
                            connection.close();
                            followedAlbumPanel.remove(labell[index]);
                            followedAlbumPanel.remove(label[index]);
                            followedAlbumPanel.remove(labelll[index]);
                            followedAlbumPanel.repaint();
                            
                        } catch (Exception ex) {
                            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                label[i].addMouseListener(new MouseAdapter() {
                    int index = i;
                    int[] songId = new int[100];
                    
                    public void mouseClicked(MouseEvent e) {
                        try {
                            followedAlbumPanel.setVisible(false);
                            followedAlbumAltPanel.setVisible(true);
                            connection = DatabaseConnection.connect();
                            query = "select so.SongName,so.SongID from songs so,albums a,song_albums sa"
                                    + " where a.AlbumID=sa.AlbumID and sa.SongID=so.SongID"
                                    + " and a.AlbumName='" + labell[index].getText() + "'";
                            
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            int count2 = 0;
                            JLabel[] labelSong = new JLabel[20];
                            JLabel[] labelSongg = new JLabel[20];
                            JLabel[] labelSonggg = new JLabel[20];
                            JLabel[] labelSongggg = new JLabel[20];
                            JLabel[] labelSonggggg = new JLabel[20];
                            int j = 0;
                            while (resultSet.next()) {
                                songId[j] = resultSet.getInt("SongID");
                                int x = (j % 2) * 510;
                                double y = Math.floor(j / 2) * 60;
                                Image img = new ImageIcon(this.getClass().getResource("Images/listen.jpg")).getImage();
                                labelSong[j] = new JLabel();
                                labelSongg[j] = new JLabel();
                                labelSong[j].setIcon(new ImageIcon(img));
                                labelSong[j].setBounds(x + 30, (int) y + 15, 500, 50);
                                labelSongg[j].setBounds(x + 30, (int) y + 45, 100, 50);
                                labelSongg[j].setText(resultSet.getString("SongName"));
                                labelSongg[j].setForeground(Color.PINK);
                                labelSongg[j].setFont(new Font(Font.SERIF, Font.PLAIN, 12));
                                labelSonggg[j] = new JLabel();
                                labelSonggg[j].setBounds(x + 130 + 30, (int) y + 45, 100, 50);
                                labelSonggg[j].setText("Kaydet");
                                labelSonggg[j].setForeground(Color.white);
                                labelSonggg[j].setFont(new Font(Font.SERIF, Font.PLAIN, 12));
                                labelSongggg[j] = new JLabel();
                                labelSongggg[j].setBounds(x + 130 + 130 + 30, (int) y + 45, 100, 50);
                                labelSongggg[j].setText("Dinle");
                                labelSongggg[j].setForeground(Color.white);
                                labelSongggg[j].setFont(new Font(Font.SERIF, Font.PLAIN, 12));
                                labelSonggggg[j] = new JLabel();
                                labelSonggggg[j].setBounds(x + 130 + 130 + 130 + 30, (int) y + 45, 100, 50);
                                labelSonggggg[j].setText("Detay");
                                labelSonggggg[j].setForeground(Color.white);
                                labelSonggggg[j].setFont(new Font(Font.SERIF, Font.PLAIN, 12));
                                followedAlbumAltPanel.add(labelSong[j]);
                                followedAlbumAltPanel.add(labelSongg[j]);
                                followedAlbumAltPanel.add(labelSonggg[j]);
                                followedAlbumAltPanel.add(labelSongggg[j]);
                                followedAlbumAltPanel.add(labelSonggggg[j]);
                                followedAlbumAltPanel.repaint();
                                
                                labelSonggg[j].addMouseListener(new MouseAdapter() {
                                    int index = j;
                                    String kindName = "";
                                    int count = 0;
                                    int playListID;
                                    
                                    public void mouseClicked(MouseEvent e) {
                                        try {
                                            connection = DatabaseConnection.connect();
                                            query = "select k.KindName from kinds k,song_kinds sk,songs s"
                                                    + " where sk.KindID=k.KindID and s.SongID=sk.SongID and"
                                                    + " s.SongName='" + labelSongg[index].getText() + "'";
                                            
                                            statement = connection.createStatement();
                                            resultSet = statement.executeQuery(query);
                                            while (resultSet.next()) {
                                                kindName = "";
                                                kindName = kindName.concat(resultSet.getString(1));
                                                System.out.println(kindName);
                                            }
                                            query = "select PlayListID from play_lists "
                                                    + " where UserID='" + LoginScreen.id + "'"
                                                    + " and PlayListName='" + kindName + "'";
                                            
                                            statement = connection.createStatement();
                                            resultSet = statement.executeQuery(query);
                                            System.out.println(kindName);
                                            while (resultSet.next()) {
                                                playListID = resultSet.getInt(1);
                                            }
                                            query = "select pls.SongID from play_list_songs pls,songs s,play_lists pl"
                                                    + " where pls.SongID=s.SongID and pls.PlayListID=pl.PlayListID"
                                                    + " and pl.UserID='" + LoginScreen.id + "'"
                                                    + " and pl.PlayListName='" + kindName + "'"
                                                    + " and s.SongName='" + labelSongg[index].getText() + "'";
                                            statement = connection.createStatement();
                                            resultSet = statement.executeQuery(query);
                                            while (resultSet.next()) {
                                                count++;
                                            }
                                            if (count == 0) {
                                                
                                                query = " insert into play_list_songs (SongID,PlayListID)"
                                                        + " values (?,?)";
                                                preparedStatement = connection.prepareStatement(query);
                                                preparedStatement.setInt(1, songId[index]);
                                                preparedStatement.setInt(2, playListID);
                                                preparedStatement.execute();
                                                connection.close();
                                            } else {
                                                JOptionPane.showMessageDialog(albumlerPanel5, "You are already add this song in your playlist");
                                            }
                                            
                                        } catch (Exception ex) {
                                            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                });
                                
                                labelSonggggg[j].addMouseListener(new MouseAdapter() {
                                    int index2 = j;
                                    String singersName = "";
                                    String kindName = "";
                                    
                                    public void mouseClicked(MouseEvent e) {
                                        try {
                                            connection = DatabaseConnection.connect();
                                            query = "select si.SingerName from singers si,singer_songs ss, songs so where"
                                                    + " si.SingerID=ss.SingerID and ss.SongID=so.SongID and"
                                                    + " so.SongName='" + labelSongg[index2].getText() + "'";
                                            
                                            statement = connection.createStatement();
                                            resultSet = statement.executeQuery(query);
                                            while (resultSet.next()) {
                                                singersName = singersName.concat(resultSet.getString(1) + " , ");
                                            }
                                            connection = DatabaseConnection.connect();
                                            query = "select ki.KindName from songs so,kinds ki,song_kinds sk"
                                                    + " where ki.KindID=sk.KindID and sk.SongID=so.SongID "
                                                    + " and so.SongName='" + labelSongg[index2].getText() + "'";
                                            statement = connection.createStatement();
                                            resultSet = statement.executeQuery(query);
                                            while (resultSet.next()) {
                                                kindName = resultSet.getString(1);
                                            }
                                            
                                            JOptionPane.showMessageDialog(albumlerAltPanel, "This songs singers:" + singersName + " \n and song's kind:" + kindName
                                                    + "\n and album name:" + labell[index].getText());
                                            singersName = "";
                                        } catch (Exception ex) {
                                            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                    
                                });
                                
                                labelSongggg[j].addMouseListener(new MouseAdapter() {
                                    int index2 = j;
                                    int listen;
                                    
                                    public void mouseClicked(MouseEvent e) {
                                        int songIDforUser = 0;
                                        try {
                                            connection = DatabaseConnection.connect();
                                            query = "select ViewCount,SongID from songs where SongName='" + labelSongg[index2].getText() + "'";
                                            statement = connection.createStatement();
                                            resultSet = statement.executeQuery(query);
                                            
                                            while (resultSet.next()) {
                                                listen = resultSet.getInt(1);
                                                songIDforUser = resultSet.getInt(2);
                                            }
                                            query = "update songs set ViewCount=?  where SongName='" + labelSongg[index2].getText() + "'";
                                            preparedStatement = connection.prepareStatement(query);
                                            preparedStatement.setInt(1, (listen + 1));
                                            preparedStatement.executeUpdate();
                                            preparedStatement.close();
                                            connection.close();
                                            JOptionPane.showMessageDialog(albumlerAltPanel, "The song is resting");
                                            connection = DatabaseConnection.connect();
                                            query = "select so.SongName,uls.listeningCount from songs so,user_listening_song uls,users u"
                                                    + " where so.SongID=uls.SongID and uls.UserID=u.UserID and so.SongName='" + labelSongg[index2].getText() + "'"
                                                    + " and u.UserName='" + LoginScreen.name + "'";
                                            statement = connection.createStatement();
                                            resultSet = statement.executeQuery(query);
                                            int count = 0;
                                            int listenUser = 0;
                                            while (resultSet.next()) {
                                                count++;
                                                listenUser = resultSet.getInt("ListeningCount");
                                                
                                            }
                                            if (count == 0) {
                                                query = " insert into user_listening_song (UserID,SongID,ListeningCount)"
                                                        + " values (?, ?,?)";
                                                preparedStatement = connection.prepareStatement(query);
                                                preparedStatement.setInt(1, LoginScreen.id);
                                                preparedStatement.setInt(2, songIDforUser);
                                                preparedStatement.setInt(3, 1);
                                                preparedStatement.execute();
                                                connection.close();
                                            } else {
                                                query = "update user_listening_song set ListeningCount=?  where UserID='" + LoginScreen.id + "'"
                                                        + "  and SongID='" + songIDforUser + "'";
                                                preparedStatement = connection.prepareStatement(query);
                                                preparedStatement.setInt(1, (listenUser + 1));
                                                preparedStatement.executeUpdate();
                                                preparedStatement.close();
                                                connection.close();
                                            }
                                            
                                        } catch (Exception ex) {
                                            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                    
                                });
                                j++;
                            }
                        } catch (Exception ex) {
                            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                
                i++;
                
            }
            connection.close();
        } catch (Exception ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_albumlerLabelMouseClicked
//tıklayınca takip ettiğin sanatçıları getiriyor,gelen resmin üzerine tıklayınca şarkılarını gösteriyor
    private void sanatcilarLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sanatcilarLabelMouseClicked
        sanatcilerPanel.setVisible(true);
        followedSingerAltPanel.removeAll();
        followedSingerPanel.removeAll();
        followedSingerPanel.setVisible(true);
        followedSingerAltPanel.setVisible(false);
        albumlerPanel.setVisible(false);
        girisPanel.setVisible(false);
        gozatPanel.setVisible(false);
        muzikTurleriPanel5.setVisible(false);
        muzikTurleriAltPanel.setVisible(false);
        muziklerAltPanel.setVisible(false);
        girisPanel.setVisible(false);
        newListPanel.setVisible(false);
        myListAltPanel.setVisible(false);
        try {
            connection = DatabaseConnection.connect();
            query = "select si.SingerName from followed_singers fs,singers si where "
                    + " si.SingerID=fs.SingerID and fs.UserID='" + LoginScreen.id + "'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            JLabel[] label = new JLabel[50];
            JLabel[] labell = new JLabel[50];
            JLabel[] labelll = new JLabel[50];
            int i = 0;
            while (resultSet.next()) {
                int x = (i % 6) * 170;
                double y = Math.floor(i / 6) * 170;
                System.out.println(i);
                String imagePath = "Images/imagesinger.jpg";
                Image img = new ImageIcon(this.getClass().getResource(imagePath)).getImage();
                label[i] = new JLabel();
                label[i].setIcon(new ImageIcon(img));
                label[i].setBounds(x + 65, (int) y, 150, 150);
                labell[i] = new JLabel();
                labell[i].setBounds(x + 65, (int) y + 135, 100, 50);
                labell[i].setText(resultSet.getString("SingerName"));
                labell[i].setForeground(Color.pink);
                labell[i].setFont(new Font(Font.SERIF, Font.PLAIN, 14));
                labelll[i] = new JLabel();
                labelll[i].setBounds(x + 65 + 100, (int) y + 135, 30, 50);
                labelll[i].setText("Sil");
                labelll[i].setForeground(Color.pink);
                labelll[i].setFont(new Font(Font.SERIF, Font.PLAIN, 14));
                followedSingerPanel.add(label[i]);
                followedSingerPanel.add(labell[i]);
                followedSingerPanel.add(labelll[i]);
                followedSingerPanel.repaint();
                labelll[i].addMouseListener(new MouseAdapter() {
                    int index = i;
                    
                    public void mouseClicked(MouseEvent e) {
                        try {
                            int idSil = 0;
                            connection = DatabaseConnection.connect();
                            query = "Select SingerID from singers where SingerName='" + labell[index].getText() + "'";
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            while (resultSet.next()) {
                                idSil = resultSet.getInt("SingerID");
                            }
                            connection = DatabaseConnection.connect();
                            query = "DELETE FROM followed_singers WHERE SingerID='" + idSil + "'"
                                    + " and UserID='" + LoginScreen.id + "'";
                            statement = connection.createStatement();
                            statement.executeUpdate(query);
                            connection.close();
                            followedSingerPanel.remove(label[index]);
                            followedSingerPanel.remove(labell[index]);
                            followedSingerPanel.remove(labelll[index]);
                            followedSingerPanel.repaint();
                        } catch (Exception ex) {
                            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                label[i].addMouseListener(new MouseAdapter() {
                    int index = i;
                    
                    public void mouseClicked(MouseEvent e) {
                        
                        try {
                            followedSingerPanel.setVisible(false);
                            followedSingerAltPanel.setVisible(true);
                            connection = DatabaseConnection.connect();
                            query = "select so.SongName,so.SongID from singers si,singer_songs ss,songs so where si.SingerName='" + labell[index].getText() + "'"
                                    + "and si.SingerID=ss.SingerID and ss.SongID=so.SongID ";
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            int count2 = 0;
                            JLabel[] labelSong = new JLabel[20];
                            JLabel[] labelSongg = new JLabel[20];
                            JLabel[] labelSonggg = new JLabel[20];
                            JLabel[] labelSongggg = new JLabel[20];
                            JLabel[] labelSonggggg = new JLabel[20];
                            int[] songId = new int[20];
                            int j = 0;
                            while (resultSet.next()) {
                                songId[j] = resultSet.getInt("SongID");
                                int x = (j % 2) * 510;
                                double y = Math.floor(j / 2) * 80;
                                Image img = new ImageIcon(this.getClass().getResource("Images/listen.jpg")).getImage();
                                labelSong[j] = new JLabel();
                                labelSongg[j] = new JLabel();
                                labelSong[j].setIcon(new ImageIcon(img));
                                labelSong[j].setBounds(x + 30, (int) y + 15, 500, 50);
                                labelSongg[j].setBounds(x + 30, (int) y + 45, 100, 50);
                                labelSongg[j].setText(resultSet.getString("SongName"));
                                labelSongg[j].setForeground(Color.PINK);
                                labelSongg[j].setFont(new Font(Font.SERIF, Font.PLAIN, 16));
                                labelSonggg[j] = new JLabel();
                                labelSonggg[j].setBounds(x + 130 + 30, (int) y + 45, 100, 50);
                                labelSonggg[j].setText("Kaydet");
                                labelSonggg[j].setForeground(Color.white);
                                labelSonggg[j].setFont(new Font(Font.SERIF, Font.PLAIN, 14));
                                labelSongggg[j] = new JLabel();
                                labelSongggg[j].setBounds(x + 130 + 130 + 30, (int) y + 45, 100, 50);
                                labelSongggg[j].setText("Dinle");
                                labelSongggg[j].setForeground(Color.white);
                                labelSongggg[j].setFont(new Font(Font.SERIF, Font.PLAIN, 14));
                                labelSonggggg[j] = new JLabel();
                                labelSonggggg[j].setBounds(x + 130 + 130 + 130 + 30, (int) y + 45, 100, 50);
                                labelSonggggg[j].setText("Detay");
                                labelSonggggg[j].setForeground(Color.white);
                                labelSonggggg[j].setFont(new Font(Font.SERIF, Font.PLAIN, 14));
                                followedSingerAltPanel.add(labelSong[j]);
                                followedSingerAltPanel.add(labelSongg[j]);
                                followedSingerAltPanel.add(labelSonggg[j]);
                                followedSingerAltPanel.add(labelSongggg[j]);
                                followedSingerAltPanel.add(labelSonggggg[j]);
                                followedSingerAltPanel.repaint();
                                
                                labelSonggg[j].addMouseListener(new MouseAdapter() {
                                    int index = j;
                                    String kindName = "";
                                    int count = 0;
                                    int playListID;
                                    
                                    public void mouseClicked(MouseEvent e) {
                                        try {
                                            connection = DatabaseConnection.connect();
                                            query = "select k.KindName from kinds k,song_kinds sk,songs s"
                                                    + " where sk.KindID=k.KindID and s.SongID=sk.SongID and"
                                                    + " s.SongName='" + labelSongg[index].getText() + "'";
                                            
                                            statement = connection.createStatement();
                                            resultSet = statement.executeQuery(query);
                                            while (resultSet.next()) {
                                                kindName = "";
                                                kindName = kindName.concat(resultSet.getString(1));
                                                System.out.println(kindName);
                                            }
                                            query = "select PlayListID from play_lists "
                                                    + " where UserID='" + LoginScreen.id + "'"
                                                    + " and PlayListName='" + kindName + "'";
                                            
                                            statement = connection.createStatement();
                                            resultSet = statement.executeQuery(query);
                                            System.out.println(kindName);
                                            while (resultSet.next()) {
                                                playListID = resultSet.getInt(1);
                                            }
                                            query = "select pls.SongID from play_list_songs pls,songs s,play_lists pl"
                                                    + " where pls.SongID=s.SongID and pls.PlayListID=pl.PlayListID"
                                                    + " and pl.UserID='" + LoginScreen.id + "'"
                                                    + " and pl.PlayListName='" + kindName + "'"
                                                    + " and s.SongName='" + labelSongg[index].getText() + "'";
                                            statement = connection.createStatement();
                                            resultSet = statement.executeQuery(query);
                                            while (resultSet.next()) {
                                                count++;
                                            }
                                            if (count == 0) {
                                                
                                                query = " insert into play_list_songs (SongID,PlayListID)"
                                                        + " values (?,?)";
                                                preparedStatement = connection.prepareStatement(query);
                                                preparedStatement.setInt(1, songId[index]);
                                                preparedStatement.setInt(2, playListID);
                                                preparedStatement.execute();
                                                connection.close();
                                            } else {
                                                JOptionPane.showMessageDialog(albumlerPanel5, "You are already add this song in your playlist");
                                            }
                                            
                                        } catch (Exception ex) {
                                            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                });
                                labelSonggggg[j].addMouseListener(new MouseAdapter() {
                                    int index2 = j;
                                    String singersName = "";
                                    String kindName = "";
                                    String albumName = "";
                                    
                                    public void mouseClicked(MouseEvent e) {
                                        try {
                                            connection = DatabaseConnection.connect();
                                            query = "select si.SingerName from singers si,singer_songs ss, songs so where"
                                                    + " si.SingerID=ss.SingerID and ss.SongID=so.SongID and"
                                                    + " so.SongName='" + labelSongg[index2].getText() + "'";
                                            
                                            statement = connection.createStatement();
                                            resultSet = statement.executeQuery(query);
                                            while (resultSet.next()) {
                                                singersName = singersName.concat(resultSet.getString(1) + " , ");
                                            }
                                            connection = DatabaseConnection.connect();
                                            query = "select ki.KindName from songs so,kinds ki,song_kinds sk "
                                                    + " where ki.KindID=sk.KindID and sk.SongID=so.SongID "
                                                    + " and so.SongName='" + labelSongg[index2].getText() + "'";
                                            statement = connection.createStatement();
                                            resultSet = statement.executeQuery(query);
                                            while (resultSet.next()) {
                                                kindName = resultSet.getString(1);
                                                
                                            }
                                            connection = DatabaseConnection.connect();
                                            query = "select a.AlbumName from songs so,albums a,song_albums sa"
                                                    + " where a.AlbumID=sa.AlbumID and sa.SongID=so.SongID"
                                                    + " and so.SongName='" + labelSongg[index2].getText() + "'";
                                            statement = connection.createStatement();
                                            resultSet = statement.executeQuery(query);
                                            while (resultSet.next()) {
                                                
                                                albumName = resultSet.getString(1);
                                                
                                            }
                                            
                                            JOptionPane.showMessageDialog(albumlerAltPanel, "This songs singers:" + singersName + " \n and song's kind:" + kindName
                                                    + "\n and album name:" + albumName);
                                            singersName = "";
                                        } catch (Exception ex) {
                                            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                    
                                });
                                
                                labelSongggg[j].addMouseListener(new MouseAdapter() {
                                    int index2 = j;
                                    int listen;
                                    
                                    public void mouseClicked(MouseEvent e) {
                                        int songIDforUser = 0;
                                        try {
                                            connection = DatabaseConnection.connect();
                                            query = "select ViewCount,SongID from songs where SongName='" + labelSongg[index2].getText() + "'";
                                            statement = connection.createStatement();
                                            resultSet = statement.executeQuery(query);
                                            
                                            while (resultSet.next()) {
                                                listen = resultSet.getInt(1);
                                                songIDforUser = resultSet.getInt(2);
                                            }
                                            query = "update songs set ViewCount=?  where SongName='" + labelSongg[index2].getText() + "'";
                                            preparedStatement = connection.prepareStatement(query);
                                            preparedStatement.setInt(1, (listen + 1));
                                            preparedStatement.executeUpdate();
                                            preparedStatement.close();
                                            connection.close();
                                            JOptionPane.showMessageDialog(albumlerAltPanel, "The song is resting");
                                            connection.close();
                                            connection = DatabaseConnection.connect();
                                            query = "select so.SongName,uls.listeningCount from songs so,user_listening_song uls,users u"
                                                    + " where so.SongID=uls.SongID and uls.UserID=u.UserID and so.SongName='" + labelSongg[index2].getText() + "'"
                                                    + " and u.UserName='" + LoginScreen.name + "'";
                                            statement = connection.createStatement();
                                            resultSet = statement.executeQuery(query);
                                            int count = 0;
                                            int listenUser = 0;
                                            while (resultSet.next()) {
                                                count++;
                                                listenUser = resultSet.getInt("ListeningCount");
                                                
                                            }
                                            if (count == 0) {
                                                query = " insert into user_listening_song (UserID,SongID,ListeningCount)"
                                                        + " values (?, ?,?)";
                                                preparedStatement = connection.prepareStatement(query);
                                                preparedStatement.setInt(1, LoginScreen.id);
                                                preparedStatement.setInt(2, songIDforUser);
                                                preparedStatement.setInt(3, 1);
                                                preparedStatement.execute();
                                                connection.close();
                                            } else {
                                                query = "update user_listening_song set ListeningCount=?  where UserID='" + LoginScreen.id + "'"
                                                        + "  and SongID='" + songIDforUser + "'";
                                                preparedStatement = connection.prepareStatement(query);
                                                preparedStatement.setInt(1, (listenUser + 1));
                                                preparedStatement.executeUpdate();
                                                preparedStatement.close();
                                                connection.close();
                                            }
                                            
                                        } catch (Exception ex) {
                                            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                    
                                });
                                
                                j++;
                            }
                        } catch (Exception ex) {
                            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                i++;
            }
            connection.close();
        } catch (Exception ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_sanatcilarLabelMouseClicked
//dinlediğin müzik türlerini getiriyor,kategori kategori ayırdım.pop,jazz,klasik olarak
    private void muzikTurleriLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_muzikTurleriLabel5MouseClicked
        muzikTurleriPanel5.setVisible(true);
        albumlerPanel5.setVisible(false);
        sanatcilerPanel5.setVisible(false);
        listelerPanel5.setVisible(false);
        albumlerAltPanel.setVisible(false);
        sanatcilarAltPanel.setVisible(false);
        muzikTurleriAltPanel.setVisible(false);
        muziklerAltPanel.setVisible(false);
        try {
            connection = DatabaseConnection.connect();
            query = "select * from kinds";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            JLabel[] label = new JLabel[9];
            JLabel[] labell = new JLabel[9];
            int i = 0;
            
            while (resultSet.next()) {
                int x = (i % 6) * 170;
                double y = Math.floor(i / 6) * 170;
                System.out.println(i);
                Image img = new ImageIcon(this.getClass().getResource("Images/image1.jpg")).getImage();
                label[i] = new JLabel();
                label[i].setIcon(new ImageIcon(img));
                label[i].setBounds(x + 65, (int) y, 160, 160);
                labell[i] = new JLabel();
                labell[i].setText(resultSet.getString("KindName"));
                labell[i].setBounds(x + 65, (int) y + 140, 80, 50);
                labell[i].setForeground(Color.pink);
                labell[i].setFont(new Font(Font.SERIF, Font.PLAIN, 14));
                muzikTurleriPanel5.add(label[i]);
                muzikTurleriPanel5.add(labell[i]);
                muzikTurleriPanel5.repaint();
                label[i].addMouseListener(new MouseAdapter() {
                    int index = i;
                    
                    public void mouseClicked(MouseEvent e) {
                        muzikTurleriPanel5.setVisible(false);
                        muzikTurleriAltPanel.setVisible(true);
                        model = (DefaultTableModel) table.getModel();
                        model.setRowCount(0);
                        try {
                            kind = labell[index].getText();
                            showInTable(labell[index].getText());
                        } catch (Exception ex) {
                            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                i++;
            }
            connection.close();
        } catch (Exception ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_muzikTurleriLabel5MouseClicked
//tüm albümler+şarkıları
    private void albumlerLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_albumlerLabel5MouseClicked
        muzikTurleriPanel5.setVisible(false);
        albumlerPanel5.setVisible(true);
        sanatcilerPanel5.setVisible(false);
        listelerPanel5.setVisible(false);
        albumlerAltPanel.removeAll();
        albumlerAltPanel.setVisible(false);
        sanatcilarAltPanel.setVisible(false);
        listelerAltPanel.setVisible(false);
        muzikTurleriAltPanel.setVisible(false);
        muziklerAltPanel.setVisible(false);
        try {
            connection = DatabaseConnection.connect();
            query = "select * from albums";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            JLabel[] label = new JLabel[20];
            JLabel[] labell = new JLabel[20];
            JLabel[] labelll = new JLabel[20];
            String[] isimler = new String[20];
            int[] albumIDs = new int[20];
            int i = 0;
            
            while (resultSet.next()) {
                int x = (i % 6) * 170;
                double y = Math.floor(i / 6) * 170;
                System.out.println(i);
                String imagePath = "Images/image" + ((i + 1) % 19) + ".jpg";
                Image img = new ImageIcon(this.getClass().getResource(imagePath)).getImage();
                label[i] = new JLabel();
                label[i].setIcon(new ImageIcon(img));
                label[i].setBounds(x + 65, (int) y, 150, 150);
                isimler[i] = "";
                isimler[i] = isimler[i].concat(resultSet.getString("AlbumName"));
                albumIDs[i] = resultSet.getInt("AlbumID");
                labell[i] = new JLabel();
                labell[i].setBounds(x + 65, (int) y + 135, 80, 50);
                labell[i].setText(isimler[i]);
                labell[i].setForeground(Color.pink);
                labell[i].setFont(new Font(Font.SERIF, Font.PLAIN, 14));
                labelll[i] = new JLabel();
                labelll[i].setBounds(x + 65 + 80, (int) y + 135, 80, 50);
                labelll[i].setText("Takip Et");
                labelll[i].setForeground(Color.lightGray);
                labelll[i].setFont(new Font(Font.SERIF, Font.PLAIN, 14));
                albumlerPanel5.add(label[i]);
                albumlerPanel5.add(labell[i]);
                albumlerPanel5.add(labelll[i]);
                albumlerPanel5.repaint();
                
                labell[i].addMouseListener(new MouseAdapter() {
                    int index = i;
                    
                    public void mouseClicked(MouseEvent e) {
                        String names = "";
                        try {
                            connection = DatabaseConnection.connect();
                            query = "select DISTINCT si.SingerName,a.AlbumKind "
                                    + " from albums a,song_albums sa,songs s,singer_songs ss,singers si"
                                    + " where a.AlbumID=sa.AlbumID and sa.SongID=s.SongID and s.SongID=ss.SongID"
                                    + "  and ss.SingerID=si.SingerID "
                                    + " and a.AlbumName='" + isimler[index] + "'";
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            int one = 0;
                            while (resultSet.next()) {
                                if (one == 0) {
                                    names = names.concat(resultSet.getString("AlbumKind") + "\n");
                                    one++;
                                }
                                names = names.concat(resultSet.getString("SingerName") + "\n");
                                
                            }
                            JOptionPane.showMessageDialog(albumlerPanel5, names);
                        } catch (Exception ex) {
                            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                
                labelll[i].addMouseListener(new MouseAdapter() {
                    int index = i;
                    
                    public void mouseClicked(MouseEvent e) {
                        try {
                            connection = DatabaseConnection.connect();
                            query = "Select * from followed_albums where"
                                    + " UserID='" + LoginScreen.id + "'"
                                    + " and AlbumID='" + albumIDs[index] + "'";
                            preparedStatement = connection.prepareStatement(query);
                            resultSet = preparedStatement.executeQuery();
                            int countFollowAlbum = 0;
                            while (resultSet.next()) {
                                JOptionPane.showMessageDialog(albumlerPanel5, "You are already following this album");
                                countFollowAlbum++;
                            }
                            if (countFollowAlbum == 0) {
                                connection = DatabaseConnection.connect();
                                query = "insert into followed_albums(UserID,AlbumID)"
                                        + " values (?, ?)";
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.setInt(1, LoginScreen.id);
                                preparedStatement.setInt(2, albumIDs[index]);
                                preparedStatement.execute();
                                JOptionPane.showMessageDialog(albumlerPanel5, "You followed this album");
                            }
                            
                        } catch (Exception ex) {
                            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                
                label[i].addMouseListener(new MouseAdapter() {
                    int index = i;
                    int[] songId = new int[100];
                    
                    public void mouseClicked(MouseEvent e) {
                        try {
                            albumlerPanel5.setVisible(false);
                            albumlerAltPanel.setVisible(true);
                            connection = DatabaseConnection.connect();
                            query = "select so.SongName,so.SongID from songs so,albums a,song_albums sa"
                                    + " where a.AlbumID=sa.AlbumID and sa.SongID=so.SongID"
                                    + " and a.AlbumName='" + isimler[index] + "'";
                            
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            int count2 = 0;
                            JLabel[] labelSong = new JLabel[20];
                            JLabel[] labelSongg = new JLabel[20];
                            JLabel[] labelSonggg = new JLabel[20];
                            JLabel[] labelSongggg = new JLabel[20];
                            JLabel[] labelSonggggg = new JLabel[20];
                            int j = 0;
                            while (resultSet.next()) {
                                songId[j] = resultSet.getInt("SongID");
                                int x = (j % 2) * 510;
                                double y = Math.floor(j / 2) * 60;
                                Image img = new ImageIcon(this.getClass().getResource("Images/listen.jpg")).getImage();
                                labelSong[j] = new JLabel();
                                labelSongg[j] = new JLabel();
                                labelSong[j].setIcon(new ImageIcon(img));
                                labelSong[j].setBounds(x + 30, (int) y + 15, 500, 50);
                                labelSongg[j].setBounds(x + 30, (int) y + 45, 100, 50);
                                labelSongg[j].setText(resultSet.getString("SongName"));
                                labelSongg[j].setForeground(Color.PINK);
                                labelSongg[j].setFont(new Font(Font.SERIF, Font.PLAIN, 12));
                                labelSonggg[j] = new JLabel();
                                labelSonggg[j].setBounds(x + 130 + 30, (int) y + 45, 100, 50);
                                labelSonggg[j].setText("Kaydet");
                                labelSonggg[j].setForeground(Color.white);
                                labelSonggg[j].setFont(new Font(Font.SERIF, Font.PLAIN, 12));
                                labelSongggg[j] = new JLabel();
                                labelSongggg[j].setBounds(x + 130 + 130 + 30, (int) y + 45, 100, 50);
                                labelSongggg[j].setText("Dinle");
                                labelSongggg[j].setForeground(Color.white);
                                labelSongggg[j].setFont(new Font(Font.SERIF, Font.PLAIN, 12));
                                labelSonggggg[j] = new JLabel();
                                labelSonggggg[j].setBounds(x + 130 + 130 + 130 + 30, (int) y + 45, 100, 50);
                                labelSonggggg[j].setText("Detay");
                                labelSonggggg[j].setForeground(Color.white);
                                labelSonggggg[j].setFont(new Font(Font.SERIF, Font.PLAIN, 12));
                                albumlerAltPanel.add(labelSong[j]);
                                albumlerAltPanel.add(labelSongg[j]);
                                albumlerAltPanel.add(labelSonggg[j]);
                                albumlerAltPanel.add(labelSongggg[j]);
                                albumlerAltPanel.add(labelSonggggg[j]);
                                albumlerAltPanel.repaint();
                                labelSonggg[j].addMouseListener(new MouseAdapter() {
                                    int index = j;
                                    String kindName = "";
                                    int count = 0;
                                    int playListID;
                                    
                                    public void mouseClicked(MouseEvent e) {
                                        try {
                                            connection = DatabaseConnection.connect();
                                            query = "select k.KindName from kinds k,song_kinds sk,songs s"
                                                    + " where sk.KindID=k.KindID and s.SongID=sk.SongID and"
                                                    + " s.SongName='" + labelSongg[index].getText() + "'";
                                            
                                            statement = connection.createStatement();
                                            resultSet = statement.executeQuery(query);
                                            while (resultSet.next()) {
                                                kindName = "";
                                                kindName = kindName.concat(resultSet.getString(1));
                                                System.out.println(kindName);
                                            }
                                            query = "select PlayListID from play_lists "
                                                    + " where UserID='" + LoginScreen.id + "'"
                                                    + " and PlayListName='" + kindName + "'";
                                            
                                            statement = connection.createStatement();
                                            resultSet = statement.executeQuery(query);
                                            System.out.println(kindName);
                                            while (resultSet.next()) {
                                                playListID = resultSet.getInt(1);
                                            }
                                            query = "select pls.SongID from play_list_songs pls,songs s,play_lists pl"
                                                    + " where pls.SongID=s.SongID and pls.PlayListID=pl.PlayListID"
                                                    + " and pl.UserID='" + LoginScreen.id + "'"
                                                    + " and pl.PlayListName='" + kindName + "'"
                                                    + " and s.SongName='" + labelSongg[index].getText() + "'";
                                            statement = connection.createStatement();
                                            resultSet = statement.executeQuery(query);
                                            while (resultSet.next()) {
                                                count++;
                                            }
                                            if (count == 0) {
                                                
                                                query = " insert into play_list_songs (SongID,PlayListID)"
                                                        + " values (?,?)";
                                                preparedStatement = connection.prepareStatement(query);
                                                preparedStatement.setInt(1, songId[index]);
                                                preparedStatement.setInt(2, playListID);
                                                preparedStatement.execute();
                                                connection.close();
                                            } else {
                                                JOptionPane.showMessageDialog(albumlerPanel5, "You are already add this song in your playlist");
                                            }
                                            
                                        } catch (Exception ex) {
                                            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                });
                                
                                labelSonggggg[j].addMouseListener(new MouseAdapter() {
                                    int index2 = j;
                                    String singersName = "";
                                    String kindName = "";
                                    
                                    public void mouseClicked(MouseEvent e) {
                                        try {
                                            connection = DatabaseConnection.connect();
                                            query = "select si.SingerName from singers si,singer_songs ss, songs so where"
                                                    + " si.SingerID=ss.SingerID and ss.SongID=so.SongID and"
                                                    + " so.SongName='" + labelSongg[index2].getText() + "'";
                                            
                                            statement = connection.createStatement();
                                            resultSet = statement.executeQuery(query);
                                            while (resultSet.next()) {
                                                singersName = singersName.concat(resultSet.getString(1) + " , ");
                                            }
                                            connection = DatabaseConnection.connect();
                                            query = "select ki.KindName from songs so,kinds ki,song_kinds sk"
                                                    + " where ki.KindID=sk.KindID and sk.SongID=so.SongID "
                                                    + " and so.SongName='" + labelSongg[index2].getText() + "'";
                                            statement = connection.createStatement();
                                            resultSet = statement.executeQuery(query);
                                            while (resultSet.next()) {
                                                kindName = resultSet.getString(1);
                                            }
                                            
                                            JOptionPane.showMessageDialog(albumlerAltPanel, "This songs singers:" + singersName + " \n and song's kind:" + kindName
                                                    + "\n and album name:" + isimler[index]);
                                            singersName = "";
                                        } catch (Exception ex) {
                                            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                    
                                });
                                
                                labelSongggg[j].addMouseListener(new MouseAdapter() {
                                    int index2 = j;
                                    int listen;
                                    
                                    public void mouseClicked(MouseEvent e) {
                                        int songIDforUser = 0;
                                        try {
                                            connection = DatabaseConnection.connect();
                                            query = "select ViewCount,SongID from songs where SongName='" + labelSongg[index2].getText() + "'";
                                            statement = connection.createStatement();
                                            resultSet = statement.executeQuery(query);
                                            
                                            while (resultSet.next()) {
                                                listen = resultSet.getInt(1);
                                                songIDforUser = resultSet.getInt(2);
                                            }
                                            query = "update songs set ViewCount=?  where SongName='" + labelSongg[index2].getText() + "'";
                                            preparedStatement = connection.prepareStatement(query);
                                            preparedStatement.setInt(1, (listen + 1));
                                            preparedStatement.executeUpdate();
                                            preparedStatement.close();
                                            connection.close();
                                            JOptionPane.showMessageDialog(albumlerAltPanel, "The song is resting");
                                            connection = DatabaseConnection.connect();
                                            query = "select so.SongName,uls.listeningCount from songs so,user_listening_song uls,users u"
                                                    + " where so.SongID=uls.SongID and uls.UserID=u.UserID and so.SongName='" + labelSongg[index2].getText() + "'"
                                                    + " and u.UserName='" + LoginScreen.name + "'";
                                            statement = connection.createStatement();
                                            resultSet = statement.executeQuery(query);
                                            int count = 0;
                                            int listenUser = 0;
                                            while (resultSet.next()) {
                                                count++;
                                                listenUser = resultSet.getInt("ListeningCount");
                                                
                                            }
                                            if (count == 0) {
                                                query = " insert into user_listening_song (UserID,SongID,ListeningCount)"
                                                        + " values (?, ?,?)";
                                                preparedStatement = connection.prepareStatement(query);
                                                preparedStatement.setInt(1, LoginScreen.id);
                                                preparedStatement.setInt(2, songIDforUser);
                                                preparedStatement.setInt(3, 1);
                                                preparedStatement.execute();
                                                connection.close();
                                            } else {
                                                query = "update user_listening_song set ListeningCount=?  where UserID='" + LoginScreen.id + "'"
                                                        + "  and SongID='" + songIDforUser + "'";
                                                preparedStatement = connection.prepareStatement(query);
                                                preparedStatement.setInt(1, (listenUser + 1));
                                                preparedStatement.executeUpdate();
                                                preparedStatement.close();
                                                connection.close();
                                            }
                                            
                                        } catch (Exception ex) {
                                            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                    
                                });
                                j++;
                            }
                        } catch (Exception ex) {
                            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                
                i++;
            }
            connection.close();
        } catch (Exception ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }//GEN-LAST:event_albumlerLabel5MouseClicked
//tüm şarkıcılar+şarkıları
    private void sanatcilerLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sanatcilerLabel5MouseClicked
        muzikTurleriPanel5.setVisible(false);
        albumlerPanel5.setVisible(false);
        sanatcilerPanel5.setVisible(true);
        listelerPanel5.setVisible(false);
        sanatcilarAltPanel.removeAll();
        albumlerAltPanel.setVisible(false);
        sanatcilarAltPanel.setVisible(false);
        listelerAltPanel.setVisible(false);
        muzikTurleriAltPanel.setVisible(false);
        muziklerAltPanel.setVisible(false);
        try {
            connection = DatabaseConnection.connect();
            query = "select * from singers";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            int count = 0;
            int a = 0;
            int[] idler = new int[200];
            String[] isimler = new String[200];
            while (resultSet.next()) {
                idler[a] = resultSet.getInt("SingerID");
                isimler[a] = "";
                isimler[a] = isimler[a].concat(resultSet.getString("SingerName"));
                count++;
                System.out.println(isimler[a]);
                a++;
                
            }
            JLabel[] label = new JLabel[count];
            JLabel[] labell = new JLabel[count];
            JLabel[] labelll = new JLabel[count];
            for (int i = 0; i < count; i++) {
                int x = (i % 7) * 160;
                double y = Math.floor(i / 7) * 160;
                System.out.println(i);
                Image img = new ImageIcon(this.getClass().getResource("Images/imagesinger.jpg")).getImage();
                label[i] = new JLabel();
                label[i].setIcon(new ImageIcon(img));
                label[i].setBounds(x + 5, (int) y, 140, 140);
                labell[i] = new JLabel();
                labell[i].setBounds(x, (int) y + 125, 100, 50);
                labell[i].setText(isimler[i]);
                labell[i].setForeground(Color.white);
                labell[i].setFont(new Font(Font.SERIF, Font.PLAIN, 14));
                labelll[i] = new JLabel();
                labelll[i].setBounds(x + 100, (int) y + 125, 80, 50);
                labelll[i].setText("Takip Et");
                labelll[i].setForeground(Color.lightGray);
                labelll[i].setFont(new Font(Font.SERIF, Font.PLAIN, 14));
                sanatcilerPanel5.add(label[i]);
                sanatcilerPanel5.add(labell[i]);
                sanatcilerPanel5.add(labelll[i]);
                sanatcilerPanel5.repaint();
                labelll[i].addMouseListener(new MouseAdapter() {
                    int index = i;
                    
                    public void mouseClicked(MouseEvent e) {
                        int count = 0;
                        try {
                            connection = DatabaseConnection.connect();
                            query = "Select * from followed_singers where"
                                    + " UserID='" + LoginScreen.id + "'"
                                    + " and SingerID='" + idler[index] + "'";
                            preparedStatement = connection.prepareStatement(query);
                            resultSet = preparedStatement.executeQuery();
                            int countFollowSinger = 0;
                            while (resultSet.next()) {
                                JOptionPane.showMessageDialog(albumlerPanel5, "You are already following this singer");
                                countFollowSinger++;
                            }
                            if (countFollowSinger == 0) {
                                connection = DatabaseConnection.connect();
                                query = "insert into followed_singers(UserID,SingerID)"
                                        + " values (?, ?)";
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.setInt(1, LoginScreen.id);
                                preparedStatement.setInt(2, idler[index]);
                                preparedStatement.execute();
                                JOptionPane.showMessageDialog(albumlerPanel5, "You followed this singer");
                            }
                            
                        } catch (Exception ex) {
                            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                
                label[i].addMouseListener(new MouseAdapter() {
                    int index = i;
                    
                    public void mouseClicked(MouseEvent e) {
                        
                        try {
                            sanatcilerPanel5.setVisible(false);
                            sanatcilarAltPanel.setVisible(true);
                            connection = DatabaseConnection.connect();
                            query = "select so.SongName,so.SongID from singers si,singer_songs ss,songs so where si.SingerID='" + idler[index] + "'"
                                    + "and si.SingerID=ss.SingerID and ss.SongID=so.SongID ";
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            int count2 = 0;
                            JLabel[] labelSong = new JLabel[20];
                            JLabel[] labelSongg = new JLabel[20];
                            JLabel[] labelSonggg = new JLabel[20];
                            JLabel[] labelSongggg = new JLabel[20];
                            JLabel[] labelSonggggg = new JLabel[20];
                            int[] songId = new int[20];
                            int j = 0;
                            while (resultSet.next()) {
                                songId[j] = resultSet.getInt("SongID");
                                int x = (j % 2) * 510;
                                double y = Math.floor(j / 2) * 80;
                                Image img = new ImageIcon(this.getClass().getResource("Images/listen.jpg")).getImage();
                                labelSong[j] = new JLabel();
                                labelSongg[j] = new JLabel();
                                labelSong[j].setIcon(new ImageIcon(img));
                                labelSong[j].setBounds(x + 30, (int) y + 15, 500, 50);
                                labelSongg[j].setBounds(x + 30, (int) y + 45, 100, 50);
                                labelSongg[j].setText(resultSet.getString("SongName"));
                                labelSongg[j].setForeground(Color.PINK);
                                labelSongg[j].setFont(new Font(Font.SERIF, Font.PLAIN, 16));
                                labelSonggg[j] = new JLabel();
                                labelSonggg[j].setBounds(x + 130 + 30, (int) y + 45, 100, 50);
                                labelSonggg[j].setText("Kaydet");
                                labelSonggg[j].setForeground(Color.white);
                                labelSonggg[j].setFont(new Font(Font.SERIF, Font.PLAIN, 14));
                                labelSongggg[j] = new JLabel();
                                labelSongggg[j].setBounds(x + 130 + 130 + 30, (int) y + 45, 100, 50);
                                labelSongggg[j].setText("Dinle");
                                labelSongggg[j].setForeground(Color.white);
                                labelSongggg[j].setFont(new Font(Font.SERIF, Font.PLAIN, 14));
                                labelSonggggg[j] = new JLabel();
                                labelSonggggg[j].setBounds(x + 130 + 130 + 130 + 30, (int) y + 45, 100, 50);
                                labelSonggggg[j].setText("Detay");
                                labelSonggggg[j].setForeground(Color.white);
                                labelSonggggg[j].setFont(new Font(Font.SERIF, Font.PLAIN, 14));
                                sanatcilarAltPanel.add(labelSong[j]);
                                sanatcilarAltPanel.add(labelSongg[j]);
                                sanatcilarAltPanel.add(labelSonggg[j]);
                                sanatcilarAltPanel.add(labelSongggg[j]);
                                sanatcilarAltPanel.add(labelSonggggg[j]);
                                sanatcilarAltPanel.repaint();
                                
                                labelSonggg[j].addMouseListener(new MouseAdapter() {
                                    int index = j;
                                    String kindName = "";
                                    int count = 0;
                                    int playListID;
                                    
                                    public void mouseClicked(MouseEvent e) {
                                        try {
                                            connection = DatabaseConnection.connect();
                                            query = "select k.KindName from kinds k,song_kinds sk,songs s"
                                                    + " where sk.KindID=k.KindID and s.SongID=sk.SongID and"
                                                    + " s.SongName='" + labelSongg[index].getText() + "'";
                                            
                                            statement = connection.createStatement();
                                            resultSet = statement.executeQuery(query);
                                            while (resultSet.next()) {
                                                kindName = "";
                                                kindName = kindName.concat(resultSet.getString(1));
                                                System.out.println(kindName);
                                            }
                                            query = "select PlayListID from play_lists "
                                                    + " where UserID='" + LoginScreen.id + "'"
                                                    + " and PlayListName='" + kindName + "'";
                                            
                                            statement = connection.createStatement();
                                            resultSet = statement.executeQuery(query);
                                            System.out.println(kindName);
                                            while (resultSet.next()) {
                                                playListID = resultSet.getInt(1);
                                            }
                                            query = "select pls.SongID from play_list_songs pls,songs s,play_lists pl"
                                                    + " where pls.SongID=s.SongID and pls.PlayListID=pl.PlayListID"
                                                    + " and pl.UserID='" + LoginScreen.id + "'"
                                                    + " and pl.PlayListName='" + kindName + "'"
                                                    + " and s.SongName='" + labelSongg[index].getText() + "'";
                                            statement = connection.createStatement();
                                            resultSet = statement.executeQuery(query);
                                            while (resultSet.next()) {
                                                count++;
                                            }
                                            if (count == 0) {
                                                
                                                query = " insert into play_list_songs (SongID,PlayListID)"
                                                        + " values (?,?)";
                                                preparedStatement = connection.prepareStatement(query);
                                                preparedStatement.setInt(1, songId[index]);
                                                preparedStatement.setInt(2, playListID);
                                                preparedStatement.execute();
                                                connection.close();
                                            } else {
                                                JOptionPane.showMessageDialog(albumlerPanel5, "You are already add this song in your playlist");
                                            }
                                            
                                        } catch (Exception ex) {
                                            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                });
                                labelSonggggg[j].addMouseListener(new MouseAdapter() {
                                    int index2 = j;
                                    String singersName = "";
                                    String kindName = "";
                                    String albumName = "";
                                    
                                    public void mouseClicked(MouseEvent e) {
                                        try {
                                            connection = DatabaseConnection.connect();
                                            query = "select si.SingerName from singers si,singer_songs ss, songs so where"
                                                    + " si.SingerID=ss.SingerID and ss.SongID=so.SongID and"
                                                    + " so.SongName='" + labelSongg[index2].getText() + "'";
                                            
                                            statement = connection.createStatement();
                                            resultSet = statement.executeQuery(query);
                                            while (resultSet.next()) {
                                                singersName = singersName.concat(resultSet.getString(1) + " , ");
                                            }
                                            connection = DatabaseConnection.connect();
                                            query = "select ki.KindName from songs so,kinds ki,song_kinds sk "
                                                    + " where ki.KindID=sk.KindID and sk.SongID=so.SongID "
                                                    + " and so.SongName='" + labelSongg[index2].getText() + "'";
                                            statement = connection.createStatement();
                                            resultSet = statement.executeQuery(query);
                                            while (resultSet.next()) {
                                                kindName = resultSet.getString(1);
                                                
                                            }
                                            connection = DatabaseConnection.connect();
                                            query = "select a.AlbumName from songs so,albums a,song_albums sa"
                                                    + " where a.AlbumID=sa.AlbumID and sa.SongID=so.SongID"
                                                    + " and so.SongName='" + labelSongg[index2].getText() + "'";
                                            statement = connection.createStatement();
                                            resultSet = statement.executeQuery(query);
                                            while (resultSet.next()) {
                                                
                                                albumName = resultSet.getString(1);
                                                
                                            }
                                            
                                            JOptionPane.showMessageDialog(albumlerAltPanel, "This songs singers:" + singersName + " \n and song's kind:" + kindName
                                                    + "\n and album name:" + albumName);
                                            singersName = "";
                                        } catch (Exception ex) {
                                            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                    
                                });
                                
                                labelSongggg[j].addMouseListener(new MouseAdapter() {
                                    int index2 = j;
                                    int listen;
                                    
                                    public void mouseClicked(MouseEvent e) {
                                        int songIDforUser = 0;
                                        try {
                                            connection = DatabaseConnection.connect();
                                            query = "select ViewCount,SongID from songs where SongName='" + labelSongg[index2].getText() + "'";
                                            statement = connection.createStatement();
                                            resultSet = statement.executeQuery(query);
                                            
                                            while (resultSet.next()) {
                                                listen = resultSet.getInt(1);
                                                songIDforUser = resultSet.getInt(2);
                                            }
                                            query = "update songs set ViewCount=?  where SongName='" + labelSongg[index2].getText() + "'";
                                            preparedStatement = connection.prepareStatement(query);
                                            preparedStatement.setInt(1, (listen + 1));
                                            preparedStatement.executeUpdate();
                                            preparedStatement.close();
                                            connection.close();
                                            JOptionPane.showMessageDialog(albumlerAltPanel, "The song is resting");
                                            connection.close();
                                            connection = DatabaseConnection.connect();
                                            query = "select so.SongName,uls.listeningCount from songs so,user_listening_song uls,users u"
                                                    + " where so.SongID=uls.SongID and uls.UserID=u.UserID and so.SongName='" + labelSongg[index2].getText() + "'"
                                                    + " and u.UserName='" + LoginScreen.name + "'";
                                            statement = connection.createStatement();
                                            resultSet = statement.executeQuery(query);
                                            int count = 0;
                                            int listenUser = 0;
                                            while (resultSet.next()) {
                                                count++;
                                                listenUser = resultSet.getInt("ListeningCount");
                                                
                                            }
                                            if (count == 0) {
                                                query = " insert into user_listening_song (UserID,SongID,ListeningCount)"
                                                        + " values (?, ?,?)";
                                                preparedStatement = connection.prepareStatement(query);
                                                preparedStatement.setInt(1, LoginScreen.id);
                                                preparedStatement.setInt(2, songIDforUser);
                                                preparedStatement.setInt(3, 1);
                                                preparedStatement.execute();
                                                connection.close();
                                            } else {
                                                query = "update user_listening_song set ListeningCount=?  where UserID='" + LoginScreen.id + "'"
                                                        + "  and SongID='" + songIDforUser + "'";
                                                preparedStatement = connection.prepareStatement(query);
                                                preparedStatement.setInt(1, (listenUser + 1));
                                                preparedStatement.executeUpdate();
                                                preparedStatement.close();
                                                connection.close();
                                            }
                                            
                                        } catch (Exception ex) {
                                            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                    
                                });
                                
                                j++;
                            }
                        } catch (Exception ex) {
                            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
            }
            connection.close();
        } catch (Exception ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }//GEN-LAST:event_sanatcilerLabel5MouseClicked
//tüm kullanıcılarr+takip edilen premiumsa listeleri
    private void listelerLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listelerLabel5MouseClicked
        muzikTurleriPanel5.setVisible(false);
        albumlerPanel5.setVisible(false);
        sanatcilerPanel5.setVisible(false);
        listelerPanel5.setVisible(true);
        listelerAltPanel.removeAll();
        listelerAltPanel.setVisible(false);
        listelerAltAltPanel.setVisible(false);
        albumlerAltPanel.setVisible(false);
        albumlerAltPanel.removeAll();
        sanatcilarAltPanel.removeAll();
        sanatcilarAltPanel.setVisible(false);
        muzikTurleriAltPanel.setVisible(false);
        muziklerAltPanel.setVisible(false);
        model = (DefaultTableModel) tableList.getModel();
        model.setRowCount(0);
        try {
            connection = DatabaseConnection.connect();
            query = "SELECT * from users where UserID!='" + LoginScreen.id + "'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            JLabel[] label = new JLabel[50];
            JLabel[] labell = new JLabel[50];
            JLabel[] labelll = new JLabel[50];
            int[] idler = new int[50];
            int i = 0;
            
            while (resultSet.next()) {
                idler[i] = resultSet.getInt("UserID");
                int x = (i % 6) * 170;
                double y = Math.floor(i / 6) * 170;
                System.out.println(i);
                Image img = new ImageIcon(this.getClass().getResource("Images/imagePeople.png")).getImage();
                label[i] = new JLabel();
                label[i].setIcon(new ImageIcon(img));
                label[i].setBounds(x + 65, (int) y, 160, 160);
                labell[i] = new JLabel();
                labell[i].setBounds(x + 65, (int) y + 140, 50, 50);
                labell[i].setText(resultSet.getString("UserName"));
                labell[i].setForeground(Color.white);
                labell[i].setFont(new Font(Font.SERIF, Font.PLAIN, 15));
                labelll[i] = new JLabel();
                labelll[i].setBounds(x + 65 + 50, (int) y + 140, 80, 50);
                labelll[i].setText("Takip Et");
                labelll[i].setForeground(Color.white);
                labelll[i].setFont(new Font(Font.SERIF, Font.PLAIN, 15));
                listelerPanel5.add(label[i]);
                listelerPanel5.add(labell[i]);
                listelerPanel5.add(labelll[i]);
                listelerPanel5.repaint();
                
                labelll[i].addMouseListener(new MouseAdapter() {
                    int index = i;
                    int premiumAccountIDtrue;
                    int countFollowUser = 0;
                    int say = 0;
                    
                    public void mouseClicked(MouseEvent e) {
                        model = (DefaultTableModel) tableList.getModel();
                        model.setRowCount(0);
                        int countFollowUser = 0;
                        try {
                            connection = DatabaseConnection.connect();
                            query = "select p.PremiumAccountID from premiumaccount p,users u"
                                    + " where u.UserID=p.UserID and u.UserID='" + idler[index] + "'";
                            preparedStatement = connection.prepareStatement(query);
                            resultSet = preparedStatement.executeQuery();
                            
                            while (resultSet.next()) {
                                
                                premiumAccountIDtrue = resultSet.getInt(1);
                                say++;
                                
                            }
                            if (say != 0) {
                                connection = DatabaseConnection.connect();
                                query = "Select * from followed_users where"
                                        + " UserID='" + LoginScreen.id + "'"
                                        + " and PremiumAccountID in(select PremiumAccountID from premiumaccount p"
                                        + " where p.UserID='" + idler[index] + "'"
                                        + ")";
                                preparedStatement = connection.prepareStatement(query);
                                resultSet = preparedStatement.executeQuery();
                                while (resultSet.next()) {
                                    JOptionPane.showMessageDialog(albumlerPanel5, "You are already following this user");
                                    countFollowUser++;
                                }
                                if (countFollowUser == 0) {
                                    connection = DatabaseConnection.connect();
                                    query = "insert into followed_users(UserID,PremiumAccountID)"
                                            + " values (?, ?)";
                                    preparedStatement = connection.prepareStatement(query);
                                    preparedStatement.setInt(1, LoginScreen.id);
                                    preparedStatement.setInt(2, premiumAccountIDtrue);
                                    preparedStatement.execute();
                                    JOptionPane.showMessageDialog(albumlerPanel5, "You followed this user");
                                }
                            } else {
                                JOptionPane.showMessageDialog(albumlerPanel5, "You can't followed this user.Because this user not premium");
                            }
                        } catch (Exception ex) {
                            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                
                label[i].addMouseListener(new MouseAdapter() {
                    int index = i;
                    int premiumAccountIDtrue;
                    int say = 0;
                    
                    public void mouseClicked(MouseEvent e) {
                        model = (DefaultTableModel) tableList.getModel();
                        model.setRowCount(0);
                        try {
                            connection = DatabaseConnection.connect();
                            query = "select p.PremiumAccountID from premiumaccount p,users u"
                                    + " where u.UserID=p.UserID and u.UserID='" + idler[index] + "'";
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            while (resultSet.next()) {
                                premiumAccountIDtrue = resultSet.getInt(1);
                                say++;
                                
                            }
                            if (say != 0) {
                                connection = DatabaseConnection.connect();
                                query = "SELECT u.UserName from users u,premiumaccount p,followed_users fu where u.UserID=p.UserID and"
                                        + " u.UserName='" + labell[index].getText() + "'"
                                        + " and fu.UserID='" + LoginScreen.id + "'"
                                        + " and fu.PremiumAccountID='" + premiumAccountIDtrue + "'";
                                statement = connection.createStatement();
                                resultSet = statement.executeQuery(query);
                                int count = 0;
                                while (resultSet.next()) {
                                    count++;
                                }
                                
                                if (count != 0) {
                                    try {
                                        
                                        listelerPanel5.setVisible(false);
                                        listelerAltPanel.setVisible(true);
                                        connection = DatabaseConnection.connect();
                                        query = "SELECT  p.PlayListName from users u ,play_lists p where u.UserID=p.UserID"
                                                + " and UserName='" + labell[index].getText() + "'";
                                        statement = connection.createStatement();
                                        resultSet = statement.executeQuery(query);
                                        int count2 = 0;
                                        
                                        JLabel[] labelList = new JLabel[20];
                                        JLabel[] labelListt = new JLabel[20];
                                        JLabel[] labelListtt = new JLabel[20];
                                        int j = 0;
                                        while (resultSet.next()) {
                                            int x = (j % 7) * 160;
                                            double y = Math.floor(j / 7) * 160;
                                            Image img = new ImageIcon(this.getClass().getResource("Images/imagekind.jpg")).getImage();
                                            labelList[j] = new JLabel();
                                            labelList[j].setIcon(new ImageIcon(img));
                                            labelList[j].setBounds(x + 15, (int) y, 150, 150);
                                            labelListt[j] = new JLabel();
                                            labelListt[j].setBounds(x + 10, (int) y + 130, 70, 50);
                                            labelListt[j].setText(resultSet.getString("PlayListName"));
                                            labelListt[j].setForeground(Color.PINK);
                                            labelListt[j].setFont(new Font(Font.SERIF, Font.PLAIN, 16));
                                            labelListtt[j] = new JLabel();
                                            labelListtt[j].setBounds(x + 10 + 70, (int) y + 130, 70, 50);
                                            labelListtt[j].setText("Takip Et");
                                            labelListtt[j].setForeground(Color.LIGHT_GRAY);
                                            labelListtt[j].setFont(new Font(Font.SERIF, Font.PLAIN, 16));
                                            listelerAltPanel.add(labelList[j]);
                                            listelerAltPanel.add(labelListtt[j]);
                                            listelerAltPanel.add(labelListt[j]);
                                            listelerAltPanel.repaint();
                                            labelListtt[j].addMouseListener(new MouseAdapter() {
                                                int indexx = j;
                                                int index = i;
                                                int countFollowedList = 0;
                                                int premID;
                                                int playListId;
                                                
                                                public void mouseClicked(MouseEvent e) {
                                                    try {
                                                        connection = DatabaseConnection.connect();
                                                        query = "select p.PremiumAccountID,pl.PlayListID from premiumaccount p,users u,play_lists pl"
                                                                + " where u.UserID=p.UserID and u.UserID='" + idler[index] + "'"
                                                                + " and p.UserID=pl.UserID and pl.PlayListName='" + labelListt[indexx].getText() + "'";
                                                        statement = connection.createStatement();
                                                        resultSet = statement.executeQuery(query);
                                                        
                                                        while (resultSet.next()) {
                                                            
                                                            premID = resultSet.getInt(1);
                                                            playListId = resultSet.getInt(2);
                                                        }
                                                        
                                                        connection = DatabaseConnection.connect();
                                                        query = "Select * from followed_play_lists fpl,play_lists pl where"
                                                                + " fpl.UserID='" + LoginScreen.id + "'"
                                                                + " and fpl.PlayListID=pl.PlayListID and pl.PlayListName='" + labelListt[indexx].getText() + "'"
                                                                + " and PremiumAccountID in(select PremiumAccountID from premiumaccount p"
                                                                + " where p.UserID='" + idler[index] + "'"
                                                                + ")";
                                                        
                                                        statement = connection.createStatement();
                                                        resultSet = statement.executeQuery(query);
                                                        while (resultSet.next()) {
                                                            JOptionPane.showMessageDialog(albumlerPanel5, "You are already following this playlist");
                                                            countFollowedList++;
                                                        }
                                                        
                                                        if (countFollowedList == 0) {
                                                            connection = DatabaseConnection.connect();
                                                            query = "insert into followed_play_lists(UserID,PremiumAccountID,PlayListID)"
                                                                    + " values (?,?,?)";
                                                            preparedStatement = connection.prepareStatement(query);
                                                            preparedStatement.setInt(1, LoginScreen.id);
                                                            preparedStatement.setInt(2, premID);
                                                            preparedStatement.setInt(3, playListId);
                                                            preparedStatement.execute();
                                                            JOptionPane.showMessageDialog(albumlerPanel5, "You followed this playlist");
                                                        }
                                                        
                                                    } catch (Exception ex) {
                                                        Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                                                    }
                                                    
                                                }
                                            });
                                            labelList[j].addMouseListener(new MouseAdapter() {
                                                int indexx = j;
                                                
                                                public void mouseClicked(MouseEvent e) {
                                                    listelerAltPanel.setVisible(false);
                                                    listelerAltAltPanel.setVisible(true);
                                                    model = (DefaultTableModel) tableList.getModel();
                                                    model.setRowCount(0);
                                                    try {
                                                        listUserNamee = labell[index].getText();
                                                        listNamee = labelListt[indexx].getText();
                                                        showInTableListeler(labell[index].getText(), labelListt[indexx].getText());
                                                    } catch (Exception ex) {
                                                        Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                                                    }
                                                    
                                                }
                                            });
                                            
                                            j++;
                                        }
                                    } catch (Exception ex) {
                                        Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(listelerPanel5, "Please first follow to user");
                                }
                            } else {
                                JOptionPane.showMessageDialog(listelerPanel5, "This user not premium.So you can't access their list");
                            }
                        } catch (Exception ex) {
                            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    
                }
                );
                
                i++;
            }
            connection.close();
        } catch (Exception ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_listelerLabel5MouseClicked
//top10 pop
    private void popLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_popLabelMouseClicked
        girisAltPanel.removeAll();
        girisAltPanel.setVisible(true);
        girisPanel.setVisible(true);
        muziklerAltPanel.setVisible(false);
        albumlerPanel.setVisible(false);
        sanatcilerPanel.setVisible(false);
        gozatPanel.setVisible(false);
        jPanel5.setVisible(false);
        muzikTurleriPanel5.setVisible(false);
        albumlerPanel5.setVisible(false);
        sanatcilerPanel5.setVisible(false);
        listelerPanel5.setVisible(false);
        muzikTurleriAltPanel.setVisible(false);
        albumlerAltPanel.setVisible(false);
        sanatcilarAltPanel.setVisible(false);
        try {
            
            connection = DatabaseConnection.connect();
            query = "SELECT so.* FROM songs so ,kinds ki, song_kinds sk where ki.KindID=sk.KindID and ki.KindName='POP'"
                    + " and sk.SongID=so.SongID order BY  so.ViewCount DESC LIMIT 10";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            JLabel labelSong[] = new JLabel[10];
            JLabel labelSongg[] = new JLabel[10];
            JLabel labelSonggg[] = new JLabel[10];
            JLabel labelSongggg[] = new JLabel[10];
            JLabel labelSonggggg[] = new JLabel[10];
            int[] songId = new int[10];
            int j = 0;
            while (resultSet.next()) {
                songId[j] = resultSet.getInt("SongID");
                int x = (j % 2) * 510;
                double y = Math.floor(j / 2) * 80;
                Image img = new ImageIcon(this.getClass().getResource("Images/listen.jpg")).getImage();
                labelSong[j] = new JLabel();
                labelSong[j].setIcon(new ImageIcon(img));
                labelSong[j].setBounds(x + 30, (int) y + 15, 500, 50);
                labelSongg[j] = new JLabel();
                labelSongg[j].setBounds(x + 30, (int) y + 45, 100, 50);
                labelSongg[j].setText(resultSet.getString("SongName"));
                labelSongg[j].setForeground(Color.PINK);
                labelSongg[j].setFont(new Font(Font.SERIF, Font.PLAIN, 12));
                labelSonggg[j] = new JLabel();
                labelSonggg[j].setBounds(x + 130 + 30, (int) y + 45, 100, 50);
                labelSonggg[j].setText("Kaydet");
                labelSonggg[j].setForeground(Color.white);
                labelSonggg[j].setFont(new Font(Font.SERIF, Font.PLAIN, 12));
                labelSongggg[j] = new JLabel();
                labelSongggg[j].setBounds(x + 130 + 130 + 30, (int) y + 45, 100, 50);
                labelSongggg[j].setText("Dinle");
                labelSongggg[j].setForeground(Color.white);
                labelSongggg[j].setFont(new Font(Font.SERIF, Font.PLAIN, 12));
                labelSonggggg[j] = new JLabel();
                labelSonggggg[j].setBounds(x + 130 + 130 + 130 + 30, (int) y + 45, 100, 50);
                labelSonggggg[j].setText("Detay");
                labelSonggggg[j].setForeground(Color.white);
                labelSonggggg[j].setFont(new Font(Font.SERIF, Font.PLAIN, 12));
                girisAltPanel.add(labelSongg[j]);
                girisAltPanel.add(labelSonggg[j]);
                girisAltPanel.add(labelSongggg[j]);
                girisAltPanel.add(labelSonggggg[j]);
                girisAltPanel.add(labelSong[j]);
                girisAltPanel.repaint();
                labelSonggg[j].addMouseListener(new MouseAdapter() {
                    int index = j;
                    String kindName = "";
                    int count = 0;
                    int playListID;
                    
                    public void mouseClicked(MouseEvent e) {
                        try {
                            connection = DatabaseConnection.connect();
                            query = "select k.KindName from kinds k,song_kinds sk,songs s"
                                    + " where sk.KindID=k.KindID and s.SongID=sk.SongID and"
                                    + " s.SongName='" + labelSongg[index].getText() + "'";
                            
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            while (resultSet.next()) {
                                kindName = "";
                                kindName = kindName.concat(resultSet.getString(1));
                                System.out.println(kindName);
                            }
                            query = "select PlayListID from play_lists "
                                    + " where UserID='" + LoginScreen.id + "'"
                                    + " and PlayListName='" + kindName + "'";
                            
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            System.out.println(kindName);
                            while (resultSet.next()) {
                                playListID = resultSet.getInt(1);
                            }
                            query = "select pls.SongID from play_list_songs pls,songs s,play_lists pl"
                                    + " where pls.SongID=s.SongID and pls.PlayListID=pl.PlayListID"
                                    + " and pl.UserID='" + LoginScreen.id + "'"
                                    + " and pl.PlayListName='" + kindName + "'"
                                    + " and s.SongName='" + labelSongg[index].getText() + "'";
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            while (resultSet.next()) {
                                count++;
                            }
                            if (count == 0) {
                                
                                query = " insert into play_list_songs (SongID,PlayListID)"
                                        + " values (?,?)";
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.setInt(1, songId[index]);
                                preparedStatement.setInt(2, playListID);
                                preparedStatement.execute();
                                connection.close();
                            } else {
                                JOptionPane.showMessageDialog(albumlerPanel5, "You are already add this song in your playlist");
                            }
                            
                        } catch (Exception ex) {
                            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                labelSonggggg[j].addMouseListener(new MouseAdapter() {
                    int index2 = j;
                    String singersName = "";
                    String kindName = "";
                    String albumName = "";
                    
                    public void mouseClicked(MouseEvent e) {
                        try {
                            connection = DatabaseConnection.connect();
                            query = "select si.SingerName from singers si,singer_songs ss, songs so where"
                                    + " si.SingerID=ss.SingerID and ss.SongID=so.SongID and"
                                    + " so.SongName='" + labelSongg[index2].getText() + "'";
                            
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            while (resultSet.next()) {
                                singersName = singersName.concat(resultSet.getString(1) + " , ");
                            }
                            connection = DatabaseConnection.connect();
                            query = "select ki.KindName from songs so,kinds ki,song_kinds sk "
                                    + " where ki.KindID=sk.KindID and sk.SongID=so.SongID "
                                    + " and so.SongName='" + labelSongg[index2].getText() + "'";
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            while (resultSet.next()) {
                                kindName = resultSet.getString(1);
                                
                            }
                            connection = DatabaseConnection.connect();
                            query = "select a.AlbumName from songs so,albums a,song_albums sa"
                                    + " where a.AlbumID=sa.AlbumID and sa.SongID=so.SongID"
                                    + " and so.SongName='" + labelSongg[index2].getText() + "'";
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            while (resultSet.next()) {
                                
                                albumName = resultSet.getString(1);
                                
                            }
                            
                            JOptionPane.showMessageDialog(albumlerAltPanel, "This songs singers:" + singersName + " \n and song's kind:" + kindName
                                    + "\n and album name:" + albumName);
                            singersName = "";
                        } catch (Exception ex) {
                            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    
                });
                
                labelSongggg[j].addMouseListener(new MouseAdapter() {
                    int index2 = j;
                    int listen;
                    
                    public void mouseClicked(MouseEvent e) {
                        int songIDforUser = 0;
                        try {
                            connection = DatabaseConnection.connect();
                            query = "select ViewCount,SongID from songs where SongName='" + labelSongg[index2].getText() + "'";
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            
                            while (resultSet.next()) {
                                listen = resultSet.getInt(1);
                                songIDforUser = resultSet.getInt(2);
                            }
                            query = "update songs set ViewCount=?  where SongName='" + labelSongg[index2].getText() + "'";
                            preparedStatement = connection.prepareStatement(query);
                            preparedStatement.setInt(1, (listen + 1));
                            preparedStatement.executeUpdate();
                            preparedStatement.close();
                            connection.close();
                            JOptionPane.showMessageDialog(albumlerAltPanel, "The song is resting");
                            connection.close();
                            connection = DatabaseConnection.connect();
                            query = "select so.SongName,uls.listeningCount from songs so,user_listening_song uls,users u"
                                    + " where so.SongID=uls.SongID and uls.UserID=u.UserID and so.SongName='" + labelSongg[index2].getText() + "'"
                                    + " and u.UserName='" + LoginScreen.name + "'";
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            int count = 0;
                            int listenUser = 0;
                            while (resultSet.next()) {
                                count++;
                                listenUser = resultSet.getInt("ListeningCount");
                                
                            }
                            if (count == 0) {
                                query = " insert into user_listening_song (UserID,SongID,ListeningCount)"
                                        + " values (?, ?,?)";
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.setInt(1, LoginScreen.id);
                                preparedStatement.setInt(2, songIDforUser);
                                preparedStatement.setInt(3, 1);
                                preparedStatement.execute();
                                connection.close();
                            } else {
                                query = "update user_listening_song set ListeningCount=?  where UserID='" + LoginScreen.id + "'"
                                        + "  and SongID='" + songIDforUser + "'";
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.setInt(1, (listenUser + 1));
                                preparedStatement.executeUpdate();
                                preparedStatement.close();
                                connection.close();
                            }
                            
                        } catch (Exception ex) {
                            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    
                });
                j++;
            }
            connection.close();
        } catch (Exception ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_popLabelMouseClicked
//top10
    private void genelLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_genelLabelMouseClicked
        girisAltPanel.removeAll();
        girisPanel.setVisible(true);
        girisAltPanel.setVisible(true);
        albumlerPanel.setVisible(false);
        sanatcilerPanel.setVisible(false);
        gozatPanel.setVisible(false);
        jPanel5.setVisible(false);
        muzikTurleriPanel5.setVisible(false);
        albumlerPanel5.setVisible(false);
        sanatcilerPanel5.setVisible(false);
        listelerPanel5.setVisible(false);
        muzikTurleriAltPanel.setVisible(false);
        albumlerAltPanel.setVisible(false);
        sanatcilarAltPanel.setVisible(false);
        albumlerAltPanel.removeAll();
        sanatcilarAltPanel.removeAll();
        muziklerAltPanel.setVisible(false);
        try {
            
            connection = DatabaseConnection.connect();
            query = "SELECT * FROM songs order BY  ViewCount DESC LIMIT 10";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            JLabel labelSong[] = new JLabel[10];
            JLabel labelSongg[] = new JLabel[10];
            JLabel labelSonggg[] = new JLabel[10];
            JLabel labelSongggg[] = new JLabel[10];
            JLabel labelSonggggg[] = new JLabel[10];
            int[] songId = new int[10];
            int j = 0;
            while (resultSet.next()) {
                songId[j] = resultSet.getInt("SongID");
                int x = (j % 2) * 510;
                double y = Math.floor(j / 2) * 80;
                Image img = new ImageIcon(this.getClass().getResource("Images/listen.jpg")).getImage();
                labelSong[j] = new JLabel();
                labelSong[j].setIcon(new ImageIcon(img));
                labelSong[j].setBounds(x + 30, (int) y + 15, 500, 50);
                labelSongg[j] = new JLabel();
                labelSongg[j].setBounds(x + 30, (int) y + 45, 100, 50);
                labelSongg[j].setText(resultSet.getString("SongName"));
                labelSongg[j].setForeground(Color.PINK);
                labelSongg[j].setFont(new Font(Font.SERIF, Font.PLAIN, 12));
                labelSonggg[j] = new JLabel();
                labelSonggg[j].setBounds(x + 130 + 30, (int) y + 45, 100, 50);
                labelSonggg[j].setText("Kaydet");
                labelSonggg[j].setForeground(Color.white);
                labelSonggg[j].setFont(new Font(Font.SERIF, Font.PLAIN, 12));
                labelSongggg[j] = new JLabel();
                labelSongggg[j].setBounds(x + 130 + 130 + 30, (int) y + 45, 100, 50);
                labelSongggg[j].setText("Dinle");
                labelSongggg[j].setForeground(Color.white);
                labelSongggg[j].setFont(new Font(Font.SERIF, Font.PLAIN, 12));
                labelSonggggg[j] = new JLabel();
                labelSonggggg[j].setBounds(x + 130 + 130 + 130 + 30, (int) y + 45, 100, 50);
                labelSonggggg[j].setText("Detay");
                labelSonggggg[j].setForeground(Color.white);
                labelSonggggg[j].setFont(new Font(Font.SERIF, Font.PLAIN, 12));
                girisAltPanel.add(labelSongg[j]);
                girisAltPanel.add(labelSonggg[j]);
                girisAltPanel.add(labelSongggg[j]);
                girisAltPanel.add(labelSonggggg[j]);
                girisAltPanel.add(labelSong[j]);
                girisAltPanel.repaint();
                labelSonggg[j].addMouseListener(new MouseAdapter() {
                    int index = j;
                    String kindName = "";
                    int count = 0;
                    int playListID;
                    
                    public void mouseClicked(MouseEvent e) {
                        try {
                            connection = DatabaseConnection.connect();
                            query = "select k.KindName from kinds k,song_kinds sk,songs s"
                                    + " where sk.KindID=k.KindID and s.SongID=sk.SongID and"
                                    + " s.SongName='" + labelSongg[index].getText() + "'";
                            
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            while (resultSet.next()) {
                                kindName = "";
                                kindName = kindName.concat(resultSet.getString(1));
                                System.out.println(kindName);
                            }
                            query = "select PlayListID from play_lists "
                                    + " where UserID='" + LoginScreen.id + "'"
                                    + " and PlayListName='" + kindName + "'";
                            
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            System.out.println(kindName);
                            while (resultSet.next()) {
                                playListID = resultSet.getInt(1);
                            }
                            query = "select pls.SongID from play_list_songs pls,songs s,play_lists pl"
                                    + " where pls.SongID=s.SongID and pls.PlayListID=pl.PlayListID"
                                    + " and pl.UserID='" + LoginScreen.id + "'"
                                    + " and pl.PlayListName='" + kindName + "'"
                                    + " and s.SongName='" + labelSongg[index].getText() + "'";
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            while (resultSet.next()) {
                                count++;
                            }
                            if (count == 0) {
                                
                                query = " insert into play_list_songs (SongID,PlayListID)"
                                        + " values (?,?)";
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.setInt(1, songId[index]);
                                preparedStatement.setInt(2, playListID);
                                preparedStatement.execute();
                                connection.close();
                            } else {
                                JOptionPane.showMessageDialog(albumlerPanel5, "You are already add this song in your playlist");
                            }
                            
                        } catch (Exception ex) {
                            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                labelSonggggg[j].addMouseListener(new MouseAdapter() {
                    int index2 = j;
                    String singersName = "";
                    String kindName = "";
                    String albumName = "";
                    
                    public void mouseClicked(MouseEvent e) {
                        try {
                            connection = DatabaseConnection.connect();
                            query = "select si.SingerName from singers si,singer_songs ss, songs so where"
                                    + " si.SingerID=ss.SingerID and ss.SongID=so.SongID and"
                                    + " so.SongName='" + labelSongg[index2].getText() + "'";
                            
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            while (resultSet.next()) {
                                singersName = singersName.concat(resultSet.getString(1) + " , ");
                            }
                            connection = DatabaseConnection.connect();
                            query = "select ki.KindName from songs so,kinds ki,song_kinds sk "
                                    + " where ki.KindID=sk.KindID and sk.SongID=so.SongID "
                                    + " and so.SongName='" + labelSongg[index2].getText() + "'";
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            while (resultSet.next()) {
                                kindName = resultSet.getString(1);
                                
                            }
                            connection = DatabaseConnection.connect();
                            query = "select a.AlbumName from songs so,albums a,song_albums sa"
                                    + " where a.AlbumID=sa.AlbumID and sa.SongID=so.SongID"
                                    + " and so.SongName='" + labelSongg[index2].getText() + "'";
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            while (resultSet.next()) {
                                
                                albumName = resultSet.getString(1);
                                
                            }
                            
                            JOptionPane.showMessageDialog(albumlerAltPanel, "This songs singers:" + singersName + " \n and song's kind:" + kindName
                                    + "\n and album name:" + albumName);
                            singersName = "";
                        } catch (Exception ex) {
                            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    
                });
                
                labelSongggg[j].addMouseListener(new MouseAdapter() {
                    int index2 = j;
                    int listen;
                    
                    public void mouseClicked(MouseEvent e) {
                        int songIDforUser = 0;
                        try {
                            connection = DatabaseConnection.connect();
                            query = "select ViewCount,SongID from songs where SongName='" + labelSongg[index2].getText() + "'";
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            
                            while (resultSet.next()) {
                                listen = resultSet.getInt(1);
                                songIDforUser = resultSet.getInt(2);
                            }
                            query = "update songs set ViewCount=?  where SongName='" + labelSongg[index2].getText() + "'";
                            preparedStatement = connection.prepareStatement(query);
                            preparedStatement.setInt(1, (listen + 1));
                            preparedStatement.executeUpdate();
                            preparedStatement.close();
                            connection.close();
                            JOptionPane.showMessageDialog(albumlerAltPanel, "The song is resting");
                            connection.close();
                            connection = DatabaseConnection.connect();
                            query = "select so.SongName,uls.listeningCount from songs so,user_listening_song uls,users u"
                                    + " where so.SongID=uls.SongID and uls.UserID=u.UserID and so.SongName='" + labelSongg[index2].getText() + "'"
                                    + " and u.UserName='" + LoginScreen.name + "'";
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            int count = 0;
                            int listenUser = 0;
                            while (resultSet.next()) {
                                count++;
                                listenUser = resultSet.getInt("ListeningCount");
                                
                            }
                            if (count == 0) {
                                query = " insert into user_listening_song (UserID,SongID,ListeningCount)"
                                        + " values (?, ?,?)";
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.setInt(1, LoginScreen.id);
                                preparedStatement.setInt(2, songIDforUser);
                                preparedStatement.setInt(3, 1);
                                preparedStatement.execute();
                                connection.close();
                            } else {
                                query = "update user_listening_song set ListeningCount=?  where UserID='" + LoginScreen.id + "'"
                                        + "  and SongID='" + songIDforUser + "'";
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.setInt(1, (listenUser + 1));
                                preparedStatement.executeUpdate();
                                preparedStatement.close();
                                connection.close();
                            }
                            
                        } catch (Exception ex) {
                            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    
                });
                j++;
            }
            connection.close();
        } catch (Exception ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_genelLabelMouseClicked
//top10 jazz
    private void jazzLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jazzLabelMouseClicked
        girisPanel.setVisible(true);
        girisAltPanel.setVisible(true);
        girisAltPanel.removeAll();
        girisAltPanel.setVisible(true);
        albumlerPanel.setVisible(false);
        sanatcilerPanel.setVisible(false);
        gozatPanel.setVisible(false);
        jPanel5.setVisible(false);
        muzikTurleriPanel5.setVisible(false);
        albumlerPanel5.setVisible(false);
        sanatcilerPanel5.setVisible(false);
        listelerPanel5.setVisible(false);
        muzikTurleriAltPanel.setVisible(false);
        albumlerAltPanel.setVisible(false);
        sanatcilarAltPanel.setVisible(false);
        muziklerAltPanel.setVisible(false);
        try {
            
            connection = DatabaseConnection.connect();
            query = "SELECT so.* FROM songs so ,kinds ki, song_kinds sk where ki.KindID=sk.KindID and ki.KindName='JAZZ'"
                    + " and sk.SongID=so.SongID order BY  so.ViewCount DESC LIMIT 10";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            JLabel labelSong[] = new JLabel[10];
            JLabel labelSongg[] = new JLabel[10];
            JLabel labelSonggg[] = new JLabel[10];
            JLabel labelSongggg[] = new JLabel[10];
            JLabel labelSonggggg[] = new JLabel[10];
            int[] songId = new int[10];
            int j = 0;
            while (resultSet.next()) {
                songId[j] = resultSet.getInt("SongID");
                int x = (j % 2) * 510;
                double y = Math.floor(j / 2) * 80;
                Image img = new ImageIcon(this.getClass().getResource("Images/listen.jpg")).getImage();
                labelSong[j] = new JLabel();
                labelSong[j].setIcon(new ImageIcon(img));
                labelSong[j].setBounds(x + 30, (int) y + 15, 500, 50);
                labelSongg[j] = new JLabel();
                labelSongg[j].setBounds(x + 30, (int) y + 45, 100, 50);
                labelSongg[j].setText(resultSet.getString("SongName"));
                labelSongg[j].setForeground(Color.PINK);
                labelSongg[j].setFont(new Font(Font.SERIF, Font.PLAIN, 12));
                labelSonggg[j] = new JLabel();
                labelSonggg[j].setBounds(x + 130 + 30, (int) y + 45, 100, 50);
                labelSonggg[j].setText("Kaydet");
                labelSonggg[j].setForeground(Color.white);
                labelSonggg[j].setFont(new Font(Font.SERIF, Font.PLAIN, 12));
                labelSongggg[j] = new JLabel();
                labelSongggg[j].setBounds(x + 130 + 130 + 30, (int) y + 45, 100, 50);
                labelSongggg[j].setText("Dinle");
                labelSongggg[j].setForeground(Color.white);
                labelSongggg[j].setFont(new Font(Font.SERIF, Font.PLAIN, 12));
                labelSonggggg[j] = new JLabel();
                labelSonggggg[j].setBounds(x + 130 + 130 + 130 + 30, (int) y + 45, 100, 50);
                labelSonggggg[j].setText("Detay");
                labelSonggggg[j].setForeground(Color.white);
                labelSonggggg[j].setFont(new Font(Font.SERIF, Font.PLAIN, 12));
                girisAltPanel.add(labelSongg[j]);
                girisAltPanel.add(labelSonggg[j]);
                girisAltPanel.add(labelSongggg[j]);
                girisAltPanel.add(labelSonggggg[j]);
                girisAltPanel.add(labelSong[j]);
                
                girisAltPanel.repaint();
                labelSonggg[j].addMouseListener(new MouseAdapter() {
                    int index = j;
                    String kindName = "";
                    int count = 0;
                    int playListID;
                    
                    public void mouseClicked(MouseEvent e) {
                        try {
                            connection = DatabaseConnection.connect();
                            query = "select k.KindName from kinds k,song_kinds sk,songs s"
                                    + " where sk.KindID=k.KindID and s.SongID=sk.SongID and"
                                    + " s.SongName='" + labelSongg[index].getText() + "'";
                            
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            while (resultSet.next()) {
                                kindName = "";
                                kindName = kindName.concat(resultSet.getString(1));
                                System.out.println(kindName);
                            }
                            query = "select PlayListID from play_lists "
                                    + " where UserID='" + LoginScreen.id + "'"
                                    + " and PlayListName='" + kindName + "'";
                            
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            System.out.println(kindName);
                            while (resultSet.next()) {
                                playListID = resultSet.getInt(1);
                            }
                            query = "select pls.SongID from play_list_songs pls,songs s,play_lists pl"
                                    + " where pls.SongID=s.SongID and pls.PlayListID=pl.PlayListID"
                                    + " and pl.UserID='" + LoginScreen.id + "'"
                                    + " and pl.PlayListName='" + kindName + "'"
                                    + " and s.SongName='" + labelSongg[index].getText() + "'";
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            while (resultSet.next()) {
                                count++;
                            }
                            if (count == 0) {
                                
                                query = " insert into play_list_songs (SongID,PlayListID)"
                                        + " values (?,?)";
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.setInt(1, songId[index]);
                                preparedStatement.setInt(2, playListID);
                                preparedStatement.execute();
                                connection.close();
                            } else {
                                JOptionPane.showMessageDialog(albumlerPanel5, "You are already add this song in your playlist");
                            }
                            
                        } catch (Exception ex) {
                            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                labelSonggggg[j].addMouseListener(new MouseAdapter() {
                    int index2 = j;
                    String singersName = "";
                    String kindName = "";
                    String albumName = "";
                    
                    public void mouseClicked(MouseEvent e) {
                        try {
                            connection = DatabaseConnection.connect();
                            query = "select si.SingerName from singers si,singer_songs ss, songs so where"
                                    + " si.SingerID=ss.SingerID and ss.SongID=so.SongID and"
                                    + " so.SongName='" + labelSongg[index2].getText() + "'";
                            
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            while (resultSet.next()) {
                                singersName = singersName.concat(resultSet.getString(1) + " , ");
                            }
                            connection = DatabaseConnection.connect();
                            query = "select ki.KindName from songs so,kinds ki,song_kinds sk "
                                    + " where ki.KindID=sk.KindID and sk.SongID=so.SongID "
                                    + " and so.SongName='" + labelSongg[index2].getText() + "'";
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            while (resultSet.next()) {
                                kindName = resultSet.getString(1);
                                
                            }
                            connection = DatabaseConnection.connect();
                            query = "select a.AlbumName from songs so,albums a,song_albums sa"
                                    + " where a.AlbumID=sa.AlbumID and sa.SongID=so.SongID"
                                    + " and so.SongName='" + labelSongg[index2].getText() + "'";
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            while (resultSet.next()) {
                                
                                albumName = resultSet.getString(1);
                                
                            }
                            
                            JOptionPane.showMessageDialog(albumlerAltPanel, "This songs singers:" + singersName + " \n and song's kind:" + kindName
                                    + "\n and album name:" + albumName);
                            singersName = "";
                        } catch (Exception ex) {
                            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    
                });
                
                labelSongggg[j].addMouseListener(new MouseAdapter() {
                    int index2 = j;
                    int listen;
                    
                    public void mouseClicked(MouseEvent e) {
                        int songIDforUser = 0;
                        try {
                            connection = DatabaseConnection.connect();
                            query = "select ViewCount,SongID from songs where SongName='" + labelSongg[index2].getText() + "'";
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            
                            while (resultSet.next()) {
                                listen = resultSet.getInt(1);
                                songIDforUser = resultSet.getInt(2);
                            }
                            query = "update songs set ViewCount=?  where SongName='" + labelSongg[index2].getText() + "'";
                            preparedStatement = connection.prepareStatement(query);
                            preparedStatement.setInt(1, (listen + 1));
                            preparedStatement.executeUpdate();
                            preparedStatement.close();
                            connection.close();
                            JOptionPane.showMessageDialog(albumlerAltPanel, "The song is resting");
                            connection.close();
                            connection = DatabaseConnection.connect();
                            query = "select so.SongName,uls.listeningCount from songs so,user_listening_song uls,users u"
                                    + " where so.SongID=uls.SongID and uls.UserID=u.UserID and so.SongName='" + labelSongg[index2].getText() + "'"
                                    + " and u.UserName='" + LoginScreen.name + "'";
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            int count = 0;
                            int listenUser = 0;
                            while (resultSet.next()) {
                                count++;
                                listenUser = resultSet.getInt("ListeningCount");
                                
                            }
                            if (count == 0) {
                                query = " insert into user_listening_song (UserID,SongID,ListeningCount)"
                                        + " values (?, ?,?)";
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.setInt(1, LoginScreen.id);
                                preparedStatement.setInt(2, songIDforUser);
                                preparedStatement.setInt(3, 1);
                                preparedStatement.execute();
                                connection.close();
                            } else {
                                query = "update user_listening_song set ListeningCount=?  where UserID='" + LoginScreen.id + "'"
                                        + "  and SongID='" + songIDforUser + "'";
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.setInt(1, (listenUser + 1));
                                preparedStatement.executeUpdate();
                                preparedStatement.close();
                                connection.close();
                            }
                            
                        } catch (Exception ex) {
                            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    
                });
                j++;
            }
            connection.close();
        } catch (Exception ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jazzLabelMouseClicked
//top10 klasik
    private void klasikLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_klasikLabelMouseClicked
        girisAltPanel.removeAll();
        girisPanel.setVisible(true);
        girisAltPanel.setVisible(true);
        albumlerPanel.setVisible(false);
        sanatcilerPanel.setVisible(false);
        gozatPanel.setVisible(false);
        jPanel5.setVisible(false);
        muzikTurleriPanel5.setVisible(false);
        albumlerPanel5.setVisible(false);
        sanatcilerPanel5.setVisible(false);
        listelerPanel5.setVisible(false);
        muzikTurleriAltPanel.setVisible(false);
        albumlerAltPanel.setVisible(false);
        sanatcilarAltPanel.setVisible(false);
        muziklerAltPanel.setVisible(false);
        try {
            
            connection = DatabaseConnection.connect();
            query = "SELECT so.* FROM songs so ,kinds ki, song_kinds sk where ki.KindID=sk.KindID and ki.KindName='KLASİK'"
                    + " and sk.SongID=so.SongID order BY  so.ViewCount DESC LIMIT 10";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            JLabel labelSong[] = new JLabel[10];
            JLabel labelSongg[] = new JLabel[10];
            JLabel labelSonggg[] = new JLabel[10];
            JLabel labelSongggg[] = new JLabel[10];
            JLabel labelSonggggg[] = new JLabel[10];
            int[] songId = new int[10];
            int j = 0;
            while (resultSet.next()) {
                songId[j] = resultSet.getInt("SongID");
                int x = (j % 2) * 510;
                double y = Math.floor(j / 2) * 80;
                Image img = new ImageIcon(this.getClass().getResource("Images/listen.jpg")).getImage();
                labelSong[j] = new JLabel();
                labelSong[j].setIcon(new ImageIcon(img));
                labelSong[j].setBounds(x + 30, (int) y + 15, 500, 50);
                labelSongg[j] = new JLabel();
                labelSongg[j].setBounds(x + 30, (int) y + 45, 100, 50);
                labelSongg[j].setText(resultSet.getString("SongName"));
                labelSongg[j].setForeground(Color.PINK);
                labelSongg[j].setFont(new Font(Font.SERIF, Font.PLAIN, 12));
                labelSonggg[j] = new JLabel();
                labelSonggg[j].setBounds(x + 130 + 30, (int) y + 45, 100, 50);
                labelSonggg[j].setText("Kaydet");
                labelSonggg[j].setForeground(Color.white);
                labelSonggg[j].setFont(new Font(Font.SERIF, Font.PLAIN, 12));
                labelSongggg[j] = new JLabel();
                labelSongggg[j].setBounds(x + 130 + 130 + 30, (int) y + 45, 100, 50);
                labelSongggg[j].setText("Dinle");
                labelSongggg[j].setForeground(Color.white);
                labelSongggg[j].setFont(new Font(Font.SERIF, Font.PLAIN, 12));
                labelSonggggg[j] = new JLabel();
                labelSonggggg[j].setBounds(x + 130 + 130 + 130 + 30, (int) y + 45, 100, 50);
                labelSonggggg[j].setText("Detay");
                labelSonggggg[j].setForeground(Color.white);
                labelSonggggg[j].setFont(new Font(Font.SERIF, Font.PLAIN, 12));
                girisAltPanel.add(labelSongg[j]);
                girisAltPanel.add(labelSonggg[j]);
                girisAltPanel.add(labelSongggg[j]);
                girisAltPanel.add(labelSonggggg[j]);
                girisAltPanel.add(labelSong[j]);
                
                girisAltPanel.repaint();
                labelSonggg[j].addMouseListener(new MouseAdapter() {
                    int index = j;
                    String kindName = "";
                    int count = 0;
                    int playListID;
                    
                    public void mouseClicked(MouseEvent e) {
                        try {
                            connection = DatabaseConnection.connect();
                            query = "select k.KindName from kinds k,song_kinds sk,songs s"
                                    + " where sk.KindID=k.KindID and s.SongID=sk.SongID and"
                                    + " s.SongName='" + labelSongg[index].getText() + "'";
                            
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            while (resultSet.next()) {
                                kindName = "";
                                kindName = kindName.concat(resultSet.getString(1));
                                System.out.println(kindName);
                            }
                            query = "select PlayListID from play_lists "
                                    + " where UserID='" + LoginScreen.id + "'"
                                    + " and PlayListName='" + kindName + "'";
                            
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            System.out.println(kindName);
                            while (resultSet.next()) {
                                playListID = resultSet.getInt(1);
                            }
                            query = "select pls.SongID from play_list_songs pls,songs s,play_lists pl"
                                    + " where pls.SongID=s.SongID and pls.PlayListID=pl.PlayListID"
                                    + " and pl.UserID='" + LoginScreen.id + "'"
                                    + " and pl.PlayListName='" + kindName + "'"
                                    + " and s.SongName='" + labelSongg[index].getText() + "'";
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            while (resultSet.next()) {
                                count++;
                            }
                            if (count == 0) {
                                
                                query = " insert into play_list_songs (SongID,PlayListID)"
                                        + " values (?,?)";
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.setInt(1, songId[index]);
                                preparedStatement.setInt(2, playListID);
                                preparedStatement.execute();
                                connection.close();
                            } else {
                                JOptionPane.showMessageDialog(albumlerPanel5, "You are already add this song in your playlist");
                            }
                            
                        } catch (Exception ex) {
                            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                labelSonggggg[j].addMouseListener(new MouseAdapter() {
                    int index2 = j;
                    String singersName = "";
                    String kindName = "";
                    String albumName = "";
                    
                    public void mouseClicked(MouseEvent e) {
                        try {
                            connection = DatabaseConnection.connect();
                            query = "select si.SingerName from singers si,singer_songs ss, songs so where"
                                    + " si.SingerID=ss.SingerID and ss.SongID=so.SongID and"
                                    + " so.SongName='" + labelSongg[index2].getText() + "'";
                            
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            while (resultSet.next()) {
                                singersName = singersName.concat(resultSet.getString(1) + " , ");
                            }
                            connection = DatabaseConnection.connect();
                            query = "select ki.KindName from songs so,kinds ki,song_kinds sk "
                                    + " where ki.KindID=sk.KindID and sk.SongID=so.SongID "
                                    + " and so.SongName='" + labelSongg[index2].getText() + "'";
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            while (resultSet.next()) {
                                kindName = resultSet.getString(1);
                                
                            }
                            connection = DatabaseConnection.connect();
                            query = "select a.AlbumName from songs so,albums a,song_albums sa"
                                    + " where a.AlbumID=sa.AlbumID and sa.SongID=so.SongID"
                                    + " and so.SongName='" + labelSongg[index2].getText() + "'";
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            while (resultSet.next()) {
                                
                                albumName = resultSet.getString(1);
                                
                            }
                            
                            JOptionPane.showMessageDialog(albumlerAltPanel, "This songs singers:" + singersName + " \n and song's kind:" + kindName
                                    + "\n and album name:" + albumName);
                            singersName = "";
                        } catch (Exception ex) {
                            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    
                });
                
                labelSongggg[j].addMouseListener(new MouseAdapter() {
                    int index2 = j;
                    int listen;
                    
                    public void mouseClicked(MouseEvent e) {
                        int songIDforUser = 0;
                        try {
                            connection = DatabaseConnection.connect();
                            query = "select ViewCount,SongID from songs where SongName='" + labelSongg[index2].getText() + "'";
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            
                            while (resultSet.next()) {
                                listen = resultSet.getInt(1);
                                songIDforUser = resultSet.getInt(2);
                            }
                            query = "update songs set ViewCount=?  where SongName='" + labelSongg[index2].getText() + "'";
                            preparedStatement = connection.prepareStatement(query);
                            preparedStatement.setInt(1, (listen + 1));
                            preparedStatement.executeUpdate();
                            preparedStatement.close();
                            connection.close();
                            JOptionPane.showMessageDialog(albumlerAltPanel, "The song is resting");
                            connection.close();
                            connection = DatabaseConnection.connect();
                            query = "select so.SongName,uls.listeningCount from songs so,user_listening_song uls,users u"
                                    + " where so.SongID=uls.SongID and uls.UserID=u.UserID and so.SongName='" + labelSongg[index2].getText() + "'"
                                    + " and u.UserName='" + LoginScreen.name + "'";
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            int count = 0;
                            int listenUser = 0;
                            while (resultSet.next()) {
                                count++;
                                listenUser = resultSet.getInt("ListeningCount");
                                
                            }
                            if (count == 0) {
                                query = " insert into user_listening_song (UserID,SongID,ListeningCount)"
                                        + " values (?, ?,?)";
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.setInt(1, LoginScreen.id);
                                preparedStatement.setInt(2, songIDforUser);
                                preparedStatement.setInt(3, 1);
                                preparedStatement.execute();
                                connection.close();
                            } else {
                                query = "update user_listening_song set ListeningCount=?  where UserID='" + LoginScreen.id + "'"
                                        + "  and SongID='" + songIDforUser + "'";
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.setInt(1, (listenUser + 1));
                                preparedStatement.executeUpdate();
                                preparedStatement.close();
                                connection.close();
                            }
                            
                        } catch (Exception ex) {
                            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    
                });
                j++;
                
            }
            connection.close();
        } catch (Exception ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_klasikLabelMouseClicked
//kullaıcıların ülkesine göre top10
    private void ulkeLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ulkeLabelMouseClicked
        girisAltPanel.removeAll();
        girisAltAltPanel.removeAll();
        girisAltAltPanel.setVisible(false);
        girisAltPanel.setVisible(true);
        muziklerAltPanel.setVisible(false);
        girisAltPanel.repaint();
        try {
            connection = DatabaseConnection.connect();
            query = "Select Distinct Country from users";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            int i = 0;
            JLabel[] label = new JLabel[20];
            JLabel[] labell = new JLabel[20];
            while (resultSet.next()) {
                int x = (i % 7) * 160;
                double y = Math.floor(i / 7) * 170;
                Image img = new ImageIcon(this.getClass().getResource("Images/imageCountry.jpg")).getImage();
                label[i] = new JLabel();
                label[i].setIcon(new ImageIcon(img));
                label[i].setBounds(x + 20, (int) y + 15, 150, 150);
                labell[i] = new JLabel();
                labell[i].setBounds(x + 20, (int) y + 150, 100, 50);
                labell[i].setText(resultSet.getString("Country"));
                labell[i].setForeground(Color.PINK);
                labell[i].setFont(new Font(Font.SERIF, Font.PLAIN, 18));
                girisAltPanel.add(label[i]);
                girisAltPanel.add(labell[i]);
                girisAltPanel.repaint();
                label[i].addMouseListener(new MouseAdapter() {
                    int index = i;
                    
                    public void mouseClicked(MouseEvent e) {
                        try {
                            girisAltPanel.setVisible(false);
                            girisAltAltPanel.setVisible(true);
                            connection = DatabaseConnection.connect();
                            query = "select s.SongName,s.SongID,Sum(uls.ListeningCount) from songs s,user_listening_song uls,users u"
                                    + " where u.UserID=uls.UserID and uls.SongID=s.SongID"
                                    + " and u.Country='" + labell[index].getText() + "'"
                                    + "group by s.SongID  order by Sum(uls.ListeningCount) DESC LIMIT 10";
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            int j = 0;
                            JLabel labelSong[] = new JLabel[10];
                            JLabel labelSongg[] = new JLabel[10];
                            JLabel labelSonggg[] = new JLabel[10];
                            JLabel labelSongggg[] = new JLabel[10];
                            JLabel labelSonggggg[] = new JLabel[10];
                            int[] songId = new int[10];
                            while (resultSet.next()) {
                                songId[j] = resultSet.getInt("SongID");
                                int x = (j % 2) * 510;
                                double y = Math.floor(j / 2) * 80;
                                Image img = new ImageIcon(this.getClass().getResource("Images/listen.jpg")).getImage();
                                labelSong[j] = new JLabel();
                                labelSong[j].setIcon(new ImageIcon(img));
                                labelSong[j].setBounds(x + 30, (int) y + 15, 500, 50);
                                labelSongg[j] = new JLabel();
                                labelSongg[j].setBounds(x + 30, (int) y + 45, 100, 50);
                                labelSongg[j].setText(resultSet.getString("SongName"));
                                labelSongg[j].setForeground(Color.PINK);
                                labelSongg[j].setFont(new Font(Font.SERIF, Font.PLAIN, 12));
                                labelSonggg[j] = new JLabel();
                                labelSonggg[j].setBounds(x + 130 + 30, (int) y + 45, 100, 50);
                                labelSonggg[j].setText("Kaydet");
                                labelSonggg[j].setForeground(Color.white);
                                labelSonggg[j].setFont(new Font(Font.SERIF, Font.PLAIN, 12));
                                labelSongggg[j] = new JLabel();
                                labelSongggg[j].setBounds(x + 130 + 130 + 30, (int) y + 45, 100, 50);
                                labelSongggg[j].setText("Dinle");
                                labelSongggg[j].setForeground(Color.white);
                                labelSongggg[j].setFont(new Font(Font.SERIF, Font.PLAIN, 12));
                                labelSonggggg[j] = new JLabel();
                                labelSonggggg[j].setBounds(x + 130 + 130 + 130 + 30, (int) y + 45, 100, 50);
                                labelSonggggg[j].setText("Detay");
                                labelSonggggg[j].setForeground(Color.white);
                                labelSonggggg[j].setFont(new Font(Font.SERIF, Font.PLAIN, 12));
                                girisAltAltPanel.add(labelSongg[j]);
                                girisAltAltPanel.add(labelSonggg[j]);
                                girisAltAltPanel.add(labelSongggg[j]);
                                girisAltAltPanel.add(labelSonggggg[j]);
                                girisAltAltPanel.add(labelSong[j]);
                                girisAltAltPanel.repaint();
                                labelSonggg[j].addMouseListener(new MouseAdapter() {
                                    int index = j;
                                    String kindName = "";
                                    int count = 0;
                                    int playListID;
                                    
                                    public void mouseClicked(MouseEvent e) {
                                        try {
                                            connection = DatabaseConnection.connect();
                                            query = "select k.KindName from kinds k,song_kinds sk,songs s"
                                                    + " where sk.KindID=k.KindID and s.SongID=sk.SongID and"
                                                    + " s.SongName='" + labelSongg[index].getText() + "'";
                                            
                                            statement = connection.createStatement();
                                            resultSet = statement.executeQuery(query);
                                            while (resultSet.next()) {
                                                kindName = "";
                                                kindName = kindName.concat(resultSet.getString(1));
                                                System.out.println(kindName);
                                            }
                                            query = "select PlayListID from play_lists "
                                                    + " where UserID='" + LoginScreen.id + "'"
                                                    + " and PlayListName='" + kindName + "'";
                                            
                                            statement = connection.createStatement();
                                            resultSet = statement.executeQuery(query);
                                            System.out.println(kindName);
                                            while (resultSet.next()) {
                                                playListID = resultSet.getInt(1);
                                            }
                                            query = "select pls.SongID from play_list_songs pls,songs s,play_lists pl"
                                                    + " where pls.SongID=s.SongID and pls.PlayListID=pl.PlayListID"
                                                    + " and pl.UserID='" + LoginScreen.id + "'"
                                                    + " and pl.PlayListName='" + kindName + "'"
                                                    + " and s.SongName='" + labelSongg[index].getText() + "'";
                                            statement = connection.createStatement();
                                            resultSet = statement.executeQuery(query);
                                            while (resultSet.next()) {
                                                count++;
                                            }
                                            if (count == 0) {
                                                
                                                query = " insert into play_list_songs (SongID,PlayListID)"
                                                        + " values (?,?)";
                                                preparedStatement = connection.prepareStatement(query);
                                                preparedStatement.setInt(1, songId[index]);
                                                preparedStatement.setInt(2, playListID);
                                                preparedStatement.execute();
                                                connection.close();
                                            } else {
                                                JOptionPane.showMessageDialog(girisAltAltPanel, "You are already add this song in your playlist");
                                            }
                                            
                                        } catch (Exception ex) {
                                            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                });
                                labelSonggggg[j].addMouseListener(new MouseAdapter() {
                                    int index2 = j;
                                    String singersName = "";
                                    String kindName = "";
                                    String albumName = "";
                                    
                                    public void mouseClicked(MouseEvent e) {
                                        try {
                                            connection = DatabaseConnection.connect();
                                            query = "select si.SingerName from singers si,singer_songs ss, songs so where"
                                                    + " si.SingerID=ss.SingerID and ss.SongID=so.SongID and"
                                                    + " so.SongName='" + labelSongg[index2].getText() + "'";
                                            
                                            statement = connection.createStatement();
                                            resultSet = statement.executeQuery(query);
                                            while (resultSet.next()) {
                                                singersName = singersName.concat(resultSet.getString(1) + " , ");
                                            }
                                            connection = DatabaseConnection.connect();
                                            query = "select ki.KindName from songs so,kinds ki,song_kinds sk "
                                                    + " where ki.KindID=sk.KindID and sk.SongID=so.SongID "
                                                    + " and so.SongName='" + labelSongg[index2].getText() + "'";
                                            statement = connection.createStatement();
                                            resultSet = statement.executeQuery(query);
                                            while (resultSet.next()) {
                                                kindName = resultSet.getString(1);
                                                
                                            }
                                            connection = DatabaseConnection.connect();
                                            query = "select a.AlbumName from songs so,albums a,song_albums sa"
                                                    + " where a.AlbumID=sa.AlbumID and sa.SongID=so.SongID"
                                                    + " and so.SongName='" + labelSongg[index2].getText() + "'";
                                            statement = connection.createStatement();
                                            resultSet = statement.executeQuery(query);
                                            while (resultSet.next()) {
                                                
                                                albumName = resultSet.getString(1);
                                                
                                            }
                                            
                                            JOptionPane.showMessageDialog(girisAltAltPanel, "This songs singers:" + singersName + " \n and song's kind:" + kindName
                                                    + "\n and album name:" + albumName);
                                            singersName = "";
                                        } catch (Exception ex) {
                                            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                    
                                });
                                
                                labelSongggg[j].addMouseListener(new MouseAdapter() {
                                    int index2 = j;
                                    int listen;
                                    
                                    public void mouseClicked(MouseEvent e) {
                                        int songIDforUser = 0;
                                        try {
                                            connection = DatabaseConnection.connect();
                                            query = "select ViewCount,SongID from songs where SongName='" + labelSongg[index2].getText() + "'";
                                            statement = connection.createStatement();
                                            resultSet = statement.executeQuery(query);
                                            
                                            while (resultSet.next()) {
                                                listen = resultSet.getInt(1);
                                                songIDforUser = resultSet.getInt(2);
                                            }
                                            query = "update songs set ViewCount=?  where SongName='" + labelSongg[index2].getText() + "'";
                                            preparedStatement = connection.prepareStatement(query);
                                            preparedStatement.setInt(1, (listen + 1));
                                            preparedStatement.executeUpdate();
                                            preparedStatement.close();
                                            connection.close();
                                            JOptionPane.showMessageDialog(albumlerAltPanel, "The song is resting");
                                            connection.close();
                                            connection = DatabaseConnection.connect();
                                            query = "select so.SongName,uls.listeningCount from songs so,user_listening_song uls,users u"
                                                    + " where so.SongID=uls.SongID and uls.UserID=u.UserID and so.SongName='" + labelSongg[index2].getText() + "'"
                                                    + " and u.UserName='" + LoginScreen.name + "'";
                                            statement = connection.createStatement();
                                            resultSet = statement.executeQuery(query);
                                            int count = 0;
                                            int listenUser = 0;
                                            while (resultSet.next()) {
                                                count++;
                                                listenUser = resultSet.getInt("ListeningCount");
                                                
                                            }
                                            if (count == 0) {
                                                query = " insert into user_listening_song (UserID,SongID,ListeningCount)"
                                                        + " values (?, ?,?)";
                                                preparedStatement = connection.prepareStatement(query);
                                                preparedStatement.setInt(1, LoginScreen.id);
                                                preparedStatement.setInt(2, songIDforUser);
                                                preparedStatement.setInt(3, 1);
                                                preparedStatement.execute();
                                                connection.close();
                                            } else {
                                                query = "update user_listening_song set ListeningCount=?  where UserID='" + LoginScreen.id + "'"
                                                        + "  and SongID='" + songIDforUser + "'";
                                                preparedStatement = connection.prepareStatement(query);
                                                preparedStatement.setInt(1, (listenUser + 1));
                                                preparedStatement.executeUpdate();
                                                preparedStatement.close();
                                                connection.close();
                                            }
                                            
                                        } catch (Exception ex) {
                                            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                    
                                });
                                j++;
                            }
                            
                        } catch (Exception ex) {
                            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                    }
                });
                i++;
                
            }
            connection.close();
        } catch (Exception ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_ulkeLabelMouseClicked
//tüm müzikler
    private void muziklerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_muziklerMouseClicked
        muzikTurleriPanel5.setVisible(false);
        albumlerPanel5.setVisible(false);
        sanatcilerPanel5.setVisible(false);
        listelerPanel5.setVisible(false);
        albumlerAltPanel.removeAll();
        albumlerAltPanel.setVisible(false);
        sanatcilarAltPanel.setVisible(false);
        listelerAltPanel.setVisible(false);
        muzikTurleriAltPanel.setVisible(false);
        try {
            muziklerAltPanel.setVisible(true);
            model = (DefaultTableModel) tableGenel.getModel();
            model.setRowCount(0);
            showInTableGenel();
        } catch (Exception ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_muziklerMouseClicked
//dinleme sayısını 1 artırıyor
    private void tableGenelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableGenelMouseClicked
        int index = tableGenel.getSelectedRow();
        model = (DefaultTableModel) tableGenel.getModel();
        int listen = 0;
        try {
            connection = DatabaseConnection.connect();
            query = "select ViewCount from songs where SongID='" + Integer.parseInt(model.getValueAt(index, 0).toString()) + "'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            
            while (resultSet.next()) {
                listen = resultSet.getInt(1);
                
            }
            query = "update songs set ViewCount=?  where SongID='" + Integer.parseInt(model.getValueAt(index, 0).toString()) + "'";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, (listen + 1));
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            JOptionPane.showMessageDialog(albumlerAltPanel, "The song is resting");
            
            model = (DefaultTableModel) tableGenel.getModel();
            model.setRowCount(0);
            try {
                showInTableGenel();
            } catch (Exception ex) {
                Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
            }
            connection = DatabaseConnection.connect();
            query = "select uls.listeningCount from songs so,user_listening_song uls,users u"
                    + " where so.SongID=uls.SongID and uls.UserID=u.UserID and so.SongID='" + Integer.parseInt(model.getValueAt(index, 0).toString()) + "'"
                    + " and u.UserName='" + LoginScreen.name + "'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            int count = 0;
            int listenUser = 0;
            while (resultSet.next()) {
                count++;
                listenUser = resultSet.getInt("ListeningCount");
                
            }
            if (count == 0) {
                query = " insert into user_listening_song (UserID,SongID,ListeningCount)"
                        + " values (?, ?,?)";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, LoginScreen.id);
                preparedStatement.setInt(2, Integer.parseInt(model.getValueAt(index, 0).toString()));
                preparedStatement.setInt(3, 1);
                preparedStatement.execute();
                connection.close();
            } else {
                query = "update user_listening_song set ListeningCount=?  where UserID='" + LoginScreen.id + "'"
                        + "  and SongID='" + Integer.parseInt(model.getValueAt(index, 0).toString()) + "'";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, (listenUser + 1));
                preparedStatement.executeUpdate();
                preparedStatement.close();
                connection.close();
            }
            connection.close();
        } catch (Exception ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }//GEN-LAST:event_tableGenelMouseClicked
//dinleme sayısını 1 artırıyor
    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        int index = table.getSelectedRow();
        model = (DefaultTableModel) table.getModel();
        int listen = 0;
        try {
            connection = DatabaseConnection.connect();
            query = "select ViewCount from songs where SongID='" + Integer.parseInt(model.getValueAt(index, 0).toString()) + "'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            
            while (resultSet.next()) {
                listen = resultSet.getInt(1);
                
            }
            query = "update songs set ViewCount=?  where SongID='" + Integer.parseInt(model.getValueAt(index, 0).toString()) + "'";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, (listen + 1));
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            JOptionPane.showMessageDialog(albumlerAltPanel, "The song is resting");
            connection = DatabaseConnection.connect();
            
            query = "update user_listening_song set ListeningCount=?  where UserID='" + LoginScreen.id + "'"
                    + "  and SongID='" + Integer.parseInt(model.getValueAt(index, 0).toString()) + "'";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, (Integer.parseInt(model.getValueAt(index, 5).toString()) + 1));
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            
            model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);
            try {
                showInTable(kind);
            } catch (Exception ex) {
                Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
            }
            connection.close();
        } catch (Exception ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tableMouseClicked
//single şarkıları listeliyor
    private void singleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_singleMouseClicked
        albumlerPanel.setVisible(false);
        girisPanel.setVisible(false);
        sanatcilerPanel.setVisible(false);
        muzikTurleriPanel5.setVisible(false);
        albumlerPanel5.setVisible(false);
        sanatcilerPanel5.setVisible(false);
        listelerPanel5.setVisible(false);
        muzikTurleriAltPanel.setVisible(false);
        albumlerAltPanel.setVisible(false);
        sanatcilarAltPanel.setVisible(false);
        listelerAltPanel.setVisible(false);
        girisAltPanel.setVisible(false);
        muziklerAltPanel.setVisible(false);
        listelerAltAltPanel.setVisible(false);
        singlePanel.setVisible(true);
        model = (DefaultTableModel) singleTable.getModel();
        model.setRowCount(0);
        try {
            showInTableSingleSong();
        } catch (Exception ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_singleMouseClicked
//tursuz şarkıları listeliyor
    private void tursuzMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tursuzMouseClicked
        albumlerPanel.setVisible(false);
        girisPanel.setVisible(false);
        sanatcilerPanel.setVisible(false);
        muzikTurleriPanel5.setVisible(false);
        albumlerPanel5.setVisible(false);
        sanatcilerPanel5.setVisible(false);
        listelerPanel5.setVisible(false);
        muzikTurleriAltPanel.setVisible(false);
        albumlerAltPanel.setVisible(false);
        sanatcilarAltPanel.setVisible(false);
        listelerAltPanel.setVisible(false);
        girisAltPanel.setVisible(false);
        muziklerAltPanel.setVisible(false);
        listelerAltAltPanel.setVisible(false);
        singlePanel.setVisible(false);
        tursuzPanel.setVisible(true);
        model = (DefaultTableModel) tursuzTable.getModel();
        model.setRowCount(0);
        try {
            showInTableTursuzSong();
        } catch (Exception ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }//GEN-LAST:event_tursuzMouseClicked
//takip edilen playlistler+şarkıları
    private void followedListLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_followedListLabelMouseClicked
        followedListPanel.removeAll();
        followedListAltPanel.removeAll();
        close();
        model = (DefaultTableModel) followedListTable.getModel();
        model.setRowCount(0);
        followedList_UserPanel.setVisible(true);
        followedListPanel.setVisible(true);
        followedListAltPanel.setVisible(false);
        followedUserPanel.setVisible(false);
        followedUserAltPanel.setVisible(false);
        try {
            connection = DatabaseConnection.connect();
            query = "select pl.PlayListName,u.UserName,fpl.PlayListID from users u,premiumaccount pa,followed_play_lists fpl,play_lists pl"
                    + "  where u.UserID=pa.UserID and pa.PremiumAccountID=fpl.PremiumAccountId"
                    + " and pl.PlayListID=fpl.PlayListID"
                    + " and fpl.UserID='" + LoginScreen.id + "'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            JLabel[] label = new JLabel[50];
            JLabel[] labell = new JLabel[50];
            JLabel[] labelll = new JLabel[50];
            JLabel[] labellll = new JLabel[50];
            int i = 0;
            while (resultSet.next()) {
                int x = (i % 6) * 170;
                double y = Math.floor(i / 6) * 170;
                System.out.println(i);
                Image img = new ImageIcon(this.getClass().getResource("Images/imagekind.jpg")).getImage();
                label[i] = new JLabel();
                label[i].setIcon(new ImageIcon(img));
                label[i].setBounds(x + 5, (int) y, 160, 160);
                labell[i] = new JLabel();
                labell[i].setBounds(x + 5, (int) y + 140, 60, 50);
                labell[i].setText(resultSet.getString("UserName"));
                labell[i].setForeground(Color.white);
                labell[i].setFont(new Font(Font.SERIF, Font.PLAIN, 15));
                labellll[i] = new JLabel();
                labellll[i].setBounds(x + 5 + 60, (int) y + 140, 80, 50);
                labellll[i].setText(resultSet.getString("PlayListName"));
                labellll[i].setForeground(Color.white);
                labellll[i].setFont(new Font(Font.SERIF, Font.PLAIN, 15));
                labelll[i] = new JLabel();
                labelll[i].setBounds(x + 5 + 130, (int) y + 140, 80, 50);
                labelll[i].setText("Sil");
                labelll[i].setForeground(Color.white);
                labelll[i].setFont(new Font(Font.SERIF, Font.PLAIN, 15));
                followedListPanel.add(label[i]);
                followedListPanel.add(labell[i]);
                followedListPanel.add(labelll[i]);
                followedListPanel.add(labellll[i]);
                followedListPanel.repaint();
                label[i].addMouseListener(new MouseAdapter() {
                    int index = i;
                    
                    public void mouseClicked(MouseEvent e) {
                        followedListPanel.removeAll();
                        followedListPanel.setVisible(false);
                        followedListAltPanel.setVisible(true);
                        model = (DefaultTableModel) followedListTable.getModel();
                        model.setRowCount(0);
                        try {
                            listUserNameee = labell[index].getText();
                            listNameee = labellll[index].getText();
                            showInTableListelerFoll(listUserNameee, listNameee);
                        } catch (Exception ex) {
                            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                labelll[i].addMouseListener(new MouseAdapter() {
                    int index = i;
                    
                    public void mouseClicked(MouseEvent e) {
                        try {
                            int idSil = 0;
                            connection = DatabaseConnection.connect();
                            query = "select pl.PlayListID from play_lists pl,users u"
                                    + " where u.UserName='" + labell[index].getText() + "'"
                                    + " and pl.UserID=u.UserID"
                                    + " and pl.PlayListName='" + labellll[index].getText() + "'";
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            while (resultSet.next()) {
                                idSil = resultSet.getInt("PlayListID");
                            }
                            connection = DatabaseConnection.connect();
                            query = "DELETE FROM followed_play_lists WHERE PlayListID='" + idSil + "'"
                                    + " and UserID='" + LoginScreen.id + "'";
                            statement = connection.createStatement();
                            statement.executeUpdate(query);
                            connection.close();
                            followedListPanel.remove(label[index]);
                            followedListPanel.remove(labell[index]);
                            followedListPanel.remove(labelll[index]);
                            followedListPanel.remove(labellll[index]);
                            followedListPanel.repaint();
                            
                        } catch (Exception ex) {
                            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                i++;
            }
            connection.close();
        } catch (Exception ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }//GEN-LAST:event_followedListLabelMouseClicked
//takip edilen kullanıcılar+playlistler+şarkıları
    private void followedUserLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_followedUserLabelMouseClicked
        close();
        followedUserPanel.removeAll();
        followedUserAltPanel.removeAll();
        followedList_UserPanel.setVisible(true);
        followedListPanel.setVisible(false);
        followedListAltPanel.setVisible(false);
        followedUserPanel.setVisible(true);
        followedUserAltPanel.setVisible(false);
        model = (DefaultTableModel) followedUserListTable.getModel();
        model.setRowCount(0);
        int i = 0;
        try {
            connection = DatabaseConnection.connect();
            query = "select u.UserName,fu.PremiumAccountID from followed_users fu,users u,premiumaccount pa"
                    + "  where pa.PremiumAccountID=fu.PremiumAccountID and pa.UserID=u.UserID"
                    + " and fu.UserID='" + LoginScreen.id + "'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            JLabel[] label = new JLabel[50];
            JLabel[] labell = new JLabel[50];
            JLabel[] labelll = new JLabel[50];
            String[] isimler = new String[50];
            int[] premiumAccountIDtrue = new int[50];
            while (resultSet.next()) {
                premiumAccountIDtrue[i] = resultSet.getInt("PremiumAccountID");
                int x = (i % 6) * 170;
                double y = Math.floor(i / 6) * 170;
                //System.out.println(i);
                Image img = new ImageIcon(this.getClass().getResource("Images/imagePeople.png")).getImage();
                label[i] = new JLabel();
                label[i].setIcon(new ImageIcon(img));
                label[i].setBounds(x + 5, (int) y, 160, 160);
                labell[i] = new JLabel();
                labell[i].setBounds(x + 5, (int) y + 140, 100, 50);
                labell[i].setText(resultSet.getString("UserName"));
                isimler[i] = "";
                isimler[i] = isimler[i].concat(resultSet.getString("UserName"));
                // System.out.println(resultSet.getString("UserName"));
                labell[i].setForeground(Color.white);
                labell[i].setFont(new Font(Font.SERIF, Font.PLAIN, 15));
                labelll[i] = new JLabel();
                labelll[i].setBounds(x + 5 + 110, (int) y + 140, 80, 50);
                labelll[i].setText("Sil");
                labelll[i].setForeground(Color.white);
                labelll[i].setFont(new Font(Font.SERIF, Font.PLAIN, 15));
                followedUserPanel.add(label[i]);
                followedUserPanel.add(labell[i]);
                followedUserPanel.add(labelll[i]);
                followedUserPanel.repaint();
                labelll[i].addMouseListener(new MouseAdapter() {
                    int index = i;
                    
                    public void mouseClicked(MouseEvent e) {
                        try {
                            int idSil = 0;
                            connection = DatabaseConnection.connect();
                            query = "select p.PremiumAccountID from premiumaccount p,users u"
                                    + " where u.UserName='" + labell[index].getText() + "'"
                                    + "and u.UserID=p.UserID ";
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            while (resultSet.next()) {
                                idSil = resultSet.getInt("PremiumAccountID");
                            }
                            connection = DatabaseConnection.connect();
                            query = "DELETE FROM followed_users WHERE PremiumAccountID='" + idSil + "'"
                                    + " and UserID='" + LoginScreen.id + "'";
                            statement = connection.createStatement();
                            statement.executeUpdate(query);
                            connection.close();
                            followedUserPanel.remove(label[index]);
                            followedUserPanel.remove(labell[index]);
                            followedUserPanel.remove(labelll[index]);
                            followedUserPanel.repaint();
                        } catch (Exception ex) {
                            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                label[i].addMouseListener(new MouseAdapter() {
                    int index = i;
                    
                    public void mouseClicked(MouseEvent e) {
                        //System.out.println(index);
                        model = (DefaultTableModel) followedUserListTable.getModel();
                        model.setRowCount(0);
                        try {
                            try {
                                
                                followedUserPanel.setVisible(false);
                                followedUserAltPanel.setVisible(true);
                                connection = DatabaseConnection.connect();
                                query = "SELECT  p.PlayListName from users u ,play_lists p where u.UserID=p.UserID"
                                        + " and u.UserName='" + labell[index].getText() + "'";
                                System.out.println(isimler[index]);
                                listUserNameeee = labell[index].getText();
                                statement = connection.createStatement();
                                resultSet = statement.executeQuery(query);
                                JLabel[] labelList = new JLabel[20];
                                JLabel[] labelListt = new JLabel[20];
                                int j = 0;
                                while (resultSet.next()) {
                                    int x = (j % 7) * 160;
                                    double y = Math.floor(j / 7) * 160;
                                    Image img = new ImageIcon(this.getClass().getResource("Images/imagekind.jpg")).getImage();
                                    labelList[j] = new JLabel();
                                    labelList[j].setIcon(new ImageIcon(img));
                                    labelList[j].setBounds(x + 15, (int) y, 150, 150);
                                    labelListt[j] = new JLabel();
                                    labelListt[j].setBounds(x + 10, (int) y + 130, 70, 50);
                                    labelListt[j].setText(resultSet.getString(1));
                                    labelListt[j].setForeground(Color.PINK);
                                    labelListt[j].setFont(new Font(Font.SERIF, Font.PLAIN, 16));
                                    followedUserAltPanel.add(labelList[j]);
                                    followedUserAltPanel.add(labelListt[j]);
                                    followedUserAltPanel.repaint();
                                    labelList[j].addMouseListener(new MouseAdapter() {
                                        int indexx = j;
                                        
                                        public void mouseClicked(MouseEvent e) {
                                            followedUserAltPanel.setVisible(false);
                                            followedListAltPanel.setVisible(false);
                                            followedUserALtAltPanel.setVisible(true);
                                            model = (DefaultTableModel) followedUserListTable.getModel();
                                            model.setRowCount(0);
                                            try {
                                                
                                                listNameeee = labelListt[indexx].getText();
                                                //System.out.println(listUserNameeee);
                                                //System.out.println(isimler[index]);
                                                // System.out.println(index);
                                                showInTableListelerUserFoll(listUserNameeee, listNameeee);
                                            } catch (Exception ex) {
                                                Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                            
                                        }
                                    });
                                    
                                    j++;
                                }
                            } catch (Exception ex) {
                                Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                        } catch (Exception ex) {
                            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    
                }
                );
                
                i++;
            }
            connection.close();
        } catch (Exception ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_followedUserLabelMouseClicked
//dinleme sayısını 1 arttırıyor
    private void tableListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableListMouseClicked
        int index = tableList.getSelectedRow();
        model = (DefaultTableModel) tableList.getModel();
        int listen = 0;
        try {
            connection = DatabaseConnection.connect();
            query = "select ViewCount from songs where SongID='" + Integer.parseInt(model.getValueAt(index, 4).toString()) + "'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            
            while (resultSet.next()) {
                listen = resultSet.getInt(1);
                
            }
            query = "update songs set ViewCount=?  where SongID='" + Integer.parseInt(model.getValueAt(index, 4).toString()) + "'";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, (listen + 1));
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            JOptionPane.showMessageDialog(listelerAltAltPanel, "The song is resting");
            
            model = (DefaultTableModel) tableList.getModel();
            model.setRowCount(0);
            try {
                showInTableListeler(listUserNamee, listNamee);
            } catch (Exception ex) {
                Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
            }
            connection = DatabaseConnection.connect();
            query = "select uls.listeningCount from songs so,user_listening_song uls,users u"
                    + " where so.SongID=uls.SongID and uls.UserID=u.UserID and so.SongID='" + Integer.parseInt(model.getValueAt(index, 4).toString()) + "'"
                    + " and u.UserName='" + LoginScreen.name + "'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            int count = 0;
            int listenUser = 0;
            while (resultSet.next()) {
                count++;
                listenUser = resultSet.getInt("ListeningCount");
                
            }
            if (count == 0) {
                query = " insert into user_listening_song (UserID,SongID,ListeningCount)"
                        + " values (?, ?,?)";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, LoginScreen.id);
                preparedStatement.setInt(2, Integer.parseInt(model.getValueAt(index, 4).toString()));
                preparedStatement.setInt(3, 1);
                preparedStatement.execute();
                connection.close();
            } else {
                query = "update user_listening_song set ListeningCount=?  where UserID='" + LoginScreen.id + "'"
                        + "  and SongID='" + Integer.parseInt(model.getValueAt(index, 4).toString()) + "'";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, (listenUser + 1));
                preparedStatement.executeUpdate();
                preparedStatement.close();
                connection.close();
            }
            connection.close();
        } catch (Exception ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_tableListMouseClicked
//dinleme sayısını 1 arttırıyor
    private void followedListTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_followedListTableMouseClicked
        int index = followedListTable.getSelectedRow();
        model = (DefaultTableModel) followedListTable.getModel();
        int listen = 0;
        try {
            connection = DatabaseConnection.connect();
            query = "select ViewCount from songs where SongID='" + Integer.parseInt(model.getValueAt(index, 4).toString()) + "'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            
            while (resultSet.next()) {
                listen = resultSet.getInt(1);
                
            }
            query = "update songs set ViewCount=?  where SongID='" + Integer.parseInt(model.getValueAt(index, 4).toString()) + "'";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, (listen + 1));
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            JOptionPane.showMessageDialog(followedListAltPanel, "The song is resting");
            
            model = (DefaultTableModel) followedListTable.getModel();
            model.setRowCount(0);
            try {
                showInTableListelerFoll(listUserNameee, listNameee);
            } catch (Exception ex) {
                Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
            }
            connection = DatabaseConnection.connect();
            query = "select uls.listeningCount from songs so,user_listening_song uls,users u"
                    + " where so.SongID=uls.SongID and uls.UserID=u.UserID and so.SongID='" + Integer.parseInt(model.getValueAt(index, 4).toString()) + "'"
                    + " and u.UserName='" + LoginScreen.name + "'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            int count = 0;
            int listenUser = 0;
            while (resultSet.next()) {
                count++;
                listenUser = resultSet.getInt("ListeningCount");
                
            }
            if (count == 0) {
                query = " insert into user_listening_song (UserID,SongID,ListeningCount)"
                        + " values (?, ?,?)";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, LoginScreen.id);
                preparedStatement.setInt(2, Integer.parseInt(model.getValueAt(index, 4).toString()));
                preparedStatement.setInt(3, 1);
                preparedStatement.execute();
                connection.close();
            } else {
                query = "update user_listening_song set ListeningCount=?  where UserID='" + LoginScreen.id + "'"
                        + "  and SongID='" + Integer.parseInt(model.getValueAt(index, 4).toString()) + "'";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, (listenUser + 1));
                preparedStatement.executeUpdate();
                preparedStatement.close();
                connection.close();
            }
            connection.close();
        } catch (Exception ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_followedListTableMouseClicked
//dinleme sayısını 1 arttırıyor
    private void followedUserListTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_followedUserListTableMouseClicked
        int index = followedUserListTable.getSelectedRow();
        model = (DefaultTableModel) followedUserListTable.getModel();
        int listen = 0;
        try {
            connection = DatabaseConnection.connect();
            query = "select ViewCount from songs where SongID='" + Integer.parseInt(model.getValueAt(index, 4).toString()) + "'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            
            while (resultSet.next()) {
                listen = resultSet.getInt(1);
                
            }
            query = "update songs set ViewCount=?  where SongID='" + Integer.parseInt(model.getValueAt(index, 4).toString()) + "'";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, (listen + 1));
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            JOptionPane.showMessageDialog(followedListAltPanel, "The song is resting");
            
            model = (DefaultTableModel) followedUserListTable.getModel();
            model.setRowCount(0);
            try {
                showInTableListelerUserFoll(listUserNameeee, listNameeee);
            } catch (Exception ex) {
                Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
            }
            connection = DatabaseConnection.connect();
            query = "select uls.listeningCount from songs so,user_listening_song uls,users u"
                    + " where so.SongID=uls.SongID and uls.UserID=u.UserID and so.SongID='" + Integer.parseInt(model.getValueAt(index, 4).toString()) + "'"
                    + " and u.UserName='" + LoginScreen.name + "'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            int count = 0;
            int listenUser = 0;
            while (resultSet.next()) {
                count++;
                listenUser = resultSet.getInt("ListeningCount");
                
            }
            if (count == 0) {
                query = " insert into user_listening_song (UserID,SongID,ListeningCount)"
                        + " values (?, ?,?)";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, LoginScreen.id);
                preparedStatement.setInt(2, Integer.parseInt(model.getValueAt(index, 4).toString()));
                preparedStatement.setInt(3, 1);
                preparedStatement.execute();
                connection.close();
            } else {
                query = "update user_listening_song set ListeningCount=?  where UserID='" + LoginScreen.id + "'"
                        + "  and SongID='" + Integer.parseInt(model.getValueAt(index, 4).toString()) + "'";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, (listenUser + 1));
                preparedStatement.executeUpdate();
                preparedStatement.close();
                connection.close();
            }
            connection.close();
        } catch (Exception ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_followedUserListTableMouseClicked

    private void listAltAltAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listAltAltAddMouseClicked
        try {
            int count = 0;
            connection = DatabaseConnection.connect();
            query = "select s.SongID from songs s,play_lists pl,play_list_songs pls,kinds ki,song_kinds sk"
                    + " where s.SongName='" + addedSongName.getText() + "'"
                    + " and s.SongID=sk.SongID"
                    + " and sk.KindID=ki.KindID"
                    + " and ki.KindName=pl.PlayListName"
                    + " and pl.UserID='" + LoginScreen.id + "'"
                    + " and pl.PlayListID=pls.PlayListID"
                    + " and pls.SongID=s.SongID";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                JOptionPane.showMessageDialog(listelerAltAltPanel, "This song already exis in your list");
                count++;
            }
            if (count == 0) {
                connection = DatabaseConnection.connect();
                query = "select  pl.PlayListID ,s.SongID from songs s,play_lists pl,kinds ki,song_kinds sk"
                        + " where s.SongID=sk.SongID"
                        + " and s.SongName='" + addedSongName.getText() + "'"
                        + " and sk.KindID=ki.KindID"
                        + " and ki.KindName=pl.PlayListName"
                        + " and pl.UserID='" + LoginScreen.id + "'";
                statement = connection.createStatement();
                resultSet = statement.executeQuery(query);
                int id1 = 0, id2 = 0;
                while (resultSet.next()) {
                    id1 = resultSet.getInt(1);
                    id2 = resultSet.getInt(2);
                }
                connection = DatabaseConnection.connect();
                query = " insert into play_list_songs (SongID,PlayListID)"
                        + " values (?,?)";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, id2);
                preparedStatement.setInt(2, id1);
                preparedStatement.execute();
                connection.close();
            }
            connection.close();
        } catch (Exception ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_listAltAltAddMouseClicked

    private void muziklerAltaddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_muziklerAltaddMouseClicked
        
        try {
            int count = 0;
            connection = DatabaseConnection.connect();
            query = "select s.SongID from songs s,play_lists pl,play_list_songs pls,kinds ki,song_kinds sk"
                    + " where s.SongName='" + muziklerAltSongAdd.getText() + "'"
                    + " and s.SongID=sk.SongID"
                    + " and sk.KindID=ki.KindID"
                    + " and ki.KindName=pl.PlayListName"
                    + " and pl.UserID='" + LoginScreen.id + "'"
                    + " and pl.PlayListID=pls.PlayListID"
                    + " and pls.SongID=s.SongID";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                JOptionPane.showMessageDialog(muziklerAltPanel, "This song already exis in your list");
                count++;
            }
            if (count == 0) {
                connection = DatabaseConnection.connect();
                query = "select  pl.PlayListID ,s.SongID from songs s,play_lists pl,kinds ki,song_kinds sk"
                        + " where s.SongID=sk.SongID"
                        + " and s.SongName='" + muziklerAltSongAdd.getText() + "'"
                        + " and sk.KindID=ki.KindID"
                        + " and ki.KindName=pl.PlayListName"
                        + " and pl.UserID='" + LoginScreen.id + "'";
                statement = connection.createStatement();
                resultSet = statement.executeQuery(query);
                int id1 = 0, id2 = 0;
                while (resultSet.next()) {
                    id1 = resultSet.getInt(1);
                    id2 = resultSet.getInt(2);
                }
                connection = DatabaseConnection.connect();
                query = " insert into play_list_songs (SongID,PlayListID)"
                        + " values (?,?)";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, id2);
                preparedStatement.setInt(2, id1);
                preparedStatement.execute();
                connection.close();
            }
            connection.close();
        } catch (Exception ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_muziklerAltaddMouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        
        girisAltPanel.removeAll();
        albumlerAltPanel.removeAll();
        followedAlbumAltPanel.removeAll();
        followedAlbumPanel.removeAll();
        followedSingerPanel.removeAll();
        followedSingerAltPanel.removeAll();
        myListPanel.setVisible(false);
        myListAltPanel.setVisible(false);
        sanatcilerPanel.setVisible(false);
        newListPanel.setVisible(true);
    }//GEN-LAST:event_jLabel1MouseClicked
//yeni playlist 
    private void newListAddLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_newListAddLabelMouseClicked
        try {
            int count = 0;
            
            connection = DatabaseConnection.connect();
            query = "select * from play_lists where UserID='" + LoginScreen.id + "'"
                    + " and PlayListName='" + newListLabel.getText() + "'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                count++;
            }
            connection.close();
            if (count == 0) {
                
                connection = DatabaseConnection.connect();
                query = " insert into play_lists(PlayListName,UserID)"
                        + " values (?,?)";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, newListLabel.getText());
                preparedStatement.setInt(2, LoginScreen.id);
                preparedStatement.execute();
                connection.close();
                JOptionPane.showMessageDialog(newListPanel, "Succesfully added");
            } else {
                JOptionPane.showMessageDialog(newListPanel, "This list already exist");
            }
        } catch (Exception ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_newListAddLabelMouseClicked

    private void muziklerAltaddOptionalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_muziklerAltaddOptionalMouseClicked
        try {
            int count = 0;
            int id1 = 0, id2 = 0;
            connection = DatabaseConnection.connect();
            statement = connection.createStatement();
            query = "select pl.PlayListID from play_lists pl"
                    + " where pl.UserID='" + LoginScreen.id + "'"
                    + "   and pl.PlayListName='" + muzikleraltaltOptionalList.getText() + "'";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                id2 = resultSet.getInt("PlayListID");
                count++;
            }
            if (count != 0) {
                int count2 = 0;
                connection = DatabaseConnection.connect();
                statement = connection.createStatement();
                query = "select * from play_lists pl,play_list_songs pls,songs so"
                        + " where pl.UserID='" + LoginScreen.id + "'"
                        + "   and pl.PlayListName='" + muzikleraltaltOptionalList.getText() + "'"
                        + "   and pls.PlayListID=pl.PlayListID"
                        + "   and so.SongName='" + muziklerAltSongAdd.getText() + "'"
                        + "   and so.SongID=pls.SongID";
                resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    count2++;
                }
                if (count2 == 0) {
                    connection = DatabaseConnection.connect();
                    query = "select SongID from songs where SongName='" + muziklerAltSongAdd.getText() + "'";
                    statement = connection.createStatement();
                    resultSet = statement.executeQuery(query);
                    
                    while (resultSet.next()) {
                        id1 = resultSet.getInt(1);
                    }
                    connection = DatabaseConnection.connect();
                    query = " insert into play_list_songs (SongID,PlayListID)"
                            + " values (?,?)";
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setInt(1, id1);
                    preparedStatement.setInt(2, id2);
                    preparedStatement.execute();
                    connection.close();
                } else {
                    JOptionPane.showMessageDialog(muziklerAltPanel, "This song already exis in your list");
                }
                
            } else {
                JOptionPane.showMessageDialog(muziklerAltPanel, "This list not exis");
            }
            connection.close();
        } catch (Exception ex) {
            Logger.getLogger(HomePage.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_muziklerAltaddOptionalMouseClicked

    private void listAltAltAddOptionalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listAltAltAddOptionalMouseClicked
        try {
            int count = 0;
            int id1 = 0, id2 = 0;
            connection = DatabaseConnection.connect();
            statement = connection.createStatement();
            query = "select pl.PlayListID from play_lists pl"
                    + " where pl.UserID='" + LoginScreen.id + "'"
                    + "   and pl.PlayListName='" + addedListNameOPtional.getText() + "'";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                id2 = resultSet.getInt("PlayListID");
                count++;
            }
            if (count != 0) {
                int count2 = 0;
                connection = DatabaseConnection.connect();
                statement = connection.createStatement();
                query = "select * from play_lists pl,play_list_songs pls,songs so"
                        + " where pl.UserID='" + LoginScreen.id + "'"
                        + "   and pl.PlayListName='" + addedListNameOPtional.getText() + "'"
                        + "   and pls.PlayListID=pl.PlayListID"
                        + "   and so.SongName='" + addedSongName.getText() + "'"
                        + "   and so.SongID=pls.SongID";
                resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    count2++;
                }
                if (count2 == 0) {
                    connection = DatabaseConnection.connect();
                    query = "select SongID from songs where SongName='" + addedSongName.getText() + "'";
                    statement = connection.createStatement();
                    resultSet = statement.executeQuery(query);
                    
                    while (resultSet.next()) {
                        id1 = resultSet.getInt(1);
                    }
                    connection = DatabaseConnection.connect();
                    query = " insert into play_list_songs (SongID,PlayListID)"
                            + " values (?,?)";
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setInt(1, id1);
                    preparedStatement.setInt(2, id2);
                    preparedStatement.execute();
                    connection.close();
                } else {
                    JOptionPane.showMessageDialog(muziklerAltPanel, "This song already exis in your list");
                }
                
            } else {
                JOptionPane.showMessageDialog(muziklerAltPanel, "This list not exis");
            }
            connection.close();
        } catch (Exception ex) {
            Logger.getLogger(HomePage.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_listAltAltAddOptionalMouseClicked

    private void listelerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listelerMouseClicked
        try {
            myListPanel.removeAll();
            followedListPanel.removeAll();
            myListAltPanel.setVisible(false);
            myListPanel.setVisible(true);
            newListPanel.setVisible(false);
            close();
            connection = DatabaseConnection.connect();
            query = "select UserID from users where UserName='" + LoginScreen.name + "'"
                    + " and Password='" + LoginScreen.password + "'";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                number = resultSet.getInt(1);
            }
            query = "select PlayListName from play_lists where UserID='" + number + "'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            
            JLabel[] label = new JLabel[9];
            JLabel[] labell = new JLabel[9];
            JLabel[] labelll = new JLabel[9];
            int i = 0;
            
            while (resultSet.next()) {
                int x = (i % 6) * 170;
                double y = Math.floor(i / 6) * 170;
                System.out.println(i);
                Image img = new ImageIcon(this.getClass().getResource("Images/imagekind.jpg")).getImage();
                label[i] = new JLabel();
                label[i].setIcon(new ImageIcon(img));
                label[i].setBounds(x + 5, (int) y, 160, 160);
                labell[i] = new JLabel();
                labell[i].setBounds(x + 5, (int) y + 140, 60, 50);
                labell[i].setText(resultSet.getString(1));
                labell[i].setForeground(Color.white);
                labell[i].setFont(new Font(Font.SERIF, Font.PLAIN, 15));
                labelll[i] = new JLabel();
                labelll[i].setBounds(x + 5 + 60, (int) y + 140, 30, 50);
                labelll[i].setText("Sil");
                labelll[i].setForeground(Color.white);
                labelll[i].setFont(new Font(Font.SERIF, Font.PLAIN, 15));
                myListPanel.add(label[i]);
                myListPanel.add(labell[i]);
                myListPanel.add(labelll[i]);
                myListPanel.repaint();
                labelll[i].addMouseListener(new MouseAdapter() {
                    int index = i;
                    
                    public void mouseClicked(MouseEvent e) {
                        if ((!labell[index].getText().equals("POP")) && (!labell[index].getText().equals("JAZZ")) && (!labell[index].getText().equals("KLASIK"))) {
                            try {
                                int idSil = 0;
                                connection = DatabaseConnection.connect();
                                query = "Select PlayListID from play_lists where PlayListName='" + labell[index].getText() + "'"
                                        + " and UserID='" + LoginScreen.id + "'";
                                statement = connection.createStatement();
                                resultSet = statement.executeQuery(query);
                                while (resultSet.next()) {
                                    idSil = resultSet.getInt("PlayListID");
                                }
                                connection = DatabaseConnection.connect();
                                query = "DELETE FROM play_lists WHERE PlayListID='" + idSil + "'"
                                        + " and UserID='" + LoginScreen.id + "'";
                                statement = connection.createStatement();
                                statement.executeUpdate(query);
                                connection.close();
                                myListPanel.remove(label[index]);
                                myListPanel.remove(labell[index]);
                                myListPanel.remove(labelll[index]);
                                myListPanel.repaint();
                            } catch (Exception ex) {
                                Logger.getLogger(HomePage.class
                                        .getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            JOptionPane.showMessageDialog(myListPanel, "You cant this.Because this default list.");
                        }
                    }
                });
                label[i].addMouseListener(new MouseAdapter() {
                    int index = i;
                    
                    public void mouseClicked(MouseEvent e) {
                        myListAltPanel.setVisible(true);
                        myListPanel.setVisible(false);
                        model = (DefaultTableModel) myTable.getModel();
                        model.setRowCount(0);
                        try {
                            myListName = labell[index].getText();
                            showInTableMyListeler(labell[index].getText());
                        } catch (Exception ex) {
                            Logger.getLogger(HomePage.class
                                    .getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                i++;
            }
            connection.close();
        } catch (Exception ex) {
            Logger.getLogger(HomePage.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_listelerMouseClicked

    private void myTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_myTableMouseClicked
        int index = myTable.getSelectedRow();
        model = (DefaultTableModel) myTable.getModel();
        int listen = 0;
        try {
            connection = DatabaseConnection.connect();
            query = "select ViewCount from songs where SongID='" + Integer.parseInt(model.getValueAt(index, 4).toString()) + "'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            
            while (resultSet.next()) {
                listen = resultSet.getInt(1);
                
            }
            query = "update songs set ViewCount=?  where SongID='" + Integer.parseInt(model.getValueAt(index, 4).toString()) + "'";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, (listen + 1));
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            JOptionPane.showMessageDialog(myListAltPanel, "The song is resting");
            
            model = (DefaultTableModel) myTable.getModel();
            model.setRowCount(0);
            try {
                showInTableMyListeler(myListName);
            } catch (Exception ex) {
                Logger.getLogger(HomePage.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            connection = DatabaseConnection.connect();
            query = "select uls.listeningCount from songs so,user_listening_song uls,users u"
                    + " where so.SongID=uls.SongID and uls.UserID=u.UserID and so.SongID='" + Integer.parseInt(model.getValueAt(index, 4).toString()) + "'"
                    + " and u.UserName='" + LoginScreen.name + "'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            int count = 0;
            int listenUser = 0;
            while (resultSet.next()) {
                count++;
                listenUser = resultSet.getInt("ListeningCount");
                
            }
            if (count == 0) {
                query = " insert into user_listening_song (UserID,SongID,ListeningCount)"
                        + " values (?, ?,?)";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, LoginScreen.id);
                preparedStatement.setInt(2, Integer.parseInt(model.getValueAt(index, 4).toString()));
                preparedStatement.setInt(3, 1);
                preparedStatement.execute();
                connection.close();
            } else {
                query = "update user_listening_song set ListeningCount=?  where UserID='" + LoginScreen.id + "'"
                        + "  and SongID='" + Integer.parseInt(model.getValueAt(index, 4).toString()) + "'";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, (listenUser + 1));
                preparedStatement.executeUpdate();
                preparedStatement.close();
                connection.close();
            }
            connection.close();
        } catch (Exception ex) {
            Logger.getLogger(HomePage.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_myTableMouseClicked

    private void silMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_silMouseClicked
        
        try {
            int count = 0;
            int sId = 0, plId = 0;
            connection = DatabaseConnection.connect();
            query = "select s.SongID,pl.PlayListID from songs s,play_lists pl,play_list_songs pls,kinds ki,song_kinds sk"
                    + " where s.SongName='" + silName.getText() + "'"
                    + " and s.SongID=sk.SongID"
                    + " and sk.KindID=ki.KindID"
                    + " and ki.KindName=pl.PlayListName"
                    + " and pl.UserID='" + LoginScreen.id + "'"
                    + " and pl.PlayListID=pls.PlayListID"
                    + " and pls.SongID=s.SongID";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                sId = resultSet.getInt(1);
                plId = resultSet.getInt(2);
                count++;
            }
            if (count != 0) {
                connection = DatabaseConnection.connect();
                query = "DELETE FROM play_list_songs WHERE SongID='" + sId + "'"
                        + " and PlayListId='" + plId + "'";
                statement = connection.createStatement();
                statement.executeUpdate(query);
                connection.close();
                model = (DefaultTableModel) myTable.getModel();
                model.setRowCount(0);
                try {
                    
                    showInTableMyListeler(myListName);
                } catch (Exception ex) {
                    Logger.getLogger(HomePage.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(myListAltPanel, "This song not exis in your list");
            }
            connection.close();
        } catch (Exception ex) {
            Logger.getLogger(HomePage.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_silMouseClicked

    private void silOtherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_silOtherMouseClicked
        try {
            int count = 0;
            int sId = 0, plId = 0;
            connection = DatabaseConnection.connect();
            query = "select s.SongID,pl.PlayListID from songs s,play_lists pl,play_list_songs pls,kinds ki,song_kinds sk"
                    + " where s.SongName='" + silName.getText() + "'"
                    + " and s.SongID=sk.SongID"
                    + " and sk.KindID=ki.KindID"
                    + " and pl.PlayListName='" + silNameLiist.getText() + "'"
                    + " and pl.UserID='" + LoginScreen.id + "'"
                    + " and pl.PlayListID=pls.PlayListID"
                    + " and pls.SongID=s.SongID";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                sId = resultSet.getInt(1);
                plId = resultSet.getInt(2);
                count++;
            }
            if (count != 0) {
                connection = DatabaseConnection.connect();
                query = "DELETE FROM play_list_songs WHERE SongID='" + sId + "'"
                        + " and PlayListId='" + plId + "'";
                statement = connection.createStatement();
                statement.executeUpdate(query);
                connection.close();
                model = (DefaultTableModel) myTable.getModel();
                model.setRowCount(0);
                try {
                    
                    showInTableMyListeler(myListName);
                } catch (Exception ex) {
                    Logger.getLogger(HomePage.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(myListAltPanel, "This song not exis in your list");
            }
            connection.close();
        } catch (Exception ex) {
            Logger.getLogger(HomePage.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_silOtherMouseClicked

    private void popLabelUsersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_popLabelUsersMouseClicked
        girisAltPanel.removeAll();
        girisAltPanel.setVisible(true);
        girisPanel.setVisible(true);
        muziklerAltPanel.setVisible(false);
        albumlerPanel.setVisible(false);
        sanatcilerPanel.setVisible(false);
        gozatPanel.setVisible(false);
        jPanel5.setVisible(false);
        muzikTurleriPanel5.setVisible(false);
        albumlerPanel5.setVisible(false);
        sanatcilerPanel5.setVisible(false);
        listelerPanel5.setVisible(false);
        muzikTurleriAltPanel.setVisible(false);
        albumlerAltPanel.setVisible(false);
        sanatcilarAltPanel.setVisible(false);
        try {
            
            int[] listeningcount = new int[100];
            connection = DatabaseConnection.connect();
            query = "select s.SongName,s.SongID,uls.ListeningCount from user_listening_song uls,songs s,song_kinds sk,kinds k "
                    + " where uls.UserID='" + LoginScreen.id + "'"
                    + "and s.SongId=uls.SongID and s.SongID=sk.SongID and sk.KindID=k.KindID and k.KindName='POP'"
                    + "order BY  uls.ListeningCount DESC LIMIT 10";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            JLabel labelSong[] = new JLabel[10];
            JLabel labelSongg[] = new JLabel[10];
            JLabel labelSonggg[] = new JLabel[10];
            JLabel labelSongggg[] = new JLabel[10];
            JLabel labelSonggggg[] = new JLabel[10];
            int[] songId = new int[10];
            int j = 0;
            while (resultSet.next()) {
                songId[j] = resultSet.getInt("SongID");
                listeningcount[j] = resultSet.getInt("ListeningCount");
                int x = (j % 2) * 510;
                double y = Math.floor(j / 2) * 80;
                Image img = new ImageIcon(this.getClass().getResource("Images/listen.jpg")).getImage();
                labelSong[j] = new JLabel();
                labelSong[j].setIcon(new ImageIcon(img));
                labelSong[j].setBounds(x + 30, (int) y + 15, 500, 50);
                labelSongg[j] = new JLabel();
                labelSongg[j].setBounds(x + 30, (int) y + 45, 100, 50);
                labelSongg[j].setText(resultSet.getString("SongName"));
                labelSongg[j].setForeground(Color.PINK);
                labelSongg[j].setFont(new Font(Font.SERIF, Font.PLAIN, 12));
                labelSonggg[j] = new JLabel();
                labelSonggg[j].setBounds(x + 130 + 30, (int) y + 45, 100, 50);
                labelSonggg[j].setText("Kaydet");
                labelSonggg[j].setForeground(Color.white);
                labelSonggg[j].setFont(new Font(Font.SERIF, Font.PLAIN, 12));
                labelSongggg[j] = new JLabel();
                labelSongggg[j].setBounds(x + 130 + 130 + 30, (int) y + 45, 100, 50);
                labelSongggg[j].setText("Dinle");
                labelSongggg[j].setForeground(Color.white);
                labelSongggg[j].setFont(new Font(Font.SERIF, Font.PLAIN, 12));
                labelSonggggg[j] = new JLabel();
                labelSonggggg[j].setBounds(x + 130 + 130 + 130 + 30, (int) y + 45, 100, 50);
                labelSonggggg[j].setText("Detay");
                labelSonggggg[j].setForeground(Color.white);
                labelSonggggg[j].setFont(new Font(Font.SERIF, Font.PLAIN, 12));
                girisAltPanel.add(labelSongg[j]);
                girisAltPanel.add(labelSonggg[j]);
                girisAltPanel.add(labelSongggg[j]);
                girisAltPanel.add(labelSonggggg[j]);
                girisAltPanel.add(labelSong[j]);
                girisAltPanel.repaint();
                labelSonggg[j].addMouseListener(new MouseAdapter() {
                    int index = j;
                    String kindName = "";
                    int count = 0;
                    int playListID;
                    
                    public void mouseClicked(MouseEvent e) {
                        try {
                            connection = DatabaseConnection.connect();
                            query = "select k.KindName from kinds k,song_kinds sk,songs s"
                                    + " where sk.KindID=k.KindID and s.SongID=sk.SongID and"
                                    + " s.SongName='" + labelSongg[index].getText() + "'";
                            
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            while (resultSet.next()) {
                                kindName = "";
                                kindName = kindName.concat(resultSet.getString(1));
                                System.out.println(kindName);
                            }
                            query = "select PlayListID from play_lists "
                                    + " where UserID='" + LoginScreen.id + "'"
                                    + " and PlayListName='" + kindName + "'";
                            
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            System.out.println(kindName);
                            while (resultSet.next()) {
                                playListID = resultSet.getInt(1);
                            }
                            query = "select pls.SongID from play_list_songs pls,songs s,play_lists pl"
                                    + " where pls.SongID=s.SongID and pls.PlayListID=pl.PlayListID"
                                    + " and pl.UserID='" + LoginScreen.id + "'"
                                    + " and pl.PlayListName='" + kindName + "'"
                                    + " and s.SongName='" + labelSongg[index].getText() + "'";
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            while (resultSet.next()) {
                                count++;
                            }
                            if (count == 0) {
                                
                                query = " insert into play_list_songs (SongID,PlayListID)"
                                        + " values (?,?)";
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.setInt(1, songId[index]);
                                preparedStatement.setInt(2, playListID);
                                preparedStatement.execute();
                                connection.close();
                            } else {
                                JOptionPane.showMessageDialog(albumlerPanel5, "You are already add this song in your playlist");
                            }
                            
                        } catch (Exception ex) {
                            Logger.getLogger(HomePage.class
                                    .getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                labelSonggggg[j].addMouseListener(new MouseAdapter() {
                    int index2 = j;
                    String singersName = "";
                    String kindName = "";
                    String albumName = "";
                    
                    public void mouseClicked(MouseEvent e) {
                        try {
                            connection = DatabaseConnection.connect();
                            query = "select si.SingerName from singers si,singer_songs ss, songs so where"
                                    + " si.SingerID=ss.SingerID and ss.SongID=so.SongID and"
                                    + " so.SongName='" + labelSongg[index2].getText() + "'";
                            
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            while (resultSet.next()) {
                                singersName = singersName.concat(resultSet.getString(1) + " , ");
                            }
                            singersName = singersName.concat("\n" + "listening count:::" + listeningcount[index2]);
                            connection = DatabaseConnection.connect();
                            query = "select ki.KindName from songs so,kinds ki,song_kinds sk "
                                    + " where ki.KindID=sk.KindID and sk.SongID=so.SongID "
                                    + " and so.SongName='" + labelSongg[index2].getText() + "'";
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            while (resultSet.next()) {
                                kindName = resultSet.getString(1);
                                
                            }
                            connection = DatabaseConnection.connect();
                            query = "select a.AlbumName from songs so,albums a,song_albums sa"
                                    + " where a.AlbumID=sa.AlbumID and sa.SongID=so.SongID"
                                    + " and so.SongName='" + labelSongg[index2].getText() + "'";
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            while (resultSet.next()) {
                                
                                albumName = resultSet.getString(1);
                                
                            }
                            
                            JOptionPane.showMessageDialog(albumlerAltPanel, "This songs singers:" + singersName + " \n and song's kind:" + kindName
                                    + "\n and album name:" + albumName);
                            singersName = "";
                        } catch (Exception ex) {
                            Logger.getLogger(HomePage.class
                                    .getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    
                });
                
                labelSongggg[j].addMouseListener(new MouseAdapter() {
                    int index2 = j;
                    int listen;
                    
                    public void mouseClicked(MouseEvent e) {
                        int songIDforUser = 0;
                        try {
                            connection = DatabaseConnection.connect();
                            query = "select ViewCount,SongID from songs where SongName='" + labelSongg[index2].getText() + "'";
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            
                            while (resultSet.next()) {
                                listen = resultSet.getInt(1);
                                songIDforUser = resultSet.getInt(2);
                            }
                            query = "update songs set ViewCount=?  where SongName='" + labelSongg[index2].getText() + "'";
                            preparedStatement = connection.prepareStatement(query);
                            preparedStatement.setInt(1, (listen + 1));
                            preparedStatement.executeUpdate();
                            preparedStatement.close();
                            connection.close();
                            JOptionPane.showMessageDialog(albumlerAltPanel, "The song is resting");
                            connection.close();
                            connection = DatabaseConnection.connect();
                            query = "select so.SongName,uls.listeningCount from songs so,user_listening_song uls,users u"
                                    + " where so.SongID=uls.SongID and uls.UserID=u.UserID and so.SongName='" + labelSongg[index2].getText() + "'"
                                    + " and u.UserName='" + LoginScreen.name + "'";
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            int count = 0;
                            int listenUser = 0;
                            while (resultSet.next()) {
                                count++;
                                listenUser = resultSet.getInt("ListeningCount");
                                
                            }
                            if (count == 0) {
                                query = " insert into user_listening_song (UserID,SongID,ListeningCount)"
                                        + " values (?, ?,?)";
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.setInt(1, LoginScreen.id);
                                preparedStatement.setInt(2, songIDforUser);
                                preparedStatement.setInt(3, 1);
                                preparedStatement.execute();
                                connection.close();
                            } else {
                                query = "update user_listening_song set ListeningCount=?  where UserID='" + LoginScreen.id + "'"
                                        + "  and SongID='" + songIDforUser + "'";
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.setInt(1, (listenUser + 1));
                                preparedStatement.executeUpdate();
                                preparedStatement.close();
                                connection.close();
                            }
                            
                        } catch (Exception ex) {
                            Logger.getLogger(HomePage.class
                                    .getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    
                });
                j++;
            }
            connection.close();
        } catch (Exception ex) {
            Logger.getLogger(HomePage.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_popLabelUsersMouseClicked

    private void jazzLabelUsersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jazzLabelUsersMouseClicked
        
        girisAltPanel.removeAll();
        girisAltPanel.setVisible(true);
        girisPanel.setVisible(true);
        muziklerAltPanel.setVisible(false);
        albumlerPanel.setVisible(false);
        sanatcilerPanel.setVisible(false);
        gozatPanel.setVisible(false);
        jPanel5.setVisible(false);
        muzikTurleriPanel5.setVisible(false);
        albumlerPanel5.setVisible(false);
        sanatcilerPanel5.setVisible(false);
        listelerPanel5.setVisible(false);
        muzikTurleriAltPanel.setVisible(false);
        albumlerAltPanel.setVisible(false);
        sanatcilarAltPanel.setVisible(false);
        try {
            int[] listeningcount = new int[100];
            connection = DatabaseConnection.connect();
            query = "select s.SongName,s.SongID,uls.ListeningCount from user_listening_song uls,songs s,song_kinds sk,kinds k "
                    + " where uls.UserID='" + LoginScreen.id + "'"
                    + "and s.SongId=uls.SongID and s.SongID=sk.SongID and sk.KindID=k.KindID and k.KindName='JAZZ'"
                    + "order BY  uls.ListeningCount DESC LIMIT 10";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            JLabel labelSong[] = new JLabel[10];
            JLabel labelSongg[] = new JLabel[10];
            JLabel labelSonggg[] = new JLabel[10];
            JLabel labelSongggg[] = new JLabel[10];
            JLabel labelSonggggg[] = new JLabel[10];
            int[] songId = new int[10];
            int j = 0;
            while (resultSet.next()) {
                songId[j] = resultSet.getInt("SongID");
                listeningcount[j] = resultSet.getInt("ListeningCount");
                int x = (j % 2) * 510;
                double y = Math.floor(j / 2) * 80;
                Image img = new ImageIcon(this.getClass().getResource("Images/listen.jpg")).getImage();
                labelSong[j] = new JLabel();
                labelSong[j].setIcon(new ImageIcon(img));
                labelSong[j].setBounds(x + 30, (int) y + 15, 500, 50);
                labelSongg[j] = new JLabel();
                labelSongg[j].setBounds(x + 30, (int) y + 45, 100, 50);
                labelSongg[j].setText(resultSet.getString("SongName"));
                labelSongg[j].setForeground(Color.PINK);
                labelSongg[j].setFont(new Font(Font.SERIF, Font.PLAIN, 12));
                labelSonggg[j] = new JLabel();
                labelSonggg[j].setBounds(x + 130 + 30, (int) y + 45, 100, 50);
                labelSonggg[j].setText("Kaydet");
                labelSonggg[j].setForeground(Color.white);
                labelSonggg[j].setFont(new Font(Font.SERIF, Font.PLAIN, 12));
                labelSongggg[j] = new JLabel();
                labelSongggg[j].setBounds(x + 130 + 130 + 30, (int) y + 45, 100, 50);
                labelSongggg[j].setText("Dinle");
                labelSongggg[j].setForeground(Color.white);
                labelSongggg[j].setFont(new Font(Font.SERIF, Font.PLAIN, 12));
                labelSonggggg[j] = new JLabel();
                labelSonggggg[j].setBounds(x + 130 + 130 + 130 + 30, (int) y + 45, 100, 50);
                labelSonggggg[j].setText("Detay");
                labelSonggggg[j].setForeground(Color.white);
                labelSonggggg[j].setFont(new Font(Font.SERIF, Font.PLAIN, 12));
                girisAltPanel.add(labelSongg[j]);
                girisAltPanel.add(labelSonggg[j]);
                girisAltPanel.add(labelSongggg[j]);
                girisAltPanel.add(labelSonggggg[j]);
                girisAltPanel.add(labelSong[j]);
                girisAltPanel.repaint();
                labelSonggg[j].addMouseListener(new MouseAdapter() {
                    int index = j;
                    String kindName = "";
                    int count = 0;
                    int playListID;
                    
                    public void mouseClicked(MouseEvent e) {
                        try {
                            connection = DatabaseConnection.connect();
                            query = "select k.KindName from kinds k,song_kinds sk,songs s"
                                    + " where sk.KindID=k.KindID and s.SongID=sk.SongID and"
                                    + " s.SongName='" + labelSongg[index].getText() + "'";
                            
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            while (resultSet.next()) {
                                kindName = "";
                                kindName = kindName.concat(resultSet.getString(1));
                                System.out.println(kindName);
                            }
                            query = "select PlayListID from play_lists "
                                    + " where UserID='" + LoginScreen.id + "'"
                                    + " and PlayListName='" + kindName + "'";
                            
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            System.out.println(kindName);
                            while (resultSet.next()) {
                                playListID = resultSet.getInt(1);
                            }
                            query = "select pls.SongID from play_list_songs pls,songs s,play_lists pl"
                                    + " where pls.SongID=s.SongID and pls.PlayListID=pl.PlayListID"
                                    + " and pl.UserID='" + LoginScreen.id + "'"
                                    + " and pl.PlayListName='" + kindName + "'"
                                    + " and s.SongName='" + labelSongg[index].getText() + "'";
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            while (resultSet.next()) {
                                count++;
                            }
                            if (count == 0) {
                                
                                query = " insert into play_list_songs (SongID,PlayListID)"
                                        + " values (?,?)";
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.setInt(1, songId[index]);
                                preparedStatement.setInt(2, playListID);
                                preparedStatement.execute();
                                connection.close();
                            } else {
                                JOptionPane.showMessageDialog(albumlerPanel5, "You are already add this song in your playlist");
                            }
                            
                        } catch (Exception ex) {
                            Logger.getLogger(HomePage.class
                                    .getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                labelSonggggg[j].addMouseListener(new MouseAdapter() {
                    int index2 = j;
                    String singersName = "";
                    String kindName = "";
                    String albumName = "";
                    
                    public void mouseClicked(MouseEvent e) {
                        try {
                            connection = DatabaseConnection.connect();
                            query = "select si.SingerName from singers si,singer_songs ss, songs so where"
                                    + " si.SingerID=ss.SingerID and ss.SongID=so.SongID and"
                                    + " so.SongName='" + labelSongg[index2].getText() + "'";
                            
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            while (resultSet.next()) {
                                singersName = singersName.concat(resultSet.getString(1) + " , ");
                            }
                            singersName = singersName.concat("\n" + "listening count:::" + listeningcount[index2]);
                            connection = DatabaseConnection.connect();
                            query = "select ki.KindName from songs so,kinds ki,song_kinds sk "
                                    + " where ki.KindID=sk.KindID and sk.SongID=so.SongID "
                                    + " and so.SongName='" + labelSongg[index2].getText() + "'";
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            while (resultSet.next()) {
                                kindName = resultSet.getString(1);
                                
                            }
                            connection = DatabaseConnection.connect();
                            query = "select a.AlbumName from songs so,albums a,song_albums sa"
                                    + " where a.AlbumID=sa.AlbumID and sa.SongID=so.SongID"
                                    + " and so.SongName='" + labelSongg[index2].getText() + "'";
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            while (resultSet.next()) {
                                
                                albumName = resultSet.getString(1);
                                
                            }
                            
                            JOptionPane.showMessageDialog(albumlerAltPanel, "This songs singers:" + singersName + " \n and song's kind:" + kindName
                                    + "\n and album name:" + albumName);
                            singersName = "";
                        } catch (Exception ex) {
                            Logger.getLogger(HomePage.class
                                    .getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    
                });
                
                labelSongggg[j].addMouseListener(new MouseAdapter() {
                    int index2 = j;
                    int listen;
                    
                    public void mouseClicked(MouseEvent e) {
                        int songIDforUser = 0;
                        try {
                            connection = DatabaseConnection.connect();
                            query = "select ViewCount,SongID from songs where SongName='" + labelSongg[index2].getText() + "'";
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            
                            while (resultSet.next()) {
                                listen = resultSet.getInt(1);
                                songIDforUser = resultSet.getInt(2);
                            }
                            query = "update songs set ViewCount=?  where SongName='" + labelSongg[index2].getText() + "'";
                            preparedStatement = connection.prepareStatement(query);
                            preparedStatement.setInt(1, (listen + 1));
                            preparedStatement.executeUpdate();
                            preparedStatement.close();
                            connection.close();
                            JOptionPane.showMessageDialog(albumlerAltPanel, "The song is resting");
                            connection.close();
                            connection = DatabaseConnection.connect();
                            query = "select so.SongName,uls.listeningCount from songs so,user_listening_song uls,users u"
                                    + " where so.SongID=uls.SongID and uls.UserID=u.UserID and so.SongName='" + labelSongg[index2].getText() + "'"
                                    + " and u.UserName='" + LoginScreen.name + "'";
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            int count = 0;
                            int listenUser = 0;
                            while (resultSet.next()) {
                                count++;
                                listenUser = resultSet.getInt("ListeningCount");
                                
                            }
                            if (count == 0) {
                                query = " insert into user_listening_song (UserID,SongID,ListeningCount)"
                                        + " values (?, ?,?)";
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.setInt(1, LoginScreen.id);
                                preparedStatement.setInt(2, songIDforUser);
                                preparedStatement.setInt(3, 1);
                                preparedStatement.execute();
                                connection.close();
                            } else {
                                query = "update user_listening_song set ListeningCount=?  where UserID='" + LoginScreen.id + "'"
                                        + "  and SongID='" + songIDforUser + "'";
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.setInt(1, (listenUser + 1));
                                preparedStatement.executeUpdate();
                                preparedStatement.close();
                                connection.close();
                            }
                            
                        } catch (Exception ex) {
                            Logger.getLogger(HomePage.class
                                    .getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    
                });
                j++;
            }
            connection.close();
        } catch (Exception ex) {
            Logger.getLogger(HomePage.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        

    }//GEN-LAST:event_jazzLabelUsersMouseClicked

    private void klasikLabelUsersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_klasikLabelUsersMouseClicked
        girisAltPanel.removeAll();
        girisAltPanel.setVisible(true);
        girisPanel.setVisible(true);
        muziklerAltPanel.setVisible(false);
        albumlerPanel.setVisible(false);
        sanatcilerPanel.setVisible(false);
        gozatPanel.setVisible(false);
        jPanel5.setVisible(false);
        muzikTurleriPanel5.setVisible(false);
        albumlerPanel5.setVisible(false);
        sanatcilerPanel5.setVisible(false);
        listelerPanel5.setVisible(false);
        muzikTurleriAltPanel.setVisible(false);
        albumlerAltPanel.setVisible(false);
        sanatcilarAltPanel.setVisible(false);
        try {
            int[] listeningcount = new int[100];
            connection = DatabaseConnection.connect();
            query = "select s.SongName,s.SongID,uls.ListeningCount from user_listening_song uls,songs s,song_kinds sk,kinds k "
                    + " where uls.UserID='" + LoginScreen.id + "'"
                    + "and s.SongId=uls.SongID and s.SongID=sk.SongID and sk.KindID=k.KindID and k.KindName='KLASIK'"
                    + "order BY  uls.ListeningCount DESC LIMIT 10";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            JLabel labelSong[] = new JLabel[10];
            JLabel labelSongg[] = new JLabel[10];
            JLabel labelSonggg[] = new JLabel[10];
            JLabel labelSongggg[] = new JLabel[10];
            JLabel labelSonggggg[] = new JLabel[10];
            int[] songId = new int[10];
            int j = 0;
            while (resultSet.next()) {
                songId[j] = resultSet.getInt("SongID");
                listeningcount[j] = resultSet.getInt("ListeningCount");
                int x = (j % 2) * 510;
                double y = Math.floor(j / 2) * 80;
                Image img = new ImageIcon(this.getClass().getResource("Images/listen.jpg")).getImage();
                labelSong[j] = new JLabel();
                labelSong[j].setIcon(new ImageIcon(img));
                labelSong[j].setBounds(x + 30, (int) y + 15, 500, 50);
                labelSongg[j] = new JLabel();
                labelSongg[j].setBounds(x + 30, (int) y + 45, 100, 50);
                labelSongg[j].setText(resultSet.getString("SongName"));
                labelSongg[j].setForeground(Color.PINK);
                labelSongg[j].setFont(new Font(Font.SERIF, Font.PLAIN, 12));
                labelSonggg[j] = new JLabel();
                labelSonggg[j].setBounds(x + 130 + 30, (int) y + 45, 100, 50);
                labelSonggg[j].setText("Kaydet");
                labelSonggg[j].setForeground(Color.white);
                labelSonggg[j].setFont(new Font(Font.SERIF, Font.PLAIN, 12));
                labelSongggg[j] = new JLabel();
                labelSongggg[j].setBounds(x + 130 + 130 + 30, (int) y + 45, 100, 50);
                labelSongggg[j].setText("Dinle");
                labelSongggg[j].setForeground(Color.white);
                labelSongggg[j].setFont(new Font(Font.SERIF, Font.PLAIN, 12));
                labelSonggggg[j] = new JLabel();
                labelSonggggg[j].setBounds(x + 130 + 130 + 130 + 30, (int) y + 45, 100, 50);
                labelSonggggg[j].setText("Detay");
                labelSonggggg[j].setForeground(Color.white);
                labelSonggggg[j].setFont(new Font(Font.SERIF, Font.PLAIN, 12));
                girisAltPanel.add(labelSongg[j]);
                girisAltPanel.add(labelSonggg[j]);
                girisAltPanel.add(labelSongggg[j]);
                girisAltPanel.add(labelSonggggg[j]);
                girisAltPanel.add(labelSong[j]);
                girisAltPanel.repaint();
                labelSonggg[j].addMouseListener(new MouseAdapter() {
                    int index = j;
                    String kindName = "";
                    int count = 0;
                    int playListID;
                    
                    public void mouseClicked(MouseEvent e) {
                        try {
                            connection = DatabaseConnection.connect();
                            query = "select k.KindName from kinds k,song_kinds sk,songs s"
                                    + " where sk.KindID=k.KindID and s.SongID=sk.SongID and"
                                    + " s.SongName='" + labelSongg[index].getText() + "'";
                            
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            while (resultSet.next()) {
                                kindName = "";
                                kindName = kindName.concat(resultSet.getString(1));
                                System.out.println(kindName);
                            }
                            query = "select PlayListID from play_lists "
                                    + " where UserID='" + LoginScreen.id + "'"
                                    + " and PlayListName='" + kindName + "'";
                            
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            System.out.println(kindName);
                            while (resultSet.next()) {
                                playListID = resultSet.getInt(1);
                            }
                            query = "select pls.SongID from play_list_songs pls,songs s,play_lists pl"
                                    + " where pls.SongID=s.SongID and pls.PlayListID=pl.PlayListID"
                                    + " and pl.UserID='" + LoginScreen.id + "'"
                                    + " and pl.PlayListName='" + kindName + "'"
                                    + " and s.SongName='" + labelSongg[index].getText() + "'";
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            while (resultSet.next()) {
                                count++;
                            }
                            if (count == 0) {
                                
                                query = " insert into play_list_songs (SongID,PlayListID)"
                                        + " values (?,?)";
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.setInt(1, songId[index]);
                                preparedStatement.setInt(2, playListID);
                                preparedStatement.execute();
                                connection.close();
                            } else {
                                JOptionPane.showMessageDialog(albumlerPanel5, "You are already add this song in your playlist");
                            }
                            
                        } catch (Exception ex) {
                            Logger.getLogger(HomePage.class
                                    .getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                labelSonggggg[j].addMouseListener(new MouseAdapter() {
                    int index2 = j;
                    String singersName = "";
                    String kindName = "";
                    String albumName = "";
                    
                    public void mouseClicked(MouseEvent e) {
                        try {
                            connection = DatabaseConnection.connect();
                            query = "select si.SingerName from singers si,singer_songs ss, songs so where"
                                    + " si.SingerID=ss.SingerID and ss.SongID=so.SongID and"
                                    + " so.SongName='" + labelSongg[index2].getText() + "'";
                            
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            while (resultSet.next()) {
                                singersName = singersName.concat(resultSet.getString(1) + " , ");
                            }
                            singersName = singersName.concat("\n" + "listeening count:::" + listeningcount[index2]);
                            connection = DatabaseConnection.connect();
                            query = "select ki.KindName from songs so,kinds ki,song_kinds sk "
                                    + " where ki.KindID=sk.KindID and sk.SongID=so.SongID "
                                    + " and so.SongName='" + labelSongg[index2].getText() + "'";
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            while (resultSet.next()) {
                                kindName = resultSet.getString(1);
                                
                            }
                            connection = DatabaseConnection.connect();
                            query = "select a.AlbumName from songs so,albums a,song_albums sa"
                                    + " where a.AlbumID=sa.AlbumID and sa.SongID=so.SongID"
                                    + " and so.SongName='" + labelSongg[index2].getText() + "'";
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            while (resultSet.next()) {
                                
                                albumName = resultSet.getString(1);
                                
                            }
                            
                            JOptionPane.showMessageDialog(albumlerAltPanel, "This songs singers:" + singersName + " \n and song's kind:" + kindName
                                    + "\n and album name:" + albumName);
                            singersName = "";
                        } catch (Exception ex) {
                            Logger.getLogger(HomePage.class
                                    .getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    
                });
                
                labelSongggg[j].addMouseListener(new MouseAdapter() {
                    int index2 = j;
                    int listen;
                    
                    public void mouseClicked(MouseEvent e) {
                        int songIDforUser = 0;
                        try {
                            connection = DatabaseConnection.connect();
                            query = "select ViewCount,SongID from songs where SongName='" + labelSongg[index2].getText() + "'";
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            
                            while (resultSet.next()) {
                                listen = resultSet.getInt(1);
                                songIDforUser = resultSet.getInt(2);
                            }
                            query = "update songs set ViewCount=?  where SongName='" + labelSongg[index2].getText() + "'";
                            preparedStatement = connection.prepareStatement(query);
                            preparedStatement.setInt(1, (listen + 1));
                            preparedStatement.executeUpdate();
                            preparedStatement.close();
                            connection.close();
                            JOptionPane.showMessageDialog(albumlerAltPanel, "The song is resting");
                            connection.close();
                            connection = DatabaseConnection.connect();
                            query = "select so.SongName,uls.listeningCount from songs so,user_listening_song uls,users u"
                                    + " where so.SongID=uls.SongID and uls.UserID=u.UserID and so.SongName='" + labelSongg[index2].getText() + "'"
                                    + " and u.UserName='" + LoginScreen.name + "'";
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            int count = 0;
                            int listenUser = 0;
                            while (resultSet.next()) {
                                count++;
                                listenUser = resultSet.getInt("ListeningCount");
                                
                            }
                            if (count == 0) {
                                query = " insert into user_listening_song (UserID,SongID,ListeningCount)"
                                        + " values (?, ?,?)";
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.setInt(1, LoginScreen.id);
                                preparedStatement.setInt(2, songIDforUser);
                                preparedStatement.setInt(3, 1);
                                preparedStatement.execute();
                                connection.close();
                            } else {
                                query = "update user_listening_song set ListeningCount=?  where UserID='" + LoginScreen.id + "'"
                                        + "  and SongID='" + songIDforUser + "'";
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.setInt(1, (listenUser + 1));
                                preparedStatement.executeUpdate();
                                preparedStatement.close();
                                connection.close();
                            }
                            
                        } catch (Exception ex) {
                            Logger.getLogger(HomePage.class
                                    .getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    
                });
                j++;
            }
            connection.close();
        } catch (Exception ex) {
            Logger.getLogger(HomePage.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        

    }//GEN-LAST:event_klasikLabelUsersMouseClicked

    private void genelLabelUsersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_genelLabelUsersMouseClicked
        
        girisAltPanel.removeAll();
        girisAltPanel.setVisible(true);
        girisPanel.setVisible(true);
        muziklerAltPanel.setVisible(false);
        albumlerPanel.setVisible(false);
        sanatcilerPanel.setVisible(false);
        gozatPanel.setVisible(false);
        jPanel5.setVisible(false);
        muzikTurleriPanel5.setVisible(false);
        albumlerPanel5.setVisible(false);
        sanatcilerPanel5.setVisible(false);
        listelerPanel5.setVisible(false);
        muzikTurleriAltPanel.setVisible(false);
        albumlerAltPanel.setVisible(false);
        sanatcilarAltPanel.setVisible(false);
        try {
            int[] listeningcount = new int[100];
            connection = DatabaseConnection.connect();
            query = "select s.SongName,s.SongID,uls.ListeningCount from user_listening_song uls,songs s"
                    + " where uls.UserID='" + LoginScreen.id + "'"
                    + " and s.SongId=uls.SongID"
                    + " order BY  uls.ListeningCount DESC LIMIT 10";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            JLabel labelSong[] = new JLabel[10];
            JLabel labelSongg[] = new JLabel[10];
            JLabel labelSonggg[] = new JLabel[10];
            JLabel labelSongggg[] = new JLabel[10];
            JLabel labelSonggggg[] = new JLabel[10];
            int[] songId = new int[10];
            int j = 0;
            while (resultSet.next()) {
                songId[j] = resultSet.getInt("SongID");
                listeningcount[j] = resultSet.getInt("ListeningCount");
                int x = (j % 2) * 510;
                double y = Math.floor(j / 2) * 80;
                Image img = new ImageIcon(this.getClass().getResource("Images/listen.jpg")).getImage();
                labelSong[j] = new JLabel();
                labelSong[j].setIcon(new ImageIcon(img));
                labelSong[j].setBounds(x + 30, (int) y + 15, 500, 50);
                labelSongg[j] = new JLabel();
                labelSongg[j].setBounds(x + 30, (int) y + 45, 100, 50);
                labelSongg[j].setText(resultSet.getString("SongName"));
                labelSongg[j].setForeground(Color.PINK);
                labelSongg[j].setFont(new Font(Font.SERIF, Font.PLAIN, 12));
                labelSonggg[j] = new JLabel();
                labelSonggg[j].setBounds(x + 130 + 30, (int) y + 45, 100, 50);
                labelSonggg[j].setText("Kaydet");
                labelSonggg[j].setForeground(Color.white);
                labelSonggg[j].setFont(new Font(Font.SERIF, Font.PLAIN, 12));
                labelSongggg[j] = new JLabel();
                labelSongggg[j].setBounds(x + 130 + 130 + 30, (int) y + 45, 100, 50);
                labelSongggg[j].setText("Dinle");
                labelSongggg[j].setForeground(Color.white);
                labelSongggg[j].setFont(new Font(Font.SERIF, Font.PLAIN, 12));
                labelSonggggg[j] = new JLabel();
                labelSonggggg[j].setBounds(x + 130 + 130 + 130 + 30, (int) y + 45, 100, 50);
                labelSonggggg[j].setText("Detay");
                labelSonggggg[j].setForeground(Color.white);
                labelSonggggg[j].setFont(new Font(Font.SERIF, Font.PLAIN, 12));
                girisAltPanel.add(labelSongg[j]);
                girisAltPanel.add(labelSonggg[j]);
                girisAltPanel.add(labelSongggg[j]);
                girisAltPanel.add(labelSonggggg[j]);
                girisAltPanel.add(labelSong[j]);
                girisAltPanel.repaint();
                labelSonggg[j].addMouseListener(new MouseAdapter() {
                    int index = j;
                    String kindName = "";
                    int count = 0;
                    int playListID;
                    
                    public void mouseClicked(MouseEvent e) {
                        try {
                            connection = DatabaseConnection.connect();
                            query = "select k.KindName from kinds k,song_kinds sk,songs s"
                                    + " where sk.KindID=k.KindID and s.SongID=sk.SongID and"
                                    + " s.SongName='" + labelSongg[index].getText() + "'";
                            
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            while (resultSet.next()) {
                                kindName = "";
                                kindName = kindName.concat(resultSet.getString(1));
                                System.out.println(kindName);
                            }
                            query = "select PlayListID from play_lists "
                                    + " where UserID='" + LoginScreen.id + "'"
                                    + " and PlayListName='" + kindName + "'";
                            
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            System.out.println(kindName);
                            while (resultSet.next()) {
                                playListID = resultSet.getInt(1);
                            }
                            query = "select pls.SongID from play_list_songs pls,songs s,play_lists pl"
                                    + " where pls.SongID=s.SongID and pls.PlayListID=pl.PlayListID"
                                    + " and pl.UserID='" + LoginScreen.id + "'"
                                    + " and pl.PlayListName='" + kindName + "'"
                                    + " and s.SongName='" + labelSongg[index].getText() + "'";
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            while (resultSet.next()) {
                                count++;
                            }
                            if (count == 0) {
                                
                                query = " insert into play_list_songs (SongID,PlayListID)"
                                        + " values (?,?)";
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.setInt(1, songId[index]);
                                preparedStatement.setInt(2, playListID);
                                preparedStatement.execute();
                                connection.close();
                            } else {
                                JOptionPane.showMessageDialog(albumlerPanel5, "You are already add this song in your playlist");
                            }
                            
                        } catch (Exception ex) {
                            Logger.getLogger(HomePage.class
                                    .getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                labelSonggggg[j].addMouseListener(new MouseAdapter() {
                    int index2 = j;
                    String singersName = "";
                    String kindName = "";
                    String albumName = "";
                    
                    public void mouseClicked(MouseEvent e) {
                        try {
                            connection = DatabaseConnection.connect();
                            query = "select si.SingerName from singers si,singer_songs ss, songs so where"
                                    + " si.SingerID=ss.SingerID and ss.SongID=so.SongID and"
                                    + " so.SongName='" + labelSongg[index2].getText() + "'";
                            
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            while (resultSet.next()) {
                                singersName = singersName.concat(resultSet.getString(1) + " , ");
                            }
                            singersName = singersName.concat("\n" + "listening count:::" + listeningcount[index2])
                            connection = DatabaseConnection.connect();
                            query = "select ki.KindName from songs so,kinds ki,song_kinds sk "
                                    + " where ki.KindID=sk.KindID and sk.SongID=so.SongID "
                                    + " and so.SongName='" + labelSongg[index2].getText() + "'";
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            while (resultSet.next()) {
                                kindName = resultSet.getString(1);
                                
                            }
                            connection = DatabaseConnection.connect();
                            query = "select a.AlbumName from songs so,albums a,song_albums sa"
                                    + " where a.AlbumID=sa.AlbumID and sa.SongID=so.SongID"
                                    + " and so.SongName='" + labelSongg[index2].getText() + "'";
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            while (resultSet.next()) {
                                
                                albumName = resultSet.getString(1);
                                
                            }
                            
                            JOptionPane.showMessageDialog(albumlerAltPanel, "This songs singers:" + singersName + " \n and song's kind:" + kindName
                                    + "\n and album name:" + albumName);
                            singersName = "";
                        } catch (Exception ex) {
                            Logger.getLogger(HomePage.class
                                    .getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    
                });
                
                labelSongggg[j].addMouseListener(new MouseAdapter() {
                    int index2 = j;
                    int listen;
                    
                    public void mouseClicked(MouseEvent e) {
                        int songIDforUser = 0;
                        try {
                            connection = DatabaseConnection.connect();
                            query = "select ViewCount,SongID from songs where SongName='" + labelSongg[index2].getText() + "'";
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            
                            while (resultSet.next()) {
                                listen = resultSet.getInt(1);
                                songIDforUser = resultSet.getInt(2);
                            }
                            query = "update songs set ViewCount=?  where SongName='" + labelSongg[index2].getText() + "'";
                            preparedStatement = connection.prepareStatement(query);
                            preparedStatement.setInt(1, (listen + 1));
                            preparedStatement.executeUpdate();
                            preparedStatement.close();
                            connection.close();
                            JOptionPane.showMessageDialog(albumlerAltPanel, "The song is resting");
                            connection.close();
                            connection = DatabaseConnection.connect();
                            query = "select so.SongName,uls.listeningCount from songs so,user_listening_song uls,users u"
                                    + " where so.SongID=uls.SongID and uls.UserID=u.UserID and so.SongName='" + labelSongg[index2].getText() + "'"
                                    + " and u.UserName='" + LoginScreen.name + "'";
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(query);
                            int count = 0;
                            int listenUser = 0;
                            while (resultSet.next()) {
                                count++;
                                listenUser = resultSet.getInt("ListeningCount");
                                
                            }
                            if (count == 0) {
                                query = " insert into user_listening_song (UserID,SongID,ListeningCount)"
                                        + " values (?, ?,?)";
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.setInt(1, LoginScreen.id);
                                preparedStatement.setInt(2, songIDforUser);
                                preparedStatement.setInt(3, 1);
                                preparedStatement.execute();
                                connection.close();
                            } else {
                                query = "update user_listening_song set ListeningCount=?  where UserID='" + LoginScreen.id + "'"
                                        + "  and SongID='" + songIDforUser + "'";
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.setInt(1, (listenUser + 1));
                                preparedStatement.executeUpdate();
                                preparedStatement.close();
                                connection.close();
                            }
                            
                        } catch (Exception ex) {
                            Logger.getLogger(HomePage.class
                                    .getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    
                });
                j++;
            }
            connection.close();
        } catch (Exception ex) {
            Logger.getLogger(HomePage.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        

    }//GEN-LAST:event_genelLabelUsersMouseClicked

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        this.dispose();
        this.setVisible(false);
        LoginScreen login = new LoginScreen();
        login.setVisible(true);
    }//GEN-LAST:event_jLabel11MouseClicked

    private void newListLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newListLabelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newListLabelActionPerformed

    private void addOptionalSingleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addOptionalSingleMouseClicked
        try {
            int count = 0;
            int id1 = 0, id2 = 0;
            connection = DatabaseConnection.connect();
            statement = connection.createStatement();
            query = "select pl.PlayListID from play_lists pl"
                    + " where pl.UserID='" + LoginScreen.id + "'"
                    + "   and pl.PlayListName='" + addedListNameOPtionalSingle.getText() + "'";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                id2 = resultSet.getInt("PlayListID");
                count++;
            }
            if (count != 0) {
                int count2 = 0;
                connection = DatabaseConnection.connect();
                statement = connection.createStatement();
                query = "select * from play_lists pl,play_list_songs pls,songs so"
                        + " where pl.UserID='" + LoginScreen.id + "'"
                        + "   and pl.PlayListName='" + addedListNameOPtionalSingle.getText() + "'"
                        + "   and pls.PlayListID=pl.PlayListID"
                        + "   and so.SongName='" + addedSongNameSingle.getText() + "'"
                        + "   and so.SongID=pls.SongID";
                resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    count2++;
                }
                if (count2 == 0) {
                    connection = DatabaseConnection.connect();
                    query = "select SongID from songs where SongName='" + addedSongNameSingle.getText() + "'";
                    statement = connection.createStatement();
                    resultSet = statement.executeQuery(query);
                    
                    while (resultSet.next()) {
                        id1 = resultSet.getInt(1);
                    }
                    connection = DatabaseConnection.connect();
                    query = " insert into play_list_songs (SongID,PlayListID)"
                            + " values (?,?)";
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setInt(1, id1);
                    preparedStatement.setInt(2, id2);
                    preparedStatement.execute();
                    connection.close();
                } else {
                    JOptionPane.showMessageDialog(muziklerAltPanel, "This song already exis in your list");
                }
                
            } else {
                JOptionPane.showMessageDialog(muziklerAltPanel, "This list not exis");
            }
            connection.close();
        } catch (Exception ex) {
            Logger.getLogger(HomePage.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_addOptionalSingleMouseClicked

    private void addOptionalTursuzMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addOptionalTursuzMouseClicked
        try {
            int count = 0;
            int id1 = 0, id2 = 0;
            connection = DatabaseConnection.connect();
            statement = connection.createStatement();
            query = "select pl.PlayListID from play_lists pl"
                    + " where pl.UserID='" + LoginScreen.id + "'"
                    + "   and pl.PlayListName='" + addedListNameOPtionalTursuz.getText() + "'";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                id2 = resultSet.getInt("PlayListID");
                count++;
            }
            if (count != 0) {
                int count2 = 0;
                connection = DatabaseConnection.connect();
                statement = connection.createStatement();
                query = "select * from play_lists pl,play_list_songs pls,songs so"
                        + " where pl.UserID='" + LoginScreen.id + "'"
                        + "   and pl.PlayListName='" + addedListNameOPtionalTursuz.getText() + "'"
                        + "   and pls.PlayListID=pl.PlayListID"
                        + "   and so.SongName='" + addedSongNameTursuz.getText() + "'"
                        + "   and so.SongID=pls.SongID";
                resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    count2++;
                }
                if (count2 == 0) {
                    connection = DatabaseConnection.connect();
                    query = "select SongID from songs where SongName='" + addedSongNameTursuz.getText() + "'";
                    statement = connection.createStatement();
                    resultSet = statement.executeQuery(query);
                    
                    while (resultSet.next()) {
                        id1 = resultSet.getInt(1);
                    }
                    connection = DatabaseConnection.connect();
                    query = " insert into play_list_songs (SongID,PlayListID)"
                            + " values (?,?)";
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setInt(1, id1);
                    preparedStatement.setInt(2, id2);
                    preparedStatement.execute();
                    connection.close();
                } else {
                    JOptionPane.showMessageDialog(muziklerAltPanel, "This song already exis in your list");
                }
                
            } else {
                JOptionPane.showMessageDialog(muziklerAltPanel, "This list not exis");
            }
            connection.close();
        } catch (Exception ex) {
            Logger.getLogger(HomePage.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_addOptionalTursuzMouseClicked

    private void addedSongNameSingleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addedSongNameSingleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addedSongNameSingleActionPerformed

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        try {
            connection = DatabaseConnection.connect();
            query = "delete from users where UserID=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, LoginScreen.id);
            preparedStatement.executeUpdate();
            connection.close();
            this.dispose();
            this.setVisible(false);
            LoginScreen login = new LoginScreen();
            login.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel15MouseClicked
    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new HomePage().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(HomePage.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addOptionalSingle;
    private javax.swing.JLabel addOptionalTursuz;
    private javax.swing.JTextField addedListNameOPtional;
    private javax.swing.JTextField addedListNameOPtionalSingle;
    private javax.swing.JTextField addedListNameOPtionalTursuz;
    private javax.swing.JTextField addedSongName;
    private javax.swing.JTextField addedSongNameSingle;
    private javax.swing.JTextField addedSongNameTursuz;
    private javax.swing.JPanel albumlerAltPanel;
    private javax.swing.JLabel albumlerLabel;
    private javax.swing.JLabel albumlerLabel5;
    private javax.swing.JPanel albumlerPanel;
    private javax.swing.JPanel albumlerPanel5;
    private javax.swing.JLabel countryLabel;
    private javax.swing.JPanel followedAlbumAltPanel;
    private javax.swing.JPanel followedAlbumPanel;
    private javax.swing.JPanel followedListAltPanel;
    private javax.swing.JLabel followedListLabel;
    private javax.swing.JPanel followedListPanel;
    private javax.swing.JLayeredPane followedListPanel1;
    private javax.swing.JTable followedListTable;
    private javax.swing.JPanel followedList_UserPanel;
    private javax.swing.JPanel followedSingerAltPanel;
    private javax.swing.JPanel followedSingerPanel;
    private javax.swing.JPanel followedUserALtAltPanel;
    private javax.swing.JPanel followedUserAltPanel;
    private javax.swing.JLabel followedUserLabel;
    private javax.swing.JTable followedUserListTable;
    private javax.swing.JPanel followedUserPanel;
    private javax.swing.JLabel genelLabel;
    private javax.swing.JLabel genelLabelUsers;
    private javax.swing.JPanel girisAltAltPanel;
    private javax.swing.JPanel girisAltPanel;
    private javax.swing.JLabel girisLabel;
    private javax.swing.JPanel girisPanel;
    private javax.swing.JLabel gozatLabel;
    private javax.swing.JPanel gozatPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPane3;
    private javax.swing.JLayeredPane jLayeredPane4;
    private javax.swing.JLayeredPane jLayeredPane5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JLabel jazzLabel;
    private javax.swing.JLabel jazzLabelUsers;
    private javax.swing.JLabel klasikLabel;
    private javax.swing.JLabel klasikLabelUsers;
    private javax.swing.JLabel listAltAltAdd;
    private javax.swing.JLabel listAltAltAdd1;
    private javax.swing.JLabel listAltAltAdd2;
    private javax.swing.JLabel listAltAltAddOptional;
    private javax.swing.JLabel listeler;
    private javax.swing.JPanel listelerAltAltPanel;
    private javax.swing.JPanel listelerAltPanel;
    private javax.swing.JLabel listelerLabel5;
    private javax.swing.JPanel listelerPanel5;
    private javax.swing.JPanel muzikTurleriAltPanel;
    private javax.swing.JLabel muzikTurleriLabel5;
    private javax.swing.JPanel muzikTurleriPanel5;
    private javax.swing.JLabel muzikler;
    private javax.swing.JPanel muziklerAltPanel;
    private javax.swing.JTextField muziklerAltSongAdd;
    private javax.swing.JLabel muziklerAltadd;
    private javax.swing.JLabel muziklerAltaddOptional;
    private javax.swing.JTextField muzikleraltaltOptionalList;
    private javax.swing.JPanel myListAltPanel;
    private javax.swing.JPanel myListPanel;
    private javax.swing.JTable myTable;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel newListAddLabel;
    private javax.swing.JTextField newListLabel;
    private javax.swing.JPanel newListPanel;
    private javax.swing.JLabel popLabel;
    private javax.swing.JLabel popLabelUsers;
    private javax.swing.JLabel premornorm;
    private javax.swing.JPanel sanatcilarAltPanel;
    private javax.swing.JLabel sanatcilarLabel;
    private javax.swing.JLabel sanatcilerLabel5;
    private javax.swing.JPanel sanatcilerPanel;
    private javax.swing.JPanel sanatcilerPanel5;
    private javax.swing.JLabel sil;
    private javax.swing.JTextField silName;
    private javax.swing.JTextField silNameLiist;
    private javax.swing.JLabel silOther;
    private javax.swing.JLabel single;
    private javax.swing.JPanel singlePanel;
    private javax.swing.JTable singleTable;
    private javax.swing.JTable table;
    private javax.swing.JTable tableGenel;
    private javax.swing.JTable tableList;
    private javax.swing.JLabel tursuz;
    private javax.swing.JPanel tursuzPanel;
    private javax.swing.JTable tursuzTable;
    private javax.swing.JLabel ulkeLabel;
    // End of variables declaration//GEN-END:variables
}
