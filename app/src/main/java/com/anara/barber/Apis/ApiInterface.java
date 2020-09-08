package com.anara.barber.Apis;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {

    @Headers({"Content-type: application/json"})
    @POST("/admin/api/barbers/login")
    Call<String> loginBarber(@Body String serviceId);

    @Headers({"Content-type: application/json"})
    @POST("/admin/api/saloons/register")
    Call<String> registerSaloon(@Body String serviceId);

    @Headers({"Content-type: application/json"})
    @POST("/admin/api/saloons/login")
    Call<String> sendMobile(@Body String mobile);

    @Headers({"Content-type: application/json"})
    @POST("/admin/api/saloons/saloon-income")
    Call<String> getSalonIncome(@Body String mobile);

    @Headers({"Content-type: application/json"})
    @POST("/admin/api/barbers/barber-income")
    Call<String> getBarberIncome(@Body String mobile);


}
