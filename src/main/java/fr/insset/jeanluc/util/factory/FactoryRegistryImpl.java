package fr.insset.jeanluc.util.factory;



import fr.insset.jeanluc.util.hierarchy.Hierarchy;
import java.awt.event.HierarchyBoundsAdapter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 * Factory registries are organized into a tree. The root is a singleton but
 * anyone can create a child of any already available registry.
 *
 * @author jldeleage
 */
public class FactoryRegistryImpl implements FactoryRegistry {


    // This class is no more a singleton.
    // Actually, it is a hierarchy. Each action receives its own factory
    // to enable hierarchical contexts.
    // A root registry is provided through the static variable


    //========================================================================//
    //                                 R O O T                                //
    //========================================================================//


    private FactoryRegistryImpl() {
    }


    public FactoryRegistryImpl(FactoryRegistry parent) {
        if (parent == null) {
            // TODO : should we raise an exception ?
        }
        this.parent = parent;
    }


//    private FactoryRegistryImpl() {
//    }
    public static FactoryRegistry getRegistry() {
        return registry;
    }



    //========================================================================//
    //            L O C A L   R E G I S T R Y   M A N A G E M E N T           //
    //========================================================================//


    @Override
    public FactoryRegistry createChild() {
        return new FactoryRegistryImpl(this);
    }




    @Override
    public FactoryRegistry getParent() {
        return parent;
    }

    public void setParent(Hierarchy<AbstractFactory> parent) {
        this.parent = (FactoryRegistry) parent;
    }


    @Override
    public AbstractFactory   getLocalProperty(Object inKey) {
        return factories.get(inKey);
    }
    


    //========================================================================//
    //                   F A C T O R Y   M A N A G E M E N T                  //  
    //========================================================================//


    public AbstractFactory getLocalFactory(String inName) {
        return factories.get(inName);
    }

    @Override
    public void registerFactory(String inName, AbstractFactory inFactory) {
        factories.put(inName, inFactory);
    }

    @Override
    public <T> void registerFactory(String inName, Class<T> inClass) {
        registerFactory(inName, new DefaultFactory(inClass));
    }

    @Override
    public void registerDefaultFactory(String inName, AbstractFactory inFactory) {
        AbstractFactory factory = factories.get(inName);
        if (factory == null) {
            factories.put(inName, inFactory);
        }
    }       // registerDefaultFactory

    @Override
    public <T> void registerDefaultFactory(String inName, Class<T> inClass) {
        registerDefaultFactory(inName, new DefaultFactory(inClass));
    }



    //========================================================================//


    private Map<String, AbstractFactory>        factories = new HashMap<>();

    /**
     * Root of the tree.
     */
    private static  FactoryRegistry     registry = new FactoryRegistryImpl();
    /**
     * The tree is use "bottom up" : from child to parent. So we do not need
     * to keep children registries but the parent registry.
     */
    private         FactoryRegistry     parent;



    //========================================================================//
    //                         H E L P E R   C L A S S                        //
    //========================================================================//


    public static class DefaultFactory<T> implements AbstractFactory {

        public DefaultFactory(Class<T> delegate) {
            this.delegate = delegate;
        }

        @Override
        public Object newInstance(Object... inArgs) throws InstantiationException {
            try {
                // Why should we test ? The general case (anny number of args)
                // works fine in the special case (no args)
                int nbArgs = inArgs.length;
                // If there are no args, we try to use a constructor without
                // parameters. If there is no such constructor, we try to
                // use a constructor with one variable argument.
                if (nbArgs == 0) {
                    try {
                        return delegate.newInstance();
                    }
                    catch (Exception e) {
                        try {
                            // "silent" exception : we are going to look for
                            // a zero or one parameter constructor.
                            Constructor constructor = delegate.getConstructor(new Class[0]);
                            return constructor.newInstance(new Object[] { inArgs });
                        } catch (NoSuchMethodException | SecurityException | InstantiationException ex) {
                            Logger.getLogger(FactoryRegistryImpl.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                Constructor<?>[] constructors = delegate.getConstructors();
                constructorLoop: for (Constructor constructor : constructors) {
                    int parameterCount = constructor.getParameterCount();
                    if (parameterCount != 1) {
                        continue;
                    }
                    Parameter parameter = constructor.getParameters()[0];
                    if (parameter.isVarArgs()) {
                        return constructor.newInstance(new Object[] { inArgs });                        
                    }
                }       // constructorLoop
                // We have found no constructor, either without parameters
                // or with a varargs parameter
                throw new InstantiationException("No suitable constructor");
            } catch (InstantiationException ex) {
                throw ex;
            } catch (IllegalAccessException 
                            |IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(FactoryRegistryImpl.class.getName()).log(Level.SEVERE, null, ex);
                throw new InstantiationException(ex.getLocalizedMessage());
            }
        }

        private Class<T>    delegate;
    }       // DefaultFactory


}       // FactoryRegistry
