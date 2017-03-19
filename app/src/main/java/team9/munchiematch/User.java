package team9.munchiematch;

import java.util.ArrayList;

/**
 * Created by rober on 3/18/2017.
 */

public class User {
    private ArrayList<Recipe> userRecipes;
    private ArrayList<Recipe> likedRecipes;
    //TODO Add "Matches" Field
    //TODO Add login information

    public User() {
        userRecipes = new ArrayList<Recipe>();
        likedRecipes = new ArrayList<Recipe>();
    }

    public void addRecipe(Recipe newRecipe) {
        userRecipes.add(newRecipe);
    }

    public void addLikedRecipe(Recipe newLikeRecipe) {
        likedRecipes.add(newLikeRecipe);
    }
}
