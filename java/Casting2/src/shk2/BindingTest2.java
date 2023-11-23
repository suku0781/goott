package shk2;

/**
 * packageName : shk2
 * fileName : BindingTest2
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

class Child extends Parent {
    int x = 20;

    void method() { // @Override 어노테이션을 붙이지 않았는데 자동으로 됨. Overrides method in Parent
        System.out.println("Child Method");

    }
}



public class BindingTest2 {
    public static void main(String[] args) {
//        Parent p = new Parent(); // 본인의 참조타일으로 본인의 인스턴스를 생성.
        Parent p = new Child(); // 다형성 override 된 메서드를 출력함.

        Child c = new Child(); // 본인의 참조타일으로 본인의 인스턴스를 생성.

        System.out.println("p.x " + p.x);
        System.out.println("c.x " + c.x);
        p.method(); // p.method
        c.method(); // c.method
    }

}
