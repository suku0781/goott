package shk;

public class ExceptionEx4Test {
    public static void method1() {
        boolean isSuccess = true;

        try {
            System.out.println("\t method1 : 로직 처리");
            if (isSuccess) {
                System.out.println("\t method1 : 실행");
                return;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("\t method1: 필수로직처리");
    }

    public static void method2() {
        boolean isSuccess = true;

        try {
            System.out.println("\t method2 : 로직 처리");
            if (isSuccess) {
                System.out.println("\t method2 : 실행");
                return;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            System.out.println("\t method2: finally 필수로직 처리");
        }

    }


    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println("method1() 호출");
        method1();

        System.out.println("metdho2() 호출");
        method2();
    }

}