package fr.ekito.injector.web;

import fr.ekito.injector.Module;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by arnaud on 26/04/2016.
 */
public class WebModule extends Module {
    @Override
    public void load() {
        provide(githubWS(), GitHubService.class);
    }

    private GitHubService githubWS() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .client(httpClient)
                .addConverterFactory(gsonConverterFactory)
                .build();

        GitHubService service = retrofit.create(GitHubService.class);
        return service;
    }
}
