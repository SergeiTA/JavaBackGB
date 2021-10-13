package service;

import modelsProductController.ErrorInfo;
import modelsProductController.Product;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.ArrayList;


public interface ProductInterface {

    @GET("products")
    Call<ArrayList<Product>> getAllProducts();

    @GET("products/{id}")
    Call<Product> getProduct(@Path("id") Integer id);

    @POST("products")
    Call<Product> createProduct(@Body Product createProductRequest);

    @POST("products")
    Call<ErrorInfo> createProductWithId(@Body Product createProductRequest);

    @PUT("products")
    Call<Product> modifyProduct(@Body Product createProductRequest);

    @PUT("products")
    Call<ErrorInfo> modifyProductWithNULLId(@Body Product createProductRequest);

    @DELETE("products/{id}")
    Call<ResponseBody> deleteProduct(@Path("id") int id);

    @GET("products")
    Call<ResponseBody> getAllProductsDefaultResponse();



}
