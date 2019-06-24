package com.yhcxxt.wanandroid.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.Toast;

import com.yhcxxt.wanandroid.R;
import com.yhcxxt.wanandroid.dialog.MyDialog;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sks on 2015/9/16.
 */
public class Utils {


    public static final int NO_NETWORK_STATE = 0;     //无网络
    public static final int WIFI_STATE = 1;     //wifi


    /*
     * 取得应用的版本号,就是哪个版本,
     */
    public static String longVersionName(Context context) {
        String mVersion = null;
        if (mVersion == null) {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi;
            try {
                pi = pm.getPackageInfo(context.getPackageName(), 0);
                mVersion = pi.versionName;

            } catch (NameNotFoundException e) {
                mVersion = ""; // failed, ignored
            }
        }
        return mVersion;
    }

    /*
     * 取得应用的版本号,就是修改次.
     */
    public static int longVersionCode(Context context) {
        int mVersionCode = 0;
        if (mVersionCode == 0) {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi;
            try {
                pi = pm.getPackageInfo(context.getPackageName(), 0);
                mVersionCode = pi.versionCode;

            } catch (NameNotFoundException e) {
                mVersionCode = 0; // failed, ignored
            }
        }
        return mVersionCode;
    }

    //使用Toast
    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }


    //客户端认证加密字符串
    public static String AppMD5String(Context context, String model, String action) {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        try {
            date = df.parse(df.format(new Date()));
        } catch (ParseException e) {
        }
        ;
        long time = date.getTime();
//        long timecurrentTimeMillis = System.currentTimeMillis();
        return md5(model + action + time + "99-k");
    }

    //字符串md5加密
    public static String md5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }

    /**
     * 判断当前是否有可用的网络以及网络类型 0：无网络 1：WIFI 2：CMWAP 3：CMNET
     *
     * @param context
     * @return
     */
    public static int isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            return 0;
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        NetworkInfo netWorkInfo = info[i];
                        if (netWorkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                            return 1;
                        } else if (netWorkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                            String extraInfo = netWorkInfo.getExtraInfo();
                            if ("cmwap".equalsIgnoreCase(extraInfo)
                                    || "cmwap:gsm".equalsIgnoreCase(extraInfo)) {
                                return 2;
                            }
                            return 3;
                        }
                    }
                }
            }
/*
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWifi = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            NetworkInfo mMobile = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (mWifi != null){
                return 1;
            }else if (mMobile != null){
                return 2;
            }
*/

        }
        return 0;

    }
    //--------------------------------------------------------

    /**
     * 是否有网络连接
     *
     * @param paramContext
     * @return
     */
    public static boolean hasNetwork(Context paramContext) {
        try {
            ConnectivityManager localConnectivityManager = (ConnectivityManager) paramContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            @SuppressLint("MissingPermission") NetworkInfo localNetworkInfo = localConnectivityManager.getActiveNetworkInfo();
            if ((localNetworkInfo != null) && (localNetworkInfo.isAvailable()))
                return true;
        } catch (Throwable localThrowable) {
            localThrowable.printStackTrace();
        }
        return false;
    }

    /**
     * {@link android.Manifest.permission#ACCESS_NETWORK_STATE}.
     *
     * @param context
     * @return
     */
    public static boolean isWifi(Context context) {
        ConnectivityManager connectMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        @SuppressLint("MissingPermission") NetworkInfo info = connectMgr.getActiveNetworkInfo();
        if (info == null)
            return false;
        return info.getType() == ConnectivityManager.TYPE_WIFI;
    }


    //--------------------------------------------------------

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale - 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    //时间戳转时间
    public static String time(Context context, long s) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date(s * 1000));
        return date;
    }

    //时间戳转时间
    public static String time2(Context context, long s) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date(s * 1000));
        return date;
    }

    private static MyDialog myDialog;

    //启动自定义dialog
    public static void showDialog(Activity context, String title, String content, String leftText, String rightText,
                                  View.OnClickListener onClickListener) {
        myDialog = new MyDialog(context, R.style.my_dialog, title, content, leftText, rightText, onClickListener);
        myDialog.setCanceledOnTouchOutside(false);
        myDialog.show();
    }

    //销毁dialog
    public static void dismissDialog() {
        if (myDialog.isShowing())
            myDialog.dismiss();
    }
}