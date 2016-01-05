package kamal.calculator;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView resultView;
    private TextView expressionView;
    private static String expressionString = "";

    // flags
    private static boolean OpenBracketsFlag = false;
    private static boolean lastBracketsFlag = false;
    private static boolean lastOperatorFlag = false;
    private static boolean lastOperandFlag = false;
    private static boolean resultRequested = false;
    private static boolean averageCalculation = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expressionView = (TextView) findViewById(R.id.expression_output);
        resultView = (TextView) findViewById(R.id.result_output);

    }// end onCreate()

    // @+id/col1 functions
    //////////////////////////////////////////////////////////////////////////////

    // Delete button clicked
    // - deletes the last character in expression
    public void btnDel(View view) {

        if (expressionString.isEmpty()) {
            displayExpression("0");
        }
        else {
            expressionString = expressionString.substring(0, expressionString.length() - 1);
            displayExpression(expressionString);


        }
    }// end btnDel()

    // Bracket button clicked
    //  - adds either opening or closing bracket to the expression string
    public void btnBrackets(View view) {
        String closeBrackets = ")", openBrackets = "(";

        if (!OpenBracketsFlag && !lastBracketsFlag) {
            expressionString += openBrackets;
            setOpenBracketsFlag(true);
        }
        else if (!lastBracketsFlag) {
            expressionString += closeBrackets;
            setOpenBracketsFlag(false);
        }
        else {
            View v = (View) findViewById(R.id.parent);
            Snackbar.make(v, "Input Number after Bracket", Snackbar.LENGTH_SHORT).show();
        }
        displayExpression(expressionString);
    }// end btnBrackets()

    // Division ( / ) button clicked
    //  - adds division to the expression string
    public void btnDiv(View view) {
        String operator = "\u00F7";

        if (lastOperatorFlag) {
            View v = (View) findViewById(R.id.parent);
            Snackbar.make(v, "Input Number", Snackbar.LENGTH_SHORT).show();
        }
        else {
            expressionString += operator;
            setLastOperatorFlag(true);
        }

        displayExpression(expressionString);
    }// end btnDiv

    // Multiplication ( x ) button clicked
    //  - adds x to the expression string
    public void btnMul(View view) {
        String operator = "x";

        if (lastOperatorFlag) {
            View v = (View) findViewById(R.id.parent);
            Snackbar.make(v, "Input Number", Snackbar.LENGTH_SHORT).show();
        }
        else {
            expressionString += operator;
            setLastOperatorFlag(true);
        }

        displayExpression(expressionString);
    }// end btnMul()

    // Subtraction ( - ) button clicked
    //  - adds - to the expression string
    public void btnSub(View view) {
        String operator = "-";

        if (lastOperatorFlag) {
            View v = (View) findViewById(R.id.parent);
            Snackbar.make(v, "Input Number", Snackbar.LENGTH_SHORT).show();
        }
        else {
            expressionString += operator;
            setLastOperatorFlag(true);
        }

        displayExpression(expressionString);
    }// end btnSub

    // Addition ( + ) button clicked
    //  - adds + to the expression string
    public void btnAdd(View view) {
        String operator = "+";

        if (lastOperatorFlag) {
            View v = (View) findViewById(R.id.parent);
            Snackbar.make(v, "Input Number", Snackbar.LENGTH_SHORT).show();
        }
        else {
            expressionString += operator;
            setLastOperatorFlag(true);
        }

        displayExpression(expressionString);
    }// end btnAdd()


    // @+id/col2 functions
    //////////////////////////////////////////////////////////////////////////////

    // Average button clicked
    //  - sums the next set of operands entered
    public void btnAvg(View view) {
        String operator = "+";

        View v = (View) findViewById(R.id.parent);
        Snackbar.make(v, "Input number followed by + " +
                "\nAverage is calculated automatically after = is pressed",
                Snackbar.LENGTH_SHORT).show();

        if (averageCalculation) {
            Snackbar.make(v, "Input Number", Snackbar.LENGTH_SHORT).show();
        }
        else {
            setAverageCalculation(true);
        }

        displayExpression(expressionString);
    }// end btnSum()

    // @+id/col3 functions "\u00B2" = square subscript
    //////////////////////////////////////////////////////////////////////////////

    // @+id/col4 functions
    //////////////////////////////////////////////////////////////////////////////

    // btnClear()
    // - clears both expressions and result and

    // history functions
    //////////////////////////////////////////////////////////////////////////////

    // views
    //////////////////////////////////////////////////////////////////////////////

    // method expressionOutput
    //  - argument : int
    //  - calculates what is to be outputted in the expressions view
    //  - calls displayExpression()
    public void expressionOutput(int num) {

        if (expressionString.equals("")) {
            expressionString += num;
        }
        else {
            expressionString += " " + num;
        }

        displayExpression(expressionString);
    }

    // method expressionOutput
    //  - argument : int
    //  - calculates what is to be outputted in the expressions view
    //  - calls displayResult()
    public void resultOutput(int num) {
        String resultString = "" + num;
        displayResult(resultString);
    }

    // method displayExpression
    //  displays expression in the expressionView
    private void displayExpression(String aString) {
            expressionView.setText(aString);
    }

    // method displayResult
    //  - argument = String
    //  - displays the string in the resultView
    private void displayResult(String aString) {
        resultView.setText(aString);
    }

    // switches
    //////////////////////////////////////////////////////////////////////////////

    // setOpenBracketsFlag
    //  - argument = boolean
    //  sets the flag OpenBracketsFlag to the argument
    private void setOpenBracketsFlag(boolean bool) {
        OpenBracketsFlag = bool;
        setLastBracketsFlag(true);
    }


    // setLastBracketsFlag
    // - argument = boolean
    //  sets the flag lastBracketsFlag to the argument
    private void setLastBracketsFlag(boolean bool) {
        lastBracketsFlag = bool;
        setLastOperandFlag(false);
        setLastOperatorFlag(false);
    }

    // setLastOperatorFlag
    // - argument = boolean
    //  sets the flag lastOperator to the argument
    private void setLastOperatorFlag(boolean bool) {
        lastOperatorFlag = bool;
        setLastBracketsFlag(false);
        setLastOperandFlag(false);
    }

    // setLastOperandFlag
    // - argument = boolean
    //  sets the flag lastOperand to the argument
    private void setLastOperandFlag(boolean bool) {
        lastOperandFlag = bool;
        setLastBracketsFlag(false);
        setLastOperatorFlag(false);
    }

    // setAverageCalculation
    // - argument = boolean
    //  sets the flag lastOperand to the argument
    private void setAverageCalculation(boolean bool) {
        averageCalculation = bool;
        setLastBracketsFlag(false);
        setLastOperatorFlag(false);
    }

    // clearAll
    //  clears result and expressions, sets all flags to false


}
