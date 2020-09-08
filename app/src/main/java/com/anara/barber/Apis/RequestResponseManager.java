package com.anara.barber.Apis;

import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RequestResponseManager {

    public static void loginBarber(JSONObject parameters, int requestCode, OnResponseListener onResponseListener) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<String> call = apiInterface.loginBarber(parameters.toString());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NotNull Call<String> call, @NotNull Response<String> response) {

                try {
                    Log.e("tag", response.message() + " = =  = call n = = = " + response.body() + " = = = " + response.code());
                    Object object = invokeParser(response.body(), requestCode);
                    onResponseListener.onResponse(object);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NotNull Call<String> call, @NotNull Throwable t) {

                Log.e("tag", " = =  = call error = = = " + t.getMessage());
                onResponseListener.onResponse(null);

            }
        });
    }

    public static void getApiCall(JSONObject parameters, int requestCode, OnResponseListener onResponseListener) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<String> call = apiInterface.registerSaloon(parameters.toString());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NotNull Call<String> call, @NotNull Response<String> response) {

                try {
                    Log.e("tag", response.message() + " = =  = call n = = = " + response.body() + " = = = " + response.code());
                    Object object = invokeParser(response.body(), requestCode);
                    onResponseListener.onResponse(object);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NotNull Call<String> call, @NotNull Throwable t) {

                Log.e("tag", " = =  = call error = = = " + t.getMessage());
                onResponseListener.onResponse(null);

            }
        });
    }

    public static void checkRegister(JSONObject parameters, int requestCode, OnResponseListener onResponseListener) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<String> call = apiInterface.sendMobile(parameters.toString());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NotNull Call<String> call, @NotNull Response<String> response) {

                try {
                    Log.e("tag", " = =  = call n = = = " + response.body());
                    Object object = invokeParser(response.body(), requestCode);
                    onResponseListener.onResponse(object);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NotNull Call<String> call, @NotNull Throwable t) {

                Log.e("tag", " = =  = call error = = = " + t.getMessage());
                onResponseListener.onResponse(null);

            }
        });
    }

    public static void getSalonIncome(JSONObject parameters, int requestCode, OnResponseListener onResponseListener) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<String> call = apiInterface.getSalonIncome(parameters.toString());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NotNull Call<String> call, @NotNull Response<String> response) {

                try {
                    Log.e("tag", " = =  = call n = = = " + response.body());
                    Object object = invokeParser(response.body(), requestCode);
                    onResponseListener.onResponse(object);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(@NotNull Call<String> call, @NotNull Throwable t) {

                Log.e("tag", " = =  = call error = = = " + t.getMessage());
                onResponseListener.onResponse(null);

            }
        });
    }

    public static void getBarberIncome(JSONObject parameters, int requestCode, OnResponseListener onResponseListener) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<String> call = apiInterface.getBarberIncome(parameters.toString());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NotNull Call<String> call, @NotNull Response<String> response) {

                try {
                    Log.e("tag", " = =  = call n = = = " + response.body());
                    Object object = invokeParser(response.body(), requestCode);
                    onResponseListener.onResponse(object);
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(@NotNull Call<String> call, @NotNull Throwable t) {

                Log.e("tag", " = =  = call error = = = " + t.getMessage());
                onResponseListener.onResponse(null);

            }
        });
    }

    public static Object invokeParser(String response, int requestType) {
        if (requestType == Const.Saloon_Register_Request) {
            return Parser.getHomePageResponse(response);
        } else if (requestType == Const.Check_Register_Request) {
            return Parser.getHomePageResponse(response);
        } else if (requestType == Const.Check_Barber_Register_Request) {
            return Parser.getHomePageResponse(response);
        } else if (requestType == Const.Salon_Income_Request) {
            return Parser.getHomePageResponse(response);
        } else if (requestType == Const.Barber_Income_Request) {
            return Parser.getHomePageResponse(response);
        } else if (requestType == Const.Login_Barber_Request) {
            return Parser.getHomePageResponse(response);
        }
        return null;
    }

    public interface OnResponseListener {
        void onResponse(Object response);
    }

}
