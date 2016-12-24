package calc.di.component;

import javax.inject.Singleton;

import calc.ui.BaseActivity;
import dagger.Component;
import calc.CalculatorApp;
import calc.di.module.AppModule;

/**
 * Created by mhamoud on 2016-12-24.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(CalculatorApp app);
    void inject(BaseActivity baseActivity);
//    void inject(MainPresenter mainPresenter);
}
