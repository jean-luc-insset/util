package fr.insset.jeanluc.ete.util.hierarchy;


import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jldeleage
 */
public class ExempleImpl implements Exemple {


    @Override
    public Object sayHello(Object obj) {
        return hello + " " + obj; 
    }



    public void setHello(Object inHello) {
        hello = (String)inHello;
    }


    public String   getHello() {
        return hello;
    }


    public  Object  getValue(String inName) {
        return map.get(inName);
    }

    public  boolean    setValue(String inName, Object inValue) {
        map.put(inName, (String)inValue);
        return true;
    }



    private     String      hello;

    private     Map<String, String>     map = new HashMap<>();

}
