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
public class ExceptionEx2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = 0;
        boolean isPos = false;
        while(!isPos){
            try {
                System.out.print("양수를 입력하십시오. >> ");
                num = sc.nextInt();

                if(num <= 0) throw new NoPositionInteger("양수가 아닙니다.");
                System.out.println("입력한 수 : " + num);
                isPos = true;
            } catch (InputMismatchException e) {
                System.out.println("양의 정수를 입력하십시오.");
            } catch (NoPositionInteger e) {
//            throw new RuntimeException(e);
//            e.printStackTrace();
                System.out.println("입력한 숫자 "+num+"은 양수가 아닙니다. ");
                System.out.println(e.getMessage());
            }
        }



    }
}