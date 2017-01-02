package calc.ui;

/**
 * Created by mhamoud on 2017-01-02.
 */

public interface BasePresenter<V> {

    void attachView(V view);

    void detachView();
}
