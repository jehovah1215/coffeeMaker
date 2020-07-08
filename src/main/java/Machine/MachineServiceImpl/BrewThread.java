package Machine.MachineServiceImpl;

import Machine.Inventory;

import java.util.*;

/**
 * Created by sushant.s on 08/07/20.
 */

/**
 * Class which checks if any drink can be brewd or not
 */
public class BrewThread implements   Runnable  {
    HashMap<String, Double> receipe;
    String drink;

    BrewThread(HashMap<String, Double> receipe, String drink)
    {
        this.receipe =receipe;
        this.drink =drink;
    }
    public void run() {
            Inventory inv = Inventory.getInventoryInstance();
            for (String ingredients : receipe.keySet()) {
                HashSet<String> usedIngredients = new HashSet<String>();
                Double required = receipe.get(ingredients);
                Double available = inv.getBykey(ingredients);
                if (available == null) {
                    reset(usedIngredients);//put back used ingredientas if failed to brew drink
                    System.out.println(drink + " cannot be prepared b/c " + ingredients + " is not available");
                    return ;
                } else if (available < required) {
                    reset(usedIngredients);// put back used ingredientas
                    System.out.println(drink + " cannot be prepared b/c " + ingredients + " is not sufficient");
                    return ;

                }

                usedIngredients.add(ingredients);
                inv.putBykey(ingredients, available - required);//check if enough ingredient is available

            }
            System.out.println(drink + " is prepared");



    }
/*
     If failed to brew any drink refil it with the unsed amount which was consumed in process
 */
    private void reset (HashSet<String> usedIngredients ) {
        Inventory inv = Inventory.getInventoryInstance();

        for(String ing : usedIngredients) {
                inv.putBykey(ing,receipe.get(ing)+inv.getBykey(ing));
            }


    }

}
