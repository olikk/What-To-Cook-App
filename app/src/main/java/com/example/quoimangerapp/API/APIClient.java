package com.example.quoimangerapp.API;

import com.example.quoimangerapp.API.retrofitModels.Recipe;
import com.example.quoimangerapp.API.retrofitModels.RecipeSummary;
import com.example.quoimangerapp.API.retrofitModels.Recipes;
import com.example.quoimangerapp.API.retrofitModels.mappers.IngredientsMapper;
import com.example.quoimangerapp.API.retrofitModels.mappers.RecipeSummaryMapper;
import com.example.quoimangerapp.API.retrofitModels.mappers.VisualizeIngredientsParametersMapper;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    public static final String BASE_URL = "https://api.spoonacular.com";
    public static final String API_KEY = "e7c4eeade409451b97542747eedc1f65";

    private static APIInterface apiInterface = null;
    private static final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    private static final OkHttpClient.Builder client = new OkHttpClient.Builder();

    static {
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        client.addInterceptor(logging);
        client.readTimeout(20, TimeUnit.SECONDS);
        client.connectTimeout(20, TimeUnit.SECONDS);
        client.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("Content-Type", "application/json")
                        .header("Accept", "application/json")
                        .method(original.method(), original.body())
                        .build();
                return chain.proceed(request);
            }
        });
    }

    public static APIInterface getApiInterface() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        apiInterface = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build().create(APIInterface.class);
        return apiInterface;
    }




    public void allRecipes(final Callback<List<Recipes>> callback) {

        Call<List<Recipes>> call = apiInterface.allRecipes(
                API_KEY, 4
        );
        call.enqueue(callback);
    }

    /**
     * Summarize Recipe
     * Summarize the recipe in a short text.
     *
     * @param recipeSummaryMapper
     * @param callback
     */
    public void summarizeRecipe(final RecipeSummaryMapper recipeSummaryMapper,
                                final Callback<RecipeSummary> callback) {

        Call<RecipeSummary> call = apiInterface.summarizeRecipe(
                recipeSummaryMapper.getId(),
                API_KEY
        );
        call.enqueue(callback);
    }

    /**
     * Visualize Ingredients
     * Visualize ingredients of a recipe.
     *
     * @param visualizeIngredientsParametersMapper
     * @param callback
     */
    public void visualizeIngredients(final VisualizeIngredientsParametersMapper visualizeIngredientsParametersMapper,
                                     final Callback<String> callback) {

        Call<String> call = apiInterface.visualizeIngredients(
                visualizeIngredientsParametersMapper.getIngredientsListAsStringPerLine(),
                API_KEY
        );
        call.enqueue(callback);
    }

    public void findRecipesByIngredients(final IngredientsMapper ingredientsMapper, final Callback<List<Recipe>> callback) {

        Call<List<Recipe>> call = apiInterface.findRecipesByIngredients( ingredientsMapper.isFillIngredients(),
                ingredientsMapper.getIngredientsAsString(","),
                ingredientsMapper.isLimitLicense(),
                ingredientsMapper.getNumber(),
                ingredientsMapper.getRanking(), API_KEY);

        call.enqueue(callback);
    }
}
