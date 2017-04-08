package team9.munchiematch;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class MatchingActivity extends AppCompatActivity {

    public TextView recipeTitle;
    public TextView likeDislikeStatus;
    public ImageView recipeImage;
    private StorageReference recipeDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching);
        recipeTitle = (TextView) this.findViewById(R.id.recipeTitle);
        recipeImage = (ImageView) this.findViewById(R.id.recipeImage);
        likeDislikeStatus = (TextView) this.findViewById(R.id.likeDislikeStatus);
        likeDislikeStatus.setText("");
        recipeDatabase = FirebaseStorage.getInstance().getReference();

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
        loadNextRecipe("Black Circle Recipe");
    }

    public void xMarkPressed (View v){
        //save likes into User Profile for retrieval later
        delayedStatus(500, "Disliked", "#FF0000");
        loadNextRecipe("Black Circle Recipe Again...");
    }

    //Ideally contains picture/title/recipe
    //There has to be some methods to navigate the database and retrieve items from them
    public void loadNextRecipe(final String titleOfRecipe){
        recipeTitle.postDelayed(new Runnable() {
            @Override
            public void run () {
                recipeTitle.setText(titleOfRecipe);
                recipeImage.setImageResource(R.drawable.ic_check_circle_black_24px); //change to picture in database...
            }
        }, 1000); // sets delay before updating picture and text
    }
    // robert has a recipeObject.getTitle();
    // call recipeObject.getPicture();




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
