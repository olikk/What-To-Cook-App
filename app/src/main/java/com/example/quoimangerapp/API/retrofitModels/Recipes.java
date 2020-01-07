package com.example.quoimangerapp.API.retrofitModels;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Recipes implements Parcelable{
    @SerializedName("id")
    private Integer id;

    @SerializedName("image")
    private String image;

    @SerializedName("title")
    private String title;

    private Recipes(Parcel parcel){
        image = parcel.readString();
        title = parcel.readString();
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Recipes{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", titile=" + title +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(image);
        dest.writeString(title);
    }

    public static final Parcelable.Creator<Recipes> CREATOR = new
            Parcelable.Creator<Recipes>() {
                public Recipes createFromParcel(Parcel in) {
                    return new Recipes(in);
                }

                public Recipes[] newArray(int size) {
                    return new Recipes[size];
                }};
}
