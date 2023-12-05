package ex4;

import java.io.*;

/**
 * packageName : ex4
 * fileName : FileSaveTest
 * author : goott5
 * date : 2023-12-05
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-12-05          goott5             최초생성
 **/
public class FileSaveTest {

    public static void main(String[] args) {
        String copyPath = "D:\\lecture\\java\\IOEx1\\파일저장테스트.txt";

        OutputStream os = null;

        try {
            os = new FileOutputStream(copyPath);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new OutputStreamWriter(os, "utf-8"));

            String txt = "FileInputOutputStream 너무어려워.\r\n으아아아악";
            bw.write(txt);

            bw.flush();
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
