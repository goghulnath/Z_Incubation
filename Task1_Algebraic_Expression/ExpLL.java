package com.ZohoIncubation.Task1;

import java.util.HashMap;
import java.util.Map;

// Creating user-defined LinkedList

public class ExpLL {

    // To keep track of head and tail of the node
    private Node head;
    private Node tail;

    // Method to display the nodes in the list
    public void display(){
        Node temp = head;
        StringBuilder sb = new StringBuilder();
        while (temp!=null){
            int coEff = temp.getCoEff();
            Map<String, Integer> varExp = temp.getVarExp();

            sb.append(coEff<0?coEff:"+"+coEff);
            for (Map.Entry<String, Integer> varEx: varExp.entrySet()) {
                int value = varEx.getValue();
                sb.append(varEx.getKey());
                sb.append(value>1?"^"+value:"");
            }
            temp = temp.getNext();
        }
        System.out.println(sb.charAt(0)=='+' ? sb.replace(0,1,""):sb);
    }

    // Method for Combining the node in the list
    // based on the variables
    public static void combineNodes(ExpLL resultList){

        Node temp1, temp2;
        temp1 = resultList.head;

        while(temp1!=null && temp1.getNext()!=null){

            temp2 = temp1;
            while (temp2.getNext()!=null){
                boolean gudToGo = true;

                if(temp1.getVarExp().size() == temp2.getNext().getVarExp().size()) {
                    for (Map.Entry<String, Integer> map : temp1.getVarExp().entrySet()) {
                        if (temp2.getNext().getVarExp().containsKey(map.getKey()) &&
                                temp2.getNext().getVarExp().get(map.getKey()) == map.getValue()) {
                            continue;
                        }
                        gudToGo = false;
                        break;
                    }
                }else
                    gudToGo = false;

                if(gudToGo){
                    temp1.setCoEff(temp1.getCoEff() + temp2.getNext().getCoEff());
                    temp2.setNext(temp2.getNext().getNext());
                }else {
                    temp2 = temp2.getNext();
                }
            }
            temp1 = temp1.getNext();
        }
    }


    // Method to perform operation
    public static ExpLL Operation(ExpLL list1, ExpLL list2, char op){

        // list for storing the result
        // after performing operation
        ExpLL resultList = new ExpLL();

        // Referring the node passed
        // from temporary nodes for iteration
        Node list1R = list1.head;
        Node list2R = list2.head;

        while (list1R!=null){
            while (list2R != null){

                // Creating map for storing
                // key-value pair of variables and its exponent
                Map<String, Integer> newMap = new HashMap<>();

                int coEffs=1;
                // Creating node
                Node node = new Node();

                // flag to perform operation of manipulating
                // the values of two nodes and adding into the result list
                // or simply insert them
                boolean gudToGo = true;

                // when the operation is multiplication
                if(op=='*'){
                    coEffs =  list1R.getCoEff() * list2R.getCoEff();
                    node.setCoEff(coEffs);
                    for(Map.Entry<String, Integer> m: list1R.getVarExp().entrySet())
                        newMap.put(m.getKey(), m.getValue());

                    for(Map.Entry<String, Integer> map: list2R.getVarExp().entrySet()) {
                        newMap.put(map.getKey(), newMap.getOrDefault(map.getKey(), 0)+map.getValue());
                    }
                    node.setVarExp(newMap);
                }
                // when the operation is Addition or subtraction
                else{
                    if(list1R.getVarExp().size() == list2R.getVarExp().size()) {
                        // To check if map in both the nodes are having the same values
                        for (Map.Entry<String, Integer> map : list1R.getVarExp().entrySet()) {
                            if (list2R.getVarExp().containsKey(map.getKey()) &&
                                    list2R.getVarExp().get(map.getKey()) == map.getValue()) {
                                continue;
                            }
                            gudToGo = false;
                            break;
                        }

                        // performing arithmetic operation on co-efficients
                        // if flag is true
                        if(gudToGo){
                            switch (op){
                                case '+':
                                    coEffs =  list1R.getCoEff() + list2R.getCoEff();
                                    break;
                                case '-':
                                    coEffs =  list1R.getCoEff() + (list2R.getCoEff()*-1);
                            }

                            // putting the result coefficient into the newly created node
                            node.setCoEff(coEffs);

                            // Iterating the list1 node to get those in the new map
                            for(Map.Entry<String, Integer> map: list1R.getVarExp().entrySet()) {
                                newMap.put(map.getKey(),map.getValue());
                            }

                            // putting map values ino the node
                            node.setVarExp(newMap);
                        }
                        // If flag is false
                        // simply add those nodes into the result list
                        else{
                            int signVal = op=='+' ? 1 : -1;
                            resultList.insertNode(new Node(list1R.getCoEff(), list1R.getVarExp()));
                            resultList.insertNode(new Node(list2R.getCoEff()*signVal, list2R.getVarExp() ));
                            list2R = list2R.getNext();
                            break;
                        }
                    }
                    // simply add those nodes into the result list
                    else{
                        int signVal = op=='+' ? 1 : -1;
                        resultList.insertNode(new Node(list1R.getCoEff(), list1R.getVarExp()));
                        resultList.insertNode(new Node(list2R.getCoEff()*signVal, list2R.getVarExp() ));
                        list2R = list2R.getNext();
                        break;
                    }
                }
//                if(node.varExp!=null)
                    resultList.insertNode(node);

                list2R = list2R.getNext();
                // break the inner loop when the operation is not multiplication
                if(op!='*')
                    break;
            }
            if(op=='*')
                list2R = list2.head;

            list1R = list1R.getNext();
        }
        // returning the temporary list
        return resultList;
    }


    // Method to insert node into the list
    public void insertNode(Node newNode){

        // when adding the first node
        if(head==null){
            head = tail = newNode;
            return;
        }
        // adding new nodes at the tail
        // And make the new node 'tail'
        tail.setNext(newNode);
        tail = newNode;
    }

    // Method to create new node with the values
    // And insert into the list
    public void insert(int coEff, Map<String, Integer> varExp){
        Node node = new Node(coEff, varExp);
        if(head==null){
            head=tail=node;
            return;
        }
        insertLast(node);
    }

    // Method to
    public void insertLast(Node node){
        tail.setNext(node);
        tail = node;
    }

}

// Creating node class
 class Node{
    private int coEff;
    private Map<String, Integer> varExp;
    private Node next;

    public int getCoEff(){
        return this.coEff;
    }
    public Map<String, Integer> getVarExp(){
        return this.varExp;
    }
    public Node getNext(){
        return this.next;
    }
    public void setCoEff(int coEff){
        this.coEff = coEff;
    }
    public void setVarExp(Map<String, Integer> varExp){
        this.varExp =varExp;
    }
    public void setNext(Node next){
        this.next = next;
    }

    public Node(){
        int coEff=0;
        Map<String, Integer> varExp = null;
        Node next = null;
    }
    public Node(int coEff){
        this.coEff = coEff;
    }

     public Node(int coEff, Map<String, Integer> varExp){
         this.coEff = coEff;
         this.varExp = varExp;
     }
}