package com.example.user.myapplication2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.model.Family;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by user on 16/08/2016.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    ArrayList<Family> arrayList = new ArrayList<>();

    public RecyclerAdapter(ArrayList<Family> arrayList){
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
        holder.namePassByUser.setText(arrayList.get(position).getNamePassByUser());
        holder.nameUsedInLear.setText(arrayList.get(position).getNameUsedInLear());
        holder.nameUsedInClient.setText(arrayList.get(position).getNameUsedInClient());
        holder.date.setText(arrayList.get(position).getDate());
        holder.client.setText(arrayList.get(position).getClient());
        holder.extra.setText(arrayList.get(position).getExtra());
        holder.date_creation.setText("date");
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView namePassByUser,nameUsedInLear,nameUsedInClient,date,client,extra,date_creation;
        public MyViewHolder(View itemView){
            super(itemView);

            namePassByUser = (TextView) itemView.findViewById(R.id.namePassByUser);
            nameUsedInClient = (TextView) itemView.findViewById(R.id.nameUsedInClient);
            nameUsedInLear = (TextView) itemView.findViewById(R.id.nameUsedInLear);
            date = (TextView) itemView.findViewById(R.id.date);
            client = (TextView) itemView.findViewById(R.id.client);
            extra = (TextView) itemView.findViewById(R.id.extra);
            date_creation = (TextView) itemView.findViewById(R.id.date_creation);
        }

    }
}
