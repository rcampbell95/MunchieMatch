package team9.munchiematch;

import java.net.URI;
import java.util.ArrayList;

/**
 * Created by rober on 3/18/2017.
 */

public class User {
    private ArrayList<Recipe> userRecipes;
    private ArrayList<Recipe> likedRecipes;

    FirebaseUser loggedInUser;
    //TODO Add login information

    public User(FirebaseUser currentUser) {
        userRecipes = new ArrayList<Recipe>();
        likedRecipes = new ArrayList<Recipe>();

        loggedInUser = currentUser;
    }

    public void addRecipe(Recipe newRecipe) {
        userRecipes.add(newRecipe);
    }

    public String getUserName() {
        loggedInUser.getDisplayName();
    }

    public Uri getProfilePicture() {
        loggedInUser.getPhotoUrl();
    }

    public void addLikedRecipe(Recipe newLikeRecipe) {
        likedRecipes.add(newLikeRecipe);
    }
}
