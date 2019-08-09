package com.thangnc.hdgirlwallpaper.api;

import com.thangnc.hdgirlwallpaper.model.category.Category;
import com.thangnc.hdgirlwallpaper.model.latest.Latest;
import com.thangnc.hdgirlwallpaper.model.photo.Photo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("posts?_embed")
    Call<List<Latest>> getLastest();

    @GET("media")
    Call<List<Photo>> getPhoto(@Query("page")int page, @Query("per_page")int per_page, @Query("parent") String parent);

    @GET("categories")
    Call<List<Category>> getCategory(@Query("page")int page, @Query("per_page")int per_page);
}
