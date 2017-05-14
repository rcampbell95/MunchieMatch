package team9.munchiematch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class detailedRecipeView extends AppCompatActivity {

    public TextView detailedRecipeTitle;
    public ImageView detailedRecipeImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_recipe_view);


        detailedRecipeTitle = (TextView) this.findViewById(R.id.dRTitle);
        detailedRecipeImage = (ImageView) this.findViewById(R.id.dRImage);
        detailedRecipeTitle.setText(LikedRecipesFragment.titleID);
        detailedRecipeImage.setImageResource(LikedRecipesFragment.imageID);
    }

    public void timerButtonPressed (View v){
        Intent intent = new Intent(this, TimerActivity.class);
        startActivity(intent);
    }
}
