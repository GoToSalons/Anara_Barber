package com.anara.barber.Apis;

import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RequestResponseManager {

    public static void getSalon() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        JSONObject parameters = new JSONObject();
        try {
            parameters.put("service_id", 1);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Call<String> call = apiInterface.getSalonList(parameters.toString());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NotNull Call<String> call, @NotNull Response<String> response) {

                try {


                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(@NotNull Call<String> call, @NotNull Throwable t) {

                Log.e("tag"," = =  = call error = = = " + t.getMessage());

            }
        });
    }
}
