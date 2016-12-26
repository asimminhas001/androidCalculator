package calc;

import android.app.Application;

import calc.di.component.AppComponent;
import calc.di.component.DaggerAppComponent;
import calc.di.module.AppModule;

/**
 * Created by mhamoud on 2016-12-24.
 */

public class CalculatorApp extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

        appComponent.inject(this);
    }

    public AppComponent getComponent() {
        return appComponent;
    }

}
