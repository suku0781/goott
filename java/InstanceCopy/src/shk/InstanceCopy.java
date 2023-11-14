package shk;

public class InstanceCopy {
    public static void main(String[] args) {
        Car car = new Car();
        System.out.println(car.hashCode() + " " + car.toString());
        Car car2 = new Car("기아", "쏘렌토", 45000000);
        System.out.println(car2.hashCode() + " " + car2.toString());
        Car car3 = new Car("쌍용", "토레스", 29000000);
        System.out.println(car3.hashCode() + " " + car3.toString());
        Car car4 = new Car(car3);
        System.out.println(car4.hashCode() + " " + car4.toString());
        car3.brand = "현대";
        car3.carName = "산타페";
        car3.price = 45000000;
        System.out.println(car3.hashCode() + " " + car3.toString());
    }
}