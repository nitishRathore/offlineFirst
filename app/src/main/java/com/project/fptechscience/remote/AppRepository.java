package com.project.fptechscience.remote;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

import com.project.fptechscience.model.Facility;
import com.project.fptechscience.model.GenericResponse;
import com.project.fptechscience.room.AppDatabase;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Nitish Singh on 2019-05-30.
 */
public class AppRepository {

    public static final String TAG = AppRepository.class.getName();
    private Context mContext;
    private LiveData<GenericResponse> responseLiveData = new MutableLiveData<>();
    private AppDatabase appDatabase;

    public AppRepository(Context mContext) {
        appDatabase = Room.databaseBuilder(mContext, AppDatabase.class, "fptechscience").build();
        this.mContext = mContext;
    }


    public void getNetworkCall() {
        ApiService apiService = NetworkClient.getApiService();
        Observable<GenericResponse> responseObservable = apiService.getAllFacilites();
        responseObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableObserver<GenericResponse>() {
            @Override
            public void onNext(GenericResponse genericResponse) {
                if (genericResponse != null) {
                    saveFacilitesInDB(genericResponse);
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete: ");
            }
        });
    }

    private void saveFacilitesInDB(GenericResponse genericResponse) {

        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {

                List<Facility> facilities = genericResponse.getFacilities();
                for (Facility facility : facilities) {
                    appDatabase.getFacitlityDao().insertAllFacilityInDb(facility);
                }

            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete: Data InsertionFinished");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onComplete: Data failed" + e.getMessage());
            }
        });


        Log.e(TAG, "saveFacilitesInDB: getFacilities " + genericResponse.getFacilities().size());
        Log.e(TAG, "saveFacilitesInDB: getExclusions " + genericResponse.getExclusions().size());
    }

}
