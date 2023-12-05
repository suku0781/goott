package ex5;

/**
 * packageName : ex5
 * fileName : ThreadEx5
 * author : goott5
 * date : 2023-12-05
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-12-05          goott5             최초생성
 **/
public class ThreadEx5 {

    public static void main(String[] args) {
        yThread th1 = new yThread("*");
        yThread th2 = new yThread("**");
        yThread th3 = new yThread("***");

        th1.start();
        th2.start();
        th3.start();

        try {
            Thread.sleep(2000);
            th1.suspend();

            Thread.sleep(2000);
            th2.suspend();

            Thread.sleep(2000);
            th1.resume();

            Thread.sleep(2000);
            th1.stop();
            th2.stop();

            Thread.sleep(2000);
            th3.stop();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}

class yThread implements Runnable{
    boolean stopped = false;
    boolean suspended = false;

    Thread t1;

    public yThread(String name) {
        t1 = new Thread(this, name); // Thread(Runnable target, String name)
    }

    public void suspend(){
        suspended = true;
        t1.interrupt();
        System.out.println(t1.getName() + " interrupt() by suspend()");
    }

    public void resume(){
        suspended = false;
    }

    public void stop(){
        stopped = true;
        t1.interrupt();
        System.out.println(t1.getName() + " interrupt() by stop()");

    }

    public void start(){
        t1.start();
    }

    @Override
    public void run() {
        String name = t1.getName();

        while (!stopped) {
            if(!suspended){ // suspend가 안된 상황
                System.out.println(name);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                // suspend가 된 상황
                Thread.yield(); // 다른 스레드에게 양보

            }

        }
        System.out.println(name + " stopped. ");

    }
}
