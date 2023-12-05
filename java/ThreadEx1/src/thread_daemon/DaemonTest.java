package thread_daemon;

/**
 * packageName : thread_daemon
 * fileName : DaemonTest
 * author : goott5
 * date : 2023-12-05
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-12-05          goott5             최초생성
 **/
public class DaemonTest {
    public static void main(String[] args) {
        AutoSaveThread saveThread = new AutoSaveThread();

        saveThread.setDaemon(true);
        saveThread.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
        System.out.println("메인종료");
    }
}

class AutoSaveThread extends Thread{
    public void save() {
        System.out.println("작업문서를 자동저장함.");
    }

    @Override
    public void run() {

        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
                e.printStackTrace();
                break;
            }

            save();
        }

    }

}
