
import Models.AlbumModel;
import Models.SongAlbumModel;
import Models.SongModel;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class SongAlbumRelationShip extends javax.swing.JFrame {

    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Connection connection = null;
    String query;
    DefaultTableModel model;
    Statement statement;

    public SongAlbumRelationShip() throws Exception {
        initComponents();
        this.getContentPane().setBackground(new Color(45, 52, 71));
        showInTableSong();
        showInTableAlbum();
        showInTableSongAlbum();
        this.setLocationRelativeTo(null);
        setLocationRelativeTo(null);
    }

    public void showInTableSongAlbum() throws Exception {
        ArrayList<SongAlbumModel> list = getSongAlbumList();
        model = (DefaultTableModel) songAlbumTable.getModel();
        Object[] row = new Object[3];
        for (int i = 0; i < list.size(); i++) {

            row[0] = list.get(i).getSongAlbumID();
            row[1] = list.get(i).getSongID();
            row[2] = list.get(i).getAlbumID();
            model.addRow(row);
        }

    }

    public ArrayList<SongAlbumModel> getSongAlbumList() throws Exception {
        ArrayList<SongAlbumModel> songAlbumsList = new ArrayList<SongAlbumModel>();
        connection = DatabaseConnection.connect();
        query = "Select * From song_albums";
        Statement statement;
        ResultSet resulset;
        statement = connection.createStatement();
        resulset = statement.executeQuery(query);
        SongAlbumModel songAlbum;
        while (resulset.next()) {
            songAlbum = new SongAlbumModel(resulset.getInt("SongAlbumID"), resulset.getInt("SongID"), resulset.getInt("AlbumID"));
            songAlbumsList.add(songAlbum);
        }
        return songAlbumsList;

    }

    public void showInTableSong() throws Exception {
        ArrayList<SongModel> list = getSongList();
        model = (DefaultTableModel) songTable.getModel();
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

    public ArrayList<SongModel> getSongList() throws Exception {
        ArrayList<SongModel> songsList = new ArrayList<SongModel>();
        connection = DatabaseConnection.connect();
        query = "Select s.SongID,s.ViewCount,s.SongName,s.Time,k.KindName From songs s,kinds k,song_kinds sk"
                + " where s.SongID=sk.SongID and sk.KindID=k.KindID";
        Statement statement;
        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);
        SongModel song;
        while (resultSet.next()) {
            song = new SongModel(resultSet.getInt("SongID"), resultSet.getInt("ViewCount"), resultSet.getString("SongName"), resultSet.getString("KindName"), resultSet.getDouble("Time"));
            songsList.add(song);
        }
        return songsList;

    }

    public void showInTableAlbum() throws Exception {
        ArrayList<AlbumModel> list = getAlbumList();
        model = (DefaultTableModel) albumTable.getModel();
        Object[] row = new Object[4];
        for (int i = 0; i < list.size(); i++) {

            row[0] = list.get(i).getAlbumID();
            row[1] = list.get(i).getAlbumName();
            row[2] = list.get(i).getAlbumDate();
            row[3] = list.get(i).getAlbumKind();
            model.addRow(row);
        }

    }

    public ArrayList<AlbumModel> getAlbumList() throws Exception {
        ArrayList<AlbumModel> albumsList = new ArrayList<AlbumModel>();
        connection = DatabaseConnection.connect();
        query = "Select * From albums";
        Statement statement;
        ResultSet resulset;
        statement = connection.createStatement();
        resulset = statement.executeQuery(query);
        AlbumModel album;
        while (resulset.next()) {
            album = new AlbumModel(resulset.getInt("AlbumID"), resulset.getString("AlbumName"), resulset.getString("Date"), resulset.getString("AlbumKind"));
            albumsList.add(album);
        }
        return albumsList;

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        songTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        albumTable = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        songAlbumTable = new javax.swing.JTable();
        previousPage = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        songID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        albumID = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        songAlbumID = new javax.swing.JTextField();
        add = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        update = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        songTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SongID", "SongName", "ViewCounts", "Kind", "Time"
            }
        ));
        jScrollPane3.setViewportView(songTable);

        albumTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "AlbumID", "AlbumName", "AlbumDate", "Kind"
            }
        ));
        jScrollPane2.setViewportView(albumTable);
        if (albumTable.getColumnModel().getColumnCount() > 0) {
            albumTable.getColumnModel().getColumn(0).setPreferredWidth(60);
            albumTable.getColumnModel().getColumn(0).setMaxWidth(60);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3)
                    .addComponent(jScrollPane2))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        songAlbumTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SongAlbumID", "SongID", "AlbumID"
            }
        ));
        jScrollPane1.setViewportView(songAlbumTable);

        previousPage.setBackground(new java.awt.Color(45, 52, 71));
        previousPage.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        previousPage.setForeground(new java.awt.Color(255, 255, 255));
        previousPage.setText("Prev");
        previousPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousPageActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("SongID");

        songID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                songIDActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("AlbumID");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("SongAlbumID");

        add.setBackground(new java.awt.Color(45, 52, 71));
        add.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        add.setForeground(new java.awt.Color(255, 255, 255));
        add.setText("ADD");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        delete.setBackground(new java.awt.Color(45, 52, 71));
        delete.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        delete.setForeground(new java.awt.Color(255, 255, 255));
        delete.setText("DELETE");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        update.setBackground(new java.awt.Color(45, 52, 71));
        update.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        update.setForeground(new java.awt.Color(255, 255, 255));
        update.setText("UPDATE");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(previousPage, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(add, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(delete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(update, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                                    .addComponent(songAlbumID)
                                    .addComponent(songID)
                                    .addComponent(albumID)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(67, 67, 67)
                                .addComponent(jLabel2))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(jLabel3)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(previousPage, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(78, 78, 78)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(songAlbumID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(songID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addGap(26, 26, 26)
                                .addComponent(albumID, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(jScrollPane1))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(63, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void previousPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousPageActionPerformed
        this.dispose();
        this.setVisible(false);
        AdminMethods methods = new AdminMethods();
        methods.setVisible(true);
    }//GEN-LAST:event_previousPageActionPerformed

    private void songIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_songIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_songIDActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        if ((!albumID.getText().isEmpty()) && (!songID.getText().isEmpty())) {
            try {
                String kindsong = null, kindalbum = null;
                connection = DatabaseConnection.connect();
                query = "select AlbumKind from albums where AlbumID='" + Integer.valueOf(albumID.getText()) + "'";
                statement = connection.createStatement();
                resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    kindalbum = resultSet.getString("AlbumKind");
                }
                connection = DatabaseConnection.connect();
                query = "select k.KindName from songs s,song_kinds sk,kinds k"
                        + " where s.SongID=sk.SongID and sk.KindID=k.KindID"
                        + " and s.SongID='" + Integer.valueOf(songID.getText()) + "'";
                statement = connection.createStatement();
                resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    kindsong = resultSet.getString("KindName");
                }
                if (kindsong.equals(kindalbum)) {
                    connection = DatabaseConnection.connect();
                    query = "insert into song_albums(SongID,AlbumID) values(?,?)";
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setInt(1, Integer.valueOf(songID.getText()));
                    preparedStatement.setInt(2, Integer.valueOf(albumID.getText()));
                    preparedStatement.execute();
                    connection.close();
                    model = (DefaultTableModel) songAlbumTable.getModel();
                    model.setRowCount(0);
                    showInTableSongAlbum();
                } else {
                    JOptionPane.showMessageDialog(this, "SongKind!=AlbumKind");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "SongID is uniq");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please fill albumid and songid");
        }
    }//GEN-LAST:event_addActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        if (!songAlbumID.getText().isEmpty()) {
            try {
                connection = DatabaseConnection.connect();
                query = "DELETE FROM song_albums WHERE SongAlbumID=?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, Integer.valueOf(songAlbumID.getText()));
                preparedStatement.executeUpdate();
                connection.close();
                model = (DefaultTableModel) songAlbumTable.getModel();
                model.setRowCount(0);
                showInTableSongAlbum();
            } catch (Exception ex) {
                Logger.getLogger(SingerSongRelationShip.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please choose a id");
        }
    }//GEN-LAST:event_deleteActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        if (!songAlbumID.getText().isEmpty()) {
            try {
                connection = DatabaseConnection.connect();
                query = "update song_albums set SongID=? , AlbumID=?  where SongAlbumID='" + Integer.valueOf(songAlbumID.getText()) + "'";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, Integer.valueOf(songID.getText()));
                preparedStatement.setInt(2, Integer.valueOf(albumID.getText()));
                preparedStatement.executeUpdate();
                preparedStatement.close();
                model = (DefaultTableModel) songAlbumTable.getModel();
                model.setRowCount(0);
                showInTableSongAlbum();
                connection.close();
            } catch (Exception ex) {
                Logger.getLogger(SingerSongRelationShip.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please choose a id");
        }
    }//GEN-LAST:event_updateActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SongAlbumRelationShip.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SongAlbumRelationShip.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SongAlbumRelationShip.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SongAlbumRelationShip.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new SongAlbumRelationShip().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(SongAlbumRelationShip.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add;
    private javax.swing.JTextField albumID;
    private javax.swing.JTable albumTable;
    private javax.swing.JButton delete;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton previousPage;
    private javax.swing.JTextField songAlbumID;
    private javax.swing.JTable songAlbumTable;
    private javax.swing.JTextField songID;
    private javax.swing.JTable songTable;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
}
