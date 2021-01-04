package core.disjoint;


import java.io.*;
import java.util.HashMap;

public class ReadTxt {

    /**
     * 创建文件
     */
    public static boolean createFile(File fileName)throws Exception{
        try{
            if(!fileName.exists()){
                fileName.createNewFile();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return true;
    }


    /**
     *读取TXT内容
     */
    public static int readTxtFile(String path,long content){
        File file = new File(path);
        int result = 0;
        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(file),"gbk");
            BufferedReader br = new BufferedReader(reader);
            String s = null;
            String key,value;
            while((s=br.readLine())!=null){
                String[] cells = s.split(" ");
                key = cells[0];
                value = cells[1];
                if(key.equals(String.valueOf(content))){
                    //System.out.println("HHHHH"+value);
                    result = Integer.valueOf(value);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 写入TXT，覆盖原内容
     * @param content
     * @param fileName
     * @return
     * @throws Exception
     */
    public static boolean writeTxtFile(String content,File fileName)throws Exception{
        RandomAccessFile mm=null;
        boolean flag=false;
        FileOutputStream fileOutputStream=null;
        try {
            fileOutputStream = new FileOutputStream(fileName);
            fileOutputStream.write(content.getBytes("gbk"));
            fileOutputStream.close();
            flag=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    //写入output.txt
    public static boolean write(int index, HashMap<Long,Byte>map){
        try {
            String path = "output" + index + ".txt";
            ReadTxt.clearInfoForFile(path);
            File file = new File(path);
            if(!file.exists()){
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(map);
            oos.close();
            fos.close();
        }catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * 写入hashTable
     */
    public static void fileChaseFW_hash(Long [][] hash,int size) {
        String filePath = "hashTable1.txt";
        ReadTxt.clearInfoForFile(filePath);
        try {
            File file = new File(filePath);
            if(!file.exists()){
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(hash);
            oos.close();
            fos.close();
            System.out.println("Table written!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 清空已有的文件内容，以便下次重新写入新的内容
    public static void clearInfoForFile(String fileName) {
        File file =new File(fileName);
        try {
            if(!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter =new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //追加一行
    public static void fileChaseFW(String path,long hash,int cost) {
        String filePath = path;
        try {
            //构造函数中的第二个参数true表示以追加形式写文件
            FileWriter fw = new FileWriter(filePath,true);
            fw.write(hash + " " + cost + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("文件写入失败！" + e);
        }
    }


    public static void main(String[] args) throws Exception{
        File file = new File("output1.txt");
        ReadTxt.clearInfoForFile("output1.txt");

    }
}
