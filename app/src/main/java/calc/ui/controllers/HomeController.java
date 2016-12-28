package calc.ui.controllers;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.ControllerChangeHandler;
import com.bluelinelabs.conductor.ControllerChangeType;
import com.bluelinelabs.conductor.RouterTransaction;

import calc.animchangehandlers.FlipChangeHandler;
import calc.ui.controllers.base.BaseController;
import kamal.calculator.R;

/**
 * Created by mhamoud on 2016-12-27.
 */

public class HomeController extends BaseController {

    @Override
    protected String getTitle() {
        return getResources().getString(R.string.calculator_screen_name);
    }

    public HomeController() {
        setHasOptionsMenu(true);
    }

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_home, container, false);
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);

        // Set up views here (eg RecyclerView + adapter)

        getActionBar().setDisplayShowHomeEnabled(true);
        getActionBar().setLogo(R.mipmap.ic_launcher);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.home, menu);
    }

    @Override
    protected void onChangeStarted(@NonNull ControllerChangeHandler changeHandler, @NonNull ControllerChangeType changeType) {
        setOptionsMenuHidden(!changeType.isEnter);

        if (changeType.isEnter) {
            setTitle();
        }
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
