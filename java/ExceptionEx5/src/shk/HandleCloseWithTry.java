package shk;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * packageName : shk
 * fileName : HandleCloseWithTry
 * author : goott5
 * date : 2023-11-27
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-27          goott5             최초생성
 **/
public class HandleCloseWithTry {
    // jdk 1.8버전 부터 적용
    // AutoCloseable 인터페이스를 구현한 클래스에 한해서
    
    public static void main(String[] args){
        try (FileReader fr = new FileReader("D:\\lecture\\java\\ExceptionEx5\\src\\shk\\score.dat");
             BufferedReader br = new BufferedReader(fr)){
            while(true){
                String readLine = br.readLine();
                if(readLine == null) break;
                System.out.println(readLine);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
