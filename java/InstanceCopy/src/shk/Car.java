package shk;

public class Car {
    String brand;
    String carName;
    int price;

    Car() {
        this("현대", "소나타", 35000000);
    }

    Car(String brand, String carName, int price) {
        this.brand = brand;
        this.carName = carName;
        this.price = price;
    }

    Car(Car c) {
        this.brand = c.brand;
        this.carName = c.carName;
        this.price = c.price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" + "brand='" + brand + '\'' + ", carName='" + carName + '\'' + ", price=" + price + '}';
    }
}
