package com.resolve.security;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity {

    private ProgressDialog pDialog;

    protected void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    protected void showProgress(String message){
        if(pDialog != null){
            pDialog.dismiss();
        }

        pDialog = new ProgressDialog(this);
        pDialog.setMessage(message);
        pDialog.setTitle(null);
        pDialog.show();
    }

    protected void hideProgress(){
        if(pDialog != null)
            pDialog.dismiss();
    }

    private Toast t;
    protected void showToast(String message){
        if(t != null){
            t.cancel();
        }
        t = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        t.show();
    }
}
