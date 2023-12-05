package thread_interrupt;

import javax.swing.*;

/**
 * packageName : thread_interrupt
 * fileName : InterruptTest2
 * author : goott5
 * date : 2023-12-05
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-12-05          goott5             최초생성
 **/
class InterThread extends Thread{
    @Override
    public void run() {
        int i = 10;

        while (i != 0 && !isInterrupted()) { // isInterrupted() : 인터럽트가 되었는지 안되었는지 boolean값을 반환함.
            System.out.println(i--);
            for(long x = 0; x < 2500000000L ; x++); // 무의미한 for문(지연시키기 위함)
        }

        System.out.println("탈출! " + this.isInterrupted());

    }
}

public class InterruptTest2 {

    public static void main(String[] args) {
        InterThread t1 = new InterThread();
        t1.start();

        String input = JOptionPane.showInputDialog("아무값이나 입력");
        System.out.println(input);

        t1.interrupt(); // interrupt를 호출하면 interrupt상태가 true
        System.out.println("isInterrupt : " + t1.isInterrupted());
        System.out.println("isInterrupt : " + t1.isInterrupted());
        System.out.println("main : " + Thread.interrupted()); // main스레드가 interrupt되었느지
        System.out.println("main : " + Thread.interrupted());
    }
}
