package com.ZohoIncubation.Task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExpLM {

    public static void main(String[] args) {

        //POLYNOMIAL EVALUATION

        // Creating Arraylist to store the expressions
        List<ExpLL> wholeExp = new ArrayList<>();

        // Getting input
        System.out.println("Enter the polynomial expression");
        Scanner input = new Scanner(System.in);

        String expr = "(a^2+b^2+2ab)+(a^2+b^2+2ab)";

        // Creating Array to store the split parts of
        // given expression after splitting
        String[] exprArray;

        // Getting the operator
        char op = expr.charAt(expr.indexOf(")")+1);

            //For multiplication operation
        if(op=='*')
            exprArray = expr.split("\\*");
            // For Addition and Subtraction
        else
        {
            StringBuilder sb = new StringBuilder(expr);
            sb.replace(expr.indexOf(")")+1, expr.indexOf(")")+2, "@");
            exprArray = sb.toString().split("\\@");
        }


        // Creating LinkedList (class ExpLL) for each expression

        for(String exp : exprArray)
        {
            ExpLL list = new ExpLL();

            // Calling 'formNodes' method in class Splitter to form nodes
            Splitter.formNodes(list,exp);

            //Adding nodes to the LinkedList
            wholeExp.add(list);
        }

        // Calling the method 'Operation' in class ExpLL
        // to perform the respective operation
        // by passing lists of each expression
        ExpLL resultList = ExpLL.Operation(wholeExp.get(0), wholeExp.get(1), op);

        // Calling the method 'combineNodes'
        // to join nodes based on the variables
        ExpLL.combineNodes(resultList);

        // Displaying final solution
        // after performing the operation
        System.out.print("Result: ");
        resultList.display();

    }
}
