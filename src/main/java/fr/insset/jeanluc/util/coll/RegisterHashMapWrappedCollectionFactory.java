/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.util.coll;

import fr.insset.jeanluc.util.factory.AbstractFactory;
import java.util.Collection;
import fr.insset.jeanluc.util.factory.FactoryRegistry;

/**
 *
 * @author jldeleage
 */
public abstract class RegisterHashMapWrappedCollectionFactory {
    
    public static void register() {
        FactoryRegistry registry = FactoryRegistry.getRegistry();
        AbstractFactory factory = registry.getFactory(MapWrappedCollection.HASH_MAP_WRAPPED_COLLECTION);
        // double checking. Actually, it would not hurt if the factory were
        // to be defined twice.
        if (factory == null) {
            synchronized(RegisterHashMapWrappedCollectionFactory.class) {
                if (factory == null) {
                    factory = new AbstractFactory() {
                        @Override
                        public Object newInstance(Object... inArgs) throws InstantiationException {
                            if (inArgs.length == 1) {
                                return new DefaultMapWrappedCollection((Collection)inArgs[0]);
                            }
                            else {
                                return new CompositeMapWrappedCollection((Collection) inArgs[0], (MapWrappedCollection)inArgs[1]);
                            }
                        }       // newInstance
                        @Override
                        public Class getBuiltClass() {
                            return DefaultMapWrappedCollection.class;
                        }
                    };      // new AbstractFactory
                    registry.registerFactory(MapWrappedCollection.HASH_MAP_WRAPPED_COLLECTION, factory);
                }
            }       // synchronized
        }       //factory == null
    }       // register()

}
