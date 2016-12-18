package kamal.calculator;

import android.support.v7.widget.RecyclerView;

/**
 * Project: Calculator
 * Author: kamalhamoud
 * Date: 2016-12-03
 */

public interface MainActivityContract {

    void displayExpression(String aString);
    void displayResult(String aString);
    RecyclerView getHistoryRecyclerView();
}
