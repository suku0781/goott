

public class ReferenceTypeParam {
    public static void change(int[] x){
        x[0] = 1000;
        System.out.println("change 메서드 x : " + x[0]);
    }
    public static void main(String[] args) {
        int[] x = {10};
        System.out.println("X : " + x[0]);
        change(x); // 참조타입을 매개변수로 전달
        System.out.println("change 메서드 호출 후 x : " + x[0]);
    }
}