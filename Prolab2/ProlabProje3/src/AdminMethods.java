
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminMethods extends javax.swing.JFrame {

    public AdminMethods() {
        initComponents();
         this.getContentPane().setBackground(new Color(45, 52, 71));
         this.setLocationRelativeTo(null);
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        singerMethods = new javax.swing.JButton();
        SingerSongMethods = new javax.swing.JButton();
        albumMethods = new javax.swing.JButton();
        previousPage = new javax.swing.JButton();
        songMethods = new javax.swing.JButton();
        songAlbum = new javax.swing.JButton();
        songKind = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        singerMethods.setBackground(new java.awt.Color(33, 150, 243));
        singerMethods.setText("SINGER METHODS");
        singerMethods.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                singerMethodsActionPerformed(evt);
            }
        });

        SingerSongMethods.setBackground(new java.awt.Color(33, 150, 243));
        SingerSongMethods.setText("Singer-Song");
        SingerSongMethods.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SingerSongMethodsActionPerformed(evt);
            }
        });

        albumMethods.setBackground(new java.awt.Color(33, 150, 243));
        albumMethods.setText("ALBUM METHODS");
        albumMethods.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                albumMethodsActionPerformed(evt);
            }
        });

        previousPage.setBackground(new java.awt.Color(45, 52, 71));
        previousPage.setForeground(new java.awt.Color(255, 255, 255));
        previousPage.setText("Prev");
        previousPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousPageActionPerformed(evt);
            }
        });

        songMethods.setBackground(new java.awt.Color(33, 150, 243));
        songMethods.setText("SONG METHODS");
        songMethods.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                songMethodsActionPerformed(evt);
            }
        });

        songAlbum.setBackground(new java.awt.Color(33, 150, 243));
        songAlbum.setText("Song-Album");
        songAlbum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                songAlbumActionPerformed(evt);
            }
        });

        songKind.setBackground(new java.awt.Color(33, 150, 243));
        songKind.setText("Song-Kind");
        songKind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                songKindActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 236, Short.MAX_VALUE)
                        .addComponent(songAlbum, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(songKind, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(singerMethods, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                            .addComponent(songMethods, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(albumMethods, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                    .addComponent(SingerSongMethods, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(previousPage, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(previousPage, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SingerSongMethods, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(songMethods, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(songAlbum, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(songKind, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(albumMethods, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(singerMethods, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(85, 85, 85))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
//adminin yaptığı ,yapabileceği işlemlerin sayfasına yönlendire butonlar burada yer alıyor.
    private void albumMethodsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_albumMethodsActionPerformed
        try {
            this.dispose();
            this.setVisible(false);
            AdminAlbumMethods methods=new AdminAlbumMethods();
            methods.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(AdminMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_albumMethodsActionPerformed

    private void SingerSongMethodsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SingerSongMethodsActionPerformed
        try {
            this.dispose();
            this.setVisible(false);
            SingerSongRelationShip methods=new SingerSongRelationShip();
            methods.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(AdminMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_SingerSongMethodsActionPerformed

    private void singerMethodsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_singerMethodsActionPerformed
        try {
            this.dispose();
            this.setVisible(false);
            AdminSingerMethods methods=new AdminSingerMethods();
            methods.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(AdminMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_singerMethodsActionPerformed

    private void previousPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousPageActionPerformed
        this.dispose();
        this.setVisible(false);
        LoginForAdmin admin=new LoginForAdmin();
       admin.setVisible(true);
    }//GEN-LAST:event_previousPageActionPerformed

    private void songMethodsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_songMethodsActionPerformed
         this.dispose();
        this.setVisible(false);
        AdminSongMethods methods = null;
        try {
            methods = new AdminSongMethods();
        } catch (Exception ex) {
            Logger.getLogger(AdminMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
        methods.setVisible(true);
    }//GEN-LAST:event_songMethodsActionPerformed

    private void songAlbumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_songAlbumActionPerformed
        try {
            this.dispose();
            this.setVisible(false);
            SongAlbumRelationShip admin=new SongAlbumRelationShip();       
            admin.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(AdminMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_songAlbumActionPerformed

    private void songKindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_songKindActionPerformed
        try {
            this.dispose();
            this.setVisible(false);
            SongKindRelationShip admin=new SongKindRelationShip();
            admin.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(AdminMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_songKindActionPerformed

    public static void main(String args[]) {
      
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminMethods().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton SingerSongMethods;
    private javax.swing.JButton albumMethods;
    private javax.swing.JButton previousPage;
    private javax.swing.JButton singerMethods;
    private javax.swing.JButton songAlbum;
    private javax.swing.JButton songKind;
    private javax.swing.JButton songMethods;
    // End of variables declaration//GEN-END:variables
}
