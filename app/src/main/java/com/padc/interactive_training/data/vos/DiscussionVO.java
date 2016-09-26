package com.padc.interactive_training.data.vos;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * Created by NayLinAung on 9/16/2016.
 */
public class DiscussionVO {

    @SerializedName("discussion_title")
    private String discussionTitle;

    @SerializedName("discussion")
    private String description;

    @SerializedName("user_id")
    private String userId;

    private String postPastTime;

    @SerializedName("post_datetime")
    private String postedTime;

    @SerializedName("like_count")
    private Integer likes;

    @SerializedName("replies")
    private List<ReplyVO> replies;

    public String getTitle() {
        return discussionTitle;
    }

    public void setTitle(String title) {
        this.discussionTitle = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPostPastTime() {
        return postPastTime;
    }

    public void setPostPastTime(String postPastTime) {
        this.postPastTime = postPastTime;
    }

    public String getPostedTime() {
        return postedTime;
    }

    public void setPostedTime(String postedTime) {
        this.postedTime = postedTime;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }
}
