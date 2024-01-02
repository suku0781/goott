package countEngWordT;

import java.util.*;

/**
 * packageName : countEngWord
 * fileName : Main
 * author : goott5
 * date : 2023-12-04
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-30          goott5             최초생성
 **/
public class Main {
    /*
    * 아래의 문자열에 단어별로 몇번씩 나오는지 횟수를 출력하시오. (Map을 이용.)
    * Hash table based implementation of the Map interface. This implementation provides all of the optional map operations, and permits null values and the null key. (The HashMap class is roughly equivalent to Hashtable, except that it is unsynchronized and permits nulls.) This class makes no guarantees as to the order of the map; in particular, it does not guarantee that the order will remain constant over time.
    * This implementation provides constant-time performance for the basic operations (get and put), assuming the hash function disperses the elements properly among the buckets. Iteration over collection views requires time proportional to the "capacity" of the HashMap instance (the number of buckets) plus its size (the number of key-value mappings). Thus, it's very important not to set the initial capacity too high (or the load factor too low) if iteration performance is important.
    * */
    private static String[] array = new String[0];
    private static HashMap<String, Integer> hashMap = new HashMap();

    public static void main(String[] args){
//        String target = "Hash table based implementation of the Map interface. This implementation provides all of the optional map operations, and permits null values and the null key. (The HashMap class is roughly equivalent to Hashtable, except that it is unsynchronized and permits nulls.) This class makes no guarantees as to the order of the map; in particular, it does not guarantee that the order will remain constant over time. This implementation provides constant-time performance for the basic operations (get and put), assuming the hash function disperses the elements properly among the buckets. Iteration over collection views requires time proportional to the \"capacity\" of the HashMap instance (the number of buckets) plus its size (the number of key-value mappings). Thus, it's very important not to set the initial capacity too high (or the load factor too low) if iteration performance is important.";
        String target = "An object that maps keys to values. A map cannot contain duplicate keys; each key can map to at most one value.This interface takes the place of the Dictionary class, which was a totally abstract class rather than an interface. The Map interface provides three collection views, which allow a map's contents to be viewed as a set of keys, collection of values, or set of key-value mappings. The order of a map is defined as the order in which the iterators on the map's collection views return their elements. Some map implementations, like the TreeMap class, make specific guarantees as to their order; others, like the HashMap class, do not.";
        String strLower = target.toLowerCase();
        String str2 = strLower.replaceAll("[^0-9a-zA-Zㄱ-ㅎ가-힣]", " ");
        String[] arr = str2.split(" ");
        HashMap<String, Integer> map = new HashMap<>();

        for(int i = 0 ; i < arr.length ; i++) {
            if(!map.containsKey(arr[i])){
                map.put(arr[i],1);
            } else {
                map.put(arr[i], map.get(arr[i])+1);
            }
        }

        Iterator iter = map.entrySet().iterator();

        while(iter.hasNext()){
            Map.Entry entry = (Map.Entry) iter.next();
            int value = ((Integer)entry.getValue()).intValue();
            System.out.println(entry.getKey() + " : " + value);
        }

        TreeMap<String, Integer> temp = new TreeMap<String, Integer>(String.CASE_INSENSITIVE_ORDER);
        temp.putAll(map); // a부터 z까지 자동 정렬 된다.
        System.out.println(temp);
    }
}