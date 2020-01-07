package com.example.quoimangerapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.quoimangerapp.Database.AppDatabase;
import com.example.quoimangerapp.Database.Entity.UserIngredient;
import com.example.quoimangerapp.MyApplication;
import com.example.quoimangerapp.R;
import com.example.quoimangerapp.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * TODO: Replace the implementation with code for your data type.
 */
public class MyIngredientsRecyclerViewAdapter extends RecyclerView.Adapter<MyIngredientsRecyclerViewAdapter.ViewHolder> {

    private final List<UserIngredient> mValues;

    public MyIngredientsRecyclerViewAdapter(List<UserIngredient> items) {
        mValues = items;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mon_ingrediant, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mContentView.setText(mValues.get(position).getIngredient());
        holder.mDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (mValues.size()!=0) {
                    UserIngredient theRemovedItem = mValues.get(position);
                    // remove your item from data base
                    mValues.remove(position);  // remove the item from list
                    notifyItemRemoved(position); // notify the adapter about the removed item
                    AppDatabase db = MyApplication.getInstance().getDatabase();
                    db.userIngredientDao().delete(theRemovedItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mContentView;
        public UserIngredient mItem;
        public  final ImageButton mDelete;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContentView = mView.findViewById(R.id.list_item_string);
            mDelete = view.findViewById(R.id.delete_btn);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
