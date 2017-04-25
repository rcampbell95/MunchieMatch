package team9.munchiematch;

import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by rober on 4/18/2017.
 */

public class RecipeBuilder {
    ArrayList<View> ingredientElements;
    ArrayList<View> stepElements;
    Recipe recipe;

    public RecipeBuilder() {


        recipe = new Recipe();
    }
    public void setTitle(String newTitle) {
        recipe.setTitle(newTitle);
    }

    public void setIngredients(View view) {
        ingredientElements = findElements(view);
    }

    public void setSteps(View view) {
        stepElements = findElements(view);
    }

    public void setMealType(MealType recipeMealType) {
        recipe.setRecipeMealType(recipeMealType);
    }

    public void setPrivacy() {
        ;
    }

    public void setPicture(Bitmap picture) {
        recipe.setPicture(picture);
    }

    public Recipe createRecipe() {
        return recipe;
    }

    private ArrayList<View> findElements(View view) {
        ArrayList<View> elements = new ArrayList<View>();
        depthFirstSearch((ViewGroup)view, elements);

        return elements;
    }


    private void depthFirstSearch(ViewGroup viewGroup, ArrayList<View> elements) {
        for(int index=0; index<((ViewGroup)viewGroup).getChildCount(); ++index) {
            View nextChild = ((ViewGroup) viewGroup).getChildAt(index);
            if (!elements.contains(nextChild)) {
                if (nextChild instanceof ViewGroup) {
                    depthFirstSearch((ViewGroup) nextChild, elements);
                }
                elements.add(nextChild);
            }
        }
    }
}
