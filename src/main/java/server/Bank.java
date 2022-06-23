package server;

import common.Ingredient;

import java.util.ArrayList;

public class Bank {
    private ArrayList<Ingredient> bank = new ArrayList<Ingredient>();

    public Bank(ArrayList<Ingredient> bank) {
        this.bank = bank;
    }

    public ArrayList<Ingredient> getBank() {
        return bank;
    }

    public void setBank(ArrayList<Ingredient> bank) {
        this.bank = bank;
    }

    public Ingredient popIngredient(){
        Ingredient ingredientToReturn = this.bank.get(0);
        this.bank.remove(ingredientToReturn);
        return  ingredientToReturn;
    }

    public void pushIngredient(Ingredient ingredient) {
        this.bank.add(ingredient);

    }
}
