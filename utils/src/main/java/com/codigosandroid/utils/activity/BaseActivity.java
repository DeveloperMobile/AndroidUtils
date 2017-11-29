package com.codigosandroid.utils.activity;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import com.codigosandroid.utils.utils.AlertUtil;

/**
 * Created by Tiago on 17/11/2017.
 */

public class BaseActivity extends DebugActivity {

    private static final String TAG = BaseActivity.class.getSimpleName();

    protected Context getContext() {
        return this;
    }

    private Activity getActivity() {
        return this;
    }

    protected void toast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    protected void toast(int msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    protected void alert(String msg) {
        AlertUtil.alert(this, msg);
    }

    protected void alert(String title, String msg) {
        AlertUtil.alert(this, title, msg);
    }

    protected void alert(int msg) {
        AlertUtil.alert(this, getString(msg));
    }

    protected void alert(int title, int msg) {
        AlertUtil.alert(this, getString(title), getString(msg));
    }

    protected void snack(View view, int msg, Runnable runnable) {
        this.snack(view, this.getString(msg), runnable);
    }

    protected void snack(View view, int msg) {
        this.snack(view, getString(msg), null);
    }

    protected void snack(View view, String msg, final Runnable runnable) {
        Snackbar.make(view, msg, 0).setAction("Ok", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (runnable != null) {
                    runnable.run();
                }
            }
        }).show();
    }

    protected boolean getBoolean(int res) {
        return getResources().getBoolean(res);
    }

}
