package com.movies.movies.api;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by user on 3/3/2018.
 */

public class FullRestAdapter {

    static ArrayList<NameValuePair> params;

    public static api_inerface createAPI(final String fullUrl) {

        Log.d("class", "FullUrlRestAdapter");
        Log.d("fullUrl", fullUrl);

        params = new ArrayList<>();
        setParams(fullUrl);

        // Define the interceptor, add authentication headers
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                HttpUrl.Builder builder = chain.request().url().newBuilder();
                for (NameValuePair entry : params) {
                    builder.addQueryParameter(entry.getName(), entry.getValue());
                }

                Request newRequest = chain.request()
                        .newBuilder()
                        .url(builder.build())
                        .header("Accept", "application/json")
                        .build();

                return chain.proceed(newRequest);
            }
        };

        HttpLoggingInterceptor Logging = new HttpLoggingInterceptor();
        Logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(Logging).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(fullUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit.create(api_inerface.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.FROYO)
    public static ArrayList<NameValuePair> setParams(String endpoint) {
        final String uri = api_inerface.BasicUrl + endpoint;


        params = new ArrayList<>();
        params.add(new BasicNameValuePair("api_key", api_inerface.Api_key));

        Collections.sort(params, new SortParams());
        String encodedParams = URLEncodedUtils.format(params, "utf-8");
        Log.d("full encodedParamString", encodedParams);

        return params;
    }

    static class SortParams implements Comparator<NameValuePair> {

        @Override
        public int compare(NameValuePair nameValuePair1, NameValuePair nameValuePair2) {
            return nameValuePair1.getName().compareTo(nameValuePair2.getName());
        }
    }
}
