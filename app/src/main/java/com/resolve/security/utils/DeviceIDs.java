package com.resolve.security.utils;

import android.content.Context;
import android.os.Debug;
import android.provider.Settings;
import android.text.TextUtils;

import com.resolve.security.BuildConfig;

import java.util.UUID;

public class DeviceIDs {

    public static final String UNIQUE_RISOLVE_UUID = "ba195532-3eb9-45bb-8710-5711a5a526c5";

    public static String getSecureAndroidID(Context context) {
        String androidId = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        return androidId;
    }

    private static String uniqueID = null;
    private static final String PREF_UNIQUE_ID = "PREF_UNIQUE_ID";

    public synchronized static String id() {
        if (BuildConfig.DEBUG) {
            return UNIQUE_RISOLVE_UUID;
        } else if (TextUtils.isEmpty(uniqueID)) {
            uniqueID = Preferences.getString(PREF_UNIQUE_ID);
            if (TextUtils.isEmpty(uniqueID)) {
                uniqueID = UUID.randomUUID().toString();
                Preferences.saveValue(PREF_UNIQUE_ID, uniqueID);
            }
        }
        return uniqueID;
    }

}
