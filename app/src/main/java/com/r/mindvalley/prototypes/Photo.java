package com.r.mindvalley.prototypes;

import android.graphics.Color;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.ArrayList;

/**
 * Created by Laukik on 04-Sep-16.
 */
@Parcel
public class Photo {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("created_at")
    @Expose
    private String createdDate; // // FIXME: 7/9/16 fix it later

    @SerializedName("width")
    @Expose
    private int width;

    @SerializedName("height")
    @Expose
    private int height;

    @SerializedName("likes")
    @Expose
    private int likes;

    @SerializedName("liked_by_user")
    @Expose
    private boolean isLiked;

    @SerializedName("user")
    @Expose
    private User user;

    @SerializedName("color")
    @Expose
    private String color;

    @SerializedName("categories")
    @Expose
    private ArrayList<Category> categories = new ArrayList<>();

    @SerializedName("urls")
    @Expose
    private Urls urls;

    public Photo() { // empty constructor needed by Parcel
    }

    // Shortcuts
    public int getColorInt() {
        return Color.parseColor(this.color);
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id='" + id + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", likes=" + likes +
                ", isLiked=" + isLiked +
                ", user=" + user +
                ", color='" + color + '\'' +
                ", categories=" + categories +
                ", urls=" + urls +
                '}';
    }

    // GETTERS & SETTERS
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    public Urls getUrls() {
        return urls;
    }

    public void setUrls(Urls urls) {
        this.urls = urls;
    }
}
