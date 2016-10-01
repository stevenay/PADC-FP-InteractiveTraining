package com.padc.interactive_training.data.agent.retrofit;

import android.util.Log;

import com.padc.interactive_training.InteractiveTrainingApp;
import com.padc.interactive_training.data.agent.CourseDataAgent;
import com.padc.interactive_training.data.models.ArticleModel;
import com.padc.interactive_training.data.responses.ArticleListResponse;
import com.padc.interactive_training.utils.CommonInstances;
import com.padc.interactive_training.utils.InteractiveTrainingConstants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by htoomt on 10/1/2016.
 */


public class RetrofitDataAgent implements CourseDataAgent {
    private static RetrofitDataAgent objInstance;

    private final CourseApi theApi;

    private RetrofitDataAgent() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(InteractiveTrainingConstants.COURSE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(CommonInstances.getGsonInstance()))
                .client(okHttpClient)
                .build();

        theApi = retrofit.create(CourseApi.class);
    }

    public static RetrofitDataAgent getInstance() {
        if (objInstance == null) {
            objInstance = new RetrofitDataAgent();
        }
        return objInstance;
    }

    @Override
    public void loadArticles() {
        Call<ArticleListResponse> loadCourseCall = theApi.loadArticles(InteractiveTrainingConstants.ACCESS_TOKEN);
        loadCourseCall.enqueue(new Callback<ArticleListResponse>() {
            @Override
            public void onResponse(Call<ArticleListResponse> call, Response<ArticleListResponse> response) {
                Log.d(InteractiveTrainingApp.TAG, "OnResponse");
                ArticleListResponse courseListResponse = response.body();
                if (courseListResponse == null) {
                    ArticleModel.getInstance().notifyErrorInLoadingArticles(response.message());
                } else {
                    ArticleModel.getInstance().notifyArticlesLoaded(courseListResponse.getArticleList());
                }
            }

            @Override
            public void onFailure(Call<ArticleListResponse> call, Throwable throwable) {
                Log.d(InteractiveTrainingApp.TAG, "OnFailure " + throwable.getMessage());
                ArticleModel.getInstance().notifyErrorInLoadingArticles(throwable.getMessage());
            }
        });
    }
}
