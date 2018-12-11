package com.gami.hgassignment.view.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.gami.hgassignment.R;
import com.gami.hgassignment.model.DataModel;
import com.gami.hgassignment.view.adapter.HighLightAdapter;
import com.gami.hgassignment.view_model.DataViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private HighLightAdapter highLightAdapter;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager layoutManager;
    private int previousCenterPos;
    private DataViewModel dataViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataViewModel= ViewModelProviders.of(this).get(DataViewModel.class);
        updateAdapterUi();
        mRecyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        layoutManager=new LinearLayoutManager(MainActivity.this);
        mRecyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration itemDecor = new DividerItemDecoration(mRecyclerView.getContext(),
                layoutManager.getOrientation());
        mRecyclerView.addItemDecoration(itemDecor);
        //set adapter
        highLightAdapter=new HighLightAdapter();
        mRecyclerView.setAdapter(highLightAdapter);
        //fetching data
        fetchData();
        //for highlight logic
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int center = mRecyclerView.getHeight() / 2;
                View centerView = mRecyclerView.findChildViewUnder(center, mRecyclerView.getHeight()/2);
                int centerPos = mRecyclerView.getChildAdapterPosition(centerView);
                if (previousCenterPos != centerPos) {
                    // remove highlight from the previously highlighted recyclerView
                    View prevView = mRecyclerView.getLayoutManager().findViewByPosition(previousCenterPos);
                    if (prevView != null) {
                        View rowLayout = prevView.findViewById(R.id.item_parent_layout);
                        int gray = ContextCompat.getColor(MainActivity.this, R.color.colorGray);
                        rowLayout.setBackgroundColor(gray);
                    }
                    // highlight view in the middle of recyclerView
                    if (centerView != null) {
                        View rowLayout = centerView.findViewById(R.id.item_parent_layout);
                        int highlightColor = ContextCompat.getColor(MainActivity.this, R.color.colorAccent);
                        rowLayout.setBackgroundColor(highlightColor);
                    }

                    previousCenterPos = centerPos;
                }
            }
        });

    }

    private void updateAdapterUi(){
        if(dataViewModel!=null){
            dataViewModel.getDataList().observe(this, new Observer<ArrayList<Integer>>() {
                @Override
                public void onChanged(@Nullable ArrayList<Integer> list) {
                    highLightAdapter.setData(list);
                }
            });
        }
    }

    private void fetchData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                final ArrayList<Integer> list=new DataModel().getData();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dataViewModel.setModelData(list);
                    }
                });
            }
        }).start();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
