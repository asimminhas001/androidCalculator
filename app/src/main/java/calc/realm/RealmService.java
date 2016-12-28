package calc.realm;

import android.util.Log;

import calc.realm.models.Equation;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by kamalhamoud on 2016-12-27.
 */

public class RealmService {
    private final Realm mRealm;

    public RealmService(final Realm realm) {
        mRealm = realm;
    }

    public void closeRealm() {
        mRealm.close();
    }

    public boolean saveEquation(Equation eq) {
        try {
            mRealm.insertOrUpdate(eq);
            return true;
        } catch (IllegalStateException e) {
            Log.d(this.getClass().getSimpleName(), e.getLocalizedMessage());
            return false;
        }
    }

    public RealmResults<Equation> getAllEquations() {
        return mRealm.where(Equation.class).findAll();
    }
}
