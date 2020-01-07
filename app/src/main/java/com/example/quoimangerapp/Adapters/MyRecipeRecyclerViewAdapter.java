package com.example.quoimangerapp.Adapters;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quoimangerapp.API.retrofitModels.Recipes;
import com.example.quoimangerapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;



public class MyRecipeRecyclerViewAdapter extends RecyclerView.Adapter<MyRecipeRecyclerViewAdapter.ViewHolder> {

    private List<Recipes> mValues;

    public MyRecipeRecyclerViewAdapter(List<Recipes>  values) {
        mValues = values;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_recipe, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        String url;
        holder.mItem = mValues.get(position);
        holder.mContentView.setText(mValues.get(position).getTitle());
        if (mValues.get(position).getImage().contains("https")){
            url = mValues.get(position).getImage();
        }else{
            url = "https://spoonacular.com/recipeImages/" + mValues.get(position).getImage();
        }
        Picasso.get().load(url).into(holder.mPhotoView);


    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mPhotoView;
        public final TextView mContentView;

        public Recipes mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mPhotoView = view.findViewById(R.id.recipe_photo);
            mContentView = view.findViewById(R.id.recipe_list_name);


        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
