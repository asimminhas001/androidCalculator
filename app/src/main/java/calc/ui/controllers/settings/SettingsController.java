package calc.ui.controllers.settings;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bluelinelabs.conductor.ControllerChangeHandler;
import com.bluelinelabs.conductor.ControllerChangeType;

import butterknife.BindView;
import calc.ui.controllers.base.BaseController;
import kamal.calculator.R;

/**
 * Created by mhamoud on 2016-12-27.
 */

public class SettingsController extends BaseController implements SettingsView {

    private SettingsPresenter presenter;

    @BindView(R.id.settings_textview)
    TextView textView;

    public SettingsController() {
        setHasOptionsMenu(false);
    }

    @Override
    protected String getTitle() {
        return getResources().getString(R.string.settings_screen_name);
    }

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_settings, container, false);
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);
    }

    @Override
    protected void onAttach(@NonNull View view) {
        super.onAttach(view);

        presenter = new SettingsPresenter();
        presenter.attachView(this);

        getActionBar().setDisplayShowHomeEnabled(false);
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

}
