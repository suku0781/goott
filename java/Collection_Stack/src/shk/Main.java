package shk;

import java.util.List;
import java.util.Stack;

/**
 * packageName :
 * fileName : ${NAME}
 * author : goott5
 * date : 2023-11-27
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-27          goott5             최초생성
 **/
public class Main {
    public static void main(String[] args) {
        // Stack : 자료구조의 Stack구조를 클래스화 한 것.
        // LIFO(Last In First Out)
//        List<String> stack = new Stack<String>();
        Stack<String> stack = new Stack<String>();

        stack.push("구글");
        stack.push("인텔리제이");
        stack.push("카카오톡");
        stack.push("자바API");
        stack.push("구글");
        stack.push("지메일");

        System.out.println(stack.peek()); // 지메일, 스택에서 가장 마지막 데이터를 꺼냈다가 다시 집어넣음.
        System.out.println(stack.indexOf("자바API")); // 3
        System.out.println(stack.indexOf("구글")); // 3
        System.out.println(stack + " | " + stack.pop() + " | " + stack); // 3

        for(int i = stack.size()-1 ; i >= 0 ; i-- ){
            System.out.println(stack.get(i));
        }

        while(!stack.empty()){
            System.out.println(stack.pop());
        }
        System.out.println(stack.isEmpty());


    }
}