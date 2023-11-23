package shk;

/**
 * packageName : shk
 * fileName : MobilePhone
 * author : goott5
 * date : 2023-11-23
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-23          goott5             최초생성
 **/
public class MobilePhone implements Remotable {

    @Override
    public void remoteControl(ElectronicDevice ed) {
        ed.powerOn();

        System.out.println("Controlling " + ed.getClass().getName());
    }
}
