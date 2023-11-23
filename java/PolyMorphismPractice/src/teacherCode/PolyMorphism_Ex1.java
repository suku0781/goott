package teacherCode;

public class PolyMorphism_Ex1 {


    public static void main(String[] args) {
        //TODO Auto-generated method stub
        Buyer b = new Buyer();

        b.buy(new Tv(200000)); // 티비구매
        b.buy(new Monitor(100000));
        b.buy(new Computer());
        b.buy(new Tv(200000));
        b.buy(new Computer());

        b.outputReceipt(); // 영수증 출력
    }
}