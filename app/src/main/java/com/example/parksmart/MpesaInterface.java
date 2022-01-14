package com.example.parksmart;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public class  MpesaInterface {

    //https://sandbox.safaricom.co.ke/oauth/v1/generate?grant_type=client_credentials
    @GET("/oauth/v1/generate?grant_type=client_credentials")
    Call<MpesaModel> getAccessToken(
            @Header("Authorization") String basicToken
    ) {
        return null;
    }

    @POST("/mpesa/stkpush/v1/processrequest")
    Call<MpesaResponse> getRequest(
            @Header("Authorization") String basicToken,
            @Body Model lipaNaMpesa
    ) {
        return null;
    }
}
