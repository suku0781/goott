package shk;



public class StaticVsInstance {
    static int sInt; // static 멤버변수
    int iInt; // instance 멤버변수

    {
        // 초기화 블럭
        System.out.println("iInt의 초기값: " + iInt);
        iInt = 5;
        System.out.println("iInt의 값: " + iInt);
    }

    static{
        // static 멤버 초기화 블럭
        System.out.println("sInt의 초기값: " + sInt);
        sInt = 100;
        System.out.println("sInt의 값: " + sInt);
    }

    public StaticVsInstance(){
        System.out.println("기본 생성자 호출됨.");
    }

    public void acc(){
        sInt++;
        this.iInt++;

        System.out.println("no : " + this.hashCode() + ", sInt : " + StaticVsInstance.sInt + ", iInt : " + this.iInt);
    }

    public static void main(String[] args) {
        // 코드를 타이핑 하고 static과 instance의 차이점을 구별하시오.
        for(int i = 0 ; i < 10 ; i++) {
            StaticVsInstance svi = new StaticVsInstance(); // 컴파일러가 기본생성자 생성.
            svi.acc();
        }



    }
}