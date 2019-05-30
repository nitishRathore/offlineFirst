package com.project.fptechscience.room;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.project.fptechscience.model.Facility;

import java.util.List;
//
//@Dao
//public interface OptionWithFacilityDao {
//
//    @Query("SELECT * from Facility")
//    public List<OptionWithFacilityDao> getAllFacilitiesWithOptions();
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void insertInDAO(Facility...facilities);
//
//}
