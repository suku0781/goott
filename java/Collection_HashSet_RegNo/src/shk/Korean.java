package shk;

import java.util.Objects;

import static java.lang.Object.*;

/**
 * packageName : shk
 * fileName : Korean
 * author : goott5
 * date : 2023-11-28
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-28          goott5             최초생성
 **/
public class Korean {
    private String regNo; // 주민번호
    private String name; // 이름

    public Korean(String regNo, String name) {
        this.regNo = regNo;
        this.name = name;
    }

    public String getRegNo() {
        return regNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return (this.regNo + this.name).hashCode();
    }

//    @Override
//    public boolean equals(Object obj) {
//        if(this == obj) return true;
//        if(obj == null || getClass() != obj.getClass()) return false;
//        Korean kor = (Korean) obj;
//        return Objects.equals(regNo, kor.regNo);
////        return super.equals(obj);
//    }

    // 선생님 코드
    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if(obj instanceof Korean){
            Korean tmp = (Korean) obj;
            if(this.regNo.equals(tmp.regNo) && this.name.equals(tmp.name)){
                result = true;
            }
        }
        return result;
    }
    // 선생님 코드 end

    @Override
    public String toString() {
        return "Korean{" +
                "regNo='" + regNo + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

}
