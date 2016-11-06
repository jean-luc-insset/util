/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.ete.util.hierarchy;

/**
 *
 * @author jldeleage
 */
public interface Exemple {

    public Object sayHello(Object obj);

    public void setHello(Object inObj);
    public Object getHello();

    public  Object  getValue(String inName);
    public  boolean setValue(String inName, Object inValue);

}
