package utils;

import lombok.experimental.UtilityClass;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.time.Duration;

@UtilityClass
public class RetrofitUtil {
    private final String defaultBaseURL = PropertiesReader.getProperties().getProperty("defaultBaseURLHW5");

    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();

    public Retrofit getRetrofit() {

        OkHttpClient client = new OkHttpClient().newBuilder()
                .connectTimeout(Duration.ofMinutes(1L))
                .addInterceptor(loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC))
                .build();

        return new Retrofit.Builder()
                .client(client)
                .baseUrl(defaultBaseURL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public Retrofit getRetrofit(String customBaseURL) {

        OkHttpClient client = new OkHttpClient().newBuilder()
                .connectTimeout(Duration.ofMinutes(1L))
                .addInterceptor(loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC))
                .build();

        return new Retrofit.Builder()
                .client(client)
                .baseUrl(customBaseURL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

}
