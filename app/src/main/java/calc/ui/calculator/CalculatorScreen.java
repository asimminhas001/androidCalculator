package calc.ui.calculator;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mhamoud on 2016-12-24.
 */

public final class CalculatorScreen implements Parcelable {

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CalculatorScreen> CREATOR = new Creator<CalculatorScreen>() {
        @Override
        public CalculatorScreen createFromParcel(Parcel in) {
            return new CalculatorScreen();
        }

        @Override
        public CalculatorScreen[] newArray(int size) {
            return new CalculatorScreen[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        return o != null && o instanceof CalculatorScreen;
    }
}
