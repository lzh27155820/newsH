package com.xyz.newsh.utlis;

import android.content.Context;
import android.content.SharedPreferences;

public class CacheUitls {


    private static SharedPreferences sharedPreferences;



    public static Boolean getBoolean(Context context,String key){


        if(sharedPreferences==null){

            sharedPreferences=context.getSharedPreferences("config",Context.MODE_PRIVATE);
        }

        return sharedPreferences.getBoolean(key,false);
    }

    public static Boolean putBoolean(String key){

        try {

            SharedPreferences.Editor editor = sharedPreferences.edit();
             editor.putBoolean(key, true);
             editor.commit();
             boolean aBoolean = sharedPreferences.getBoolean(key, false);
            System.out.println(aBoolean);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public static void putString(String key, String value) {

        sharedPreferences.edit().putString(key, value).commit();
    }

    public static String getString(String key) {

        return sharedPreferences.getString(key,"");
    }

}
