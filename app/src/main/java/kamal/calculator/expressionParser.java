package kamal.calculator;

import java.util.Objects;
import java.util.Stack;

/**
 * Program: ExpressionParser
 * Project: Calculator
 * Author: kamal hamoud
 * Date: 2016-01-07
 */
public class ExpressionParser {

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
     *  - parses expression string using split() with a " " delim
     *  - go through resulting string array seperating operators, and operands
     *  - and computes the result
     *  @returns String of the result of computation
     */
    protected static HistoryObject computeResult(String expressionString) {
        String[] expressionStringArray = expressionString.split(" ");
        double result;
        String resultStr;
        Stack operandStack = new Stack();
        Stack operatorStack = new Stack();

        if (Objects.equals(expressionString, "")) {
            HistoryObject ahistoryObject = new HistoryObject("0", "0");
            return ahistoryObject;
        }

        for (int i = 0; i < expressionStringArray.length; i++) {

            // Load stacks
            //  if the next element is a bracket, create a new string of the operands
            //      and operators inside the brackets and recursively call computeResult(bracketExpression)
            //      which gets pushed into the operandStack when answer is returned.
            //  if an operator or operand, they get pushed into their respective stacks.
            String element = expressionStringArray[i];
            if (element.matches("")) {
                continue;
            } else {
                if (isBracket(element)) {
                    String bracketExpression = "";
                    if (!element.matches("\\)")) {
                        element = expressionStringArray[++i];

                        while (!isBracket(element) && (isOperand(element) || isOperator(element))) {
                            if (isOperator(element)) {
                                bracketExpression += " " + element + " ";
                            } else {
                                bracketExpression += element;
                            }
                            element = expressionStringArray[++i];
                        }
                    }
                    operandStack.push(computeResult(bracketExpression).resultString);
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
        resultStr = Double.toString(result);
        HistoryObject historyObject = new HistoryObject(expressionString, resultStr);
        return historyObject;
    }

    /**
     * Takes the 2 stacks and computes the result
     * @param operandStack
     * @param operatorStack
     * @return
     */
    private static double computeOperatorStack(Stack operandStack, Stack operatorStack) {
        double operand1, operand2;
        String operator;

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
     * Returns the top operand in the operand stack if available
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
     * Matches the operator with the correct method to call to acquire a result
     * @param operand1
     * @param operator
     * @param operand2
     * @return double - result of computation
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