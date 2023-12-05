package thread_interrupt;

/**
 * packageName : thread_interrupt
 * fileName : InterruptTest
 * author : goott5
 * date : 2023-12-05
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-12-05          goott5             최초생성
 **/
class PrintThread extends Thread{
    @Override
    public void run() {

        try {
            while(true){
                System.out.println("실행중...");
                Thread.sleep(1); // 일시정지
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("예외발생");
        }
        System.out.println("리소스정리...");
        System.out.println("실행종료");

    }
}


public class InterruptTest {
    public static void main(String[] args) {
        Thread pThread = new PrintThread();
        pThread.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
            
        }
        
        pThread.interrupt();
    }
}
