package shk;

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
public class ExceptionEx3 {

    //  사용자에게 입력받은 수를 리턴하는 메서드
    private static int getPositiveNumber(String msg) throws InputMismatchException {
        System.out.print(msg +" >> ");
        Scanner sc = new Scanner(System.in);
        int tmp = sc.nextInt();
        return tmp;
    }





    public static void main(String[] args) throws InputMismatchException {
        int sum = 0;
        try {
            sum = getPositiveNumber("숫자를 입력하십시오.");
            System.out.println(sum);
        } catch (InputMismatchException e) {
            System.out.println("숫자가 아닙니다.");
        }

        getPositiveNumber("문자열을 입력하십시오.");
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        int num2 = Integer.parseInt(str);
        System.out.println("num2 : " + num2);




    }
}