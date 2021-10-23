
import Models.SingerModel;
import Models.SingerSongModel;
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

public class SingerSongRelationShip extends javax.swing.JFrame {

    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Connection connection = null;
    String query;
    DefaultTableModel model;

    public SingerSongRelationShip() throws Exception {
        initComponents();
        this.getContentPane().setBackground(new Color(45, 52, 71));
        showInTableSong();
        showInTableSinger();
        showInTableSingerSong();
        this.setLocationRelativeTo(null);
        setLocationRelativeTo(null);
    }

    public void showInTableSingerSong() throws Exception {
        ArrayList<SingerSongModel> list = getSingerSongList();
        model = (DefaultTableModel) singerSongTable.getModel();
        Object[] row = new Object[3];
        for (int i = 0; i < list.size(); i++) {

            row[0] = list.get(i).getSingerSongID();
            row[1] = list.get(i).getSingerID();
            row[2] = list.get(i).getSongID();
            model.addRow(row);
        }

    }

    public ArrayList<SingerSongModel> getSingerSongList() throws Exception {
        ArrayList<SingerSongModel> singerSongsList = new ArrayList<SingerSongModel>();
        connection = DatabaseConnection.connect();
        String query = "Select * From singer_songs";
        Statement statement;
        ResultSet resulset;
        statement = connection.createStatement();
        resulset = statement.executeQuery(query);
        SingerSongModel singerSong;
        while (resulset.next()) {
            singerSong = new SingerSongModel(resulset.getInt("SingerSongID"), resulset.getInt("SingerID"), resulset.getInt("SongID"));
            singerSongsList.add(singerSong);
        }
        return singerSongsList;

    }

    public void showInTableSong() throws Exception {
        ArrayList<SongModel> list = getSongList();
        model = (DefaultTableModel) songTable.getModel();
        Object[] row = new Object[4];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getSongID();
            row[1] = list.get(i).getSongName();
            row[2] = list.get(i).getViewsCounts();
            row[3] = list.get(i).getTime();
            model.addRow(row);
        }

    }

    public ArrayList<SongModel> getSongList() throws Exception {
        ArrayList<SongModel> songsList = new ArrayList<SongModel>();
        connection = DatabaseConnection.connect();
        query = "Select * From songs";
        Statement statement;
        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);
        SongModel song;
        while (resultSet.next()) {
            song = new SongModel(resultSet.getInt("SongID"), resultSet.getInt("ViewCount"), resultSet.getString("SongName"), resultSet.getDouble("Time"));
            songsList.add(song);
        }
        return songsList;

    }

    public void showInTableSinger() throws Exception {
        ArrayList<SingerModel> list = getSingerList();
        model = (DefaultTableModel) singerTable.getModel();
        Object[] row = new Object[3];
        for (int i = 0; i < list.size(); i++) {

            row[0] = list.get(i).getSingerID();
            row[1] = list.get(i).getSingerName();
            row[2] = list.get(i).getSingerCountry();
            model.addRow(row);
        }

    }

    public ArrayList<SingerModel> getSingerList() throws Exception {
        ArrayList<SingerModel> singersList = new ArrayList<SingerModel>();
        connection = DatabaseConnection.connect();
        String query = "Select * From singers";
        Statement statement;
        ResultSet resulset;
        statement = connection.createStatement();
        resulset = statement.executeQuery(query);
        SingerModel singer;
        while (resulset.next()) {
            singer = new SingerModel(resulset.getInt("SingerID"), resulset.getString("SingerName"), resulset.getString("SingerCountry"));
            singersList.add(singer);
        }
        return singersList;

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        songTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        singerTable = new javax.swing.JTable();
        add = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        update = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        singerSongTable = new javax.swing.JTable();
        songID = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        singerID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        singerSongID = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        previousPage = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        songTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SongID", "SongName", "ViewCounts", "Time"
            }
        ));
        jScrollPane1.setViewportView(songTable);
        if (songTable.getColumnModel().getColumnCount() > 0) {
            songTable.getColumnModel().getColumn(0).setMaxWidth(60);
            songTable.getColumnModel().getColumn(3).setMaxWidth(75);
        }

        singerTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SingerID", "Singer Name", "Singer Country"
            }
        ));
        jScrollPane2.setViewportView(singerTable);
        if (singerTable.getColumnModel().getColumnCount() > 0) {
            singerTable.getColumnModel().getColumn(0).setMaxWidth(60);
        }

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

        singerSongTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SingerSongID", "SingerID", "SongID"
            }
        ));
        jScrollPane3.setViewportView(singerSongTable);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("SongID");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("SingerID");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("SingerSongID(delete-update)");

        previousPage.setBackground(new java.awt.Color(45, 52, 71));
        previousPage.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        previousPage.setForeground(new java.awt.Color(255, 255, 255));
        previousPage.setText("Prev");
        previousPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousPageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(32, 32, 32)
                .addComponent(singerID, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(songID, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(137, 137, 137))
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(64, 64, 64)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(add, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(delete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(update, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                            .addComponent(singerSongID))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(previousPage, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(previousPage, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(258, 258, 258)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(songID, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(singerSongID, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(186, 186, 186)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(singerID, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(10, 10, 10))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        if ((!singerID.getText().isEmpty()) && (!songID.getText().isEmpty())) {
            try {
                connection = DatabaseConnection.connect();

                query = "insert into singer_songs(SingerID,SongID) values(?,?)";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, Integer.valueOf(singerID.getText()));
                preparedStatement.setInt(2, Integer.valueOf(songID.getText()));
                preparedStatement.execute();
                connection.close();
                model = (DefaultTableModel) singerSongTable.getModel();
                model.setRowCount(0);
                showInTableSingerSong();
            } catch (Exception ex) {
                Logger.getLogger(SingerSongRelationShip.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please fill all the blank");
        }
    }//GEN-LAST:event_addActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        if ((!singerSongID.getText().isEmpty())) {
            try {
                connection = DatabaseConnection.connect();
                query = "DELETE FROM singer_songs WHERE SingerSongID=?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, Integer.valueOf(singerSongID.getText()));
                preparedStatement.executeUpdate();
                connection.close();
                model = (DefaultTableModel) singerSongTable.getModel();
                model.setRowCount(0);
                showInTableSingerSong();
            } catch (Exception ex) {
                Logger.getLogger(SingerSongRelationShip.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please choose a id");
        }
    }//GEN-LAST:event_deleteActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        if ((!singerSongID.getText().isEmpty())) {
            try {
                if ((!singerID.getText().isEmpty()) && (!songID.getText().isEmpty())) {
                    connection = DatabaseConnection.connect();
                    query = "update singer_songs set SingerID=? , SongID=?  where SingerSongID='" + Integer.valueOf(singerSongID.getText()) + "'";
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setInt(1, Integer.valueOf(singerID.getText()));
                    preparedStatement.setInt(2, Integer.valueOf(songID.getText()));
                    preparedStatement.executeUpdate();
                    preparedStatement.close();
                    model = (DefaultTableModel) singerSongTable.getModel();
                    model.setRowCount(0);
                    showInTableSingerSong();
                    connection.close();
                } else {
                    JOptionPane.showMessageDialog(this, "Please fill all blank");
                }
            } catch (Exception ex) {
                Logger.getLogger(SingerSongRelationShip.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please choose a id");

        }
    }//GEN-LAST:event_updateActionPerformed

    private void previousPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousPageActionPerformed
        this.dispose();
        this.setVisible(false);
        AdminMethods methods = new AdminMethods();
        methods.setVisible(true);
    }//GEN-LAST:event_previousPageActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new SingerSongRelationShip().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(SingerSongRelationShip.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add;
    private javax.swing.JButton delete;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton previousPage;
    private javax.swing.JTextField singerID;
    private javax.swing.JTextField singerSongID;
    private javax.swing.JTable singerSongTable;
    private javax.swing.JTable singerTable;
    private javax.swing.JTextField songID;
    private javax.swing.JTable songTable;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
}
