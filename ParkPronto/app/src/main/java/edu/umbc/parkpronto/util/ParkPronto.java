package edu.umbc.parkpronto.util;

import android.app.Application;
import android.content.Context;

/**
 * Created by sushantathley on 9/29/16.
 */

public class ParkPronto extends Application {

    private static Context __context = null;

    @Override
    public void onCreate() {
        super.onCreate();
        __context = getApplicationContext();


    }


    public static Context getAppContext() {
        return __context;
    }

}
