package com.example;


import com.google.gson.annotations.SerializedName;

public class Config {


    @SerializedName("current_version")
    int version;

    @SerializedName("force_update")
    Boolean update;

    public int getVersion() {
        return version;
    }

    public Boolean getUpdate() {
        return update;
    }
}
