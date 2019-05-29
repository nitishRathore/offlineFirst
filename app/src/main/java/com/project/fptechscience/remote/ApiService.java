package com.project.fptechscience.remote;

import com.project.fptechscience.model.GenericResponse;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Nitish Singh on 2019-05-30.
 */
public interface ApiService {


    @GET("ad-assignment/db")
    Observable<GenericResponse> getAllFacilites();

}
