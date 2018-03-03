package com.empresa.jlvg89.empresa.network;

import com.empresa.jlvg89.empresa.models.Enterprise;
import com.empresa.jlvg89.empresa.models.QueryResult;
import com.empresa.jlvg89.empresa.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by jlvg89 on 02/03/18.
 */

public interface RetrofitService {
    @FormUrlEncoded
    @POST("users/auth/sign_in")
    Call<User> requestLogin(@Field("email") String email,
                            @Field("password") String password);

    @GET("enterprises")
    Call<QueryResult> fetchEnterprisesByName(@Query("name") String enterprise);
}
