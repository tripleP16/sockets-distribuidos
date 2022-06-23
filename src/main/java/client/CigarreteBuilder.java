package client;

import common.Ingredient;

public class CigarreteBuilder {
    private SmokerStock stock;
    private Ingredient infiniteIngredient;

    public CigarreteBuilder(SmokerStock stock, Ingredient infiniteIngredient) {
        this.stock = stock;
        this.infiniteIngredient = infiniteIngredient;
    }

    public Cigarrette build(){
        Ingredient second = null;
        Ingredient third = null;

        for (Ingredient ingredient: this.stock.getStock()) {
            if(second == null){
                if(third == null){
                    if(!ingredient.getId().equals(this.infiniteIngredient.getId())){
                        second = ingredient;
                    }
                }
            }
            if(third == null && second != null){
                if(!second.getId().equals(ingredient.getId())){
                    if(!this.infiniteIngredient.getId().equals(ingredient.getId())){
                        third = ingredient;
                    }
                }
            }

        }
        if(second != null && third != null){
            this.stock.removeIngredient(second);
            this.stock.removeIngredient(third);
            return new Cigarrette(this.infiniteIngredient,second,third);
        }
        return null;
    }

    public SmokerStock getStock(){
        return this.stock;
    }
}
