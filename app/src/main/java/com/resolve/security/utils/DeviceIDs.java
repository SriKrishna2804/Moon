package com.resolve.security.utils;

import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;

import java.util.UUID;

public class DeviceIDs {

    public static String getSecureAndroidID(Context context){
        String androidId = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        return androidId;
    }

    private static String uniqueID = null;
    private static final String PREF_UNIQUE_ID = "PREF_UNIQUE_ID";

    public synchronized static String id(Context context) {
        if (TextUtils.isEmpty(uniqueID)) {
            uniqueID = Preferences.getString(PREF_UNIQUE_ID);
            if (TextUtils.isEmpty(uniqueID)) {
                uniqueID = UUID.randomUUID().toString();
                Preferences.saveValue(PREF_UNIQUE_ID, uniqueID);
            }
        }
        return uniqueID;
    }

}
