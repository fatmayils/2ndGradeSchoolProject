import java.util.Random;
//random oluşacak yeri belirledik
public class Mantar extends Obje{
    int control=0;
    Random random = new Random();
    int[][] mushroom=new int[1][2];
   
    int mushroomX,mushroomY;
    public int[][] mushroomLocation(int[][] map){
        for(int i=0;;i++)
        {
            mushroomX=random.nextInt(10);
            mushroomY=random.nextInt(12);
            if(map[mushroomX][mushroomY]==1)
            {
               mushroom[0][0]=mushroomX;
               mushroom[0][1]=mushroomY;
                return mushroom;
            }
        }
      
    }
}
