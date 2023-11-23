package shk3;

/**
 * packageName : shk3
 * fileName : BindingTest3
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

        System.out.println("[test] x = " + x); // 20 본인의 x 값
        System.out.println("[test] super.x = " + super.x); // 10 부모 x 값
        System.out.println("[test] this.x = " + this.x); // 20 현 객체 x 값


    }
}

public class BindingTest3 {
    public static void main(String[] args){
        Parent p = new Child();
        Child c = new Child();

        System.out.println("p.x : " + p.x);
        System.out.println("c.x : " + c.x);
        p.method(); // overriding 된 메서드가 출력됨.
        c.method();

    }
}
