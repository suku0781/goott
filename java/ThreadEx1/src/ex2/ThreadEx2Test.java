package ex2;

import javax.swing.*;

/**
 * packageName : ex2
 * fileName : ThreadEx2Test
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
        String input = JOptionPane.showInputDialog("아무값이나 입력하세요.");
        System.out.println("입력값은 : " + input);

        for(int i = 0 ; i < 50 ; i++) {
            System.out.println(i);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

public class ThreadEx2Test {
    public static void main(String[] args) {
        ThreadEx ex = new ThreadEx();
        ex.start();

        for(int i = 0 ; i < 50 ; i++){
            System.out.println(i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
