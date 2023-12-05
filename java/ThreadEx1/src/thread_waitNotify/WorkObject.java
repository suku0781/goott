package thread_waitNotify;

/**
 * packageName : thread_waitNotify
 * fileName : WorkObject
 * author : goott5
 * date : 2023-12-05
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-12-05          goott5             최초생성
 **/
public class WorkObject {
    public synchronized void methodA() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + " : methodA() 작업");
        notify(); // 다른 스레드를 실행. 현재 스레드는 대기상태로 만듦.

        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public synchronized void methodB() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + " : methodB() 작업");
        notify(); // 다른 스레드를 실행. 현재 스레드는 대기상태로 만듦.

        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
