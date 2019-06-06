package com.example.loicdandoy.firebase;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<Recipe> mDataset;

    // Declaring a Glide object
    private RequestManager glide;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, username, recipeTime, mealType;
        public ImageView image;

        public MyViewHolder(View v) {
            super(v);

            name = v.findViewById(R.id.nameView);
            username = v.findViewById(R.id.usernameView);
            recipeTime = v.findViewById(R.id.recipeTimeView);
            mealType = v.findViewById(R.id.recipeMealType);
            image = v.findViewById(R.id.imageView);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<Recipe> myDataset, RequestManager glide) {
        mDataset = myDataset;
        this.glide = glide;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;

        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_text_view, parent, false);

        // TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.my_text_view, parent, false);
        // MyViewHolder vh = new MyViewHolder(v);
        return new MyViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.name.setText(mDataset.get(position).name);
        holder.username.setText(mDataset.get(position).username);
        holder.recipeTime.setText(mDataset.get(position).recipeTime);
        holder.mealType.setText(mDataset.get(position).meal_type);

        Glide.with(holder.image).load(mDataset.get(position).recipeImg).into(holder.image);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void updateData(ArrayList<Recipe> viewModels) {
        mDataset.clear();
        mDataset.addAll(viewModels);
        notifyDataSetChanged();
    }

    // Create object Recipe
    public Recipe getLabel(int position){
        return this.mDataset.get(position);
    }
}