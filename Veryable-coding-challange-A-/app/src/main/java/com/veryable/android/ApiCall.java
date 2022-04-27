package com.veryable.android;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiCall {
    @GET("veryable.json")
    Call<List<BankDetails>> getBankDetails();
}
