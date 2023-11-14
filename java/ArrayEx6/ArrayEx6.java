import java.util.Arrays;

public class ArrayEx6 {
    public static void main(String[] args) {
        // 버블정렬
        // 0 ~ 9까지의 수를 랜덤하게 배열에 넣는다.
        // 버블정렬 방식으로 오름차순 정렬 해보기
        // 셔플
        // int 배열에 0 ~ 9 까지 차례대로 넣고 섞은 후 섞인 배열을 출력
        int[] array = new int[10];
        for(int i = 0 ; i < array.length ; i++) {
            array[i] = i;
        }
        System.out.println("array" + Arrays.toString(array));
        for(int i = 0 ; i < array.length ; i++) {
            int ind = (int)(Math.random()*10);
            int tmp = array[0];
            array[0] = array[ind];
            array[ind] = tmp;
            System.out.println(Arrays.toString(array));
        }


        for(int i = 0 ; i < array.length-1 ; i++) {
            boolean isSort = false;
            for(int j = 0 ; j < array.length -1-i ; j++) {
                if(array[j] > array[j + 1]){
                    // 자리바꾸기
                    int tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tmp;
                    isSort = true;
                }
            }

            for(int k = 0 ; k < array.length ; k++ ){
                System.out.print(array[k]);
            }
            System.out.println();
        }


    }

}
