
import java.util.Random;
//random olarak 5 farklı konum aldık ve bir dizide tutuyoruz
public class Altin extends Obje {
    Random random = new Random();
    public int[][] createGold(int[][] map) {
        int[][] Gold = new int[5][3];//3.parametre altın ve oyuncunun konumu aynı olduğunda altının kaybolması için.
        int goldX, goldY, countGold = 0;
        int control = 0;
        /* int[][] map
                = {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1},
                {1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1},
                {1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1},
                {1, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1},
                {1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}

                };*/

        for (int i = 0; countGold < 5; i++) {
            goldX = random.nextInt(8);
            goldY = random.nextInt(12);
            control=0;
            if (map[goldX][goldY] == 1) {
                for (int j = 0; j < countGold; j++) {
                    if (goldX == Gold[j][0] && goldY == Gold[j][1]) {
                        control = 1;
                        break;

                    }
                }
                if (control == 0) {
                    Gold[countGold][0] = goldX;
                    Gold[countGold][1] = goldY;
                    Gold[countGold][2] = 0;
                    countGold++;
                }

            }

        }
      /*  for (int i = 0; i < 5; i++) {
            System.out.println(Gold[i][0] + "  " + Gold[i][1]);
        }*/
        return Gold;

    }
}
