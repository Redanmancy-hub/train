package cqgcxy.javaweb.util;

public class NewsUtil {
    private String str;

    public void setStr(String str){
        this.str = str;
    }
    public String getStr(){
        return replace(str);
    }

    public String replace(String str) {
//        String newStr1 = "";
        String newStr2 = "";
//        newStr1 = str.replaceAll(" ", "&nbsp");
        newStr2 = str.replaceAll("\r\n", "<br>");
        return newStr2;
    }
}
