package ex1;

import com.sun.imageio.spi.InputStreamImageInputStreamSpi;

import java.io.*;
import java.util.Arrays;

/**
 * packageName :
 * fileName : ${NAME}
 * author : goott5
 * date : 2023-12-05
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-12-05          goott5             최초생성
 **/
public class Main {
    public static void main(String[] args) {
        byte[] inSrc = {1,2,3,4,5,6,7,8,9}; // 원몬데이터 (출발지)
        byte[] outSrc = null; // 목적지

//        outSrc = inSrc; // 배열 복사 개념이 아닌 스트림 객체를 통한 데이터 전송

        // 입출력 스트림 객체 생성
        InputStream input = null;

        ByteArrayOutputStream output = null;

        input = new ByteArrayInputStream(inSrc);
        output = new ByteArrayOutputStream();

        int data = 0;

        try {
            while ((data = input.read()) != -1) { // input 객체를 통해 읽은 값이 -1 : EOF(End Of File)이 아닌동안 읽음.
                output.write(data);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        outSrc = output.toByteArray(); // output에 있는 데이터를 byte[]로 변환하여 outSrc에 넣음.

        for(byte b : outSrc) {
            System.out.print(b + "\t");
        }

        try {
            input.close(); // 입출력 스트림 객체는 입출력이 완료되면 close해줘야한다.
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}