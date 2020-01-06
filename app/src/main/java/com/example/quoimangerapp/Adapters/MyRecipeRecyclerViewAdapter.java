package com.example.quoimangerapp.Adapters;

import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
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
        holder.mItem = mValues.get(position);
        Picasso.get().load("https://spoonacular.com/recipeImages/" + mValues.get(position)
                        .getImage()).into(holder.mPhotoView);
        Log.d("TAG",  mValues.get(position).getTitle()+ "");
        holder.mContentView.setText(mValues.get(position).getTitle());
        /*holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    //mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });*/
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
