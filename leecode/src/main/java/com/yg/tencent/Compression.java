package com.yg.tencent;

/**
 * TODO
 *
 * @author Y.G
 * @date 2020/6/25 14:13
 *
 * eg: HG[3|B[2|CA]]F−>HG[3|BCACA]F−>HGBCACABCACABCACAF
 **/
public class Compression {

    public static void main(String[] args) {
        String str="BHCJSBCSCW[100|DASKDNKJWDNWCNQWCNOQCNQWOICNWQOINCWQOICNQWOIXWOISWIODAOWPQWDMQKOQZCDWF]WQJDWQUINCQQW[99|SDWQJCIQIUWCNQUCNWQIDNWQUIFNSALQP]DQOJOIXZALPPQQAAX";
        System.out.println(rec(str));
    }

    public static String rec(String str){
        if(str == null){
            return "";
        }
        if(str.contains("[")){
            StringBuilder sb = new StringBuilder();
            int start = 0;
            int point = 0;
            for(int i=0; i < str.length(); i++){
                if(str.charAt(i) == '['){
                    if(point == 0){
                        sb.append(str, start, i);
                        start = i;
                    }
                    point++;
                }
                if(str.charAt(i) == ']'){
                    point--;
                    if(point==0){
                        String sonStr = str.substring(start+1, i);
                        int firstSp = sonStr.indexOf("|");
                        int reNum = Integer.parseInt(sonStr.substring(0, firstSp));
                        String repStr = rec(sonStr.substring(firstSp+1));
                        for(int j=0; j< reNum; j++) {
                            sb.append(repStr);
                        }
                        start = i+1;
                    }
                }
            }
            if(start < str.length()) {
                sb.append(str, start, str.length());
            }
            return sb.toString();
        }else{
            return str;
        }
    }
}
