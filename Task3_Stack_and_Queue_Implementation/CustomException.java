package com.ZohoIncubation.Task3;

public class CustomException extends Exception{
    public CustomException(String message){
        super(message);
    }
    // for stack using linked list
    public static Exception flowExceptionCheck(Node node){
        if(node==null)
            return new CustomException("Empty Stack Exception");
        return null;
    }

    public static Exception elementException(Node node){
        if(node==null)
            return new CustomException("No Such Element Exception");
        return null;
    }

}
