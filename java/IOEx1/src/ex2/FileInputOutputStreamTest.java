package ex2;

import java.io.*;

/**
 * packageName : ex2
 * fileName : FileInputOutputStreamTest
 * author : goott5
 * date : 2023-12-05
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-12-05          goott5             최초생성
 **/
public class FileInputOutputStreamTest {
    public static void main(String[] args) {
        String filePath = "D:\\lecture\\java\\IOEx1\\ioExTest.txt";
        String copyFile = "D:\\lecture\\java\\IOEx1\\copyIoExTest.txt";

        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            fis = new FileInputStream(filePath);
            fos = new FileOutputStream(copyFile);

            int data = 0;
            while((data = fis.read()) != -1 ){
                System.out.println((char)data);
                fos.write((char)data);
            }

            fis.close();
            fos.close();
        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
    }
}
