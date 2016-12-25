package calc.ui.calculator;

import android.content.Context;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import calc.ui.settings.SettingsScreen;
import flow.Flow;
import kamal.calculator.R;

/**
 * Created by mhamoud on 2016-12-24.
 */

public final class CalculatorView extends LinearLayout implements View.OnClickListener {

    public CalculatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

        setOrientation(VERTICAL);
    }

    void init() {
        ButterKnife.bind(this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        TextView toolbarTitle = ButterKnife.findById(this, R.id.toolbar_title);
        ImageView toolbarSettingsBtn = ButterKnife.findById(this, R.id.toolbar_settings_btn);

        toolbarTitle.setText(R.string.calculator_screen_name);
        toolbarSettingsBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view instanceof ImageView) {
            // Go to SettingsScreen
            // You can pass data by adding a constructor to the SettingsScreen
            Flow.get(getContext()).set(new SettingsScreen());
        }
    }
}
