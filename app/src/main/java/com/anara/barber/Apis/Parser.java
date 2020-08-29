package com.anara.barber.Apis;

import android.util.Log;

import com.anara.barber.Model.BaseRs;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    public static BaseRs getHomePageResponse(String response) {

        try {

            Log.d("tag"," =  response = = " + response);

            Gson gson = new Gson();

            Type type = new TypeToken<BaseRs>() {}.getType();
            return gson.fromJson(response, type);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("tag","= = error in parser  = = " + e.getMessage());
        }

        return null;
    }


}
