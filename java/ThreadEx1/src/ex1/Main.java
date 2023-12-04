package ex1;

/**
 * packageName :
 * fileName : ${NAME}
 * author : goott5
 * date : 2023-12-04
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-12-04          goott5             최초생성
 **/

class ThreadEx extends Thread{
    @Override
    public void run() {
        for(int i = 0 ; i < 10 ; i++) {
            System.out.println(this.getName() + ", i = " + i);
        }
    }
}

class ThreadRunnable implements Runnable{

    @Override
    public void run() {
        for (int i = 0 ; i < 10 ;i++) {
            System.out.println(Thread.currentThread().getName() + ", i = " + i);
        }
//
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }
}

public class Main {
    public static void main(String[] args) {
        // 스레드 객체 생성 방법.
        // 1) Thread 클래스 상속받아서 구현한 클래스
        ThreadEx t1 = new ThreadEx();

        // 2) Runnable 인터페이스를 구현한 클래스
        ThreadRunnable r = new ThreadRunnable();
        Thread t2 = new Thread(r);

        // 우선순위(set하기위해서는 start() 메서드 실행 전에 써준다.)
        t1.setPriority(Thread.MAX_PRIORITY);
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY); // priority 우선순위대로 한다는 보장은 없음.

        // 스레드 실행
        t1.start();
        t2.start();

//        try {
////            t1.sleep();// sleep() 메서드는 static 메서드 이므로 이와같이 사용하지 않는다.
//            Thread.sleep(2000); // 항상 현재 실행중인 스레드에 동작. (현재 스레드는 mainThread)
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        for(int i = 0 ; i < 10 ; i++){
            System.out.println(Thread.currentThread().getName() + ", i = " + i);
            for(int j = 0 ; j < 100000 ; j++);
        } // 스케줄러가 조절해줌.
    }
}