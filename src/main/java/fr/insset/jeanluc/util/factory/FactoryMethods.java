/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.util.factory;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author jldeleage
 */
public class FactoryMethods {


    public final static String  LIST    = "list";
    public final static String  SET     = "set";

    
    public  static  <T>   List<T>   newList(Class<T> inClass) throws InstantiationException {
        FactoryRegistry registry = FactoryRegistry.getRegistry();
        AbstractFactory factory = registry.getFactory(LIST);
        if (factory == null) {
            synchronized (registry) {
                factory = registry.getFactory(LIST);
                if (factory == null) {
                    factory = new FactoryRegistryImpl.DefaultFactory(LinkedList.class);
                    registry.registerFactory(LIST, factory);
                }       // factory == null
            }       // synchronized
        }       // factory == null
        return (List<T>)factory.newInstance();
    }

    
    public  static  <T>   Set<T>   newSet(Class<T> inClass) throws InstantiationException {
        FactoryRegistry registry = FactoryRegistry.getRegistry();
        AbstractFactory factory = registry.getFactory(SET);
        if (factory == null) {
            synchronized (registry) {
                factory = registry.getFactory(SET);
                if (factory == null) {
                    factory = new FactoryRegistryImpl.DefaultFactory(HashSet.class);
                    registry.registerFactory(SET, factory);
                }
            }       // synchronized
        }       // factory == null
        return (Set<T>)factory.newInstance();
    }




}
