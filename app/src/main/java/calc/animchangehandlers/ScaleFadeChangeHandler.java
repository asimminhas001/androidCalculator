package calc.animchangehandlers;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.changehandler.AnimatorChangeHandler;

/**
 * Created by mhamoud on 2016-12-27.
 */

public class ScaleFadeChangeHandler extends AnimatorChangeHandler {

    public ScaleFadeChangeHandler() {
        super(DEFAULT_ANIMATION_DURATION, true);
    }

    @Override
    @NonNull
    protected Animator getAnimator(@NonNull ViewGroup container, View from, View to, boolean isPush, boolean toAddedToContainer) {
        AnimatorSet animator = new AnimatorSet();
        if (to != null && toAddedToContainer) {
            animator.play(ObjectAnimator.ofFloat(to, View.ALPHA, 0, 1));
        }

        if (from != null) {
            animator.play(ObjectAnimator.ofFloat(from, View.ALPHA, 0));
            animator.play(ObjectAnimator.ofFloat(from, View.SCALE_X, 0.8f));
            animator.play(ObjectAnimator.ofFloat(from, View.SCALE_Y, 0.8f));
        }

        return animator;
    }

    @Override
    protected void resetFromView(@NonNull View from) {
    }
}