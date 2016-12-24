package calc.ui.settings;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by mhamoud on 2016-12-24.
 */

public final class SettingsView extends LinearLayout {

    public SettingsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
    }

    @Override protected void onFinishInflate() {
        super.onFinishInflate();


//        EditText nameView = (EditText) findViewById(R.id.welcome_screen_name);
//
//        nameView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
//                Flow.get(view).set(new CalculatorScreen(view.getText().toString()));
//                return true;
//            }
//        });
    }
}
