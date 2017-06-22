package com.rw.loadingdialog;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.TypedValue;

/**
 * Created by ravindu on 21/06/17.
 */

class Utils
{
    /**
     * Gets the current accent color
     * @param context calling context
     * @return accent color or -1
     */
    static int getAccentColor(Context context) {
        TypedValue typedValue = new TypedValue();

        TypedArray a = context.obtainStyledAttributes(typedValue.data, new int[] { R.attr.colorAccent });
        int color = a.getColor(0, -1);

        a.recycle();

        return color;
    }
}
