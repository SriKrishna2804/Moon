package com.resolve.security;

import android.app.Application;

import com.resolve.security.utils.ConnectivityReceiver;
import com.resolve.security.utils.Preferences;
import com.resolve.security.web.AppModule;
import com.resolve.security.web.DaggerNetComponent;
import com.resolve.security.web.NetComponent;
import com.resolve.security.web.NetModule;

public class App extends Application {

    private static App app;

    public static App getApp() {
        return app;
    }

    private NetComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        Preferences.init(this);
        mNetComponent = DaggerNetComponent.builder()
                // list of modules that are part of this component need to be created here too
                .appModule(new AppModule(this)) // This also corresponds to the name of your module: %component_name%Module
                .netModule(new NetModule("http://risolvesm.com/security/"))
                .build();
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }

    public void setNetComponent(String baseUrl){
        mNetComponent = DaggerNetComponent.builder()
                // list of modules that are part of this component need to be created here too
                .appModule(new AppModule(this)) // This also corresponds to the name of your module: %component_name%Module
                .netModule(new NetModule(baseUrl))
                .build();
    }


    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }

}