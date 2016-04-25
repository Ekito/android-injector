package fr.ekito.injector.demoapp.di;

import fr.ekito.injector.Module;
import fr.ekito.injector.demoapp.ws.GitHubService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by arnaud on 25/04/2016.
 */
public class MyModule extends Module {
    @Override
    public void load() {
        OkHttpClient httpClient = httpClient();

        provide(httpClient, OkHttpClient.class);
        provide(buildWSStack(httpClient), GitHubService.class);
    }

    private OkHttpClient httpClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
        return httpClient;
    }

    private GitHubService buildWSStack(OkHttpClient httpClient) {

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
