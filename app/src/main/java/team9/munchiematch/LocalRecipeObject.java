package team9.munchiematch;

/**
 * Created by StarWizard on 4/15/2017.
 */

public class LocalRecipeObject {
    int recipeImageID;
    boolean likedDisliked;
    boolean seen = false;
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
    public void setSeen() {
        seen = true;
    }
    public boolean readSeenStatus(){
        return seen;
    }
    public boolean readLikedStatus(){
        return likedDisliked;
    }
}
