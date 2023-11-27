package makeBackForwardBtn;

import java.util.EmptyStackException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;

/**
 * packageName : makeBackForwardBtn
 * fileName : MakeBackForwardBtn
 * author : goott5
 * date : 2023-11-27
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-27          goott5             최초생성
 **/
public class MakeBackForwardBtn {
    static Stack<String> stack = new Stack<>();
    static Stack<String> behind = new Stack<>();
    static Scanner sc = new Scanner(System.in);
    static String now = null;

    private static int test(){
        try {
            behind.push(stack.pop());
            now = stack.peek();
            System.out.println("-----------------------------");
            return babyOneMoreTime() ? 1 : 2;
        } catch (EmptyStackException | InputMismatchException e) {
            System.out.println("뒤로 갈 페이지가 없습니다.");
            return 9;
        }
    }

    private static int test2(){
        try {
            stack.push(behind.pop());
            now = stack.peek();
            System.out.println("-----------------------------");
            return babyOneMoreTime() ? 3 : 4;
        } catch (EmptyStackException | InputMismatchException e) {
            System.out.println("앞으로 갈 페이지가 없습니다.");
            return 9;
        }
    }

    private static int test3() {
        System.out.print("이동할 사이트명 입력 >> ");
//
//        try {
//            stack.push(behind.pop());
//            now = stack.peek();
//            System.out.println("-----------------------------");
//            return babyOneMoreTime() ? 3 : 4;
//        } catch (EmptyStackException | InputMismatchException e) {
//            System.out.println("앞으로 갈 페이지가 없습니다.");
//            return 9;
//        }
        return 9;
    }

    public static boolean babyOneMoreTime(){
        System.out.print("한번더? ");
        if(sc.nextLine().equals("y") || sc.nextLine().equals("Y")){
            return true;
        } else {
            return false;
        }

    }

    public static void pushNumber(){
        stack.push("네이버");
        stack.push("카카오톡");
        stack.push("스택오버플로우");
        stack.push("구글");
        stack.push("유튜브");
        stack.push("깃허브");
        stack.push("넷플릭스");

        now = stack.peek();
    }

    public static void main(String[] args) {
        // 사이트 기록
        pushNumber();

        System.out.println("[접속 페이지 정보] " + stack);

        do{
            System.out.println("현재 페이지 : " + stack.peek());
            System.out.print("뒤로 r키 | 앞으로 u키 | 새로운 페이지 t키");
        } while( (((sc.nextLine().equals("r")) ? test() : (sc.nextLine().equals("u")) ? test2() : test3())%2 != 0) );

        System.out.println("종료됨...");
        System.out.println("마지막으로 접속한 페이지 : " + now);
    }



}
