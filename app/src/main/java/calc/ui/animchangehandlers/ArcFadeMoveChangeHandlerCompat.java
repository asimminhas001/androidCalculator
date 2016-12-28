package calc.ui.animchangehandlers;

import com.bluelinelabs.conductor.changehandler.FadeChangeHandler;
import com.bluelinelabs.conductor.changehandler.TransitionChangeHandlerCompat;

/**
 * Created by mhamoud on 2016-12-27.
 */

public class ArcFadeMoveChangeHandlerCompat extends TransitionChangeHandlerCompat {

    public ArcFadeMoveChangeHandlerCompat() {
        super(new ArcFadeMoveChangeHandler(), new FadeChangeHandler());
    }

}