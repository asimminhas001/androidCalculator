package kamal.calculator;

import java.util.Objects;
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

        if (Objects.equals(expressionString, "")) {
            return "0";
        }

        for (int i = 0; i < expressionStringArray.length; i++) {

            // Load stacks
            //  if the next element is a bracket, push everything in the bracket in
            //      the bracket stack.
            //  if an operator or operand, they get pushed into their respective stacks.
            String element = expressionStringArray[i];
            if (element.matches("")) {
                i++;
            } else {
                if (isBracket(element)) {
                    // While we are still inside the parenthesis
                    //  push everything inside the bracket stack
                    //  then compute the result.
                    if (element.matches("\\)")) break;
                    element = expressionStringArray[++i];
                    while (!isBracket(element) && (isOperand(element) || isOperator(element))) {
                        bracketStack.push(element);
                        element = expressionStringArray[++i];
                    }
                    //while (!bracketStack.empty()) {
                    computeBracketStack(bracketStack, operandStack, operatorStack);
                    //}
                }
                else if (isOperator(element)) {
                    operatorStack.push(element);
                }
                else if (isOperand(element)) {
                    operandStack.push(element);
                }
            }

        }

        result = computeOperatorStack(operandStack, operatorStack);

        return Double.toString(result);
    }

    /**
     * Takes the stacks and computes the result
     * @param operandStack
     * @param operatorStack
     * @return
     */
    private static double computeOperatorStack(Stack operandStack, Stack operatorStack) {
        double operand1, operand2, operandOutput;
        String operator, operand;

        // replace operand loading (i.e. Double.parseDouble(operandStack.pop().toString());)
        //  with method that checks for errors and returns a double
        while (!operatorStack.empty())
        {
            operand2 = loadOperand(operandStack);
            operand1 = loadOperand(operandStack);
            operator = operatorStack.pop().toString();
            operandStack.push(computeEquation(operand1, operator, operand2));
        }

        return Double.parseDouble(operandStack.pop().toString());
    }

    /**
     * @param operandStack
     * @return
     */
    private static double loadOperand(Stack operandStack) {
        if (operandStack.empty()) {
            return 0;
        }
        return Double.parseDouble(operandStack.pop().toString());
    }

    /**
     *
     * @param bracketSt
     * @param operandSt
     * @param operatorSt
     * @return
     */
    private static double computeBracketStack(Stack bracketSt, Stack operandSt, Stack operatorSt) {
        double operand1, operand2, result;
        String operator;
        while (!bracketSt.empty()) {

            if (bracketSt.size() == 3) {
                operand2 = Double.parseDouble(bracketSt.pop().toString());
                operator = bracketSt.pop().toString();
                operand1 = Double.parseDouble(bracketSt.pop().toString());
                result = computeEquation(operand1, operator, operand2);
                operandSt.push(result);
            }
//            else if (bracketSt.size() % 3 != 0 ) {
//                operand2 = Double.parseDouble(operandSt.pop().toString()
//                operator = bracketSt.pop().toString();
//                operand1 = Double.parseDouble(bracketSt.pop().toString());
//                result = computeEquation(operand1, operator, operand2);
//                operandSt.push(result);
//            }

        }

        return 0;
    }

    /**
     *
     * @param operand1
     * @param operator
     * @param operand2
     * @return
     */
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