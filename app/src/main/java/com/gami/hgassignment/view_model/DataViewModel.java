package com.gami.hgassignment.view_model;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;

public class DataViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Integer>> mutableLiveData;

    public DataViewModel(){
        mutableLiveData=new MutableLiveData<>();
    }
    public MutableLiveData<ArrayList<Integer>> getDataList(){
        return mutableLiveData;
    }

    public void setModelData(ArrayList<Integer> list){
        mutableLiveData.setValue(list);
    }
}
