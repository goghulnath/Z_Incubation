package com.ZohoIncubation.Task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListUtil {

    public static void insertFirst(Node listHead, Node newNode){
        if(listHead.getPrev()==null){
            listHead.setPrev(newNode);
            newNode.setNext(listHead);
            return;
        }
        insertAt(listHead, newNode);
    }

    public static Node insertLast(Node listTail, Node newNode){
        if(listTail.getNext()==null) {
            listTail.setNext(newNode);
            newNode.setPrev(listTail);
            return newNode;
        }
        return null;
    }

    public static void insertAt(Node tempList, Node newNode){
            Node tempNode =  tempList.getPrev();
            tempNode.setNext(newNode);
            newNode.setPrev(tempNode);
            newNode.setNext(tempList);
            tempList.setPrev(newNode);
    }

    // using binary search
    public static Map<String, Node> insertOrder(int startIndex, int endIndex, Node startNode, Node endNode, Node newNode) {

        Map<String, Node> map = new HashMap<>();

        if((int)newNode.getValue() <= (int)startNode.getValue()){
            ListUtil.insertFirst(startNode, newNode);
            map.put("head", newNode);
            return map;
        }


        if((int)newNode.getValue() >= (int)endNode.getValue()){
            ListUtil.insertLast(endNode, newNode);
            map.put("tail", newNode);
            return map;
        }

        if(Math.abs(startIndex - endIndex) ==1){
            insertAt(endNode, newNode);
            return map;
        }

        int midIndex = (startIndex + endIndex)/2;

        Node tempNode = startNode;

        int i = startIndex;
        while (i<midIndex){
             tempNode = tempNode.getNext();
            ++i;
        }

        int tempValue = (int)tempNode.getValue();
        int newNodeValue =(int)newNode.getValue();

        if(tempValue == newNodeValue){
            insertAt(tempNode, newNode);
            return map;
        }else if(newNodeValue < tempValue){
            insertOrder(startIndex,midIndex,startNode,tempNode,newNode);
        }else{
            insertOrder(midIndex,endIndex,tempNode,endNode,newNode);
        }
        return map;
//        return new HashMap<>();
    }

}
