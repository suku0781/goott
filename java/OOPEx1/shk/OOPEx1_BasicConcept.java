package shk;

import java.util.Arrays;
import java.util.Scanner;

public class OOPEx1_BasicConcept {
    public static void main(String[] args){
        // car 객체 생성
        Car car1 = new Car(); // 객체는 참조타입이다. car1객체에 Car메모리 주소를 넣어준다.

        Car[] carArr = new Car[10];
        for(int i = 0 ; i < 10 ; i++){
            carArr[i] = new Car();
            System.out.println(carArr[i].hashCode());
        }

        System.out.println("car1의 모델명 : " + car1.model + ", car1의 가격 : " + car1.price + ", 색상 : " + car1.color);

        Car car2 = new Car(); // 인스턴스 생성.
        car2.color = "화이트";
        car2.brandName = "kia";
        car2.model = "K3";
        car2.price = 20000000;
        System.out.println("car2의 모델명 : " + car2.model + ", car2의 가격 : " + car2.price + ", 색상 : " + car2.color);

        // System.out.println(Arrays.hashCode(carArr));
        // 메서드
        // static method
        System.out.println(Math.abs(-3));

        // non-static method
        Scanner sc = new Scanner(System.in);
        System.out.println("num>>>");
        int num = sc.nextInt();
        System.out.println("num>>>"+num);

        sc.close();
//        sc = null;

        System.out.println("num>>>");
//        int num1 = sc.nextInt(); //  Scanner closed
//        System.out.println("num1>>>"+num1);

        // Car객체의 멤버 메서드 displayCar()호출
        car1.displayCar();
        car2.displayCar();
    }
}
