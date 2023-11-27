package shk;

/**
 * packageName :
 * fileName : ${NAME}
 * author : goott5
 * date : 2023-11-24
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-24          goott5             최초생성
 **/
public class ParseManager {
    public static Parsable getParser(String filename) {
        // 확장자가 xml인지 json인지 판단.
        // filename = abc.xml 일 경우 xml만 뽑아내기위해서는 .을 기준으로 이후를 가져오면 됨.

        String ext = filename.substring(filename.lastIndexOf(".")+1);
        Parsable result = null;

        if(ext.toLowerCase().equals("xml")){
            result = new XmlParser();
        } else if(ext.toLowerCase().equals("json")){
            result = new JsonParser();
        }

        return result;
    }


    public static void main(String[] args){
        Parsable pm = getParser("test.xml");
        pm.parse("xml");

        Parsable pm2 = getParser("test.json");
        pm2.parse("json");
    }
}