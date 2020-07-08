package Machine;
import java.util.*;

import Machine.Interfaces.IBrewMachine;
import Machine.MachineServiceImpl.BrewMachineImpl;

/**
 * Created by sushant.s on 08/07/20.
 */
/*
Main class :::

This Project expects inputs are coming from json file and the order of processing requests can be random .
 */
public class BrewApp {


    public static void main(String[] args) {


        /*
        Test Case :1
         */
        IBrewMachine brewMachineUtils = new BrewMachineImpl();
        HashMap<String, Object> parsedRequest = RequestParseUtil.processJsonRequest("/Users/sushant.s/Downloads/MyProjects/src/main/resources/input1.json");// parse the input json
        brewMachineUtils.initBrewMachine(parsedRequest);//init machine
        System.out.println("=====STATUS OF INVENTORY BEFORE =====");
        HashMap<String,Double> prestatus1 = brewMachineUtils.getInventoryStatus();
        System.out.println(prestatus1);
        brewMachineUtils.serveDrinks(parsedRequest);//method to brew drink
        System.out.println("=====STATUS OF INVENTORY AFTER=====");
        HashMap<String,Double> status = brewMachineUtils.getInventoryStatus();
        System.out.println(status);
        System.out.println("=====Automatic Refilling in Process=====");
        brewMachineUtils.refillIngredient();//refilling


        brewMachineUtils.flushBrewMachine();// set zero
        System.out.println("Running Another Test Case" );

        /*
        Test Case :2
         */
        IBrewMachine brewMachineUtils2 = new BrewMachineImpl();
        HashMap<String, Object> parsedRequest2 = RequestParseUtil.processJsonRequest("/Users/sushant.s/Downloads/MyProjects/src/main/resources/input2.json");
        brewMachineUtils2.initBrewMachine(parsedRequest2);
        System.out.println("=====STATUS OF INVENTORY BEFORE =====");
        HashMap<String,Double> prestatus2 = brewMachineUtils.getInventoryStatus();
        System.out.println(prestatus2);
        brewMachineUtils2.serveDrinks(parsedRequest2);//method to brew drink
        System.out.println("=====STATUS OF INVENTORY AFTER=====");
        HashMap<String,Double> status2 = brewMachineUtils2.getInventoryStatus();
        System.out.println(status2);
        System.out.println("=====Automatic Refilling in Process=====");
        brewMachineUtils2.refillIngredient();
        }
    }

