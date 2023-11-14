package shk;

public class OOPEx2_Constructor {
    public static void main(String[] args) {
        MobilePhone phone = new MobilePhone();

        MobilePhone galaxy = new MobilePhone("Samsung", "Galaxy S23 Ultra", 12);
        System.out.println(galaxy);
        System.out.println(phone.toString());

        MobilePhone iphone = new MobilePhone("apple", 8);
        System.out.println(iphone.toString());

//        iphone.model = "iPhone"; // 바람직한 코드는 아님.
        System.out.println(iphone.toString());
        System.out.println(galaxy.getRam());
//        System.out.println(galaxy.setRam(16));
        galaxy.setRam(40);
        System.out.println(galaxy.toString());
        Mobile phone2 = new Mobile("Samsung", "Galaxy Fold 5", 16);
        System.out.println(phone2.toString());
        System.out.println(phone2.getModel());
        phone2.setModel("Galaxy Z Flip 5");
        phone2.setRam(12);
        System.out.println(phone2.toString());


    }
}