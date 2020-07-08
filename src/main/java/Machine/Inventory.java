package Machine;

import java.util.HashMap;

public class Inventory {

    private static Inventory inventory_Instance;
    public HashMap<String,Double> inventoryMap;

    private Inventory() {
        inventoryMap = new HashMap<String, Double>();

    }
    public static Inventory getInventoryInstance()
    {
        if(inventory_Instance==null) {
            synchronized (Inventory.class) {
                if (inventory_Instance == null) {
                    inventory_Instance = new Inventory();

                }
            }
        }

        return inventory_Instance;
    }

    public  HashMap<String,Double> setInventoryMap(HashMap<String,Double> inventoryMap){
       this.inventoryMap = inventoryMap;
        return inventoryMap;
    }
    public  HashMap<String,Double> getInventoryMap(){

                return this.inventoryMap;
    }
}
