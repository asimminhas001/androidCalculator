package kamal.calculator;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

/**
 * Program: ButtonsHelper
 * Project: Calculator
 * Author: kamalhamoud
 * Date: 2016-01-07
 */
public class ButtonsHelper {

    static HistorySQLiteConnection db = HistorySQLiteConnection.getsInstance(MainActivity.contextOfApplication);
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    protected List<HistoryObject> aList = db.getHistory();



    protected static View parentView;
    protected static TextView expressionView;
    protected static String expressionString = "";

    /**
     * Flags
     */
    protected static boolean OpenBracketsFlag = false;
    protected static boolean lastBracketsFlag = false;
    protected static boolean lastOperatorFlag = false;
    protected static boolean lastOperandFlag = false;
    protected static boolean periodFlag = false;
    protected static boolean getAnswer = false;

    /**
     * @param v
     */
    public ButtonsHelper(View v) {
        this.parentView = v;
        expressionView = (TextView) v.findViewById(R.id.expression_output);
    }


    /*********************************************************
     * Operators
     */

    /**
     * btnDiv()
     *
     * @param view
     */
    public void btnDiv(View view) {
        String operator = " / ";

        if (expressionString.equals("") ||
                (lastOperatorFlag &&
                        (!lastOperandFlag && !lastBracketsFlag)))  {
            if (aList.size() > 0 && expressionString.equals("")) {
                getAnswer = true;
                reloadPreviousResult(operator);
            } else {
                Snackbar.make(parentView, "Input Number",
                        Snackbar.LENGTH_SHORT).show();
            }
        } else {
            expressionString = expressionString.concat(operator);
            setLastOperatorFlag(true);
        }
        MainActivity.displayExpression(expressionString);
    }// end btnFraction()

    /**
     * btnExponent()
     *
     * @param view
     */
    public void btnExponent(View view) {
        String operator = " ^ ";

        if (expressionString.equals("") ||
                (lastOperatorFlag &&
                        (!lastOperandFlag && !lastBracketsFlag)))  {
            if (aList.size() > 0 && expressionString.equals("")) {
                reloadPreviousResult(operator);
            } else {
                Snackbar.make(parentView, "Input Number",
                        Snackbar.LENGTH_SHORT).show();
            }
        } else {
            expressionString = expressionString.concat(operator);
            setLastOperatorFlag(true);
        }
        MainActivity.displayExpression(expressionString);
    }

    /**
     * btnRemainder()
     *
     * @param view
     */
    public void btnRemainder(View view) {
        String operator = " % ";

        if (expressionString.equals("") ||
                (lastOperatorFlag &&
                        (!lastOperandFlag && !lastBracketsFlag)))  {
            if (aList.size() > 0 && expressionString.equals("")) {
                reloadPreviousResult(operator);
            } else {
                Snackbar.make(parentView, "Input Number",
                        Snackbar.LENGTH_SHORT).show();
            }
        } else {
            expressionString = expressionString.concat(operator);
            setLastOperatorFlag(true);
        }

        MainActivity.displayExpression(expressionString);
    }// end btnDiv

    /**
     * btnMul()
     *
     * @param view
     */
    public void btnMul(View view) {
        String operator = " x ";

        if (expressionString.equals("") ||
                (lastOperatorFlag &&
                        (!lastOperandFlag && !lastBracketsFlag)))  {
            if (aList.size() > 0 && expressionString.equals("")) {
                reloadPreviousResult(operator);
            } else {
                Snackbar.make(parentView, "Input Number",
                        Snackbar.LENGTH_SHORT).show();
            }
        } else {
            expressionString = expressionString.concat(operator);
            setLastOperatorFlag(true);
        }

        MainActivity.displayExpression(expressionString);
    }// end btnMul()

    /**
     * btnSub()
     *
     * @param view
     */
    public void btnSub(View view) {
        String operator = " - ";

        if (expressionString.equals("") ||
                (lastOperatorFlag &&
                        (!lastOperandFlag && !lastBracketsFlag)))  {
            if (aList.size() > 0 && expressionString.equals("")) {
                reloadPreviousResult(operator);
            } else {
                Snackbar.make(parentView, "Input Number",
                        Snackbar.LENGTH_SHORT).show();
            }
        } else {
            expressionString = expressionString.concat(operator);
            setLastOperatorFlag(true);
        }

        MainActivity.displayExpression(expressionString);
    }// end btnSub

    /**
     * btnAdd()
     *
     * @param view
     */
    public void btnAdd(View view) {
        String operator = " + ";

        if (expressionString.equals("") ||
                (lastOperatorFlag &&
                        (!lastOperandFlag && !lastBracketsFlag)))  {
            if (expressionString.equals("") && aList.size() > 0) {
                reloadPreviousResult(operator);
            } else {
                Snackbar.make(parentView, "Input Number",
                        Snackbar.LENGTH_SHORT).show();
            }
        } else {
            expressionString = expressionString.concat(operator);
            setLastOperatorFlag(true);
        }

        MainActivity.displayExpression(expressionString);
    }// end btnAdd()

    /**
     * btnEq()
     *
     * @param view
     */
    public void btnEq(View view) {

        // Create historyObject which is returned by computeResult
        if ( expressionString.endsWith(" ") &&
                !expressionString.endsWith(" ) ") || expressionString.endsWith(".") ) {
            Snackbar.make(parentView, "End expression with a number.",
                    Snackbar.LENGTH_SHORT).show();
            return;
        }
        HistoryObject historyObject = ExpressionParser.computeResult(expressionString);

        // display resultString
        MainActivity.displayResult(historyObject.resultString);
        // reset expression string
        MainActivity.displayExpression(expressionString = "");
        // add history object to history database
        if (!historyObject.expressionString.equals("0")) {
            db.addHistory(historyObject);

            HistoryAdapter adapter = new HistoryAdapter(db.getHistory());
            MainActivity.historyView.setAdapter(adapter);
            adapter.notifyItemInserted(aList.size() - 1);
            MainActivity.historyView.scrollToPosition(0);
        }
        clearAllFlags();
    }


    /**
     * btnBrackets()
     *
     * @param view
     */
    public void btnBrackets(View view) {
        String closeBrackets = " ) ", openBrackets = " ( ";

        if (!OpenBracketsFlag && !lastBracketsFlag &&
                lastOperatorFlag || expressionString.equals("")) {

            expressionString = expressionString.concat(openBrackets);
            setOpenBracketsFlag(true);
            setLastBracketsFlag(true);

        } else if (OpenBracketsFlag && !lastOperatorFlag) {

            expressionString = expressionString.concat(closeBrackets);
            setOpenBracketsFlag(false);
            setLastBracketsFlag(true);

        } else {

            Snackbar.make(parentView, "Input Operator before Bracket\nInput Number after Bracket",
                    Snackbar.LENGTH_SHORT).show();
        }
        MainActivity.displayExpression(expressionString);
    }// end btnBrackets()

    /**
     * btnLog()
     *
     * @param view
     */
    public void btnLog(View view) {
        String operator = "log ";

        if (lastOperandFlag && !lastOperatorFlag &&
                !lastBracketsFlag) {
                Snackbar.make(parentView, "Input Operator",
                        Snackbar.LENGTH_SHORT).show();
        } else {
            expressionString = expressionString.concat(operator);
            setLastOperandFlag(true);
        }

        MainActivity.displayExpression(expressionString);
    }

    /*********************************************************
     * Operands
     */

    /**
     * btn9()
     *
     * @param view
     */
    public void btn9(View view) {
        expressionString = expressionString.concat("9");
        setLastOperandFlag(true);
        MainActivity.displayExpression(expressionString);
    }

    /**
     * btn8()
     *
     * @param view
     */
    public void btn8(View view) {
        expressionString = expressionString.concat("8");
        setLastOperandFlag(true);
        MainActivity.displayExpression(expressionString);
    }

    /**
     * btn7()
     *
     * @param view
     */
    public void btn7(View view) {
        expressionString = expressionString.concat("7");
        setLastOperandFlag(true);
        MainActivity.displayExpression(expressionString);
    }

    /**
     * btn6()
     *
     * @param view
     */
    public void btn6(View view) {
        expressionString = expressionString.concat("6");
        setLastOperandFlag(true);
        MainActivity.displayExpression(expressionString);
    }

    /**
     * btn5()
     *
     * @param view
     */
    public void btn5(View view) {
        expressionString = expressionString.concat("5");
        setLastOperandFlag(true);
        MainActivity.displayExpression(expressionString);
    }

    /**
     * btn4()
     *
     * @param view
     */
    public void btn4(View view) {
        expressionString = expressionString.concat("4");
        setLastOperandFlag(true);
        MainActivity.displayExpression(expressionString);
    }

    /**
     * btn3()
     *
     * @param view
     */
    public void btn3(View view) {
        expressionString = expressionString.concat("3");
        setLastOperandFlag(true);
        MainActivity.displayExpression(expressionString);
    }


    /**
     * btn2()
     *
     * @param view
     */
    public void btn2(View view) {
        expressionString = expressionString.concat("2");
        setLastOperandFlag(true);
        MainActivity.displayExpression(expressionString);
    }

    /**
     * btn1()
     *
     * @param view
     */
    public void btn1(View view) {
        expressionString = expressionString.concat("1");
        setLastOperandFlag(true);
        MainActivity.displayExpression(expressionString);
    }

    /**
     * btn0()
     *
     * @param view
     */
    public void btn0(View view) {
        expressionString = expressionString.concat("0");
        setLastOperandFlag(true);
        MainActivity.displayExpression(expressionString);
    }

    /**
     * btnPeriod()
     *
     * @param view
     */
    public void btnPeriod(View view) {

        if (periodFlag) {
            Snackbar.make(parentView, "Illegal to set another period",
                    Snackbar.LENGTH_SHORT).show();
        } else {
            expressionString = expressionString.concat(".");
            setPeriodFlag(true);
        }
        MainActivity.displayExpression(expressionString);
    }

    /*********************************************************
     * Functions
     */

    /**
     * btnDel()
     *
     * @param view
     */
    public void btnDel(View view) {

        if (expressionString.isEmpty()) {
            MainActivity.displayExpression("0");
        } else {

            if (expressionString.endsWith(" ")) {
                deleteTillOperand();

            } else {
                if (!expressionString.isEmpty()) {
                    expressionString = expressionString.substring(0,
                            expressionString.length() - 1);
                }

            }

            MainActivity.displayExpression(expressionString);
        }
    }// end btnDel()


    /**
     * btnClear()
     *
     * @param view
     */
    public void btnClear(View view) {
        MainActivity.displayExpression(expressionString = "");
        MainActivity.displayResult("");
        clearAllFlags();
        Snackbar.make(parentView, "Expression Cleared",
                Snackbar.LENGTH_SHORT).show();
    }

    /**
     * btnClearAll()
     *
     * @param view
     */
    public void btnClearHistory(View view) {
        ExpressionParser.resultOutput(0);
        MainActivity.displayExpression(expressionString = "");

        if (db.getHistory().size() > 0) {
            db.deleteHistory();

            HistoryAdapter adapter = new HistoryAdapter(db.getHistory());
            MainActivity.historyView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            MainActivity.historyView.scrollToPosition(0);
        }
        clearAllFlags();
        Snackbar.make(parentView, "All Cleared",
                Snackbar.LENGTH_SHORT).show();
    }

    /**
     * btnAns()
     *
     * @param view
     */
    public void btnAns(View view) {
        view.setBackgroundColor(Color.parseColor("#607D8B"));

        Snackbar.make(parentView, "Select an Answer from history",
                Snackbar.LENGTH_SHORT).show();
        getAnswer = true;
        MainActivity.displayExpression(expressionString);
    }// end btnAvg()


    /*********************************************************
     * Switches
     */

    /**
     * setOpenBracketsFlag()
     *
     * @param bool
     */
    protected void setOpenBracketsFlag(boolean bool) {
        OpenBracketsFlag = bool;
    }

    /**
     * setLastBracketsFlag()
     *
     * @param bool
     */
    protected static void setLastBracketsFlag(boolean bool) {
        lastBracketsFlag = bool;
        lastOperandFlag = false;
        lastOperatorFlag = false;
    }

    /**
     * setLastOperatorFlag()
     *
     * @param bool
     */
    protected static void setLastOperatorFlag(boolean bool) {
        lastOperatorFlag = bool;
        lastBracketsFlag = false;
        lastOperandFlag = false;
        periodFlag = false;
    }

    /**
     * setLastOperandFlag()
     *
     * @param bool
     */
    protected static void setLastOperandFlag(boolean bool) {
        lastOperandFlag = bool;
        lastBracketsFlag = false;
        lastOperatorFlag = false;
    }

    /**
     * setPeriodFlag
     *
     * @param bool
     */
    protected void setPeriodFlag(boolean bool) {
        periodFlag = bool;
        lastBracketsFlag = false;
        lastOperandFlag = false;
        lastOperatorFlag = false;
    }

    /**
     * clearAllFlags()
     */
    protected void clearAllFlags() {
        lastBracketsFlag = false;
        lastOperandFlag = false;
        lastOperatorFlag = false;
        lastBracketsFlag = false;
        periodFlag = false;
    }

    /**
     * Allows you to either use an expression or result from history in next equation
     *
     * @param aHistoryObject
     */
    protected static void recallHistory(HistoryObject aHistoryObject) {

        if (getAnswer && (lastOperatorFlag || expressionString.equals(""))) {

            if (aHistoryObject.resultString.equals("-Infinity") ||
                    aHistoryObject.resultString.equals("Infinity") ||
                    Double.parseDouble(aHistoryObject.resultString) == 0) {
                Snackbar.make(parentView, "Error - Invalid result from history",
                        Snackbar.LENGTH_SHORT).show();
                return;
            }

            expressionString += aHistoryObject.resultString;
            getAnswer = false;

        } else if (lastOperatorFlag || expressionString.equals("")) {

            expressionString += aHistoryObject.expressionString;

        } else {

            Snackbar.make(parentView, "Enter an operator",
                    Snackbar.LENGTH_SHORT).show();
        }
//        Log.d(LOG_TAG, "expression: " + expressionString);
        MainActivity.displayExpression(expressionString);
        setFlags(expressionString);
        setLastOperandFlag(true);
    }

    protected static void setFlags(String expressionStr) {
        if (expressionStr.contains("[\\(]")
                && !expressionStr.contains("[\\)]")) {
            OpenBracketsFlag = true;
        }
        if (expressionStr.endsWith("[ ][\\+-\\^%/x][ ]")) {
           setLastOperatorFlag(true);
        }
    }

    /**
     * grabs the top history item and loads the result into the expression view
     * @param operator
     */
    protected static void reloadPreviousResult(String operator){

        if (db.getHistory().isEmpty()) {
            Snackbar.make(parentView, "Error - History not available, Enter number",
                    Snackbar.LENGTH_SHORT).show();
            return;
        }
        List<HistoryObject> history = db.getHistory();
        HistoryObject aHistoryObject = history.get(0);

        if (aHistoryObject.resultString.equals("Infinity") ||
                aHistoryObject.resultString.equals("-Infinity") ||
                Double.parseDouble(aHistoryObject.resultString) == 0) {
            Snackbar.make(parentView, "Error - Invalid result from history",
                    Snackbar.LENGTH_SHORT).show();
            return;
        }
        expressionString = (aHistoryObject.resultString + operator);

        MainActivity.displayExpression(expressionString);
        setFlags(expressionString);
        setLastOperatorFlag(true);
    }

    /**
     * deletes starting from the end of expressionString string the first operator it encounters
     */
    protected void deleteTillOperand() {
        int i = 0;
        String lastChar = expressionString.substring(expressionString.length() - 1, expressionString.length());

        while (!expressionString.isEmpty() && !ExpressionParser.isOperand(lastChar)) {
            if (lastChar.matches("\\)")) setOpenBracketsFlag(true);
            i++;
            if (!expressionString.isEmpty()) {
                expressionString = expressionString.substring(0, expressionString.length() - 1);
                if (!expressionString.isEmpty()) {
                    lastChar = expressionString.substring(expressionString.length() - 1, expressionString.length());
                }
            }
            if (i > 4) break;
        }

        if (expressionString.length() > 4 && expressionString.substring(expressionString.substring(0, expressionString.length()-1).lastIndexOf(" "),expressionString.length()).matches("[\\(\\)log\\^\\+\\-\\%/x]+")){
            setLastOperatorFlag(true);
        } else {
            setLastOperandFlag(true);
        }
        Log.d(LOG_TAG, "In delete " + "expression String is: " + expressionString
                + "\nlast char is " + lastChar);
    }
}
