package teacherCode;

public class Product {
    private int price;// 제품가격
    private int point;// 적립금 (가격의 10%)

    public Product(int price) {
        this.price = price;
        this.point = (int)price/10;


    }


    public int getPrice() {
        return price;
    }


    public void setPrice(int price) {
        this.price = price;
    }


    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }


    @Override
    public String toString() {
        return "Product [price=" + price + ", point=" + point + "]";
    }



}