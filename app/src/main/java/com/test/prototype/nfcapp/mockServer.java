package com.test.prototype.nfcapp;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Tom on 21/03/2015.
 */
public class mockServer {

    //holding data to pretend to be a database until the server is up and running
    public static class items{
       public static HashMap<String, Item> itemsList = new HashMap<String, Item>();
    }
}
