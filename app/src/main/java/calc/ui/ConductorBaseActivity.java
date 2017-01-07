package calc.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;

import butterknife.BindView;
import butterknife.ButterKnife;
import calc.CalculatorApp;
import calc.ui.controllers.home.HomeController;
import kamal.calculator.R;

/**
 * Created by mhamoud on 2016-12-27.
 */

public final class ConductorBaseActivity extends AppCompatActivity implements BaseView, ActionBarProvider {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.controller_container)
    ViewGroup container;

    private Router router;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((CalculatorApp) getApplication())
                .getComponent()
                .inject(this);

        setContentView(R.layout.activity_conductor);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        router = Conductor.attachRouter(this, container, savedInstanceState);
        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(new HomeController()));
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onBackPressed() {
        if (!router.handleBack()) {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        // todo close realm
        super.onDestroy();
    }
}
