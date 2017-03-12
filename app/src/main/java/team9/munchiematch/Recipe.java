package team9.munchiematch;

import java.util.ArrayList;

/**
 * Created by rober on 3/8/2017.
 */

public class Recipe {
    ArrayList<Pair> recipeIngredients;
    ArrayList<Pair> recipeSteps;

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


}

