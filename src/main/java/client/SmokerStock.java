package client;

import common.Ingredient;

import java.util.ArrayList;

public class SmokerStock {
    private Ingredient infiniteIngridient ;
    private ArrayList<Ingredient> stock = new ArrayList<Ingredient>();

    public SmokerStock(Ingredient infiniteIngridient) {
        this.infiniteIngridient = infiniteIngridient;
    }
    public void addIngredient(Ingredient ingredient){
        this.stock.add(ingredient);
    }
    public void removeIngredient(Ingredient ingredient){

        this.stock.remove(this.stock.indexOf(ingredient));

    }

    public Ingredient getInfiniteIngridient() {
        return infiniteIngridient;
    }

    public void setInfiniteIngridient(Ingredient infiniteIngridient) {
        this.infiniteIngridient = infiniteIngridient;
    }

    public ArrayList<Ingredient> getStock() {
        return stock;
    }

    public void setStock(ArrayList<Ingredient> stock) {
        this.stock = stock;
    }
}
