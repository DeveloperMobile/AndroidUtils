package com.codigosandroid.utils.utils;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.codigosandroid.utils.R;
import com.google.android.material.navigation.NavigationView;

/**
 * Created by Tiago on 17/11/2017.
 */

public class NavDrawerUtil {

    public static void setHeaderValues(NavigationView navDrawerView, int imgUserPhotoId,
                                       String navUserName, String navUserEmail) {
        View headerView = navDrawerView.getHeaderView(0);

        if (headerView != null) {
            TextView tNome = (TextView) headerView.findViewById(R.id.tNome);
            TextView tEmail = (TextView) headerView.findViewById(R.id.tEmail);
            ImageView imgUserPhoto = (ImageView) headerView.findViewById(R.id.imgUserPhoto);

            if (imgUserPhoto != null) {
                imgUserPhoto.setImageResource(imgUserPhotoId);
            }

            if (tNome != null && tEmail != null) {
                tNome.setText(navUserName);

                if (navUserEmail == null || navUserEmail.equals("")) {
                    tEmail.setVisibility(View.INVISIBLE);
                } else {
                    tEmail.setText(navUserEmail);
                }
            }
        }
    }
}
