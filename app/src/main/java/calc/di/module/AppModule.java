package calc.di.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import calc.realm.RealmService;
import dagger.Module;
import dagger.Provides;
import calc.CalculatorApp;
import dagger.producers.ProducerModule;
import io.realm.Realm;

/**
 * Created by mhamoud on 2016-12-24.
 */

@Module
public class AppModule {

    private CalculatorApp calculatorApp;

    public AppModule(CalculatorApp app) {
        this.calculatorApp = app;
    }

    @Provides
    @Singleton
    public Context provideApplicationContext() {
        return calculatorApp;
    }

    @Provides
    @Singleton
    public SharedPreferences provideSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(calculatorApp);
    }

    @Provides
    Realm provideRealm() {
        return Realm.getDefaultInstance();
    }

    @Provides
    RealmService provideRealmService(final Realm realm) {
        return new RealmService(realm);
    }

}
