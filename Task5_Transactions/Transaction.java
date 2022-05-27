package com.ZohoIncubation.Task5;

import com.ZohoIncubation.Task3.CustomStackLL;
import com.ZohoIncubation.Task3.Node;
import java.util.ArrayList;
import java.util.List;

public class Transaction {

    private List<Variable> storeList;
    static CustomStackLL<Transaction> transactionStack = new CustomStackLL<>();

    public Transaction() {
        this.storeList = new ArrayList<>();
    }

    public boolean set(String var, String val){
        try{
            Variable resultVar = getVariableAssist(var);
            if(resultVar != null){
                resultVar.getValue().setValue(val);
                return true;
            }
            Variable variable = new Variable(var,val);
            storeList.add(variable);
            return true;

        }catch (Exception ex){
            return false;
        }
    }

    public boolean update(String var, String val){

        Node<Transaction> temp = transactionStack.getTail();
        while(temp!=null){
            Variable tempVar = temp.getValue().getVariable(var);
            if(tempVar!=null){
                tempVar.getValue().setValue(val);
                return true;
            }
            temp = temp.getPrev();
        }
        return false;
    }


    public String get(String var, CustomStackLL transactionStack){
//        if(getVariable(var)!=null){
//            return getVariable(var).getValue().getValue();
//        }
        Node<Transaction> temp = transactionStack.getTail();
        while(temp!=null){
            Variable tempVar = temp.getValue().getVariable(var);
            if(tempVar!=null)
                return tempVar.getValue().getValue();
            temp = temp.getPrev();
        }
       return null ;
    }


    public Variable getVariableAssist(String var){
        for (Variable variable: storeList) {
            if(variable.getVariable().equals(var)){
                return variable;
            }
        }
        return null;
    }


    public Variable getVariable(String var){

        Variable temp;
        Variable variable = getVariableAssist(var);

        if(variable==null){
            temp = null;
        }else{
            temp = variable;
        }
        return temp;
    }

    public boolean unset(String var){

        Node<Transaction> temp = transactionStack.getTail();

        while(temp!=null){
            Variable tempVar = temp.getValue().getVariable(var);
            if(tempVar!=null){
              temp.getValue().storeList.remove(tempVar);
                return true;
            }
            temp = temp.getPrev();
        }

        return false;
    }

    public int count(String val){
        int count = 0;
        for (Variable variable: storeList) {
            if (variable.getValue().getValue().equals(val)) {
                ++count;
            }
        }
        return count;
    }

    public static void merge(Transaction t1, Transaction t2){
        for (Variable var: t2.storeList) {
            t1.set(var.getVariable(), var.getValue().getValue());
        }
    }

}
