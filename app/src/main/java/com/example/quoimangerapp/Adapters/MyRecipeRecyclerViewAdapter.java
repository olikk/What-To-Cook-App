package com.example.quoimangerapp.Adapters;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quoimangerapp.API.retrofitModels.Recipes;
import com.example.quoimangerapp.R;
import com.example.quoimangerapp.ui.recipe.RecipeMoreFragment;
import com.squareup.picasso.Picasso;

import java.util.List;



public class MyRecipeRecyclerViewAdapter extends RecyclerView.Adapter<MyRecipeRecyclerViewAdapter.ViewHolder> {

    private List<Recipes> mValues;
    private Context context;

    public MyRecipeRecyclerViewAdapter(Context cxt, List<Recipes>  values) {

        mValues = values;
        context = cxt;
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
        Integer id = mValues.get(position).getId();
        holder.mItem = mValues.get(position);
        holder.mContentView.setText(mValues.get(position).getTitle());
        if (mValues.get(position).getImage().contains("https")){
            url = mValues.get(position).getImage();
        }else{
            url = "https://spoonacular.com/recipeImages/" + mValues.get(position).getImage();
        }
        Picasso.get().load(url).into(holder.mPhotoView);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                FragmentTransaction ft = ((AppCompatActivity)context)
                        .getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.nav_host_fragment, (new RecipeMoreFragment())
                        .newInstance(id));
                ft.commit();

            }
        });


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
