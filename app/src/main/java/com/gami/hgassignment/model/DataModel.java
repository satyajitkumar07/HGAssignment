package com.gami.hgassignment.model;

import java.util.ArrayList;

public class DataModel {

    public ArrayList<Integer> getData(){
        ArrayList<Integer> list=new ArrayList<>();
        for(int k=1;k<=10000;k++){
            list.add(k);
        }
        return list;
    }

}
