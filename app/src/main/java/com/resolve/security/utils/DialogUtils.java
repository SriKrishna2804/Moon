package com.resolve.security.utils;

import android.content.Context;
import android.support.v7.app.AlertDialog;


public class DialogUtils {

    public static void showDialog(Context context, String message, String title){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton("OK", (dialog, which) -> {
            dialog.dismiss();
        });
        builder.show();
    }

}
