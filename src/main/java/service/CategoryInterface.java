package service;

import modelsProductController.Category;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CategoryInterface {

    @GET("categories/{id}")
    Call<Category> getCategory(@Path("id") Integer id);

}
