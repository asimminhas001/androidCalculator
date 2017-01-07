package calc.ui.controllers.settings;

import calc.ui.BasePresenter;

/**
 * Created by mhamoud on 2017-01-02.
 */

public class SettingsPresenter implements BasePresenter<SettingsView> {

    private SettingsView settingsView;

    @Override
    public void attachView(SettingsView view) {
        // attach/subscribe/open

        this.settingsView = view;

        settingsView.setText("Press the Back btn to return to the previous screen.");
    }

    @Override
    public void detachView() {
        // detach/unsubscribe/close

        this.settingsView = null;
    }

}
