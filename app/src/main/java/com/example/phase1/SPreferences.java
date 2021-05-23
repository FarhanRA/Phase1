package com.example.phase1;

import android.content.Context;
import android.content.SharedPreferences;

public class SPreferences {
    protected final String USERNAME = "userName";

    private Context context;
    private SharedPreferences sp;

    public SPreferences(Context context){
        this.context = context;
        sp = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
    }


    //------username------
    public void setUserName(String userName){
        sp.edit().putString(USERNAME, userName).commit();
    }
    public String getUserName(){
        return sp.getString(USERNAME, "n/a");
    }
    //-----------------


}
