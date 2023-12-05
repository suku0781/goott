package ex3;

/**
 * packageName : ex3
 * fileName : ThreadJoinTest
 * author : goott5
 * date : 2023-12-04
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-12-04          goott5             최초생성
 **/
class SumThread extends Thread{
    private int sum;

    public int getSum() {
        return sum;
    }

    SumThread(String name){
        super.setName(name);
    }

    @Override
    public void run() {
        System.out.println("sum() 실행중...");
        for(int i = 0 ; i <= 100 ; i++) {
            sum += i;


        }
    }
}


public class ThreadJoinTest {
    public static void main(String[] args) {

        // 스레드 생성
        SumThread sumThread = new SumThread("sum-Thread");

        sumThread.start(); // runnable 한 상태로 전환.


        try {
            sumThread.join(); // main스레드는 일시정지 상태에 있으면서 sumThread가 계산작업을 마칠 때 까지 기다린다.
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("1~100 까지의 합 : " + sumThread.getSum());
    }
}
