package rit.stu;

import rit.cs.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class DYI {
    public static void main(String[] args) {
        /**
         * First, a welcome message is displayed (one time only). The user is then prompted with ">" and the
         * program waits for the user to enter the expression. Once entered, the program builds the parse tree.
         * It then emits the inorder string of the expression and displays the evaluation of it.This process is
         * repeated until the user enters "quit" - this causes the program to display a closing message and then
         * the program gracefully terminates
         */
        Scanner input = new Scanner(System.in);
        System.out.println("Derp your interpreter v1.0 :) ");
        String expression;
        do {
            System.out.print("> ");
            expression = input.nextLine();
            List<String> tokens = new ArrayList<String>();
            StringTokenizer expressions_list = new StringTokenizer(expression, " ");
            while (expressions_list.hasMoreTokens())
                tokens.add(expressions_list.nextToken());
            DYI operations = new DYI();
            Expression parsed_operation = operations.parse((ArrayList) tokens);
            System.out.println("Emit: " + parsed_operation.emit());
            System.out.println("Evaluate: " + parsed_operation.evaluate());
        } while (!expression.equalsIgnoreCase("quit"));
    }
    public Expression parse(ArrayList tokens){
        /**
         * remove the first token from the list
         * if the first token is an operator (+, -, *, etc.)
         * Recursively call parse with the remaining tokens to make the left expression
         * Recursively call parse with the remaining tokens to make the right expression
         * Return the new expression with left and right.
         * If it's not an operator, then it's an integer
         * Return an integer expression with the correct value.
         *
         * @tokens an arrayList that stores a list of numbers and operators
         *
         * @return an Expression that depends on the operation that is a part of the expression
         */
        String s1 = (String) tokens.remove(0);
        switch (s1) {
            case "+": {
                Expression left = parse(tokens);
                Expression right = parse(tokens);
                return new AddExpression(left, right);
            }
            case "-": {
                Expression left = parse(tokens);
                Expression right = parse(tokens);
                return new SubExpression(left, right);
            }
            case "*": {
                Expression left = parse(tokens);
                Expression right = parse(tokens);
                return new MulExpression(left, right);
            }
            case "/": {
                Expression left = parse(tokens);
                Expression right = parse(tokens);
                return new DivExpression(left, right);
            }
            case "%": {
                Expression left = parse(tokens);
                Expression right = parse(tokens);
                return new ModExpression(left, right);
            }
            case "quit":
                System.out.println("Goodbye");
                System.exit(0);
        }
        int expression = Integer.parseInt(s1);
        return new IntExpression(expression);
    }
}
