package com.example.doanorderfood.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.doanorderfood.model.Staff;
import com.google.gson.Gson;


public class SharePreferenceUtils {

    private static SharedPreferences preferences;
    private SharedPreferences.Editor editor;


    public SharePreferenceUtils(Context context) {
        preferences = context.getSharedPreferences(Const.SHARE_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }


    public static void deleteSave(Context context, String key) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear().commit();
    }

    public static void deleteKeySave(Context context, String key) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(key).commit();
    }

    public static void insertStringData(Context context, String key, String value) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static int getIntData(Context context, String key) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        int r = preferences.getInt(key, 0);
        return r;
    }

    public static void insertIntData(Context context, String key, int value) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static void insertObjDataStaff(Context context, String key, Staff staff) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(staff);
        editor.putString(key, json);
        editor.apply();
    }

    public static Staff getObjData(Context context, String key) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = preferences.getString(key, "");
        Staff staff = gson.fromJson(json, Staff.class);
        // int r = preferences.getInt(key, 0);
        return staff;
    }

    public static boolean getBooleanData(Context context, String key) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        boolean r = preferences.getBoolean(key, false);
        return r;
    }

    public static void insertBooleanData(Context context, String key, boolean value) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static String getStringData(Context context, String key) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String r = preferences.getString(key, "");
        return r;
    }

}
