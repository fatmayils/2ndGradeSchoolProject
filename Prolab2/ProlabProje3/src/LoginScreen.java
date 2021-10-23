
import java.awt.Color;
import java.awt.Image;
import java.sql.*;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class LoginScreen extends javax.swing.JFrame {

    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Connection connection = null;
    String query;
    static String name;
    static String password;
    static int id;
    static String country;

    public LoginScreen() {
        initComponents();
        Image image = new ImageIcon(this.getClass().getResource("Images/user.png")).getImage();
        userLabel.setIcon(new ImageIcon(image));
        this.getContentPane().setBackground(new Color(45, 52, 71));
        signUP.setBackground(new Color(21, 25, 40));
        loginForAdmin.setBackground(new Color(21, 25, 40));
        updateButton.setBackground(new Color(21, 25, 40));
        login.setBackground(new Color(33, 150, 243));
        this.setLocationRelativeTo(null);
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        userName = new javax.swing.JTextField();
        userPassword = new javax.swing.JPasswordField();
        login = new javax.swing.JButton();
        signUP = new javax.swing.JButton();
        loginForAdmin = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        userLabel = new javax.swing.JLabel();
        updateButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(45, 34, 47));
        setForeground(new java.awt.Color(44, 42, 40));

        login.setForeground(new java.awt.Color(255, 255, 255));
        login.setText("LOGIN");
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });

        signUP.setForeground(new java.awt.Color(255, 255, 255));
        signUP.setText("SIGN UP");
        signUP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signUPActionPerformed(evt);
            }
        });

        loginForAdmin.setForeground(new java.awt.Color(255, 255, 255));
        loginForAdmin.setText("LOGIN FOR ADMIN");
        loginForAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginForAdminActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("USERNAME");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("PASSWORD");

        updateButton.setForeground(new java.awt.Color(255, 255, 255));
        updateButton.setText("Update information");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(userLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(signUP, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loginForAdmin))
                .addGap(21, 21, 21))
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(47, 47, 47)
                        .addComponent(userName, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(updateButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(userPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(117, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(signUP, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(loginForAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(userLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userName, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void signUPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signUPActionPerformed
        this.dispose();
        this.setVisible(false);
        SignUpScreen sign = new SignUpScreen();
        sign.setVisible(true);
    }//GEN-LAST:event_signUPActionPerformed

    private void loginForAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginForAdminActionPerformed
        this.dispose();
        this.setVisible(false);
        LoginForAdmin admin = new LoginForAdmin();
        admin.setVisible(true);
    }//GEN-LAST:event_loginForAdminActionPerformed

    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed
        if ((!userName.getText().isEmpty()) && (!userPassword.getText().isEmpty())) {
            try {
                connection = DatabaseConnection.connect();
                query = "select * from users where UserName='" + userName.getText() + "'"
                        + " and Password='" + userPassword.getText() + "'";
                try {
                    PreparedStatement preparedStmt = connection.prepareStatement(query);
                    resultSet = preparedStmt.executeQuery();

                    if (resultSet.next()) {
                        if (userName.getText().equals(resultSet.getString("UserName")) && userPassword.getText().equals(resultSet.getString("Password"))) {
                            System.out.println("login succesfully");
                            name = userName.getText();
                            password = userPassword.getText();
                            id = resultSet.getInt("UserID");
                            country = resultSet.getString("Country");
                            this.dispose();
                            this.setVisible(false);
                            HomePage home = new HomePage();
                            home.setVisible(true);

                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "stupid");
                    }

                } catch (Exception em) {
                    // TODO: handle exception
                }
                connection.close();

            } catch (Exception ex) {
                // TODO: handle exception
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please  fill the all content");

        }
    }//GEN-LAST:event_loginActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        this.dispose();
        this.setVisible(false);
        UpdateInformation upp = new UpdateInformation();
        upp.setVisible(true);
    }//GEN-LAST:event_updateButtonActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton login;
    private javax.swing.JButton loginForAdmin;
    private javax.swing.JButton signUP;
    private javax.swing.JButton updateButton;
    private javax.swing.JLabel userLabel;
    private javax.swing.JTextField userName;
    private javax.swing.JPasswordField userPassword;
    // End of variables declaration//GEN-END:variables
}
