package Machine;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import Machine.IMachineService.IMachineService;
import Machine.MachineServiceImpl.MachineServiceImpl;
import com.google.gson.Gson;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Created by sushant.s on 08/07/20.
 */
public class MachineMainClass {

    IMachineService service = new MachineServiceImpl();
    Inventory inventory = Inventory.getInventoryInstance();

    public static void main(String[] args) {
        MachineMainClass mc = new MachineMainClass();
        HashMap<String,Object> parsedRequest = RequestParseUtil.processJsonRequest("/Users/sushant.s/Downloads/MyProjects/src/main/resources/input1.json");
        mc.serveDrinks(parsedRequest);
    }


    void serveDrinks( HashMap<String,Object> parseMap) {
        int outlets = (Integer) parseMap.get("outlets");
        HashMap<String,Double> storage =  (HashMap<String,Double>)parseMap.get("storage");
        inventory.inventoryMap=storage;
        List<Beverages> beveragesList = (List<Beverages>)parseMap.get("beverages");
        for (int i=0;i<beveragesList.size();i++) {
            String drinkName =beveragesList.get(i).name;
           boolean status = service.brewDrink(beveragesList.get(i).composition,drinkName);
           if(status)
               System.out.println("Here is your "+ drinkName);
            else
               System.out.print("Argghh :( ");
        }

    }
}
