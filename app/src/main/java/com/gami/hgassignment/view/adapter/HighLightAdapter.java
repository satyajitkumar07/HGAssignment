package com.gami.hgassignment.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gami.hgassignment.R;

import java.util.ArrayList;

public class HighLightAdapter extends RecyclerView.Adapter<HighLightAdapter.HighLightVH> {

    private ArrayList<Integer> list;
    @NonNull
    @Override
    public HighLightVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int pos) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_item_layout,viewGroup,false);
        HighLightVH vh=new HighLightVH(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull HighLightVH highLightVH, int pos) {
        highLightVH.valueTxt.setText(""+list.get(pos));
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public void setData(ArrayList<Integer> list){
        this.list=list;
    }

    class HighLightVH extends RecyclerView.ViewHolder{
        TextView valueTxt;
        public HighLightVH(@NonNull View itemView) {
            super(itemView);
            valueTxt=(TextView)itemView.findViewById(R.id.value);
        }
    }
}
