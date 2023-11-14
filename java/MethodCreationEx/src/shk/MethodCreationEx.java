package shk;

public class MethodCreationEx {
    public static int add(int a, int b){ // static메서드
        return a+b;
    }

    public float add(float a, float b){ // non-static메서드
        float result = 0f;
        result = a + b;
        return result;
    }

    // 메서드 오버로딩
    public static int add(int num1, int num2, int num3){
        return num1 + num2 + num3;
    }

    public float add(int n, float m){
        return n + m;
    }

    public String outputNTimes(String a, int b){
        for(int i = 0 ; i < b ; i++) {
            System.out.println(a);
        }

        return a;
    }

    public void outputNTimes2(String a, int b){
        for(int i = 0 ; i < b ; i++) {
            System.out.println(a);
        }
    }

    public static void main(String[] args) {
        MethodCreationEx mce = new MethodCreationEx();
        System.out.println(MethodCreationEx.add(1,2)); // static메서드 초출 : 클래스이름.메서드명
        System.out.println(add(516,84));// 호출하는 곳과 호출되는 클래스가 같다면 메서드 호출 시 클래스명 생략 가능.
        System.out.println("add오버로딩된 메서드 출력값 : "+add(100, 200, 300));// 오버로딩된 메서드 호출
        System.out.println("add오버로딩된 메서드 출력값 : "+mce.add(100, 2.01f));// 오버로딩된 메서드 호출


        float fResult = mce.add(3.14f, 5.12f);
        System.out.println(fResult);

        // 이름이 outputNTimes이고, 매개변수를 String 타입 변수 하나와 int타입 하나를 받아서 그 문자열을 화면에 n번 반복해서 출력하는 메서드(인스턴스 메서드)를 만들고 호출하기
        mce.outputNTimes2("이거 호출해봐", 4);

        MyMath2 mymath = new MyMath2(); // 인스턴스 메서드 호출 시 new 메서드를 사용하기.
        System.out.println("빼기"+mymath.subtract(300, 400));
        System.out.println("곱하기"+mymath.multiply(300, 400));
        System.out.println("나누기"+mymath.divide(300, 400));
        System.out.println("더하기"+(int)mymath.add(300, 400));

    }


}

class MyMath2{
    long subtract(long a, long b) {
        return a - b;
    }
    long multiply(long a, long b) {
        return a * b;
    }
    double divide(double a, double b) {
        return a / b;
    }
    double add(double a, double b) {
        return a + b;
    }
}