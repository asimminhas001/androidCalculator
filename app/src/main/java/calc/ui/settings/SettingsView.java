package calc.ui.settings;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import kamal.calculator.R;

/**
 * Created by mhamoud on 2016-12-24.
 */

public final class SettingsView extends LinearLayout {

    public SettingsView(Context context, AttributeSet attrs) {
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

        toolbarTitle.setText(R.string.settings_screen_name);
        toolbarSettingsBtn.setVisibility(GONE);
    }
}
