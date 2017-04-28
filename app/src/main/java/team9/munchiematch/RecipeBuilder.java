package team9.munchiematch;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Iterator;

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

    /**
     * Builds a list of ingredients
     * from the corresponding layout
     * @param view
     */
    public void setIngredients(View view) {
        int viewCount = 0;
        ingredientElements = findElements(view);
        ArrayList<Ingredient> ingredientList = new ArrayList<Ingredient>();

        Ingredient_Measurement unit = null;
        String ingredient = new String();
        double ingredientQuantity = 0;

        for(Iterator<View> i = ingredientElements.iterator();i.hasNext();) {
            viewCount += 1;

            View currentView = i.next();
            int viewId = currentView.getId();

            if(viewId == R.id.ingredientChoice) {
                ingredient = ((EditText)currentView).getText().toString();
            }
            else if(viewId == R.id.quantityNumber) {
                String text = ((EditText)currentView).getText().toString();
                try {
                    ingredientQuantity = Double.parseDouble(text);
                }
                catch(NumberFormatException e) {
                    e.printStackTrace();
                }
            }
            else if(viewId == R.id.quantityUnits) {
                Object selectedItem = ((Spinner)currentView).getSelectedItem();
                unit = (Ingredient_Measurement) selectedItem;
            }

            if(currentView instanceof LinearLayout) {
                viewCount = 0;
                Ingredient newIngredient = new Ingredient(Ingredient_Type.meat, ingredient, unit, ingredientQuantity);
                ingredientList.add(newIngredient);
            }
        }

        recipe.setIngredients(ingredientList);
    }

    /**
     * Builds a list of recipe steps
     * from the corresponding layout
     * @param view
     */
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

    /**
     * Recursively creates a list of all the
     * Views in a ViewGroup
     * @param view
     */
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
