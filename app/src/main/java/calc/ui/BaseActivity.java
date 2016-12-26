package calc.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import calc.CalculatorApp;
import calc.flow.dispatcher.BasicDispatcher;
import calc.flow.parceler.BasicKeyParceler;
import calc.ui.calculator.CalculatorScreen;
import flow.Flow;
import kamal.calculator.R;

/**
 * Created by mhamoud on 2016-12-24.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((CalculatorApp) getApplication())
                .getComponent()
                .inject(this);

        setContentView(R.layout.base_activity_frame);
        ButterKnife.bind(this);
    }

    @Override
    protected void attachBaseContext(Context baseContext) {
        baseContext = Flow.configure(baseContext, this) //
                .dispatcher(new BasicDispatcher(this)) //
                .defaultKey(new CalculatorScreen()) //
                .keyParceler(new BasicKeyParceler()) //
                .install();
        super.attachBaseContext(baseContext);
    }

    @Override
    public void onBackPressed() {
        if (!Flow.get(this).goBack()) {
            super.onBackPressed();
        }
    }
}
