package calc.realm;

import android.util.Log;

import calc.realm.models.Equation;
import io.realm.Realm;
import io.realm.RealmResults;
import timber.log.Timber;

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
            e.printStackTrace();
            Timber.tag("Realm Service Error: saveEquation()");
            Timber.e(e);
            return false;
        }
    }

    public RealmResults<Equation> getAllEquations() {
        return mRealm.where(Equation.class).findAll();
    }
}
