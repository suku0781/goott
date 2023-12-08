package ex5;

import java.io.*;

/**
 * packageName : BufferTest
 * fileName : BufferTest
 * author : goott5
 * date : 2023-12-06
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-12-06          goott5             최초생성
 **/
public class BufferTest {
    public static void main(String[] args) throws IOException {
        // 입출력 스트림 생성.
        String originalFile1 = BufferTest.class.getResource("snowBuffer.jpg").getPath();
        String originalFile2 = BufferTest.class.getResource("snowBuffer2.jpg").getPath();

        String copyFile1 = "D:\\lecture\\java\\snowBuffer.jpg";
        String copyFile2 = "D:\\lecture\\java\\snowBuffer2.jpg";

            FileInputStream fis1 = new FileInputStream(originalFile1);
            FileOutputStream fos1 = new FileOutputStream(copyFile1);

            FileInputStream fis2 = new FileInputStream(originalFile2);
            FileOutputStream fos2 = new FileOutputStream(copyFile2);

            // 버퍼를 이용할 경우 - 2번 파일
            BufferedInputStream bis = new BufferedInputStream(fis2);
            BufferedOutputStream bos = new BufferedOutputStream(fos2);

            // 버퍼를 이용하지 않는 경우 - 1번 파일
            long noBufferTime = copyFile(fis1, fos1);

            // 버퍼를 이용하지 않는 경우 - 2번 파일
            long bufferTime = copyFile(bis, bos);

            fis1.close();
            fos1.close();
            bis.close();
            bos.close();


    }

    static long copyFile(InputStream is, OutputStream os) throws IOException {
        long startTime = System.nanoTime();
        while(true){
            int data = is.read();
            if(data == -1) break;
            os.write(data);
        }

        os.flush();

        long endTime = System.nanoTime();

        return (endTime - startTime);
    }
}
