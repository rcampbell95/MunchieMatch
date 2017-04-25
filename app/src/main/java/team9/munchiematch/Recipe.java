package team9.munchiematch;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by rober on 3/8/2017.
 */

public class Recipe {
    private String title;
    private ArrayList<Ingredient> recipeIngredients;
    private ArrayList<Pair<String, String>> recipeSteps;
    private MealType recipeMealType;
    private Privacy recipeVisibility = Privacy.PRIVATE;
    private Bitmap picture;

    public Recipe() {
        ;
        ///TODO-- Add constuctor?
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String newTitle) {
        title = newTitle;
    }

    public void setPicture(Bitmap newPicture) {
        picture = newPicture;
    }

    public Bitmap getPicture() {
        return picture;
    }

    public void setIngredients(ArrayList<Ingredient> userSubmittedIngredients) {
        recipeIngredients = userSubmittedIngredients;
    }

    //TODO -- refactor this method
    public boolean findIngredient(Ingredient searchIngredient, Comparator<Recipe> compareScheme) {
        for(Ingredient ingredientLine : recipeIngredients) {
            if(ingredientLine == searchIngredient) {
                return true;
            }
        }
        return false;
    }

    public MealType getRecipeMealType() {
        return recipeMealType;
    }

    public void setRecipeMealType(MealType newRecipeMealType) {
        recipeMealType = newRecipeMealType;
    }

    public void setPrivate() {
        recipeVisibility = Privacy.PRIVATE;
    }

    public void setPublic() {
        recipeVisibility = Privacy.PUBLIC;
    }

    /*
    Private class that implements
    a tuple data structure
    using generics
     */

    public class Pair<K, E> {
        K first;
        E second;

        public Pair(K newFirst, E newSecond){
            first = newFirst;
            second = newSecond;
        }

        public K getFirst() {
            return first;
        }

        public E getSecond() {
            return second;
        }
    }

    /*
    Private class that represents an ingredient
    in the user submitted recipe
     */

    private enum Privacy {PUBLIC, PRIVATE}
}

