package kamal.calculator;

import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Program:
 * Project: ${Project_Name}
 * Author: kamalhamoud
 * Date: 2016-01-07
 */
public class ButtonsHelper {

    static HistorySQLiteConnection db = HistorySQLiteConnection.getsInstance(MainActivity.contextOfApplication);



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
     *
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

        if (lastOperatorFlag || (!lastOperandFlag && !lastBracketsFlag)) {
            Snackbar.make(parentView, "Input Number",
                    Snackbar.LENGTH_SHORT).show();
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
        expressionString = expressionString.concat(" ^ ");
        MainActivity.displayExpression(expressionString);
    }

    /**
     * btnRemainder()
     *
     * @param view
     */
    public void btnRemainder(View view) {
        String operator = " % ";

        if (lastOperatorFlag || (!lastOperandFlag && !lastBracketsFlag)) {
            Snackbar.make(parentView, "Input Number",
                    Snackbar.LENGTH_SHORT).show();
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

        if (lastOperatorFlag || (!lastOperandFlag && !lastBracketsFlag)) {
            Snackbar.make(parentView, "Input Number",
                    Snackbar.LENGTH_SHORT).show();
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

        if (lastOperatorFlag || (!lastOperandFlag && !lastBracketsFlag)) {
            Snackbar.make(parentView, "Input Number",
                    Snackbar.LENGTH_SHORT).show();
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

        if (lastOperatorFlag || (!lastOperandFlag && !lastBracketsFlag)) {
            Snackbar.make(parentView, "Input Number",
                    Snackbar.LENGTH_SHORT).show();
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
        if (expressionString.endsWith(" ")) {
            Snackbar.make(parentView, "End expression with a number.",
                    Snackbar.LENGTH_SHORT).show();
            return;
        }
        HistoryObject historyObject = ExpressionParser.computeResult(expressionString);
        List<HistoryObject> aList = db.getHistory();

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

        if (!OpenBracketsFlag && !lastBracketsFlag && (lastOperatorFlag || expressionString.equals(""))) {
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
        expressionString = expressionString.concat(" log ");
        setLastOperatorFlag(true);
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
            String lastChar = expressionString.substring(expressionString.length() - 1);

            if (ExpressionParser.isSpace(lastChar)) {
                expressionString = expressionString.substring(0,
                        expressionString.length() - 3);

                setLastOperandFlag(true);
                if (OpenBracketsFlag) {
                    setOpenBracketsFlag(false);
                }

            } else {
                expressionString = expressionString.substring(0,
                        expressionString.length() - 1);
            }

            MainActivity.displayExpression(expressionString);
        }
    }// end btnDel()

    /**
     * btnClearExp()
     *
     * @param view
     */
    public void btnClearExp(View view) {
        MainActivity.displayExpression(expressionString = "");
        clearAllFlags();
        Snackbar.make(parentView, "Expression Cleared",
                Snackbar.LENGTH_SHORT).show();
    }

    /**
     * btnClearAll()
     * @param view
     */
    public void btnClearAll(View view) {
        ExpressionParser.resultOutput(0);
        MainActivity.displayExpression(expressionString = "");
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
        List<HistoryObject> aList = db.getHistory();
        HistoryObject lastExpression;
        Snackbar.make(parentView, "Lets you use the result of an Expression in history",
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
    protected void setLastBracketsFlag(boolean bool) {
        lastBracketsFlag = bool;
        lastOperandFlag = false;
        lastOperatorFlag = false;
    }

    /**
     * setLastOperatorFlag()
     *
     * @param bool
     */
    protected void setLastOperatorFlag(boolean bool) {
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
     *  Allows you to either use an expression or result from history in next equation
     * @param aHistoryObject
     */
    public static void recallHistory(HistoryObject aHistoryObject) {

        if (getAnswer && (lastOperatorFlag || expressionString.equals(""))) {
            expressionString += aHistoryObject.resultString;
            getAnswer = false;
        } else if (lastOperatorFlag || expressionString.equals("")){
            expressionString += aHistoryObject.expressionString;
        } else {
            Snackbar.make(parentView, "Enter an operator",
                    Snackbar.LENGTH_SHORT).show();
        }
        MainActivity.displayExpression(expressionString);
        setFlags(aHistoryObject);
        setLastOperandFlag(true);
    }

    public static void setFlags(HistoryObject aHistoryObject) {
        if (aHistoryObject.expressionString.contains("[\\(]")
                && !aHistoryObject.expressionString.contains("[\\)]")) {
            OpenBracketsFlag = true;
        }
    }

}
