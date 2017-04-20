package team9.munchiematch;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

//import munchiematch.munchiematch.R;

public class RecipeSubmissionActivity extends AppCompatActivity {
    private Spinner mealTypeDropDown;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private Button addIngredient;
    private Button addRecipeStep;
    private Button addPicture;
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

        // Specify the layout to use when the list of choices appears
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        //mealTypeDropDown.setAdapter(adapter);

        addIngredient = (Button) this.findViewById(R.id.addIngredientButton);
        addRecipeStep = (Button) this.findViewById(R.id.addStepButton);
        addPicture = (Button) this.findViewById(R.id.pictureUpload);

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

    //TODO -- implement addIngredient and addStep functions
    //TODO -- make new TextView and EditView children of corresponding LinearLayout
    public void addIngredient(View view) {
        TextView ingredientText = new TextView(this);
        ingredientText.setText("Ingredient");
        ingredientList.addView(ingredientText);

        EditText ingredient = new EditText(this);
        ingredientList.addView(ingredient);

        TextView quantityText = new TextView(this);
        quantityText.setText("Quantity");
        ingredientList.addView(quantityText);

        EditText quantity = new EditText(this);
        ingredientList.addView(quantity);
    }

    public void addStep(View view) {
        TextView descriptionText = new TextView(this);
        descriptionText.setText("Step Description");
        stepList.addView(descriptionText);

        EditText description = new EditText(this);
        stepList.addView(description);

        TextView timeText = new TextView(this);
        timeText.setText("Time Needed");
        stepList.addView(timeText);

        EditText stepTime = new EditText(this);
        stepList.addView(stepTime);
    }


    public void takePicture(View view) {
        dispatchTakePictureIntent();
    }
}
