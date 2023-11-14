import java.util.Arrays;

public class ArrayEx3_ArrayCopy {
    public static void main(String[] args){
        char[] chArr = new char[] {'a', 'b', 'c'};

        System.out.println("변경 전 원본 배열 : " + Arrays.toString(chArr));

        // 원본 배열을 복사 (Deep-Copy)
        char[] copyChArr = new char[chArr.length];
        int i = 0;
        for( char  c : chArr ){
            copyChArr[i++] = c;
        }
        System.out.println("변경 전 사본 배열 : " + Arrays.toString(copyChArr));

        System.out.println("원본 배열 주소값 : " + chArr.hashCode() + "사본 배열 주소값 : " + copyChArr.hashCode() );

        // 원본의 1번째 요소의 값을 변경해보기
        // 깊은 복사 : 단순히 배열을 복사함. 주소값이 다름. 원본배열이 변경되어도 사본배열은 변경되지 않음.
        chArr[1] = 'B';
        System.out.println("변경 후 원본 배열 : " + Arrays.toString(chArr));
        System.out.println("변경 후 사본 배열 : " + Arrays.toString(copyChArr));

        System.out.println(chArr == copyChArr); // 배열객체의 주소값이 같은지 비교 false
        System.out.println("---------------------------------------------------------------------------------");

        // 원본 배열을 복사 (shallow-copy)
        // 얕은 복사 : 배열의 주소값까지 복사. 사본배열이나 원본배열이 변경되면 같이 바뀜.
        String[] heros = new String[]{"아이언맨", "헐크", "캡틴아메리카노"};
        String[] copyHeros = heros;
        System.out.println("변경 전 원본배열 heros : " + Arrays.toString(heros) + ", 변경 전 사본배열 heros : " + Arrays.toString(copyHeros));
        heros[2] = "캡틴아메리카";
        System.out.println("변경 후 원본배열 heros : " + Arrays.toString(heros) + ", 변경 후 사본배열 heros : " + Arrays.toString(copyHeros));
        copyHeros[2] = "토르";
        System.out.println("변경2 후 원본배열 heros : " + Arrays.toString(heros) + ", 변경2 후 사본배열 heros : " + Arrays.toString(copyHeros));

        //---------------------------------------------------------------------------------
        System.out.println("-------------------------------------String------------------------------------");
        String str1 = "대한민국"; // 문자열 리터럴
        String str2 = "대한민국";

        System.out.println(str1.hashCode());
        System.out.println(str2.hashCode());
        System.out.println(str1);
        System.out.println(str2);
        System.out.println(System.identityHashCode(str1));
        System.out.println(System.identityHashCode(str2)); // 해시코드가 같음. 값이 같다면 메모리 절약을 위해서 같은 주소를 참조함.

//        String test = () -> { return (str1 == str2) ? "같은 주소다." : "다른 주소다."; };
//        System.out.println(test);

        if(str1.equals(str2)){
            System.out.println("같은 문자다.");
        } else {
            System.out.println("다른 문자다.");
        }

        String str3 = new String("대한민국");
        String str4 = new String("대한민국");
        System.out.println(System.identityHashCode(str3));
        System.out.println(System.identityHashCode(str4)); // 해시코드가 다름.

        if(str3==str4){ // == : 참조 타입에서 주소값 비교하는것.
            System.out.println("같은 주소다.");
        } else {
            System.out.println("다른 주소다.");
        }

        if(str3.equals(str4)){
            System.out.println("같은 문자다.");
        } else {
            System.out.println("다른 문자다.");
        }

    }
}
