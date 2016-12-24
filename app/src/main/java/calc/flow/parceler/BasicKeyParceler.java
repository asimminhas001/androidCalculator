package calc.flow.parceler;

import android.os.Parcelable;
import android.support.annotation.NonNull;

import flow.KeyParceler;

/**
 * Created by mhamoud on 2016-12-24.
 */

/**
 * Assumes states are {@link Parcelable}.
 *
 * A more realistic implementation might rely on a library like auto-value-parcel,
 * auto-parcel, or parceler.
 * */
public final class BasicKeyParceler implements KeyParceler {

    @NonNull
    @Override
    public Parcelable toParcelable(@NonNull Object key) {
        return (Parcelable) key;
    }

    @NonNull
    @Override
    public Object toKey(@NonNull Parcelable parcelable) {
        return parcelable;
    }
}
