package com.example.quanlyquanan.api;

import com.example.quanlyquanan.model.Bill;
import com.example.quanlyquanan.model.MyDate;
import com.example.quanlyquanan.model.UpdateFood;
import com.example.quanlyquanan.response.ResponseAmount;
import com.example.quanlyquanan.response.ResponseBill;
import com.example.quanlyquanan.response.ResponseBillById;
import com.example.quanlyquanan.response.ResponseBillInfoById;
import com.example.quanlyquanan.response.ResponseFoodById;
import com.example.quanlyquanan.setting.MyApplication;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BillApi {

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    BillApi billApi = new Retrofit.Builder()
            .baseUrl(MyApplication.BASE_API_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(BillApi.class);

    @GET("/bills")
    Call<ResponseBill> getBills();

    @GET("/bills/{id}")
    Call<ResponseBillById> getBillById(@Path("id") String id);

    @FormUrlEncoded
    @POST("/bills/")
    Call<ResponseBillById> createNewBill( @Field("timeCheckIn") String timeCheckIn,
                                          @Field("table") String table,
                                          @Field("seller") String seller);

    @GET("/bills/date")
    Call<ResponseAmount> getBillByDate(
            @Query("month") String month,
            @Query("year") String year
    );

    @PATCH("/bills/{id}")
    Call<ResponseBillById> thanhtoan(@Path("id") String id, @Body Bill bill);

}
