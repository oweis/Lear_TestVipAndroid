package com.example.user.myapplication2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.user.Model.Family;

import java.util.ArrayList;

/**
 * Created by user on 16/08/2016.
 */
public class RecyclerAdapterFamily extends RecyclerView.Adapter<RecyclerAdapterFamily.MyViewHolder> {

    ArrayList<Family> arrayList = new ArrayList<>();

    public RecyclerAdapterFamily(ArrayList<Family> arrayList){
        this.arrayList = arrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_family,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        String title = arrayList.get(position).getNamePassByUser();

        holder.namePassByUser.setText(title);
        holder.nameUsedInLear.setText("Name Used In Lear : " + arrayList.get(position).getNameUsedInLear());
        holder.nameUsedInClient.setText("Name Used In Client : " +arrayList.get(position).getNameUsedInClient());
        holder.date.setText("Date : " + arrayList.get(position).getDate());
        holder.client.setText("Client : " + arrayList.get(position).getClient());
        holder.buttonIdFamily.setTag(arrayList.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView namePassByUser,nameUsedInLear,nameUsedInClient,date,client,extra,date_creation;
        Button buttonIdFamily;
        public MyViewHolder(View itemView){
            super(itemView);

            namePassByUser = (TextView) itemView.findViewById(R.id.namePassByUser);
            nameUsedInLear = (TextView) itemView.findViewById(R.id.nameUsedInLear);
            nameUsedInClient = (TextView) itemView.findViewById(R.id.nameUsedInClient);
            date = (TextView) itemView.findViewById(R.id.date);
            client = (TextView) itemView.findViewById(R.id.client);
            buttonIdFamily = (Button) itemView.findViewById(R.id.buttonIdFamily);
        }
    }


}
