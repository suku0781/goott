package shk;

public class MobilePhone {
    // private 접근제어자 : 같은 클래스 내에서만 접근 허용.
    private String brand;
    private String model;
    private int ram;

    // 기본생성자
    public MobilePhone(){
        System.out.println("고객님 폰이 개통되었습니다.");
    }

    //생성자 오버로딩
    public MobilePhone(String brand, String model, int ram){
        this.brand = brand;
        this.model = model;
        this.ram = ram;
    }

    // 생성자 오버로딩
    public MobilePhone(String brand, int ram){
//        this(); //기본생성자 호출 (기본 생성자는 반드시 첫줄에 위치해야 한다.)

        this(brand, null, ram);
    }

//    public MobilePhone(String model, int ram){ // 오버로딩 실행규칙 위반(지역변수 타입이 일치)
//        this(null, model, ram);
//    }

    // 멤버변수 , 생성자, 메서드 , toString() 순서가 관습적임.
    // get멤버변수1(); getter 생성
    // set멤버변수(); setter 생성
    String getBrand(){ return this.brand; }
    String getModel(){ return this.model; }
//    int getRam(){ return this.ram; }
    String getRam(){ return this.ram + "GB"; }

    void setBrand(String brand){ this.brand = brand; }
    void setModel(String model){ this.model = model; }
    void setRam(int ram){ if(setRam2(ram)) this.ram = ram; }

    // ram은 32까지만 가능.
    boolean setRam2(int ram){
        if(ram < 32) {
            return true;
        } else{
            return false;
        }
    }

    public String toString(){
        return "["+this.getClass().getName()+" : "+this.hashCode()+"] brandName : " + this.brand + ", model : " + this.model + ", ram : " + this.ram;
    }
}
