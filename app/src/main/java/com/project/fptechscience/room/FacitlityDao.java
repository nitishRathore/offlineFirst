package com.project.fptechscience.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.project.fptechscience.model.Facility;

import java.util.List;

/**
 * Created by Nitish Singh on 2019-05-30.
 */
@Dao
public interface FacitlityDao {

    @Query("select * from Facility")
    LiveData<List<Facility>> getAllFacilitiesFromDB();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllFacilityInDb(Facility ...facilities);

}
