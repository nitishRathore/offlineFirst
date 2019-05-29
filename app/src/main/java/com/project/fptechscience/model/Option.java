
package com.project.fptechscience.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.reactivex.annotations.NonNull;

@Entity
public class Option {

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    @SerializedName("id")
    @Expose
    @NonNull
    private String id;
    @ColumnInfo(name = "name")
    @SerializedName("name")
    @Expose
    private String name;
    @ColumnInfo(name = "icon")
    @SerializedName("icon")
    @Expose
    private String icon;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
