package common;

import java.util.concurrent.ThreadLocalRandom;

public class IngredientGenerator {
    public static Ingredient infiniteIngredient(){
        int randomNum = ThreadLocalRandom.current().nextInt(1, 3 + 1);
        switch (randomNum){
            case 2: return new Matches();
            case 3: return new Tobacco();
            default: return new Paper();

        }
    }
}
