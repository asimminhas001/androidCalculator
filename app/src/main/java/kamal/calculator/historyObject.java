package kamal.calculator;

/**
 * Program: expressionObject
 * Project: Android Calculator
 * Author: kamalhamoud
 * Date: 2016-01-15
 */
public class historyObject{
    private String expressionString;
    private String resultString;

    /**
     * Constructs an expressionObject
     * @param anExpressionString
     * @param aResultString
     */
    public historyObject(String anExpressionString, String aResultString){
        expressionString = anExpressionString;
        resultString = aResultString;
    }

}
