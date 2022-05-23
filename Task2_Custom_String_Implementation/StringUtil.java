package com.ZohoIncubation.Task2;

import java.nio.charset.StandardCharsets;

public class StringUtil {

    public static byte[] getByteArray(String str){
        return str.getBytes(StandardCharsets.UTF_8);
    }
    public static int getLength(String str){
        return getLength(str.getBytes(StandardCharsets.UTF_8));
    }

    public static boolean isContain(String str, boolean isSequenceStarted, byte[] val, int start){
        byte[] tempArr = getByteArray(str);
        int len = getLength(val);
        int j=0;
        for (int i = start; i < len; i++) {

            if((char)tempArr[j]==(char)val[i]){
                isSequenceStarted = true;
            }else if(isSequenceStarted){
                j=0;
                isSequenceStarted=false;
                continue;
            }
            if(isSequenceStarted){
                if(j==tempArr.length-1)
                {
                    return true;
                }
                j++;
            }
        }
        return false;
    }

    public static boolean isContain(String str, byte[] val, int start){
        byte[] tempArr = getByteArray(str);
        int len = getLength(val);
        int tempLen = getLength(tempArr);
        int j=0;
        for (int i = start; i < len && j<tempLen; i++) {
            if((char)val[i]==(char)tempArr[j]){
                j++;
                continue;
            }
            return false;
        }
        return true;
    }

    public static String changeCase(byte[] val, boolean islower){
        String str="";
        for(byte temp: val){
            if(islower){
                if(temp>=65 && temp<=90){
                    str += (char)(temp+32);
                    continue;
                }
            }else{
                    if(temp>=97 && temp<=122){
                        str+= (char)(temp-32);
                        continue;
                    }
            }
            str+=(char)temp;
        }
        return str;
    }

    public static boolean equality(String objString, String string){
        if (objString.length() == StringUtil.getLength(string))
            return StringUtil.isContain(objString, false, getByteArray(string), 0);

        return false;
    }
    public static String copyValues(char[] data, int index, int  count){
        String str="";
        for (int i = index; i < index+count; i++)
            str+=data[i];
        return str;
    }

    public static int findIndexes(String str, boolean isSequenceStarted, byte[] val, int start, boolean shouldReverse){
        if (shouldReverse){
            reverse(val);
        }
        int point=-1;
        byte[] tempArr = getByteArray(str);
        int j=0;
        for (int i = start; i < val.length; i++) {
            if((char)tempArr[j]==(char)val[i]){
                isSequenceStarted = true;
            }else if(isSequenceStarted){
                isSequenceStarted = false;
                j=0;
            }
            if(isSequenceStarted){
                if(j==tempArr.length-1)
                {
                        point = i;
                        break;
                }
                j++;
            }
        }
        int result = point-tempArr.length+1;
        return  result>= 0 ? result: point;
    }

    public static int getLength(byte[] val){
        int count=0, temp=0;

        try{
            for (int i = 0; ; i++) {
                temp = val[i];
                count++;
            }
        }catch (Exception e){
            return count;
        }
    }
    public static int getLength(char[] val){
        int count=0, temp=0;

        try{
            for (int i = 0; ; i++) {
                temp = val[i];
                count++;
            }
        }catch (Exception e){
            return count;
        }
    }

    public static void reverse(byte[] value){
        int len = getLength(value);
        int j = len-1;
        byte temp;
        for (int i = 0; i <= j; i++,j--) {
            temp = value[i];
            value[i] = value[j];
            value[j] = temp;
        }
    }

}
