package com.ZohoIncubation.Task2;

public class CustomString {

    // Storing string as an array of characters
    private byte[] values;

    // Instance variable 'string'
    private String string;

    private int len = 0;

    // Constructor
    public CustomString(String str) {

        // Initializing instance variable 'string'
        this.string = str;

        // Converting the string to byte array instance
        // this.values = this.string.getBytes(StandardCharsets.UTF_8);
        this.values = StringUtil.getByteArray(this.string);

        //Initializing instance variable 'len' with the help of length()
        len = length();
    }

    // 1 -----------charAt()-------------
    public char charAt(int index) throws Exception {
        String temp = this.string;
        try {
            if (index < 0 || index >  StringUtil.getLength(temp) - 1) {
                //Could throw IndexOutOfBoundsException
                throw new StringIndexOutOfBoundsException(index);
            } else {
                return (char) this.values[index];
            }
        } catch (Exception ex) {
            System.out.print("Exception :" + ex.getMessage());
        }
        return ' ';
    }

    // 2 ----------length()--------------
    public int length() {
        return StringUtil.getLength(values);
    }

    // 3 ---------- toCharArray()--------
    public char[] toCharArray() {
        char[] charArray = new char[length()];
        for (int i = 0; i < this.len; i++) {
            charArray[i] = (char) this.values[i];
        }
        return charArray;
    }

    // 4 ----------concat()------------
    public String concat(String string2) {
        return this.string + "" + string2;
    }

    // 5 -----------contains()-----------
    public boolean contains(String str) {
        return StringUtil.isContain(str, false, this.values, 0);
    }

    // 6 ----------startsWith()---------
    public boolean startsWith(String str) {

        if(StringUtil.getLength(str)>len)
            return false;
        return StringUtil.isContain(str, this.values, 0);

    }

    // 7 ----------endsWith()----------
    public boolean endsWith(String str) {

        int strLen = StringUtil.getLength(str);
        if(StringUtil.getLength(str)>len)
            return false;

        int startIndex = len - strLen;
        return StringUtil.isContain(str, this.values, startIndex);
    }

    // 8 -----------equals()------------
    public boolean equals(String str) {
       return StringUtil.equality(this.string,str);
    }

    public boolean equalsIgnoreCase(String str){
        byte[] temp = StringUtil.getByteArray(str);
       return StringUtil.equality(StringUtil.changeCase(temp,true), StringUtil.changeCase(this.values,true));
    }

    //  -----------toLowerCase()------------
    public String toLowerCase(){
       return StringUtil.changeCase(this.values,true);
    }

    //  -----------toUpperCase()------------
    public String toUpperCase(){
        return StringUtil.changeCase(this.values,false);
    }

    // ------------- isEmpty()---------------
    public boolean isEmpty(){
        if(len==0)
            return true;
        return false;
    }

    // ----------trim()-------------------
    public String trim(){
        String str = "";
        boolean isStringStarted = false;
        for (int i = 0; i < len; i++) {
            if(this.values[i]==' '){
                if(isStringStarted)
                    break;
                continue;
            }
            str+=(char)this.values[i];
            isStringStarted = true;
        }
        int start = 0;
        int end = len-1;
        // Another Logic
        // while (this.string.charAt(start)==' '){
        //     ++start;
        // }
        // while (this.string.charAt(end)==' '){
        //     --end;
        // }
        return str;
    }
    // --------------copyValueOf----------
    public String copyValueOf(char[] data, int index, int  count) throws Exception{
        try{
            if(index<0 || index>= StringUtil.getLength(data) ){
                throw new StringIndexOutOfBoundsException(index);
            }
            if(index+count> StringUtil.getLength(data)){
                throw new StringIndexOutOfBoundsException(index+count);
            }
            String str="";
            for (int i = index; i < index+count; i++)
                str+=data[i];
            return str;
        }catch (Exception e){
            System.out.print(e.getMessage());
        }
        return " ";
    }

    //---------------indexOf()------------
    public int indexOf(String str){
        return  indexOf(str,0);
    }

    public int indexOf(String str, int searchIndex){
        return StringUtil.findIndexes(str,false, this.values, searchIndex,false);
    }

    //----------------lastIndexOf()-----------
    public int lastIndexOf(String str){
        return lastIndexOf(str,0);
    }
    public int lastIndexOf(String str,int searchIndex){
        return StringUtil.findIndexes(str,false, this.values, searchIndex,true);
    }

    //---------------subString()-------------
    public String subString(int startIndex){
        if(startIndex<0)
            startIndex = 0;
        return subString(startIndex,len);
    }

    public String subString(int startIndex, int lastIndex){
        if(startIndex>lastIndex){
            int temp = startIndex;
            startIndex = lastIndex;
            lastIndex = temp;
        }

        String str="";
        for (int i = startIndex; i < lastIndex ; i++) {
            str+=(char)this.values[i];
        }
        return str;
    }

    //-------------replace()---------------
    public String replace(char searchChar, char newChar){

        String str="";

        for (int i = 0; i < len; i++) {
            if(this.values[i] == searchChar){
                str+=(char)newChar;
                continue;
            }
            str+=(char)this.values[i];
        }

        return str;

    }

    public String replace(String searchChar, String newChar){
        String str="";
        System.out.println(searchChar + " " + newChar);
        return str;
    }

    // -----------------toString()------------------
    public String toString(){
        return this.string;
    }

    //---------------valueOf()------------------
    public String valueOf(Object obj){
        return obj+"";
    }

    //--------------- compareTo()---------------
    public int compareTo(String str){
        byte[] temp = StringUtil.getByteArray(str);
        int strLen = StringUtil.getLength(temp);
        int j=0;
        for (int i = 0; i < len && j < strLen; i++, j++) {
            if(this.values[i]==temp[j])
                continue;
            return this.values[i]-temp[i];
        }
        if(len!=strLen)
            return len-strLen;
        return 0;
    }

}