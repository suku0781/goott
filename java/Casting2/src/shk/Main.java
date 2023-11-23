package shk;

/**
 * packageName :
 * fileName : ${NAME}
 * author : goott5
 * date : 2023-11-21
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-21          goott5             최초생성
 **/

class Parent{
    int x = 10;
    void method() {
        System.out.println("Parent Method");

    }
}

class Child extends Parent{

}


public class Main {
    public static void main(String[] args) {
        Parent p = new Child();
        Child c = new Child();

        System.out.println("p.X" + p.x);
        System.out.println("c.X" + c.x);

        p.method();
        c.method();
    }
}