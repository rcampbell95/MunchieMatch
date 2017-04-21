package team9.munchiematch;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.Intent;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class IngredientSearch extends AppCompatActivity {
    MaterialSearchView searchView;
    ListView lstView;

    public static void main(String[] args) {
        Ingredient i1 = new Ingredient(Ingredient_Type.dairy, "American Cheese", Ingredient_Measurement.slices);
        Ingredient i2 = new Ingredient(Ingredient_Type.bread, "White Bread", Ingredient_Measurement.slices);
        Ingredient i3 = new Ingredient(Ingredient_Type.meat, "Sliced Ham", Ingredient_Measurement.slices);

        List<Ingredient> ingredients = Arrays.asList(i1,i2,i3);
        List<Ingredient> results = new ArrayList<>();
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_search);


        Intent intent = getIntent();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Ingredients");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));

        lstView = (ListView) findViewById(R.id.lstView);
        //ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lstSource);
        //lstView.setAdapter(adapter);


        searchView = (MaterialSearchView) findViewById(R.id.search_view);

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {

                //If closed Search View , lstView will return default
                lstView = (ListView) findViewById(R.id.lstView);
                //ArrayAdapter adapter = new ArrayAdapter(IngredientSearch.this, android.R.layout.simple_list_item_1, lstSource);
                //lstView.setAdapter(adapter);

            }
        });

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText != null && !newText.isEmpty()) {
                    List<String> lstFound = new ArrayList<String>();
                    //for (String item : lstSource) {
                    //    if (item.contains(newText))
                    //        lstFound.add(item);
                    //}

                    ArrayAdapter adapter = new ArrayAdapter(IngredientSearch.this, android.R.layout.simple_list_item_1, lstFound);
                    lstView.setAdapter(adapter);
                } else {
                    //if search text is null
                    //return default
                    //ArrayAdapter adapter = new ArrayAdapter(IngredientSearch.this, android.R.layout.simple_list_item_1, lstSource);
                    //lstView.setAdapter(adapter);
                }
                return true;
            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }
}
