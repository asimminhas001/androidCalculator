package calc.di.component;

import javax.inject.Singleton;

import calc.ui.ConductorBaseActivity;
import calc.ui.FlowBaseActivity;
import dagger.Component;
import calc.CalculatorApp;
import calc.di.module.AppModule;

/**
 * Created by mhamoud on 2016-12-24.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(CalculatorApp calculatorApp);
    void inject(FlowBaseActivity flowBaseActivity);
    void inject(ConductorBaseActivity conductorBaseActivity);
//    void inject(MainPresenter mainPresenter);
}
