package team9.munchiematch;

import android.net.Uri;

import com.google.firebase.auth.FirebaseUser;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by robert on 3/18/2017.
 */

public class User {
    private ArrayList<Recipe> userRecipes;
    private HashSet<LocalRecipeObject> likedRecipes;

    private FirebaseUser loggedInUser;
    //TODO Add user profile picture
    private String userName;
    private String userAge;

    private static User instance;

    private User(FirebaseUser currentUser) {
        userRecipes = new ArrayList<Recipe>();
        likedRecipes = new HashSet<LocalRecipeObject>();

        loggedInUser = currentUser;
    }

    public static User getInstance(FirebaseUser currentUser) {
        if(instance == null) {
            instance = new User(currentUser);
            return instance;
        }
        else return instance;
    }

    public void addRecipe(Recipe newRecipe) {
        userRecipes.add(newRecipe);
    }

    public ArrayList<Recipe> getRecipes() {return userRecipes;}


    public String getUserName() {
        return userName;
    }

    public Uri getProfilePicture() {
        return loggedInUser.getPhotoUrl();
    }

    public void addLikedRecipe(LocalRecipeObject newLikeRecipe) {
        likedRecipes.add(newLikeRecipe);
    }

    public void setName(final String name) {
        userName = name;
    }

    public void setAge(final String age) {
        this.userAge = age;
    }
}
