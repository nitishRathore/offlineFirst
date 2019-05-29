package com.project.fptechscience.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.project.fptechscience.model.Facility;
import com.project.fptechscience.model.Option;

/**
 * Created by Nitish Singh on 2019-05-30.
 */

@Database(entities = {Facility.class, Option.class} , version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FacitlityDao getFacitlityDao();
}
