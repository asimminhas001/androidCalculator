package calc.ui.controllers.home;

import calc.ui.BasePresenter;

/**
 * Created by mhamoud on 2017-01-02.
 */

public class HomePresenter implements BasePresenter<HomeView> {

    private HomeView homeView;

    @Override
    public void attachView(HomeView view) {
        // attach/subscribe/open

        this.homeView = view;

        homeView.setText("Tap here for a Fade effect. Tap the Settings button for a Flip effect");
    }

    @Override
    public void detachView() {
        // detach/unsubscribe/close

        this.homeView = null;
    }
}
