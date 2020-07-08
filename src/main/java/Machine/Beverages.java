package Machine;

import java.util.HashMap;

/**
 * Created by sushant.s on 08/07/20.
 */
public class Beverages {

    public String name;

    public HashMap<String,Double> composition;

    Beverages(String name,  HashMap<String,Double> composition)
    {
        this.composition=composition;
        this.name=name;
    }
}
