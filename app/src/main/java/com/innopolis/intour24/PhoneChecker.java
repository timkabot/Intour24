package com.innopolis.intour24;

import android.util.Log;

/**
 * Created by ekaterina on 6/20/17.
 */

public class PhoneChecker {

    private static final String TAG = "PCDebug";

    /**
     * Only for russian phones
     * TODO rewrite
     * @param phone
     * @return true if everything is alright
     */
    public static boolean checkPhone(String phone){
        Log.d(TAG, "Checking phone " + phone);
        boolean flag = true;
        if(phone == null) {
            Log.d(TAG, "Phone is null");
            return false;
        }
        phone = phone.replaceAll("-","");
        if (!phone.split("")[1].equals("7")){//for some reason from [1]
            Log.d(TAG, "Phone doesn't start at 7 " + "[0] " + phone.split("")[0] + phone.split("")[1]);
            return false;
        }else if(phone.length() != 11) {
            Log.d(TAG, "Phone length is not 11");
            return false;
        }
        Log.d(TAG, "Phone was " + flag);
        return flag;
    }
}
