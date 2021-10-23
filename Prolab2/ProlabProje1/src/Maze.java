
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Maze extends JPanel implements KeyListener {

    public static int bitti = 0;
    private Altin gold = new Altin();
    private Mantar mushroom = new Mantar();
    private BufferedImage image1, image2, image3, image4, image5, image6;
    private Oyuncu player;//giris ekranından gelen veriye göre player oluşturacak.
    private int Ismushroom = 0;//mantar ve playernun konumu aynı olduğunda mantarın kaybolması için.
    private int[][] myGold;//altın konumunun tutulduğu dizi
    private int[][] myMushroom;//mantar konumunun tutulduğu dizi
    private int[] goldWidth = {61, 61, 61, 61, 61};
    private int[] goldHeight = {65, 65, 65, 65, 65};
    private int mushroomWidth = 70, mushroomHeight = 52;
    private int[][] map = new int[11][13];//haritamız
    private String[] character;
    private Label label = new Label();
    private Label minPath = new Label();
    private Label footDistance = new Label();
    private ArrayList<Dusman> badCharacter = new ArrayList<>();
    private ArrayList<Locasyon> badCharacterYer = new ArrayList<>();
    private int movementPlayer = 0;
     private int movementPlayer2 = 0;
    private int movementBadCharacter = 1;
    private int movementBadCharacter2 = 1;
    char[] startingGate;//kötü karakterin giriş kapısı
    int c;//keylistener için
    int x = 6, y = 5;//playernun başlangıç konumları
    private String distance = "";//oyuncuya olan uzaklık
    private String foot = "";//kaç adımda gider
    int a = 0;
    int show = 0;
    int dizi1[][];
    int dizi2[][];
    Puan puan = new Puan();

    public Maze() {

        //Burada dosyadan  kaç kötü karakter olduğunu ve harita dizisindeki verileri alıyoruz
        String line;
        ArrayList<String> arr = new ArrayList<>();//txt de ki stringler için
        int countCharacter = 0;//düşman sayısını tutar
        File file = new File("harita.txt");
        try {
            Scanner x = new Scanner(file);
            while (x.hasNextLine()) {
                line = x.nextLine();
                arr.add(line);
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == ',') {
                        countCharacter++;
                    }
                }
            }
            x.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        character = new String[countCharacter];//Kötü karakterlerin isimlerinin tutulduğu dizi
        startingGate = new char[countCharacter];//Kötü karakterlerin giriş kapılarını tutulduğu dizi
        //Karakter:Gargamel,Kapi:A
        for (int i = 0; i < countCharacter; i++) {
            String[] first = arr.get(i).split(",");// İlk büyük cümleyi virgülle 2 ye parçaladım.
            String[] second = first[0].split(":");// İkiye parçaladığım alanın karakter:isim kısmını ikiye böldüm
            String[] third = first[1].split(":");//İkiye parçaladığım alanın Kapı:A şeklinde ki kısmını ikiye böldüm
            character[i] = second[1];//Adını aldı
            startingGate[i] = third[1].charAt(0);
        }
        for (int i = countCharacter; i < 11 + countCharacter; i++) {
            String[] bol = arr.get(i).split("\t");
            for (int j = 0; j < 13; j++) {
                i -= countCharacter;
                this.map[i][j] = Integer.valueOf(bol[j]);
                i += countCharacter;
            }
        }
        Karakter.setMap(map);
//Burada ise kötü karakter için dosyadan okuduğum harf ve ada göre lokasyon+nesne üretiyorum
        for (int i = 0; i < countCharacter; i++) {
            Locasyon location = new Locasyon();
            if (character[i].equals("Azman")) {
                location = startingPointForBad(startingGate[i]);
                Dusman azman = new Azman();
                azman.setLokasyon(location);
                badCharacterYer.add(location);
                this.badCharacter.add(azman);
            } else if (character[i].equals("Gargamel")) {
                location = startingPointForBad(startingGate[i]);
                Dusman gargamel = new Gargamel();
                gargamel.setLokasyon(location);
                badCharacterYer.add(location);
                this.badCharacter.add(gargamel);
            }

        }
        myGold = gold.createGold(map);
        myMushroom = mushroom.mushroomLocation(map);
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int go = 0; skooor > 0; go++) {
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Maze.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    try {
                        for (int i = 0; i < 5; i++) {
                            myGold[i][2] = 1;
                            goldWidth[i] = 0;
                            goldHeight[i] = 0;
                        }
                        repaint();
                        Thread.sleep(6000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Maze.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    for (int i = 0; i < 5; i++) {
                        myGold[i][2] = 0;
                        goldWidth[i] = 61;
                        goldHeight[i] = 65;
                    }
                    repaint();
                    Altin gold = new Altin();
                    myGold = gold.createGold(map);
                }
            }
        });
        thread1.start();
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Maze.class.getName()).log(Level.SEVERE, null, ex);
                }
                for (int go = 0; skooor > 0; go++) {
                    try {
                        Thread.sleep(12000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Maze.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        Ismushroom = 1;
                        mushroomWidth = 0;
                        mushroomHeight = 0;
                        repaint();
                        Thread.sleep(8000);
                        Ismushroom = 0;
                        mushroomWidth = 70;
                        mushroomHeight = 52;
                        repaint();
                        Mantar mushroom = new Mantar();
                        myMushroom = mushroom.mushroomLocation(map);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Maze.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        thread2.start();
        badCharacter.get(0).shortestPath(y, x);
        dizi1 = badCharacter.get(0).getMatris();
        badCharacter.get(1).shortestPath(y, x);
        dizi2 = badCharacter.get(1).getMatris();
        label.setBounds(71 * 13, 50, 300, 50);
        label.setFont(new Font("Courier New", Font.ITALIC, 12));
        label.setForeground(Color.WHITE);
        minPath.setBounds(71 * 13, 120, 550, 50);
        minPath.setFont(new Font("Courier New", Font.ITALIC, 12));
        minPath.setForeground(Color.WHITE);
        footDistance.setBounds(71 * 13, 250, 500, 50);
        footDistance.setFont(new Font("Courier New", Font.ITALIC, 12));
        footDistance.setForeground(Color.WHITE);
        this.add(footDistance);
        this.add(minPath);
        this.add(label);
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.setSize(1500, 800);
        this.setVisible(true);
    }

    public int[][] getMap() {
        return map;
    }

    public void setMap(int[][] map) {
        this.map = map;
    }
    int aaaa = 0;

    @Override

    public void paint(Graphics g) {
        int countOne = 0;
        int countTwo = 0;
        super.paint(g);
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                Color color = null;
                if ((map[5][6] == 1) && (i == 5) && (6 == j)) {
                    color = Color.BLUE;
                } else if (((map[0][3] == 1) && (i == 0) && (3 == j)) || ((map[0][10] == 1) && (i == 0) && (10 == j))
                        || ((map[5][0] == 1) && (i == 5) && (0 == j)) || ((map[10][3] == 1) && (i == 10) && (j == 3))
                        || (map[7][12] == 1) && (i == 7) && (12 == j)) {
                    color = Color.RED;
                } else if (map[i][j] == 0) {
                    color = Color.BLACK;
                } else if (map[i][j] == 1) {
                    color = Color.WHITE;
                }
                if (dizi1[i][j] == 1) {
                    color = Color.pink;
                    countOne++;

                }
                if (dizi2[i][j] == 1) {
                    color = Color.YELLOW;
                    countTwo++;
                }

                g.setColor(color);
                g.fillRect(70 * j, 70 * i, 70, 70);
                g.setColor(Color.BLACK);
                g.drawRect(70 * j, 70 * i, 70, 70);

            }
        }
        badCharacter.get(0).setMin(countOne - 1);
        badCharacter.get(1).setMin(countTwo - 1);
        try {
            if (Giris.gorunurluk == 1) {
                player = new GozlukluSirin();
                image1 = ImageIO.read(new File("Resimler\\gozluklu.png"));

            } else if (Giris.gorunurluk == 2) {
                player = new TembelSirin();
                image1 = ImageIO.read(new File("Resimler\\tembel.png"));

            }

            if (show == 0) {
                label.setText(puan.puaniGoster(player));
                show++;
            }
            image2 = ImageIO.read(new File("Resimler\\sirine.png"));
            image3 = ImageIO.read(new File("Resimler\\altin.png"));
            image4 = ImageIO.read(new File("Resimler\\mantar.png"));
            image5 = ImageIO.read(new File("Resimler\\azman.png"));
            image6 = ImageIO.read(new File("Resimler\\gargamel.png"));
        } catch (IOException ex) {
            Logger.getLogger(Maze.class.getName()).log(Level.SEVERE, null, ex);
        }
        g.drawImage(image1, 70 * x, 70 * y, this);
        g.drawImage(image2, 70 * 12, 70 * 7, this);
        for (int i = 0; i < myGold.length; i++) {
            g.drawImage(image3, 70 * myGold[i][1], 70 * myGold[i][0], 61 - goldWidth[i], 70 - goldHeight[i], this);
        }
        g.drawImage(image4, 70 * myMushroom[0][1], 70 * myMushroom[0][0], 70 - mushroomWidth, 52 - mushroomHeight, this);

        for (int i = 0; i < badCharacter.size(); i++) {
            if (badCharacter.get(i).getName().equals("Azman")) {
                g.drawImage(image5, 70 * badCharacter.get(i).getLokasyon().getX(), 70 * badCharacter.get(i).getLokasyon().getY(), this);

            } else {
                g.drawImage(image6, 70 * badCharacter.get(i).getLokasyon().getX(), 70 * badCharacter.get(i).getLokasyon().getY(), this);

            }
        }
        for (int i = 0; i < badCharacter.size(); i++) {
            distance += badCharacter.get(i).getName() + " in/ın " + player.getName() + " e olan uzaklığı "
                    + badCharacter.get(i).getMin() + " br dir.";
            if (badCharacter.get(i).adim == 1) {
                foot += badCharacter.get(i).getName() + " " + badCharacter.get(i).getMin() + " adim ,\n";
            } else {
                int result = (badCharacter.get(i).getMin() / 2) + (badCharacter.get(i).getMin() % 2);
                foot += badCharacter.get(i).getName() + " " + result + " adim ,\n";
            }
        }
        minPath.setText(distance);
        footDistance.setText(foot);
        distance = "";
        foot = "";

        if (x == 12 && y == 7 && skooor > 0) {
            label.setText("Kazandınız");
            bitti = 1;
            this.setVisible(false);
            BitisEkrani bitis = new BitisEkrani();
        }
        for (int i = 0; i < 5; i++) {
            if (myGold[i][1] == x && myGold[i][0] == y && myGold[i][2] == 1) {
                String text = "";
                //System.out.println("varaltin");
                myGold[i][2] = 0;
                goldWidth[i] = 61;
                goldHeight[i] = 65;
                skooor += 5;
                player.setSkor(skooor);
                System.out.println(player.getSkor());
                text += puan.puaniGoster(player);
                label.setText(text);
            }
        }
        if (myMushroom[0][1] == x && myMushroom[0][0] == y && Ismushroom == 1) {
            String text = "";
            //System.out.println("varmantar");
            mushroomWidth = 70;
            mushroomHeight = 52;
            Ismushroom = 0;
            skooor += 50;
            player.setSkor(skooor);
            System.out.println(player.getSkor());
            text += puan.puaniGoster(player);
            label.setText(text);

        }
        for (int i = 0; i < badCharacter.size(); i++) {
            if (badCharacter.get(i).getLokasyon().getX() == x && badCharacter.get(i).getLokasyon().getY() == y) {
                String text = "";
                if (player.getName().equals("Gözlüklü Şirin")) {
                    skooor -= badCharacter.get(i).getReduceOfPointG();
                } else {
                    skooor -= badCharacter.get(i).getReduceOfPointT();
                }
                player.setSkor(skooor);
                text += puan.puaniGoster(player);
                label.setText(text);
                if (skooor <= 0) {
                    label.setText("oyun bitti");
                    this.setVisible(false);
                    BitisEkrani bitis = new BitisEkrani();
                    bitis.setVisible(true);
                }
                Locasyon location = new Locasyon();
                if (badCharacter.get(i).getName().equals("Azman")) {
                    location = startingPointForBad(startingGate[i]);

                    badCharacter.get(i).setLokasyon(location);
                } else if (badCharacter.get(i).getName().equals("Gargamel")) {
                    location = startingPointForBad(startingGate[i]);
                    badCharacter.get(i).setLokasyon(location);
                }
                badCharacter.get(0).shortestPath(y, x);
                dizi1 = badCharacter.get(0).getMatris();
                badCharacter.get(1).shortestPath(y, x);
                dizi2 = badCharacter.get(1).getMatris();
            }
            repaint();
        }

        // GraphShow graf=new GraphShow(x,y,10,3);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        c = e.getKeyCode();
        if (c == KeyEvent.VK_LEFT) { //sol hareket tuşuna bastığımızda 
            if (x - player.adim >= 0 && map[y][x - player.adim] == 1 && map[y][x - 1] == 1) {
                x = x - player.adim;
                movementPlayer++;
                movementPlayer2++;
                if (player.adim == 2) {
                    for (int i = 0; i < 5; i++) {
                        if ((myGold[i][1] == x && myGold[i][0] == y && myGold[i][2] == 1)
                                || ((myGold[i][1] == x + 1 && myGold[i][0] == y && myGold[i][2] == 1))) {
                            String text = "";
                            System.out.println("varaltin");
                            myGold[i][2] = 0;
                            goldWidth[i] = 61;
                            goldHeight[i] = 65;
                            skooor += 5;
                            player.setSkor(skooor);
                            System.out.println(player.getSkor());
                            text += puan.puaniGoster(player);
                            label.setText(text);
                        }
                    }
                    if ((myMushroom[0][1] == x && myMushroom[0][0] == y && Ismushroom == 1)
                            || (myMushroom[0][1] == x + 1 && myMushroom[0][0] == y && Ismushroom == 1)) {
                        String text = "";
                        System.out.println("varmantar");
                        mushroomWidth = 70;
                        mushroomHeight = 52;
                        Ismushroom = 0;
                        skooor += 50;
                        player.setSkor(skooor);
                        System.out.println(player.getSkor());
                        text += puan.puaniGoster(player);
                        label.setText(text);

                    }

                }
                // x ekseninde a birim sola kaydırıyor
            }
        } else if (c == KeyEvent.VK_RIGHT) {   //sağ hareket tuşuna bastığımızda
            if (x + player.adim >= 0 && x + player.adim <= 12 && map[y][x + player.adim] == 1 && map[y][x + 1] == 1) {
                x = x + player.adim;                           // x ekseninde a birim sağa kaydırıyor
                movementPlayer++;
                 movementPlayer2++;
                if (player.adim == 2) {
                    for (int i = 0; i < 5; i++) {
                        if ((myGold[i][1] == x && myGold[i][0] == y && myGold[i][2] == 1)
                                || ((myGold[i][1] == x - 1 && myGold[i][0] == y && myGold[i][2] == 1))) {
                            String text = "";
                            System.out.println("varaltin");
                            myGold[i][2] = 0;
                            goldWidth[i] = 61;
                            goldHeight[i] = 65;
                            skooor += 5;
                            player.setSkor(skooor);
                            System.out.println(player.getSkor());
                            text += puan.puaniGoster(player);
                            label.setText(text);
                        }
                    }
                    if ((myMushroom[0][1] == x && myMushroom[0][0] == y && Ismushroom == 1)
                            || (myMushroom[0][1] == x - 1 && myMushroom[0][0] == y && Ismushroom == 1)) {
                        String text = "";
                        System.out.println("varmantar");
                        mushroomWidth = 70;
                        mushroomHeight = 52;
                        Ismushroom = 0;
                        skooor += 50;
                        player.setSkor(skooor);
                        System.out.println(player.getSkor());
                        text += puan.puaniGoster(player);
                        label.setText(text);

                    }

                }
            }
        } else if (c == KeyEvent.VK_UP) {      //üst hareket tuşuna bastığımızda
            if (y - player.adim >= 0 && map[y - player.adim][x] == 1 && map[y - 1][x] == 1) {
                y = y - player.adim;                           //y ekseninde a birim yukarı atıyor
                movementPlayer++;
                 movementPlayer2++;
                if (player.adim == 2) {
                    for (int i = 0; i < 5; i++) {
                        if ((myGold[i][1] == x && myGold[i][0] == y && myGold[i][2] == 1)
                                || ((myGold[i][1] == x && myGold[i][0] == y + 1 && myGold[i][2] == 1))) {
                            String text = "";
                            // System.out.println("varaltin");
                            myGold[i][2] = 0;
                            goldWidth[i] = 61;
                            goldHeight[i] = 65;
                            skooor += 5;
                            player.setSkor(skooor);
                            System.out.println(player.getSkor());
                            text += puan.puaniGoster(player);
                            label.setText(text);
                        }
                    }
                    if ((myMushroom[0][1] == x && myMushroom[0][0] == y && Ismushroom == 1)
                            || (myMushroom[0][1] == x && myMushroom[0][0] == y + 1 && Ismushroom == 1)) {
                        String text = "";
                        // System.out.println("varmantar");
                        mushroomWidth = 70;
                        mushroomHeight = 52;
                        Ismushroom = 0;
                        skooor += 50;
                        player.setSkor(skooor);
                        System.out.println(player.getSkor());
                        text += puan.puaniGoster(player);
                        label.setText(text);

                    }

                }

            }
        } else if (c == KeyEvent.VK_DOWN) {    //alt hareket tuşuna bastığımızda
            if (y + player.adim >= 0 && y + player.adim <= 10 && map[y + player.adim][x] == 1 && map[y + 1][x] == 1) {
                y = y + player.adim;                           //y ekseninde a birim aşağı atıyor
                movementPlayer++;
                 movementPlayer2++;
                if (player.adim == 2) {
                    for (int i = 0; i < 5; i++) {
                        if ((myGold[i][1] == x && myGold[i][0] == y && myGold[i][2] == 1)
                                || ((myGold[i][1] == x && myGold[i][0] == y - 1 && myGold[i][2] == 1))) {
                            String text = "";
                            System.out.println("varaltin");
                            myGold[i][2] = 0;
                            goldWidth[i] = 61;
                            goldHeight[i] = 65;
                            skooor += 5;
                            player.setSkor(skooor);
                            System.out.println(player.getSkor());
                            text += puan.puaniGoster(player);
                            label.setText(text);
                        }
                    }
                    if ((myMushroom[0][1] == x && myMushroom[0][0] == y && Ismushroom == 1)
                            || (myMushroom[0][1] == x && myMushroom[0][0] == y - 1 && Ismushroom == 1)) {
                        String text = "";
                        System.out.println("varmantar");
                        mushroomWidth = 70;
                        mushroomHeight = 52;
                        Ismushroom = 0;
                        skooor += 50;
                        player.setSkor(skooor);
                        System.out.println(player.getSkor());
                        text += puan.puaniGoster(player);
                        label.setText(text);

                    }

                }
            }
        }

        repaint();
        //2.şart
        for (int i = 0; i < badCharacter.size(); i++) {
            if (badCharacter.get(i).getLokasyon().getX() == x && badCharacter.get(i).getLokasyon().getY() == y) {
                String text = "";
                if (player.getName().equals("Gözlüklü Şirin")) {
                    skooor -= badCharacter.get(i).getReduceOfPointG();
                } else {
                    skooor -= badCharacter.get(i).getReduceOfPointT();
                }
                player.setSkor(skooor);
                text += puan.puaniGoster(player);
                label.setText(text);
                if (skooor <= 0) {
                    label.setText("oyun bitti");
                    this.setVisible(false);
                    BitisEkrani bitis = new BitisEkrani();
                    bitis.setVisible(true);
                }
                Locasyon location = new Locasyon();
                if (badCharacter.get(i).getName().equals("Azman")) {
                    location = startingPointForBad(startingGate[i]);

                    badCharacter.get(i).setLokasyon(location);
                } else if (badCharacter.get(i).getName().equals("Gargamel")) {
                    location = startingPointForBad(startingGate[i]);
                    badCharacter.get(i).setLokasyon(location);
                     movementPlayer2=0;
                    movementBadCharacter2=1;
                }
                badCharacter.get(0).shortestPath(y, x);
                dizi1 = badCharacter.get(0).getMatris();
                badCharacter.get(1).shortestPath(y, x);
                dizi2 = badCharacter.get(1).getMatris();
            }
            repaint();
        }
        if (movementPlayer == movementBadCharacter) {
            for (int i = 0; i < badCharacter.size(); i++) {
                int x1 = badCharacter.get(i).getLokasyon().getX();
                int y1 = badCharacter.get(i).getLokasyon().getY();
                Locasyon l = new Locasyon();
                badCharacter.get(i).shortestPath(y, x);
                int dizi[][] = badCharacter.get(i).getMatris();
                if (badCharacter.get(i).adim == 1) {
                    if (x1 + 1 <= 12 && dizi[y1][x1 + 1] == 1) {
                        x1 = x1 + 1;
                    } else if (x1 - 1 >= 0 && dizi[y1][x1 - 1] == 1) {
                        x1 = x1 - 1;
                    } else if (y1 - 1 >= 0 && dizi[y1 - 1][x1] == 1) {
                        y1 = y1 - 1;
                    } else if (y + 1 <= 10 && dizi[y1 + 1][x1] == 1) {
                        y1 = y1 + 1;
                    }
                    l.setX(x1);
                    l.setY(y1);
                    badCharacter.get(i).setLokasyon(l);
                    repaint();

                } else if (badCharacter.get(i).adim == 2&&movementPlayer2 == movementBadCharacter2) {
                    if (x1 + 1 < dizi[0].length && dizi[y1][x1 + 1] == 1) {
                        x1 = x1 + 1;
                        if (x1 + 1 < dizi[0].length && dizi[y1][x1 + 1] == 1) {
                            x1 = x1 + 1;
                        } else if (y1 - 1 >= 0 && dizi[y1 - 1][x1] == 1) {
                            y1 = y1 - 1;
                        } else if (y + 1 < dizi.length && dizi[y1 + 1][x1] == 1) {
                            y1 = y1 + 1;
                        }
                    } else if (x1 - 1 >= 0 && dizi[y1][x1 - 1] == 1) {
                        x1 = x1 - 1;
                        if (x1 - 1 >= 0 && dizi[y1][x1 - 1] == 1) {
                            x1 = x1 - 1;
                        } else if (y1 - 1 >= 0 && dizi[y1 - 1][x1] == 1) {
                            y1 = y1 - 1;
                        } else if (y + 1 < dizi.length && dizi[y1 + 1][x1] == 1) {
                            y1 = y1 + 1;
                        }
                    } else if (y1 - 1 >= 0 && dizi[y1 - 1][x1] == 1) {
                        y1 = y1 - 1;
                        if (x1 + 1 < dizi[0].length && dizi[y1][x1 + 1] == 1) {
                            x1 = x1 + 1;
                        } else if (x1 - 1 >= 0 && dizi[y1][x1 - 1] == 1) {
                            x1 = x1 - 1;
                        } else if (y1 - 1 >= 0 && dizi[y1 - 1][x1] == 1) {
                            y1 = y1 - 1;
                        }
                    } else if (y1 + 1 <= 10 && dizi[y1 + 1][x1] == 1) {
                        y1 = y1 + 1;
                        if (x1 + 1 <= dizi[0].length && dizi[y1][x1 + 1] == 1) {
                            x1 = x1 + 1;
                        } else if (x1 - 1 >= 0 && dizi[y1][x1 - 1] == 1) {
                            x1 = x1 - 1;
                        } else if (y1 + 1 < dizi.length && dizi[y1 + 1][x1] == 1) {
                            y1 = y1 + 1;
                        }
                    }
                    repaint();
                    l.setX(x1);
                    l.setY(y1);
                    badCharacter.get(i).setLokasyon(l);
                    repaint();
                    movementBadCharacter2++;
                }
            }
            movementBadCharacter++;
            badCharacter.get(0).shortestPath(y, x);
            dizi1 = badCharacter.get(0).getMatris();
            badCharacter.get(1).shortestPath(y, x);
            dizi2 = badCharacter.get(1).getMatris();
        }
        repaint();

    }

    @Override
    public void repaint() {
        super.repaint();
    }
    int skooor = 20;

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public static Locasyon startingPointForBad(char harf) {
        Locasyon location = new Locasyon();
        if (harf == 'A') {
            location.setX(3);
            location.setY(0);
        } else if (harf == 'B') {
            location.setX(10);
            location.setY(0);
        } else if (harf == 'C') {
            location.setX(0);
            location.setY(5);
        } else if (harf == 'D') {
            location.setX(3);
            location.setY(10);
        }

        return location;

    }
}
