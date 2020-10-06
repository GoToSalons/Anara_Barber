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
    Call<String> registersalon(@Body String serviceId);

    @Headers({"Content-type: application/json"})
    @POST("/admin/api/saloons/edit-profile")
    Call<String> updateSalonProfile(@Body String serviceId);

    @Headers({"Content-type: application/json"})
    @POST("/admin/api/barbers/edit-profile")
    Call<String> updateBarberProfile(@Body String serviceId);

    @Headers({"Content-type: application/json"})
    @POST("/admin/api/saloons/login")
    Call<String> sendMobile(@Body String mobile);

    @Headers({"Content-type: application/json"})
    @POST("/admin/api/saloons/saloon-income")
    Call<String> getSalonIncome(@Body String mobile);

    @Headers({"Content-type: application/json"})
    @POST("/admin/api/barbers/barber-income")
    Call<String> getBarberIncome(@Body String mobile);

    @Headers({"Content-type: application/json"})
    @POST("/admin/api/saloons/barber/save")
    Call<String> addBarber(@Body String mobile);

    @Headers({"Content-type: application/json"})
    @POST("/admin/api/saloons/barber/delete")
    Call<String> deleteBarber(@Body String mobile);


    @Headers({"Content-type: application/json"})
    @POST("/admin/api/barbers/booking-list")
    Call<String> getBarberBookings(@Body String mobile);

    @Headers({"Content-type: application/json"})
    @POST("/admin/api/barbers/add-schedule")
    Call<String> addBarberSchedule(@Body String mobile);


    @Headers({"Content-type: application/json"})
    @POST("/admin/api/barbers/add-break")
    Call<String> addBarberScheduleBreak(@Body String mobile);

    @Headers({"Content-type: application/json"})
    @POST("/admin/api/barbers/list-schedule")
    Call<String> getBarberSchedule(@Body String mobile);


}
