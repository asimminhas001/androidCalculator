package calc;

import android.app.Application;
import android.util.Log;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import calc.di.component.AppComponent;
import calc.di.component.DaggerAppComponent;
import calc.di.module.AppModule;
import io.realm.BuildConfig;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import timber.log.Timber;

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

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        initRealmConfiguration();
        appComponent.inject(this);
    }

    public AppComponent getComponent() {
        return appComponent;
    }

    private void initRealmConfiguration() {
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
