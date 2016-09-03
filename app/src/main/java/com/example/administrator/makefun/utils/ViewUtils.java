package com.example.administrator.makefun.utils;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by sysadminl on 2015/12/1.
 */
public class ViewUtils {
    private static LayoutInflater inflater;

    public static View getViewById(View view, int resId) {
        SparseArray<View> mViews = (SparseArray<View>) view.getTag();
        if (mViews == null) {
            mViews = new SparseArray<View>();
            view.setTag(mViews);
        }
        View child = mViews.get(resId);
        if (child == null) {
            child = view.findViewById(resId);
            mViews.put(resId, child);
        }
        return child;
    }


    public static View inflate(Context context,int res){
        if(inflater==null) {
            inflater = LayoutInflater.from(context);
        }
        return inflater.inflate(res,null);
    }
}
