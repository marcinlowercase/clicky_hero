package com.example.treytontheoassign;

import android.widget.ImageView;

public class Utils {

    public static void setImageBaseOnValue(ImageView imageView, Integer value) {
        switch (value) {
            case 0:
                imageView.setImageResource(R.drawable.up);
                break;
            case 1:
                imageView.setImageResource(R.drawable.right);
                break;
            case 2:
                imageView.setImageResource(R.drawable.down);
                break;
            case 3:
                imageView.setImageResource(R.drawable.left);
                break;
        }
    }


    public static void setImageBaseOnResult(ImageView imageView, boolean result) {
        if (result) {
            imageView.setImageResource(android.R.drawable.btn_star_big_on);
        } else {
            imageView.setImageResource(android.R.drawable.btn_star_big_off);
        }
    }

}
