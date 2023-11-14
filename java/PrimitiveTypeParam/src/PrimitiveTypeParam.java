class Data{
    int x;

}


public class PrimitiveTypeParam {
    public static void main(String[] args) {
        Data d = new Data();
        d.x = 10;
        System.out.println("x : " + d.x);

        change(d.x);
        System.out.println("change 메서드 호출 후 x : "+d.x);
    }

    static void change(int x) {
        x = 1000;
        System.out.println("change 메서드 x : "+x);
    }
}