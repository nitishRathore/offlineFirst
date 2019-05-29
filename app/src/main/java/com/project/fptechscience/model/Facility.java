
package com.project.fptechscience.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Relation;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.reactivex.annotations.NonNull;

@Entity
public class Facility {

    @PrimaryKey(autoGenerate = false)
    @SerializedName("facilityId")
    @Expose
    @NonNull
    private String facilityId;
    @ColumnInfo(name = "name")
    @SerializedName("name")
    @Expose
    private String name;
    @Relation(parentColumn = "facilityId",entityColumn = "id",entity = Option.class)
    @SerializedName("options")
    @Expose
    private List<Option> options = null;

    public String getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

}
