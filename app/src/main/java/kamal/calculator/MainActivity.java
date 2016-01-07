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
    private String expressionString = "";
    private View parentView;

    /**
     * Flags
     */
    private static boolean OpenBracketsFlag = false;
    private static boolean lastBracketsFlag = false;
    private static boolean lastOperatorFlag = false;
    private static boolean lastOperandFlag = false;
    private static boolean resultRequested = false;
    private static boolean averageCalculation = false;
    private static boolean periodFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expressionView = (TextView) findViewById(R.id.expression_output);
        resultView = (TextView) findViewById(R.id.result_output);
        parentView = findViewById(R.id.parent);

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
            expressionString = expressionString.substring(0,
                    expressionString.length() - 1);
            displayExpression(expressionString);


        }
    }// end btnDel()

    // Bracket button clicked
    //  - adds either opening or closing bracket to the expression string
    public void btnBrackets(View view) {
        String closeBrackets = ")", openBrackets = "(";

        if (!OpenBracketsFlag && !lastBracketsFlag) {
            expressionString = expressionString.concat(openBrackets);
            setOpenBracketsFlag(true);
        }
        else if (!lastBracketsFlag) {
            expressionString = expressionString.concat(closeBrackets);
            setOpenBracketsFlag(false);
        }
        else {
            Snackbar.make(parentView, "Input Number after Bracket",
                    Snackbar.LENGTH_SHORT).show();
        }
        displayExpression(expressionString);
    }// end btnBrackets()

    // Division ( / ) button clicked
    //  - adds division to the expression string
    public void btnDiv(View view) {
        String operator = "\u00F7";

        if (lastOperatorFlag) {
            Snackbar.make(parentView, "Input Number",
                    Snackbar.LENGTH_SHORT).show();
        }
        else {
            expressionString = expressionString.concat(operator);
            setLastOperatorFlag(true);
        }

        displayExpression(expressionString);
    }// end btnDiv

    // Multiplication ( x ) button clicked
    //  - adds x to the expression string
    public void btnMul(View view) {
        String operator = "x";

        if (lastOperatorFlag) {
            Snackbar.make(parentView, "Input Number",
                    Snackbar.LENGTH_SHORT).show();
        }
        else {
            expressionString = expressionString.concat(operator);
            setLastOperatorFlag(true);
        }

        displayExpression(expressionString);
    }// end btnMul()

    // Subtraction ( - ) button clicked
    //  - adds - to the expression string
    public void btnSub(View view) {
        String operator = "-";

        if (lastOperatorFlag) {
            Snackbar.make(parentView, "Input Number",
                    Snackbar.LENGTH_SHORT).show();
        }
        else {
            expressionString = expressionString.concat(operator);
            setLastOperatorFlag(true);
        }

        displayExpression(expressionString);
    }// end btnSub

    // Addition ( + ) button clicked
    //  - adds + to the expression string
    public void btnAdd(View view) {
        String operator = "+";

        if (lastOperatorFlag) {
            Snackbar.make(parentView, "Input Number",
                    Snackbar.LENGTH_SHORT).show();
        }
        else {
            expressionString = expressionString.concat(operator);
            setLastOperatorFlag(true);
        }

        displayExpression(expressionString);
    }// end btnAdd()


    // @+id/col2 functions
    //////////////////////////////////////////////////////////////////////////////

    // Average button clicked (not done)
    //  - sums the next set of operands entered
    public void btnAvg(View view) {
        String operator = "+";

        Snackbar.make(parentView, "Input number followed by + " +
                "\nAverage is calculated automatically after = is pressed",
                Snackbar.LENGTH_SHORT).show();

        if (averageCalculation) {
            Snackbar.make(parentView, "Input Number",
                    Snackbar.LENGTH_SHORT).show();
        }
        else {
            setAverageCalculation(true);
        }

        displayExpression(expressionString);
    }// end btnAvg()

    // Fractions button clicked
    //  - inputs a slash to signify fraction
    public void btnFraction(View view) {
        String operator = "/";

        if (lastOperandFlag) {
            Snackbar.make(parentView, "Input Number",
                    Snackbar.LENGTH_SHORT).show();
        }
        else {
            expressionString = expressionString.concat(operator);
            setLastOperandFlag(true);
        }
        displayExpression(expressionString);
    }// end btnFraction()

    // 9 Operand clicked
    //  - inputs number 9 into expression
    public void btn9(View view) {
        expressionString = expressionString.concat("9");
        setLastOperandFlag(true);
        displayExpression(expressionString);
    }

    // 6 Operand clicked
    //  - inputs number 6 into expression
    public void btn6(View view) {
        expressionString = expressionString.concat("6");
        setLastOperandFlag(true);
        displayExpression(expressionString);
    }

    // 3 Operand clicked
    //  - inputs number 3 into expression
    public void btn3(View view) {
        expressionString = expressionString.concat("3");
        setLastOperandFlag(true);
        displayExpression(expressionString);
    }

    // Equal btn clicked
    //  - inputs number 9 into expression
    public void btnEq(View view) {
        displayResult("result");
    }

    // @+id/col3 functions "\u00B2" = square subscript
    //////////////////////////////////////////////////////////////////////////////

    // FF Operator clicked
    //  - inputs number 0 into expression
    public void btnFF(View view) {
        expressionString = expressionString.concat("\u00B2");
        displayExpression(expressionString);
    }

    // sq Operator clicked
    //  - inputs number 0 into expression
    public void btnSq(View view) {
        expressionString = expressionString.concat("\u00B2");
        displayExpression(expressionString);
    }

    // 8 Operand clicked
    //  - inputs number 8 into expression
    public void btn8(View view) {
        expressionString = expressionString.concat("8");
        setLastOperandFlag(true);
        displayExpression(expressionString);
    }

    // 5 Operand clicked
    //  - inputs number 5 into expression
    public void btn5(View view) {
        expressionString = expressionString.concat("5");
        setLastOperandFlag(true);
        displayExpression(expressionString);
    }

    // 2 Operand clicked
    //  - inputs number 2 into expression
    public void btn2(View view) {
        expressionString = expressionString.concat("2");
        setLastOperandFlag(true);
        displayExpression(expressionString);
    }

    // 0 Operand clicked
    //  - inputs number 0 into expression
    public void btn0(View view) {
        expressionString = expressionString.concat("0");
        setLastOperandFlag(true);
        displayExpression(expressionString);
    }

    // @+id/col4 functions
    //////////////////////////////////////////////////////////////////////////////

    // btnClearExp()
    // - clears expression
    public void btnClearExp(View view) {
        expressionString = "";
        displayExpression(expressionString);
        clearAllFlags();
        Snackbar.make(parentView, "Expression Cleared",
                Snackbar.LENGTH_SHORT).show();
    }

    // btnClearRes()
    // - clears result
    public void btnClearRes(View view) {
        resultOutput(0);
        Snackbar.make(parentView, "Result Cleared",
                Snackbar.LENGTH_SHORT).show();
    }

    // 7 Operand clicked
    //  - inputs number 7 into expression
    public void btn7(View view) {
        expressionString = expressionString.concat("7");
        setLastOperandFlag(true);
        displayExpression(expressionString);
    }

    /**
     * 4 Operand clicked
     * @param view
     * - inputs number 4 into expression
     */
    public void btn4(View view) {
        expressionString = expressionString.concat("4");
        setLastOperandFlag(true);
        displayExpression(expressionString);
    }

    // 1 Operand clicked
    //  - inputs number 1 into expression
    public void btn1(View view) {
        expressionString = expressionString.concat("1");
        setLastOperandFlag(true);
        displayExpression(expressionString);
    }

    // . Operator clicked
    //  - inputs . into expression
    public void btnPeriod(View view) {

        if (periodFlag) {
            Snackbar.make(parentView, "Illegal to set another period",
                    Snackbar.LENGTH_SHORT).show();
        }
        else {
            expressionString = expressionString.concat(".");
            setPeriodFlag(true);
        }
        displayExpression(expressionString);
    }
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
            expressionString = expressionString.concat("" + num);
        }
        else {
            expressionString = expressionString.concat(" " + num);
        }

        displayExpression(expressionString);
    }

    // method resultOutput
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
        if (aString.compareTo("") == 0) {
            expressionView.setText("0");
        } else {
            expressionView.setText(aString);
        }
    }

    // method displayResult
    //  - argument = String
    //  - displays the string in the resultView
    private void displayResult(String aString) {
        if (aString.compareTo("") == 0) {
            resultView.setText("0");
        } else {
            resultView.setText(aString);
        }
    }

    // switches
    //////////////////////////////////////////////////////////////////////////////

    // setOpenBracketsFlag
    //  - argument = boolean
    //  sets the flag OpenBracketsFlag to the argument
    private void setOpenBracketsFlag(boolean bool) {
        OpenBracketsFlag = bool;
        lastBracketsFlag = false;
    }


    // setLastBracketsFlag
    // - argument = boolean
    //  sets the flag lastBracketsFlag to the argument
    private void setLastBracketsFlag(boolean bool) {
        lastBracketsFlag = bool;
        lastOperandFlag = false;
        lastOperatorFlag = false;
    }

    // setLastOperatorFlag
    // - argument = boolean
    //  sets the flag lastOperator to the argument
    private void setLastOperatorFlag(boolean bool) {
        lastOperatorFlag = bool;
        lastBracketsFlag = false;
        lastOperandFlag= false;
    }

    // setLastOperandFlag
    // - argument = boolean
    //  sets the flag lastOperand to the argument
    private void setLastOperandFlag(boolean bool) {
        lastOperandFlag = bool;
        lastBracketsFlag = false;
        lastOperatorFlag = false;
    }

    // setAverageCalculation
    // - argument = boolean
    //  sets the flag lastOperand to the argument
    private void setAverageCalculation(boolean bool) {
        averageCalculation = bool;
        lastBracketsFlag = false;
        lastOperatorFlag = false;
    }

    private void setPeriodFlag(boolean bool) {
        periodFlag = bool;
        lastBracketsFlag = false;
        lastOperandFlag = false;
        lastOperatorFlag = false;
    }

    // clearAllFlags
    //  clears all flags
    private void clearAllFlags() {
        lastBracketsFlag = false;
        lastOperandFlag = false;
        lastOperatorFlag = false;
        averageCalculation = false;
        lastBracketsFlag = false;
    }


}
