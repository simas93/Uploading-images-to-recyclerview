package com.example.simas.fourthexercise.Model.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.simas.fourthexercise.FragmentLayout;
import com.example.simas.fourthexercise.Model.Nature;
import com.example.simas.fourthexercise.Model.helper.Utils;
import com.example.simas.fourthexercise.MyActivity;
import com.example.simas.fourthexercise.R;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by simas on 8/16/2016.
 */
public class NatureAdapter extends RecyclerView.Adapter<NatureAdapter.Holder> {

    private List<Nature> natureList;
    private MyActivity parentActivity;

    public NatureAdapter(MyActivity parentActivity) {
        natureList= new ArrayList<>();
        this.parentActivity = parentActivity;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_row, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Nature currentNature = natureList.get(position);
        holder.textView.setText(currentNature.getPhotoName());
        System.out.println("Pareina URL" + currentNature.getPhotoUrl());
        Picasso.with(holder.itemView.getContext()).load(currentNature.getPhotoUrl()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return natureList.size();
    }

    public void addNature(Nature nature) {
        natureList.add(nature);
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected ImageView imageView;
        protected TextView textView;

        public Holder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.nature_photo);
            textView = (TextView) itemView.findViewById(R.id.photo_name);
            itemView.setOnClickListener(this);
        }

       @Override
        public void onClick(View view) {
           FragmentLayout fragmentLayout = new FragmentLayout().newInstance(natureList.get(getAdapterPosition()).getPhotoUrl());
           parentActivity.onItemClicked(fragmentLayout);
        }
    }
}
