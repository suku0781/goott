package teacherCode;

public class Buyer {
    private int money = 1000000;
    private int point = 0;

    Product[] item = new Product[10]; //구매제품을 저장하는 배열

    int i = 0; // item배열에 사용할 인덱스카운트
    //기본 생성자
    public Buyer() {
    }
    public void buy (Product p) {
        if(this.money < p.getPrice()) {
            System.out.println("돈이 부족합니다");
            return;
        }
        this.money -= p.getPrice(); // 구입한 제품 가격 빼기 -=
        this.point += p.getPoint(); // 포인트누적 +=
        this.item[i++] = p; // 구매제품 배열에 제품을 넣기
        System.out.println(p.toString() + "물건을 구입했습니다");
    }

    public void outputReceipt() { //구매목록출력
        int sum = 0;

        System.out.println("-------구매 제품 목록 -------");
        for (int i = 0; i < item.length; i++) {
            if(item[i] == null) break;
            System.out.println(item[i] + "(" + item[i].getPrice() + "원)");

            sum += item[i].getPrice();
        }
        System.out.println("구입하신 물품의 총 금액은" + sum + "입니다");
        System.out.println("적립포인트는" + this.point + "점 입니다");
    }
}
