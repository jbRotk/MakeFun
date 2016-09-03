package com.example.administrator.makefun.utils;

import android.content.Context;

/**
 * 全局静态参数集合
 */

public class Global {
	public static Context mContext;

	public synchronized static void setApplicationContext(Context context) {
		mContext = context;
	}

	public static Context getApplicationContext() {
		return mContext;
	}
}
