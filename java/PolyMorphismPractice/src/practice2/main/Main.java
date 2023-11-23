package practice2.main;

import practice2.employees.FullTimeEmployee;
import practice2.employees.PartTimeEmployee;

/**
 * packageName : practice2.main
 * fileName : main
 * author : goott5
 * date : 2023-11-22
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-22          goott5             최초생성
 **/
public class Main {
    public static void main(String[] args) {
        Department 디지털사업본부 = new Department("디지털사업본부");
        Department 디지털사업1본부 = new Department("디지털사업1본부");
        Department 디지털사업2본부 = new Department("디지털사업2본부");

        Employees hay = new FullTimeEmployee("hayoung", "전임", null, "01012345657", 971010, "금천구 독산동");
        Employees hyo = new PartTimeEmployee("hyunseong", "인턴", null, "01056456542", 971111, "금천구 금천동", 8);
        Employees hyk = new FullTimeEmployee("suhyeok", "책임", null, "01012345678", 970411, "금천구 가산동");

        디지털사업본부.setEmpList(hay);
        디지털사업1본부.setEmpList(hyo);
        디지털사업2본부.setEmpList(hyk);


        System.out.println(디지털사업본부);
        System.out.println(디지털사업1본부);
        System.out.println(디지털사업2본부);

        System.out.println(hay);
        System.out.println(hyo);
        System.out.println(hyk);

    }
}
