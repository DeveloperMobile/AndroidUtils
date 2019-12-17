package com.codigosandroid.utils.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.codigosandroid.utils.utils.LogUtil;

/**
 * Created by Tiago on 23/11/2017.
 */

public class DebugFragment extends Fragment {

    protected static final String TAG = DebugFragment.class.getSimpleName();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isLogLifecycle()) {
            log("onCreate(): " + savedInstanceState);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (isLogLifecycle()) {
            log(getClassName() + "onCreateView()");
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (isLogLifecycle()) {
            log(getClassName() + "onActivityCreated()");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (isLogLifecycle()) {
            log("onStart()");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isLogLifecycle()) {
            log("onResume()");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (isLogLifecycle()) {
            log("onSaveInstanceState(): " + outState);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (isLogLifecycle()) {
            log("onPause()");
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (isLogLifecycle()) {
            log("onStop()");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (isLogLifecycle()) {
            log(getClassName() + "onDetach()");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (isLogLifecycle()) {
            log(getClassName() + "onDestroyView()");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isLogLifecycle()) {
            log("onDestroy()");
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

    protected boolean isLogLifecycle() {
        return false;
    }

}
