package com.ZohoIncubation.Task5;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Transaction initTransaction = new Transaction();
        Transaction currentTransaction = initTransaction;

        Transaction.transactionStack.push(initTransaction);
        while (true){
            System.out.println("Enter the command");
            String cmd = input.nextLine();
            String[] cmdSplit = cmd.split(" ");
            int splitLen = cmdSplit.length;
            String action = cmdSplit[0].toUpperCase();

            if(action.equals("SET") || action.equals("UPDATE")){
                if(splitLen!=3){
                    Main.showProperSyntax();
                    continue;
                }
            }else if(action.equals("GET") || action.equals("COUNT") || action.equals("UNSET")){
                if(splitLen!=2){
                    Main.showProperSyntax();
                    continue;
                }
            }else if(action.equals("BEGIN") || action.equals("ROLLBACK") || action.equals("COMMIT")){
                if(splitLen!=1){
                    Main.showProperSyntax();
                    continue;
                }
            }
            switch (action){
                case "SET":
                    boolean isSet = currentTransaction.set(cmdSplit[1],  cmdSplit[splitLen-1]);
                    break;
                case "GET":
                    String resultVal = currentTransaction.get(cmdSplit[splitLen-1], Transaction.transactionStack);
                    System.out.println(resultVal);
                    break;
                case "UNSET":
                     boolean isUnset = currentTransaction.unset(cmdSplit[splitLen-1]);
                    if(!isUnset)
                        System.out.println("no variable named " + cmdSplit[splitLen-1]);
                    break;
                case "UPDATE":
                    boolean isUpdated = currentTransaction.update(cmdSplit[1],  cmdSplit[splitLen-1]);
                    if (!isUpdated)
                        System.out.println("no variable named" + cmdSplit[1]);
                    break;
                case "COUNT":
                    int count = currentTransaction.count(cmdSplit[splitLen-1]);
                    System.out.println(count==0 ? "NULL" : count);
                    break;
                case "BEGIN":
                    Transaction newTransaction = new Transaction();
                    Transaction.transactionStack.push(newTransaction);
                    currentTransaction = newTransaction;
                    break;
                case "ROLLBACK":
                    if(Transaction.transactionStack.size()!=0){
                        Transaction.transactionStack.pop();
                        currentTransaction = Transaction.transactionStack.size()>=1 ? Transaction.transactionStack.peek() : initTransaction;
                    }
                    break;
                case "COMMIT":
                    if(Transaction.transactionStack.size()>=2){
                        Transaction popped = Transaction.transactionStack.pop();
                        Transaction peek = Transaction.transactionStack.peek();
                        Transaction.merge(peek,popped);
                        currentTransaction = peek;
                    }
                    break;
                default:
                    System.out.println("Please enter valid command: SET, GET, UNSET, UPDATE, BEGIN, ROLLBACK, COMMIT");
                    break;

            }
        }
    }

    public static void showProperSyntax(){
        System.out.println("Follow proper syntax");
        System.out.println("--> SET | UNSET | UPDATE (variableName) (value)");
        System.out.println("--> GET (variableName)");
        System.out.println("--> COUNT (value)");
        System.out.println("--> BEGIN | ROLLBACK | COMMIT");
    }

}
