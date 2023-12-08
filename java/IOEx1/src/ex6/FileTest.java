package ex6;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

/**
 * packageName : ex6
 * fileName : FileTest
 * author : goott5
 * date : 2023-12-06
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-12-06          goott5             최초생성
 **/
public class FileTest {

    public static void main(String[] args) throws IOException {
        DecimalFormat df = new DecimalFormat("#,###");
        File dir = new File("D:\\lecture\\java");
        File dir2 = new File("D:\\lecture\\java\\IOEx1\\temp\\test");
        File file1 = new File("D:\\lecture\\java\\IOEx1\\temp\\test1.txt");
        File file2 = new File("D:\\lecture\\java\\IOEx1\\temp\\test2.txt");
        File file3 = new File("D:\\lecture\\java\\IOEx1\\temp\\test3.txt");
        if(!dir2.exists()){
            dir2.mkdir();
        }

        if(!file1.exists()){
            file1.createNewFile();
        }
        if(!file2.exists()){
            file2.createNewFile();
        }
        if(!file3.exists()){
            file3.createNewFile();
        }

        File[] files = dir.listFiles();
        int fileCnt = 0;
        int dirCnt = 0;
        long totalFileSize = 0;

        for(File f : files) {
//            System.out.println(f.isDirectory());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd a HH:mm");
            String lastModified = sdf.format(new Date(f.lastModified()));
            boolean perRead = false;
            boolean perWrite = false;
            boolean perExcute = false;

            if(f.canRead()) perRead = true;
            if(f.canWrite()) perWrite = true;
            if(f.canExecute()) perExcute = true;
            System.out.printf("%-25s%-25s", ((perRead ? "r" : "-")+""+(perWrite ? "w" : "-")+""+(perExcute ? "x" : "-")), lastModified);
            if(f.isDirectory()) {
                dirCnt++;
                System.out.printf("%-10s%-10s", "<DIR>", f.getName());
            } else {
                fileCnt++;
                totalFileSize += f.length();
                System.out.printf("%-10s%-10s", df.format(f.length()), f.getName());
            }

            System.out.println();
        }


        System.out.println(fileCnt + "개 파일");
        System.out.println(dirCnt + "개 디렉터리");
        System.out.println(df.format(totalFileSize) + "바이트");
        System.out.println(df.format(dir.getUsableSpace()) + "바이트 남음");


    }
}
