package com.project.fptechscience.remote;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;

import com.project.fptechscience.db.FacilityObj;
import com.project.fptechscience.db.OptionObj;
import com.project.fptechscience.model.Exclusion;
import com.project.fptechscience.model.Facility;
import com.project.fptechscience.model.GenericResponse;
import com.project.fptechscience.model.Option;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Nitish Singh on 2019-05-30.
 */
public class AppRepository {

    public static final String TAG = AppRepository.class.getName();
    private Context mContext;
    private LiveData<GenericResponse> responseLiveData = new MutableLiveData<>();
    //    private AppDatabase appDatabase;
    Realm realm;

    public AppRepository(Context mContext) {
        realm = Realm.getDefaultInstance();
//        appDatabase = Room.databaseBuilder(mContext, AppDatabase.class, "fptechscience").build();
        this.mContext = mContext;
    }


    public void getNetworkCall() {
        ApiService apiService = NetworkClient.getApiService();
        Observable<GenericResponse> responseObservable = apiService.getAllFacilites();
        responseObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableObserver<GenericResponse>() {
            @Override
            public void onNext(GenericResponse genericResponse) {
                if (genericResponse != null) {
                    Log.e(TAG, "getNetworkCall: " + genericResponse.getFacilities().size());
//                    saveFacilitesInDB(genericResponse);
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

        realm.beginTransaction();
        for (Facility facility : genericResponse.getFacilities()) {
            Log.e(TAG, "saveFacilitesInDB: " + facility.getFacilityId());
            FacilityObj facility1 = realm.createObject(FacilityObj.class, facility.getFacilityId());
            facility1.setFacilityId(facility.getFacilityId());
            facility1.setName(facility.getName());
            RealmList<OptionObj> options = new RealmList<>();
            for (Option option : facility.getOptions()) {
                OptionObj option1 = realm.createObject(OptionObj.class, option.getOptionId());
                option1.setIcon(option.getName());
                option1.setOptionId(option.getOptionId());
                option1.setName(option.getName());
                options.add(option1);

            }
            facility1.setOptions(options);
            realm.insert(facility1);

        }

        List<List<Exclusion>> exclusionList = genericResponse.getExclusions();


        realm.commitTransaction();
        Log.e(TAG, "saveFacilitesInDB: getFacilities " + genericResponse.getFacilities().size());
        Log.e(TAG, "saveFacilitesInDB: getExclusions " + genericResponse.getExclusions().size());
    }

}
