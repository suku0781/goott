import java.util.Arrays;

public class MethodCreationEx1 {

    public static void dpArr(int[] arr, String t){
        System.out.println(t + " : "+Arrays.toString(arr));
    }
    public static void dpArr(int arr, String t){
        System.out.println(t + " : " + arr);
    }
    public static void ascArr(int[] arr) {
        for(int i = 0 ; i < arr.length ; i++) {
            for(int j = 0 ; j < arr.length-i ; j++){
                if(j+1 < arr.length){
                    if(arr[j] > arr[j+1]) {
                        int tmp = arr[j];
                        arr[j] = arr[j+1];
                        arr[j+1] = tmp;
                    }
                }
            }
        }

        dpArr(arr, "정렬");
    }
    public static void sumArr(int[] arr) {
        int sum = 0;
        for(int i : arr) {
            sum += i;
        }
        
        dpArr(sum, "총합");
    }
    
    public static void main(String[] args) {

        // 크기가 6인 int배열을 임의의 수로 생성.
        // 매서드 : 배열을 출력하는 메서드를 만들고 호출
        // 메서드 : 위의 배열을 오름차순 정렬하는 메서드를 만들고
        // 정렬된 배열을 출력
        // 메서드 : 배열의 총 합을 출력.
        int[] array = new int[6];
        for(int i = 0 ; i < array.length ; i++) {
            boolean isSame = false;
            int ranNum = (int)(Math.random() * 10 + 1);
            for(int j = 0 ; j < i ; j++ ) {
                if(array[j] == ranNum) isSame = true;
            }
            if(isSame){
                i--;
            } else {
                array[i] = ranNum;
            }
        }
        dpArr(array, "배열");
        ascArr(array);
        sumArr(array);


    }
}