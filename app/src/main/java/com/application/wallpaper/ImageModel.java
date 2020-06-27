package com.application.wallpaper;

import android.os.Parcel;
import android.os.Parcelable;

public class ImageModel implements Parcelable {
    private String image_url;

    public ImageModel() {
    }

    public ImageModel(String image_url) {
        this.image_url = image_url;
    }

    protected ImageModel(Parcel in) {
        image_url = in.readString();
    }

    public static final Creator<ImageModel> CREATOR = new Creator<ImageModel>() {
        @Override
        public ImageModel createFromParcel(Parcel in) {
            return new ImageModel(in);
        }

        @Override
        public ImageModel[] newArray(int size) {
            return new ImageModel[size];
        }
    };

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(image_url);
    }
}
