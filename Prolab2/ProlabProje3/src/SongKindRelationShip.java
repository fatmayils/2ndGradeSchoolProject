
import Models.KindModel;
import Models.SongKindModel;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author fatma
 */
public class SongKindRelationShip extends javax.swing.JFrame {

    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Connection connection = null;
    String query;
    DefaultTableModel model;

    public SongKindRelationShip() throws Exception {
        initComponents();
        this.getContentPane().setBackground(new Color(45, 52, 71));
        showInTableSong();
        showInTableKind();
        showInTableSongKind();
        this.setLocationRelativeTo(null);
        setLocationRelativeTo(null);
    }

    public void showInTableSongKind() throws Exception {
        ArrayList<SongKindModel> list = getSongKindList();
        model = (DefaultTableModel) songKindTable.getModel();
        Object[] row = new Object[3];
        for (int i = 0; i < list.size(); i++) {

            row[0] = list.get(i).getSongKindID();
            row[1] = list.get(i).getSongID();
            row[2] = list.get(i).getKindID();
            model.addRow(row);
        }

    }

    public ArrayList<SongKindModel> getSongKindList() throws Exception {
        ArrayList<SongKindModel> songKindsList = new ArrayList<SongKindModel>();
        connection = DatabaseConnection.connect();
        query = "Select * From song_kinds";
        Statement statement;
        ResultSet resulset;
        statement = connection.createStatement();
        resulset = statement.executeQuery(query);
        SongKindModel songKind;
        while (resulset.next()) {
            songKind = new SongKindModel(resulset.getInt("SongKindID"), resulset.getInt("SongID"), resulset.getInt("KindID"));
            songKindsList.add(songKind);
        }
        connection.close();
        return songKindsList;

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
        connection.close();
        return songsList;

    }

    public void showInTableKind() throws Exception {
        ArrayList<KindModel> list = getKindList();
        model = (DefaultTableModel) kindTable.getModel();
        Object[] row = new Object[2];
        for (int i = 0; i < list.size(); i++) {

            row[0] = list.get(i).getKindID();
            row[1] = list.get(i).getKindName();
            model.addRow(row);
        }

    }

    public ArrayList<KindModel> getKindList() throws Exception {
        ArrayList<KindModel> kindsList = new ArrayList<KindModel>();
        connection = DatabaseConnection.connect();
        query = "Select * From kinds";
        Statement statement;
        ResultSet resulset;
        statement = connection.createStatement();
        resulset = statement.executeQuery(query);
        KindModel kind;
        while (resulset.next()) {
            kind = new KindModel(resulset.getInt("KindID"), resulset.getString("KindName"));
            kindsList.add(kind);
        }
        connection.close();
        return kindsList;

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        songKindTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        kindTable = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        songTable = new javax.swing.JTable();
        previousPage = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        songKindID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        songID = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        kindID = new javax.swing.JTextField();
        add = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        update = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        songKindTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SongKindID", "SongID", "KindID"
            }
        ));
        jScrollPane1.setViewportView(songKindTable);

        kindTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "KindID", "KindName"
            }
        ));
        jScrollPane2.setViewportView(kindTable);

        songTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SongID", "SongName", "ViewCounts", "Time"
            }
        ));
        jScrollPane3.setViewportView(songTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

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
        jLabel1.setText("SongKindID");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("SongID");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("KindID");

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
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(82, 82, 82)
                                        .addComponent(jLabel1))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(88, 88, 88)
                                        .addComponent(jLabel2))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(100, 100, 100)
                                        .addComponent(jLabel3)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(delete, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(add, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(songID)
                                            .addComponent(songKindID)
                                            .addComponent(kindID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)))
                                    .addComponent(update, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(56, 56, 56))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(previousPage, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(songKindID, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(songID, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(kindID, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(previousPage, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void previousPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousPageActionPerformed
        this.dispose();
        this.setVisible(false);
        AdminMethods methods = new AdminMethods();
        methods.setVisible(true);
    }//GEN-LAST:event_previousPageActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        if ((!songID.getText().isEmpty()) && (!kindID.getText().isEmpty())) {
            try {
                connection = DatabaseConnection.connect();
                query = "insert into song_kinds(SongID,KindID) values(?,?)";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, Integer.valueOf(songID.getText()));
                preparedStatement.setInt(2, Integer.valueOf(kindID.getText()));
                preparedStatement.execute();
                connection.close();
                model = (DefaultTableModel) songKindTable.getModel();
                model.setRowCount(0);
                showInTableSongKind();
                connection.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "added invalid");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please fill songid and kindid");
        }
    }//GEN-LAST:event_addActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        if (!songKindID.getText().isEmpty()) {
            try {
                connection = DatabaseConnection.connect();
                query = "DELETE FROM song_kinds WHERE SongKindID=?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, Integer.valueOf(songKindID.getText()));
                preparedStatement.executeUpdate();
                connection.close();
                model = (DefaultTableModel) songKindTable.getModel();
                model.setRowCount(0);
                showInTableSongKind();
                connection.close();
            } catch (Exception ex) {
                Logger.getLogger(SingerSongRelationShip.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please choose an id");
        }
    }//GEN-LAST:event_deleteActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        if (!songKindID.getText().isEmpty()) {
            try {
                connection = DatabaseConnection.connect();
                query = "update song_kinds set SongID=? , KindID=?  where SongKindID='" + Integer.valueOf(songKindID.getText()) + "'";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, Integer.valueOf(songID.getText()));
                preparedStatement.setInt(2, Integer.valueOf(kindID.getText()));
                preparedStatement.executeUpdate();
                preparedStatement.close();
                model = (DefaultTableModel) songKindTable.getModel();
                model.setRowCount(0);
                showInTableSongKind();
                connection.close();
            } catch (Exception ex) {
                Logger.getLogger(SingerSongRelationShip.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please choose an id");
        }
    }//GEN-LAST:event_updateActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new SongKindRelationShip().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(SongKindRelationShip.class.getName()).log(Level.SEVERE, null, ex);
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField kindID;
    private javax.swing.JTable kindTable;
    private javax.swing.JButton previousPage;
    private javax.swing.JTextField songID;
    private javax.swing.JTextField songKindID;
    private javax.swing.JTable songKindTable;
    private javax.swing.JTable songTable;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
}
