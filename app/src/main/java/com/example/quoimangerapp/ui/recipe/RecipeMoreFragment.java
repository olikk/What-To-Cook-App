package com.example.quoimangerapp.ui.recipe;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quoimangerapp.API.APIClient;
import com.example.quoimangerapp.API.APIInterface;
import com.example.quoimangerapp.API.retrofitModels.ExtendedIngredient;
import com.example.quoimangerapp.API.retrofitModels.RecipeInformation;
import com.example.quoimangerapp.API.retrofitModels.Recipes;
import com.example.quoimangerapp.API.retrofitModels.RecipesList;
import com.example.quoimangerapp.Adapters.MyRecipeRecyclerViewAdapter;
import com.example.quoimangerapp.Adapters.RecipeIngredientsRecyclerViewAdapter;
import com.example.quoimangerapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RecipeMoreFragment extends Fragment {


    private Integer id;
    private static List<ExtendedIngredient> ingredients = new ArrayList<>();
    ImageView recipe_photo;
    TextView title, preparation_time, steps;
    FloatingActionButton shareButon;

    public RecipeMoreFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param id recipe id.
     * @return A new instance of fragment RecipeMoreFragment.
     */
    public static RecipeMoreFragment newInstance(Integer id) {
        RecipeMoreFragment fragment = new RecipeMoreFragment();
        Bundle args = new Bundle();
        args.putInt("id", id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getInt("id");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container != null) {
            container.removeAllViews();
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipe_more, container, false);
    }

    @Override
    public void onViewCreated(View root, Bundle savedInstanceState) {
        super.onViewCreated(root, savedInstanceState);

        recipe_photo = root.findViewById(R.id.info_recipe_photo);
        title = root.findViewById(R.id.info_recipe_name);
        preparation_time = root.findViewById(R.id.info_recipe_time);
        steps = root.findViewById(R.id.info_instructions);
        shareButon = root.findViewById(R.id.action_share);

        APIInterface apiInterface = APIClient.getApiInterface();

        RecyclerView recyclerView = root.findViewById(R.id.recipe_ingrediants);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));

        Call<RecipeInformation> call = apiInterface.getRecipeInformation("application/json",
                "application/json", id, "e7c4eeade409451b97542747eedc1f65");

        call.enqueue(new Callback<RecipeInformation>() {
            @Override
            public void onResponse(Call<RecipeInformation> call, Response<RecipeInformation> response) {
                Log.d("TAG", response.code() + "");
                RecipeInformation recipe_info = response.body();
                ingredients = recipe_info.getExtendedIngredients();
                recyclerView.setAdapter(new RecipeIngredientsRecyclerViewAdapter(ingredients));
                Picasso.get().load(recipe_info.getImage()).into(recipe_photo);
                title.setText(recipe_info.getTitle());
                preparation_time.setText(String.valueOf(recipe_info.getReadyInMinutes()));
                steps.setText(Html.fromHtml(recipe_info.getInstructions()));
                shareButon.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        intent.putExtra(Intent.EXTRA_TEXT, recipe_info.getSourceUrl());
                        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Envie d'essayer cette recette? ;)");
                        startActivity(Intent.createChooser(intent, "Share"));
                    }
                });

            }

            @Override
            public void onFailure(Call<RecipeInformation> call, Throwable t) {
                call.cancel();
                System.out.println("call" + call.toString());
                t.printStackTrace();
            }
        });

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    /*public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
}
