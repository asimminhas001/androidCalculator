package kamal.calculator;

import android.util.Log;
import java.util.HashMap;
import java.util.Objects;
import java.util.Stack;

/**
 * Program: ExpressionParser
 * Project: Calculator
 * Author: kamal hamoud
 * Date: 2016-01-07
 */

/**
 * TODO: DONE List
 */
    // TODO: Add ANS button that allows use of previous answer in current equation - DONE
    // TODO: make history click output expression and result into outputs - DONE
    // TODO: Use previous answer if operator is implemented. - DONE
    // TODO: Add log and ^ functions - DONE
    // TODO: Move equals, dot, 0 and mod button to make it more user friendly -DONE
    // TODO: Add clear history button/function - DONE

/**
 * TODO List
 */
    // TODO: 2 % 0 = NAN ???
    // TODO: ANS btn feedback
    // TODO: have btns off when not useable (i.e. after operator no operator)
    // TODO: reduce divider shadow (elevation)
    // TODO: implement global hashmap through interface implementation

    
public class ExpressionParser {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private static HashMap<String, Double> operatorMap = new HashMap();
    private static boolean operatorMapLoaded = false;


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
            if (isOperand(element)) {
                operandStack.push(element);
                continue;
            }
            if (element.matches("")) {
                continue;
            } else {
                if (isBracket(element)) {
                    String bracketExpression = "";
                    if (!element.matches("\\)")) {
                        element = expressionStringArray[++i];

                        while (!isBracket(element) && (isOperand(element) || isOperator(element) || element.matches(""))) {
                            if (element.matches("")) {
                                element = expressionStringArray[++i];
                                continue;
                            }
                            if (isOperator(element)) {
                                bracketExpression += " " + element + " ";
                            } else {
                                bracketExpression += element;
                            }
                            if (i + 1 > expressionStringArray.length - 1) break;
                            element = expressionStringArray[++i];
                        }
                    }
                    operandStack.push(computeResult(bracketExpression).resultString);
                }
                else if (isOperator(element)) {
                    operatorStack.push(element);
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
            if (operator.equals("log")) {
                operandStack.push(operand1);
                operandStack.push(String.format("%.4f", computeEquation(operand1, operator, operand2)));
                continue;
            }
            operandStack.push(String.format("%.4f", computeEquation(operand1, operator, operand2)));
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

//    private static void loadOperatorMap() {
//
//
//    } // end loadOperatorMap()


    /**
     * Matches the operator with the correct method to call to acquire a result
     * @param operand1
     * @param operator
     * @param operand2
     * @return double - result of computation
     */
    private static double computeEquation(double operand1, String operator, double operand2) {
        double result = 0;

        if (!operatorMapLoaded) {
            operatorMap.put("+", add(operand1, operand2));
            operatorMap.put("-", sub(operand1, operand2));
            operatorMap.put("x", mul(operand1, operand2));
            operatorMap.put("/", div(operand1, operand2));
            operatorMap.put("%", mod(operand1, operand2));
            operatorMap.put("log", log(operand2));
            operatorMap.put("^", exponent(operand1, operand2));

            operatorMapLoaded = true;
        }

        result = operatorMap.get(operator);

//        Log.d(LOG_TAG, "result is: " + result + "in double computeEquation" );

        return result;
    }

    /**
     * Determines if string fragment is an operator or not
     * "[0-9]*[.]*"
     * @param expressionStringFragment
     * @return boolean
     */
    protected static boolean isOperand(String expressionStringFragment) {
        return expressionStringFragment.matches("[0-9]*[.]*[0-9]+");
    }


    /**
     * Determines if string fragment is an operator or not
     * "[\\+\\-\\*%/x]+"
     * @param expressionStringFragment
     * @return boolean
     */
    protected static boolean isOperator(String expressionStringFragment) {
        return expressionStringFragment.matches("[log\\^\\+\\-\\%/x]+");
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
        if (B == 0) {
            return 0;
        }
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

    /**
     *
     * @param A (Base)
     * @param B (Exponent)
     * @return A to the power of B
     */
    private static double exponent(double A, double B) {
        return (Math.pow(A, B));
    }

    /**
     *
     * @param A operand
     * @return returns log(A)
     */
    private static double log(double A) {
        return Math.log(A);
    }


}