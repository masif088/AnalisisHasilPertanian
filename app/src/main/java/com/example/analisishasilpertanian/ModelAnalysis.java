package com.example.analisishasilpertanian;

import android.os.Parcel;
import android.os.Parcelable;

public class ModelAnalysis implements Parcelable {
    public static final Creator<ModelAnalysis> CREATOR = new Creator<ModelAnalysis>() {
        @Override
        public ModelAnalysis createFromParcel(Parcel source) {
            return new ModelAnalysis(source);
        }

        @Override
        public ModelAnalysis[] newArray(int size) {
            return new ModelAnalysis[size];
        }
    };
    public static String MODELANALYSIS = "MODELANALYSIS";
    private String id;
    private String name;
    private String commodity;
    private String results;
    private String maxResults;
    private String age;
    private String image;

    public ModelAnalysis(String id, String name, String commodity, String results, String maxResults, String age, String image) {
        this.id = id;
        this.name = name;
        this.commodity = commodity;
        this.results = results;
        this.maxResults = maxResults;
        this.age = age;
        this.image = image;
    }
    public ModelAnalysis() {
    }

    protected ModelAnalysis(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.commodity = in.readString();
        this.results = in.readString();
        this.maxResults = in.readString();
        this.age = in.readString();
        this.image = in.readString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommodity() {
        return commodity;
    }

    public void setCommodity(String commodity) {
        this.commodity = commodity;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public String getMaxResults() {
        return maxResults;
    }

    public void setMaxResults(String maxResults) {
        this.maxResults = maxResults;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.commodity);
        dest.writeString(this.results);
        dest.writeString(this.maxResults);
        dest.writeString(this.age);
        dest.writeString(this.image);
    }
}
