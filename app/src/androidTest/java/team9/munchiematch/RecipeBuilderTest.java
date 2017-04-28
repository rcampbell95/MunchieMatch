package team9.munchiematch;

/**
 * Created by rober on 4/23/2017.
 */

import android.app.Activity;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.annotation.UiThreadTest;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.rule.ActivityTestRule;
import android.widget.EditText;
import android.widget.Spinner;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class RecipeBuilderTest {

    @Rule
    public ActivityTestRule<RecipeSubmissionActivity> rule  = new  ActivityTestRule<RecipeSubmissionActivity>(RecipeSubmissionActivity.class) {

        @Override
        protected Intent getActivityIntent() {
            InstrumentationRegistry.getTargetContext();
            Intent intent = new Intent(InstrumentationRegistry.getTargetContext(), RecipeSubmissionActivity.class);
            return intent;

        }
    };

    @UiThreadTest
    @Test
    public void testSetIngredients() {
        Activity recipeSubmissionActivity = rule.getActivity();

        EditText ingredient = (EditText) recipeSubmissionActivity.findViewById(R.id.ingredientChoice);
        ingredient.setText("Chicken");

        EditText ingredientQuantity = (EditText) recipeSubmissionActivity.findViewById(R.id.quantityNumber);
        ingredientQuantity.setText("6.2");

        Spinner ingredientUnits = (Spinner) recipeSubmissionActivity.findViewById(R.id.quantityUnits);
        Ingredient_Measurement unit = Ingredient_Measurement.slices;
        ingredientUnits.setSelection(unit.ordinal());

        recipeSubmissionActivity.findViewById(R.id.recipeIngredientLayout);


    }

    @Test
    public void testSetSteps() {
        ;
    }

    @Test
    public void testSetMealType() {
        ;
    }

    @Test
    public void testSetPrivacy() {
        ;
    }

    @Test
    public void testSetPicture() {
    }

    @Test
    public void testCreateRecipe() {
    }

    @Test
    public void testFindElements() {
        RecipeBuilder builder = new RecipeBuilder();
    }
}
