package mining;

/**
 * packageName : mining
 * fileName : Main
 * author : goott5
 * date : 2023-11-24
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-24          goott5             최초생성
 **/
public class Main {
    public static String[] ore = {"diamond", "iron", "iron", "iron", "stone", "stone"};
    public static String[] tools = {"diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone"	};
    public static String[] ore2 = {"iron", "stone"};
    public static String[] tools2 = {"diamond", "diamond", "diamond", "diamond", "diamond", "iron", "iron", "iron", "iron", "iron", "diamond"};

    public static void solution(String[] ore, String[] tools) {
        int count = 0;
        int tireRate = 0;
        for(String t : tools){
            for(String o : ore){
//                if(o.equals("dia")) System.out.println("test1");
//                if(o.equals("iron")) System.out.println("test2");
//                if(o.equals("stone")) System.out.println("test3");
                if(o.equals(t)) tireRate++;


                System.out.println((count++)+ ", " +t  + ", " +  o);
                if(count > 4) {
                    count = 0;
                    break;
                }
            }
        }
    }


    public static void main(String[] args) {
//        String[] ore = {"dia", "iron", "stone"};
        // [1, 3, 2]
        solution(ore, tools);


    }
}
