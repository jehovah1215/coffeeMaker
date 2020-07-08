package Machine.Interfaces;

import java.util.HashMap;

/**
 * Created by sushant.s on 09/07/20.
 */
public interface IBrewMachine {

    void serveDrinks(HashMap<String, Object> parseMap);

    HashMap<String, Double> getInventoryStatus();

    void refillIngredient();

    void initBrewMachine(HashMap<String, Object> parseMap);

    void flushBrewMachine();
}

