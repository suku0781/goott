package shk;

/**
 * packageName : shk
 * fileName : Computer
 * author : goott5
 * date : 2023-11-23
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-23          goott5             최초생성
 **/
public class Computer implements ElectronicDevice {

    @Override
    public void powerOn() {
        System.out.println("Booting a " + getClass().getName());
    }
}
