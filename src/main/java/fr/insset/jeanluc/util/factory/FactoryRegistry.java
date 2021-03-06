package fr.insset.jeanluc.util.factory;

import fr.insset.jeanluc.util.hierarchy.Hierarchy;



/**
 * The factory registry uses a hierachical structure&nbsp;: any registry
 * may have a parent registry.<br>
 * There are two ways of using that hierarchy&nbsp;:<ul>
 * <li>through parameters&nbsp;: passing the registry through a parameter
 * of each called method allows the developer to control which registry is
 * used at any time</li>
 * <li>through a ThreadLocal variable and the methods {@link #getRegistry() 
 * getRegistry()} and {@link
 * #setRegistry(fr.insset.jeanluc.ete.util.factory.FactoryRegistry) 
 * setRegistry}.</li>
 * </ul>
 * There is a "current" registry one can get through the static
 * method {@link #getRegistry() getRegistry() }.<br>
 * 
 *
 * @author jldeleage
 */
public interface FactoryRegistry extends Hierarchy<AbstractFactory> {


    public final static String  FACTORY_REGISTRY = "factory_registry";

    public final static ThreadLocal<FactoryRegistry> registry = new ThreadLocal<>();



    //========================================================================//
    //                 H I E R A R C H Y   M A N A G E M E N T                //
    //========================================================================//


    public FactoryRegistry      createChild();

    public FactoryRegistry      getParent();


    //------------------------------------------------------------------------//
    // Concurrence and hierearchy
    //------------------------------------------------------------------------//


    public static FactoryRegistry   getRegistry() {
        FactoryRegistry result = registry.get();
        if (result == null) {
            result = new FactoryRegistryImpl(null);
            registry.set(result);
        }
        return result;
    }



    public static void setRegistry(FactoryRegistry inRegistry) {
        registry.set(inRegistry);
    }


    public static FactoryRegistry  pushNewRegistry() {
        FactoryRegistry     registry = getRegistry();
        FactoryRegistry child = registry.createChild();
        setRegistry(child);
        return child;
    }


    /**
     * Replaces the current registry by its parent.
     */
    public static void popRegistry() {
        registry.set(registry.get().getParent());
    }


    //========================================================================//
    //                 F A C T O R I E S   M A N A G E M E N T                //
    //========================================================================//



    //------------------------------------------------------------------------//
    // REGISTRATION                                                           //
    //------------------------------------------------------------------------//

    void registerDefaultFactory(Object inName, AbstractFactory inFactory);

    <T> void registerDefaultFactory(Object inName, Class<T> inClass);

    void registerFactory(Object inName, AbstractFactory inFactory);

    <T> void registerFactory(Object inName, Class<T> inClass);


    //------------------------------------------------------------------------//
    // QUERYING                                                               //
    //------------------------------------------------------------------------//


    public  AbstractFactory         getLocalFactory(Object inName);
    public  default AbstractFactory getFactory(Object inName) {
        FactoryRegistry current = this;
        while (current != null) {
            AbstractFactory localFactory = current.getLocalFactory(inName);
            if (localFactory != null) {
                return localFactory;
            }
            current = current.getParent();
        }
        return null;
    }


    //========================================================================//
    // S H O R T C U T S                                                      //
    //========================================================================//


    public static Object newInstance(Object inId) throws InstantiationException, IllegalAccessException {
        FactoryRegistry registry = getRegistry();
        AbstractFactory factory = registry.getFactory(inId);
        if (factory == null && (inId instanceof Class)) {
            Class   theClass = (Class)inId;
            return theClass.newInstance();
        }
        return factory.newInstance();
    }

    public static void register(String inId, Class inClass) {
        FactoryRegistry registry = getRegistry();
        if (registry == null) {
            registry = new FactoryRegistryImpl(null);
            setRegistry(registry);
        }
        registry.registerDefaultFactory(inId, inClass);
    }


}

