package team9.munchiematch;

import java.util.ArrayList;

/**
 * Created by rober on 3/8/2017.
 */

public class Recipe {
    ArrayList<Integer> recipeIngredients;

    /*
    Private class that represents
    a line of user input
    to the recipe submission form
     */
    private class IngredientPair {
        Ingredient certainIngredient;
        double quantity;

        public IngredientPair(Ingredient newIngredient, double newQuantity){
            certainIngredient = newIngredient;
            quantity = newQuantity;
        }

        public Ingredient getIngredient() {
            return certainIngredient;
        }

        public double getQuantity() {
            return quantity;
        }

    }

    /*
    Private class that represents an ingredient
    in the user submitted recipe
     */
    private class Ingredient {

    }
}

