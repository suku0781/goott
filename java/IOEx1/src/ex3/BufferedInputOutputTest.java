package ex3;

import java.io.*;

/**
 * packageName : ex3
 * fileName : BufferedInputOutputTest
 * author : goott5
 * date : 2023-12-05
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-12-05          goott5             최초생성
 **/
public class BufferedInputOutputTest {
    public static void main(String[] args) {
        String filePath = "D:\\lecture\\java\\IOEx1\\ioExTest.txt";
        String copyFile = "D:\\lecture\\java\\IOEx1\\copyIoExTest2.txt";

        InputStreamReader is = null;
        OutputStream os = null;

        try {
            FileInputStream fis = new FileInputStream(filePath);
            is = new InputStreamReader(fis, "utf-8");
            os = new FileOutputStream(copyFile);

        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        BufferedReader br = new BufferedReader(is);
        BufferedWriter bw = null;

        try {
            bw = new BufferedWriter(new OutputStreamWriter(os, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        // 읽고 쓰는 작업
        int data = 0;

        try {
            while((data = br.read()) != -1) {
                System.out.println((char) data);
                bw.write(data);
            }
            bw.flush(); // 버퍼에 잔류하는 모든 문자를 출력 (버퍼 비우기)

            br.close(); //
            bw.close(); // 출력 스트링을 닫고 사용 메모리를 해제.
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
