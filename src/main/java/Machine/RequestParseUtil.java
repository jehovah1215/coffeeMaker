package Machine;

import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sushant.s on 08/07/20.
 */

/**
 * This class will parse the inputJson Request
 */
public class RequestParseUtil {

    public static HashMap<String,Object> processJsonRequest(String jsonFilePath){
        HashMap<String,Object> parsedRequestObject = new HashMap<String, Object>();

        JSONParser parser = new JSONParser();

        try {
            FileReader reader = new FileReader(jsonFilePath);
            Object obj = parser.parse(reader);

            JSONObject jsonObject =  (JSONObject) obj;

            HashMap<String, Object> map = new Gson().fromJson(jsonObject.toString(), HashMap.class);
            Map<String, Object> machine= (Map<String, Object>) map.get("machine");
            Map<String, Object> beverages= (Map<String, Object>) machine.get("beverages");
            Map<String, Double> outlets = (Map<String, Double>)machine.get("outlets");
            int outletCount = outlets.get("count_n").intValue();
            parsedRequestObject.put("outlets",outletCount);
            Map<String,Double> total_items_quantity = (Map<String, Double>)machine.get("total_items_quantity");
            HashMap<String,Double> storage = new HashMap<String, Double>(total_items_quantity);
            parsedRequestObject.put("storage",storage);
            List<Beverages> beveragesList = new ArrayList<Beverages>();
            for(String type : beverages.keySet())
            {
                String drink =type;
                Map<String,Double> composition = (Map<String, Double>)(beverages.get(type));
                HashMap<String,Double> comp = new HashMap<String, Double>(composition);
                beveragesList.add(new Beverages(drink,comp));
            }
            parsedRequestObject.put("beverages",beveragesList);

        } catch (ParseException e1) {
            e1.printStackTrace();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return parsedRequestObject;
    }



}
