package shk;

/**
 * packageName : shk
 * fileName : Xml
 * author : goott5
 * date : 2023-11-24
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-24          goott5             최초생성
 **/
public class XmlParser implements Parsable{

    @Override
    public void parse(String extension) {
        if(extension.equals("xml")){
            System.out.println("파싱가능한 xml 파일입니다. ");
        }
    }
}
