
package fr.insset.jeanluc.util.visit;

import fr.insset.jeanluc.util.EteException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 * An instance of this class can visit anything.<br>
 * To achieve this goal, the instance can maintain a map linking a method to
 * any class to visit.<br>
 *
 * @author jldeleage
 */
public class DynamicVisitorUtil {

    /**
     * Scans this object looking for methods starting with <code>inPrefix</code>.
     * For each such method, looks for a class in the packages with a
     * matching name.<br>
     * For example if the prefix is <code>visit</code>, there is a method
     * named <code>visitBinaryOperation</code> and a class named
     * <code>BinaryOperation</code>, the couple (BinaryOperation.class,
     * visitBinaryOperation) is registered.
     * 
     * @param inPrefix 
     */
    public void register(String inPrefix, String... inPackages) {
        Method[] methods = getClass().getMethods();
        for (int i=0 ; i<inPackages.length ; i++)
        for (Method m : methods) {
            String name = m.getName();
            if (name.startsWith(inPrefix)) {
                name = name.substring(inPrefix.length());
                for (String aPackage : inPackages) {
                    if (! aPackage.endsWith(".")) {
                        aPackage += ".";
                    }
                    try {
                        Class<?> forName = Class.forName(aPackage + name);
                        register (forName, m);
                    }
                    catch (Exception e) {
                        System.out.println("Class not found : " + aPackage + name);
                        // this exception does not matter : there is no class
                        // with the selected name in this package
                    }
                }       // loop on packages
            }       // name.startsWith
        }       // loop on methods
    }       // register


    public void register(Class inClass, Method inMethod) {
        visitingMethods.put(inClass, inMethod);
    }


    public Object genericVisit(Object inVisited, Object inParameter) throws EteException {
        Class visitedClass = inVisited.getClass();
        Method lookFor = lookFor(visitedClass);
        if (lookFor != null) {
            try {
                Object result = lookFor.invoke(this, new Object[] {inVisited, inParameter });
                return result;
            } catch (IllegalAccessException | IllegalArgumentException| InvocationTargetException ex) {
                Logger.getLogger(DynamicVisitorUtil.class.getName()).log(Level.SEVERE, null, ex);
                throw new EteException(ex);
            }
        }
        return null;
    }




    /**
     * Returns the method which has been registered for this class or a
     * super-class of this class (or for an interface implemented by this
     * class).
     * 
     * @param inClass
     * @return first registered matching method
     */
    protected Method lookFor(Class inClass) {
        Class current = inClass;
        do {
            Method m = visitingMethods.get(current);
            if (m!= null) {
                return m;
            }
            current = current.getSuperclass();
        } while (current != null);
        Class[] interfaces = inClass.getInterfaces();
        for (Class anInterface : interfaces) {
            Method m = visitingMethods.get(anInterface);
            if (m!= null) {
                return m;
            }            
        }
        return null;
    }

    protected Method lookForInterface(Class inInterface) {
        Method method = visitingMethods.get(inInterface);
        if (method != null) {
            return method;
        }
        for (Class anInterface : inInterface.getInterfaces()) {
            method = lookForInterface(anInterface);
            if (method != null) {
                return method;
            }
        }
        return null;
    }

    private Map<Class, Method>      visitingMethods = new HashMap<>();


}


