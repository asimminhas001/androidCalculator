package kamal.calculator;

/**
 * Program: expressionObject
 * Project: Android Calculator
 * Author: kamalhamoud
 * Date: 2016-01-15
 */
public class HistoryObject {
    public Long Id;
    public String expressionString;
    public String resultString;

    /**
     * constructs a historyObject
     * @param anExpressionString
     * @param aResultString
     */
    public HistoryObject(Long anId,String anExpressionString, String aResultString){
        Id = anId;
        expressionString = anExpressionString;
        resultString = aResultString;
    }

    public HistoryObject(String expressionString, String resultString) {
        this.expressionString = expressionString;
        this.resultString = resultString;
    }

    public HistoryObject() {
    }


}
