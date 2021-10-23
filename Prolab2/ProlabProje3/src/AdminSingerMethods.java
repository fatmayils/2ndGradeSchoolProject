
import Models.SingerModel;
import java.sql.*;
import java.awt.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class AdminSingerMethods extends javax.swing.JFrame {

    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Connection connection = null;
    String query;
    DefaultTableModel model;

    public AdminSingerMethods() throws Exception {
        initComponents();
         this.getContentPane().setBackground(new Color(45, 52, 71));
        showInTable();
        this.setLocationRelativeTo(null);
        setLocationRelativeTo(null);
    }

    //şarkıcıları tabloya yazdırıyor
    public void showInTable() throws Exception {
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

    //veritabanından şarkıları çekiyor,listeye atıyor
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
        singerTable = new javax.swing.JTable();
        singerName = new javax.swing.JTextField();
        singerCountry = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        singerAdd = new javax.swing.JButton();
        singerDelete = new javax.swing.JButton();
        singerUpdate = new javax.swing.JButton();
        singerID = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        previousPage = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        singerTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SingerID", "Singer Name", "Singer Country"
            }
        ));
        jScrollPane1.setViewportView(singerTable);
        if (singerTable.getColumnModel().getColumnCount() > 0) {
            singerTable.getColumnModel().getColumn(0).setMaxWidth(60);
        }

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("NAME");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("COUNTRY");

        singerAdd.setBackground(new java.awt.Color(45, 52, 71));
        singerAdd.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        singerAdd.setForeground(new java.awt.Color(255, 255, 255));
        singerAdd.setText("ADD");
        singerAdd.setHideActionText(true);
        singerAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                singerAddActionPerformed(evt);
            }
        });

        singerDelete.setBackground(new java.awt.Color(45, 52, 71));
        singerDelete.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        singerDelete.setForeground(new java.awt.Color(255, 255, 255));
        singerDelete.setText("DELETE");
        singerDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                singerDeleteActionPerformed(evt);
            }
        });

        singerUpdate.setBackground(new java.awt.Color(45, 52, 71));
        singerUpdate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        singerUpdate.setForeground(new java.awt.Color(255, 255, 255));
        singerUpdate.setText("UPDATE");
        singerUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                singerUpdateActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("ID(delete-update)");

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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(singerID, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(singerCountry, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(singerUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(singerDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(singerAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(singerName, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(50, 50, 50))
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(previousPage, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(previousPage, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(singerName, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(singerCountry, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addComponent(singerAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(singerID, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(singerDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(singerUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //şarkı ekliyor,şarkıcı adını uniq olarak düşündüm,aynı şarkıcı adından varsa ekleme yapmıyor
    private void singerAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_singerAddActionPerformed
        if ((!singerName.getText().isEmpty()) && (!singerCountry.getText().isEmpty())) {
            try {
                connection = DatabaseConnection.connect();
                String query;
                int control = 0;
                try {
                    query = "select SingerName from singers where SingerName='" + singerName.getText() + "'";
                    preparedStatement = connection.prepareStatement(query);
                    resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        JOptionPane.showMessageDialog(this, "This singer already exist");
                        control++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (control == 0) {
                    try {
                        query = " insert into singers (SingerName, SingerCountry)"
                                + " values (?, ?)";
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, singerName.getText());
                        preparedStatement.setString(2, singerCountry.getText());
                        preparedStatement.execute();
                        connection.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "This singer already exist");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please fill all the content");
        }
        try {
            model = (DefaultTableModel) singerTable.getModel();
            model.setRowCount(0);
            showInTable();
        } catch (Exception ex) {
            Logger.getLogger(AdminSingerMethods.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_singerAddActionPerformed

    //şarkıcı siliyor id ye göre,o id yoksa silmiyor
    private void singerDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_singerDeleteActionPerformed
        try {

            int control = 0;
            connection = DatabaseConnection.connect();
            query = "Select SingerID from singers where SingerID='" + Integer.valueOf(singerID.getText()) + "' ";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                control++;
            }
            if (control == 0) {

                JOptionPane.showMessageDialog(this, "This id not exist");

            }

            query = "DELETE FROM singers WHERE SingerID=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, Integer.valueOf(singerID.getText()));
            preparedStatement.executeUpdate();
            connection.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "This id not exist");
        }
        try {
            model = (DefaultTableModel) singerTable.getModel();
            model.setRowCount(0);
            showInTable();
        } catch (Exception ex) {
            Logger.getLogger(AdminSingerMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_singerDeleteActionPerformed

    //verilen id ye göre şarkıcıyı günceliyor,o id yoksa güncelleme yapmıyor
    private void singerUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_singerUpdateActionPerformed
        if (!singerID.getText().isEmpty() && (!singerName.getText().isEmpty()) && (!singerCountry.getText().isEmpty())) {
            int control = 0;
            try {
                try {
                    connection = DatabaseConnection.connect();
                } catch (Exception ex) {
                    Logger.getLogger(AdminSingerMethods.class.getName()).log(Level.SEVERE, null, ex);
                }
                query = "Select SingerID from singers where SingerID='" + Integer.valueOf(singerID.getText()) + "' ";
                preparedStatement = connection.prepareStatement(query);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    control++;
                }
                if (control == 0) {

                    JOptionPane.showMessageDialog(this, "This id not exist");

                }
                query = "update singers set SingerName=? , SingerCountry=?  where SingerID='" + Integer.valueOf(singerID.getText()) + "'";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, singerName.getText());
                preparedStatement.setString(2, singerCountry.getText());
                preparedStatement.executeUpdate();
                preparedStatement.close();
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please fill all the content");
        }
        try {
            model = (DefaultTableModel) singerTable.getModel();
            model.setRowCount(0);
            showInTable();
        } catch (Exception ex) {
            Logger.getLogger(AdminSingerMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_singerUpdateActionPerformed

    //önceki sayfaya dönmeyi sağlıyor
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
                    new AdminSingerMethods().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(AdminSingerMethods.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton previousPage;
    private javax.swing.JButton singerAdd;
    private javax.swing.JTextField singerCountry;
    private javax.swing.JButton singerDelete;
    private javax.swing.JTextField singerID;
    private javax.swing.JTextField singerName;
    private javax.swing.JTable singerTable;
    private javax.swing.JButton singerUpdate;
    // End of variables declaration//GEN-END:variables
}
