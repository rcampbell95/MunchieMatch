package team9.munchiematch;

/**
 * Created by StarWizard on 4/15/2017.
 */

public class LocalRecipeObject {
    int recipeImageID;
    boolean likedDisliked;
    String recipeTitle;

    public LocalRecipeObject(int location, String title){
        recipeImageID = location;
        recipeTitle = title;
    }
    public void setDisliked (){
        likedDisliked = false;
    }
    public void setLiked(){
        likedDisliked = true;
    }
}
