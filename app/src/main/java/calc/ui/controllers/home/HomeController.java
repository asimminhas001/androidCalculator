package calc.ui.controllers.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bluelinelabs.conductor.ControllerChangeHandler;
import com.bluelinelabs.conductor.ControllerChangeType;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler;

import butterknife.BindView;
import calc.animchangehandlers.FlipChangeHandler;
import calc.ui.controllers.base.BaseController;
import calc.ui.controllers.settings.SettingsController;
import kamal.calculator.R;

/**
 * Created by mhamoud on 2016-12-27.
 */

public class HomeController extends BaseController implements HomeView {

    private HomePresenter presenter;

    @BindView(R.id.home_screen_textview)
    TextView textView;

    public HomeController() {
        setHasOptionsMenu(true);
    }

    @Override
    protected String getTitle() {
        return getResources().getString(R.string.calculator_screen_name);
    }

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_home, container, false);
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);
    }

    @Override
    protected void onAttach(@NonNull View view) {
        super.onAttach(view);

        presenter = new HomePresenter();
        presenter.attachView(this);

        getActionBar().setDisplayShowHomeEnabled(true);
        getActionBar().setLogo(R.mipmap.ic_launcher);

        // retrolambda test
        textView.setOnClickListener(view1 -> {
            getRouter().pushController(RouterTransaction.with(new SettingsController())
                    .pushChangeHandler(new FadeChangeHandler())
                    .popChangeHandler(new FadeChangeHandler()));
        });
    }

    @Override
    protected void onDetach(@NonNull View view) {
        super.onDetach(view);

        presenter.detachView();
    }

    @Override
    public void setText(String text) {
        textView.setText(text);
    }

    @Override
    public Context getContext() {
        return getActivity();
    }

    @Override
    protected void onChangeStarted(@NonNull ControllerChangeHandler changeHandler, @NonNull ControllerChangeType changeType) {
        setOptionsMenuHidden(!changeType.isEnter);

        if (changeType.isEnter) {
            setTitle();
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.home, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                getRouter().pushController(RouterTransaction.with(new SettingsController())
                        .pushChangeHandler(new FlipChangeHandler())
                        .popChangeHandler(new FlipChangeHandler()));
                break;
            default:
                break;
        }


        return super.onOptionsItemSelected(item);
    }
}
