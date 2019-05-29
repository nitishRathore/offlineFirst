package com.project.fptechscience.remote;

import com.google.gson.Gson;
import com.project.fptechscience.utils.AppConstants;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Nitish Singh on 2019-05-30.
 */
public class NetworkClient {


    private static Retrofit getRetrofitInstance() {

        return new Retrofit.Builder().baseUrl(AppConstants.BASE_URL).addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).client(getHttpClient()).build();
    }


    private static OkHttpClient getHttpClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        return new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();
    }


    public static ApiService getApiService() {
       return getRetrofitInstance().create(ApiService.class);
    }
}
