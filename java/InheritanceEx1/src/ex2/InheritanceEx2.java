package ex2;

/**
 * packageName : ex2
 * fileName : InheritanceEx2
 * author : goott5
 * date : 2023-11-20
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-20          goott5             최초생성
 **/
class ParentA{
    private int a = 100;

    public int getA(){
        return this.a;
    }
}

class parentB{

}

//    class ETC extends parentA, parentB{ // 다중상속불가.
//
//    }

class Child extends ParentA{

}

public class InheritanceEx2 extends ParentA {

    public static void main(String[] args) {
        System.out.println(new ParentA().getA());
        System.out.println(new Child().getA());

        System.out.println(new InheritanceEx2().getA());

        // Instanceof 연산자
        ParentA objParentA = new ParentA();
        Child objChild = new Child();

        if(objChild instanceof Child){
            System.out.println("objChild가 child의 객체 : 참");
        } else {
            System.out.println("objChild가 child의 객체 : 거짓");
        }

        if(objChild instanceof ParentA){
            System.out.println("objChild가 ParentA의 객체 : 참");
        } else {
            System.out.println("objChild가 ParentA의 객체 : 거짓");
        }

        if(objParentA instanceof Child){
            System.out.println("objParentA가 Child의 객체 : 참");
        } else {
            System.out.println("objParentA가 Child의 객체 : 거짓");
        }

        if(objChild instanceof parentB){ //  incompatible types: ex2.Child cannot be converted to ex2.parentB
            System.out.println("objChild가 parentB의 객체 : 참");
        } else {
            System.out.println("objChild가 parentB의 객체 : 거짓");
        }

    }


}
