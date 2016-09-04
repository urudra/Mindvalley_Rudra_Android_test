package com.r.mindvalley.prototypes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by Laukik on 04-Sep-16.
 */
@Parcel
public class Urls {

    @SerializedName("raw")
    @Expose
    private String raw;

    @SerializedName("full")
    @Expose
    private String full;

    @SerializedName("regular")
    @Expose
    private String regular;

    @SerializedName("small")
    @Expose
    private String small;

    @SerializedName("thumb")
    @Expose
    private String thumb;

    public Urls() { // needed via Parcel
    }

    @Override
    public String toString() {
        return "Urls{" +
                "raw='" + raw + '\'' +
                ", full='" + full + '\'' +
                ", regular='" + regular + '\'' +
                ", small='" + small + '\'' +
                ", thumb='" + thumb + '\'' +
                '}';
    }

    // GETTERS & SETTERS
    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public String getFull() {
        return full;
    }

    public void setFull(String full) {
        this.full = full;
    }

    public String getRegular() {
        return regular;
    }

    public void setRegular(String regular) {
        this.regular = regular;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }
}
