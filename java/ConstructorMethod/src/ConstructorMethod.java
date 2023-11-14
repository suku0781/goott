class Data1{
    int num;
    Data1(){}; // 기본생성자가 만약 없다면 컴파일러가 자동으로 만들어준다.
}

class Data2{
    int num;
    Data2(int x){ // 생성자를 오버로딩해서 int타입 수를 매개변수로 받아 멤버변수에 넣었다.
        num = x;
    };
}

public class ConstructorMethod {
    public static void main(String[] args) {
        Data1 d1 = new Data1();
//        Data2 d2 = new Data2(); // constructor Data2 in class Data2 cannot be applied to given types
        Data2 d2 = new Data2(3);
        System.out.println(d1.hashCode());
    }
}