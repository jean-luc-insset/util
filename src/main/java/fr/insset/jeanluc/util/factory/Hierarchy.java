/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.util.factory;

/**
 *
 * @author jldeleage
 */
public interface Hierarchy {

    public  Object          getLocalProperty(Object inKey);

    public  default Object  getProperty(Object inKey) {
        Hierarchy current = this;
        do {
            Object result = getLocalProperty(inKey);
            if (result != null) {
                return result;
            }
            current = current.getParent();
            if (current == null) {
                return null;
            }
        } while (true);
    }
    

    public  Hierarchy       getParent();

}
