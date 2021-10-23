
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Giris extends JFrame implements ActionListener {

    Image icon;
    private JButton btnGozluklu;
    private JButton btnTembel;
    private BufferedImage image;
    public static int gorunurluk;//hangi karekteri seçtiğimizi diğer panele yansıtmak için

    public Giris() {
        btnGozluklu = new JButton("Gözlüklü Şirin");
        btnTembel = new JButton("Tembel Şirin");
        Font font = new Font(btnGozluklu.getFont().getName(), btnGozluklu.getFont().getStyle(), 26);
        btnGozluklu.setFont(font);
        btnTembel.setFont(font);
        btnGozluklu.setBackground(Color.BLUE);
        btnTembel.setBackground(Color.BLUE);
        btnGozluklu.setBounds(950, 300, 250, 150);
        btnGozluklu.addActionListener(this);
        btnTembel.setBounds(950, 500, 250, 150);
        btnTembel.addActionListener(this);
        this.setLayout(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.pack();
        this.setLocationRelativeTo(null);
        setLocationRelativeTo(null);
        this.add(btnGozluklu);
        this.add(btnTembel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        try {
            image = ImageIO.read(new File("Resimler\\ekran.jpeg"));
        } catch (IOException ex) {

        }
        g.drawImage(image, 0, 0, 1600, 850, this);

        Font currentFont = g.getFont();
        Font newFont = currentFont.deriveFont(currentFont.getSize() * 5F);
        g.setFont(newFont);
        g.setColor(Color.WHITE);
        g.drawString("!!!Oyuncu seçiniz!!!", 800, 200);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnGozluklu) {
            this.dispose();
            this.setVisible(false);
            gorunurluk = 1;
            Main main = new Main();

        } else if (e.getSource() == btnTembel) {
            this.dispose();
            this.setVisible(false);
            gorunurluk = 2;
            Main main = new Main();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Giris main = new Giris();
                main.setVisible(true);
            }
        });
    }
}
