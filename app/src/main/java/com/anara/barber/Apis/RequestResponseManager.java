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
        Call<String> call = apiInterface.registersalon(parameters.toString());
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

    public static void updateSalonProfile(JSONObject parameters, int requestCode, OnResponseListener onResponseListener) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<String> call = apiInterface.updateSalonProfile(parameters.toString());
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

    public static void updateBarberProfile(JSONObject parameters, int requestCode, OnResponseListener onResponseListener) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<String> call = apiInterface.updateBarberProfile(parameters.toString());
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

    public static void deleteBarber(JSONObject parameters, int requestCode, OnResponseListener onResponseListener) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<String> call = apiInterface.deleteBarber(parameters.toString());
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

    public static void getBarberBooking(JSONObject parameters, int requestCode, OnResponseListener onResponseListener) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<String> call = apiInterface.getBarberBookings(parameters.toString());
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


    public static void addBarber(JSONObject parameters, int requestCode, OnResponseListener onResponseListener) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<String> call = apiInterface.addBarber(parameters.toString());
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

    public static void addBarberSchedule(JSONObject parameters, int requestCode, OnResponseListener onResponseListener) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<String> call = apiInterface.addBarberSchedule(parameters.toString());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NotNull Call<String> call, @NotNull Response<String> response) {

                try {
                    Log.e("tag", " = =  = call schedule n = = = " + response.body());
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

    public static void addBarberScheduleBreak(JSONObject parameters, int requestCode, OnResponseListener onResponseListener) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<String> call = apiInterface.addBarberScheduleBreak(parameters.toString());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NotNull Call<String> call, @NotNull Response<String> response) {

                try {
                    Log.e("tag", " = =  = call break n = = = " + response.body());
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

    public static void getBarberSchedule(JSONObject parameters, int requestCode, OnResponseListener onResponseListener) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<String> call = apiInterface.getBarberSchedule(parameters.toString());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NotNull Call<String> call, @NotNull Response<String> response) {

                try {
                    Log.e("tag", " = =  = call break n = = = " + response.body());
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
        if (requestType == Const.salon_Register_Request) {
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
        } else if (requestType == Const.Delete_Barber_Request) {
            return Parser.getHomePageResponse(response);
        } else if (requestType == Const.Add_Barber_Request) {
            return Parser.getHomePageResponse(response);
        }
        return null;
    }

    public interface OnResponseListener {
        void onResponse(Object response);
    }

}
