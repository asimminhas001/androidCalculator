package calc.ui.controllers.base;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.bluelinelabs.conductor.ControllerChangeHandler;
import com.bluelinelabs.conductor.ControllerChangeType;

import calc.CalculatorApp;

/**
 * Created by mhamoud on 2016-12-28.
 */

public abstract class RefWatchingController extends ButterKnifeController {

    protected RefWatchingController() {
    }

    protected RefWatchingController(Bundle args) {
        super(args);
    }

    private boolean hasExited;

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (hasExited) {
            CalculatorApp.refWatcher.watch(this);
        }
    }

    @Override
    protected void onChangeEnded(@NonNull ControllerChangeHandler changeHandler, @NonNull ControllerChangeType changeType) {
        super.onChangeEnded(changeHandler, changeType);

        hasExited = !changeType.isEnter;
        if (isDestroyed()) {
            CalculatorApp.refWatcher.watch(this);
        }
    }

}
