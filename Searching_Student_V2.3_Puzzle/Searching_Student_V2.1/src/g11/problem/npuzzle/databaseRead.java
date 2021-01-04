package g11.problem.npuzzle;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Iterator;

public class databaseRead {
    //读取hashTable
    public databaseRead(String[] fls) {
        System.out.print("reading database ...");
        for (int i = 0; i < fls.length; i++) {
            try {
                System.out.print("..");
                FileInputStream fileIn = new FileInputStream(fls[i]);
                ObjectInputStream objectIn = new ObjectInputStream(fileIn);
                Object obj = objectIn.readObject();
                if (i == 0) {
                    result1 = (HashMap<Long, Byte>) obj;
                } else if (i == 1) {
                    result2 = (HashMap<Long, Byte>) obj;
                } else if (i == 2) {
                    result3 = (HashMap<Long, Byte>) obj;
                } else if (i == 3) {
                    result4 = (HashMap<Long, Byte>) obj;
                }
                objectIn.close();
                fileIn.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException c) {
                System.out.println("Employee class not found");
                c.printStackTrace();
                return;
            }


        }
        System.out.println();
    }

    public static void setHashInit(String fls) {
        try {
            FileInputStream fileIn = new FileInputStream(fls);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            Object obj = objectIn.readObject();
            hashTable = (Long[][]) obj;
            objectIn.close();
            fileIn.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
            return;
        }
    }

    public static Byte getHeu(long hash, int i) {
        if(i == 1){
            return result1.get(hash);
        }
        else if(i == 2){
            return result2.get(hash);
        }
        else if(i == 3){
            return result3.get(hash);
        }else{
            return result4.get(hash);
        }
    }

    public static HashMap<Long, Byte> result1 = new HashMap<>();
    public static HashMap<Long, Byte> result2 = new HashMap<>();
    public static HashMap<Long, Byte> result3 = new HashMap<>();
    public static HashMap<Long, Byte> result4 = new HashMap<>();
    public static Long[][] hashTable;
}
