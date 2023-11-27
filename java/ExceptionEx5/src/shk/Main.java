package shk;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * packageName :
 * fileName : ${NAME}
 * author : goott5
 * date : 2023-11-27
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-27          goott5             최초생성
 **/
public class Main {
    public static void main(String[] args) {
        // D:\lecture\java\ExceptionEx5\src\shk
        BufferedReader br = null;
        try {
            // 1. 파일을 읽기 위한 객체 생성. 파일에 스트림 연결
            br = new BufferedReader(new FileReader("D:\\lecture\\java\\ExceptionEx5\\src\\shk\\score.dat"));

            // 2. 파일 읽기
            while(true){
                String readLine = br.readLine();
                if(readLine == null) break;
                System.out.println(readLine);
            }

        } catch (FileNotFoundException e) {
            System.out.println("파일을 찾을 수 없습니다.");
        } catch (IOException e) {
//            throw new RuntimeException(e);
            System.out.println("IOException");
        }

        // 3. 파일 스트림 닫기
        try {
            if(br != null) br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        if(br != null) br.close();
    }
}