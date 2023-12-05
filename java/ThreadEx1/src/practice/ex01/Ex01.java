package practice.ex01;

/**
 * packageName : practice
 * fileName : ex01
 * author : goott5
 * date : 2023-12-05
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-12-05          goott5             최초생성
 **/
class ExtendThread extends Thread{
    @Override
    public void run() {
        System.out.println("상속받아서 가져온 메서드");
    }
}

class ImplThread implements Runnable{

    @Override
    public void run() {
        System.out.println("인터페이스를 구현하여 가져온 메서드");
    }
}

public class Ex01 {
    public static void main(String[] args) {
        ExtendThread t1 = new ExtendThread();
        ImplThread t2 = new ImplThread();
        Thread tr = new Thread(t2);
        t1.start();
        tr.start();

        try {
            Thread.sleep(2000);
            System.out.println("test" + Thread.currentThread());
            System.out.println("test");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
