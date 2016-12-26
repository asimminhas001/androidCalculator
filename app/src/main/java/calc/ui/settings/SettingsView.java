package calc.ui.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import calc.CalculatorApp;
import kamal.calculator.R;
import uz.shift.colorpicker.LineColorPicker;
import uz.shift.colorpicker.OnColorChangedListener;

import static android.content.ContentValues.TAG;

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

        final Toolbar toolbar = ButterKnife.findById(this, R.id.toolbar);
        TextView toolbarTitle = ButterKnife.findById(this, R.id.toolbar_title);
        ImageView toolbarSettingsBtn = ButterKnife.findById(this, R.id.toolbar_settings_btn);
        LineColorPicker colorPicker = ButterKnife.findById(this, R.id.picker);

        toolbarTitle.setText(R.string.settings_screen_name);
        toolbarSettingsBtn.setVisibility(GONE);

        // set color palette
        colorPicker.setColors(new int[]{Color.RED, R.color.colorPrimary, Color.BLUE, Color.YELLOW});

        // set selected color [optional]
        colorPicker.setSelectedColor(R.color.colorPrimary);

        // set on change listener
        colorPicker.setOnColorChangedListener(new OnColorChangedListener() {
            @Override
            public void onColorChanged(int c) {
                Log.d(TAG, "Selected color " + Integer.toHexString(c));

                // set toolbar color
                toolbar.setBackgroundColor(c);
            }
        });

        // get selected color
        int color = colorPicker.getColor();
    }

}
