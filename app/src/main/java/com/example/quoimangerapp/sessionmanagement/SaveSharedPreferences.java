package com.example.quoimangerapp.sessionmanagement;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import static com.example.quoimangerapp.sessionmanagement.PreferencesUtility.LOGGED_IN_PREF;
import static com.example.quoimangerapp.sessionmanagement.PreferencesUtility.LOGGED_IN_USER;

public class SaveSharedPreferences {

    static SharedPreferences getPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * Set the Login Status
     * @param context
     * @param loggedIn
     */
    public static void setLoggedIn(Context context, boolean loggedIn) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.clear();
        editor.putBoolean(LOGGED_IN_PREF, loggedIn);
        editor.commit();
    }

    /**
     * Get the Login Status
     * @param context
     * @return boolean: login status
     */
    public static boolean getLoggedStatus(Context context) {
        return getPreferences(context).getBoolean(LOGGED_IN_PREF, false);
    }

    /**
     * Set the Login Status
     * @param context
     * @param id
     */
    public static void setLoggedInUserId(Context context, int id) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.clear();
        editor.putInt(LOGGED_IN_USER, id);
        editor.commit();
    }

    /**
     * Get the Login Status
     * @param context
     * @return boolean: login status
     */
    public static int getLoggedInUserId(Context context) {
        return getPreferences(context).getInt(LOGGED_IN_USER, -1);
    }
}