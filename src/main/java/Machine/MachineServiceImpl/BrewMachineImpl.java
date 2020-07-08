package Machine.MachineServiceImpl;

import Machine.Beverages;
import Machine.Interfaces.IBrewMachine;
import Machine.Inventory;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by sushant.s on 09/07/20.
 */
public class BrewMachineImpl implements IBrewMachine {
    final double THRESHOLD_VALUE = 5.0;// minimum value check to refill again
    final double REFILL_AMOUNT = 100.0; // amount by which you want to refill

    public void serveDrinks(HashMap<String, Object> parseMap) {
        int outlets = (Integer) parseMap.get("outlets");
        ExecutorService executorService = Executors.newFixedThreadPool(outlets);
        final  List<Beverages> beveragesList = (List<Beverages>) parseMap.get("beverages");
        final int size = beveragesList.size();
        for(int i=0 ; i<size ;i++) {
            String drinkName = beveragesList.get(i).name;
            BrewThread brewThread = new BrewThread(beveragesList.get(i).composition, drinkName);
            executorService.execute(brewThread);
        }
        executorService.shutdown();
    }

    public HashMap<String, Double> getInventoryStatus() {
        return Inventory.getInventoryInstance().getInventoryMap();
    }

    public void refillIngredient() {

        HashMap<String, Double> status = getInventoryStatus();
        for (String ingredient : status.keySet()) {
            if (status.get(ingredient) < THRESHOLD_VALUE) {
                System.out.println(ingredient + " is running low.....refilling it ");
                status.put(ingredient, status.get(ingredient) + REFILL_AMOUNT);
            }
        }
    }

    public void initBrewMachine(HashMap<String, Object> parseMap) {
        Inventory inventory = Inventory.getInventoryInstance();
        HashMap<String, Double> storage = (HashMap<String, Double>) parseMap.get("storage");
        inventory.setInventoryMap(storage);

    }

    public void flushBrewMachine() {
        Inventory inventory = Inventory.getInventoryInstance();
        inventory.setInventoryMap(new HashMap<String, Double>());

    }
}
