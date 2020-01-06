package com.example.quoimangerapp.ui.recipe;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quoimangerapp.API.APIClient;
import com.example.quoimangerapp.API.APIInterface;
import com.example.quoimangerapp.API.retrofitModels.Recipes;
import com.example.quoimangerapp.API.retrofitModels.RecipesList;
import com.example.quoimangerapp.Adapters.MyRecipeRecyclerViewAdapter;
import com.example.quoimangerapp.R;
import com.example.quoimangerapp.dummy.DummyContent;
import com.example.quoimangerapp.dummy.DummyContent.DummyItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A fragment representing a list of Items.
 * <p/>
 * interface.
 */
public class RecipeFragment extends Fragment {

    //private OnListFragmentInteractionListener mListener;
    private List<Recipes> values = new ArrayList<>();
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RecipeFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_list, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View root, Bundle savedInstanceState) {
        super.onViewCreated(root, savedInstanceState);
        APIInterface apiInterface = APIClient.getApiInterface();
        RecyclerView recyclerView = root.findViewById(R.id.recipe_list_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));

        Call<RecipesList> call = apiInterface.allRecipes("application/json",
                "application/json","e7c4eeade409451b97542747eedc1f65", 6);
        call.enqueue(new Callback<RecipesList>() {
            @Override
            public void onResponse(Call<RecipesList> call, Response<RecipesList> response) {
                Log.d("TAG", response.code() + "");
                values = response.body().getRecipesList();
                recyclerView.setAdapter(new MyRecipeRecyclerViewAdapter(values));
            }
            @Override
            public void onFailure(Call<RecipesList> call, Throwable t) {
                call.cancel();
                System.out.println("call"+ call.toString());
                t.printStackTrace();
            }

        });

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
   /* public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Recipes recipe);
    }*/
}
