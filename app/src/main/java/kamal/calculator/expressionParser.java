package kamal.calculator;

import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

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
    // TODO: Add ANS button that allows use of previous answer in current equation
    // TODO: make history click output expression and result into outputs
    // TODO: Use previous answer if operator is implemented
    // TODO: Add log and ^ functions
    // TODO: Move equals, dot, 0 and mod button to make it more user friendly
    // TODO: Add clear history button/function
    // TODO: 2 % 0 = NAN ???
    // TODO: implement global hashmap through interface implementation
    // TODO: ANS btn feedback
    // TODO: Remove infinity from history and inability to recall it
    // TODO: reduce divider shadow (elevation)

/**
 * TODO List
 */
    // TODO: have btns off when not useable (i.e. after operator no operator)
    // TODO: voice input??????

interface OperatorMethods {
    double method(double op1, double op2);
}
    
public class ExpressionParser {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    protected static View parentView = MainActivity.parentView;


    private static HashMap<String, OperatorMethods> operatorMap = new HashMap();
    private static boolean operatorMapLoaded = false;
    private static String bracketExpression;



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
        String resultStr, element;
        Stack operandStack = new Stack();
        Stack operatorStack = new Stack();

        if (Objects.equals(expressionString, "")) {
            HistoryObject aHistoryObject = new HistoryObject("0", "0");
            return aHistoryObject;
        }

        for (int i = 0; i < expressionStringArray.length; i++) {

            element = expressionStringArray[i];
            if (isOperand(element)) {
                operandStack.push(element);
                continue;
            }
            if (isOperator(element)) {
                operatorStack.push(element);
                continue;
            }
            if (element.matches("")) {
                continue;
            } else {
                if (isBracket(element)) {
                    i = extractBracketExpression(expressionStringArray, i);
                    operandStack.push(computeResult(bracketExpression).resultString);
                }
            }
        }

        result = computeOperatorStack(operandStack, operatorStack);
        resultStr = Double.toString(result);
        HistoryObject historyObject = new HistoryObject(expressionString, resultStr);
        return historyObject;
    }


    /**
     *  Extracts the expression inside the current set of brackets
     *      the expression is being parsed at
     * @param expressionStringArray
     * @param marker (expressionStringArrays location marker)
     * @return
     */
    private static int extractBracketExpression(String expressionStringArray[], int marker){
        int i = marker;
        String element = expressionStringArray[i];
        bracketExpression = "";

        if (!element.matches("\\)")) {
            element = expressionStringArray[++i];

            while (!isBracket(element) && (isOperand(element) || isOperator(element) || element.matches(""))) {
                if (element.matches("")) {
                    element = expressionStringArray[++i];
                    continue;
                }
                if (isOperand(element)) {
                    bracketExpression += element;
                } else {
                    bracketExpression += " " + element + " ";
                }
                if (i + 1 > expressionStringArray.length - 1) break;
                element = expressionStringArray[++i];
            }
        }
        return i;
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
        if (operandStack.empty()) {
            Snackbar.make(parentView, "Invalid Expression",
                    Snackbar.LENGTH_SHORT).show();
            return 0;
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
    private static double computeEquation(final double operand1, String operator, final double operand2) {

        if (!operatorMapLoaded || operatorMap.isEmpty()) {
            loadOperatorMap();
        }
        return operatorMap.get(operator).method(operand1, operand2);

        // log result into android monitor
//        Log.d(LOG_TAG, "result is: " + result + "in double computeEquation" );
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


    private static void loadOperatorMap() {
        operatorMap.put("+", new OperatorMethods() {
            @Override
            public double method(double op1, double op2) {
                return op1 + op2;
            }
        });
        operatorMap.put("-", new OperatorMethods() {
            @Override
            public double method(double op1, double op2) {
                return op1 - op2;
            }
        });
        operatorMap.put("x", new OperatorMethods() {
            @Override
            public double method(double op1, double op2) {
                return op1 * op2;
            }
        });
        operatorMap.put("/", new OperatorMethods() {
            @Override
            public double method(double op1, double op2) {
                return op1 / op2;
            }
        });
        operatorMap.put("%", new OperatorMethods() {
            @Override
            public double method(double op1, double op2) {
                return op1 % op2;
            }
        });
        operatorMap.put("log", new OperatorMethods() {
            @Override
            public double method(double op1, double op2) {
                return Math.log(op2);
            }
        });
        operatorMap.put("^", new OperatorMethods() {
            @Override
            public double method(double x, double y) {
                return Math.pow(x, y);
            }
        });

        operatorMapLoaded = true;

    } // end loadOperatorMap()


}