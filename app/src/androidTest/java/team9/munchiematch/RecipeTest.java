package team9.munchiematch;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by rober on 3/20/2017.
 */

@RunWith(AndroidJUnit4.class)
public class RecipeTest {
    @Test
    public void testSetTitle() {
        String testTitle = "Mashed Potatoes";
        Recipe testRecipe = new Recipe();
        testRecipe.setTitle(testTitle);

        assert(!testTitle.equals(testRecipe.getTitle()));

    }

    @Test
    public void testGetTitle() {

    }

    @Test
    public void testSetIngredients() {

    }

    @Test
    public void testGetRecipeMealType() {

    }

    @Test
    public void testSetRecipeMealType() {

    }

    @Test
    public void testFindIngredient() {
        ;
    }

    @Test
    public void testSetPrivate() {

    }

    @Test
    public void testSetPublic() {

    }
}
