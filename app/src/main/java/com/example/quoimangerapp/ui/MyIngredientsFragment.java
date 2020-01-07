package com.example.quoimangerapp.ui;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quoimangerapp.API.APIClient;
import com.example.quoimangerapp.API.APIInterface;
import com.example.quoimangerapp.API.retrofitModels.Recipes;
import com.example.quoimangerapp.API.retrofitModels.RecipesList;
import com.example.quoimangerapp.Adapters.MyIngredientsRecyclerViewAdapter;
import com.example.quoimangerapp.Adapters.MyRecipeRecyclerViewAdapter;
import com.example.quoimangerapp.Database.AppDatabase;
import com.example.quoimangerapp.Database.Entity.UserIngredient;
import com.example.quoimangerapp.MyApplication;
import com.example.quoimangerapp.R;
import com.example.quoimangerapp.sessionmanagement.SaveSharedPreferences;
import com.example.quoimangerapp.ui.recipe.RecipeFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyIngredientsFragment extends Fragment {

    //private View view;
    EditText ingredient;
    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private List<Recipes> values = new ArrayList<>();

    public MyIngredientsFragment() {
        // Required empty public constructor
    }

    public String getIngredientsListAsStringPerLine(List<UserIngredient> ingredientList) {

        StringBuilder ingredientsBuilder = new StringBuilder();
        try {
            synchronized (ingredientList) {
                int ingredientsListSize = ingredientList.size();
                int i;
                for(i = 0; i < ingredientsListSize - 1; i++) {
                    ingredientsBuilder.append(ingredientList.get(i).getIngredient());
                    ingredientsBuilder.append(System.getProperty("line.separator"));
                }
                ingredientsBuilder.append(ingredientList.get(i).getIngredient());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ingredientsBuilder.toString();
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static MyIngredientsFragment newInstance(int columnCount) {
        MyIngredientsFragment fragment = new MyIngredientsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_ingredients, container, false);

        return  view;
    }

    @Override
    public void onViewCreated(View root, Bundle savedInstanceState) {
        super.onViewCreated(root, savedInstanceState);
        final Button addIngrButton = root.findViewById(R.id.button_add_ingr);
        ingredient = root.findViewById(R.id.add_my_ingredient);

        final Button searchButton = root.findViewById(R.id.button_search_recipes2);
        RecyclerView recyclerView = root.findViewById(R.id.user_ingrediants);

        AppDatabase db = MyApplication.getInstance().getDatabase();

        List<UserIngredient> mValues = db.userIngredientDao()
                .findByUser(SaveSharedPreferences.getLoggedInUserId(getActivity()
                        .getApplicationContext()));

        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        recyclerView.setAdapter(new MyIngredientsRecyclerViewAdapter(mValues));


        addIngrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String ingredientValue = ingredient.getText().toString();

                if ((TextUtils.isEmpty(ingredientValue))) {

                    Toast.makeText(getActivity().getApplicationContext(), "Veuillez remplir le nom d'ingrédiant", Toast.LENGTH_SHORT).show();

                } else {

                    int userId = SaveSharedPreferences.getLoggedInUserId(getActivity().getApplicationContext());

                    UserIngredient userIngredient = new UserIngredient();
                    userIngredient.setIngredient(ingredientValue);
                    userIngredient.setUser_id(userId);

                    db.userIngredientDao().insertAll(userIngredient);

                    Toast.makeText(getActivity().getApplicationContext(),
                            "ingrédiant ajouté", Toast.LENGTH_SHORT).show();

                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.nav_host_fragment, new MyIngredientsFragment());
                    ft.commit();
                }
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APIInterface apiInterface = APIClient.getApiInterface();

                Call<List<Recipes>> call = apiInterface.findRecipesByIngredients("application/json",
                        "application/json", getIngredientsListAsStringPerLine(mValues),
                        true, 6,1,
                        "e7c4eeade409451b97542747eedc1f65");
                call.enqueue(new Callback<List<Recipes>>() {
                    @Override
                    public void onResponse(Call<List<Recipes>> call, Response<List<Recipes>> response) {
                        Log.d("TAG", response.code() + "");
                        values = response.body();

                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.nav_host_fragment, (new RecipeFragment()).newInstance((ArrayList<Recipes>)values));
                        ft.commit();
                    }
                    @Override
                    public void onFailure(Call<List<Recipes>> call, Throwable t) {
                        call.cancel();
                        System.out.println("call"+ call.toString());
                        t.printStackTrace();
                    }
                });
            }
        });

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
