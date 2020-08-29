package com.anara.barber.Apis;

import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RequestResponseManager {

    public static void getApiCall(JSONObject parameters, int requestCode,OnResponseListener onResponseListener) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<String> call = apiInterface.registerSaloon(parameters.toString());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NotNull Call<String> call, @NotNull Response<String> response) {

                try {
                    Log.e("tag"," = =  = call n = = = " + response.body());
                    Object object = invokeParser(response.body(), requestCode);
                    onResponseListener.onResponse(object);
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(@NotNull Call<String> call, @NotNull Throwable t) {

                Log.e("tag"," = =  = call error = = = " + t.getMessage());
                onResponseListener.onResponse(null);

            }
        });
    }

    public static Object invokeParser(String response, int requestType) {
        if (requestType == Const.Saloon_Register_Request) {
            return Parser.getHomePageResponse(response);
        }
        return null;
    }

    public interface OnResponseListener {
        void onResponse(Object response);
    }

}
