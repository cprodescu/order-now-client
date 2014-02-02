package de.code4fun.ordernow;

import android.app.Application;
import com.parse.Parse;

public class App extends Application {

    @Override public void onCreate() {
        super.onCreate();

        Parse.initialize(this, "OPmH20F10mK1HrXJTVxVb3S6RLTuiT3J1LAmV3JG",
                "ZEnh4BXnGng5T6nNGKJnd27mnbz6NRzCmclvpwy7");
    }
}
