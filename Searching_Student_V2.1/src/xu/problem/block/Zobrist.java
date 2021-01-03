package xu.problem.block;

import java.util.Arrays;
import java.util.Random;
import java.util.zip.ZipOutputStream;

public class Zobrist {
    public static long Zob[][];//存放zobrist的数组
    private int length;//2*size+1

    public Zobrist(int size){
        length = 2*size+1;
        Zob = new long[length][3];//1   Black    2   white    0    empty
        Random random = new Random();

        for(int i = 0;i<length;i++){
            for(int j = 0;j<3;j++){
                Zob[i][j] = random.nextLong();
            }
        }
    }

    public static void main(String[] args) {
        Zobrist z = new Zobrist(12);
        System.out.println(Zobrist.Zob);
    }
}
