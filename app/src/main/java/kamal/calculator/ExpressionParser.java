package kamal.calculator;

import android.support.design.widget.Snackbar;
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
    // TODO: Operator precedence still not correct when '-' is in the equation
    // TODO: have btns off when not useable (i.e. after operator no operator)
    // TODO: voice input??????

interface OperatorMethods {
    double method(double op1, double op2);
}
    
public class ExpressionParser {

    //private static final String LOG_TAG = MainActivity.class.getSimpleName(); //error logging
    protected static View parentView = MainActivity.parentView;


    private static HashMap<String, OperatorMethods> operatorMap = new HashMap<>();
    private static boolean operatorMapLoaded = false;
    private static String bracketExpression;



    /**
     * resultOutput method
     *  - displays the number in the Result TextView
     *  @param num number to be outputted to user
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
     *  @param expressionString inputted expressionString
     *  a HistoryObject with result and expression
     */
    protected static HistoryObject computeResult(String expressionString) {
        String[] expressionStringArray = expressionString.split(" ");
        double result;
        String resultStr, element;
        Stack<String> operandStack = new Stack<>();
        Stack<String> operatorStack = new Stack<>();

        if (Objects.equals(expressionString, "")) {
            return new HistoryObject("0", "0");
        }

        for (int i = 0; i < expressionStringArray.length; i++) {

            element = expressionStringArray[i];
            if (isOperand(element)) {
                operandStack.push(element);
                continue;
            }
            if (isOperator(element)) {
                operatorStack.push(element);
            } else {
                if (isBracket(element)) {
                    i = extractBracketExpression(expressionStringArray, i);
                    operandStack.push(computeResult(bracketExpression).resultString);
                }
            }
        }

        result = computeOperatorStack(operandStack, operatorStack);
        resultStr = Double.toString(result);
        return new HistoryObject(expressionString, resultStr);
    }


    /**
     *  Extracts the expression inside the current set of brackets
     *      the expression is being parsed at
     * @param expressionStringArray the array containing the input
     * @param marker (expressionStringArrays location marker)
     * @return location of next element to be processed after )
     */
    private static int extractBracketExpression(String expressionStringArray[], int marker){
        int i = marker;
        String element = expressionStringArray[i];
        bracketExpression = "";

        if (!element.matches("\\)")) {
            element = expressionStringArray[++i];

            while (!isBracket(element) && (isOperand(element) ||
                    isOperator(element) || element.matches(""))) {

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
     * @param operandStack stack containing operator strings
     * @param operatorStack stack containing operand strings
     * @return the result of the computation of the two stacks
     */
    private static double computeOperatorStack(Stack operandStack, Stack operatorStack) {
        double operand1, operand2;
        String operator;

        // replace operand loading (i.e. Double.parseDouble(operandStack.pop().toString());)
        //  with method that checks for errors and returns a double
        while (!operatorStack.empty())
        {

            if (operatorStack.size() > 1) {
                operator = operatorStack.pop().toString();
                if (operatorPrecedence(operator) < operatorPrecedence(operatorStack.peek().toString())) {
                    String higherPrecedenceOperator = operatorStack.pop().toString();
                    String currentOperand = operandStack.pop().toString();
                    operatorStack.push(operator);
                    operator = higherPrecedenceOperator;
                    operand2 = loadOperand(operandStack);
                    operand1 = loadOperand(operandStack);

                    operandStack.push(String.format("%.4f", computeEquation(operand1, operator, operand2)));
                    operandStack.push(currentOperand);
                    continue;
                } else {
                    operand2 = loadOperand(operandStack);
                    operand1 = loadOperand(operandStack);
                }
            } else {
                operator = operatorStack.pop().toString();
                operand2 = loadOperand(operandStack);
                operand1 = loadOperand(operandStack);
            }

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
     * @param operandStack stack containing operands (0-9)
     * @return the top item in the stack
     */
    private static double loadOperand(Stack operandStack) {
        if (operandStack.empty()) {
            return 0;
        }
        return Double.parseDouble(operandStack.pop().toString());
    }

    /**
     * Matches the operator with the correct method to call to acquire a result
     * @param operand1 first operand in equation
     * @param operator the operator
     * @param operand2 second operand in equation
     * @return double - result of computation
     */
    private static double computeEquation(double operand1, String operator, double operand2) {

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
     * @param expressionStringFragment element from expression string array
     * @return true = if
     */
    protected static boolean isOperand(String expressionStringFragment) {
        return expressionStringFragment.matches("[\\-]*[0-9]*[.]*[0-9]+");
    }


    /**
     * Determines if string fragment is an operator or not
     * "[\\+\\-\\*%/x]+"
     * @param expressionStringFragment element from expression string array
     * @return if element is one of the operators
     */
    protected static boolean isOperator(String expressionStringFragment) {
        return expressionStringFragment.matches("[log\\^\\+\\-%/x]+");
    }

    /**
     * Determines if string fragment is a bracket or not
     * "[\\(\\)]"
     * @param expressionStringFragment element from expression string array
     * @return if element is a bracket character
     */
    protected static boolean isBracket(String expressionStringFragment) {
        return expressionStringFragment.matches("[\\(\\)]");
    }


    /**
     * Loads the operator hash map with the operator key and its equivalent method
     */
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

    /**
     *  checks the operator precedence
     * @param anOperator string of operator
     * @return number of operator precedence
     */
    private static double operatorPrecedence(String anOperator) {
        if (anOperator.matches("[\\^%/x]+")) {
            return 2;
        } else return 1;
    }


}