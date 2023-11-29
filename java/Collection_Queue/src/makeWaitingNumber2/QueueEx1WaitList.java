package makeWaitingNumber2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * packageName : makeWaitingNumber2
 * fileName : QueueEx1WaitList
 * author : goott5
 * date : 2023-11-28
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-28          goott5             최초생성
 **/
public class QueueEx1WaitList {

    private static void issueWaitNumber(Queue<Integer> waitList){
        // 대기번호 생성
        int waitNumber = waitList.size()+1;
        waitList.offer(waitNumber);
        System.out.println("대기번호 " + waitNumber + "가 발급되었습니다.");
    }

    private static void callWaitNumber(Queue<Integer> waitList) {
        if(waitList.isEmpty()){
            System.out.println("대기중인 손님이 없습니다.");
        } else {
            int nextNumber = waitList.poll();
            System.out.println(nextNumber + "번 호출.");
        }
    }

    public static void main(String[] args){
        Queue<Integer> waitList = new LinkedList<>();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1. 대기 번호 발급 | 2. 대기 번호 호출 | 3. 종료");
            System.out.print("메뉴를 선택하십시오. >> ");

            int choice = sc.nextInt();
            switch (choice){
                case 1:
                    System.out.println("");
                    issueWaitNumber(waitList);
                    break;
                case 2:
                    System.out.println("");
                    callWaitNumber(waitList);
                    break;
                case 3:
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("잘못된 선택입니다. 다시 선택하십시오.");
            }
        }


    }


}
