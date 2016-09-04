package com.r.mindvalley.prototypes;

/**
 * Created by Laukik on 04-Sep-16.
 */
@Parcel
public class ProfileImage {

    @SerializedName("small")
    @Expose
    private String small;

    @SerializedName("medium")
    @Expose
    private String medium;

    @SerializedName("large")
    @Expose
    private String large;

    public ProfileImage() { // needed via Parcel
    }

    // Shortcuts & Circumstances
    @Override
    public String toString() {
        return "ProfileImage{" +
                "small='" + small + '\'' +
                ", medium='" + medium + '\'' +
                ", large='" + large + '\'' +
                '}';
    }

    // GETTERS & SETTERS
    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }
}
