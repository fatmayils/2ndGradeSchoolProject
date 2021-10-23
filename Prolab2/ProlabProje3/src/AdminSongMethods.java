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

public class AdminSongMethods extends javax.swing.JFrame {

    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Connection connection = null;
    String query;
    DefaultTableModel model;

    public AdminSongMethods() throws Exception {
        initComponents();
        this.getContentPane().setBackground(new Color(45, 52, 71));
        showInTableSong();
        this.setLocationRelativeTo(null);
        setLocationRelativeTo(null);
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        previousPage = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        songTable = new javax.swing.JTable();
        songID = new javax.swing.JTextField();
        songName = new javax.swing.JTextField();
        time = new javax.swing.JTextField();
        songUpdate = new javax.swing.JButton();
        songDelete = new javax.swing.JButton();
        songAdd = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        updateCount = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        previousPage.setBackground(new java.awt.Color(45, 52, 71));
        previousPage.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        previousPage.setForeground(new java.awt.Color(255, 255, 255));
        previousPage.setText("Prev");
        previousPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousPageActionPerformed(evt);
            }
        });

        songTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SongID", "SongName", "ViewCounts", "Time"
            }
        ));
        jScrollPane1.setViewportView(songTable);

        songName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                songNameActionPerformed(evt);
            }
        });

        songUpdate.setBackground(new java.awt.Color(45, 52, 71));
        songUpdate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        songUpdate.setForeground(new java.awt.Color(255, 255, 255));
        songUpdate.setText("UPDATE");
        songUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                songUpdateActionPerformed(evt);
            }
        });

        songDelete.setBackground(new java.awt.Color(45, 52, 71));
        songDelete.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        songDelete.setForeground(new java.awt.Color(255, 255, 255));
        songDelete.setText("DELETE");
        songDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                songDeleteActionPerformed(evt);
            }
        });

        songAdd.setBackground(new java.awt.Color(45, 52, 71));
        songAdd.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        songAdd.setForeground(new java.awt.Color(255, 255, 255));
        songAdd.setText("ADD");
        songAdd.setFocusTraversalPolicyProvider(true);
        songAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                songAddActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("SongID");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("SongName");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Time");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("(Only for updates)  viewcount");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(79, 79, 79)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(songID, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(79, 79, 79)
                                        .addComponent(jLabel5)
                                        .addGap(37, 37, 37))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel2)
                                        .addGap(8, 8, 8)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(songAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(songDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(songUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(songName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(time, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(71, 71, 71)
                                        .addComponent(updateCount, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel3)))
                                .addGap(113, 113, 113))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(previousPage, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(previousPage, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(songID, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(songName, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(time, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(songUpdate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(songDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(updateCount, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(songAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 27, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void previousPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousPageActionPerformed
        this.dispose();
        this.setVisible(false);
        AdminMethods methods = new AdminMethods();
        methods.setVisible(true);
    }//GEN-LAST:event_previousPageActionPerformed

    private void songDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_songDeleteActionPerformed
        if (!songID.getText().isEmpty()) {
            try {
                int control = 0;
                connection = DatabaseConnection.connect();
                query = "Select SongID from songs where SongID='" + Integer.valueOf(songID.getText()) + "' ";
                preparedStatement = connection.prepareStatement(query);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    control++;
                }
                if (control == 0) {
                    JOptionPane.showMessageDialog(this, "This id not exist");

                }

                query = "DELETE FROM songs WHERE SongID=?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, Integer.valueOf(songID.getText()));
                preparedStatement.executeUpdate();
                connection.close();
                model = (DefaultTableModel) songTable.getModel();
                model.setRowCount(0);
                showInTableSong();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "This id not exist");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please choose an id");
        }
    }//GEN-LAST:event_songDeleteActionPerformed

    private void songAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_songAddActionPerformed
        if ((!songName.getText().isEmpty()) && (!time.getText().isEmpty())) {
            try {
                connection = DatabaseConnection.connect();
                int control = 0;
                query = "select SongName from songs where SongName='" + songName.getText() + "'";
                preparedStatement = connection.prepareStatement(query);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    control++;
                    JOptionPane.showMessageDialog(this, "This song already exist");
                }
                if (control == 0) {
                    try {
                        query = " insert into songs (SongName,Time)"
                                + " values (?,?)";
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, songName.getText());
                        preparedStatement.setDouble(2, Double.valueOf(time.getText()));
                        preparedStatement.execute();
                        connection.close();
                        model = (DefaultTableModel) songTable.getModel();
                        model.setRowCount(0);
                        showInTableSong();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "add not success");

                    }

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please  fill the all content");
        }
    }//GEN-LAST:event_songAddActionPerformed

    private void songUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_songUpdateActionPerformed
        if (!songID.getText().isEmpty()) {
            try {
                connection = DatabaseConnection.connect();
                int control = 0;
                query = "select SongName from songs where SongName='" + songName.getText() + "'"
                        + " and songID!='" + Integer.valueOf(songID.getText()) + "'";
                preparedStatement = connection.prepareStatement(query);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    control++;
                    JOptionPane.showMessageDialog(this, "This song already exist");
                }
                connection.close();
                if (control == 0) {
                    connection = DatabaseConnection.connect();
                    query = "update songs set SongName=? , Time=? ,ViewCount=? where SongId='" + Integer.valueOf(songID.getText()) + "'";
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, songName.getText());
                    preparedStatement.setDouble(2, Double.valueOf(time.getText()));
                    preparedStatement.setInt(3, Integer.valueOf(updateCount.getText()));
                    preparedStatement.executeUpdate();
                    preparedStatement.close();
                    connection.close();
                    model = (DefaultTableModel) songTable.getModel();
                    model.setRowCount(0);
                    try {
                        showInTableSong();
                    } catch (Exception ex) {

                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Update invalid");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please choose an id");
        }
    }//GEN-LAST:event_songUpdateActionPerformed

    private void songNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_songNameActionPerformed
   
    }//GEN-LAST:event_songNameActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new AdminSongMethods().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(AdminSongMethods.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton previousPage;
    private javax.swing.JButton songAdd;
    private javax.swing.JButton songDelete;
    private javax.swing.JTextField songID;
    private javax.swing.JTextField songName;
    private javax.swing.JTable songTable;
    private javax.swing.JButton songUpdate;
    private javax.swing.JTextField time;
    private javax.swing.JTextField updateCount;
    // End of variables declaration//GEN-END:variables
}
