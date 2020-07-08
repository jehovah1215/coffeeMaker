package Machine;

import java.util.HashMap;

/**
 * Created by sushant.s on 08/07/20.
 */
public class Beverages {

    String name;

    HashMap<String,Double> composition;

    Beverages(String name,  HashMap<String,Double> composition)
    {
        this.composition=composition;
        this.name=name;
    }
}
