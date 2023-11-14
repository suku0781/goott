import java.util.Arrays;
import java.util.Scanner;

public class ArrayEx2 {
    public static void main(String[] args) {
        // 연습문제
        // 거스름돈을 몇개의 동전으로 지불할 수 있는지 구하는 프로그램을 작성하시오.
        // 예를들어 거스름돈 money = 2680 이라고 하면
        // 출력 500원짜리 동전 5개, 100원짜리 동전 1개, 50원 짜리 동전1개, 10원 짜리 동전 3개

//        int change = 4390;
//        int[] coins = new int[4];
//        int coin500 = (change / 500);
//        change -= 500 * coin500;
//        coins[0] = coin500;
//
//        int coin100 = (change / 100);
//        change -= 100 * coin100;
//        coins[1] = coin100;
//
//        int coin50 = (change / 50);
//        change -= 50 * coin50;
//        coins[2] = coin50;
//
//        int coin10 = (change / 10);
//        change -= 10 * coin10;
//        coins[3] = coin10;
//
//        System.out.println("코인 : " + Arrays.toString(coins));

//        Scanner sc = new Scanner(System.in);
//        System.out.println("잔돈을 입력하십시오. (최소단위 10원)");
//        int change = sc.nextInt();
        int change = 5680;
        int[] coins = {500, 100, 50, 10};
        int[] coinCnt = new int[4];
        for(int i = 0 ; i < coins.length ; i++){
            int tmp = (change / coins[i]);
            change -= coins[i] * tmp;
            coinCnt[i] = tmp;
        }

        System.out.println("500원 : " + coinCnt[0] + ", 100원 : " + coinCnt[1] + ", 50원 : " + coinCnt[2] + ", 10원 : " + coinCnt[3]  );


        // 69, 28, 91, 33, 99, 55, 95, 11, 44
        // 정수배열에 넣고 최댓값과 최솟값을 구하시오.
        int[] intArr = {69, 28, 91, 33, 99, 55, 95, 11, 44};
        int maxNum = intArr[0];
        int minNum = intArr[0];
        for(int i = 1 ; i < intArr.length ; i++) {
            if(maxNum < intArr[i]) maxNum = intArr[i];
            if(minNum > intArr[i]) minNum = intArr[i];
        }

        System.out.println("최대값 : " + maxNum + ", 최소값 : " + minNum);

    }
}
