package team9.munchiematch;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import static android.text.InputType.TYPE_CLASS_NUMBER;

//import munchiematch.munchiematch.R;

public class RecipeSubmissionActivity extends AppCompatActivity {
    private Spinner mealTypeDropDown;
    private Spinner ingredientUnits;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    private LinearLayout ingredientList;
    private LinearLayout stepList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_submission);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mealTypeDropDown = (Spinner) this.findViewById(R.id.mealTypeChoices);
        mealTypeDropDown.setAdapter(new ArrayAdapter<MealType>(this, android.R.layout.simple_spinner_item, MealType.values()));

        ingredientUnits = (Spinner) this.findViewById(R.id.quantityUnits);
        ingredientUnits.setAdapter(new ArrayAdapter<Ingredient_Measurement>(this,
                android.R.layout.simple_spinner_item, Ingredient_Measurement.values()));


        // Specify the layout to use when the list of choices appears
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        //mealTypeDropDown.setAdapter(adapter);

        ingredientList = (LinearLayout) this.findViewById(R.id.recipeIngredientLayout);
        stepList = (LinearLayout) this.findViewById(R.id.recipeStepLayout);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            //mImageView.setImageBitmap(imageBitmap);

            //TODO -- set imageBitmap equal to bitmap in recipe or change instance variable
            // in recipe class
        }
    }

    /**
     * Dynamically creates the UI elements to add
     * another ingredient when the "Add Ingredient" button
     * is pressed
     * @param view
     */
    public void addIngredient(View view) {
        TextView ingredientText = new TextView(this);
        ingredientText.setText("Ingredient");
        ingredientText.setId(R.id.ingredientText);
        ingredientList.addView(ingredientText);

        EditText ingredient = new EditText(this);
        ingredient.setId(R.id.ingredientChoice);
        ingredientList.addView(ingredient);

        TextView quantityText = new TextView(this);
        quantityText.setText("Quantity");
        quantityText.setId(R.id.quantityText);
        ingredientList.addView(quantityText);

        LinearLayout quantityField = new LinearLayout(this, null, R.style.newRecipeItem);

        EditText quantityNumber = new EditText(this);
        quantityNumber.setInputType(TYPE_CLASS_NUMBER);
        quantityNumber.setId(R.id.quantityNumber);
        quantityField.addView(quantityNumber);

        Spinner quantityUnits = new Spinner(this);

        quantityUnits.setAdapter(new ArrayAdapter<Ingredient_Measurement>(this,
                android.R.layout.simple_spinner_item, Ingredient_Measurement.values()));
        quantityUnits.setId(R.id.quantityUnits);

        quantityField.addView(quantityUnits);

        ingredientList.addView(quantityField);
    }

    /**
     * Dynamically creates the UI elements to
     * add a step to the recipe when the
     * "Add Step" button is pressed
     * @param view
     */
    public void addStep(View view) {
        TextView descriptionText = new TextView(this);
        descriptionText.setText("Step Description");
        descriptionText.setId(R.id.descriptionText);
        stepList.addView(descriptionText);

        EditText description = new EditText(this);
        description.setId(R.id.recipeDescription);
        stepList.addView(description);

        TextView timeText = new TextView(this);
        timeText.setText("Time Needed");
        timeText.setId(R.id.timeText);
        stepList.addView(timeText);

        EditText stepTime = new EditText(this);
        stepTime.setId(R.id.stepTime);
        stepList.addView(stepTime);
    }



    public void takePicture(View view) {
        dispatchTakePictureIntent();
    }

    public void submitRecipe(View view) {
        RecipeBuilder builder = new RecipeBuilder();
        builder.setIngredients(ingredientList);
    }
}
