package com.r.mindvalley.api;

import java.util.ArrayList;

/**
 * Created by Laukik on 04-Sep-16.
 */
public interface ApiService {

    @GET("raw/wgkJgazE")
    Call<ArrayList<Photo>> getList();
}