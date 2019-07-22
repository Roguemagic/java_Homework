package com.ys.mail;

public class GetPersonInfo {
    public static int getMonth(String personInfo) throws Exception{
        String[] personStr=personInfo.split(",");
        int birthmonth=0;
        if(personStr.length==4){
            String[] birthday=personStr[2].split("/");
            try {
                birthmonth=Integer.parseInt(birthday[1]);
            }catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return  birthmonth;
    }

    public static int getDay(String personInfo) throws Exception{
        String[] personStr=personInfo.split(",");
        int birthmtday=0;
        if(personStr.length==4){
            String[] birthday=personStr[2].split("/");
            try {
                birthmtday=Integer.parseInt(birthday[2]);
            }catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return birthmtday;
    }

    public static String getMailAdres(String personInfo) throws Exception{
        String[] personStr=personInfo.split(",");
        return personStr[3];
    }

    public static String getName(String personInfo) throws Exception {
        String[] personStr = personInfo.split(",");
        return personStr[1];
    }
}
