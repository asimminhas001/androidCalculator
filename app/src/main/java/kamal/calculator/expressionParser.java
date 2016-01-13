package kamal.calculator;

import java.util.Stack;

/**
 * Program:
 * Project: Calculator
 * Author: kamal hamoud
 * Date: 2016-01-07
 */
public class ExpressionParser {

//    /**
//     * expressionOutput
//     *  - calculates
//     *  @param num
//     */
//    protected static void expressionOutput(int num, String expressionString) {
//        if (expressionString.equals("")) {
//            expressionString = expressionString.concat("" + num);
//        }
//        else {
//            expressionString = expressionString.concat(" " + num);
//        }
//
//        MainActivity.displayExpression(expressionString);
//    }

    /**
     * resultOutput method
     *  - displays the number in the Result TextView
     *  @param num
     */
    protected static void resultOutput(int num) {
        String resultString = "" + num;
        MainActivity.displayResult(resultString);
    }

    /**
     * computeResult()
     *  - parses expression string and computes the result
     *  @returns String of the result of computation
     */
    protected static String computeResult(String expressionString) {
        String[] expressionStringArray = expressionString.split(" ");
        double result;
        Stack operandStack = new Stack();
        Stack operatorStack = new Stack();
        Stack bracketStack = new Stack();

        if (expressionString == "") {
            return "0";
        }

        for (int i = 0; i < expressionStringArray.length; i++) {

            // Load stacks
            //  if the next element is a bracket, push everything in the bracket in
            //      the bracket stack.
            //  if an operator or operand, they get pushed into their respective stacks.
            String element = expressionStringArray[i];
            if (expressionString.contains("[\\(\\)]")) {
                if (isBracket(element)) {
                    // While we are still inside the parenthesis
                    //  push everything inside the bracket stack
                    //  then compute the result.
                    while (!isBracket(element)) {
                        element = expressionStringArray[++i];
                        bracketStack.push(element);

                        while (!bracketStack.empty()) {
                            computeBracketStack(bracketStack, operandStack, operatorStack);
                        }
                    }
                }
            }
            else if (isOperator(element)) {
                operatorStack.push(element);
            }
            else if (isOperand(element)) {
                operandStack.push(element);
            }
        }

        result = computeOperatorStack(operandStack, operatorStack);

        return Double.toString(result);
    }

    private static double computeOperatorStack(Stack operandStack, Stack operatorStack) {
        double result = 0, operand1, operand2;
        String operator;

        while (!operatorStack.empty())
        {
            operand2 = Double.parseDouble(operandStack.pop().toString());
            operand1 = Double.parseDouble(operandStack.pop().toString());
            operator = operatorStack.pop().toString();
            result = computeEquation(operand1, operator, operand2);
        }

        return result;
    }

    private static double computeBracketStack(Stack bracketSt, Stack operandSt, Stack operatorSt) {
        double operand1, operand2, result;
        String operator;
        while (!bracketSt.empty()) {

            if (bracketSt.size() % 3 == 0) {
                operand2 = Double.parseDouble(bracketSt.pop().toString());
                operator = bracketSt.pop().toString();
                operand1 = Double.parseDouble(bracketSt.pop().toString());
                result = computeEquation(operand1, operator, operand2);
                operandSt.push(result);
            }
            else if (bracketSt.size() % 3 != 0 ) {
                operand2 = Double.parseDouble(operandSt.pop().toString());
                operator = bracketSt.pop().toString();
                operand1 = Double.parseDouble(bracketSt.pop().toString());
                result = computeEquation(operand1, operator, operand2);
                operandSt.push(result);
            }

        }

        return 0;
    }

    private static double computeEquation(double operand1, String operator, double operand2) {
        double result = 0;

        switch (operator) {
            case "+": result = add(operand1, operand2);
                break;
            case "-": result = sub(operand1, operand2);
                break;
            case "x": result = mul(operand1, operand2);
                break;
            case "/": result = div(operand1, operand2);
                break;
            case "%": result = mod(operand1, operand2);
                break;
        }

        return result;
    }

    /**
     * Determines if string fragment is an operator or not
     * "[0-9]*[.]*"
     * @param expressionStringFragment
     * @return boolean
     */
    protected static boolean isOperand(String expressionStringFragment) {
        return expressionStringFragment.matches("[0-9]*[.]*");
    }


    /**
     * Determines if string fragment is an operator or not
     * "[\\+\\-\\*%/x]+"
     * @param expressionStringFragment
     * @return boolean
     */
    protected static boolean isOperator(String expressionStringFragment) {
        return expressionStringFragment.matches("[\\+\\-\\*%/x]+");
    }

    /**
     * Determines if string fragment is a bracket or not
     * "[\\(\\)]"
     * @param expressionStringFragment
     * @return boolean
     */
    protected static boolean isBracket(String expressionStringFragment) {
        return expressionStringFragment.matches("[\\(\\)]");
    }


    /**
     * Determines if string fragment is a space or not
     * "[\\s]+"
     * @param expressionStringFragment
     * @return
     */
    protected static boolean isSpace(String expressionStringFragment) {
        return expressionStringFragment.matches("[\\s]+");
    }

    /**
     * Adds two Doubles
     * @param A
     * @param B
     * @return
     */
    private static double add(double A, double B) {
        return A + B;
    }

    /**
     * Subtracts two Doubles
     * @param A
     * @param B
     * @return
     */
    private static double sub(double A, double B) {
        return A - B;
    }

    /**
     * multiplies two Doubles
     * @param A
     * @param B
     * @return
     */
    private static double mul(double A, double B) {
        return A * B;
    }

    /**
     * Divides two Doubles
     * @param A
     * @param B
     * @return
     */
    private static double div(double A, double B) {
        return A / B;
    }

    /**
     * Finds the remainder(mod) of A divided by B
     * @param A
     * @param B
     * @return
     */
    private static double mod(double A, double B) {
        return A % B;
    }
}