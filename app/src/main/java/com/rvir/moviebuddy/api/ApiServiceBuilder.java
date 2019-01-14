package com.rvir.moviebuddy.api;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServiceBuilder implements ApiConstants {

  public static MovieDbService build() {
    OkHttpClient.Builder builder = new OkHttpClient.Builder();
    builder.addInterceptor(new Interceptor() {
      @Override
      public Response intercept(Chain chain) throws IOException {
        //Dodamo api key v vsak api request
        Request original = chain.request();
        HttpUrl originalHttpUrl = original.url();
        HttpUrl url = originalHttpUrl.newBuilder()
            .addQueryParameter("api_key", API_KEY)
            .build();
        Request.Builder requestBuilder = original.newBuilder()
            .url(url);
        Request request = requestBuilder.build();
        return chain.proceed(request);
      }
    });
    builder.addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
      @Override
      public void log(String message) {
        Log.d("API", message);
      }
    }).setLevel(HttpLoggingInterceptor.Level.BODY));

    builder.retryOnConnectionFailure(true);
    builder.connectTimeout(10, TimeUnit.SECONDS); // Establishing connection timeout
    builder.readTimeout(30, TimeUnit.SECONDS); // Socket sending timeout


    OkHttpClient client = builder.build();
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build();
    return retrofit.create(MovieDbService.class);
  }

}
