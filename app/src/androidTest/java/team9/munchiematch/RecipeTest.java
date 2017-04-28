package team9.munchiematch;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;

/**
 * Created by rober on 3/20/2017.
 */

@RunWith(AndroidJUnit4.class)
public class RecipeTest {
    @Test
    public void testSetTitle() {
        String[] data = {"Grilled Cheese", "Pot Pie", "Muffins", "Chocolate", "Eel", ""};
        for(int i = 0;i < data.length;i++) {
            Recipe test = new Recipe();
            test.setTitle(data[i]);

            assertEquals(test.getTitle(), data[i]);
        }

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
