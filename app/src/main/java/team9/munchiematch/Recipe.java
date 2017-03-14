package team9.munchiematch;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Created by rober on 3/8/2017.
 */

public class Recipe {
    private String title;
    private ArrayList<Pair<String, Ingredient>> recipeIngredients;
    private ArrayList<Pair<String, String>> recipeSteps;
    private Privacy recipeVisibility = Privacy.PRIVATE;
    private Bitmap picture;


    /*
    Private class that implements
    a tuple data structure
    using generics
     */

    private class Pair<K, E> {
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

    }

    private enum Privacy {PUBLIC, PRIVATE};


}

