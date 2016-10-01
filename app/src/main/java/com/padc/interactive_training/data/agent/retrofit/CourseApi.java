package com.padc.interactive_training.data.agent.retrofit;

import com.padc.interactive_training.data.responses.ArticleListResponse;
import com.padc.interactive_training.utils.InteractiveTrainingConstants;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by htoomt on 10/1/2016.
 */

public interface CourseApi {

    @FormUrlEncoded
    @POST(InteractiveTrainingConstants.API_GET_ARTICLE_LIST)
    Call<ArticleListResponse> loadArticles(
            @Field(InteractiveTrainingConstants.PARAM_ACCESS_TOKEN) String param);

}
