package com.zxy.query;

public class BlogQuery {
    //post title
    private String title;
    //post category id
    private Long typeId;
    //whether or not a blog post is recommended
    private boolean recommend;

    //class constructor
    public BlogQuery() {
    }

    //getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }
}
