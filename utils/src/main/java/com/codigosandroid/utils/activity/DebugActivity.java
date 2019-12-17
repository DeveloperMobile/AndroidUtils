package com.codigosandroid.utils.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.codigosandroid.utils.utils.LogUtil;

/**
 * Created by Tiago on 23/11/2017.
 */

public class DebugActivity extends AppCompatActivity {

    protected static final String TAG = DebugActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isLifecycle()) {
            log("onCreate(): " + savedInstanceState);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (isLifecycle()) {
            log("onStart()");
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (isLifecycle()) {
            log("onRestart()");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isLifecycle()) {
            log("onResume()");
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (isLifecycle()) {
            log("onSaveInstanceState(): " + outState);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isLifecycle()) {
            log("onPause()");
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isLifecycle()) {
            log("onStop()");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isLifecycle()) {
            LogUtil.debug(TAG, "onDestroy()");
        }
    }

    public String getClassName() {
        // Retorna o nome da classe sem o pacote
        Class cls = ((Object) this).getClass();
        String s = cls.getSimpleName();
        return s;
    }

    protected void log(String msg) {
        if (isLogOn()) {
            LogUtil.debug(TAG, msg);
        }
    }

    protected boolean isLogOn() {
        return true;
    }

    protected boolean isLifecycle() {
        return false;
    }

}
