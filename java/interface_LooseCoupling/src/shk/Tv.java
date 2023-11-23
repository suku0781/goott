package shk;

/**
 * packageName : shk
 * fileName : Tv
 * author : goott5
 * date : 2023-11-23
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-23          goott5             최초생성
 **/
public class Tv implements ElectronicDevice {
    private String brandName;

    public Tv(String brandName) {
        this.brandName = brandName;
    }

    @Override
    public void powerOn() {
        System.out.println("TvClass : Tv On.");
    }


}
