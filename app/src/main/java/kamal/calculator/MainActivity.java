package kamal.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    protected static TextView resultView;
    protected static TextView expressionView;
    protected static View parentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expressionView = (TextView) findViewById(R.id.expression_output);
        resultView = (TextView) findViewById(R.id.result_output);
        parentView = findViewById(R.id.parent);
        final ButtonsHelper buttonsHelper = new ButtonsHelper(parentView);

        /*********************************************************
         * Operands Buttons onClick listeners
         */

        /**
         * btnPeriod
         */
        Button btnPeriod = (Button) findViewById(R.id.btnPeriod);
        btnPeriod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonsHelper.btnPeriod(v);
            }
        });

        /**
         * btn0
         */
        Button btn0 = (Button) findViewById(R.id.btn0);
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonsHelper.btn0(v);
            }
        });

        /**
         * btn1
         */
        Button btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonsHelper.btn1(v);
            }
        });

        /**
         * btn2
         */
        Button btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonsHelper.btn2(v);
            }
        });

        /**
         * btn3
         */
        Button btn3 = (Button) findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonsHelper.btn3(v);
            }
        });

        /**
         * btn4
         */
        Button btn4 = (Button) findViewById(R.id.btn4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonsHelper.btn4(v);
            }
        });

        /**
         * btn5
         */
        Button btn5 = (Button) findViewById(R.id.btn5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonsHelper.btn5(v);
            }
        });

        /**
         * btn6
         */
        Button btn6 = (Button) findViewById(R.id.btn6);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonsHelper.btn6(v);
            }
        });

        /**
         * btn7
         */
        Button btn7 = (Button) findViewById(R.id.btn7);
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonsHelper.btn7(v);
            }
        });

        /**
         * btn8
         */
        Button btn8 = (Button) findViewById(R.id.btn8);
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonsHelper.btn8(v);
            }
        });

        /**
         * btn9
         */
        Button btn9 = (Button) findViewById(R.id.btn9);
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonsHelper.btn9(v);
            }
        });

        /*********************************************************
         * Operator Buttons onClick Listeners
         */

        /**
         * btnEq
         */
        Button btnEq = (Button) findViewById(R.id.btnEq);
        btnEq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonsHelper.btnEq(v);
            }
        });

        /**
         * btnAdd
         */
        Button btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonsHelper.btnAdd(v);
            }
        });

        /**
         * btnSub
         */
        Button btnSub = (Button) findViewById(R.id.btnSub);
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonsHelper.btnSub(v);
            }
        });

        /**
         * btnMul
         */
        Button btnMul= (Button) findViewById(R.id.btnMul);
        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonsHelper.btnMul(v);
            }
        });

        /**
         * btnRemainder
         */
        Button btnRemainder = (Button) findViewById(R.id.btnRemainder);
        btnRemainder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonsHelper.btnRemainder(v);
            }
        });

        /**
         * btnDiv
         */
        Button btnDiv = (Button) findViewById(R.id.btnDiv);
        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonsHelper.btnDiv(v);
            }
        });

        /*********************************************************
         *
         * Functions Buttons onClick Listener
         */

        /**
         * btnBrackets
         */
        Button btnBrackets = (Button) findViewById(R.id.btnBrackets);
        btnBrackets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonsHelper.btnBrackets(v);
            }
        });

        /**
         * btnDel
         */
        Button btnDel = (Button) findViewById(R.id.btnDel);
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonsHelper.btnDel(v);
            }
        });


        /**
         * btnAvg
         */
        Button btnAvg = (Button) findViewById(R.id.btnAvg);
        btnAvg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonsHelper.btnAvg(v);
            }
        });

        /**
         * btnSq
         */
        Button btnExponent = (Button) findViewById(R.id.btnExponent);
        btnExponent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonsHelper.btnExponent(v);
            }
        });

        /**
         * btnFF
         */
        Button btnLog = (Button) findViewById(R.id.btnLog);
        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonsHelper.btnLog(v);
            }
        });

        /**
         * btnClearExp
         */
        Button btnClearExp = (Button) findViewById(R.id.btnClearExp);
        btnClearExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonsHelper.btnClearExp(v);
            }
        });

        /**
         * btnClearRes
         */
        Button btnClearRes = (Button) findViewById(R.id.btnClearAll);
        btnClearRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonsHelper.btnClearAll(v);
            }
        });

    }// end onCreate()

    /*********************************************************
     * View methods
     */

    /**
     * displayExpression()
     *  - displays the string passed in the expressionView
     *  @param aString
     */
    protected static void displayExpression(String aString) {
        if (aString.compareTo("") == 0) {
            expressionView.setText("0");
        } else {
            expressionView.setText(aString);
        }
    }

    /**
     * displayResult()
     *  - displays the string passed in the resultView
     *  @param aString
     */
    protected static void displayResult(String aString) {
        if (aString.compareTo("") == 0) {
            resultView.setText("0");
        } else {
            resultView.setText(aString);
        }
    }


}
