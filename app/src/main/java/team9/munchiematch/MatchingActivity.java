package team9.munchiematch;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import team9.munchiematch.LocalRecipeObject;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Collections;


public class MatchingActivity extends AppCompatActivity {

    public ArrayList<LocalRecipeObject> recipeList = new ArrayList<LocalRecipeObject>();
    public TextView recipeTitle;
    public TextView likeDislikeStatus;
    public ImageView recipeImage;
    public int randomInt;
    boolean dislikedPressed = false;
    public int index = 0;
    private StorageReference recipeDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching);
        recipeTitle = (TextView) this.findViewById(R.id.recipeTitle);
        recipeImage = (ImageView) this.findViewById(R.id.recipeImage);
        likeDislikeStatus = (TextView) this.findViewById(R.id.likeDislikeStatus);
        likeDislikeStatus.setText("");

        recipeList.add(new LocalRecipeObject(R.drawable.bacon_guacamolegrilled_cheese_sandwich, "Bacon Sandwich"));
        recipeList.add(new LocalRecipeObject(R.drawable.berry_ice_lemonade, "Berry Ice Lemonade"));
        recipeList.add(new LocalRecipeObject(R.drawable.choco_chip_oreo_cookies, "Oreo Cookies"));
        recipeList.add(new LocalRecipeObject(R.drawable.fettuccine_alfredo, "Fettuccine Alfredo"));
        recipeList.add(new LocalRecipeObject(R.drawable.herb_parmesan_french_fries, "Herb French Fries"));
        recipeList.add(new LocalRecipeObject(R.drawable.mango_chicken_tenders, "Mango Chicken Tenders"));
        recipeList.add(new LocalRecipeObject(R.drawable.mini_deep_dish_pizza, "Mini Deep Dish Pizza"));
        recipeList.add(new LocalRecipeObject(R.drawable.portobello_pesto_pizza, "Portebello Pesto Pizza"));
        recipeList.add(new LocalRecipeObject(R.drawable.spinich_tempeh_dumplings, "Spinach Tempeh Dumplings"));
        recipeList.add(new LocalRecipeObject(R.drawable.sweet_n_sour_chicken, "Sweet N Sour Chicken"));
        Collections.shuffle(recipeList); // Initially shuffle the recipe list...
        //display current initial index
        recipeTitle.setText(recipeList.get(index).recipeTitle);
        recipeImage.setImageResource(recipeList.get(index).recipeImageID);


    }

    public void goToSettings(View view) {
       Intent intent = new Intent(this, IngredientSearch.class);
       startActivity(intent);
        // goes to Settings activity when gear button is tapped.
    }

    public void goToProfile(View view) {
        Intent intent = new Intent(this, NavigationActivity.class);
        // will need to be changed to UserProfileActivity.class in the future
        startActivity(intent);
        // goes to Profile activity when gear button is tapped.
    }

    public void checkMarkPressed(View v){
        //removes this item from future selects
        delayedStatus(500, "Liked", "#458B00");
        loadNextRecipe();
    }

    public void xMarkPressed (View v){
        dislikedPressed = true;
        delayedStatus(500, "Disliked", "#FF0000");
        loadNextRecipe();
    }
    //Displays current recipe in index
    public void loadNextRecipe(){
        if (recipeList.isEmpty()){ //check if empty
            recipeTitle.setText("No more items left...");
            recipeImage.setImageResource(android.R.color.transparent);
        }
        else{
            if (dislikedPressed){
                recipeList.get(index).setDisliked();
                recipeList.get(index).setSeen();
                dislikedPressed = false;
                recipeList.remove(index); // delete object from arraylist if disliked...
                if (index > recipeList.size() - 1) { //holy crap if the last object is deleted decrement DUH
                    index--;
                }
                //recipeList.trimToSize(); // shrink array list
                if (recipeList.isEmpty()){ //check if empty after removing
                    recipeTitle.setText("No more items left...");
                    recipeImage.setImageResource(android.R.color.transparent);
                }
                else {
                    recipeTitle.setText(recipeList.get(index).recipeTitle);
                    recipeImage.setImageResource(recipeList.get(index).recipeImageID);
                }
            }
            else{ //likedIsPressed
                recipeList.get(index).setLiked();
                recipeList.get(index).setSeen();
                User user = User.getInstance(FirebaseAuth.getInstance().getCurrentUser());
                user.addLikedRecipe(recipeList.get(index));
                index++;
                randomInt = (int )(Math.random() * 9 + 1);
                if (randomInt == 5){
                    Intent intent = new Intent(this, youveBeenMatched.class);
                    startActivity(intent);
                }

                if (index > recipeList.size() - 1) { //check if index is greater than list... if it is reset
                    Collections.shuffle(recipeList);
                    index = 0;
                    recipeTitle.setText(recipeList.get(index).recipeTitle);
                    recipeImage.setImageResource(recipeList.get(index).recipeImageID);
                }
                else {
                    recipeTitle.setText(recipeList.get(index).recipeTitle);
                    recipeImage.setImageResource(recipeList.get(index).recipeImageID);
                }
            }
        }
    }

    public void delayedStatus (int seconds, final String status, final String color){
        likeDislikeStatus.setVisibility(View.VISIBLE);
        likeDislikeStatus.setTextColor(Color.parseColor(color));
        likeDislikeStatus.setText(status);
        likeDislikeStatus.postDelayed(new Runnable () {
            @Override
            public void run () {
                likeDislikeStatus.setVisibility(View.GONE);
            }
        }, seconds);
    }



}
