package team9.munchiematch;

/**
 * Created by Yoku on 3/11/17.
 */
enum Ingredient_Type {
    spice, meat, dairy, fruit, vegetable, nut, bread
};
enum Ingredient_Measurement {cup, tablespoon, teaspoon, pinch, bowl, lbs, grams, leaf, part, liters, gallons, slices,

};

public class Ingredient {
    private Ingredient_Type type;
    private String name;
    private Ingredient_Measurement measurement;
    public Ingredient(Ingredient_Type type, String name, Ingredient_Measurement measurement) {
        this.type = type;
        this.name = name;

    }

    public Ingredient_Type getType() {
        return type;

    }

    public Ingredient_Measurement getMeasurement(){
        return measurement;
    }

    public void setType(Ingredient_Type type){
        this.type = type;
    }

    public void setMeasurement(Ingredient_Measurement measurement) {
        this.measurement = measurement;
    }

    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "Ingredient{" + "name=" + name +'}';
    }
}

