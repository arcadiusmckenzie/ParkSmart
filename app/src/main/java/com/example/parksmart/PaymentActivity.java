package com.example.parksmart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.GsonBuilder;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Base64;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PaymentActivity extends AppCompatActivity implements View.OnClickListener{

    Button mbtn;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        mbtn = findViewById(R.id.mpesa_payment);
        mbtn.setOnClickListener(this);
        editText = findViewById(R.id.phone_number);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.mpesa_payment:
                Mpesa();
                break;
        }
    }

    private void Mpesa() {

        String phoneNumber = editText.getText().toString();
        if (editText.equals("")){
            editText.setError("Provide Mobile number");
            editText.requestFocus();
            return;
        }
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://sandbox.safaricom.co.ke/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create())).build();
        //Instance of Interface
        Interface mpesaInterface = retrofit.create(Interface.class);
        String basicToken = "Basic emFmak4ycEpuN3dBUkdndmp2eWlFeXNYNDcwM2ZiU1E6NmJ5SzgyY3hBWEVCTGFWWA==";

        Call<MpesaModel> call = mpesaInterface.getAccessToken(basicToken);
        call.enqueue(new Callback<MpesaModel>() {
            @Override
            public void onResponse(Call<MpesaModel> call, Response<MpesaModel> response) {
                if (response.code() != 200){
                    Toast.makeText(getApplicationContext(), "Not working", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(getApplicationContext(), "Code"+response.body().getAccess_token(), Toast.LENGTH_SHORT).show();
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                String businessCode = "174379";
                String code = String.valueOf(businessCode);
                String passKey = "bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919";
                String amt = "100";

                String phone = "254"+phoneNumber.substring(1,10);
                SimpleDateFormat df = new SimpleDateFormat("YYYYMMddhhmmss");
                String timme = df.format(timestamp);
                String combine = code+passKey+timme;
                String transact = "CustomerPayBillOnline";
                String test = "test";
                String url = "https://7548-154-123-211-72.ngrok.io/api/mpesa/callback";
                String bas64 = Base64.getEncoder().encodeToString(combine.getBytes()).toString();

                Model lipaNaMpesa = new Model(
                        code,
                        bas64,
                        timme,
                        transact,
                        amt,
                        phone,
                        code,
                        phone,
                        url,
                        test,
                        test

                );
                Call<MpesaResponse> call1 = mpesaInterface.getRequest("Bearer " +response.body().getAccess_token(), lipaNaMpesa);
                call1.enqueue(new Callback<MpesaResponse>() {
                    @Override
                    public void onResponse(Call<MpesaResponse> call, Response<MpesaResponse> response) {
                        if (response.code() != 200){
                            Toast.makeText(getApplicationContext(), "Not working", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(getApplicationContext(), "Code"+phone, Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<MpesaResponse> call, Throwable t) {

                    }
                });
            }

            @Override
            public void onFailure(Call<MpesaModel> call, Throwable t) {

            }
        });

    }
}