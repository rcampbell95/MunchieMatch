package team9.munchiematch;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by rober on 3/8/2017.
 */

public class Recipe {
    private String title;
    private ArrayList<Pair<String, Ingredient>> recipeIngredients;
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

    public void setIngredients(ArrayList<Pair<String, Ingredient>> userSubmittedIngredients) {
        recipeIngredients = userSubmittedIngredients;
    }

    public boolean findIngredient(Ingredient searchIngredient, Comparator<Recipe> compareScheme) {
        for(Pair<String, Ingredient> ingredientLine : recipeIngredients) {
            if(ingredientLine.getSecond() == searchIngredient) {
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
    private class Ingredient {
        ///TODO-- Remove Placeholder
    }

    private class MealType {
        //TODO-- Remove Placeholder
    }

    private enum Privacy {PUBLIC, PRIVATE}
}

