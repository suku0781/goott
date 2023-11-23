package electronics;

import shk.ElectronicProduct;

/**
 * packageName : shk
 * fileName : Tv
 * author : goott5
 * date : 2023-11-22
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-22          goott5             최초생성
 **/
public class Tv extends ElectronicProduct {
    String brand;
    String model;
    int inch;

    public Tv(String productName, int price, int pointRate, String brand, String model, int inch){
        super(productName, price, pointRate);
        this.brand = brand;
        this.model = model;
        this.inch = inch;
    }

    public String getBrand(){
        return this.brand;
    }

    public String getModel(){
        return this.model;
    }

    public int getInch(){
        return this.inch;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
