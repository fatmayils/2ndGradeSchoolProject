
import Models.AlbumModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import java.awt.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class AdminAlbumMethods extends javax.swing.JFrame {

    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Connection connection = null;
    String query;
    DefaultTableModel model;

    public AdminAlbumMethods() throws Exception {
        initComponents();
        this.getContentPane().setBackground(new Color(45, 52, 71));
        showInTable();
        this.setLocationRelativeTo(null);
        setLocationRelativeTo(null);
    }

    //tablomuzun sütunlarına verileri aktarıyoruz
    public void showInTable() throws Exception {
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

    //veritabanından verileri çekip model listemize atıyoruz.Daha sonra bunu üstteki metodla tabloya döküyoruz.
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

        previousPage = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        albumTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        albumName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        albumDate = new javax.swing.JTextField();
        albumAdd = new javax.swing.JButton();
        albumDelete = new javax.swing.JButton();
        albumUpdate = new javax.swing.JButton();
        albumID = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        albumKind = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

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

        albumTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "AlbumID", "AlbumName", "AlbumDate", "Kind"
            }
        ));
        jScrollPane1.setViewportView(albumTable);
        if (albumTable.getColumnModel().getColumnCount() > 0) {
            albumTable.getColumnModel().getColumn(0).setMaxWidth(60);
        }

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("NAME");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("DATE");

        albumAdd.setBackground(new java.awt.Color(45, 52, 71));
        albumAdd.setForeground(new java.awt.Color(255, 255, 255));
        albumAdd.setText("ADD");
        albumAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                albumAddActionPerformed(evt);
            }
        });

        albumDelete.setBackground(new java.awt.Color(45, 52, 71));
        albumDelete.setForeground(new java.awt.Color(255, 255, 255));
        albumDelete.setText("DELETE");
        albumDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                albumDeleteActionPerformed(evt);
            }
        });

        albumUpdate.setBackground(new java.awt.Color(45, 52, 71));
        albumUpdate.setForeground(new java.awt.Color(255, 255, 255));
        albumUpdate.setText("UPDATE");
        albumUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                albumUpdateActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("ID(delete-update)");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Kind");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel4)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(albumID, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addGap(54, 54, 54)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(albumKind, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(albumDate, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(albumName))))
                            .addGap(32, 32, 32)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(albumDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(albumAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(albumUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38))))
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(previousPage, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(previousPage, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(albumID, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(albumName, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(albumKind, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(albumDate, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(40, 40, 40)
                        .addComponent(albumAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(albumDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(albumUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Ekleme yaparken album adını uniq olarak düşündüm.Aynı albüm adından varsa ekleme yapmıyor.
    private void albumAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_albumAddActionPerformed
        if ((!albumName.getText().isEmpty()) && (!albumDate.getText().isEmpty()) && (!albumKind.getText().isEmpty())) {
            try {
                connection = DatabaseConnection.connect();
            } catch (Exception ex) {
                Logger.getLogger(AdminAlbumMethods.class.getName()).log(Level.SEVERE, null, ex);
            }
            int control = 0;
            try {

                query = "select AlbumName from albums where AlbumName='" + albumName.getText() + "'";

                preparedStatement = connection.prepareStatement(query);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    JOptionPane.showMessageDialog(this, "This album already exist");
                    control++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (control == 0) {
                try {
                    try {
                        query = " insert into albums (AlbumName, Date,AlbumKind)"
                                + " values (?, ?,?)";
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, albumName.getText());
                        preparedStatement.setString(2, albumDate.getText());
                        preparedStatement.setString(3, albumKind.getText());
                        preparedStatement.execute();
                    } catch (SQLException ex) {
                        Logger.getLogger(AdminAlbumMethods.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    model = (DefaultTableModel) albumTable.getModel();
                    model.setRowCount(0);
                    showInTable();
                } catch (Exception ex) {
                    Logger.getLogger(AdminAlbumMethods.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please  fill the all content");
        }
    }//GEN-LAST:event_albumAddActionPerformed
    //album silerken de silincek id nin varlığı kontrol ediliyor,varsa siliniyor
    private void albumDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_albumDeleteActionPerformed
        try {

            int control = 0;
            connection = DatabaseConnection.connect();
            query = "Select AlbumID from albums where AlbumID='" + Integer.valueOf(albumID.getText()) + "' ";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                control++;
            }
            if (control == 0) {
                JOptionPane.showMessageDialog(this, "This id not exist");

            }

            query = "DELETE FROM albums WHERE AlbumID=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, Integer.valueOf(albumID.getText()));
            preparedStatement.executeUpdate();
            connection.close();
            model = (DefaultTableModel) albumTable.getModel();
            model.setRowCount(0);
            showInTable();
        } catch (Exception e) {

            JOptionPane.showMessageDialog(this, "This id not exist");
        }
    }//GEN-LAST:event_albumDeleteActionPerformed
//album güncellemesi yaparken güncellencek albümün id sine göre güncelleme yapıyoruz,varsa güncelliyor
    private void albumUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_albumUpdateActionPerformed
        try {
            int control = 0;
            try {
                connection = DatabaseConnection.connect();
            } catch (Exception ex) {
                Logger.getLogger(AdminAlbumMethods.class.getName()).log(Level.SEVERE, null, ex);
            }
            query = "Select AlbumID from albums where AlbumID='" + Integer.valueOf(albumID.getText()) + "' ";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                control++;
            }
            if (control == 0) {
                JOptionPane.showMessageDialog(this, "This id not exist");

            }
            if (!albumID.getText().isEmpty() && (!albumName.getText().isEmpty()) && (!albumDate.getText().isEmpty()) && (!albumKind.getText().isEmpty())) {
                try {
                    query = "update albums set AlbumName=? where AlbumId='" + Integer.valueOf(albumID.getText()) + "'";
                    PreparedStatement ps = connection.prepareStatement(query);
                    ps.setString(1, albumName.getText());
                    ps.executeUpdate();
                    ps.close();
                    model = (DefaultTableModel) albumTable.getModel();
                    model.setRowCount(0);
                    try {
                        showInTable();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "This id not exist");
                    }
                    connection.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "This id not exist");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please fill all the content");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminAlbumMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_albumUpdateActionPerformed
    //önceki sayfaya geçerken kullanılıyor
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
                    new AdminAlbumMethods().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(AdminAlbumMethods.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton albumAdd;
    private javax.swing.JTextField albumDate;
    private javax.swing.JButton albumDelete;
    private javax.swing.JTextField albumID;
    private javax.swing.JTextField albumKind;
    private javax.swing.JTextField albumName;
    private javax.swing.JTable albumTable;
    private javax.swing.JButton albumUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton previousPage;
    // End of variables declaration//GEN-END:variables
}
