package Collection_StackEx1;

import java.util.Stack;

/**
 * packageName : Collection_StackEx1
 * fileName : BrowserHistoryTest
 * author : goott5
 * date : 2023-11-28
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-28          goott5             최초생성
 **/
public class BrowserHistoryTest {
    private static Stack<String> historyStack = new Stack<>();
    private static Stack<String> forward = new Stack<>();
    
    // 방문 페이지 history 스택에 추가
    static void visitPage(String page) {
        historyStack.push(page);
        System.out.println("방문한 페이지 : " + page);
    }
    
    // 뒤로가기 메서드 
    static void goBack(){
        if(!historyStack.isEmpty()){ // historyStack이 비어있지 않다면 
            forward.push(historyStack.pop());
            
        } else{
            System.out.println("이전 페이지가 없습니다.");
        }


    }

    // 앞으로가기 메서드
    static void goForward(){
        if(!forward.isEmpty()){ // historyStack이 비어있지 않다면
            historyStack.push(forward.pop());
        } else{
            System.out.println("다음 페이지가 없습니다.");
        }
    }

    static void printCurrent() {
        System.out.println("historyStack : " + historyStack);
        System.out.println("forward : " + forward);
        if(!historyStack.isEmpty()) {
            System.out.println("현재 페이지 : " + historyStack.peek());
        } else {
            System.out.println("현재 페이지 : " + forward.peek());
        }
    }
    
    public static void main(String[] args){
        //페이지 방문
        visitPage("구글");
        visitPage("아마존");
        visitPage("아마존 - 애플 아이폰15 Pro Max");
        visitPage("아마존 - 구입 상세 주소 입력");
        visitPage("아마존 - 카드 번호 입력");

        printCurrent();
        goBack();
        printCurrent();
        goBack();
        printCurrent();
        goForward();
        goForward();
        printCurrent();

        goForward();
        printCurrent();
    }
}
