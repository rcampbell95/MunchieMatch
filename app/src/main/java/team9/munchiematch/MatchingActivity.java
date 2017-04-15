package team9.munchiematch;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import team9.munchiematch.LocalRecipeObject;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Collections;


public class MatchingActivity extends AppCompatActivity {

    public ArrayList<LocalRecipeObject> recipeList = new ArrayList<LocalRecipeObject>();
    public TextView recipeTitle;
    public TextView likeDislikeStatus;
    public ImageView recipeImage;
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
        loadNextRecipe(); // load initial recipe
    }

    public void goToSettings(View view) {
       Intent intent = new Intent(this, LoginActivity.class);
       startActivity(intent);
        // goes to Settings activity when gear button is tapped.
    }

    public void goToProfile(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
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

    //Ideally contains picture/title/recipe
    //There has to be some methods to navigate the database and retrieve items from them
    public void loadNextRecipe(){
        recipeTitle.postDelayed(new Runnable() {
            @Override
            public void run () {
                if (recipeList.isEmpty()) {
                    recipeTitle.setText("No more items left...");
                    recipeImage.setImageResource(android.R.color.transparent);
                }
                else {
                    if (dislikedPressed == true){
                        recipeList.remove(index); // delete object from arraylist if disliked...
                        recipeList.trimToSize(); // shrink array list
                        dislikedPressed = false;
                        // then display next object
                        recipeTitle.setText(recipeList.get(index).recipeTitle);
                        recipeImage.setImageResource(recipeList.get(index).recipeImageID);
                        if (index < 0 || index >= recipeList.size()) { //case where iterator is greater than index last element
                            Collections.shuffle(recipeList);
                            index = 0;
                        }
                    }
                    else{
                        recipeTitle.setText(recipeList.get(index).recipeTitle);
                        recipeImage.setImageResource(recipeList.get(index).recipeImageID);
                        index++;
                        if (index < 0 || index >= recipeList.size()) {
                            Collections.shuffle(recipeList);
                            index = 0;
                        }

                    }
                }
            }
        }, 500); // sets delay before updating picture and text
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
