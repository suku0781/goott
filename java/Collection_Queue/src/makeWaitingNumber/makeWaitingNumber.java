package makeWaitingNumber;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * packageName : PACKAGE_NAME
 * fileName : makeWaitingNumber
 * author : goott5
 * date : 2023-11-28
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-28          goott5             최초생성
 **/
public class makeWaitingNumber {
    private static Scanner sc = new Scanner(System.in);
    private static Queue<Integer> queue = new LinkedList<>();
    static int count = 0;
    static long now = System.currentTimeMillis();
    static long currentTime = 0;


    private static void display(int num) {
        if(num == 0){
            System.out.println("번호표를 뽑고 대기하십시오.");
            System.out.println("1. 번호표 뽑기 | 9. 시스템 종료");
        } else {
            System.out.println(" 진료실로 들어오십시오.");
            System.out.println("번호표를 뽑고 대기하십시오.");
            System.out.println("1. 번호표 뽑기 | 9. 시스템 종료");
        }
    }

    private static void pullNumber() {

    }

    private static void exe(boolean dpYn) {
        boolean dpDisplay = true;
        if (dpYn) { display(0); } else { display(1); }
        final int num = Integer.parseInt(sc.nextLine());
        if(num == 1) {
            System.out.println(count + " "  + queue);
            queue.add(count++);
        } else if(num == 8){
            callNumber();
            dpDisplay = false;
        }

        if(num != 9) exe(dpDisplay);
    }

    private static void callNumber() {
        System.out.print(queue.poll() + "번 고객님");
    }

    public static void main(String[] args) {
        exe(true);

    }
}
