package com.project.fptechscience.db;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.project.fptechscience.model.Option;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class FacilityObj extends RealmObject {


    @PrimaryKey
    private String facilityId;
    private String name;
    private RealmList<OptionObj> options = null;


    public FacilityObj() {
    }

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

    public RealmList<OptionObj> getOptions() {
        return options;
    }

    public void setOptions(RealmList<OptionObj> options) {
        this.options = options;
    }
}
