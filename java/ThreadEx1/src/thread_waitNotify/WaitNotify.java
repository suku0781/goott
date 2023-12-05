package thread_waitNotify;

/**
 * packageName : thread_waitNotify
 * fileName : WaitNotify
 * author : goott5
 * date : 2023-12-05
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-12-05          goott5             최초생성
 **/
public class WaitNotify {
    public static void main(String[] args) {
        // 공유작업객체 생성.
        WorkObject workObject = new WorkObject();

        ThreadA threadA = new ThreadA(workObject);
        ThreadB threadB = new ThreadB(workObject);

        threadA.start();
        threadB.start();
    }
}
