package MakeAVocabularyList;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class English {


    List<String> getEnglish(String filename) throws IOException {
        List<String> result = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String str = "";
        while((str = br.readLine()) != null){
            result.add(str);
            str.replaceFirst(",", " | ");
        }
        return result;
    }

}
