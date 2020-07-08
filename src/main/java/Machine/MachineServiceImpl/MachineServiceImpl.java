package Machine.MachineServiceImpl;

import Machine.IMachineService.IMachineService;
import Machine.Inventory;

import java.util.*;

/**
 * Created by sushant.s on 08/07/20.
 */
public class MachineServiceImpl implements IMachineService {
    Inventory inv = Inventory.getInventoryInstance();
    HashMap<String,Double> storage = inv.inventoryMap;

    public boolean brewDrink(HashMap<String, Double> receipe, String drink) {
        for(String ingredients : receipe.keySet()) {
            HashSet<String> usedIngredients = new HashSet<String>();
            Double required  = receipe.get(ingredients);
            Double available = storage.get(ingredients);
            if(available==null)
            {
                reset(receipe,usedIngredients);
                System.out.println(drink + "cannot be prepared b/c " + ingredients + "is not available");
                return false;
            }

            else if(available<required) {
                reset(receipe,usedIngredients);
                System.out.println(drink + "cannot be prepared b/c " + ingredients + "is not sufficient");
                return false;

            }

            usedIngredients.add(ingredients);
            storage.put(ingredients,available-required);

        }
        return true;
    }

    private void reset (HashMap<String, Double> receipe ,  HashSet<String> usedIngredients ) {

        for(String ing : usedIngredients) {
                storage.put(ing,receipe.get(ing)+storage.get(ing));
            }


    }

}
