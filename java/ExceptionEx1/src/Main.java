import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * packageName :
 * fileName : ${NAME}
 * author : goott5
 * date : 2023-11-24
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-24          goott5             최초생성
 **/
public class Main {
    String errorMessage(String error) {
        return "";
    }


    public static void main(String[] args) {
        int result = 0;
        Scanner sc = new Scanner(System.in);

        System.out.print("정수를 입력하십시오. >> ");

        try {
            int num = sc.nextInt();
            result = num / 3;
            System.out.println("입력한 숫자 : " + result);
        } catch (InputMismatchException e) { // 예외 발생 당시 호출스택에 있었던 메서드의 정보와 예외메시지를 화면에 출력
            System.out.println("다음 입력시 정수를 입력하십시오.");
        } catch (ArithmeticException e) { // 발생한 예외 클래스의 인스턴스에 저장된 메시지
            System.out.println(e.getMessage());
            System.out.println("다음 입력시 0을 제외한 정수를 입력하십시오.");
        } catch(Exception e){ // 모든 예외의 최고 조상이므로 가장 마지막 catch에 입력해주어야한다.
            System.out.println("처리하지 않은 다른 예외가 발생함.");
        } finally { // tru블럭에서 예외가 발생하지 않아도 반드시 수행되는 블럭(생략 가능)
            System.out.println("Finally 끝.");
        }
        System.out.println("찐막.");
    }
}