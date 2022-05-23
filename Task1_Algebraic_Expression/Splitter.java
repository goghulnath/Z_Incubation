package com.ZohoIncubation.Task1;

import java.util.*;

public class Splitter {

    public static void formNodes(ExpLL list,String expr){
        char[] chars =expr.toCharArray();
        int exp=1;
        int signVal = 1;
        int coEff=1;
        String var;
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {

            char temp = chars[i];
            if(temp=='(' || temp==')')
                continue;
            if(temp>=49 && temp<=57){
                coEff=Integer.parseInt(temp+"");
//                signVal =1;
                continue;
            }

            if(temp>=97 && temp<=122){
                var = temp + "";
                if(i<chars.length-1 && chars[i+1]==94){
                    exp = Integer.parseInt(chars[i+2]+"");
                    i+=2;
                }
                map.put(var,exp);
                exp=1;
                continue;
            }

            if(temp == '+' || temp =='-'){
                list.insert(coEff*signVal,map);
                signVal=1;
                if(temp =='-')
                    signVal = -1;

                map = new HashMap<>();
                coEff=1;
                exp =1;
                continue;
            }
        }
        list.insert(signVal*coEff, map);
    }
}
