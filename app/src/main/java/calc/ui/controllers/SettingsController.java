package calc.ui.controllers;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.ControllerChangeHandler;
import com.bluelinelabs.conductor.ControllerChangeType;

import calc.ui.controllers.base.BaseController;
import kamal.calculator.R;

/**
 * Created by mhamoud on 2016-12-27.
 */

public class SettingsController extends BaseController {

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

        // Set up views here (eg RecyclerView + adapter)

    }

    @Override
    protected void onAttach(@NonNull View view) {
        super.onAttach(view);

        // presenter.attach();

        getActionBar().setDisplayShowHomeEnabled(false);
    }

    @Override
    protected void onDetach(@NonNull View view) {
        super.onDetach(view);

        // presenter.detach();
    }

    @Override
    protected void onChangeStarted(@NonNull ControllerChangeHandler changeHandler, @NonNull ControllerChangeType changeType) {
        setOptionsMenuHidden(!changeType.isEnter);

        if (changeType.isEnter) {
            setTitle();
        }
    }

}
