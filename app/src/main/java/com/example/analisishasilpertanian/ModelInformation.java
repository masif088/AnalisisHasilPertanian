package com.example.analisishasilpertanian;

import android.os.Parcel;
import android.os.Parcelable;

public class ModelInformation implements Parcelable {
    public static final Creator<ModelInformation> CREATOR = new Creator<ModelInformation>() {
        @Override
        public ModelInformation createFromParcel(Parcel source) {
            return new ModelInformation(source);
        }

        @Override
        public ModelInformation[] newArray(int size) {
            return new ModelInformation[size];
        }
    };
    public static String MODELINFORMATION = "MODELINFORMATION";
    private String id, name, information, image;

    public ModelInformation(String id, String name, String information, String image) {
        this.id = id;
        this.name = name;
        this.information = information;
        this.image = image;
    }

    public ModelInformation() {
    }

    protected ModelInformation(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.information = in.readString();
        this.image = in.readString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.information);
        dest.writeString(this.image);
    }
}
