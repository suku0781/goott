import java.lang.reflect.Array;
import java.util.Arrays;

public class ArrayEx5 {
    public static void main(String[] args){
        // 셔플
        // int 배열에 0 ~ 9 까지 차례대로 넣고 섞은 후 섞인 배열을 출력
        int[] arr1 = new int[10];

        for (int i = 0; i < arr1.length; i++) {
            int ran = (int)(Math.random() * 10);
            boolean flag = false;
            for(int j = 0; j < i; j++) {
                if(arr1[j] == ran) {
                    flag = true;
                }
            }

            if (flag) {
                i = i - 1;
            } else {
                arr1[i] = ran;
            }
        }
        System.out.println("reuslt : " + Arrays.toString(arr1));




    }
}
