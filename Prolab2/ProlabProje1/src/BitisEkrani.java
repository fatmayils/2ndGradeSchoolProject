
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class BitisEkrani extends JFrame {

    private BufferedImage image;

    public BitisEkrani() {

        this.setLayout(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.pack();
        this.setLocationRelativeTo(null);
        setLocationRelativeTo(null);
        this.setBackground(Color.BLACK);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        try {
            if (Maze.bitti == 1) {
                image = ImageIO.read(new File("Resimler\\kazandiniz.jpg"));

            } else {
                image = ImageIO.read(new File("Resimler\\bitti.jpg"));

            }
        } catch (IOException ex) {

        }
        g.drawImage(image, 0, 0, 1600, 800, this);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                BitisEkrani main = new BitisEkrani();
                main.setVisible(true);
            }
        });
    }
}
