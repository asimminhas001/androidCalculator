package calc.ui.settings;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * Created by mhamoud on 2016-12-24.
 */

public final class SettingsScreen implements Parcelable {

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SettingsScreen> CREATOR = new Creator<SettingsScreen>() {
        @Override
        public SettingsScreen createFromParcel(Parcel in) {
            return new SettingsScreen();
        }

        @Override
        public SettingsScreen[] newArray(int size) {
            return new SettingsScreen[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        return o != null && o instanceof SettingsScreen;
    }
}
