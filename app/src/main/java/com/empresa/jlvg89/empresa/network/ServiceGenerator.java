package com.empresa.jlvg89.empresa.network;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jlvg89 on 02/03/18.
 */

public class ServiceGenerator {
    private static final String API_BASE_URL = "http://54.94.179.135:8090/api/v1/";

    public static <S> S createService(Class<S> serviceClass, final HashMap<String, String> headers){
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                Request.Builder request = original.newBuilder();

                if(headers != null){
                    Iterator it = headers.entrySet().iterator();
                    while (it.hasNext()){
                        Map.Entry<String, String> pair = (Map.Entry) it.next();
                        request.addHeader(pair.getKey(), pair.getValue());
                        it.remove();
                    }
                }
                request.method(original.method(), original.body());

                return chain.proceed(request.build());
            }
        });

        OkHttpClient client = httpClient.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit.create(serviceClass);
    }
}
