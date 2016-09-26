package com.padc.interactive_training.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.interactive_training.R;
import com.padc.interactive_training.data.vos.ArticleVO;
import com.padc.interactive_training.views.holders.ArticleViewHolder;

import java.util.List;

/**
 * Created by htoomt on 9/18/2016.
 */
public class ArticleAdapter extends RecyclerView.Adapter<ArticleViewHolder>{

    //region variable declaration
    private static final String ACTION_LIKE_BUTTON_CLICK = "action_like_button_click";
    private static final String ACTION_LIKE_IMAGE_CLICK = "action_like_image_click";

    public static final int VIEW_TYPE_DEFAULT = 1;
    public static final int VIEW_TYPE_LOADER = 2;

    private LayoutInflater inflater;
    private List<ArticleVO> mArticleList;

    private Context context;
    private ArticleViewHolder.ControllerArticleItem controllerArticleItem;

    private boolean showLoadingImage = false;
    //endregion

    public ArticleAdapter(List<ArticleVO> mArticleList, Context context, ArticleViewHolder.ControllerArticleItem controllerArticleItem) {
        this.mArticleList = mArticleList;
        this.context = context;
        this.controllerArticleItem = controllerArticleItem;
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_item_articles, parent, false);
        final ArticleViewHolder articleViewHolder = new ArticleViewHolder(view, controllerArticleItem);
        return articleViewHolder;
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        holder.bindData(mArticleList.get(position));
    }

    @Override
    public int getItemCount() {
        return mArticleList.size();
    }
}
