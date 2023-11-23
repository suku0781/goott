package shk;

/**
 * packageName : shk
 * fileName : ElectricProduct
 * author : goott5
 * date : 2023-11-22
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-22          goott5             최초생성
 **/
public class ElectronicProduct {
    private String productName;
    private int price;
    private int pointRate;

    public ElectronicProduct(String productName, int price, int pointRate){
        this.productName = productName;
        this.price = price;
        this.pointRate = pointRate;
    }

    public String getProductName(){
        return this.productName;
    }

    public int getPrice(){
        return this.price;
    }

    public void setProductName(String prdNm) {
        this.productName = prdNm;
    }

    @Override
    public String toString() {
        return "ElectronicProduct{" +
                "productName='" + productName + '\'' +
                ", price=" + price +
                ", pointRate=" + pointRate +
                '}';
    }
}
