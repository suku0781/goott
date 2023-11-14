package shk;

public class Car {
    // 멤버 변수
    String brandName = "현대";
    String model = "그랜저";
    int price = 45000000;
    String color = "검정색";

    // 멤버 메서드
    public void displayCar() {
        System.out.println("브랜드명 : " + brandName);
        System.out.println("모델명 : " + model);
        System.out.println("가격 : " + price);
        System.out.println("색상 : " + color);
    }

}
