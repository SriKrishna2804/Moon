package com.resolve.security.web;


import com.resolve.security.LoginActivity;
import com.resolve.security.MainActivity;
import com.resolve.security.datados.LoginRequest;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules={AppModule.class, NetModule.class})
public interface NetComponent {
    void inject(MainActivity activity);
    void inject(LoginActivity activity);
}
