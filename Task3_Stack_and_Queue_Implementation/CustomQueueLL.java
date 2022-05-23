package com.ZohoIncubation.Task3;

//import java.util.List;

import java.util.Map;

public class CustomQueueLL<T> {
    private Node head;
    private Node tail;
    private int size;
    public boolean toSort;


    @Override
    public String toString(){
        return display();
    }
    public void showHeadAndTail(){
        System.out.println("Head :" + head.getValue() + ", Tail:" + tail.getValue());
    }
    public String display(){
        try {
            Exception exception = CustomException.flowExceptionCheck(head);
            if(exception!=null)
                throw exception;
            StringBuilder string = new StringBuilder();
            string.append("[");
            Node temp = head;
            while(temp!=null){
                string.append(temp.getValue()+",");
                temp = temp.getNext();
            }

            string.replace(string.length()-1, string.length(),"]");
            return string.toString();

        }catch (Exception ex){
            return "[]";
        }
    }

    public void sortSwitch(){
        toSort = !toSort;
    }


    public boolean add(T value){

         Node newNode = new Node(value);
        String type = value.getClass().getSimpleName();
        if(head==null){
            head = tail = newNode;
            ++size;
            return true;
        }
        if(toSort && type.equals("Integer")){
            Map<String, Node> resultMap = ListUtil.insertOrder(0,this.size-1, this.head, this.tail,newNode);
            if(resultMap.containsKey("head")){
                head = resultMap.get("head");
            }else if(resultMap.containsKey("tail")){
                tail = resultMap.get("tail");
            }
        }else{
            tail = ListUtil.insertLast(tail,newNode);
        }

        ++this.size;
        return true;

    }

    public Object remove(){
        T val;
        try{
            Exception  exception = CustomException.elementException(head);
            if(exception!=null)
                throw exception;
             val =(T)head.getValue();
            head = head.getNext();
            return val;
        }catch (Exception ex){
            System.out.print(ex.getMessage());
        }
        return "";
    }

    public Object poll(){
        T val;
        try{
            Exception  exception = CustomException.elementException(head);
            if(exception!=null)
                throw exception;
            val =(T)head.getValue();
            head = head.getNext();
            return val;
        }catch (Exception ex){
            return null;
        }

    }

    public Object peek(){
        try {
            Exception exception = CustomException.flowExceptionCheck(head);
            if (exception != null)
                throw exception;
            T temp = (T)(tail.getValue());
            return temp;
        }catch (Exception ex){
            return null;
        }
    }
    public Object element(){
        try {
            Exception exception = CustomException.flowExceptionCheck(head);
            if (exception != null)
                throw exception;
            T temp = (T)(tail.getValue());
            return temp;
        }catch (Exception ex){
            return ex.getMessage();
        }
    }

    public int size(){
        return this.size;
    }

    public boolean isEmpty(){
        return this.size == 0;
    }

    public Object search(T ob){
        try {
            Exception exception = CustomException.flowExceptionCheck(head);
            if (exception != null)
                throw exception;

            Node temp = tail;
            int count=0;
            while (temp!=null){
                ++count;
                if((T)temp.getValue()== ob){
                    return count;
                }
                temp = temp.getPrev();
            }
        }
        catch (Exception ex){
            System.out.print(ex.getMessage());
        }
        return -1;
    }
}
