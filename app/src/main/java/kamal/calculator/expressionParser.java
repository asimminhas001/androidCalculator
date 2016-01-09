package kamal.calculator;

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
     *  @returns Double result
     */
    protected static String computeResult(String expressionString) {
        String[] expressionStringArray = expressionString.split(" ");
        Double result;
        String resultStr = "";


        return resultStr;
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
     * @return boolean
     */
    protected static boolean isSpace(String expressionStringFragment) {
        return expressionStringFragment.matches("[\\s]+");
    }
}