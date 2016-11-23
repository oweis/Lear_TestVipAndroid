package com.example.user.LearBIP;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.example.user.Model.Cable;

import java.util.ArrayList;

/**
 * Created by user on 16/08/2016.
 */
public class RecyclerAdapterCable extends RecyclerView.Adapter<RecyclerAdapterCable.MyViewHolder> {

    ArrayList<Cable> arrayList = new ArrayList<>();

    public RecyclerAdapterCable(ArrayList<Cable> arrayList){
        this.arrayList = arrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_cable,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        String title = arrayList.get(position).getNameUsedInLear();
        holder.nameUsedInLear.setText(title);
        holder.nameUsedInClient.setText("Name Used In Client : " + arrayList.get(position).getNameUsedInClient());
        holder.level.setText("Level : " + arrayList.get(position).getLevel());
        holder.date.setText("Date : " +arrayList.get(position).getDate());
        holder.buttonIdCable.setTag(arrayList.get(position).getId());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nameUsedInLear,nameUsedInClient,level,date,id;
        Button buttonIdCable;
        public MyViewHolder(View itemView){
            super(itemView);

            nameUsedInLear = (TextView) itemView.findViewById(R.id.nameUsedInLear);
            nameUsedInClient = (TextView) itemView.findViewById(R.id.nameUsedInClient);
            level = (TextView) itemView.findViewById(R.id.level);
            date = (TextView) itemView.findViewById(R.id.date);
            id = (TextView) itemView.findViewById(R.id.id);
            buttonIdCable = (Button) itemView.findViewById(R.id.buttonIdCable);
        }

    }
}