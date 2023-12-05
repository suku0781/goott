package thread_waitNotify;

/**
 * packageName : thread_waitNotify
 * fileName : ThreadA
 * author : goott5
 * date : 2023-12-05
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-12-05          goott5             최초생성
 **/
public class ThreadA extends Thread{
    private WorkObject workObject;

    public ThreadA(WorkObject workObject) {
        setName("ThreadA");
        this.workObject = workObject;
    }

    @Override
    public void run() {
        for(int i = 0 ; i < 10 ; i++) {
            workObject.methodA();
        }
    }
}
