package shk;

class Document {
    static int num = 0;
    String title;
    String[] type = {"txt", "java", "js", "html", "css", "jsp"};

    Document() { // 사용자가 문서이름을 지정하지 않으면 제목없음1.txt 로 파일이름을 설정
        this.title = "제목없음" + ++num + "." + type[(int)(Math.random() * type.length)];;
        System.out.println("파일이름 : "+this.title);
    }

    Document(String title){
        this.title = title + "." + type[(int)(Math.random() * type.length)];
        System.out.println("파일이름 : "+this.title);
    }
}
