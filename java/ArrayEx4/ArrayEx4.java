import java.util.Arrays;
import java.util.Scanner;

public class ArrayEx4 {
    public static void main(String[] args){

        // 문제) int배열에 (1 ~ 100사이)난수 20개를 넣어놓고 유저에게 찾을 숫자를 입력 받고
        // 출력 : 찾는 값은 n번째에 있습니다.
        int[] array = new int[20];
        for(int i = 0 ; i < array.length; i++){
            array[i] = (int)(Math.random()*100+1);
        }
        System.out.println("array" + Arrays.toString(array));
        System.out.print("숫자 입력: ");
        Scanner sc = new Scanner(System.in);
        int inputNumber = sc.nextInt();


        for(int i = 0 ; i < array.length ; i++){
            if(array[i] == inputNumber){
                System.out.println("입력한 숫자" + inputNumber + "는 " + i + "번째에 있습니다.");
                break;
            } else if(i == array.length-1) {
                System.out.println("입력한 숫자"+inputNumber+"가 배열에 없습니다.");
            }

        }


    }
}
