/**
 * packageName : PACKAGE_NAME
 * fileName : Phone
 * author : goott5
 * date : 2023-11-23
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-23          goott5             최초생성
 **/
public abstract class Phone {
    public abstract void calling(); // 추상메서드 : 구현부는 없어야한다.

    void receiving() // 컴파일러가 public abstract를 자동으로 붙여준다.
    {

    }
}
