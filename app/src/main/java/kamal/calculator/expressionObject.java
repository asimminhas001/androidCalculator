package kamal.calculator;

/**
 * Program: expressionObject
 * Project: Android Calculator
 * Author: kamalhamoud
 * Date: 2016-01-15
 */
public class expressionObject {
    private String expressionString;
    private String resultString;

    /**
     *
     * @param anExpressionString
     * @param aResultString
     */
    public expressionObject(String anExpressionString, String aResultString){
        expressionString = anExpressionString;
        resultString = aResultString;
    }

    /**
     *
     * @return
     */
    public String getExpressionString() {
        return expressionString;
    }

    /**
     *
     * @param expressionString
     */
    public void setExpressionString(String expressionString) {
        this.expressionString = expressionString;
    }

    /**
     *
     * @return
     */
    public String getResultString() {
        return resultString;
    }

    /**
     *
     * @param resultString
     */
    public void setResultString(String resultString) {
        this.resultString = resultString;
    }
}
