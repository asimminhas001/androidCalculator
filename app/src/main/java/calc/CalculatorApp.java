package calc;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import calc.di.component.AppComponent;
import calc.di.component.DaggerAppComponent;
import calc.di.module.AppModule;

/**
 * Created by mhamoud on 2016-12-24.
 */

public class CalculatorApp extends Application {

    public static RefWatcher refWatcher;

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        refWatcher = LeakCanary.install(this);

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

        appComponent.inject(this);
    }

    public AppComponent getComponent() {
        return appComponent;
    }

}
